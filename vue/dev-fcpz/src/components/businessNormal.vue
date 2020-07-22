<template>
  <div class="cover">
    <!-- 基本信息 -->
    <div class="personInfo">
      <div class="headImg">
        <img :src="personInfo.imgUrl || defaultHead" alt="" />
      </div>
      <div class="basicInfo">
        <p class="commonText18">{{ personInfo.name || "暂无" }}</p>
        <div class="commonText14 phoneInfo">
          <img
            class="icon11"
            src="../assets/vehicle/businessPlan/mobile.png"
            alt=""
          />
          <span>{{ personInfo.phone | phoneCryptograph }}</span>
        </div>
      </div>
      <div class="carInfo">
        <div :class="['commonText14', 'commonText20']">
          {{ personInfo.licenseNo || "未上牌" }}
        </div>
        <div class="commonText14">
          {{ personInfo.vehicleBrand | cryptograph }}
        </div>
      </div>
    </div>
    <!-- 报价方案 -->
    <div
      class="item"
      v-if="
        (quotationObjInfo.businessChecked == '1' ||
          quotationObjInfo.trafficChecked == '1') &&
          quoteInfoVisible
      "
    >
      <div class="topLogo">
        <img src="../assets/vehicle/businessPlan/topLogo.png" alt="" />
      </div>
      <div style="display:flex;flex-direction: row;">
        <van-checkbox
          v-if="!share"
          v-model="quoteInfoChecked"
          @change="changeChecked"
          icon-size="16px"
          style="margin-right:8px;"
        />
        <span class="commonText14 commonText15">报价方案</span>
      </div>
      <!-- 交强险保期 -->
      <div class="insideItem" v-if="quotationObjInfo.trafficChecked == '1'">
        <!-- <div class="insideItem"> -->
        <p class="tipInfo">
          <span class="commonText14">交强险保期</span>
          <span class="commonText11">
            {{ quotationObjInfo.trafficEffectiveStartDate | insuranceDate }}
            -
            {{ quotationObjInfo.trafficEffectiveEndDate | insuranceEndDate }}
          </span>
        </p>
        <div class="content">
          <p class="word mb8">
            <span class="commonText12 color8E8E8E">险种</span>
            <span class="commonText12 color8E8E8E">保额</span>
            <span class="commonText12 color8E8E8E">保费(元)</span>
          </p>
          <p class="word">
            <span class="commonText14 color545454">交强险</span>
            <span class="commonText14 color545454">122000</span>
            <span class="commonText14 color545454">
              {{ quotationObjInfo.trafficDuePremium }}
            </span>
          </p>
          <p class="word">
            <span class="commonText14 color545454">车船税</span>
            <span class="commonText14 color545454"></span>
            <span class="commonText14 color545454">
              {{ quotationObjInfo.tax.sumPayment }}
            </span>
          </p>
          <div class="devideLine" />
          <p class="word">
            <span class="commonText14 color545454">交强险+车船税合计</span>
            <span class="commonText14 color3B8AFE actColor">
              {{
                quotationObjInfo.trafficDuePremium +
                  quotationObjInfo.tax.sumPayment
              }}
            </span>
          </p>
        </div>
      </div>
      <!-- 商业险保期 -->
      <div class="insideItem" v-if="quotationObjInfo.businessChecked == '1'">
        <!-- <div class="insideItem"> -->
        <p class="tipInfo">
          <span class="commonText14">商业险保期</span>
          <span class="commonText11">
            {{ quotationObjInfo.businessEffectiveStartDate | insuranceDate }}
            -
            {{ quotationObjInfo.businessEffectiveEndDate | insuranceEndDate }}
          </span>
        </p>
        <div class="content">
          <p class="word mb8">
            <span class="commonText12 color8E8E8E flexTit">险种</span>
            <span class="commonText12 color8E8E8E">保额(元)</span>
            <span class="commonText12 color8E8E8E">保费(元)</span>
          </p>
          <div
            v-for="(subItem, idx) in quotationObjInfo.businessRisks"
            :key="idx"
          >
            <p class="word">
              <span class="commonText14 color545454 flexTit">
                {{ subItem.kindName | kindName }}
              </span>
              <span class="commonText14 color545454">{{ subItem.amount }}</span>
              <span class="commonText14 color545454">
                {{ subItem.premium }}
              </span>
            </p>
            <p class="word" v-if="subItem.kindFlag == '1'">
              <span class="commonText14 color545454 flexLong">
                {{ subItem.kindName | kindName }}
                (不计免赔)
              </span>
              <!-- <span class="commonText14">{{ subItem.amount }}</span> -->
              <span class="commonText14 color545454">
                {{ subItem.nonDeductiblePremium }}
              </span>
            </p>
          </div>
          <div class="devideLine" />
          <div class="word">
            <span class="commonText14 color545454">商业险合计</span>
            <div class="costDetail">
              <!-- <p class="commonText14 commonText12 color5374A7 mb6">
                整单优惠
                {{ discountCoefficient }}
                %（
                {{ (discountCoefficient / 100).toFixed(2) }}
                ）
              </p> -->
              <p class="priceCheckout">
                <span class="commonText14 color929292 mr12 decorationLine">
                  {{ quotationObjInfo.businessStdPremium }}
                </span>
                <span class="commonText14 color3B8AFE">
                  {{ quotationObjInfo.businessDuePremium }}
                </span>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="insideItem" v-if="quotationObjInfo.wealthAccChecked == '1'">
        <!-- <div class="insideItem"> -->
        <p class="tipInfo">
          <span class="commonText14">联合销售</span>
        </p>
        <div class="content" v-if="quotationObjInfo.wealthAccChecked == '1'">
          <p class="word mb8">
            <span class="commonText12 color8E8E8E">险种</span>
            <span class="commonText12 color8E8E8E">方案</span>
            <span class="commonText12 color8E8E8E">份数</span>
            <span class="commonText12 color8E8E8E">保费(元)</span>
          </p>
          <p class="word">
            <span class="commonText14 color545454">
              {{ quotationObjInfo.wealthAccFormulaGroupName }}
            </span>
            <span class="commonText14 color545454">
              {{ quotationObjInfo.wealthAccFormulaName }}
            </span>
            <span class="commonText14 color545454">
              {{ quotationObjInfo.wealthAccBuyNum }}
            </span>
            <span class="commonText14 color545454">
              {{ quotationObjInfo.wealthAccPremium }}
            </span>
          </p>
          <div class="devideLine" />
          <p class="word">
            <span class="commonText14 color545454">合计</span>
            <span class="commonText14 color3B8AFE actColor">
              {{ quotationObjInfo.wealthAccPremium }}
            </span>
          </p>
        </div>
      </div>
      <!-- 合计保费(元) -->
      <p class="outTotalPrice">
        <span class="commonText14" style="font-weight:600;">合计保费(元)</span>
        <span class="commonText14 commonText16 colorFC820E">
          {{ quotationObjInfo.totalPremium }}
        </span>
      </p>
    </div>
    <!-- 上年理赔信息 -->
    <div class="item" v-if="Array.isArray(claimInfo) && claimInfo.length && claimInfoVisible">
      <div style="display:flex;flex-direction: row;">
        <van-checkbox
          v-if="!share"
          icon-size="16px"
          v-model="claimInfoChecked"
          @change="changeChecked"
          style="margin-right:8px;"
        />
        <span class="commonText14 commonText15">上年理赔信息</span>
      </div>
      <div class="insideItem">
        <div class="content">
          <p class="word mb9">
            <span class="commonText12 flex25 color8E8E8E">理赔险种类型</span>
            <span class="commonText12 flex20 color8E8E8E">理赔金额</span>
            <span class="commonText12 flex20 color8E8E8E">出险日期</span>
            <span class="commonText12 flex20 color8E8E8E">结案时间</span>
          </p>
          <p class="word" v-for="(item, index) in claimInfo" :key="index">
            <span class="commonText12 commonText13 color484848 flex25">
              {{ item.claimType }}
            </span>
            <span class="commonText12 commonText13 color484848 flex20">
              {{ item.claimAmount }}
            </span>
            <span class="commonText12 commonText13 color484848 flex20">
              {{ item.riskDate }}
            </span>
            <span class="commonText12 commonText13 color484848 flex20">
              {{ item.closeDate }}
            </span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import _ from "lodash";
