<template>
  <div class="search">
    <div class="search_head--nav van-hairline--bottom">
      <span @click="$router.back()"
        ><van-icon name="arrow-left" size="18"
      /></span>
      <span>高级查询</span>
      <span></span>
    </div>
    <div class="search_head">
      <span>时间范围</span>
      <span @click="showPopup = true">{{ startDate }}</span>
      <!-- <van-calendar
        :min-date="minDate"
        v-model="showStartDate"
        @confirm="
          startDate => {
            if (!this.checkDate(startDate, this.endDate)) {
              return false;
            }
            this.showStartDate = false;
            this.startDate = this.formatDate(startDate);
            this.query();
          }
        "
      /> -->
      <span class="search_head_line"></span>
      <span @click="showPopup_ = true">{{ endDate }}</span>

      <!-- <van-calendar
        v-model="showEndDate"
        :min-date="minDate"
        @confirm="
          endData => {
            if (!this.checkDate(this.startDate, endData)) {
              return false;
            }
            this.showEndDate = false;
            this.endDate = this.formatDate(endData);
            this.query();
          }
        "
      /> -->
      <span class="search_head_icon"><van-icon name="arrow-down"/></span>
    </div>
    <!-- <div class="ring-search">
      <div id="ringSearch"></div>
      <div
        class="ring_"
        v-if="!!queryData.payreffee && !!queryData.numPayreffee"
      >
        <span>{{ queryData.payreffee }}</span>
        <span>总保费(万元）</span>
        <span>{{ queryData.numPayreffee }}</span>
        <span>总件数(件）</span>
      </div>
    </div> -->
    <div
      class="search-content"
      v-if="!!queryData.payreffee && !!queryData.numPayreffee"
    >
      <p class="van-hairline--bottom">
        <span>总保费/件数</span>
        <span :style="{ color: '#3B8AFE' }"
          >{{ (queryData.payreffee /10000).toFixed(2) }}万元/{{ queryData.numPayreffee }}件</span
        >
      </p>
      <p>
        <span>车险/件数</span>
        <span
          >{{ (queryData.payreffeeCar /10000).toFixed(2) }}万元/{{ queryData.numPayreffeeCar }}件</span
        >
      </p>
      <p>
        <span>意外险/件数</span>
        <span
          >{{ (queryData.payreffeeAcc /10000).toFixed(2) }}万元/{{
            queryData.numPayreffeeAcc
          }}件</span
        >
      </p>
      <p>
        <span>财产险/件数</span>
        <span
          >{{ (queryData.payreffeeNoCar / 10000).toFixed(2) }}万元/{{
            queryData.numPayreffeeNoCar
          }}件</span
        >
      </p>
      <p>
        <span>健康险/件数</span>
        <span
          >{{ (queryData.payreffeeHea /10000).toFixed(2) }}万元/{{
            queryData.numPayreffeeHea
          }}件</span
        >
      </p>
    </div>
    <van-popup v-model="showPopup" position="bottom">
      <van-datetime-picker
        v-model="showStartDate"
        @cancel="showPopup = false"
        type="year-month"
        @confirm="
          () => {
            this.startDate = this.formatDate(showStartDate);
            if (!this.checkDate(this.startDate, this.endDate)) {
              return false;
            }
            this.query();
            this.showPopup = false;
          }
        "
      />
    </van-popup>
    <van-popup v-model="showPopup_" position="bottom">
      <van-datetime-picker
        v-model="showEndDate"
        type="year-month"
        @cancel="showPopup_ = false"
        @confirm="
          () => {
            this.endDate = this.formatDate(showEndDate);
            if (!this.checkDate(this.startDate, this.endDate)) {
              return false;
            }
            this.query();
            this.showPopup_ = false;
          }
        "
      />
    </van-popup>
    <!-- <van-datetime-picker
      v-model="showEndDate"
      type="date"
      v-if="showStartDate"
      @confirm="
        startDate => {
          if (!this.checkDate(startDate, this.endDate)) {
            return false;
          }
          this.showStartDate = false;
          this.startDate = this.formatDate(startDate);
          this.query();
        }
      "
    />
    <van-datetime-picker
      v-model="showEndDate"
      type="date"
      v-if="showEndDate"
      @confirm="
        endData => {
          if (!this.checkDate(this.startDate, endData)) {
            return false;
          }
          this.showEndDate = false;
          this.endDate = this.formatDate(endData);
          this.query();
        }
      "
    /> -->
  </div>
</template>

<script>
import echarts from "echarts";
import { Calendar, Toast } from "vant";
//业绩高级查询
import { getPerformance } from "@src/http/module/performance.js";
export default {
  components: {
    [Calendar.name]: Calendar,
    [Toast.name]: Toast
  },
  data() {
    return {
      minDate: new Date(2020, 0, 0),
      showPopup: false,
      showPopup_: false,
      showStartDate: new Date(),
      showEndDate: new Date(),
      startDate: "开始日期",
      endDate: "结束日期",
      flag: false,
      queryData: {
        payreffee: "", //总保费
        payreffeeAcc: "", //意外险保费
        payreffeeHea: "", //健康险保费
        payreffeeCar: "", //车险保费
        payreffeeNoCar: "", //财产险保费
        num_payreffee: "", //总件数
        numPayreffeeAcc: "", //意外险件数
        numPayreffeeHea: "", //健康险件数
        numPayreffeeCar: "", //车险件数
        numPayreffeeNoCar: "" //财产险件数
      },
      option: {
        series: [
          {
            type: "pie",
            radius: ["60%", "80%"],
            left: "center",
            data: [],
            label: {
              show: true,
              color: "#333",
              fontSize: "12px",
              formatter(e) {
                return `{a|${e.name}}\n {b|${e.data.value}元}\n{b|${e.data.num}件}`;
              },
              rich: {
                a: {
                  color: "#666666",
                  fontSize: "11",
                  height: "18"
                },
                b: {
                  color: "#333333",
                  fontSize: "14",
                  height: "16"
                }
              }
            }
          }
        ]
      }
    };
  },
  methods: {
    formatDate(date) {
      let now = date;
      let year = now.getFullYear(); //年
      let month = now.getMonth() + 1; //月
      let day = now.getDate(); //日
      let clock = year + "-";
      if (month < 10) clock += "0";
      clock += month + "-";
      if (day < 10) clock += "0";
      clock += day;
      return clock;
    },
    async query() {
      if (this.startDate != "开始日期" && this.endDate != "结束日期") {
        this.flag = true;
      }
      this.flag
        ? await getPerformance({
            dateStart: this.startDate,
            dateEnd: this.endDate,
            team: 1,
            userCode: JSON.parse(localStorage.getItem("userInfo")).userCode
          }).then(res => {
            if (res.code === "1" && !!res.data) {
              this.queryData = { ...res.data };
              //   this.option.series[0] = Object.assign({}, this.option.series[0], {
              //     data: [
              //       {
              //         value: res.data.payreffeeCar,
              //         num: res.data.numPayreffeeCar,
              //         name: "车险",
              //         itemStyle: {
              //           color: "#7872FF"
              //         }
              //       },
              //       {
              //         value: res.data.payreffeeAcc,
              //         num: res.data.numPayreffeeAcc,
              //         name: "意外险",
              //         itemStyle: {
              //           color: "#6DB3FF"
              //         }
              //       },
              //       {
              //         value: res.data.payreffeeHea,
              //         num: res.data.numPayreffeeHea,
              //         name: "健康险",
              //         itemStyle: {
              //           color: "#FF9151"
              //         }
              //       },
              //       {
              //         value: res.data.payreffeeNoCar,
              //         num: res.data.numPayreffeeNoCar,
              //         name: "财产险",
              //         itemStyle: {
              //           color: "#FFD300"
              //         }
              //       }
              //     ]
              //   });
              //   this.$nextTick(() => {
              //     let ringSearch = echarts.init(
              //       document.getElementById("ringSearch")
              //     );
              //     ringSearch.setOption(this.option);
              //   });
            }
          })
        : "";
    },
    //校验日历时间
    checkDate(startTime, endTime) {
      let mark = true;
      if (new Date(startTime).getTime() > new Date(endTime).getTime()) {
        Toast("结束日期不可小于开始日期");
        return (mark = false);
      }
      return mark;
    }
  }
};
</script>

<style lang="less" scoped>
.search {
  background: #ffffff;
  height: 100%;
  &_head--nav {
    padding: 15px 16px;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: center;
    span:nth-child(1) {
      vertical-align: middle;
    }
    span:nth-child(2) {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
  }
  &_head {
    height: 42px;
    line-height: 42px;
    padding: 0 20px;
    border-bottom: 12px solid #f7f7f7;
    display: flex;
    display: -webkit-flex;
    justify-content: space-around;
    -webkit-justify-content: space-around;
    -webkit-align-items: center;
    align-items: center;
    span {
      color: #c1c1c1;
      font-size: 12px;
    }
    span:nth-child(1) {
      color: rgba(51, 51, 51, 1);
      font-size: 12px;
      // margin-right: 45px;
    }
    .search_head_line {
      width: 8px;
      height: 1px;
      display: inline-block;
      vertical-align: middle;
      background: #979797;
      font-size: 12px;
      // margin: 0 30px 0 30px;
    }
  }
  .ring-search {
    height: 200px;
    width: 100%;
    position: relative;
    #ringSearch {
      height: 200px;
      width: 100%;
    }
    .ring_ {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      span {
        display: block;
        text-align: center;
      }
      span:nth-child(odd) {
        font-size: 21px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(59, 138, 254, 1);
      }
      span:nth-child(even) {
        font-size: 11px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }
  }
  &-content {
    padding: 20px;
    p {
      display: flex;
      justify-content: space-between;
      margin-bottom: 11px;
      span:nth-child(1) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(69, 69, 69, 1);
      }
      span:nth-child(2) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(122, 122, 122, 1);
      }
    }
    p:nth-child(1) {
      margin-bottom: 15px;
      padding-bottom: 15px;
    }
  }
}
</style>
