<template>
  <div class="navbar">
    <div class="popBody">
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>本年度保单基本信息</h3>
          </div>
          <template>
            <div v-if="insureObj.querySequenceNo">
              <div class="rowQu">
                <div>投保查询码</div>
                <div>{{ insureObj.querySequenceNo }}</div>
              </div>
              <div class="rowQu">
                <div>查询码有效止期</div>
                <div>{{ insureObj.queryPastDate }}</div>
              </div>
            </div>
            <div v-else class="containerFlex">
              <div class="rowQu">
                <div>投保查询码</div>
                <div>{{ insureObj.querySequenceNo }}</div>
              </div>
              <div class="rowQu">
                <div>查询码有效止期</div>
                <div>{{ insureObj.queryPastDate }}</div>
              </div>
            </div>
          </template>
          <!-- <div class="containerFlex"> -->
          <div class="rowQu">
            <div>转保业务标志</div>
            <div>{{ insureObj.renewalFlag }}</div>
          </div>
          <div class="rowQu">
            <div>整单优惠系数</div>
            <div>
              {{
                insureObj.discountCoefficientRate == "0"
                  ? "1"
                  : insureObj.discountCoefficientRate
              }}
            </div>
          </div>
          <!-- </div> -->
          <!-- <div class="containerFlex"> -->
          <div class="rowQu">
            <div>无赔款优待系数</div>
            <div>{{ insureObj.ncdRate == "0" ? "1" : insureObj.ncdRate }}</div>
          </div>
          <div class="rowQu">
            <div>浮动原因</div>
            <div>
              {{ insureObj.ncdRateFloatCauseCode
              }}{{ insureObj.ncdRateFloatCauseDesc }}
            </div>
          </div>
          <!-- </div> -->
          <!-- <div class="containerFlex"> -->
          <div class="rowQu">
            <div>交通违法系数</div>
            <div>
              {{
                insureObj.trafficViolationRate == "0"
                  ? "1"
                  : insureObj.trafficViolationRate
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>浮动原因</div>
            <div>
              {{ insureObj.trafficViolationRateFloatCauseCode
              }}{{ insureObj.trafficViolationRateFloatCauseDesc }}
            </div>
          </div>
          <!-- </div> -->
        </div>
      </div>
      <div class="backgroud"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>上年度保单基本信息</h3>
          </div>
          <div class="rowQu">
            <div>投保查询日期</div>
            <div>{{ insureObj.lastPolicyQueryDate }}</div>
          </div>
          <div class="rowQu">
            <div>起保日期</div>
            <div>{{ insureObj.lastEffectiveDate }}</div>
          </div>
          <div class="rowQu">
            <div>终保日期</div>
            <div>{{ insureObj.lastExpireDate }}</div>
          </div>
          <div class="rowQu">
            <div>签单日期</div>
            <div>{{ insureObj.lastPolicyBillDate }}</div>
          </div>
          <div class="rowQu">
            <div>最新总保费</div>
            <div>{{ insureObj.lastPolicyTotalPremium }}</div>
          </div>
          <div class="rowQu">
            <div>销售渠道代码</div>
            <div>{{ insureObj.lastProducerCode }}</div>
          </div>
        </div>
      </div>
      <div class="backgroud"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>重复投保提示</h3>
          </div>
          <div class="rowQu">
            <div>重复投保单号</div>
            <div>{{ insureObj.policyNo }}</div>
          </div>
          <div class="rowQu">
            <div>保险公司代码</div>
            <div>{{ insureObj.insurerCode }}</div>
          </div>
          <div class="rowQu long-item">
            <div>险种信息</div>
            <div>{{ insureObj.coverageItemList }}</div>
          </div>
          <div class="rowQu">
            <div>起保日期</div>
            <div>{{ insureObj.effectiveDate }}</div>
          </div>
          <div class="rowQu">
            <div>终保日期</div>
            <div>{{ insureObj.expireDate }}</div>
          </div>
          <div class="rowQu">
            <div>承保地区</div>
            <div>{{ insureObj.insurerArea }}</div>
          </div>
          <div class="rowQu">
            <div>重复投保公司种类</div>
            <div>{{ insureObj.insurerCompanyType }}</div>
          </div>
        </div>
      </div>
      <div class="backgroud"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>违法信息</h3>
          </div>
          <div class="rowQu">
            <div>违法编号</div>
            <div>{{ insureObj.violationCode }}</div>
          </div>
          <div class="rowQu">
            <div>违法行为</div>
            <div>{{ insureObj.violationRecordTypeCode }}</div>
          </div>
          <div class="rowQu">
            <div>决定书编号</div>
            <div>{{ insureObj.decisionCode }}</div>
          </div>
          <div class="rowQu">
            <div>违法时间</div>
            <div>{{ insureObj.violationTime }}</div>
          </div>
          <div class="rowQu">
            <div>违法地点</div>
            <div>{{ insureObj.violationPlace }}</div>
          </div>
          <div class="rowQu">
            <div>违法处理时间</div>
            <div>{{ insureObj.recognitionDate }}</div>
          </div>
          <div class="rowQu">
            <div>违法处理机关代码</div>
            <div>{{ insureObj.jurisdictionAgencyCode }}</div>
          </div>
          <div class="rowQu">
            <div>违法行为人姓名</div>
            <div>{{ insureObj.violationRecordTypeName }}</div>
          </div>
          <div class="rowQu">
            <div>凭证代码</div>
            <div>{{ insureObj.vouchCode }}</div>
          </div>
          <div class="rowQu">
            <div>处罚决定人</div>
            <div>{{ insureObj.sanctionPerson }}</div>
          </div>
          <div class="rowQu">
            <div>处罚决定时间</div>
            <div>{{ insureObj.sanctionDate }}</div>
          </div>
          <div class="rowQu">
            <div>行政区划代码</div>
            <div>{{ insureObj.administrationArea }}</div>
          </div>
          <div class="rowQu">
            <div>违法类型</div>
            <div>{{ insureObj.violationType }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    insureObj: Object //传入的数组
  }
};
</script>
<style lang="less" scoped>
.backgroud {
  width: 100%;
  height: 10px;
  background: rgba(245, 245, 245, 1);
}
.popBody {
  margin-top: 2rem;
  background: #efeff4;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  .containerFlex {
    display: flex;
    justify-content: flex-start;
    align-content: flex-start;
    justify-content: space-between;
  }
  .card {
    background-color: #fff;
    // padding: 0.2rem;
    // margin-bottom: 0.5rem;
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      padding: 0.1rem 0;
    }
  }
}
.rowQu {
  display: flex;
  flex: 2;
  background-color: #fff;
  padding: 0.2rem 15px;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    text-align: right;
  }
}
.long-item {
  div:nth-child(2) {
    height: auto;
    max-width: 200px;
  }
}
.rowQu {
  font-size: 14px;
}
</style>
