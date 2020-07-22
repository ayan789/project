<template>
  <div id="insuranceProducts">
    <globalNav title="保单查询" v-on:clickBack="back()"/>
    <div class="title-container">
      <van-form>
        <div class="group">
          <div class="divSelect divLine" @click="showPicker()">
            <div class="lable">险种类型</div>
            <div class="divContent">
              <span>{{ insuranceTypeName }}</span>
            </div>
            <div style="clear:both"></div>
          </div>
          <van-field
            style="margin-top:10px"
            v-model="queryData.custName"
            name="关系人"
            label="关系人"
            placeholder="请输入投保人/被保人/车主"
            maxlength="15"
          />
          <van-field
            v-if="queryData.insuranceType == '1'"
            v-model="licenseNo"
            name="车牌号"
            label="车牌号"
            placeholder="车牌号"
            maxlength="8"
          />
          <van-field
            v-else
            v-model="queryData.insuredName"
            name="被保险人"
            label="被保险人"
            placeholder="请输入被保险人"
            maxlength="15"
          />
          <van-field
            v-model="policyNo"
            name="保单"
            label="保单"
            placeholder="请输入保单"
          />
        </div>
        <div class="group" style="margin-top:10px">
          <div class="dressing">
            <div class="positionRelative">
              <div class="orderData">起保日期</div>
              <div class="startDay">
                <van-field
                  v-model="queryData.minStartDate"
                  placeholder="开始日期"
                  @click="calendarShow1 = true"
                  class="startDay"
                  input-align="center"
                  disabled
                />
              </div>
              <div class="line">至</div>
              <van-field
                class="endDay"
                v-model="queryData.maxStartDate"
                placeholder="结束日期"
                @click="calendarShow2 = true"
                input-align="center"
                disabled
              />
            </div>
            <div style="clear:both"></div>
            <van-popup v-model="calendarShow1" round position="bottom">
              <van-datetime-picker
                v-model="currentDate"
                type="date"
                :min-date="minDate"
                :max-date="maxDate"
                @cancel="cancelClick('1')"
                @confirm="onConfirm1"
              />
            </van-popup>
            <van-popup v-model="calendarShow2" round position="bottom">
              <van-datetime-picker
                v-model="currentDate"
                type="date"
                :min-date="minDate"
                :max-date="maxDate"
                @cancel="cancelClick('2')"
                @confirm="onConfirm2"
              />
            </van-popup>
          </div>
          <div class="dressing">
            <div class="positionRelative">
              <div class="orderData">终保日期</div>
              <div class="startDay">
                <van-field
                  v-model="queryData.minEndDate"
                  placeholder="开始日期"
                  @click="calendarShow3 = true"
                  class="startDay"
                  input-align="center"
                  disabled
                />
              </div>
              <div class="line">至</div>
              <van-field
                class="endDay"
                v-model="queryData.maxEndDate"
                placeholder="结束日期"
                @click="calendarShow4 = true"
                input-align="center"
                disabled
              />
            </div>
            <div style="clear:both"></div>
            <van-popup v-model="calendarShow3" round position="bottom">
              <van-datetime-picker
                v-model="currentDate"
                type="date"
                :min-date="minDate"
                :max-date="maxDate"
                @cancel="cancelClick('3')"
                @confirm="onConfirm3"
              />
            </van-popup>
            <van-popup v-model="calendarShow4" round position="bottom">
              <van-datetime-picker
                v-model="currentDate"
                type="date"
                :min-date="minDate"
                :max-date="maxDate"
                @cancel="cancelClick('4')"
                @confirm="onConfirm4"
              />
            </van-popup>
          </div>
        </div>
        <van-button
          text="查询保单"
          @click="onSubmit"
          class="signature-submit-button"
        />
      </van-form>
    </div>
    <van-popup v-model="dataPickerShow" round position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
  </div>
</template>

<script>
import globalNav from "@/components/globalNav.vue";

