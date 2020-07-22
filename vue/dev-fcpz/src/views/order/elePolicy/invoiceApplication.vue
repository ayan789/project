<!--
 * @Description: 发票申请
 * @Date: 2020-03-31 10:47:47
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-21 14:42:11
 * @FilePath: \vue-sales\src\views\order\elePolicy\invoiceApplication.vue
 -->
<template>
  <div class="invoiceApplication" id="invoiceApplication">
    <van-dialog v-model="show" :show-confirm-button="hideConfirmBtn">
      <div class="wrapper" @click.stop>
        <div class="block" v-if="type == 'success'">
          <h2>{{ successInfo.title }}</h2>
          <div class="line"></div>
          <p>{{ successInfo.content }}</p>
          <button @click="goToPage(1)">{{ successInfo.btnName }}</button>
        </div>
        <div class="block" v-if="type == 'error'">
          <h2>{{ errorInfo.title }}</h2>
          <div class="line"></div>
          <p>{{ errorInfo.content }}</p>
          <button @click="goToPage(1)">{{ errorInfo.btnName }}</button>
        </div>
        <div class="confirmDialog" v-if="type == 'confirm'">
          <h2>{{ confirm.title }}</h2>
          <div class="line"></div>
          <p>{{ confirm.content }}</p>
          <div class="btns">
            <button @click="goToPage(1)">{{ confirm.btnName1 }}</button>
            <button @click="goToPage(2)">{{ confirm.btnName2 }}</button>
          </div>
        </div>
      </div>
    </van-dialog>
  </div>
</template>
<script>
import { Overlay, Divider } from "vant";
export default {
  name: "invoiceApplication",
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider
  },
  props: {
    show: Boolean,
    type: String
  },
  data() {
    return {
      hideConfirmBtn: false,
      showTip: false,
      successInfo: {
        title: "发票申请成功",
        content:
          "1个工作日内完成开具，请稍后查看。如一直未查询到电子发票，请咨询智能客服。",
        btnName: "我知道了"
      },
      errorInfo: {
        title: "发票申请失败",
        content:
          "您已申请过纸质发票或电子发票；若您已申请过电子发票，请稍后查看。",
        btnName: "确定"
      },
      confirm: {
        title: "电子发票提醒",
        content:
          "暂未查询到电子发票，如您未曾开过纸质发票和电子发票，请点击下方按钮开具电子发票。",
        btnName1: "取消",
        btnName2: "申请开票"
      }
    };
  },
  methods: {
    goToPage(type) {
      this.showTip = false;
      this.$emit("closeTipEvent", this.showTip, type);
    }
  }
};
</script>
<style lang="less" scoped>
#invoiceApplication {
  .wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    padding: 0 30px;
  }
  .van-overlay {
    background: rgba(172, 172, 172, 1);
  }

  .block {
    height: 213px;
    background: rgba(255, 255, 255, 1);
    border-radius: 5px;
    padding: 0 25px;
    position: relative;
    h2 {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(50, 50, 51, 1);
      height: 50px;
      line-height: 50px;
      text-align: center;
    }
    .line {
      width: 275px;
      height: 1px;
      background: rgba(229, 230, 232, 1);
      margin: 0 auto;
    }
    p {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      line-height: 25px;
      padding-top: 20px;
    }
    button {
      width: 100%;
      height: 46px;
      border-radius: 23px;
      outline: none;
      background: none;
      border: none;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(42, 133, 255, 1);
      line-height: 46px;
      position: absolute;
      bottom: 17px;
      left: 2px;
    }
  }
  .confirmDialog {
    height: 213px;
    background: rgba(255, 255, 255, 1);
    border-radius: 5px;
    padding: 0 25px;
    position: relative;
    h2 {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(50, 50, 51, 1);
      height: 50px;
      line-height: 50px;
      text-align: center;
    }
    .line {
      width: 275px;
      height: 1px;
      background: rgba(229, 230, 232, 1);
      margin: 0 auto;
    }
    p {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      line-height: 25px;
      padding-top: 20px;
    }
    .btns {
      display: flex;
      justify-content: space-around;
      align-items: center;
      position: absolute;
      bottom: 17px;
      width: 100%;
      height: 46px;
      left: 0;
      right: 0;
    }
    button {
      width: 35%;
      height: 35px;
      border-radius: 20px;
      outline: none;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      line-height: 35px;
    }
    button:first-child {
      background: #fff;
      border: 1px solid rgba(59, 138, 254, 1);
      color: rgba(42, 133, 255, 1);
    }
    button:last-child {
      border: none;
      background: linear-gradient(
        125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%
      );
      color: rgba(255, 255, 255, 1);
    }
  }
}
</style>
