<template>
  <div class="vehicleInformatization">
    <globalNav
      title="投被保人信息"
      ref="globalNav"
      v-on:clickBack="clickBack"
    />
    <div class="insurePerson">
      <span></span>
      <h3>投保人</h3>
    </div>
    <div class="vehicleTop"></div>
    <div class="informatization">
      <van-form @submit="onSubmit">
        <div class="inforagent particulars">
          <van-cell title="是否同车主" value="" />
          <whichSelected
            isabsolute
            :whetherOptions="whetherList"
          ></whichSelected>
        </div>
        <van-field
          v-model="inforagent"
          required
          label="姓名"
          name="姓名"
          placeholder="请输入"
          input-align="right"
          :rules="[{ required: true, message: '请填写姓名' }]"
        />
        <div class="certificate">
          <van-field
            v-model="certificate"
            required
            label="证件类型"
            name="证件类型"
            input-align="right"
            placeholder="身份证"
            :rules="[{ required: true, message: '请填写证件类型' }]"
          />
          <div class="licenseNumberRight">
            <div class="radioButton">
              <van-icon name="arrow" />
            </div>
            <span>证件识别</span>
          </div>
        </div>
        <van-field
          v-model="identity"
          required
          label="证件号码"
          name="证件号码"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写证件号码' }]"
        />
        <van-cell
          title="证件有效起期"
          is-link
          :value="beginTime"
          required
          @click="selectTimer(true)"
        />
        <div class="certificateEnd">
          <van-cell
            title="证件有效止期"
            is-link
            :value="endTime"
            required
            @click="selectTimer(false)"
          />
          <van-checkbox v-model="alwaysUse" shape="square"
            >长期有效</van-checkbox
          >
        </div>
        <van-field
          v-model="mobileTelephone"
          required
          label="移动电话"
          name="移动电话"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写移动电话' }]"
        />
        <van-field
          v-model="email"
          required
          label="邮箱地址"
          name="邮箱地址"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写邮箱地址' }]"
        />
        <van-field
          v-model="address"
          required
          label="联系地址"
          name="联系地址"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写联系地址' }]"
        />
        <div class="inforagent particulars">
          <van-cell title="投保人与车辆关系" value="" required />
          <whichSelected
            isabsolute
            :whetherOptions="vehicleConnects"
            width="60px"
            space="5px"
          ></whichSelected>
        </div>
      </van-form>
    </div>
    <div class="insurePerson beInsurePerson">
      <span></span>
      <h3>被保人</h3>
    </div>
    <div class="vehicleTop"></div>
    <div class="informatization">
      <van-form @submit="onSubmit">
        <div class="inforagent particulars">
          <van-cell title="是否同投保人" value="" />
          <whichSelected
            isabsolute
            :whetherOptions="whetherList"
          ></whichSelected>
        </div>
        <van-field
          v-model="inforagent"
          required
          label="姓名"
          name="姓名"
          placeholder="请输入"
          input-align="right"
          :rules="[{ required: true, message: '请填写姓名' }]"
        />
        <div class="certificate">
          <van-field
            v-model="certificate"
            required
            label="证件类型"
            name="证件类型"
            input-align="right"
            placeholder="身份证"
            :rules="[{ required: true, message: '请填写证件类型' }]"
          />
          <div class="licenseNumberRight">
            <div class="radioButton">
              <van-icon name="arrow" />
            </div>
            <span>证件识别</span>
          </div>
        </div>
        <van-field
          v-model="identity"
          required
          label="证件号码"
          name="证件号码"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写证件号码' }]"
        />
        <van-cell
          title="证件有效起期"
          is-link
          :value="beginTime"
          required
          @click="selectTimer(true)"
        />
        <div class="certificateEnd">
          <van-cell
            title="证件有效止期"
            is-link
            :value="endTime"
            required
            @click="selectTimer(false)"
          />
          <van-checkbox v-model="alwaysUse" shape="square"
            >长期有效</van-checkbox
          >
        </div>
        <van-field
          v-model="mobileTelephone"
          required
          label="移动电话"
          name="移动电话"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写移动电话' }]"
        />
        <van-field
          v-model="email"
          required
          label="邮箱地址"
          name="邮箱地址"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写邮箱地址' }]"
        />
        <van-field
          v-model="address"
          required
          label="联系地址"
          name="联系地址"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写联系地址' }]"
        />
        <div class="inforagent particulars">
          <van-cell title="投保险人与车辆关系" value="" required />
          <whichSelected
            isabsolute
            :whetherOptions="vehicleConnects"
            width="60px"
            space="5px"
          ></whichSelected>
        </div>
        <van-field
          v-model="isWatchCash"
          label="反洗钱风险等级"
          name="反洗钱风险等级"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写反洗钱风险等级' }]"
        />
      </van-form>
    </div>
    <div class="insurePerson beInsurePerson">
      <span></span>
      <h3>开票信息</h3>
    </div>
    <div class="vehicleTop"></div>
    <div class="informatization">
      <van-form @submit="onSubmit">
        <div class="inforagent particulars">
          <van-cell title="是否需要电子发票" value="" />
          <whichSelected
            isabsolute
            :whetherOptions="whetherList"
          ></whichSelected>
        </div>
        <div class="inforagent particulars">
          <van-cell title="发票抬头" value="" />
          <whichSelected
            isabsolute
            :whetherOptions="ticketList"
          ></whichSelected>
        </div>
        <van-field
          v-model="mobileTelephone"
          required
          label="移动电话"
          name="移动电话"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写移动电话' }]"
        />
        <van-field
          v-model="email"
          required
          label="邮箱地址"
          name="邮箱地址"
          input-align="right"
          placeholder="请输入"
          :rules="[{ required: true, message: '请填写邮箱地址' }]"
        />
      </van-form>
    </div>
    <div class="next">
      <h3 @click="nextProcess">下一步</h3>
    </div>
    <van-popup v-model="showPicker" position="bottom" :overlay="true">
      <van-datetime-picker
        v-show="showPicker"
        v-model="currentDate"
        type="date"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="selectPickerComfirm"
        @cancel="cancelSelectPicker"
      />
    </van-popup>
  </div>
