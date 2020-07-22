<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-21 20:47:57
 * @Last Modified by: King
 * @Last Modified time: 2020-03-27 16:09:27
 * @FilePath: /src/views/performance/salary/salaryAssess.vue
-->
<template>
  <div class="salary-assess">
    <div class="card">
      <van-row type="flex" class="mode-container">
        <van-row
          type="flex"
          justify="center"
          align="center"
          class="mode"
          v-bind:class="{ 'active-mode': rankMode === '维持' }"
          @click="onRankModeChange('维持')"
        >
          <van-col>维持</van-col>
        </van-row>
        <van-row
          type="flex"
          justify="center"
          align="center"
          class="mode"
          v-bind:class="{ 'active-mode': rankMode === '晋升' }"
          @click="onRankModeChange('晋升')"
        >
          <van-col>晋升</van-col>
        </van-row>
      </van-row>
      <van-row type="flex" justify="space-between">
        <van-col class="title gap-title">
          距离本月月底目标缺口
          <div class="gap-time" v-if="rankMode === '维持'">
            考核周期：{{ kpiDate }}
          </div>
        </van-col>
      </van-row>
      <van-row type="flex" justify="center" align="center">
        <div class="liquidfill-container">
          <ve-liquidfill
            width="100%"
            height="100%"
            :data="waveChartData"
            :settings="waveChartSettings"
          />
          <van-circle
            :rate="1"
            color="#F4F4F4"
            layer-color="#F4F4F4"
            :stroke-width="40"
            size="calc(100% + 20px)"
            class="liquidfill-border"
          />
          <van-circle
            :rate="1"
            v-model="currentRate"
            :color="{
              '0%': '#FF9151',
              '100%': '#FFC4A2'
            }"
            layer-color="transparent"
            :stroke-width="60"
            size="calc(100% + 25px)"
            class="liquidfill-border"
          />
        </div>
      </van-row>
      <van-row
        class="tip"
        v-if="rankMode === '维持' && Number(rankData.num) > 0"
      >
        您需完成保单数约
        <span class="tip-focus"
          >{{
            /^[0-9]+\.?[0-9]+?$/.test(Number(rankData.num)) &&
            String(rankData.num).startsWith("0")
              ? "1"
              : rankData.num
          }}单</span
        >
        可达标
      </van-row>
      <van-row
        type="flex"
        justify="center"
        style="text-align: center"
        v-else-if="rankMode === '维持' && Number(rankData.num) <= 0"
      >
        <van-col span="24">太棒啦，您本月目标已完成，继续加油！</van-col>
      </van-row>
      <van-row
        class="tip tip-last"
        v-if="
          rankMode === '维持' &&
            Number(rankData.perDayNum) > 0.5 &&
            Number(rankData.num) > 0
        "
      >
        平均每日需完成
        <span class="tip-focus">{{
          Number(rankData.perDayNum) > 0.5 && Number(rankData.perDayNum) < 1
            ? "1"
            : rankData.perDayNum
        }}</span>
        单
      </van-row>
      <van-row class="tip" v-if="rankMode === '晋升'">
        <span v-if="standards > 0">
          您需完成保费数约
          <span class="tip-focus">{{ standards }}元</span>
          可达标
        </span>
        <span v-if="standards <= 0">
          太棒了，晋升下一职级保费目标已达成！
        </span>
      </van-row>
    </div>

    <div class="card">
      <van-row type="flex" justify="space-between" class="salary">
        <van-col span="12" class="title">基本法收入明细</van-col>
        <van-row type="flex" justify="end" align="center">
          <van-row
            type="flex"
            align="center"
            class="salary-item"
            :class="{ 'active-salary-item': salaryQueryMode === 'month' }"
            @click="onSalaryQueryModeChange('month')"
          >
            <van-col class="active-line-container">
              <div>月收入</div>
              <div
                :class="{
                  'inactive-line': salaryQueryMode === 'year',
                  'active-line': true
                }"
              ></div>
            </van-col>
          </van-row>
          <van-row
            type="flex"
            align="center"
            class="salary-item"
            :class="{ 'active-salary-item': salaryQueryMode === 'year' }"
            @click="onSalaryQueryModeChange('year')"
          >
            <van-col class="active-line-container">
              <div>年收入</div>
              <div
                :class="{
                  'inactive-line': salaryQueryMode === 'month',
                  'active-line': true
                }"
              ></div>
            </van-col>
          </van-row>
        </van-row>
      </van-row>
      <van-tabs
        line-height="0px"
        :border="false"
        v-model="salaryQueryActiveMonth"
        :style="
          `
            padding-left:25px;
            padding-right:25px;
            ${'display:' +
              (salaryQueryMode === 'month' ? 'block' : 'none') +
              ';'}
          `
        "
        @click="onClick"
      >
        <!-- <template #nav-left><div style="margin-left:25px;"></div></template>
        <template #nav-right><div style="margin-right:25px;"></div></template> -->
        <van-tab
          v-for="index in monthList"
          :border="false"
          :key="index"
          :name="index"
          :title="index + '月'"
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
              (salaryQueryActiveMonth === index
                ? 'linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%)'
                : 'white') +
              ';'}
              ${'color:' +
                (salaryQueryActiveMonth === index ? 'white' : '#333333') +
                ';'} 
          `
          "
          :disabled="
            index < new Date().getMonth() + 1 ||
            (index == new Date().getMonth() + 1 && new Date().getDate() > 26)
              ? false
              : true
          "
        >
        </van-tab>
      </van-tabs>
      <!--  v-if="salaryItems.length&&salaryQueryMode==='year'" -->
      <div class="salary-month-container">
        <div
          v-for="(v, rowIndex) in salaryItemsRow"
          :key="rowIndex"
          class="salary-month-item"
        >
          <van-row
            type="flex"
            justify="center"
            align="center"
            class="salary-month-item-row"
          >
            <van-col
              v-for="colIndex in 4"
              :key="'title-' + colIndex"
              class="salary-month-item-title"
            >
              {{ (salaryItems[rowIndex * 4 + colIndex - 1] || {}).salaryName }}
            </van-col>
          </van-row>
          <van-row
            type="flex"
            justify="center"
            align="center"
            class="salary-month-item-row"
          >
            <van-col
              v-for="colIndex in 4"
              :key="'value-' + colIndex"
              class="salary-month-item-value"
            >
              <!-- {{
                (salaryItems[rowIndex * 4 + colIndex - 1] || {}).salaryResult ||
                  0
              }} -->
              {{
                (salaryItems[rowIndex * 4 + colIndex - 1] || {}).salaryResult
              }}
            </van-col>
          </van-row>
        </div>
      </div>
      <van-row type="flex" justify="end" class="month-total">
        <van-col class="total-salary" v-if="salaryQueryMode === 'month'">
          基本法薪酬应发:
          <span class="tip-focus salaryTotal">{{ salaryTotal }}元</span>
        </van-col>
        <van-col class="total-salary" v-else>
          累计实发:
          <span class="tip-focus salaryTotal">{{ salaryTotal }}元</span>
        </van-col>
      </van-row>
    </div>
    <div class="card">
      <van-row type="flex">
        <van-col class="title gap-month-title">
          KPI考核
          <div class="gap-time score">
            <p class="evaluationScore">{{ kpiScore }}分</p>
            <span>上月考核结果</span>
          </div>
        </van-col>
      </van-row>
      <van-row type="flex" justify="center">
        <div class="ring-container">
          <ve-ring
            width="100%"
            height="100%"
            :data="ringChartData"
            :settings="ringChartSettings"
            :extend="extendringChart"
          />
        </div>
      </van-row>
      <van-row class="rule-title">
        <img
          src="../../../assets/performance/ruleDes.png"
          alt=""
          style="width:18px;height:18px;"
        />
        计算规则
      </van-row>
      <van-row type="flex" justify="center">
        <van-row type="flex" class="rules">
          <van-col
            v-for="item in ringChartData.rows"
            :key="item.indicatorName"
            span="12"
          >
            <span
              >{{ item.indicatorName }} :
              {{ item.indicatorWeight }} &nbsp;</span
            >
          </van-col>
        </van-row>
      </van-row>
    </div>
  </div>
</template>

<script>
import {
  checkRank,
  salaryQuery,
  kpiQuery
} from "@src/http/module/performance.js";
import echarts from "echarts";
import { Circle } from "vant";
import { isHaveSetSalaryPwd } from "@src/http/module/performance";
const linearGradient = new echarts.graphic.LinearGradient(0, 0, 0, 1, [
  {
    offset: 0,
    color: "#2161F9C8"
  },
  {
    offset: 1,
    color: "#97C3FFC8"
  }
]);
const seriesMapItem = {
  radius: "100%",
  itemStyle: {
    opactiy: 0.78,
    shadowBlur: 0
  },
  amplitude: "10%",
  color: [
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [
      {
        offset: 0,
        color: "#2161F9C8"
      },
      {
        offset: 1,
        color: "#97C3FFC8"
      }
    ])
  ],
  center: ["50%", "50%"],
  outline: {
    show: false
  },
  backgroundStyle: {
    color: "#00000017"
  },
  shape: "circle",
  label: {
    position: ["50%", "30%"],
    formatter: function(o) {
      return `{value|${parseFloat(o.seriesName * o.value)
        .toFixed(2)
        .toLocaleString()}}{unit|元}`;
    },
    rich: {
      value: {
        fontSize: "18",
        color: "#3B8AFE",
        fontWeight: "500"
      },
      unit: {
        fontSize: "12",
        color: "#3B8AFE"
      }
    },
    fontSize: 12
  },
  silent: true
};
export default {
  name: "salary-assess",
  components: {
    [Circle.name]: Circle
  },
  data: () => ({
    /* 修改--添加 */
    monthList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
    backgroundCircle: 1,
    currentRate: 0,
    //职级考核数据
    rankMode: "维持",
    rankData: {
      keepStandard: "1",
      promotionStandard: "1",
      real: "0",
      num: "0",
      perDayNum: "0",
      standards: ""
    },
    waveChartData: {
      columns: ["standard", "percent"],
      rows: [
        {
          real: 0,
          standard: 1,
          percent: 0
        }
      ]
    },
    waveChartSettings: {
      wave: [0],
      seriesMap: [
        {
          radius: "100%",
          itemStyle: {
            opactiy: 0.78,
            shadowBlur: 0
          },
          amplitude: "10%",
          // color: [
          //   new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          //     {
          //       offset: 0,
          //       color: "#2161F9C8"
          //     },
          //     {
          //       offset: 1,
          //       color: "#97C3FFC8"
          //     }
          //   ])

          // ],
          color: ["#4CDEFF"],
          center: ["50%", "50%"],
          outline: {
            show: false
          },
          backgroundStyle: {
            color: "#00000017"
          },
          shape: "circle",
          label: {
            position: ["50%", "30%"],
            formatter: function(o) {
              console.log(o, "-=-=-=");
              // return `{value|${(parseFloat(o.seriesName * o.value) &&
              //   parseFloat(o.seriesName * o.value)
              //     .toFixed(2)
              //     .toLocaleString()) ||
              //   0}}{unit|元}`;
              return `{value|${Number(o.seriesName) || 0}}{unit|元}`;
            },
            rich: {
              value: {
                fontSize: "18",
                color: "orange",
                fontWeight: "500"
              },
              unit: {
                fontSize: "12",
                color: "orange"
              }
            },
            fontSize: 12
          },
          silent: true
        }
      ]
    },
    //薪酬数据
    salaryQueryMode: "month",
    salaryQueryActiveMonth: new Date().getMonth(),
    salaryItems: [],
    salaryTotal: "",
    //kpi考核数据
    kpiScore: "0",
    //考核周期
    kpiDate: "",
    ringChartData: {
      columns: ["indicatorName", "indicatorScore"],
      rows: []
    },
    ringChartSettings: {
      legendLimit: 0,
      selectedMode: false,
      radius: ["40%", "30%"],
      offsetY: "50%",
      tooltip: {
        confine: true
      },
      label: {
        // formatter: "{name|{b}} {value|{c}}",
        formatter(value) {
          return value.name.length > 6
            ? value.name.slice(0, 6) + "..."
            : value.name;
        },
        color: "#666666",
        // rich: {
        //   name: { color: "#696969" },
        //   value: { color: "#3B8AFE" }
        // },
        position: "outside"
      },
      labelLine: {
        show: true,
        length: 20,
        length2: 15,
        lineStyle: {
          color: "#D5D5D5"
        }
      },
      hoverAnimation: false
    },
    /* -- */
    extendringChart: {
      tooltip: {
        confine: true,
        position: ["bottom", "bottom"],
        extraCssText: "width:100%;white-space:pre-wrap;"
      }
    },
    loading: 0
  }),
  watch: {
    loading: function(loading) {
      if (loading > 0) {
        this.$toast.loading({ duration: 0, message: "加载中..." });
      } else {
        this.$toast.clear();
      }
    }
  },
  created() {
    this.monthListSort();
    let { isLogin = false } = this.$route.params || {};
    // isLogin = true;
    this.isLogin = isLogin;
    //NOTE: 这个应该在跳转到该模块之前处理,而非在此处
    let params = {
      userCode: localStorage.getItem("userCode")
      // userCode: "8000154962"
    };
    isHaveSetSalaryPwd(params).then(
      data => {
        if (data.code === "1") {
          let isSetPwd = data.data.isSetPwd === "1";
          //这里应该判断是否已经登录且未超时
          if (isSetPwd) {
            if (isLogin) {
              this.isSetPwd = isSetPwd;
              this.isLogin = isLogin;
            } else {
              this.$router.replace({ name: "salaryQueryLogin" });
            }
          } else {
            this.$router.replace({
              name: "salaryChangeQueryPassword",
              params: { isSetPwd: false }
            });
          }
        } else {
          console.log(data);
        }
      },
      error => {
        console.log(error);
      }
    );

    this.checkRank();
    let myDate = new Date();
    this.salaryQuery(myDate.getMonth());
    this.kpiQuery();
    let month = myDate.getMonth() + 1;
    let d = new Date(myDate.getFullYear(), myDate.getMonth() + 1, 0);
    let lastDay = new Date();
    lastDay.setMonth(lastDay.getMonth() + 1 - 12);
    let nowDay = new Date();
    nowDay.setMonth(nowDay.getMonth() + 1, 0);
    this.kpiDate =
      lastDay.getFullYear() +
      "/" +
      (lastDay.getMonth() + 1) +
      "/1至" +
      nowDay.getFullYear() +
      "/" +
      (nowDay.getMonth() + 1) +
      "/" +
      nowDay.getDate();
    // this.kpiDate =
    //   myDate.getFullYear() +
    //   "/" +
    //   month +
    //   "/1至" +
    //   myDate.getFullYear() +
    //   "/" +
    //   month +
    //   "/" +
    //   d.getDate();
  },
  computed: {
    salaryItemsRow() {
      let length = this.salaryItems.length;
      let rows = parseInt(length / 4) + 1;
      let itemRows = new Array(rows);
      return itemRows.fill(0).map((v, i) => i);
    }
  },
  methods: {
    monthListSort() {
      this.monthList = this.monthList.concat(
        this.monthList.splice(0, new Date().getMonth() + 1)
      );
    },
    checkRank() {
      // let params = { userCode: "8000154962" };
      let params = { userCode: localStorage.getItem("userCode") };
      checkRank(params).then(
        rankData => {
          //console.log(rankData);
          if (rankData.code === "1") {
            this.rankData = { ...rankData.data };
            this.standards =
              this.rankData.promotionStandard - this.rankData.promotionReal;
              this.standards = Number(this.standards).toFixed(2);
            this.onRankModeChange("维持");
          } else {
          }
        },
        error => {}
      );
    },
    kpiQuery() {
      let params = {
        // userCode: "8000506155"
        userCode: localStorage.getItem("userCode")
      };
      kpiQuery(params).then(
        kpiData => {
          if (kpiData.code === "1") {
            if (kpiData.data.responseHeadDTO.status == 0) {
              this.kpiScore =
                kpiData.data.evaluationQueryResponseDTO.evaluationScore;
              this.ringChartData.rows = [
                ...kpiData.data.evaluationQueryResponseDTO
                  .evaluationIndicatorDTOList
              ];
              //console.log('this.ringChartData===='+ JSON.stringify(this.ringChartData))
            } else {
            }
          }
        },
        error => {}
      );
    },
    salaryQuery(month, year) {
      this.salaryItems = [];
      this.salaryTotal = "";
      let myDate = new Date();
      let startMonth =
        myDate.getFullYear() + (month < 10 ? "0" + month : String(month));
      // let thisYear = myDate.getFullYear() + (month < 10 ? "0" + month : String(month));
      // let pastYear = myDate.getFullYear() - 1 + (month < 10 ? "0" + month : String(month))
      // let compareMonth = myDate.getMonth() + 1
      // let startMonth = thisYear
      let evaluationMonthRange = startMonth + "-" + startMonth;
      // let evaluationMonthRange = month <=  compareMonth ? thisYear + "-" +  thisYear : pastYear + "-" +  pastYear;
      if (year == "1") {
        // let TheYear =  myDate.getFullYear();
        // let TheMonth =  myDate.getMonth()
        evaluationMonthRange =
          myDate.getFullYear() + "01" + "-" + myDate.getFullYear() + "12";
        // TheYear - 1 + (TheMonth + 2 < 10 ? "0" + (TheMonth + 2) : String(TheMonth + 2 == 13 ? TheMonth + 2 : 1 )) +
        // "-" +
        // TheYear + (TheMonth + 1 < 10 ? "0"  + (TheMonth + 1) : String(TheMonth + 1))
      }
      let params = {
        // userCode: "8000580557",
        userCode: localStorage.getItem("userCode"),
        evaluationMonthRange: evaluationMonthRange
      };
      salaryQuery(params).then(
        salaryData => {
          if (salaryData.code === "1") {
            this.salaryItems = [...salaryData.data.salaryIndicatorDTOList];
            this.salaryTotal = salaryData.data.actualPayNum;
            // console.log('salaryItems[1].salaryResult======'+this.salaryItems[1].salaryResult)
          } else {
            this.salaryItems = [];
            this.salaryTotal = 0;
          }
        },
        error => {}
      );
    },
    onClick(name, title) {
      this.salaryQuery(name);
    },
    onRankModeChange(rankMode) {
      this.rankMode = rankMode;
      let real = 0;
      let standard = 0;
      if (this.rankMode === "维持") {
        real = this.rankData.real;
        standard = this.rankData.keepStandard;
      } else {
        real = this.rankData.promotionReal;
        standard = this.rankData.promotionStandard;
      }
      // real = standard - real;
      let percent = parseFloat(real / standard);
      console.log(real, standard, percent);
      this.waveChartData.rows = [
        {
          real,
          standard,
          percent
        }
      ];
      this.waveChartSettings = Object.assign({}, this.waveChartSettings, {
        wave: [percent]
      });
      if (percent >= 1) {
        this.waveChartSettings.seriesMap[0].label = Object.assign(
          {},
          this.waveChartSettings.seriesMap[0].label,
          {
            formatter() {
              return "";
            }
          }
        );
      }

      console.log(this.waveChartSettings, "=-=--");
      this.currentRate = this.waveChartSettings.wave[0] * 100;
      console.log(this.waveChartSettings.seriesMap[0].color);
    },
    onSalaryQueryModeChange(param) {
      this.salaryQueryMode = param;
      let myDate = new Date();
      if (param == "month") {
        this.salaryQuery(myDate.getMonth());
      } else {
        this.salaryQuery(myDate.getMonth(), "1");
      }
    },
    onClickLeft() {}
  }
};
</script>

