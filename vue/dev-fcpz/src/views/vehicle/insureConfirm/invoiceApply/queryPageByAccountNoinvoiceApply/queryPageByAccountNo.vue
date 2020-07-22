<template>
<body>
  <globalNav title="投保确认" v-on:clickBack="clickBack" />
  <div id="share" @click="shareAction">
    <img src="../../../assets/common/share.png" />
  </div>
  <div class="super">
    <div class="father up">
      <div class="topTitle">
        <div class="icon"></div>
        <div class="title">{{ vehicleMessage.title }}</div>
        <div class="edit topIcon">
          <h5 @click="showMoreVehicleInfo">更多信息</h5>
          <div class="lastIcon" @click="eidtMessage(vehicleMessage)">
            <img src="../../../assets/vehicle/insureConfirm/edit.png" />
          </div>
        </div>
      </div>
      <div class="bottomInfo">
        <div class="left">车牌号码</div>
        <div class="right">{{ vehicleMessage.vehicleNo }}</div>
        <div class="left">车架号</div>
        <div class="right">{{ vehicleMessage.vehicleFrameNO }}</div>
        <div class="left">初登日期</div>
        <div class="right">{{ vehicleMessage.recordTime }}</div>
        <div class="left">发动机号</div>
        <div class="right">{{ vehicleMessage.engineNo }}</div>
        <div class="left">厂牌型号</div>
        <div class="right">{{ vehicleMessage.vehiclePlateType }}</div>
      </div>
    </div>
    <div class="father center">
      <div class="topTitle">
        <div class="icon"></div>
        <div class="title">{{ owerMessage.title }}</div>
        <div class="edit" @click="eidtMessage(owerMessage)">
          <img src="../../../assets/vehicle/insureConfirm/edit.png" />
        </div>
      </div>
      <div class="bottomInfo">
        <div class="left">姓名</div>
        <div class="right">{{ owerMessage.name }}</div>
        <div class="left">证件类型</div>
        <div class="right">{{ owerMessage.cardType }}</div>
        <div class="left">证件号码</div>
        <div class="right">{{ owerMessage.cardNo }}</div>
      </div>
    </div>
    <div class="father down">
      <div class="topTitle">
        <div class="icon"></div>
        <div class="title">{{ insurePlan.title }}</div>
        <div class="edit" @click="eidtMessage(insurePlan)">
          <img src="../../../assets/vehicle/insureConfirm/edit.png" />
        </div>
      </div>
      <div class="bottomInfo">
        <div class="item" v-for="(item, index) in insurePlan.plans" :key="index">
          <transition name="draw">
            <div class="box" v-show="index < 2 || (index >= 2 && toShow)">
              <div class="a">{{ item.title }}</div>
              <div class="b">{{ item.validTime }}</div>
              <div class="c">
                <div
                  class="subTitleLeft"
                  :style="
                      item.showInsuranceAmount == true
                        ? threeColStyle
                        : twoColStyle
                    "
                >险种</div>
                <div class="subTitleCenter" v-if="item.showInsuranceAmount == true">保额（万元）</div>
                <div
                  class="subTitleRight"
                  :style="
                      item.showInsuranceAmount == true
                        ? threeColStyle
                        : twoColStyle
                    "
                >保费（元）</div>
                <div class="subItem" v-for="(subItem, idx) in item.details" :key="idx">
                  <div
                    class="itemName"
                    :style="
                        item.showInsuranceAmount == true
                          ? threeColStyle
                          : twoColStyle
                      "
                  >{{ subItem.title }}</div>
                  <div
                    class="amount"
                    :style="
                        item.showInsuranceAmount == true
                          ? threeColStyle
                          : twoColStyle
                      "
                    v-if="item.showInsuranceAmount == true"
                  >{{ subItem.insuranceAmount }}</div>
                  <div
                    class="fee"
                    :style="
                        item.showInsuranceAmount == true
                          ? threeColStyle
                          : twoColStyle
                      "
                  >{{ subItem.price }}</div>
                </div>
              </div>
            </div>
          </transition>
        </div>
        <div id="arrow" @click="clickArrow">
          <img :src="arrowImg" />
        </div>
        <div id="price">
          <div>合计保费（元）</div>
          <div>{{ insurePlan.totalInsureAmount }}</div>
        </div>
      </div>
    </div>
    <div class="father appoint">
      <div class="topTitle">
        <div class="icon bLine"></div>
        <div @click="specialAgreement" class="title">{{ specialAppoint.title }}</div>
        <div class="edit" @click="clickNext(specialAppoint)">
          <img src="../../../assets/common/next.png" />
        </div>
      </div>
      <div class="bottomInfo">
        <div class="c">
          <div class="subTitleLeft" :style="twoColStyle">特约名称</div>
          <div class="subTitleRight" :style="twoColStyle">特约内容</div>
          <div class="subItem" v-for="(subItem, idx) in specialAppoint.appoints" :key="idx">
            <div class="itemName" :style="twoColStyle">{{ subItem.name }}</div>
            <div class="fee" :style="twoColStyle">{{ subItem.content }}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="father cash">
      <div class="topTitle">
        <div class="icon bLine"></div>
        <div class="title">{{ cashMessage.title }}</div>
        <div class="edit" @click="clickNext(cashMessage)">
          <img src="../../../assets/common/next.png" />
        </div>
      </div>
    </div>
    <div class="father starRight">
      <div class="topTitle">
        <div class="icon bLine"></div>
        <div class="title">{{ starRight.title }}</div>
        <div class="edit" @click="clickNext(starRight)">
          <img src="../../../assets/common/next.png" />
        </div>
      </div>
      <div class="bottomInfo">
        <div class="c">
          <div class="subTitleLeft">出单后星级</div>
          <div class="subTitleRight">出单后所获得积分</div>
          <div class="sc">
            <div class="leftStar">
              <van-rate
                readonly
                allow-half
                :size="15"
                :count="starRight.newCustLevel"
                void-icon="star"
                void-color="red"
                v-model="starRight.newCustLevel"
                color="#3C59B9"
              />
            </div>
            <div class="rightScore">{{ starRight.newIntegral }}</div>
          </div>
        </div>
      </div>
    </div>
    <div id="bottomDiv">
      <div @click="clickBook">商业计划书</div>
      <div id="submit" @click="submit">提交订单</div>
    </div>
  </div>
