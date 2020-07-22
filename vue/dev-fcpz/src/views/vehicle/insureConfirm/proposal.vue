<!--商业计划书-->

<style lang="less" scoped>
.proposal {
  position: absolute;
  width: 100%;
  min-height: 100%;
  background-image: linear-gradient(#bfdfff, #e4f1ff);
  .container {
    padding: 77px 10px 50px 10px;
    h3 {
      font-size: 20px;
    }
    .align_right {
      text-align: right;
    }
    .align_center {
      text-align: center;
    }
    .fl {
      float: left;
    }
    .fr {
      float: right;
    }
    .top {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-radius: 10px;
      padding: 10px;
      background-image: linear-gradient(to right, #dfefff, #ffffff);
      .left {
        display: flex;
        .card {
          width: 45px;
          height: 45px;
          border-radius: 50%;
        }
        .personInfo {
          display: flex;
          flex-direction: column;
          justify-content: center;
        }
      }
    }
    .body {
      .group {
        margin-top: 10px;
        padding: 10px;
        background: #ffffff;
        border-radius: 10px;
        .title {
          font-size: 15px;
          font-weight: 600;
          color: #333333;
        }
        .cardMain {
          display: flex;
          .leftIcon {
            margin-right: 10px;
            .icon {
              width: 49px;
              height: 49px;
              border-radius: 50%;
            }
          }
          .rightInfo {
            .name {
              font-size: 20px;
            }
            .position,
            .address {
              display: inline-block;
              font-size: 14px;
              color: #4a4a4a;
            }
            .position {
              padding-right: 10px;
            }
            .address {
              padding-left: 10px;
            }
            .address:before {
              content: "";
              position: absolute;
              width: 1px;
              height: 10px;
              margin: 5px 0 0 -10px;
              background: #979797;
            }
            .cellPhone,
            .email {
              margin-left: 10px;
              font-size: 12px;
              color: #4a4a4a;
            }
          }
        }
        .main {
          .insureDate {
            padding: 10px 0;
            .insureName {
              font-size: 14px;
              color: #333333;
            }
            .date {
              float: right;
              font-size: 11px;
              color: #ababab;
            }
          }
          .risks {
            background: #f9f9f9;
            border-radius: 2px;
            padding: 10px;
            font-size: 14px;
            color: #545454;
            .table-head {
              font-size: 12px;
              color: #8e8e8e;
            }
            .line {
              padding-bottom: 10px;
            }
            .total {
              padding-top: 10px;
              border-top: 1px solid #e9e9e9;
              .totalLabel {
                font-size: 13px;
              }
              .discount {
                color: #5374a7;
              }
              .money {
                color: #3b8afe;
                .discountBefore {
                  text-decoration: line-through;
                  margin-right: 15px;
                  color: #929292;
                }
              }
            }
          }
          .totalAmount {
            padding: 10px 10px 0 10px;
            overflow: hidden;
            .labelName {
              color: #333333;
              font-size: 14px;
              font-weight: 400;
            }
            .amount {
              font-size: 16px;
              color: #fc820e;
            }
          }
        }
      }
    }
    .gongzhonghao {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding-top: 20px;
      .QRCode {
        width: 116px;
        height: 116px;
      }
      p {
        width: 132px;
        text-align: center;
        font-size: 12px;
        color: #486895;
      }
    }
  }
  .bottom {
    #share {
      width: 100%;
      height: 46px;
    }
  }
}
</style>
<template>
  <div class="proposal">
    <globalNav clear="1" @clickBack="clickBack" />
    <div class="container">
      <div class="top">
        <div class="left">
          <div class="card">
            <img src="" />
          </div>
          <div class="personInfo">
            <h3 class="name">赵军</h3>
            <div>
              <van-icon name="phone" />
              <span class="cellPhone">136***6362</span>
            </div>
          </div>
        </div>
        <div class="right">
          <h3>沪A231321</h3>
          <p>路虎***越野车</p>
        </div>
      </div>
      <div class="body">
        <!--报价方案-->
        <div class="group">
          <div class="title">
            <van-checkbox v-model="insure" icon-size="16px"
              >报价方案</van-checkbox
            >
          </div>
          <div class="main">
            <!--交强险-->
            <div class="insureDate">
              <span class="insureName">交强险保险期限</span>
              <span class="date">2019/01/10 00:00 - 2019/01/10 00:00</span>
            </div>
            <div class="risks">
              <van-row class="table-head line">
                <van-col span="12">险种</van-col>
                <van-col span="12" class="align_right">保费(元)</van-col>
              </van-row>
              <van-row class="line">
                <van-col span="12">交强险</van-col>
                <van-col span="12" class="align_right">900.00</van-col>
              </van-row>
              <van-row class="line">
                <van-col span="12">车船税</van-col>
                <van-col span="12" class="align_right">600.00</van-col>
              </van-row>
              <van-row class="total">
                <van-col span="12">交强险+车船税</van-col>
                <van-col span="12" class="money align_right">1400</van-col>
              </van-row>
            </div>
            <!--商业险-->
            <div class="insureDate">
              <span class="insureName">商业险保险期限</span>
              <span class="date">2019/01/10 00:00 - 2019/01/10 00:00</span>
            </div>
            <div class="risks">
              <van-row class="table-head line">
                <van-col span="12">险种</van-col>
                <van-col span="6" class="align_center">保额(万元)</van-col>
                <van-col span="6" class="align_right">保费(元)</van-col>
              </van-row>
              <van-row class="line">
                <van-col span="12">机动车损失险</van-col>
                <van-col span="6" class="align_center">50.00</van-col>
                <van-col span="6" class="align_right">900.00</van-col>
              </van-row>
              <van-row class="total">
                <div class="discount align_right">
                  <span>整单优惠</span>
                  <span class="discountPer">3%</span>
                  <span class="discountAmount"> (300)</span>
                </div>
                <van-col span="12" class="totalLabel">商业险合计</van-col>
                <van-col span="12" class="money align_right">
                  <span class="discountBefore">1700.00</span>
                  <span class="discountAfter">1400.00</span>
                </van-col>
              </van-row>
            </div>
            <div class="totalAmount">
              <div class="labelName fl">合计保费(元)</div>
              <div class="amount fr">3000.00</div>
            </div>
          </div>
        </div>
        <!--上年理赔信息-->
        <div class="group">
          <div class="title">
            <van-checkbox v-model="compensation" icon-size="16px"
              >上年理赔信息</van-checkbox
            >
          </div>
          <div class="main">
            <div class="risks">
              <van-row class="table-head line">
                <van-col span="8">理赔险种类型</van-col>
                <van-col span="4" class="align_center">理赔金额(万元)</van-col>
                <van-col span="6" class="align_center">出险日期</van-col>
                <van-col span="6" class="align_right">结案时间</van-col>
              </van-row>
              <van-row class="line">
                <van-col span="8">机动车损失险</van-col>
                <van-col span="4" class="align_center">50.00</van-col>
                <van-col span="6" class="align_center">2020/3/12</van-col>
                <van-col span="6" class="align_right">2020/3/12</van-col>
              </van-row>
            </div>
          </div>
        </div>
        <!--客户权益-->
        <div class="group">
          <div class="title">
            <van-checkbox v-model="comperRights" icon-size="16px"
              >客户权益</van-checkbox
            >
            <div></div>
          </div>
        </div>
        <!--业务员名片-->
        <div class="group">
          <div class="title">
            <van-checkbox v-model="salesCard" icon-size="16px"
              >业务员名片</van-checkbox
            >
          </div>
          <div class="cardMain">
            <div class="leftIcon">
              <img class="icon" src="" />
            </div>
            <div class="rightInfo">
              <h3 class="name">金大中</h3>
              <div>
                <span class="position">资深业务经理</span>
                <span class="address">上海 徐汇营管处</span>
              </div>
              <div>
                <van-icon name="phone" size="10" />
                <span class="cellPhone">136***6362</span>
              </div>
              <div>
                <van-icon class-prefix="icon" name="email" size="10" />
                <span class="email">jason.wang@ccic.com.cn</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="gongzhonghao">
        <img class="QRCode" :src="zengzhiImg" />
        <p>关注增值服务微信公众号 立享以上服务</p>
      </div>
    </div>
    <div class="bottom">
      <van-button id="share" type="primary" color="#3B8AFE">分享</van-button>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Icon, RadioGroup, Radio, Button } from "vant";
import globalNav from "../../../components/globalNav.vue";
import "@src/assets/fonts/style.css";
import zengzhiImg from "../../../assets/vehicle/insureConfirm/zengzhi.png";
Vue.use(Icon);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(Button);
export default {
  name: "proposal",
  components: {
    globalNav
  },
  data() {
    return {
      zengzhiImg: zengzhiImg,
      insure: true,
      compensation: true,
      comperRights: true,
      salesCard: true,
      //投保方案
      insurePlan: {}
    };
  },
  methods: {
    clickBack() {
      this.$router.go(-1);
    }
  }
};
</script>
