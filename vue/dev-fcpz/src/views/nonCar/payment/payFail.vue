<template>
  <div class="pay">
    <globalNav :title="globalNav.title" @clickBack="goback" />
    <div class="body">
      <div>
        <img src="../../../assets/nonCar/payment/payError.png" alt />
        <h3>支付失败</h3>
      </div>
      <van-button round type="info" v-if="!isWeiXin" @click="returnHome">返回首页</van-button>
      <p>请返回大地行app进行后续操作</p>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav";

export default {
  data() {
    return {
      globalNav: {
        title: "支付失败"
      },
      status: false,
      isWeiXin: this.$isWeiXin()
    };
  },
  methods: {
    goback() {
      this.$router.go("-1");
    },
    //TODO suncao支付失败需要同步后台接口 待提供
    //返回首页
    returnHome() {
        this.$native.openHome("", data => {});
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
}
/deep/ .navigatorBottom {
  display: none;
}
</style>
