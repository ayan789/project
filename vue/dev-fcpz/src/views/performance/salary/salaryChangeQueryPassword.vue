<template>
  <div class="change-salary-query-password">
    <van-nav-bar title="薪酬查询密码修改" color="white">
      <img
        src="../../../assets/performance/goBack.png"
        slot="left"
        style="width:60%;height:60%;"
        @click="globalBack()"
      />
    </van-nav-bar>
    <!-- <globalNav :title="globalNav.title" @clickBack="globalBack()"></globalNav> -->

    <van-cell-group class="changePwd">
      <van-field
        class="input"
        type="password"
        v-model="data.newPassword"
        label="新密码"
        placeholder="密码必须8-16位含数字和字母"
        input-align="right"
      />
      <van-field
        class="input"
        type="password"
        v-model="newPasswordCheck"
        label="确认新密码"
        placeholder="请输入"
        input-align="right"
        @blur="loseFocus"
        @focus="focusing"
      />
      <!-- 数据 -->
      <van-field
        class="input"
        v-model="data.randomNum"
        label="图形验证码"
        :center="!check.captcha"
        placeholder="请输入"
        @click-right-icon="getCaptcha"
      >
        <img slot="right-icon" :src="src" class="captcha" />
      </van-field>
    </van-cell-group>
    <van-cell-group title=" ">
      <van-field
        class="input"
        v-model="data.smsCode"
        label="手机验证码"
        placeholder="请输入"
      >
        <van-button
          slot="button"
          size="small"
          class="get-verification-code"
          :disabled="disabled"
          @click="onSendSmsCode"
        >
          {{ disabled ? `重新发送(${resendTime})` : "获取验证码" }}
        </van-button>
      </van-field>
    </van-cell-group>
    <van-button
      class="primary-button"
      round
      type="primary"
      color="#3B8AFE"
      @click="onSubmit"
    >
      确认
    </van-button>
  </div>
</template>

<script>
import {
  captcha,
  sendSalarySMS,
  salaryQueryChangePassword
} from "@src/http/module/performance";
import EncryptUtils from "@src/utils/encryptUtils";
import { Toast } from "vant";
import globalNav from "../../../components/globalNav";

