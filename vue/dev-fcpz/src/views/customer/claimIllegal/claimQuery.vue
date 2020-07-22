<template>
  <body>
  <globalNav title="理赔查询" v-on:clickBack="back()"/>
  <div class="container claimQuery">
    <van-field
      v-model="orderPerson"
      label="投保人"
      placeholder="请输入投保人"
      input-align="left"
      maxlength="15"
    />
    <van-field
      v-model="orderBePerson"
      label="被保人"
      placeholder="请输入被保人"
      input-align="left"
      maxlength="15"
    />
    <van-field
      v-model="licenseNo"
      label="车牌号"
      placeholder="请输入车牌号"
      input-align="left"
      maxlength="8"
    />
    <van-field
      v-model="policyNo"
      label="保单号"
      placeholder="请输入保单号"
      input-align="left"
      maxlength="30"
    />
  </div>
  <h3 @click="searchMessage" id="search">查询</h3>

  <!-- 保单列表  v-if="getList&&getList.length>0"-->
  <div class="title-container">
    <div
      class="divBox"
      v-for="(item, index) in getList"
      :key="index"
      @click="onSubmit(item)"
    >
      <div class="divLine">
          <span class="divLable" style="color:#333;font-weight:550"
          >投保人：</span
          >
        <span class="divContent" style="color:#333;font-weight:bolder">{{
            item.custName
          }}</span>
        <van-icon
          name="arrow"
          class="divDetail"
          color="#BFBFBF"
          @click="onSubmit(item)"
        />
      </div>
      <div class="divLine">
        <span class="divLable">客户等级：</span>
        <span class="divContent">
            <span v-for="(items, index) in levelData" :key="index">
              <img
                v-if="
                  (item.clsLevel && Number(item.clsLevel) > items) ||
                    Number(item.clsLevel) == items
                "
                class="level"
                src="../../../assets/customer/customerEquity/fivePoint@3x.png"
              />
              <img
                v-else
                class="level"
                src="../../../assets/customer/customerEquity/fivePointNull@3x.png"
              />
            </span>
          </span>
      </div>
      <div class="divLine">
        <span class="divLable">保单号：</span>
        <span class="divContent">{{ item.policyNo }}</span>
      </div>
      <div class="divLine">
        <span class="divLable">终止日期：</span>
        <span class="divContent">{{ item.respEndDate }}</span>
      </div>
    </div>
  </div>
  </body>
</template>

<script>
import globalNav from "@/components/globalNav.vue";
import { querylist } from "@src/http/module/product.js";
import { Toast } from "vant";
//校验
import { isLicensePlateNumber, licenseNo } from "@src/utils/index.js";
export default {
  name: "claimQuery",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      getList: [],
      orderNo: "",
      orderPerson: "",
      orderBePerson: "",
      carNo: "",
      queryData: {
        userCode: localStorage.getItem("userCode"),
        custNo: "", //客户号
        custType: "1"
      }
    };
  },
  created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
  },
  computed: {
    licenseNo: {
      get: function () {
        return this.carNo;
      },
      set: function (carNo) {
        this.carNo = carNo.toUpperCase();
      }
    },
    policyNo: {
      get: function () {
        return this.orderNo;
      },
      set: function (orderNo) {
        this.orderNo = orderNo.toUpperCase();
      }
    }
  },
  methods: {
    searchMessage: function () {
      if (!this.$isNull(this.carNo) && !licenseNo(this.carNo)) {
        Toast("车牌号格式错误，请重新输入");
        return;
      }

      if (
        !(
          !this.$isNull(this.orderNo) ||
          !this.$isNull(this.orderPerson) ||
          !this.$isNull(this.orderBePerson) ||
          !this.$isNull(this.carNo)
        )
      ) {
        Toast("请至少输入其中一项查询条件");
        return;
      }
      let params = {
        userCode: localStorage.getItem("userCode"),
        licenseNo: this.carNo,
        custName: this.orderPerson,
        insuranceType: "1",
        insuredName: this.orderBePerson,
        policyNo: this.orderNo,
        minStartDate: "",
        maxStartDate: "",
        minEndDate: "",
        maxEndDate: "",
        pageSize: "10",
        pageNum: "1"
      };
      querylist(params)
        .then(res => {
          if (res.code === "1") {
            console.log(res.data.content[0]);

            if (!this.$isNull(res.data) && !this.$isNull(res.data.content)) {
              this.getList = res.data.content;
            } else {
              Toast("未查询到理赔信息，请重新输入！");
            }
          } else {
            Toast(res.msg);
          }
        })
        .catch(err => {
          this.error = true;
          console.log(this.error, "err");
        });
    },
    back() {
      this.$globalBack();
    },
    onSubmit(item) {
      this.item = {
        custNo: item.custNo, //客户号
        userCode: localStorage.getItem("userCode"), //业务号,
        custType: item.policyType,
        policyNo: item.policyNo
      };
      console.log(this.item, "toClaimOrdersView理赔-===>");
      this.$router.push({
        name: "claimOrders",
        query: { item: this.item }
      });
    }
  }
};
</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  /* text-decoration: none;
  font-size: 14px;
  font-weight: 400; */
  color: #333333;
}

.van-cell {
  height: 48px;
  line-height: 48px;
  width: 100%;
  padding: 0px 10px;
}

.van-cell--required::before {
  margin-left: -5px;
}

.container {
  background: white;
  margin-top: 180px;
  width: 100%;
  padding-left: 10px;
}

body {
  background: #f7f7f7;
  width: 100%;
  height: 100%;
  position: absolute;
}

#search {
  display: block;
  /* width: 100%; */
  height: 46px;
  background: #3b8afe;
  margin: 54px 15px 0px 15px;
  color: white;
  border-radius: 46px;
  text-align: center;
  line-height: 46px;
  font-size: 16px;
}

.container {
  margin-top: 80px;
}
</style>
<style lang="less" scoped>
.title-container {
  padding-top: 80px;
}

.divBox {
  margin: 5px;
  padding: 5px 10px;
  background: #fff;
  font-size: 14px;
  border-radius: 6px;
  margin-bottom: 20px;

  .divLine {
    height: 30px;
    line-height: 30px;

    .divLable {
      width: 80px;
      color: #454545;
      display: inline-block;
    }

    .divContent {
      color: #7a7a7a;

      .level {
        width: 16px;
        height: 16px;
        display: inline-block;
      }
    }

    .divDetail {
      float: right;
      margin-right: 10px;
      cursor: pointer;
      line-height: 30px;
    }
  }
}
</style>
