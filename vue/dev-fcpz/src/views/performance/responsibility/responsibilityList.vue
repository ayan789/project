<template>
  <div class="responsibility-list">
    <div class="title-container">
      <van-nav-bar title="责任书" left-arrow @click-left="globalBack()" />
    </div>
    <!-- 修改--添加 -->
    <!-- @click-left="$router.back()" -->
    <van-pull-refresh
      v-model="refreshing"
      :finished="finished"
      @refresh="onRefresh"
      style="height:600px;"
    >
      <van-list
        :finished="finished"
        finished-text="没有更多了"
        style="height:100%;min-height:400px;"
      >
        <van-cell
          v-for="(item, index) in list"
          :key="item.id"
          center
          style="height:61px;"
          clickable
          @click="onItemClick(item, index)"
        >
          <template slot="title">
            <van-row type="flex" justify="space-between" align="center">
              <span class="custom-title">{{ item.responsibilityName }}</span>
              <span
                v-if="isTeamLeader == 0"
                :class="[
                  'tag-normal',
                  item.signStatus === '1' ? 'tag-complete' : ''
                ]"
              >
                {{ statusName(item.signStatus) }}
              </span>

              <span
                v-if="isTeamLeader == 1"
                :class="[
                  'tag-normal',
                  tList.signStatus === '1' && pList.signStatus === '1'
                    ? 'tag-complete'
                    : ''
                ]"
              >
                {{
                  tList.signStatus === "1" && pList.signStatus === "1"
                    ? "已签署"
                    : "未签署"
                }}
              </span>
            </van-row>
          </template>
        </van-cell>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
