<template>
  <div id="insuranceProducts">
    <globalNav title="保单列表" v-on:clickBack="back()" />
    <scroll
      :data="getList"
      :pulldown="pulldown"
      :pullup="true"
      @pulldown="getData(true)"
      @scrollToEnd="getData()"
      :probeType="setType"
    >
      <div class="title-container">
        <div
          class="divBox"
          v-for="(item, index) in getList"
          :key="index"
          @click="onSubmit(item.policyNo)"
        >
          <div class="divLine">
            <span class="divLable" style="color:#333;font-weight:550"
              >投保人：</span
            >
            <span class="divContent" style="color:#333;font-weight:bolder">{{
              item.custName
            }}</span>
            <van-icon
              name="arrow"
              class="divDetail"
              color="#BFBFBF"
              @click="onSubmit(item.policyNo)"
            />
          </div>
          <div class="divLine">
            <span class="divLable">客户等级：</span>
            <span class="divContent">
              <span v-for="(items, index) in levelData" :key="index">
                <img
                  v-if="
                    (item.clsLevel && Number(item.clsLevel) > items) ||
                      Number(item.clsLevel) == items
                  "
                  class="level"
                  src="../../../assets/customer/customerEquity/fivePoint@3x.png"
                />
                <img
                  v-else
                  class="level"
                  src="../../../assets/customer/customerEquity/fivePointNull@3x.png"
                />

                <!-- {{item.clsLevel }} -->
              </span>
            </span>
          </div>
          <div class="divLine">
            <span class="divLable">保单号：</span>
            <span class="divContent">{{ item.policyNo }}</span>
          </div>
          <div class="divLine">
            <span class="divLable">终止日期：</span>
            <span class="divContent">{{ item.respEndDate | date }}</span>
          </div>
        </div>
        <p class="isBottom" v-if="isBottom">
          --------------我也是有底线的--------------
        </p>
      </div>
    </scroll>
  </div>
</template>

<script>
import globalNav from "@/components/globalNav.vue";
import scroll from "@/components/scroll.vue";
import Vue from "vue";
import { Toast, PullRefresh, List } from "vant";
import { querylist } from "@src/http/module/product.js";
Vue.use(PullRefresh);
Vue.use(List);
export default {
  components: {
    [PullRefresh.name]: PullRefresh,
    [List.name]: List,
    globalNav,
    scroll
  },
  data() {
    return {
      pulldown: true,
      isBottom: false,
      setType: 1,
      levelData: [1, 2, 3, 4, 5],
      refreshing: true,
      finished: true,
      getList: [],
      getType: "",
      queryData: {
        userCode: localStorage.getItem("userCode"),
        licenseNo: "",
        custName: "",
        insuranceType: "1",
        insuredName: "",
        policyNo: "",
        minStartDate: "",
        maxStartDate: "",
        minEndDate: "",
        maxEndDate: "",
        pageSize: "10",
        pageNum: "1"
      }
    };
  },
  filters: {
    date(date) {
      if (date && typeof date == "string" && date.length) {
        return date.substring(0, 10);
      }
      return "";
    }
  },
  computed: {
    getD: function() {
      return JSON.parse(this.$route.query.listItem);
    }
  },
  created() {
    // this.getData();
  },
  mounted() {
    this.queryData = this.getD;
    this.getData();
  },
  methods: {
    back() {
      this.$globalBack();
    },
    async getData(isRefresh) {
      if (this.isBottom) return;
      if (isRefresh) {
        this.getList = [];
      }
      // this.queryData.pageNum = 6;
      this.getType = JSON.parse(this.$route.query.listItem).insuranceType;
      // await querylist(JSON.parse(this.$route.query.listItem))
      await querylist(this.queryData)
        .then(res => {
          if (res.code === "1") {
            if (res.data.content.length == 0) {
              Toast("查不到数据");
            } else {
              if (
                Number(this.queryData.pageNum) < res.data.totalPages &&
                res.data.content.length == 10
              ) {
                this.getList = [...this.getList, ...res.data.content];
                this.queryData.pageNum++;
              } else if (
                Number(this.queryData.pageNum) == res.data.totalPages
              ) {
                if (this.isBottom) return;
                this.getList = [...this.getList, ...res.data.content];
                this.isBottom = true;
              }
            }
          } else {
            Toast(res.msg);
          }
        })
        .finally(() => {
          this.refreshing = false;
          this.finished = true;
        });
    },
    onSubmit(number) {
      let _this = this;
      let pamas = {
        userCode: localStorage.getItem("userCode"),
        policyNo: number,
        // policyNo: 'PEGD20330154690000000002',
        queryType: String(_this.getType)
      };
      if (_this.getType == "1") {
        this.$router.push({
          name: "detailOrder",
          query: {
            item: JSON.stringify(pamas)
          }
        });
      } else if (_this.getType == "2") {
        this.$router.push({
          name: "detailOrderY",
          query: {
            // item: JSON.stringify(res.data)
            item: JSON.stringify(pamas)
          }
        });
      } else if (_this.getType == "3") {
        this.$router.push({
          name: "detailOrderNo",
          query: {
            // item: JSON.stringify(res.data.policyVO)
            item: JSON.stringify(pamas)
          }
        });
      }
    }
  }
};
</script>
<style lang="less" scoped>
#insuranceProducts {
  background: rgba(247, 247, 247, 1);
  font-family: PingFangSC-Medium, PingFang SC;
  .title {
    background: linear-gradient(
      129deg,
      rgb(30, 70, 246) 0%,
      rgb(39, 154, 255) 100%
    );
    .van-nav-bar {
      background: none;
    }
    .van-nav-bar__title {
      color: #fff !important;
    }
  }
  .title-container {
    padding-top: 80px;
  }
  .divBox {
    margin: 5px;
    padding: 5px 10px;
    background: #fff;
    font-size: 14px;
    border-radius: 6px;
    margin-bottom: 20px;
    .divLine {
      height: 30px;
      line-height: 30px;
      .divLable {
        width: 80px;
        color: #454545;
        display: inline-block;
      }
      .divContent {
        color: #7a7a7a;
        .level {
          width: 16px;
          height: 16px;
          display: inline-block;
        }
      }
      .divDetail {
        float: right;
        margin-right: 10px;
        cursor: pointer;
        line-height: 30px;
      }
    }
  }
  .isBottom {
    text-align: center;
    font-size: 15px;
    color: lightgray;
  }
}
</style>
