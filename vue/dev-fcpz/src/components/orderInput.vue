<template>
  <div class="super">
    <div id="father">
      <textarea
        maxlength="60"
        cols="30"
        rows="10"
        placeholder="请输入备注信息"
        v-model="inputMessage"
        v-on:input="inputText"
      ></textarea>
      <h3>{{ inputNumber }}/60</h3>
    </div>
    <div id="bottom">
      <div id="clear" @click="cancelAction">取消</div>
      <div id="col"></div>
      <div id="sure" @click="saveAction">保存</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "orderInput",
  data() {
    return {
      inputMessage: "",
      inputNumber: 0
    };
  },
  methods: {
    saveAction: function() {
       this.$emit("saveEvent",this.inputMessage);
    },
    cancelAction: function() {
      this.$emit('cancel');
    },
    inputText: function() {
      this.inputNumber = this.inputMessage.length;
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
#father {
  width: 100%;
  height: 153px;
  border: 1px solid rgba(229, 229, 229, 1);
  border-radius: 5px;
}
textarea {
  margin: 15px 15px 0 15px;
  resize: none;
  width: calc(100% - 30px);
  border: none;
  outline: 0;
  display: block;
  height: 101px;
  overflow: auto;
  display: block;
  line-height: 18px;
  color: #999999;
}
#father > :last-child {
  margin: 12px 15px;
  text-align: right;
  height: 17px;
  font-size: 12px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 17px;
}
#bottom {
  height: 46px;
  text-align: center;
}
#clear,
#sure {
  width: calc(50% - 1px);
  float: left;
  height: 46px;
  line-height: 46px;
  font-size: 16px;
}
#sure {
  color: rgba(59, 138, 254, 1);
  float: right;
}
#col {
  float: left;
  width: 1px;
  background: #eaeaea;
  height: 46px;
}

#clear {
  background: white;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
}
</style>
