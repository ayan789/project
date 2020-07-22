<!--   百万无忧特別约定 -->
<template>
  <div
    id="safeguard-detail"
    sticky
  >
    <!-- <van-nav-bar title="保障详情" /> -->
    <globalNav
      :title="globalNav.title"
      @clickBack="backToPrePage"
    ></globalNav>
    <div class="title-container">
      <van-col
        class="coverage-content"
        v-if="!flag"
      >
        <div
          v-for="(text, index) in XZ"
          :key="index"
        >{{ text }}</div>
      </van-col>
      <van-col
        class="coverage-content"
        v-else
      >
        <div v-if="haveSheBao=='Y'">
          <p><b>有社保</b></p>
          <div
            v-for="(text, index) in YOU"
            :key="index"
          >{{ text }}</div>
        </div>
        <div v-else>
          <p><b>无社保</b></p>
          <div
            v-for="(text, index) in WU"
            :key="`${index+5}`"
          >{{ text }}</div>
        </div>
        <!-- <p><b>混合版</b></p>
        <div
          v-for="(text, index) in HUN"
          :key="`{${index+9}}`"
        >{{ text }}</div> -->

      </van-col>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "Aboutpersonal",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  beforeRouteEnter: (to, from, next) => {

    next(vm => {
      if (from.name == "stopCancerForm" || vm.$route.params.from == "WTI01") {
        vm.flag = true

        if (vm.$route.params.isSheBao) {
          if (vm.$route.params.isSheBao == "Y") {
            vm.haveSheBao = "Y"
          } else {
            vm.haveSheBao = "W"
          }
        }
      } else {
        vm.flag = false
      }
    })
  },
  created () {
  },
  data: () => ({
    haveSheBao: "Y",
    flag: false,
    appoint: "1",
    globalNav: {
      title: "特别约定"
    },
    XZ: [
      "1、本保险主险条款适用《中国大地保险个人医疗保险条款（C款）》及《大地通达公共交通工具意外伤害保险条款》；若投保附加险，其中，“恶性肿瘤扩展质子重离子医疗”附加险条款适用《中国大地保险附加个人恶性肿瘤扩展质子重离子医疗保险条款》；“恶性肿瘤扩展特需医疗”附加险条款适用《中国大地保险个人医疗保险条款（C款）附加扩展特需医疗保险条款》；“恶性肿瘤赴日医疗”附加险条款适用《中国大地保险附加个人恶性肿瘤赴日医疗保险条款》；“恶性肿瘤院外特种药品费用”附加险条款适用《中国大地保险附加个人恶性肿瘤院外特种药品费用医疗保险条款》。",
      "2、被保险人接受本合同中约定的100种重大疾病治疗的，可以享受绿色就医通道服务（包括全国三级甲等医院副主任以上医师专家预约、住院安排及手术安排各一次（限同一医院））及住院医疗费用垫付服务，服务申请电话：95590。"
    ],
    YOU: [
      "1、本保单续保无等待期。",
      "2、有社保被保险人在保险期限内确诊初次患有恶性肿瘤、且在中国大陆境内二级及以上公立医院普通部接受治疗而发生的合理且必要的个人支付的住院医疗费用、特殊门诊医疗费用、住院前30天及出院后30天内的门急诊医疗费用，保险人按100%给付保险金。",
      "3、无社保被保险人但以有社保的价格购买本产品的，或有社保的被保险人未以有社保身份就医并结算的，在保险期限内确诊初次患有恶性肿瘤、且在中国大陆境内二级及以上公立医院普通部治疗而发生的合理且必要的个人支付的住院医疗费用、特殊门诊医疗费用、住院前30天及出院后30天内的门急诊医疗费用，保险人对于其中60%的部分给付保险金。",
      "4、上海质子重离子医院产生的保险责任范围内费用100%给付",
      "5、本保单适用于《个人恶性肿瘤医疗保险条款》【注册编号：C00001032512018052517772】。"
    ],
    WU: [
      "1、本保单续保无等待期。",
      "2、被保险人在保险期限内确诊初次患有恶性肿瘤、且在中国大陆境内二级及以上公立医院普通部接受治疗而发生的合理且必要的个人支付的住院医疗费用、特殊门诊医疗费用、住院前30天及出院后30天内的门急诊医疗费用，保险人按100%给付保险金。",
      "3、上海质子重离子医院产生的保险责任范围内费用100%给付。",
      "4、本保单适用于《个人恶性肿瘤医疗保险条款》【注册编号：C00001032512018052517772】。",

    ],
    HUN: [
      "1、本保单续保无等待期。",
      "2、被保险人在保险期限内确诊初次患有恶性肿瘤、且在中国大陆境内二级及以上公立医院普通病房接受治疗而发生的合理且必要的个人支付的住院医疗费用、特殊门诊医疗费用、住院前30天及出院后30天内的门急诊医疗费用，保险人按100%给付保险金。",
      "3、被保险人在保险期限内确诊初次患有恶性肿瘤、且在中国大陆境内二级及以上公立医院普通病房接受治疗而发生的合理且必要的个人支付的住院医疗费用、特殊门诊医疗费用、住院前30天及出院后30天内的门急诊医疗费用，保险人按100%给付保险金。",
      "4、上海质子重离子医院产生的保险责任范围内费用100%给付。",
      "5、本保单适用于《个人恶性肿瘤医疗保险条款》【注册编号：C00001032512018052517772】。"
    ]
  }),
  methods: {
    // 返回上一页
    backToPrePage () {
      this.$globalBack();
      // console.log(this.$router);
    }
  }
};
</script>

<style scoped>
#safeguard-detail {
  background-color: #f7f7f7;
  /* overflow: hidden; */
}
#tab-content {
  padding-top: 10px;
}
#safeguard-detail {
  background: white;
}
.title-container {
  padding: 15px;
  margin: 60px 0 10px 0;
  background-color: white;
}
.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
.coverage-content {
  margin-top: 0.4rem;
}
</style>
