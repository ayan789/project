<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-21 20:47:49
 * @Last Modified by: King
 * @Last Modified time: 2020-06-28 16:14:42
 * @FilePath: /src/views/vehicle/payment/paymentByAlipayMessage.vue
-->
<template>
  <div id="super">
    <nav-bar
      type="vehicle"
      title="支付宝短信支付"
      clear="1"
      @clickBack="$router.back()"
    />
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
          <div class="rightText">{{ insuredMobile }}</div>
        </div>
        <div class="father">
          <van-checkbox
            v-model="checkedBeInsured"
            @change="v => changePhoneNum(v, 'beInsured')"
          />
          <div class="subCenter">
            <div class="centerTitle">被保人</div>
            <div class="centerDetail">手机号码</div>
          </div>
          <div class="rightText">{{ beInsuredMobile }}</div>
        </div>
        <div class="father last">
          <van-checkbox
            v-model="checkedMobile"
            @change="v => changePhoneNum(v, 'input')"
          />
          <div class="subCenter">
            <div class="centerTitle">其他</div>
            <div class="centerDetail">手机号码</div>
          </div>
          <div class="rightText">
            <input
              type="text"
              placeholder="请输入"
              v-model="inputMobile"
              maxlength="11"
            />
          </div>
        </div>
      </div>
      <div id="bottomMsg">
        <div>短信内容</div>
        <div>{{ message }}</div>
      </div>
    </div>
    <div class="bottom-button">
      <div id="sendMsg" @click="sendMessage">发送短信</div>
    </div>
  </div>
</template>

<script>
import { getPaymentUrl, sendMessage } from "@src/http/module/vehicle";
import { mapState } from "vuex";
import { Toast } from "vant";

export default {
  name: "paymentRecordCode",
  components: {},
  data() {
    return {
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
      inputMobile: "" //输入手机号
    };
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      insuredMobile: ({ policyHolder: { phone } }) => phone,
      beInsuredMobile: ({ recognizee: { phone } }) => phone,
      totalPremium: ({ insurancePlan }) => insurancePlan.totalPremium,
      name: ({ carOwner: { name } }) => name,
      lisenceNo: ({ car: { lisenceNo } }) => lisenceNo
    })
  },
  created() {
    let params = { orderId: this.orderId, flag: "5" };
    getPaymentUrl(params).then(
      res => {
        if (res.code === "1" && res.data) {
          // window.location.href = res.data.url;
          let productName = "";
          if (productName) {
            this.message =
              "尊敬的客户，您的" +
              productName +
              "投保订单已生成，保费合计为" +
              this.totalPremium +
              "元，请点击链接 " +
              res.data.url +
              " 使用支付宝完成保费支付。";
          } else {
            this.message =
              "尊敬的客户，您的爱车" +
              (this.licenseNo ? "[" + this.licenseNo + "]" : "") +
              "投保订单已生成，保费合计为" +
              this.totalPremium +
              "元，请点击链接 " +
              res.data.url +
              " 使用支付宝完成保费支付。";
          }
        }
      },
      error => {}
    );
  },
  methods: {
    changePhoneNum(v, type) {
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
    phoneNum() {
      // console.log(this.checkedInsured, this.checkedBeInsured, this.inputMobile);
      if (this.checkedInsured) return this.insuredMobile;
      if (this.checkedBeInsured) return this.beInsuredMobile;
      return this.inputMobile;
    },
    sendMessage: function() {
      //console.log("发送短信");
      if (this.$isNull(this.phoneNum())) {
        Toast("手机号码不能为空");
        return;
      }
      const { status, msg } = this.$validate({required: "手机号码", mobile: "不符合规则"}, this.phoneNum())
      if (!status) {
        Toast("手机号码不符合规则");
        return;
      }
      //调用接口参数
      const params = {
        content: this.message, //短信模板
        phoneNo: this.phoneNum() //接收人手机号
      };
      sendMessage(params).then(res => {
        if (res.code == 1) {
          Toast("发送成功");
        }
      }).catch(error => {

      })
      // //调用壳方法，传递调用参数和接受响应数据
      // this.$native.sendMessage(jsonObject, data => {
      //   // Toast("share 接口调用成功，壳响应数据:" + data);
      // });
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
.content {
  width: 100%;
  height: calc(100% - 77px - 84px);
  overflow: scroll;
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
  line-height: 24px;
}
#bottomMsg > :first-child {
  height: 20px;
  font-size: 14px;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  margin-bottom: 8px;
}

.bottom-button {
  width: 100%;
  height: 44px;
  padding: 20px;
}

#sendMsg {
  width: calc(100% - 30px);
  height: 44px;
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