export default {
  props: {
    personInfo: {
      type: Object,
      default: () => {}
    },
    quotationObjInfo: {
      type: Object,
      default: () => {}
    },
    claimInfo: {
      // 理赔信息
      type: Array,
      default: () => []
    },
    totalPayment: {
      type: [Number, String],
      default: 0
    },
    share: {
      type: Boolean,
      default: false
    },
    quoteInfoVisible: {
      type: Boolean,
      default: true
    },
    claimInfoVisible: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      quoteInfoChecked: true,
      claimInfoChecked: true,
      defaultHead: require("@/assets/vehicle/businessPlan/defaultHead.png")
    };
  },
  filters: {
    phoneCryptograph(v) {
      if (!v || typeof v != "string" || !v.hasOwnProperty("length")) return "";
      return v.substr(0, 3) + "****" + v.substr(-4, 4);
    },
    cryptograph(v) {
      if (!v || typeof v != "string" || !v.hasOwnProperty("length")) return "";
      return v.substr(0, 2) + "***" + v.substr(-3, 3);
    }
  },
  computed: {
    discountCoefficient() {
      let discountCoefficient = 0;
      if (_.result(this.quotationObjInfo, "discountCoefficient", "")) {
        discountCoefficient = _.result(
          this.quotationObjInfo,
          "discountCoefficient",
          ""
        );
      }
      // else {
      //   if (this.quotationObjInfo.businessChecked == "1") {
      //     discountCoefficient = Number.parseInt(
      //       ((this.quotationObjInfo.businessStdPremium -
      //         this.quotationObjInfo.businessDuePremium) /
      //         this.quotationObjInfo.businessStdPremium) *
      //         100
      //     );
      //   }
      // }
      return discountCoefficient;
    }
  },
  methods: {
    changeChecked() {
      this.$emit("change", {
        quoteInfoChecked: this.quoteInfoChecked,
        claimInfoChecked: this.claimInfoChecked
      });
    }
  }
};
</script>
<style lang="less" scoped>
// 通用
// decorationLine
.decorationLine {
  text-decoration: line-through;
  text-decoration-color: #929292;
}
// 字体
.commonText18 {
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(11, 36, 95, 1);
  line-height: 25px;
}
.commonText20 {
  font-size: 20px !important;
  line-height: 28px !important;
}
.commonText12 {
  font-size: 12px !important;
  line-height: 17px !important;
}
.commonText13 {
  font-size: 13px !important;
  line-height: 18px !important;
}
.commonText15 {
  font-size: 15px !important;
  line-height: 21px !important;
}
.commonText14 {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
.commonText11 {
  font-size: 11px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 16px;
}
.commonText16 {
  font-size: 16px;
  font-weight: 600;
  line-height: 22px;
}
// 图标
.icon11 {
  width: 11px;
  height: 11px;
}
.icon14 {
  width: 14px;
  height: 11px;
}
// 间距
.mb8 {
  margin-bottom: 8px;
}
.mb6 {
  margin-bottom: 6px;
}
.mr12 {
  margin-right: 12px;
}
.mb9 {
  margin-bottom: 9px;
}
// 颜色
.actColor {
  font-weight: 500;
  color: rgba(59, 138, 254, 1);
}
.color5374A7 {
  color: #5374a7;
}
.color333333 {
  color: #333333;
}
.color484848 {
  color: #484848;
}
.color545454 {
  color: #545454;
}
.color999999 {
  color: #999999;
}
.color929292 {
  color: #929292;
}
.color3B8AFE {
  color: #3b8afe;
  font-weight: 500;
}
.colorFC820E {
  color: #fc820e;
}
.color8E8E8E {
  color: #8e8e8e;
}
.phoneInfo {
  display: flex;
  flex-direction: row;
  align-items: center;
}
// 通用
.cover {
  // background: linear-gradient(
  //   180deg,
  //   rgba(191, 223, 255, 1) 0%,
  //   rgba(228, 241, 255, 1) 100%
  // );
  padding: 0 11px;
  overflow: visible;
  .personInfo {
    // margin-top: -70px;
    margin-bottom: 13px;
    background: linear-gradient(
      71deg,
      rgba(223, 239, 255, 1) 0%,
      rgba(255, 255, 255, 1) 100%
    );
    box-shadow: 0px 1px 15px 0px rgba(150, 176, 204, 0.23);
    border-radius: 10px;
    padding: 20px 15px;
    display: flex;
    flex-direction: row;
    align-items: center;
    .headImg {
      width: 45px;
      height: 45px;
      border-radius: 50%;
      margin-right: 17px;
      img {
        width: 100%;
      }
    }
    .basicInfo {
      height: 45px;
      display: flex;
      flex: 1;
      flex-direction: column;
      justify-content: space-between;
    }
    .carInfo {
      margin-top: -4px;
      height: 49px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
  }
  .item {
    position: relative;
    padding: 18px;
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 1px 15px 0px rgba(150, 176, 204, 0.23),
      0px 1px 15px 0px rgba(197, 212, 228, 0.55);
    border-radius: 10px;
    margin-bottom: 10px;
    .topLogo {
      width: 160px;
      height: 63px;
      position: absolute;
      right: -12px;
      top: -16px;
      img {
        width: 100%;
      }
    }
    .insideItem {
      margin-top: 15px;
      .tipInfo {
        margin-bottom: 13px;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .content {
        background: rgba(249, 249, 249, 1);
        border-radius: 2px;
        padding: 6px 16px 8px;
        .word {
          display: flex;
          justify-content: space-between;
          align-items: flex-end;
          span {
            flex: 1;
            text-align: center;
            line-height: 24px;
            margin-bottom: 2px;
          }
          span:first-child {
            text-align: left;
          }
          span:last-child {
            text-align: right;
          }
          .flexTit {
            flex: 2.5;
          }
          .flexLong {
            flex: 3;
          }
          .flex25 {
            width: 26%;
            text-align: center;
          }
          .flex20 {
            width: 20%;
            text-align: center;
          }
        }
        .devideLine {
          border-top: 1px solid #e9e9e9;
          margin: 9px 0;
        }
      }
    }
    .outTotalPrice {
      margin-top: 12px;
      padding: 0 15px;
      display: flex;
      justify-content: space-between;
    }
  }
}
.priceCheckout {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
