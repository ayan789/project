<template>
  <div
    class="woodpecker-bg-status woodpecker-wrapper scroll-div"
    :style="{ backgroundImage: 'url(' + indexTop + ')' }"
  >
    <div class="header-one" @click="onClickLeft()">
      <img
        style="width:100%;height:100%"
        src="@/assets/activity/left.png"
        alt="edit"
      />
    </div>
    <!-- <vWrapper
      min-height="277"
      header-height="39"
      footerHeight="44"
      margin="227px auto 0 auto"
      :other="true"
    >
      <div class="woodpecker-detail">
        <div v-for="(c,index) in content" :key="c.title">
          <v-title :index='index+1'>{{c.title}}</v-title>
          <div class="desc" v-for="d in c.desc" :key="d" v-html="d"></div>
        </div>
      </div>
    </vWrapper>-->
    <img src="@src/assets/activity/woodpecker/content.png" class="content" />
    <vWrapper
      min-height="0"
      header-height="30"
      footerHeight="48"
      margin="13px auto"
      :other="true"
      style="position: relative;padding-bottom: 38px"
    >
      <vTitle>反馈</vTitle>
      <van-field
        v-model="feedback"
        type="textarea"
        placeholder="请输入您要反馈的内容"
        show-word-limit
        maxlength="100"
        class="feedback-textarea"
        :autosize="{ maxHeight: 125, minHeight: 75 }"
      />
      <div
        class="submit"
        :style="{ backgroundImage: 'url(' + pic + ')' }"
        @click="submit"
      ></div>
      <div
        :style="{
          height: '36px',
          backgroundImage: 'url(' + whiteHeardBottom + ')'
        }"
        class="foot-img-div"
      ></div>
    </vWrapper>
    <!-- <vWrapper min-height="134" header-height="30" footerHeight="44" margin="0 auto" :other="true">
      <div class="download-wrapper">
        <image class="qrcode" src=''></image>
        <div class="qrcode"></div>
        <div class="down-load">
          <div :style="{'backgroundImage': 'url(' +fingerPrint + ')'}" class="woodpecker-bg-status finger-print"></div>
          <div class="download-text">长按识别二维码</div>
          下载大地行APP
        </div>
      </div>
    </vWrapper>-->
  </div>
</template>
<script>
import indexTop from "@src/assets/activity/woodpecker/indexTop.png";
import vTitle from "./woodpeckercomponents/title";
import vWrapper from "./woodpeckercomponents/wrapper";
import submitPic from "@src/assets/activity/woodpecker/submit.png";
import graySubmitPic from "@src/assets/activity/woodpecker/gray-submit.png";
import fingerPrint from "@src/assets/activity/woodpecker/fingerprint.png";
import { suggest } from "@src/http/module/activityApi.js";
import whiteHeardBottom from "@src/assets/activity/woodpecker/whiteHeardBottom.png";