<style lang="less" scoped>
.liquidfill-container {
  width: 125px;
  height: 125px;
  position: relative;
  margin: 25px 110px;
}
.liquidfill-border {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.ring-container {
  position: relative;
  width: 100%;
  height: 250px;
}
.rules {
  width: 302px;
  background: #f1f7ff;
  border-radius: 5px;
  border: 1px dashed #bed0e7;
  flex-wrap: wrap;
  word-break: break-all;
  padding: 6px 12px;
  margin: 20px;
  margin-top: 8px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #7f7f7f;
  line-height: 19px;
}
.mode-container {
  padding: 25px 25px 0;
  width: 138px;
  height: 24px;
  justify-content: space-between;
}
.mode {
  width: 64px;
  height: 24px;
  border-radius: 5px;
  font-size: 12px;
  color: #999999;
  border: 1px solid #e0e0e0;
}
.active-mode {
  width: 61px;
  height: 24px;
  color: white;
  background: linear-gradient(270deg, #3793ff 0%, #245aed 100%);
}
.gap-time {
  height: 12px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 12px;
}
.tip {
  height: 25px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 25px;
  text-align: center;
}
.tip-focus {
  color: #3b8afe;
}
.tip-last {
  padding-bottom: 23px;
}
.totol-salary {
  height: 22px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
.col-item-name-container {
  width: 296px;
  height: 30px;
  background: rgba(226, 241, 255, 1);
  border-radius: 15px;
  margin: 0px 25px;
}
.col-item-name {
  width: 148px;
  height: 18px;
  font-size: 13px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #4e619a;
  line-height: 18px;
  text-align: center;
}
.col-item-name-left {
  margin-left: 9px;
}
.col-item-name-right {
  margin-right: 9px;
}
.col-item-container {
  width: 277px;
  height: 33px;
  margin: 0px 34px;
  border-bottom: 1px solid #ebebeb;
}
.col-item {
  width: 138px;
  height: 20px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #484848;
  line-height: 20px;
  text-align: center;
}
.title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
  line-height: 25px;
}
.salary {
  padding: 14px 0px 7px 0px;
  margin: 0px 20px;
  // border-bottom: 1px solid #ebebeb;
}
.salary-item {
  width: 48px;
  height: 20px;
  margin-left: 14px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 12px;
}
.month-total {
  padding-top: 11px;
  padding-right: 33px;
  padding-bottom: 20px;
}
.total-salary {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #333333;
  line-height: 20px;
}
.active-salary-item {
  font-size: 14px;
  line-height: 20px;
  color: #333333;
}
.salary-item-active {
  width: 21px;
  height: 3px;
  background: #3b8afe;
  margin-top: -12px;
}
.card {
  margin: 0px 15px 10px 15px;
  // width: 345px;
  background: white;
  box-shadow: 0px 1px 19px 0px #c7c7c726;
  border-radius: 10px;
  position: relative;
}
.salary-assess {
  background-color: #f7f7f7;
  padding-bottom: 25px;
  padding-top: 17px;
}
.active-line-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.active-line {
  width: 21px;
  height: 3px;
  background: blue;
}
.inactive-line {
  display: none;
}
.gap-title {
  margin-top: 17px;
  margin-left: 25px;
}
.gap-month-title {
  margin-top: 12px;
  margin-left: 21px;
  .score {
    position: absolute;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 10px;
    justify-content: center;
    align-items: center;
    color: #666666;
    .evaluationScore {
      position: absolute;
      bottom: 180%;
      left: 27%;
      color: #3b8afe;
    }
  }
}
.rule-title {
  margin-left: 20px;
}
.salary-month-container {
  margin: 0 25px;
  margin-top: 28px;
  border: 1px solid #dfdfdf;
  border-bottom: 0;
}
.salary-month-item {
  flex: 1;
  display: flex;
  flex-direction: column;
}
.salary-month-item-row {
  flex: 1;
  display: flex;
  flex-direction: row;
}
.salary-month-item-title,
.salary-month-item-value {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  border-right: 1px solid #eaeaea;
  border-bottom: 1px solid #eaeaea;
  height: 30px;
  background: white;
}
.salary-month-item-title {
  border-bottom: 0;
  height: 27px;
  background: #f9f9f9;
}
.salary-month-item-title:last-child,
.salary-month-item-value:last-child {
  border-right: 0;
}
.salaryTotal {
  color: #fc820e;
}
</style>
