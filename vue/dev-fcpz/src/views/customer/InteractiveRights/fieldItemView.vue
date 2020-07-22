<template>
  <div class="itemStyle">
    <div class="leftContent">
      <p class="interactEdit_title">{{ item.title }}</p>
      <van-field
        v-model="value_"
        label="备注:"
        key="index"
        center
        clearable
        rows="1"
        type="textarea"
        ref="input"
        :disabled="statusValue"
        :placeholder="item.remark">
      </van-field>
      <p class="timeStyle">{{ item.createTime }}</p>
    </div>

    <div style="width: 40px">
      <p class="editStyle" v-if="statusValue" @click="changeStatus">编辑</p>
      <p class="editStyle" v-else @click="clickItemEdit(item)">完成</p>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { Toast } from "vant";
export default {
  name: "fieldItemView",
  data() {
    return {
      value_: "",
      statusValue: true
    };
  },
  props: {
    item: Object
  },

  computed: {
    ...mapState(["customerData"])
  },

  methods: {
    clickItemEdit(item) {
      if (this.value_.trim()) {
        this.statusValue = !this.statusValue;
        item.remark = this.value_.trim();
        this.$emit('clickItemEdit', item);
        this.value_ = "";
      } else {
        Toast('请先录入备注');
      }
    },
    changeStatus() {
      this.statusValue = !this.statusValue;
      this.$refs.input.focus();
      this.value_ = this.item.remark;
    }
  }
};
</script>

<style scoped>
.itemStyle {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px 20px;
  width: 90%;
}

.timeStyle {
  color: #999999;
  font-size: 12px;
}

.interactEdit_title {
  color: #333333;
  font-size: 14px;
  font-weight: bold;
}

.leftContent {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  width: 80%;
}

.itemStyle >>> .van-field__body {
  background-color: #F5F5F5;
  padding: 5px;
  border-radius: 7px;
}

.editStyle {
  color: #3B8AFE;
  font-size: 12px;
}

.itemStyle >>> .van-field__control {
  min-width: 10%;
  color: #545454;
}
</style>
