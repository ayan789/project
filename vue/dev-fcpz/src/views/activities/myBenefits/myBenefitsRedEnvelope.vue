/* 曹征前 我的福利--红包礼品 */
<template>
  <div class="myBenefitsRedEnvelope">
    <div class="marketingActivitiesHeader">
      <div class="hearder-title">
        <div class="header-one" @click="back">
          <img
            style="width:100%;height:100%"
            src="@/assets/activity/left.png"
            alt="edit"
          />
        </div>
        <div class="header-two"><p>我的福利</p></div>
      </div>
      <div class="select-bottom">
        <div class="select-texts">
          <span
            :class="isActived[0] == true ? '' : 'actived'"
            class="select"
            @click="changeActived(0)"
            >红包礼品</span
          >
          <span
            :class="isActived[1] == true ? '' : 'actived'"
            class="select"
            style="margin-left: 65px"
            @click="changeActived(1)"
            >绩效奖金</span
          >
        </div>
      </div>
    </div>
    <div
      style=" position: fixed;top:16vh;left: 0px;right: 0px;background-color: #F7F7F7;padding-top: 15px;border-radius: 15px 15px 0 0"
    >
      <div>
        <!--第一个标签“红包礼品”-->
        <div v-if="isActived[0] == true">
          <van-tabs
            active="isGet"
            v-model="active"
            background="#F7F7F7"
            color="#3B8AFE"
            line-width="25"
          >
            <!-- <div> -->
            <van-tab title="待领取">
              <div
                style="height:65vh;  -webkit-overflow-scrolling: touch;
                    overflow-scrolling: touch;
                    overflow-y: scroll;padding:0 15px 100px;"
              >
                <van-list>
                  <van-cell
                    is-link
                    v-for="(v, k) in noget"
                    :key="v.key"
                    @click="showPopup(k)"
                  >
                    <MoneyList
                      :activityCode="v.activityCode"
                      :activityName="v.activityName"
                      :name="v.awardName"
                      :money="v.amount + ''"
                      :start="v.activityStart"
                      :end="v.activityEnd"
                      :awardForm="v.awardForm + ''"
                      :activityUrl="v.activityUrl"
                      :isGet="false"
                    />
                  </van-cell>
                </van-list>
              </div>
              <van-popup
                v-model="show"
                position="bottom"
                :style="{ height: '60%', width: '100%' }"
                round="true"
              >
                <div class="alert-div">
                  <p
                    style="color:#333333;padding:15px 0;margin-left: 15px;font-size: 16px"
                  >
                    领福利
                  </p>
                  <div style="margin:0 17px">
                    <div v-for="v in noGets" :key="v.key">
                      <div class="get-money">
                        <div class="money-desc">
                          <div style="width:44px;height:44px;margin-left: 10px">
                            <img
                              style="width:100%;height:100%"
                              src="@/assets/activity/money.png"
                              alt="edit"
                            />
                          </div>
                          <div style="margin-left: 11px">
                            <div
                              style="color: #FF5000;font-size: 18px"
                              v-if="
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].awardForm == 1
                              "
                            >
                              {{
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].amount
                              }}元红包
                            </div>
                            <div
                              style="color: #FF5000;font-size: 18px"
                              v-if="
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].awardForm == 2
                              "
                            >
                              {{
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].awardName
                              }}
                            </div>
                            <div
                              style="color: #FF5000;font-size: 14px;margin-top: 6px"
                            >
                              日期{{
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].activityStart &&
                                  noget[nogetIndex].activityStart.slice(0, 10)
                              }}-{{
                                noget &&
                                  noget[nogetIndex] &&
                                  noget[nogetIndex].activityEnd &&
                                  noget[nogetIndex].activityEnd.slice(0, 10)
                              }}
                            </div>
                          </div>
                        </div>
                        <div
                          style="height: 80%;border-left: 1px dotted #FFB999"
                        ></div>
                        <div
                          class="money-on"
                          v-if="v == true"
                          @click="
                            applyAndAwardList(
                              noget && noget[nogetIndex].activityCode,
                              noget && noget[nogetIndex].awardLevel
                            )
                          "
                        >
                          <p class="order-on">立即领取</p>
                        </div>
                        <div class="money-on" v-if="v == false">
                          <p class="order-on actied">立即领取</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </van-popup>
            </van-tab>
            <van-tab title="已领取">
              <div
                style="height:65vh;overflow: scroll;  -webkit-overflow-scrolling: touch;
                          overflow-scrolling: touch;
                          overflow-y: scroll;padding:0 15px 100px"
              >
                <!-- <div v-for="value in isget" :key="value.id"> -->
                <van-cell v-for="value in isget" :key="value.id">
                  <MoneyList
                    :name="value.awardName"
                    :activityName="value.activityName"
                    :money="value.amount + ''"
                    :overtime="value.getAwardTime"
                    :isGet="true"
                    :awardForm="value.awardForm + ''"
                  />
                </van-cell>
                <!-- </div> -->
              </div>
            </van-tab>
            <van-tab title="" disabled></van-tab>
            <van-tab title="" disabled></van-tab>
            <!-- </div> -->
          </van-tabs>
        </div>
        <!--第二个标签“绩效奖金”-->
        <div v-else>
          <div
            style="background-color:#F7F7F7; padding:0 20px;height: 82vh;overflow-y:scroll;  -webkit-overflow-scrolling: touch;
                    overflow-scrolling: touch;"
          >
            <div v-for="v in overlist" :key="v.key">
              <Performance :showData="v" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import IsGetCom from "../common/isGetCom";