export default {
  components: {
    globalNav
  },
  data: () => ({
    reg: /^(?![^a-zA-Z]+$)(?!\\D+$).{8,16}$/,
    resendTime: 60,
    newPasswordCheck: "",
    src: "",
    randomNum: "",
    captcha: "",
    isSetPwd: "",
    data: {
      userCode: "",
      newPassword: "",
      smsCode: "",
      randomNum: ""
    },
    globalNav: {
      title: "薪酬查询密码修改",
      isShare: false
    },
    check: {
      newPassword: "",
      newPasswordCheck: "",
      smsCode: "",
      captcha: ""
    }
  }),
  created() {
    // this.isSetPwd = this.$router.params.isSetPwd || "0";
    this.getCaptcha();
  },
  computed: {
    disabled: function() {
      return this.resendTime < 60;
    }
  },
  methods: {
    // 失焦
    loseFocus() {
      if (this.data.newPassword !== this.newPasswordCheck) {
        Toast("确认密码录入不一致");
      }
    },
    // 聚焦
    focusing() {
      if (this.data.newPassword === "") Toast("请先录入新密码");
    },
    getCaptcha() {
      //获取图形验证码
      captcha({}).then(
        data => {
          if (data.code === "1") {
            this.randomNum = data.data.randomNum;
            this.src = data.data.src;
          }
        },
        error => {
          //console.log(error);
        }
      );
    },
    onSendSmsCode() {
      // 未录入内容提示
      if (this.data.newPassword === "") {
        Toast("请输入新密码");
        return false;
      }
      if (!this.data.newPassword.match(this.reg)) {
        Toast("密码必须8-16位含数字和字母");
        return false;
      }
      if (this.newPasswordCheck === "") {
        Toast("请输入新密码确认");
        return false;
      }
      if (this.data.newPassword !== this.newPasswordCheck) {
        Toast("两次输入密码不同请确认");
        return false;
      }
      if (this.data.randomNum === "") {
        Toast("请输入图形验证码");
        return false;
      }
      /* 初始位置 */
      let params = {
        captcha: {
          randomNum: this.randomNum,
          captcha: this.data.randomNum
        },
        comCode: localStorage.getItem("comCode"),
        userCode: localStorage.getItem("userCode")
      };
      sendSalarySMS(params).then(
        data => {
          // 判断图形验证码
          if (data.code !== "1") {
            this.getCaptcha();
            Toast(data.msg);
            return false;
          } else {
            /* 后移位置 */
            if (this.interval) return;
            this.resendTime = 59;
            this.interval = setInterval(() => {
              if (this.resendTime > 0) {
                this.resendTime -= 1;
              } else {
                this.resendTime = 60;
                clearInterval(this.interval);
                this.interval = null;
              }
            }, 1000);
          }
          // console.log(data);
        },
        error => {
          // console.log(error);
        }
      );
    },
    onSubmit() {
      let encrypt = new JSEncrypt();
      encrypt.setPublicKey(
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzfKrgzOisGDgpPBspXbAeqd7aDPxeP/zU275Rg64AAmvm8zl44RBtfi0eb+9Pt3mGCr37w2rWbI2xAwVcL+DSjSttSrh9ndW7mS5JND5GSL8mYw80fG+N1Mdl46iTAS6s5exaxscOrPJ4deuUWZFdH8ZYQjZmH6mIpVd34JhFpQIDAQAB"
      );
      this.data.userCode = localStorage.getItem("userCode");
      const params = Object.assign({}, this.data, {
        newPassword: encrypt.encrypt(this.data.newPassword)
      });
      if (this.data.newPassword === "") {
        Toast("请输入新密码");
        return false;
      } else if (!this.data.newPassword.match(this.reg)) {
        Toast("密码必须8-16位含数字和字母");
        return false;
      } else if (this.newPasswordCheck === "") {
        Toast("请输入新密码确认");
        return false;
      } else if (this.data.newPassword !== this.newPasswordCheck) {
        Toast("两次输入密码不同请确认");
        return false;
      } else if (this.data.randomNum === "") {
        Toast("请输入图形验证码");
        return false;
      } else if (this.data.smsCode === "") {
        Toast("请输入短信验证码");
        return false;
      }
      // if (
      //   !this.data.newPassword ||
      //   !this.data.newPasswordCheck ||
      //   !this.data.smsCode ||
      //   !this.data.captcha
      // ) {
      //   Toast("请填写完整信息");
      //   return;
      // }
      salaryQueryChangePassword(params)
        .then(
          data => {
            //console.log(data);
            if (data.code !== "1") {
              Toast(data.msg);
              return false;
            }
            if (this.isSetPwd === "1") {
              this.$router.back();
            } else {
              Toast({ message: "密码修改成功", duration: 3500 });
              this.$router.replace({ name: "salaryQueryLogin" });
            }
          },
          error => {
            //console.log(error);
          }
        )
        .finally(() => {});
    },
    globalBack() {
      this.$router.replace({ name: "performance" });
    }
  }
};
</script>

<style scoped>
.change-salary-query-password {
  width: 375px;
  height: 667px;
  background-color: #f7f7f7;
}
.input {
  min-height: 48px;
}
.captcha {
  width: 100px;
  min-height: 28px;
  align-self: center;
}
.get-verification-code {
  width: 109px;
  height: 20px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3b8afe;
  line-height: 20px;
  border: none;
  border-left: 1px solid #d8d8d8;
}
.primary-button {
  margin: 28px 16px;
  width: 344px;
  height: 46px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  line-height: 22px;
}
.changePwd {
  /* margin-top: 78px; */
  margin-top: 15px;
}
</style>