import { Form, Toast } from "vant";
//校验
import { isLicensePlateNumber, licenseNo } from "@src/utils/index.js";
export default {
  components: {
    [Form.name]: Form,
    globalNav
  },
  data() {
    return {
      minDate: new Date(2010, 10, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      queryData: {
        userCode: localStorage.getItem("userCode"),
        licenseNo: "",
        custName: "",
        insuranceType: "1",
        insuredName: "",
        policyNo: "",
        minStartDate: "",
        maxStartDate: "",
        minEndDate: "",
        maxEndDate: "",
        pageSize: "10",
        pageNum: "1"
      },
      calendarShow1: false,
      calendarShow2: false,
      calendarShow3: false,
      calendarShow4: false,
      dataPickerShow: false,
      insuranceTypeName: "车险",
      pickerData: [
        { text: "车险", value: "1" },
        { text: "意健险", value: "2" },
        { text: "财产险", value: "3" }
      ]
    };
  },
  created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
  },
  computed: {
    licenseNo: {
      get: function () {
        return this.queryData.licenseNo;
      },
      set: function (licenseNo) {
        this.queryData.licenseNo = licenseNo.toUpperCase();
      }
    },
    policyNo: {
      get: function () {
        return this.queryData.policyNo;
      },
      set: function (policyNo) {
        this.queryData.policyNo = policyNo.toUpperCase();
      }
    }
  },
  methods: {
    dataPickerConfirm(data) {
      this.queryData.insuranceType = data.value;
      this.insuranceTypeName = data.text;
      this.dataPickerShow = false;
    },
    dataPickerCancel() {
      this.dataPickerShow = false;
    },
    showPicker() {
      this.dataPickerShow = !this.dataPickerShow;
    },
    timeSet(value) {
      var date = value;
      var m = date.getMonth() + 1;
      var d = date.getDate();
      if (m >= 1 && m <= 9) {
        m = "0" + m;
      }
      if (d >= 0 && d <= 9) {
        d = "0" + d;
      }
      return date.getFullYear() + "-" + m + "-" + d;
    },
    onConfirm1(date) {
      this.timeSet(date);
      this.queryData.minStartDate = this.timeSet(date);
      this.calendarShow1 = false;
    },
    onConfirm2(date) {
      this.timeSet(date);
      this.queryData.maxStartDate = this.timeSet(date);
      this.calendarShow2 = false;
    },
    onConfirm3(date) {
      this.timeSet(date);
      this.queryData.minEndDate = this.timeSet(date);
      this.calendarShow3 = false;
    },
    onConfirm4(date) {
      this.timeSet(date);
      this.queryData.maxEndDate = this.timeSet(date);
      this.calendarShow4 = false;
    },
    cancelClick(type) {
      switch (type) {
        case "1":
          this.calendarShow1 = false;
          break;
        case "2":
          this.calendarShow2 = false;
          break;
        case "3":
          this.calendarShow3 = false;
          break;
        case "4":
          this.calendarShow4 = false;
          break;
      }
    },
    selectPickerComfirm: function (value) {
      var date = new Date(value);
      var date_value =
        date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
      if (this.isBegin) {
        this.beginTime = date_value;
      } else {
        this.endTime = date_value;
      }
      this.showPicker = !this.showPicker;
    },
    cancelSelectPicker: function () {
      this.showPicker = !this.showPicker;
    },
    async onSubmit() {
      if (
        !this.$isNull(this.queryData.licenseNo) &&
        !licenseNo(this.queryData.licenseNo)
      ) {
        Toast("车牌号格式错误，请重新输入");
        return;
      }

      if (
        !this.$isNull(this.queryData.custName) ||
        !this.$isNull(this.queryData.policyNo) ||
        !this.$isNull(this.queryData.licenseNo) ||
        !this.$isNull(this.queryData.insuredName) ||
        !this.$isNull(this.queryData.minStartDate) ||
        !this.$isNull(this.queryData.maxStartDate) ||
        !this.$isNull(this.queryData.minEndDate) ||
        !this.$isNull(this.queryData.maxEndDate)
      ) {
        this.$router.push({
          name: "detailList",
          query: {
            listItem: JSON.stringify(this.queryData)
          }
        });
      } else {
        Toast("请输入查询条件");
      }
    },
    back() {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
#insuranceProducts {
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Medium, PingFang SC;

  .title {
    background: linear-gradient(129deg,
    rgb(30, 70, 246) 0%,
    rgb(39, 154, 255) 100%);

    .van-nav-bar {
      background: none;
    }

    .van-nav-bar__title {
      color: #fff !important;
    }
  }

  .title-container {
    padding-top: 80px;
  }

  .group {
    flex-direction: column;
    padding-bottom: 1px;

    .divSelect {
      padding: 0 16px;
      line-height: 44px;
      font-size: 14px;
      background: #fff;

      .lable {
        width: 100px;
        float: left;
        color: #333;
      }

      .divContent {
        float: left;
        width: calc(100% - 100px);

        select {
          width: 100%;
          border: none;
          outline: none;
          background: #fff;
        }
      }
    }

    .divLine {
      margin: 1px 0;
    }

    .dressing {
      background: #fff;
    }

    .positionRelative {
      .van-cell {
        width: 106px;
        background: #fff;
        padding: 0 25px;
        font-size: 14px;
      }

      .orderData {
        float: left;
        padding-left: 15px;
        width: 70px;
        color: #333;
        height: 40px;
        line-height: 40px;
        font-size: 14px;
      }

      .startDay .van-cell,
      .endDay.van-cell {
        padding: 8px 5px;
        color: #333;
      }

      .startDay {
        float: left;
        margin-right: 10px;
        color: #323233;
      }

      .line {
        float: left;
        height: 40px;
        line-height: 40px;
        color: #333;
        font-size: 14px;
      }
    }

    .calendar {
      button {
        background: linear-gradient(125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%) !important;
        border-radius: 23px;
      }
    }
  }

  .signature-submit-button {
    width: 345px;
    height: 46px;
    background: #3b8afe;
    border-radius: 46px;
    // margin: 43px 15px;
    display: block;
    margin: 10% auto;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: white;
    line-height: 23px;
  }
}
</style>