import { Tag, PullRefresh, List, Toast } from "vant";
import {
  responsibilityListQuery,
  firstListQuery
} from "@src/http/module/performance.js";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    [Tag.name]: Tag,
    [PullRefresh.name]: PullRefresh,
    [List.name]: List,
    [Toast.name]: Toast
  },
  data: () => ({
    newdate: "",
    entrydate: "",
    salesdate: "",
    userInfo: "",
    isTeamLeader: "",
    refreshing: true,
    finished: true,
    list: [],
    tList: [],
    pList: [],
    originalList: [],
    // 判断第一次数据
    queryData: {
      userCode: localStorage.getItem("userCode"),
      isYear: new Date().getFullYear().toString(),
      isTeamLeader: JSON.parse(localStorage.getItem("userInfo")).isTeamLeader
    }
  }),
  created() {
    //埋点
    trackMap("c_performance_responsibility_letter", "业绩责任书", {
      c_salesperson_type: "Y",
      c_market_type: "Y",
      c_market_name: "",
      c_market_id: "",
      c_event_category: "业绩"
    });
    this.entrydate =
      JSON.parse(localStorage.getItem("userInfo")).entrydate || "";
    this.salesdate =
      JSON.parse(localStorage.getItem("userInfo")).salesdate || "";
    if (this.entrydate && this.salesdate) {
      this.entrydate = this.entrydate
        .toString()
        .replace(/-/g, "")
        .slice(0, 8);
      this.salesdate = this.salesdate
        .toString()
        .replace(/-/g, "")
        .slice(0, 8);
      if (this.salesdate >= this.entrydate) {
        if (+this.salesdate.slice(0, 4) === new Date().getFullYear()) {
          this.newdate =
            this.salesdate.slice(4, 5) == 0
              ? +this.salesdate.slice(5, 6) - 1
              : +this.salesdate.slice(4, 6) - 1;
        }
      }
    } else if (this.entrydate) {
      this.entrydate = this.entrydate
        .toString()
        .replace(/-/g, "")
        .slice(0, 8);
      if (+this.entrydate.slice(0, 4) === new Date().getFullYear()) {
        this.entrydate =
          this.entrydate.slice(4, 5) == 0
            ? +this.entrydate.slice(5, 6) - 1
            : +this.entrydate.slice(4, 6) - 1;
      }
    } else if (this.salesdate) {
      this.salesdate = this.salesdate
        .toString()
        .replace(/-/g, "")
        .slice(0, 8);
      if (+this.salesdate.slice(0, 4) === new Date().getFullYear()) {
        this.newdate =
          this.salesdate.slice(4, 5) == 0
            ? +this.salesdate.slice(5, 6) - 1
            : +this.salesdate.slice(4, 6) - 1;
      }
    }
    console.log("newdate------->", this.newdate);
    // if(this.entrydate && this.salesdate){
    //   this.entrydate = new Date(this.entrydate)
    //   this.salesdate = new Date(this.salesdate)
    //   console.log(this.entrydate,"===");
    //   console.log(this.salesdate,"===");
    //   if (this.salesdate >= this.entrydate){
    //     if(this.salesdate.getFullYear() === new Date().getFullYear()) {
    //       this.newdate = this.salesdate.getMonth()
    //     }
    //   }
    // }else if(this.entrydate) {
    //     this.entrydate = new Date(this.entrydate)
    //     if(this.entrydate.getFullYear() === new Date().getFullYear()) {
    //         this.newdate = this.entrydate.getMonth()
    //     }
    // }else if (this.salesdate) {
    //     this.salesdate = new Date(this.salesdate)
    //     if(this.salesdate.getFullYear() === new Date().getFullYear()) {
    //         this.newdate = this.salesdate.getMonth()
    //     }
    // }
    // console.log("newdate------->",this.newdate)
    localStorage.setItem("newdate", JSON.stringify(this.newdate));
    this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.isTeamLeader = this.userInfo.isTeamLeader;
    this.onRefresh();
    this.onBack();
  },

  methods: {
    /* 修改--添加 */
    globalBack() {
      //跳到业绩页面
      this.$router.push({
        name: "performance"
      });
    },
    // 判断是否第一次接口
    onBack() {
      firstListQuery(this.queryData).then(
        data => {
          if (data.code === "1") {
            this.onLoad();
          } else {
            Toast.fail(data.msg);
          }
        },
        error => {
          //console.log(error);
        }
      );
    },
    onLoad() {
      let param = {
        userCode: localStorage.getItem("userCode"),
        page: {
          page: 1,
          size: 10
        }
      };
      if (this.refreshing) {
        this.list = [];
        this.refreshing = false;
      }

      responsibilityListQuery(param).then(
        data => {
          if (data.code === "1") {
            let list = [];
            this.originalList = data.data.list;
            this.tList = data.data.list.filter(
              item => item.responsibilityType === "T"
            )[0];
            this.pList = data.data.list.filter(
              item => item.responsibilityType === "P"
            )[0];
            list.push(
              data.data.list.filter(item => item.responsibilityType === "P")[0]
            );
            // if (this.isTeamLeader == 1) {
            //   list.push(
            //     data.data.list.filter(
            //       item => item.responsibilityType === "T"
            //     )[0]
            //   );
            // } else if (this.isTeamLeader == 0) {
            //   list.push(
            //     data.data.list.filter(
            //       item => item.responsibilityType === "P"
            //     )[0]
            //   );
            // }
            this.list = list;
          } else {
            Toast.fail(data.msg);
          }
        },
        error => {
          //console.log(error);
        }
      );
    },
    onRefresh() {
      this.finished = false;
      this.refreshing = true;
      this.onLoad();
    },
    onItemClick(item, index) {
      /* 修改--添加 */
      if (
        // (this.isTeamLeader == 0 && item.signStatus === "1") ||
        // (this.isTeamLeader == 1 && this.tList.signStatus==1 )
        (this.isTeamLeader == 0 &&
          item.signStatus === "1" &&
          item.changeStatus !== "1") ||
        (this.isTeamLeader == 1 &&
          this.tList.signStatus == 1 &&
          this.tList.changeStatus !== "1")
      ) {
        this.$router.push({
          name: "promiseBook",
          query: {
            id: item.id
          }
        });
      } else {
        localStorage.setItem("originalList", JSON.stringify(this.originalList));
        this.$router.push({
          name: "responsibilityEdit",
          query: {
            data: JSON.stringify(this.originalList)
          }
        });
      }
      //console.log(index, item);
      // this.$router.push({ name: "responsibilityDetail", params: item });
    },
    statusName(status) {
      switch (status) {
        case "0":
          return "未签署";
        case "1":
          return "已签署";
        // case "3":
        //   return "审批中";
        // case "4":
        //   return "已审核";
        default:
          return "未签署";
      }
    }
  }
};
</script>

<style lang="less" scoped>
.responsibility-list {
  width: 375px;
  height: 667px;
  background-color: #f7f7f7;
  .title-container {
    background: linear-gradient(
      129deg,
      rgb(30, 70, 246) 0%,
      rgb(39, 154, 255) 100%
    );
    .van-nav-bar {
      background: none;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
  }
}
.tag-normal {
  width: 57px;
  height: 24px;
  background: #eff6ff;
  border-radius: 12px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 24px;
  color: #3b8afe;
  text-align: center;
}
.tag-complete {
  background: #ecfcf1;
  color: #1a8c37;
}
</style>
