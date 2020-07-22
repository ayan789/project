<!--
 * @Description:公共组件 -- 产品交互页
 * @Date: 2020-05-18 14:14:53
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-29 14:18:00
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\common\productContent.vue
-->
<template>
  <div
    class="productContent"
    id="productContent"
  >
    <vantab :message="message" />

    <div
      class="titleWhite"
      id="vantab0"
      v-if="productInfo.title"
    >
      <productTitle>{{ productInfo.title }}</productTitle>
      <p>{{ productInfo.desc }}</p>
    </div>
    <div class="productInfo">
      <img
        v-for="item in productInfo.content"
        :key="item.id"
        :src="item.img"
        class="productInfo-img"
      />
    </div>
    <div
      class="titleWhite"
      id="vantab1"
      v-if="safeguardDetail.title"
    >
      <productTitle>{{ safeguardDetail.title }}</productTitle>
      <p class="warp">{{ safeguardDetail.desc }}</p>
    </div>
    <div class="productInfo">
      <img
        v-for="item in safeguardDetail.content"
        :key="item.id"
        :src="item.img"
        class="productInfo-img"
      />
    </div>
    <div
      class="titleWhite"
      id="vantab2"
      v-if="claimStatement.title"
    >
      <productTitle>{{ claimStatement.title }}</productTitle>
      <p class="warp">{{ claimStatement.desc }}</p>
    </div>
    <div class="productInfo">
      <img
        v-for="item in claimStatement.content"
        :key="item.id"
        :src="item.img"
        class="productInfo-img"
      />
    </div>
    <div
      class="titleWhite"
      id="vantab3"
      v-if="questionArr.length"
    >
      <productTitle>{{ commonProblem.title }}</productTitle>
      <p class="warp">{{ commonProblem.desc }}</p>
    </div>
    <div
      class="common-problem"
      v-if="questionArr.length"
    >
      <div>
        <question-item
          v-for="(item, index) in questionArr"
          :key="item.id"
          :index="index"
        >
          <span
            slot="question"
            class="question"
          >{{ item.title }}</span>
          <span
            slot="answer"
            class="answer"
          >{{ item.desc }}</span>
        </question-item>
      </div>
      <div
        class="more"
        @click="moreQuesion"
      >
        {{ !isClicked ? "查看更多" : "收起" }}
      </div>
    </div>
    <div
      class="title"
      id="vantab4"
    >
      <productTitle>{{ termsAndConditions.title }}</productTitle>
    </div>
    <div class="clause">
      <div class="notice-clause"></div>
      <van-cell-group>
        <van-cell
          v-for="item in termsAndConditions.content"
          :key="item.id"
          :title="item.title"
          value
          is-link
          @click="gotoClause(item)"
        />
      </van-cell-group>
    </div>
  </div>
</template>

