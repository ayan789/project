
<template>
  <div
    class="prInfoPlan"
    id="prInfoPlan"
  >
    <div class="title blueBack">
      <productTitle v-show="prTitle">{{prTitle}}</productTitle>
    </div>
    <div class="outter">
      <van-tabs
        v-model="activeEdition"
        swipeable
        color="#3B8AFE"
        line-height="5px"
        line-width="28px"
        title-active-color="#111111"
        title-inactive-color="#111111"
        ref="editionRef"
        @change="renderPerson(true)"
        @rendered="rend"
      >
        <van-tab
          v-for="(item,index) in formulaList"
          :title="item.edition"
          :key="index"
          :name="item.planId"
          title-style="{font-size:14px;font-family:PingFangSC-Medium,PingFang SC;font-weight:500; color:rgba(17,17,17,1); line-height:46px;}"
        >
          <safePlanCard
            ref="safePlanCard"
            :activeEdition="item.planId"
            :fromRoute="fromRoute"
            :mainInsuranceList="item.mainInsuranceList"
            :subsidiaryInsuranceList="item.subsidiaryInsuranceList"
            @toggleInsurance="toggleInsurance"
          />
        </van-tab>
      </van-tabs>
      <safePlanPersonInfo
        :isSharePage="isSharePage"
        ref="safePlanPersonInfo"
        :personInfoForm="personInfoForm"
        :backReasoningFactor="backReasoningFactor"
        :riskCode="riskCode"
        :fromRoute="fromRoute"
        @selectInsuredAge="selectInsuredAge"
        @changeShebao="changeShebao"
        @changeCommNumOfCopies="changeCommNumOfCopies"
        @changeSecurityNum="changeSecurityNum"
        @selectOther="selectOther"
      />
    </div>
    <productContent
      :prContentObj="prContentObj"
      :commonProblem="commonProblem"
    />
  </div>
</template>
<script>
import productTitle from "../common/title";
import safePlanCard from "../common/safePlan_card_plan";
import safePlanPersonInfo from "../common/safePlan_personInfoPlan";
import productContent from "../common/productContent";
import { trial } from "@src/http/module/nonCar";

