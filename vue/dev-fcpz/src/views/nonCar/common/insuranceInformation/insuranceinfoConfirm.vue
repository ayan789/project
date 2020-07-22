<template>
  <!-- 投保确认 -->
  <div class="insuranceinfoConfirm">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
    />

    <div class="card">
      <van-cell-group>
        <div class="title-container">
          <div class="topLeft">
            <img
              src="@/assets/nonCar/propertyInsurance/infoInput.png"
              alt="header"
            />
          </div>
          <div class="topRight">
            <div class="title">{{ millionData.formulaName }}</div>
            <div class="time">
              保障期限: {{ millionData.startDate }}至{{ millionData.endDate }}
            </div>
          </div>
          <div
            class="icon"
            @click="goback"
          >
            <img
              src="@/assets/common/edit.png"
              alt="edit"
            />
          </div>
        </div>
        <van-cell value>
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">百万医疗</span>
          </template>
        </van-cell>
        <van-cell
          title="投保人信息"
          icon="location-o"
        />
        <van-cell
          title="投保人姓名"
          :value="millionData.appliInfo.appliName"
        />
        <van-cell
          title="证件类型"
          :value="millionData.appliInfo.appliIdentifyTypeName"
        />

        <van-cell
          title="证件号码"
          :value="millionData.appliInfo.appliIdentifyNumber"
        />
        <van-cell
          title="出生日期"
          :value="millionData.appliInfo.appliBirthDate"
        />
        <van-cell
          title="手机号"
          :value="millionData.appliInfo.appliMobile"
        />
        <van-cell
          title="邮箱"
          :value="millionData.appliInfo.appliEMail"
        />
      </van-cell-group>
    </div>
    <template>
      <div
        class="card"
        v-for="(item, index) in millionData.accidentalInjuryInfos"
        :key="index"
      >
        <van-cell-group>
          <van-cell
            title="被保人信息"
            icon="location-o"
          />
          <van-cell
            title="与投保人关系"
            :value="relationChangeName(item.familyRelation)"
          />

          <van-cell
            title="被保人姓名"
            :value="item.familyName"
          />
          <van-cell
            title="证件类型"
            :value="identifyTypeS(item.familyIdentifyType)"
          />

          <van-cell
            title="证件号码"
            :value="item.familyIdentifyNo"
          />
          <van-cell
            title="出生日期"
            :value="item.insuredBirthDate"
          />
          <van-cell
            title="手机号"
            :value="item.mobile"
          />
          <van-cell
            title="社保类型"
            :value="shebaoChangeName(item.insuredSheBaoType)"
          />
        </van-cell-group>
      </div>
    </template>

    <div class="card">
      <van-cell-group>
        <van-cell
          title="保障期限"
          icon="location-o"
        />
        <van-cell
          title="日期区间"
          :value="`${millionData.startDate}` + '-' + `${millionData.endDate}`"
        />
      </van-cell-group>
    </div>
    <div
      class="card"
      v-if="millionData.invoiceInfo.mobile"
    >
      <van-cell-group>
        <van-cell
          title="开票信息"
          icon="comment-o"
          class="select-customer-icon"
        />
        <van-cell
          title="手机号"
          :value="millionData.invoiceInfo.mobile"
        />
        <van-cell
          title="电子邮箱"
          :value="millionData.invoiceInfo.email"
        />
      </van-cell-group>
    </div>
    <!-- <div class="card" v-if="millionData.invoiceInfo.mobile">
      <van-cell-group>
        <van-cell title="开票信息" icon="location-o" />
        <van-cell title="发票抬头" value="内容" />
        <van-cell title="手机号" :value="millionData.invoiceInfo.mobile" />
        <van-cell title="电子邮箱" :value="millionData.invoiceInfo.email" />
        <van-cell title="客户类型" :value="millionData.invoiceInfo.customerType" />
        <van-cell title="纳税人类型" :value="millionData.invoiceInfo.accotaxPayerTypeuntNo" />
        <van-cell title="发票类型" :value="millionData.invoiceInfo.invoiceType" />
        <van-cell title="公司名称" :value="millionData.invoiceInfo.companyName" />
        <van-cell title="纳税人识别码 " :value="millionData.invoiceInfo.taxPayerNo" />
        <van-cell title="注册地址 " :value="millionData.invoiceInfo.taxRegisterAddress" />
        <van-cell title="注册电话 " :value="millionData.invoiceInfo.taxRegisterTel" />
        <van-cell title="开户银行" :value="millionData.invoiceInfo.depositeBankName" />
        <van-cell title="银行账号" :value="millionData.invoiceInfo.accountNo" />
      </van-cell-group>
    </div>-->

    <div class="tabbar">
      <div class="info">
        保费
        <span>{{ millionData.sumPremium }}</span>元
      </div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >确认投保</van-button>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from 'vue'
