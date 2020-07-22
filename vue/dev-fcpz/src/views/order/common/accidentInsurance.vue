/* zhangminjie 订单列表-公共组件-订单卡片（上拉刷新，下拉刷新） */
<template>
  <div class="orderCard">
    <!-- 下拉刷新 -->
    <van-pull-refresh
      v-model="myDownFresh.isLoading"
      success-text="刷新成功"
      loosing-text
      @refresh="onDownFresh"
    >
      <!-- 上拉刷新 -->
      <van-list
        v-model="myUpFresh.loading"
        :finished="myUpFresh.finished"
        finished-text="没有更多了"
        @load="onUpFresh"
      >
        <!-- 待处理 -->
        <van-swipe-cell
          :name="item.id"
          class="orderCardInfo"
          :before-close="beforeClose"
          v-for="(item, index) in ordersListObj.ordersList"
          v-show="ordersListObj.ordersList.length > 0"
          :key="index"
        >
          <van-card class="orderInfo" @click="redirectOrderDetailPage(item)">
            <div slot="title">
              <div class="orderInfoTop">
                <div class="left">
                  <div class="titleIcon">
                    <img
                      src="@/assets/order/orderList/orderList.png"
                      alt="orderList"
                    />
                  </div>
                  <p>{{ item.productName }}</p>
                </div>
                <div class="right">
                  <button class="tempSave" v-if="item.status == 0">
                    暂存
                  </button>
                  <button class="underwrite" v-if="item.status == 1">
                    待核保
                  </button>
                  <button class="underwrite" v-if="item.status == 2">
                    核保成功
                  </button>
                  <button
                    class="noPay"
                    :class="item.status == 4 ? 'redPay' : ''"
                    v-if="item.status == 4"
                  >
                    待支付
                  </button>
                  <button class="noPay" v-if="item.status == 6">
                    已支付
                  </button>
                  <button class="guaranteed" v-if="item.status == 7">
                    保障中
                  </button>
                  <button class="unUsed" v-if="item.status == 10">
                    已失效
                  </button>
                </div>
              </div>
              <div class="orderInfoMid">
                <p>
                  <span>被保人</span>
                  <span>{{ item.insuredName }}</span>
                </p>
                <p>
                  <span>车牌号</span>
                  <span>{{ item.licensePlateNo }}</span>
                </p>
                <p
                  v-if="
                    item.status == 0 ||
                      item.status == 1 ||
                      item.status == 2 ||
                      item.status == 4
                  "
                >
                  <span>应付金额</span>
                  <span class="strong" v-show="item.sumPremium"
                    >{{ item.sumPremium }}元</span
                  >
                </p>
                <p v-if="item.status == 4 && item.payApplyNo">
                  <span>支付号</span>
                  <span class="strong">{{ item.payApplyNo }}</span>
                </p>
                <p v-if="item.status == 7 || item.status == 10">
                  <span>保障期限</span>
                  <span v-shoe="item.startDate && item.endDate"
                    >{{ item.startDate.split(" ")[0] }}至
                    {{ item.endDate.split(" ")[0] }}</span
                  >
                </p>
              </div>
            </div>
            <div slot="footer" class="orderInfoBottom" @click.stop>
              <!-- 暂存（ 未下单） -->
              <div v-if="item.status == 0 && item.imgId" class="center2">
                <!-- <button>继续录入</button> -->
                <button @click="deleteOrderInfo(item.insureNo)">
                  删除订单
                </button>
              </div>
              <!-- 暂存（已下单） -->
              <div v-if="item.status == 0 && !item.imgId" class="center2">
                <button @click="gotoUploadImg(item.insureNo)">影像上传</button>
              </div>
              <!-- 待核保 -->
              <div v-if="item.status == 1" class="center2">
                <button @click="underwriting(item)">提交核保</button>
                <button @click="gotoUploadImg(item.insureNo)">影像上传</button>
              </div>
              <!-- 核保完成 -->
              <div v-if="item.status == 2" class="center2">
                <button @click="gotoUploadImg(item.insureNo)">影像上传</button>
                <button @click="gotoPayPage(item)">立即支付</button>
              </div>
              <!-- 待支付 -->
              <div v-if="item.status == 4" class="center2">
                <button @click="gotoUploadImg(item.insureNo)">影像上传</button>
                <button @click="gotoPayPage(item)">立即支付</button>
              </div>
              <!-- 保障中 && 生效 -->
              <div v-if="item.status == 7 || item.status == 6" class="center4">
                <button>客户</button>
                <button @click="gotoUploadImg(item.insureNo)">影像上传</button>
                <button>电子发票</button>
                <button>电子保单</button>
              </div>
              <!-- 已失效 -->
              <div v-if="item.status == 10" class="center2">
                <button @click="deleteOrderInfo(item.insureNo)">
                  删除订单
                </button>
              </div>
            </div>
          </van-card>

          <van-button
            slot="right"
            square
            text="删除"
            type="danger"
            class="deleteOrder"
          />
        </van-swipe-cell>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>
