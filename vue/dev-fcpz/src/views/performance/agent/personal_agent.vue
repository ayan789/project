<template>
  <div class="personal_agent">
    <van-sticky>
      <van-nav-bar left-arrow @click-left="back()">
        <template #title>
          <div class="personal_agent-tab">
            <div
              v-for="(item, index) in tabItem"
              :key="index"
              @click="actived = index"
              :class="[actived == index ? 'personal_agent-tab-actived' : '']"
            >
              {{ item }}
            </div>
          </div>
        </template>
      </van-nav-bar>
    </van-sticky>
    <div class="performanceInfo" v-if="actived === 0">
      <div
        class="performanceInfo-content"
        v-for="(item, index) in resultPerformance"
        :key="index"
        v-show="resultPerformance.length > 0"
      >
        <p>
          <span
            ><img
              src="../../../assets/performance/performanceInfo.png"
              alt=""
            />
            {{ item.agentName }}</span
          >
          <span
            ><van-icon
              name="arrow"
              @click="
                $router.push({
                  name: 'costReflect',
                  params: {
                    item: item
                  }
                })
              "
          /></span>
        </p>
        <p>
          <span>P码</span> <span> {{ item.agentCode }}</span>
        </p>
        <p>
          <span>可提现手续费</span> <span>{{ item.planFee }}(元)</span>
        </p>
      </div>
    </div>
    <div class="performanceInfo" v-if="actived === 1">
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        :error.sync="error"
        @load="onLoad"
        :immediate-check="false"
      >
        <!-- error-text="请求失败，点击重新加载" -->
        <div
          class="performanceInfo-content"
          v-for="(item, index) in resultInfo"
          :key="index"
        >
          <p>
            <span
              ><img
                src="../../../assets/performance/performanceInfo.png"
                alt=""
              />
              {{ item.userName }}</span
            >
            <span></span>
          </p>
          <p>
            <span>P码</span> <span>{{ item.pcode }}</span>
          </p>
          <p>
            <span>考试时间</span> <span>{{ item.examDate }}</span>
          </p>
          <p>
            <span>考试成绩</span> <span>{{ item.examScore }}</span>
          </p>
          <p>
            <span>职业资格证号码</span>
            <span>{{ item.qualificationsCode }}</span>
          </p>
          <p>
            <span>审核通过时间</span> <span>{{ item.updateDate }}</span>
          </p>
        </div>
      </van-list>
    </div>
    <div class="__performanceInfo" v-if="resultPerformance.length == 0">
      <div class="no_data">
        <img src="../../../assets/performance/no_data.png" alt="" />
        <span style="color：#999999;font-size:12px; ">暂无数据</span>
      </div>
    </div>
  </div>
</template>

<script>
import {
  queryInviteUserDetail,
  planFeeInquiry
} from "@src/http/module/performance.js";
import { List } from "vant";
export default {
  components: {
    [List.name]: List
  },
  data: () => ({
    // tabItem: ["业绩信息", "注册信息"],
    tabItem: ["业绩信息"],
    actived: 0,
    formData: {
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
      agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
      handlerCode:
        JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
      queryType: "01",
      page: {
        page: 1,
        size: 5
      }
    },
    loading: false,
    finished: false,
    error: false,
    resultInfo: [],
    resultPerformance: []
  }),
  async created() {
    //全局获取缓存数据
    // this.$globalSetLocalStorage();
    this.formData = {
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
      agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
      handlerCode:
        JSON.parse(localStorage.getItem("userInfo")).handlerCode || "",
      queryType: "01",
      page: {
        page: 1,
        size: 5
      }
    };
    await Promise.all([this.queryInviteUserDetail_(), this.planFeeInquiry_()]);
  },
  methods: {
    back() {
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
    },
    onLoad() {
      this.queryInviteUserDetail_();
      this.formData.page.page += 1;
    },
    async queryInviteUserDetail_() {
      await queryInviteUserDetail(this.formData)
        .then(res => {
          if (res.code === "1") {
            this.resultInfo.push(...res.data.inviteListResponseVO.list);
            // 加载状态结束
            this.loading = false;
            if (
              this.resultInfo.length >= res.data.inviteListResponseVO.page.rows
            ) {
              this.finished = true;
            }
          } else {
            this.$dialog.alert({
              message: res.msg
            });
          }
        })
        .catch(() => {
          this.error = true;
        });
    },
    async planFeeInquiry_() {
      await planFeeInquiry({
        queryType: "02",
        agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
        // userCode: localStorage.getItem("userCode") || "",
        handlerCode:
          JSON.parse(localStorage.getItem("userInfo")).handlerCode || ""
      }).then(res => {
        if (res.code === "1") {
          this.resultPerformance = [
            ...res.data.planFeeInquiryResponseVO.planFees
          ];
        } else {
          this.$dialog.alert({
            message: res.msg
          });
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.__performanceInfo {
  height: 50%;
  position: relative;

  .no_data {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  span {
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translate(-50%, -50%);
  }
}
.personal_agent {
  /deep/.van-nav-bar {
    background: linear-gradient(
      92deg,
      rgba(33, 83, 235, 1) 0%,
      rgba(55, 147, 255, 1) 100%
    );
    /deep/.van-icon {
      color: #fff;
    }
  }
  &-tab {
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    font-size: 15px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    &-actived {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      /* 修--改 */
      margin-left: 38%;
    }
  }
  .performanceInfo {
    // height: 100%;
    // background: #ffffff;
    margin-bottom: 10px;
    &-content {
      padding: 10px 15px;
      background: #fff;
      margin-bottom: 10px;
    }
    p {
      display: flex;
      justify-content: space-between;
      margin-bottom: 6px;
      img {
        height: 22px;
        width: 22px;
        vertical-align: bottom;
      }
      span:nth-child(1) {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
    }
    p:nth-child(2),
    p:nth-child(3),
    p:nth-child(4),
    p:nth-child(5),
    p:nth-child(6) {
      span {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }
  }
}
</style>
