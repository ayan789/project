<template>
  <div class="prePayMent">
    <globalNav
      :title="globalNav.title"
      :clear="globalNav.clear"
      @clickBack="backToPrePage"
    ></globalNav>
    <van-cell title="投保单号" :value="insureInfo.insureNo" class="top" />
    <van-cell title="支付号" :value="insureInfo.payApplyNo" />
    <van-cell title="支付金额">
      <div slot="default" class="money">
        {{ insureInfo.sumPremium ? insureInfo.sumPremium : "" }}
      </div>
    </van-cell>
    <van-cell title="保险起期" :value="insureInfo.startDate" />
    <van-button type="info" block class="btn" @click="goPay"
      >确认支付</van-button
    >
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import { getPayUrl } from "../../../http/module/nonCar.js";
import { DeBase64 } from "../../../utils/des.js";
export default {
  data() {
    return {
      globalNav: {
        title: "支付"
        // clear: "1"
      },
      insureInfo: {
        insureNo: "",
        payApplyNo: "",
        sumPremium: "",
        startDate: "",
        checkNo: ""
      }
    };
  },
  created() {
    this.initData();
  },
  components: {
    globalNav
  },
  methods: {
    initData() {
      // let obj = this.$route ? DeBase64(this.$route.query.payInfo) : "";
      // console.log(222, DeBase64(this.$route.query.payInfo));
      // // console.log(333, decryptDes(str, "payInfo"));

       let {
        proposalNo,
        paymentNo,
        startDate,
        sumPremium,
        checkNo
      } = this.$route.query;
      this.insureInfo.insureNo = proposalNo;
      this.insureInfo.payApplyNo = paymentNo;
      this.insureInfo.sumPremium =
        sumPremium && this.formatNum(parseInt(sumPremium).toFixed(2));
      this.insureInfo.startDate = startDate;
      this.insureInfo.checkNo = checkNo;
    },
    //格式化钱数
    formatNum(num) {
      const numStr = num.toString().split("."); //先转为字符串，再分开整数和小数部分
      let numInt = numStr[0]; // 整数部分
      let numDec = numStr.length > 1 ? "." + numStr[1] : "";
      // 小数部分，只有原数字存在小数点的时候，分割完长度才会大于1，才需要存储小数，记得前面加小数点
      let resultInt = ""; //存储整数部分处理结果
      while (numInt.length > 3) {
        //当剩余整数部分长度大于3时继续处理
        resultInt = "," + numInt.slice(-3) + resultInt; //把后三位截出来，和处理结果拼在一起，前面加逗号
        numInt = numInt.slice(0, -3); // 剩下的部分是除去刚刚截掉的3位的部分
      }
      return numInt + resultInt + numDec; //结果是“剩余的不足3位的整数”+“处理好的整数结果”+“小数部分”
    },
    // 跳转到
    async goPay() {
      let params = {
        proposalNo: this.insureInfo.insureNo,
        paymentNo: this.insureInfo.payApplyNo,
        checkNo: this.insureInfo.checkNo,
        flag:"1"
      };
      let res = await getPayUrl(params);
      console.dir(res);
      if (res.code == "1" && res.data) {
        window.location.href = res.data.url;
      }
    },
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
.prePayMent {
  .money {
    font-size: 17px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(252, 130, 14, 1);
  }
  .top {
    padding-top: 2.1rem;
  }
  .btn {
    width: 345px;
    height: 46px;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
    border-radius: 23px;
    margin: 40px auto 0;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
  }
  .van-cell__title,
  .van-cell__value {
    flex: none;
    width: 30%;
  }
  .van-cell__value {
    width: 70%;
  }
}
</style>
