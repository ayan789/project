/* zhangminjie 订单列表-公共组件-订单卡片（上拉刷新，下拉刷新） */
<template>
  <div class="orderCard healthIn">
    <!-- 下拉刷新 -->
    <van-pull-refresh
      v-model="isLoading"
      success-text="刷新成功"
      loosing-text
      @refresh="onDownFresh"
    >
      <!-- 上拉刷新 -->
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onUpFresh"
      >
        <!-- 待处理 -->
        <van-swipe-cell
          :name="item.id"
          class="orderCardInfo"
          :before-close="beforeClose"
          v-for="(item, index) in dataList"
          :key="index"
          :disabled="
            !((item.status == '0' && !item.insureNo) || item.status == '10')
          "
        >
          <van-card
            class="orderInfo"
            @click="redirectOrderDetailPage(item)"
          >
            <div slot="title">
              <div class="orderInfoTop">
                <div class="left">
                  <div class="titleIcon">
                    <img
                      src="@/assets/order/orderList/healthInsurance.png"
                      alt="orderList"
                    />
                  </div>
                  <p>{{ item.insuranceName }}</p>
                </div>
                <div class="right">
                  <button
                    class="tempSave"
                    v-if="item.status == 0"
                  >暂存</button>
                  <button
                    class="underwrite"
                    v-if="item.status == 1"
                  >
                    待核保
                  </button>
                  <!-- <button
                    class="underwrite"
                    v-if="item.status == 2 && item.signStatus == 0"
                  >
                    暂存
                  </button> -->
                  <button
                    class="underwrite"
                    v-if="item.status == 2"
                  >
                    核保完成
                  </button>
                  <button
                    class="noPay"
                    :class="item.payApplyNo == '' ? '' : 'redPay'"
                    v-if="item.status == 4 && item.payApplyNo == ''"
                  >
                    核保完成
                  </button>
                  <button
                    class="noPay"
                    :class="item.status == 4 ? 'redPay' : ''"
                    v-if="item.status == 4"
                  >
                    待支付
                  </button>
                  <button
                    class="noPay"
                    v-if="item.status == 6"
                  >已支付</button>
                  <button
                    class="guaranteed"
                    v-if="item.status == 7"
                  >
                    保障中
                  </button>
                  <button
                    class="unUsed"
                    v-if="item.status == 10"
                  >
                    已失效
                  </button>
                </div>
              </div>
              <div class="orderInfoMid">
                <p>
                  <span>投保人姓名</span>
                  <span class="itemContent">{{ item.applicantName }}</span>
                </p>
                <!-- <p>
                  <span>车牌号</span>
                  <span>{{ item.licensePlateNo }}</span>
                </p>-->
                <p v-if="
                    item.status == 0 ||
                      item.status == 1 ||
                      item.status == 2 ||
                      item.status == 4
                  ">
                  <span>应付金额</span>
                  <span
                    class="strong"
                    v-show="item.sumPremium"
                  >{{ item.sumPremium }}元</span>
                </p>
                <p v-if="item.status == 4 && item.payApplyNo">
                  <span>支付号</span>
                  <span class="strong">{{ item.payApplyNo }}</span>
                </p>
                <p v-if="
                    item.status == 7 || item.status == 10 || item.status == 6
                  ">
                  <span>保障期限</span>
                  <span
                    v-show="item.startDate && item.endDate"
                    class="itemContent"
                  >
                    {{ item.startDate.split(" ")[0] }}至
                    {{ item.endDate.split(" ")[0] }}
                  </span>
                </p>
              </div>
            </div>
            <div
              slot="footer"
              class="orderInfoBottom"
              @click.stop
            >
              <!-- 暂存（ 未下单） -->
              <div
                v-if="item.status == 0 && !item.insureNo"
                class="center2"
              >
                <button @click="continueWrite(item)">继续录入</button>
                <button @click="deleteOrderInfo(item)">删除订单</button>
              </div>
              <!-- 暂存（已下单） -->
              <div
                class="center2"
                v-if="item.status == 0 && item.insureNo"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button
                  v-if="
                    item.insureNo &&
                      item.isNeedSign == '1' &&
                      item.signStatus == '0'
                  "
                  @click="gotoSignature(item)"
                >
                  去签字
                </button>
              </div>
              <!-- 待核保 -->
              <div
                v-if="item.status == 1"
                class="center2"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button
                  v-if="
                    item.insureNo &&
                      item.isNeedSign == '1' &&
                      item.signStatus == '0'
                  "
                  @click="gotoSignature(item)"
                >
                  去签字
                </button>
                <button
                  v-if="item.orderSource == '1'"
                  @click="twoUnderwriting(item)"
                >
                  提交核保
                </button>
                <button
                  v-if="item.orderSource == '0'"
                  @click="underwriting(item)"
                >
                  提交核保
                </button>
              </div>
              <!-- 核保完成 -未签字 -->
              <div
                v-if="item.status == 2"
                class="center2"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button
                  v-if="
                    item.insureNo &&
                      item.isNeedSign == '1' &&
                      item.signStatus == '0'
                  "
                  @click="gotoSignature(item)"
                >
                  去签字
                </button>
                <button
                  @click="gotoPayPage(item)"
                  v-show="item.isSeeFee == '1' && item.orderSource == '0'"
                >
                  立即支付
                </button>

                <button
                  @click="twogotoPayPage(item)"
                  v-show="item.isSeeFee == '1' && item.orderSource == '1'"
                >
                  立即支付
                </button>
              </div>
              <!-- 待支付 -->
              <div
                v-if="item.status == 4"
                class="center2"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button
                  @click="gotoPayPage(item)"
                  v-show="item.isSeeFee == '1' && item.orderSource == '0'"
                >
                  立即支付
                </button>

                <button
                  @click="twogotoPayPage(item)"
                  v-show="item.isSeeFee == '1' && item.orderSource == '1'"
                >
                  立即支付
                </button>
              </div>
              <!-- 保障中 && 生效 -->
              <div
                v-if="item.status == 7 || item.status == 6"
                class="center4"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button @click="customerInfo(item)">客户</button>
                <button @click="doEleInvoice(item)">电子发票</button>
                <button @click="doElePolicy(item)">电子保单</button>
              </div>
              <!-- 已失效 -->
              <div
                v-if="item.status == 10"
                class="center2"
              >
                <button
                  v-if="item.insureNo && item.isNeedUploadImg == '1'"
                  @click="gotoUploadImg(item.insureNo)"
                >
                  影像管理
                </button>
                <button @click="deleteOrderInfo(item)">删除订单</button>
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
    <invoiceApplication
      :show="showInvoiceApplication"
      :type="invoicetype"
      @closeTipEvent="closeTip"
    />
    <policyDownload
      title="点击下方的下载按钮，可在线转发电子保单，输入邮箱地址或移动电话，直接把电子保单发送至客户邮箱或者手机中"
      :show="showPolicyDownload"
      :personInfo="personInfo"
      @downLoadPolicyEvent="downLoadPolicy"
      @hidePolicyDownload="hidePolicyDownload"
      @sendElectronicPolicyEvent="doSendElectronicPolicy"
    />
    <invoiceDownload
      title="点击下方的下载按钮，可在线转发电子发票，输入邮箱地址或移动电话，直接把电子发票发送至客户邮箱或者手机中"
      :show="showInvoiceDownload"
      :personInfo="personInfo"
      @downLoadInvoiceEvent="downLoadInvoice"
      @hideInvoiceDownload="hideInvoiceDownload"
      @sendElectronicPolicyEvent="doSendElectronicInvoice"
    />
  </div>