<script>
import productTitle from "../common/title";
import vantab from "../common/vantab.vue";
import questionItem from "../common/question-item";
export default {
  name: "productContent",
  components: {
    productTitle,
    vantab,
    questionItem
  },
  props: {
    prContentObj: Object,
    commonProblem: Object
  },
  data () {
    return {
      message: [],
      questionArr: [], //问题
      isClicked: false, // 默认第一次没有点过
      productInfo: {}, //产品简介
      safeguardDetail: {}, //保障详情
      claimStatement: {}, //理赔说明
      termsAndConditions: {} //条款须知
    };
  },
  computed: {
    questionA: {
      get () {
        return this.commonProblem;
      },
      set () {
        this.renderCommonProblem();
      }
    },
    contentO: {
      get () {
        return this.prContentObj;
      },
      set () {
        this.renderPrInfo();
      }
    }
  },
  watch: {
    questionA () {
      this.$nextTick(() => {
        this.renderCommonProblem();
      });
    },
    contentO () {
      this.$nextTick(() => {
        this.renderPrInfo();
      });
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.renderCommonProblem();
      this.renderPrInfo();
    });
  },
  methods: {
    // 渲染常见问题列表
    renderCommonProblem () {
      if (this.commonProblem.hasOwnProperty("content") && this.commonProblem.content.length != 0) {
        if (this.commonProblem.content.length > 4) {
          this.questionArr = this.commonProblem.content.slice(0, 4);
        } else {
          this.questionArr = this.commonProblem.content;
        }
      }
    },
    //渲染产品简介，理赔说明，条款须知
    renderPrInfo () {

      if (!this.$isNull(this.prContentObj)) {
        if (
          !this.$isNull(this.prContentObj.productInfo) &&
          this.prContentObj.productInfo.content.length
        ) {
          this.productInfo = this.prContentObj.productInfo;
        }
        if (
          !this.$isNull(this.prContentObj.safeguardDetail) &&
          this.prContentObj.safeguardDetail.content.length
        ) {
          this.safeguardDetail = this.prContentObj.safeguardDetail;
        }
        if (
          !this.$isNull(this.prContentObj.claimStatement) &&
          this.prContentObj.claimStatement.content.length
        ) {
          this.claimStatement = this.prContentObj.claimStatement;
        }
        if (!this.$isNull(this.prContentObj.termsAndConditions)) {
          this.termsAndConditions = this.prContentObj.termsAndConditions;
        }
        this.renderTabTitle();
      }
    },
    // 渲染浮动的tab头
    renderTabTitle () {
      let tabs = [
        this.productInfo,
        this.safeguardDetail,
        this.claimStatement,
        this.commonProblem,
        this.termsAndConditions
      ];
      let aa = tabs.filter(item => !this.$isNull(item) && item.content.length != 0);

      aa.forEach((item, i) => {
        this.message.push({ title: item.title, id: i });
      });
    },
    // 跳转到相应的条款内
    gotoClause (item) {
      if (item.chlidren instanceof Array && item.chlidren.length > 0) {
        let obj = {
          list: item.chlidren,
          title: item.title
        };
        this.$router.push({
          name: "clauseTerms",
          query: { list: JSON.stringify(obj) }
        });
      } else {
        // // https://view.officeapps.live.com/op/view.aspx?src=https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/e8300975-30b9-4c8f-97f4-afcc27848f98.docx
        this.$router.push({
          name: "clausePdf",
          query: { title: item.title, link: item.link }
        });
        // this.$native.openWebView({
        //   url: "https://view.officeapps.live.com/op/view.aspx?src=" + item.link,
        //   type: "0",
        //   titleBarTag: "1",
        //   titleBar: item.title,
        //   isBackHome: "1"
        // });
      }
    },
    moreQuesion () {
      if (!this.isClicked) {
        this.questionArr = this.commonProblem.content;
      } else {
        this.questionArr = this.commonProblem.content.slice(0, 4);
      }
      this.isClicked = !this.isClicked;
    }
  }
};
</script>
<style lang="less" scope>
#productContent {
  // background: rgba(36, 128, 234, 1);
  // padding-bottom: 65px;
  .title {
    height: 43px;
    padding-top: 10px;
    text-align: center;
  }
  .van-checkbox__icon--disabled.van-checkbox__icon--checked .van-icon {
    color: #fff;
  }

  .van-checkbox__icon--disabled .van-icon {
    background-color: #3b8afe;
    border-color: #3b8afe;
  }
  .card {
    width: 355px;
    // height: 223px;
    background: rgba(255, 255, 255, 1);
    border-radius: 8px;
    margin: 0 auto;
    padding: 10px 0;
    [class*="van-hairline"]::after {
      border: none;
    }
    .van-tab {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      border: 1px solid rgba(208, 208, 208, 1);
      margin-right: 14px;
      border-radius: 5px;
    }
    .van-tab--active {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      flex-basis: 46% !important;
      border: 1px solid rgba(59, 138, 254, 1);
    }
    .van-ellipsis {
      flex-basis: 46% !important;
    }
    .van-tabs__line {
      width: 28px !important;
      height: 5px !important;
      background: none;
      border-radius: 6px;
      bottom: 0;
    }
    .van-tabs__nav--line {
      padding-bottom: 0%;
    }
    .van-tabs__wrap {
      margin: 8px 0 0 20px;
      width: 332px;
    }

    .titleStrong {
      height: 40px;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      // line-height: 40px;
      margin: 11px 23px 17px;
    }
    .insureDesc {
      background: rgba(247, 247, 247, 1);
      border-radius: 8px;
      // height: 93px;
      margin: 0 10px;
      padding: 10px 13px;
      line-height: 22px;
      .strong {
        font-size: 14px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 7px;
      }
      .light {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(17, 17, 17, 1);
      }
      .gray {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
      }
    }
    .right {
      text-align: right;
    }
  }
  .personInfo {
    width: 355px;
    background: rgba(255, 255, 255, 0.99);
    border-radius: 8px;
    margin: 10px auto 0;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
    padding: 5px 0;
    box-sizing: border-box;
    .minTitle {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(17, 17, 17, 1);
      line-height: 46px;
    }
    [class*="van-hairline"]::after {
      border: none;
    }
    button {
      width: 54px;
      height: 27px;
      border-radius: 3px;
      border: 1px solid rgba(238, 238, 238, 1);
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(185, 185, 185, 1);
    }
    button:first-child {
      margin-right: 15px;
    }
    .blue {
      background: rgba(238, 245, 255, 1);
      color: rgba(43, 121, 237, 1);
    }
    .van-checkbox {
      float: right;
    }
  }
  .titleWhite {
    text-align: center;
    margin: 10px 0;
    p {
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      padding: 0 39px;
      font-weight: 400;
      color: rgba(255, 255, 255, 1);
    }
    .warp {
      padding: 0 50px;
    }
  }
  .titleWhite:first-child {
    margin: 0 0 10px 0;
  }
  .productInfo {
    text-align: center;
    .productInfo-img {
      width: 356px;
      height: auto;
      margin-bottom: 10px;
    }
    .productInfo-img:last-child {
      margin-bottom: 0;
    }
  }
  .pic {
    text-align: center;
    .pic-img {
      width: 356px;
      height: 263px;
    }
  }
  .claimExplanation {
    text-align: center;
    .claimExplanation-img {
      width: 356px;
      height: 491px;
    }
  }
  .common-problem {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 356px;
    border-top: 1px solid transparent;
    background: rgb(255, 255, 255);
    border-radius: 8px;
    margin: 0 auto;
    padding: 0 21px 16px 21px;
    box-sizing: border-box;
    .question {
      margin-bottom: 8px;
    }
    .more {
      margin: 30px auto 0 auto;
      text-align: center;
      width: 52px;
      height: 19px;
      font-size: 13px;
      font-weight: 400;
      color: rgb(153, 153, 153);
      line-height: 19px;
    }
  }
  .van-cell-group {
    border-radius: 8px;
  }
  .van-cell:last-child {
    border-radius: 8px;
  }
  .clause {
    width: 355px;
    // height: 206px;
    background: rgb(255, 255, 255);
    border-radius: 12px 12px 8px 8px;
    margin: 0 auto;
    position: relative;
    .notice-clause {
      width: 355px;
      height: 27px;
      background-image: url(../../../../assets/nonCar/worryFree/noticeClause.png);
      background-position: 0 0;
      background-repeat: no-repeat;
      background-size: 100% 27px;
    }
    [class*="van-hairline"]::after {
      border: none;
    }
  }
}
</style>
