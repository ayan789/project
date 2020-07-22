<template>

  <body>
    <globalNav title="订单详情" />
    <div id="share" @click="shareAction">
      <img src="@/assets/common/share.png" />
    </div>
    <div class="super">
      <div class="father up">
        <div class="topTitle">
          <div class="icon"></div>
          <div class="title">{{vehicleMessage.title}}</div>
        </div>
        <div class="bottomInfo">
          <div class="left">车牌号码</div>
          <div class="right">{{vehicleMessage.vehicleNo}}</div>
          <div class="left">车架号</div>
          <div class="right">{{vehicleMessage.vehicleFrameNO}}</div>
          <div class="left">初登日期</div>
          <div class="right">{{vehicleMessage.recordTime}}</div>
          <div class="left">发动机号</div>
          <div class="right">{{vehicleMessage.engineNo}}</div>
          <div class="left">厂牌型号</div>
          <div class="right">{{vehicleMessage.vehiclePlateType}}</div>
        </div>
      </div>
      <div class="father center">
        <div class="topTitle">
          <div class="icon"></div>
          <div class="title">{{owerMessage.title}}</div>
        </div>
        <div class="bottomInfo">
          <div class="left">姓名</div>
          <div class="right">{{owerMessage.name}}</div>
          <div class="left">证件类型</div>
          <div class="right">{{owerMessage.cardType}}</div>
          <div class="left">证件号码</div>
          <div class="right">{{owerMessage.cardNo}}</div>
        </div>
      </div>
      <div class="father down">
        <div class="topTitle">
          <div class="icon"></div>
          <div class="title">{{insurePlan.title}}</div>
        </div>
        <div class="bottomInfo">
          <div class="item" v-for="(item,index) in insurePlan.plans" :key="index">
            <div class="a">{{item.title}}</div>
            <div class="b">{{item.validTime}}</div>
            <div class="c">
              <div class="subTitleLeft" :style="item.showInsuranceAmount == true?threeColStyle:twoColStyle">险种</div>
              <div class="subTitleCenter" v-if="item.showInsuranceAmount == true">保额（万元）</div>
              <div class="subTitleRight" :style="item.showInsuranceAmount == true?threeColStyle:twoColStyle">保费（元）</div>
              <div class="subItem" v-for="(subItem,idx) in item.details" :key="idx">
                <div class="itemName" :style="item.showInsuranceAmount == true?threeColStyle:twoColStyle">
                  {{subItem.title}}</div>
                <div class="amount" :style="item.showInsuranceAmount == true?threeColStyle:twoColStyle"
                  v-if="item.showInsuranceAmount == true">{{subItem.insuranceAmount}}</div>
                <div class="fee" :style="item.showInsuranceAmount == true?threeColStyle:twoColStyle">{{subItem.price}}
                </div>
              </div>
            </div>
          </div>
          <div id="price">
            <div>合计保费（元）</div>
            <div>{{insurePlan.totalInsureAmount}}</div>
          </div>
        </div>
      </div>
      <div id="copyInsure" @click='copyInsureMessage'>复制投保</div>
    </div>
  </body>
</template>

<script>
  import globalNav from "@/components/globalNav.vue";
  export default {
    name: "orderDetailCopy",
    components: {
      globalNav
    },
    props: {
      // msg: String,
    },
    data() {
      return {
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
                },
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
                },
                {
                  title: "第三者责任险",
                  insuranceAmount: "50.0元",
                  price: "400.00"
                }
              ]
            }
          ]
        }
      };
    },
    methods: {
      copyInsureMessage: function () {
        //console.log('复制投保');
      },
      shareAction: function () {
        //console.log("分享");
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
    background: #f7f7f7;
  }

  #share {
    width: 44px;
    height: 44px;
    position: fixed;
    z-index: 99;
    top: 10px;
    right: 10px;
  }

  #share>img {
    width: 22px;
    height: 22px;
    display: block;
    margin: 0 auto;
    margin-top: 5px;
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
    overflow: hidden;
  }

  .up {
    height: 196px;
  }

  .center {
    height: 130px;
    margin-top: 0px;
  }

  .down {
    margin-top: 0px;
    overflow: auto;
  }

  .bottomInfo {
    margin: 6px 0px 18px 0px;
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
    width: 80%;
    text-align: left;
  }

  #down {
    height: 200px;
    margin: 0px 15px 40px 15px;
    width: calc(100% - 30px);
    background: white;
    border-radius: 8px;
  }

  #copyInsure {
    margin: 20px 15px 20px 15px;
    width: calc(100% - 30px);
    height: 46px;
    line-height: 46px;
    border-radius: 46px;
    color: white;
    background: #3b8afe;
    text-align: center;;
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

  .itemName,
  .amount {
    float: left;
  }

  .fee {
    float: right;
    text-align: right;
  }

  #price {
    background: rgba(250, 250, 250, 1);
    border-radius: 2px;
    height: 60px;
    margin: 20px 0px 0px 0px;
    padding: 0 18px;
    line-height: 60px;
  }

  #price>div {
    float: left;
    width: 50%;
  }

  #price> :first-child {
    text-align: left;
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
  }

  #price> :last-child {
    text-align: right;
    font-size: 17px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(206, 34, 98, 1);
  }
</style>