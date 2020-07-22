<template>
  <div class="containerOptions">
    <ul class="chooser-list">
      <li
        :style="cssStyle"
        v-for="(item, index) in incomeList"
        :key="index"
        @click="optionsClick(item)"
        :class="{ active: checkActive(item) }"
      >
        <span>{{ item }}</span>
      </li>
    </ul>
  </div>
</template>
<script>
// import "@/style/customer/options.less";
export default {
  model: {
    prop: "currArr",
    event: "input"
  },
  props: {
    incomeList: Array, //传入的数组
    isMultiply: {
      //是否是多选。默认为false：单选；true：多选
      type: Boolean,
      default: false
    },
    cssStyle: Object //可以自定义单选或者多选的样式
  },
  data() {
    return {
      currValArr: ["全部"]
    };
  },
  methods: {
    optionsClick(item) {
      this.$set(this.currValArr, 0, item); // 将该值设为当前数组的第一项
      if (this.isMultiply === false) {
        //单选
      } else {
        //多选
        if (this.currValArr.indexOf(item) === -1) {
          // 当前数组中没有该值则push到数组
          this.currValArr.push(item);
        } else {
          //当前数组中有该值，找到该值下标并删除
          this.currValArr.splice(this.currValArr.indexOf(item), 1);
        }
      }
      this.$emit("toparents", item);
    },
    checkActive(item) {
      if (this.isMultiply === false) {
        this.currValArr.length = 1;
      }
      return this.currValArr.indexOf(item) !== -1;
    },
    // 清空选中状态
    clearActive() {
      this.currValArr.splice(0, this.currValArr.length);
    },
    // 设置第一个选中状态
    setActive() {
      this.currValArr = ["全部"];
    }
  }
};
</script>
<style lang="less" scoped>
.containerOptions {
  ul,
  li {
    margin: 0;
    padding: 0;
    list-style: none;
  }
  .chooser {
    position: relative;
    display: inline-block;
  }
  .chooser-list li {
    margin-right: 10px;
    width: 104px;
    height: 36px;
    background: rgba(249, 250, 252, 1);
    border-radius: 2px;
    margin-bottom: 10px;
    font-size: 13px;
    line-height: 36px;
    display: inline-block;
    color: #999999;
    text-align: center;
    background: rgba(190, 190, 190, 0.12);
  }
  .chooser-list li.active {
    color: #3b8afe;
    background: rgba(59, 138, 254, 0.12) !important;
  }
}
</style>
