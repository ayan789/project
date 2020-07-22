<!-- eslint-disable prettier/prettier -->
/* zhangminjie 订单列表-公共组件-订单卡片（上拉刷新，下拉刷新） */
<template>
  <div
    class="orderCard"
    id="container"
  >
    <!-- 下拉刷新 -->
    <van-pull-refresh
      v-model="isLoading"
      success-text="刷新成功"
      loosing-text
      @refresh="onDownFresh"
    >
      <!--上拉加载 -->
      <van-list
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="onUpFresh"
      >
        <!-- 待处理 -->
        <van-swipe-cell
          :name="item.id + '*' + item.orderStatus"
          class="orderCardInfo"
          v-show="insureDatalist.length > 0"
          :before-close="beforeClose"
          v-for="(item, index) in insureDatalist"
          :key="index"
          :disabled="item.orderStatus != '10'"
        >
          <van-card
            class="orderInfo"
            @click="redirectOrderDetailPage(item)"
          >
            <div slot="title">
              <div class="orderInfoTop">
                <div class="left">
                  <!-- <div class="titleIcon">
                    <img src="@/assets/order/orderList/icon1.png" alt="orderList" />
                  </div> -->

                  <p>{{ item.licenseNo }}</p>
                </div>
                <div class="right">
                  <button
                    class="tempSave"
                    v-if="item.orderStatus == '10'"
                  >
                    暂存
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '20'"
                  >
                    待提交
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '24'"
                  >
                    核保退回
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '21'"
                  >
                    核保中
                  </button>
                  <button
                    class="tempSave unpay"
                    v-else-if="item.orderStatus == '22'"
                  >
                    核保失败
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '23'"
                  >
                    核保通过
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '30'"
                  >
                    已生效
                  </button>

                  <button
                    class="tempSave unUsed"
                    v-else-if="item.orderStatus == '31'"
                  >
                    已失效
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '32'"
                  >
                    满期
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '33'"
                  >
                    退保
                  </button>
                  <button
                    class="tempSave unpay"
                    v-else-if="item.orderStatus == '40'"
                  >
                    待支付
                  </button>
                  <button
                    class="tempSave unpay"
                    v-else-if="item.orderStatus == '41'"
                  >
                    支付中
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '42'"
                  >
                    已支付
                  </button>
                  <button
                    class="tempSave unUsed"
                    v-else-if="item.orderStatus == '50'"
                  >
                    已过期
                  </button>
                  <button
                    class="tempSave"
                    v-else-if="item.orderStatus == '51'"
                  >
                    待续保
                  </button>
                </div>
              </div>
              <div class="orderInfoMid">
                <div v-if="item.orderStatus == '10' || item.orderStatus == '20' || item.orderStatus == '24' ||item.orderStatus == '21'">
                  <span>报价日期</span>
                  <span class="right_C">{{ item.lastQuoteDate }}</span>
                </div>
                <div v-if="item.orderStatus == '40' || item.orderStatus == '41'">
                  <span>核保日期</span>
                  <span class="right_C">{{ item.underwritePassDate }}</span>
                </div>
                <div v-if="item.orderStatus == '42' || item.orderStatus == '30'">
                  <span>生效日期</span>
                  <span class="right_C">{{ item.createPolicyTime }}</span>
                </div>
                <div>
                  <span>投保人</span>
                  <span class="right_C">{{ item.applicantName }}</span>
                </div>
                <!-- <template v-if="item.orderStatus == '30'"> -->
                <!-- <p>
                    <span>保障期限</span>
                    <span>{{ item.effictiveDate }}至{{ item.expireDate }}</span>
                </p>-->
                <div v-if="item.sumPayment">
                  <span>合计(保费+车船税)</span>
                  <span class="right_C">{{ item.totalPremium }} 元</span>
                </div>
                <div v-else>
                  <span>合计(保费)</span>
                  <span class="right_C">{{ item.totalPremium }} 元</span>
                </div>
                <div v-if="item.feeShowSwitch == '1' || item.feeShowSwitch == '3'">
                  <span>预计费用（元）</span>
                  <span>{{ item.totalCfeeLimit }}</span>
                </div>
                <div v-if="item.feeShowSwitch == '2' || item.feeShowSwitch == '3'">
                  <span>预计佣金（元）</span>
                  <span>{{ item.totalC1feeLimit }}</span>
                </div>
                <!-- </template> -->
                <!-- <template> -->

                <!-- </template> -->
              </div>
            </div>
            <div
              slot="footer"
              class="orderInfoBottom"
              @click.stop
            >
              <!-- <template> -->
              <div class="hintInfo1">
                <!-- 暂存（ 未下单） -->
                <template
                  v-if="item.orderStatus == '10'"
                  class="center2"
                >
                  <button @click="continueInsurance(item)">继续报价</button>
                </template>
                <!-- 待核保 -->
                <template
                  v-else-if="item.orderStatus == '20'"
                  class="center2"
                >
                  <button
                    class="verificationBtn"
                    v-if="item.orderCheckNoFlag == '1' && (item.businessQuerySequenceNo || item.trafficQuerySequenceNo)"
                    @click="verification(item)"
                  >
                    验证码申请
                  </button>
                  <button @click="gotoUploadImg(item)">影像管理</button>
                  <button @click="submitAudit(item)">提交核保</button>
                </template>

                <!-- 核保退回 -->
                <template
                  v-else-if="item.orderStatus == '24'"
                  class="center2"
                >
                  <!-- <button @click="continueInsurance(item)">投保修改</button> -->
                  <button
                    class="verificationBtn"
                    @click="copyPolicy(item)"
                  >复制投保单</button>
                  <button @click="submitAudit(item)">提交核保</button>
                  <button @click="gotoUploadImg(item)">影像管理</button>
                  <button @click="getUnderWriting(item)">核保查询</button>
                </template>
                <!-- 核保中 -->
                <template
                  v-else-if="item.orderStatus == '21'"
                  class="center2"
                >
                  <button @click="gotoUploadImg(item)">影像管理</button>
                  <button @click="getUnderWriting(item)">核保查询</button>
                </template>

                <!-- 核保失败 -->
                <template
                  v-else-if="item.orderStatus == '22'"
                  class="center2"
                >
                  <button
                    class="verificationBtn"
                    @click="copyPolicy(item)"
                  >
                    复制投保单
                  </button>
                  <button @click="getUnderWriting(item)">核保查询</button>
                </template>

                <!-- 待支付 -->
                <template
                  v-else-if="item.orderStatus == '40'"
                  class="center2"
                >
                  <button
                    v-if="item.payExpireFlag == '0'"
                    @click="gotoPayPage(item)"
                  >
                    支付
                  </button>
                  <button
                    class="verificationBtn"
                    v-if="item.orderCheckNoFlag == '1' && item.payExpireFlag == '0' && (item.businessQuerySequenceNo || item.trafficQuerySequenceNo)"
                    @click="verification(item)"
                  >
                    验证码申请
                  </button>
                  <button @click="gotoUploadImg(item)">影像管理</button>
                  <button @click="getUnderWriting(item)">核保查询</button>
                </template>

                <!-- 已生效 -->
                <template
                  v-else-if="item.orderStatus == '30'"
                  class="center2"
                >
                  <button @click="gotoCustomerInfo(item)">客户信息</button>
                  <button @click="elecPolicy(item)">电子保单</button>
                  <button @click="gotoUploadImg(item)">影像管理</button>
                  <button
                    v-if="item.invoiceType == '3'"
                    @click="elecVoice(item)"
                  >
                    电子发票
                  </button>
                  <button
                    v-if="item.oneClickRenewalFlag == '1'"
                    @click="onebuttonRenewal(item)"
                  >
                    一键续保
                  </button>
                  <!-- <button @click="gotoUploadImg(item)">保单批改</button> -->

                </template>

                <!-- 已失效31 -->
                <template
                  v-else-if="item.orderStatus == '31'"
                  class="center2"
                >
                  <button
                    class="verificationBtn"
                    @click="copyPolicy(item)"
                  >
                    复制投保单
                  </button>
                </template>

                <!-- 已过期 -->
                <template
                  v-else-if="item.orderStatus == '50'"
                  class="center2"
                >
                  <button
                    class="verificationBtn"
                    @click="copyPolicy(item)"
                  >
                    复制投保单
                  </button>
                </template>

                <!-- 核保失败 -->
                <template
                  v-else
                  class="center2"
                >
                  <button
                    class="verificationBtn"
                    @click="copyPolicy(item)"
                  >
                    复制投保单
                  </button>
                </template>
              </div>
              <div
                class="remark"
                @click="changeHintInfo(item)"
              >
                <div class="hintInfo">
                  <span>备注</span>
                  <div class="iconArrow">
                    <van-icon name="arrow-down" />
                  </div>
                </div>
              </div>
              <!-- </template> -->
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
    <van-dialog
      v-model="hintShow"
      :showCancelButton="true"
      confirmButtonText="保存"
      @confirm="hintConfirm"
    >
      <template>
        <div class="hintsuper">
          <div id="hintfather">
            <textarea
              maxlength="120"
              cols="30"
              rows="10"
              placeholder="请输入备注信息"
              v-model="inputMessage"
              v-on:input="inputText"
            ></textarea>
            <h3>{{ inputNumber }}/120</h3>
          </div>
        </div>
      </template>
    </van-dialog>
    <!-- 申请验证码 -->
    <div class="verificationShow">
      <van-overlay :show="verificationShow">
        <div class="wrapper">
          <div class="block">
            <div class="closeBtn">
              <img
                @click="closeBtn"
                src="../../../assets/order/orderList/close.png"
                alt
              />
            </div>
            <div class="title">
              <h3>请与投保人/被保人联系获取 短信验证码并录入</h3>
            </div>
            <div class="send">
              <van-cell-group>
                <van-field
                  v-model="verificationValue"
                  placeholder="请输入验证码"
                />
              </van-cell-group>
              <div class="getCode">
                <van-button
                  slot="button"
                  :class="[!disabled ? 'signature-code-button' : '']"
                  :disabled="disabled"
                  @click="getCode"
                >
                  {{ disabled ? `重新发送 ${resendTime}` : "获取验证码" }}
                </van-button>
              </div>
            </div>
            <div
              @click="preservationBtn"
              class="preservation"
            >保存</div>
          </div>
        </div>
      </van-overlay>
    </div>
    <policyDownloadwarranty
      :show="showPolicyDownload"
      title="点击下方的下载按钮，可在线转发电子保单，输入邮箱地址或移动电话，直接把电子保单发送至客户邮箱或者手机中"
      :personInfo="personInfo"
      :businessPolicyNo="businessPolicyNo"
      :trafficPolicyNo="trafficPolicyNo"
      :wealthPolicyNo="wealthPolicyNo"
      @downLoadPolicyEvent="downLoadPolicy"
      @hidePolicyDownload="hidePolicyDownload"
      @sendElectronicPolicyEvent="doSendElectronicPolicy"
      @PolicyNoVal="PolicyNoVal"
    />
    <!-- 下载电子发票 -->
    <policyDownload
      :show="showPolicyDownload1"
      title="点击下方的下载按钮，可在线转发电子发票，输入邮箱地址或移动电话，直接把电子发票发送至客户邮箱或者手机中"
      :personInfo="personInfo"
      :businessPolicyNo="businessPolicyNo"
      :trafficPolicyNo="trafficPolicyNo"
      :wealthPolicyNo="wealthPolicyNo"
      @downLoadPolicyEvent="downLoadPolicy1"
      @hidePolicyDownload="hidePolicyDownload"
      @sendElectronicPolicyEvent="doSendElectronicPolicy1"
      @PolicyNoVal="PolicyNoVal"
    />
    <invoiceApplication
      :show="showInvoiceApplication"
      :type="invoicetype"
      @closeTipEvent="closeTip"
    />
    <!-- 核保查询弹出框 -->
    <van-popup v-model="underWritingShow">
      <div class="containerShow">
        <img
          class="img"
          @click="closeImg"
          src="../../../assets/order/orderList/close.png"
          alt
        />

        <div
          v-for="(item, index) in underWritingList"
          :key="index"
        >
          <div class="underWriting">
            <div>核保状态</div>
            <div class="right_30">{{ item.certiStatus }}</div>
          </div>
          <div class="underWriting">
            <div>核保人</div>
            <div class="right_30">{{ item.undwrtName }}</div>
          </div>
          <div class="underWriting">
            <div>核保时间</div>
            <div class="right_30">{{ item.undwrtDate }}</div>
          </div>
          <div class="underWriting">
            <div>核保意见</div>
            <div class="right_30">{{ item.undwrtText }}</div>
          </div>
          <div class="underWriting">
            <div>核保级别</div>
            <div class="right_30">
              {{ item.uwLevel != "null" ? item.uwLevel : "" }}
            </div>
          </div>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script>
