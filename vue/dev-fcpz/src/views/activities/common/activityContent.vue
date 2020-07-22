/* 曹征前 营销活动-活动详情 */
<template>
  <div class="activityContent">
    <div class="activitiesContent">
      <!--活动名称-->
      <h2>{{ showData && showData.activityName }}</h2>
      <!--活动内容：暂时是文字，后期需要后台传入富文本显示-->
      <pre style="padding-top: 20px">{{
        showData && showData.activityDetails
      }}</pre>
      <!--isNeedApply==1 活动需要报名  2活动不需要报名;   isApply ==1登陆人还没有报名， 2已经报名-->
      <p
        class="contentFooter"
        v-if="showData && showData.isApply == 1 && showData.isNeedApply == 1"
      >
        <button @click="post_activityApply">立即报名</button>
      </p>
      <!--awardType":1,实时奖励，2、终止日奖励；-->
      <div v-if="showData && showData.awardType != 1">
        <p>活动预测</p>
        <p v-if="type == 3">
          <!--保费比率-->
          <span v-if="isNone == 0"
            >您尚未达成活动绩效奖励领取条件，请继续加油！</span
          >
          <span v-if="isNone == 1"
            >恭喜您！已达成活动绩效领取条件，当前奖励比率为
            <span style="color:red">{{ showData.curLevel }}%</span>
            可领取
            <span style="color:red">{{ showData.awardName }}元</span>
            绩效奖金；最高绩档次比率为
            <span style="color:red">{{ showData.maxAwardName }}%</span>
            ，请继续努力！
          </span>
          <span v-if="isNone == 2">
            恭喜您！已达成最高活动绩效领取条件，当前奖励比率为
            <span style="color:red">{{ showData.curLevel }}%</span>
            ，可领取
            <span style="color:red">{{ showData.awardName }}元</span>
            绩效奖金！
          </span>
        </p>
        <p v-if="type == 4">
          <!--固定奖励-->
          <span v-if="isNone == 0"
            >您尚未达成活动绩效奖励领取条件，请继续加油！</span
          >
          <span v-if="isNone == 1"
            >恭喜您！已达成活动绩效领取条件，当前可领取
            <span style="color:red">{{ showData.awardName }}元</span>
            绩效奖金；最高绩效奖金为
            <span style="color:red">{{ showData.maxAwardName }}元</span>
            ，请继续努力！</span
          >
          <span v-if="isNone == 2"
            >恭喜您！已达成最高活动绩效领取条件，可领取绩效奖金
            <span style="color:red">{{ showData.maxAwardName }}元</span>！</span
          >
        </p>
        <p v-if="type == 5">
          <!--礼品-->
          <span v-if="isNone == 0"
            >您尚未达成活动绩效奖励领取条件，请继续加油！</span
          >
          <span v-if="isNone == 1">
            恭喜您！已达成活动绩效领取条件，可领取
            <span style="color:red">{{ showData.awardName }}</span>
            ；活动最高档次奖励为
            <span style="color:red">{{ showData.maxAwardName }}</span
            >，请继续努力！</span
          >
          <span v-if="isNone == 2"
            >恭喜您！已达成最高活动绩效领取条件，可领取
            <span style="color:red">{{ showData.maxAwardName }}</span
            >！</span
          >
        </p>
      </div>

      <!--awardType":1,实时奖励，2、终止日奖励；-->
      <div
        v-if="
          showData &&
            showData.awardType == 1 &&
            showData.isApply == 2 &&
            showData.isNeedApply == 1
        "
      >
        <div class="in-tabs" style="margin-top: 30px">
          <van-tabs
            v-model="active"
            background="#F7F7F7"
            color="#3B8AFE"
            line-width="25"
          >
            <div>
              <van-tab title="待领取">
                <div
                  style="height:30vh;overflow: scroll;padding:0 15px;background-color: #F7F7F7"
                >
                  <van-cell
                    is-link
                    @click="showPopup(k)"
                    style="margin-top: 20px;border-radius: 5px"
                    v-for="(v, k) in noget"
                    :key="v.key"
                  >
                    <div class="pag-content">
                      <div class="content-title">
                        <p style="font-size: 17px;color: #333333">
                          {{
                            v && v.awardForm == 1
                              ? v.amount + "元红包"
                              : v.awardName + ""
                          }}
                        </p>
                        <p style="color: #333333"></p>
                      </div>
                      <div class="content-desc">
                        <div style="width:42px;height:33px">
                          <img
                            style="width:100%;height:100%"
                            src="@/assets/activity/redpag1.png"
                            alt="edit"
                            v-if="v.awardForm == 1"
                          />
                          <img
                            style="width:100%;height:100%"
                            src="@/assets/activity/gift.png"
                            alt="edit"
                            v-if="v.awardForm == 2"
                          />
                        </div>
                        <div class="desc-count">
                          <p
                            style="color:#333333;font-size: 16px"
                            v-if="v.awardForm == 1"
                          >
                            {{ v && v.amount }}红包
                          </p>
                          <p
                            style="color:#333333;font-size: 16px"
                            v-if="v.awardForm == 2"
                          >
                            {{ v && v.awardName }}
                          </p>
                          <p
                            style="color:#999999;font-size: 13px;overflow: hidden"
                          >
                            有效期
                            {{
                              showData &&
                                showData.activityStart &&
                                showData.activityStart.slice(0, 10)
                            }}
                            -
                            {{
                              showData &&
                                showData.activityEnd &&
                                showData.activityEnd.slice(0, 10)
                            }}
                          </p>
                        </div>
                      </div>
                    </div>
                  </van-cell>
                </div>
                <van-popup
                  v-model="show"
                  position="bottom"
                  :style="{
                    height: '60%',
                    width: '100%',
                    borderRadius: '15px 15px 0 0'
                  }"
                >
                  <div class="alert-div">
                    <div class="alert-title">
                      <p
                        style="color:#333333;padding:15px 0;font-size: 16px;font-weight: 600"
                      >
                        领福利
                      </p>
                      <div style="width:14px;height:14px;" @click="closedPop()">
                        <img
                          style="width:100%;height:100%"
                          src="@/assets/activity/closed.png"
                          alt="edit"
                        />
                      </div>
                    </div>

                    <div style="margin:0 14px">
                      <div v-for="(v, k) in noGets" :key="v.key">
                        <noGetCom
                          v-if="v == true"
                          :value="v"
                          :showData="showData && showData"
                          :keys="nogetIndex"
                          @applyAndAwardList="
                            applyAndAwardList(
                              showData &&
                                showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex]
                                  .activityCode,
                              showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex].awardLevel
                            )
                          "
                        />
                        <noGetCom
                          v-else
                          :value="v"
                          :keys="nogetIndex"
                          :showData="showData && showData"
                          @applyAndAwardList="
                            applyAndAwardList(
                              showData &&
                                showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex]
                                  .activityCode,
                              showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex].awardLevel
                            )
                          "
                        />
                      </div>
                    </div>
                  </div>
                </van-popup>
              </van-tab>
              <van-tab title="已领取">
                <div
                  style="height:30vh;overflow: scroll;padding:0 15px;background-color: #F7F7F7"
                >
                  <div
                    v-for="v in showData && showData.isGetAwardList"
                    :key="v.id"
                  >
                    <IsGetCom
                      :awardCount="v.awardCount + ''"
                      :awardForm="v.awardForm + ''"
                      :amount="v.amount + ''"
                      :name="v.awardName + ''"
                      :data="v.getAwardTime"
                    />
                  </div>
                </div>
              </van-tab>
            </div>
          </van-tabs>
        </div>
      </div>
      <div
        v-if="showData && showData.awardType == 1 && showData.isNeedApply == 2"
      >
        <div class="in-tabs" style="margin-top: 30px">
          <van-tabs
            v-model="active"
            background="#F7F7F7"
            color="#3B8AFE"
            line-width="25"
          >
            <div>
              <van-tab title="待领取">
                <div
                  style="height:30vh;overflow: scroll;padding:0 15px;background-color: #F7F7F7"
                >
                  <van-cell
                    is-link
                    @click="showPopup(k)"
                    style="margin-top: 20px;border-radius: 5px"
                    v-for="(v, k) in noget"
                    :key="v.key"
                  >
                    <div class="pag-content">
                      <div class="content-title">
                        <p style="font-size: 17px;color: #333333">
                          {{
                            v && v.awardForm == 1
                              ? v.amount + "元红包"
                              : v.awardName + ""
                          }}
                        </p>
                        <p style="color: #333333"></p>
                      </div>
                      <div class="content-desc">
                        <div style="width:42px;height:33px">
                          <img
                            style="width:100%;height:100%"
                            src="@/assets/activity/redpag1.png"
                            alt="edit"
                            v-if="v.awardForm == 1"
                          />
                          <img
                            style="width:100%;height:100%"
                            src="@/assets/activity/gift.png"
                            alt="edit"
                            v-if="v.awardForm == 2"
                          />
                        </div>
                        <div class="desc-count">
                          <p
                            style="color:#333333;font-size: 16px"
                            v-if="v.awardForm == 1"
                          >
                            {{ v && v.amount }}红包
                          </p>
                          <p
                            style="color:#333333;font-size: 16px"
                            v-if="v.awardForm == 2"
                          >
                            {{ v && v.awardName }}
                          </p>
                          <p
                            style="color:#999999;font-size: 13px;overflow: hidden"
                          >
                            有效期
                            {{
                              showData &&
                                showData.activityStart &&
                                showData.activityStart.slice(0, 10)
                            }}
                            -
                            {{
                              showData &&
                                showData.activityEnd &&
                                showData.activityEnd.slice(0, 10)
                            }}
                          </p>
                        </div>
                      </div>
                    </div>
                  </van-cell>
                </div>
                <van-popup
                  v-model="show"
                  position="bottom"
                  :style="{
                    height: '60%',
                    width: '100%',
                    borderRadius: '15px 15px 0 0'
                  }"
                >
                  <div class="alert-div">
                    <div class="alert-title">
                      <p
                        style="color:#333333;padding:15px 0;font-size: 16px;font-weight: 600"
                      >
                        领福利
                      </p>
                      <div style="width:14px;height:14px;" @click="closedPop()">
                        <img
                          style="width:100%;height:100%"
                          src="@/assets/activity/closed.png"
                          alt="edit"
                        />
                      </div>
                    </div>

                    <div style="margin:0 14px">
                      <div v-for="(v, k) in noGets" :key="v.key">
                        <noGetCom
                          v-if="v == true"
                          :value="v"
                          :showData="showData && showData"
                          :keys="nogetIndex"
                          @applyAndAwardList="
                            applyAndAwardList(
                              showData &&
                                showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex]
                                  .activityCode,
                              showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex].awardLevel
                            )
                          "
                        />
                        <noGetCom
                          v-else
                          :value="v"
                          :keys="nogetIndex"
                          :showData="showData && showData"
                          @applyAndAwardList="
                            applyAndAwardList(
                              showData &&
                                showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex]
                                  .activityCode,
                              showData.notGetAwardList &&
                                showData.notGetAwardList[nogetIndex].awardLevel
                            )
                          "
                        />
                      </div>
                    </div>
                  </div>
                </van-popup>
              </van-tab>
              <van-tab title="已领取">
                <div
                  style="height:30vh;overflow: scroll;padding:0 15px;background-color: #F7F7F7"
                >
                  <div
                    v-for="v in showData && showData.isGetAwardList"
                    :key="v.id"
                  >
                    <IsGetCom
                      :awardCount="v.awardCount + ''"
                      :awardForm="v.awardForm + ''"
                      :amount="v.amount + ''"
                      :name="v.awardName + ''"
                      :data="v.getAwardTime"
                    />
                  </div>
                </div>
              </van-tab>
            </div>
          </van-tabs>
        </div>
      </div>
    </div>
    <div style="text-align: center;margin-top: 5px;margin-bottom: 20px">
      <p @click="toUserActivity" style="color:white;font-size:20px">查看更多</p>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import IsGetCom from "../common/isGetCom";
