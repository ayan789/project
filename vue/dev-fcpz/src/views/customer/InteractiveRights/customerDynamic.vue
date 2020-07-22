<template>
  <div class="customerDynamic">
    <div v-if="keys.length > 0">
      <div
        class="change_dataTime"
        @click="dataTimeShow = true"
      >
        请选择时间日期
      </div>
      <van-popup v-model="dataTimeShow" position="bottom" @close="cancelClick">
        <div
          style="display: flex;justify-content: space-between;padding:10px 20px;color: #0D84F2;"
        >
          <div @click="cancelClick">取消</div>
          <div @click="query()">确定</div>
        </div>
        <div v-if="keys.length > 0" class="calendar">
          <van-picker :columns="years" @change="onYears" style="width:33%;"/>
          <van-picker style="width:33%" :columns="months" @change="onMonths"/>
          <van-picker :columns="days" @change="onDays" style="width:33%"/>
        </div>
      </van-popup>
      <div
        class="today"
        style=" color: #3B8AFE; font-size: 18px"
        ref="todayView">
        {{data_time}}
      </div>
      <div
        class="information">
        <div>
          <div v-if="top_data" class="top_tip_text">———— 别滑了～我也是有顶的 ————</div>
          <van-pull-refresh v-model="refreshing" @refresh="onRefresh" pulling-text="下拉即可加载更多...">
            <van-list
              :immediate-check="false"
              v-model="loading"
              :finished="finished"
              finished-text="没有更多了"
              offset="10"
              ref="todayViewTwo"
              @load="onLoad">
              <div v-for="item in items && items" :key="item.index" ref="top_status">
                <div class="customerDynamic_itemView">
                  <div class="lineTimeView">
                    <div class="customerDynamic_lineView"></div>
                    <div class="circle"></div>
                    <div class="customerDynamic_lineView"></div>
                  </div>
                  <div style="display: flex;border-bottom: 1px dotted #EDEEEF">
                    <div style="color:#545454;font-size: 14px">
                      {{ item.activityDate.slice(11, 16) }}
                    </div>
                    <div style="color:#545454;padding-left: 18px;font-size: 16px ">
                      {{ item.actionName }}
                    </div>
                  </div>
                </div>
              </div>
            </van-list>
          </van-pull-refresh>
        </div>
      </div>
    </div>
    <div
      v-if="keys.length == 0"
      :style="{ backgroundImage: 'url(' + noDataIMG + ')' }"
      class="nodate-image"
    ></div>
    <div
      v-if="keys.length == 0"
      style="display: flex;justify-content: center;margin-top: -10px"
    >
      <span style="color:#999999">暂无数据</span>
    </div>
  </div>
