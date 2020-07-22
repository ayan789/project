<template>
  <div id="bottom">
    <h3 class="title">使用以下方式打开</h3>
    <div id="wechat" @click="openInWechat">
      <img src="@/assets/vehicle/payment/weixin.png" />
    </div>
    <div id="wx">微信</div>
    <div id="cancel" @click="cancel">取消</div>
  </div>
</template>

<script>
//  订单支付页面
export default {
  name: "paymentWechat",
  methods: {
    cancel: function() {
      this.$emit("cancel");
    },
    openInWechat: function() {
      this.$emit("openInWechat");
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

#bottom {
  width: 100%;
  height: 252px;
  bottom: 0;
  position: absolute;
  z-index: 99;
  background: white;
  border-radius: 20px 20px 0px 0px;
}

.title {
  height: 52px;
  text-align: center;
  color: white;
  line-height: 52px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
}

#wechat {
  width: 100%;
  height: 60px;
  border-radius: 60px;
  margin-top: 30px;
}
#wechat > img {
  margin: 0 auto;
  width: 60px;
  height: 60px;
  display: block;
  object-fit: fill;
}
#wx {
  width: 100%;
  height: 38px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(125, 126, 128, 1);
  line-height: 38px;
  text-align: center;
}

#cancel {
  width: 100%;
  height: 46px;
  margin-top: 29px;
  background: rgba(247, 247, 247, 1);
  font-size: 16px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 46px;
  text-align: center;
}
</style>
