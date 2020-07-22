<template>
  <div id="app">
    <div id="father">
      <img src="../../../assets/customer/InteractiveRights/robot.png" />
      <div id="message">
        <div id="title">{{ message }}</div>
        <div class="hor"></div>
        <div id="bottom">
          <div class="button product" @click="showProduct">了解产品</div>
          <div class="sel"></div>
          <div class="button price" @click="sharePrice">分享报价</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//  销售助手
export default {
  name: "salesAssistant",
  components: {},
  data() {
    return {
      message:
        "根据大数据分析显示，当前客户可能投保百万医疗的几率较高，请主动联系客户为其投保。"
    };
  },
  methods: {
    showProduct: function() {
      //console.log("了解产品");
      //  this.$router.push({ path:'../portrait/PortraitUpload.vue'  })
    },
    sharePrice: function() {
      //console.log("分享报价");
      //  this.$router.push({ path:'/two.html'  })
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
#app {
  background: #fafafa;
  height: 100%;
  width: 100%;
  position: absolute;
  margin: 0 auto;
}

#father {
  /* background: yellow; */
  height: 150px;
  width: 100%;
  margin: 0 auto; /*水平居中*/
  position: absolute;
  top: 50%; /*偏移*/
  transform: translateY(-50%);
}
#title {
  text-align: left;
  text-indent: 1.5em;
  font-size: 12px;
  line-height: 20px;
  padding: 10px;
}
#message {
  background: white;
  box-shadow: 0px 4px 14px 0px rgba(4, 14, 28, 0.04);
  border-radius: 10px;
  margin: 30px 92px 0 34px;
}
.button {
  color: #0d84f2;
  font-size: 10px;
  text-align: center;
  width: 49%;
  height: 32px;
  line-height: 32px;
}
#bottom {
  height: 36px;
  width: 100%;
}
.hor {
  background: #f7f7f7;
  height: 1px;
  margin: 8px 0 8px 0;
}
.sel {
  background: #f7f7f7;
  width: 1px;
  height: 21px;
  margin-top: 8px;
  float: left;
}
.product,
.sel {
  float: left;
}
.price {
  float: right;
}
.hor {
  height: 1px;
  margin: 5px 10px 0px 10px;
  /* float: none; */
}
img {
  width: 56px;
  height: 56px;
  object-fit: fill;
  border-radius: 56px;
  background-color: red;
  right: 30px;
  position: fixed;
  z-index: 99;
}
</style>
