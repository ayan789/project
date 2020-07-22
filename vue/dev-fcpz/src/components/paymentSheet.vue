<template>
  <div id="bottom">
    <h3 class="title">请选择支付方式</h3>
    <div id="paystyle">
      <div
        v-for="(item, index) in payStyles"
        :key="index"
        class="payFather"
        @click="clickPayStyle(item)"
      >
        <div class="icon" :style="payColors[index]"></div>
        <div class="payText">{{ item }}</div>
        <div class="payNext">
          <img src="@/assets/common/next.png" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//  订单支付页面
export default {
  name: "paymentSheet",
  data() {
    return {
      isShowing: true,
      payStyles: ["二维码支付", "转发支付", "支付宝支付", "银行卡快捷支付"],
      payColors: [
        { background: "#F9A631" },
        { background: "#2DA641" },
        { background: "#1FC4EE" },
        { background: "#3175FA" }
      ]
    };
  },
  methods: {
    clickPayStyle: function(item) {
    //   console.log(item);
      this.$emit('clickPayStyle', item);
    }
  }
};
</script>

<style scoped>
#bottom {
  width: 100%;
  background: white;
  border-radius: 20px 20px 0px 0px;
}
.title {
  height: 56px;
  text-align: center;
  font-size: 16px;
  font-weight: 600;
  color: white;
  line-height: 56px;
  background: linear-gradient(
    125deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
  border-radius: 20px 20px 0px 0px;
}

#paystyle {
  margin: 0 15px 20px 15px;
  background: white;
  padding: 12px 0px 15px 12px;
  border-radius: 0px 0px 10px 10px;
}

.payFather {
  height: 60px;
  background: white;
}

.icon {
  margin: 10px 0px 10px 25px;
  width: 40px;
  height: 40px;
  border-radius: 40px;
}

.icon,
.payText {
  float: left;
}
.payNext {
  float: right;
}

.payNext,
.payText {
  height: 60px;
}
.payText {
  width: calc(100% - 146px);
  text-align: left;
  margin-left: 12px;
  line-height: 60px;
  color: #333333;
}
.payNext {
  width: 69px;
}
.payNext > img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: none;
}
</style>
