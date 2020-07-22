<template>
  <div>
    <popNav popTitle="车辆信息" />
    <div class="popBody">
      <div class="card">
        <div class="carInfo">
          <div>
            <div class="licensePlate">
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.licenseNo : ""
              }}
            </div>
            <!-- <div class="licenseModel">
              {{
              detailed.vehicleModelVO
              ? detailed.vehicleModelVO.vehicleBrand
              : ""
              }}
            </div> -->
          </div>
        </div>
        <div>
          <div class="rowQu">
            <div>新车未上牌</div>
            <div v-if="detailed.vehicleModelVO">
              {{ detailed.vehicleModelVO.noLicenseFlag == "1" ? "是" : "否" }}
            </div>
          </div>
          <div class="rowQu">
            <div>车牌号</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.licenseNo : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>初登日期</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.enrollDate
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>车架号</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.frameNo : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>发动机号</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.engineNo : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>行驶证车型</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.travelVehicleModel
                  : ""
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="background"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <div>号牌类型</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                $utils.findText(
                  licenseTypes,
                  detailed.vehicleModelVO.licenseType
                ) == "请选择"
                  ? ""
                  : $utils.findText(
                      licenseTypes,
                      detailed.vehicleModelVO.licenseType
                    )
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>厂牌型号</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.vehicleBrand
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>车辆种类</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                $utils.findText(
                  carKindCodes,
                  detailed.vehicleModelVO.carKindCode
                )
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>车辆使用性质</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                $utils.findText(
                  useNatureCodes,
                  detailed.vehicleModelVO.useNatureCode
                )
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>交管车辆类型</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.vehicleCategoryName
                  : ""
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="background"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <div>新车置购价(元)</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.purchasePrice
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>车辆实际价(元)</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.actualValue
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>过户车标志</div>
            <div v-if="detailed.vehicleModelVO">
              {{ detailed.vehicleModelVO.chgOwnerFlag == "1" ? "是" : "否" }}
            </div>
          </div>
          <div class="rowQu">
            <div>转移登记日期</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.transferDate
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>使用年限</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.userYear : ""
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="background"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <div>能源类型</div>
            <!-- <div
              v-if="detailed.vehicleModelVO"
            >{{ $utils.findText(fuelTypes, detailed.vehicleModelVO.fuelType) }}</div>-->
          </div>
          <div class="rowQu">
            <div>核定载客数</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.seatCount : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>核定载质量(KG)</div>
            <div>
              {{
                detailed.vehicleModelVO ? detailed.vehicleModelVO.tonCount : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>整备质量(KG)</div>
            <div>
              {{ detailed.vehicleModelVO ? detailed.vehicleModelVO.net : "" }}
            </div>
          </div>
          <div class="rowQu">
            <div>排量(ML)</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.exhaustScale
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>功率(KW)</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.powerScale
                  : ""
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="background"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <div>是否涉农</div>
            <div v-if="detailed.vehicleModelVO">
              {{ detailed.vehicleModelVO.isAboutagri == "1" ? "是" : "否" }}
            </div>
          </div>
          <div class="rowQu">
            <div>是否外地车</div>
            <div v-if="detailed.vehicleModelVO">
              {{ detailed.vehicleModelVO.ecdemicVehicle == "1" ? "是" : "否" }}
            </div>
          </div>
          <div class="rowQu">
            <div>车贷投保多年标志</div>
            <div v-if="detailed.vehicleModelVO">
              {{ detailed.vehicleModelVO.loanVehicleFlag == "1" ? "是" : "否" }}
            </div>
          </div>
          <div class="rowQu">
            <div>验车情况</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                detailed.vehicleModelVO.carCheckStatus == "1"
                  ? "已验车"
                  : "未验车"
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>验车人</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.carChecker
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>验车时间</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.carCheckTime
                  : ""
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="background"></div>
      <div class="card">
        <div>
          <div class="rowQu">
            <div>特殊车投保标志</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                $utils.findText(
                  specialCarFlags,
                  detailed.vehicleModelVO.specialCarFlag
                )
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>争议解决方式</div>
            <div v-if="detailed.vehicleModelVO">
              {{
                detailed.vehicleModelVO.argueSolution === "01" ? "诉讼" : "仲裁"
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>行使区域</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.runAreaCode
                  : ""
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>指定查询地区代码</div>
            <div>
              {{
                detailed.vehicleModelVO
                  ? detailed.vehicleModelVO.specialCarFlag
                  : ""
              }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";
import Config from "@src/common/json/newCoreDataDictionary";

export default {
  components: {
    popNav
  },
  data() {
    return {
      detailed: {
        vehicleTaxVo: {},
        vehicleModelVO: {
          licenseNo: ""
        }
      },
      taxFlagSHs: "",
      useNatureCodes: "",
      licenseTypes: "",
      carKindCodes: "",
      specialCarFlags: ""
    };
  },
  mounted() {
    this.$nextTick(() => {
      (this.useNatureCodes = Object.keys(Config.useNatureCode).map(key => ({
        text: Config.useNatureCode[key],
        value: key
      }))),
        (this.licenseTypes = Object.keys(Config.licenseType).map(key => ({
          text: Config.licenseType[key],
          value: key
        })));
      this.carKindCodes = Object.keys(Config.carKindCode).map(key => ({
        text: Config.carKindCode[key],
        value: key
      }));
      this.specialCarFlags = Object.keys(Config.specialCarFlag).map(key => ({
        text: Config.specialCarFlag[key],
        value: key
      }));
      this.detailed.vehicleModelVO = this.$route.params;
      console.log(this.detailed.vehicleModelVO, "111");
    });
  },
  methods: {
    //返回上一页
    goBack() {
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
  // .van-tabs {
  //   width: 240px;
  // }
  .background {
    width: 100%;
    height: 10px;
    background: rgba(245, 245, 245, 1);
  }
  .card {
    background-color: #fff;
    padding: 10px 15px;
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      padding: 0.1rem 0;
    }
  }
}
.carInfo {
  height: 68px;
  background: url("../../../../assets/common/backCar.png") no-repeat;
  background-size: 100% 68px;
  div {
    .licensePlate {
      font-size: 20px;
      font-weight: 500;
      color: rgba(0, 0, 0, 1);
      line-height: 28px;
      padding-top: 15px;
      // padding-left: 18px;
    }
    .licenseModel {
      padding-left: 18px;
      font-size: 14px;
      color: rgba(68, 68, 68, 1);
      line-height: 20px;
    }
  }
}
.rowQu {
  position: relative;
  .InsuranceNum,
  .applicationFormNum {
    position: absolute;
    right: 4px;
    top: 8px;
    padding: 1px 2px;
    border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
  }
  .InsuranceNum1 {
    position: absolute;
    right: 35px;
    top: 10px;
  }
  display: flex;
  background-color: #fff;
  padding: 0.2rem 0;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    // text-align: center;
    align-content: center;
  }
  .rowQuLeft {
    flex: 1;
  }
  .rowQuRight {
    flex: 4;
    text-align: right;
  }
}
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
.rowQu {
  font-size: 14px;
}
</style>
