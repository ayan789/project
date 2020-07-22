<template>
  <div
    class="brave-bg-status brave-activities scroll-div"
    :style="{ backgroundImage: 'url(' + braveBg + ')' }"
  >
    <img src="../../../assets/activity/brave/trophy.png" class="trophy" />
    <img
      src="@/assets/activity/brave/brave-invincible.png"
      class="brave-invincible"
    />
    <div class="header-one" @click="onClickLeft()">
      <img
        style="width:100%;height:100%"
        src="@/assets/activity/left.png"
        alt="edit"
      />
    </div>
    <div class="receive">
      <div class="get-sign">
        完成签单 赚取
        <span>红包</span>
      </div>
      <div class="redbag-wrapper" v-if="isCanGetCount.isCanGetCount">
        <img
          src="@/assets/activity/brave/red-envelopes.png"
          class="red-envelopes"
          v-for="i in isCanGetCount.isCanGetCount"
          :key="i"
          ref="redEnvelopes"
        />
      </div>
      <div class="get-receive">
        <div
          class="get-receive-red"
          v-if="isCanGetCount.isCanGetCount"
          @click="getAward"
        >
          点击领取
          <span v-if="awardGet">（已领{{ awardGet }}个）</span>
        </div>
        <div class="get-receive-red" v-else-if="awardGet">
          已领取红包
          <div>{{ awardGet }}个</div>
        </div>
        <div class="get-receive-red get-receive-pink" v-else>点击领取</div>
      </div>
    </div>
    <div class="receive-rules">
      <div class="add-rules">
        <span>1-3(单)</span>
        <span>4-6(单)</span>
        <span>6以上(单)</span>
      </div>
      <div class="add-money">
        <div class="add-money-item" v-for="(m, index) in addMoney" :key="m">
          <span
            v-if="index > 0"
            class="line"
            :class="{ 'finish-line': finish[index] }"
          ></span>
          <img
            src="@/assets/activity/brave/finish.png"
            class="finish-item"
            v-if="finish[index]"
          />
          <span class="money-item" v-else>{{ m }}</span>
        </div>
      </div>
      <div class="receive-rules-desc">
        <div class="receive-rules-title">
          竞赛期间，凡是家用车业务并附加车加人产品的，按以下标准给予“车加人”激励
        </div>
        <div class="receive-item" v-for="r in receiveDesc" :key="r.count">
          <div class="left">
            <span class="count">{{ r.count }}</span>
            <span class="money">{{ r.money }}</span>
          </div>
          <span class="geted"
            >已领取（{{ r.getCount }}/{{ r.totalCount }}）</span
          >
        </div>
        <div class="end">
          本期活动总奖金池封顶10万，先到先得，总奖金达到封顶金额本期活动结束。
        </div>
      </div>
    </div>
    <div class="brave-other">
      <vTitle>{{ activityPeople.title }}</vTitle>
      <div
        class="rules-content time people-content"
        v-for="c in activityPeople.content"
        :key="c"
      >
        {{ c }}
      </div>
      <div style="margin-top:20px;">
        <vTitle>{{ activityTime.title }}</vTitle>
        <div class="rules-content time">{{ activityTime.content }}</div>
      </div>
    </div>
    <div class="brave-other">
      <vTitle>{{ otherMatters.title }}</vTitle>
      <div
        class="rules-content matter"
        v-for="(c, index) in otherMatters.content"
        :key="c"
      >
        <span class="dot">{{ index + 1 }}</span>
        {{ c }}
      </div>
    </div>
    <img
      src="@src/assets/activity/brave/signUp.png"
      class="sign-up"
      v-if="isApply == 1 && isNeedApply == 1"
      @click="signUp"
    />>
  </div>
</template>
<script>
import vTitle from "./bravecomponents/title";
import braveBg from "@src/assets/activity/brave/brave-bg.png";
import { mapState } from "vuex";
import globalNav from "@/components/globalNav";