import globalNav from "../../../../components/globalNav";
import { mapGetters } from "vuex";
import { placeOrder } from "../../../../http/module/nonCar.js";
import { Toast } from "vant";
export default {
  data () {
    return {
      globalNav: {
        title: "保单确认"
      },
      cc: localStorage.getItem("cc"),
      custNo: "", //操作客户代码
      comCode: "", //所属分公司机构代码
      proposalNo: "", //投保单号
      millionData: {}, //前一个页面对象数据
      formulaName: "" //方案名称
    };
  },
  created () {
    //获取当前页面缓存信息
    const millionHomeStep2 = localStorage.getItem("millionHomeStep2");
    //如果当前页面有值
    if (!this.$isNull(millionHomeStep2)) {
      const millionHomeStep2Obj = JSON.parse(millionHomeStep2);
      this.millionData = millionHomeStep2Obj;
    }
  },
  methods: {
    change () {
      this.$router.go(-1);
    },
    goback () {
      //统一返回，如果直接从壳跳进来，调用壳返回
      this.$globalBack();
    },
    enter () {
      let params = {};
      let millionHomeStep2 = JSON.parse(
        localStorage.getItem("millionHomeStep2")
      );
      let params2 = {};
      for (let i = 0; i < millionHomeStep2.accidentalInjuryInfos.length; i++) {
        let key = "";
        switch (i) {
          case 0: {
            key = "";
            break;
          }
          case 1: {
            key = "Two";
            break;
          }
          case 2: {
            key = "Three";
            break;
          }
          case 3: {
            key = "Four";
            break;
          }
          case 4: {
            key = "Five";
            break;
          }
          case 5: {
            key = "Six";
            break;
          }
          case 6: {
            key = "Seven";
            break;
          }
        }
        let appliIdentityObj = "appliIdentity" + key;
        let insuredNameObj = "insuredName" + key;
        let insuredIdentifyTypeObj = "insuredIdentifyType" + key;
        let insuredIdentifyNumberObj = "insuredIdentifyNumber" + key;
        let insuredMobileObj = "insuredMobile" + key;
        let insuredBirthDateObj = "insuredBirthDate" + key;
        let ageObj = "";
        if (i == 0) {
          ageObj = "ageOne";
        } else {
          ageObj = "age" + key;
        }

        let insuredSheBaoTypeObj = "insuredSheBaoType" + key;
        params2[appliIdentityObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyRelation;
        params2[insuredNameObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyName;
        params2[insuredIdentifyTypeObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyIdentifyType;
        // if (params2[insuredIdentifyTypeObj] == "02") {
        //   params2[insuredIdentifyTypeObj] = "03";
        // } else if (params2[insuredIdentifyTypeObj] == "03") {
        //   params2[insuredIdentifyTypeObj] = "28";
        // }
        params2[insuredIdentifyNumberObj] =
          millionHomeStep2.accidentalInjuryInfos[i].familyIdentifyNo;
        params2[insuredMobileObj] =
          millionHomeStep2.accidentalInjuryInfos[i].mobile;
        params2[insuredBirthDateObj] =
          millionHomeStep2.accidentalInjuryInfos[i].insuredBirthDate;
        params2[ageObj] = millionHomeStep2.accidentalInjuryInfos[i].age;
        params2[insuredSheBaoTypeObj] =
          millionHomeStep2.accidentalInjuryInfos[i].insuredSheBaoType;
      }
      // 转化身份数值
      // for (let i = 0; i < params1.accidentalInjuryInfos.length; i++) {
      //   let appliAge = this.$getAge(params1.accidentalInjuryInfos[i].insuredBirthDate);

      // }
      const defaultData = {
        //开始默认数据
        storeCode:
          this.$GetRequest("storeCode", true) ||
          localStorage.getItem("storeCode"),
        productCode: "FM51",
        riskCode: "WTN",
        formulaSize: "1"
      }


      params = {
        ...millionHomeStep2.appliInfo,
        accidentalInjuryInfos: millionHomeStep2.accidentalInjuryInfos, //被保人数组
        ...params2,
        ...defaultData,
        startDate: millionHomeStep2.startDate, //开始时间
        endDate: millionHomeStep2.endDate, //结束时间
        ...millionHomeStep2.ageArr,
        sumPremium: millionHomeStep2.sumPremium,
        securityNum: millionHomeStep2.securityNum,
        sumAmount: millionHomeStep2.sumAmount,
        formulaCode: millionHomeStep2.formulaCode,
        formulaName: millionHomeStep2.formulaName,
        invoiceInfo: { ...millionHomeStep2.invoiceInfo },
        ...millionHomeStep2,
        orderNo: this.isNeedOrderNo ? localStorage.getItem("tenmillionOrderNoFramily") : '',
        agentInfo: JSON.parse(localStorage.getItem("agentInfo") ? localStorage.getItem("agentInfo") : "{}"),
        isSheBao: "Y"//默认都是有社保
      };
      params.startDate = millionHomeStep2.startDate; //开始时间
      params.endDate = millionHomeStep2.endDate; //结束时间

      Toast.loading({
        message: "投保中...",
        forbidClick: true
      });
      //判断有代理人信息赋值
      if ((JSON.stringify(params.agentInfo) != "{}") && !this.$isNull(params.agentInfo) && localStorage.getItem('isAgent') != '0') {
        params.isAgent = "1"
      }
      //下单接口
      placeOrder(params).then(res => {
        Toast.clear;
        if (res.code == "1") {
          this.custNo = res.data.insureInfo.custNo; //操作客户代码
          this.comCode = res.data.insureInfo.comCode; //所属分公司机构代码
          this.proposalNo = res.data.insureInfo.orderNo; //投保单号
          this.$router.push({
            name: "signatureConfirm",
            query: {
              insureNo: res.data.insureInfo.insureNo,
              sumPremium: millionHomeStep2.sumPremium,
              mobile: millionHomeStep2.appliInfo.appliMobile,
              productName: millionHomeStep2.formulaName,
              applicantName: millionHomeStep2.appliInfo.appliName
            }
          });
        } else {
          this.$dialog.alert({
            message: res.message || res.msg
          });
          // Toast({ message: res.msg, position: 'bottom', duration: 3000 })
        }
      });
      Vue.prototype.isNeedOrderNo = false
    },
    // 身份证类型转化
    identifyTypeS (type) {
      let typeVal = "";
      switch (type) {
        case "01":
          typeVal = "身份证";
          break;
        case "03":
          typeVal = "护照";
          break;
        case "28":
          typeVal = "港澳台居住证";
          break;
      }
      return typeVal;
    },
    relationChangeName (type) {
      let typeVal = "";
      switch (type) {
        case "50":
          typeVal = "父母";
          break;
        case "10":
          typeVal = "配偶";
          break;
        case "40":
          typeVal = "子女";
          break;
        case "01":
          typeVal = "本人";
          break;
        case "99":
          typeVal = "其他";
      }
      return typeVal;
    },
    shebaoChangeName (type) {
      let typeVal = "";
      switch (type) {
        case "1":
          typeVal = "城镇职工基本医疗保险";
          break;
        case "8":
          typeVal = "城乡居民基本医疗保险";
          break;
        case "2":
          typeVal = "城镇居民基本医疗保险";
          break;
        case "3":
          typeVal = "新农合医疗保险";
          break;
        case "7":
          typeVal = "公费医疗";
          break;
        case "4":
          typeVal = "民政优抚医疗保险";
          break;
        case "6":
          typeVal = "其他";
          break;
      }

      return typeVal;
    }
  },
  components: {
    globalNav
  }
};
</script>
<style lang="less" scoped>
.insuranceinfoConfirm {
  padding-top: 1.5rem;
  height: min-content !important ;
  padding-bottom: 42px;
  .card {
    margin-top: 0.3rem;
  }
  .tabbar {
    display: flex;
    width: 100%;
    position: fixed; // todo suncao fixed
    -webkit-overflow-scroll: touch;
    bottom: 0;
    .info {
      flex: 1;
      background-color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      span {
        color: #fc820e;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
      }
    }
    .btn {
      flex: 1;
      button {
        width: 100%;
      }
    }
  }
}

.title-container {
  height: 56px;
  padding: 15px;
  // margin: 0 0 10px 0;
  background-color: white;
  display: flex;
  align-items: center;
  flex: 1;
  position: relative;
  .topLeft {
    width: 103px;
    height: 63px;
    img {
      width: 73px;
      height: auto;
    }
  }
  .topRight {
    display: flex;
    flex-direction: column;
  }
  .icon {
    position: absolute;
    right: 26px;
    top: 26px;
    img {
      width: 18px;
      height: auto;
    }
  }
}
.title {
  font-size: 18px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 25px;
  width: 80%;
}
.time {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 19px;
}
.select-customer-icon {
  color: rgba(59, 138, 254, 1);
  div {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;
  }
}
</style>
