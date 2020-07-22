<template>
  <div class="invoice-detail">
    <div class="title-container">
      <div class="title-text">{{ title }}</div>
      <div class="title-sub-text">
        {{ subTitle }}
      </div>
    </div>
    <van-row
      type="flex"
      justify="center"
      align="center"
      class="invoice-apply-button-container"
    >
      <van-col offset="2px">
        <div type="primary" class="invoice-apply-button" @click="onClick">
          电子发票
        </div>
      </van-col>
    </van-row>
    <invoice-information v-if="status === 2" v-bind="info" />
    <invoice-info-input v-if="status === 1" />
    <van-row v-if="status === 2" class="group-title-container">
      <van-col class="group-title-text">车主信息</van-col>
    </van-row>
    <van-cell v-if="status === 2" center title="电子邮箱">
      <template slot="default">
        <van-row type="flex" justify="flex-end" align="center">
          <van-col class="send-email">{{ sendEmail }}</van-col>
          <van-col>
            <van-button class="send-button" @click="onSendClick">
              重新发送
            </van-button>
          </van-col>
        </van-row>
      </template>
    </van-cell>
    <div class="invoice-description-text">
      <span v-for="(line, index) in description.split('\n')" :key="index">
        {{ line }}
      </span>
    </div>
    <van-popup
      v-model="invoiceApplySuccessInfoShow"
      class="invoice-apply-success-info"
    >
      <van-row type="flex" justify="center" class="info-title-container">
        <van-col class="info-title-text">发票申请成功</van-col>
      </van-row>
      <div class="info-content">
        将以下资料发送至客户邮箱，点击文件名可在线查看分享
      </div>
      <van-row type="flex" justify="space-between" class="info-detail-link">
        <van-row type="flex" justify="space-between" class="link-button">
          <van-icon name="notes-o" size="17" />
          电子保单
        </van-row>
        <van-row type="flex" justify="space-between" class="link-button">
          <van-icon name="notes-o" size="17" />
          保险条款
        </van-row>
      </van-row>
      <input class="info-email-input" placeholder="请输入电子邮箱" />
      <div class="info-download-button" @click="onDownloadClick">下载</div>
    </van-popup>
    <van-popup
      v-model="invoiceApplySuccessTipShow"
      class="invoice-apply-success-tip"
    >
      <van-row type="flex" justify="center" class="info-title-container">
        <van-col class="info-title-text">发票申请成功</van-col>
      </van-row>
      <div class="tip-content">
        电子发票短链接稍后将发送至您的手机，您也可以开票详情查看已开发票
      </div>
      <div class="tip-button" @click="invoiceApplySuccessTipShow = false">
        我知道了
      </div>
    </van-popup>
  </div>
</template>

<script>
import { Button, Popup } from "vant";
import InvoiceInformation from "./invoiceInformation";
import InvoiceInfoInput from "./invoiceInformationInput";

export default {
  components: {
    [Popup.name]: Popup,
    [Button.name]: Button,
    [InvoiceInfoInput.name]: InvoiceInfoInput,
    [InvoiceInformation.name]: InvoiceInformation
  },
  data: () => ({
    invoiceApplySuccessInfoShow: false,
    invoiceApplySuccessTipShow: false,
    info: {
      header: "上海XXXX科技有限公司",
      code: "ABS8 7263 2636 6326 66",
      phone: "13112112111",
      email: "zzlnln@173.com"
    },
    sendEmail: "17718677581",
    status: 2,
    title: "",
    subTitle: "",
    description: ""
  }),
  created() {
    // 1 未申请 2 已申请
    switch (this.status) {
      case 1: {
        this.title = "申请开票";
        this.subTitle =
          "补开电子发票，完善以下信息后，电子发票链接将发送至客户手机";
        this.description =
          "说明：\n1、暂未查询到电子发票信息，若需开具电子发票，完善以上信息\n2、如果已经申请其他类型发票，重新申请电子发票将对冲掉纸质发票。";
        break;
      }
      case 2: {
        this.title = "发票申请中";
        this.subTitle = "发票已申请，请等待财务处理";
        this.description =
          "如果已申请开票，但客户反映未收到发票，可重新发送客户手机";
        break;
      }
      default:
        break;
    }
  },
  methods: {
    onClick() {},
    onSendClick() {},
    onDownloadClick() {}
  }
};
</script>

<style lang="less" scoped>
.title-container {
  background: #3b8afe0d;
  padding: 15px;
}
.title-text {
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  line-height: 22px;
  margin-bottom: 5px;
}
.title-sub-text {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 19px;
}

.invoice-apply-button-container {
  padding: 14px 0 10px 0;
}
.invoice-apply-button {
  width: 139px;
  height: 40px;
  background: linear-gradient(125deg, #1e46f6 0%, #279aff 100%);
  border-radius: 23px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  line-height: 40px;
  text-align: center;
  background-color: #f7f7f7;
}
.group-title-container {
  padding: 10px 15px;
  background: #f7f7f7;
}
.group-title-text {
  width: 60px;
  height: 21px;
  font-size: 15px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  line-height: 21px;
}
.send-email {
  width: 82px;
  height: 20px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #666666;
  line-height: 20px;
}
.send-button {
  width: 70px;
  height: 27px;
  background: white;
  border-radius: 14px;
  border: 1px solid #3b8afe;
  line-height: 27px;
  text-align: center;
  font-size: 11px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #3b8afe;
  padding: 0;
  white-space: nowrap;
  margin-left: 14px;
}

.invoice-description-text {
  background-color: #f7f7f7;
  font-size: 12px;
  font-family: PingFang-SC-Regular, PingFang-SC;
  font-weight: 400;
  color: #828282;
  line-height: 17px;
  padding: 15px;
}
.invoice-apply-success-info {
  width: 315px;
  height: 318px;
  background: white;
  border-radius: 5px;

  .info-content {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 25px;
    margin: 13px 20px;
  }
  .info-detail-link {
    margin: 0 54px;
    .link-button {
      width: 80px;
      height: 20px;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #3b8afe;
      line-height: 20px;
    }
  }
  .info-email-input {
    width: 225px;
    margin: 20px 25px;
    padding: 13px 20px;
    background: white;
    border-radius: 21px;
    border: 1px solid #f2f2f2;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #c1c1c1;
    line-height: 20px;
  }
  .info-download-button {
    width: 265px;
    height: 46px;
    margin: 0 25px 0 25px;
    background: #3b8afe;
    border-radius: 23px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: white;
    line-height: 46px;
    text-align: center;
  }
}
.info-title-container {
  margin: 10px 20px 0px 20px;
  padding: 10px 0;
  border-bottom: 1px solid #e5e6e8;
}
.info-title-text {
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #323233;
  line-height: 20px;
}
.invoice-apply-success-tip {
  width: 315px;
  height: 213px;
  background: white;
  border-radius: 5px;
  .tip-content {
    width: 265px;
    height: 59px;
    margin: 20px 25px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 25px;
  }
  .tip-button {
    width: 275px;
    height: 46px;
    margin: 0 25px;
    background: #3b8afe;
    border-radius: 23px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: white;
    line-height: 46px;
    text-align: center;
  }
}
</style>
