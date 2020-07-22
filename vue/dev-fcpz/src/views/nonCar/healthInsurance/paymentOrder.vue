

<template>
  <div id="super">
    <globalNav title="订单支付" />
    <div id="top">
      <!-- <h3 class="title">支付信息</h3> -->
      <div class="father">
        <div class="left">投保单号</div>
        <div class="right">{{insureNo}}</div>
      </div>
      <div class="father">
        <div class="left">产品信息</div>
        <div class="right">{{productInfo}}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">投保人</div>
        <div class="right">{{insureName}}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">支付号</div>
        <div class="right">{{paymentNo}}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">验证码</div>
        <div class="right">{{vertifyCode}}</div>
        <div class="line"></div>
      </div>
      <div class="father">
        <div class="left">应付金额</div>
        <div class="right price">{{price}}</div>
        <div class="line"></div>
      </div>
    </div>
    <div id="bottom">
      <h3 class="title">请选择支付方式</h3>
      <!-- <div id="blueLine"></div> -->
      <div id="paystyle">
        <div v-for="(item,index) in payStyles" :key="index" class='payFather' @click="clickPayStyle(item)">
          <div class="icon" :style="payColors[index]"></div>
          <div class="payText">{{item}}</div>
          <div class="payNext">
            <img src="@/assets/common/next.png" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//  订单支付页面
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "paymentOrder",
  components: {
    globalNav
  },
  data() {
    return {
      payStyles: ["二维码支付", "转发支付", "支付宝支付", "银行卡快捷支付"],
      payColors: [
        { background: "#F9A631" },
        { background: "#2DA641" },
        { background: "#1FC4EE" },
        { background: "#3175FA" }
      ],
      insureNo: "998880099000222",
      productInfo: "车险(商业险+交强险)",
      insureName: "高圆圆",
      paymentNo: "99888222",
      vertifyCode: "888222",
      price: "¥1,000.00"
    };
  },
  methods: {
    clickPayStyle: function(item) {
      //console.log(item);
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
body {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
  overflow: hidden;
}
#top {
  width: 100%;
  margin-top: 64px;
}
.title {
  height: 39px;
  text-align: left;
  margin-left: 15px;
  margin-top: 9px;
  font-size: 15px;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
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
.price {
  font-size: 17px;
  font-weight: 600;
  color: rgba(206, 34, 98, 1);
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

#blueLine {
  height: 3px;
  background: linear-gradient(
    125deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
  margin: 0 15px;
}
#paystyle {
  margin: 0 15px 20px 15px;
  background: white;
  padding: 12px 0px 15px 12px;
  border-radius:0px 0px 10px 10px;
}

.payFather{
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

