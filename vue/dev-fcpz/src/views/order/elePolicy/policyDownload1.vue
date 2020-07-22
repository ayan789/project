<!--
 * @Description: 电子保单下载
 * @Date: 2020-03-31 10:47:47
 * @Author: zhangminjie
 * @LastEditTime: 2020-04-21 11:12:30
 * @FilePath: \vue-sales\src\views\order\elePolicy\policyDownload.vue
 -->
<template>
  <div
    class="policyDownload"
    id="policyDownload"
  >
    <van-overlay
      class="overlay"
      :show="show"
    >
      <div
        class="wrapper"
        @click.stop
      >
        <div class="block">
          <p>{{ title }}</p>
          <div class="bottom">
            <button @click="downLoad">下载</button>
          </div>
          <van-cell-group class="top">
            <!-- <div class="radio">
              <van-radio-group
                v-if="businessPolicyNo && trafficPolicyNo"
                @change="radioCilck"
                v-model="radio"
              >
                <van-radio name="0">{{ businessPolicyNo }}</van-radio>
                <van-radio name="1">{{ trafficPolicyNo }}</van-radio>
                <van-radio
                  v-if="wealthPolicyNo"
                  name="2"
                >
                  {{ wealthPolicyNo }}
                </van-radio>
              </van-radio-group>
            </div> -->
            <van-field
              v-model="personInfo.mobileNo"
              clearable
              label
              placeholder="用来接收电子发票链接"
            >
              <div
                slot="left-icon"
                class="leftIcon"
              >
                <img
                  :src="telSrc"
                  alt="链接"
                />
              </div>
              <div
                slot="right-icon"
                class="rightIcon"
                @click="send('2')"
              >
                <span>发送</span>
              </div>
            </van-field>
            <van-field
              v-model="personInfo.email"
              clearable
              label
              placeholder="请输入客户邮箱"
            >
              <div
                slot="left-icon"
                class="leftIcon"
              >
                <img
                  :src="emailSrc"
                  alt="邮箱"
                />
              </div>
              <div
                slot="right-icon"
                class="rightIcon"
                @click="send('1')"
              >
                <span>发送</span>
              </div>
            </van-field>
          </van-cell-group>
        </div>
        <div
          class="close"
          @click="doClose"
        >
          <img
            :src="closeSrc"
            alt="close"
          />
        </div>
      </div>
    </van-overlay>
  </div>
</template>
<script>
import { Overlay, Divider, Toast } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "policyDownload",
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  props: {
    show: Boolean,
    personInfo: Object,
    title: String,
    businessPolicyNo: String,
    wealthPolicyNo: String,
    trafficPolicyNo: String
  },
  data () {
    return {
      focusTop: false,
      radio: "0",
      emailSrc: require("@/assets/order/elePolicy/email.png"),
      telSrc: require("@/assets/order/elePolicy/tel.png"),
      closeSrc: require("@/assets/order/elePolicy/close.png")
    };
  },
  methods: {
    // 返回上一页
    backToPrePage () {
      this.$globalBack();
    },
    // 点击×操作的事件
    doClose () {
      let isShow = false;
      this.$emit("hidePolicyDownload", isShow);
      this.$parent.reset();
    },
    // 发送
    send (type) {
      let flag = false;
      switch (type) {
        case "1":
          if (!this.checkEmail()) {
            flag = false;
            return;
          } else {
            flag = true;
          }
          break;
        case "2":
          if (!this.checkTel()) {
            flag = false;
            return;
          } else {
            flag = true;
          }
          break;
      }
      this.personInfo.policyNo = [];
      if (this.businessPolicyNo) {
        this.personInfo.policyNo.push(this.businessPolicyNo);
      }
      if (this.wealthPolicyNo) {
        this.personInfo.policyNo.push(this.wealthPolicyNo);
      }
      if (this.trafficPolicyNo) {
        this.personInfo.policyNo.push(this.trafficPolicyNo);
      }
      this.personInfo.policyNo = this.personInfo.policyNo.join()
      this.$emit("sendElectronicPolicyEvent", type, this.personInfo);
      return flag;
    },
    // 下载
    downLoad () {
      this.$emit("downLoadPolicyEvent");
    },
    //验证手机
    checkTel () {
      let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
      if (!this.personInfo.mobileNo.trim()) {
        Toast({
          forbidClick: true,
          message: "请输入手机号码",
          type: "fail"
        });
        return false;
      } else if (!reg.test(this.personInfo.mobileNo.trim())) {
        Toast({
          forbidClick: true,
          message: "请输入正确的手机号码",
          type: "fail"
        });
        return false;
      }
      return true;
    },
    // 验证邮箱
    checkEmail () {
      let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
      if (!this.personInfo.email.trim()) {
        Toast({
          forbidClick: true,
          message: "请输入邮箱",
          type: "fail"
        });
        return false;
      } else if (!reg.test(this.personInfo.email.trim())) {
        Toast({
          forbidClick: true,
          message: "请输入正确的邮箱",
          type: "fail"
        });
        return false;
      }
      return true;
    }
    // focus () {
    //   setTimeout(() => {
    //     this.focusTop = true;
    //   }, 200);
    // },
    // blur () {
    //   setTimeout(() => {
    //     this.focusTop = false;
    //   }, 200);
    // }
  }
};
</script>
<style lang="less" scoped>
.van-radio {
  margin-bottom: 10px;
}
.policyDownload {
  background: #acacac;
  position: relative;
  height: 100%;
  div {
    box-sizing: border-box;
  }
  .block {
    background: rgba(255, 255, 255, 1);
    background-image: url(../../../assets/order/elePolicy/elePolicy.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
    border-radius: 5px;
    padding: 0 20px;
    .top {
      padding: 20px 0 0 0;
      .radio {
        padding-left: 20px;
        padding-bottom: 4px;
      }
    }
    p {
      font-size: 13px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      line-height: 23px;
      padding: 14px 0;
    }
  }
  .wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    padding: 0 30px;
  }
  .leftIcon {
    width: 20px;
    height: 20px;
    margin-right: 5px;
    img {
      width: 100%;
      height: auto;
    }
  }
  .rightIcon {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
  }
  .bottom {
    button {
      width: 295px;
      height: 46px;
      line-height: 46px;
      border-radius: 23px;
      border: 1px solid rgba(59, 138, 254, 1);
      margin-top: 35px;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      background: linear-gradient(
        125deg,
        rgba(42, 128, 255, 1) 0%,
        rgba(42, 165, 255, 1) 100%
      );
      color: rgba(255, 255, 255, 1);
    }
  }
  .close {
    width: 26px;
    height: 26px;
    margin-top: 30px;
    img {
      width: 100%;
      height: auto;
    }
  }
}
.van-radio {
  font-size: 14px;
}
/deep/.van-overlay {
  background: rgba(172, 172, 172, 1);
  z-index: 99 !important;
}
.overlay {
  z-index: 99 !important;
}
.wrapper {
  height: 75% !important;
}
</style>
