<!--
 * @Author: heweiwei
 * @Page: 平台信息
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-06-29 11:38:19
 * @FilePath: /src/views/vehicle/selectProduct/floatingCoefficient.vue
-->
<template>
  <div class="floatingCoefficient">
    <van-nav-bar title="平台信息" left-arrow @click-left="$router.back()">
      <template #left>
        <van-icon name="arrow-left" size="18" color="#ccc" />
      </template>
    </van-nav-bar>
    <div class="content">
      <div class="tabs-container">
        <!-- 商业险 -->
        <div class="tab-container" @click="tabChange('商业险')">
          <div
            :class="[
              'tab-title',
              tabActive == '商业险' ? 'tab-active-title' : ''
            ]"
          >
            商业险
          </div>
          <div
            :class="[
              'tab-line',
              tabActive == '商业险' ? 'tab-active-line' : ''
            ]"
          ></div>
        </div>
        <!-- 交强险 -->
        <div class="tab-container" @click="tabChange('交强险')">
          <div
            :class="[
              'tab-title',
              tabActive == '交强险' ? 'tab-active-title' : ''
            ]"
          >
            交强险
          </div>
          <div
            :class="[
              'tab-line',
              tabActive == '交强险' ? 'tab-active-line' : ''
            ]"
          ></div>
        </div>
      </div>
      <!-- 商业险 -->
      <div v-if="tabActive == '商业险'">
        <!-- 本年度保单基本信息 -->
        <div class="card">
          <div class="group">本年度保单基本信息</div>
          <div class="cell">
            <div>投保查询码</div>
            <div>{{ insureObj.querySequenceNo }}</div>
          </div>
          <div class="cell">
            <div>查询码有效止期</div>
            <div>{{ insureObj.queryPastDate }}</div>
          </div>
          <div class="cell">
            <div>转保业务标志</div>
            <div>{{ insureObj.renewalFlag == "0" ? "否" : "是" }}</div>
          </div>
          <div class="cell">
            <div>整单优惠系数</div>
            <div>{{ insureObj.discountCoefficientRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>自主核保系数</div>
            <div>{{ insureObj.underwritingRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>自主渠道系数</div>
            <div>{{ insureObj.channelRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>无赔款优待系数</div>
            <div>{{ insureObj.ncdRate | ncdRateValue }}</div>
          </div>
          <div class="cell">
            <div>浮动原因</div>
            <div>{{ insureObj.ncdRateFloatCauseDesc }}</div>
          </div>
          <div class="cell">
            <div>不浮动原因</div>
            <div>{{ insureObj.ncdRateNonFloatCauseDesc }}</div>
          </div>
          <div class="cell">
            <div>交通违法系数</div>
            <div>{{ insureObj.trafficViolationRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>浮动原因</div>
            <div>{{ insureObj.trafficViolationRateFloatCauseDesc }}</div>
          </div>
          <div class="cell">
            <div>不浮动原因</div>
            <div>{{ insureObj.trafficViolationRateNonFloatCauseDesc }}</div>
          </div>
        </div>
        <!-- 上年度保单基本信息 -->
        <div class="card">
          <div class="group">上年度保单基本信息</div>
          <div class="cell">
            <div>投保查询日期</div>
            <div>{{ insureObj.lastPolicyQueryDate }}</div>
          </div>
          <div class="cell">
            <div>起保日期</div>
            <div>{{ insureObj.lastEffectiveDate }}</div>
          </div>
          <div class="cell">
            <div>终保日期</div>
            <div>{{ insureObj.lastExpireDate }}</div>
          </div>
          <div class="cell">
            <div>签单日期</div>
            <div>{{ insureObj.lastPolicyBillDate }}</div>
          </div>
          <div class="cell">
            <div>最新总保费</div>
            <div>{{ insureObj.lastPolicyTotalPremium }}</div>
          </div>
          <div class="cell">
            <div>销售渠道代码</div>
            <div>{{ insureObj.lastProducerCode }}</div>
          </div>
        </div>
        <!-- 重复投保提示 -->
        <div class="card">
          <div class="group">重复投保提示</div>
          <div class="cell">
            <div>重复投保单号</div>
            <div>{{ insureObj.policyNo }}</div>
          </div>
          <div class="cell">
            <div>保险公司代码</div>
            <div>{{ insureObj.insurerCode }}</div>
          </div>
          <div class="cell long-info">
            <div>险种信息</div>
            <div>{{ insureObj.coverageItemList }}</div>
          </div>
          <div class="cell">
            <div>起保日期</div>
            <div>{{ insureObj.effectiveDate }}</div>
          </div>
          <div class="cell">
            <div>终保日期</div>
            <div>{{ insureObj.expireDate }}</div>
          </div>
          <div class="cell">
            <div>承保地区</div>
            <div>{{ insureObj.insurerArea }}</div>
          </div>
          <div class="cell">
            <div>重复投保公司种类</div>
            <div>{{ insureObj.insurerCompanyType }}</div>
          </div>
        </div>
        <!-- 违法信息 -->
        <div class="card">
          <div class="group">
            {{ (insureObj.violationList.length ? "" : "无") + "违法信息" }}
          </div>
          <van-tabs color="#3b8afe" v-if="insureObj.violationList.length">
            <van-tab
              v-for="(item, index) in insureObj.violationList"
              :key="index"
              :title="'违法信息' + (index + 1)"
            >
              <div class="cell">
                <div>违法编号</div>
                <div>{{ item.violationCode }}</div>
              </div>
              <div class="cell">
                <div>违法行为</div>
                <div>{{ item.violationRecordTypeCode }}</div>
              </div>
              <div class="cell">
                <div>决定书编号</div>
                <div>{{ item.decisionCode }}</div>
              </div>
              <div class="cell">
                <div>违法时间</div>
                <div>{{ item.violationTime }}</div>
              </div>
              <div class="cell">
                <div>违法地点</div>
                <div>{{ item.violationPlace }}</div>
              </div>
              <div class="cell">
                <div>违法处理时间</div>
                <div>{{ item.recognitionDate }}</div>
              </div>
              <div class="cell">
                <div>违法处理机关代码</div>
                <div>{{ item.jurisdictionAgencyCode }}</div>
              </div>
              <div class="cell">
                <div>违法行为人姓名</div>
                <div>{{ item.violationRecordTypeName }}</div>
              </div>
              <div class="cell">
                <div>凭证代码</div>
                <div>{{ item.vouchCode }}</div>
              </div>
              <div class="cell">
                <div>处罚决定人</div>
                <div>{{ item.sanctionPerson }}</div>
              </div>
              <div class="cell">
                <div>处罚决定时间</div>
                <div>{{ item.sanctionDate }}</div>
              </div>
              <div class="cell">
                <div>行政区划代码</div>
                <div>{{ item.administrationArea }}</div>
              </div>
              <div class="cell">
                <div>违法类型</div>
                <div>{{ item.violationType }}</div>
              </div>
            </van-tab>
          </van-tabs>
        </div>
      </div>
      <!-- 交强险 -->
      <div v-if="tabActive == '交强险'">
        <!-- 基本信息 -->
        <div class="card">
          <div class="group">基本信息</div>
          <div class="cell">
            <div>投保查询码</div>
            <div>{{ compulsoryObj.querySequenceNo }}</div>
          </div>
          <div class="cell">
            <div>转保业务标志</div>
            <div>{{ compulsoryObj.renewalFlag == "0" ? "否" : "是" }}</div>
          </div>
          <div class="cell">
            <div>违法调整系数</div>
            <div>{{ compulsoryObj.trafficViolationRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>浮动原因</div>
            <div>{{ compulsoryObj.trafficViolationRateFloatCauseDesc }}</div>
          </div>
          <div class="cell">
            <div>不浮动原因</div>
            <div>{{ compulsoryObj.trafficViolationRateNonFloatCauseDesc }}</div>
          </div>
          <div class="cell">
            <div>理赔调整系数</div>
            <div>{{ compulsoryObj.claimRate | rateValue }}</div>
          </div>
          <div class="cell">
            <div>浮动原因</div>
            <div>{{ compulsoryObj.claimRateFloatDesc }}</div>
          </div>
          <div class="cell">
            <div>不浮动原因</div>
            <div>{{ compulsoryObj.claimRateNonFloatDesc }}</div>
          </div>
        </div>
        <!-- 违法信息 -->
        <div class="card">
          <div class="group">
            {{ (compulsoryObj.peccancyList.length ? "" : "无") + "违法信息" }}
          </div>
          <van-tabs color="#3b8afe" v-if="compulsoryObj.peccancyList.length">
            <van-tab
              v-for="(item, index) in compulsoryObj.peccancyList"
              :key="index"
              :title="'违法信息' + (index + 1)"
            >
              <div class="cell">
                <div>违法编号</div>
                <div>{{ item.peccancyId }}</div>
              </div>
              <div class="cell">
                <div>违法行为</div>
                <div>{{ item.peccancyCode }}</div>
              </div>
              <div class="cell">
                <div>决定书编号</div>
                <div>{{ item.decisionId }}</div>
              </div>
              <div class="cell">
                <div>决定书类型</div>
                <div>{{ item.decisionType }}</div>
              </div>
              <div class="cell">
                <div>违法时间</div>
                <div>{{ item.peccancTime }}</div>
              </div>
              <div class="cell">
                <div>违法地点</div>
                <div>{{ item.peccancyPlace }}</div>
              </div>
              <div class="cell">
                <div>违法处理时间</div>
                <div>{{ item.manageTime }}</div>
              </div>
              <div class="cell">
                <div>调整系数</div>
                <div>{{ item.trafficViolationRate }}</div>
              </div>
              <div class="cell">
                <div>违法驾驶员驾驶证号</div>
                <div>{{ item.drivingLicence }}</div>
              </div>
              <div class="cell">
                <div>处理状态</div>
                <div>
                  {{ item.processingStatus == "1" ? "已处理" : "未处理" }}
                </div>
              </div>
              <div v-if="addressFlag == '5201'" class="cell">
                <div>违法驾驶员姓名</div>
                <div>{{ item.driverName }}</div>
              </div>
              <div v-if="addressFlag == '5201'" class="cell">
                <div>违法行为内容</div>
                <div>{{ item.peccancyDetail }}</div>
              </div>
            </van-tab>
          </van-tabs>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  data: function() {
    return {
      tabActive: "商业险"
    };
  },
  filters: {
    rateValue(v) {
      if (v === 0 || v === "0") {
        return "1";
      }
      if (v < 0) {
        return (1 + v).toFixed(2);
      }
      return v;
    },
    // 2020年06月29日11:37:54 @周振平
    // 这个逻辑应该是如果是空或0显示1，负数和正数都要+1，有上浮的情况，看数据核心返回的是浮动的值
    ncdRateValue(v) {
      if (v == 0) {
        return "1";
      } else {
        return (1 + v).toFixed(2);
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      compulsoryObj: ({ trafficPlatformInfo }) => trafficPlatformInfo,
      insureObj: ({ businessPlatformInfo }) => businessPlatformInfo
    }),
    addressFlag() {
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      return comCode.substr(0, 4);
    }
  },
  methods: {
    tabChange(title) {
      this.tabActive = title;
    },
    showTip() {
      this.$dialog.alert({
        message:
          "X<sup>car</sup>指数是根据定价模型得到的车险标准保费下的整单预期赔付率，是对业务质量优劣的区分，指数越高表明业务质量可能越好。"
      });
    }
  }
};
</script>
<style lang="less" scoped>
.floatingCoefficient {
  width: 100%;
  height: 100%;
  overflow: hidden;
  .content {
    height: calc(100% - 77px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    background: #f7f7f7;
    flex-direction: column;
    div {
      display: flex;
      flex-direction: column;
    }
    .tabs-container {
      width: 100%;
      height: 75px;
      background-image: url("../../../assets/vehicle/platbg.png");
      background-size: 100% 75px;
      align-items: center;
      flex-direction: row;
      .tab-container {
        flex-direction: column;
        align-items: center;
        margin: 0 10px 0 20px;
        .tab-title {
          font-size: 14px;
          color: #666666;
        }
        .tab-active-title {
          font-size: 17px;
          font-weight: 500;
          color: black;
        }
        .tab-line {
          width: 28px;
          height: 5px;
          margin-top: 8px;
          background: transparent;
        }
        .tab-active-line {
          border-radius: 2.5px;
          background: #3b8afe;
        }
      }
    }
    .card {
      margin-bottom: 10px;
      background: white;
      padding: 0 20px;
      .group {
        padding: 17px 0 12px;
        font-size: 16px;
        color: #333333;
        font-weight: 600;
      }
      .cell {
        position: relative;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        font-size: 14px;
        padding-bottom: 6px;
        margin-bottom: 6px;
        div:first-child {
          color: #454545;
        }
        div:last-child {
          color: #7a7a7a;
        }
      }
      .cell:not(:last-child)::after {
        position: absolute;
        box-sizing: border-box;
        content: " ";
        pointer-events: none;
        right: 0;
        bottom: 0;
        left: 0;
        border-bottom: 1px solid #ebedf0;
        -webkit-transform: scaleY(0.5);
        transform: scaleY(0.5);
      }
      .long-info {
        height: auto;
        word-wrap: wrap;
        word-break: break-all;
        div:nth-child(2) {
          max-width: 200px;
        }
      }
    }
  }
}
/deep/.van-tabs__wrap {
  margin-bottom: 10px;
}
/deep/.van-tab {
  display: flex;
  flex: 0 1 auto;
  padding-right: 8px;
}
</style>
