<template>
  <div id="toast">
    <div id="close" @click="cancel">
      <img src="@/assets/vehicle/payment/close.png" />
    </div>
    <h3 id="info">请与投保人/被保人联系获取短信验证码并录入</h3>
    <div id="codeFather">
      <input type="text" placeholder="请输入验证码" v-model='vertifyCode'/>
      <h3 id="getcode" @click="getVertify">获取验证码</h3>
    </div>
    <h3 id="save" @click="saveAction">保存</h3>
  </div>
</template>

<script>
//  验证码申请P1页面
export default {
  name: "paymentCode",
  data(){
      return {
          vertifyCode:'',
      }
  },
  methods: {
      cancel(){
           this.$emit("cancel");
      },
    getVertify: function() {
      this.$emit("getCode");
    },
    saveAction: function() {
      this.$emit("saveCode",this.vertifyCode);
    },
    
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
#toast {
  height: 276px;
  width: 100%;
  background: white;
  position: absolute;
  top: 50%; /*偏移*/
  transform: translateY(-50%);
}

#close {
  width: 44px;
  height: 44px;
  float: right;
}
#close > img {
  display: block;
  height: 16px;
  width: 16px;
  margin: 14px;
}
#info {
  margin: 40px 58px 40px 58px;
  height: 44px;
  font-size: 16px;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
}

#codeFather {
  margin: 0 25px 30px 25px;
  height: 46px;
  border-radius: 21px;
  border: 1px solid rgba(242, 242, 242, 1);
}

input {
  float: left;
  width: 60%;
  text-align: left;
  padding-left: 15px;
  height: 46px;
  line-height: 46px;
  border-radius: 0px;
  border-width: 0px;
  overflow: hidden;
  border: 0;
  outline: none;
  font-size: 14px;
  font-weight: 400;
  color: rgba(193, 193, 193, 1);
  background: transparent;
}

input::-webkit-input-placeholder {
  color: #c1c1c1;
  font-size: 14px;
  text-align: left;
}

#getcode {
  float: right;
  width: calc(40% - 15px);
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  line-height: 46px;
  height: 46px;
}
#save {
  margin: 0px 25px 0 25px;
  height: 46px;
  background: rgba(59, 138, 254, 1);
  border-radius: 23px;
  line-height: 46px;
  text-align:center;
  color:white;
}
</style>
