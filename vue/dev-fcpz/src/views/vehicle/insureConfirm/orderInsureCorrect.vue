<template>
  <div class="super">
    <globalNav title="保单批改" />
    <div class="infoTitle">
      <h3>批改前信息</h3>
    </div>
    <div class="info">
      <div class="left">未上牌车辆标志</div>
      <div class="right">{{ before.hasCard }}</div>
      <div class="left">号牌号码</div>
      <div class="right">{{ before.cardNo }}</div>
      <div class="left">号牌类型</div>
      <div class="right">{{ before.cardType }}</div>
    </div>
    <div></div>
    <div class="infoTitle second">
      <h3>批改后信息</h3>
    </div>
    <div class="info last">
      <div class="left after">未上牌车辆标志</div>
      <input type="text" v-model="after.hasCard" />
      <div class="line"></div>
      <div class="left after">号牌号码</div>
      <input type="text" v-model="after.cardNo" />
      <div class="line"></div>
      <div class="left after">号牌类型</div>
      <div class="right selector after" @click="selectCarPlateType">
        <div>{{ after.cardType }}</div>
        <div id="next">
          <img src="@/assets/common/next.png" />
        </div>
      </div>
    </div>
    <div id="submitt" @click="submit">提交</div>
    <van-popup v-model="showPicker" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :default-index="1"
        v-show="showPicker"
        :columns="columns"
        @cancel="onCancel"
        @confirm="onConfirm"
      />
    </van-popup>
  </div>
</template>
<script>
//保单批改页面
import globalNav from "@/components/globalNav.vue";
export default {
  name: "orderInsureCorrect",
  components: {
    globalNav
  },
  props: {},

  data() {
    return {
      showPicker: false,
      columns: ["卡车", "越野车", "皮卡", "房车", "电动车"],
      before: {
        hasCard: "是",
        cardNo: "京A-888888",
        cardType: "小型汽车号牌"
      },
      after: {
        hasCard: "是",
        cardNo: "京A-888888",
        cardType: "请选择"
      }
    };
  },
  methods: {
    onConfirm(value, index) {
      this.showPicker = !this.showPicker;
      //console.log(`当前值：${value}, 当前索引：${index}`);
      this.after.cardType = value;
    },
    onCancel() {
      this.showPicker = !this.showPicker;
      //console.log("取消");
    },
    submit: function() {
      //console.log("提交");
    },
    selectCarPlateType: function() {
      //console.log("请选择");
      this.showPicker = !this.showPicker;
    }
  }
};
</script>
<style scoped>
* {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
body,
#app,
.super {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
}
h3 {
  margin-left: 15px;
  margin-top: 18px;
  color: #333333;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: bold;
  line-height: 22px;
  text-align: left;
  font-size: 16px;
}
.infoTitle {
  height: 48px;
  margin-top: 64px;
  background: white;
}
.second {
  margin-top: 10px;
}
.info {
  width: calc(100% - 30px);
  height: 108px;
  padding: 0px 15px;
  background: white;
}
.line {
  height: 1px;
  width: 100%;
  background: #e5e6e8;
}
.last {
  height: 144px;
}
.left,
.right,
input {
  line-height: 36px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  height: 36px;
}

.left {
  float: left;
  width: 120px;
  text-align: left;
}
.right,
input,
.selector {
  float: right;
  width: calc(100% - 120px);
  text-align: right;
}

.selector > :first-child {
  float: left;
  width: calc(100% - 30px);
}
#next {
  float: right;
  width: 30px;
  height: 48px;
  display: flex;
  justify-content: center;
  margin-top: 17px;
}
#next > img {
  display: block;
  width: 14px;
  height: 14px;
}
input {
  border-radius: 0px;
  border-width: 0px;
  overflow: hidden;
  border: 0;
  outline: none;
}

input::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
  text-align: right;
}
.after,
input {
  height: 48px;
  line-height: 48px;
}

#submitt {
  margin: 178px 15px 15px 15px;
  width: calc(100% - 30px);
  height: 46px;
  line-height: 46px;
  color: white;
  background: #3b8afe;
  border-radius: 23px;
}
</style>