import Vue from "vue";
import policyDownload from "../elePolicy/policyDownload";
import policyDownloadwarranty from "../elePolicy/policyDownload1";
import invoiceApplication from "../elePolicy/invoiceApplication";
import {
  SwipeCell,
  Card,
  Button,
  Dialog,
  Sku,
  List,
  PullRefresh,
  Toast,
  Overlay
} from "vant";
import {
  getOrderList,
  getoddNumb,
  getDetails,
  getPolicyInfomation,
  deleteOrder,
  imageUpdate,
  checkAuthorization,
  launchAuthorization,
  electronicPolicy,
  sendElectronicPolicy,
  sendElectronicPolicy1,
  getRemark,
  getVerifyCode,
  updateVerifyCode,
  toInvoiceLinkQuery,
  eInvoiceApplication,
  oneClickRenewal
} from "@src/http/module/order.js";
import { getPayNo, submitAudit } from "@src/http/module/vehicle.js";
Vue.use(SwipeCell);
Vue.use(Card);
Vue.use(Button);
Vue.use(Sku);
Vue.use(List);
Vue.use(PullRefresh);

export default {
  name: "orderListCard",
  props: {
    status: Number,
    searchInput: Object,
    vehiceFlag: String,
    judgeFlag: Boolean
  },
  // props: [
  //   // 'ordersListObj', //订单列表
  //   "insuranceType", //险种类型
  //   "status", //订单状态
  //   "searchInput",
  //   "vehiceFlag",
  //   "judgeFlag"
  // ],
  components: {
    [policyDownload.name]: policyDownload,
    [Overlay.name]: Overlay,
    [invoiceApplication.name]: invoiceApplication,
    policyDownloadwarranty
  },
  data() {
    return {
      container: 0,
      myScrollValue: 0,
      underWritingShow: false,
      underWritingList: [],
      // radioObj: {
      //   trafficPolicyNo:"",
      //   businessPolicyNo:""
      // },
      PolicyNoValue: "",
      trafficPolicyNo: "",
      businessPolicyNo: "",
      wealthPolicyNo: "",
      payCheckNoFlag: true,
      disabled: false,
      resendTime: "",
      interval: "",
      showInvoiceApplication: false,
      showTip: false,
      invoicetype: "success",
      personInfo: {
        mobileNo: "",
        email: ""
      },
      verificationId: "",
      verificationValue: "",
      verificationShow: false,
      orderIdInfo: "",
      typeFlag: false,
      isLoading: false,
      finished: false, //控制在页面往下移动到底部时是否调用接口获取数据
      loading: false, //控制上拉加载的加载动画
      orderInfo: {},
      hintShow: false,
      inputMessage: "",
      inputNumber: 0,
      showPolicyDownload: false,
      showPolicyDownload1: false,
      // 查询车险
      form: {
        createTimeEnd: "", //订单创建时间-结束
        createTimeStart: "", //订单创建时间-起始
        dateQueryFlag: "", //时间查询标记-1:查一周内 2:查1个月内 3:查3个月内 4:查6个月内 0:查全部
        maxPremium: "", //最高保费-元
        minPremium: "", //最低保费-元
        orderStatusFlag: "", //订单状态查询标记,1待处理 2待支付 3已支付 0全部
        pageNo: 1, //页码
        pageSize: 20, //每页大小
        premiumQueryFlag: "", //保费查询标记-1:查0-2000 2:查2000-5000 3:查5000以上 0:查全部
        queryParam: "", //搜索条件-支持模糊查询
        systemSource: "", //出单来源
        userCode: "" //员工代码
      },
      insureDatalist: [],
      insureDatalistValue: [],
      searchInputValue: "",
      licenseNovalue: "",
      myScroll: ""
    };
  },
  updated() {
    this.myScroll.scrollTop = sessionStorage.getItem("myScrollValue");
  },
  created() {
    this.$nextTick(() => {
      if (this.$route.query.insureOrderDetailFlag) {
        localStorage.removeItem("tePosiiton");
        this.$store.commit("orderList/resetbackInfoObj");
        let obj = {
          id: this.$route.query.id,
          status: this.$route.query.orderStatus,
          payExpireFlag: this.$route.query.payExpireFlag,
          oneClickRenewalFlag: this.$route.query.oneClickRenewalFlag,
          vehicleTypeFlag: this.$route.query.vehicleTypeFlag
        };
        this.$store.commit("orderList/setbackInfoObj", obj);
        sessionStorage.setItem("typeFlag", this.status);
        this.typeFlag = true;
        this.$emit("typeFlagevent", this.typeFlag);
        this.$router.push({
          name: "insureOrderDetail",
          query: obj
        });
      }
      if (this.judgeFlag == false) {
        this.typeFlag = false;
      } else {
        this.typeFlag = true;
      }
    });
  },
  mounted() {
    this.myScroll = document.getElementById("cardList");
    console.log(this.myScroll);
    this.myScroll.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy() {
    sessionStorage.setItem("myScrollValue", this.myScrollValue);
  },
  activated() {
    //tip关闭
    this.insureDatalist.map(item => {
      item.id = false;
    });
  },
  methods: {
    handleScroll() {
      this.myScrollValue = this.myScroll.scrollTop;
    },
    // 子传父， 保单号
    PolicyNoVal(value) {
      this.PolicyNoValue = value;
    },
    reset() {
      this.trafficPolicyNo = "";
      this.businessPolicyNo = "";
      this.wealthPolicyNo = "";
    },
    //  核保查询弹框关闭
    closeImg() {
      this.underWritingShow = false;
    },
    //   备注
    changeHintInfo(item) {
      this.orderIdInfo = item.id;
      let params = {
        orderId: item.id,
        userCode: localStorage.getItem("userCode")
      };
      getDetails(params).then(res => {
        this.inputMessage = res.data.vehicleOrder.remarkValue;
        this.hintShow = !this.hintShow;
      });
    },
    inputText: function() {
      this.inputNumber = this.inputMessage.length;
    },
    hintConfirm: function() {
      //TODO 提交备注
      let params = {
        id: this.orderIdInfo,
        remarkValue: this.inputMessage
      };
      getRemark(params).then(res => {
        // this.getdataList();
      });
    },
    getInsureDatalist() {
      this.insureDatalistValue = [];
    },
    // 验证码申请
    verification(orderInfo) {
      this.verificationValue = "";
      this.verificationShow = true;
      this.verificationId = orderInfo.id;
      clearInterval(this.interval);
      this.disabled = false;
    },
    //  点击获取验证码
    getCode() {
      this.verificationValue = "";
      let params = {
        orderId: this.verificationId
      };
      getVerifyCode(params).then(res => {
        if (res.code == "1") {
          Toast(res.data.errorMessage);
          this.disabled = true;
          this.resendTime = 299;
          this.interval = setInterval(() => {
            if (this.resendTime > 0) {
              this.resendTime -= 1;
            } else {
              this.resendTime = 300;
              clearInterval(this.interval);
              this.disabled = false;
            }
          }, 1000);
        } else {
          Toast(res.msg);
        }
      });
    },
    // 验证码保存
    preservationBtn() {
      let params = {
        orderId: this.verificationId,
        verifyCode: this.verificationValue
      };
      updateVerifyCode(params).then(res => {
        setTimeout(() => {
          this.verificationShow = false;
          Toast(res.msg);
        }, 200);
      });
    },
    closeBtn() {
      this.verificationShow = false;
    },
    // 上拉加载
    onUpFresh() {
      // setTimeout(() => {
      if (this.isLoading) {
        return false;
      }
      this.form.orderStatusFlag = this.status;
      if (localStorage.getItem("userCode")) {
        this.form.userCode = localStorage.getItem("userCode");
      }
      this.form.orderStatusFlag = this.status;
      this.form.queryParam = sessionStorage.getItem("searchInputValue");
      this.form.dateQueryFlag = this.searchInput.dataType;
      this.form.createTimeStart = this.searchInput.startDay;
      this.form.createTimeEnd = this.searchInput.endDay;
      this.form.premiumQueryFlag = this.searchInput.premiumMsg;
      this.form.minPremium = this.searchInput.premiumMin;
      this.form.maxPremium = this.searchInput.premiumMax;
      this.form.systemSource = this.searchInput.singleSourceMsg;
      this.form.orderStatus = this.searchInput.screenVal;
      getOrderList(this.form)
        .then(res => {
          if (res.code === "1") {
            this.insureDatalist = this.insureDatalist.concat(
              res.data.orderVOList
            );
            /** NOTE @deprecated 2020年07月01日15:27:57 */
            if (res.data.payCheckNoFlag == "0") {
              this.payCheckNoFlag = false;
            } else {
              this.payCheckNoFlag = true;
            }
            // var allArr = [];
            // for (var i = 0; i < this.insureDatalist.length; i++) {
            //   var flag = true;
            //   for (var j = 0; j < allArr.length; j++) {
            //     if (this.insureDatalist[i].id == allArr[j].id) {
            //       flag = false;
            //     }
            //   }
            //   if (flag) {
            //     allArr.push(this.insureDatalist[i]);
            //   }
            // }
            // this.insureDatalist = allArr;

            let person = this.insureDatalist;
            let obj = {};

            let peon = person.reduce((cur, next) => {
              obj[next.id] ? "" : (obj[next.id] = true && cur.push(next));
              return cur;
            }, []); //设置cur默认类型为数组，并且初始值为空的数组
            this.insureDatalist = peon;
            // console.log(peon, "hewewei");
            // console.log(this.insureDatalist, "上拉");
            this.loading = false;
            if (
              res.data.orderVOList &&
              res.data.orderVOList.length < this.form.pageSize
            ) {
              this.finished = true;
            }
            if (!res.data.orderVOList) {
              this.finished = true;
            }
            if (this.form.pageNo == res.data.pages) {
              this.finished = true;
            }
            this.form.pageNo++;
          } else {
            this.handle();
          }
        })
        .catch(err => {
          this.handle();
        });
      // }, 100);
    },
    // 下拉刷新
    onDownFresh() {
      // setTimeout(() => {
      this.getdataList();
      // }, 200);
    },
    handle() {
      this.loading = false;
      this.finished = true;
    },
    //初始化 暂不要和下拉加载封装一起 单独写开
    getdataList() {
      this.form.pageNo = 1;
      this.finished = false;
      if (this.loading) {
        return false;
      }
      if (localStorage.getItem("userCode")) {
        this.form.userCode = localStorage.getItem("userCode");
      }
      this.form.orderStatusFlag = sessionStorage.getItem("typeFlag");
      this.form.queryParam = sessionStorage.getItem("searchInputValue");
      this.form.dateQueryFlag = this.searchInput.dataType;
      this.form.createTimeStart = this.searchInput.startDay;
      this.form.createTimeEnd = this.searchInput.endDay;
      this.form.premiumQueryFlag = this.searchInput.premiumMsg;
      this.form.minPremium = this.searchInput.premiumMin;
      this.form.maxPremium = this.searchInput.premiumMax;
      this.form.systemSource = this.searchInput.singleSourceMsg;
      this.form.orderStatus = this.searchInput.screenVal;
      let data = this.form;
      this.insureDatalist = [];
      getOrderList(data)
        .then(res => {
          if (res.code === "1") {
            /** NOTE @deprecated 2020年07月01日15:27:57 */
            if (res.data.payCheckNoFlag == "0") {
              this.payCheckNoFlag = false;
            } else {
              this.payCheckNoFlag = true;
            }
            this.loading = false;
            if (this.insureDatalist) {
              this.insureDatalist = [];
            }
            this.insureDatalist = this.insureDatalist.concat(
              res.data.orderVOList
            );
            // console.log(this.insureDatalist, "下拉");
            if (
              res.data.orderVOList &&
              res.data.orderVOList.length < this.form.pageSize
            ) {
              this.finished = true;
            }
            if (!res.data.orderVOList) {
              this.finished = true;
            }
            if (this.form.pageNo == res.data.pages) {
              this.finished = true;
            }
          } else {
            this.handle();
          }
        })
        .catch(err => {
          this.handle();
        });
      this.isLoading = false; //关闭下拉刷新效果
      this.finished = false;
    },
    // 滑动滑块内容关闭时的行为
    beforeClose({ position, instance, name }) {
      var arrObj = name.split("*");
      var paramsData = {
        id: arrObj[0],
        orderStatus: arrObj[1]
      };
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
              if (arrObj[1] != "10") {
                Toast("只有暂存状态才能删除");
              } else {
                deleteOrder(paramsData)
                  .then(res => {
                    if (res.code === "1") {
                      Toast({
                        forbidClick: true,
                        message: "删除成功",
                        type: "success"
                      });
                      this.getdataList();
                    }
                  })
                  .catch(err => {});
              }
            })
            .catch(err => {});
      }
    },
    // 跳转到详情页面
    redirectOrderDetailPage(orderInfo) {
      localStorage.removeItem("tePosiiton");
      this.$store.commit("orderList/resetbackInfoObj");
      let obj = {
        id: orderInfo.id,
        status: orderInfo.orderStatus,
        payExpireFlag: orderInfo.payExpireFlag,
        oneClickRenewalFlag: orderInfo.oneClickRenewalFlag,
        vehicleTypeFlag: orderInfo.vehicleTypeFlag
      };
      this.$store.commit("orderList/setbackInfoObj", obj);
      sessionStorage.setItem("typeFlag", this.status);
      this.typeFlag = true;
      this.$emit("typeFlagevent", this.typeFlag);
      this.$router.push({
        name: "insureOrderDetail",
        query: obj
      });
    },
    // 跳转到影像上传页面-待改造跳转影像提供H5页面
    async gotoUploadImg(insureNo) {
      let dateValue = new Date();
      let year = String(dateValue.getFullYear());
      let mounth = String(dateValue.getMonth());
      let day = String(dateValue.getDay());
      if (year.length == 1) {
        year = "0" + year;
      }
      if (day.length == 1) {
        day = "0" + day;
      }
      let dataParmas = year + mounth + day;
      let imgType = "";
      let bussNo = "";
      if (insureNo.businessApplicationNo) {
        bussNo = insureNo.businessApplicationNo;
      }
      if (!insureNo.businessApplicationNo && insureNo.trafficApplicationNo) {
        bussNo = insureNo.trafficApplicationNo;
      }
      if (this.vehiceFlag == 2) {
        imgType = "UW_D";
        let params = {
          userCode: localStorage.getItem("userCode"),
          comCode: localStorage.getItem("comCode"),
          bussNo,
          imgType,
          busiDate: dataParmas,
          flag: this.$getIP().indexOf("net") >= 0 ? "1" : "0"
        };
        const res = await imageUpdate(params);
        if (res.code !== "1") {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        } else {
          this.$native.openWebView({
            url: res.data.url,
            type: "0",
            titleBarTag: "1",
            titleBar: "影像管理"
          });
          // window.location.href = res.data.url;
        }
      }
    },
    // 跳转到支付页面
    gotoPayPage(orderInfo) {
      //跳转支付之前先申请支付号，再跳转
      this.varifyCodeApply(orderInfo);
    },
    //  一键续保
    onebuttonRenewal(orderInfo) {
      let params = {
        orderId: orderInfo.id
      };
      oneClickRenewal(params).then(res => {
        if (res.code == "1") {
          this.$router.push({
            name: "vehicle",
            params: { orderId: res.data.orderId },
            query: {
              vehicleType: orderInfo.vehicleTypeFlag
            }
          });
        } else {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        }
      });
    },
    // 提交核保
    submitAudit(orderInfo) {
      if (orderInfo) {
        Dialog.confirm({
          title: "",
          message: "是否提交核保？"
        })
          .then(() => {
            const orderId = orderInfo.id;
            const params = {
              orderId,
              userCode: localStorage.getItem("userCode")
            };
            submitAudit(params).then(res => {
              if (res.code == "1") {
                if (res.data.uwType != "3") {
                  Toast({
                    message: "提交核保成功",
                    type: "success",
                    position: "bottom"
                  });
                } else {
                  // 获取支付号
                  this.varifyCodeApply(orderInfo);
                }
              } else {
                setTimeout(() => {
                  Toast(res.msg);
                }, 500);
              }
            });
          })
          .catch(() => {
            // on cancel
          });
      }
    },
    //核保查询
    getUnderWriting(orderInfo) {
      if (orderInfo) {
        // var applicationNo = "";
        // if (orderInfo.businessApplicationNo && orderInfo.trafficApplicationNo) {
        //   applicationNo = orderInfo.businessApplicationNo;
        // } else {
        //   if (orderInfo.businessApplicationNo) {
        //     applicationNo = orderInfo.businessApplicationNo;
        //   }
        //   if (orderInfo.trafficApplicationNo) {
        //     applicationNo = orderInfo.trafficApplicationNo;
        //   }
        // }
        // var applicationNo = orderInfo.businessApplicationNo;
        const params = {
          orderId: orderInfo.id,
          userCode: localStorage.getItem("userCode")
        };
        getPolicyInfomation(params).then(res => {
          if (res.code == "1") {
            this.underWritingList = res.data.certiStatusList;
            this.underWritingShow = true;
            // Dialog.alert({
            //   message: res.data.certiStatusList[0].certiStatus
            // }).then(() => {});
          } else {
            Dialog.alert({
              message: res.msg
            }).then(() => {});
          }
        });
      }
    },
    //继续投保
    continueInsurance(orderInfo) {
      if (!orderInfo) {
        return;
      }
      const orderId = orderInfo.id;

      if (orderId) {
        this.$router.push({
          name: "vehicle",
          params: {
            orderId: orderId
          },
          query: {
            vehicleType: orderInfo.vehicleTypeFlag
          }
        });
      }
    },
    //验证码查询- 获取支付号
    varifyCodeApply(orderInfo) {
      if (orderInfo) {
        const orderId = orderInfo.id;
        let bussNo = "";
        if (orderInfo.businessApplicationNo) {
          bussNo = orderInfo.businessApplicationNo;
        }
        if (
          !orderInfo.businessApplicationNo &&
          orderInfo.trafficApplicationNo
        ) {
          bussNo = orderInfo.trafficApplicationNo;
        }
        //支付方式
        // const payType = "pos";
        //注销支付号标志，1-注销，非1值为支付申请
        // const cancelPaymentCodeFlag = "2";
        const params = {
          // cancelPaymentCodeFlag,
          orderId,
          // payType,
          userCode: localStorage.getItem("userCode")
        };
        getPayNo(params).then(res => {
          if (res.code != "1") {
            setTimeout(() => {
              Toast(res.msg);
            }, 500);
          } else {
            this.$router.push({
              name: "paymentIndex",
              params: {
                orderId: orderInfo.id, //订单号
                proposalNo: bussNo, //投保单号
                totalPremium: res.data.payAmount, // 保费
                policyHolder: orderInfo.insurantName, //投保人姓名
                paymentNo: res.data.payApplyNo,
                checkNo: res.data.payVerificationCode
              }
            });
          }
        });
      }
    },
    // 客户信息
    gotoCustomerInfo(orderInfo) {
      if (!orderInfo.custNo) {
        Toast("当前没有客户信息");
      } else {
        if (orderInfo.custType == "01") {
          orderInfo.custType = "1";
        }
        if (orderInfo.custType == "02") {
          orderInfo.custType = "2";
        }
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
    //  分享给客户
    sharePage(objInfo) {
      let userCode = localStorage.getItem("userCode");
      if (this.licenseNovalue == null || this.licenseNovalue == "") {
        this.licenseNovalue = "未上牌";
      }
      let tempUrl =
        this.$getIP() +
        "/order/orderList/authorization?policyNo=" +
        objInfo.policyNo +
        "&" +
        "authUuid=" +
        objInfo.authUuid +
        "&" +
        "authType=" +
        objInfo.authType +
        "&" +
        "userCode=" +
        userCode;

      let descValue = "【" + this.licenseNovalue + "】电子保单";
      let jsonObject = {
        sharePlatform: ["0"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
        shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
        title: descValue, //分享标题
        imgURL:
          "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
        image: "", //分享图片（base64编码）, 与imgURL二选一
        url: tempUrl, //分享链接
        desc: "尊敬的客户，您的电子保单已生成。" //分享描述
      };
      this.$native.share(jsonObject, data => {});
    },
    //  电子保单
    elecPolicy(item) {
      this.PolicyNoValue = "";
      this.personInfo.mobileNo = item.applicantPhone;
      this.personInfo.email = item.applicantEmail;
      //  保单号
      this.businessPolicyNo = item.businessPolicyNo;
      this.trafficPolicyNo = item.trafficPolicyNo;
      this.wealthPolicyNo = item.wealthPolicyNo;

      if (item.businessPolicyNo && item.trafficPolicyNo) {
        this.businessPolicyNo = item.businessPolicyNo;
        this.trafficPolicyNo = item.trafficPolicyNo;
        this.personInfo.policyNo = item.businessPolicyNo;
      } else {
        if (item.businessPolicyNo) {
          this.personInfo.policyNo = item.businessPolicyNo;
        }
        if (item.trafficPolicyNo) {
          this.personInfo.policyNo = item.trafficPolicyNo;
        }
      }
      // this.personInfo.policyNo = item.policyNo;
      this.showPolicyDownload = true;
      this.licenseNovalue = item.licenseNo;
    },
    //  点击下载电子保单校验
    downLoadPolicy() {
      if (this.PolicyNoValue) {
        this.personInfo.policyNo = this.PolicyNoValue;
      }
      var params = {
        authType: "100",
        policyNo: this.personInfo.policyNo,
        userCode: localStorage.getItem("userCode")
      };
      //  校验授权
      checkAuthorization(params).then(res => {
        if (res.code === "1") {
          if (res.data.isAuth == "1") {
            // this.electronicPolicyDownload();
            // localStorage.setItem("downLoadUrl", res.data.policyS3path);
            // localStorage.setItem("signS3pathUrl", res.data.signS3path);
            this.$router.push({
              name: "elePolicyPreview1",
              query: {
                businessPolicyNo: this.businessPolicyNo,
                wealthPolicyNo: this.wealthPolicyNo,
                trafficPolicyNo: this.trafficPolicyNo
              },
              title: "电子保单"
            });
          } else {
            Dialog.confirm({
              title: "",
              message: "是否发起授权"
            })
              .then(() => {
                //  发起授权
                launchAuthorization(params).then(res => {
                  if (res.code != "1") {
                    setTimeout(() => {
                      Toast("res.msg");
                    }, 500);
                  } else {
                    let objInfo = {
                      policyNo: res.data.policyNo,
                      authUuid: res.data.authUuid,
                      authType: res.data.authType
                    };
                    this.sharePage(objInfo);
                  }
                });
              })
              .catch(() => {});
          }
        } else {
          setTimeout(() => {
            Toast(res.msg);
          }, 500);
        }
      });
    },
    // 关闭电子保单下载提示框
    hidePolicyDownload(value) {
      this.showPolicyDownload = value;
      this.showPolicyDownload1 = value;
    },
    // 发送电子保单
    doSendElectronicPolicy(type, user) {
      let userInfo = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let obj = {
        policyNo: user.policyNo,
        type: type,
        userCode: userInfo.userCode,
        comCode: userInfo.comCode
      };
      switch (type) {
        case "2":
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
            }
          });
          break;
        case "1":
          obj.email = user.email;
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
            }
          });
          break;
      }
    },
    // 下载电子保单接口
    electronicPolicyDownload() {
      if (this.PolicyNoValue) {
        this.personInfo.policyNo = this.PolicyNoValue;
      }
      electronicPolicy({
        policyNo: this.personInfo.policyNo
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
          if (res.data == "0") {
            Toast({
              forbidClick: true,
              message: res.msg,
              // message: "请您10分钟后再次进行下载",
              type: "fail",
              duration: 3000
            });
          } else {
            if (this.$isNull(res.data.policyS3path)) {
              Dialog.alert({
                message: res.data.responseHead.appMessage || ""
              }).then(() => {});
              return;
            }
            localStorage.setItem("downLoadUrl", res.data.policyS3path);
            localStorage.setItem("signS3pathUrl", res.data.signS3path);
            this.$router.push({
              name: "elePolicyPreview",
              query: { downLoadUrl: res.data.policyS3path },
              title: "电子保单"
            });
          }
          // Toast.allowMultiple();
          // Toast({
          //   forbidClick: true,
          //   message: res.msg,
          //   type: "success"
          // });
          // setTimeout(() => {
          //   Toast.clear();
          // }, 2000);
        }
      });
    },
    //  点击下载电子保单校验
    downLoadPolicy1() {
      if (this.PolicyNoValue) {
        this.personInfo.policyNo = this.PolicyNoValue;
      }
      this.electronicPolicyDownload1();
      // var params = {
      //   authType: "200",
      //   policyNo: this.personInfo.policyNo,
      //   userCode: localStorage.getItem("userCode")
      // };
      //  校验授权
      // checkAuthorization(params).then(res => {
      //   if (res.code === "1") {
      //     if (res.data.isAuth == "1") {
      //       this.electronicPolicyDownload1();
      //     } else {
      //       Dialog.confirm({
      //         title: "",
      //         message: "是否发起授权"
      //       })
      //         .then(() => {
      //           //  发起授权
      //           launchAuthorization(params).then(res => {
      //             if (res.code != "1") {
      //               setTimeout(() => {
      //                 Toast("res.msg");
      //               }, 500);
      //             } else {
      //               let objInfo = {
      //                 policyNo: res.data.policyNo,
      //                 authUuid: res.data.authUuid,
      //                 authType: res.data.authType
      //               };
      //               this.sharePage1(objInfo);
      //             }
      //           });
      //         })
      //         .catch(() => {});
      //     }
      //   } else {
      //     setTimeout(() => {
      //       Toast(res.msg);
      //     }, 500);
      //   }
      // });
    },
    // 下载电子发票
    electronicPolicyDownload1() {
      if (this.PolicyNoValue) {
        this.personInfo.policyNo = this.PolicyNoValue;
      }
      let obj = {
        mobileNo: this.personInfo.mobileNo,
        policyNo: this.personInfo.policyNo
      };
      this.doEinvoiceInquiry(obj).then(res => {
        // console.log("返回数据============" + res);
        if (res.code == "1" && res.data) {
          if (
            res.data.responseBody &&
            res.data.responseBody.invoiceLinkList.length > 0
          ) {
            // let param = {
            //   url: res.data.responseBody.invoiceLinkList[0].shortLink,
            //   type: "0",
            //   titleBarTag: "1",
            //   shareUrl: res.data.responseBody.invoiceLinkList[0].shortLink, //TODO suncao 影像管理
            //   titleBar: "电子发票预览",
            //   sharePlatform: ["0", "1"],
            //   shareType: "url",
            //   title: "电子发票预览",
            //   imgURL:
            //     "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
            //   image: "", //分享图片（base64编码）, 与imgURL二选一
            //   desc: "尊敬的客户，您的电子发票已生成，可以在线预览电子发票"
            // };
            // console.log("请求壳子的参数===", JSON.stringify(param));
            // this.$native.openWebView(param, data => {});
            let shortLink = res.data.responseBody.invoiceLinkList[0].shortLink;
            let url = "";
            if (shortLink.indexOf("electronicsdmz") >= 0) {
              url =
                this.$getIP() +
                "/order/elePolicy/invoicePreview?shortLink=" +
                encodeURI(res.data.responseBody.invoiceLinkList[0].shortLink);
            } else {
              url = shortLink;
            }
            // let url =
            //   this.$getIP() +
            //   "/order/elePolicy/invoicePreview?shortLink=" +
            //   res.data.responseBody.invoiceLinkList[0].shortLink;
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
            this.$native.openWebView(param, data => {});
          } else {
            Dialog.alert({
              message: "您目前暂无电子发票，请稍后查看"
            }).then(() => {});
          }
        } else {
          this.showInvoiceApplication = true;
          // Dialog.alert({
          //   message: res.msg
          // }).then(() => { });
        }
      });
    },
    doSendElectronicPolicy1(type, user) {
      let userInfo = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let obj = {
        policyNo: user.policyNo,
        type: type,
        userCode: userInfo.userCode,
        comCode: userInfo.comCode
      };
      switch (type) {
        case "2":
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
    // 发送电子保单到手机或者邮箱接口
    async sendElePolicy(obj) {
      return await sendElectronicPolicy(obj);
    },
    // 发送电子发票到手机或者邮箱接口
    async sendElePolicy1(obj) {
      return await sendElectronicPolicy1(obj);
    },
    //  电子发票
    elecVoice(item) {
      this.PolicyNoValue = "";
      this.personInfo.mobileNo = item.applicantPhone;
      this.personInfo.email = item.applicantEmail;
      if (
        item.businessPolicyNo &&
        item.trafficPolicyNo &&
        item.wealthPolicyNo
      ) {
        this.businessPolicyNo = item.businessPolicyNo;
        this.trafficPolicyNo = item.trafficPolicyNo;
        this.wealthPolicyNo = item.wealthPolicyNo;
        this.personInfo.policyNo = item.businessPolicyNo;
      } else {
        if (item.businessPolicyNo && item.trafficPolicyNo) {
          this.businessPolicyNo = item.businessPolicyNo;
          this.trafficPolicyNo = item.trafficPolicyNo;
          this.personInfo.policyNo = item.businessPolicyNo;
        }
        if (item.businessPolicyNo && item.wealthPolicyNo) {
          this.businessPolicyNo = item.businessPolicyNo;
          this.wealthPolicyNo = item.wealthPolicyNo;
          this.personInfo.policyNo = item.businessPolicyNo;
        }
        if (item.wealthPolicyNo && item.wealthPolicyNo) {
          this.wealthPolicyNo = item.wealthPolicyNo;
          this.wealthPolicyNo = item.wealthPolicyNo;
          this.personInfo.policyNo = item.businessPolicyNo;
        }
        if (item.wealthPolicyNo) {
          this.personInfo.policyNo = item.wealthPolicyNo;
        }
        if (item.businessPolicyNo) {
          this.personInfo.policyNo = item.businessPolicyNo;
        }
        if (item.trafficPolicyNo) {
          this.personInfo.policyNo = item.trafficPolicyNo;
        }
      }
      // this.personInfo.policyNo = item.policyNo;
      this.personInfo.insureNo = item.insureNo;

      this.showPolicyDownload1 = true;
    },
    // 查询电子发票
    async doEinvoiceInquiry(obj) {
      return await toInvoiceLinkQuery(obj);
    },
    // 申请电子发票
    async doEInvoiceApplication(obj) {
      return await eInvoiceApplication(obj);
    },
    //  一键续保
    renewPolicy(orderInfo) {
      //先申请 新的订单ID，再跳转到出单流程
      this.copyPolicy(orderInfo);
    },
    //保单复制
    copyPolicy(orderInfo) {
      if (orderInfo) {
        const orderId = orderInfo.id;
        const params = {
          orderId,
          userCode: localStorage.getItem("userCode")
        };
        //复制投保单
        getoddNumb(params).then(res => {
          if (res.code == 1) {
            this.$router.push({
              name: "vehicle",
              query: {
                orderId: res.data.orderId,
                status: this.orderStatus,
                vehicleType: orderInfo.vehicleTypeFlag
              }
            });
          } else {
            setTimeout(() => {
              Toast(res.msg);
            }, 500);
          }
        });
      }
    },
    // 关闭发票申请提示框
    closeTip(value) {
      this.showInvoiceApplication = value;
    }
  },
  watch: {
    // myScroll (v) {
    //   console.log(v);
    // }
    //   insureDatalist: {
    //     handler(newValue, oldValue) {
    //       console.log(newValue, "newValue==>986");
    //       console.log(oldValue, "oldValue==>987");
    //       // newValue.map(item=>{
    //       //   this.XXX.push(item)
    //       //   })
    //       this.insureDatalistValue = [];
    //       this.insureDatalistValue = newValue;
    //       // this.$forceUpdate();
    //       // console.log(this.insureDatalist, "111");
    //     },
    //     deep: true
    //   }
  }
};
</script>

