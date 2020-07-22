<template>
  <div id="payment-method-sheet" @click="close">
    <van-overlay :show="show" @click="show = false" >
      <div id="sheet">
        <van-row id="sheet-title" type="flex" justify="center" align="center">
          <van-col>请选择支付方式</van-col>
        </van-row>
        <van-cell-group class="sheet-group" :border="false">
          <van-cell
            v-for="item in actions"
            :key="item.name"
            class="sheet-item"
            :border="false"
            center
            is-link
            @click="onSelect(item)"
          >
            <template slot="icon">
              <img class="sheet-item-icon" :src="item.src" />
            </template>
            <template slot="title">
              <span class="sheet-item-title">{{ item.name }}</span>
            </template>
          </van-cell>
          <van-cell>
            <van-button type="primary" size="large" @click="close"
              >取消</van-button
            >
          </van-cell>
        </van-cell-group>
      </div>
    </van-overlay>
  </div>
</template>

<script>
import Vue from "vue";
import { ActionSheet, Overlay } from "vant";

Vue.use(ActionSheet);
Vue.use(Overlay);
export default {
  props: {
    isShow: Boolean //是否显示
  },
  data: () => ({
    show: false,
    actions: [
      // { name: "二维码支付", src: require("@/assets/payment/payCode.png") },
      // { name: "转发支付", src: require("@/assets/payment/payShare.png") },
      // {
      //   name: "支付宝支付",
      //   subname: "描述信息",
      //   src: require("@/assets/payment/payLogo.png")
      // },
      {
        name: "微信支付",
        subname: "描述信息",
        src: require("@/assets/payment/payWechat.png")
      },
      {
        name: "银行卡快捷支付",
        subname: "描述信息",
        src: require("@/assets/payment/payCard.png")
      }
    ]
  }),
  watch: {
    isShow() {
      this.show = this.isShow;
    }
  },
  methods: {
    close() {
      this.$emit("closeEvent");
    },
    onSelect(item) {
      this.$emit("paymentEvent", item);
    }
  }
};
</script>

<style lang="less" scoped>
#payment-method-sheet {
  position: absolute;
  display: flex;
  align-items: flex-end;
  justify-content: flex-end;
  flex-direction: column;
  align-self: flex-end;
  height: 100%;
  width: 100%;
  z-index: 9999;
  .van-overlay {
    background: rgba(172, 172, 172, 1);
  }
}
#sheet {
  width: 90%;
  background: white;
  border-radius: 15px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
#sheet-title {
  height: 48px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
.sheet-group {
  width: 90%;
  margin: 0 auto;
}
.sheet-item {
  padding: 12px 0;
  border-bottom: 0px;
}
.sheet-item-icon {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  border: 1px solid #e2e2e2;
  margin-right: 12px;
}
.sheet-item-title {
  font-size: 16px;
  font-family: PingFangHK-Regular, PingFangHK;
  font-weight: 400;
  color: #333333;
  line-height: 22px;
}
.van-button--primary {
  background: linear-gradient(
    125deg,
    rgba(42, 128, 255, 1) 0%,
    rgba(42, 165, 255, 1) 100%
  );
  border-radius: 23px;
}
</style>
