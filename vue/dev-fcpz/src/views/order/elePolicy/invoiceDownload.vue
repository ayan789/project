<!--
 * @Description: 电子发票申请下载
 * @Date: 2020-03-31 10:47:47
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 19:29:53
 * @FilePath: \nonCarCongfig\src\views\order\elePolicy\invoiceDownload.vue
 -->
<template>
  <div class="policyDownload" id="policyDownload">
    <van-overlay :show="show">
      <div class="wrapper" @click.stop>
        <div class="block">
          <p>
            点击下方下载按钮，可在线转发电子发票，输入邮箱地址或移动电话，直接把电子发票发送至客户邮箱或手机中
          </p>
          <div class="bottom">
            <button @click="downLoad">下载</button>
          </div>
          <van-cell-group class="top">
            <van-field
              v-model="personInfo.email"
              clearable
              label=""
              placeholder="请输入客户邮箱"
            >
              <div slot="left-icon" class="leftIcon">
                <img :src="emailSrc" alt="邮箱" />
              </div>
              <div slot="right-icon" class="rightIcon" @click="send('1')">
                <span>发送</span>
              </div>
            </van-field>
            <van-field
              v-model="personInfo.mobileNo"
              clearable
              label=""
              placeholder="用来接收电子发票链接"
            >
              <div slot="left-icon" class="leftIcon">
                <img :src="telSrc" alt="链接" />
              </div>
              <div slot="right-icon" class="rightIcon" @click="send('2')">
                <span>发送</span>
              </div>
            </van-field>
          </van-cell-group>
        </div>
        <div class="close" @click="doClose">
          <img :src="closeSrc" alt="close" />
        </div>
      </div>
    </van-overlay>
  </div>
</template>
<script>
import { Overlay, Divider, Toast } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "invoiceDownload",
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  props: {
    show: Boolean,
    personInfo: Object
  },
  data() {
    return {
      email: "",
      link: "",
      emailSrc: require("@/assets/order/elePolicy/email.png"),
      telSrc: require("@/assets/order/elePolicy/tel.png"),
      closeSrc: require("@/assets/order/elePolicy/close.png")
    };
  },
  created() {},
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    },
    // 点击×操作的事件
    doClose() {
      let isShow = false;
      this.$emit("hideInvoiceDownload", isShow);
    },
    // 发送
    send(type) {
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
      this.$emit("sendElectronicPolicyEvent", type, this.personInfo);

      return flag;
    },
    // 下载
    downLoad() {
      this.$emit("downLoadInvoiceEvent");
    },
    //验证手机
    checkTel() {
      let reg = /^1[3|4|5|6|8|7|9][0-9]\d{8}$/;
      if (!this.personInfo.mobileNo || !this.personInfo.mobileNo.trim()) {
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
    checkEmail() {
      let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
      if (!this.personInfo.email || !this.personInfo.email.trim()) {
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
  }
};
</script>
<style lang="less" scoped>
#policyDownload {
  background: rgba(255, 255, 255, 1);
  position: relative;
  div {
    box-sizing: border-box;
  }
  .van-overlay {
    background: rgba(172, 172, 172, 1);
  }
  .block {
    height: 313px;
    background: rgba(255, 255, 255, 1);

    background-image: url(../../../assets/order/elePolicy/elePolicy.png);
    background-repeat: no-repeat;
    background-size: 100% auto;
    border-radius: 5px;
    padding: 0 20px;
    .top {
      padding: 40px 0 0 0;
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
      margin-top: 40px;
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
/deep/.van-overlay {
  background: rgba(172, 172, 172, 1);
  z-index: 99 !important;
}
</style>
