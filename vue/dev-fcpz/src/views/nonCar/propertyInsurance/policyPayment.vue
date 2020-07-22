/* zhangminjie 财产险-保单支付 */
<template>
  <div class="policyPayment">
    <globalNav title="保单支付"></globalNav>
    <div class="middle">
      <van-cell-group>
        <van-cell title="投保单号" value="998880099000222" />
        <van-cell title="产品信息" value="车险(商业险+交强险)" />
        <van-cell title="投保人" value="高圆圆" />
        <van-cell title="支付号" value="99888222" />
        <van-cell title="验证码" value="888222" />
        <van-cell title="应付金额" value="¥1,000.00">
          <template slot="default">
            <span class="croal">¥1,000.00</span>
          </template>
        </van-cell>
      </van-cell-group>
    </div>
    <div class="bottom">
      <h4 class="title">请选择支付方式</h4>
      <div class="paymentWayOut">
        <van-cell-group class="paymentWay">
          <van-cell title="二维码支付" is-link to="/paymentByQRCode">
            <template slot="icon">
              <div class="paymenIcon"></div>
            </template>
          </van-cell>
          <van-cell title="转发支付" is-link to="">
            <template slot="icon">
              <div class="paymenIcon green"></div>
            </template>
          </van-cell>
          <van-cell title="支付宝支付" is-link to="/paymentByAlipayMessage">
            <template slot="icon">
              <div class="paymenIcon blue"></div>
            </template>
          </van-cell>
          <van-cell title="银行卡快捷支付" is-link to="/paymentByBankCard">
            <template slot="icon">
              <div class="paymenIcon blue1"></div>
            </template>
          </van-cell>
        </van-cell-group>
      </div>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav";
export default {
  name: "policyPayment",
  components: {
    globalNav
  },
  data: () => ({
    policyInfo: {}
  }),
  methods: {}
};
</script>

<style lang="less" scoped>
.policyPayment {
  width: 100%;
  height: calc(100vh - 70px);
  overflow: scroll;
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Semibold, PingFang SC;
  padding-top: 70px;
  div {
    box-sizing: border-box;
  }
  .middle {
    font-size: 14px;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
    .croal {
      color: rgba(252, 130, 14, 1);
      font-size: 17px;
      font-weight: 600;
    }
    .van-cell__value {
      font-size: 12px;
      color: rgba(102, 102, 102, 1);
      line-height: 17px;
    }
  }
  .bottom {
    width: 100%;
    border-radius: 10px;
    .title {
      width: 98px;
      height: 20px;
      font-size: 14px;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      line-height: 20px;
      margin: 22px 0 10px 20px;
    }
    .paymentWayOut {
      width: 375px;
      padding: 0 15px;
      .paymentWay {
        width: 345px;
        background: rgba(255, 255, 255, 1);
        .van-cell {
          display: flex;
          align-items: center;
          height: 60px;
          line-height: 60px;
          .van-cell__title {
            font-size: 16px;
            font-weight: 400;
            color: rgba(51, 51, 51, 1);
            line-height: 23px;
          }
        }
        .paymenIcon {
          width: 40px;
          height: 40px;
          background: rgba(249, 166, 49, 1);
          border-radius: 20px;
          margin-right: 12px;
        }
        .green {
          background: rgba(45, 166, 65, 1);
        }
        .blue {
          background: rgba(31, 196, 238, 1);
        }
        .blue1 {
          background: rgba(49, 117, 250, 1);
        }
      }
    }
  }
}
</style>
