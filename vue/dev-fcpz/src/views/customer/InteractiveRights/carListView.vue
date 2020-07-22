<template>
  <div class="queryRemindView">
    <Global_globalNav title="车辆信息" @clickBack="clickBack"/>
    <div class="contentStyle">
      <div v-for="(item,index) in editorCustomerData.tempData.carList" class="itemStyle">
        <div class="logoStyle" :style="{marginTop:index!=0?'10px':'0px'}">
          <img :src="$imageUrl.cardIcon" alt="" class="logoIcon">
          <span class="textspan">车牌号：{{item.licenseNo}}</span>
        </div>
        <div class="lineStyle"/>
        <div class="textStyle">
          <span class="textRight">车架号：</span>
          <span class="textLeft">{{item.frameNo}}</span>
        </div>
        <div class="textStyle">
          <span class="textRight">厂牌型号：</span>
          <span class="textLeft">{{item.brandName}}</span>
        </div>
        <div class="textStyle" style="margin-bottom: 10px">
          <span class="textRight">发动机号：</span>
          <span class="textLeft">{{item.engineNo}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "carListView",
  data() {
    return {
      carList: [{
        licenseNo: '22',
        vim: 'll',
        brandName: '22',
        engineNo: '22'
      }, {
        licenseNo: '22',
        vim: 'll',
        brandName: '22',
        engineNo: '22'
      }]
    };
  },
  computed: {
    ...mapState(["editorCustomerData"]),
  },
  created() {
    console.log(this.editorCustomerData.tempData.carList, 'ppp');
  },
  methods: {
    clickBack() {
      this.$router.back();
    }
  }
};
</script>

<style scoped>
.queryRemindView {
  position: fixed;
  width: 100%;
}

.contentStyle {
  background: #F6F6F6;
  margin-top: 80px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 65px);
  display: flex;
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
  opacity: 10%;
  width: 100%;
  margin: 10px 0px;
}

.textRight {
  font-size: 14px;
  color: #454545;
}

.textLeft {
  font-size: 14px;
  color: #7A7A7A;
}
</style>