</body>
</template>

<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script>
import globalNav from "../../../components/globalNav.vue";
import { getQueryIndemnifyDetails } from "vuex";
import Vue from "vue";
import { Rate, Dialog } from "vant";
import "vant/lib/index.css";
import { starRights, contributing } from "@src/http/module/vehicle.js";
export default {
  name: "vehicleInsureConfirm",
  components: {
    globalNav,
    [Rate.name]: Rate,
    [Dialog.name]: Dialog
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      toShow: false,
      arrowImg: require("../../../assets/customer/InteractiveRights/down_arrow.png"),
      twoColStyle: {
        width: "50%"
      },
      threeColStyle: {
        width: "33.3%"
      },
      //车辆信息
      vehicleMessage: {
        title: "车辆信息", //标题
        vehicleNo: "京A-888888", //车牌号
        vehicleFrameNO: "Benzi-G500", //车架号
        recordTime: "2020-02-25", // 初登日期
        engineNo: "V-8", // 发动机号
        vehiclePlateType: "梅赛德斯奔驰越野车" //厂牌型号
      },
      //车主信息
      owerMessage: {
        title: "车主信息", //标题
        name: "Mr Hu", //姓名
        cardType: "身份证", //证件类型
        cardNo: "42119994211999999999999999999999" //证件号码
      },
      //投保方案
      insurePlan: {
        title: "投保方案", //标题
        totalInsureAmount: "3,000,000.00元", //合计保费
        plans: [
          {
            title: "交强险保险期限",
            validTime: "2019/10/01-2020/10/01",
            showInsuranceAmount: false,
            details: [
              {
                title: "交强险",
                insuranceAmount: "50.0元",
                price: "900.00"
              },
              {
                title: "车船险",
                insuranceAmount: "50.0元",
                price: "400.00"
              }
            ]
          },
          {
            title: "商业险保险期限",
            validTime: "2019/10/01-2020/10/01",
            showInsuranceAmount: true,
            details: [
              {
                title: "车辆损失险",
                insuranceAmount: "50.0元",
                price: "900.00"
              }
              // {
              //     title: "第三者责任险",
              //     insuranceAmount: "50.0元",
              //     price: "400.00"
              // }
            ]
          },
          {
            title: "财产险保险期限",
            validTime: "2019/10/01-2020/10/01",
            showInsuranceAmount: true,
            details: [
              {
                title: "财产损失险",
                insuranceAmount: "50.0元",
                price: "900.00"
              },
              {
                title: "人身伤害险",
                insuranceAmount: "50.0元",
                price: "400.00"
              }
            ]
          }
        ]
      },
      //特别约定
      specialAppoint: {
        title: "特别约定",
        appoints: [
          {
            name: "新增设备特别约定A",
            content: "特约内容ABCD"
          },
          {
            name: "新增设备特别约定B",
            content: "特约内容EFGH"
          }
        ]
      },
      //反洗钱信息
      cashMessage: {
        title: "反洗钱信息"
      },
      //星级权益接口返回值
      starRight: {
        custIntegral: "0",
        custLevel: "1",
        custName: "谭立东",
        newCustLevel: 1,
        newIntegral: "2212",
        serviceItemsAfter: [
          {
            serviceId: "156",
            serviceName: "非事故道路救援"
          },
          {
            serviceId: "2804",
            serviceName: "赠送静电贴"
          },
          {
            serviceId: "2807",
            serviceName: "短信提醒"
          }
        ],
        serviceItemsNow: [],
        totalCore: "2212",
        head: null,
        list: [],
        page: null,
        result: {
          code: "1",
          message: "查询客户出单后所获积分成功"
        }
      }
    };
  },
  created() {
    this.starRightsinfo();
    this.getContributing();
  },
  methods: {
    //  星级权益接口
    async starRightsinfo() {
      const res = await starRights({ orderId: 123 });
      //console.log(res, "res =>379");
    },
    //  特约接口
    async getContributing() {
      const res = await contributing({
        newCoreFlag: "string",
        orgCodes: "string",
        riskCodeBs: "string",
        riskCodeTs: "string",
        userCode: "string"
      });
      //console.log(res, "res =>390");
    },
    specialAgreement() {
      this.$router.push({ path: "insureAppointConfirm" });
    },
    clickBack() {
      //console.log("点击了返回");
    },
    clickBook() {
      //console.log("商业计划书");
    },
    submit() {
      //console.log("提交订单");
    },
    copyInsureMessage: function() {
      //console.log("复制投保");
    },
    shareAction: function() {
      //console.log("分享");
    },
    clickArrow: function() {
      this.toShow = !this.toShow;
      this.arrowImg =
        this.toShow == false
          ? require("../../../assets/customer/InteractiveRights/down_arrow.png")
          : require("../../../assets//customer/InteractiveRights/up_arrow.png");
    },
    showMoreVehicleInfo() {
      //console.log("更多信息");
    },
    eidtMessage(info) {
      //console.log(info.title);
    },
    clickNext(info) {
      //console.log(info.title);
    }
  }
};
</script>
<style scoped>
* {
  overflow: auto;
  text-overflow: ellipsis;
  white-space: nowrap;
}

