<template>
  <div class="searchPage">
    <div>
      <van-search v-model="keyWords" placeholder="请输入搜索关键词" @input="searchClick" />
    </div>
    <div>
      <h3>历史搜索</h3>
      <options :options="historyList" v-model="historyOptions" :isMultiply="true"></options>
    </div>
    <div>
      <h3>你可能想搜</h3>
      <options :options="wantList" v-model="wantOptions" :isMultiply="true"></options>
    </div>
    <div>
      <h3>热销险种</h3>
      <div class="pageList" v-for="(item, i) in listOptions" :key="i">
        <div class="pageListImg">
          <img src alt />
        </div>
        <div class="pageListRight">
          <div class="pageListTitle">{{ item.title }}</div>
          <p>{{ item.introduce }}</p>
          <span>已售 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ item.salesvolume }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import options from "@/components/options.vue";
import { Search } from "vant";
import { getQuerycustomeromerList } from "../../../http/module/customerApi";

export default {
  components: {
    options,
    [Search.name]: Search
  },
  data() {
    return {
      keyWords: "",
      historyOptions: [],
      historyList: [
        "价格敏感",
        "关注理赔",
        "非车险产品偏好",
        "喜欢理财",
        "高旅行为多"
      ],
      wantOptions: [],
      wantList: ["非车险客户", "哈哈哈哈", "呵呵呵呵", "来啊来啊的"],
      listOptions: [
        {
          img: "",
          title: "百万航意 出行无忧",
          introduce: "大是大非阿斯蒂芬大是大非阿斯蒂大是大非阿斯蒂",
          salesvolume: "888"
        },
        {
          img: "",
          title: "百万航意 出行无忧",
          introduce: "大是大非阿斯蒂芬大是大非阿斯蒂大是大非阿斯蒂",
          salesvolume: "888"
        },
        {
          img: "",
          title: "百万航意 出行无忧",
          introduce: "大是大非阿斯蒂芬大是大非阿斯蒂大是大非阿斯蒂",
          salesvolume: "888"
        },
        {
          img: "",
          title: "百万航意 出行无忧",
          introduce: "大是大非阿斯蒂芬大是大非阿斯蒂大是大非阿斯蒂",
          salesvolume: "888"
        }
      ],

      querycustomeromerList: {
        //搜索
        letterList: [
          //字母列表
        ],
        customerList: [
          //客户列表
          {
            custNo: "", //客户号
            customerName: "", //客户名称
            sex: "", //性别
            mobileNum: "", //手机号
            customerTag: "", //客户标签
            customerLevel: "", //客户星级
            isReg: "", //是否注册
            birthStatus: "", //生日提醒状态
            taskStatus: "" //提醒状态
          }
        ]
      }
    };
  },

  methods: {
    //搜索
    async searchClick(value) {
      let params = {
        searchCondition: "", //筛选条件
        searchFuzzy: value, //模糊搜索字段
        custType: "", //客户类型
        userCode: "" //企业号
      };

      let res = getQuerycustomeromerList(params);
      //console.log(res);
    }
  }
};
</script>
<style lang="less" scoped>
.searchPage {
  padding: 7px 15px 0 15px;
  h3 {
    padding: 18px 0 10px 0;
    font-size: 15px;
    font-family: PingFang-SC-Medium, PingFang-SC;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    margin-bottom: 11px;
  }
  .pageList {
    display: flex;
    margin-bottom: 12px;
    .pageListImg {
      width: 89px;
      height: 90px;
      border-radius: 3px;
      background: pink;
      margin-right: 13px;
      img {
        width: 100%;
      }
    }
    .pageListRight {
      width: 240px;
      display: flex;
      flex-direction: column;
      padding-top: 3px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      font-size: 14px;
      .pageListTitle {
        font-size: 18px;
        color: #333333;
      }
      p {
        color: rgba(144, 144, 144, 1);
        margin: 7px 0 15px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
      span {
        color: rgba(253, 101, 98, 1);
      }
    }
  }
}
</style>
