<template>
  <div class="navbar">
    <div class="popBody">
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>基本信息</h3>
          </div>
          <template>
            <div v-if="compulsoryObj.querySequenceNo">
              <div class="rowQu">
                <div>投保查询码</div>
                <div>{{ compulsoryObj.querySequenceNo }}</div>
              </div>
              <div class="rowQu">
                <div>转保业务标志</div>
                <div>{{ compulsoryObj.renewalFlag }}</div>
              </div>
            </div>
            <div v-else class="containerFlex">
              <div class="rowQu">
                <div>投保查询码</div>
                <div>{{ compulsoryObj.querySequenceNo }}</div>
              </div>
              <div class="rowQu">
                <div>转保业务标志</div>
                <div>{{ compulsoryObj.renewalFlag }}</div>
              </div>
            </div>
          </template>

          <!-- <div class="containerFlex"> -->
          <div class="rowQu">
            <div>违法调整系数</div>
            <div>{{ compulsoryObj.trafficViolationRate=="0"?"1":compulsoryObj.trafficViolationRate }}</div>
          </div>
          <div class="rowQu">
            <div>浮动原因</div>
            <div>{{ compulsoryObj.trafficViolationRateFloatCauseCode }}{{compulsoryObj.trafficViolationRateFloatCauseDesc}}</div>
          </div>
          <!-- </div> -->
          <!-- <div class="containerFlex"> -->
          <div class="rowQu">
            <div>理赔调整系数</div>
            <div>{{ compulsoryObj.claimRate=="0"?"1":compulsoryObj.claimRate }}</div>
          </div>
          <div class="rowQu">
            <div>浮动原因</div>
            <div>{{ compulsoryObj.claimRateFloatCode }}{{compulsoryObj.claimRateFloatDesc}}</div>
          </div>
          <!-- </div> -->
        </div>
      </div>
      <div class="card">
        <div>
          <div class="rowQu">
            <h3>违法信息</h3>
          </div>
          <div class="rowQu">
            <div>违法编号</div>
            <div>{{ compulsoryObj.peccancyId }}</div>
          </div>
          <div class="rowQu">
            <div>违法行为</div>
            <div>{{ compulsoryObj.peccancyCode }}</div>
          </div>
          <div class="rowQu">
            <div>决定书编号</div>
            <div>{{ compulsoryObj.decisionId }}</div>
          </div>
          <div class="rowQu">
            <div>决定书类型</div>
            <div>{{ compulsoryObj.decisionType }}</div>
          </div>
          <div class="rowQu">
            <div>违法时间</div>
            <div>{{ compulsoryObj.peccancTime }}</div>
          </div>
          <div class="rowQu">
            <div>违法地点</div>
            <div>{{ compulsoryObj.peccancyPlace }}</div>
          </div>
          <div class="rowQu">
            <div>违法处理时间</div>
            <div>{{ compulsoryObj.manageTime }}</div>
          </div>
          <div class="rowQu">
            <div>调整系数</div>
            <div>{{ compulsoryObj.trafficViolationRate=="0"?"1":compulsoryObj.trafficViolationRate }}</div>
          </div>
          <div class="rowQu">
            <div>违法驾驶员驾驶证号</div>
            <div>{{ compulsoryObj.drivingLicence }}</div>
          </div>
          <div class="rowQu">
            <div>处理状态</div>
            <div>{{ compulsoryObj.processingStatus == "1" ? "已处理" : "未处理" }}</div>
          </div>
          <div v-if="addressFlag == '5201'" class="rowQu">
            <div>违法驾驶员姓名</div>
            <div>{{ compulsoryObj.driverName }}</div>
          </div>
          <div v-if="addressFlag == '5201'" class="rowQu">
            <div>违法行为内容</div>
            <div>{{ compulsoryObj.peccancyDetail }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    compulsoryObj: Object //传入的数组
  },
  data() {
    return {
      addressFlag: ""
    };
  },
  created() {
    this.addressFlag = localStorage.getItem("comCode").slice(0, 4);
  }
};
</script>
<style lang="less" scoped>
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
    margin-bottom: 10px;
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
  flex: 1;
  background-color: #fff;
  padding: 0.2rem 15px;
  div:nth-of-type(1) {
    flex: 3;
  }
  div:nth-of-type(2) {
    flex: 5;
    text-align: right;
  }
}
.rowQu {
  font-size: 14px;
}
</style>
