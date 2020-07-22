<template>
  <div id="insuranceProducts">
    <div class="title-container">
      <div class="titleHeader">
        <van-nav-bar title="保单详情" left-arrow @click-left="$router.back()" />
      </div>
      <div class="divBox">
        <div
          class="bg"
          v-for="(item, index) in getData.insuredList"
          :key="index"
        >
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon4.png" />
            <span class="divLable title">投保人信息</span>
          </div>
          <div class="divLine">
            <span class="divLable">姓名：</span>
            <span class="divContent">{{ item.insuredName }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">证件类型：</span>
            <span class="divContent">{{ item.identifyType }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">证件号码：</span>
            <span class="divContent">{{ item.identifyNumber }}</span>
          </div>
        </div>
        <div
          class="bg"
          v-for="(item, index) in getData.itemkindlist"
          :key="index"
        >
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon2.png" />
            <span class="divLable title">被投保人信息</span>
          </div>
          <div class="divLine">
            <span class="divLable">姓名：</span>
            <span class="divContent">{{ item.insuredName }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">证件类型：</span>
            <span class="divContent">{{ item.identifyType }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">证件号码：</span>
            <span class="divContent">{{ item.identifyNumber }}</span>
          </div>
        </div>
        <div class="bg">
          <div class="divLine">
            <img class="img" src="../../../assets/order/orderList/icon1.png" />
            <span class="divLable title">保险责任</span>
          </div>
          <div class="divLine">
            <span class="divLable">投保责任：</span>
            <span class="divContent">保额</span>
          </div>
          <div v-for="(item, index) in getData.itemkindlist" :key="index">
            <div class="divLine">
              <span class="divLable">{{ item.kindName }}：</span>
              <span class="divContent">{{ item.amount }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { queryDetail } from '@src/http/module/product.js'
export default {
  data() {
    return {
      getData: '',
      parmasD: {
        userCode: '',
        policyNo: '',
        queryType: ''
      }
    }
  },
  computed: {
    getData1: function() {
      return JSON.parse(this.$route.query.item)
    }
  },
  mounted() {
    this.onSubmit()
  },
  methods: {
    async onSubmit() {
      this.parmasD.userCode = this.getData1.userCode
      this.parmasD.policyNo = this.getData1.policyNo
      this.parmasD.queryType = this.getData1.queryType
      await queryDetail(this.parmasD)
        .then(res => {
          if (res.code === '1') {
            this.getData = res.data.policyVO
          } else {
            Toast({
              duration: 2000,
              message: res.msg
            })
          }
        })
        .catch(err => {})
    },
    identifyType: function(ite) {
      if (ite == '01') {
        return '身份证'
      } else if (ite == '02') {
        return '户口薄'
      } else if (ite == '03') {
        return '护照'
      } else if (ite == '04') {
        return '军官证'
      } else if (ite == '07') {
        return '组织机构代码证'
      } else if (ite == '21') {
        return '三证合一代码'
      }
    }
  }
}
</script>
<style lang="less" scoped>
#insuranceProducts {
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Medium, PingFang SC;
  .titleHeader {
    background: linear-gradient(
      129deg,
      rgb(30, 70, 246) 0%,
      rgb(39, 154, 255) 100%
    );
    .van-nav-bar {
      background: none;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
  }
  .divBox {
    padding: 5px 0;
    background: #fff;
    font-size: 14px;
    margin-bottom: 20px;
    .divLine {
      padding: 8px 10px;
      .img {
        width: 22px;
        float: left;
        margin-right: 5px;
        margin-top: -2px;
      }
      .title {
        color: #333;
        font-weight: 550;
      }
      .divLable {
        color: #454545;
        display: inline-block;
      }
      .divContent {
        color: #7a7a7a;
        float: right;
      }
    }
    .bg {
      margin-bottom: 20px;
    }
  }
}
</style>
