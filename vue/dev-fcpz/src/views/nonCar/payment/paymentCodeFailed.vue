

<template>
  <div id="super">
    <globalNav title="订单查询" />
    <div id="top">
      <img src="../../../assets/common/default.png" id="def" />
      <h3 id="infoMsg">
        验证码效验不通过，请点击下方“补录验证码”
        在新的页面进行补录
      </h3>
      <h3 id="resendCode" @click="resendCode">补录验证码</h3>
    </div>
  </div>
</template>

<script>
//  验证码申请P1页面
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "paymentCodeFailed",
  components: {
    globalNav
  },
  methods: {
    resendCode: function() {
      //console.log("补录验证码");
    }
  }
};
</script>

<style scoped>
#top {
  width: 100%;
  height: 368px;
  position: absolute;
  top: 50%; /*偏移*/
  transform: translateY(-50%);
}
#infoMsg {
  margin: 0 57px;
  height: 30px;
  font-size: 13px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 18px;
}

#def{
  margin: 0 calc(50% - 64px);
}

#resendCode {
  height: 46px;
  width: 205px;
  margin: 0 auto;
  margin-top: 81px;
  background: rgba(59, 138, 254, 1);
  border-radius: 23px;
  line-height: 46px;
  text-align: center;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
}
</style>

