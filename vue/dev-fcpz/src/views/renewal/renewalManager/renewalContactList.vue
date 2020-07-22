<template>
  <div class="contactList">
    <div class="contactList-head">
      <p @click="renewalEnd_">
        {{ RenewalSort ? "到期日↑" : "到期日↓" }}
      </p>
      <form action="javascript:return true">
        <van-search
          v-model="UpperCase"
          placeholder="车牌号/投保人/被保险人/保单号"
          show-action
          @search="onSearch"
          @input="onInput"
          @clear="onClear"
          shape="round"
        >
          <div slot="left-icon">
            <van-icon name="search" @click="onSearch(UpperCase)" />
          </div>
          <div slot="action" @click="showScreen = true">
            <span class="screen"
              ><img src="../../../assets/renewal/screen.png" alt=""
            /></span>
          </div>
        </van-search>
      </form>
      <van-popup
        v-model="showScreen"
        position="bottom"
        :style="{ height: '70%' }"
        round
      >
        <renewalContactListScreen
          :renewalScreenType="renewalScreenType"
          :maxRespEndDateProp="queryData.maxRespEndDate"
          :minRespEndDateProp="queryData.minRespEndDate"
          :showScreen.sync="showScreen"
          @sumbit="sumbit"
          v-if="showScreen"
        ></renewalContactListScreen>
      </van-popup>
    </div>
    <div
      class="contactList-num"
      v-cloak
      v-if="
        this.type.name === '4' &&
          (this.channelCode === 'C0000015' ||
            this.channelCode === 'C0000022' ||
            this.channelCode === 'C0000019' ||
            this.channelCode === 'C0000023')
      "
    >
      <p>
        <span>{{ resultDataNum.expireTotalCount }}</span>
        <span>保单到期数</span>
      </p>
      <p>
        <span>{{ `${Number(resultDataNum.destRate || 0)}%` }}</span>
        <span>目标推送率</span>
      </p>
      <p>
        <span>{{ resultDataNum.destTotalCount }}</span>
        <span>目标推送量</span>
      </p>
      <p>
        <span>{{ resultDataNum.realTotalCount }}</span>
        <span>实际推送量</span>
      </p>
    </div>
    <div
      :class="`${type.name != '4' ? 'fixed_bottom' : 'fixed_bottom margin'}`"
    >
      <van-list
        v-model="loading"
        :finished="finished"
        @load="onLoad"
        :offset="50"
        :error.sync="error"
        error-text="请求失败，点击重新加载"
        :immediate-check="false"
        ref="vanList"
      >
        <template #finished>
          <div
            v-show="Array.isArray(resultData) && resultData.length == 0"
            class="noData"
          ></div>
        </template>
        <div
          class="contactList-content"
          v-for="(item, index) in resultData"
          :key="index"
        >
          <div class="contactList-content-checkbox" v-if="type.name === '4'">
            <van-checkbox v-model="item.checked"></van-checkbox>
          </div>
          <div>
            <p class="contactList-content-pin" v-if="type.name === '4'">
              <span>{{ item.handleStatusName }}</span>
            </p>
            <div class="contactList-content_head">
              <p class="contactList-content_head--span">
                <img src="../../../assets/renewal/car.png" alt="" />
                <span class="van-ellipsis"
                  >{{ item.licenseNo }}
                  {{ type.name === "4" ? item.applicantName : "" }}
                  {{ item.insuranceType || "车险" }}</span
                >
              </p>
              <div
                class="state"
                :style="item.state.borderColor"
                v-if="
                  type.name === '1' &&
                    item.quotationArea &&
                    item.quotatFlag &&
                    item.carType === '1'
                "
              >
                <p :style="item.state.borderColorOne">
                  {{ item.state.state.stateOne }}
                </p>
                <p :style="item.state.borderColorTwo">
                  {{ item.state.state.stateTwo }}
                </p>
              </div>
              <div v-else-if="item.carType === '0'" class="nocarState">
                <p>
                  非家用车
                </p>
              </div>
            </div>
            <div
              class="contactList-content_details"
              @click="
                $router.push({
                  name:
                    type.name === '4'
                      ? 'renewalClaimDetails'
                      : 'renewalContactDetails',
                  params: { ...item, type: type.name }
                })
              "
            >
              <div class="contactList-content_details_" v-if="type.name != '4'">
                <span>投保人</span>
                <span>{{ item.applicantName || "" }}</span>
                <span>{{ item.respEndDate || "" }}到期</span>
                <span>{{ item.dueTime || "" }}</span>
                <span v-if="type.name != '2'">业务风险分类</span>
                <span v-if="type.name != '2'">{{
                  item.handler2name || ""
                }}</span>
                <span
                  >上年保费{{
                    item.compulsoryFlag === "1"
                      ? "(商业险+交强险)"
                      : item.compulsoryFlag === "2"
                      ? "(交强险)"
                      : "(商业险)"
                  }}</span
                >
                <span>{{
                  item.premium ? Number(item.premium || 0).toFixed(2) : ""
                }}</span>
                <span v-if="type.name != '2'">
                  续保预报价{{
                    item.renewalCompulsoryFlag === "1"
                      ? "(商业险+交强险)"
                      : item.renewalCompulsoryFlag === "2"
                      ? "(交强险)"
                      : "(商业险)"
                  }}</span
                >
                <span v-if="type.name == '2'">
                  续保保费{{
                    `${
                      item.renewalCompulsoryFlag === "1"
                        ? "(商业险+交强险)"
                        : item.renewalCompulsoryFlag === "2"
                        ? "(交强险)"
                        : "(商业险)"
                    }`
                  }}
                </span>
                <span
                  ><img
                    src="../../../assets/renewal/question.png"
                    alt=""
                    @click.stop="getQuoteInfo(index, item)"
                    v-if="
                      item.showButton === 'Y' &&
                        item.showQuoteInfo &&
                        !item.quotationMoney &&
                        (type.name === '1' || type.name === '3')
                    "
                  />
                  <span
                    v-else
                    :style="{
                      color:
                        Number(item.quotationMoney || 0) >
                          Number(item.premium || 0) ||
                        Number(item.quoteMoney || 0) > Number(item.premium || 0)
                          ? '#FE6767'
                          : 'blue'
                    }"
                  >
                    {{
                      `${
                        item.quotationMoney
                          ? item.quotationMoney
                            ? Number(item.quotationMoney || 0).toFixed(2)
                            : ""
                          : item.quoteMoney
                          ? Number(item.quoteMoney || 0).toFixed(2)
                          : ""
                      }`
                    }}</span
                  >
                  <span class="amount-del">{{
                    item.premium ? Number(item.premium || 0).toFixed(2) : ""
                  }}</span>
                </span>
              </div>
              <div v-else class="contactList-content_details_ claim">
                <span>待续保保单终止日期</span>
                <span>{{ item.respEndDate || "" }}</span>
                <span
                  >上年保费{{
                    `${
                      item.riskCode === "DFA"
                        ? "(交强险)"
                        : item.riskCode === "DEA"
                        ? "(商业险)"
                        : ""
                    }`
                  }}</span
                >
                <span>{{
                  item.premium ? Number(item.premium || 0).toFixed(2) : ""
                }}</span>
                <span>续存年</span>
                <span>{{ item.survivingYear || "" }}</span>
                <span>最后报价时间</span>
                <span>{{ item.lastestQuoteDate || "" }}</span>
                <span>业务风险分类</span>
                <span>{{ item.handler2name || "" }}</span>
              </div>
            </div>
            <div class="contactList-content_footer">
              <span class="quotationTimes" v-show="item.carType === '1'"
                >报价{{ item.quoteTimesSum || 0 }}次</span
              >
              <div class="button">
                <!-- <div v-if="type.name === '3'"> -->
                <!-- <van-button
                  type="default"
                  size="mini"
                  v-if="type.name === '3'"
                  @click.stop="getRenewal(item)"
                  >脱保原因</van-button
                > -->
                <van-dialog
                  v-model="item.showCancelRenewal"
                  title="脱保原因"
                  show-cancel-button
                  confirmButtonText="保存"
                  class="CancelRenewal"
                  ref="RenewalDialog"
                  :beforeClose="
                    (aciton, done) => {
                      beforeClose(aciton, done, item);
                    }
                  "
                >
                  <template #default>
                    <p class="CancelRenewal-title">脱保原因</p>
                    <div class="select-noRenewalReason">
                      <p
                        class="select"
                        @click="noRenewalSelect = !noRenewalSelect"
                      >
                        <span>{{ selectValue.label }}</span>
                        <van-icon name="arrow-down" />
                      </p>
                      <div class="select-option" v-if="noRenewalSelect">
                        <p
                          v-for="(item, i) in cancelRenewal"
                          :key="i"
                          @click="
                            () => {
                              selectValue = { ...item };
                              noRenewalSelect = false;
                              isRenwalResnon =
                                item.value === 'RSK' || item.value === 'RSI';
                            }
                          "
                          :class="[
                            selectValue.value == item.value
                              ? 'select-option-actived'
                              : ''
                          ]"
                        >
                          {{ item.label }}
                        </p>
                      </div>
                    </div>
                    <div v-if="isRenwalResnon">
                      <p class="CancelRenewal-title">详细内容</p>
                      <van-field
                        v-model="cancelRenewalRenson"
                        rows="2"
                        autosize
                        label=""
                        type="textarea"
                        maxlength="50"
                        placeholder="请输入脱保原因"
                        show-word-limit
                      />
                    </div>
                  </template>
                </van-dialog>
                <!-- </div> -->
                <van-button
                  type="default"
                  size="mini"
                  v-if="
                    (type.name == '3' || type.name == '1') &&
                      item.showButton === 'Y'
                  "
                  @click="createProposaPost(item)"
                  >生成计划书</van-button
                >

                <van-button
                  type="default"
                  size="mini"
                  v-if="type.name === '2'"
                  @click="$router.push('/nonCar')"
                  >更多推荐</van-button
                >
                <van-button
                  type="default"
                  size="mini"
                  @click="custDetails(item)"
                  >客户详情</van-button
                >
                <van-button
                  type="default"
                  size="mini"
                  @click="messagePhone(item)"
                  class="contact-customers"
                  >联系客户
                </van-button>
              </div>
              <van-icon
                name="ellipsis"
                class="ellipsis-icon"
                size="21"
                color="#CCCCCC"
                @click="item.showTip = !item.showTip"
              />
              <div class="tips" v-show="item.showTip">
                <span class="triangle"></span>
                <span
                  @click="
                    $router.push({
                      name: 'operationTrajectory',
                      params: { ...item }
                    })
                  "
                  >{{ handleStatusName(item.handleStatus) }}</span
                >
                <span
                  :class="[
                    type.name === '3'
                      ? 'van-hairline--top-bottom'
                      : 'van-hairline--top'
                  ]"
                  @click="
                    $router.push({
                      name: 'renewalTrackRecord',
                      params: { ...item }
                    })
                  "
                  >跟踪记录</span
                >
                <span v-if="type.name === '3'" @click.stop="getRenewal(item)"
                  >脱保原因</span
                >
              </div>
            </div>
          </div>
        </div>
      </van-list>
      <div class="contactList-screen--footer" v-if="this.type.name === '4'">
        <van-button type="default" @click="push">推电销</van-button>
        <!-- 仅直销个代、非销、四级机构、互动渠道用户可见 -->
        <van-button
          type="primary"
          color="#3B8AFE"
          @click="claim"
          v-if="
            this.channelCode === 'C0000015' ||
              this.channelCode === 'C0000022' ||
              this.channelCode === 'C0000019' ||
              this.channelCode === 'C0000023'
          "
          >认领
        </van-button>
      </div>
    </div>
    <van-popup
      v-model="showCallPhone"
      position="bottom"
      :style="{ width: '100vw' }"
    >
      <van-picker
        show-toolbar
        :columns="phoneList | maskPhone"
        title="联系电话"
        @cancel="showCallPhone = false"
        @confirm="onConfirm"
        ref="picker"
      />
    </van-popup>
  </div>
