<template>
  <div class="noAuthorization">
    <Global_globalNav title="查违章" @clickBack="clickBack" />
    <div class="contentStyle">
      <consultLiterature
        @openCarlIST="openCarlIST"
        :carNo="$route.query.item.custNo"
      ></consultLiterature>
      <div class="noAuthorization_tipStyle" v-if="statusType != '1'">
        <img :src="$imageUrl.noShouQ" alt="" class="imgTipStyle" />
        <div class="inform">客户未授权，请先通知授权</div>
        <div class="buttonStyle" @click="toAuthorization">
          通知授权
        </div>
      </div>

      <div v-if="statusType == '1'">
        <div
          class="violationType"
          v-if="
            customerData.getQueryViolationRecordData &&
              customerData.getQueryViolationRecordData.violationList.length > 0
          "
        >
          <ul>
            <li
              v-for="(item,
              index) in customerData.getQueryViolationRecordData &&
                customerData.getQueryViolationRecordData.violationList"
              :key="index"
            >
              <div class="violationLight">
                <div class="violationGenre">违章类型</div>
                <div class="park">{{ item.behavior }}</div>
              </div>
              <div class="violationLotion">
                <div class="violationStop">违法地点</div>
                <div class="specificLocation">{{ item.address }}</div>
              </div>
              <div class="violationTime">
                <div class="violationDay">违法时间</div>
                <div class="specificTime">{{ item.time }}</div>
              </div>
              <div class="violationTime">
                <div class="violationDay">罚款金额</div>
                <div class="specificTime" style="color: #3B8AFE">
                  {{ item.fine ? item.fine : 0 }}.00
                </div>
              </div>
              <div class="violationTime">
                <div class="violationDay">违法记分</div>
                <div class="specificTime" style="color: #3B8AFE">
                  {{ item.score }}分
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="noView" v-else>
          <img :src="$imageUrl.noweizLogo" alt="" class="LogoStyle" />
          <span style="color:#999999;font-size: 14px">暂无违章记录</span>
        </div>
      </div>

      <van-action-sheet
        v-model="show"
        :actions="actions"
        cancel-text="取消"
        @cancel="onCancel"
        @select="onSelect"
      />
    </div>
  </div>
