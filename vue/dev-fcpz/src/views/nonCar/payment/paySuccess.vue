<template>
  <div class="pay">
    <globalNav :title="globalNav.title" />
    <div class="body">
      <div>
        <img src="../../../assets/nonCar/payment/payOk.png" alt />
        <h3>支付成功</h3>
      </div>
      <van-button round type="info" @click="returnHome" v-if="!isWeiXin">返回首页</van-button>
      <p>请返回大地行app进行后续操作</p>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav";
import { payResultConfirm } from "../../../http/module/nonCar.js";
import { Toast } from "vant";

export default {
  data() {
    return {
      globalNav: {
        title: "支付成功"
      },
      status: false,
      isWeiXin: this.$isWeiXin()
    };
  },
  methods: {
    goback() {
      this.$router.go("-1");
    },
    payResultConfirm(param) {
      payResultConfirm(param).then(res => {
        if (res.code == "1") {
          Toast("成功");
        }
      });
    },
    //返回首页
    returnHome() {
        //todo suncao openhome
        this.$native.openHome("", data => {});
    }
  },
  created() {
    let ext = this.$GetRequest("ext", true);
    let orderAmount = this.$GetRequest("orderAmount", true);
    if (orderAmount == 0) {
      return;
    } else {
      let param = { insureNo: ext };
      this.payResultConfirm(param);
    }
  },
  components: {
    globalNav
  }
};
</script>
<style lang="less" scoped>
.pay {
  .body {
    padding-top: 2rem;
    box-sizing: border-box;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
    flex-direction: column;
    div {
      text-align: center;
      img {
        width: 50%;
      }
      h3 {
        font-size: 0.8rem;
      }
    }
    button {
      width: 50%;
    }
    p {
      color: #2b79ed;
      font-size: 16px;
    }
  }
  /deep/.van-nav-bar__left {
    display: none;
  }
}
/deep/ .navigatorBottom {
  display: none;
}
</style>