<template>
  <div class="setAuto-">
    <div class="setAuto-head">
      <span @click="$emit('update:showSetAuto', false)"
        ><van-icon name="arrow-left"
      /></span>
      <span>提现模式</span>
    </div>
    <!-- <div class="isAuto">
      <span></span>
      <van-switch v-model="checked" />
    </div> -->
    <van-row type="flex" justify="space-between" align="center" class="isAuto">
      <van-col>是否自动提现</van-col>
      <van-col><van-switch v-model="checked" size="22px"/></van-col>
    </van-row>
    <van-row
      type="flex"
      justify="space-between"
      align="center"
      class="frequency"
      :style="{ visibility: checked ? '' : 'hidden' }"
    >
      <van-col>提现频率</van-col>
      <van-col>
        <van-button
          v-for="item in costFrequency"
          :key="item.value"
          @click="save(item.value)"
          :class="[item.value === queryData.frequency ? 'actived' : '']"
          >{{ item.label }}</van-button
        >
      </van-col>
    </van-row>
    <!-- <van-button type="info" size="large" class="subimt" @click="save"
      >保存</van-button
    > -->
  </div>
</template>

<script>
import { Switch } from "vant";
import {
  queryWithdrawalConfig,
  updateConfig
} from "@src/http/module/performance.js";
export default {
  props: ["showSetAuto"],
  components: {
    [Switch.name]: Switch
  },
  data: () => ({
    checked: false,
    costFrequency: [
      { value: "1", label: "日" },
      { value: "2", label: "周" },
      { value: "3", label: "月" }
    ],
    queryData: {
      frequency: "",
      isAutomatic: "",
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
      agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
      handlerCode:
        JSON.parse(localStorage.getItem("userInfo")).handlerCode || ""
    }
  }),
  watch: {
    checked(val) {
      if (!val) {
        this.save();
      }
    }
  },
  created() {
    //初始化查询提现频率
    queryWithdrawalConfig({
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
      agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
      handlerCode:
        JSON.parse(localStorage.getItem("userInfo")).handlerCode || ""
    }).then(res => {
      if (res.code === "1") {
        this.checked =
          res.data.queryConfigResponseVO.isAutomatic === "1" ? true : false;
        this.queryData.frequency = res.data.queryConfigResponseVO.frequency;
      }
    });
  },
  methods: {
    //更新提现频率
    async save(value) {
      this.queryData.frequency = value;
      this.queryData.isAutomatic = this.checked ? "1" : "2";
      await updateConfig(this.queryData).then(res => {
        this.$dialog.alert({
          message: `${res.data.detail[0].message}`,
          callback: () => {
            this.$emit("update:showSetAuto", false);
            this.$emit("refreshList");
          }
        });
      });
    }
  },
  destroyed() {
    //重置数据
    this.checked = false;
    this.queryData.costFrequency = "";
  }
};
</script>
<style lang="less" scoped>
.setAuto- {
  background: #f5f5f5;
  height: 100%;
  .setAuto-head {
    padding: 0 16px 0 0;
    background: #fff;
    height: 64px;
    line-height: 64px;
    text-align: center;
    span {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
    span:nth-child(1) {
      float: left;
      margin-left: 16px;
    }
    span:nth-child(2) {
      margin-right: 16px;
    }
  }
  .isAuto {
    background: #fff;
    padding: 10px 15px;
    font-size: 14px;
    margin-bottom: 10px;
  }
  .frequency {
    padding: 10px 15px;
    background: #fff;
    font-size: 14px;
    /deep/.van-button {
      margin-right: 10px;
      width: 49px;
      height: 27px;
      line-height: 27px;
      border-radius: 3px;
      border: 1px solid rgba(238, 238, 238, 1);
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(185, 185, 185, 1);
    }
    .actived {
      width: 49px;
      height: 27px;
      background: rgba(238, 245, 255, 1);
      border-radius: 3px;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(43, 121, 237, 1);
      border: none;
    }
  }
  .subimt {
    position: fixed;
    bottom: 0;
  }
}
</style>
