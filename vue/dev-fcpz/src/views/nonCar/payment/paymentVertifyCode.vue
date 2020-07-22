<template>
  <div id="super">
    <globalNav title="订单查询" />
    <div id="background" @click="showToast"></div>
    <van-popup
      v-model="isShowing"
      position="center"
      :style="{
        height: '276px',
        width: 'calc(100% - 60px)'
      }"
    >
      <paymentCode
        v-on:getCode="getCode"
        v-on:saveCode="saveCode"
        v-on:cancel="cancel"
      />
    </van-popup>
  </div>
</template>

<script>
//  验证码申请P1页面
import globalNav from "@/components/globalNav.vue";
import paymentCode from "@/components/paymentCode.vue";
export default {
  name: "paymentVertifyCode",
  components: {
    globalNav,
    paymentCode
  },
  data() {
    return {
      isShowing: true
    };
  },
  methods: {
    cancel() {
      this.isShowing = !this.isShowing;
    },
    getCode: function() {
      //console.log("获取验证码");
    },
    saveCode: function(code) {
      //console.log("保存", code);
    },
    showToast: function() {
      this.isShowing = !this.isShowing;
    }
  }
};
</script>

<style scoped>
#toastBg {
  background: rgba(0, 0, 0, 0.3);
  /* opacity: 1; */
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: 99;
}
#background {
  width: 100%;
  height: 100%;
  position: absolute;
}

.van-popup {
  border-radius: 5px;
}
</style>
