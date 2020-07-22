<template>
  <div>
    <div class="performanceTeam-details">
      <!-- <p>{{ orgdata.teamName || "团队名称" }}</p>
      <p>{{ orgdata.comName || "团队所属机构" }}</p>
      <p>{{ orgdata.teamCode || "团队代码" }}</p>
      <P>{{ orgdata.teamSize || "团队人数" }}人</P> -->
      <div>
        <img
          class="performanceTeam-details--img"
          src="../../../assets/performance/team_default.png"
          alt=""
        />
      </div>
      <div class="performanceTeam-details--content">
        <p>{{ orgdata.teamName }}</p>
        <p>{{ orgdata.comName }}</p>
        <p>
          <span>代码</span>{{ orgdata.teamCode }}<span></span> <span>人数</span
          ><span>{{ orgdata.teamSize }}人</span>
        </p>
      </div>
    </div>
    <div class="performance-details">
      <div class="performance-details_head">
        <span>昨日业绩</span>
      </div>
      <div class="team-ring">
        <div id="teamRing"></div>
        <div
          class="ring_"
          v-if="!!queryData.payreffee && !!queryData.numPayreffee"
        >
          <span>{{ queryData.payreffee }}</span>
          <span>总保费(元）</span>
          <span>{{ queryData.numPayreffee }}</span>
          <span>总件数(件）</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//团队业绩查询
import {
  getPerformanceByQueryType,
  teamQuery
} from "@src/http/module/performance.js";
import echarts from "echarts";
export default {
  props: {
    actived: {
      type: Number
    }
  },
  components: {},
  data() {
    return {
      orgdata: {
        teamName: "", //团队名称
        comName: "", //团队所属机构
        teamCode: "", //团队代码
        teamSize: "" //团队人数
      },
      totalpremium: "23.08",
      piece: "2",
      insurance: [],
      queryData: {
        payreffee: "", //总保费
        payreffeeAcc: "", //意外险保费
        payreffeeHea: "", //健康险保费
        payreffeeCar: "", //车险保费
        payreffeeNoCar: "", //财产险保费
        numPayreffee: "", //总件数
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
            minAngle: 60,
            avoidLabelOverlap: true,
            data: [
              {
                value: 310,
                num: 310,
                // name: "车险",
                name: "车险保费",
                itemStyle: {
                  color: "#7872FF"
                }
              },
              {
                value: 234,
                num: 310,
                // name: "意外险",
                name: "意外险保费",
                itemStyle: {
                  color: "#6DB3FF"
                }
              },
              {
                value: 135,
                num: 310,
                // name: "健康险",
                name: "健康险保费",
                itemStyle: {
                  color: "#FF9151"
                }
              },
              {
                value: 335,
                num: 310,
                // name: "财产险",
                name: "非车险保费",
                itemStyle: {
                  color: "#FFD300"
                }
              }
            ],
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
              // formatter: ["{a|{a}次}", "{b|{b}}", "{c|{c}", "{d|{d}"].join("\n")
            },
            labelLine: {
              length:"5",
              length2: "10"
            }
          }
        ]
      }
    };
  },
  async created() {
    await Promise.all([this.queryPerformance(), this.queryTeam()]);
  },
  mounted() {
    // let teamRing = echarts.init(document.getElementById("teamRing"));
    // teamRing.setOption(this.option);
  },
  methods: {
    //业绩查询
    async queryPerformance() {
      const params = {
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode,
        team: "1",
        queryType: "L"
      };
      await getPerformanceByQueryType(params).then(res => {
        if (res.code === "1" && !!res.data) {
          this.queryData = { ...res.data };
          this.option.series[0] = Object.assign({}, this.option.series[0], {
            data: [
              {
                value: res.data.payreffeeCar,
                num: res.data.numPayreffeeCar,
                // name: "车险",
                name: "车险保费",
                itemStyle: {
                  color: "#7872FF"
                }
              },
              {
                value: res.data.payreffeeAcc,
                num: res.data.numPayreffeeAcc,
                // name: "意外险",
                name: "意外险保费",
                itemStyle: {
                  color: "#6DB3FF"
                }
              },
              {
                value: res.data.payreffeeHea,
                num: res.data.numPayreffeeHea,
                // name: "健康险",
                name: "健康险保费",
                itemStyle: {
                  color: "#FF9151"
                }
              },
              {
                value: res.data.payreffeeNoCar,
                num: res.data.numPayreffeeNoCar,
                // name: "财产险",
                name: "非车险保费",
                itemStyle: {
                  color: "#FFD300"
                }
              }
            ]
          });
          let teamRing = echarts.init(document.getElementById("teamRing"));
          teamRing.resize();
          teamRing.setOption(this.option);
        }
      });
    },
    //团队信息查询
    async queryTeam() {
      await teamQuery({
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode
      }).then(res => {
        if (res.code === "1" && !!res.data) {
          Object.keys(this.orgdata).forEach(key => {
            this.orgdata[key] = res.data[key];
          });
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.performanceTeam-details {
  position: relative;
  margin: 0 auto;
  background: rgba(255, 255, 255, 1);
  padding: 20px 0 20px 24px;
  margin-bottom: 10px;
  box-sizing: border-box;
  display: flex;
  display: -webkit-box;
  display: -webkit-flex;
  &--img {
    width: 60px;
    height: 60px;
    opacity: 1;
    margin-right: 17px;
  }
  &--content {
    p:nth-child(1) {
      font-size: 16px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      margin-bottom: 5px;
    }
    p:nth-child(2) {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      margin-bottom: 10px;
    }
    p:nth-child(3) {
      span:nth-child(odd) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        margin-right: 9px;
      }
      span:nth-child(even) {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(51, 51, 51, 1);
      }
      span:nth-child(2) {
        margin-right: 36px;
      }
    }
  }
}
.performance-details {
  margin: 0 auto;
  background: rgba(255, 255, 255, 1);
  padding-bottom: 20px;
  &_head {
    padding: 10px 0 8px;
    margin: 0 20px;
    display: flex;
    display: -webkit-flex;
    display: -ms-flex;
    display: -o-flex;
    justify-content: space-between;
    -o-justify-content: space-between;
    -webkit-justify-content: space-between;
    -ms-justify-content: space-between;
    align-items: center;
    & > span:nth-child(1) {
      font-size: 14px;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
    & > span:nth-child(2) {
      span:nth-child(1) {
        font-size: 11px;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
        line-height: 12px;
        margin-right: 9px;
      }
      span:nth-child(2) {
        font-size: 14px;
        font-weight: 500;
        color: rgba(59, 138, 254, 1);
        line-height: 15px;
      }
      i {
        color: rgba(102, 102, 102, 1);
        vertical-align: top;
        font-size: 13px;
      }
    }
  }
  .team-ring {
    height: 240px;
    width: 100%;
    position: relative;
    #teamRing {
      height: 240px;
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
  // &_content_line {
  //   display: flex;
  //   display: -webkit-flex;
  //   display: -ms-flex;
  //   display: -o-flex;
  //   justify-content: space-around;
  //   -webkit-justify-content: space-around;
  //   -ms-justify-content: space-around;
  //   -o-justify-content: space-around;
  //   margin-top: 20px;
  //   p:nth-child(1) {
  //     font-size: 14px;
  //     font-weight: 400;
  //     color: rgba(51, 51, 51, 1);
  //   }
  //   p:nth-child(2) {
  //     font-size: 11px;
  //     font-weight: 400;
  //     color: rgba(153, 153, 153, 1);
  //   }
  // }
}
</style>
