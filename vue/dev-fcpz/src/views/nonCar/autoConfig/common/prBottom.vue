<template>
  <div
    class="prBottom"
    id="prBottom"
  >
    <div class="tabbar">
      <div
        class="flex1"
        v-for="item in proBottom"
        :key="item.id"
      >
        <div
          class="price"
          v-if="item.type == 'text'"
        >
          <div>{{ !formatSumPremium ? 0 : formatSumPremium }}</div>
          <p>{{ item.desc }}</p>
        </div>
        <div
          class="show"
          v-if="item.type == 'button' && item.pbBox.length > 0"
          @click="show = !show"
        >
          {{ item.desc }}
        </div>
        <div
          class="btn"
          v-if="item.type == 'button' && item.pbBox.length == 0"
        >
          <van-button
            type="info"
            @click="doInsure"
          >{{ item.desc }}</van-button>
        </div>
      </div>
    </div>
    <!-- 底部按钮弹框  TODO 展业夹暂不显示-->
    <div
      v-if="show"
      class="bottomPop"
    >
      <div
        v-for="(item, index) in pbBox"
        :key="index"
      >
        <van-button
          type="info"
          class="bottomBtn"
          plain
          v-if="item.code=='poster'"
          @click="goProduct(item.code)"
        >{{ item.name }}</van-button>
        <div
          class="line"
          v-show="pbBox.length > 1 && index != pbBox.length - 1"
        />
      </div>
    </div>
    <!-- <van-popup v-model="show" closeable>
      <div class="content">
        <h3>{{ title }}</h3>
        <div class="divBtn">
          <div v-for="(item, index) in pbBox" :key="index">
            <img :src="item.linkUrl" alt="edit" />
            <van-button type="info" plain @click="goProduct(item.code)">{{
              item.name
            }}</van-button>
          </div>
        </div>
      </div>
    </van-popup> -->
  </div>
</template>
<script>
export default {
  name: "prBottom",
  props: {
    proBottom: Array,
    title: String,
    isSharePage: String
  },
  data () {
    return {
      show: false,
      pbBox: [],
      formatSumPremium: ""
    };
  },
  computed: {
    sumP: {
      get () {
        return this.$store.state.config.detailData.sumPremium;
      },
      set () {
        let premium = this.$store.state.config.detailData.sumPremium;
        if (premium == 0) {
          this.formatSumPremium = "";
        } else {
          this.formatSumPremium = this.formatNum(premium.toFixed(2));
        }
      }
    },
    premium () {
      return this.formatSumPremium;
    }
  },
  watch: {
    proBottom: {
      handler () {
        if (this.proBottom.length > 0) {
          for (let i = 0, len = this.proBottom.length; i < len; i++) {
            if (this.proBottom[i].pbBox.length > 0) {
              this.pbBox = this.proBottom[i].pbBox;
            }
          }
        }
      },
      deep: true,
      immediate: true
    },
    sumP: {
      handler () {
        let premium = this.$store.state.config.detailData.sumPremium;
        if (premium == 0) {
          this.formatSumPremium = "";
        } else {
          this.formatSumPremium = this.formatNum(premium.toFixed(2));
        }
      },
      deep: true,
      immediate: true
    },
    formatSumPremium: {
      handler () {
        let premium = this.$store.state.config.detailData.sumPremium;
        if (premium == 0) {
          this.formatSumPremium = "";
        } else {
          this.formatSumPremium = this.formatNum(premium.toFixed(2));
        }
      },
      deep: true,
      immediate: true
    }
  },
  mounted () {

    let premium = this.$store.state.config.detailData.sumPremium;
    if (premium == 0) {
      this.formatSumPremium = "";
    } else {
      this.formatSumPremium = this.formatNum(premium.toFixed(2));
    }
  },
  methods: {
    //分享海报或者制作计划书
    goProduct (code) {
      this.$emit("goProduct", code);
    },
    // 投保按钮
    doInsure () {
      this.$emit("doInsure");
    },
    //格式化钱数
    formatNum (num) {
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
    }
  }
};
</script>
<style lang="less" scoped>
#prBottom {
  width: 100%;
  height: 46px;
  position: fixed;
  bottom: 0;
  background: rgba(255, 255, 255, 1);
  text-align: center;

  .tabbar {
    display: flex;
    position: fixed;
    width: 100%;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: #fff;
    height: 46px;
    .flex1 {
      flex: 1;
    }
    .price {
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      font-size: 0.3rem;
      div {
        color: #fc820e;
        font-size: 0.4rem;
      }
    }
    .show {
      flex: 1;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(72, 104, 149, 1);
      background-color: #d6e6ff;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100%;
    }
    .btn {
      button {
        width: 100%;
        height: 46px;
      }
    }
  }
  .van-popup {
    width: 90%;
    border-radius: 10px;

    .content {
      padding: 16px;
      h3 {
        text-align: center;
        padding-bottom: 0.2rem;
        font-size: 18px;
      }
      .divBtn {
        display: flex;
        div {
          flex: 1;
          text-align: center;
          img {
            width: 90%;
            border-radius: 10px;
            margin-top: 0.2rem;
          }
          button {
            margin-top: 0.2rem;
          }
        }
      }
    }
  }
  .bottomPop {
    position: fixed;
    bottom: 57px;
    left: 50%;
    width: 116px;
    min-height: 43px;
    background: #ffffff;
    transform: translateX(-50%);
    box-shadow: 0 0 0.37333rem 0 rgba(0, 0, 0, 0.17);
    .bottomBtn {
      width: 84px;
      height: 43px;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
      line-height: 43px;
      padding: 0;
      border: none;
    }
    .line {
      width: 84px;
      height: 1px;
      background: #dddddd;
      margin: 0 auto;
    }
  }
  .bottomPop:after {
    content: "";
    position: absolute;
    bottom: 0;
    left: 40%;
    border: 10px solid transparent;
    border-top-color: #ffffff;
    border-bottom: 0;
    margin: 0 0 -10px -10px;
  }
}
</style>