export default {
  data() {
    return {
      braveBg,
      isApply: 2, // 是否参加了活动 ，1参加，2未参加
      isNeedApply: 2, // 活动要报名，1未报，2已报
      activityPeople: {
        title: "激励范围",
        content: ["直个渠道全体销售人员", "（不含四级、非销和渠道总监）"]
      },
      activityTime: {
        title: "活动时间",
        content: "4月27日 - 4月30日"
      },
      otherMatters: {
        title: "其他事项",
        content: [
          "数据口径：活动期间签单的车险（不含摩托车）业务，且承保期间需大于100天。",
          "严禁弄虚作假，一旦发现虚挂业务套取奖励行为的，除扣回相应激励外，还将对相关人员进行通报。",
          "活动期间，同一台车的交强险、商业险计为一单。",
          "本方案最终解释权归总公司个人渠道支持部。"
        ]
      },
      addMoney: ["¥30", "¥50", "¥80"],
      receiveDesc: [
        { count: "1-3单", money: "30元 ", totalCount: 4, getCount: 0 },
        { count: "4-6单 ", money: "50元 ", totalCount: 4, getCount: 0 },
        { count: "6以上", money: "80元 ", totalCount: 4, getCount: 0 }
      ],
      // totalAwardList:[{isCanGetCount:0,getCount:3,totalCount:3},{isCanGetCount:0,getCount:4,totalCount:4},{isCanGetCount:0,getCount:0,totalCount:4}], // 档次列表
      totalAwardList: [], // 档次列表
      activityCode: "", // 活动标志
      awardLevel: "", // 该活动的档次。
      salesmanCode: this.$GetRequest("usercode", true)
        ? this.$GetRequest("usercode", true)
        : this.localStorage.get("userInfo", {}).userCode,
      // 公共顶部
      globalNav: {
        title: "勇者无敌",
        isShare: false
      }
    };
  },
  computed: {
    ...mapState(["activityData"]),
    isCanGetCount() {
      return (
        this.totalAwardList.find(item => {
          return item.isCanGetCount && +item.isCanGetCount > 0;
        }) || {}
      );
    },
    awardGet() {
      return this.receiveDesc.reduce((count, item) => {
        return count + item.getCount;
      }, 0);
    },
    finish() {
      return this.receiveDesc.map(item => {
        return item.totalCount == item.getCount;
      });
    }
  },
  created() {
    this.getActivityDetails();
  },
  mounted() {
    if (this.$refs.redEnvelopes) {
      let length = this.$refs.redEnvelopes.length;
      let baseX = 50;
      if (this.isCanGetCount.isCanGetCount > 4) {
        baseX = 30;
      }
      this.$refs.redEnvelopes.forEach((item, index) => {
        let x = baseX * index;
        let y = index ? -Math.floor(Math.random() * (90 - 70 + 1) + 70) : 0;
        if (index == length - 1 && length > 3) {
          y = 0;
        }
        if (!((index + 1) % 2) && length > 4) {
          y = y + 15;
        }
        item.style.transform = `translate(${x}px,${y}px)`;
      });
    }
  },
  methods: {
    async signUp() {
      let params = {
        activityCode: this.activityCode,
        salesmanCode: this.salesmanCode
      };
      await this.$store.dispatch("activityData/getActivityApply", params);
      let activityApply = this.activityData.activityApply;
      if (activityApply.code == 1) {
        this.$toast("报名成功");
        this.getActivityDetails();
        //判断是否是“isApplyOk实时奖励”“isApplyEndOk终止日奖励”
        // this.showData && this.showData.awardType == 1
        //   ? (this.isApplyOk = true)
        //   : (this.isApplyEndOk = true);
      } else {
        let msg = activityApply.msg;
        this.$toast(msg);
      }
    },
    getActivityDetails() {
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

      this.activityCode = activityCode;
      let params = {
        activityCode: this.activityCode,
        salesmanCode: this.salesmanCode
      };
      this.$store
        .dispatch("activityData/getActivityDetails", params)
        .then(() => {
          let activityDetails = this.activityData.activityDetails;
          let totalAwardList =
            activityDetails &&
            activityDetails.activityResponse &&
            activityDetails.activityResponse.totalAwardList;
          this.isApply =
            activityDetails &&
            activityDetails.activityResponse &&
            activityDetails.activityResponse.isApply;
          this.isNeedApply =
            activityDetails &&
            activityDetails.activityResponse &&
            activityDetails.activityResponse.isNeedApply;
          this.totalAwardList = totalAwardList || [];
          this.receiveDesc.length =
            (totalAwardList && totalAwardList.length) || 0;
          this.receiveDesc.forEach((item, index) => {
            if (totalAwardList.length) {
              item.totalCount = totalAwardList[index].totalCount;
              item.getCount = totalAwardList[index].getCount;
            }
            if (totalAwardList[index] && totalAwardList[index].isCanGetCount) {
              this.awardLevel = totalAwardList[index].awardLevel;
            }
          });
        });
      // this.receiveDesc.length = this.totalAwardList.length
      // this.receiveDesc.forEach((item,index)=>{
      //     item.totalCount = this.totalAwardList[index].totalCount
      //     item.getCount = this.totalAwardList[index].getCount
      // })
    },
    async getAward() {
      //领取红包
      let params = {
        activityCode: this.activityCode,
        awardLevel: this.awardLevel,
        salesmanCode: this.salesmanCode
      };
      await this.$store.dispatch("activityData/getAward", params);
      let award = this.activityData.award;
      this.$toast(award.msg);
      this.getActivityDetails();
    },
    // 返回上一页
    onClickLeft() {
      this.$globalBack();
    }
  },
  components: {
    vTitle
  }
};
</script>
<style lang="less" scoped>
#app .router-view {
  height: auto;
}
.brave-bg-status {
  background-size: 100% auto;
  background-repeat: no-repeat;
}
.scroll-div {
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: 100vh;
}
.header-one {
  position: absolute;
  top: 20px;
  left: 10px;
  height: 20px;
  width: 20px;
}
.brave-activities {
  border-top: 1px solid transparent;
  padding-bottom: 40px;
  div,
  span {
    box-sizing: border-box;
  }
  .trophy {
    width: 200px;
    height: 206px;
    position: absolute;
    left: 0;
    top: 20px;
  }
  .brave-invincible {
    width: 200px;
    height: 79px;
    position: absolute;
    right: 0;
    top: 60px;
  }
  .receive {
    width: 335px;
    padding: 26px 0;
    background: #fef7f1;
    margin: 185px auto 0 auto;
    border-radius: 4px 4px 0px 0px;
    .redbag-wrapper {
      padding: 29px;
      margin-top: 60px;
      margin-bottom: -100px;
      .red-envelopes {
        width: 34px;
        height: 40px;
      }
    }
    .get-receive {
      position: relative;
      margin: 0 auto;
      width: 146px;
      height: 146px;
      border-radius: 50%;
      background: rgba(255, 255, 255, 1);
      box-shadow: 0px 10px 11px 0px rgba(211, 211, 211, 0.5);
      .get-receive-red {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 122px;
        height: 122px;
        box-shadow: 0px 10px 11px 0px rgba(211, 211, 211, 0.5);
        border-radius: 50%;
        border: 6px solid rgb(249, 48, 55);
        font-size: 18px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 600;
        color: rgb(249, 48, 55);
        text-shadow: 0px 10px 11px rgba(211, 211, 211, 0.5);
        &.get-receive-pink {
          color: rgba(249, 48, 55, 0.5);
          border: 6px solid rgba(249, 48, 55, 0.5);
        }
        & > span {
          width: 122px;
          text-align: center;
          font-weight: 500;
          margin-top: 5px;
          white-space: nowrap;
          text-overflow: ellipsis;
          overflow: hidden;
        }
      }
    }
    .get-sign {
      position: relative;
      margin: 0 auto 10px auto;
      width: 208px;
      height: 33px;
      font-size: 24px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 600;
      color: rgba(0, 0, 0, 0.85);
      line-height: 33px;
      &::after {
        position: absolute;
        content: "";
        left: 0;
        bottom: 0;
        width: 202px;
        height: 10px;
        background: rgba(250, 157, 0, 0.15);
        border-radius: 5px;
      }
      & > span {
        color: #ffb127;
      }
    }
  }
  .receive-rules {
    width: 335px;
    padding: 15px 0;
    background: rgb(255, 255, 255);
    border-radius: 0px 0px 4px 4px;
    margin: 0 auto;
    .add-rules {
      display: flex;
      padding: 0 56px;
      margin-bottom: 14px;
      justify-content: space-between;
      font-size: 10px;
      font-family: PingFangSC-Regular, PingFang SC;
      color: rgba(0, 0, 0, 0.5);
      line-height: 14px;
    }
    .add-money {
      display: flex;
      justify-content: space-between;
      padding: 0 56px;
      .add-money-item {
        display: flex;
        align-items: center;
        .money-item {
          display: flex;
          align-items: center;
          justify-content: center;
          width: 26px;
          height: 26px;
          background: rgba(249, 48, 55, 0.2);
          border-radius: 50%;
          font-size: 12px;
          font-family: DINAlternate-Bold, DINAlternate;
          font-weight: bold;
          color: rgba(255, 255, 255, 1);
          line-height: 14px;
        }
        .line {
          width: 71px;
          background: rgba(249, 48, 55, 0.2);
          height: 2px;
        }
        .finish-item {
          width: 26px;
          height: 26px;
        }
        .finish-line {
          background: rgb(249, 48, 55);
        }
      }
    }
  }
  .receive-rules-desc {
    margin: 10px auto 0 auto;
    width: 288px;
    background: rgba(254, 247, 241, 1);
    border-radius: 4px;
    padding: 14px 19px 9px 14px;
    .receive-rules-title {
      font-size: 14px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 600;
      color: rgb(51, 51, 51);
      line-height: 24rpx;
      margin-bottom: 16px;
    }
    .receive-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      line-height: 24px;
      .left {
        width: 120px;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .count {
        font-size: 14px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
      }
      .money {
        font-size: 14px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 600;
        color: rgba(250, 175, 47, 1);
      }
    }
    .end {
      margin-top: 6px;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
    }
  }
  .brave-other {
    width: 335px;
    padding: 20px 0;
    margin: 15px auto 0 auto;
    background: rgb(254, 247, 241);
    box-shadow: 0px 3px 4px 0px rgba(248, 37, 50, 1);
    border-radius: 4px;
    .rules-content {
      font-size: 15px;
      padding: 0 26px;
      margin-top: 20px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(0, 0, 0, 0.85);
      line-height: 25px;
    }
    .people-content:last-child {
      margin-top: 0 !important;
    }
    .time {
      text-align: center;
    }
    .matter {
      display: flex;
      .dot {
        flex-shrink: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 4px;
        margin-right: 4px;
        border-radius: 50%;
        font-size: 8px;
        color: #f82532;
        width: 16px;
        height: 16px;
        background: rgba(249, 48, 55, 0.15);
      }
    }
  }
  .sign-up {
    display: block;
    margin: 15px auto 0 auto;
    width: 197px;
    height: 47px;
  }
}
</style>
