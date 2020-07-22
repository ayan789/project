<template>
  <div class="productContent">
    <div class="titleWhite" id="vantab2">
      <h5>产品简介</h5>
    </div>
    <div class="card" style="height:480px;">
      <div class="pic" style="width:100%;height:100%;">
        <img
          :src="guaranteeDetailSrc[4]"
          class="lipei"
          style="width:100%;height: 100%;margin:-7% 0 0 -4%;"
        />
      </div>
    </div>

    <div class="titleWhite" id="vantab1">
      <h5>保障详情</h5>
      <!-- <p class="warp">大病小病都可保，保障额度足覆盖</p> -->
    </div>
    <div class="card" style="height:702px;">
      <div class="pic" style="width:100%;height:100%;">
        <img
          :src="guaranteeDetailSrc[5]"
          class="lipei"
          style="width:100%;height:100%;margin:-7% 0 0 -5%;"
        />
      </div>
    </div>
    <!-- <div class="card"> -->
    <!-- 客户权益 -->
    <!-- <div class="starItem">
        <div class="starItemcommonText15">
          <van-checkbox v-model="checked" icon-size="16px">
            <span class="commonText15">客户权益</span>
          </van-checkbox>
        </div>
        <star-equity :isEquity="false" :isOut="true" :starInfo="starRight" />
      </div>
    </div>
    <div class="card">
      <div class="container">
        <div>
          <img class="optimization" src="@/assets/nonCar/healthInsurance/optimization.png" alt />
          <span>为您优选</span>
        </div>
        <div class="optimizationImg"></div>
      </div>
    </div>-->
    <div class="titleWhite" id="vantab2">
      <h5 style="margin-bottom:14px;">常见问题</h5>
    </div>
    <div id="common-problem">
      <div style="margin:0 20px 15px;">
        <div>
          <question-item
            v-for="(item, index) in questionArr"
            :key="item.question"
            :index="index"
          >
            <span slot="question" class="question">{{ item.question }}</span>
            <span slot="answer" class="answer">{{ item.answer }}</span>
          </question-item>
        </div>
        <div class="more" @click="moreQuesion">
          {{ !isClicked ? "查看更多" : "收起" }}
        </div>
      </div>
    </div>

    <div class="titleWhite" id="vantab2">
      <h5>理赔说明</h5>
      <p style="margin-bottom:14px;">全程指导，5步快速理赔</p>
    </div>
    <div class="card">
      <div class="pic" style="height:100%;" v-if="person == 'T'">
        <img
          :src="guaranteeDetailSrc[1]"
          class="lipei"
          style="width:100%;margin-left:-4%;"
        />
      </div>
      <div class="pic" style="height:100%;" v-else>
        <img
          :src="guaranteeDetailSrc[2]"
          class="lipei"
          style="width:100%;margin-left:-4%;"
        />
      </div>
    </div>

    <div class="titleWhite" id="vantab3">
      <h5>条款须知</h5>
    </div>
    <div class="clause">
      <div class="blueDiv">
        <div class="spaceWhite"></div>
        <div
          class="spaceWhite"
          style="position: absolute;top:0px;right:0px;"
        ></div>
        <van-cell-group style="top:-9px;">
          <van-cell title="保险条款" is-link @click="toTipPage('保险条款')" />
          <van-cell
            title="投保须知"
            value
            is-link
            @click="toTipPage('投保须知')"
          />
          <van-cell
            title="投保声明"
            value
            is-link
            @click="toTipPage('投保声明')"
          />
          <van-cell title="特別约定" is-link @click="toTipPage('特別约定')" />
          <van-cell title="责任免除" is-link @click="toTipPage('责任免除')" />
        </van-cell-group>
      </div>
    </div>
  </div>
