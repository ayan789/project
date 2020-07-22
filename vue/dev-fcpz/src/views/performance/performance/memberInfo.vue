<template>
  <div>
    <Global_globalNav
      title="成员业绩"
      @clickBack="$router.back()"
    ></Global_globalNav>
    <div class="member-info">
      <div class="member-detail">
        <p>
          <!-- <img src="http://placehold.it/51x51" alt="" /> -->
          <img
              :src="
                `https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/${userCode}`
              "
            />
        </p>
        <div>
          <p>{{ userName }}</p>
          <p>
            <span class="gray">所属机构</span>
            <span class="black">{{ resultData.teamName }}</span>
          </p>
          <p>
            <span class="gray">直销渠道</span>
            <span class="black">{{ resultData.channelName }}</span>
          </p>
        </div>
      </div>
      <personal-performance></personal-performance>
      <rate-progress></rate-progress>
    </div>
  </div>
</template>

<script>
import personalPerformance from "./personalPerformance";
import { teamQuery } from "@src/http/module/performance.js";
import {
  firstListQuery
} from "@src/http/module/performance";
//达成进度
import rateProgress from "./rateProgress";
export default {
  components: {
    personalPerformance,
    rateProgress
  },
  data: () => ({
    resultData: {},
  }),
  computed: {
    userName() {
      return this.$route.query.userName;
    },
    /* 修改--添加 */
    userCode() {
      return this.$route.query.userCode;
    }
  },
  async created() {
    /* 修改--添加 */
    firstListQuery({
        userCode: this.$route.query.userCode,
        isYear: new Date().getFullYear().toString(),
        isTeamLeader: ''
      }
    ).then(
      data => {
        if (data.code === "1") {
          console.log(data.msg);
          } else {
            console.log(data.msg);
          }
        },
        error => {
          //console.log(error);
        }
    );  
    
    await teamQuery({
      userCode: this.$route.query.userCode
    }).then(res => {
      if (res.code === "1") {
        this.resultData = { ...res.data };
      }
    });
  }
};
</script>

<style lang="less" scoped>
.member-info {
  background: #fff;
  margin-top: 80px;
  .member-detail {
    box-sizing: border-box;
    padding-top: 20px;
    width: 345px;
    height: 159px;
    margin: 0 auto;
    margin-bottom: 20px;
    text-align: center;
    background: #ffffff;
    box-shadow: 0px 1px 19px 0px rgba(199, 199, 199, 0.15);
    div {
      display: inline-block;
      & > p:nth-child(1) {
        font-size: 18px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 7px;
        width: 100%;
        height: 20px;
        text-align: left;
      }
      & > p:nth-child(2) {
        margin-bottom: 6px;
        text-align: left;
      }
      & > p:nth-child(3) {
        margin-bottom: 6px;
        text-align: left;
      }
      .gray {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        margin-right: 11px;
      }
      .black {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
    }
    & > p:nth-child(1) {
      margin: 0 20px 0 8px;
      height: 51px;
      width: 51px;
      // width: 100%;
      border-radius: 50%;
      overflow: hidden;
      display: inline-block;
    }
  }
}
</style>
