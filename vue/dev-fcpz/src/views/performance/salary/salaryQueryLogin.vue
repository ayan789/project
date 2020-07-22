<template>
  <div style="overflow:hidden;">
    <div class="salary-query-password">
      <van-nav-bar title="薪酬查询" color="white" fixed>
        <van-icon name="arrow-left" slot="left" @click="onBack()" :size="16" />
        <!-- <img src="../../../assets/performance/goBack.png" slot="left" style="width:60%;height:60%;" @click="onBack()"> -->
      </van-nav-bar>
      <!-- <globalNav :title="globalNav.title" @clickBack="onBack"></globalNav> -->
      <van-field
        class="password-input"
        type="password"
        v-model="password"
        label="密码"
        placeholder="请输入"
        input-align="right"
      />
      <van-button
        class="primary-button"
        round
        type="primary"
        color="#3B8AFE"
        @click="primary"
      >
        确认
      </van-button>
      <van-row
        class="warning-container"
        type="flex"
        justify="start"
        align="center"
      >
        <van-icon name="warning-o" color="#B2B2B2" />
        <span class="warning">仅限个人查看，薪酬信息请保密</span>
      </van-row>
      <van-row
        type="flex"
        justify="center"
        align="bottom"
        class="change-password-button"
        @click="changePassword"
      >
        <span class="change-password-text">修改密码</span>
      </van-row>
    </div>
  </div>
</template>

<script>
import { Toast } from "vant";
import EncryptUtils from "@src/utils/encryptUtils";
import {
  isHaveSetSalaryPwd,
  salaryQueryLogin
} from "@src/http/module/performance";
import globalNav from "../../../components/globalNav";

export default {
  components: {
    [Toast.name]: Toast,
    globalNav
  },
  data: () => ({
    password: "",
    globalNav: {
      title: "薪酬查询密码设置"
    }
  }),
  methods: {
    onBack() {
      // this.$router.back()
      /* 返回主页 */
      this.$router.replace({ name: "performance" });
      // this.$globalBack();
    },
    primary() {
      if (this.password === "") {
        Toast.fail("请输入密码");
        return false;
      }
      let encrypt = new JSEncrypt();
      encrypt.setPublicKey(
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzfKrgzOisGDgpPBspXbAeqd7aDPxeP/zU275Rg64AAmvm8zl44RBtfi0eb+9Pt3mGCr37w2rWbI2xAwVcL+DSjSttSrh9ndW7mS5JND5GSL8mYw80fG+N1Mdl46iTAS6s5exaxscOrPJ4deuUWZFdH8ZYQjZmH6mIpVd34JhFpQIDAQAB"
      );

      let params = {
        userCode: localStorage.getItem("userCode"),
        salaryPassword: encrypt.encrypt(this.password)
      };
      salaryQueryLogin(params)
        .then(
          data => {
            if (data.code === "1") {
              this.$router.replace({
                name: "performance",
                params: { isLogin: true }
              });
            } else {
              setTimeout(() => {
                Toast.fail(data.message || data.msg);
              }, 20);
            }
          },
          error => {
            Toast.fail(error);
          }
        )
        .catch(error => {})
        .finally(() => {});
    },
    changePassword() {
      this.$router.push({
        name: "salaryChangeQueryPassword",
        params: { isSetPwd: true }
      });
    }
  }
};
</script>

<style scoped>
.salary-query-password {
  width: 375px;
  height: 667px;
  background-color: #f7f7f7;
  /* margin-top: 80px; */
  margin-top: 60px;
}
.password-input {
  margin-top: 10px;
}
.primary-button {
  margin: 28px 16px;
  width: calc(100% - 32px);
  height: 46px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 22px;
}
.warning-container {
  margin-left: 25px;
  align-items: center;
}
.warning {
  margin-left: 10px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 17px;
}
.change-password-button {
  position: absolute;
  bottom: 26px;
  width: 100%;
}
.change-password-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
</style>
