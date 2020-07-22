<template>
  <div
    class="costSelect"
    @click="selectDate_"
    :style="{ background: backgroungStyle }"
  >
    <div class="costSelect-data" style="width：2.96667rem; ">
      <span style="font-size:0.3rem;">{{ nowDate | fifterDate }}</span>
      <!-- <van-icon
        name="arrow"
        :style="{ transform: show ? 'rotate(90deg)' : '' }"
      /> -->
      <van-icon
        name="play"
        color="#999999"
        :style="{ transform: show ? 'rotate(90deg)' : '' }"
      />
    </div>
    <div v-show="show">
      <p
        class="costSelect-option"
        v-for="item in selectDateList"
        @click="checkDate(item)"
        :key="item"
      >
        {{ item | fifterDate }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  model: {
    prop: "selectDate",
    event: "selected"
  },
  props: {
    selectDate: String,
    backgroungStyle: String
  },
  data: () => {
    return {
      nowDate: "", //当前选中的日期
      selectDateList: [],
      show: false
    };
  },
  created() {
    this.nowDate_();
  },
  filters: {
    fifterDate(val) {
      if (val) return `${val.split("-")[0]}年${val.split("-")[1]}月`;
    }
  },
  methods: {
    //当前日期
    nowDate_() {
      let year = new Date().getFullYear();
      let month = new Date().getMonth() + 1;
      if (month < 10) month = "0" + month;
      this.nowDate = `${year}-${month}`;
      this.$emit("selected", this.nowDate);
    },
    //下拉日期
    selectDate_() {
      this.show = !this.show;
      this.selectDateList = [...[]];
      let year = new Date().getFullYear();
      let month = new Date().getMonth() + 1;
      for (let i = 1; i <= month; i++) {
        if (month < 10) {
          this.selectDateList.push(`${year}-0${i}`);
        } else {
          this.selectDateList.push(`${year}-${i}`);
        }
      }
    },
    //选中日期
    checkDate(val) {
      this.nowDate = val;
      this.$emit("selected", val);
    }
  }
};
</script>

<style lang="less" scoped>
.costSelect {
  background: #fff;
  position: relative;
  &-data {
    width: 100px;
    height: 20px;
    font-size: 15px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: center;
    -webkit-box-pack: justify;
    // -webkit-justify-content: space-between;
    -webkit-justify-content: center;
    // justify-content: space-between;
    justify-content: center;
    padding: 5px 15px;
    // border: 1px solid #f5f5f5;
    background: #fff;
    border-radius: 20px;
    /deep/.van-icon {
      vertical-align: middle;
    }
  }
  & div:nth-child(2) {
    position: absolute;
    top: 30px;
    left: 10px;
    background: #ffffff;
    height: auto;
    width: 110px;
    text-indent: 10px;
    font-size: 14px;
    border: 1px solid #f5f5f5;
    border-top: none;
    border-radius: 4px;
  }
  &-option {
    height: 25px;
    margin: 5px 0;
  }
}
</style>
