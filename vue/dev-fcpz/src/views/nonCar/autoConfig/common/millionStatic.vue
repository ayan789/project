<template>
  <div class="productContent">
    <div class="titleWhite" id="vantab0">
      <h5>产品简介</h5>
      <p>大病小病都可保，保障额度足覆盖备份</p>
    </div>
    <div class="card">
      <div class="productInfo">
        <van-row>
          <van-col span="6">
            <img :src="productIcons[0]" alt="copyRenewal" />
          </van-col>
          <van-col span="18" class="productText">
            <h3>全家老小的全面选择</h3>
            <div>30天到60岁可投保，</div>
            <div>最高续保年龄扩展至100岁。</div>
          </van-col>
        </van-row>
        <van-row>
          <van-col span="6">
            <img :src="productIcons[1]" alt="copyRenewal" />
          </van-col>
          <van-col span="18" class="productText">
            <h3>免赔额</h3>
            <div>恶性肿瘤和10种重大手术扩展至100种重大疾病，无免赔额。</div>
          </van-col>
        </van-row>
        <van-row>
          <van-col span="6">
            <img :src="productIcons[2]" alt="copyRenewal" />
          </van-col>
          <van-col span="18" class="productText">
            <h3>新增自选附加险</h3>
            <div>
              恶性肿瘤院外特种药品附加险覆盖了恶性肿瘤院外费用，新增恶性肿瘤赴日就医接受先进的治疗技术，就医环境舒适。
            </div>
          </van-col>
        </van-row>
        <van-row>
          <van-col span="6">
            <img :src="productIcons[3]" alt="copyRenewal" />
          </van-col>
          <van-col span="18" class="productText">
            <h3>增值服务</h3>
            <div>
              恶性肿瘤院外特种药品附加险覆盖了恶性肿瘤院外绿色通道，医疗费用垫付服务涵盖疾病扩展至100种重大疾病。
            </div>
          </van-col>
        </van-row>
        <van-row>
          <van-col span="6">
            <img :src="productIcons[4]" alt="copyRenewal" />
          </van-col>
          <van-col span="18" class="productText">
            <h3>住院相关急症</h3>
            <div>住院前15天增加至住院前30天。</div>
          </van-col>
        </van-row>
        <van-row>
          <van-col span="6"></van-col>
          <van-col span="18" class="productText">
            <h3>救护车使用类</h3>
            <div>同城救护车使用费用也覆盖。</div>
          </van-col>
        </van-row>
      </div>
    </div>

    <div class="titleWhite" id="vantab1">
      <h5>保障详情</h5>
      <p class="warp">大病小病都可保，保障额度足覆盖</p>
    </div>
    <div class="card">
      <div class="pic">
        <img :src="guaranteeDetailSrc[0]" />
      </div>
      <div class="details">
        <div class="title">住院和特殊门诊医药费,合理范围不设社保都能报</div>
        <div class="outpatientDepartment">
          <h4>门诊费</h4>
          <div class="outpatientDetails">
            <div>住院前30天门诊</div>
            <div>住院后30天门诊费</div>
            <div>特殊门诊</div>
          </div>
        </div>
        <div class="right">
          <div class="hospitalization">
            <h4>住院费</h4>
            <div class="hospitalizationDetails">床位费,手术费,诊疗费</div>
          </div>
          <div class="medical">
            <h4>医药费</h4>
            <div class="medicalDetails">进口药,自费药,社保用药</div>
          </div>
        </div>
      </div>
    </div>
    <!-- 客户权益 -->
    <div class="card" v-if="!healthTellFamilyValue">
      <div class="starItem">
        <div class="starItemcommonText15">
          <van-checkbox disabled v-model="checked" icon-size="16px">
            <span class="commonText15">客户权益</span>
          </van-checkbox>
        </div>
        <star-equity :isEquity="false" :isOut="true" :starInfo="starRight" />
      </div>
    </div>
    <div class="card" v-else>
      <div class="enjoyContainer">
        <div>
          <van-checkbox v-model="enjoyValue" icon-size="16px">
            <span class="enjoy">尊享服务</span>
          </van-checkbox>
          <div class="enjoyDiv">
            <img class="img" :src="guaranteeDetailSrc[2]" />
            <div class="enjoyRight">
              <div class="money">百万医疗尊享垫费服务</div>
              <div class="describe">
                每次垫付金额不超过5 万元人民币，每次住院最多可申请3 次
              </div>
            </div>
          </div>
          <div class="enjoyDiv enjoyDivScd">
            <img class="img" :src="guaranteeDetailSrc[2]" />
            <div class="enjoyRight">
              <div class="money">百万医疗尊享绿通服务</div>
              <div class="describe">免费赠送100种重大手术绿色通道</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="card">
      <div class="container">
        <div>
          <img
            class="optimization"
            src="@/assets/nonCar/healthInsurance/optimization.png"
            alt
          />
          <span>为您优选</span>
        </div>
        <div class="optimizationImg"></div>
      </div>
    </div>
    <!-- <div class="titleWhite" id="vantab2">
        <h5>理赔说明</h5>
        <p style="margin-bottom:14px;">全程指导，5步快速理赔</p>
        <div class="card">
          <div class="pic">
           <img :src="guaranteeDetailSrc[1]"  class="lipei" />
          </div>
        </div>
    </div>

    <div class="titleWhite" id="vantab3">
      <h5>条款须知</h5>
    </div>
    <div class="clause">
      <div class="blueDiv">
        <div class="spaceWhite"></div>
        <van-cell-group>
          <van-cell title="特別约定" is-link to="/nonCar/clauseFine/Aboutpersonal" />
          <van-cell title="投保须知" value is-link to="/nonCar/clauseFine/InsureNotice" />
        </van-cell-group>
      </div>
    </div>-->
  </div>