</template>
<script>
// import starEquity from "@/components/starEquity.vue";
import questionItem from "../common/question-item";
export default {
  components: {
    // starEquity,
    questionItem
  },
  created() {
    this.questionArr = this.productContents.slice(0, 4);
  },
  methods: {
    toTipPage(name) {
      const topPos = document.documentElement.scrollTop;
      localStorage.setItem("topPos", topPos);
      // this.$store.dispatch("nonCar/updatePos", topPos);
      if (name === "保险条款") {
        this.$router.push({ name: "personalTerms" });
      } else if (name === "投保须知") {
        this.$router.push({ name: "InsureNotice" });
      } else if (name === "投保声明") {
        this.$router.push({ name: "InsureStatement" });
      } else if (name === "特別约定") {
        this.$router.push({ name: "Aboutpersonal" });
      }else if (name === "责任免除") {
        this.$router.push({ name: "liabilityExempt" });
      }
      this.$emit('toTipPage')
    },
    moreQuesion() {
      if (!this.isClicked) {
        this.questionArr = this.productContents;
      } else {
        this.questionArr = this.productContents.slice(0, 4);
      }
      this.isClicked = !this.isClicked;
    }
  },
  props: {
    person: {
      type: String,
      default: "T"
    }
  },
  data() {
    return {
      questionArr: [],
      isClicked: false,
      checked: true,
      starRight: {
        curLevel: 1,
        curPoints: 0,
        totPoints: 0,
        name: "",
        custIntegral: 0,
        custLevel: 0
      },
      productIcons: [
        require("@/assets/nonCar/healthInsurance/img1.png"),
        require("@/assets/nonCar/propertyInsurance/copyRenewal2.png"),
        require("@/assets/nonCar/propertyInsurance/copyRenewal3.png"),
        require("@/assets/nonCar/healthInsurance/img4.png"),
        require("@/assets/nonCar/healthInsurance/img5.png"),
        require("@/assets/nonCar/healthInsurance/icon8.png"),
        require("@/assets/nonCar/healthInsurance/img6.png"),
        require("@/assets/nonCar/healthInsurance/img7.png")
      ],
      guaranteeDetailSrc: [
        require("@/assets/nonCar/healthInsurance/detail.png"),
        require("@/assets/nonCar/healthInsurance/lipei-geren.png"),
        require("@/assets/nonCar/healthInsurance/lipei-jiating.png"),
        require("@/assets/nonCar/propertyInsurance/bianZhi.png"),
        require("@/assets/nonCar/healthInsurance/chanpin.png"),
        require("@/assets/nonCar/healthInsurance/baozhang.png"),
      ],
      productContents: [
        {
          question: "我有医保了，还需要购买百万医疗吗？",
          answer:
            "非常有必要，以癌症为例，治疗费用动辄几十万甚至上百万，医保只能覆盖60-70%，且很多疗效好，副作用低的自费药、进口药，医保都不能报销。而天地关爱百万医疗不限病种，不限用药，叠加最高可报600万，保费最低只需138元，是对医保最好的补充。"
        },
        {
          question: "1万元免赔额是什么，家庭共享1万元免赔额的独特性在哪？",
          answer:
            "在扣除社保报销或公费医疗报销金额后，对于您个人支付的医疗费用需扣除1万元免赔额。家庭共享1万元免赔额的独特之处在于不只是大病，小病也能用上天地关爱百万医疗进行报销了，以四口之家为例，购买普通的百万医疗险，全家人需自费4万元才能用上百万医疗进行报销，而天地关爱百万医疗只需自费1万元，就能进行报销了，是实实在在用得上的百万医疗险。"
        },
        {
          question: "期满了应如何续保？",
          answer:
            "保险合同期满前将通过短信发送投保链接给投保人，点击投保链接完成付款即可完成续保。续保无等待期也不需要重新进行健康告知。"
        },
        {
          question:
            "投保了天地关爱百万医疗旧版本产品，如何升级成天地关爱百万医疗最新一代？",
          answer:
            "大地保险为尊贵的老客户提供续保升级服务，投保过天地关爱百万旧版本产品的老客户在续保时，即可遵照新一代产品的续保规则选择升级为天地关爱百万医疗新一代，无需重新健康告知，也无需重新计算等待期。"
        },
        {
          question: "可以报销哪些医疗费用？",
          answer:
            "被保人因意外或疾病在二级及以上医院普通部接受住院及前30天和后30天门急诊、特殊门诊和门诊手术治疗的，对于责任内个人支付超过1万的部分，100%赔付，不限医保范围，进口药自费药均可报销；100种重大疾病医疗无免赔报销。"
        },
        {
          question: "质子重离子治疗，哪些费用可以赔付？",
          answer:
            "质子和重离子技术是国际公认的放疗尖端技术，是目前治疗恶性肿瘤的最佳治疗手段之一。为给用户提供最好的医疗保障，如果您购买了质子重离子医疗附加险，我们对您在上海质子重离子医院接受质子重离子医疗所发生的医疗费用，按照100%比例赔付。"
        },
        {
          question: "恶性肿瘤特需病房包含哪些费用报销？",
          answer:
            "如购买了恶性肿瘤扩展特需医疗保险附加险，被保险人在等待期后首次确诊患有条款约定的恶性肿瘤（无论一种或者多种），因此在保险人指定的医疗机构或者境内合法经营的二级以上（含）公立医院的特需医疗部或VIP部，由此发生的必要的、合理的主险保险条款中约定的医疗费用。"
        },
        {
          question: "是否所有被保险人都能够享受医疗费用垫付服务？如何申请？",
          answer:
            " 所有被保险人均可享受医疗费用垫付服务，但需注意我司仅针对符合保险责任范围内治疗因100种重大疾病发生的住院医疗费用提供垫付服务。被保险人通过95590 全国服务电话、柜台或线上均可提交申请，申请材料包括：（1）理赔垫付申请书；（2）重疾确诊报告；（3）医院催缴凭证及押金单；（4）身份证及社保卡复印件；（5）就诊门诊病历、住院病情证明（含病历记录首页）、确诊检查单（包括但不限于病理报告单、心电图等）。"
        },
        {
          question: "绿色通道服务包含哪些内容？",
          answer:
            " 我司百万医疗产品针对接受100种重大疾病住院治疗的被保险人提供绿色通道服务，具体内容包含：全国三级甲等医院副主任以上医师专家预约、住院安排及手术安排各一次（限同一医院）。"
        }
      ]
    };
  }
};
</script>
<style lang="less" scoped>
#problem {
  width: 22px;
  height: 20px;
  float: left;
}
#problem-bottom {
  width: 5px;
  height: 3px;
  float: right;
}
.container {
  padding: 20px 10px;

  .optimization {
    width: 22px;
    height: 22px;
    float: left;
    margin-right: 8px;
  }
  span {
    font-size: 15px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 21px;
  }
}
.optimizationImg {
  width: 312px;
  height: 152px;
  margin: 10px auto;
  background: url("../../../assets/nonCar/healthInsurance/youOptimization.png")
    no-repeat;
  background-size: 312px 152px;
}
.details {
  width: 311px;
  height: 158px;
  margin: 15px auto;
  // background: linear-gradient(
  //   150deg,
  //   rgba(52, 140, 255, 1) 0%,
  //   rgba(99, 220, 228, 1) 100%
  // );
  // background: rgba(99, 220, 228, 1);
  h4 {
    padding-top: 10px;
    color: #2480ea;
    padding-left: 20px;
  }
  .title {
    text-align: center;
    font-size: 13px;
    color: #2480ea;
    line-height: 19px;
    padding: 14px 0;
  }
  .outpatientDepartment {
    background: #63dce4;
    width: 133px;
    height: 109px;
    background: url("../../../assets/nonCar/prospectus/outpatientDepartment.png");
    background-size: 133px 109px;
    float: left;
    .outpatientDetails {
      padding: 10px 0 0 20px;
      color: #43a5f7;
      div {
        line-height: 22px;
      }
    }
  }
  .right {
    float: right;
    width: 170px;
    height: 109px;
    // background: url("../../../assets/nonCar/prospectus/outpatientDepartment.png");
    // background-size: 170px 109px;
    .hospitalization {
      width: 170px;
      height: 52px;
      background: url("../../../assets/nonCar/prospectus/hospitalization.png");
      background-size: 170px 52px;
      margin-bottom: 10px;
      .hospitalizationDetails {
        padding-left: 20px;
        padding-top: 5px;
        color: #43a5f7;
      }
    }
    .medical {
      width: 170px;
      height: 52px;
      background: url("../../../assets/nonCar/prospectus/medical.png");
      background-size: 170px 52px;
      .medicalDetails {
        padding-left: 20px;
        padding-top: 5px;
        color: #43a5f7;
      }
    }
  }
}
.card {
  background: rgba(255, 255, 255, 1);
  border-radius: 0.2rem;
  box-sizing: border-box;
  margin: 0.2rem;
  overflow: hidden;
  margin-top: 0;
}
.titleWhite {
  text-align: center;
  padding: 15px 0 5px 0;
  background: url("../../../assets/common/icon8.png") no-repeat;
  background-size: 100px 32px;
  background-position: 50% 22%;
  h5 {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    line-height: 16px;
    margin-bottom: 8px;
  }

  p {
    height: 13px;
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 13px;
  }
  .warp {
    width: 221px;
    height: 36px;
    text-align: center;
    font-size: 13px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 18px;
    margin: 0 auto;
  }
}
.productInfo {
  // width: 356px;
  height: 232px;
  background: rgba(255, 255, 255, 1);
  border-radius: 8px;
  margin: 0 auto;
  padding: 20px 37px 20px 18px;
  .productText {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(17, 17, 17, 1);
    line-height: 20px;
    margin-bottom: 16px;

    h3 {
      margin-bottom: 5px;
    }

    div {
      color: #666;
    }
  }
  img {
    width: 45px;
    height: auto;
  }
}
.pic {
  width: 356px;
  height: 379px;
  background: rgba(255, 255, 255, 1);
  border-radius: 8px;
  margin: 0 auto;
  padding: 25px 17px;
  img {
    width: 94%;
    height: auto;
  }
  .pic-tetx {
    width: 278px;
    h5 {
      font-weight: 600;
      font-size: 14px;
      color: #333333;
    }
    span {
      font-size: 13px;
      color: #999999;
    }
  }
}

