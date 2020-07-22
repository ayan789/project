<template>
  <div>
    <div class="performance-details">
      <div class="performance-details_head">
        <span class="title">昨日业绩<img :src='infoImg' class="infoImg" /></span>
        <!-- <van-icon name="info-o" class="icon" :size="12" color="#7297CC" /> -->
      </div>
      <!-- <ve-ring
        :data="yestPerformanceData"
        :settings="chartSettings"
        :extend="extendData"
        :colors="extendData.colors"
      ></ve-ring> -->
      <div class="ring-graphic">
        <div id="ring"></div>
        <div
          class="ring_"
          v-if="!!queryData.payreffee && !!queryData.numPayreffee"
        >
          <span>{{ queryData.payreffee }}</span>
          <span>总保费(元）</span>
          <span>{{ queryData.numPayreffee }}</span>
          <span>总件数(件）</span>
        </div>
        <div class="ring_" v-else>
          <img src="../../../assets/performance/no_data.png" alt="">
          <span style="color：#999999;font-size:12px; ">暂无数据</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//个人业绩查询
import echarts from "echarts";
import { getPerformanceByQueryType } from "@src/http/module/performance.js";
import { Icon } from "vant";
import infoImg from '@/assets/performance/info-i.png'
export default {
  props: {
    actived: {
      type: Number
    }
  },
  components: {
    [Icon.name]: Icon
  },
  data() {
    return {
      infoImg,
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
            minAngle: 50,
            avoidLabelOverlap: true,
            itemStyle: {
              normal: {
                labelLine: {
                  length: 7
                }
              }
            },
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
                name: "意外险保费",
                itemStyle: {
                  color: "#6DB3FF"
                }
              },
              {
                value: 135,
                num: 310,
                // name: "健康险",
                name: "健康险保费",
                itemStyle: {
                  color: "#FF9151"
                }
              },
              {
                value: 335,
                num: 310,
                // name: "财产险",
                name: "非车险保费",
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
            },

            labelLine: {
              length2: "10"
            }
          }
        ]
      }
    };
  },
  created() {
    this.queryPerformance();
  },
  //  watch: {
  // 加个监听的，，防止父级数据改变，子组件没有跟着改变
  // actived: {
  //   handler(val) {
  //     if (val) {
  //       console.log(val)
  //       this.queryPerformance()
  //     }
  //   },
  //   deep: true,
  //   immediate: true
  // }
  // },
  computed: {
    memberCode() {
      return this.$route.query.userCode;
    },
    isMember() {
      return this.$route.query.isMember;
    }
  },
  methods: {
    //业绩查询
    async queryPerformance() {
      const params = {
        userCode: this.memberCode
          ? this.memberCode
          : JSON.parse(localStorage.getItem("userInfo")).userCode,
        team: "",
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
          let ring = echarts.init(document.getElementById("ring"));
          ring.resize();
          ring.setOption(this.option);
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.performance-details {
  position: relative;
  // height: 186px;
  background: #fff;
  &_head {
    padding-left: 26px;
    font-size: 15px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #333333;
    .icon {
      margin-left: 8px;
    }
    .title{
      font-size: 14px;
      display: flex;
      align-items: center;
      font-size: 0.4rem;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 600;
      color: #333333;
      .infoImg{
        margin-left: 5px;
        width: 18px;
        height: 18px;
      }
    }
  }
  &_content_line {
    display: flex;
    display: -webkit-flex;
    display: -ms-flex;
    display: -o-flex;
    justify-content: space-around;
    -webkit-justify-content: space-around;
    -ms-justify-content: space-around;
    -o-justify-content: space-around;
    margin-top: 20px;
    p:nth-child(1) {
      font-size: 14px;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }
    p:nth-child(2) {
      font-size: 11px;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
    }
  }
  #ring {
    height: 240px;
    width: 100%;
  }
  .ring-graphic {
    height: 240px;
    position: relative;
    padding-bottom: 20px;
    .ring_ {
      position: absolute;
      left: 50%;
      top: 47%;
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
}
</style>
