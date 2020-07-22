<template>
  <div class="customerClaimProgress">
    <div v-if="dataStatus">
      <div
        :style="{ backgroundImage: 'url(' + $imageUrl.LPXQBG + ')' }"
        class="BGStyle"
      >
        <van-nav-bar
          fixed
          title=""
          :left-arrow="leftArrow"
          @click-left="clickBack"
          style="background: transparent"
        />
        <img :src="$imageUrl.cardLogo" alt="" class="imgTipStyle" />
      </div>
      <div class="contenView">
        <img
          :src="processedIndex >= 1 ? $imageUrl.yibaoan : $imageUrl.weibaoan"
          alt=""
          class="centerA"
        />
        <div
          :style="processedIndex >= 1 ? finishedTextStyle : unfinishedTextStyle"
        >
          报案
        </div>
        <div
          class="centerB"
          :style="processedIndex >= 1 ? finishedStyle : unfinishedStyle"
        ></div>
        <img
          :src="processedIndex >= 2 ? $imageUrl.yiweixiu : $imageUrl.weidingsun"
          alt=""
          class="centerA"
        />
        <div
          :style="processedIndex >= 2 ? finishedTextStyle : unfinishedTextStyle"
        >
          定损
        </div>
        <div
          class="centerB"
          :style="processedIndex >= 1 ? finishedStyle : unfinishedStyle"
        ></div>
        <img
          :src="processedIndex >= 3 ? $imageUrl.yizhifu : $imageUrl.weizhifu"
          alt=""
          class="centerA"
        />
        <div
          :style="processedIndex >= 3 ? finishedTextStyle : unfinishedTextStyle"
        >
          支付
        </div>
        <div
          class="centerB"
          :style="processedIndex >= 1 ? finishedStyle : unfinishedStyle"
        ></div>
        <img
          :src="processedIndex >= 4 ? $imageUrl.yijiean : $imageUrl.weijiean"
          alt=""
          class="centerA"
        />
        <div
          :style="processedIndex >= 4 ? finishedTextStyle : unfinishedTextStyle"
        >
          结案
        </div>
      </div>

      <div class="titleView">
        <div
          class="bgIMGView"
          :style="{ backgroundImage: 'url(' + $imageUrl.centenBG + ')' }"
        >
          <div class="textBAStyle">案件号：{{ registNo }}</div>
          <div class="textBAStyle">车牌号：{{ carNo }}</div>
          <div class="textBAStyle">被保人：{{ insuredName }}</div>
        </div>
      </div>

      <div class="bodyView">
        <div class="itemStyle">
          <span class="downLeft">报案时间</span>
          <span class="downRight">{{ reportDate }}</span>
        </div>
        <div class="itemStyle">
          <span class="downLeft">查勘时间</span>
          <span class="downRight">{{ surveyTime }}</span>
        </div>
        <div class="itemStyle">
          <span class="downLeft">定损时间</span>
          <span class="downRight">{{ lossEndTime }}</span>
        </div>
        <div class="lineView" />
        <div class="itemStyle" v-if="sumDefloss">
          <span class="downLeft">车辆损失总金额</span>
          <span class="downRight">{{ sumDefloss || 0.0 }}元</span>
        </div>
        <div class="itemStyle" v-if="sumVeriLoss">
          <span class="downLeft">财产损失总金额</span>
          <span class="downRight">{{ sumVeriLoss || 0.0 }}元</span>
        </div>
        <div class="itemStyle" v-if="sumPersonLoss">
          <span class="downLeft">人身损失总金额</span>
          <span class="downRight">{{ sumPersonLoss || 0.0 }}元</span>
        </div>
        <div class="itemStyle" v-if="lossTotal">
          <span class="downLeft">定损总金额</span>
          <span class="downRight">{{ lossTotal || 0.0 }}元</span>
        </div>
        <div class="itemStyle" v-if="realpayDate">
          <span class="downLeft">支付时间</span>
          <span class="downRight">{{ realpayDate }}</span>
        </div>
        <div class="itemStyle" v-if="realPayFee">
          <span class="downLeft">支付到账总金额</span>
          <span class="downRight" style="color: #E9BD7C"
            >{{ realPayFee || 0.0 }}元</span
          >
        </div>
      </div>
    </div>

    <div v-else>
      <div class="queryRemindView_noData">
        <img
          :src="this.$imageUrl.pastDueImg"
          alt=""
          class="queryRemindView_img"
        />
      </div>
    </div>

    <div v-if="processedIndex != 4">
      <div class="shareView" @click="shareToCustomer" v-if="leftArrow">
        分享给客户
      </div>
    </div>
  </div>
