<template>
  <div id="safeguard-detail" sticky>
    <!-- <van-nav-bar title="保障详情" /> -->
    <globalNav :title="globalNav.title"></globalNav>
    <div class="title-container">
      <van-tabs
        line-width="28px"
        line-height="5px"
        color="#3B8AFE"
        v-model="activeTab"
      >
        <van-tab
          v-for="tab in tabs"
          :title="tab"
          :name="tab"
          :key="tab"
          :title-style="activeTab === tab ? 'color:red;' : ''"
        >
          <div id="tab-content">
            <van-cell
              v-for="item in info[tab]"
              :key="item.title"
              :label="item.label"
            >
              <template slot="title">
                <van-row
                  type="flex"
                  justify="space-between"
                  align="center"
                  class="item-title"
                >
                  <van-col>{{ item.title }}</van-col>
                  <van-col>{{ item.value }}</van-col>
                </van-row>
              </template>
            </van-cell>
          </div>
        </van-tab>
      </van-tabs>
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
    activeTab: "基础版",
    tabs: ["基础版", "升级版", "无忧版"],
    globalNav: {
      title: "保障详情"
    },
    info: {
      基础版: [
        {
          title: "一般医疗",
          value: "300万",
          label:
            "包括住院医疗费用、特殊门诊医疗费用、一般门诊急诊医疗费用。扣除免赔后，按照100%比例报销。"
        },
        {
          title: "恶性肿瘤或重大手术疾病医疗",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        },
        {
          title: "公共交通意外伤害-飞机",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        }
      ],
      升级版: [
        {
          title: "一般医疗",
          value: "300万",
          label:
            "包括住院医疗费用、特殊门诊医疗费用、一般门诊急诊医疗费用。扣除免赔后，按照100%比例报销。"
        },
        {
          title: "恶性肿瘤或重大手术疾病医疗",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        },
        {
          title: "公共交通意外伤害-飞机",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        }
      ],
      无忧版: [
        {
          title: "一般医疗",
          value: "300万",
          label:
            "包括住院医疗费用、特殊门诊医疗费用、一般门诊急诊医疗费用。扣除免赔后，按照100%比例报销。"
        },
        {
          title: "恶性肿瘤或重大手术疾病医疗",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        },
        {
          title: "公共交通意外伤害-飞机",
          value: "300万",
          label:
            "包括恶性肿瘤住院医疗费用、恶性肿瘤特殊门诊医疗用费恶性肿瘤一般门诊急诊医疗费用。发生恶性肿瘤后先在一般医疗保险金报销，报销完之后再在恶性肿瘤保险金下报销。恶性肿瘤津贴：本产品的恶性肿瘤津贴为1万元。被保险人在等待期后经医院确诊初次发生恶性肿瘤的，我们按照恶性肿瘤津贴保险金额给付恶性肿瘤津贴保险金，本项保险责任终止。"
        }
      ]
    }
  }),
  methods: {}
};
</script>

<style scoped>
#safeguard-detail {
  background-color: #f7f7f7;
  overflow: hidden;
}
#tab-content {
  padding-top: 10px;
}
.title-container {
  padding: 15px;
  margin: 60px 0 10px 0;
  background-color: white;
}
.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
</style>
