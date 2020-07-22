<template>
  <div class="container">
    <div v-if="custSourceType == 2" class="attention" style="align-items: center">
      <div class="box">
        <div class="title-img">
          <img
            style="width:100%;height:100%"
            src="@/assets/customer/customerEquity/equity@3x.png"
            alt="edit"
          />
        </div>
      </div>
      <div class="desc">
        <p style="color:#333333;font-size:14px;margin-top: 10px;margin-bottom: 10px">重点关注</p>
        <div class="desc-text" v-if="datas && datas.importantInfo1">
          {{ datas && datas.importantInfo1 }}
        </div>
        <div
          class="desc-text"
          v-if="datas && datas.importantInfo2"
          style="border-top: 1px dotted #EDEEEF"
        >
          {{ datas && datas.importantInfo2 }}
        </div>
        <div
          class="desc-text"
          v-if="datas && datas.importantInfo3"
          style="border-top: 1px dotted #EDEEEF"
        >
          {{ datas && datas.importantInfo3 }}
        </div>
      </div>
    </div>
    <div
      v-if="custSourceType == 2"
      class="attention"
      style="align-items: center"
      @click="toPolicy"
    >
      <div class="box">
        <div class="title-img">
          <img
            style="width:100%;height:100%"
            src="@/assets/customer/customerEquity/policy@3x.png"
            alt="edit"
          />
        </div>
      </div>
      <div class="desc" style="flex:1">
        <p style="color:#333333;font-size:14px;margin-top: 10px;margin-bottom: 10px">保险信息</p>
      </div>
      <p class="container_text">{{ datas && datas.policyInfo }}</p>
      <div class="title-img-right">
        <img
          style="width:100%;height:100%"
          src="@/assets/customer/customerEquity/rightArrowhead@3x.png"
          alt="edit"
        />
      </div>
    </div>
    <div
      v-if="custSourceType == 2"
      class="attention"
      style="align-items: center"
      @click="toPortrayal"
    >
      <div class="box">
        <div class="title-img">
          <img
            style="width:100%;height:100%"
            src="@/assets/customer/customerEquity/query@3x.png"
            alt="edit"
          />
        </div>
      </div>
      <div class="desc">
        <p style="color:#333333;font-size:14px;margin-top: 10px;margin-bottom: 10px">客户画像</p>
      </div>
      <div style="flex:1"></div>
      <div class="title-img-right">
        <img
          style="width:100%;height:100%"
          src="@/assets/customer/customerEquity/rightArrowhead@3x.png"
          alt="edit"
        />
      </div>
    </div>
    <div
      v-if="custSourceType == 1"
      :style="{backgroundImage: 'url(' + noDataIMG + ')'}"
      class="nodate-image">
    </div>
    <div v-if="custSourceType == 1" style="display: flex;justify-content: center;margin-top: -10px"><span
      style="color:#999999">暂无数据</span></div>

    <productRecommendation :product="product"></productRecommendation>
  </div>
</template>
<script>
import productRecommendation from "@/components/productRecommendation.vue";
import axios from "axios";
import noDataIMG from "../../../assets/customer/customerEquity/noData.png";

export default {
  props: {
    datas: Object,
    product: Array,
    custSourceType: String,
    custNo: String,
    custType: String
  },
  components: {
    productRecommendation
  },
  data() {
    return {
      noDataIMG: noDataIMG
    };
  },

  methods: {
    toPolicy() {
      this.$router.push({
        name: "insuranceInfoView",
        query: { custNo: this.custNo, custType: this.custType }
      });
    },
    async toPortrayal() {
      let bodyData = {
        requestHead: {},
        requestBody: {
          userid: localStorage.getItem("userCode"),
          custno: this.custNo
        }
      };

      let config = {};
      config.headers = {
        "Content-Type": "application/json",
        Accept: "*/*"
      };
      axios
        .post("https://m.95590.cn/uprofile-app/app/token", bodyData, config)
        .then(res => {
          if (res.status == "200") {
            let status =
              res &&
              res.data &&
              res.data.responseBody &&
              res.data.responseBody.status;
            if (status == 0) {
              // window.location.href = res.data.responseBody.uprofile_h5_url;
              let param = {
                type: "0",
                titleBarTag: "1",
                titleBar: '客户画像',
                sharePlatform: [],
                shareType: "url",
                title: "客户画像",
                imgURL: "",
                image: "",
                url: res.data.responseBody.uprofile_h5_url,
                desc: "客户画像"
              };
              this.$native.openWebView(param, data => {
              });
            }
          }
        });
    }
  }
};
</script>
<style lang="less" scoped>
.container {
  margin: 7px 15px 0 15px;
  font-size: 16px;
  position: static;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: 60vh;
  /*padding-top: 50px;*/

  .nodate-image {
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 300px;
    margin: 0px auto;
    height: 300px;
  }

  .box {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: flex-start;

    .title-img {
      width: 20px;
      height: 20px;
    }
  }

  .title-img-right {
    margin-left: 8px;
    width: 5px;
    height: 14px;
  }

  .attention {
    background: #ffffff;
    display: flex;
    flex-direction: row;
    /*align-items: center;*/
    border-bottom: 1px solid #edeeef;
    //竖线
    .line {
      margin-left: 12px;
      border: 0.4px solid #d0d0d0;
      height: 30px;
    }

    .desc {
      margin-left: 12px;
      padding-right: 12px;

      .desc-text {
        padding: 10px 0;
        color: #333333;
        font-size: 14px;
        /*letter-spacing: 2.5px;*/
      }
    }
  }
}

.container_text {
  width: 60%;
  font-size: 14px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
