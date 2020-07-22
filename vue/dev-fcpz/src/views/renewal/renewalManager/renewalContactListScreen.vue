<template>
  <div>
    <div class="contactList-screen">
      <div v-if="renewalScreenType != '4'">
        <p class="title">车辆种类</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in carTypeList"
            :key="index"
            :class="[
              queryData.carType == item.value ? 'screen-button--actived' : ''
            ]"
            @click="addData(item.value, 'carType')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <div class="insurance-data">
        <p class="title">距保单到期日</p>
        <van-row class="policyDate">
          <div>
            <span @click="showPopup = true" class="policy-data--start">{{
              renewanlDate.minRespEndDate || "开始日期"
            }}</span>
            <van-popup v-model="showPopup" position="bottom" :overlay="false">
              <van-datetime-picker
                v-model="popDate.minRespEndDate"
                type="date"
                :max-date="nowDate"
                @cancel="showPopup = false"
                @confirm="
                  () => {
                    this.confirm_startDate();
                  }
                "
              />
            </van-popup>
            <span class="policy-data--line">-</span>
            <span @click="showPopup_ = true" class="policy-data--end">{{
              renewanlDate.maxRespEndDate || "结束日期"
            }}</span>
            <van-popup v-model="showPopup_" position="bottom" :overlay="false">
              <van-datetime-picker
                v-model="popDate.maxRespEndDate"
                type="date"
                :max-date="nowDate"
                @cancel="showPopup_ = false"
                @confirm="
                  () => {
                    this.confirm_endDate();
                  }
                "
              />
            </van-popup>
          </div>
        </van-row>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in renewalScreenType === '3'
              ? type3_insuranceData
              : insuranceData"
            :key="index"
            :class="[
              queryData.dueDate === item.value ? 'screen-button--actived ' : ''
            ]"
            @click="addData(item.value, 'dueDate')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <div class="is-renewal" v-if="this.renewalScreenType === '3'">
        <p class="title">同业是否已续保</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in transferFlagList"
            :key="index"
            :class="[
              queryData.transferFlag === item.value
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'transferFlag')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <div class="renewal-reason" v-if="this.renewalScreenType === '3'">
        <p class="title">脱保原因是否已填写</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in offReasonFlagList"
            :key="index"
            :class="[
              queryData.offReasonFlag === item.value
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'offReasonFlag')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <!-- 续保认领任务状态 -->
      <div class="task-status--claim" v-if="this.renewalScreenType === '4'">
        <p class="title">
          任务状态<span @click="checkAll">{{
            checkAllFlag ? "全选" : "取消"
          }}</span>
        </p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in taskStatusClaim"
            :key="index"
            :class="[
              curHandleStatus.indexOf(item.value) != -1
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'curHandleStatus')"
            >{{ item.label }}</van-col
          >
        </van-row>
        <p
          class="more-task--status"
          @click="
            () => {
              this.showMore = !this.showMore;
              showMore
                ? this.taskStatusClaim.push(...this.taskStatusClaimMore)
                : this.taskStatusClaim.splice(6);
            }
          "
        >
          {{ !this.showMore ? "展开更多" : "收起" }}
          <van-icon :name="`${!this.showMore ? 'arrow-down' : 'arrow-up'}`" />
        </p>
      </div>
      <div class="task-status" v-else>
        <p class="title">任务状态</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in taskStatus"
            :key="index"
            :class="[
              queryData.curHandleStatus === item.value
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'curHandleStatus')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <div class="insurance-amount">
        <p class="title">上年保费金额</p>
        <van-row>
          <van-col
            span="8"
            class="screen-button"
            v-for="(item, index) in insuranceAmount"
            :key="index"
            :class="[
              queryData.renewalAmount === item.value
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'renewalAmount')"
            >{{ item.label }}</van-col
          >
          <van-col span="8" class="screen-button">
            <van-field
              v-model="amount.lastPremiumStart"
              placeholder="最低保费"
              :formatter="formatterAmount"
              :border="false"
            />
            <!-- <span class="amount-section--line">-</span> -->
          </van-col>
          <van-col span="8" class="screen-button">
            <van-field
              v-model="amount.lastPremiumEnd"
              placeholder="最高保费"
              :formatter="formatterAmount"
            />
          </van-col>
        </van-row>
      </div>
      <div class="insurance-amount" v-if="this.renewalScreenType === '4'">
        <p class="title">我司续保状态</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in renewedStatus"
            :key="index"
            :class="[
              queryData.renewedStatus === item.value
                ? 'screen-button--actived '
                : ''
            ]"
            @click="addData(item.value, 'renewedStatus')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
      <div class="insurance-amount" v-if="this.renewalScreenType === '4'">
        <p class="title">是否已报价</p>
        <van-row>
          <van-col
            class="screen-button"
            span="8"
            v-for="(item, index) in isOffer"
            :key="index"
            :class="[
              queryData.isQuote === item.value ? 'screen-button--actived ' : ''
            ]"
            @click="addData(item.value, 'isQuote')"
            >{{ item.label }}</van-col
          >
        </van-row>
      </div>
    </div>
    <div class="contactList-screen--footer">
      <van-button type="default" @click="canel">取消</van-button>
      <van-button type="primary" color="#3B8AFE" @click="submit"
        >确定</van-button
      >
    </div>
  </div>
