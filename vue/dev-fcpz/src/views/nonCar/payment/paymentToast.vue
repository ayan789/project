<template>
  <div id="super">
    <div id="background" @click="showToast"></div>
    <!-- <div id="bottom">
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
            <img src="../../../assets/common/next.png" />
          </div>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
//  订单支付页面
import globalNav from "@/components/globalNav.vue";
import paymentSheet from "@/components/paymentSheet.vue";

export default {
  name: "paymentToast",
  components: {
    globalNav,
    paymentSheet
  },
  data() {
    return {};
  },
  methods: {
    showToast: function() {
      //console.log("开始");
      this.isShowing = !this.isShowing;
    },
    clickPayStyle: function(item) {
      //console.log(item);
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

#toastBg {
  width: 100%;
  height: 100%;
  position: absolute;
  background: rgba(0, 0, 0, 1);
  opacity: 0.3;
}
.van-popup {
  background-color: transparent;
}
#bottom {
  width: 100%;
  height: 326px;
  bottom: 0;
  position: absolute;
  z-index: 99;
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
    bottom: -326px;
    opacity: 1;
  }
  to {
    bottom: 0px;
    opacity: 1;
  }
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