let channelInfoParam = {
  isProxy: false,
  businessNatureCode: "0101",
  businessNatureName: "业务员销售（直销）",
  agentCode: "",
  agentName: "",
  agreementNo: "",
  agreementName: "",
  agentAgreementCode: "",
  agentAgreementName: "",
  businessNature2Name: "",
  handler2Code: "",
  handler2Name: "",
  businessCode: "",
  businessName: "",
  projectCode: "",
  projectName: "",
  thirdBusinessInfo: "",
  id: "",
  isDefault: "",
  mobile: "",
  operator: "",
  orgCode: "",
  practisingCertificateNo: "",
  salesCodeName: "",
  salesIdNo: "",
  salesName: "",
  businessSourceName: "",
  inputDate: "",
  addressName: "",
  businessNature: "",
  comCode: "",
  permitNo: "",
  phoneNumber: "",
  riskCode: "YEF"
};
export default {
  components: {
    productTitle,
    safePlanCard,
    safePlanPersonInfo,
    productContent
  },
  props: {
    safeguardPlanList: Array,
    commonProblem: Object,
    backReasoningFactor: Array,
    fromRoute: Object,
    isSharePage: String
  },
  data () {
    return {
      prTitle: "",//保障计划title
      activeEdition: 1,//tabs绑定值
      formulaList: [],//方案
      personInfoForm: [], //form
      riskCode: "",
      prContentObj: { //简介等
        productInfo: {},
        safeguardDetail: {},
        claimStatement: {},
        termsAndConditions: {}
      },

      channelInfo: channelInfoParam,
      isNeedHealthNotice: false,//是否健康告知
      healthNoticeObj: {},//健康告知obj
      riskCode: "",
      storeData: {}, //缓存取的数据
      strategyId: 0

    };
  },
  computed: {
    formulaData: {
      set () {
        if (this.safeguardPlanList.length > 0) {
          this.formulaList = this.safeguardPlanList;
        }
      },
      get () {
        return this.safeguardPlanList;
      }
    },
    proTitle () {
      return this.prTitle;
    },
    frRoute () {
      return this.fromRoute;
    }
  },
  watch: {
    formulaData () {
      if (this.safeguardPlanList.length > 0) {
        this.formulaList = this.safeguardPlanList;
        this.createPageData();
      }
    },
    frRoute: {
      handler () {
        return this.fromRoute;
      },
      deep: true
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.createPageData();
    });
  },
  updated () {
    this.$nextTick(() => {
      this.updatePremiumsNum(); //更新保费
    });
  },
  methods: {
    rend () {
      this.renderPerson(false);
    },
    // 构造页面所需数据
    createPageData () {
      this.storeData = JSON.parse(
        JSON.stringify(this.$store.state.config.productInfo)
      );

      this.formulaList = this.safeguardPlanList;
      for (let i = 0, len = this.formulaList.length; i < len; i++) {
        if (
          this.formulaList[i].hasOwnProperty("safeguardPlan") &&
          this.formulaList[i].safeguardPlan
        ) {
          this.formulaList[i].mainInsuranceList = this.formulaList[
            i
          ].safeguardPlan.formulaObj.mainInsurance;
          this.formulaList[i].subsidiaryInsuranceList = this.formulaList[
            i
          ].safeguardPlan.formulaObj.subsidiaryInsurance;
          let planId = this.formulaList[i].planId;
          this.formulaList[i][planId] = this.formulaList[
            i
          ].safeguardPlan.relatedPersonInfo;
          this.formulaList[i][planId].formulaCode = this.formulaList[
            i
          ].formulaCode;
          this.formulaList[i][planId].riskCode = this.formulaList[i].riskCode;
          // 保障年限
          this.formulaList[i][planId].giCoveragePeriod = this.formulaList[
            i
          ].giCoveragePeriod;
          // 保障年限单位 1 年 2 月 3 天
          this.formulaList[i][planId].giPeriodType = this.formulaList[
            i
          ].giPeriodType;
          for (
            let j = 0, len = this.formulaList[i].mainInsuranceList.length;
            j < len;
            j++
          ) {
            this.formulaList[i].mainInsuranceList[j].formatAmout =
              this.formulaList[i].mainInsuranceList[j].amount +
              " " +
              this.formulaList[i].mainInsuranceList[j].unit;
          }
        }
      }
      this.activeEdition =
        this.storeData.planId == 1 ? this.activeEdition : this.storeData.planId;

      this.renderPerson(false);
    },
    // 渲染子组件
    renderPerson (flag) {

      for (
        let i = 0, len = this.$refs.editionRef.children.length;
        i < len;
        i++
      ) {
        let { isActive, index, name } = this.$refs.editionRef.children[i];
        if (isActive) {
          let {
            form,
            formulaCode,
            riskCode,
            giCoveragePeriod,
            giPeriodType
          } = this.formulaList[index][name];
          this.personInfoForm = form;
          this.riskCode = riskCode;
          this.prTitle = this.formulaList[index].safeguardPlan.title;
          let {
            productInfo,
            claimStatement,
            termsAndConditions,
            safeguardDetail
          } = this.formulaList[index];
          this.prContentObj = {
            productInfo,
            safeguardDetail,
            claimStatement,
            termsAndConditions
          };
          this.isNeedHealthNotice = this.formulaList[index].isNeedHealthNotice;
          this.healthNoticeObj = this.formulaList[index].healthNotice;
          this.$emit(
            "getIsNeedhealNotice",
            this.isNeedHealthNotice,
            this.healthNoticeObj
          );
          let { isIssueAfterPay } = this.formulaList[index];
          if (flag) {
            this.$store.dispatch("config/resetDetailPageData");
          }
          this.$store.dispatch(
            "config/updateStrategyId",
            this.strategyId == 0 ? parseInt(this.strategyId) : 0
          );
          this.$store.dispatch("config/updateIsIssueAfterPay", isIssueAfterPay);
          let obj = {
            formulaCode,
            riskCode,
            giCoveragePeriod,
            giPeriodType,
            planId: name
          };
          let obj1 = {
            formulaCode,
            riskCode,
            planId: name
          };
          this.$store.dispatch("config/updateDetailData", obj);
          this.$store.dispatch("config/updateProductInfo", obj1);
          this.activeEdition = name;
        }
      }
      this.refreshChildComponent(flag);
    },
    //清空store缓存,刷新子组件
    refreshChildComponent (flag) {
      if (flag) {
        let index = this.$refs.safePlanCard.findIndex(item => {
          return item.activeEdition == this.activeEdition;
        });
        this.$store.dispatch("config/updateProductInfo", {
          planId: this.activeEdition
        });
        if (index >= 0) {
          this.$refs.safePlanCard[index].updateMainSub();
        }
        this.$refs.safePlanPersonInfo.renderFrom(this.activeEdition);
      }
    },
    // 获取税率的key
    getRateKey () {
      let trialArr = this.$store.state.config.detailData.trialArr;
      // let formData = this.$store.state.config.detailData.formData;
      var listArr = [];
      trialArr.forEach(function (el) {
        for (var i = 0; i < listArr.length; i++) {
          if (!el.index) {
            el.index = i;
          }
          // 对比相同的字段key，相同放入对应的数组
          if (listArr[i].index == el.index) {
            listArr[i].listInfo.push({
              field: el.field,
              index: el.index,
              children: el.children
            });
            return;
          }
        }

        // 第一次对比没有参照，放入参照
        listArr.push({
          index: el.index,
          listInfo: [
            {
              field: el.field,
              index: el.index,
              children: el.children
            }
          ]
        });
      });
      console.log(452, listArr);

      // let keys = [];
      // for (let i = 0, len = listArr.length; i < len; i++) {
      //   let { listInfo } = listArr[i];
      //   let key = "";
      //   for (let j = 0, len1 = listInfo.length; j < len1; j++) {
      //     let { field } = listInfo[j];
      //     let obj = formData.find(item => {
      //       return item.field == field;
      //     });

      //     if (obj) {
      //       listInfo[j].value = obj.value;
      //     } else {
      //       listInfo[j].value = "";
      //     }

      //     if (j == len1 - 1) {
      //       key += listInfo[j].value;
      //     } else {
      //       key += listInfo[j].value + "_";
      //     }
      //   }
      //   if (key) {
      //     keys.push(key);
      //   }
      // }
      // return keys;
    },
    // 险种勾选,更新保费
    toggleInsurance (index, selectedObj) {
      let subObj = this.$store.state.config.detailData.selectedSubIn;
      let id = selectedObj.id;
      let obj = subObj ? JSON.parse(JSON.stringify(subObj)) : {};

      if (this.$isNull(subObj[id])) {
        obj[id] = selectedObj;
      } else {
        delete obj[id];
      }

      this.$store.dispatch("config/updateSelectedSubIn", obj);
      this.updatePremiumsNum();
    },
    // 更改年龄，保障人数等
    selectOther (code, value, backReasoning) {
      if (code.indexOf("insuredAge") >= 0) {
      } else if (code.indexOf("securityNum") >= 0) {
      } else if (code.indexOf("commSecurityPeriod") >= 0) {
        let formData = this.$store.state.config.detailData.formData;
        let obj = formData.find(item => {
          return item.field == code;
        });
        obj.value = value;
        if (backReasoning == "1") {
          let obj = this.backReasoningFactor.find(item => {
            return item.factorValue == value;
          });
          if (!this.$isNull(obj)) {
            this.activeEdition = obj.planId;
          }
        } else {
          console.log(513, code);
          this.updatePremiumsNum(code, value);
        }
      } else {
      }
    },

    // 改变年龄
    selectInsuredAge (code, value, backReasoning) {
      let formData = this.$store.state.config.detailData.formData;
      let obj = formData.find(item => {
        return item.field == code;
      });
      obj.value = value;
      if (backReasoning == "1") {
        let obj = this.backReasoningFactor.find(item => {
          return item.factorValue == value;
        });
        if (!this.$isNull(obj)) {
          this.activeEdition = obj.planId;
        }
      } else {
        this.updatePremiumsNum(code, value);
      }
    },

    // 改变被保人数
    changeSecurityNum (code, value, backReasoning) {
      let formData = this.$store.state.config.detailData.formData;
      let obj = formData.find(item => {
        return item.field == code;
      });
      obj.value = value;
      if (backReasoning == "1") {
        let value = this.$store.state.config.detailData.securityNum;
        let obj = this.backReasoningFactor.find(item => {
          return item.factorValue == value;
        });
        if (!this.$isNull(obj)) {
          this.activeEdition = obj.planId;
        }
      } else {
        this.updatePremiumsNum(code, value);
      }
    },
    // 改变社保
    changeShebao (code, value, backReasoning) {
      let formData = this.$store.state.config.detailData.formData;
      let obj = formData.find(item => {
        return item.field == code;
      });
      obj.value = value;
      if (backReasoning == "1") {
        let obj = this.backReasoningFactor.find(item => {
          return item.factorValue == value;
        });
        if (!this.$isNull(obj)) {
          this.activeEdition = obj.planId;
        }
      } else {
        this.updatePremiumsNum(code, value);
      }
    },
    // 改变保险份数
    changeCommNumOfCopies (code, value, backReasoning) {
      let formData = this.$store.state.config.detailData.formData;
      let obj = formData.find(item => {
        return item.field == code;
      });
      obj.value = value;
      if (backReasoning == "1") {
        let val = this.$store.state.config.detailData.commNumOfCopies;
        let obj = this.backReasoningFactor.find(item => {
          return item.factorValue == val;
        });
        if (!this.$isNull(obj)) {
          this.activeEdition = obj.planId;
        }
      } else {
        this.updatePremiumsNum(code, value);
      }
    },

    // 获取更新保费的请求rate参数
    getRate (code, value, rate) {
      let obj = {
        rateAgeMin: null,
        rateAgeMax: null,
        periodMin: null,
        periodMax: null,
        rateSeatMin: null,
        rateSeatMax: null
      };
      // 年龄
      if (code.indexOf("Age") >= 0) {
        if (code.indexOf("_") >= 0 && value) {
          obj.rateAgeMin = parseInt(value.split("-")[0]);
          obj.rateAgeMax = parseInt(value.split("-")[1]);
          rate[code.split("_")[0] - 1] = obj;
        } else if (code.indexOf("_") < 0 && value) {
          // rate[0].rateAgeMin = parseInt(value.split("-")[0]);
          // rate[0].rateAgeMax = parseInt(value.split("-")[1]);
          obj.rateAgeMin = parseInt(value.split("-")[0]);
          obj.rateAgeMax = parseInt(value.split("-")[1]);
          rate[0] = obj;
        }
      }
      // 保障期间
      if (code.indexOf("Period") >= 0 && value) {
        rate.forEach(item => {
          item.periodMin = parseInt(value.split("-")[0]);
          item.periodMax = parseInt(value.split("-")[1]);
        });
      }
      // 座位
      if (code.indexOf("Seat") >= 0) {
        if (code.indexOf("_") >= 0 && value) {
          // rate[code.split("_")[0] - 1].rateSeatMin = parseInt(
          //   value.split("-")[0]
          // );
          // rate[code.split("_")[0] - 1].rateSeatMax = parseInt(
          //   value.split("-")[1]
          // );
          obj.rateSeatMin = parseInt(value.split("-")[0]);
          obj.rateSeatMax = parseInt(value.split("-")[1]);
          rate[code.split("_")[0] - 1] = obj;
        } else if (code.indexOf("_") < 0 && value) {
          // rate[0].rateSeatMin = parseInt(value.split("-")[0]);
          // rate[0].rateSeatMax = parseInt(value.split("-")[1]);
          obj.rateSeatMin = parseInt(value.split("-")[0]);
          obj.rateSeatMax = parseInt(value.split("-")[1]);
          rate[0] = obj;
        }
      }
      return rate;
    },
    // 更新保费
    updatePremiumsNum (code, value) {
      let selectedMainIn = this.$store.state.config.detailData.selectedMainIn;
      let selectedSubIn = this.$store.state.config.detailData.selectedSubIn;
      let commNumOfCopies = this.$store.state.config.detailData.commNumOfCopies;
      let planCoverageIds = [];
      for (let k in selectedMainIn) {
        let { id } = selectedMainIn[k];
        planCoverageIds.push(id);
      }

      for (let k in selectedSubIn) {
        let { id } = selectedSubIn[k];
        planCoverageIds.push(id);
      }
      let rate = [
        {
          rateAgeMin: null,
          rateAgeMax: null,
          periodMin: null,
          periodMax: null,
          rateSeatMin: null,
          rateSeatMax: null
        }
      ];

      let trialArr = this.$store.state.config.detailData.trialArr;
      let formData = this.$store.state.config.detailData.formData;
      for (let i = 0, len = trialArr.length; i < len; i++) {
        let { listInfo } = trialArr[i];
        for (let j = 0, len1 = listInfo.length; j < len1; j++) {
          let { field } = listInfo[j];
          let obj = formData.find(item => {
            return item.field == field;
          });

          if (obj) {
            listInfo[j].value = obj.value;
          } else {
            listInfo[j].value = "";
          }
          // console.log(688, trialArr);
          if (field) {
            rate = this.getRate(field, listInfo[j].value, rate);
          }
        }
      }

      if (code && code.indexOf("commNumOfCopies") >= 0) {
        let sum = this.$store.state.config.detailData.basePremium;
        if (commNumOfCopies != 0) {
          sum = sum * commNumOfCopies;
        }
        // console.log(684, sum, commNumOfCopies, planCoverageIds);
        this.$store.dispatch("config/updateSumPremium", sum);
        return;
      }

      // rate = this.getRate(code, value, rate);
      console.log(707, rate, code, value);

      rate.forEach(item => {
        if (!item.rateAgeMin) {
          item.rateAgeMin = null;
        }
        if (!item.rateAgeMax) {
          item.rateAgeMax = null;
        }
        if (!item.periodMin) {
          item.periodMin = null;
        }
        if (!item.periodMax) {
          item.periodMax = null;
        }
        if (!item.rateSeatMin) {
          item.rateSeatMin = null;
        }
        if (!item.rateSeatMax) {
          item.rateSeatMax = null;
        }
      });
      let obj = {
        planId: this.activeEdition,
        planCoverageIds: [...planCoverageIds],
        rate: [...rate]
      };
      trial(obj)
        .then(res => {
          if (res.code == "1") {
            let sum = res.data ? parseInt(res.data) : 0;
            this.$store.dispatch(
              "config/updateBasePremium",
              res.data ? parseInt(res.data) : 0
            );
            if (commNumOfCopies != 0) {
              sum = sum * commNumOfCopies;
            }
            // console.log(728, sum, commNumOfCopies, planCoverageIds);

            this.$store.dispatch("config/updateSumPremium", sum);
          } else {
            this.$toast(res.msg);
          }
        })
        .catch();
    },

    // 设置代理的值
    setChannelInfo () {
      this.$refs.safePlanPersdonInfo.setChannelInfo();
    }
  }
};
</script>
<style lang="less" scoped>
#prInfoPlan {
  // background: rgba(36, 128, 234, 1);
  height: auto !important;
  padding-bottom: 60px;

  .outter {
    margin: 0 0.2rem;
    box-sizing: border-box;
    overflow: hidden;
    .van-tabs--line .van-tabs__wrap {
      height: 1.17333rem;
      border-radius: 8px 8px 0 0;
    }
    .van-tab--active {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(17, 17, 17, 1);
      line-height: 46px;
    }
    [class*="van-hairline"]::after {
      border: none;
    }
    .van-tabs__line {
      z-index: 0;
    }
  }
  .van-checkbox__icon--disabled.van-checkbox__icon--checked .van-icon {
    color: #fff;
  }

  .van-checkbox__icon--disabled .van-icon {
    background-color: #3b8afe;
    border-color: #3b8afe;
  }
  .title {
    height: 43px;
    padding-top: 10px;
    text-align: center;
  }
  .blueBack {
    background-image: url(../../../../assets/nonCar/propertyInsurance/titleBack.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
  }
  .card-title {
    font-weight: 600;
  }

  select {
    appearance: none;
    border: none;
    color: #323233;
    background: #fff;
    outline: none;
    -moz-appearance: none; /* Firefox */
    -webkit-appearance: none; /* Safari 和 Chrome */
  }
}
</style>
