<template>
  <div id="coverage">
    <!-- <van-nav-bar title="健康及职业告知" /> -->
    <globalNav :title="globalNav.title"></globalNav>
    <div class="title-container">
      <van-row type="flex" class="coverage-item">
        <van-col><div class="coverage-tag">重要</div></van-col>
        <van-col class="coverage-title">
          为保证被保人的保险权益在理赔时不受影响，请确认被保人健康及职业状况是否符合投保条件
        </van-col>
      </van-row>
      <van-row
        v-for="(item, index) in items"
        :key="index"
        type="flex"
        justify="flex-start"
        align="flex-start"
        class="coverage-item item-container"
      >
        <van-col class="coverage-content">{{ index + 1 }}、</van-col>
        <van-col class="coverage-content">
          <div v-for="(text, index) in item.split('\n')" :key="index">
            {{ text }}
          </div>
        </van-col>
      </van-row>
    </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    items: [
      "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。\n恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。",
      "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。\n恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
    ],
    globalNav: {
      title: "健康及职业告知"
    }
  }),
  methods: {}
};
</script>

<style scoped>
#coverage {
  background-color: #f7f7f7;
  overflow: hidden;
}
.coverage-item {
  padding: 15px;
}
.title-container {
  padding: 15px;
  margin: 60px 0 10px 0;
  background-color: white;
}
.item-container {
  background-color: white;
}
.coverage-tag {
  font-size: 9px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  line-height: 13px;
  padding: 4px 4px 0px 4px;
  margin-top: 4px;
  background-color: #3b8afe;
  border-radius: 2px;
  margin-right: 6px;
  white-space: nowrap;
}
.coverage-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  line-height: 22px;
}
.coverage-content {
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 18px;
}
</style>