</template>

<script>
import { Field, Form, Calendar, RadioGroup, Radio, Toast } from "vant";
export default {
  props: [
    "renewalScreenType",
    "showScreen",
    "minRespEndDateProp",
    "maxRespEndDateProp"
  ],
  components: {
    [Field.name]: Field,
    [Form.name]: Form,
    [Calendar.name]: Calendar,
    [RadioGroup.name]: RadioGroup,
    [Radio.name]: Radio,
    [Toast.name]: Toast
  },
  data: () => ({
    checkAllFlag: true,
    popDate: {
      minRespEndDate: new Date(),
      maxRespEndDate: new Date()
    },
    showPopup: false,
    showPopup_: false,
    showMore: false, //续保认领任务状态展开默认为不展开
    renewanlDate: {
      minRespEndDate: "开始日期",
      maxRespEndDate: "结束日期"
    }, //距保单到期日
    minDate: new Date(2020, 1, 1),
    insuranceData: [
      { label: "7天内", value: 7 },
      { label: "15天内", value: 15 },
      { label: "30天内", value: 30 },
      { label: "60天内", value: 60 },
      { label: "90天内", value: 90 }
    ], //到期日
    type3_insuranceData: [
      { label: "15天内", value: -15 },
      { label: "脱保15天以上", value: -30 }
    ], //已过期到期日
    taskStatus: [
      { label: "全部", value: "23,24,25,1,6,7,8,401,901,906,0,22,21,27,28,99" },
      { label: "已认领", value: "1,901,401,23,28,24,99" },
      { label: "未处理", value: "0,21" },
      { label: "已推电销", value: "6,7,8,906,22,27,25" }
    ], //续保任务状态
    taskStatusClaim: [
      { label: "未处理", value: "0" },
      { label: "认领(业务员)", value: "1" },
      { label: "已认领(底线保护)", value: "23" },
      { label: "已指派(底线保护)", value: "24" },
      { label: "认领(绑定保护)", value: "28" },
      { label: "认领(系统默认)", value: "901" }
    ],
    taskStatusClaimMore: [
      { label: "推电销(业务员)", value: "6" },
      { label: "推电销(逾期未操作)", value: "7" },
      { label: "已认领推电销（业务员)", value: "8" },
      { label: "已指派(孤儿单)", value: "21" },
      { label: "推电销(孤儿单)", value: "22" },
      { label: "推电销(互动保护)", value: "27" },
      { label: "推电销(撤回)", value: "401" },
      { label: "推电销(系统默认)", value: "906" },
      { label: "推电销(批量导入)", value: "25" },
      { label: "机构自留", value: "99" }
      // { label: "机构自留", value: "" }
    ],
    curHandleStatus: ["0", "1", "23", "24", "28", "901"], //续保认领任务状态
    insuranceAmount: [
      {
        label: "全部",
        value: 3
      },
      {
        label: "0-2000元",
        value: 0
      },
      {
        label: "2000-5000元",
        value: 1
      },
      {
        label: "5000以上",
        value: 2
      }
    ],
    renewalAmount: "",
    // sortOrder: ["按终保日期", "按投保人", "按车牌号"],
    transferFlagList: [
      { label: "全部", value: "" },
      { label: "是", value: "1" },
      { label: "否", value: "0" }
    ],
    offReasonFlagList: [
      { label: "全部", value: "" },
      { label: "是", value: "1" },
      { label: "否", value: "0" }
    ],
    renewedStatus: [
      { label: "全部", value: "" },
      { label: "已续保", value: "1" },
      { label: "未续保", value: "0" }
    ],
    isOffer: [
      { label: "全部", value: "" },
      { label: "已报价", value: "1" },
      { label: "未报价", value: "0" }
    ], //是否报价
    applicantName: "", //投保人
    licenseNo: "", //车牌号
    insuredCode: "", //保单号
    amount: {
      // amountMin: "", //上一年保费金额最小值
      // amountMax: "" //上一年保费金额最大值
    },
    carTypeList: [
      { label: "全部", value: "" },
      { label: "家用车", value: "1" },
      { label: "非家用车", value: "0" }
    ], //车辆类型
    queryData: {
      renewalAmount: 3,
      //必填入参
      maxRespEndDate: "", //到期日期查询上限
      minRespEndDate: "", //到期日期查询下限
      lastPremiumStart: "", //上年保费金额上限
      lastPremiumEnd: "", //上年保费金额下限
      transferFlag: "", //是否同业已续保:1是0否
      offReasonFlag: "", //是否填写脱保原因:1是0否
      renewedStatus: "", //我司续保状态:1是0否
      isQuote: "", //	是否已报价：1是0否
      pageNum: 1, //页码
      pageSize: 10, //页数
      curHandleStatus: "23,24,25,1,6,7,8,401,901,906,0,22,21,27,28,99", //续保任务状态
      carType: "" // 车辆类型 1 家用车 0 非家用车
    }
  }),
  watch: {
    curHandleStatus(val) {
      if (val.length == 0) {
        this.checkAllFlag = true;
      } else {
        this.checkAllFlag = false;
      }
    },
    "queryData.renewalAmount"(val) {
      if (!val && val != 0) return false;
      let obj = {
        0: {
          lastPremiumEnd: "2000",
          lastPremiumStart: "0"
        },
        1: {
          lastPremiumEnd: "5000",
          lastPremiumStart: "2000"
        },
        2: {
          lastPremiumEnd: "",
          lastPremiumStart: "5000"
        },
        3: {
          lastPremiumEnd: "",
          lastPremiumStart: ""
        }
      };
      this.queryData = Object.assign({}, this.queryData, {
        ...obj[val]
      });
      //把上年保费填充输入框
      this.amount = Object.assign({}, this.amount, {
        ...obj[val]
      });
      obj = null;
    }
  },
  computed: {
    renewalPlatformDay() {
      return (
        JSON.parse(localStorage.getItem("ConfigParams")).renewalPlatformDay ||
        ""
      );
    },
    nowDate() {
      //已过期最大选择时间为当日
      return this.renewalScreenType === "3"
        ? new Date()
        : new Date(2030, 10, 1);
    }
  },
  created() {
    //海南增加45天筛选条件
    if (
      this.renewalScreenType !== "3" &&
      JSON.parse(localStorage.getItem("userInfo")).company.substring(0, 4) ===
        "4601"
    ) {
      this.insuranceData.splice(3, 0, { label: "45天内", value: 45 });
    }
  },
  mounted() {
    switch (this.renewalScreenType) {
      //未续保
      case "1":
        /**
         * 未续保 根据平台信息去区分
         * 30天平台 T~T+40
         * 40/45天平台 T~T+50
         * 60天平台 T~T+70
         * 90天平台T~T+90
         */
        if (this.renewalPlatformDay === "30") {
          this.queryData = Object.assign({}, this.queryData, {
            ...this.getDay_(40)
          });
          this.renewanlDate = Object.assign(this.renewanlDate, {
            ...this.getDay_(40)
          });
          this.popDate = Object.assign(this.popDate, {
            minRespEndDate: new Date(this.getDay_(40).minRespEndDate),
            maxRespEndDate: new Date(this.getDay_(40).maxRespEndDate)
          });
        } else if (
          this.renewalPlatformDay === "40" ||
          this.renewalPlatformDay === "45"
        ) {
          this.queryData = Object.assign({}, this.queryData, {
            ...this.getDay_(50)
          });
          this.renewanlDate = Object.assign(this.renewanlDate, {
            ...this.getDay_(50)
          });
          this.popDate = Object.assign(this.popDate, {
            minRespEndDate: new Date(this.getDay_(50).minRespEndDate),
            maxRespEndDate: new Date(this.getDay_(50).maxRespEndDate)
          });
        } else if (this.renewalPlatformDay === "60") {
          this.queryData = Object.assign({}, this.queryData, {
            ...this.getDay_(70)
          });
          this.renewanlDate = Object.assign(this.renewanlDate, {
            ...this.getDay_(70)
          });
          this.popDate = Object.assign(this.popDate, {
            minRespEndDate: new Date(this.getDay_(70).minRespEndDate),
            maxRespEndDate: new Date(this.getDay_(70).maxRespEndDate)
          });
        } else if (this.renewalPlatformDay === "90") {
          this.queryData = Object.assign({}, this.queryData, {
            ...this.getDay_(90)
          });
          this.renewanlDate = Object.assign(this.renewanlDate, {
            ...this.getDay_(90)
          });
          this.popDate = Object.assign(this.popDate, {
            minRespEndDate: new Date(this.getDay_(90).minRespEndDate),
            maxRespEndDate: new Date(this.getDay_(90).maxRespEndDate)
          });
        }
        break;
      /**
       * 已续保
       * 默认终保止期为 T+30
       */
      case "2":
        this.queryData = Object.assign({}, this.queryData, {
          ...this.getDay_(30)
        });
        this.renewanlDate = Object.assign(this.renewanlDate, {
          ...this.getDay_(30)
        });
        this.popDate = Object.assign(this.popDate, {
          minRespEndDate: new Date(this.getDay_(30).minRespEndDate),
          maxRespEndDate: new Date(this.getDay_(30).maxRespEndDate)
        });
        //去掉90天选择条件
        // this.insuranceData.splice(3, 1);
        break;
      /**
       * 已过期
       * 默认终保止期 T-3O
       */
      case "3":
        // eslint-disable-next-line no-case-declarations
        let [maxRespEndDate, minRespEndDate] = [
          this.getDay(-29).maxRespEndDate,
          this.getDay(0).maxRespEndDate
        ];
        this.queryData = Object.assign({}, this.queryData, {
          maxRespEndDate: minRespEndDate,
          minRespEndDate: maxRespEndDate
        });
        this.renewanlDate = Object.assign(this.renewanlDate, {
          maxRespEndDate: minRespEndDate,
          minRespEndDate: maxRespEndDate
        });
        this.popDate = Object.assign(this.popDate, {
          minRespEndDate: new Date(this.getDay(-28).maxRespEndDate),
          maxRespEndDate: new Date(this.getDay(-28).minRespEndDate)
        });
        break;
      /**
       * 已认领根据 认领的查询规则
       */
      case "4":
        this.queryData = Object.assign({}, this.queryData, {
          maxRespEndDate: this.maxRespEndDateProp,
          minRespEndDate: this.minRespEndDateProp
        });
        this.renewanlDate = Object.assign(this.renewanlDate, {
          maxRespEndDate: this.maxRespEndDateProp,
          minRespEndDate: this.minRespEndDateProp
        });
        this.popDate = Object.assign(this.popDate, {
          minRespEndDate: new Date(this.minRespEndDateProp),
          maxRespEndDate: new Date(this.maxRespEndDateProp)
        });
    }
    // /**
    //  *
    //  * 已续保 默认终保止期为 T+30
    //  * 已过期 默认终保止期 T-3O
    //  */
    // let objTemp = {
    //   "2": {
    //     ...this.getDay(30)
    //   },
    //   "3": {
    //     maxRespEndDate: this.getDay(-30).minRespEndDate,
    //     minRespEndDate: this.getDay(-30).maxRespEndDate
    //   }
    // };
    // this.queryData = Object.assign(
    //   {},
    //   this.queryData,
    //   objTemp[this.renewalScreenType]
    // );
    // this.renewanlDate = Object.assign(this.renewanlDate, {
    //   ...objTemp[this.renewalScreenType]
    // });
    // objTemp = null;
    // console.log(this.queryData);
  },
  methods: {
    getDay_(day) {
      let today = new Date();
      let targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
      today.setTime(targetday_milliseconds);
      let tYear = today.getFullYear();
      let tMonth = today.getMonth();
      let tDate = today.getDate();
      tMonth = this.doHandleMonth(tMonth + 1);
      tDate = this.doHandleMonth(tDate);
      return {
        maxRespEndDate: ` ${tYear}-${tMonth}-${tDate}`,
        minRespEndDate: `${new Date().getFullYear()}-${this.doHandleMonth(
          new Date().getMonth() + 1
        )}-${this.doHandleMonth(new Date().getDate())}`
      };
    },
    //获取几日内的时间区间
    //T ~-T- 要加两天
    getDay(day) {
      let today = new Date();
      let targetday_milliseconds =
        today.getTime() + 1000 * 60 * 60 * 24 * (day - 1);
      today.setTime(targetday_milliseconds);
      let tYear = today.getFullYear();
      let tMonth = today.getMonth();
      let tDate = today.getDate();
      tMonth = this.doHandleMonth(tMonth + 1);
      tDate = this.doHandleMonth(tDate);
      return {
        maxRespEndDate: ` ${tYear}-${tMonth}-${tDate}`,
        minRespEndDate: `${new Date().getFullYear()}-${this.doHandleMonth(
          new Date().getMonth() + 1
        )}-${this.doHandleMonth(new Date().getDate())}`
      };
    },
    doHandleMonth(month) {
      let m = month;
      if (month.toString().length == 1) {
        m = "0" + month;
      }
      return m;
    },
    //格式化金额
    formatterAmount(value) {
      let valueTemp = value.replace(/[^\d]/g, "");
      return valueTemp.replace(/^[0]+/, "");
    },
    //格式化日期
    formatDate(startDate) {
      let now = startDate;
      let year = now.getFullYear(); //年
      let month = now.getMonth() + 1; //月
      let day = now.getDate(); //日
      let clock = year + "-";
      if (month < 10) clock += "0";
      clock += month + "-";
      if (day < 10) clock += "0";
      clock += day;
      return clock;
    },
    //校验时间区间
    checkDate(startTime, endTime) {
      let temp = new Date(startTime),
        mark = true;
      temp.setMonth(temp.getMonth() + 3);
      if (new Date(endTime).getTime() > new Date(temp).getTime()) {
        mark = false;
      }
      return mark;
    },
    //开始时间
    confirm_startDate() {
      if (
        new Date(this.popDate.minRespEndDate).format("yyyy-MM-dd") >
        new Date(this.popDate.maxRespEndDate).format("yyyy-MM-dd")
      ) {
        this.$toast.fail("开始时间不能小于结束时间！");
        return false;
      }
      this.showPopup = false;
      this.$set(
        this.renewanlDate,
        "minRespEndDate",
        this.formatDate(this.popDate.minRespEndDate)
      );
    },
    //结束时间
    confirm_endDate() {
      if (
        new Date(this.popDate.minRespEndDate).format("yyyy-MM-dd") >
        new Date(this.popDate.maxRespEndDate).format("yyyy-MM-dd")
      ) {
        this.$toast.fail("开始时间不能小于结束时间！");
        return false;
      }
      this.showPopup_ = false;
      this.$set(
        this.renewanlDate,
        "maxRespEndDate",
        this.formatDate(this.popDate.maxRespEndDate)
      );
    },
    canel() {
      this.$emit("update:showScreen", false);
    },
    submit() {
      //判断上年保费金额
      if (
        Number.parseInt(this.amount.lastPremiumEnd) <
        Number.parseInt(this.amount.lastPremiumStart)
      ) {
        this.$toast.fail("请输入正确的保费区间！");
        return false;
      }
      //判断时间
      if (
        !this.checkDate(
          this.popDate.minRespEndDate,
          this.popDate.maxRespEndDate
        )
      ) {
        this.$toast.fail("距保单到期日不能超过三个月");
        return false;
      }
      this.$emit("update:showScreen", false);
      delete this.queryData.renewalAmount;
      this.queryData = Object.assign(
        {},
        this.queryData,
        this.renewanlDate,
        this.amount,
        {
          curHandleStatus:
            this.renewalScreenType === "4"
              ? this.curHandleStatus.join(",")
              : this.queryData.curHandleStatus
        }
      );
      //console.log(this.queryData, "====");
      this.$emit("sumbit", this.queryData);
    },
    //任务状态全选
    checkAll() {
      this.checkAllFlag = !this.checkAllFlag;
      !this.checkAllFlag
        ? (this.curHandleStatus = [
            "0",
            "1",
            "23",
            "24",
            "28",
            "901",
            "6",
            "7",
            "8",
            "27",
            "22",
            "21",
            "401",
            "906",
            "25",
            "99"
          ])
        : (this.curHandleStatus = []);
    },
    /**
     *把筛选数据加到queryData
     * @param {String} value 当前选中的值
     * @param {String} type 选中的类型
     */

    addData(value, type) {
      if (
        [
          "dueDate", //距保单到期日
          "curHandleStatus", //任务状态
          "renewedStatus", //我司续保状态
          "isQuote", //是否报价
          "transferFlag", //同行是否续保
          "offReasonFlag", //脱保原因是否已填写
          "renewalAmount", //上一年金额
          "carType" //车辆种类
        ].includes(type)
      ) {
        this.$set(this.queryData, type, value);
        //距保单到期日 距保单到期日 需要重置数据
        // type === "renewalAmount" ? (this.amount = { ...{} }) : "";
      }

      if (type == "dueDate") {
        /**
         *已过期的条件为T ~T- N
         */
        if (this.renewalScreenType === "3") {
          // eslint-disable-next-line no-unused-vars
          //15天内
          if (value == "-15") {
            let { maxRespEndDate, minRespEndDate } = {
              ...this.getDay(value + 1)
            };
            this.queryData = Object.assign({}, this.queryData, {
              maxRespEndDate: this.getDay(0).maxRespEndDate,
              minRespEndDate: maxRespEndDate
            });
            this.renewanlDate = Object.assign(this.renewanlDate, {
              maxRespEndDate: this.getDay(0).maxRespEndDate,
              minRespEndDate: maxRespEndDate
            });
            // console.log()
          } else if (value == "-30") {
            this.queryData = Object.assign({}, this.queryData, {
              maxRespEndDate: this.getDay(-15).maxRespEndDate,
              minRespEndDate: this.getDay(-29).maxRespEndDate
            });
            this.renewanlDate = Object.assign(this.renewanlDate, {
              maxRespEndDate: this.getDay(-15).maxRespEndDate,
              minRespEndDate: this.getDay(-29).maxRespEndDate
            });
          }
        } else {
          /**
           * 未续保，已续保，已过期 把筛选条件填充日期控件中去
           */
          this.renewanlDate = Object.assign(
            {},
            this.renewanlDate,
            this.getDay(value)
          );
        }
      }
      /**
       * 续保认领任务状态
       */
      type === "curHandleStatus"
        ? this.curHandleStatus.indexOf(value) == -1
          ? this.curHandleStatus.push(value)
          : this.curHandleStatus.splice(this.curHandleStatus.indexOf(value), 1)
        : "";
    }
  }
};
</script>
<style lang="less" scoped>
.contactList-screen {
  padding: 0 18px;
  // margin-bottom: 45px;
  height: calc(70vh - 45px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  & > div {
    margin-bottom: 15px;
  }
  .title {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    margin-bottom: 10px;
  }
  .screen-button {
    font-size: 14px;
    font-family: PingFangHK-Regular, PingFangHK;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    width: 106px;
    min-height: 36px;
    line-height: 36px;
    background: rgba(249, 250, 252, 1);
    border-radius: 2px;
    text-align: center;
    margin-bottom: 10px;
    margin-right: 10px;
  }
  .screen-button--actived {
    background: rgba(236, 243, 254, 1);
    color: rgba(59, 138, 254, 1);
  }
  .screen-button:nth-child(3n + 3) {
    margin-right: 0px;
  }
  .task-status--claim {
    .title {
      & > span {
        color: rgba(59, 138, 254, 1);
        margin-left: 10px;
        float: right;
      }
    }
    .screen-button {
      // word-break: break-all;
      // white-space: normal;
      line-height: normal;
      height: 56px;
      display: flex;
      display: -webkit-flex;
      align-items: center;
      -webkit-align-items: center;
      justify-content: center;
      -webkit-justify-content: center;
      font-size: 12px;
    }
    .more-task--status {
      text-align: center;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
    }
  }
  .insurance-data {
    .policyDate {
      margin-bottom: 10px;
      .policy-data--start {
        display: inline-block;
        width: 106px;
        height: 36px;
        line-height: 36px;
        text-align: center;
        background: rgba(249, 250, 252, 1);
        border-radius: 2px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #979797;
      }
      .policy-data--line {
        margin: 0 2px;
        color: #979797;
      }
      .policy-data--end {
        display: inline-block;
        width: 106px;
        height: 36px;
        line-height: 36px;
        text-align: center;
        background: rgba(249, 250, 252, 1);
        border-radius: 2px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #979797;
      }
    }
  }
  .amount-section {
    display: flex;
    display: -webkit-flex;
    align-items: center;
    -webkit-align-items: center;
    /deep/.van-cell {
      padding: 0;
      width: 106px;
      height: 36px;
      background: rgba(249, 250, 252, 1);
      border-radius: 2px;
    }
    /deep/.van-field__control {
      width: 106px;
      height: 36px;
      text-align: center;
    }
    .amount-section--line {
      margin: 0 2px;
      color: #979797;
    }
  }
}
.contactList-screen--footer {
  bottom: 0;
  position: fixed;
  width: 100%;
  /deep/.van-button {
    width: 50%;
  }
}
</style>