import noGetCom from "../common/noGetCom";
import { Toast } from "vant";
//awardForm 2 是礼品奖励，1红包，3保费比率，4固定奖励 5礼品
export default {
  name: "activitiesContent",
  computed: {
    ...mapState(["activityData"])
  },
  async created() {
    //首页弹窗点击跳转
    let activityCode = this.$GetRequest("activityCode", true);
    if (activityCode) {
      this.activityCode = activityCode;

      let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (userInfo) {
        this.salesmanCode = userInfo.userCode;
      } else {
        this.salesmanCode = "8000151931";
      }

      await this.post_detail(false);
    } else if (this.$route.query.item.activityCode) {
      //活动列表跳转过来
      const { activityCode, salesmanCode } = this.$route.query.item;
      this.$store.dispatch("activityData/setActiveData", {
        activityCode: activityCode,
        salesmanCode: salesmanCode
      });
      if (activityCode) {
        this.activityCode = activityCode;
        await this.post_detail(false);
      }
    } else if (this.activityData.setActiveData.activityCode) {
      //”我的福利“返回触发
      let { activityCode, salesmanCode } = this.activityData.setActiveData;
      this.activityCode = activityCode;
      this.salesmanCode = salesmanCode;
      await this.post_detail(false);
    } else {
      //原生跳转过来
      const { activityCode, salesmanCode } = JSON.parse(this.$route.query.item);
      this.activityCode = activityCode;
      this.salesmanCode = salesmanCode;
      // this.$store.dispatch("activityData/setActiveData", {activityCode:activityCode,salesmanCode:salesmanCode});
      if (activityCode) {
        this.activityCode = activityCode;
        await this.post_detail(false);
      }
    }
  },
  components: {
    IsGetCom,
    noGetCom
  },
  data: () => ({
    isShowRes: false, //是否显示报名结果弹窗
    isOrderSuccess: false, //是否报名成功
    isApplyOk: false, //是否是实施奖励
    show: false, //控制底部弹窗展示红包列表
    noGets: [], //未领取红包数据集合
    showData: {}, //后台返回的总的数据结合
    salesmanCode: "", //登录者的业务号
    isApplyEndOk: false, //是否显示终止日
    active: 0, //控制tabs切换
    noget: [], //接口获取的未领取列表
    nogetIndex: 0, //用户点击的未领取的key
    activityCode: "", //路由传过来的值
    isNone: "", //没有完成任何任务0，完成部分1，全部完成2
    type: "" //3保费比率；4、固定奖励； 5礼品；
  }),
  methods: {
    toUserActivity() {
      this.$router.push({
        name: "myBenefitsRedEnvelope"
      });
    },
    closedPop() {
      this.show = false;
    },

    showPopup(k) {
      this.nogetIndex = k;
      // let getCount = parseInt(this.noget && this.noget[k] && this.noget[k].isCanGetCount); //可领取个数
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
      let count = parseInt(
        this.noget && this.noget[k] && this.noget[k].awardCount
      );
      let getCount = parseInt(
        this.noget && this.noget[k] && this.noget[k].isCanGetCount
      ); //可领取个数
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
    async applyAndAwardList(activityCode, awardLevel) {
      //先领取红包接口
      let params = {
        activityCode: activityCode,
        awardLevel: awardLevel,
        salesmanCode: this.salesmanCode
      };
      await this.$store.dispatch("activityData/getAward", params);
      let award = this.activityData.award;
      Toast("领取成功");
      //领取成功后再次调用详情
      if (award.code == 1) {
        this.post_detail(false);
      }
    },

    setZhongZhiRi() {
      if (this.showData.awardName == "" || this.showData.awardName == null) {
        //未完成任何任务
        this.isNone = 0;
      } else if (this.showData.awardName && this.showData.isMaxLevel == 0) {
        //isMaxLevel：0完成部分、1全部完成
        //完成部分任务
        this.isNone = 1;
      } else if (this.showData.isMaxLevel == 1) {
        //isMaxLevel：0完成部分、1全部完成
        //完成全部任务
        this.isNone = 2;
      }
      switch (
        this.showData.awardForm //awardForm 实时奖励：1红包，2是礼品奖励；终止日奖励：3保费比率，4固定奖励 5礼品
      ) {
        case 3:
          this.type = 3;
          break;
        case 4:
          this.type = 4;
          break;
        case 5:
          this.type = 5;
          break;
        default:
          this.type = "";
      }
    },
    async post_detail(testType) {
      //testType 是否是测试
      let activityCode = this.$GetRequest("activityCode", true);
      if (!activityCode) {
        const { activityCode, salesmanCode } = this.$route.query.item;
        if (activityCode) {
          this.salesmanCode = salesmanCode;
          this.activityCode = activityCode;
        }
      }

      let params = {
        activityCode: this.activityCode,
        salesmanCode: this.salesmanCode
      };
      await this.$store.dispatch("activityData/getActivityDetails", params);
      // let activityDetails = {};
      if (testType) {
        //测试数据。
        let activityDetailss = {
          code: "1",
          data: {
            msg: "查询成功",
            code: 1,
            activityResponse: {
              id: 351,
              activityCode: "123xx",
              isRenewalActivity: 1,
              renewalPercentStart: "2020-03-07 16:51:09",
              renewalPercentEnd: "2020-03-07 16:49:17",
              activityName: "这是名称",
              activityStart: "2020-03-07 16:49:36",
              activityEnd: "2020-03-25 16:49:39",
              issuedStart: "2020-03-07 16:49:42",
              issuedEnd: "2020-03-20 16:49:45",
              activitySummarize: "aada",
              putProduction: "",
              sameTimeInsurance: "",
              sameTimeCount: "",
              surroundTimeInsurance: "",
              surroundTimeCount: "",
              targetInsurance: "",
              activityBudget: "",
              activityDetailsSource: "",
              activityDetails: "我的活动内容按有",
              createTime: "2020-03-13 15:23:41",
              activityCreator: "",
              organizationCode: "",
              organizationName: "",
              activityStatus: 2,
              checkStatus: "",
              isPutList: "",
              isNeedApply: 1,
              applyStartDate: "2020-03-07 21:26:45",
              applyEndDate: "2020-03-15 21:26:49",
              behaviorStatistics: "",
              autoAdd: 1,
              activityPicture: "",
              isGet: 0,
              notGet: 5,
              amount: "",
              maxAmount: "",
              awardName: "",
              maxAwardName: "",
              awardForm: 12,
              awardType: 1,
              activityPolicyAwardPo: "",
              isGetAwardList: [],
              notGetAwardList: [
                {
                  id: 351,
                  activityCode: "123xx",
                  awardType: 1,
                  awardForm: 2,
                  isBreakLimit: 2,
                  maximumAmount: 2222.0,
                  getWay: 1,
                  awardLevel: 1,
                  amount: 200,
                  awardName: "234",
                  percent: 50,
                  awardCount: 5,
                  autoSupplyCount: 0,
                  everySupplyCount: 3,
                  levelMaxCount: 10,
                  levelMaxAmount: 10000,
                  isCanGetCount: 0,
                  isPast: 1
                }
              ],
              isApply: 2
            }
          },
          msg: ""
        };
        this.showData =
          activityDetailss && activityDetailss.data.activityResponse;
      } else {
        //获取store里的值
        let activityDetails = this.activityData.activityDetails;
        this.showData = activityDetails && activityDetails.activityResponse;
        if (this.showData.awardType != 1) {
          this.setZhongZhiRi();
        }
        this.$emit("onIng", this.showData.activityStatus); //activityStatus == 0未开始 1 未开始  2进行中 3已结束
      }
      this.noget = this.showData && this.showData.notGetAwardList;
      this.showNoGets();
    },
    //活动报名
    async post_activityApply() {
      const { activityCode, salesmanCode } = this.$route.query.item;
      if (activityCode) {
        this.activityCode = activityCode;
        this.salesmanCode = salesmanCode;
      }
      let params = {
        activityCode: this.activityCode,
        salesmanCode: this.salesmanCode
      };
      await this.$store.dispatch("activityData/getActivityApply", params);
      let activityApply = this.activityData.activityApply;
      if (activityApply.code == 1) {
        Toast("报名成功");
        this.post_detail(false);
        //判断是否是“isApplyOk实时奖励”“isApplyEndOk终止日奖励”
        // this.showData && this.showData.awardType == 1
        //   ? (this.isApplyOk = true)
        //   : (this.isApplyEndOk = true);
      } else {
        let msg = activityApply.msg;
        Toast(msg);
      }
    }
  }
};
</script>

<style lang="less" scoped>
.activityContent {
  font-family: PingFangSC-Medium, PingFang SC;
  border-radius: 10px;
  background: url("../../../assets/activity/background.png");
  background-size: 100% 100%;
  height: 76vh;
  position: fixed;
  top: 25vh;
  left: 0;
  right: 0;
  padding: 0 15px;
  overflow-y: scroll;
  .alert-div {
    .alert-title {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      padding: 0 15px;
    }
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
          padding: 0 8px;
          font-size: 12px;
          border-radius: 11.5px;
        }
      }
    }
  }
  .activitiesContent {
    /*width: 345px;*/
    min-height: 55vh;
    position: relative;
    background: rgba(255, 255, 255, 1);
    border-radius: 8px;
    padding: 23px 17px 19px 15px;

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
    h2 {
      height: 25px;
      font-size: 18px;
      font-weight: 600;
      color: rgba(24, 95, 200, 1);
      line-height: 25px;
      text-align: center;
      margin-bottom: 19px;
    }
    pre {
      font-size: 13px;
      font-weight: 500;
      color: rgba(48, 137, 222, 1);
      line-height: 18px;
      word-wrap: break-word;
      word-break: normal;
      white-space: pre-wrap;
      padding-bottom: 40px;
    }
    p {
      font-size: 13px;
      font-weight: 500;
      color: rgba(48, 137, 222, 1);
      line-height: 18px;
      word-wrap: break-word;
      word-break: normal;
      white-space: pre-wrap;
    }
    .supplement {
      text-indent: 1rem;
    }
    .contentFooter {
      /*width: 296px;*/
      position: absolute;
      bottom: 10px;
      left: 0;
      right: 0;
      display: flex;
      justify-content: center;
      button {
        width: 296px;
        height: 46px;
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        font-size: 16px;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        line-height: 46px;
        outline: none;
        border: none;
      }
    }
  }
}
</style>