</template>
<script>
import consultLiterature from "@/components/consultLiterature";
import { mapState } from "vuex";
import { getShareUrl } from "../../../http/module/customerApi";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    consultLiterature
  },

  data() {
    return {
      statusType: "0",
      violationTypeList: [],
      show: false,
      actions: [
        { name: "选项" },
        { name: "选项" },
        { name: "选项", subname: "描述信息" }
      ]
    };
  },
  computed: {
    ...mapState(["customerData"])
  },

  watch: {
    customerData: {
      handler: function(val, oldVal) {
        this.$nextTick(function() {
          this.statusType =
            val.getQueryViolationRecordData &&
            val.getQueryViolationRecordData.isAuth;
          //console.log(this.statusType, '==statusType');
          this.actions = val && val.getCarList;
        });
      },
      // 深度观察监听
      deep: true,
      immediate: true
    }
  },

  created() {
    const {
      custNo,
      userCode,
      carNo,
      frameNo,
      enginNo
    } = this.$route.query.item;
    let params_ = {
      custNo: custNo, //客户号
      userCode: userCode,
      carNo: carNo,
      frameNo: frameNo,
      enginNo: enginNo,
      brandName: ""
    };
    this.getQueryViolationRecord(params_);
    let params = {
      custNo: custNo,
      createUser: userCode,
      type: "9",
      title: "查违章",
      userCode: userCode
    };
    this.$store.dispatch("customerData/addOrUpdateInteractions", params);
    //获取所有车辆
    let tempParams = {
      custNo: custNo, //客户号
      userCode: userCode
    };
    this.$store.dispatch("customerData/getQueryViolationCarList", tempParams);
  },
  methods: {
    //获取车辆列表与授权信息
    getQueryViolationRecord(params_) {
      this.$store.dispatch("customerData/getQueryViolationRecordData", params_);
    },
    openCarlIST() {
      //console.log('打开');
      this.show = true;
    },
    onSelect(item) {
      console.log(item);
      const { userCode, carNo, frameNo, enginNo } = item;
      let params_ = {
        custNo: this.$route.query.item.custNo, //客户号
        userCode: userCode,
        carNo: carNo,
        frameNo: frameNo,
        enginNo: enginNo,
        brandName: item.brandName
      };
      this.getQueryViolationRecord(params_);
      this.show = false;
    },
    onCancel() {
      this.show = false;
    },
    clickBack() {
      this.$router.back();
      this.$store.commit("customerData/clearData_");
    },
    async toAuthorization() {
      const {
        custNo,
        userCode,
        carNo,
        frameNo,
        enginNo
      } = this.$route.query.item;
      const { userName, recommendCode } = this.$localStorage;
      let tempUrl =
        this.$getIP() +
        "/customer/authorization?userCode=" +
        localStorage.getItem("userCode") +
        "&custNo=" +
        custNo +
        "&userName=" +
        encodeURI(userName) +
        "&recommendCode=" +
        encodeURI(recommendCode) +
        "&shareData=" +
        new Date().getTime();
      let res = await getShareUrl({ url: tempUrl });
      console.log(res.data.shortUrl, "res.data.shortUrl=");
      console.log(tempUrl, "tempUrl=");
      if (res.code == "1") {
        let jsonObject = {
          sharePlatform: ["0", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: "授权通知", //分享标题
          imgURL:
            "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
          image: "", //分享图片（base64编码）, 与imgURL二选一
          url: res.data.shortUrl, //分享链接
          desc: "尊敬的客户，您的专属客户经理正在帮您查询违章情况，请您授权！", //分享描述
          phoneNum: "" //电话号码,当需要短信分享时获取
        };
        //埋点
        trackMap(
          "c_customer_info_violation_enquiry_empower",
          "客户信息违章查询授权",
          {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "客户管理"
          }
        );
        this.$native.share(jsonObject);
      }
    }
  }
};
</script>
<style lang="less" scoped>
.noAuthorization {
  height: 609px;
  background: #f5f5f5;
  position: fixed;
}

.contentStyle {
  margin-top: 65px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 55px);
  background-color: #ffffff;
}

.violationType {
  margin: 0px 15px;
  background-color: #f5f5f5;
  margin-bottom: 20px;

  ul {
    li {
      margin-bottom: 15px;
      padding: 10px 15px;
      border-radius: 5px;
      background: #f5f5f5;

      .violationLight {
        height: 30px;

        .violationGenre,
        .park {
          line-height: 30px;
        }

        .violationGenre {
          font-size: 14px;
          color: #454545;
          float: left;
        }

        .park {
          float: right;
          font-size: 14px;
          color: #7a7a7a;
        }
      }

      .violationLotion {
        height: 30px;

        .violationStop,
        .specificLocation {
          line-height: 30px;
        }

        .violationStop {
          font-size: 14px;
          color: #454545;
          float: left;
        }

        .specificLocation {
          color: #7a7a7a;
          float: right;
          font-size: 14px;
        }
      }

      .violationTime {
        height: 30px;

        .violationDay,
        .specificTime {
          line-height: 30px;
        }

        .violationDay {
          font-size: 14px;
          color: #454545;
          float: left;
        }

        .specificTime {
          color: #7a7a7a;
          float: right;
          font-size: 13px;
        }
      }
    }
  }
}

.noAuthorization_tipStyle {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;

  .imgTipStyle {
    width: 127px;
    height: 97px;
    background-size: contain;
    margin-top: 55px+65px;
  }

  .inform {
    color: #666666;
    font-size: 12px;
    margin-top: 13px;
  }

  .buttonStyle {
    margin-top: 45px;
    width: 204px;
    height: 36px;
    background: linear-gradient(to right, #2a80ff, #2aa5ff);
    display: flex;
    justify-content: center;
    align-items: center;
    color: #ffffff;
    border-radius: 18px;
  }
}

.noView {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin-top: 120px;

  .LogoStyle {
    width: 126px;
    height: 96px;
    background-size: contain;
    margin: 10px;
  }
}
</style>
