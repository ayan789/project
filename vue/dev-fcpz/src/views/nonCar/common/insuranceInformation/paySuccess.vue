<template>
  <div class="pay">
    <globalNav :title="globalNav.title" @clickBack="goback" />
    <div class="body">
      <div>
        <img src="../../../../assets/nonCar/payOk.png" alt />
        <h3>支付成功</h3>
      </div>
      <van-button round type="info">返回首页</van-button>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../../components/globalNav";
import { payResultConfirm } from "../../../../http/module/nonCar.js";
import { Toast } from "vant";

export default {
  data() {
    return {
      globalNav: {
        title: "支付成功"
      },
      status: false
    };
  },
  methods: {
    goback() {
      this.$router.go("-1");
    },
    payResultConfirm(ext) {
      payResultConfirm({
        insureNo: ext
      }).then(res => {
        if (res.code == "1") {
          Toast("成功");
        }
      });
    }
  },
  created() {
    let ext = window.location.href.split("ext=")[1];
    this.payResultConfirm(ext);
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
  }
}
/deep/ .navigatorBottom {
  display: none;
}
</style>