</template>
<script>
import {
  queryCustomerDynamicDate,
  getQueryCustomerDynamic
} from "../../../http/module/customerApi";
import noDataIMG from "../../../assets/customer/customerEquity/noData.png";
export default {
  props: {
    custCode: String,
    custNo: String
  },
  components: {},
  data() {
    return {
      res: {},
      years: [],
      months: [],
      days: [],
      ySelect: "",
      mSelect: "",
      dSelect: "",
      items: [],
      keys: [], //接口返回是否有数据
      dataTimeShow: false,
      noDataIMG: noDataIMG,
      refreshing: false,
      loading: false,
      finished: false,
      list: [],
      temp_slidingType: "2",//滑动手势

      temp_slidingType_1: {
        temp_pageNum_1: 1,
        temp_pageContent_1: '20'
      },

      temp_slidingType_2: {
        temp_pageNum_2: 1,
        temp_pageContent_2: '20'
      },

      top_data: false,
      data_time: ""
    };
  },
  async created() {
    let bb = {
      custCode: this.custCode
    };
    let res_4 = await queryCustomerDynamicDate(bb);
    //判断是否可以走下去
    let kes = [];
    for (let key in res_4.data) {
      kes.push(key);
    }
    this.keys = kes;
    //不判断可能会报错
    if (kes.length > 0) {
      this.res = res_4;
      this.years = [];
      for (let key in res_4.data) {
        this.years.push(key);
      }
      this.years = this.years.reverse();
      this.ySelect = this.years[0];
      this.onYears("", this.ySelect);
      this.query();
    }
  },

  updated() {
    this.$refs.todayViewTwo.scroller.addEventListener("scroll", () => {
      //先获取单独的高度
      let itemHeight = this.$refs.top_status[0].offsetHeight || 50;
      //再获取到当前滑动到第几个
      let item_index = parseInt(this.$refs.todayViewTwo.scroller.scrollTop / itemHeight);
      this.$nextTick(() => {
        this.data_time = this.items[item_index].activityDate.slice(0, 4) + ' 年 ' + this.items[item_index].activityDate.slice(5, 7) + " 月 " + this.items[item_index].activityDate.slice(8, 11) + " 日 ";
      });
    });
  },
  beforeDestroy() {
    window.removeEventListener("scroll", () => {
    });
  },

  methods: {
    //下拉加载之前的数据
    onRefresh() {
      this.finished = false;
      this.onLoad();
    },

    async onLoad() {
      console.log("加载====");
      if (this.refreshing) {
        if (this.temp_slidingType_2.temp_pageNum_2 >= this.temp_slidingType_2.temp_pageContent_2) {
          this.top_data = true;
        } else {
          //下滑
          let aa = {
            custCode: this.custCode,
            date: `${this.ySelect}-${this.mSelect}-${this.dSelect}`,
            custNo: this.custNo,
            slidingType: "2",//1：上滑加载之前的日期；2：下滑下载之后的日期
            pageSize: "50",
            pageNum: this.temp_slidingType_2.temp_pageNum_2
          };
          let res_5 = await getQueryCustomerDynamic(aa);
          if (res_5.code == "1") {
            this.items.unshift(...res_5.data.list);
            this.temp_slidingType_2.temp_pageNum_2 = this.temp_slidingType_2.temp_pageNum_2 + 1;
            this.temp_slidingType_2.temp_pageContent_2 = res_5.data.pages;
            this.data_time = this.items[0].activityDate.slice(0, 4) + ' 年 ' + this.items[0].activityDate.slice(5, 7) + " 月 " + this.items[0].activityDate.slice(8, 11) + " 日 ";
          }
        }
        this.refreshing = false;
      } else {
        //上滑
        let aa = {
          custCode: this.custCode,
          date: `${this.ySelect}-${this.mSelect}-${this.dSelect}`,
          custNo: this.custNo,
          slidingType: "1",// 1：上滑加载之前的日期；2：下滑下载之后的日期
          pageSize: "50",
          pageNum: this.temp_slidingType_1.temp_pageNum_1
        };
        let res_5 = await getQueryCustomerDynamic(aa);
        if (res_5.code == "1") {
          this.items.push(...res_5.data.list);
          this.temp_slidingType_1.temp_pageNum_1 = this.temp_slidingType_1.temp_pageNum_1 + 1;
          this.temp_slidingType_1.temp_pageContent_1 = res_5.data.pages;
        }
        this.loading = false;
        if (this.temp_slidingType_1.temp_pageNum_1 >= this.temp_slidingType_1.temp_pageContent_1 + 1) {
          this.finished = true;
        }
      }
    },

    cancelClick() {
      this.dataTimeShow = false;
    },
    onYears(picker, values) {
      let res = this.res.data;
      let tmpM = [];
      let tmpD = [];
      let a = Object.values(res[values]);
      if (a) {
        a = a.reverse();
        for (let i = 0; i < a.length; i++) {
          tmpM.push(a[i][0].month);
        }
      }
      this.months = tmpM;
      let b = a[0];
      for (let j = 0; j < b.length; j++) {
        tmpD.push(b[j].day);
      }
      this.days = tmpD;
      this.ySelect = values;
      this.mSelect = tmpM[0];
      this.dSelect = tmpD[0];
      console.info(
        "年事件--》",
        this.ySelect + "-" + this.mSelect + "-" + this.dSelect
      );
    },
    onMonths(picker, values) {
      let y = this.ySelect;
      this.mSelect = values;
      let tmpD = [];
      let a = Object.values(this.res.data[y]);
      for (let i = 0; i < a.length; i++) {
        let b = a[i][0].month;
        if (b == values) {
          for (let k = 0; k < a[i].length; k++) {
            tmpD.push(a[i][k].day);
          }
        }
      }
      this.days = tmpD;
      this.mSelect = values;
      this.dSelect = tmpD[0];
      console.info(
        "月事件--》",
        this.ySelect + "-" + this.mSelect + "-" + this.dSelect
      );
    },
    onDays(picker, values) {
      this.dSelect = values;
      console.info(
        "日事件--》",
        this.ySelect + "-" + this.mSelect + "-" + this.dSelect
      );
    },
    async query() {
      this.dataTimeShow = false;
      let aa = {
        custCode: this.custCode,
        date: `${this.ySelect}-${this.mSelect}-${this.dSelect}`,
        custNo: this.custNo,
        slidingType: "1",// 1：上滑加载之前的日期；2：下滑下载之后的日期
        pageSize: "50",
        pageNum: 1
      };
      let res_5 = await getQueryCustomerDynamic(aa);
      if (res_5.code == "1") {
        this.items = [];
        this.temp_slidingType_1.temp_pageNum_1 = 1;
        this.items = res_5.data.list;
        this.temp_slidingType_1.temp_pageNum_1 = this.temp_slidingType_1.temp_pageNum_1 + 1;
        this.temp_slidingType_1.temp_pageContent_1 = res_5.data.pages;
        this.data_time = this.items[0].activityDate.slice(0, 4) + ' 年 ' + this.items[0].activityDate.slice(5, 7) + " 月 " + this.items[0].activityDate.slice(8, 11) + " 日 ";
      }
    }
  }
};
</script>
<style lang="less" scoped>
.customerDynamic {
  height: 60vh;
  padding: 20px 15px 0 15px;

  .nodate-image {
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 300px;
    margin: 0px auto;
    height: 300px;
  }

  .calendar {
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 1px 19px 0px rgba(199, 199, 199, 0.16);
    border-radius: 10px;
    display: flex;
    padding: 0 30px;
  }

  .today {
    font-size: 12px;
    color: rgba(168, 167, 167, 1);
    line-height: 14px;
    padding: 18px 0 8px 10px;
    position: fixed;
    width: 100%;
  }

  .information {
    height: 50vh;
    position: static;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    overflow-y: scroll;
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 4px 9px 0px rgba(0, 0, 0, 0.04);
    border-radius: 10px;
    margin-bottom: 17px;
    margin-top: 40px;

    .top_tip_text {
      color: #545454;
      font-size: 14px;
      width: 100%;
      display: flex;
      justify-content: center;
      align-content: center;
    }
  }
}

.change_dataTime {
  color: #0D84F2;
  fontSize: 16px;
  font-weight: bold;
  text-align: left;
  margin-left: 8px;
}

.customerDynamic_itemView {
  display: flex;
  flex-direction: row;
  align-items: center;
  line-height: 36px;
  height: 36px;
}

.lineTimeView {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /*background-color: #0D84F2;*/
  margin-left: 40px;
  margin-right: 15px;

  .circle {
    width: 6px;
    height: 6px;
    border-radius: 3px;
    background-color: #F4F4F4;

  }

  .customerDynamic_lineView {
    height: 15px;
    width: 1px;
    background-color: #F4F4F4;
  }
}
</style>
