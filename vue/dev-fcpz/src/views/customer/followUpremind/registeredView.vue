<template>
  <div class="queryRemindView">
    <!--    <Global_globalNav title="权益提醒" @clickBack="clickBack" clear="2"/>-->
    <navigationItem title="权益提醒" :leftArrow="leftArrow" />
    <div class="queryRemindView_contentStyle" v-if="dataStatus">
      <div>
        <!--        <img :src="$imageUrl.qyBG" alt=""-->
        <!--             class="queryRemindView_imgView">-->
        <div
          class="queryRemindView_imgView"
          :style="{ backgroundImage: 'url(' + $imageUrl.qyBG + ')' }"
        >
          <div class="queryRemindView_text1">客户经理{{ userName }}提醒您</div>
          <div class="queryRemindView_title1">
            <div class="queryRemindView_text">有</div>
            <div class="queryRemindView_title2">尊享服务</div>
            <div class="queryRemindView_text">即将到期</div>
          </div>
        </div>
        <div
          class="queryRemindView_contentItem"
          v-for="(item, index) in bbrList"
        >
          <div class="titleStyle">{{ item.bbrCustName }}尊享服务礼包</div>
          <div
            class="queryRemindView_cardStyle"
            v-for="(v, i) in item.interestList_1"
          >
            <div class="queryRemindView_itemCard">
              <div class="queryRemindView_cardLeft">
                <div class="queryRemindView_boderStyle">
                  <img
                    :src="$imageUrl.tipicon"
                    alt=""
                    style="width:44px;height: 44px "
                  />
                </div>
              </div>
              <div class="queryRemindView_inlineStle" />
              <div class="queryRemindView_cardRight">
                <div style="font-size: 16px;color: #1D3557;font-weight: bold">
                  {{ v.equitiesName }}
                </div>
                <div>{{ v.startTime }}-{{ v.endTime }}</div>
              </div>
            </div>
          </div>

          <div
            class="queryRemindView_cardStyle"
            v-if="item.showStatus"
            v-for="(v, i) in item.interestList_2"
          >
            <div class="queryRemindView_itemCard">
              <div class="queryRemindView_cardLeft">
                <div class="queryRemindView_boderStyle">
                  <img
                    :src="$imageUrl.tipicon"
                    alt=""
                    style="width:44px;height: 44px "
                  />
                </div>
              </div>
              <div class="queryRemindView_inlineStle" />
              <div class="queryRemindView_cardRight">
                <div style="font-size: 16px;color: #1D3557;font-weight: bold">
                  {{ v.equitiesName }}
                </div>
                <div>{{ v.startTime }}-{{ v.endTime }}</div>
              </div>
            </div>
          </div>

          <div
            class="queryRemindView_icon"
            @click="showClick(index)"
            v-if="item.showIcon"
          >
            <van-icon :name="item.showStatus ? 'arrow-up' : 'arrow-down'" />
          </div>
        </div>
        <div class="tipView">
          <div class="queryRemindView_title" style="margin:10px">领取方式</div>
          <div class="queryRemindView_title" style="font-size: 12px">
            长按扫描下方二维码，关注公众号
          </div>
          <div class="queryRemindView_title" style="font-size: 12px">
            在公众号"增值服务>星级权益"中查看使用
          </div>
          <!--          <img :src="$localStorage.recommendCode" alt="" class="queryRemindView_userNameIcon">-->
          <div class="queryRemindView_userNameIcon">
            <div id="qrcode" ref="qrcode"></div>
          </div>
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
    <div
      class="queryRemindView_buttonStyle"
      @click="submitClick"
      v-if="leftArrow"
    >
      <span class="buttonText">分享给客户</span>
    </div>
  </div>
</template>

