<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-21 20:47:49
 * @Last Modified by: King
 * @Last Modified time: 2020-04-03 12:45:24
 * @FilePath: /src/views/vehicle/payment/paymentRecordCode.vue
-->

<template>
  <div id="super">
    <globalNav title="补录验证码" />
    <div id="top">
      <h3 class="title">投保信息</h3>
      <div class="father">
        <div class="left">投保人</div>
        <div class="right">{{ insureName }}</div>
      </div>
      <div class="father">
        <div class="left">投保单</div>
        <div class="right">{{ insureNo }}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">支付金额</div>
        <div class="right">{{ price }}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">支付号</div>
        <div class="right">{{ paymentNo }}</div>
        <div class="line"></div>
      </div>
      <div id="space"></div>
      <div class="father">
        <div class="left">验证码</div>
        <div id="final">
          <input type="text" v-model="vertifyCode" placeholder="请输入验证码" />
          <div class="verLine"></div>
          <div class="last" @click="getCode">获取验证码</div>
        </div>
      </div>
    </div>
    <div id="sure" @click="sureAction">确定</div>
  </div>
</template>

<script>
//  补录验证码页面
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "paymentRecordCode",
  components: {
    globalNav
  },
  data() {
    return {
      insureNo: "998880099000222",
      productInfo: "车险(商业险+交强险)",
      insureName: "高圆圆",
      paymentNo: "99888222",
      vertifyCode: "888222",
      price: "¥1,000.00"
    };
  },
  methods: {
    sureAction: function() {
      //console.log('确定');
    },
    getCode: function() {
      //console.log("获取验证码");
    }
  }
};
</script>

<style scoped>
body {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
}
#top {
  width: 100%;
  margin-top: 53px;
}
#space {
  width: 100%;
  height: 10px;
  background: #f7f7f7;
}
.title {
  height: 39px;
  text-align: left;
  padding-left: 15px;
  margin-top: 9px;
  font-size: 15px;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  background: white;
  width: calc(100% - 15px);
  line-height: 39px;
}

.father {
  width: calc(100% - 30px);
  height: 48px;
  background: white;
  padding: 0 15px;
}
.left,
.right {
  line-height: 48px;
  height: 48px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
}
.line {
  height: 1px;
  background: #f7f7f7;
}
.left {
  float: left;
}
.right {
  float: right;
}

#final {
  width: 80%;
  height: 48px;
  float: right;
}

input,
.verLine {
  float: left;
}
.last {
  float: right;
  height: 48px;
  line-height: 48px;
  width: 72px;
  text-align: center;
  font-size: 12px;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  line-height: 48px;
  background-clip: text;
}
.verLine {
  height: 20px;
  width: 1px;
  margin: 14px 0px;
  background: #ededed;
  margin-left: 15px;
}
input {
  float: left;
  width: calc(100% - 98px);
  text-align: right;
  height: 48px;
  line-height: 48px;
  border-radius: 0px;
  border-width: 0px;
  overflow: hidden;
  border: 0;
  outline: none;
  color: #666666;
  font-size: 14px;
}

input::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
  text-align: right;
}

#sure {
  margin: 30px 15px 0 15px;
  width: calc(100% - 30px);
  height: 46px;
  color: white;
  background: #3b8afe;
  border-radius: 46px;
  line-height: 46px;
  text-align: center;
}
</style>
