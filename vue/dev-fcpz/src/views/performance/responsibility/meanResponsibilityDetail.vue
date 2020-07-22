<template>
  <div class="responsibility-detail">
    <globalNav :title="globalNav.title" @clickBack="$router.back()"></globalNav>
    <div class="content">
      <div class="chart-wrap">
        <div class="title">
          <span>年收入目标</span>
        </div>
        <div class="chart">
          <div id="chart" ref="chart"></div>
        </div>
      </div>
    </div>
    <div class="collect-wrap">
      <div class="title">
        您每月的总保费及各险类保费目标为
      </div>
      <van-row class="total" type="flex" align="center">
        <van-col class="icon">总</van-col>
        <span class="total-premium">
          总保费
          <span class="premium">{{ this.data.insurePremium }}元 </span>
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
              {{ this.data.carInsurePremium }}元
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
              {{ this.data.accInsurePremium }}元
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
              {{ this.data.heaInsurePremium }}元
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
              {{ this.data.propInsurePremium }}元
            </van-col>
          </van-row>
        </div>

        <div class="right-wrap"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { responsibilityListQuery } from "@src/http/module/performance";
import globalNav from "@/components/globalNav";
import echarts from "echarts";
export default {
  components: {
    globalNav
  },
  data: () => ({
    data: {},
    globalNav: {
      title: ""
    },
    option: {
      title: {
        text: " ",
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
  watch: {
    selectedMonth: function(value) {}
  },
  created() {
    this.data = { ...this.$route.params };
    if (!this.data.hasOwnProperty("responsibilityName")) {
      this.data = responsibilityListQuery({}).then(
        data => {},
        error => {}
      );
    }
    this.globalNav.title = this.data.responsibilityName + "明细";
  },
  mounted() {
    // this.navBarHeight = this.$refs["nav-bar"].offsetHeight;
    this.$nextTick(() => {
      this.initChart();
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
  padding-top: 64px;
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
