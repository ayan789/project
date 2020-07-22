<template>
  <div class="rankDetail">
    <Global_globalNav
      title="团队排名"
      @clickBack="
        () => {
          this.$router.back();
        }
      "
    ></Global_globalNav>
    <div class="tabs">
      <span
        class="tabMonth"
        v-for="(item, index) in tabMonth"
        :key="index"
        @click="actived(index)"
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
        >
          <p>
            <span v-if="index === 0">
              <img src="../../../assets/performance/rankTop.png" />
            </span>
            <span v-else-if="index === 1">
              <img src="../../..//assets/performance/rankSecond.png" />
            </span>
            <span v-else-if="index === 2">
              <img src="../../..//assets/performance/rankThird.png" />
            </span>
            <span v-else>{{ index + 1 }}</span
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
export default {
  data() {
    return {
      activedItem: 0,
      tabMonth: ["本月", "本季", "本年"],
      personRank: [],
      selfRank: {}
    };
  },
  filters: {
    filterPm(val) {
      if (Number(val) > 0) {
        return `+${val}`;
      } else if (Number(val) === 0) {
        return `—`;
      } else {
        return `${val}`;
      }
    }
  },
  created() {
    this.queryRank();
  },
  methods: {
    actived(index) {
      this.activedItem = index;
      this.queryRank();
    },
    async queryRank() {
      const {actived} = this.$route.query
      const params = {
        rankFlag: !actived?'P':'T',
        teamComFlag: actived?'F':'T',
        teamCode: this.$localStorage.teamcode,
        timeFlag: ["M", "Q", "Y"][this.activedItem],
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode
      };
      await getpersAndTeamRanking(params).then(res => {
        if (res.code === "1") {
          this.$set(
            this.selfRank,
            "name",
            actived === 0
              ? res.data.rankResponseVo.userName
              : res.data.rankResponseVo.teamName
          );
          this.$set(this.selfRank, "pm", res.data.rankResponseVo.pm);
          this.$set(this.selfRank, "jrpm", res.data.rankResponseVo.jrpm); // jrpm是当前排名
          if (actived=== 1) {
            res.data.rankResponseVo.rankDetail = JSON.parse(
              JSON.stringify(res.data.rankResponseVo.rankDetail).replace(
                /teamName/g,
                "userName"
              )
            );
          }
          this.personRank = [...res.data.rankResponseVo.rankDetail];
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.rankDetail {
  background: #ffffff;
}
.tabs {
  margin: 0 19px;
  margin-top: 80px;
  padding-top: 10px;
  background: #fff;
  & > span {
    display: inline-block;
    width: 64px;
    height: 24px;
    line-height: 24px;
    background: #fff;
    border-radius: 5px;
    border: 1px solid rgba(224, 224, 224, 1);
    opacity: 0.2;
    font-size: 12px;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
    text-align: center;
    margin-right: 8px;
    margin-bottom: 30px;
  }
  & > span:nth-child(1) {
    margin-left: 20px;
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
      font-size: 14px;
      font-weight: 400;
      color: rgba(31, 81, 155, 1);
      span:nth-child(1) {
        margin-right: 19px;
      }
    }
    p:nth-child(2) {
      font-size: 12px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      span:nth-child(1) {
        margin-right: 6px;
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
    /* 修改--添加 */
    // margin: 0 20px;
    border-bottom: 1px solid #f4f4f4;
    p:nth-child(1) {
      font-size: 14px;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      span:nth-child(1) {
        margin-right: 19px;
        vertical-align: middle;
        /* 修改--添加 */
           img {
            vertical-align: middle;
            margin-left: -13px;
            margin-right: -13px;
          }
      }
    }
    p:nth-child(2) {
      font-size: 12px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      span:nth-child(1) {
        margin-right: 6px;
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
    margin-bottom: 14px;
  }
  .more-icon {
    margin-left: 5px;
    vertical-align: middle;
  }
}
</style>
