<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-21 20:47:49
 * @Last Modified by: King
 * @Last Modified time: 2020-03-31 22:45:57
 * @FilePath: \vue-sales\src\views\nonCar\payment\paymentByAlipayMessage.vue
-->
<template>
  <div id="super">
    <van-sticky>
      <nav-bar
        type="vehicle"
        title="支付宝短信支付"
        clear="1"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div class="content">
      <div id="topTitle">请选择接收短信手机号码</div>
      <div id="center">
        <div class="father">
          <van-checkbox
            v-model="checkedInsured"
            @change="v => changePhoneNum(v, 'insured')"
          ></van-checkbox>
          <div class="subCenter">
            <div class="centerTitle">投保人</div>
            <div class="centerDetail">手机号码</div>
          </div>
          <div class="rightText">{{ mobileNo }}</div>
        </div>
        <div class="father">
          <van-checkbox
            v-model="checkedBeInsured"
            @change="v => changePhoneNum(v, 'beInsured')"
          ></van-checkbox>
          <div class="subCenter">
            <div class="centerTitle">被保人</div>
            <div class="centerDetail">手机号码</div>
          </div>
          <div class="rightText">{{ insuredMobile }}</div>
        </div>
        <div class="father last">
          <van-checkbox
            v-model="checkedMobile"
            @change="v => changePhoneNum(v, 'input')"
          ></van-checkbox>
          <div class="subCenter">
            <div class="centerTitle">其他</div>
            <div class="centerDetail">手机号码</div>
          </div>
          <div class="rightText">
            <input
              type="text"
              placeholder="请输入"
              v-model="inputMobile"
            />
          </div>
        </div>
      </div>
      <div id="bottomMsg">
        <div>短信内容</div>
        <div style="width:300px">{{ message }}</div>
      </div>
      <div
        id="sendMsg"
        @click="sendMessage"
      >发送短信</div>
    </div>
  </div>
</template>

<script>
import { getPayUrl } from "@src/http/module/nonCar";
import { Toast } from "vant";
export default {
  name: "paymentRecordCode",
  components: {},
  data () {
    return {
      phoneNum: "",
      checkedInsured: true,
      checkedBeInsured: false,
      checkedMobile: false,
      selectBox: {
        background: "#3B8AFE",
        borderColor: "#3B8AFE"
      },
      unSelectBox: {
        background: "white",
        borderColor: "#C8C9CC"
      },
      message: "",
      inputMobile: "", //输入手机号
      proposalNo: this.$route.query.proposalNo, //投保单号
      productName: this.$route.query.productName, //产品
      applicantName: this.$route.query.applicantName, //投保人姓名
      paymentNo: this.$route.query.paymentNo,
      checkNo: this.$route.query.checkNo,
      sumPremium: this.$route.query.sumPremium,
      mobileNo: this.$route.query.mobileNo, //投保人手机号码
      insuredName: this.$route.query.insuredName, //被投保人姓名
      insuredMobile: this.$route.query.insuredMobile //被投保人电话
    };
  },

  created () {

    let params = {
      proposalNo: this.proposalNo,
      paymentNo: this.paymentNo,
      checkNo: this.checkNo,
      flag: "5"//5：支付宝支付  获取支付宝短信地址
    };
    getPayUrl(params).then(
      res => {
        if (res.code === "1" && res.data) {
          console.log(res.data.url);
          console.log("TODO suncao 跳转支付宝短信支付111111111");
          //TODO suncao 跳转支付宝短信支付链接地址
          if (res.code === "1" && res.data) {
            var url = res.data.url;
            if (this.productName) {
              this.message =
                "【中国大地保险】尊敬的客户，您的" +
                this.productName +
                "投保订单已生成，保费合计为" +
                this.sumPremium +
                "元，请点击链接 " +
                url +
                "  使用支付宝完成保费支付。";
            } else {
              this.message =
                "【中国大地保险】尊敬的客户，您的爱车" +
                (this.licenseNo ? "<" + this.licenseNo + ">" : "") +
                "投保订单已生成，保费合计为" +
                this.totalPremium +
                "元，请点击链接 " +
                url +
                "  使用支付宝完成保费支付。";
            }
          }
        }
      },
      error => { }
    );
  },
  methods: {
    changePhoneNum (v, type) {
      if (v) {
        switch (type) {
          case "insured": {
            this.checkedBeInsured = false;
            this.checkedMobile = false;
            break;
          }
          case "beInsured": {
            this.checkedInsured = false;
            this.checkedMobile = false;
            break;
          }
          case "input": {
            this.checkedInsured = false;
            this.checkedBeInsured = false;
            break;
          }
        }
      }
    },
    sendMessage: function () {
      //console.log("发送短信");
      if (!this.checkedMobile && !this.checkedInsured && !this.checkedBeInsured) {
        return Toast("请选择手机号码");
      }
      if (
        this.checkedMobile &&
        !this.checkedInsured &&
        !this.checkedBeInsured &&
        !this.inputMobile
      ) {
        return Toast("手机号码不能为空");
      }
      if (
        !this.checkedMobile &&
        this.checkedInsured &&
        !this.checkedBeInsured &&
        !this.mobileNo
      ) {
        return Toast("投保人手机号码不能为空");
      }
      if (
        !this.checkedMobile &&
        !this.checkedInsured &&
        this.checkedBeInsured &&
        !this.insuredMobile
      ) {
        return Toast("被保人手机号码不能为空");
      }
      if (this.checkedMobile && this.inputMobile) {
        this.phoneNum = this.inputMobile;
      }
      if (this.checkedInsured && this.mobileNo) {
        this.phoneNum = this.mobileNo;
      }
      if (this.checkedBeInsured && this.insuredMobile) {
        this.phoneNum = this.insuredMobile;
      }
      if (!/^1[3456789]\d{9}$/.test(this.phoneNum)) {
        return Toast("手机号格式不正确");
      }
      //调用接口参数发送短信
      let jsonObject = {
        messageDes: this.message, //分享描述
        phoneNo: this.phoneNum //电话号码,当需要短信分享时获取
      };
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.sendMessage(jsonObject, data => {
        this.$toast("sendMessage 接口调用成功，壳响应数据:" + data);
      });
    }
  }
};
</script>

