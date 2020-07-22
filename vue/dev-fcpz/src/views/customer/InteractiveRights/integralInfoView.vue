<template>
  <div class="queryRemindView">
    <navigationItem title="星级权益"/>
    <div class="contentStyle">
      <div class="queryRemindView_itemView" v-for="(item,index) in levelList">
        <div class="leftItem">
          <span class="leftTitle">{{item.integralTitle}}</span>
          <span class="leftText">有效期：{{item.startDate}}至{{item.endDate}}</span>
        </div>
        <span class="rightText">
          + {{item.addIntegral}}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getQueryCoreInfo
} from "../../../http/module/customerApi";
import navigationItem from "../followUpremind/navigationItem";
export default {
  name: "integralInfoView",
  components: {
    navigationItem
  },
  data() {
    return {
      levelList: [
        {
          addIntegral: "",
          integralType: ""
        }
      ]
    };
  },

  async mounted() {
    const { comCode, custNo } = JSON.parse(this.$route.query.item);
    let params = {
      custNo: custNo
    };
    let res = await getQueryCoreInfo(params);
    this.levelList = res.data;
    this.levelList.map((v, i) => {
      switch (v.integralType) {
        case '1001':
          v.integralTitle = '保费收入';
          break;
        case '1002':
          v.integralTitle = '续保';
          break;
        case '1003':
          v.integralTitle = '未出险';
          break;
        case '1004':
          v.integralTitle = '客户信息完善';
          break;
        case '1005':
          v.integralTitle = '参加客户活动';
          break;
        case '1006':
          v.integralTitle = '客户推荐';
          break;
        case '1007':
          v.integralTitle = '未出险每月签到';
          break;
        case '1009':
          v.integralTitle = '未出险(出险一次)';
          break;
        case '1010':
          v.integralTitle = '未出险(出险两次)';
          break;
        case '1008':
          v.integralTitle = '首次购买';
          break;
      }
    });
    //console.log(res, 'jjj');
  },

  methods: {
    clickBack() {
      this.$router.back();
    },
  }
};
</script>

<style scoped>
.queryRemindView {
  position: fixed;
  width: 100%;
}

.contentStyle {
  background: #F6F6F6;
  margin-top: 80px;
  overflow: scroll;
  height: calc(100vh - 85px);
  display: flex;
  padding: 0 10px;
  flex-direction: column;
}

.queryRemindView_itemView {
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin: 5px 0px;
  flex-direction: row;
  padding: 10px;
}

.leftItem {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  flex-direction: column;
}

.leftTitle {
  color: #666666;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
}

.leftText {
  color: #999999;
  font-size: 12px;
}

.rightText {
  font-size: 16px;
  color: #666666;
  font-weight: bold;
}
</style>
