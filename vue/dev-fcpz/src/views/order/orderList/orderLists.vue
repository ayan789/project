/* zhangminjie 订单列表 */
<template>
  <div id="orderList">
    <div class="Listheader">
      <van-nav-bar title="订单管理" left-arrow @click-left="goBack" />
      <van-tabs
        v-model="insuranceTypeTab.insuranceType"
        @click="changeInsureTypeTabs"
      >
        <van-tab
          v-for="item in insuranceOptionList"
          :key="item.id"
          :name="item.id"
        >
          <div slot="title">{{ item.title }}</div>
        </van-tab>
      </van-tabs>
      <div class="radiusDiv"></div>
    </div>
    <div class="orderStatusheader">
      <div class="searchOrder">
        <!-- <img
          class="orderListSearch"
          src="@/assets/order/orderList/search.png"
          alt="search"
          @click="searchOrderList"
        /> -->
        <form class="form" action="javascript:return true">
          <!-- <input
            v-if="insuranceTypeTab.insuranceType == 2"
            type="search"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[0]"
            @keyup.enter="searchOrderList"
          />
          <input
            v-if="insuranceTypeTab.insuranceType == 0"
            type="search"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[1]"
            @keyup.enter="searchOrderList"
          />
          <input
            v-if="insuranceTypeTab.insuranceType == 1"
            type="search"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[2]"
            @keyup.enter="searchOrderList"
          />
          <van-icon
            v-show="infoObj.searchInput != ''"
            name="close"
            class="clear"
            size="12"
            @click="infoObj.searchInput = ''"
          /> -->
          <van-search
            v-if="insuranceTypeTab.insuranceType == 2"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[0]"
            @search="searchOrderList"
            @clear="infoObj.searchInput = ''"
          />
          <van-search
            v-if="insuranceTypeTab.insuranceType == 0"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[1]"
            @search="searchOrderList"
            @clear="infoObj.searchInput = ''"
          />
          <van-search
            v-if="insuranceTypeTab.insuranceType == 1"
            class="search"
            v-model="infoObj.searchInput"
            :placeholder="placeholder[2]"
            @search="searchOrderList"
            @clear="infoObj.searchInput = ''"
          />
        </form>
        <div @click="showPopup">筛选</div>
        <img
          class="screenRight"
          src="@/assets/order/orderList/screenRight.png"
          alt="filter"
          @click="showPopup"
        />
      </div>
      <van-tabs v-model="orderTabObj.orderActive" @click="changeTab">
        <van-tab
          v-for="item in orderOptionList"
          :key="item.status"
          :name="item.status"
        >
          <div slot="title">{{ item.title }}</div>
          <div v-show="arrowDownShow" @click="arrowDown" class="arrow-down">
            <van-icon v-show="!optionFlag" name="arrow-down" />
            <van-icon v-show="optionFlag" name="arrow-up" />
          </div>
          <div class="option" :class="[optionFlag ? 'optionBlock' : '']">
            <div
              v-for="(item, index) in screenShowList"
              :key="index"
              @click="screenShow(item, index)"
            >
              {{ item }}
            </div>
          </div>
        </van-tab>
      </van-tabs>
      <div
        class="cardList"
        id="cardList"
        v-show="insuranceTypeTab.insuranceType === 2"
      >
        <!-- :ordersListObj="carOrderlist" -->
        <!-- 车险 -->
        <carInsurance
          :insuranceType="insuranceTypeTab.insuranceType"
          :status="orderTabObj.orderActive"
          :searchInput="infoObj"
          :vehiceFlag="vehiceFlag"
          :judgeFlag="orderType"
          @typeFlagevent="typeFlagevent"
          ref="catInsurance"
        ></carInsurance>
      </div>
      <div class="cardList" v-show="insuranceTypeTab.insuranceType === 0">
        <healthInsurance
          :insuranceType="insuranceTypeTab.insuranceType"
          :status="orderTabObj.orderActive"
          :searchInput="infoObj"
          ref="healthInsurance"
        ></healthInsurance>
      </div>
      <div class="cardList" v-show="insuranceTypeTab.insuranceType === 1">
        <propertyInsurance
          :insuranceType="insuranceTypeTab.insuranceType"
          :status="orderTabObj.orderActive"
          :searchInput="infoObj"
          ref="propertyInsurance"
        ></propertyInsurance>
      </div>
    </div>
    <van-popup
      v-model="popupShow"
      position="bottom"
      :style="{ height: '70%' }"
      class="popup"
    >
      <div class="dressing">
        <div class="orderData">订单日期</div>
        <orderAll
          :incomeList="nocarList"
          @toparents="getDays"
          ref="orderAll1"
        ></orderAll>
        <div class="positionRelative">
          <div class="startDay">
            <van-field
              v-model="infoObj.startDay"
              placeholder="开始日期"
              disabled
              @click="startDayClick"
              class="startDay"
              input-align="center"
            />
          </div>
          <div class="line">-</div>
          <van-field
            class="endDay"
            v-model="infoObj.endDay"
            placeholder="结束日期"
            disabled
            @click="endDayClick"
            input-align="center"
          />
        </div>
        <van-overlay :show="showDatePicker">
          <van-datetime-picker
            v-if="dateType == 1"
            v-model="dafaultDate"
            type="date"
            class="datePicker"
            title="选择开始时间"
            :min-date="minDate"
            :max-date="maxDate"
            @confirm="startDateConfirm"
            @cancel="showDatePicker = false"
          />
          <van-datetime-picker
            v-if="dateType == 2"
            v-model="dafaultDate"
            type="date"
            class="datePicker"
            title="选择结束时间"
            :min-date="minDate"
            :max-date="maxDate"
            @confirm="endDateConfirm"
            @cancel="showDatePicker = false"
          />
        </van-overlay>
        <!-- <van-calendar
          v-model="calendarShow"
          type="range"
          @confirm="onConfirm"
          class="calendar"
          color="#358DFD"
          :min-date="minDate"
          :max-date="maxDate"
          :default-date="dafaultDate"
          :allow-same-day="allowSameDay"
        />-->
        <div class="orderData">保费金额</div>
        <orderAll
          :incomeList="premiumList"
          @toparents="getPremium"
          ref="orderAll2"
        ></orderAll>
        <div class="positionRelative">
          <div class="startDay">
            <van-field
              v-model="infoObj.premiumMin"
              input-align="center"
              @blur="changeMsg"
              placeholder="最低保费"
              clearable
            />
          </div>
          <div class="line">-</div>
          <van-field
            v-model="infoObj.premiumMax"
            @blur="changeMsg1"
            input-align="center"
            placeholder="最高保费"
            clearable
          />
        </div>
        <div class="orderData">出单来源</div>
        <orderAll
          :incomeList="issueList"
          @toparents="singleSource"
          ref="orderAll3"
        ></orderAll>
        <confirmBottom @click="filterOrderList"></confirmBottom>
      </div>
    </van-popup>
  </div>
