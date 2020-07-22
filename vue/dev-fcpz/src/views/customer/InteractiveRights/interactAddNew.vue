<template>
  <body>
    <globalNav title="互动纪录" />
    <div class="hello">
      <div class="top">
        <div class="lef">可自定义备注</div>
        <div class="rig">
          <h3 @click="addNewOne">新增</h3>
        </div>
      </div>
      <div class="separate"></div>
      <div class="bottom">
        <ul v-for="(item, index) in datas" v-bind:key="index">
          <li>
            <div class="col">
              <div class="leftContent">
                <p class="title">{{ item.title }}</p>
                <p>{{ item.date }}</p>
                <p>{{ item.remark }}</p>
              </div>
              <div class="edit" @click="clickItemEdit(item)">
                <div class="colSep"></div>
                <p>编辑</p>
              </div>
            </div>
            <div class="rowSep"></div>
          </li>
        </ul>
      </div>
    </div>
  </body>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
import {
  getCustomerInteractions,
  addOrUpdateInteractions
} from "@src/http/module/customerApi.js";

export default {
  name: "interactAddNew",
  components: {
    globalNav
  },
  props: {
    userCode: String, //业务号
    custNo: String //客户号
  },
  data() {
    return {
      datas: []
    };
  },

  created() {
    this.queryInteractions();
  },

  methods: {
    clickItemEdit: function(item) {
      //console.log("点击了一下编辑：" + item.title);
    },

    addNewOne: function() {
      //console.log("点击了一下新增");
    },

    //查询互动纪录
    async queryInteractions() {
      let params = {
        userCode: this.userCode, //业务号
        custNo: this.custNo //客户号
      };
      let res = await getCustomerInteractions(params);
      //console.log(res);
      if (res != undefined && res.code == "1") {
        let yearList = JSON.parse(JSON.stringify(res.data));
        var interactions = [];
        for (var i = 0; i < yearList.length; i++) {
          for (var k = 0; k < yearList[i].monthList.length; k++) {
            for (
              var p = 0;
              p < yearList[i].monthList[k].chatrecordList.length;
              p++
            ) {
              let item = yearList[i].monthList[k].chatrecordList[p];
              interactions.push(item);
            }
          }
        }
        this.data = interactions;
      }
    },

    async addOrUpdateInteractions(params) {
      // let params = {
      //   custNo: "2020101010233131313", //客户号
      //   remarkId: "", //记录id（为空时新增，非空时修改）
      //   type: "1",    //互动类型（1.打电话、2.在线联系、3.续保报价、4.线上活动转发、5.生日提醒、客户关怀（包括6.权益提醒、7.出险提醒，8.未注册提醒）、9.查违章、10.协助报案、11.理赔查询、12.自定义）
      //   diyTitle: "", //自定义标题(新增自定义互动记录时必传)
      //   remark: "",   //备注
      //   userCode:'',//业务号
      //   year: "" ,    //创建互动记录年份
      //   month: "" ,   //创建互动记录月份
      // };
      let res = await addOrUpdateInteractions(params);
      //console.log(res);
      if (res != undefined && res.code == "1") {
        let response = JSON.parse(JSON.stringify(res.data));
      }
    }
  }
};
</script>
<style scoped>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}
p {
  /* height: 90px; */
  font-size: 12px;
  font-weight: 400;
  color: #666666;
  /* line-height: 90px; */
}

h3 {
  overflow: hidden;
  text-align: center;
}
body {
  background: rgba(247, 247, 247, 1);
}
.top,
.bottom {
  background: white;
  width: 100%;
}
.top {
  margin-top: 44px;
  height: 58px;
  /* background-color: yellow; */
  display: inline-block;
}
.col {
  height: 88px;
  /* display: inline-block; */
}
.leftContent {
  /* background-color: red; */
  width: 70%;
  float: left;
  text-align: left;
  text-indent: 1.5em;
  height: 87px;
}
.lef {
  width: 40%;
  height: 58px;
  float: left;
  line-height: 58px;
  text-align: left;
  text-indent: 1.5em;
}
.rig {
  float: right;
  width: 60%;
  height: 28px;
  margin-top: 15px;
  min-width: 65px;
}
.rig > h3 {
  width: 65px;
  height: 28px;
  background-color: #3b8afe;
  margin: 0 auto;
  margin-right: 15px;
  border-radius: 30px;
  font-size: 13px;
  font-weight: 400;
  line-height: 28px;
  color: white;
}
/* .colSep, */
.edit {
  float: right;
}
.colSep {
  width: 1px;
  height: 58px;
  margin: 0 auto;
  margin-top: 14px;
  margin-left: 0px;
  /* margin-right: 75px; */
  background: #f7f7f7;
  float: left;
  /* right: 0px; */
}
.rowSep {
  background: #f7f7f7;
  height: 1px;
  margin-left: 16px;
  margin-right: 0px;
}
.edit {
  width: 74px;
  height: 87px;
  line-height: 87px;
  text-align: center;
  margin: 0 auto;
  margin-top: 0px;
  margin-right: 0px;
  font-size: 12px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}
.edit > p {
  float: right;
  width: 73px;
  height: 87px;
}
.title {
  font-size: 14px;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  line-height: 40px;
}
.separate {
  height: 26px;
}

.space {
  height: 20px;
}
</style>