</template>

<script>
import { Search, button, checkbox, Dialog, Popup, List, Toast } from "vant";
//获取续保任务列表
import {
  queryRenewalTaskInfo,
  renewalClaim,
  updateUnRenewReason,
  queryRenewalQuoteInfo,
  getRenewalExpireReason,
  createProposa
} from "@src/http/module/renewal.js";
//订单详情
import { getDetails } from "@src/http/module/order.js";
//互动记录 获取手机号
import {
  addOrUpdateInteractions,
  getQueryCustPhone
} from "@src/http/module/customerApi.js";
//校验
import {
  isRenwalLicensePlateNumber,
  isName,
  isPolicyNo
} from "@src/utils/index.js";
import { data } from "@src/utils/dataDictionary.js";
import { mapMutations } from "vuex";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "renewalContactList",
  props: {
    type: Object,
    requestActived: String
  },
  components: {
    [Search.name]: Search,
    [button.name]: button,
    [checkbox.name]: checkbox,
    [Dialog.Component.name]: Dialog.Component,
    [Popup.name]: Popup,
    [List.name]: List,
    [Toast.name]: Toast,
    //筛选弹框
    renewalContactListScreen: () => import("./renewalContactListScreen")
  },
  data: () => ({
    scollTop: "",
    value: "",
    checkType: "",
    nodata_img: require("@src/assets/renewal/renewalListData.png"),
    loading: false, //是否加载下拉刷新
    finished: false, //数据是否加载完
    error: false, //上拉刷新报错信息
    isRenwalResnon: false, //
    cancelRenewalRenson: "", //脱保原因
    showCancelRenewal: false, //脱保原因弹框
    noRenewalSelect: false, //脱保原因下拉框
    // isRenewalEnd: false, //终保止期显示隐藏
    isRenewalEndText: "到期日↑",
    RenewalSort: true,
    showScreen: false, //筛选弹框显示
    renewalScreenType: "", //筛选弹框类型
    phoneList: [], //客户手机号LIST
    custPhone: "", //选中的手机号
    messageItem: {}, //选中的手机号的数据
    isMessagePhone: "", //判读是发送短信还是手机号 1 手机号 2 短信
    showCallPhone: false, //拨打电话弹出层
    selectValue: {
      value: "",
      label: "请选择"
    },
    cancelRenewal: [
      { value: "RSA", label: "报废" },
      { value: "RSB", label: "车辆办理转籍到外省" },
      { value: "RSC", label: "车辆办理停驶" },
      { value: "RSD", label: "保费太贵" },
      { value: "RSE", label: "人为脱保" },
      { value: "RSJ", label: "暂时不开" },
      { value: "RSG", label: "追踪不及时脱保" },
      { value: "RSH", label: "过户联系不上脱保" },
      { value: "RSI", label: "保其他公司" },
      { value: "RSK", label: "其他原因" }
    ], //脱保原因
    resultData: [
      // {
      //   licenseNo: "湘A12",
      //   applicantName: "张玉磊",
      //   riskName: "中国大地保险",
      //   quotationTimes: 2,
      //   respEndDate: "2020/02/22 ",
      //   dueTime: "5天08时到期",
      //   lastPremium: "900.00",
      //   quotationMoney: "400.00",
      //   showTip: false,
      //   checked: false
      // }
    ],
    resultDataNum: {
      expireTotalCount: "" || "-", //保单到期数
      destTotalCount: "" || "-", //目标推送量
      destRate: 0, //目标推送率
      realTotalCount: "" || "-" //实际推送量
    },
    checkedData: {
      handleStatus: "", // 1 认领 2 推电销
      policyList: [],
      userCode: "" //业务员编号
    }, //续保认领选中数据
    queryData: {
      // appName: "",
      // businessnature2V: "",
      // carType: "",
      // channel: JSON.parse(localStorage.getItem("userInfo")).channelCode,
      // custType: "",
      // frameno: "",
      // insuranceType: "",
      // insuredName: "",
      // licenseNo: "",
      // policyNo: "",
      // relatedPerson: "",
      // renewalProbability: "",
      // survivingYear: "",

      //必填参数
      maxRespEndDate: "",
      minRespEndDate: "",
      orderByFlag: "1",
      comCode: "",
      pageNum: 1,
      pageSize: 10,
      trackStatus: "1",
      userCode: ""
    }, //查询续保信息参数
    noRenewalData: {
      id: "string", //主键
      policyNo: "string", //保单号
      reasonDetail: "string", //原因详情
      reasonType: "string", //脱保原因枚举值
      // riskCode: "string", //险种类型
      userCode: "string" //业务员编号
    }, //脱保原因更新
    channelCode: JSON.parse(localStorage.getItem("userInfo")).channelCode || "", //渠道CODE
    isPlatform:
      JSON.parse(localStorage.getItem("ConfigParams")).renewalPlatformDay || "", //判断平台配置信息
    isSaleMan: JSON.parse(localStorage.getItem("userInfo")).isSaleMan || "", //是否销售人员 0 否 1 是
    // d: new Date(), //当前时间
    flag: true, //认领和推电销接口开关
    responseDate: ""
  }),
  activated() {
    //tip关闭
    this.resultData.map(item => {
      item.showTip = false;
    });
    document.querySelector(".fixed_bottom").scroll(0, this.scrollTop);
  },
  destroyed() {
    window.removeEventListener("scroll", this.getScrollTop);
  },
  created() {
    /* 从原生页面待报价传终保区间 */
    if (
      this.requestActived &&
      this.$GetRequest("maxRespEndDate", true) &&
      this.$GetRequest("minRespEndDate", true)
    ) {
      this.queryData = Object.assign({}, this.queryData, {
        maxRespEndDate: this.$GetRequest("maxRespEndDate", true) || "",
        minRespEndDate: this.$GetRequest("minRespEndDate", true) || "",
        isQuote: "0"
      });
    }
    /* 从原生页面待报价传终保区间 */
    if (
      this.$GetRequest("actived", true) &&
      this.$GetRequest("maxRespEndDate", true) &&
      this.$GetRequest("minRespEndDate", true)
    ) {
      this.queryData = Object.assign({}, this.queryData, {
        maxRespEndDate: this.$GetRequest("maxRespEndDate", true) || "",
        minRespEndDate: this.$GetRequest("minRespEndDate", true) || "",
        isQuote: "0"
      });
    }
    //续保认领页面 默认倒序
    if (this.type.name === "4") {
      this.RenewalSort = false;
      this.$set(this.queryData, "orderByFlag", this.RenewalSort ? 1 : 2);
    }
    /**
     * 渠道为空 默认走非空渠道 C0000023
     * 90天平台 t+4
     */
    if (!this.channelCode) {
      this.channelCode = "C0000023";
      this.isPlatform = "90";
    }
    /* 从人渠道为保代渠道 认领流程同直个渠道 */
    if (
      this.channelCode === "C0000029" &&
      JSON.parse(localStorage.getItem("ConfigParams")).protectGeneration === "Y"
    ) {
      this.channelCode = "C0000015";
    }

    //页面初始化查询条件
    this.$set(this.queryData, "trackStatus", this.type.name);

    /**
     * 续保认领 终保止期区间按照 渠道 平台去划分
     * 续保认领 任务状态传默认值
     * 默认：未处理、认领（业务员）、已认领（底线保护）、已指派（底线保护）、认领（绑定保护）、认领（系统默认）
     */

    /**
     * this.type.name === 4 续保认领 根据平台配置 修改 终保止期区间
     * maxRespEndDate
     * minRespEndDate
     *
     */
    if (this.type.name != "4") {
      return false;
    }
    this.queryData = Object.assign({}, this.queryData, {
      curHandleStatus: "0,1,23,24,28,901"
    });
    //直销个代C0000015、非销（HR口径，非销售序列）C0000023、四级机构C0000022
    if (
      this.channelCode === "C0000015" ||
      this.channelCode === "C0000022" ||
      this.channelCode === "C0000023"
    ) {
      /**
       * 30 40 45 60 天平台
       * 保险止期默认查询时间范围
       * T+ 3月的１日至月底，T为当前月
       */
      if (
        this.isPlatform === "30" ||
        this.isPlatform === "45" ||
        this.isPlatform === "40" ||
        this.isPlatform === "60"
      ) {
        this.queryData = Object.assign({}, this.queryData, {
          ...this.getMonthTime(3)
        });
      } else if (this.isPlatform === "90") {
        /**
         * 90天平台
         * 保险止期默认查询时间范围
         * T+ 4月的１日至月底，T为当前月
         */
        this.queryData = Object.assign({}, this.queryData, {
          ...this.getMonthTime(4)
        });
      } else if (
        data.comCode[
          JSON.parse(localStorage.getItem("userInfo")).company.substring(0, 4)
        ] === "浙江分公司" &&
        this.isPlatform === "60"
      ) {
        /**
         * 浙江（60天平台）直个渠道续保认领查询
         * 保险止期默认查询时间范围
         * T+ 3月的１日至月底，T为当前月
         */
        this.queryData = Object.assign({}, this.queryData, {
          ...this.getMonthTime(3)
        });
      }
    } else if (this.channelCode === "C0000019") {
      /**
       * 从人渠道归属为互动渠道C0000019
       * 保险止期默认查询时间范围为：Ｔ＋2季度整个季度，T为当前季度
       */
      this.queryData = Object.assign({}, this.queryData, {
        minRespEndDate: this.getMonthTime(6).minRespEndDate,
        maxRespEndDate: this.getMonthTime(8).maxRespEndDate
      });
    } else {
      this.queryData = Object.assign({}, this.queryData, {
        ...this.getDay(90)
      });
    }
  },
  mounted() {
    document
      .querySelector(".fixed_bottom")
      .addEventListener("scroll", this.getScrollTop);
    document
      .querySelector(".fixed_bottom")
      .addEventListener("scroll", this.getScrollTop);
    this.renewalScreenType = this.type.name;
    /**
     * 页面初始化
     */
    let { comCode, userCode } = {
      ...JSON.parse(localStorage.getItem("userInfo"))
    };
    this.queryData = Object.assign({}, this.queryData, {
      comCode: comCode,
      userCode: userCode
    });
    this.queryRenewalList();
  },
  filters: {
    maskPhone(val) {
      return val.reduce(
        (prev, cur) => [
          ...prev,
          cur.substr(0, 3) + "****" + cur.substr(cur.length - 4)
        ],
        []
      );
    }
  },
  watch: {
    isRenwalResnon: function(val) {
      if (!val) {
        this.cancelRenewalRenson = "";
      }
    }
  },
  computed: {
    UpperCase: {
      set(val) {
        this.value = val.toUpperCase();
      },
      get() {
        return this.value;
      }
    },
    d() {
      // try {
      //   return new Date(this.responseDate);
      // } catch (err) {
      //   return new Date();
      // }
      let dateTemp;
      this.responseDate
        ? (dateTemp = new Date(this.responseDate))
        : (dateTemp = new Date());
      return dateTemp;
    }
  },
  methods: {
    getScrollTop() {
      this.scrollTop = document.querySelector(".fixed_bottom").scrollTop;
    },
    ...mapMutations("vehicle", [
      "setCarInfo",
      "setCarOwner",
      "setPolicyHolder",
      "setOrderId",
      "setRecognizee"
    ]),
    handleStatusName(val) {
      switch (val) {
        case "23":
          return "认领（底线保护）";
        case "24":
          return "指派（底线保护）";
        case "25":
          return "推电销（批量导入）";
        case "1":
          return "认领（业务员）";
        case "6":
          return "推电销（业务员）";
        case "7":
          return "推电销（逾期未操作）";
        case "8":
          return "已认领推电销（业务员）";
        case "401":
          return "推电销（撤回）";
        case "901":
          return "认领（系统默认）";
        case "906":
          return "推电销（系统默认）";
        case "0":
          return "未处理";
        case "22":
          return "推电销（孤儿单）";
        case "21":
          return "已指派（孤儿单）";
        case "27":
          return "推电销（互动保护";
        case "28":
          return "认领（绑定保护）";
        default:
          return "任务状态";
      }
    },
    checkSystem() {
      var u = window.navigator.userAgent;
      var isAndroid = u.indexOf("Android") > -1 || u.indexOf("Linux") > -1; //g
      var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
      if (isAndroid) {
        return "android";
      }
      if (isIOS) {
        return "ios";
      }
    },
    getQuarter() {
      let month = this.d.getMonth();
      if (month < 3) {
        return 1;
      } else if (month < 6) {
        return 2;
      } else if (month < 9) {
        return 3;
      } else if (month < 12) {
        return 4;
      }
    },
    /**
     * 获取T+N的时间区间
     * @param {Numebr}  N  T+N月份
     */
    getMonthTime(N) {
      let maxRespEndDate = new Date(),
        minRespEndDate = new Date();
      maxRespEndDate.setMonth(new Date().getMonth() + 1 + N, 0);
      minRespEndDate.setMonth(new Date().getMonth() + N, 1);
      return {
        maxRespEndDate: `${maxRespEndDate.getFullYear()}-${this.doDate(
          maxRespEndDate.getMonth() + 1
        )}-${this.doDate(maxRespEndDate.getDate())}`,
        minRespEndDate: `${minRespEndDate.getFullYear()}-${this.doDate(
          minRespEndDate.getMonth() + 1
        )}-${this.doDate(minRespEndDate.getDate())}`
      };
    },
    //获取几日内的时间区间
    getDay(day) {
      let today = new Date();
      let targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
      today.setTime(targetday_milliseconds);
      let tYear = today.getFullYear();
      let tMonth = today.getMonth();
      let tDate = today.getDate();
      tMonth = this.doDate(tMonth + 1);
      tDate = this.doDate(tDate);
      return {
        maxRespEndDate: ` ${tYear}-${tMonth}-${tDate}`,
        minRespEndDate: `${new Date().getFullYear()}-${this.doDate(
          new Date().getMonth() + 1
        )}-${this.doDate(new Date().getDate())}`
      };
    },
    doDate(date) {
      let d = date;
      if (date.toString().length == 1) {
        d = "0" + d;
      }
      return d;
    },
    /**
     * 续保状态state
     * @param {String} quotationArea :续保状态区间 1 2 3 4
     *  @param {String} quotatFlag : 1 已首报 2 未首报 3 已二报 4 未二报
     */
    stateColor(quotationArea, quotatFlag) {
      let [grey, green, red, yellow] = [
        "#999999",
        "#10C143",
        "#FD6363",
        "#FFCC99"
      ];
      if (quotationArea === "1" && quotatFlag === "13") {
        //已首报 已二报
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "已首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "1" && quotatFlag === "23") {
        return {
          borderColor: {
            "border-color": red
          },
          borderColorOne: {
            color: red
          },
          borderColorTwo: {
            color: red
          },
          state: { stateOne: "未首报", stateTwo: "" }
        };
      } else if (quotationArea === "1" && quotatFlag === "24") {
        return {
          borderColor: {
            "border-color": red
          },
          borderColorOne: {
            color: red
          },
          borderColorTwo: {
            color: red
          },
          state: { stateOne: "未首报", stateTwo: "" }
        };
      } else if (quotationArea === "1" && quotatFlag === "14") {
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "已首报", stateTwo: "" }
        };
      } else if (quotationArea === "2" && quotatFlag === "24") {
        return {
          borderColor: {
            "border-color": yellow
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: yellow
          },
          state: { stateOne: "未首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "2" && quotatFlag === "13") {
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "已首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "2" && quotatFlag === "14") {
        return {
          borderColor: {
            "border-color": yellow
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: yellow
          },
          state: { stateOne: "已首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "2" && quotatFlag === "23") {
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "未首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "3" && quotatFlag === "24") {
        return {
          borderColor: {
            "border-color": red
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: red
          },
          state: { stateOne: "未首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "3" && quotatFlag === "14") {
        return {
          borderColor: {
            "border-color": red
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: red
          },
          state: { stateOne: "已首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "3" && quotatFlag === "23") {
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "未首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "3" && quotatFlag === "13") {
        return {
          borderColor: {
            "border-color": green
          },
          borderColorOne: {
            color: green
          },
          borderColorTwo: {
            color: green
          },
          state: { stateOne: "已首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "4" && quotatFlag === "24") {
        return {
          borderColor: {
            "border-color": grey
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: grey
          },
          state: { stateOne: "未首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "4" && quotatFlag === "13") {
        return {
          borderColor: {
            "border-color": grey
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: grey
          },
          state: { stateOne: "已首报", stateTwo: "已二报" }
        };
      } else if (quotationArea === "4" && quotatFlag === "14") {
        return {
          borderColor: {
            "border-color": grey
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: grey
          },
          state: { stateOne: "已首报", stateTwo: "未二报" }
        };
      } else if (quotationArea === "4" && quotatFlag === "23") {
        return {
          borderColor: {
            "border-color": grey
          },
          borderColorOne: {
            color: grey
          },
          borderColorTwo: {
            color: grey
          },
          state: { stateOne: "未首报", stateTwo: "已二报" }
        };
      } else {
        // return {
        //   borderColor: {
        //     "border-color": grey
        //   },
        //   borderColorOne: {
        //     color: grey
        //   },
        //   borderColorTwo: {
        //     color: grey
        //   },
        //   state: { stateOne: "", stateTwo: "" }
        // };
      }
    },
    //筛选
    sumbit(data) {
      this.queryData = Object.assign(
        {},
        this.queryData,
        { ...data },
        {
          pageNum: 1
        }
      );
      this.resultData = [...[]];
      // /**
      //  * 修改组件bug
      //  * 筛选完毕后 list组件不会调用onload方法 手动把finished关闭
      //  */
      this.finished = false;
      this.loading = false;
      // this.queryRenewalList();
      this.$refs["vanList"].check();
      // this.onLoad();
    },
    //客户详情
    custDetails(item) {
      if (!item.custNo) {
        this.$dialog.alert({
          message: "没有此客户号！"
        });
        return false;
      }
      this.$router.push({
        path: "/customer/customerEquity",
        query: {
          custNo: item.custNo,
          viewType: "1",
          custSourceType: "2",
          custType: "1"
        }
      });
    },
    //获取续保报价
    getQuoteInfo(index, val) {
      this.queryRenewalQuoteInfo_(index, val);
    },
    //终止保期正序倒序
    renewalEnd_() {
      this.RenewalSort = !this.RenewalSort;
      this.$set(this.queryData, "orderByFlag", this.RenewalSort ? 1 : 2);
      this.queryData.pageNum = 1;
      this.resultData = [...[]];
      this.queryRenewalList();
    },
    //搜索
    onSearch(val) {
      // let { comCode, userCode } = {
      //   ...JSON.parse(localStorage.getItem("userInfo"))
      // };
      // //重置选择条件
      // this.queryData = {
      //   maxRespEndDate: "",
      //   minRespEndDate: "",
      //   orderByFlag: "1",
      //   comCode: comCode,
      //   pageNum: 1,
      //   pageSize: 10,
      //   trackStatus: "1",
      //   userCode: userCode
      // };
      if (val) {
        if (isPolicyNo(val.trim())) {
          if (!(val.trim().length === 22 || val.trim().length == 24)) {
            this.$toast.fail("没有查询到车险续保信息！");
            return false;
          }
        }
        if (
          !isRenwalLicensePlateNumber(val.trim()) &&
          !isName(val.trim()) &&
          !isPolicyNo(val.trim())
        ) {
          this.$toast.fail("没有查询到车险续保信息！");
          return false;
        }
        this.queryData = Object.assign({}, this.queryData, {
          licenseNo: isRenwalLicensePlateNumber(val.trim()) ? val.trim() : "",
          appName: isName(val.trim()) ? val.trim() : "",
          policyNo:
            isPolicyNo(val.trim()) &&
            (val.trim().length === 22 || val.trim().length == 24)
              ? val.trim()
              : ""
        });
        this.queryData.pageNum = 1;
        this.resultData = [...[]];
        this.finished = false;
        this.loading = true;
        this.onLoad();
      } else {
        this.queryData = Object.assign({}, this.queryData, {
          licenseNo: "",
          appName: "",
          policyNo: "",
          pageNum: 1
        });
        this.resultData = [...[]];
        // this.queryRenewalList();
        this.finished = false;
        this.loading = false;
        this.$refs["vanList"].check();
      }
    },
    onInput(val) {
      if (!val) {
        this.queryData = Object.assign({}, this.queryData, {
          licenseNo: "",
          appName: "",
          policyNo: "",
          pageNum: 1
        });
      }
    },
    //点击搜索清除
    onClear() {
      this.queryData = Object.assign({}, this.queryData, {
        licenseNo: "",
        appName: "",
        policyNo: "",
        pageNum: 1
      });
      this.resultData = [...[]];
      this.finished = false;
      this.loading = false;
      this.$refs["vanList"].check();
    },
    //推电销
    push() {
      if (!this.resultData.some(item => item.checked === true)) {
        this.$toast.fail("请选择保单进行操作");
        return false;
      }
      Dialog.alert({
        message: "是否确认推送电销",
        confirmButtonText: "推电销",
        showCancelButton: true,
        cancelButtonText: "取消"
      })
        .then(() => {
          this.checkedData.handleStatus = "2";
          this.pushClaim();
        })
        .catch(() => {});
    },
    //认领
    claim() {
      if (!this.resultData.some(item => item.checked === true)) {
        this.$toast.fail("请选择保单进行操作");
        return false;
      }
      Dialog.alert({
        message: "是否确认领取",
        confirmButtonText: "认领",
        showCancelButton: true,
        cancelButtonText: "取消"
      })
        .then(() => {
          this.checkedData.handleStatus = "1";
          this.pushClaim();
        })
        .catch(() => {});
    },
    //生成计划书
    createProposaPost(item) {
      //埋点
      trackMap("c_my_renewal_generate_proposal", "我的续保生成计划书", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "续保"
      });
      createProposa({
        frameNo: item.frameno || "",
        orgId: item.orgId || "",
        userCode: localStorage.getItem("userCode") || "",
        compulsoryFlag: item.compulsoryFlag || "",
        phone: item.phone || "",
        quoateType: item.quoateType || "",
        respEndDate: item.respEndDate || ""
      })
        .then(res => {
          if (res.code === "1") {
            if (res.data.jumpFlag === "2" || res.data.jumpFlag === "3") {
              // suncao 20200428
              this.setOrderId(res.data.orderId);
              this.$router.push({
                name: "vehicle",
                query: {
                  vehicleType:
                    res.data.vehicleKindCode == "11" &&
                    res.data.vehicleUseNatureCode == "85"
                      ? "1"
                      : this.checkCarType(res.data.vehicleKindCode)
                }
              });
            }
            //重复投保
            if (res.data.jumpFlag === "1" && res.data.errorCode === "10015") {
              this.$dialog.alert({
                message: res.data.errorMessage,
                showCancelButton: true,
                callback: action => {
                  if (action === "confirm") {
                    this.setOrderId(res.data.orderId);
                    this.$router.push({
                      name: "vehicle",
                      query: {
                        vehicleType:
                          res.data.vehicleKindCode == "11" &&
                          res.data.vehicleUseNatureCode == "85"
                            ? "1"
                            : this.checkCarType(res.data.vehicleKindCode)
                      }
                    });
                  } else if (action === "cancel") {
                  }
                }
              });
            }
          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
        })
        .catch(err => {});
    },
    checkCarType(vehicleKindCode) {
      let carType = [
        {
          //货车、客车、挂车
          vehicleKindCode: ["11", "31", "21", "22"]
        },
        {
          //摩托车、拖拉机、三轮车
          vehicleKindCode: [
            "51",
            "52",
            "53",
            "61",
            "62",
            "63",
            "64",
            "9A",
            "9B",
            "AA",
            "AB",
            "AC",
            "AD",
            "AE",
            "BA",
            "BB",
            "BC",
            "BD",
            "BE",
            "CA",
            "CB",
            "CC",
            "CD",
            "CE"
          ]
        },
        {
          //特种车
          vehicleKindCode: [
            "411",
            "412",
            "413",
            "414",
            "42",
            "431",
            "432",
            "433",
            "434",
            "435",
            "437",
            "438",
            "44",
            "451",
            "452",
            "453",
            "46",
            "47",
            "48"
          ]
        }
      ];
      return (
        carType.findIndex(
          v => true === v.vehicleKindCode.includes(vehicleKindCode)
        ) + 2
      );
    },
    messagePhone(item) {
      this.messageItem = item;
      if (this.$isNull(item.custNo)) {
        this.$dialog.alert({ message: "没有客户手机号！" });
        return;
      }
      this.getQueryCustPhonePost(item.custNo)
        .then(res => {
          if (res.code === "1") {
            if (res.data.length == 0) {
              this.$dialog.alert({ message: "没有客户手机号！" });
              return false;
            }
            this.phoneList = [...res.data].reduce(
              (prev, cur) =>
                cur.phoneNumber ? [...prev, cur.phoneNumber] : prev,
              []
            );
            this.showCallPhone = true;
          } else {
            this.$dialog.alert({
              message: res.msg || res.message
            });
          }
        })
        .catch(err => {
          this.$dialog.alert({
            message: err.msg || err.message
          });
        });
    },
    //手机中间四位掩码显示
    maskPhoneNumber(phoneNumber) {
      let len = phoneNumber.length;
      return phoneNumber.substr(0, 3) + "****" + phoneNumber.substr(len - 4);
    },
    onConfirm(val) {
      let column_index = this.$refs["picker"].getColumnIndex(0);
      val = this.phoneList[column_index];
      this.showCallPhone = false;
      this.$dialog.alert({
        message: this.maskPhoneNumber(val),
        showCancelButton: true,
        cancelButtonColor: "#3B8AFE",
        confirmButtonColor: "#3B8AFE",
        confirmButtonText: "拨打电话",
        cancelButtonText: "发送短信",
        closeOnClickOverlay: true,
        callback: action => {
          /**
           * confirm 拨打电话
           * cancel 发送短信
           */
          if (action === "confirm") {
            this.callPhone(val);
          } else if (action === "cancel") {
            this.sendMessage(val);
          }
        }
      });
    },
    //拨打电话
    callPhone(phone) {
      const phoneNo = phone;
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.getPhoneCall(phoneNo, data => {
        addOrUpdateInteractions({
          userCode: localStorage.getItem("userCode"),
          createUser: localStorage.getItem("userCode"),
          custNo: this.messageItem.custNo || "",
          remark: "",
          title: "打电话",
          type: "1"
        });
      });
    },
    //发送短信
    sendMessage(phone) {
      let jsonObject = {
        messageDes: `尊敬的${this.messageItem.applicantName ||
          ""}，您的爱车${this.messageItem.licenseNo || ""}${
          this.type.name === "3" ? "已" : "将"
        }于${this.messageItem.respEndDate ||
          ""}保险到期，为使您的出行得到切实的保障，您可随时联系我。${JSON.parse(
          localStorage.getItem("userInfo")
        ).userName || ""}：${JSON.parse(localStorage.getItem("userInfo"))
          .userPhone || ""}`,
        phoneNo: phone || ""
      };
      //已续保 和 认领 短信模板不用带入
      if (this.type.name === "2" || this.type.name === "4") {
        jsonObject = {
          messageDes: "",
          phoneNo: phone
        };
      }
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.sendMessage(jsonObject, data => {
        // this.$toast("sendMessage 接口调用成功，壳响应数据:" + data);
      });
    },
    //保存脱保原因
    beforeClose(action, done, item) {
      if (action === "confirm") {
        if (
          (!this.cancelRenewalRenson || !this.selectValue.value) &&
          (this.selectValue.value === "RSI" || this.selectValue.value === "RSK")
        ) {
          this.$toast.fail("请填写脱保原因");
          done(false);
        } else {
          this.confirmNoRenewal(data, item);
          done();
        }
      } else {
        done();
      }
    },
    //获取脱保原因
    getRenewal(item) {
      this.noRenewalSelect = false;
      getRenewalExpireReason({
        compulsoryFlag: item.compulsoryFlag,
        frameNo: item.frameno,
        licenseNo: item.licenseNo,
        orgId: item.orgId,
        policyNo: item.policyNo,
        respEndDate: item.respEndDate,
        // riskCode:
        //   item.compulsoryFlag === "1"
        //     ? "DEA"
        //     : item.compulsoryFlag === "2"
        //     ? "DFA"
        //     : "DEA",
        userCode: localStorage.getItem("userCode")
      })
        .then(res => {
          if (res.code === "1" && res.data) {
            this.selectValue = Object.assign({}, this.selectValue, {
              value: res.data[0].reasonType,
              label: this.cancelRenewal.find(
                item => item.value === res.data[0].reasonType
              ).label
            });
            this.cancelRenewalRenson = res.data[0].offReason;
            //脱保原因为 其他公司 和 其他原因 显示脱保详情
            this.isRenwalResnon =
              this.selectValue.value === "RSK" ||
              this.selectValue.value === "RSI";
            item.showCancelRenewal = true;
            // this.$nextTick(() => {
            //   this.$watch("selectValue.value", val => {
            //     // if (oldVal != newVal) {
            //     this.cancelRenewalRenson = "";
            //     // }
            //   });
            // });
          } else if (res.code === "1" && !res.data) {
            item.showCancelRenewal = true;
          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
        })
        .catch(err => {})
        .finally(() => {});
    },
    //更新脱保原因
    confirmNoRenewal(data, item) {
      if (!this.selectValue.value) return false;
      this.noRenewalData = Object.assign({}, this.noRenewalData, {
        compulsoryFlag: item.compulsoryFlag || "",
        frameNo: item.frameno || "",
        id: item.id || "",
        licenseNo: item.licenseNo || "",
        policyNo: item.policyNo || "",
        reasonDetail: this.cancelRenewalRenson,
        reasonType: this.selectValue.value,
        respEndDate: item.respEndDate,
        // riskCode: item.riskCode || "",
        userCode: localStorage.getItem("userCode")
      });
      this.updateRenewalReanson_();
    },
    //上拉刷新
    onLoad() {
      this.queryRenewalList();
      // this.queryData.pageNum += 1;
    },
    /**
     * 续保已过期校验生成计划书按钮显示规则
     * T~T-30 超出三十天不显示，不包括今天
     */
    checkProspectus(date) {
      let show = true,
        monthBetweenMil = new Date().getTime() + 24 * 60 * 60 * 1000 * -31,
        endDate = new Date();
      endDate.setTime(monthBetweenMil);
      if (
        new Date().getTime() <= new Date(date).getTime() ||
        endDate.getTime() >= new Date(date).getTime()
      ) {
        show = false;
      }
      return show;
    },
    //校验推电销保单规则
    checkPolicyNoPush(item) {
      /* 不包括以下状态不可推电销 */
      if (
        !["24", "23", "21", "1", "901", "28", "401", "0", "99"].includes(
          item.handleStatus
        )
      ) {
        this.$toast.fail(
          `仅 未处理、已指派（孤儿单）、认领（业务员）、认领（系统默认）、认领（绑定保护），认领（底线保护），指派（底线保护），机构自留”的续保任务可推电销！`
        );
        this.flag = false;
        return false;
      }
      // 操作时间范围为保单到期之前 === 优化
      /**
       * 未处理、已指派（孤儿单）、认领（业务员）、认领（系统默认）、认领（绑定保护）推电销（撤回）机构自留
       * 可以操作推电销按钮
       */
      if (
        ["0", "21", "1", "901", "28", "401", "99"].includes(item.handleStatus)
      ) {
        /**
         * 操作时间范围为保单到期之前
         */
        if (this.d.getTime() >= new Date(item.respEndDate).getTime()) {
          this.$toast.fail(`不在操作时间范围内，不能进行推电销操作`);
          this.flag = false;
          return false;
        }
      } else {
        //认领（底线保护）允许操作推电销 设置开关
        if (
          item.handleStatus === "23" &&
          JSON.parse(localStorage.getItem("ConfigParams")).claimFlag === "Y"
        ) {
          if (this.d.getTime() >= new Date(item.respEndDate).getTime()) {
            this.$toast.fail(`不在操作时间范围内，不能进行推电销操作`);
            this.flag = false;
            return false;
          } else {
            return;
          }
        } else {
          this.$toast.fail(`已认领（底线保护）不能进行推电销操作`);
          this.flag = false;
          return false;
        }
        //指派（底线保护）允许操作推电销 设置开关
        // eslint-disable-next-line no-unreachable
        if (
          item.handleStatus === "24" &&
          JSON.parse(localStorage.getItem("ConfigParams")).assignFlag === "Y"
        ) {
          if (this.d.getTime() >= new Date(item.respEndDate).getTime()) {
            this.$toast.fail(`不在操作时间范围内，不能进行推电销操作`);
            this.flag = false;
            return false;
          } else {
            return;
          }
        } else {
          this.$toast.fail(`已指派（底线保护）不能进行推电销操作`);
          this.flag = false;
          return false;
        }
      }
    },
    //校验认领保单规则
    checkPolicyNoClaim(item) {
      //保单状态为“未处理”的可以操作认领按钮
      if (item.handleStatus != 0) {
        this.$toast.fail(`仅“未处理”的续保任务可认领！`);
        this.flag = false;
        return false;
      }
      //从人渠道归属为：直销个代C0000015、非销（HR口径，非销售序列）C0000023、四级机构C0000022
      if (
        this.channelCode === "C0000015" ||
        this.channelCode === "C0000022" ||
        this.channelCode === "C0000023"
      ) {
        //30天平台
        if (this.isPlatform === "30") {
          if (
            item.handleStatus === "0" &&
            !(this.d.getDate() >= 15 && this.d.getDate() <= 27)
          ) {
            this.$toast.fail(
              `${this.d.getMonth() + 1}月15-27日认领/推送${this.d.getMonth() +
                4}月到期未处理的单子！`
            );
            this.flag = false;
            return false;
          } //90天平台
        } else if (this.isPlatform === "90") {
          if (
            item.handleStatus === "0" &&
            !(this.d.getDate() >= 15 && this.d.getDate() <= 27)
          ) {
            this.$toast.fail(
              `${this.d.getMonth() + 1}月15-27日认领/推送${this.d.getMonth() +
                5}月到期未处理的单子！`
            );
            this.flag = false;
            return false;
          }
        } else if (
          this.isPlatform === "40" ||
          this.isPlatform === "45" ||
          this.isPlatform === "60"
        ) {
          if (
            item.handleStatus === "0" &&
            !(this.d.getDate() >= 1 && this.d.getDate() <= 8)
          ) {
            this.$toast.fail(
              `${this.d.getMonth() + 1}月1-8日认领/推送${this.d.getMonth() +
                4}月到期未处理的单子！`
            );
            this.flag = false;
            return false;
            //浙江 60天平台 只统计存续年是0的未处理的续保任务
          } else if (
            this.isPlatform === "60" &&
            data.comCode[this.$localStorage.comCode.substring(0, 4)] ===
              "浙江分公司"
          ) {
            if (item.survivingYear != "0") {
              this.$toast.fail(`仅存续年是0可认领!`);
              this.flag = false;
              return false;
            }
          }
        }
        //（2）从人渠道归属为互动渠道C0000019，不区分续保平台配置，全国一致，操作频率为每个季度一次
      } else if (this.channelCode === "C0000019") {
        if (
          this.d.getMonth() + 1 != 3 &&
          this.d.getMonth() + 1 != 6 &&
          this.d.getMonth() + 1 != 9 &&
          this.d.getMonth() + 1 != 12
        ) {
          this.$toast.fail(
            `每个季度最后一个月（3、6、9、12月）的15-27号，操作${this.getQuarter() +
              2} 季度整个季度的到期未处理的数据！`
          );
          this.flag = false;
          return false;
        }
        if (!(this.d.getDate() >= 15 && this.d.getDate() <= 27)) {
          this.$toast.fail(
            `每个季度最后一个月（3、6、9、12月）的15-27号，操作${this.getQuarter() +
              2} 季度整个季度的到期未处理的数据！`
          );
          this.flag = false;
          return false;
        }
        if (item.handleStatus != "0") {
          this.$toast.fail(
            `每个季度最后一个月（3、6、9、12月）的15-27号，操作${this.getQuarter() +
              2} 季度整个季度的到期未处理的数据！`
          );
          this.flag = false;
          return false;
        }
      } else {
        //其他从人渠道用户登录，不需要操作认领，故不展示认领按钮
      }
    },
    /**
     * api调用
     * ======================================================
     */
    //获取续保任务列表
    async queryRenewalList() {
      await queryRenewalTaskInfo(this.queryData)
        .then(res => {
          this.responseDate = res.responseDate;
          /**
           * list为null 至为空数组
           */
          if (res.data) {
            if (!res.data.list) res.data.list = [];
          }

          if (res.code === "1") {
            this.resultDataNum = Object.assign(
              {},
              this.resultDataNum,
              res.data
            );
            this.resultData.push(...res.data.list);
            Object.keys(this.resultData).map(i => {
              //跟踪记录和撤电销显示
              this.$set(this.resultData[i], "showTip", false);
              //续保认领选中按钮
              this.$set(this.resultData[i], "checked", false);
              //脱保原因按钮显示
              this.$set(this.resultData[i], "showCancelRenewal", false);
              //报价按钮显示
              this.$set(this.resultData[i], "showQuoteInfo", true);
              //联系客户弹框显示
              this.$set(this.resultData[i], "showPhoneMessage", false);

              //续保已过期生成计划书显示 放开限制====
              // this.$set(
              //   this.resultData[i],
              //   "showProspectus",
              //   this.type.name === "3"
              //     ? this.checkProspectus(this.resultData[i].respEndDate)
              //     : true
              // );
              //========

              //未续保state
              this.resultData[i].state = {
                ...this.stateColor(
                  this.resultData[i].quotationArea,
                  this.resultData[i].quotatFlag
                )
              };
            });
            this.queryData.pageNum += 1;
            // 加载状态结束
            this.loading = false;
            if (this.resultData.length >= res.data.totalSize) {
              this.finished = true;
            }
          } else {
            this.$dialog.alert({
              message: res.msg || res.message
            });
          }
        })
        .catch(err => {
          this.error = true;
        });
    },
    //获取续保报价信息
    async queryRenewalQuoteInfo_(index, val) {
      this.resultData[index].showQuoteInfo = false;
      // setTimeout(() => {
      //   this.resultData.forEach((item, i) => {
      //     if (item.policyNo === val.policyNo && i == index) {
      //       this.$set(this.resultData[i], "quoteMoney", "111");
      //     }
      //   });
      // }, 500);
      await queryRenewalQuoteInfo({
        frameNo: val.frameno || "",
        compulsoryFlag: val.compulsoryFlag || "",
        phone: val.phone || "",
        quoateType: val.quoateType || "",
        // orgId: localStorage.getItem("comCode") || "",
        respEndDate: val.respEndDate || "",
        userCode: localStorage.getItem("userCode") || ""
        // userName: JSON.parse(localStorage.getItem("userInfo")).userName || ""
      }).then(res => {
        if (res && res.code === "1") {
          this.resultData.forEach((item, i) => {
            if (item.frameNo === val.frameNo && i == index) {
              this.$set(
                this.resultData[i],
                "quoteMoney",
                res.data.quoteMoney || ""
              );
              this.$set(
                this.resultData[i],
                "renewalCompulsoryFlag",
                res.data.renewalCompulsoryFlag || ""
              );
            }
          });
          addOrUpdateInteractions({
            userCode: localStorage.getItem("userCode"),
            createUser: localStorage.getItem("userCode"),
            custNo: val.custNo || "",
            remark: "",
            title: "续保报价",
            type: "3"
          });
        } else {
          this.$dialog.alert({
            message: res.msg
          });
        }
      });
    },
    //认领和推销
    async pushClaim() {
      this.checkedData.policyList = [...[]];
      //筛选出选中的保单
      Object.values(this.resultData)
        .filter(item => item.checked === true)
        .map(item => {
          this.checkedData.policyList.push({
            policyNos: item.policyNo,
            transferReason: item.transferReason
          });
          /**
           * 校验认领和推电销规则
           * 1 认领 2 推电销
           */
          this.checkedData.handleStatus === "1"
            ? this.checkPolicyNoClaim(item)
            : this.checkPolicyNoPush(item);
        });
      this.checkedData.userCode = localStorage.getItem("userCode") || "";
      this.flag
        ? await renewalClaim(this.checkedData).then(res => {
            if (res.code === "1") {
              //认领成功XX张保单、失败XX张保单，总认领保单数XX张，实时推送率XX.XX%
              let text =
                this.checkedData.handleStatus === "1" ? "认领" : "推电销";
              this.$toast.success(
                `${text}成功${res.data.successCount || "0"}张保单、失败${res
                  .data.failedCount || "0"}张保单,总${text}保单数${res.data
                  .totalCount || "0"}张,，实时推送率${(
                  (Number(res.data.successCount || 0) /
                    Number(res.data.totalCount || 0)) *
                  100
                ).toFixed(2)}%`
              );
              //埋点
              trackMap(
                this.checkedData.handleStatus === "1"
                  ? "c_my_renewal_renewal_claim"
                  : "c_my_renewal_renewal_push_pin",
                this.checkedData.handleStatus === "1"
                  ? "我的续保续保认领"
                  : "我的续保续保推电销",
                {
                  c_salesperson_type: "N",
                  c_market_type: "Y",
                  c_market_name: "",
                  c_market_id: "",
                  c_event_category: "续保"
                }
              );
              //认领和推电销成功后重新刷新页面
              this.queryData = Object.assign({}, this.queryData, {
                pageNum: 1,
                pageSize: 10
              });
              this.resultData = [...[]];
              this.queryRenewalList();
            } else {
              this.$dialog.alert({
                message: res.msg
              });
            }
          })
        : "";
      //把开关打开
      this.flag = true;
    },
    //更新脱保原因
    async updateRenewalReanson_() {
      await updateUnRenewReason(this.noRenewalData).then(res => {
        if (res.code === "1") {
          this.$toast.success("更新退保原因成功");
          this.cancelRenewalRenson = "";
          this.selectValue = Object.assign({}, this.selectValue, {
            value: "",
            label: "请选择"
          });
        } else {
          this.$dialog.alert({
            message: res.message || res.msg
          });
        }
      });
    },
    //查询订单信息
    getDetails_(orderId) {
      return new Promise((resolve, reject) => {
        getDetails({
          orderId: orderId,
          userCode: localStorage.getItem("userCode")
        })
          .then(res => {
            resolve(res);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    //获取手机号getQueryCustPhone
    getQueryCustPhonePost(custNo) {
      return new Promise((resolve, reject) => {
        getQueryCustPhone({
          custNo: custNo || ""
        })
          .then(res => {
            resolve(res);
          })
          .catch(err => {
            reject(err);
          });
      });
    }
  }
};
</script>

<style lang="less" scoped>
.contactList {
  /deep/ * p {
    margin-bottom: 0;
  }
  background: #f7f7f7;
  // margin-top: 40px;
  &-borderRadius {
    padding-top: 6px;
    height: 8px;
    background: linear-gradient(
      129deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    div {
      height: 8px;
      border-radius: 0.21333rem 0.21333rem 0 0;
      background: #fff;
    }
    // position: sticky;
    // position: -webkit-sticky;
    // top: 44px;
    // z-index: 99;
  }
  &-head {
    display: flex;
    display: -webkit-flex;
    position: relative;
    border-radius: 8px 8px 0px 0px;
    // position: sticky;
    // position: -webkit-sticky;
    // top: 50px;
    // z-index: 99;
    form {
      flex: 1;
      -webkit-flex: 1;
    }
    & > p {
      height: 54px;
      line-height: 54px;
      background: #fff;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #333;
      padding-left: 16px;
    }
    /deep/.van-search {
      // flex: 1;
      // -webkit-flex: 1;
      /deep/.van-field__control {
        font-size: 14px;
        // color: #c0c0c0;
      }
    }
    /deep/ .van-search__action {
      margin-right: 10px;
    }
    .screen {
      img {
        width: 15px;
        height: 17px;
        vertical-align: middle;
      }
    }
    /deep/ .van-icon {
      vertical-align: -2px;
    }
    .renewalEnd {
      position: absolute;
      // bottom: -175px;
      top: 50px;
      left: 15px;
      width: 92px;
      height: 80px;
      background: rgba(255, 255, 255, 1);
      box-shadow: 0px 1px 14px 0px rgba(0, 0, 0, 0.17);
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      z-index: 9;
      border-radius: 5px;
      div {
        margin-bottom: 10px;
        text-align: center;
      }
      &::before {
        display: inline-block;
        content: "";
        height: 0;
        width: 0;
        border-width: 10px;
        border-style: solid;
        border-color: transparent transparent #fff transparent;
        transform: translate(15px, -19px);
        -webkit-transform: translate(15px, -19px);
        box-shadow: transparent 1px 14px 0px rgba(0, 0, 0, 0.17);
      }
    }
  }
  .contactList-content {
    margin: 10px 15px;
    background: #fff;
    padding: 15px;
    border-radius: 5px;
    display: flex;
    display: -webkit-flex;
    align-items: center;
    -webkit-align-items: center;
    justify-content: space-between;
    .contactList-content-checkbox {
      margin-right: 10px;
      flex-shrink: 0;
    }
    .contactList-content-pin {
      width: max-content;
      padding: 0 6px;
      height: 15px;
      line-height: 15px;
      text-align: center;
      background: linear-gradient(
        270deg,
        rgba(229, 79, 71, 1) 0%,
        rgba(251, 132, 72, 1) 100%
      );
      border-radius: 8px;
      font-size: 9px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
      margin-bottom: 10px;
      span {
        // -webkit-transform: scale(0.8);
        // transform: scale(0.8);
      }
    }
    .contactList-content_head {
      display: flex;
      display: -webkit-flex;
      -webkit-justify-content: space-between;
      justify-content: space-between;
      margin-bottom: 13px;
      position: relative;
      /deep/.van-checkbox {
        margin-right: -50px;
      }
      .contactList-content_head--span {
        font-size: 16px;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        flex-shrink: 0;
        -webkit-flex-shrink: 0;
        img {
          width: 21px;
          height: 17px;
          margin-right: 12px;
        }
        span {
          display: inline-block;
          width: 190px;
        }
      }
      .contactList-content_head--span_ {
        font-size: 13px;
        font-weight: 400;
        color: #a7a7a7;
        flex-shrink: 0;
        -webkit-flex-shrink: 0;
      }
      .state {
        position: absolute;
        top: 4px;
        right: 0;
        width: 39px;
        height: 39px;
        border: 1px solid rgba(253, 99, 99, 1);
        border-radius: 39px;
        color: rgba(253, 99, 99, 1);
        font-size: 9px;
        transform: rotate(-14deg);
        -webkit-transform: rotate(-14deg);
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -webkit-flex-direction: column;
        flex-direction: column;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center;
        -webkit-box-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        p {
          // -webkit-transform: scale(0.8);
          // transform: scale(0.8);
          margin-top: 1px;
        }
      }
      .nocarState {
        position: absolute;
        top: 4px;
        right: 0;
        padding: 2px;
        border: 1px solid rgba(253, 99, 99, 1);
        color: #fd6363;
        font-size: 9px;
        transform: rotate(-14deg);
        -webkit-transform: rotate(-14deg);
      }
    }
    .contactList-content_details {
      .claim span {
        margin-bottom: 14px;
      }
      .contactList-content_details_ {
        // background: rgba(250, 250, 250, 1);
        display: flex;
        // display: -webkit-flex;
        padding: 12px 0px 15px 0px;
        flex-wrap: wrap;
        // -webkit-flex-wrap: wrap;
        justify-content: space-between;
        // -webkit-justify-content: space-between;
        // box-sizing: border-box;
        margin-bottom: 10px;
        span {
          width: 50%;
          // flex: 1;
          font-size: 13.5px;
          color: #666;
          margin-bottom: 8px;
        }
        span:nth-child(odd) {
          // flex-shrink: 1;
          white-space: nowrap;
        }
        span:nth-child(even) {
          text-align: right;
          // flex-shrink: 0;
        }
        span:nth-child(6) {
          /deep/.van-icon {
            margin-right: 3px;
          }
        }
        img {
          width: 15px;
          height: 15px;
          vertical-align: middle;
          margin-right: 4px;
        }
        .amount-del {
          text-decoration: line-through;
          color: #c2c2c2;
          margin-left: 5px;
        }
      }
    }

    .contactList-content_footer {
      display: flex;
      display: -webkit-flex;
      align-items: center;
      position: relative;
      .CancelRenewal-title {
        font-size: 14px;
        font-family: PingFang-SC-Medium, PingFang-SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        text-align: left;
        padding-left: 10px;
        margin: 20px 0 10px;
      }
      .button {
        flex: 1;
        -webkit-flex: 1;
        text-align: right;
        margin-right: 5px;
        margin-left: 2px;
        white-space: nowrap;
      }
      .van-button {
        height: 21px;
        // line-height: 21px;
        border-radius: 3px;
        // opacity: 0.25;
        border-color: rgba(59, 138, 254, 0.25);
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #3b8afe;
        padding: 0 4px;
        a {
          color: #3b8afe;
        }
      }
      .ellipsis-icon {
        vertical-align: bottom;
        transform: rotate(90deg);
        -webkit-transform: rotate(90deg);
      }
      .quotationTimes {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #333;
        flex-shrink: 0;
      }
      .tips {
        position: absolute;
        right: -15px;
        top: 42px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        box-shadow: 2px 2px 14px 2px rgba(0, 0, 0, 0.17);
        border-radius: 10px;
        padding: 14px;
        background: #fff;
        z-index: 9;
        span {
          display: block;
          text-align: center;
          padding: 8px 0;
        }
        .triangle {
          display: inline-block;
          content: "";
          height: 0;
          width: 0;
          border-width: 10px;
          border-style: solid;
          border-color: transparent transparent #fff transparent;
          position: absolute;
          top: -36px;
          right: 15%;
          box-shadow: transparent 1px 14px 0px rgba(0, 0, 0, 0.17);
          pointer-events: none;
        }
      }
    }
    /deep/.van-dialog {
      overflow: inherit;
    }
    .CancelRenewal {
      text-align: center;
      border-radius: 10px;
      // overflow: hidden;
      /deep/.van-dialog__footer--buttons {
        border-radius: 10px;
      }
      /deep/.van-cell__value {
        background: #f5f5f5;
        padding: 10px;
      }
      .select-noRenewalReason {
        position: relative;
        .select {
          width: 276px;
          height: 36px;
          line-height: 36px;
          background: rgba(245, 245, 245, 1);
          border-radius: 4px;
          font-size: 14px;
          font-family: PingFang-SC-Medium, PingFang-SC;
          font-weight: 500;
          color: rgba(102, 102, 102, 1);
          margin: 0 auto;
          display: flex;
          display: -webkit-flex;
          justify-content: space-between;
          align-items: center;
          padding: 0 10px;
          margin: 0 10px;
        }
        .select-option {
          position: absolute;
          width: 296px;
          height: 200px;
          overflow-y: scroll;
          top: 36px;
          z-index: 99;
          left: 15px;
          margin: 10px;
          border-top: none;
          margin: 0 auto;
          background: #fff;
          z-index: 9999;
          p {
            margin-bottom: 5px;
            padding: 5px 0;
          }
          .select-option-actived {
            background: #f5f5f5;
          }
        }
      }
    }
  }
  .contactList-num {
    display: flex;
    display: -webkit-flex;
    background: #fff;
    height: 79px;
    padding: 0 28px;
    margin-top: 10px;
    justify-content: space-between;
    -webkit-justify-content: space-between;
    align-items: center;
    -webkit-align-items: center;
    p {
      span {
        display: block;
        text-align: center;
      }
      span:nth-child(1) {
        font-size: 20px;
        font-family: DINAlternate-Bold, DINAlternate;
        font-weight: bold;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 10px;
      }
      span:nth-child(2) {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #666;
      }
    }
  }
  .contactList-screen--footer {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    display: flex;
    display: -webkit-flex;
    /deep/.van-button {
      // width: 50%;
      flex: 1;
      -webkit-flex: 1;
      // margin: 0 7.5px;
    }
  }
  .contact-customers {
    position: relative;
    &--phoneMes {
      position: absolute;
      top: 36px;
      width: 50px;
      // border-radius: 5px;
      // background: rgba(255, 255, 255, 1);
      // box-shadow: 0px 1px 5px 0px rgba(0, 0, 0, 0.17);
      img {
        width: 20px;
        height: 20px;
        margin-left: 5px;
      }
    }
  }
  .phoneList {
    text-align: center;
    border-radius: 10px;
    // padding-top: 20%;
    p {
      margin: 10px;
      font-size: 14px;
    }
  }
  .margin {
    &::after {
      content: "";
      height: 130px;
      width: 100vw;
      display: block;
    }
  }
  .fixed_bottom {
    height: calc(100vh - 150px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    margin-bottom: 80px;
    // padding-bottom: 45px;

    .van-list {
    }
    //   display: -webkit-box;
    //   display: -webkit-flex;
    //   display: flex;
    //   -webkit-box-orient: vertical;
    //   -webkit-box-direction: normal;
    //   -webkit-flex-direction: column;
    //   flex-direction: column;
    //   height: -webkit-fill-available;
    //   -webkit-box-align: center;
    //   align-items: center;
    //   -webkit-align-items: center;
    // .contactList-content {
    //   // margin-bottom: 45px;
    // }
  }
  .noData {
    width: 180px;
    height: 150px;
    margin: 0 auto;
    margin-top: 76px;
    background: url("../../../assets/renewal/renewalListData.png") no-repeat;
    background-size: cover;
    position: relative;
    // img {
    //   width: 180px;
    //   height: 150px;
    //   display: block;
    // }
    &::after {
      left: 50%;
      position: absolute;
      bottom: -50px;
      transform: translateX(-50%);
      content: "暂无数据";
      display: block;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
    }
  }
}
/deep/.van-picker-column__item--selected {
  color: #3b8afe;
}
</style>