</template>

<script>
import orderAll from "../common/orderAll.vue";
import confirmBottom from "@/components/confirmBottom.vue";
import carInsurance from "../common/carInsurance.vue";
import propertyInsurance from "../common/propertyInsurance.vue";
import healthInsurance from "../common/healthInsurance.vue";
import { DatetimePicker, Overlay, Search } from "vant";
export default {
  components: {
    orderAll,
    confirmBottom,
    carInsurance,
    propertyInsurance,
    healthInsurance,
    [DatetimePicker.name]: DatetimePicker,
    [Overlay.name]: Overlay,
    [Search.name]: Search
  },

  data: () => ({
    arrowDownShow: true,
    screenShowList: ["待处理", "暂存", "核保中", "待提交", "核保退回"],
    orderType: false,
    vehiceFlag: "2",
    optionFlag: false,
    infoObj: {
      dataVal: "", //
      searchInput: "", //搜索
      dataType: "", //日期选项
      startDay: "", //开始日期
      endDay: "", //结束日期
      premiumMsg: "", //保额选项
      premiumMin: "", //最低保额
      premiumMax: "", //最高保额
      singleSourceMsg: "", //出单来源
      screenVal: ""
    },
    startDay: "", //开始日期
    endDay: "", //结束日期
    premiumList: ["全部", "0-2000元", "2000-5000元", "5000元以上"],
    issueList: ["全部", "超级销售APP", "其他"],
    nocarList: ["全部", "一周内", "一月内", "三月内", "六月内"],
    popupShow: false,
    calendarShow: false,
    orderTabObj: {
      orderActive: 1,
      status: 0
    },
    orderTabIndex: 4,
    insuranceTypeTab: {
      insuranceType: 2
    },
    //险种类型
    insuranceOptionList: [
      {
        id: 2,
        title: "车险"
      },
      {
        id: 0,
        title: "意健险"
      },
      {
        id: 1,
        title: "财产险"
      }
    ],
    orderOptionList: [
      {
        title: "待处理",
        status: 1
      },
      {
        title: "待付款",
        status: 2
      },
      {
        title: "已付款",
        status: 3
      },
      {
        title: "全部",
        status: 0
      }
    ],
    resData: {},
    currOrderlistObj: {
      size: 10,
      page: 1,
      insuranceType: 1,
      ordersList: [],
      status: 4
    },
    orderListArr: [],
    carOrderlist: [],
    placeholder: [
      "车牌号、投保人、被保人、车主",
      "投保人姓名、保单号、手机号",
      "投保人、车牌号、保单号、手机号"
    ],
    maxDate: new Date(new Date().getTime()),
    minDate: new Date(
      new Date().getFullYear() - 2,
      new Date().getMonth(),
      new Date().getDate()
    ),
    dafaultDate: new Date(new Date().getTime()),
    showOverlay: false,
    allowSameDay: true,
    showDatePicker: false,
    dateType: 1, // 1 开始时间， 2 结束时间
    selectDate: {
      sDate: "",
      eDate: ""
    }
  }),
  created() {
    this.$globalSetLocalStorage();
    if (this.$route.query.typeFlag) {
      sessionStorage.setItem("typeFlag", this.$route.query.typeFlag);
      this.orderTabObj.orderActive = +sessionStorage.getItem("typeFlag");
    } else {
      // this.orderTabObj.orderActive = 0;
    }
    if (this.$route.query.status) {
      sessionStorage.setItem("typeFlag", this.$route.query.status);
      this.orderTabObj.orderActive = +sessionStorage.getItem("typeFlag");
    }
    // if (this.$route.query.status) {
    //   sessionStorage.setItem("typeFlag", this.$route.query.status);
    //   this.orderTabObj.orderActive = +sessionStorage.getItem("typeFlag");
    // }
    if (this.$route.query.moudle) {
      this.insuranceTypeTab.insuranceType = +this.$route.query.moudle;
    }
    if (sessionStorage.getItem("typeFlag")) {
      this.orderTabObj.orderActive = +sessionStorage.getItem("typeFlag");
    } else {
      this.orderTabObj.orderActive = 1;
    }
  },
  mounted() {
    setTimeout(() => {
      this.infoObj.searchInput = sessionStorage.getItem("searchInputValue");
    }, 500);
  },
  methods: {
    //  待处理箭头显示隐藏
    arrowDown() {
      this.optionFlag = !this.optionFlag;
    },
    //  待处理筛选
    screenShow(item, index) {
      this.orderOptionList[0].title = item;
      switch (index) {
        case 0:
          this.infoObj.screenVal = "";
          break;
        case 1:
          this.infoObj.screenVal = "10";
          break;
        case 2:
          this.infoObj.screenVal = "21";
          break;
        case 3:
          this.infoObj.screenVal = "20";
          break;
        case 4:
          this.infoObj.screenVal = "24";
          break;
      }
      this.optionFlag = !this.optionFlag;
      this.$refs.catInsurance.getdataList();
    },
    typeFlagevent(typeFlagevent) {
      this.orderType = typeFlagevent;
    },
    // 初始化订单列表数据
    // initOrderlist(obj) {
    //   this.carOrderlist = obj;
    // },
    //返回上一页
    goBack() {
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb();
      } else {
        this.$globalBack();
      }
      // this.$router.go("-1");
    },
    // 搜索车牌/投保人/保单号/手机号
    searchOrderList() {
      switch (this.vehiceFlag) {
        case "2":
          sessionStorage.setItem(
            "searchInputValue",
            this.infoObj.searchInput.trim()
          );
          // this.$refs.catInsurance.getInsureDatalist();
          this.$refs.catInsurance.getdataList();
          break;
        case "0":
          this.$refs.healthInsurance.getdataList();
          break;
        case "1":
          this.$refs.propertyInsurance.getdataList();
          break;
      }
    },
    //险种类型切换
    changeInsureTypeTabs(type) {
      // this.insuranceTypeTab.insuranceType = type;
      if (type != 2) {
        this.arrowDownShow = false;
        this.optionFlag = false;
      } else {
        this.arrowDownShow = true;
      }
      this.vehiceFlag = String(type);
      switch (type) {
        //意健险
        case 0:
          this.$refs.healthInsurance.getdataList();
          break;
        //财产险
        case 1:
          // console.log(3222, this.$refs.propertyInsurance);
          // this.$utils.removeEmpty(this.$refs.propertyInsurance[0].dataList);
          // this.$refs.propertyInsurance
          this.$refs.propertyInsurance.getdataList();
          break;
        //车险
        case 2:
          // this.$utils.removeEmpty(this.$refs.catInsurance[0].dataList);
          //暂时调用子组件的下拉刷新 若有问题 直接调接口
          this.$refs.catInsurance.getdataList();
          break;
      }
    },
    // tab页切换
    changeTab(type) {
      this.$refs.catInsurance.getInsureDatalist();
      let preType = sessionStorage.getItem("typeFlag");

      sessionStorage.setItem("typeFlag", type);
      console.log(preType + "=====:" + type);
      if (this.insuranceTypeTab.insuranceType === 2) {
        this.orderTabObj.orderActive = type;
        if (type == 1 && preType == 1) {
          this.optionFlag = !this.optionFlag;
        } else {
          this.optionFlag = false;
        }

        // this.$refs.catInsurance.orderStatusFlag = type;
        this.$refs.catInsurance.getdataList();
      }
      if (this.insuranceTypeTab.insuranceType === 1) {
        this.orderTabObj.orderActive = type;
        // this.$refs.propertyInsurance.form.status = type === 0 ? 4 : type;
        this.$refs.propertyInsurance.getdataList();
      }
      if (this.insuranceTypeTab.insuranceType === 0) {
        this.orderTabObj.orderActive = type;
        // console.log(this.$refs.healthInsurance.form.status);
        // this.$refs.healthInsurance.form.status = type === 0 ? 4 : type;
        this.$refs.healthInsurance.getdataList();
      }
    },
    // 展示过滤图层
    showPopup() {
      this.popupShow = true;
      this.selectDate.sDate = "";
      this.selectDate.eDate = "";
      this.infoObj = {
        dataVal: "", //
        searchInput: "", //搜索
        dataType: "", //日期选项
        startDay: "", //开始日期
        endDay: "", //结束日期
        premiumMsg: "", //保额选项
        premiumMin: "", //最低保额
        premiumMax: "", //最高保额
        singleSourceMsg: "" //出单来源
      };
      this.$nextTick(() => {
        this.$refs.orderAll1.setActive();
        this.$refs.orderAll2.setActive();
        this.$refs.orderAll3.setActive();
      });
    },
    // 获取时间查询
    getDays(val) {
      this.dataVal = val;
      if (this.dataVal) {
        this.infoObj.startDay = "";
        this.infoObj.endDay = "";
      }
      if (this.dataVal === "全部") {
        this.infoObj.dataType = "";
      }
      if (this.dataVal === "一周内") {
        this.infoObj.dataType = "1";
      }
      if (this.dataVal === "一月内") {
        this.infoObj.dataType = "2";
      }
      if (this.dataVal === "三月内") {
        this.infoObj.dataType = "3";
      }
      if (this.dataVal === "六月内") {
        this.infoObj.dataType = "4";
      }
    },
    // 获取保费金额
    getPremium(val) {
      if (val) {
        this.infoObj.premiumMin = "";
        this.infoObj.premiumMax = "";
      }
      if (val === "全部") {
        this.infoObj.premiumMsg = "";
      }
      if (val === "0-2000元") {
        this.infoObj.premiumMsg = "1";
      }
      if (val === "2000-5000元") {
        this.infoObj.premiumMsg = "2";
      }
      if (val === "5000元以上") {
        this.infoObj.premiumMsg = "3";
      }
    },
    // 输入保费
    changeMsg() {
      if (this.infoObj.premiumMin || this.infoObj.premiumMax) {
        this.infoObj.premiumMsg = "";
        // this.$nextTick(() => {
        //   this.$refs.orderAll2.clearActive();
        // });
        this.infoObj.premiumMin =
          this.infoObj.premiumMin.match(/^\d*(\.?\d{0,2})/g)[0] || null;
        console.log(this.infoObj.premiumMin, "11111");
      }

      if (this.infoObj.premiumMin && this.infoObj.premiumMax) {
        if (
          parseInt(this.infoObj.premiumMin) > parseInt(this.infoObj.premiumMax)
        ) {
          this.$toast.fail("最低保费不能大于最高保费");
          return;
        }
      } else if (
        this.infoObj.premiumMin &&
        parseInt(this.infoObj.premiumMin) <= 0
      ) {
        this.$toast.fail("最低保费只能大于0");
        return;
      }
    },
    changeMsg1() {
      this.infoObj.premiumMax =
        this.infoObj.premiumMax.match(/^\d*(\.?\d{0,2})/g)[0] || null;
    },
    //  出单来源
    singleSource(val) {
      if (val === "全部") {
        this.infoObj.singleSourceMsg = "";
      }
      if (val === "超级销售APP") {
        this.infoObj.singleSourceMsg = "1";
      }
      if (val === "其他") {
        this.infoObj.singleSourceMsg = "2";
      }
    },
    // 筛选确定
    filterOrderList() {
      if (!this.checkDate()) {
        return;
      }
      if (!this.checkPremium()) {
        return;
      }
      switch (this.vehiceFlag) {
        case "2":
          this.$refs.catInsurance.getdataList();
          break;
        case "0":
          this.$refs.healthInsurance.getdataList();
          break;
        case "1":
          this.$refs.propertyInsurance.getdataList();
          break;
      }
      this.selectDate.sDate = "";
      this.selectDate.eDate = "";
      this.popupShow = false;
    },
    // 日期格式化
    formatDate(date) {
      let year = date.getFullYear();
      if (year < 9) {
        year = `0${year}`;
      }
      let month = date.getMonth() + 1;
      if (month < 9) {
        month = `0${month}`;
      }
      let day = date.getDate();
      if (day < 9) {
        day = `0${day}`;
      }
      //console.log(`${year}-${month}-${day}`);
      return `${year}-${month}-${day}`;
    },
    // 日期选择完成后触发，若show-confirm为true，则点击确认按钮后触发
    onConfirm(date) {
      // console.log(date);
      const [start, end] = date;
      this.calendarShow = false;
      this.infoObj.startDay = this.formatDate(start);
      this.infoObj.endDay = this.formatDate(end);
      if (this.infoObj.startDay || this.infoObj.endDay) {
        this.infoObj.dataType = "";
      }
    },
    // startDay点击事件
    startDayClick() {
      this.dateType = 1;
      this.showDatePicker = true;
    },
    // endDay点击事件
    endDayClick() {
      this.dateType = 2;
      this.showDatePicker = true;
    },
    // 选择开始日期后回调
    startDateConfirm(date) {
      this.selectDate.sDate = date;
      this.infoObj.startDay = this.formatDate(date);
      this.showDatePicker = false;
      if (this.selectDate.sDate && this.selectDate.eDate) {
        let st = this.selectDate.sDate.getTime();
        let et = this.selectDate.eDate.getTime();
        if (st > et) {
          this.$toast.fail("开始时间不能大于结束时间");
        }
      }
      console.log(574, this.$refs.orderAll);
      this.$nextTick(() => {
        this.$refs.orderAll1.clearActive();
      });
    },
    // 选择结束日期后的回调
    endDateConfirm(date) {
      this.selectDate.eDate = date;
      this.infoObj.endDay = this.formatDate(date);
      this.showDatePicker = false;
      if (this.selectDate.sDate && this.selectDate.eDate) {
        let st = this.selectDate.sDate.getTime();
        let et = this.selectDate.eDate.getTime();
        if (st > et) {
          this.$toast.fail("结束时间不能小于开始时间");
        }
      }
      this.$nextTick(() => {
        this.$refs.orderAll1.clearActive();
      });
    },
    // 判断开始时间和结束时间的大小
    checkDate() {
      let flag = true;
      if (this.selectDate.sDate && this.selectDate.eDate) {
        let st = this.selectDate.sDate.getTime();
        let et = this.selectDate.eDate.getTime();
        if (st > et) {
          this.$toast.fail("结束时间不能小于开始时间");
          flag = false;
        }
      } else if (!this.selectDate.sDate && this.selectDate.eDate) {
        this.$toast.fail("开始时间不能为空");
        flag = false;
      } else if (!this.selectDate.eDate && this.selectDate.sDate) {
        this.$toast.fail("结束时间不能为空");
        flag = false;
      }
      return flag;
    },
    // 判断保费区间的大小
    checkPremium() {
      let flag = true;
      if (this.infoObj.premiumMin && this.infoObj.premiumMax) {
        if (parseInt(this.infoObj.premiumMin) <= 0) {
          this.$toast.fail("最低保费只能大于0");
          return;
        } else if (
          parseInt(this.infoObj.premiumMin) > parseInt(this.infoObj.premiumMax)
        ) {
          this.$toast.fail("最低保费不能大于最高保费");
          flag = false;
        }
      } else if (!this.infoObj.premiumMin && this.infoObj.premiumMax) {
        this.$toast.fail("最低保费不能为空");
        flag = false;
      } else if (!this.infoObj.premiumMax && this.infoObj.premiumMin) {
        this.$toast.fail("最高保费不能为空");
        flag = false;
      }
      return flag;
    }
  }
};
</script>

