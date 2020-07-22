<template>
  <div class="login">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
    ></globalNav>
    <van-form @submit="onSubmit">
      <van-field
        v-model="userCode"
        name="账号"
        label="账号"
        placeholder="账号"
        :rules="[{ required: true, message: '请填写账号' }]"
      />
      <van-field
        v-model="password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 16px;">
        <van-button
          round
          block
          type="info"
          native-type="submit"
        >提交</van-button>
      </div>
    </van-form>
  </div>
</template>
<script>
import globalNav from "../../components/globalNav";
import { login, query } from "@src/http/module/login.js";

export default {
  components: {
    globalNav
  },
  data () {
    return {
      globalNav: {
        title: "登录",
        isShare: true
      },
      userCode: "8000506130", //
      password: "000000",
      params: {
        loginFlag: "2",
        mobile: "",
        password: "000000",
        system: "0",
        terminalType: "ios_h5",
        userCode: "8000506130",
        version: ""
      }
    };
  },
  methods: {
    onSubmit (values) {
      let params = {
        loginFlag: "2",
        mobile: "",
        password: this.password,
        system: "0",
        terminalType: "ios_h5",
        userCode: this.userCode,
        version: ""
      };
      login(params).then(res => {
        if (res.code == 1) {
          this.$store.commit("loginInfo/SET_USERCODE", res.data.userCode);
          // console.log(this.$store.state.loginInfo.userCode);
          localStorage.setItem("userInfo", JSON.stringify(res.data));
          localStorage.setItem("token", JSON.stringify(res.data.token));
          localStorage.setItem("userCode", res.data.userCode);
          localStorage.setItem("comCode", res.data.comCode);
          localStorage.setItem("storeCode", res.data.storeCode);
          //获取配置接口
          // csu/config/query
          // comCode
          // userCode
          let subParams = {
            userCode: res.data.userCode,
            comCode: res.data.comCode,
            branchCode: res.data.branchCode
          };
          query(subParams).then(res => {
            console.log(res);
            if (res.code == 1) {
              localStorage.setItem("ConfigParams", JSON.stringify(res.data));
              this.$router.push("/");
            }
          });
        } else {
        }
      });
    },
    goback () {
      this.$router.go("-1");
    }
  }
};
</script>
<style lang="less" scoped>
.hello {
  width: 100%;
  position: absolute;
  top: 0;
}
.login {
  display: flex;
  width: 100%;
  align-items: center;
  background-color: #fff;
  flex-wrap: wrap;
  /deep/ .van-form {
    // padding-top: 2rem;
    width: 100%;
    .van-button--round {
      margin-top: 2rem;
    }
  }
}
/deep/ .navigatorBottom {
  display: none;
}
</style>
