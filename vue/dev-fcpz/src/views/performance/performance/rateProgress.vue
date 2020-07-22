<template>
  <div class="rateProgress">
    <div class="rateProgress_head">
      <div class="title">达成进度<img :src="infoImg" class="infoImg" /></div>
      <div class="search" v-if="this.isResponsibility">
        <router-link :to="{ path: '/rateSearch' }" class="serach-link"
          ><van-icon name="search" color="#6E97D3" size="16px" />
          <span>高级查询</span>
        </router-link>
      </div>
    </div>
    <div class="rateProgress_content">
      <div class="tabs" v-if="this.isResponsibility">
        <span
          class="tabMonth"
          v-for="(item, index) in tabMonth"
          :key="index"
          @click="checkTag(index)"
          :class="[activedItem === index ? 'tabActived' : '']"
        >
          {{ item }}
        </span>
        <div class="progress_rate" v-if="this.activedItem != 2">
          <div class="progressMz">
            <p>{{ progressMz.name }}</p>
            <p>(达成率 {{ progressMz.rate }}%)</p>
            <p><span class="title">已完成 </span>{{ (progressMz.actual / 10000).toFixed(2) }}万元</p>
            <p><span class="title">总目标 </span>{{ (progressMz.total / 10000).toFixed(2) }}万元</p>
          </div>
          <div v-for="(item, index) in progressData" :key="index" v-show="item.total">
            <p>
              <span>{{ item.name }}</span
              ><span>（ 达成率{{ item.rate }}% ）</span>
            </p>
            <van-progress
              :percentage="item.rate > 100 ? 100 : item.rate"
              stroke-width="6"
              :show-pivot="false"
              :color="item.progressColor"
            />
            <p>
              <span>{{ (item.actual / 10000).toFixed(2) }}万元</span><span>{{ (item.total / 10000).toFixed(2) }}万元</span>
            </p>
          </div>
        </div>
        <div class="progress_year" v-if="this.activedItem === 2">
          <div id="line"></div>
        </div>
      </div>
      <div v-else>
        <div class="achieveProPart" @click="remidehis">
          <div class="posPic">
            <img :src="posPic" alt="" />
          </div>
          <p class="responsibility">
            {{
              `${
                actived === 0
                  ? "您还未制定个人年度计划，快去完成吧"
                  : isMember
                  ? "该成员还未签署责任书，提醒他"
                  : "您团队成员均未签署责任书 "
              }`
            }}
          </p>
          <p class="go">快去完成</p>
        </div>
      </div>
      <ve-radar
        v-if="this.actived === 0 || this.isMember == 1"
        :data="chartData"
        shape="polygon"
        :title="polygonData.radarTitle"
        :grid="polygonData.radarGrid"
        :legend="polygonData.legend"
        :colors="polygonData.color"
        :extend="chartExtend"
        :radar="chartExtend"
        height="250px"
      ></ve-radar>
      <!-- <van-icon name="info-o" class="icon" /> -->
    </div>
  </div>
</template>