import Vue from "vue";
import {
  SwipeCell,
  Card,
  Button,
  Dialog,
  Sku,
  List,
  PullRefresh,
  Toast
} from "vant";
import {
  storeOrderDetail,
  forwardPayProductInsureInfo,
  certiNotice,
  deleteStoreOrder
} from "@src/http/module/order.js";
import {
  underwriting,
  uploadImage,
  h5imgQueryDownService,
  applyPay
} from "@src/http/module/nonCar.js";
Vue.use(SwipeCell);
Vue.use(Card);
Vue.use(Button);
Vue.use(Sku);
Vue.use(List);
Vue.use(PullRefresh);
let requestData = {
  applicantName: "", //投保人姓名
  currentDate: "", //创建日期
  endDate: "", //保单止期
  insuranceCode: "", //险种代码
  insuranceType: "", //保险类别
  insureNo: "", //投保单号
  nclUserCode: "",
  startDate: "", //保单起期
  status: "", //投保单状态
  userId: "" //所属用户
};
export default {
  name: "orderListCard",
  props: {
    ordersListObj: Object, //订单列表
    insuranceType: Number, //险种类型
    status: Number //订单状态
  },
  components: {},
  data: () => ({
    myUpFresh: {
      loading: false,
      finished: false
    },
    myDownFresh: {
      isLoading: false
    },
    orderInfo: {}
  }),
  watch: {
    // 监听上拉刷新中的值
    upFresh(oldVal, newVal) {
      //console.log(newVal);
      this.myUpFresh = newVal;
      let {
        loading,
        finished
      } = this.$store.state.orderList.ordersListData.downFresh;
      this.myUpFresh = { loading, finished };
    },
    // 监听下拉刷新中的值
    downFresh(oldVal, newVal) {
      //console.log(newVal);
      let { isLoading } = this.$store.state.orderList.ordersListData.downFresh;
      this.myDownFresh = { isLoading };
    }
  },
  computed: {
    unFresh: {
      get() {
        return this.$store.state.orderList.ordersListData.upFresh;
      },
      set() {
        let {
          loading,
          finished
        } = this.$store.state.orderList.ordersListData.upFresh;
        this.myUpFresh = { loading, finished };
      }
    },
    downFresh: {
      get() {
        return this.$store.state.orderList.ordersListData.downFresh;
      },
      set() {
        let {
          isLoading
        } = this.$store.state.orderList.ordersListData.downFresh;
        this.myDownFresh = { isLoading };
      }
    }
  },
  mounted() {},
  methods: {
    // 上拉加载
    onUpFresh() {
      this.myUpFresh = this.$store.state.orderList.ordersListData.upFresh;
      this.$store.dispatch("orderList/storeOrderListQuery", this.ordersListObj);
      this.$emit("getOrderListEvent", this.ordersListObj);
      console.log(8888, this.ordersListObj);
    },
    // 下拉加载
    onDownFresh() {
      this.$store.dispatch("orderList/storeOrderListQuery", this.ordersListObj);
      let { isLoading } = this.$store.state.orderList.ordersListData.downFresh;
      this.myDownFresh = { isLoading };
      //console.log(this.myDownFresh);
      console.log(9999, this.ordersListObj);
    },
    // 滑动滑块内容关闭时的行为
    beforeClose({ position, instance, name }) {
      //console.log(name);
      switch (position) {
        case "left":
        case "cell":
        case "outside":
          instance.close();
          break;
        case "right":
          Dialog.confirm({
            message: "确定删除吗？"
          })
            .then(() => {
              instance.close();
              this.$store.dispatch("orderList/deleteStoreOrder", name);
              this.ordersListObj.ordersList.splice(
                this.ordersListObj.ordersList.findIndex(
                  item => item.id === name
                ),
                1
              );
            })
            .catch(err => {
              //console.log(err);
            });
          break;
      }
    },
    // 跳转到详情页面
    redirectOrderDetailPage(orderInfo) {
      // 财产险
      // if (this.insuranceTypeTab.insuranceType == 1) {
      // }
      console.log(orderInfo);
      this.orderInfo = orderInfo;
      // this.$store.dispatch("orderList/getStoreOrderDetail", {
      //   insureNo: orderInfo.insureNo
      // });
      // // 已失效订单
      // this.$router.push({
      //   name: "proOrderDetailUnused",
      //   params: { insureNo: orderInfo.insureNo }
      // });
      // //  未支付订单
      // this.$router.push({
      //   name: "proOrderDetailUnPay",
      //   params: { userId: 123 }
      // });
      // // 待支付 保障中 已失效
      // // 待核保 ,待支付（申请支付号失败）核保完成
      // //  暂存（投保未确认未下单）,暂存（投保已确认已下单）
      // this.$router.push({
      //   name: "proOrderDetailTempSave",
      //   params: { userId: 123 }
      // });
      // //  保障中订单
      // this.$router.push({
      //   name: "proOrderDetailGuaranteed",
      //   params: { userId: 123 }
      // });
    },
    // 删除订单信息
    deleteOrderInfo(id) {
      Dialog.confirm({
        message: "确定删除吗？"
      })
        .then(() => {
          this.$store.dispatch("orderList/deleteStoreOrder", id);
          this.ordersListObj.ordersList.splice(
            this.ordersListObj.ordersList.findIndex(item => item.id === id),
            1
          );
        })
        .catch(err => {
          //console.log(err);
        });
    },
    // 查询订单详情
    async storeOrderDetailQuery() {
      let obj = {
        applicantName: "",
        currentDate: "",
        endDate: "",
        insuranceCode: "",
        insuranceType: "0",
        insureNo: "TEGD19210211090000000010",
        nclUserCode: "",
        startDate: "",
        status: "",
        userId: "2102000016"
      };
      const res = await storeOrderDetail(obj);
      //console.log(res);
    },
    // 订单转发支付查询信息
    async forwardPayProductInsureInfoQuery() {
      let obj = {
        forwardDate: "2aec1e8e361b5718f75d2f0dec67f654",
        proposalNo: "37b69d7af876cf5baf0a2e5075d846bd63fd3ea8f3e05090"
      };
      const res = await forwardPayProductInsureInfo(obj);
      //console.log(res);
    },
    // 门店保单回写
    async certiNoticeUpdate() {
      let obj = {
        insureNo: "TQPU19410190150000040001",
        policyNo: "PQPU19410190150000040001"
      };
      const res = await certiNotice(obj);
      //console.log(res);
    },
    // 跳转到影像上传页面
    gotoUploadImg(insureNo) {
      this.$router.push({
        name: "proOrderPortraitUpload",
        params: { proposalNo: insureNo }
      });
    },
    // 跳转到支付页面
    gotoPayPage(orderInfo) {
      let {
        insureNo,
        payApplyNo,
        identityCode,
        sumPremium,
        startDate
      } = orderInfo;
      this.$router.push({
        name: "paymentOrder",//TODO suncao
        query: {
              params: JSON.stringify({
              proposalNo: insureNo,
              paymentNo: payApplyNo,
              checkNo: identityCode,
              sumPremium: sumPremium,
              startDate: startDate,
              // productName:insuranceName,
              // applicantName:applicantName
            })
          }
      });
    },
    // 待核保
    async underwriting(orderInfo) {
      let _this = this;
      let res = await underwriting({ insureNo: orderInfo.insureNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success", position: "bottom" });
        // 获取支付号
        _this.getApplyPay(orderInfo.insureNo);
      }
    },
    // 获取支付号
    async getApplyPay(insureNo) {
      let request = await applyPay({ insureNo: insureNo });
      if (request.code == "1") {
        Toast({
          message: request.msg,
          type: "success",
          position: "bottom"
        });
        if (request.data && request.data.insureInfo) {
          let {
            insureNo,
            payApplyNo,
            identityCode,
            sumPremium,
            startDate
          } = request.data.insureInfo;
          this.$router.push({
            name: "paymentOrder",//TODO suncao
            query: {
                  params: JSON.stringify({
                  proposalNo: insureNo,
                  paymentNo: payApplyNo,
                  checkNo: identityCode,
                  sumPremium: sumPremium,
                  startDate: startDate,
                  // productName:insuranceName,
                  // applicantName:applicantName
                })
              }
          });
        }
      } else {
        Toast({
          message: request.msg,
          type: "success",
          position: "bottom"
        });
      }
    }
  }
};
</script>

<style lang="less">
.orderCard {
  padding: 0 15px;
  * {
    box-sizing: border-box;
    // height: calc(100vh - 200px);
    // overflow: scroll;
    // padding-top: 20px;
  }
  // padding: 14px 15px;
  .orderInfo {
    width: 345px;
    // height: 189px;
    background: rgba(255, 255, 255, 1);
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    padding: 13px;
    box-sizing: border-box;
    margin: 0 auto;
  }
  .orderCardInfo {
    // height: 209px;
    margin-top: 10px;
  }
  .orderCardInfo:first-child {
    margin-top: 0;
  }
  .redPay {
    color: #e64646 !important;
  }
  .orderInfoTop {
    height: 36px;
    width: 100%;
    font-size: 16px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
    line-height: 36px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 13px;
    .left {
      width: 80%;
      height: 36px;
      display: flex;
      flex-direction: row;
      align-items: center;

      .titleIcon {
        width: 36px;
        height: 36px;
        background: rgba(241, 247, 255, 1);
        border-radius: 22px;
        border: 1px solid rgba(171, 209, 255, 1);
        margin-right: 10px;
        img {
          width: 100%;
          height: auto;
        }
      }
      p {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
    }
    .right {
      button {
        border: none;
        background: none;
        width: 56px;
        height: 20px;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(59, 138, 254, 1);
        line-height: 20px;
      }
    }
  }
  .orderInfoMid {
    width: 315px;
    // height: 89px;
    background: rgba(250, 250, 250, 1);
    display: flex;
    flex-direction: column;
    padding: 12px;
    p {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 18px;
      height: 18px;
      margin-bottom: 10px;
    }
    p:last-child {
      margin-bottom: 0;
    }
    .strong {
      font-size: 14px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
    }
  }
  .orderInfoBottom {
    margin-top: 15px;

    button {
      color: rgba(59, 138, 254, 1);
      width: 80px;
      height: 30px;
      border-radius: 3px;
      border: 1px solid rgba(59, 138, 254, 1) !important;
      margin-right: 7px;
      background: none;
    }
    button:last-child {
      margin-right: 0;
    }
  }
  .deleteOrder {
    width: 60px;
    height: 100%;
    background: rgba(251, 50, 50, 1);
    border-radius: 0px 8px 8px 0px;
    line-height: 189px;
    font-size: 20px;
    text-align: center;
    color: rgba(255, 255, 255, 1);
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
  }
  .tempSave {
    width: 28px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
  }
  .underwrite {
    width: 56px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
    line-height: 20px;
  }
  .unpay {
    width: 42px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(252, 14, 14, 1);
    line-height: 20px;
  }
  .guaranteed {
    width: 42px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 190, 145, 1);
    line-height: 20px;
  }
  .unUsed {
    width: 42px;
    height: 20px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 20px;
  }
  .center4 {
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  .center2 {
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }
}
</style>
