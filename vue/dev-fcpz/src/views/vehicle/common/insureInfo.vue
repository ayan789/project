<!--
 * @Author: King
 * @Page: 保单详情
 * @Date: 2020-04-07 13:46:47
 * @Last Modified by: King
 * @Last Modified time: 2020-04-24 11:47:39
 * @FilePath: /src/views/vehicle/common/insureInfo.vue
-->
<template>
  <div>
    <nav-bar type="vehicle" title="订单详情" :left="false" />
    <!-- 页面内容 -->
    <div class="content">
      <!--车辆-->
      <div class="card">
        <van-cell class="item group">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/vehicle.png"
          />
          <div slot="title" class="item-title">车辆信息</div>
        </van-cell>
        <div class="vehicle-info">
          <div class="vehicle-license">{{ carInfo.licenseNo || "未上牌" }}</div>
          <div class="vehicle-brand">
            {{ carInfo.vehicleBrand || "车辆品牌为空" }}
          </div>
        </div>
        <!--车辆展示信息-->
        <div
          v-for="(labelName, key) in showCarInfo"
          :key="key"
          class="info-row"
        >
          <div class="key-col">{{ labelName }}</div>
          <div class="value-col">{{ carInfo[key] }}</div>
        </div>
      </div>
      <!--车主-->
      <div class="card">
        <van-cell class="item group">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/carowner.png"
          />
          <div slot="title" class="item-title">车主信息</div>
        </van-cell>
        <!--车主展示信息-->
        <div
          v-for="(labelName, key) in showOwnerInfo"
          :key="key"
          class="info-row"
        >
          <div class="key-col">{{ labelName }}</div>
          <!--证件类型做映射处理-->
          <div v-if="key == 'identityType'" class="value-col">
            {{ identifyType[carOwnerInfo[key]] }}
          </div>
          <div v-else class="value-col">{{ carOwnerInfo[key] }}</div>
        </div>
      </div>
      <!--投保方案-->
      <div class="card">
        <van-cell class="item group">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/plan.png"
          />
          <div slot="title" class="item-title">投保方案</div>
        </van-cell>
        <!--交强险-->
        <div class="risk-info" v-if="insurePlan.trafficChecked == '1'">
          <div>
            <div class="risk-time-title">交强险保险期限</div>
            <div class="risk-time-value">
              {{ insurePlan.trafficEffectiveStartDate | insuranceDate }}
              -
              {{ insurePlan.trafficEffectiveEndDate | insuranceEndDate }}
            </div>
            <div class="info-col">
              <div class="key-row">
                <div>险种</div>
                <div>保费</div>
              </div>
              <div class="value-row">
                <div>交强险</div>
                <div>{{ insurePlan.trafficDuePremium }}</div>
              </div>
              <div class="value-row">
                <div>车船税</div>
                <div>{{ insurePlan.tax.sumPayment }}</div>
              </div>
            </div>
          </div>
        </div>
        <!--商业险-->
        <div class="risk-info" v-if="insurePlan.businessChecked == '1'">
          <div>
            <div class="risk-time-title">商业险保险期限</div>
            <div class="risk-time-value">
              {{ insurePlan.businessEffectiveStartDate | insuranceDate }}
              -
              {{ insurePlan.businessEffectiveEndDate | insuranceEndDate }}
            </div>
            <div class="info-col">
              <div class="key-row key-row-long">
                <div>险种</div>
                <div>保额(元)</div>
                <div>保费(元)</div>
              </div>
              <div
                v-for="(item, index) in insurePlan.businessRisks"
                :key="index"
              >
                <div class="value-row value-row-long">
                  <div>{{ item.kindName | kindName }}</div>
                  <div>{{ item.amount }}</div>
                  <div>{{ item.premium }}</div>
                </div>
                <div
                  class="value-row value-row-nocenter"
                  v-show="item.kindFlag == '1'"
                >
                  <div>
                    {{ item.kindName | kindName }}
                    (不计免赔)
                  </div>
                  <div>{{ item.nonDeductiblePremium }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--联合销售-->
        <div class="card" v-if="insurePlan.wealthAccChecked == '1' && toShow">
          <div class="risk-info">
            <div class="risk-time-title">联合销售</div>
            <div class="info-col">
              <div class="key-row">
                <div>险种</div>
                <div>方案</div>
                <div>份数</div>
                <div>保费(元)</div>
              </div>
              <div class="value-row">
                <div>{{ insurePlan.wealthAccFormulaGroupName }}</div>
                <div>{{ insurePlan.wealthAccFormulaName }}</div>
                <div>{{ insurePlan.wealthAccBuyNum }}</div>
                <div>{{ insurePlan.wealthAccPremium }}</div>
              </div>
            </div>
          </div>
        </div>
        <div
          v-if="insurePlan.wealthAccChecked == '1'"
          class="unfolding-arrow"
          @click="clickArrow"
        >
          <img :src="arrowImg" />
        </div>
        <van-cell class="item">
          <div slot="title" class="total-premium">
            合计保费（元）
          </div>
          <div class="premium-value">
            {{ totalPayment || "0.00" }}
          </div>
        </van-cell>
      </div>
      <!--特别约定-->
      <div class="card">
        <van-cell class="item group">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/appoint.png"
          />
          <div slot="title" class="item-title">特别约定</div>
        </van-cell>
        <div class="info-col" v-if="engageList.length">
          <div class="key-row">
            <div>特约名称</div>
            <div>特约内容</div>
          </div>
          <div class="value-row" v-for="(item, idx) in engageList" :key="idx">
            <div>{{ item.specialAgreementName }}</div>
            <div>{{ item.specialAgreementName }}</div>
          </div>
        </div>
      </div>
      <!-- 反洗钱 -->
      <div class="card">
        <van-cell class="item">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/anti.png"
          />
          <div slot="title" class="item-title">反洗钱信息</div>
        </van-cell>
      </div>
      <!--星级权益-->
      <div class="card">
        <van-cell class="item group">
          <img
            slot="icon"
            class="item-left-icon"
            src="../../../assets/vehicle/vehicleInfo/rightsinterests.png"
          />
          <div slot="title" class="item-title">星级权益</div>
        </van-cell>
        <!-- new -->
        <div class="customer-info">
          <div class="star-level">
            <div class="stars">
              <img
                v-for="i in Number(starRight.newCustLevel) || 1"
                class="star"
                :key="i"
                src="../../../assets/vehicle/vehicleInfo/star.png"
                alt=""
              />
            </div>
            <div class="customer-text">出单后星级</div>
          </div>
          <div class="customer-integral">
            <span class="integral">{{ starRight.newIntegral }}</span>
            <span class="customer-text">出单后所获得积分</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/navBar.vue";
import { mapState, mapActions } from "vuex";
import config from "@src/common/json/newCoreDataDictionary";
import { getShareInfo } from "@src/http/module/vehicle";

export default {
  name: "quoteShare",
  components: {
    [NavBar.name]: NavBar
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      shareCode: "",
      toShow: false, //联合销售下拉显示
      arrowImg: require("../../../assets/customer/InteractiveRights/down_arrow.png"),
      // 证件类型字典
      identifyType: config.identifyType,
      // 需要展示的车辆信息
      showCarInfo: {
        // licenseNo: "车牌号",
        frameNo: "车架号",
        enrollDate: "初登日期",
        engineNo: "发动机号"
        // vehicleBrand: "厂牌型号"
      },
      // 需要展示的车主信息
      showOwnerInfo: {
        name: "姓名",
        identityType: "证件类型",
        identityNo: "证件号码"
      }
    };
  },
  computed: {
    ...mapState("vehicle", {
      carInfo: state => state.car,
      carOwnerInfo: state => state.carOwner,
      quoteInfo: state => state.quoteInfo,
      insurePlan: state => state.insurancePlan,
      policyHolder: state => state.policyHolder,
      starRight: state => state.rightsInterests,
      engageList: state => state.engageList
    }),
    totalPayment() {
      let total = 0;
      if (this.insurePlan.trafficChecked == "1") {
        if (Number(this.insurePlan.trafficDuePremium)) {
          total += this.insurePlan.trafficDuePremium;
        }
        if (Number(this.insurePlan.tax.sumPayment)) {
          total += this.insurePlan.tax.sumPayment;
        }
      }
      if (this.insurePlan.businessChecked == "1") {
        if (Number(this.insurePlan.businessDuePremium)) {
          total += this.insurePlan.businessDuePremium;
        }
      }
      if (this.insurePlan.wealthAccChecked == "1") {
        if (Number(this.insurePlan.wealthAccPremium)) {
          total += this.insurePlan.wealthAccPremium;
        }
      }

      return total.toFixed(2);
    }
  },
  created() {
    this.shareCode = this.$GetRequest("shareCode");
    if (this.shareCode) {
      this.init();
    }
  },
  methods: {
    ...mapActions("vehicle", ["initWithDetail"]),
    init() {
      getShareInfo({ shareCode: this.shareCode })
        .then(res => {
          if (res.code === "1") {
            this.initWithDetail(res.data);
            this.$nextTick(() => {
              console.log("forceUpdate");
              this.$forceUpdate();
            });
          } else {
            console.log("not 1");
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    clickArrow: function() {
      this.toShow = !this.toShow;
      this.arrowImg =
        this.toShow == false
          ? require("../../../assets/customer/InteractiveRights/down_arrow.png")
          : require("../../../assets//customer/InteractiveRights/up_arrow.png");
    },
    clickBack() {
      this.applicationNo = "";
      this.$router.push({ name: "insureInformation" });
    },
    clickCopy() {},
    pay() {}
  }
};
</script>
<style lang="less" scoped>
* {
  overflow: auto;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.content {
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  background: #f7f7f7;
}

/* 折叠效果 */
.box {
  width: 100%;
}

.draw-enter-active,
.draw-leave-active {
  transition: all 0.3s ease;
}

.draw-enter,
.draw-leave-to {
  /* .fade-leave-active below version 2.1.8 */
  height: 0;
}

.unfolding-arrow {
  height: 38px;
  width: 100%;
  margin-top: 6px;
}

.unfolding-arrow > img {
  display: block;
  width: 20px;
  height: 14px;
  margin: 12px calc(50% - 10px);
}
.dialog {
  .info {
    padding: 10px;
    font-size: 14px;
    color: #333;
    p,
    span {
      white-space: pre-wrap;
    }
  }
}
.content {
  div {
    display: flex;
    flex-direction: column;
  }
  .card {
    background: white;
    margin-bottom: 12px;
  }
  .group {
    margin: 8px 0px 2px;
  }
  .group::after {
    border: 0;
  }
  .group:last-child {
    margin: 0;
  }
  .item {
    height: 48px;
    flex-direction: row;
    align-items: center;
    .item-left-icon {
      width: 22px;
      height: 22px;
      margin-right: 8px;
    }
    .item-title {
      font-size: 16px;
      font-weight: 600;
      color: #333333;
    }
    .item-placeholder {
      color: #ababab;
    }
    .total-premium {
      font-size: 14px;
      font-weight: 500;
      color: #333333;
    }
    .premium-value {
      font-size: 16px;
      font-weight: 600;
      color: #fc820e;
    }
  }
  .info-row {
    flex: 1;
    margin: 0 18px;
    flex-direction: row;
    justify-content: space-between;
    font-size: 14px;
    line-height: 23px;
    .key-col {
      color: #333333;
    }
    .value-col {
      color: #666666;
    }
  }
  .info-row:last-child {
    margin-bottom: 20px;
  }
  .info-col {
    margin: 12px 0;
    padding: 16px;
    background: #fafafa;
    border-radius: 2px;
    justify-content: space-between;
    .key-row {
      flex: 1;
      font-size: 12px;
      line-height: 17px;
      color: #8e8e8e;
      flex-direction: row;
      justify-content: space-between;
      div {
        flex: 1;
        text-align: center;
      }
      div:first-child {
        flex: 1.5;
        text-align: left;
      }
      div:last-child {
        text-align: right;
      }
    }
    .value-row {
      flex: 1;
      font-size: 14px;
      line-height: 23px;
      color: #545454;
      margin-bottom: 5px;
      flex-direction: row;
      justify-content: space-between;
      div {
        flex: 1;
        text-align: center;
      }
      div:first-child {
        flex: 1.5;
        text-align: left;
      }
      div:last-child {
        text-align: right;
      }
    }
    .key-row-long,
    .value-row-long {
      div:first-child {
        flex: 3;
        text-align: left;
      }
    }
    .value-row-nocenter {
      div:first-child {
        flex: 5;
        text-align: left;
      }
    }
  }
  .more-info {
    height: 48px;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    .info-title {
      font-size: 12px;
      color: #4c76b5;
    }
    .arrow {
      margin-left: 8px;
    }
  }
  .risk-info {
    flex: 1;
    margin: 20px;
    margin-top: 0px;
    background: white;
  }
  .risk-info:last-child {
    margin-bottom: 0;
  }
  .risk-time-title {
    font-size: 14px;
    line-height: 20px;
    color: #333333;
  }
  .risk-time-value {
    font-size: 12px;
    line-height: 17px;
    color: #ababab;
  }
  .customer-info {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    margin: 0 20px;
    background: #fafafa;
    border-radius: 2px;
    height: 84px;
    margin-bottom: 20px;
    .star-level {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .stars {
        flex-direction: row;
        justify-content: center;
        margin-bottom: 8px;
        .star {
          width: 20px;
          height: 20px;
        }
      }
    }
    .customer-integral {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .integral {
        font-size: 18px;
        font-weight: 500;
        line-height: 25px;
        color: #3b8afe;
      }
    }
    .customer-text {
    }
  }
  .bottom-info {
    margin-top: 30px;
    margin-bottom: 15px;
    font-size: 16px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    .business-plan-book {
      width: calc(100% - 32px);
      height: 46px;
      border-radius: 23px;
      background: linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%);
      display: flex;
      justify-content: center;
      align-items: center;
      color: white;
    }
    .submit {
      display: flex;
      flex: 1;
      justify-content: center;
      align-items: center;
      background: linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%);
      color: white;
    }
  }
}
.vehicle-info {
  margin: 0 16px 12px;
  border-radius: 10px;
  background: #eef5ff;
  padding: 12px 16px;
  flex-direction: row;
  .vehicle-license {
    font-size: 20px;
    color: black;
    font-weight: 500;
  }
  .vehicle-brand {
    font-size: 14px;
    color: #444444;
  }
}
</style>