</template>
<script>
// import "@/style/vehicle/insureConfirm/insureInfomation.less";
import whichSelected from "@/components/whichSelected.vue";
import globalNav from "@/components/globalNav.vue";

export default {
  components: {
    whichSelected,
    globalNav
  },
  data() {
    return {
      isWatchCash: "否",
      alwaysUse: false,
      showPicker: false,
      minDate: new Date(1990, 1, 1),
      maxDate: new Date(2050, 1, 1),
      currentDate: new Date(),
      beginTime: "请选择",
      endTime: "请选择",
      email: "",
      address: "",
      username: "",
      password: "",
      occupationvalue: 0,
      nationalityvalue: 0,
      occupationList: [
        { text: "请选择", value: 0 },
        { text: "新款商品", value: 1 },
        { text: "活动商品", value: 2 }
      ],
      ticketList: ["同投保人", "同被保人"],
      whetherList: ["是", "否"],
      vehicleConnects: ["所有", "使用", "管理"],
      nonlocalcarList: ["是", "否"],
      transferList: ["是", "否"],
      genderList: ["男", "女"],
      checked: false,
      inforagent: "",
      licenseNumber: "",
      vinNumber: "",
      registerDate: "2010-02-18",
      engineNumber: "",
      passengerNumber: "",
      brandNumber: "",
      vehicleName: "",
      identity: "",
      certificate: "",
      birth: "",
      occupation: "",
      nationality: "",
      mobileTelephone: "",
      contactAddress: ""
    };
  },
  methods: {
    selectTimer(isStart) {
      //console.log('选择')
      this.isBegin = isStart;
      this.showPicker = !this.showPicker;
    },
    selectPickerComfirm(value) {
      var date = new Date(value);
      var date_value =
        date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
      //console.log(date_value)
      if (this.isBegin) {
        this.beginTime = date_value;
      } else {
        this.endTime = date_value;
      }
      this.showPicker = !this.showPicker;
    },
    cancelSelectPicker() {
      this.showPicker = !this.showPicker;
    },
    onSubmit(values) {
      //console.log('submit', values)
      //console.log(values)
    },
    occupationChange() {
      this.occupation = this.occupationvalue;
    },
    nationalityChange() {
      this.nationality = this.nationalityvalue;
    },
    clickBack(message) {
      //console.log(message)
    },
    nextProcess() {
      //console.log('下一步')
    }
  }
};
</script>
<style lang="less" scoped>
.bg81 {
  padding-top: 20px;
  background: #f7f7f7;
  height: 61px;
}
.vehicleInformatization {
  .insurePerson {
    height: 48px;
    margin-top: 44px;
    width: 100%;
    background: #f7f7f7;
  }
  .whetherVehicle {
    float: right;
  }
  .beInsurePerson {
    margin-top: 0px;
  }

  .insurePerson > span {
    height: 9px;
    display: block;
  }

  .checkBox {
    height: 57px;
    width: 100%;
  }

  .checkBox > span {
    height: 9px;
    background: #f7f7f7;
    width: 100%;
    display: block;
  }

  .checkBox > :last-child {
    height: 48px;
    padding-left: 15px;
    line-height: 48px;
    width: calc(100% - 30px);
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }

  .insurePerson > h3 {
    display: block;
    margin-left: 15px;
    height: 39px;
    width: calc(100% - 15px);
    line-height: 39px;
    font-size: 15px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
  }

  .vehicleTop {
    height: 0px;
    background: #f7f7f7;
  }

  .informatization {
    .particulars,
    .nonlocalCar,
    .transfer {
      height: 48px;
      // border-bottom: 1px solid #e5e6e8;
      position: relative;

      .whether {
        float: left;
        font-size: 14px;
        color: rgba(51, 51, 51, 1);
        padding-top: 14px;
      }

      .whetherRight {
        padding-top: 11px;
        float: right;

        .agency,
        .noAgency {
          padding: 5px 24px;
        }

        .agency {
          float: left;
          margin-right: 10px;
          border-radius: 14px;
          border: 1px solid rgba(59, 138, 254, 1);
          color: #3b8afe;
        }

        .noAgency {
          float: left;
          border-radius: 14px;
          border: 1px solid rgba(229, 229, 229, 1);
          color: #e5e5e5;
        }
      }
    }

    .inforagent {
      position: relative;

      .inforagentIcon {
        position: absolute;
        top: 17px;
        right: 17px;
        color: #bfbfbf;
        font-size: 14px;
      }
    }

    .vehicleInfo,
    .vehicleOwner {
      height: 48px;
      background: #f7f7f7;
      overflow: hidden;
      padding: 0 15px 0 18px;

      .vehicleLeft,
      .recognition,
      .vehicleCamera {
        padding-top: 18px;
      }

      .vehicleLeft {
        float: left;
        font-size: 15px;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
      }

      .recognition {
        margin-left: 182px;
        float: left;
        font-size: 14px;
        color: rgba(59, 138, 254, 1);
      }

      .vehicleCamera {
        float: right;
        width: 16px;
        height: 20px;
      }
    }

    .certificateEnd {
      height: 48px;
      width: 100%;

      .van-icon-arrow::before {
        margin-top: 2px;
      }
    }

    .certificateEnd > :first-child {
      float: left;
      width: calc(100% - 101px);
      line-height: 29px;
    }

    .certificateEnd > :last-child {
      float: right;
      width: 101px;
      height: 47px;
      border-bottom: 1px solid #ebedf0;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }

    .licenseNumber {
      position: relative;

      .licenseNumberRight {
        position: absolute;
        right: 18px;
        top: 15px;
        overflow: hidden;

        .vehicleLine {
          width: 1px;
          height: 20px;
          background: #ededed;
          float: left;
          margin-right: 20px;
        }

        .radioButton {
          float: left;
          margin-right: 23px;
        }

        .radioSearch {
          padding-top: 3px;
          float: left;

          .img {
            background-size: 16px;
          }
        }
      }
    }

    .lineBotton {
      height: 1px;
      background: #e5e6e8;
      margin-left: 15px;
    }

    .vinNumber {
      position: relative;

      .vinNumberSearch {
        position: absolute;
        right: 18px;
        top: 16px;
      }
    }

    .nonlocalCar,
    .transfer {
      #whetherCar {
        padding: 7px 30px;
        border: 1px solid rgba(229, 229, 229, 1);
        color: #e5e5e5;
      }
    }

    .vehicleMore {
      text-align: center;
      line-height: 48px;

      span {
        border-bottom: 1px solid #ababab;
        color: rgba(153, 153, 153, 1);
      }
    }

    .certificate {
      position: relative;

      .licenseNumberRight {
        position: absolute;
        right: 48px;
        top: 13px;
        overflow: hidden;

        .radioButton {
          float: left;
          margin-right: 18px;
          padding-top: 4px;
          color: #bfbfbf;
        }

        span {
          font-size: 14px;
          color: rgba(59, 138, 254, 1);
        }
      }
    }

    .vehicleConfirm {
      height: 81px;
      padding-left: 15px;
      padding-right: 16px;
      padding-top: 20px;
      background: rgba(247, 247, 247, 1);

      .confirm {
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        height: 46px;
        line-height: 46px;
        text-align: center;
        font-size: 16px;
        color: rgba(255, 255, 255, 1);
      }
    }
  }

  .next {
    height: 61px;
    width: 100%;
    background: #f7f7f7;
    padding-top: 20px;
  }

  .next > h3 {
    margin: 0px 15px 15px 15px;
    width: calc(100% - 30px);
    height: 46px;
    border-radius: 46px;
    background: #3b8afe;
    line-height: 46px;
    text-align: center;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
  }
}
</style>
