
<template>
  <div id="super">
    <globalNav title="订单查询" />
    <div id="background" @click="showToast"></div>
    <!-- <div id="bottom">
      <h3 class="title">使用以下方式打开</h3>
      <div id="wechat" @click="openInWechat">
        <img src="../../../assets/vehicle/payment/weixin.png" />
      </div>
      <div id="wx">微信</div>
      <div id="cancel" @click="cancel">取消</div>
    </div> -->
    <van-popup
      v-model="isShowing"
      position="bottom"
      :style="{ height: '252px' }"
    >
    <paymentWechat v-on:cancel = 'cancel' v-on:openInWechat ='openInWechat' />

    </van-popup>
  </div>
</template>

<script>
//  订单支付页面
import globalNav from "@/components/globalNav.vue";
import paymentWechat from "@/components/paymentWechat.vue";

export default {
  name: "paymentToastWechat",
  components: {
    globalNav,
    paymentWechat
  },
  data(){
    return {
      isShowing:false,
    }
  },
  methods: {
    cancel: function() {
      //console.log("取消");
      this.isShowing = !this.isShowing;
    },
    showToast: function() {
     this.isShowing = !this.isShowing;
    },
    openInWechat: function() {
      //console.log('微信');
    }
  }
};
</script>

<style scoped>
body,
#background {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
}

#bottom {
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

#bottom.show {
  visibility: visible;
  -webkit-animation: sport 0.5s;
  animation: sport 0.5s;
  transform: translateY(0px);
}

/* @-webkit-keyframes fadein {
    from {bottom: -326px; opacity: 1;} 
    to {bottom: 0px; opacity: 1;}
} */
@keyframes sport {
  0% {
    transform: translateY(80px);
    opacity: 1;
  }
  25% {
    transform: translateY(50px);
    opacity: 1;
  }
  50% {
    transform: translateY(20px);
    opacity: 1;
  }
  100% {
    transform: translateY(0px);
    opacity: 1;
  }
}
@keyframes sport {
  from {
    bottom: -252px;
    opacity: 1;
  }
  to {
    bottom: 0px;
    opacity: 1;
  }
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
  object-fit: none;
}
#wx {
  width: 100%;
  height: 38px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(125, 126, 128, 1);
  line-height: 38px;
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
}

/* 
@-webkit-keyframes fadeout {
    from {bottom: 30px; opacity: 1;} 
    to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
    from {bottom: 30px; opacity: 1;}
    to {bottom: 0; opacity: 0;}
} */
</style>

