/* zhangminjie 公共组件--订单详情中间内容部分-ui修改后 */
<template>
  <div class="orderDetailMidContent">
    <div class="guaranteedContent">
      <van-card class="guaranteedContentInfo">
        <div
          slot="title"
          class="guaranteedContentTop"
        >
          <div class="header">
            <!-- <div class="leftIcon"></div> -->
            <span>保障内容</span>
          </div>
          <div
            class="guaranteedContentBottom"
            v-if="insuranceType == 1"
          >
            <p
              v-for="(item, index) in guaranteedCollageContent"
              :key="index"
            >
              <span>{{ item.name }}</span>
              <span>{{ item.value }}万</span>
            </p>
          </div>
          <div
            class="guaranteedContentBottom"
            v-if="insuranceType == 2"
          >
            <p
              v-for="(item, index) in guaranteedCollageContent"
              :key="index"
            >
              <span>{{ item.name }}</span>
              <span>{{ item.value }}万</span>
            </p>
            <p v-if="!guaranteedCollageContent.length">
              <span>个人恶性肿瘤医疗责任</span>
              <span>200万</span>
            </p>
          </div>
          <div>

          </div>
        </div>
      </van-card>
    </div>
    <div class="person">
      <van-card class="personInfo">
        <div
          slot="title"
          class="guaranteedContentTop"
        >
          <div class="header">
            <!-- <div class="leftIcon"></div> -->
            <span>投被保人</span>
          </div>
          <div class="personInfoBottom">
            <div v-if="insuranceType == 1">
              <p @click="redirectMoreInfoPage">
                <span>投保人</span>
                <span>
                  {{ person.applicantName }}
                  {{ person.appliIdentify }}
                  <van-icon name="arrow" />
                </span>
              </p>
              <P @click="redirectMoreInfoPage">
                <span>被保人</span>
                <span>
                  {{ person.insuredName }} {{ person.insuredIdentify }}
                  <van-icon name="arrow" />
                </span>
              </P>
            </div>
            <p
              v-if="personArr.length > 0"
              @click="redirectMoreInfoPage"
            >
              <span>投保人</span>
              <span>
                {{ person.applicantName }}
                {{ person.appliIdentify }}
                <van-icon name="arrow" />
              </span>
            </p>
            <div
              v-for="(item, index) in personArr"
              :key="index"
            >
              <p @click="redirectMoreInfoPage">
                <span v-if="!item.relateInsured">被保人</span>
                <span v-if="item.relateInsured">被保人({{
                    item.relateInsured ? item.relateInsured : ""
                  }})</span>
                <span>
                  {{ item.insuredName }}
                  {{ item.insuredIdentify }}
                  <van-icon name="arrow" />
                </span>
              </p>
            </div>

            <div v-if="insuranceType == 2 && personArr.length == 0">
              <p @click="redirectMoreInfoPage">
                <span>投保人</span>
                <span>
                  {{ person.applicantName }}
                  {{ person.appliIdentify }}
                  <van-icon name="arrow" />
                </span>
              </p>
              <div
                v-for="(item, index) in personArr"
                :key="index"
              >
                <p @click="redirectMoreInfoPage">
                  <span v-if="!item.relateInsured">被保人</span>
                  <span v-if="item.relateInsured">被保人({{
                      item.relateInsured ? item.relateInsured : ""
                    }})</span>
                  <span>
                    {{ item.insuredName }}
                    {{ item.insuredIdentify }}
                    <van-icon name="arrow" />
                  </span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </van-card>
    </div>
    <div
      class="guaranteedContent"
      v-if="insuranceType == 1"
    >
      <van-card class="guaranteedContentInfo">
        <div
          slot="title"
          class="guaranteedContentTop"
        >
          <div class="header">
            <!-- <div class="leftIcon"></div> -->
            <span>运输工具</span>
          </div>
          <div class="guaranteedContentBottom">
            <p
              v-for="item in vechileInfoList"
              :key="item.id"
            >
              <span>{{ item.title }}</span>
              <span>{{ item.desc }}</span>
            </p>
          </div>
        </div>
      </van-card>
    </div>
    <div class="guaranteedContent">
      <van-card class="guaranteedContentInfo">
        <div
          slot="title"
          class="guaranteedContentTop"
        >
          <div class="header">
            <!-- <div class="leftIcon"></div> -->
            <span>保费</span>
          </div>
          <div class="guaranteedContentBottom">
            <p
              v-for="item in insuranceCost"
              :key="item.id"
            >
              <span>{{ item.desc }}</span>
              <span :class="[item.type == 2 ? 'red' : '']">￥{{ item.price }}</span>
            </p>
          </div>
        </div>
      </van-card>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import { Card } from "vant";
import { Button } from "vant";

Vue.use(Card);
Vue.use(Button);
export default {
  props: {
    guaranteedCollageContent: Array, //传入的数组

    personList: Array,
    person: Object,
    isCollage: Boolean,
    insuranceCost: Array,
    insuranceType: Number, //1 财产险，2 健康险
    vechileInfoList: Array
  },
  created () {
    // console.dir(this.personList)
  },
  mounted () {
    this.personArr = this.personList;
  },
  data: () => ({
    personArr: []
  }),
  methods: {
    // 跳转到更多信息页面
    redirectMoreInfoPage () {
      this.$emit("redirectMoreInfoPageEvent");
    }
  }
};
</script>

<style lang="less" scoped>
.orderDetailMidContent {
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
  .guaranteedContentInfo,
  .personInfo,
  .insuranceCostInfo {
    background: rgba(255, 255, 255, 1);
    border-radius: 4px;
    display: flex;
    flex-direction: column;
    // position: relative;
    // box-sizing: border-box;
    padding: 15px;
  }
  .guaranteedContent,
  .person,
  .insuranceCost {
    margin-top: 10px;
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
}
</style>
