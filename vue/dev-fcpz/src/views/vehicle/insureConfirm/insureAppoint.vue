<template>
  <div class="container">
    <nav-bar
      type="vehicle"
      title="增值服务特别约定"
      @clickBack="$emit('closeInsureAppoint')"
    />
    <!-- <div id="mother">
      <div class="leftFather">
        <h3 class="cate" :style="selectStyle">
          dsdsa
        </h3>
        <h3 class="cate">dsdsa</h3>
        <h3 class="cate">dsdsa</h3>
        <h3 class="cate">dsdsa</h3>
      </div>
    </div>
    <div id="right">
      <div class="rightFather">
        <van-checkbox class="contentWord"
          >被保车辆为家庭自用车时，为本保险车辆提供1次快递服务寄送车辆代检材料。</van-checkbox
        >
      </div>
      <div class="rightFather">
        <van-checkbox class="contentWord"
          >被保车辆为家庭自用车时，为本保险车辆提供1次快递服务寄送车辆代检材料。</van-checkbox
        >
      </div>
      <div class="rightFather">
        <van-checkbox class="contentWord"
          >被保车辆为家庭自用车时，为本保险车辆提供1次快递服务寄送车辆代检材料。</van-checkbox
        >
      </div>
    </div> -->
    <div class="content">
      <div id="mother">
        <div
          class="leftFather"
          v-for="(item, index) in engages.serviceTypes"
          :key="index"
        >
          <h3
            @click="selectCate(index, item)"
            class="cate"
            :style="selectIndex == index ? selectStyle : ''"
          >
            {{ item.serviceTypeName }}
          </h3>
        </div>
        <div
          class="leftFather"
          v-for="(item, index) in engages.serviceTypes"
          :key="'copy' + index"
        >
          <h3
            @click="selectCate(index, item)"
            class="cate"
            :style="selectIndex == index ? selectStyle : ''"
          >
            {{ item.serviceTypeName }}
          </h3>
        </div>
        <div
          class="leftFather"
          v-for="(item, index) in engages.serviceTypes"
          :key="'copy1' + index"
        >
          <h3
            @click="selectCate(index, item)"
            class="cate"
            :style="selectIndex == index ? selectStyle : ''"
          >
            {{ item.serviceTypeName }}
          </h3>
        </div>
      </div>
      <div id="right">
        <div
          class="rightFather"
          v-for="(item, index) in allServiceItems"
          v-show="currentType == item.serviceTypeCode"
          :key="index"
          @click="selectAppoint(index, item)"
        >
          <van-checkbox v-model="item.selected" class="contentWord">
            {{ item.serviceName }}
          </van-checkbox>
        </div>
      </div>
    </div>
    <div id="bottom">
      <div @click="clearSelected">清空</div>
      <div @click="sureAction">确定</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "insureAppoint",
  props: {
    engage: Object
  },
  data() {
    console.log("get Data: ", this.engage);
    let arr = [];
    this.engage.serviceTypes &&
      this.engage.serviceTypes.map(serviceType => {
        serviceType.serviceItems.map(serviceItem => {
          arr.push(serviceItem);
        });
      });
    return {
      selectIndex: 0,
      currentType: "",
      multiCheck: "0", //是否开始单选开关
      selectStyle: {
        background: "#FFFFFF",
        fontSize: "16px",
        fontWeight: "500",
        color: "#3B8AFE"
      },
      engages: this.engage || {
        serviceTypes: []
      },
      allServiceItems: arr
    };
  },
  watch: {
    engage(engage) {
      this.engages = engage || {
        serviceTypes: []
      };
      let arr = [];
      engage.serviceTypes &&
        engage.serviceTypes.map(serviceType => {
          serviceType.serviceItems.map(serviceItem => {
            arr.push(serviceItem);
          });
        });
      this.allServiceItems = arr;
    }
  },
  computed: {
    serviceItems() {
      return this.allServiceItems.filter(
        item => this.currentType == item.serviceTypeCode
      );
    }
  },
  //生命周期初始化；
  mounted() {
    // 初始默认显示第一栏
    if (
      this.engages &&
      this.engages.serviceTypes &&
      this.engages.serviceTypes.length
    ) {
      this.currentType = this.engages.serviceTypes[0].serviceTypeCode;
      this.multiCheck = this.engages.serviceTypes[0].multiCheck;
    }
  },
  methods: {
    // 左侧栏目点击事件
    selectCate: function(index, item) {
      this.selectIndex = index;
      this.currentType = item.serviceTypeCode;
      this.multiCheck = item.multiCheck;
      this.$forceUpdate();
    },
    // 选择事件
    selectAppoint: function(index, item) {
      //判断是否开启单选模式
      this.allServiceItems.forEach(item => {
        //chenjiafeng 判断在当前栏目下，并且支持单选
        if (
          this.currentType == item.serviceTypeCode &&
          this.multiCheck == "1"
        ) {
          item.selected = false;
        }
      });
      item.selected = !item.selected;
      this.$forceUpdate();
    },
    // 清空事件
    clearSelected: function() {
      this.allServiceItems.map(item => {
        item.selected = false;
      });
      this.$forceUpdate();
    },
    // 确定事件
    sureAction: function() {
      let content = "";
      // 确定时将选中特约内容存放进主特约
      this.allServiceItems.map(item => {
        if (item.selected) {
          content += item.serviceName;
        }
      });
      this.engages.specialContent = content;
      this.engages.selected = true;
      // 如果未选择增值特约，则取消特约勾选
      if (!content) {
        this.engages.selected = false;
      }
      this.$emit("closeInsureAppoint", this.engages);
    }
  }
};
</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  text-align: left;
  text-overflow: ellipsis;
  white-space: pre-wrap;
}
.container {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.leftFather {
  width: 100px;
  height: 50px;
  border-bottom: 1px solid white;
}
.content {
  width: 100%;
  height: calc(100% - 77px - 48px);
  overflow-y: hidden;
  display: flex;
  flex-direction: row;
}
#mother {
  width: 100px;
  height: 100%;
  background: #f7f7f7;
  overflow-y: scroll;
}
#right {
  width: 275px;
  height: 100%;
  background: white;
  overflow-y: scroll;
}
.rightFather {
  margin: 0px 10px 10px 0px;
  width: 100%;
}
.contentWord {
  /* max-width: 65%; */
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 20px;
}
.cate {
  height: 50px;
  line-height: 50px;
  text-align: center;
  font-size: 14px;
  font-weight: 400;
  color: #666666;
}

.van-checkbox,
.checkMsg {
  float: left;
}

.checkMsg {
  flex: 1;
  /* line-height: 50px; */
  text-align: left;
  font-size: 14px;
  font-weight: 400;
  color: #666666;
  width: 78%;
  margin-top: 10px;
  line-height: 24px;
}
.van-checkbox {
  /* background: white; */
  margin: 21px 11px 0 18px;
}
#bottom {
  width: 100%;
  height: 46px;
  position: fixed;
  z-index: 99;
  bottom: 0px;
  left: 0px;
  right: 0px;
}
#bottom > div {
  width: 50%;
  line-height: 46px;
  height: 46px;
  text-align: center;
  float: left;
}
#bottom > :first-child {
  background: white;
  color: #3b8afe;
  font-size: 14px;
  border-top: 1px solid #e5e5e5;
}

#bottom > :last-child {
  background: linear-gradient(
    125deg,
    rgba(42, 128, 255, 1) 0%,
    rgba(42, 165, 255, 1) 100%
  );
  box-shadow: 0px 2px 10px 0px rgba(0, 0, 0, 0.1);
  color: #ffffff;
  font-size: 16px;
  border-top: 1px solid #3b8afe;
}
</style>
