<template>
  <div>
    <popNav popTitle="重货评分" />
    <div class="popBody">
      <!-- <div class="backImg">
        <div class="title">险别信息</div>
      </div> -->
      <div class="card">
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              商业三者险期望出险频率
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'triBusinTripRisk'"
            >
              {{ item.key == "triBusinTripRisk"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              驾驶行为全国评分
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'nationalDriScore'"
            >
              {{ item.key == "nationalDriScore"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              驾驶行为本省评分
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'provinceDriScore'"
            >
              {{ item.key == "provinceDriScore"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              数据状态描述
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dataSatusDesc'"
            >
              {{ item.key == "dataSatusDesc"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              号牌号码
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'licenseNo'"
            >
              {{ item.key == "licenseNo"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              车身颜色
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'plateColor'"
            >
              {{ item.key == "plateColor"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              车架号/VIN码
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'vin'"
            >
              {{ item.key == "vin"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              轨迹完整车
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'trackIntegrity'"
            >
              {{ item.key == "trackIntegrity"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              平均速度
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'averageSpeed'"
            >
              {{ item.key == "averageSpeed"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              日均疲劳时长
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dailyFatigueTime'"
            >
              {{ item.key == "dailyFatigueTime"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              夜间行驶里程占比
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'nightDriRatio'"
            >
              {{ item.key == "nightDriRatio"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              高速公路行驶里程占比
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'highwayRatio'"
            >
              {{ item.key == "highwayRatio"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              超速行驶里程占比
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'speedingRatio'"
            >
              {{ item.key == "speedingRatio"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              运营率
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'operationRatio'"
            >
              {{ item.key == "operationRatio"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              第一长跑路线次数占比
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'firstRunRouteRatio'"
            >
              {{ item.key == "firstRunRouteRatio"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              百公里途径危险道路次数占比
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dangerRoadNo'"
            >
              {{ item.key == "dangerRoadNo"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              日均行驶里程
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dailyMileage'"
            >
              {{ item.key == "dailyMileage"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              轨迹完整率系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'trackInteCoefficient'"
            >
              {{ item.key == "trackInteCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              平均速度系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'avgSpeedCoefficient'"
            >
              {{ item.key == "avgSpeedCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              日均运营时长系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dayOperaCoefficient'"
            >
              {{ item.key == "dayOperaCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              夜间行驶里程占比系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'nightDriCoefficient'"
            >
              {{ item.key == "nightDriCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              高速公路行驶里程占比系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'highwayCoefficient'"
            >
              {{ item.key == "highwayCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              超速行驶里程占比系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'speeedingCoefficient'"
            >
              {{ item.key == "speeedingCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              运营率系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'operationCoefficient'"
            >
              {{ item.key == "operationCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              第一长跑路线次数占比系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'friRouteCoefficient'"
            >
              {{ item.key == "friRouteCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              百公里途径危险道路次数系数值
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'dangerRoadCoefficient'"
            >
              {{ item.key == "dangerRoadCoefficient"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              大本营
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'camp'"
            >
              {{ item.key == "camp"? item.value:'' }}
            </div>
          </div>
        </div>
        <div class="rowXian1"
          >
          <div>
            <div class="left_t">
              常跑路线
            </div>
            <div 
              v-for="(item, index) in detailed"
              :key="index"
              class="right_t" 
              v-show="item.key == 'oftenRoute'"
            >
              {{ item.key == "oftenRoute"? item.value:'' }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";

export default {
  components: {
    popNav
  },
  data () {
    return {
      detailed: []
    };
  },
  mounted () {
    this.$nextTick(() => {
      this.detailed = this.$route.query;
    });
  },
  computed: {
    heavygoodsratings () {
      return [
        { text: "商业三者险期望出险频率", value: "triBusinTripRisk" },
        { text: "驾驶行为全国评分", value: "nationalDriScore" },
        { text: "驾驶行为本省评分", value: "provinceDriScore" },
        { text: "驾驶行为本省评分", value: "dataSatusDesc" },
        { text: "号牌号码", value: "licenseNo" },
        { text: "车牌颜色，详见列表", value: "plateColor" },
        { text: "车辆识别代号（车架号/VIN码）", value: "vin" },
        { text: "轨迹完整率", value: "trackIntegrity" },
        { text: "平均速度", value: "averageSpeed" },
        { text: "日均疲劳时长", value: "dailyFatigueTime" },
        { text: "日均运营时长", value: "dailyOperatTime" },
        { text: "夜间行驶里程占比", value: "nightDriRatio" },
        { text: "高速公路行驶里程占比", value: "highwayRatio" },
        { text: "超速行驶里程占比", value: "speedingRatio" },
        { text: "运营率", value: "operationRatio" },
        { text: "第一常跑路线次数占比", value: "firstRunRouteRatio" },
        { text: "百公里途径危险道路次数", value: "dangerRoadNo" },
        { text: "日均行驶里程", value: "dailyMileage" },
        { text: "轨迹完整率系数值", value: "trackInteCoefficient" },
        { text: "平均速度系数值", value: "avgSpeedCoefficient" },
        { text: "日均运营时长系数值", value: "dayOperaCoefficient" },
        { text: "夜间行驶里程占比系数值", value: "nightDriCoefficient" },
        { text: "高速公路行驶里程占比系数值", value: "highwayCoefficient" },
        { text: "超速行驶里程占比系数值", value: "speeedingCoefficient" },
        { text: "运营率系数值", value: "operationCoefficient" },
        { text: "第一常跑路线次数占比系数值", value: "friRouteCoefficient" },
        { text: "百公里途径危险道路次数系数值", value: "dangerRoadCoefficient" }
      ];
    }
  },
  methods: {
    //返回上一页
    goBack () {
      this.$globalBack();
    }
  }
};
</script>
<style scoped lang="less">
.popBody {
  margin-top: 64px;
  background: #ffffff;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  margin-right: 150px;
  padding-top: 10px;
  height: 700px;
  // .van-tabs {
  //   width: 240px;
  // }
  .card {
    padding: 0.3rem 15px;
    background: #ffffff;
    .rowXian {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
      }
      div:nth-of-type(2) {
        text-align: center;
      }
      div:nth-of-type(3) {
        text-align: right;
      }
    }
    .rowXian1 {
      display: flex;
      div {
        flex: 1;
        line-height: 24px;
        justify-content: space-around;
        .left_t {
          float: left;
        }
        .right_t {
          float: right !important;
        }
      }
      // div:nth-of-type(2) {
      //   text-align: center;
      // }
      // div:nth-of-type(3) {
      //   text-align: right;
      // }
    }
    h4 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 14px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
        width: 100%;
        background-color: #e1e1e1;
        display: block;
        -webkit-transform-origin: 50% 100%;
        transform-origin: 50% 100%;
      }
    }
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      // padding: 0.1rem 0;
    }
  }
}
.backImg {
  height: 69px;
  background: url("../../../assets/order/orderList/headers.png") no-repeat;
  .title {
    font-size: 16px;
    font-weight: 500;
    color: #000;
    line-height: 75px;
    padding-left: 20px;
  }
}
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
.rowXian1,
.rowXian {
  font-size: 14px;
}
</style>