</template>
<script>
import starEquity from "@/components/starEquity.vue";
import { businessPlan } from "@src/http/module/nonCar.js";
export default {
  components: {
    starEquity
  },
  data() {
    return {
      healthTellFamilyValue: false,
      flagValue: false,
      enjoyValue: true,
      checked: "true",
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
        require("@/assets/nonCar/healthInsurance/img5.png")
      ],
      guaranteeDetailSrc: [
        require("@/assets/nonCar/healthInsurance/detail.png"),
        require("@/assets/nonCar/propertyInsurance/bianZhi.png"),
        require("@/assets/nonCar/propertyInsurance/enjoy.png")
      ]
    };
  },
  created() {
    if (!this.$route.query.certificateNo || this.$route.query.certificateType) {
      this.healthTellFamilyValue = true;
    }
    let params = {
      certificateNo: this.$route.query.certificateNo,
      certificateType: this.$route.query.certificateType,
      custName: this.$route.query.insuredName,
      noCarFlag: "1",
      comCode: localStorage.getItem("comCode")
    };
    businessPlan(params).then(res => {
      if (res.data) {
        this.healthTellFamilyValue = false;
      } else {
        this.healthTellFamilyValue = true;
      }
    });
  },
  mounted() {
    if (this.$route.query.routerName == undefined) {
      this.$route.query.routerName = false;
    } else {
      this.healthTellFamilyValue = true;
    }
  }
};
</script>
<style lang="less" scoped>
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
  background: url("../../../../assets/nonCar/healthInsurance/youOptimization.png")
    no-repeat;
  background-size: 312px 152px;
}
.details {
  width: 311px;
  height: 158px;
  margin: 0 auto 26px;
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
    background: url("../../../../assets/nonCar/prospectus/outpatientDepartment.png");
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
      background: url("../../../../assets/nonCar/prospectus/hospitalization.png");
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
      background: url("../../../../assets/nonCar/prospectus/medical.png");
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
.enjoyContainer {
  padding: 20px 10px;
  div {
    padding-left: 5px;
  }
  .enjoy {
    font-size: 15px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 21px;
    margin-bottom: 30px;
  }
  .enjoyDiv {
    width: 319px;
    height: 80px;
    background: rgba(246, 246, 246, 1);
    border-radius: 2px;
    margin-left: 3px;
    padding-top: 11px;
    padding-left: 10px;
    margin: 10px 0;
    .img {
      width: 28px;
      height: 28px;
      float: left;
      margin-right: 11px;
    }
    .enjoyRight {
      float: left;
      width: 260px;
      .money {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        line-height: 18px;
        padding-bottom: 6px;
      }
      .describe {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
        line-height: 18px;
      }
    }
  }
  .enjoyDivScd {
    height: 64px;
  }
}
.titleWhite {
  text-align: center;
  padding: 15px 0 5px 0;
  background: url("../../../../assets/common/icon8.png");
  background-repeat: no-repeat;
  background-position: 145px 10px;
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
  height: 480px;
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
      margin-bottom: 3px;
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
  .lipei {
    width: 100%;
    height: 336px;
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
  background-image: url(../../../../assets/nonCar/propertyInsurance/titleBack.png);
  background-repeat: no-repeat;
  background-size: 100% auto;
}

.clause {
  width: 355px;
  height: 120px;
  background: rgba(255, 255, 255, 1);
  border-radius: 8px 0px 8px 8px;
  margin: 15px auto 0;
  position: relative;
  .blueDiv {
    width: 355px;
    height: 18px;
    background: linear-gradient(
      360deg,
      rgba(44, 121, 254, 1) 0%,
      rgba(68, 170, 248, 1) 30%,
      rgba(44, 120, 255, 1) 100%
    );
    border-radius: 9px;
    position: absolute;
    top: -7px;
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
/deep/.van-checkbox__icon--disabled .van-icon {
  background: #1989fa;
}
/deep/.van-checkbox__icon--disabled.van-checkbox__icon--checked .van-icon {
  color: #ffffff;
}
</style>
