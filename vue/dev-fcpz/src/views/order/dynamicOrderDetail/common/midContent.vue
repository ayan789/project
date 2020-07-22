<!--
 * @Description: 非车配置---订单详情--中间内容部分
 * @Date: 2020-06-17 14:03:56
 * @Author: zhangminjie
 * @LastEditTime: 2020-07-03 16:55:20
 * @FilePath: \nonCarCongfig\src\views\order\dynamicOrderDetail\common\midContent.vue
-->
<template>
  <div class="midContent">
    <van-cell-group :border="!isNoBorder" class="list">
      <van-cell :border="!isNoBorder">
        <div slot="title" class="title">保障内容</div>
      </van-cell>
      <div v-for="(item, index) in guaranteedCollageContent" :key="index">
        <van-cell :border="!isNoBorder">
          <div slot="title" class="content">{{ item.productName }}</div>
          <div slot="default" class="content">
            {{ item.insured }}{{ " " + item.insureUnit }}
          </div>
        </van-cell>
      </div>
    </van-cell-group>

    <van-cell-group
      :border="!isNoBorder"
      class="list"
      v-for="(item, index) in elementList"
      :key="index"
    >
      <van-cell :border="!isNoBorder">
        <div slot="title" class="title">{{ item.groupName }}</div>
      </van-cell>
      <van-cell
        :border="!isNoBorder"
        v-for="(element, i) in item.elementList"
        :key="i"
        v-show="item.elementList.length > 0"
      >
        <div slot="title" class="content">{{ element.label }}</div>
        <div slot="default" class="content">{{ element.displayValue }}</div>
      </van-cell>
    </van-cell-group>
    <div class="list" v-if="invoiceFlag == 1 || invoiceFlag == 2" id="invoice">
      <van-cell-group>
        <van-cell :border="!isNoBorder">
          <div slot="title" class="title">开票信息</div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            发票抬头
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.companyName }}
          </div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            手机号
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxMobile }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            客户类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.clientType }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            纳税人类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxPayerType }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            发票类型
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.invoiceType }}
          </div>
        </van-cell>

        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            公司名称
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.companyName }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            纳税人识别码
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxPayerNo }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            注册地址
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxRegisterAddress }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            注册电话
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.taxRegistryPhone }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            开户银行
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.bankName }}
          </div>
        </van-cell>
        <van-cell v-if="invoiceFlag == 2">
          <div slot="title" class="content">
            银行账号
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.accountNumber }}
          </div>
        </van-cell>
        <van-cell>
          <div slot="title" class="content">
            电子邮箱
          </div>
          <div slot="default" class="content">
            {{ invoiceInfo.email }}
          </div>
        </van-cell>
      </van-cell-group>
    </div>
    <van-cell-group :border="!isNoBorder" class="list">
      <van-cell :border="!isNoBorder">
        <div slot="title" class="title">保费</div>
      </van-cell>
      <div v-for="(item, index) in insuranceCost" :key="index">
        <van-cell :border="!isNoBorder">
          <div slot="title" class="content">{{ item.desc }}</div>
          <div
            slot="default"
            :class="[item.type == 2 ? 'content red' : 'content']"
          >
            ￥ {{ item.price }}
          </div>
        </van-cell>
      </div>
    </van-cell-group>
  </div>
</template>

<script>
import Vue from "vue";
import { Card } from "vant";
import { Button } from "vant";

Vue.use(Card);
Vue.use(Button);
export default {
  name: "midContent",
  props: {
    guaranteedCollageContent: Array, //传入的数组
    elementList: Array,
    insuranceCost: Array,
    invoiceFlag: String, //("0:无发票信息,1:个人，2：企业")
    invoiceInfo: Object // 发票信息
  },
  created() {
    // console.dir(this.personList)
  },
  mounted() {
    this.personArr = this.personList;
  },
  data: () => ({
    isNoBorder: true,
    personArr: []
  }),
  methods: {}
};
</script>

<style lang="less" scoped>
.midContent {
  .guaranteed,
  .unused {
    height: 30px;
    line-height: 30px;
    font-size: 15px;
    color: rgba(255, 138, 61, 1);
  }
  .unused {
    color: rgba(51, 51, 51, 1);
  }

  .list {
    margin-top: 10px;
  }
  .list:last-child {
    margin-bottom: 10px;
  }
  .list {
    background: rgba(255, 255, 255, 1);
    border-radius: 4px;
    .title {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 22px;
    }
    .content {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 30px;
    }
    .red {
      color: #fc820e;
    }
  }
  .guaranteedContentTop {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
    position: relative;
    .benefitDetails {
      cursor: pointer;
      position: absolute;
      top: 0;
      right: 0;
      font-size: 13px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(59, 138, 254, 1);
      line-height: 19px;
    }
    .leftIcon {
      width: 14px;
      height: 14px;
      background: #084db3;
    }
    .header {
      display: flex;
      align-items: center;
      span {
        margin-left: 4px;
      }
    }
  }
  .guaranteedContentBottom {
    display: flex;
    flex-direction: column;
    margin-top: 10px;
    // min-height: 120px;
  }
  .guaranteedContentBottom p,
  .personInfoBottom p,
  .insuranceCostInfoBottom p {
    // height: 30px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 30px;
    display: flex;
    justify-content: space-between;
  }
  .guaranteedContentBottom p span:first-child {
    width: 210px;
  }
  .personInfoTop {
    width: 66px;
    height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
  }
  .personInfoBottom p {
    margin-top: 14px;
  }
  .personInfoBottom p span {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }
  .personInfoBottom p span:first-child {
    width: 30%;
  }
  .personInfoBottom p span:last-child {
    width: 70%;
    text-align: right;
  }
  .personInfoBottom {
    font-family: PingFangSC-Regular, PingFang SC;
    .vehicleInfo {
      padding: 9px 14px;
      background: rgba(250, 250, 250, 1);
      li {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 13px;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
        line-height: 21px;
      }
    }
  }
  .insuranceCostInfoTop {
    width: 33px;
    // height: 22px;
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    line-height: 22px;
  }
  .insuranceCostInfoBottom p {
    // height: 30px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 30px;
  }
  .red {
    color: #fc820e;
  }
  #invoice {
    /deep/.van-cell__title {
      width: 40%;
      flex: none;
    }
    /deep/ .van-cell__value {
      width: 60%;
      flex: none;
    }
  }
}
</style>