</template>

<script>
// 理赔详情 理赔进度公共页面
import {
  getQueryIndemnifyDetails,
  doneTaskClick
} from "@src/http/module/customerApi.js";
import { Toast } from "vant";
import { getShareUrl } from "../../../http/module/customerApi";
import { mapState } from "vuex";
import { formatDate, getDayNum } from "../../../utils";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "customerClaimProgress",
  props: {
    title: String
  },
  created() {
    this.requestDatas();
  },

  computed: {
    ...mapState(["customerData"]),
    temp_policyNo() {
      return this.$GetRequest("policyNo", true)
        ? this.$GetRequest("policyNo", true)
        : this.$route.query.item.policyNo;
    },
    temp_registNo() {
      return this.$GetRequest("registNo", true)
        ? this.$GetRequest("registNo", true)
        : this.$route.query.item.registNo;
    },
    leftArrow() {
      return this.$GetRequest("shareIsType", true) == "1" ? false : true;
    },
    temp_userCode() {
      return this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode");
    },
    dataStatus() {
      return getDayNum(
        formatDate(+this.$GetRequest("shareData", true) || new Date().getTime())
      ) < 8
        ? true
        : false;
    }
  },

  methods: {
    clickBack() {
      this.$router.back();
    },
    async shareToCustomer() {
      const { policyNo, registNo } = this.$route.query.item;
      let tempUrl =
        this.$getIP() +
        "/customer/customerClaimProgress?shareIsType=1" +
        "&policyNo=" +
        policyNo +
        "&registNo=" +
        registNo +
        "&userCode=" +
        localStorage.getItem("userCode") +
        "&shareData=" +
        new Date().getTime();
      let res = await getShareUrl({ url: tempUrl });
      if (res.code == "1") {
        let jsonObject = {
          sharePlatform: ["0", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: `[${this.carNo}]` + "理赔详情", //分享标题
          imgURL:
            "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
          image: "", //分享图片（base64编码）, 与imgURL二选一
          url: res.data.shortUrl, //分享链接
          desc: "尊敬的客户，您的理赔正在火速处理中，可在线查询进度。", //分享描述
          phoneNum: "" //电话号码,当需要短信分享时获取
        };

        this.$native.share(jsonObject, data => {
          //Toast("share 接口调用成功，壳响应数据:" + data);
          let res = JSON.parse(data);
          if (res.resultCode == "1") {
            const { custNo, id } = this.$route.query.item;
            let params = {
              remindId: id
            };
            doneTaskClick(params);
            //埋点
            trackMap(
              "c_customer_claim_progress_share",
              "客户管理理赔进度分享",
              {
                c_salesperson_type: "N",
                c_market_type: "N",
                c_market_name: "",
                c_event_category: "客户管理"
              }
            );
            console.log("分享给客户");
            //刷新跟进提醒页面
            let params_ = {
              userCode: localStorage.getItem("userCode"), //业务员号
              custNo: "", //客户号
              remindType: this.customerData.active //提醒类型
            };
            this.$store.dispatch("customerData/getfollowRemindDatat", params_);
            this.$router.back();
          }
        });
      }
    },
    async requestDatas() {
      let params = {
        policyNo: this.temp_policyNo, //保单号
        registNo: this.temp_registNo,
        userCode: this.temp_userCode
      };
      let res = await getQueryIndemnifyDetails(params);
      if (res.code == "1") {
        var response = JSON.parse(JSON.stringify(res.data));
        this.reportDate = response.reportDate;
        this.surveyTime = response.surveyTime;
        this.lossEndTime = response.lossEndTime;
        this.sumDefloss = response.sumDefloss > 0 ? response.sumDefloss : 0;
        this.sumVeriLoss = response.sumVeriLoss > 0 ? response.sumVeriLoss : 0;
        this.sumPersonLoss =
          response.sumPersonLoss > 0 ? response.sumPersonLoss : 0;
        this.lossTotal = response.lossTotal > 0 ? response.lossTotal : 0;
        this.realpayDate = response.realpayDate;
        this.realPayFee = response.realPayFee;
        this.carNo = response.carNo;
        this.insuredName = response.insuredName;
        this.registNo = response.registNo;
        this.statusList = response.statusList;
        if (response.status == "10") {
          this.processedIndex = 4;
        } else if (response.status == "09") {
          this.processedIndex = 3;
        } else if (response.status == "08") {
          this.processedIndex = 2;
        } else if (response.status == "07") {
          this.processedIndex = 1;
        } else {
          this.processedIndex = 0;
        }
      }
    }
  },

  data() {
    return {
      // tips: "温馨提示：正在火速处理，请安心等待。",
      finishedStyle: {
        //已完成的样式
        borderBottomColor: "#3B8AFE",
        borderBottomWidth: "1px",
        borderBottomStyle: "dashed"
      },
      unfinishedStyle: {
        //未完成的样式
        borderBottomColor: "#D0D0D0",
        borderBottomWidth: "1px",
        borderBottomStyle: "dashed"
      },
      finishedTextStyle: {
        //已完成的样式
        color: "#3B8AFE"
      },
      unfinishedTextStyle: {
        //未完成的样式
        color: "#D0D0D0"
      },
      registNo: "", //案件号
      carNo: "", //车牌号
      insuredName: "", //被保人
      statusList: [], //状态列表；
      processedIndex: 0, //处理进度

      reportDate: "", //报案时间
      surveyTime: "", //查勘时间
      lossEndTime: "", //定损时间
      sumDefloss: "", //车辆损失总金额
      sumVeriLoss: "", //财产损失总金额
      sumPersonLoss: "", //人身损失总金额
      lossTotal: "", //定损总金额
      realpayDate: "", //支付时间
      realPayFee: "" //支付到账总金额
    };
  }
};
</script>
<style scoped lang="less">
.customerClaimProgress {
  background-color: white;
  display: flex;
  flex-direction: column;

  .shareView {
    width: 100%;
    height: 46px;
    background: linear-gradient(to right, #2a80ff, #2aa5ff);
    color: white;
    font-size: 16px;
    align-items: center;
    display: flex;
    justify-content: center;
    position: absolute;
    bottom: 0px;
  }
}

.BGStyle {
  width: 100vw;
  background-size: contain;
  height: 134px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;

  .imgTipStyle {
    width: 136px;
    height: 72px;
    background-size: contain;
    position: absolute;
    top: 20px;
    right: 32px;
  }
}

.bodyView {
  justify-content: center;
  display: flex;
  flex-direction: column;

  .itemStyle {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin: 3px 25px;
  }
}

.contenView {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: -10px;
  background-color: #ffffff;
}

.titleView {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 10px 0px;

  .bgIMGView {
    width: 344px;
    height: 101px;
    background-size: contain;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;

    .textBAStyle {
      margin: 5px 10px;
      color: #333333;
      font-size: 14px;
    }
  }
}

.lineView {
  height: 1px;
  background-color: #f0f0f0;
  margin: 10px 25px;
}

.centerA {
  width: 19px;
  height: 19px;
  background-size: contain;
  margin: 0px 5px;
}

.centerB {
  width: 20px;
  margin: 13.5px 0;
  height: 1px;
  margin-left: 5px;
}

.downLeft {
  color: #454545;
  font-size: 14px;
}

.downRight {
  color: #7a7a7a;
  font-size: 14px;
}

.van-nav-bar .van-icon {
  color: white;
}

.van-nav-bar__text {
  color: white;
}

.van-nav-bar__title {
  color: white;
  font-size: 18px;
  font-family: PingFangSC-Medium, PingFang SC;
}

.van-hairline--bottom::after {
  border-bottom-width: 0px;
}

.queryRemindView_noData {
  width: 100%;
  background: #f7f7f7;
  margin-top: 5%;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh);
  display: flex;
  justify-content: center;
  align-items: center;
}

.queryRemindView_img {
  width: 50%;
  height: 30%;
  background-size: contain;
}
</style>
