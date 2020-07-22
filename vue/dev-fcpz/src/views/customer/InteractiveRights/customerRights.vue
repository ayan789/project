<template>
  <div class="content">
    <div v-if="custSourceType == 1"  :style="{backgroundImage: 'url(' + noDataIMG + ')'}" class="nodate-image">
    </div>
    <div v-if="custSourceType == 1" style="display: flex;justify-content: center;margin-top: -10px"><span style="color:#999999">暂无数据</span></div>

    <div v-if="custSourceType == 2 && datas.length < 1"
    :style="{backgroundImage: 'url(' + noDataIMG + ')'}" 
    class="nodate-image">
    </div>
    <div v-if="custSourceType == 2 && datas.length < 1" style="display: flex;justify-content: center;margin-top: -10px"><span style="color:#999999">暂无数据</span></div>

    <div
      v-for="(item, index) in datas"
      :key="index"
      style="padding:15px 15px 0 15px;"
    >
      <div style="display: flex;align-items: center;">
        <div class="user-icon">
          <img
            style="width:100%;heigth:100%"
            src="@/assets/customer/customerEquity/user@3x.png"
            alt="edit"
          />
        </div>
        <p style="margin-left: 10px">{{ item.bbrCustName }}</p>
      </div>
      <div class="list-desc" v-for="(v, k) in item.interestList" :key="k">
        <div
          class="list-one"
          :class="v.equitiesState != 1 ? 'list-one-gray' : ''"
        >
          <div style="width:45px;height:45px;margin-left: 10px">
            <img
              style="width:100%;height:100%"
              src="@/assets/customer/customerEquity/coupon@3x.png"
              alt="edit"
            />
          </div>
          <div class="one-text" style="margin-left: 10px">
            <div class="text">
              <div
                style="color: #1D3557;font-size: 15px;font-weight: bold"
                :class="v.equitiesState != 1 ? 'one-text-gray' : ''"
              >
                {{ v.equitiesName }}
                <!--                <span class="tag" v-if="v.equitiesState != 3">-->
                <!--                赠送-->
                <!--</span>-->
              </div>

            </div>
            <div style="margin-top: 9px;color: #5D6E86;font-size: 12px"
                 :class="v.equitiesState != 1 ? 'one-text-gray' : ''">
              <!--{{ v.startTime }}-&#45;&#45;{{ v.endTime }}-->
              {{ v.startTime }}-{{v.endTime}}
            </div>
          </div>
        </div>
        <div
          style="width:1px;height:80px;border-left: 1px dotted #BACBE4"
        ></div>
        <div
          class="list-two"
          :class="v.equitiesState != 1 ? 'list-one-gray' : ''"
        >
          <p class="p1" v-if="v.equitiesState == 1" @click="toRouter">
            提醒
          </p>
          <p class="p3" v-if="v.equitiesState == 2">
            已使用
          </p>
          <p class="p3" v-if="v.equitiesState == 3">
            已过期
          </p>
        </div>
      </div>
      <!--<div @click="on_up(index + item.bbrCustName)">收起</div>-->
    </div>

    <div class="product">
      <productRecommendation :product="product"></productRecommendation>
    </div>
  </div>
</template>
<script>
import productRecommendation from "@/components/productRecommendation.vue";
import noDataIMG from "../../../assets/customer/customerEquity/noData.png";
export default {
  props: {
    datas: Array,
    product: Array,
    custSourceType: String,
    custNo:String
  },
  components: {
    productRecommendation
  },
  created() {
    if (this.datas) {
      this.noData = true;
    }
  },
  data() {
    return {
      showList: {},
      noData: false,
      noDataIMG:noDataIMG,
    };
  },
  methods: {
    on_up: function (index) {
      this[index] ? (this[index] = false) : (this[index] = true);
    },
    toRouter: function () {
      let item = {
        "userCode": localStorage.getItem("userCode"),
        "id": "",
        "remindType": "",
        "remindTitle": "",
        "custNo": this.custNo,
        "custName": "",
        "policyNo": "",
        "registNo": "",
        "taskStatus": "",
        "outOfInsuranceStatus": "",
        "remindTimeNode": "",
        "createTime": "",
        "updateTime": "",
        "createUser": "",
        "updateUser": "",
        "taskTime": ""
      };
      this.$router.push({ name: 'registeredView', query: { item: item } });
    },
  }
};
</script>
<style lang="less" scoped>
.content {
  background-color: #ffffff;
  position: static;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: 60vh;
  /*padding-top: 50px;*/
.nodate-image{
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 300px;
  margin: 0px auto;
  height: 300px;
}
  .one-text-gray {
    color: #A2A2A2 !important;
  }

  .list-desc {
    display: flex;
    align-items: center;
    margin-top: 13px;

    .list-one-gray {
      background-color: #f4f4f4 !important;
    }

    .list-one {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      background-color: #edf4ff;
      height: 80px;
      flex: 1;
      border-radius: 5px;

      .one-text {
        .text {
          display: flex;
          align-items: center;
        }

        .tag {
          font-size: 10px;
          color: #bf6500;
          background-color: #ffebc1;
          border-radius: 7px;
          height: 15px;
          margin-left: 7px;
        }
      }
    }

    .list-one {
    }

    .list-two {
      height: 80px;
      width: 86px;
      background-color: #edf4ff;
      border-radius: 5px;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 0px 11px;

      .p1 {
        padding: 4px 20px 3.5px 20px;
        background-color: #2a80ff;
        border-radius: 11.5px;
        color: #ffffff;
        font-size: 10px;
      }

      .p2 {
        /*padding: 4px 20px 3.5px 20px;*/
        /*background-color: #2a80ff;*/
        /*border-radius: 11.5px;*/
        color: #7d95b8;
        font-size: 10px;
      }

      .p3 {
        /*padding: 4px 20px 3.5px 20px;*/
        /*background-color: #2a80ff;*/
        /*border-radius: 11.5px;*/
        color: #c6c6c6;
        font-size: 10px;
      }
    }
  }

  .user-icon {
    width: 20px;
    height: 20px;
  }

  .product {
    padding: 0px 15px 0 15px;

    span {
      display: block;
      color: #999999;
      line-height: 17px;
      padding-bottom: 24px;
    }

    .coverage {
      overflow: hidden;
      padding: 13px 0 14px 0;
      border-bottom: 1px solid #e7e7e7;

      .picture {
        width: 89px;
        height: 90px;
        background: yellow;
        float: left;
      }

      .particulars {
        float: left;
        margin-left: 13px;
        padding-top: 6px;

        .care {
          font-size: 16px;
          font-weight: 500;
          color: rgba(51, 51, 51, 1);
          line-height: 18px;
          margin-bottom: 7px;
        }

        .insurance {
          font-size: 14px;
          font-weight: 400;
          color: rgba(144, 144, 144, 1);
          line-height: 18px;
        }

        .selling {
          height: 25px;
          color: #999999;
          margin-top: 17px;
          font-size: 14px;
          font-weight: 500;
        }
      }
    }

    .coverage:nth-last-child(1) {
      border-bottom: transparent;
    }
  }
}
</style>