<style lang="less">
.van-popup {
  border-radius: 8px;
}

.containerShow {
  position: relative;
  .img {
    display: block;
    position: absolute;
    top: 10px;
    right: 25px;
  }
  padding: 35px;
  .underWriting {
    display: flex;
    div {
      flex: 1;
      width: 120px;
      font-size: 13px;
      line-height: 25px;
    }
    .right_30 {
      flex: 2;
      text-align: right;
    }
  }
}
.verificationShow {
  .wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }
  .block {
    width: 315px;
    height: 276px;
    background-color: #fff;
    border-radius: 10px;
    .closeBtn {
      padding: 10px 10px 0 0;
      float: right;
      margin-right: 10px;
    }
    .title {
      h3 {
        padding: 35px 57px;
        text-align: center;
        font-size: 16px;
        font-weight: 500;
      }
    }
    .send {
      position: relative;
      margin-bottom: 30px;
      .getCode {
        position: absolute;
        top: 0;
        right: 20px;
        font-size: 14px;
        color: rgba(59, 138, 254, 1);
      }
    }
    .preservation {
      width: 265px;
      height: 46px;
      background: rgba(255, 255, 255, 1);
      border-radius: 23px;
      border: 1px solid rgba(59, 138, 254, 1) !important;
      text-align: center;
      line-height: 46px;
      margin-left: 25px;
      color: #3b8afe;
      font-size: 16px;
    }
  }
}
.signature-code-button {
  color: rgba(59, 138, 254, 1);
}
.van-swipe-cell__wrapper {
  padding: 0 3px 0 1px;
}
.van-card__header,
.van-card__footer {
  // padding: 0 2px 0 10px;
}
.orderCard {
  // padding: 0 15px;
  * {
    box-sizing: border-box;
    // height: calc(100vh - 200px);
    // overflow: scroll;
    // padding-top: 20px;
  }
  // padding: 14px 15px;
  .orderInfo {
    // height: 189px;
    background: rgba(255, 255, 255, 1);
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    // padding: 0 13px;
    box-sizing: border-box;
    margin: 0 auto;
    padding: 14px 13px 16px 13px !important;
    .orderInfoTop {
      height: 21px;
      font-size: 15px;
      font-weight: 600;
      font-size: 15px;
      font-family: PingFangSC-Medium, PingFang SC;
      line-height: 21px;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 0px;
      margin-bottom: 14px;
      .left {
        width: 70%;
        height: 21px;
        display: flex;
        flex-direction: row;
        align-items: center;
        .titleIcon {
          width: 25px;
          height: 25px;
          background: none;
          border-radius: 22px;
          border: 1px solid rgba(171, 209, 255, 1);
          margin-right: 10px;
          img {
            width: 100%;
            height: auto;
            vertical-align: baseline;
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
          margin-bottom: 0;
        }
      }
      .right {
        width: 30%;
        button {
          border: none;
          background: none;
          width: 100%;
          height: 20px;
          font-size: 14px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(59, 138, 254, 1);
          line-height: 20px;
          text-align: right;
        }
      }
    }
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
  .orderInfoMid {
    width: 315px;
    // height: 89px;
    background: #ffffff !important;
    font-size: 14px;

    display: flex;
    flex-direction: column;
    padding: 0 !important;
    color: #888888;
    div {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 19px;
      height: 19px;
      margin-bottom: 5px;
      .right_C {
        color: #666666;
      }
    }
    div:last-child {
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
    margin-top: 0px !important;
    // width: 315px;
    .remark {
      width: 40px;
      height: 30px;
    }
    .hintInfo {
      padding-right: 252px;
      line-height: 30px;
      color: rgba(102, 102, 102, 1);
      position: relative;
      font-size: 14px;
      color: #333333;
      span {
        position: absolute;
        left: 0;
        top: 1px;
      }
      .iconArrow {
        position: absolute;
        left: 28px;
        top: 3px;
      }
    }
    .hintInfo1 {
      float: right;
      padding-top: 5px;
      width: 270px;
    }
    button {
      color: rgba(59, 138, 254, 1);
      width: 56px !important;
      height: 24px !important;
      border-radius: 3px;
      border: 1px solid rgba(59, 138, 254, 1) !important;
      // margin-right: 1px;
      background: none;
      font-size: 12px;
    }
    .verificationBtn {
      width: 68px !important;
    }
    button:nth-child(4) {
      margin-right: 0px !important;
    }
    button:last-child {
      margin-right: 0px !important;
    }
    button:nth-child(5) {
      margin-top: 5px !important;
    }
    button:nth-child(6) {
      margin-top: 5px !important;
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
/deep/ .van-button {
  line-height: 18px !important;
}
#hintfather {
  width: 100%;
  height: 153px;
  border: 1px solid rgba(229, 229, 229, 1);
  border-radius: 5px;
}
textarea {
  margin: 15px 15px 0 15px;
  resize: none;
  width: calc(100% - 30px);
  border: none;
  outline: 0;
  display: block;
  height: 101px;
  overflow: auto;
  display: block;
  line-height: 18px;
  color: #999999;
}
#hintfather > :last-child {
  margin: 12px 15px;
  text-align: right;
  height: 17px;
  font-size: 12px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 17px;
}
.van-swipe-cell__right {
  right: -3px;
}
// .ellipsis-icon {
//   vertical-align: bottom;
//   transform: rotate(90deg);
//   -webkit-transform: rotate(90deg);
//   float: right;
//   margin-top: 5px;
// }
// .tips {
//   position: absolute;
//   right: 0px;
//   bottom: 0px;
//   font-size: 12px;
//   color: rgba(51, 51, 51, 1);
//   box-shadow: 0px 1px 5px 0px rgba(0, 0, 0, 0.17);
//   border-radius: 3px;
//   padding: 3px 0 3px 15px;
//   background: #fff;
//   // span:nth-child(2) {
//   //   width: 1px;
//   //   height: 20px;
//   //   background: #e1e1e1;
//   //   display: inline-block;
//   //   // margin: 0 10px;
//   //   vertical-align: middle;
//   // }
//   &::after {
//     display: inline-block;
//     content: "";
//     height: 0;
//     width: 0;
//     border-width: 10px;
//     border-style: solid;
//     border-color: transparent transparent #fff transparent;
//     // transform: translate(175px, -33px);
//     // -webkit-transform: translate(175px, -33px);
//     position: relative;
//     top: -26px;
//     right: 0;
//     box-shadow: transparent 1px 14px 0px rgba(0, 0, 0, 0.17);
//   }
// }
.van-radio__icon .van-icon {
  background: #ccc;
}
.van-radio__icon--checked .van-icon {
  background: #1989fa;
}
</style>
