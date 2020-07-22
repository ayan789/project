/* 曹征前 营销活动-活动列表 2020.03.24*/
<template>
  <div class="marketingActivities">
    <div class="marketingActivitiesHeader">
      <!--营销活动列表的顶部轮播图-->
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item v-for="picture in pictureList" :key="picture.index">
          <img
            style="width:100%;height:100%"
            :src="picture.remindPicture"
            alt="edit"
            @click="toDeail(picture)"
          />
        </van-swipe-item>
      </van-swipe>
    </div>
    <div
      style="  position: absolute;
        top:20px;
        left: 0px;
        right: 0px;"
    >
      <NavActivity :title="''" />
    </div>
    <div
      v-if="isNoActivies == true"
      :style="{ backgroundImage: 'url(' + noDataIMG + ')' }"
      class="nodate-image"
    ></div>
    <div
      v-if="isNoActivies == true"
      style="display: flex;justify-content: center;margin-top: -10px"
    >
      <span style="color:#999999;font-size: 16px">暂无数据</span>
    </div>

    <div class="content-back" v-if="isNoActivies == false">
      <!--每一个活动-->
      <div
        class="activeCard"
        v-for="item in activeCardList"
        :key="item.id"
        @click="toDeail(item)"
      >
        <p
          style="clolor:#333333;font-size: 16px;padding-top: 10px;font-weight: 600"
        >
          {{ item.activityName }}
        </p>
        <div class="activeCardContent">
          <div class="pic">
            <img
              style="width: 123px;height: 82px;border-radius: 5px"
              :src="item.activityPicture"
              alt="edit"
              v-if="item.activityPicture"
            />
            <img
              style="width: 123px;height: 82px;border-radius: 5px"
              src="@/assets/activity/listHeard.png"
              alt="edit"
              v-else
            />
          </div>
          <p>{{ item.activitySummarize }}</p>
        </div>
        <div class="activeCardFooter">
          <p v-if="item.awardForm == 1">已领取福利：{{ item.isGet }}个</p>
          <p v-if="item.awardForm == 1">待领取福利：{{ item.notGet }}个</p>
          <p v-if="item.awardForm == 2">
            已领取福利：{{
              item.isGet == "" || item.isGet == null ? "0" : item.isGet
            }}个
          </p>
          <p v-if="item.awardForm == 2">待领取福利：{{ item.notGet }}个</p>
          <p v-if="item.awardForm == 3">
            当前绩效奖励：{{
              item.awardName == "" || item.awardName == null
                ? 0
                : item.awardName
            }}元
          </p>
          <p v-if="item.awardForm == 3">
            最高绩效奖励：{{ item.maxAwardName }}%
          </p>
          <p v-if="item.awardForm == 4">
            当前绩效奖励：{{
              item.awardName == "" || item.awardName == null
                ? 0
                : item.awardName
            }}元
          </p>
          <p v-if="item.awardForm == 4">
            最高绩效奖励：{{ item.maxAwardName }}元
          </p>
          <p v-if="item.awardForm == 5">
            当前绩效奖励：{{
              item.awardName == "" || item.awardName == null
                ? "无"
                : item.awardName
            }}
          </p>
          <p v-if="item.awardForm == 5">
            最高绩效奖励：{{ item.maxAwardName }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import NavActivity from "../common/navActivity";
import noDataIMG from "../../../assets/customer/customerEquity/noData.png";
export default {
  name: "marketingActivities",
  components: {
    NavActivity
  },
  computed: {
    ...mapState(["activityData"])
  },
  data() {
    return {
      activeCardList: [], //活动列表
      salesmanCode: this.localStorage.get("userInfo", {}).userCode,
      pictureList: [], //轮播图列表
      noDataIMG: noDataIMG, //没有数据展示的图片
      isNoActivies: true, //true没有活动，false有活动
      test:
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585064387927&di=f91b1aeb849601ae79a9a1dda23e5210&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F78%2F52%2F01200000123847134434529793168.jpg"
    };
  },
  async created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    this.post_activityList(); //获取活动列表
    this.post_picture(); //获取轮播图片
  },

  methods: {
    //路由返回
    back() {
      this.$globalBack();
    },
    //获取活动列表
    async post_activityList() {
      let params = { salesmanCode: this.salesmanCode };
      await this.$store.dispatch("activityData/getActivityList", params);
      let activityList = this.activityData.activityList;
      if (
        activityList &&
        activityList.activityResponseList &&
        activityList &&
        activityList.activityResponseList.length > 0
      ) {
        this.activeCardList = activityList && activityList.activityResponseList;
        this.isNoActivies = false;
      } else {
        this.isNoActivies = true;
      }
    },

    //获取轮播图片
    async post_picture() {
      let params = { salesmanCode: this.salesmanCode };
      await this.$store.dispatch("activityData/getPicture", params);
      let picture = this.activityData.picture;
      this.pictureList = picture && picture.data && picture.data.list;
    },

    toDeail(item) {
      //判断是走轮播图点击，还是走列表点击
      if (item.activityUrl) {
        //点击bnner轮播图跳转
        let url = item.activityUrl;
        if (url.indexOf("http") >= 0) {
          //轮播图返回的是真实的h5链接，直接跳转
          window.location.href = url;
        } else {
          //轮播图返回的是站内路由跳转
          //ps:"activities/activity/brave?activityCode=3100-20200411-0624
          let activityCode = url.slice(url.indexOf("=") + 1, url.length);
          if (url.indexOf("brave") >= 0) {
            //勇士无敌活动
            this.$router.push({
              name: "brave",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          } else if (url.indexOf("woodpecker") >= 0) {
            //啄木鸟活动
            this.$router.push({
              name: "woodpecker",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          } else {
            //正常详情页
            this.$router.push({
              name: "activityDetail",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          }
        }
      } else {
        //点击列表跳转
        let activityCode = item.activityCode;
        if (item.activityDetailsSource == 2) {
          //如果是第三方链接，判断是否是项目内页面
          if (item.activityDetails.indexOf("brave") >= 0) {
            this.$router.push({
              name: "brave",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          } else if (item.activityDetails.indexOf("woodpecker") >= 0) {
            this.$router.push({
              name: "woodpecker",
              query: {
                item: {
                  activityCode: activityCode,
                  salesmanCode: this.salesmanCode
                }
              }
            });
          } else {
            window.location.href = item.activityDetails;
          }
        } else {
          this.$router.push({
            name: "activityDetail",
            query: {
              item: {
                activityCode: activityCode,
                salesmanCode: this.salesmanCode
              }
            }
          });
        }
      }
    }
  }
};
</script>

<style lang="less" scoped>
.marketingActivities {
  font-family: PingFangSC-Medium, PingFang SC;
  background: #f7f7f7;
  position: relative;
  .nodate-image {
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 300px;
    margin: 0px auto;
    height: 300px;
  }
  .content-back {
    background: #f7f7f7;
    position: relative;
    top: -25px;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 70vh;
    overflow-y: scroll;
    border-radius: 10px;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
  }

  h1 {
    text-align: center;
  }
  .marketingActivitiesHeader {
    /*width: 100%;*/
    height: 30vh;
    background: url("../../../assets/activity/listHeard.png");
    background-size: 100% 100%;
    .my-swipe {
      height: 100%;
      background-color: #3b8afe;
    }
    .hearder-title {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      .header-one {
        position: absolute;
        top: 0px;
        left: 10px;
        height: 20px;
        width: 20px;
      }
      .header-two {
        color: #ffffff;
        font-size: 17px;
      }
    }
    .hearder-content {
      padding-top: 40px;
      padding-left: 40px;
      .content-one {
        color: white;
        font-size: 17px;
      }
      .content-two {
        margin-top: 5px;
        color: white;
        font-size: 18px;
      }
    }
  }

  .activeCard {
    width: 85%;
    height: 200px;
    background: rgba(255, 255, 255, 1);
    border-radius: 7px;
    padding: 0 13px;
    margin-bottom: 10px;

    h3 {
      height: 22px;
      font-size: 14px;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
      text-align: center;
    }
    .activeCardContent {
      height: 100px;
      /*display: flex;*/
      /*align-items: center;*/
      /*padding: 9px 0;*/
      position: relative;
      border-bottom: 1px solid #e5e6e8;
      .pic {
        width: 123px;
        height: 82px;
        background: #eee;
        margin-right: 12px;
        border-radius: 7px;
        position: absolute;
        top: 9px;
      }
      p {
        position: absolute;
        top: 9px;
        left: 140px;
        height: 82px;
        font-size: 13px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        line-height: 21px;
        padding: 0 3px;
        word-wrap: break-word;
        text-overflow: ellipsis;
        overflow: hidden;
      }
    }
    .activeCardFooter {
      height: 43px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      line-height: 43px;
    }
  }
}
</style>
