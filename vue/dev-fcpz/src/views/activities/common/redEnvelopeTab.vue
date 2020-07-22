/* zhangminjie 公共组件--已领取和待领取tab */
<template>
  <div class="redEnvelopeTab">
    <div class="activitiesMid" v-if="pageType == 1">
      <p
        :class="[selected == item.id ? 'btnActive' : '']"
        @click="changeStatus(item.id)"
        v-for="(item, index) in reciveTab"
        :key="index"
      >
        <span>{{ item.title }}</span>
        <span>{{ item.num }}个</span>
      </p>
    </div>
    <div class="activitiesMid" v-if="pageType != 1">
      <p
        :class="[selected == item.id ? 'btnActive borderLine' : '']"
        @click="changeStatus(item.id)"
        v-for="(item, index) in reciveTab"
        :key="index"
      >
        <span>{{ item.title }}</span>
        <span>{{ item.num }}个</span>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "redEnvelopeTab",
  props: {
    pageType: Number, // 1 活动规则页面  2 活动详情页面
    reciveTab: Array, // 红包的tab
    selected: Number // 选中的tab
  },
  components: {},
  data: () => ({
    currentSelected: 1
  }),
  methods: {
    // 切换已领取和待领取红包
    changeStatus(index) {
      this.currentSelected = this.selected;
      if (index != this.currentSelected) {
        this.currentSelected = index;
      }
      this.$emit("changeStatusEvent", this.currentSelected);
    }
  }
};
</script>

<style lang="less" scoped>
.redEnvelopeTab {
  .activitiesMid {
    width: 346px;
    height: 62px;
    margin: 10px auto 0;
    display: flex;
    align-items: center;
    background: rgba(255, 255, 255, 1);
    p {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      font-size: 14px;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      text-align: center;
      width: 50%;
      height: 62px;
      padding: 10px 63px;
      background: rgba(250, 250, 250, 1);
      border: 1px solid rgba(231, 231, 231, 1);
      box-sizing: border-box;
      cursor: pointer;
      span {
        display: block;
      }
    }
    p:last-child {
      border-left: none;
    }
    .btnActive {
      color: rgba(59, 138, 254, 1);
      background: rgba(255, 255, 255, 1);
    }
    .borderLine {
      border-bottom: 2px solid rgba(59, 138, 254, 1);
    }
  }
}
</style>
