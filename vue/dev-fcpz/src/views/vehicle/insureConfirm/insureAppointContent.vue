<template>
  <div class="container">
    <globalNav @clickBack="$router.back()" title="约定内容" />
    <div id="father">
      <textarea
        maxlength="200"
        cols="30"
        rows="10"
        placeholder="请输入约定内容"
        v-model="inputMessage"
        v-on:input="inputText"
      ></textarea>
      <h3 class="hintNum">{{ inputNumber }}/200</h3>
    </div>
    <div id="bottom">
      <div id="clear" @click="clearAll">清空</div>
      <div id="sure" @click="sureAction">确定</div>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "insureAppointContent",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      inputMessage: "",
      inputNumber: 0
    };
  },
  methods: {
    clearAll: function() {
      //console.log("清空");
      this.inputMessage = "";
      this.inputNumber = 0;
    },
    sureAction: function() {
      //console.log("确定");
    },
    inputText: function() {
      this.inputNumber = this.inputMessage.length;
    }
  }
};
</script>
<style scoped>
.container {
  width: 100%;
  height: 100%;
  position: absolute;
  background: white;
}
#father {
  background: white;
  margin: 84px 15px 15px 15px;
  /* calc() = calc(四则运算)
需要注意的是，运算符前后都需要保留一个空格，例如：height: calc(100vh - 10px)；
如果写成 height：calc(100vh-10px); 是不会生效的奥 */
  width: calc(100% - 30px);
  height: 225px;
  border: 1px solid rgba(229, 229, 229, 1);
  border-radius: 5px;
}

textarea {
  margin: 15px;
  resize: none;
  /* calc() = calc(四则运算)
需要注意的是，运算符前后都需要保留一个空格，例如：height: calc(100vh - 10px)；
如果写成 height：calc(100vh-10px); 是不会生效的奥 */
  width: calc(100% - 30px);
  border: none;
  outline: 0;
  display: block;
  height: 169px;
  overflow: auto;
  display: block;
  line-height: 20px;
}

.hintNum {
  text-align: right;
  margin-right: 10px;
  padding-bottom: 10px;
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
  margin: 30px 15px;
  height: 46px;
  text-align: center;
}
#clear,
#sure {
  width: calc(50% - 7.5px);
  border-radius: 46px;
  float: left;
  height: 46px;
  line-height: 46px;
  font-size: 16px;
}
#sure {
  background: rgba(59, 138, 254, 1);
  color: white;
  float: right;
}

#clear {
  background: white;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  border: 1px solid rgba(200, 201, 204, 1);
}
</style>
