<!--
 * @Description: 保障计划-主附险展示的card
 * @Date: 2020-05-18 14:14:53
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-27 18:11:22
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\common\safePlan_card.vue
-->
<template>
  <div
    class="safePlan_card"
    id="safePlan_card"
  >
    <div class="card">
      <van-collapse
        v-model="activeName"
        accordion
      >
        <van-collapse-item
          :title="insurance.title"
          v-for="(insurance, i) in mainInsuranceList"
          :key="i"
          :name="`${i}` + 1"
          :value="insurance.formatAmout"
        >{{ insurance.desc }}</van-collapse-item>
      </van-collapse>

      <van-checkbox-group
        v-model="result"
        v-if="subsidiaryInsuranceList.length > 0"
      >
        <van-cell-group>
          <van-cell
            title="附加责任"
            class="card-title"
          />
          <van-cell
            v-for="(item, index) in subsidiaryInsuranceList"
            clickable
            :key="item.id"
            :title="item.title"
            @click="toggleInsurance(index, item)"
            :label="item.desc"
          >
            <template #right-icon>
              <van-checkbox
                :name="item.id"
                ref="checkboxes"
              />
            </template>
          </van-cell>
        </van-cell-group>
      </van-checkbox-group>
    </div>
  </div>
</template>
<script>
// import {
//   FormModel as AFormModel,
//   Button as AButton,
//   Col as ACol
// } from "ant-design-vue";

export default {
  name: "safePlan_card",
  components: {
    // [AFormModel.name]: AFormModel,
    // [AButton.name]: AButton,
    // [ACol.name]: ACol
  },
  props: {
    mainInsuranceList: Array,
    subsidiaryInsuranceList: Array,
    activeEdition: Number,
    fromRoute: Object
  },

  data () {
    return {
      activeName: "0",
      result: [],
      safeguardPlanObj: {}
    };
  },
  watch: {
    mainInsuranceList: {
      handler () {
        if (this.mainInsuranceList.length > 0) {
          this.updateMainSub();
        }
      },
      deep: true
    },
    subsidiaryInsuranceList: {
      handler () {
        if (this.subsidiaryInsuranceList.length > 0) {
          this.updateMainSub();
        }
      },
      deep: true
    },
    fromRoute: {
      handler () {
        return this.fromRoute;
      },
      deep: true
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.updateMainSub();
    });
  },
  methods: {
    // 根据缓存渲染勾选的险种
    renderCheckInsurance () {
      let { selectedSubIn } = this.$store.state.config.detailData;
      if (!this.$isNull(selectedSubIn)) {
        for (let k in selectedSubIn) {
          this.result.push(selectedSubIn[k].id);
        }
      }
    },
    // 更新store中勾选的主附险
    updateMainSub () {
      this.result.splice(0, this.result.length);
      if (
        !this.$isNull(this.fromRoute.name) &&
        (this.fromRoute.name.indexOf("vehicle") >= 0 ||
          this.fromRoute.name.indexOf("clause") >= 0)
      ) {
        this.renderCheckInsurance();
        return;
      }
      let mainObj = {};
      let subObj = {};
      for (let item of this.subsidiaryInsuranceList) {
        if (item.defaultValue == "1") {
          this.result.push(item.id);
          let id = item.id;
          subObj[id] = item;
        }
      }
      this.$store.dispatch("config/updateSelectedSubIn", subObj);
      for (let item of this.mainInsuranceList) {
        if (item.defaultValue == "1") {
          let id = item.id;
          mainObj[id] = item;
        }
      }
      this.$store.dispatch("config/updateSelectedMainIn", mainObj);
    },
    // 险种勾选
    toggleInsurance (index, item) {
      this.$refs.checkboxes[index].toggle();
      this.$emit("toggleInsurance", index, item);
    }
  }
};
</script>
<style lang="less" scope>
#safePlan_card {
  background: rgba(36, 128, 234, 1);
  height: auto !important;
  padding-bottom: 5px;
  /deep/.van-checkbox__icon--disabled.van-checkbox__icon--checked .van-icon {
    color: #fff;
  }

  /deep/.van-checkbox__icon--disabled .van-icon {
    background-color: #3b8afe;
    border-color: #3b8afe;
  }
  .card {
    background: rgba(255, 255, 255, 1);

    margin: 0 auto;
    padding: 10px;
    box-sizing: border-box;
    /deep/.van-tabs--line .van-tabs__wrap {
      z-index: 1;
      width: 100%;
      position: relative;
    }
    /deep/[class*="van-hairline"]::after {
      border: none;
    }
    /deep/.van-tab {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      border: 1px solid rgba(208, 208, 208, 1);
      margin-right: 14px;
      border-radius: 5px;
    }
    /deep/.van-tab--active {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 600;
      color: rgba(51, 51, 51, 1);
      flex-basis: 46% !important;
      border: 1px solid rgba(59, 138, 254, 1);
    }
    /deep/.van-ellipsis {
      flex-basis: 46% !important;
    }
    /deep/.van-tabs__line {
      width: 28px !important;
      height: 5px !important;
      background: none;
      border-radius: 6px;
      bottom: 0;
    }
    /deep/ .van-tabs__nav--line {
      padding-bottom: 0%;
    }
    /deep/ .van-tabs__wrap {
      margin: 8px 0 0 10px;
      width: 332px;
    }

    .titleStrong {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      line-height: 30px;
      margin: 11px 23px 10px;
    }
    .insureDesc {
      background: rgba(247, 247, 247, 1);
      border-radius: 8px;
      // height: 93px;
      margin: 0 10px;
      padding: 10px 13px;
      line-height: 22px;
      .strong {
        font-size: 14px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 7px;
      }
      .light {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(17, 17, 17, 1);
      }
      .gray {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
      }
    }
    .right {
      text-align: right;
    }
    /deep/ .van-checkbox-group {
      box-sizing: border-box;
      margin: 0.2rem;
      overflow: hidden;
      border-radius: 0.2rem;

      .van-cell {
        background: rgba(247, 247, 247, 1);
      }
    }
  }
  .card-title {
    font-weight: 600;
  }

  select {
    appearance: none;
    border: none;
    color: #323233;
    background: #fff;
    outline: none;
    -moz-appearance: none; /* Firefox */
    -webkit-appearance: none; /* Safari 和 Chrome */
  }
}
</style>
