<template>
  <div class="contentStyle">
    <div v-for="(item, index) in tempListMap" class="itemStyle">
      <div
        class="textStyle"
        :style="{ marginTop: index != 0 ? '10px' : '0px' }"
      >
        <span class="textspan">{{ active == 1 ?item.insuranceName:"车险" }}</span>
        <van-icon name="arrow" size="20" @click="toInfo(item,active)"/>
      </div>
      <div class="lineStyle"/>
      <div class="textStyle">
        <span class="textRight">{{ active == 1 ? '被保人' : '投保人' }}</span>
        <span class="textLeft">{{
          active == 1 ? item.insuredName : item.custName
        }}</span>
      </div>
      <div class="textStyle" v-if="active == 0">
        <span class="textRight">车牌号：</span>
        <span class="textLeft">{{ item.licenseNo }}</span>
      </div>
      <div class="textStyle" style="margin-bottom: 10px">
        <span class="textRight">保障期限：</span>
        <span class="textLeft">{{ item.startdate }}-{{ item.enddate }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant';
export default {
  name: 'infoItemView',

  props: {
    tempListMap: Array,
    active: Number
  },
  methods: {
    toInfo(item, active) {
      //`risk_type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '险种类型(1 车险，2 意外险，3 财产险，4 健康险)',
      if (active == '1') {
        return Toast("非车险暂无保单详情，敬请期待～");
      } else {
        let url = '';
        if (item.riskType == '1') {
          url = 'detailOrder';
        } else if (item.riskType == '3') {
          url = 'detailOrderNo';
        } else {
          url = 'detailOrderY';
        }

        let params = {
          policyNo: item.policyno,
          queryType: item.riskType
        };
        this.$router.push({ name: url, query: { item: JSON.stringify(params) } });
      }
    }
  }
};
</script>

<style scoped>
.contentStyle {
  background: #f6f6f6;
  flex-direction: column;
}

.itemStyle {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  padding: 0px 20px;
  margin-bottom: 10px;
}

.logoStyle {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
}

.logoIcon {
  width: 21px;
  height: 17px;
  background-size: contain;
  margin-right: 20px;
}

.textStyle {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  margin: 2px 0px;
}

.textspan {
  color: #333333;
  font-size: 14px;
  font-weight: bold;
}

.lineStyle {
  height: 1px;
  background-color: #666666;
  opacity: 5%;
  width: 100%;
  margin: 10px 0px;
}

.textRight {
  font-size: 14px;
  color: #454545;
}

.textLeft {
  font-size: 14px;
  color: #7a7a7a;
}
</style>
