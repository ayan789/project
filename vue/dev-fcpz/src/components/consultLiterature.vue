<template>
  <div class="consultLiterature">
    <div class="picture">
      <div class="headerView">
        <div class="carLogo">
          <img :src="$imageUrl.caricon" alt="" class="logoItem">
          <div class="titleStyle">
            <span style="font-size: 20px;font-weight: bold;color: #333333;">{{customerData.getQueryViolationRecordData?customerData.getQueryViolationRecordData.carNo:''}}</span>
            <span style="color: #666666;font-size: 14px">{{customerData.getQueryViolationRecordData?customerData.getQueryViolationRecordData.brandName:''}}
            </span>
          </div>
        </div>
        <div class="licenceChange" @click="checkStatus">切换车辆</div>
      </div>
<!--      <img :src="$imageUrl.CARlOGOBG" alt="" class="CARlOGOBGStyle">-->
    </div>
    <div class="inquire">
      <div class="inquireResult">
        查询结果 (更新时间:{{customerData.getQueryViolationRecordData?customerData.getQueryViolationRecordData.updateTime:""}})
      </div>
      <img :src="$imageUrl.replace" alt="" class="iconSttyle" @click="refreshClick">
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  data() {
    return {};
  },

  props: {
    carNo: String
  },
  computed: {
    ...mapState(["customerData"])
  },
  created() {
    console.log(this.customerData);
  },
  methods: {
    checkStatus() {
      this.$emit("openCarlIST", "点击了导航上的返回");
    },
    refreshClick() {
      const { carNo, frameNo, enginNo, brandName } = this.customerData.getQueryViolationRecordData;
      let params_ = {
        custNo: this.carNo, //客户号
        userCode: localStorage.getItem("userCode"),
        carNo: carNo,
        frameNo: frameNo,
        enginNo: enginNo,
        brandName: brandName
      };
      this.$store.dispatch('customerData/getQueryViolationRecordData', params_);
    }
  }
};
</script>
<style lang="less" scoped>
.consultLiterature {
  padding: 14px 15px 0 16px;
  position: relative;
  background-color: white;

  .picture {
    height: 84px;
    padding: 0px 20px;
    margin-top: 20px;

    .headerView {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;

      .carLogo {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;

        .titleStyle {
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          margin-left: 25px;
          z-index: 100;
          margin-right: 10px;
          width: 40vw;
        }

        .logoItem {
          width: 40px;
          height: 40px;
          background-size: contain;
          z-index: 9;
        }
      }
    }

    .licenceChange {
      padding: 4px 7px;
      font-size: 14px;
      font-weight: 400;
      color: #3B95FB;
      line-height: 20px;
      height: 20px;
      text-align: center;
      width: 80px;

      top: 14px;
      right: 14px;
      background: rgba(255, 255, 255, 1);
      border-radius: 19px;
      border: 1px solid #3B95FB;
    }
  }

  .inquire {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    background-color: #F5F9FF;
    padding: 4px;
    margin-bottom: 10px;

    .iconSttyle {
      width: 16px;
      height: 16px;
      background-size: contain;
    }

    .inquireResult {
      color: #486895;
      font-size: 13px;
    }
  }
}

.CARlOGOBGStyle {
  width: 111px;
  height: 97px;
  background-size: contain;
  position: absolute;
  top: 0px;
  left: 0px
}
</style>
