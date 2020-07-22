<template>
  <div class="responsibility-detail">
    <div class="content">
      <div class="chart-wrap">
        <div class="title">
          <span>年收入目标</span>
        </div>
        <div class="chart">
          <div id="chart" ref="chart"></div>
        </div>
        <!-- <div class="team-ring">
          <div id="teamRing"></div>
          <div class="ring_" v-if="!!data.insurePremium">
            <span>{{ data.insurePremium }}</span>
            <span>总保费(万元）</span>
          </div>
        </div> -->
      </div>
      <div class="collect-wrap" v-if="data.automaticFlag==1">
        <div class="title">
          您每月的总保费及各险类保费目标为
        </div>
        <van-row class="total" type="flex" align="center">
          <van-col class="icon">总</van-col>
          <span class="total-premium">
            总保费
            <!-- <span class="premium">{{ this.data.insurePremium }}元 </span> -->
            <span class="premium">{{ this.listData[this.listData.length - 1] && this.listData[this.listData.length - 1].insurePremium }}元 </span>
          </span>
        </van-row>
        <div class="items-wrap">
          <div class="left-wrap">
            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">车险</van-col>
              <van-col class="item-premium-value">
                <!-- {{ this.data.carInsurePremium }}元 -->
                {{ this.listData[this.listData.length - 1] && this.listData[this.listData.length - 1].carInsurePremium }}元
              </van-col>
            </van-row>
            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">意外险</van-col>
              <van-col class="item-premium-value">
                <!-- {{ this.data.accInsurePremium }}元 -->
                {{ this.listData[this.listData.length - 1] && this.listData[this.listData.length - 1].accInsurePremium }}元
              </van-col>
            </van-row>
            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">健康险</van-col>
              <van-col class="item-premium-value">
                <!-- {{ this.data.heaInsurePremium }}元 -->
                {{ this.listData[this.listData.length - 1] && this.listData[this.listData.length - 1].heaInsurePremium }}元
              </van-col>
            </van-row>
            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">财产险</van-col>
              <van-col class="item-premium-value">
                <!-- {{ this.data.propInsurePremium }}元 -->
                {{ this.listData[this.listData.length - 1] && this.listData[this.listData.length - 1].propInsurePremium }}元
              </van-col>
            </van-row>
          </div>
          <div class="right-wrap"></div>
        </div>
      </div>
      <van-tabs
        scrollspy
        sticky
        line-height="0"
        :offset-top="navBarHeight"
        class="month-tabs"
        :border="false"
        v-model="selectedMonth"
        v-if="data.automaticFlag==0"
      >
        <van-tab
          v-show=" +month.monthStep -1 < newdate ? false : true"
          :disabled=" +month.monthStep - 1 < newdate ? true : false"
          v-for="month in listData"
          :key="month.monthStep"
          :name="month.monthStep"
          :title="month.monthStep + '月'"
          :title-style="
            `
            border:1px solid #E6E6E6;
            border-radius:5px;
            max-width:44px;
            height:36px;
            margin-right:8px;
            line-height:36px;
            align-self:center;
            overflow:visible;
            ${'background:' +
              (selectedMonth === month.monthStep
                ? 'linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%)'
                : 'white') +
              ';'}
              ${'color:' +
                (selectedMonth === month.monthStep ? 'white' : '#333333') +
                ';'}
            ${'background:' +
              (+month.monthStep - 1  < newdate 
                ? '#F4F4F4'
                : '') +
              ';'}
          `
          "
          class="month-item"
        >
          <div class="card">
            <van-row style="margin-bottom:14px;" type="flex" align="center">
              <van-col class="month">{{ month.monthStep + "月" }}</van-col>
              <span class="total-premium">
                总保费
                <span class="month-premium">{{ month.insurePremium }}元 </span>
              </span>
            </van-row>
            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">车险</van-col>
              <van-col class="item-premium-value">
                {{ month.carInsurePremium }}元
              </van-col>
            </van-row>

            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">意外险</van-col>
              <van-col class="item-premium-value">
                {{ month.accInsurePremium }}元
              </van-col>
            </van-row>

            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">健康险</van-col>
              <van-col class="item-premium-value">
                {{ month.heaInsurePremium }}元
              </van-col>
            </van-row>

            <van-row
              class="item-premium"
              type="flex"
              justify="space-between"
              align="center"
            >
              <van-col class="item-premium-name">财产险</van-col>
              <van-col class="item-premium-value">
                {{ month.propInsurePremium }}元
              </van-col>
            </van-row>
          </div>
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<script>
import echarts from "echarts";
import { Loading } from 'vant';
export default {
  props: {
    data: {
      type: Object
    }
  },
  data: () => ({
    newdate: "",
    isTeamLeader: "",
    insuranceKind: ["车险保费", "意外险保费", "健康险保费", "财产险保费"],
    insuranceKindKey: [
      "carInsurePremium",
      "accInsurePremium",
      "heaInsurePremium",
      "propInsurePremium"
    ],
    months: [
      "janTotalPremium",
      "febTotalPremium",
      "marTotalPremium",
      "aprTotalPremium",
      "mayTotalPremium",
      "junTotalPremium",
      "julTotalPremium",
      "augTotalPremium",
      "sepTotalPremium",
      "octTotalPremium",
      "novTotalPremium",
      "decTotalPremium"
    ],
    yearTotalPremiumNumber: 0.0,
    yearTotalPremium: "",
    yearKindPremium: [],
    monthsPremium: [],
    navBarHeight: 80,
    selectedMonth: "",
    listData: [],
    option: {
      title: {
        text: "",
        subtext: "总保费(元)",
        left: "center",
        top: "40%",
        textStyle: {
          fontSize: 21,
          fontWeight: "500",
          color: ["#3B8AFE"],
          fontFamily: "PingFangSC-Medium,PingFang SC"
        },
        subtextStyle: {
          color: "#666",
          fontSize: 11,
          fontFamily: "PingFangSC-Regular,PingFang SC",
          lineHeight: "12"
        }
      },
      series: [
        {
          type: "pie",
          radius: ["60%", "80%"],
          left: "center",
          minAngle: 60,
          avoidLabelOverlap: true,
          data: [],
          label: {
            show: true,
            color: "#333",
            fontSize: "12px",
            formatter(e) {
              // return `{a|${e.name}}\n {b|${e.data.value}万元}\n{b|${e.data.num}件}`;
              return `{a|${e.name}}\n {b|${e.data.value}元}\n`;
            },
            rich: {
              a: {
                color: "#666666",
                fontSize: "11",
                height: "12",
                lineHeight: "12",
                fontFamily: "PingFangSC-Regular,PingFang SC"
              },
              b: {
                color: "#333333",
                fontSize: "14",
                height: "20",
                fontFamily: "PingFangSC-Regular,PingFang SC",
                lineHeight: 20
              }
            }
          },
          labelLine: {
            length2: "10"
          }
        }
      ]
    }
  }),
  // watch: {
  //   selectedMonth: function(value) {}
  // },

  watch: {
    // 加个监听的，，防止父级数据改变，子组件没有跟着改变
    data: {
      handler(val) {
        if (val) {
          this.initChart();
          this.listData = this.data.perfResponsibilityDetailList;
        }
      },
      deep: true,
      // immediate: true
    }
  },
  created() {
    this.newdate = JSON.parse(localStorage.getItem("newdate"))
    console.log("newdate----->",this.newdate);
  },
  mounted() {
    // this.navBarHeight = this.$refs["nav-bar"].offsetHeight;
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.isTeamLeader = userInfo.isTeamLeader;
    this.$nextTick(() => {
      this.initChart();
      this.listData = this.data.perfResponsibilityDetailList;
      // if(this.newdate){
      //   this.listData = this.listData.slice(this.newdate)
      //   console.log(this.listData);
      // }
    });
  },
  computed: {},
  methods: {
    onBack() {},
    initChart() {
      this.option.series[0] = Object.assign({}, this.option.series[0], {
        data: [
          {
            value: this.data.carInsurePremium,
            name: "车险",
            itemStyle: {
              color: "#7872FF"
            }
          },
          {
            value: this.data.accInsurePremium,
            name: "意外险",
            itemStyle: {
              color: "#6DB3FF"
            }
          },
          {
            value: this.data.heaInsurePremium,
            name: "健康险",
            itemStyle: {
              color: "#FF9151"
            }
          },
          {
            value: this.data.propInsurePremium,
            name: "财产险",
            itemStyle: {
              color: "#FFD300"
            }
          }
        ]
      });
      this.option.title.text = this.data.insurePremium;
      let chart = echarts.init(this.$refs.chart);
      chart.resize();
      chart.setOption(this.option);
    }
  }
};
</script>
<style scoped lang="less">
.content {
  background-color: #f7f7f7;
}
.month-item {
  padding: 10px 15px 0;
}
.month-item .card {
  margin: 0;
}
.month-item:last-child {
  margin-bottom: 20px;
}
.card {
  position: relative;
  z-index: 2;
  background: white;
  box-shadow: 0px 1px 1px 0px #c7c7c7;
  border-radius: 10px;
  padding: 20px;
  margin: 10px 15px;
}
.year-image {
  width: 32px;
  height: 32px;
  border-radius: 16px;
  margin-right: 10px;
  background: #ce2262;
}
.month {
  background: linear-gradient(180deg, #247dfc 0%, #1f54f7 100%);
  width: 26px;
  height: 26px;
  margin-right: 10px;
  border-radius: 13px;
  text-align: center;
  line-height: 26px;
  color: white;
}
.item-premium {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 27px;
}
.item-premium-name {
  color: #787878;
}
.item-premium-value {
  color: #333333;
}
.total-premium {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
.year-premium {
  color: #3b8afe;
  font-size: 18px;
}
.month-premium {
  color: #3b8afe;
}
.month-tabs {
  position: relative;
  z-index: 2;
  background: white;
  border: 0;
}
.month-tabs::after {
  display: none;
}
.chart-wrap {
  width: 375px;
  height: 272px;
  background: #fff;
  margin: 16px 0 10px 0;
  .title {
    padding-left: 26px;
    font-size: 15px;
    font-weight: 500;
    color: #333;
    font-family: "PingFangSC-Medium,PingFang";
  }
  #chart {
    width: 100%;
    height: 220px;
  }
}

.item-premium {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 27px;
}
.item-premium-name {
  color: #787878;
}
.item-premium-value {
  color: #333333;
}

.year-premium {
  color: #3b8afe;
  font-size: 18px;
}
.month-premium {
  color: #3b8afe;
}
.month-tabs {
  position: relative;
  z-index: 2;
  background: white;
  border: 0;
}
.month-tabs::after {
  display: none;
}
.chart-wrap {
  width: 100%;
  height: 272px;
  background: #fff;
  margin: 16px 0 10px 0;
  .title {
    padding-left: 26px;
    font-size: 15px;
    font-weight: 500;
    color: #333;
    font-family: "PingFangSC-Medium,PingFang";
  }
  #chart {
    width: 100%;
    height: 245px;
  }
}
.collect-wrap {
  //   width: 100%;
  height: 249px;
  background: #fff;
  padding: 23px 26px;
  .title {
    font-size: 15px;
    font-weight: 500;
    color: #333;
    font-family: "PingFangSC-Medium,PingFang";
  }
  .total {
    margin-top: 23px;
    .icon {
      background: linear-gradient(180deg, #247dfc 0%, #1f54f7 100%);
      width: 26px;
      height: 26px;
      margin-right: 10px;
      border-radius: 13px;
      text-align: center;
      line-height: 26px;
      color: white;
    }
    .total-premium {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #333333;
      line-height: 20px;
      .premium {
        color: #3b8afe;
      }
    }
  }
  .items-wrap {
    margin-top: 25px;
    padding: 0 15px;
    .left-wrap {
      display: inline-block;
      width: 50%;
      //   padding-right: 7px;
    }
    .right-wrap {
      display: inline-block;
      width: 50%;
      height: 100px;
      background: url("../../../assets/performance/progress.png") no-repeat;
      background-position: center 10px;
    }
  }
}
</style>
