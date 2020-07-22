<template>
  <div>
    <van-popup
      v-model="addDialog"
      @click-overlay="$emit('update:addDialog', false)"
    >
      <div class="add-conent">
        <p class="title">联系日期</p>
        <p @click="show = true">{{ date }}</p>
        <p class="title">联系内容</p>
        <van-field
          v-model="addRecordData.recordContent"
          rows="2"
          autosize
          type="textarea"
          placeholder="请输入"
          show-word-limit
          maxlength="50"
        >
        </van-field>
      </div>
      <div class="add-button">
        <van-button type="default" @click="$emit('update:addDialog', false)"
          >取消</van-button
        >
        <van-button type="info" @click="save">保存</van-button>
      </div>
    </van-popup>
    <van-popup v-model="show" position="bottom" :overlay="false">
      <van-datetime-picker
        v-model="showDate"
        type="date"
        @cancel="show = false"
        @confirm="
          date => {
            this.date = this.formatDate(date);
            this.$set(this.addRecordData, 'createTime', date);
            this.show = false;
          }
        "
      />
    </van-popup>
  </div>
</template>

<script>
import { Popup, Calendar, Toast } from "vant";
//新增跟踪记录
// import { insertRenewTraceRecord } from "@src/http/module/renewal.js";
export default {
  props: {
    addDialog: Boolean
  },
  components: {
    [Popup.name]: Popup,
    [Calendar.name]: Calendar,
    [Toast.name]: Toast
  },
  data: () => ({
    minDate: new Date(2019, 0, 0),
    showDate: new Date(),
    show: false,
    date: "请选择",
    addRecordData: {
      createTime: "", //创建时间
      recordContent: "" //跟踪记录
    },
    assginObj: {
      showCalendar: false, //是否显示日历控件
      readonlyInput: true //是否只读，长按后可编辑
    }
  }),
  destroyed() {
    this.clearData();
  },
  methods: {
    formatDate(startDate) {
      let now = startDate;
      let year = now.getFullYear(); //年
      let month = now.getMonth() + 1; //月
      let day = now.getDate(); //日
      let clock = year + "-";
      if (month < 10) clock += "0";
      clock += month + "-";
      if (day < 10) clock += "0";
      clock += day;
      return clock;
    },
    clearData() {
      this.date = "请选择";
      this.$set(this.addRecordData, "recordContent", "");
    },
    save() {
      if (!this.addRecordData.createTime || !this.addRecordData.recordContent) {
        this.$toast({
          message: "联系日期和联系内容不可为空！"
        });
        return false;
      }
      this.addRecordData = Object.assign(this.addRecordData, this.assginObj);
      this.$emit("update:addDialog", false);
      this.$emit("add", { ...this.addRecordData });
    }
  }
};
</script>

<style lang="less" scoped>
.add-conent {
  width: 305px;
  background: rgba(255, 255, 255, 1);
  border-radius: 10px;
  padding: 20px 15px 0 15px;
  .title {
    font-size: 14px;
    font-family: PingFang-SC-Medium, PingFang-SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    margin-bottom: 10px;
  }
  & > p:nth-child(2) {
    width: 172px;
    height: 36px;
    line-height: 36px;
    background: rgba(245, 245, 245, 1);
    border-radius: 4px;
    font-size: 14px;
    font-family: PingFang-SC-Medium, PingFang-SC;
    font-weight: 500;
    color: rgba(193, 193, 193, 1);
    padding-left: 13px;
    margin-bottom: 20px;
  }
  /deep/ .van-cell {
    padding: 0;
    background: rgba(245, 245, 245, 1);
  }
  /deep/.van-field__body {
    padding: 10px;

    border-radius: 4px;
  }
}
.add-button {
  margin-top: 20px;
  /deep/.van-button {
    width: 50%;
  }
  /deep/.van-button--default {
    height: 46px;
    background: #ffffff;
    border: 1px solid rgba(229, 229, 229, 1);
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
  }
  /deep/.van-button--info {
    height: 46px;
    background: #ffffff;
    border: 1px solid rgba(229, 229, 229, 1);
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
  }
}
</style>