<style lang="less">
#orderList {
  width: 100%;
  background: rgba(247, 247, 247, 1);
  padding: 115px 5px 10px 5px;
  .cardList {
    padding: 10px 0;
    height: calc(100vh - 200px);
    overflow: scroll;
  }
  * {
    border: none;
    box-sizing: border-box;
  }
  .Listheader {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 115px;
    .van-tabs__line {
      bottom: 5px;
      background: #fff !important;
      width: 28px !important;
    }
    .van-tabs {
      padding: 0 8px;
    }
    // background: linear-gradient(
    //   92deg,
    //   rgba(33, 83, 235, 1) 0%,
    //   rgba(55, 147, 255, 1) 100%
    // );
    background: url("../../../assets/order/orderList/order.png") no-repeat;
    background-size: 100% 100%;
    .van-nav-bar {
      height: 52px;
      line-height: 52px;
    }
    .van-nav-bar,
    .van-nav-bar__title {
      font-family: PingFangSC-Medium, PingFang SC;
      font-size: 18px;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      background: none !important;
    }
    .van-tabs__line {
      background: none;
    }
    .van-nav-bar .van-icon {
      font-size: 18px;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
    .van-tabs__nav {
      background: none !important;
    }
    .van-tabs--line .van-tabs__wrap {
      height: 46px;
    }
    [class*="van-hairline"]::after {
      border: none;
    }
    .van-tab {
      font-size: 14px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      opacity: 0.7;
    }
    .van-tab--active {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      opacity: 1;
    }
    .radiusDiv {
      height: 18px;
      background: rgba(255, 255, 255, 1);
      border-radius: 10px 10px 0px 0px;
    }
  }

  .searchOrder {
    height: 46px;
    background: rgba(255, 255, 255, 1);
    display: flex;
    // justify-content: center;
    align-items: center;
    position: relative;
    .screenRight {
      width: 13px;
      height: 13px;
      margin-left: 5px;
    }
    .orderListSearch {
      display: block;
      width: 12px;
      height: 12px;
      position: absolute;
      top: 17px;
      left: 30px;
      z-index: 2;
    }
    input {
      // margin-left: 14px;
      // width: 265px;
      // height: 30px;
      // background: rgba(247, 247, 247, 1);
      // border-radius: 15px;
      // outline: none;
      // border: none;
      // font-size: 14px;
      // font-family: PingFangSC-Regular, PingFang SC;
      // font-weight: 400;
      // color: rgba(136, 136, 136, 1);
      // padding: 5px 18px 5px 37px;
      // position: relative;
      // box-sizing: border-box;
    }
    // div {
    //   width: 28px;
    //   height: 20px;
    //   font-size: 14px;
    //   font-family: PingFangSC-Regular, PingFang SC;
    //   font-weight: 400;
    //   color: rgba(102, 102, 102, 1);
    //   line-height: 20px;
    //   margin-left: 27px;
    // }
  }

  .orderStatusheader {
    position: fixed;
    top: 105px;
    left: 0;
    right: 0;
    height: 86px;
    .van-tab {
      font-size: 15px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(102, 102, 102, 1);
    }

    .van-tab--active {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 540;
      color: #333333;
    }
    .van-tabs__nav {
      height: 46px;
      background: rgba(255, 255, 255, 1) !important;
    }
    .van-tabs__line {
      width: 28px !important;
      height: 5px;
      background: rgba(59, 138, 254, 1);
      border-radius: 3px;
      bottom: 2px;
    }
    .van-tabs__nav--line {
      padding: 0;
    }
  }
}
.van-popup--bottom {
  border-radius: 15px 15px 0px 0px;
}
.popup {
  padding-bottom: 56px;
}
.dressing {
  padding: 20px 15px 15px 15px;
  .orderData {
    font-size: 14px;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
    margin-bottom: 10px;
  }
  .chooser-list li {
    background: #f9fafc !important;
  }
  .chooser-list li.active {
    background: #3b8afe !important;
  }
  .positionRelative {
    .van-cell {
      width: 106px;
      background: #f9fafc;
      padding: 8px 25px;
      font-size: 14px;
    }
    .startDay .van-cell,
    .endDay.van-cell {
      padding: 8px 5px;
    }
    height: 56px;
    position: relative;
    .startDay {
      float: left;
      margin-right: 10px;
    }
    .line {
      position: absolute;
      left: 110px;
      top: 12px;
    }
  }
  .datePicker {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 70%;
  }
  .calendar {
    button {
      background: linear-gradient(
        125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%
      ) !important;
      border-radius: 23px;
    }
  }
}
.arrow-down {
  position: absolute;
  top: 15px;
  left: 21%;
}
.option {
  width: 85px;
  border: 1px solid #ccc !important;
  background: #ffffff;
  position: absolute;
  top: 43px;
  left: 5%;
  z-index: 2;
  font-size: 14px;
  display: none;
  padding: 5px 8px;
  div {
    line-height: 34px;
  }
}
.optionBlock {
  display: block;
}
// #orderList .searchOrder input {
//   color: #333333;
// }

.form {
  position: relative;
  height: 30px;
}

.search {
  margin: 0 14px;
  width: 265px;
  height: 30px;
  background: rgba(247, 247, 247, 1);
  border-radius: 15px;
  outline: none;
  border: none;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(136, 136, 136, 1);
  position: relative;
  box-sizing: border-box;
}

// .clear {
//   display: none;
//   position: absolute;
//   width: 12px;
//   height: 12px;
//   right: 10px;
//   top: 9px;
// }

// input:valid + .clear {
//   display: inline;
// }
</style>
