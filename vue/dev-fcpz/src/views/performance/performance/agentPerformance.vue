<template>
  <div>
    <Global_globalNav title="代理人" @clickBack="back()"></Global_globalNav>
    <P class="agent-source"
      ><van-icon name="info-o" size="12" color="#7297CC" />数据来源于基本法</P
    >
    <van-collapse v-model="activeNames" @change="onChange">
      <van-collapse-item
        :border="false"
        :is-link="false"
        v-for="item in activeList"
        :name="item.name"
        :key="item.name"
      >
        <template #title>
          <p @click="checkItem(item)">
            <span>{{ item.title }}</span>
            <span v-show="!activeNames.includes(item.name)"
              >总保费

              <span :style="{ color: '#3B8AFE' }"
                >{{
                  (resultData[item.id][`total_${item.id}`] / 10000).toFixed(2)
                }}万元/{{ resultData[item.id][`num_total_${item.id}`] }}件</span
              >
            </span>
          </p>
        </template>
        <div class="ring-search">
          <div :id="`${item.id}Ring`"></div>
          <div
            class="ring_"
            v-if="
              !!resultData[item.id][`total_${item.id}`] &&
                !!resultData[item.id][`num_total_${item.id}`]
            "
          >
            <span>{{
              (Number(resultData[item.id][`total_${item.id}`]) / 10000).toFixed(
                2
              )
            }}</span>
            <span>总保费(万元）</span>
            <span>{{ resultData[item.id][`num_total_${item.id}`] }}</span>
            <span>总件数(件）</span>
          </div>
          <div class="ring_" v-else>
            <img src="../../../assets/performance/no_data.png" alt="" />
            <span
              style="color：#999999;font-size:12px; position:absolute; bottom: 0px;left: 50%;transform: translate(-50%, -50%);"
              >暂无数据</span
            >
          </div>
        </div>
      </van-collapse-item>
    </van-collapse>
  </div>
</template>

<script>
import echarts from "echarts";
import { agentPremiumQuery } from "@src/http/module/performance.js";
export default {
  data: () => ({
    activeNames: ["1"],
    activeList: [
      { name: "1", title: "昨日业绩", id: "d" },
      { name: "2", title: "本月业绩", id: "m" },
      // { name: "3", title: "本季度业绩", id: "q" },
      { name: "3", title: "本年业绩", id: "y" }
    ],
    resultData: {
      d: {},
      m: {},
      q: {},
      y: {}
    },
    ringList: {},
    option: {
      series: [
        {
          type: "pie",
          radius: ["60%", "80%"],
          grid: {
            width: "70%"
          },
          left: "center",
          minAngle: 60,
          avoidLabelOverlap: true,
          data: [],
          label: {
            show: true,
            color: "#333",
            fontSize: "12px",
            formatter(e) {
              return `{a|${e.name}}\n {b|${e.data.value}万元}\n{b|${e.data.num}件}`;
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
  }),
  async created() {
    this.agentPremiumQueryPost();
  },
  methods: {
    back() {
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb();
      } else {
        this.$router.push({ path: "/" });
      }
    },
    // /**
    //  * 参数转FORMDAtA 测试完毕后删除
    //  */
    // formData(data) {
    //   let ret = "";
    //   for (let it in data) {
    //     ret +=
    //       encodeURIComponent(it) + "=" + encodeURIComponent(data[it]) + "&";
    //   }
    //   return ret;
    // },
    // /**
    //  * 参数转FORMDAtA 测试完毕后删除
    //  */
    checkItem(val) {
      console.log(val);
      console.log(this.resultData[val.id]);
      this.option.series[0] = Object.assign({}, this.option.series[0], {
        data: [
          {
            value: (
              Number(this.resultData[val.id][`car_${val.id}`]) / 10000
            ).toFixed(2),
            num: this.resultData[val.id][`num_car_${val.id}`],
            // name: "车险",
            name: "车险保费",
            itemStyle: {
              color: "#7872FF"
            }
          },
          {
            value: (
              Number(this.resultData[val.id][`acc_${val.id}`]) / 10000
            ).toFixed(2),
            num: this.resultData[val.id][`num_acc_${val.id}`],
            // name: "意外险",
            name: "意外险保费",
            itemStyle: {
              color: "#6DB3FF"
            }
          },
          {
            value: (
              Number(this.resultData[val.id][`hea_${val.id}`]) / 10000
            ).toFixed(2),
            num: this.resultData[val.id][`num_hea_${val.id}`],
            // name: "健康险",
            name: "健康险保费",
            itemStyle: {
              color: "#FF9151"
            }
          },
          {
            value: (
              Number(this.resultData[val.id][`nocar_${val.id}`]) / 10000
            ).toFixed(2),
            num: this.resultData[val.id][`num_nocar_${val.id}`],
            // name: "财产险",
            name: "非车险保费",
            itemStyle: {
              color: "#FFD300"
            }
          }
        ]
      });
      setTimeout(() => {
        this.ringList[val.id] = echarts.init(
          document.getElementById(`${val.id}Ring`)
        );
        this.ringList[val.id].setOption(this.option);
      }, 0);
    },
    onChange(value) {},
    agentPremiumQueryPost() {
      console.log("agentCode", this.$localStorage.agentCode);
      agentPremiumQuery({
        agentCode: this.$localStorage.agentCode || ""
      }).then(res => {
        if (res.code === "1") {
          let { data = {} } = res;
          Object.keys(data).map((item, index) => {
            if (item.endsWith("d")) {
              this.$set(this.resultData["d"], item, data[item]);
            }
            if (item.endsWith("m")) {
              this.$set(this.resultData["m"], item, data[item]);
            }
            if (item.endsWith("q")) {
              this.$set(this.resultData["q"], item, data[item]);
            }
            if (item.endsWith("y")) {
              this.$set(this.resultData["y"], item, data[item]);
            }
          });

          //echarts
          Object.keys(this.resultData).map((item, index) => {
            //echarts
            this.option.series[0] = Object.assign({}, this.option.series[0], {
              data: [
                {
                  value: (
                    Number(this.resultData[item][`car_${item}`]) / 10000
                  ).toFixed(2),
                  num: this.resultData[item][`num_car_${item}`],
                  // name: "车险",
                  name: "车险保费",
                  itemStyle: {
                    color: "#7872FF"
                  }
                },
                {
                  value: (
                    Number(this.resultData[item][`acc_${item}`]) / 10000
                  ).toFixed(2),
                  num: this.resultData[item][`num_acc_${item}`],
                  // name: "意外险",
                  name: "意外险保费",
                  itemStyle: {
                    color: "#6DB3FF"
                  }
                },
                {
                  value: (
                    Number(this.resultData[item][`hea_${item}`]) / 10000
                  ).toFixed(2),
                  num: this.resultData[item][`num_hea_${item}`],
                  // name: "健康险",
                  name: "健康险保费",
                  itemStyle: {
                    color: "#FF9151"
                  }
                },
                {
                  value: (
                    Number(this.resultData[item][`nocar_${item}`]) / 10000
                  ).toFixed(2),
                  num: this.resultData[item][`num_nocar_${item}`],
                  // name: "财产险",
                  name: "非车险保费",
                  itemStyle: {
                    color: "#FFD300"
                  }
                }
              ]
            });
            this.ringList[item] = echarts.init(
              document.getElementById(`${item}Ring`)
            );
            this.ringList[item].setOption(this.option);
          });
        } else {
          this.$dialog.alert({
            message: res.msg
          });
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
/deep/.navigatorBottom {
  background: transparent !important;
}
.agent-source {
  padding-left: 23px;
  margin: 80px 0 15px;
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
}
.van-icon {
  margin-right: 3px;
  vertical-align: middle;
}
/deep/.van-collapse-item__content {
  padding: 0;
}
/deep/.navigatorBottom {
  background: #f5f5f5;
}
/deep/.van-collapse {
  margin: 0 10px;
  border-radius: 10px;
}
/deep/ [class*="van-hairline"]::after {
  border: none;
}
/deep/.van-cell:not(:last-child)::after {
  border-bottom: none;
}
.van-cell__title {
  p {
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
  }
}
.ring-search {
  height: 240px;
  width: 100%;
  position: relative;
  #dRing,
  #mRing,
  #qRing,
  #yRing {
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
</style>