<script>
import {
  getQueryRightsRemind,
  doneTaskClick,
  getShareUrl
} from "../../../http/module/customerApi";
import navigationItem from "./navigationItem";
import QRCode from "qrcodejs2";
import { mapState } from "vuex";
import { formatDate, getDayNum } from "../../../utils/index";
import { trackMap } from "@src/utils/index.js";
export default {
  data() {
    return {
      bbrList: [],
      activeName: ["1"],
      bbrList_: []
    };
  },

  props: {
    item: Object
  },

  computed: {
    ...mapState(["customerData"]),
    recommendCode() {
      return decodeURIComponent(this.$GetRequest("recommendCode", true) + "")
        ? decodeURIComponent(this.$GetRequest("recommendCode", true) + "")
        : this.$localStorage.recommendCode;
    },
    temp_userCode() {
      return this.$GetRequest("userCode", true)
        ? this.$GetRequest("userCode", true)
        : localStorage.getItem("userCode");
    },
    temp_custNo() {
      return this.$GetRequest("custNo", true)
        ? this.$GetRequest("custNo", true)
        : this.$route.query.item.custNo;
    },
    leftArrow() {
      return this.$GetRequest("shareIsType", true) == "1" ? false : true;
    },
    userName() {
      return decodeURI(this.$GetRequest("userName", true) + "")
        ? decodeURI(this.$GetRequest("userName", true) + "")
        : this.$localStorage.userName;
    },
    dataStatus() {
      return getDayNum(
        formatDate(+this.$GetRequest("shareData", true) || new Date().getTime())
      ) < 8
        ? true
        : false;
    }
  },

  mounted() {
    this.qrcode = new QRCode("qrcode", {
      text: encodeURI(this.recommendCode),
      width: 110,
      height: 110
    });
  },

  components: {
    navigationItem
  },
  async created() {
    let params = {
      userCode: this.temp_userCode,
      custNo: this.temp_custNo
    };
    let res = await getQueryRightsRemind(params);
    if (res.code == "1") {
      this.bbrList = res.data.bbrList;
      this.bbrList.map((item, index) => {
        item.showStatus = false;
        if (item.interestList.length < 3) {
          item.interestList_1 = item.interestList;
          item.showIcon = false;
        } else {
          item.interestList_1 = item.interestList.slice(0, 2);
          item.interestList_2 = item.interestList.slice(
            2,
            item.interestList.length
          );
          item.showIcon = true;
        }
      });
    }
    console.log(this.bbrList);
  },
  methods: {
    showClick(index) {
      this.bbrList.map((v, i) => {
        if (i == index) {
          v.showStatus = !v.showStatus;
        } else {
          v.showStatus = v.showStatus;
        }
      });
      this.$forceUpdate();
    },
    clickBack() {
      this.$router.back();
    },
    //分享给客户
    async submitClick() {
      const { recommendCode, userName, userCode } = this.$localStorage;
      const { custNo, id } = this.$route.query.item;
      let tempUrl =
        this.$getIP() +
        "/customer/registeredView?shareIsType=1" +
        "&userCode=" +
        userCode +
        "&custNo=" +
        custNo +
        "&userName=" +
        encodeURI(userName) +
        "&recommendCode=" +
        encodeURI(recommendCode) +
        "&shareData=" +
        new Date().getTime();
      console.log(tempUrl, "tempUrl====");
      let res = await getShareUrl({ url: tempUrl });
      if (res.code == "1") {
        let jsonObject = {
          sharePlatform: ["0", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: "权益提醒", //分享标题
          imgURL:
            "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
          image: "", //分享图片（base64编码）, 与imgURL二选一
          url: res.data.shortUrl, //分享链接
          desc: "尊敬的客户，您的专属经理提醒您，有尊享服务即将到期。", //分享描述
          phoneNum: "" //电话号码,当需要短信分享时获取
        };
        this.$native.share(jsonObject, data => {
          let res = JSON.parse(data);
          if (res.resultCode == "1") {
            const { custNo, id } = this.$route.query.item;
            let params = {
              remindId: id
            };
            doneTaskClick(params);
            console.log("分享给客户");
            //埋点
            trackMap("c_customer_serve_rights_remind", "客户服务权益提醒", {
              c_salesperson_type: "Y",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "客户管理"
            });
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
    }
  }
};
</script>

<style lang="less" scoped>
.queryRemindView {
  position: fixed;
  width: 100%;
}

.queryRemindView_contentStyle {
  width: 100%;
  background: #f7f7f7;
  margin-top: 65px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 110px);
  display: flex;
  justify-content: center;

  .tipView {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-bottom: 40px;
  }
}

.queryRemindView_imgView {
  width: 100vw;
  height: 172px;
  background-size: contain;
}

.queryRemindView_buttonStyle {
  width: 100vw;
  display: flex;
  flex-direction: row;
  justify-content: center;
  background-color: #3b8afe;
  align-items: center;
  position: fixed;
  bottom: 0;
  height: 46px;

  .buttonText {
    font-size: 16px;
    color: #ffffff;
  }
}

.queryRemindView_contentItem {
  background-color: white;
  margin: 10px 10px;
  padding: 13px 10px;
  top: -50px;
  position: relative;
  border-radius: 10px;

  .titleStyle {
    font-size: 14px;
    font-weight: bold;
    color: #333333;
    margin-bottom: 10px;
  }
}

.queryRemindView_cardStyle {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;

  .queryRemindView_itemCard {
    height: 69px;
    width: 100vm;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .queryRemindView_cardRight {
    display: flex;
    height: 69px;
    flex: 1;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    background-color: #e9f2ff;
    border-radius: 7px;
    padding-left: 10px;
    font-size: 10px;
  }

  .queryRemindView_cardLeft {
    width: 66px;
    height: 69px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #e9f2ff;
    border-radius: 7px;

    .queryRemindView_boderStyle {
      height: 40px;
      width: 40px;
      border-radius: 22px;
      background-color: white;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }

  .queryRemindView_inlineStle {
    width: 1px;
    height: 58px;
    border-right-style: dashed;
    border-right-width: 1px;
    border-right-color: #bccce3;
  }
}

.queryRemindView_title {
  color: #486895;
  font-size: 16px;
  font-weight: bold;
}

.queryRemindView_userNameIcon {
  width: 110px;
  height: 110px;
  background-size: contain;
  margin-top: 20px;
}

.queryRemindView_text {
  color: #ffffff;
  font-size: 16.5px;
  font-weight: bold;
}

.queryRemindView_text1 {
  color: #ffffff;
  font-size: 16.5px;
  font-weight: bold;
  position: relative;
  top: 40px;
  margin-left: 20px;
}

.queryRemindView_title1 {
  display: flex;
  flex-direction: row;
  position: relative;
  top: 60px;
  margin-left: 20px;
}

.queryRemindView_title2 {
  color: #ffffff;
  font-size: 25px;
  font-weight: bold;
  position: relative;
  top: -9px;
}

.queryRemindView_icon {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.queryRemindView_img {
  width: 50%;
  height: 30%;
  background-size: contain;
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
</style>