export default {
  watch: {
    feedback(val) {
      if (val) {
        this.pic = this.submitPic;
      } else {
        this.pic = this.graySubmitPic;
      }
    }
  },
  data() {
    return {
      whiteHeardBottom: whiteHeardBottom,
      indexTop,
      fingerPrint,
      submitPic,
      graySubmitPic,
      pic: graySubmitPic,
      feedback: "",
      lock: false,
      // 公共顶部
      globalNav: {
        title: "啄木鸟",
        isShare: false
      }
      // content: [
      //   {
      //     title: "活动目的",
      //     desc: [
      //       "为配合公司“三化”项目落地，借超级销售APP一期上线之机，完善系统功能，提升操作体验度，制定本方案。"
      //     ]
      //   },
      //   {
      //     title: "活动对象",
      //     desc: ["所有通过使用新销支APP反馈问题的大地员工。"]
      //   },
      //   {
      //     special: true,
      //     title: "活动奖励规则",
      //     desc: [
      //       `活动期间，通过超级销售APP[意见反馈]模块，在[功能异常]、[体验优化]、[新功能建议]三类反馈类型中反应问题的用户，皆由机会获得如下奖励：<br /><br />`,
      //       `<span>在[功能异常]中反馈问题的用户，一经采纳，在系统【我的荣誉】模块增加<span style='font-weight:700'>“啄木鸟”</span>勋章，并获得10元红包</span>`,
      //       `在[体验优化] 中反馈问题的用户，一经采纳，在系统【我的荣誉】模块增加<span style='font-weight:700'>“体验官”</span>勋章，并获得10元红包`,
      //       `在[新功能建议] 中反馈问题的用户，一经采纳，在系统【我的荣誉】模块增加<span style='font-weight:700'>“设计师”</span>勋章，并获得10元红包`
      //     ]
      //   },
      //   {
      //     title: "奖励方式",
      //     desc: [
      //       "1、多位用户反馈同一问题的，一经采纳，可重复奖励。",
      //       "2、同一用户反馈多项问题，一经采纳，勋章和红包可叠加领取。",
      //       "3、活动结束后，红包可在APP中[我的福利]界面中领取，奖金通过公司成长绩效形式发放。"
      //     ]
      //   },
      //   {
      //     title: "其他说明",
      //     desc: [
      //       "1、如发现活动期间恶意作假的，扣回奖励并全国通报批评。",
      //       "2、本方案由总公司个人渠道支持部负责解释。"
      //     ]
      //   }
      // ]
    };
  },
  methods: {
    submit() {
      if (!this.feedback || this.lock) {
        return (this.pic = this.graySubmitPic);
      }
      this.pic = this.submitPic;

      //活动详情
      let activityCode =
        (this.$route.query.item && this.$route.query.item.activityCode) || "";

      if (!activityCode && this.$route.query.item) {
        const obj = JSON.parse(this.$route.query.item);
        if (obj) {
          activityCode = obj.activityCode;
        }
      }

      if (this.$isNull(activityCode)) {
        activityCode = this.$GetRequest("activityCode", true);
      }

      const params = {
        activityCode,
        suggestion: this.feedback,
        userCode: this.$GetRequest("usercode", true)
          ? this.$GetRequest("usercode", true)
          : this.localStorage.get("userInfo", {}).userCode,
        userName: this.localStorage.get("userInfo", {}).userName,
        userTel: this.localStorage.get("userInfo", {}).userPhone,
        feedType: "5"
      };
      suggest(params).then(res => {
        this.lock = true;
        this.$toast("提交成功");
      });
    },
    // 返回上一页
    onClickLeft() {
      this.$globalBack();
    }
  },
  components: {
    vTitle,
    vWrapper
  }
};
</script>
<style lang="less" scope>
#app .router-view {
  height: auto;
}
.foot-img-div {
  /*width:312px;*/
  margin: 0 auto;
  background-size: 100% auto;
  background-repeat: no-repeat;
  position: absolute;
  bottom: 0px;
  left: 0px;
  right: 0px;
}
.scroll-div {
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: 100vh;
}
.header-one {
  position: fixed;
  top: 20px;
  left: 10px;
  height: 20px;
  width: 20px;
}
.woodpecker-bg-status {
  background-size: 100% auto;
  background-repeat: no-repeat;
}
.woodpecker-wrapper {
  width: 100%;
  min-height: 100%;
  background-color: #128d7d;
  border-top: 1px solid transparent;
  padding-bottom: 40px;
  div,
  span {
    box-sizing: border-box;
  }
  .content {
    display: block;
    width: 345px;
    height: 1797px / 2;
    margin: 227px auto 0 auto;
  }
  // .woodpecker-detail {
  //   padding: 0 18px;
  //   .desc {
  //     font-size: 14px;
  //     font-family: PingFang-SC-Medium, PingFang-SC;
  //     font-weight: 500;
  //     line-height: 20px;
  //     .big {
  //       font-weight: 600;
  //     }
  //   }
  // }
  .feedback-textarea {
    width: 312px;
    margin: 0 auto;
  }
  .submit {
    width: 163px;
    height: 44px;
    border-radius: 22px;
    margin: 20px auto 0 auto;
    background-size: auto 100%;
    background-repeat: no-repeat;
    background-position: center center;
  }
  .download-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
    padding: 0 50px 0 43px;
  }
  .qrcode {
    width: 97px;
    height: 100px;
  }
  .down-load {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 12px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(0, 0, 0, 0.5);
    letter-spacing: 4px;
    .finger-print {
      width: 50px;
      height: 55px;
    }
    .download-text {
      margin: 15px 0 6px 0;
    }
  }
  .feedback-textarea {
    background: #f3f3f3;
    border-radius: 10px;
  }
}
</style>