body,
#app,
.super {
  width: 100%;
  height: 100%;
  /* position: absolute; */
  background: #3b8afe;
}

#share {
  width: 44px;
  height: 44px;
  position: fixed;
  z-index: 99;
  right: 10px;
}

#share > img {
  width: 22px;
  height: 22px;
  display: block;
  margin: 0 auto;
  margin-top: 11px;
}

.topTitle {
  width: 100%;
}

.father {
  margin: 74px 15px 10px 15px;
  width: calc(100% - 64px);
  background: white;
  border-radius: 8px;
  padding: 17px;
}

.appoint,
.cash,
.starRight {
  margin-top: 10px;
}

.up {
  height: 196px;
}

/* .up {
        height: 196px;
    } */

.down {
  margin-top: 0px;
  overflow: auto;
}

.bottomInfo {
  margin: 6px 0px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 31.8px;
  height: calc(100% - 24px);
}

.left {
  float: left;
  width: 120px;
  text-align: left;
  height: 31.8px;
}

.right {
  float: right;
  width: calc(100% - 120px);
  text-align: right;
  height: 31.8px;
  overflow: hidden;
}

.icon {
  background: #3b8afe;
  width: 20px;
  height: 20px;
  border-radius: 20px;
  float: left;
}

.sc {
  width: 100%;
}