</template>

<script>
import invoiceApplication from "../elePolicy/invoiceApplication";
import policyDownload from "../elePolicy/policyDownload";
import invoiceDownload from "../elePolicy/invoiceDownload";
import { trackMap } from "@src/utils/index.js";
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
import { mapMutations, mapGetters } from "vuex";

import {
  deleteStoreOrder,
  orderList,
  electronicPolicy,
  einvoiceInquiry,
  sendElectronicPolicy,
  toInvoiceLinkQuery,
  sendElectronicPolicy1,
  imageUpload
} from "@src/http/module/order.js";
import {
  underwriting,
  applyPay,
  queryZc,
  underwritingTwo,
  applyPayTwo
} from "@src/http/module/nonCar.js";

export default {
  name: "orderListCard",
  components: {
    [invoiceApplication.name]: invoiceApplication,
    [invoiceDownload.name]: invoiceDownload,
    [SwipeCell.name]: SwipeCell,
    [Card.name]: Card,
    [Sku.name]: Sku,
    [List.name]: List,
    [Button.name]: Button,
    [PullRefresh.name]: PullRefresh,
    [policyDownload.name]: policyDownload
  },
  props: {
    ordersListObj: Object, //订单列表
    insuranceType: Number, //险种类型 0
    status: Number, //订单状态
    searchInput: Object //筛选信息
  },
  data: () => ({
    isLoading: false,
    finished: false, //控制在页面往下移动到底部时是否调用接口获取数据
    loading: false, //控制上拉加载的加载动画
    orderInfo: {},
    dataList: [],
    statusValue: "",
    form: {
      amountRange: "", //保费筛选查询ID

      applicantName: "", //投保人姓名

      currentDate: "", //创建日期

      dateRange: "", //日期筛选查询ID

      endDate: "", //保单止期

      insuranceCode: "", //险种代码

      insuranceType: 0, //保险类别

      insureNo: "", //投保单号

      size: 20, //每页多少条 //查询数量

      maxPremium: "", //最高保费

      minPremium: "", //最低保费

      page: 1, //第几页//起始位置

      orderEndDate: "", //日期筛选止期

      orderStrtDate: "", //日期筛选起期

      runappID: "", //出单来源

      startDate: "", //保单起期

      status: 4, //订单状态

      userCode: "", //用户ID
      keyword: ""
    },
    showInvoiceApplication: false,
    invoicetype: "success",
    showPolicyDownload: false,
    showInvoiceDownload: false,
    personInfo: {
      mobileNo: "",
      email: ""
    }
  }),

  watch: {
    // status: {
    //   handler(newValue) {
    //     console.log(292, newValue);
    //     this.form.status = newValue;
    //     this.statusValue = newValue;
    //   },
    //   deep: true
    // }

    status () {
      this.form.status = this.status;
    }
  },
  created () {
    this.$nextTick(() => {
      if (this.status == 0) {
        this.form.status = 4;
      } else {
        this.form.status = this.status;
      }
    });
  },
  computed: {
    ...mapGetters("million", ["newData"])
  },
  methods: {
    containue () {
      console.dir("ok");
    },
    onUpFresh () {
      this.finished = false;
      if (this.isLoading) {
        return false;
      }
      if (this.status == 0) {
        this.form.status = 4;
      } else {
        this.form.status = this.status;
      }
      this.form.keyword = this.searchInput.searchInput;
      this.form.dateRange = this.searchInput.dataType;
      this.form.orderStrtDate = this.searchInput.startDay;
      this.form.orderEndDate = this.searchInput.endDay;
      this.form.amountRange = this.searchInput.premiumMsg;
      this.form.minPremium = this.searchInput.premiumMin;
      this.form.maxPremium = this.searchInput.premiumMax;
      this.form.runappID = this.searchInput.singleSourceMsg;

      orderList(this.form)
        .then(res => {
          if (res.code === "1") {
            this.loading = false;
            this.dataList = this.dataList.concat(res.data.orderList);
            if (res.data.orderList.length < this.form.size) {
              this.finished = true;
            }
            if (res.data.orderList == null) {
              this.finished = true;
            }

            this.form.page++;
            var allArr = [];
            for (var i = 0; i < this.dataList.length; i++) {
              var flag = true;
              for (var j = 0; j < allArr.length; j++) {
                if (this.dataList[i].id == allArr[j].id) {
                  flag = false;
                }
              }
              if (flag) {
                allArr.push(this.dataList[i]);
              }
            }
            console.log(allArr, "allArr  ==>394");
            this.dataList = allArr;
            // console.log(this.dataList, "--------上拉刷新");
          } else {
            this.handle();
            this.$toast.fail(res.msg || res.message);
          }
        })
        .catch(err => {
          this.handle();
        });
    },

    // 下拉刷新
    onDownFresh () {
      this.getdataList();
    },
    getdataList () {
      // this.$nextTick(() => {
      //   console.log(this.status, "heweiwei  =?329");
      // });
      // console.log(335, this.status);
      this.form.page = 1;
      if (this.status == 0) {
        this.form.status = 4;
      } else {
        this.form.status = this.status;
      }
      this.finished = false;
      if (this.loading) {
        return false;
      }
      // console.log(this.status);
      this.form.keyword = this.searchInput.searchInput;
      this.form.dateRange = this.searchInput.dataType;
      this.form.orderStrtDate = this.searchInput.startDay;
      this.form.orderEndDate = this.searchInput.endDay;
      this.form.amountRange = this.searchInput.premiumMsg;
      this.form.minPremium = this.searchInput.premiumMin;
      this.form.maxPremium = this.searchInput.premiumMax;
      this.form.runappID = this.searchInput.singleSourceMsg;
      this.dataList = [];
      orderList(this.form)
        .then(res => {
          if (res.code === "1") {
            this.dataList = this.dataList.concat(res.data.orderList);
            this.isLoading = false;
            var allArr = [];
            for (var i = 0; i < this.dataList.length; i++) {
              var flag = true;
              for (var j = 0; j < allArr.length; j++) {
                if (this.dataList[i].id == allArr[j].id) {
                  flag = false;
                }
              }
              if (flag) {
                allArr.push(this.dataList[i]);
              }
            }
            // console.log(allArr, "allArr  ==>394");
            this.dataList = allArr;
            this.loading = false;
            if (
              res.data.orderList &&
              res.data.orderList.length < this.form.size
            ) {
              this.finished = true;
            }
            if (res.data.orderList.length == 0) {
              this.finished = true;
            }
            // console.log(this.dataList, "--------下拉刷新");
          } else {
            this.handle();
            this.$toast.fail(res.msg || res.message);
          }
        })
        .catch(err => {
          this.handle();
        });
      this.loading = false;
      this.isLoading = false; //关闭下拉刷新效果
      this.finished = false;
    },
    //处理上下拉
    handle () {
      this.isLoading = true;
      this.loading = false;
      this.finished = true;
    },
    //  客户
    customerInfo (orderInfo) {
      if (!orderInfo.custNo) {
        this.$toast.fail("当前没有客户信息");
      } else {
        this.$router.push({
          name: "customerEquity",
          query: {
            custNo: orderInfo.custNo, //客户号
            custSourceType: orderInfo.custSourceType, //1本地客户，2保单客户
            custType: orderInfo.custType, //1个人，2团体。
            viewType: orderInfo.custType //同custType，1个人，2团体。
          }
        });
      }
    },

    // 滑动滑块内容关闭时的行为,滑动删除
    beforeClose ({ position, instance, name }) {
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
              let orderInfo = this.dataList.find(item => item.id === name);
              this.deleteOrder(orderInfo).then(res => {
                if (res.code == "1" && res.msg) {
                  Toast.allowMultiple();
                  Toast({
                    forbidClick: true,
                    message: res.msg,
                    type: "success"
                  });
                  setTimeout(() => {
                    Toast.clear();
                  }, 2000);
                }
                this.dataList.splice(
                  this.dataList.findIndex(item => item.id === name),
                  1
                );
              });
            })
            .catch(err => {
              instance.close();
              //console.log(err);
            });
          break;
      }
    },
    // 跳转到影像管理页面
    gotoUploadImg (insureNo) {
      let obj = {
        busiDate: new Date().getFullYear() + "0000",
        bussNo: insureNo,
        classCode: "YEF",
        comCode: localStorage.getItem("userInfo")
          ? JSON.parse(localStorage.getItem("userInfo")).comCode
          : {},
        imgType: "UW_Y",
        userCode: localStorage.getItem("userInfo")
          ? JSON.parse(localStorage.getItem("userInfo")).userCode
          : {},
        flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
      };
      this.queryImageUpload(obj).then(res => {
        // console.log("44444", res);
        if (res.code == "1") {
          Toast({ message: res.msg, type: "success" });
          if (res.data && res.data.url) {
            console.log(res.data.url);
            let param = {
              url: res.data.url,
              type: "0",
              titleBarTag: "1",
              shareUrl: res.data.url, //TODO suncao 影像管理
              titleBar: "影像管理",
              sharePlatform: ["0"],
              shareType: "url",
              title: "投保订单影像管理",
              imgURL:
                "https://msupsale-uat.cci110101197006074393c-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
              image: "", //分享图片（base64编码）, 与imgURL二选一
              desc: "尊敬的客户，您的投保订单已生成，可以在线上传影像"
            };
            console.log("请求壳子的参数===", JSON.stringify(param));
            this.$native.openWebView(param, data => { });
            // window.location.href = res.data.url;
          }
        }
      });
    },
    // 获取影像管理的页面链接
    async queryImageUpload (obj) {
      return await imageUpload(obj);
    },
    // 跳转到详情页面
    redirectOrderDetailPage (orderInfo) {
      this.orderInfo = orderInfo;
      //insuranceCategory  0 ： 意健    2 财产险
      if (orderInfo.orderSource && orderInfo.orderSource == "1") {
        switch (orderInfo.status) {
          case "0":
            if (orderInfo.insureNo) {
              this.$router.push({
                name: "tempSaveOrdered",
                query: { orderId: orderInfo.id, insuranceCategory: 0 }
              });
            } else {
              this.$router.push({
                name: "tempSave",
                query: { orderId: orderInfo.id, insuranceCategory: 0 }
              });
            }

            break;
          case "1":
            this.$router.push({
              name: "underwritten",
              query: { orderId: orderInfo.id, insuranceCategory: 0 }
            });
            break;
          case "2":
            this.$router.push({
              name: "underwriting",
              query: {
                orderId: orderInfo.id,
                signStatus: orderInfo.signStatus,
                insuranceCategory: 0
              }
            });

            break;
          case "4":
            this.$router.push({
              name: "unPay",
              query: { orderId: orderInfo.id, insuranceCategory: 0 }
            });
            break;
          case "6":
            this.$router.push({
              name: "guaranteed",
              query: { orderId: orderInfo.id, insuranceCategory: 0 }
            });
            break;
          case "7":
            this.$router.push({
              name: "guaranteed",
              query: { orderId: orderInfo.id, insuranceCategory: 0 }
            });
            break;
          case "10":
            this.$router.push({
              name: "unused",
              query: { orderId: orderInfo.id, insuranceCategory: 0 }
            });
            break;

          default:
            break;
        }
      } else {
        switch (orderInfo.status) {
          case "0":
            if (orderInfo.insureNo) {
              this.$router.push({
                name: "heOrderDetailTempSaveOrdered",
                query: { insureNo: orderInfo.insureNo }
              });
            } else {
              this.$router.push({
                name: "heOrderDetailTempSave",
                query: { orderNo: orderInfo.orderNo }
              });
            }

            break;
          case "1":
            this.$router.push({
              name: "heOrderDetailUnderwritten",
              query: { insureNo: orderInfo.insureNo }
            });
            break;
          case "2":
            if (orderInfo.signStatus == 0) {
              this.$router.push({
                name: "heOrderDetailTempSaveOrdered",
                query: { insureNo: orderInfo.insureNo }
              });
            } else {
              this.$router.push({
                name: "heOrderDetailUnderwriting",
                query: {
                  insureNo: orderInfo.insureNo,
                  signStatus: orderInfo.signStatus
                }
              });
            }

            break;
          case "4":
            this.$router.push({
              name: "heOrderDetailUnPay",
              query: { insureNo: orderInfo.insureNo }
            });
            break;
          case "6":
            this.$router.push({
              name: "heOrderDetailGuaranteed",
              query: { insureNo: orderInfo.insureNo }
            });
            break;
          case "7":
            this.$router.push({
              name: "heOrderDetailGuaranteed",
              query: { insureNo: orderInfo.insureNo }
            });
            break;
          case "10":
            this.$router.push({
              name: "heOrderDetailUnused",
              query: { insureNo: orderInfo.insureNo }
            });
            break;

          default:
            break;
        }
      }
    },
    // 删除订单信息
    async deleteOrder (obj) {
      return await deleteStoreOrder({
        id: [obj.id],
        isMetaData: obj.orderSource
      });
    },
    // 按钮删除 操作
    deleteOrderInfo (orderInfo) {
      Dialog.confirm({
        message: "确定删除吗？"
      })
        .then(() => {
          this.deleteOrder(orderInfo).then(res => {
            if (res.code == "1" && res.msg) {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "success"
              });
              this.dataList.splice(
                this.dataList.findIndex(item => item.id == orderInfo.id),
                1
              );
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            } else {
              this.$toast.fail(res.msg || res.message);
            }
          });
        })
        .catch(() => {
          //console.log(err);
        });
    },
    // 继续录入,暂存未下单
    continueWrite (orderInfo) {
      if (orderInfo.orderSource == "1") {
        this.$router.push({
          name: "prIndex",
          query: { strategyId: orderInfo.strategyId, orderId: orderInfo.id }
        });
      } else {
        //  this.$getToRoute()
        let obj = { orderNo: orderInfo.orderNo };
        queryZc(obj).then(res => {
          if (res.code == "1" && res.data && res.data.storeOracleRequestVO) {
            if (res.data.storeOracleRequestVO.productCode == "FM51") {
              this.$router.push({
                name: "writeForm",
                params: {
                  back: JSON.stringify("3"),
                  item1: JSON.stringify(res.data),
                  isFromOrderList: true
                }
              });
            } else if (res.data.storeOracleRequestVO.productCode == "FM50") {
              this.cleTenMillionData();
              this.$router.push({
                name: "insuranceInformationInput",
                params: {
                  copy: JSON.stringify(res.data.storeOracleRequestVO),
                  orderNo: orderInfo.orderNo
                }
              });
            } else if (res.data.storeOracleRequestVO.productCode == "WTI01") {
              this.cleNewData();
              let tS = this.newData;
              if (res.data.storeOracleRequestVO) {
                let copy = res.data.storeOracleRequestVO;
                for (let k in tS) {
                  tS[k] = copy[k];
                }
                tS.placeInfo = copy["addressInfo"].addressName;
                this.setNewData(tS);
                this.setNewAgesJson({ premiumsJson: copy.premiumsJson });

                localStorage.setItem("newDataOrderNo", orderInfo.orderNo);
                this.$router.push({
                  name: "stopCancerForm"
                });
              }
            }
          }
        });
      }
    },
    //跳转到签字页面
    // gotoSignature(orderInfo) {
    //   this.$router.push({
    //     name: "prSign",
    //     query: {
    //       insureNo: orderInfo.insureNo
    //     }
    //   });
    // },
    gotoSignature (orderInfo) {
      if (orderInfo.orderSource == "1") {
        this.$router.push({
          name: "prSign",
          query: {
            insureNo: orderInfo.insureNo,
          },
        })
      } else {
        this.$router.push({
          name: "signatureConfirm",
          query: {
            insureNo: orderInfo.insureNo,
            sumPremium: orderInfo.sumPremium,
            mobile: orderInfo.mobileNo,
          },
        })
      }
    },
    // 跳转到支付页面
    twogotoPayPage (orderInfo) {
      console.log("意健险订单信息", JSON.stringify(orderInfo));
      if (this.$isNull(orderInfo.insureNo)) {
        Toast({
          message: "投保单号不存在",
          type: "fail"
        });
        return;
      }
      if (this.$isNull(orderInfo.payApplyNo)) {
        let _this = this;
        _this.getApplyPayTwp(orderInfo);
        return;
      }
      this.$router.push({
        name: "prPaymentOrder",
        query: {
          proposalNo: orderInfo.insureNo
        }
      });
    },
    // 跳转到支付页面
    gotoPayPage (orderInfo) {
      // console.log(orderInfo);
      let {
        insureNo,
        payApplyNo,
        identityCode,
        sumPremium,
        startDate,
        applicantName,
        mobileNo,
        insuredName,
        insuredMobile,
        insuranceName
      } = orderInfo;
      if (payApplyNo) {
        this.$router.push({
          name: "paymentOrder",
          query: {
            params: JSON.stringify({
              proposalNo: insureNo,
              paymentNo: payApplyNo,
              checkNo: identityCode,
              sumPremium: sumPremium,
              startDate: startDate,
              productName: insuranceName,
              applicantName: applicantName,
              mobileNo: mobileNo,
              insuredName: insuredName,
              insuredMobile: insuredMobile,
              insuranceType: 1 //保险类别 1财产险，2个人版，3家庭版
            })
          }
        });
      } else {
        this.getApplyPay(orderInfo);
      }
    },
    // 待核保
    async underwriting (orderInfo) {
      let _this = this;
      let res = await underwriting({ insureNo: orderInfo.insureNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success" });
        // 获取支付号
        _this.getApplyPay(orderInfo);
      } else {
        Toast({
          message: res.msg,
          type: "fail"
        });
      }
    },
    //非车配置接口---- 待核保 underwritingTwo
    async twoUnderwriting (orderInfo) {
      let _this = this;
      let res = await underwritingTwo({ insureNo: orderInfo.insureNo });
      if (res.code == "1") {
        Toast({ message: "核保成功", type: "success" });
        // 获取支付号
        _this.getApplyPayTwp(orderInfo);
      } else {
        this.$dialog.alert({ message: res.msg || res.message });
      }
    },
    // 获取支付号--旧
    async getApplyPay (orderInfo) {
      let request = await applyPay({ insureNo: orderInfo.insureNo });
      if (request.code == "1") {
        Toast({
          message: request.msg,
          type: "success"
        });
        if (request.data && request.data.insureInfo) {
          let {
            insureNo,
            payApplyNo,
            identityCode,
            sumPremium,
            startDate,
            productName,
            applicantName,
            mobileNo,
            insuredName,
            insuredMobile
          } = request.data.insureInfo;
          this.$router.push({
            name: "paymentOrder",
            query: {
              params: JSON.stringify({
                proposalNo: insureNo,
                paymentNo: payApplyNo,
                checkNo: identityCode,
                sumPremium: sumPremium,
                startDate: startDate,
                productName,
                applicantName,
                mobileNo,
                insuredName,
                insuredMobile,
                insuranceType: 1 //保险类别 1财产险，2个人版，3家庭版
              })
            }
          });
        }
      } else {
        this.$toast.fail(request.msg || request.message);
      }
    },
    // 获取支付号 --新
    async getApplyPayTwp (orderInfo) {
      let request = await applyPayTwo({ insureNo: orderInfo.insureNo });
      if (request.code == "1") {
        Toast({
          message: request.msg,
          type: "success"
        });
        if (request.data && request.data.noncarOrder.insureNo) {
          this.$router.push({
            name: "prPaymentOrder",
            query: {
              proposalNo: request.data.noncarOrder.insureNo
            }
          });
        }
      } else {
        Toast({
          message: request.msg || request.message,
          type: "fail"
        });
      }
    },
    // 电子发票
    doEleInvoice (item) {
      this.personInfo.mobileNo = item.mobileNo;
      this.personInfo.email = item.email;
      this.personInfo.insureNo = item.insureNo;
      this.personInfo.policyNo = item.policyNo;
      this.personInfo.orderSource = item.orderSource;
      let obj = {
        mobileNo: item.mobileNo,
        policyNo: item.policyNo,
        isMetaData: item.orderSource //是否动态表单订单 1是0否
      };
      console.log("电子发票");
      // 查询电子发票短链接，有短链接就去直接跳到下载页面，没有短链接就弹出申请开票弹框
      this.doEinvoiceInquiry(obj).then(res => {
        // console.log("返回数据============" + res);
        if (res.code == "1" && res.data) {
          if (
            res.data.responseBody &&
            res.data.responseBody.invoiceLinkList.length > 0
          ) {
            this.showInvoiceDownload = true;
            localStorage.setItem(
              "shortLink",
              res.data.responseBody.invoiceLinkList[0].shortLink
            );
          } else {
            //TODO suncao responseBody.successTime
            this.showInvoiceApplication = true;
            this.invoicetype = "confirm";
          }
        } else {
          this.$toast.fail(res.msg);
        }
      });
    },
    // 查询电子发票
    async doEinvoiceInquiry (obj) {
      return await toInvoiceLinkQuery(obj);
    },
    // 申请电子发票
    async doEInvoiceApplication (obj) {
      return await einvoiceInquiry(obj);
    },
    // 关闭发票申请提示框
    closeTip (value, type) {
      if (type == 2) {
        // 申请开票
        let obj = {
          mobileNo: this.personInfo.mobileNo,
          policyNo: this.personInfo.policyNo
        };
        this.doEInvoiceApplication(obj).then(res => {
          if (res.code == "1") {
            Toast.allowMultiple();
            Toast({
              forbidClick: true,
              message: res.msg,
              type: "success"
            });
            setTimeout(() => {
              Toast.clear();
            }, 2000);
          } else {
            Toast.allowMultiple();
            Toast({
              forbidClick: true,
              message: res.msg,
              type: "fail",
              duration: 3000
            });
            setTimeout(() => {
              Toast.clear();
            }, 3000);
          }
        });
      }
      this.showInvoiceApplication = value;
    },
    //  关闭电子发票下载提示框
    hideInvoiceDownload (value) {
      this.showInvoiceDownload = value;
    },
    // 下载电子发票
    downLoadInvoice () {
      // let url =
      // this.$getIP() +
      // "/order/elePolicy/invoicePreview?shortLink=" +
      // encodeURI(localStorage.getItem("shortLink"));
      // console.log("跳转电子发票页面地址："+url);

      let shortLink = localStorage.getItem("shortLink");
      let url = "";
      if (shortLink.indexOf("electronicsdmz") >= 0) {
        url =
          this.$getIP() +
          "/order/elePolicy/invoicePreview?shortLink=" +
          encodeURI(localStorage.getItem("shortLink"));
      } else {
        url = shortLink;
      }
      let param = {
        url: url,
        type: "0",
        titleBarTag: "1",
        shareUrl: url, //TODO suncao 电子发票
        titleBar: "电子发票预览",
        sharePlatform: ["0"],
        shareType: "url",
        title: "电子发票预览",
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image: "", //分享图片（base64编码）, 与imgURL二选一
        desc: "尊敬的客户，您的电子发票已生成，可以在线预览电子发票"
      };
      console.log("请求壳子的参数===", JSON.stringify(param));
      this.$native.openWebView(param, data => {
        data = JSON.parse(data);
        if (data.resultCode === "1") {
          trackMap("c_no_car_order_list_bill_apply", "非车险订单开票申请", {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "非车险出单"
          });
        }
      });
      // this.showInvoiceDownload = false;
    },
    // 发送电子发票
    doSendElectronicInvoice (type, user) {
      let userInfo = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let obj = {
        policyNo: user.policyNo,
        type: type,
        userCode: userInfo.userCode,
        comCode: userInfo.comCode,
        isMetaData: user.orderSource
      };
      switch (type) {
        case "2":
          console.log("发送手机号码");
          obj.mobile = user.mobileNo;
          this.sendElePolicy1(obj).then(res => {
            if (res.code == "0") {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "fail"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            } else {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "success"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            }
          });
          break;
        case "1":
          obj.email = user.email;
          this.sendElePolicy1(obj).then(res => {
            console.log(res);
            if (res.code == "0") {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "fail"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            } else {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "success"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            }
          });
          break;
      }
    },
    // 发送电子发票
    async sendElePolicy1 (obj) {
      return await sendElectronicPolicy1(obj);
    },
    // 电子保单
    doElePolicy (item) {
      this.personInfo.mobileNo = item.mobileNo;
      this.personInfo.email = item.email;
      this.personInfo.policyNo = item.policyNo;
      this.personInfo.orderSource = item.orderSource;
      this.showPolicyDownload = true;
      console.log("电子保单");
    },
    // 发送电子保单
    doSendElectronicPolicy (type, user) {
      let userInfo = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let obj = {
        policyNo: user.policyNo,
        type: type,
        userCode: userInfo.userCode,
        comCode: userInfo.comCode,
        isMetaData: user.orderSource
      };
      switch (type) {
        case "2":
          console.log("发送手机号码");
          obj.mobile = user.mobileNo;
          this.sendElePolicy(obj).then(res => {
            if (res.code == "0") {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "fail"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            } else {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "success"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
              //埋点
              trackMap(
                "c_no_car_order_list_electroic_policy_share",
                "非车险订单电子保单分享",
                {
                  c_salesperson_type: "N",
                  c_market_type: "Y",
                  c_market_name: "",
                  c_market_id: "",
                  c_event_category: "非车险出单"
                }
              );
            }
          });
          break;

        case "1":
          obj.email = user.email;
          this.sendElePolicy(obj).then(res => {
            console.log(res);
            if (res.code == "0") {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "fail"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
            } else {
              Toast.allowMultiple();
              Toast({
                forbidClick: true,
                message: res.msg,
                type: "success"
              });
              setTimeout(() => {
                Toast.clear();
              }, 2000);
              //埋点
              trackMap(
                "c_no_car_order_list_electroic_policy_share",
                "非车险订单电子保单分享",
                {
                  c_salesperson_type: "N",
                  c_market_type: "Y",
                  c_market_name: "",
                  c_market_id: "",
                  c_event_category: "非车险出单"
                }
              );
            }
          });
          break;
      }
    },

    // 发送电子保单到手机或者邮箱
    async sendElePolicy (obj) {
      return await sendElectronicPolicy(obj);
    },

    // 关闭电子保单下载提示框
    hidePolicyDownload (value) {
      this.showPolicyDownload = value;
    },
    // 下载电子保单
    downLoadPolicy () {
      console.log("downLoadPolicy");
      this.electronicPolicyDownload({
        policyNo: this.personInfo.policyNo,
        isMetaData: this.personInfo.orderSource
      }).then(res => {
        if (res.code == "0") {
          Toast.allowMultiple();
          Toast({
            forbidClick: true,
            message: res.msg,
            type: "fail",
            duration: 3000
          });
          setTimeout(() => {
            Toast.clear();
          }, 3000);
        } else {
          Toast.allowMultiple();
          Toast({
            forbidClick: true,
            message: res.msg,
            type: "success"
          });
          setTimeout(() => {
            Toast.clear();
          }, 2000);
          //埋点
          trackMap(
            "c_no_car_order_list_electroic_policy_down",
            "非车险订单电子保单下载",
            {
              c_salesperson_type: "N",
              c_market_type: "Y",
              c_market_name: "",
              c_market_id: "",
              c_event_category: "c_market_id"
            }
          );
          //TODO suncao
          let param = {
            url: res.data.downLoadUrl,
            type: "0",
            titleBarTag: "1"
          };
          // console.log("请求壳子===", JSON.stringify(param));
          // this.$native.openWebView(param, data => {});
          if (this.$isNull(res.data.policyS3path)) {
            this.$dialog.alert({
              message: res.responseHead.appMessage || ""
            });
            return;
          }
          localStorage.setItem("downLoadUrl", res.data.policyS3path);
          this.$router.push({
            name: "elePolicyPreview",
            query: { downLoadUrl: res.data.policyS3path }
          });
        }
      });
    },
    // 下载电子保单
    async electronicPolicyDownload (o) {
      return await electronicPolicy(o);
    },
    ...mapMutations("million", {
      setTenMillionData: "SETTENMILLIONDATA",
      setNewData: "SETNEWDATA",
      setFormula: "SETFORMULA",
      cleTenMillionData: "CLETENMILLIONDATA",
      cleNewData: "CLENEWDATA",
      setNewAgesJson: "SETNEWAGESJSON"
    })
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
    // width: 345px;
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
        height: 100%;
        background: transparent;
        border-radius: 22px;
        border: 1px solid rgba(171, 209, 255, 1);
        margin-right: 10px;
        img {
          width: 100%;
          height: auto;
          vertical-align: super;
        }
      }
      p {
        font-size: 15px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        line-height: 21px;
      }
    }
    .right {
      width: 20%;
      text-align: center;
      button {
        border: none;
        background: none;
        // width: 56px;
        // height: 20px;
        // font-size: 13px;
        // font-family: PingFangSC-Regular, PingFang SC;
        // font-weight: 400;
        // color: rgba(59, 138, 254, 1);
        // line-height: 20px;
      }
      .underwrite {
        width: 39px;
        height: 19px;
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(59, 138, 254, 1);
        line-height: 19px;
      }
      .noPay {
        width: 39px;
        height: 19px;
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(230, 70, 70, 1);
        line-height: 19px;
      }
      .unUsed {
        width: 39px;
        height: 19px;
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(163, 163, 163, 1);
        line-height: 19px;
      }
    }
  }
  .orderInfoMid {
    width: 100%;
    margin: 0 auto;
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
      color: rgba(136, 136, 136, 1);
      line-height: 19px;
      height: 19px;
      margin-bottom: 2px;
    }
    p:last-child {
      margin-bottom: 0;
    }
    .itemContent {
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 19px;
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
    line-height: 0 !important;
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
    justify-content: flex-end;
    align-items: center;
    margin-top: 14px;
  }
  .center2 {
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }
}
.van-swipe-cell__right {
  right: -3px;
}
.healthIn {
  .center4 {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    margin-top: 14px;
  }
  .van-card__content {
    min-height: 0;
  }
  .orderInfo {
    .orderInfoTop {
      margin-bottom: 14px;
      .right {
        width: 20%;
        text-align: center;
        button {
          border: none;
          background: none;
          width: 56px;
          // height: 20px;
          // font-size: 13px;
          // font-family: PingFangSC-Regular, PingFang SC;
          // font-weight: 400;
          // color: rgba(59, 138, 254, 1);
          // line-height: 20px;
        }
        /deep/.guaranteed {
          height: 19px;
          font-size: 13px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(59, 138, 254, 1);
          line-height: 19px;
        }
        /deep/.underwrite,
        /deep/.tempSave {
          height: 19px;
          font-size: 13px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(59, 138, 254, 1);
          line-height: 19px;
        }
        /deep/.noPay {
          height: 19px;
          font-size: 13px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(230, 70, 70, 1);
          line-height: 19px;
        }
        /deep/.unUsed {
          height: 19px;
          font-size: 13px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(163, 163, 163, 1);
          line-height: 19px;
        }
      }
    }
  }
}
</style>
