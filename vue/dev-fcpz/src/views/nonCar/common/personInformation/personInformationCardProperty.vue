<template>
  <div id="person-information-card-property">
    <van-row type="flex" align="center" class="title">
      <van-col class="title-icon"></van-col>
      <van-col class="title-text">{{ title }}</van-col>
    </van-row>
    <van-cell-group :border="false">
      <van-cell
        v-for="item in items"
        :key="item.title"
        :border="false"
        :title="item.title"
        :value="item.value"
        center
        class="information-item"
        :class="[item.type == 1 ? 'vehicleInfo' : '']"
        title-class="information-item-text"
        value-class="information-item-text"
      />
    </van-cell-group>
  </div>
</template>

<script>
const policyHolder = [
  { key: "", title: "姓名", value: "1" },
  { key: "", title: "证件类型", value: "1" },
  { key: "", title: "证件号码", value: "" },
  { key: "", title: "手机号", value: "" },
  { key: "", title: "邮箱", value: "" }
];
const recognizee = [
  { key: "", title: "是否同被保人", value: "1" },
  { key: "", title: "姓名", value: "1" },
  { key: "", title: "证件类型", value: "1" },
  { key: "", title: "证件号码", value: "" },
  { key: "", title: "手机号", value: "" },
  { key: "", title: "车辆信息", value: "" },
  { key: "", title: "车型", value: "", type: 1 },
  { key: "", title: "车牌号", value: "", type: 1 },
  { key: "", title: "发动机号", value: "", type: 1 },
  { key: "", title: "吨位(T)", value: "", type: 1 }
];
const invoiceInfo = [
  { key: "", title: "发票抬头", value: "" },
  { key: "", title: "纳税人识别码", value: "" },
  { key: "", title: "联系电话", value: "" },
  { key: "", title: "邮箱地址", value: "" }
];
export default {
  name: "person-information-card-property",
  props: {
    type: String,
    data: Object
  },
  data: () => ({
    title: "",
    items: []
  }),
  created() {
    let data = this.data || {};
    switch (this.type) {
      case "policyHolder":
        this.title = "投保人信息";
        this.items = policyHolder.map(item => ({
          ...item,
          value: data[item.key] || ""
        }));
        break;
      case "recognizee":
        this.title = "被保人信息";
        this.items = recognizee.map(item => ({
          ...item,
          value: data[item.key] || ""
        }));
        break;
      case "invoiceInfo":
        this.title = "开票信息";
        this.items = invoiceInfo.map(item => ({
          ...item,
          value: data[item.key] || ""
        }));
        break;
      default:
        break;
    }
  },
  methods: {}
};
</script>

<style scoped>
#person-information-card-property {
  background: white;
  padding: 0px 16px;
  margin-bottom: 10px;
}
.title {
  height: 48px;
}
.title-icon {
  width: 5px;
  height: 14px;
  background: #3b8afe;
  border-radius: 3px;
  margin-right: 8px;
}
.title-text {
  font-size: 16px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  line-height: 22px;
}
.information-item {
  height: 30px;
  padding: 0px;
}

.vehicleInfo {
  padding: 9px 14px;
  font-size: 13px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 21px;
  background: rgba(250, 250, 250, 1);
}
.vehicleInfo:first-child {
  margin-top: 7px;
}
.information-item-text {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #666666;
  line-height: 30px;
}
</style>