.title {
  float: left;
  height: 20px;
  line-height: 20px;
  margin-left: 5px;
  font-size: 14px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 20px;
  width: 30%;
  text-align: left;
}

#down {
  height: 200px;
  margin: 0px 15px 40px 15px;
  width: calc(100% - 30px);
  background: white;
  border-radius: 8px;
}

.bLine {
  width: 4px;
  height: 14px;
  background: rgba(51, 51, 51, 1);
  border-radius: 2px;
  margin-top: 3px;
}

#bottomDiv {
  margin: 40px 15px 15px 15px;
  width: calc(100% - 30px);
  height: 50px;
  line-height: 46px;
  border-radius: 46px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  /* color: white; */
}

#bottomDiv > :first-child {
  width: calc(50% - 7.5px);
  height: 46px;
  float: left;
  border-radius: 23px;
  background: white;
  color: #005bac;
  line-height: 46px;
  text-align: center;
  border: 1px solid #005bac;
}

#bottomDiv > :last-child {
  text-align: center;
  width: calc(50% - 7.5px);
  height: 46px;
  float: right;
  border-radius: 23px;
  background: #3b8afe;
  color: white;
  border: 1px solid white;
}

.item {
  margin-top: 10px;
  width: 100%;
}

.a {
  text-align: left;
  width: 100%;
  height: 23px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 23px;
}

.b {
  text-align: left;
  width: 100%;
  height: 23px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 23px;
}

.c {
  margin-top: 9px;
  background: rgba(250, 250, 250, 1);
  padding: 17px;
  border-radius: 2px;
}

.subTitleLeft {
  text-align: left;
}

.subTitleLeft,
.subTitleCenter {
  float: left;
}

.subTitleCenter {
  width: 33.3%;
  text-align: center;
}

.subTitleRight {
  float: right;
  text-align: right;
}

.subItem {
  width: 100%;
}

.itemName {
  text-align: left;
}

.amount {
  text-align: center;
}

.leftStar {
  float: left;
}

.rightScore {
  float: right;
  text-align: right;
}

.itemName,
.amount {
  float: left;
}

.fee {
  float: right;
  text-align: right;
}

#price {
  background: rgba(59, 138, 254, 0.1);
  border-radius: 2px;
  height: 60px;
  margin: 0px 0px 0px 0px;
  padding: 0 18px;
  line-height: 60px;
}

#price > div {
  float: left;
  width: 50%;
}

#price > :first-child {
  text-align: left;
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
}

#price > :last-child {
  text-align: right;
  font-size: 17px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(206, 34, 98, 1);
}

.edit {
  width: calc(70% - 25px);
  float: right;
  /* background:red; */
}

.lastIcon {
  float: right;
  width: 20px;
  height: 20px;
}

.edit > img {
  display: block;
  float: right;
  width: 20px;
  height: 20px;
}

.topIcon > h5 {
  width: calc(100% - 40px);
  height: 20px;
  margin-right: 20px;
  float: left;
  text-align: right;
  line-height: 20px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  text-decoration: underline;
}

/* 折叠效果 */
.box {
  /* height: auto; */
  width: 100%;
}

.draw-enter-active,
.draw-leave-active {
  transition: all 0.3s ease;
}

.draw-enter,
    .draw-leave-to

    /* .fade-leave-active below version 2.1.8 */
 {
  height: 0;
}

#arrow {
  height: 38px;
  width: 100%;
  margin-top: 6px;
}

#arrow > img {
  display: block;
  width: 20px;
  height: 14px;
  margin: 12px calc(50% - 10px);
}
</style>
