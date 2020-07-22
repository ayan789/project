<template>
  <div class="team-members">
    <div class="members_title">团队成员</div>
    <van-collapse v-model="activeNames" :border="false">
      <van-collapse-item
        :name="index"
        :border="false"
        v-for="(item, index) in teamMember"
        :key="index"
      >
        <div slot="title">
          {{ item.rankCode }}({{ item.teamUserList.length }}人）
        </div>
        <div slot="value" v-show="!activeNames.includes(index)">
          <img
            v-for="v in item.teamUserList"
            :key="v.userCode"
            class="teamUserList-img"
            :src="baseUrl + v.userCode"
            :onerror="defaultImg"
          />
          <!-- <img
            class="teamUserList-img"
            src="http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/8000657891"
            alt=""
          />
          <img
            class="teamUserList-img"
            src="http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/8000657891"
            alt=""
          />
          <img
            class="teamUserList-img"
            src="http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/8000657891"
            alt=""
          /> -->
        </div>
        <div
          slot="default"
          class="members_content"
          v-for="(value, i) in item.teamUserList"
          :key="i"
          @click="membersDetails(value)"
        >
          <p>
            <!-- <img :src="item.img" /><span>{{ value.userName }}</span> -->
            <!--外网 https://msupsale-test.ccic-net.com.cn/ -->
            <!-- 内网"http://mobilesales-supsale-test.caas.ccic-test.com.cn -->
            <img :src="baseUrl + value.userCode" :onerror="defaultImg" /><span
              >{{ value.userName }}</span
            >
          </p>
          <p>
            <span>总保费</span><span>{{ value.realPay }}元</span>
          </p>
        </div>
      </van-collapse-item>
    </van-collapse>
  </div>
</template>

<script>
//团队业绩查询
import { teamQuery } from "@src/http/module/performance.js";
import { Collapse, CollapseItem } from "vant";
export default {
  components: {
    [Collapse.name]: Collapse,
    [CollapseItem.name]: CollapseItem
  },
  data() {
    return {
      activeNames: ["1"],
      teamMember: [],
      baseUrl: "https://msupsale-test.ccic-net.com.cn/api/cst/s3/browse/",
      defaultImg:
        'this.src="' +
        require("../../../assets/performance/team_default.png") +
        '"'
    };
  },
  computed: {
    IP() {
      return this.$rootPath;
    }
  },
  async created() {
    await teamQuery({
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode
    }).then(res => {
      if (res.code === "1") {
        this.teamMember = [...res.data.teamRankList];
      }
    });
  },
  methods: {
    membersDetails(code) {
      const userInfo = JSON.parse(localStorage.getItem("userInfo")) || {};
      if (userInfo.isTeamLeader == "0") {
        this.$toast("团队长可查询团队成员业绩明细");
      } else if (userInfo.isTeamLeader == "1") {
        this.$router.push({
          path: "/memberInfo",
          query: {
            userCode: code.userCode,
            userName: code.userName,
            isMember: 1
          }
        });
      }
    }
  }
};
</script>

<style lang="less" scoped>
.team-members {
  margin: 0 auto;
  background: #fff;
  margin-top: 10px;
  padding-bottom: 10px;
  .members_title {
    font-size: 14px;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    height: 47px;
    line-height: 47px;
    margin: 0 19px;
    border-bottom: 1px solid #efefef;
  }
  .teamUserList-img {
    width: 30px;
    height: 30px;
    border-radius: 30px;
    margin-left: -14px;
    display: inline-block;
  }
  .members_content {
    display: flex;
    display: -webkit-flex;
    justify-content: space-between;
    -webkit-justify-content: space-between;
    p:nth-child(1) {
      img {
        width: 30px;
        height: 30px;
        border-radius: 30px;
        vertical-align: middle;
        margin-right: 10px;
      }
      span {
        font-size: 16px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
    }
    p:nth-child(2) {
      span:nth-child(1) {
        font-size: 12px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
      }
      span:nth-child(2) {
        font-size: 14px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
        margin-left: 4px;
      }
    }
  }
}
</style>
