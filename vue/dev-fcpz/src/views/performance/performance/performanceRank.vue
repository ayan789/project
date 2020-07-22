<template>
  <div class="performanceRank">
    <div class="performanceRank_head" style="border:none">
      <div class="title">
        {{ actived == "1" ? "团队" : "个人" }}排名
        <!-- <van-icon
          name="info-o"
          class="icon"
        /> -->
        <img src="../../../assets/performance/icon7.png" />
      </div>
      <div class="rankTab">
        <span
          v-for="(item, index) in actived === 0 ? tabRank : tabRank_"
          :key="index"
          @click="change(index)"
          :class="[activedOrg === index ? 'actived' : '']"
          >{{ item }}</span
        >
      </div>
    </div>
    <div v-if="activedOrg === 0" class="tabs">
      <span
        class="tabMonth"
        v-for="(item, index) in tabMonth"
        :key="index"
        @click="tabActived(index)"
        :class="[activedItem === index ? 'tabActived' : '']"
      >
        {{ item }}
      </span>
      <div class="rank_content">
        <div class="own_rank">
          <p>
            <span>{{ selfRank.jrpm }}</span
            ><span>{{ selfRank.name }}（我）</span>
          </p>
          <p>
            <span>较昨日排名</span
            ><span
              :class="[Number(selfRank.pm) > 0 ? 'addRank' : 'reduceRank']"
              >{{ selfRank.pm | filterPm }}</span
            >
          </p>
        </div>
        <div
          class="tatol_rank"
          v-for="(item, index) in personRank"
          :key="index"
          v-show="index < 5"
        >
          <p>
            <span v-if="index === 0">
              <!-- <img src="../../../assets/performance/rankTop.png" /> -->
              <img src="../../../assets/performance/firstRank.png" />
            </span>
            <span v-else-if="index === 1">
              <!-- <img src="../../..//assets/performance/rankSecond.png" /> -->
              <img src="../../..//assets/performance/secondRank.png" />
            </span>
            <span v-else-if="index === 2">
              <!-- <img src="../../..//assets/performance/rankThird.png" /> -->
              <img src="../../..//assets/performance/thirdRank.png" />
            </span>
            <span v-else-if="index < 5">{{ index + 1 }}</span
            ><span>{{ item.userName }}</span>
          </p>
          <p>
            <span>较昨日排名</span
            ><span
              :class="[Number(item.pm) > 0 ? 'addRank' : 'reduceRank']"
              :style="{ color: Number(item.pm) === 0 ? '#666' : '' }"
              >{{ item.pm | filterPm }}</span
            >
          </p>
        </div>
        <div
          class="more"
          v-if="actived === 0 && activedOrg === 0 && personRank.length > 5"
          @click="()=>$router.push({name:'rankDetail',query:{actived:this.actived}})"
        >
          更多<van-icon name="arrow" class="more-icon" />
        </div>
        <div
          class="more"
          v-else-if ="actived === 1 && activedOrg === 0 && personRank.length > 5"
          @click="()=>$router.push({name:'rankDetail',query:{actived:this.actived}})"
        >
          更多<van-icon name="arrow" class="more-icon" />
        </div>
      </div>
    </div>
    <div v-else-if="activedOrg === 1" class="tabs">
      <span
        class="tabMonth"
        v-for="(item, index) in tabMonth"
        :key="index"
        @click="tabActived(index)"
        :class="[activedItem === index ? 'tabActived' : '']"
      >
        {{ item }}
      </span>
      <div class="rank_content">
        <div class="own_rank">
          <p>
            <span>{{ selfRank.jrpm }}</span
            ><span>{{ selfRank.name }}（我）</span>
          </p>
          <p>
            <span>较昨日排名</span
            ><span
              :class="[Number(selfRank.pm) > 0 ? 'addRank' : 'reduceRank']"
              >{{ selfRank.pm | filterPm }}</span
            >
          </p>
        </div>
        <div
          class="tatol_rank"
          v-for="(item, index) in personRank"
          :key="index"
          v-show="index < 10"
        >
          <p>
            <span v-if="index === 0">
              <!-- <img src="../../../assets/performance/rankTop.png" /> -->
              <img src="../../../assets/performance/firstRank.png" />
            </span>
            <span v-else-if="index === 1">
              <!-- <img src="../../..//assets/performance/rankSecond.png" /> -->
              <img src="../../..//assets/performance/secondRank.png" />
            </span>
            <span v-else-if="index === 2">
              <!-- <img src="../../..//assets/performance/rankThird.png" /> -->
              <img src="../../..//assets/performance/thirdRank.png" />
            </span>
            <span v-else-if="index < 10">{{ index + 1 }}</span
            ><span>{{ item.userName }}</span>
          </p>
          <p>
            <span>较昨日排名</span
            ><span
              :class="[Number(item.pm) > 0 ? 'addRank' : 'reduceRank']"
              :style="{ color: Number(item.pm) === 0 ? '#666' : '' }"
              >{{ item.pm | filterPm }}</span
            >
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getpersAndTeamRanking } from "@src/http/module/performance.js";
import { Icon, Tab, Tabs } from "vant";
export default {
  props: {
    actived: {
      type: Number
    }
  },
  filters: {
    filterPm(val) {
      if (isNaN(Number(val)) ||  Number(val) === 0) {
        return `—`;
      } else if (Number(val) > 0) {
        return `+${val}`;
      } else {
        return `${val}`;
      }
    }
  },
  components: {
    [Icon.name]: Icon,
    [Tab.name]: Tab,
    [Tabs.name]: Tabs
  },
  data() {
    return {
      activeName: "a",
      tabRank: ["团队排名", "分公司排名"],
      tabRank_: ["分公司排名", "总公司排名"],
      tabMonth: ["本月", "本季", "本年"],
      activedOrg: 0,
      activedItem: 0,
      personRank: [],
      selfRank: {},
      isTeam: "",
      isTeamLeader: ""
    };
  },
  created() {
  },
  mounted() {
    const userInfo = JSON.parse(localStorage.getItem("userInfo"));
    //判断是否加入团队
    this.isTeam = userInfo.teamcode ? true : false;
    this.isTeamLeader = userInfo.isTeamLeader;
    if (!this.isTeam && this.isTeamLeader == 0) {
      this.tabRank = ["分公司排名"];
    }
    this.queryRank();
  },
  methods: {
    change(index) {
      this.activedOrg = index;
      this.queryRank();
    },
    tabActived(index) {
      this.activedItem = index;
      this.queryRank();
    },
    async queryRank() {
     let  _this = this;
      let params = {
        rankFlag: _this.actived === 0 ? "P" : "T",
        teamComFlag:
          _this.actived === 1
            ? (_this.activedOrg == 0
              ? "F"
              : "Z")
            : (_this.activedOrg == 0
              ? "T"
              : "F"),
        timeFlag: ["M", "Q", "Y"][_this.activedItem]
        // userCode: JSON.parse(localStorage.getItem("userInfo")).userCode
      };
      if (!_this.isTeam && _this.isTeamLeader == 0) {
        params.teamComFlag = "F";
      }
      _this.actived === 0
        ? (params.userCode = JSON.parse(localStorage.getItem("userInfo")).userCode)
        : (params.teamCode = JSON.parse(localStorage.getItem("userInfo")).teamcode);
      await getpersAndTeamRanking(params).then(res => {
        if (res.code === "1") {
          if(this.$isNull(res.data)){
            return;
          }
          _this.$set(_this.selfRank, "pm", res.data.rankResponseVo.pm);   // pm是浮动值
          _this.$set(_this.selfRank, "jrpm", res.data.rankResponseVo.jrpm); // jrpm是当前排名
          _this.$set(
            _this.selfRank,
            "name",
            _this.actived === 0
              ? res.data.rankResponseVo.userName
              : res.data.rankResponseVo.teamName
          );
          if (_this.actived === 1) {
            res.data.rankResponseVo.rankDetail = JSON.parse(
              JSON.stringify(res.data.rankResponseVo.rankDetail).replace(
                /teamName/g,
                "userName"
              )
            );
          }
          _this.personRank = [...res.data.rankResponseVo.rankDetail];
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.performanceRank {
  background: rgba(255, 255, 255, 1);
  margin: 0 auto;
  margin-top: 10px;
  &_head {
    padding: 0 20px;
    display: flex;
    display: -webkit-flex;
    display: -ms-flex;
    display: -o-flex;
    justify-content: space-between;
    padding: 11px 0;
    margin: 0 20px 18px;
    border-bottom: 1px solid #efefef;
    .title {
      font-size: 15px;
      /* -- */
      font-weight: 700;
      font-family:PingFangSC-Medium,PingFang SC;
      font-weight:600;
      color: #333333;
      display: flex;
      align-items: center;
      img {
        margin-left: 5px;
        width: 18px;
        height: 18px;
      }
    }
    .icon {
      margin-left: 8px;
      vertical-align: middle;
    }
    .rankTab {
      font-size: 12px;
      font-weight: 400;
      color: rgba(142, 142, 142, 1);
      span:nth-child(1) {
        margin-right: 8px;
      }
      .actived {
        color: rgba(51, 51, 51, 1);
        font-size: 14px;
        position: relative;
        &::after {
          margin-top: 2px;
          position: absolute;
          display: inline-block;
          content: "";
          width: 27px;
          height: 3px;
          background: rgba(59, 138, 254, 1);
          bottom: -5px;
          left: 50%;
          transform: translate(-50%, 4px);
        }
      }
    }
  }
  .tabs {
    margin: 0 19px;
    & > span {
      display: inline-block;
      width: 1.70667rem;
      height: 24px;
      line-height: 24px;
      // background: #d8d8d8;
      // border-radius: 21px;
      border-radius: 0.13333rem;
      border: 0.02667rem solid #e0e0e0;
      opacity: 0.8;
      font-size: 12px;
      font-weight: 400;
      // color: #999999;
      text-align: center;
      // margin-right: 8px;
      margin-right: 0.21333rem;
      // margin-bottom: 30px;
      margin-bottom: 20px;
    }
    & > span:nth-child(3) {
      margin-right: 0;
    }
    .tabActived {
      background: linear-gradient(
        270deg,
        rgba(55, 147, 255, 1) 0%,
        rgba(36, 90, 237, 1) 100%
      );
      color: rgba(255, 255, 255, 1);
      opacity: 1;
    }
  }
  .rank_content {
    .own_rank {
      display: flex;
      display: -webkit-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
      height: 48px;
      background: rgba(241, 247, 255, 1);
      border-radius: 5px;
      border: 1px dashed rgba(190, 208, 231, 1);
      align-items: center;
      -webkit-align-items: center;
      padding: 0 8px 0 12px;
      p:nth-child(1) {
        font-size: 13px;
        font-weight: 400;
        color: rgba(31, 81, 155, 1);
        span:nth-child(1) {
          margin-right: 6px;
        }
        /* -- */
        span:nth-child(2) {
          color: #064FBB;
        }
      }
      p:nth-child(2) {
        font-size: 12px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        span:nth-child(1) {
          /* -- */
          // margin-right: 6px;
        }
      }
    }
    .tatol_rank {
      display: flex;
      display: -webkit-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
      height: 48px;
      border-radius: 5px;
      align-items: center;
      -webkit-align-items: center;
      padding: 0 8px 0 12px;
      border-bottom: 1px solid #f4f4f4;
      p:nth-child(1) {
        font-size: 13px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        span:nth-child(1) {
          margin-right: 14px;
          img {
            vertical-align: middle;
            margin-left: -13px;
            /* -- */
            // margin-right: -13px;
          }
        }
      }
      p:nth-child(2) {
        font-size: 12px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        span:nth-child(1) {
          // margin-right: 6px;
        }
      }
    }
    .addRank {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(63, 166, 107, 1);
    }
    .reduceRank {
      font-size: 14px;
      font-weight: 400;
      color: rgba(206, 34, 98, 1);
    }
    .more {
      text-align: center;
      font-size: 12px;
      color: rgba(153, 153, 153, 1);
      margin-top: 10px;
      padding-bottom: 14px;
    }
    .more-icon {
      margin-left: 5px;
      vertical-align: middle;
    }
  }
}
</style>
