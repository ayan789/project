<template>
  <div id="invoice-information-input">
    <van-cell title="发票抬头" :value="header" />
    <van-cell title="纳税人识别号" :value="code" />
    <van-field
      label="移动电话"
      :value="phone"
      input-align="right"
      required
      disabled
    />
    <van-cell title="电子邮箱" :value="email" />
  </div>
</template>

<script>
import { Button } from "vant";
export default {
  components: {
    [Button.name]: Button
  },
  name: "invoice-information",
  props: {
    header: String,
    code: String,
    phone: String,
    email: String
  },
  data: () => ({}),
  methods: {}
};
</script>

<style scoped>
.select-button {
  width: 70px;
  height: 27px;
  margin-left: 10px;
  background: white;
  border-radius: 14px;
  color: #e5e5e5;
  border: 1px solid #e5e5e5;
  font-size: 11px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 27px;
  white-space: nowrap;
  padding: 0;
}
.selected-button {
  color: #3b8afe;
  border: 1px solid #3b8afe;
}
</style>