<script>
import {
  getMonthlyQuarterlyProgress,
  getAnnualProgress,
  getAssess,
  responsibilityListQuery,
  responsibilityNotice,
  firstListQuery
} from "@src/http/module/performance.js";
import echarts from "echarts";
import { Progress } from "vant";
import infoImg from "@/assets/performance/info-i.png";
export default {
  props: {
    actived: Number
  },
  components: {
    [Progress.name]: Progress
  },
  data() {
    this.chartExtend = {
      radar: {
        name: {
          show: true,
          color: "#333333"
        },
        legend: {
          show: false
        },
        shape: "polygon",
        center: ["50%", "50%"],
        radius: "50%"
      },
      indicator: [
        { name: "产能", max: 100 },
        { name: "客户数", max: 100 },
        { name: "非车业务占比", max: 100 },
        { name: "月0保费天数", max: 100 },
        { name: "赔付率", max: 100 },
        { name: "续保率", max: 100 }
      ]
    };
    return {
      infoImg,
      posPic: require("@/assets/performance/posPic.png"),
      isResponsibility: true, //判断是否签订责任书
      polygonData: {
        color: ["#F19E51", "#3B8AFE"],
        legend: {
          right: "20",
          textStyle: {
            color: "#999999",
            fontSize: "12px"
          }
        },
        radarGrid: {
          top: "20"
        },
        radarTitle: {
          text: "销售行动力测评",
          textStyle: {
            fontSize: 14,
            fontFamily: "PingFangSC-Medium,PingFang SC"
          },
          left: "5%"
        }
      },
      activedItem: 0, //达成进度切换tab
      tabMonth: ["本月", "本季", "本年"],
      chartData: {
        columns: [
          "标准",
          "产能",
          "客户数",
          "非车业务占比",
          "月0保费天数",
          "赔付率",
          "续保率"
        ],
        rows: []
      },
      progressData: [],
      progressMz: {},
      progressChartData: {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "line",
            label: {}
          },
          textStyle: {
            color: "#666"
          },
          position: ["40%", "-10%"],
          padding: [5, 21, 7, 9],
          backgroundColor: "#fff",
          formatter(e) {
            // console.log(e[0].seriesName,"==");
            return `${e[0].seriesName}:${(e[0].data)/10000}万元 <br /> ${e[1].seriesName}:${e[1].data}%`;
          }
        },
        grid: {
          left: 50,
          top: 30
        },
        xAxis: [
          {
            type: "category",
            axisLabel: {
              fontSize: "9",
              fontWeight: "400",
              color: "#666",
              interval: 0,
              margin: 10
            },
            axisTick: {
              show: false
            },
            axisLine: {
              lineStyle: {
                color: "#E0E0E0"
              }
            },
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月"
            ]
          }
        ],
        yAxis: [
          {
            type: "value",
            name: "总保费(万元)",
            axisLine: {
              show: false
            },
            axisLabel: {
              fontSize: "10",
              color: "#666",
              fontWeight: "400",
              /* -- */
              formatter(value, index) {
                // console.log(value);
                return `${(value / 10000).toFixed(2)}`;
              }
            },
            axisTick: {
              show: false
            },
            // interval: 200,
            // max: 2000,
            min: 0
          },
          {
            type: "value",
            name: "达成率(%)",
            axisLine: {
              show: false
            },
            axisLabel: {
              fontSize: "10",
              color: "#666",
              fontWeight: "400"
            },
            axisTick: {
              show: false
            },
            // max: 100,
            min: 0
          }
        ],
        series: [
          {
            name: "总保费",
            type: "line",
            value: "monthActualPremium",
            yAxisIndex: 0,
            // yAxisIndex: 1,
            smooth: true,
            lineStyle: {
              color: "#3792FF"
            },
            areaStyle: {
              color: {
                type: "linear",
                colorStops: [
                  {
                    offset: 0,
                    color: "#FFFFFF"
                  },
                  {
                    offset: 1,
                    color: "#3792FF"
                  }
                ]
              }
            },
            itemStyle: {
              color: "#3792FF"
            }
            // data: [
            //   5000,
            //   8000,
            //   5000,
            //   5000,
            //   9000,
            //   5000,
            //   5000,
            //   1000,
            //   5000,
            //   9000,
            //   4000,
            //   5000
            // ]
          },
          {
            name: "达成率",
            type: "line",
            value: "rate",
            yAxisIndex: 1,
            smooth: true,
            lineStyle: {
              color: "#FC820E"
            },
            itemStyle: {
              opacity: 0
            }
            // data: [32, 26, 76, 49, 32, 78, 78, 78, 78, 78, 78, 78]
          }
        ]
      }
    };
  },
  computed: {
    memberCode() {
      return this.$route.query.userCode;
    },
    isMember() {
      return this.$route.query.isMember;
    }
  },
  async created() {
    await Promise.all([
      this.queryGetAssess(),
      this.responsibilityListQuery_(),
      this.firstStatus()
    ]);
  },
  methods: {
    isSameDay(timeStampA, timeStampB) {
      let dateA = new Date(timeStampA);
      let dateB = new Date(timeStampB);
      return dateA.setHours(0, 0, 0, 0) == dateB.setHours(0, 0, 0, 0);
    },
    async firstStatus() {
      let data = {
        userCode: localStorage.getItem("userCode"),
        isYear: `${new Date().getFullYear().toString()}`,
        responsibilityType: this.actived == 0 || this.memberCode ? "P" : "T",
        isTeamLeader: "0"
      };
      await firstListQuery(data).then(res => {
        if (res.code === "1") {
        }
      });
    },
    remidehis() {
      if (this.actived === 0) {
        // this.$router.push("/performance/responsibility/responsibilityEdit");
        let data = {
          userCode: localStorage.getItem("userCode"),
          isYear: `${new Date().getFullYear().toString()}`,
          responsibilityType: this.actived == 0 || this.memberCode ? "P" : "T",
          isTeamLeader: "0"
        };
        firstListQuery(data).then(res => {
          if (res.code === "1") {
            // localStorage.setItem("SignOfRespon", JSON.stringify(res.data) )
            this.$router.push({
              name: "responsibilityEdit",
              query: {
                data: JSON.stringify([res.data])
              }
            });
          }
        });
      } else {
        const responsibilityNotice = localStorage.getItem(
          "responsibilityNotice" + this.$route.query.userCode
        );
        const isSameDay = this.isSameDay(new Date(), responsibilityNotice);
        if (isSameDay) {
          this.$toast("一天仅支持提醒一次，请明天再跟进");
        } else {
          this.noticeTips();
        }
      }
    },
    checkTag(index) {
      this.activedItem = index;
      if (this.activedItem === 2) {
        this.queryAnnualProgress();
      } else {
        this.queryMonthlyQuarterlyProgress();
        try {
          let line = echarts.init(document.getElementById("line"));
          line.dispose();
        } catch (err) {}
      }
    },
    noticeTips() {
      // responsibilityNotice([this.$route.query.userCode]).then(res => {
      responsibilityNotice({"userCode" : this.$route.query.userCode}).then(res => {
        if (res.code === "1") {
          localStorage.setItem(
            "responsibilityNotice" + this.$route.query.userCode,
            new Date()
          );
        }
      });
    },
    //根据个人和团队修改入参
    delParams({ [key]: _, ...rest }, key) {
      return rest;
    },
    //重新组装进度返回的数据--本月
    renewMounthData(res, mounthProgress) {
      this.progressData = [...[]];
      const action = {
        mc: {
          name: "车险保费",
          rate: (Number(res.mcRat) * 100).toFixed(),
          actual: res.mcs,
          total: res.mc,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        mh: {
          // name: "意外总保费",
          name: "意外险保费",
          rate: (Number(res.mhRat) * 100).toFixed(),
          actual: res.mhs,
          total: res.mh,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        ma: {
          // name: "健康保费",
          name: "健康险保费",
          rate: (Number(res.maRat) * 100).toFixed(),
          actual: res.mas,
          total: res.ma,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        mp: {
          // name: "财产险总保费",
          name: "非车险保费",
          rate: (Number(res.mpRat) * 100).toFixed(),
          actual: res.mps,
          total: res.mp,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        }
      };
      mounthProgress.map(item => {
        this.progressData.push(action[item]);
      });
      return this.progressData;
    },
    //重新组装进度返回的数据---季度
    renewuQarterData(res, mounthProgress) {
      this.progressData = [...[]];
      const action = {
        qc: {
          name: "车险保费",
          rate: (Number(res.qcRat) * 100).toFixed(),
          actual: res.qcs,
          total: res.qc,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        qh: {
          // name: "意外总保费",
          name: " 意外险保费",
          rate: (Number(res.qhRat) * 100).toFixed(),
          actual: res.qhs,
          total: res.qh,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        qa: {
          // name: "健康保费",
          name: "健康险保费",
          rate: (Number(res.qaRat) * 100).toFixed(),
          actual: res.qas,
          total: res.qa,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        },
        qp: {
          // name: "财产险总保费",
          name: "非车险保费",
          rate: (Number(res.qpRat) * 100).toFixed(),
          actual: res.qps,
          total: res.qp,
          progressColor:
            "linear-gradient(220deg,rgba(121,175,251,1) 0%,rgba(68,119,245,1) 100%);"
        }
      };
      mounthProgress.map(item => {
        this.progressData.push(action[item]);
      });
      return this.progressData;
    },
    //本月和本季度查询
    async queryMonthlyQuarterlyProgress() {
      let params = {
        isYear: `${new Date().getFullYear().toString()}`,
        teamCode: this.$localStorage.teamcode || "",
        timeFlag: this.activedItem === 0 ? "M" : "Q",
        responsibilityType: this.actived === 0 || this.memberCode ? "P" : "T",
        userCode: this.memberCode
          ? this.memberCode
          : JSON.parse(localStorage.getItem("userInfo")).userCode
        // userCode:'8000154962'
      };
      //根据个人和团队区分入参
      this.actived == 0 || this.isMember == 1
        ? (params = this.delParams(params, "teamCode"))
        : (params = this.delParams(params, "userCode"));
      //月度-，车险保费，健康保费，意外总保费，财产险总保费
      let mounthProgress = ["mc", "mh", "ma", "mp"];
      ``;
      //季度-，车险保费，健康保费，意外总保费，财产险总保费
      let quarterProgress = ["qc", "qh", "qa", "qp"];
      await getMonthlyQuarterlyProgress(params).then(res => {
        if (res.code === "1") {
          let tempObj = res.data.list.find(
            (item, index) => index == new Date().getMonth()
          );
          this.activedItem === 0
            ? this.renewMounthData(tempObj, mounthProgress)
            : this.renewuQarterData(tempObj, quarterProgress);
          this.activedItem === 0
            ? (this.progressMz = Object.assign({}, this.progressMz, {
                name: "总保费",
                rate: (Number(tempObj.mzRat) * 100).toFixed(),
                actual: tempObj.mzs,
                total: tempObj.mz
              }))
            : (this.progressMz = Object.assign({}, this.progressMz, {
                name: "总保费",
                rate: (Number(tempObj.qzRat) * 100).toFixed(),
                actual: tempObj.qzs,
                total: tempObj.qz
              }));
        }
      });
    },
    //本年查询
    async queryAnnualProgress() {
      let params = {
        teamCode: this.$localStorage.teamcode || "",
        timeFlag: "Y",
        isYear: new Date().getFullYear().toString(),
        responsibilityType: this.actived === 0 || this.memberCode ? "P" : "T",
        userCode: this.memberCode
          ? this.memberCode
          : JSON.parse(localStorage.getItem("userInfo")).userCode
      };
      //根据个人和团队区分入参
      this.actived == 0 || this.isMember == 1
        ? (params = this.delParams(params, "teamCode"))
        : (params = this.delParams(params, "userCode"));
      await getAnnualProgress(params).then(res => {
        if (res.code === "1") {
          let tempArr1 = [],
            tempArr2 = [];
          [...res.data.list].map(item => {
            tempArr1.push(item["monthActualPremium"]);
          });
          [...res.data.list].map(item => {
            tempArr2.push(Number(item["rate"] * 100));
          });
          this.progressChartData.series.forEach(item => {
            item.data =
              item.value === "monthActualPremium" ? tempArr1 : tempArr2;
          });
          this.$nextTick(() => {
            let line = echarts.init(document.getElementById("line"));
            line.setOption(this.progressChartData);
          });
        }
      });
    },
    //
    compare(actual, standard) {
      let [tempActual, tempStandard] = [Number(actual), Number(standard)];
      if (tempActual > tempStandard) {
        return ((tempStandard / tempActual) * 100).toFixed(2);
      } else if (tempActual < tempStandard) {
        return ((tempActual / tempStandard) * 100).toFixed(2);
      } else {
        return 100;
      }
    },
    //查询销售力评估
    async queryGetAssess() {
      await getAssess({
        userCode: this.memberCode
          ? this.memberCode
          : JSON.parse(localStorage.getItem("userInfo")).userCode
      }).then(res => {
        if (res.code === "1" && res) {
          let { data } = res;
          if (data == null) {
            return;
          }
          for (let key in data) {
            this.$set(data, key, Number(data[key]));
          }
          this.chartExtend = Object.assign(this.chartExtend, {
            tooltip: {
              formatter(params) {
                //实际值
                if (params.dataIndex === 1) {
                  return `产能: ${data.capacityStandard}<br/>客户数: ${data.customerTotalStandard}<br/>非车业务占比: ${data.nocarPremiumRatioStandard}<br/>月0保费天数: ${data.totalZeroPremiumDayStandard}<br/>赔付率: ${data.lossRatioStandard}<br/>续保率: ${data.renewalRatioStandard}`;
                } else {
                  return `产能: ${data.capaCity}<br/>客户数: ${data.customerTotal}<br/>非车业务占比: ${data.noncarPremiumRatio}<br/>月0保费天数: ${data.totalZeroPremiumDay}<br/>赔付率: ${data.lossRatio}<br/>续保率: ${data.renewalRatio}`;
                }
              }
            }
          });
          this.chartData.rows = [
            {
              标准: "实际值",
              产能:
                data.capaCity > data.capacityStandard
                  ? 100
                  : this.compare(data.capaCity, data.capacityStandard),
              客户数:
                data.customerTotal > data.customerTotalStandard
                  ? 100
                  : this.compare(
                      data.customerTotal,
                      data.customerTotalStandard
                    ),
              非车业务占比:
                data.noncarPremiumRatio > data.nocarPremiumRatioStandard
                  ? 100
                  : this.compare(
                      data.noncarPremiumRatio,
                      data.nocarPremiumRatioStandard
                    ),
              月0保费天数:
                data.totalZeroPremiumDay > data.totalZeroPremiumDayStandard
                  ? 100
                  : this.compare(
                      data.totalZeroPremiumDay,
                      data.totalZeroPremiumDayStandard
                    ),
              赔付率:
                data.lossRatio > data.lossRatioStandard
                  ? 100
                  : this.compare(data.lossRatio, data.lossRatioStandard),
              续保率:
                data.renewalRatio > data.renewalRatioStandard
                  ? 100
                  : this.compare(data.renewalRatio, data.renewalRatioStandard)
            },
            {
              标准: "标准值",
              产能:
                data.capacityStandard > data.capaCity
                  ? 100
                  : this.compare(data.capaCity, data.capacityStandard),
              客户数:
                data.customerTotal < data.customerTotalStandard
                  ? 100
                  : this.compare(
                      data.customerTotal,
                      data.customerTotalStandard
                    ),
              非车业务占比:
                data.noncarPremiumRatio < data.nocarPremiumRatioStandard
                  ? 100
                  : this.compare(
                      data.noncarPremiumRatio,
                      data.nocarPremiumRatioStandard
                    ),
              月0保费天数:
                data.totalZeroPremiumDay < data.totalZeroPremiumDayStandard
                  ? 100
                  : this.compare(
                      data.totalZeroPremiumDay,
                      data.totalZeroPremiumDayStandard
                    ),
              赔付率:
                data.lossRatio < data.lossRatioStandard
                  ? 100
                  : this.compare(data.lossRatio, data.lossRatioStandard),
              续保率:
                data.renewalRatio < data.renewalRatioStandard
                  ? 100
                  : this.compare(data.renewalRatio, data.renewalRatioStandard)
            }
          ];
        }
      });
    },
    //判断是否签订责任书
    async responsibilityListQuery_() {
      try {
        let res = await responsibilityListQuery({
          userCode: this.memberCode
            ? this.memberCode
            : JSON.parse(localStorage.getItem("userInfo")).userCode,
          // userCode:'8000154962',
          isYear: `${new Date().getFullYear().toString()}`,
          responsibilityType: this.actived == 0 || this.memberCode ? "P" : "T"
        });
        if (res && res.code === "1" && res.data.list[0].signStatus === "1") {
          await this.queryMonthlyQuarterlyProgress();
        } else {
          this.isResponsibility = false;
        }
        // await this.queryMonthlyQuarterlyProgress();
      } catch (err) {}
    }
  }
};
</script>

<style lang="less" scoped>
.rateProgress {
  margin: 10px auto 0;
  background: #ffffff;
  &_head {
    padding: 11px 0;
    margin: 0 20px 18px;
    display: flex;
    display: -webkit-flex;
    justify-content: space-between;
    -webkit-justify-content: space-between;
    .title {
      font-size: 14px;
      display: flex;
      align-items: center;
      font-size: 0.4rem;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 600;
      color: #333333;
      .infoImg {
        margin-left: 5px;
        width: 18px;
        height: 18px;
      }
    }
    .search {
      .serach-link {
        display: flex;
        align-items: center;
        .van-icon-search {
          margin-top: 1px;
        }
      }
      span {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(59, 138, 254, 1);
        // line-height:37px;
      }
    }
  }
  &_content {
    position: relative;
    .tabs {
      margin: 0 19px;
      & > span {
        display: inline-block;
        width: 64px;
        height: 24px;
        border-radius: 5px;
        line-height: 24px;
        background: #fff;
        opacity: 0.9;
        font-size: 12px;
        font-weight: 400;
        border: 1px solid rgba(224, 224, 224, 1);
        text-align: center;
        margin-right: 8px;
        margin-bottom: 30px;
      }
      & > span:nth-child(3) {
        margin-right: 0;
      }
      .tabActived {
        background: linear-gradient(
          270deg,
          rgba(55, 147, 255, 1) 0%,
          rgba(36, 90, 237, 1) 100%
        );
        color: rgba(255, 255, 255, 1);
        opacity: 1;
      }
    }
    .achieveProPart {
      text-align: center;
      .responsibility {
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        line-height: 17px;
      }
      .posPic {
        width: 175px;
        height: 126px;
        margin: 0 auto;
        img {
          width: 100%;
        }
      }
      .go {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(59, 138, 254, 1);
        line-height: 18px;
        padding-bottom: 26px;
      }
    }
    .icon {
      position: absolute;
      right: 8px;
      bottom: 8px;
    }
  }
  .progress_rate {
    padding-bottom: 20px;
    p:nth-child(1) {
      margin-bottom: 8px;
      span:nth-child(1) {
        font-size: 16px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
      span:nth-child(2) {
        font-size: 14px;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
    }
    p:nth-child(3) {
      display: flex;
      display: -webkit-flex;
      display: -ms-flex;
      display: -o-flex;
      justify-content: space-between;
      -webkit-justify-content: space-between;
      -ms-justify-content: space-between;
      -o-justify-content: space-between;
      font-size: 12px;
      font-weight: 400;
      color: #7f7f7f;
      margin-top: 6px;
      margin-bottom: 13px;
    }
    & > .progressMz {
      margin-bottom: 30px;
      padding-left: 60%;
      background: url("../../../assets/performance/progress.png") no-repeat;
      background-size: contain;
      p {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(84, 84, 84, 1);
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: start;
        -webkit-justify-content: flex-start;
        justify-content: flex-start;
        .title {
          font-size: 12px;
          font-family: PingFangSC-Regular, PingFang SC;
          font-weight: 400;
          color: rgba(84, 84, 84, 1);
        }
      }
      p:nth-child(1) {
        font-size: 16px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
      }
      p:nth-child(2) {
        font-size: 13px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
      }
    }
  }
  .progress_year {
    height: 200px;
    width: 100%;
    #line {
      height: 200px;
      width: 100%;
    }
    .echarts {
      height: 200px;
      width: 100%;
    }
  }
}
</style>