<style scoped>
body {
  width: 100%;
  /* height: 100%; */
  /* position: absolute; */
  background: #f7f7f7;
}
#topTitle {
  height: 39px;
  text-align: left;
  font-size: 14px;
  font-weight: 400;
  padding-left: 15px;
  font-size: 15px;
  color: #666666;
  width: calc(100% - 15px);
  line-height: 39px;
}
#text {
  width: 300px !important;
}
#center {
  width: calc(100% - 30px);
  height: 232px;
  background: white;
  margin: 0 15px;
  border-radius: 5px;
}
.father {
  margin: 0px 20px;
  height: 77px;
  width: calc(100% - 40px);
  border-bottom: 1px solid #e5e6e8;
  display: flex;
  justify-content: left;
}
.last {
  border-bottom: none;
}

/* .checkBox, */
.subCenter,
.rightText {
  float: left;
  text-align: left;
}

.subCenter {
  color: rgba(102, 102, 102, 1);
  width: 72px;
  height: 100%;
  padding: 20px 0 15px 8px;
}

.centerTitle {
  font-size: 16px;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
}
.centerDetail {
  height: 22px;
  font-size: 13px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 22px;
}

.rightText {
  color: rgba(153, 153, 153, 1);
  line-height: 52px;
  font-size: 13px;
  font-weight: 400;
  padding-top: 26px;
}

input {
  width: 100%;
  text-align: left;
  height: 52px;
  line-height: 52px;
  border-radius: 0px;
  border-width: 0px;
  overflow: hidden;
  border: 0;
  outline: none;
  font-size: 13px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}

input::-webkit-input-placeholder {
  color: rgba(153, 153, 153, 1);
  font-size: 13px;
  text-align: left;
}

#bottomMsg {
  margin: 15px;
  padding: 18px;
  background: rgba(255, 255, 255, 1);
  border-radius: 5px;
  text-align: left;
  font-size: 14px;
  font-family: PingFangHK-Regular, PingFangHK;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  word-wrap: break-word;
  word-break: break-all;
  /* line-height: 24px; */
}
#bottomMsg > :first-child {
  height: 20px;
  font-size: 14px;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  /* line-height: 20px; */
  margin-bottom: 8px;
}

#sendMsg {
  position: absolute;
  z-index: 99;
  bottom: 15px;
  left: 15px;
  width: calc(100% - 30px);
  height: 46px;
  font-size: 16px;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 46px;
  background: rgba(59, 138, 254, 1);
  border-radius: 23px;
  text-align: center;
}

input {
  border: 0;
  background: transparent;
}
</style>
