<!--
 * @Description: 
 * @Date: 2020-03-13 21:12:27
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-20 16:22:12
 * @FilePath: \vue-sales\src\views\order\common\moreInformation.vue
 -->
<template>
  <div id="non-car-more-information">
    <globalNav title="更多信息" @clickBack="goBack" />
    <div class="content">
      <person-information-card
        type="policyHolder"
        :insuranceType="insuranceType"
        :insurePersonInfo="insurePersonInfo"
      />
      <p v-if="insurePersonInfoList.length <= 0">
        <person-information-card
          type="recognizee"
          :insuranceType="insuranceType"
          :insurePersonInfo="insurePersonInfo"
        />
      </p>
      <p
        v-for="(item, index) in insurePersonInfoList"
        :key="index"
        v-show="insurePersonInfoList.length > 0"
      >
        <person-information-card
          type="recognizee"
          :insuranceType="insuranceType"
          :insurePersonInfo="item"
        />
      </p>
      <person-information-card
        type="invoiceInfo"
        :insuranceType="insuranceType"
        :insurePersonInfo="insurePersonInfo"
      />
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import PersonInformationCard from "../../nonCar/common/personInformation/personInformationCard";
import globalNav from "@/components/globalNav.vue";
export default {
  components: {
    [NavBar.name]: NavBar,
    [PersonInformationCard.name]: PersonInformationCard,
    [globalNav.name]: globalNav
  },
  data: () => ({
    insuranceType: 1,
    insurePersonInfo: {},
    insurePersonInfoList: []
    // applicantName: res.data.applicantName,
    // appliIdentify: res.data.appliIdentify,
    // appliIdentifyType: res.data.appliIdentifyType,
    // mobileNo:res.data.mobileNo,
    // emial:res.data.emial,
    // insuredName: res.data.accidentalInjuryInfos[key].familyName,
    // insuredIdentify: res.data.accidentalInjuryInfos[key].familyIdentifyNo,
    // relateInsured: this.$getCodeOrName("bwRelateInsured", res.data.accidentalInjuryInfos[key].familyRelation)?this.$getCodeOrName("bwRelateInsured", res.data.accidentalInjuryInfos[key].familyRelation):"",
    // insuredIdentifyType:res.data.accidentalInjuryInfos[key].familyIdentifyType,//新增
    // insuredMobile:res.data.accidentalInjuryInfos[key].mobile,
    // insuredBirthDate: data.accidentalInjuryInfos[key].insuredBirthDate,
    // insuredSheBaoType: data.accidentalInjuryInfos[key].insuredSheBaoType
  }),
  created() {
    this.insuranceType = this.$route.query
      ? this.$route.query.insuranceType
      : 1;
    this.insurePersonInfo = this.$route.query
      ? this.$route.query.insurePersonInfo
      : {};
    this.insurePersonInfoList = this.$route.query
      ? this.$route.query.insurePersonInfoList
      : [];
  },
  methods: {
    //返回上一页
    goBack() {
      this.$globalBack();
    }
  }
};
</script>

<style>
#non-car-more-information {
  padding-top: 60px;
  height: auto !important;
  /* height: calc(100vh - 30px)!important;
  overflow: scroll; */
  background-color: #f7f7f7;
}
.content {
  padding-top: 10px;
}
</style>