import Performance from "../common/performance";
import MoneyList from "../common/moneyList";
export default {
  computed: {
    ...mapState(["activityData"])
  },
  components: {
    MoneyList,
    Performance
  },
  data: () => ({
    isget: {}, //已领取红包数据集合
    show: false, //控制领取红包底部显示
    noget: [], //未领取红包数据集合
    overlist: {}, //终止日奖励数据集合
    isActived: [true, false], //控制显示颜色“红包礼品”、“绩效奖金”
    active: 0, //控制tabs组件
    noGets: [], //底部弹窗-》循环显示红包
    nogetIndex: 0, //未领取红包的一个集合的key
    defaultShow: "noGet" //noGet默认显示待领取，isGet默认显示已领取。
  }),
  async created() {
    if (this.$route.query.item) {
      //原生跳转过来，区分默认显示”待领取“”已领取“
      this.$route.query.item == "noGet" ? (this.active = 0) : (this.active = 1);
    }
    this.post_awardList(12); //参数为“12”为实时奖励查询。
    this.post_awardList(345); //参数为“345”为终止日奖励查询。
  },
  methods: {
    //路由返回
    back() {
      this.$globalBack();
    },
    //控制“红包礼品”“绩效奖金”的样式
    changeActived(index) {
      if (index == 0 && this.isActived[0] == false) {
        this.isActived = [true, false];
      } else if (index == 1 && this.isActived[1] == false) {
        this.isActived = [false, true];
      }
    },
    showPopup(k) {
      //设置未领取列表的key
      this.nogetIndex = k;
      // let getCount = parseInt(this.noget && this.noget[k].isCanGetCount); //可领取个数
      // if( getCount == "0"){
      //   Toast("无可领取红包")
      //   return;
      // }
      this.show = true;
      this.showNoGets(); //循环弹出框的“红包”列表
    },
    //循环弹出框的“红包”列表
    showNoGets() {
      let k = this.nogetIndex;
      let count = parseInt(this.noget && this.noget[k].awardCount);
      let getCount = parseInt(this.noget && this.noget[k].isCanGetCount); //可领取个数
      //循环出展示列表
      let noGets = [];
      if (count > 0) {
        for (let i = 0; i < count; i++) {
          noGets.push(false);
        }
        for (let i = 0; i < getCount; i++) {
          noGets[i] = true;
        }
      }
      this.noGets = noGets;
    },
    //查询我的福利
    async post_awardList(awardForm) {
      let params = {
        awardForm: awardForm,
        salesmanCode: this.localStorage.get("userInfo", {}).userCode
      }; //终止日奖励
      await this.$store.dispatch("activityData/getAwardList", params);
      let awardList = this.activityData.awardList;
      //红包礼品awardForm == 12；绩效奖金awardForm == 345
      if (awardForm == 12) {
        //红包领取
        this.isget = awardList && awardList.activityResponse.isGetAwardList;
        this.noget = awardList && awardList.activityResponse.notGetAwardList;
        this.showNoGets();
      } else {
        //绩效奖金
        this.overlist = awardList && awardList.activityResponse;
        console.info("overlist", this.overlist);
      }
    },
    //领取红包接口
    async applyAndAwardList(activityCode, awardLevel) {
      let params = {
        activityCode: activityCode,
        awardLevel: awardLevel,
        salesmanCode: this.localStorage.get("userInfo", {}).userCode
      };
      await this.$store.dispatch("activityData/getAward", params);
      let award = this.activityData.award;
      if (award.code == 1) {
        this.post_awardList(12); //查询红包礼品
      }
    }
  }
};
</script>

<style lang="less" scoped>
.myBenefitsRedEnvelope {
  background: rgba(247, 247, 247, 1);
  /*.van-tabs__wrap .van-hairline--top-bottom{*/
  /*width:50% !important;*/
  /*background-color: red;*/
  /*}*/
  .van-hairline--top-bottom {
    width: 50%;
  }
  .van-cell--clickable {
    margin-top: 15px;
    border-radius: 5px;
  }
  .van-cell {
    margin-top: 15px;
    border-radius: 5px;
  }
  .marketingActivitiesHeader {
    width: 100%;
    height: 18vh;
    background: url("../../../assets/activity/BG-logo.png");
    background-size: 100% 100%;
    position: fixed;
    top: 0;
    .select-bottom {
      position: absolute;
      bottom: 20px;
      display: flex;
      flex-direction: row;
      justify-content: center;
      width: 100%;
      .select-texts {
        display: flex;
        flex-direction: row;
        align-items: center;
        .select {
          font-size: 16px;
          color: #ffffff;
        }
        .actived {
          opacity: 0.7;
        }
      }
    }
    .hearder-title {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      padding-top: 20px;
      .header-one {
        position: absolute;
        top: 20px;
        left: 10px;
        height: 20px;
        width: 20px;
      }
      .header-two {
        color: #ffffff;
        font-size: 17px;
      }
    }
  }
  .pag-content {
    .content-title {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid #e5e6e8;
      padding-bottom: 5px;
    }
    .content-desc {
      margin-top: 10px;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      .desc-count {
        margin-left: 10px;
      }
    }
  }
  .alert-div {
    .get-money {
      margin-top: 10px;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      height: 74px;
      .money-desc {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;
        background-color: #feece6;
        flex: 1;
        border-radius: 5px;
        height: 74px !important;
      }
      .money-on {
        width: 80px;
        background-color: #feece6;
        height: 100%;
        border-radius: 5px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        .order-on {
          color: #ffffff;
          background-color: #ff5000;
          padding: 5px 8px;
          font-size: 12px;
          border-radius: 11.5px;
        }
        .actied {
          background-color: #d0d0d0;
        }
      }
    }
  }
}
</style>