.productContent {
  background: rgba(36, 128, 234, 1);
  padding-bottom: 65px;
}
.title1 {
  height: 43px;
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  // line-height: 43px;
  text-align: center;
  padding: 18px 0 9px 0;
}
.blueBack {
  background-image: url(../../../assets/nonCar/propertyInsurance/titleBack.png);
  background-repeat: no-repeat;
  background-size: 100% auto;
}

.clause {
  width: 96%;
  height: 254px;
  background: rgba(255, 255, 255, 1);
  border-radius: 8px 0px 8px 8px;
  margin: 6px auto 0;
  position: relative;
  .blueDiv {
    width: 100%;
    height: 27px;
    background-image: url(../../../assets/nonCar/worryFree/noticeClause.png);
    background-position: 0 0;
    background-repeat: no-repeat;
    background-size: 106% 27px;
    position: relative;
  }
}
.spaceWhite {
  width: 18px;
  height: 36px;
  border-radius: 9px;
  background: #fff;
}
.bottom18 {
  margin-bottom: 18px;
}
.commonText15 {
  font-size: 15px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  line-height: 21px;
}
.starItemcommonText15 {
  padding-top: 20px;
  padding-left: 20px;
}
#common-problem {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 360px;
  min-height: 295px;
  border-top: 1px solid transparent;
  background: rgb(255, 255, 255);
  border-radius: 8px;
  margin: 0 auto;
  // padding: 0 21px 16px 21px;
  .more {
    margin: 30px auto 0 auto;
    text-align: center;
    width: 52px;
    height: 19px;
    font-size: 13px;
    font-weight: 400;
    color: rgb(153, 153, 153);
    line-height: 19px;
  }
}
</style>
