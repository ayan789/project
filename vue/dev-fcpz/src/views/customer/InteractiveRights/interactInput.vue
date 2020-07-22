<template>
<body>
  <globalNav title="新增互动纪录" />
  <div class="top">自定义备注</div>
  <div>
    <ul>
      <li>
        <h3>主题</h3>
        <input type="text" placeholder="请输入" v-model="themeTitle" />
      </li>
      <div class='line'></div>
      <li>
        <h3>创建时间</h3>
        <input type="text" placeholder="请输入" v-model="createTime" />
      </li>
      <div class='line'></div>
      <li>
        <h3>备注</h3>
        <input type="text" placeholder="请输入" v-model="moreInfo" />
      </li>
      <div class='line'></div>
    </ul>
  </div>
  <div class="add" @click="addNewOne">新增</div>
</body>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "interactInput",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      themeTitle: "",
      createTime: "",
      moreInfo: ""
    };
  },
  methods: {
    clickItemEdit: function(index) {
      //console.log("点击了一下编辑：" + index);
    },

    addNewOne: function() {
      //console.log("点击了一下新增:"+this.themeTitle+';'+this.createTime+';'+this.moreInfo);
    }
  }
};
</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  list-style: none;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  overflow: hidden;
}
h3 {
  float: left;
  /* margin-left: 0; */
  width: 25%;
  margin: 0 auto;
  text-align: left;
  margin-left: 0px;
  /* background-color: yellow; */
  height: 48px;
  line-height: 48px;
  color: #333333;
  text-indent: 1em;
}
.line{
  height: 1px;;
  margin-left: 15px;
  background-color: rgba(229,230,232,1);

}
ul {
  display: inline-block;
  width: 100%;
}
li {
  height: 48px;
}
input {
  float: right;
  width: 70%;
  /* background-color: blue; */
  text-align: right;
  padding-right: 10px;
  height: 48px;
  line-height: 48px;
  border-radius: 0px;
  border-width: 0px;
  overflow: hidden;
  border: 0;
  outline: none;
  color:#333333;
  font-size: 14px;
}

input::-webkit-input-placeholder {
  color: #c1c1c1;
  font-size: 14px;
  text-align: right;
}
.top {
  margin-top: 44px;
  width: 100%;
  height: 41px;
  text-indent: 1.5em;
  font-size: 12px;
  font-weight: 400;
  background: rgba(247, 247, 247, 1);
  color: rgba(168, 167, 167, 1);
  line-height: 41px;
  text-align: left;
}
.add {
  text-align: center;
  height: 46px;
  position: fixed;
  z-index: 99;
  left: 15px;
  right: 15px;
  bottom: 242px;
  background-color: #3b8afe;
  line-height: 46px;
  border-radius: 46px;
  font-size: 16px;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
}

</style>
