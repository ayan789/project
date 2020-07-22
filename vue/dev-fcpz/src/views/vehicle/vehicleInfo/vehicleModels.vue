<!--
 * @Author: heweiwei
 * @Page: 
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-06-28 10:12:42
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleModels.vue
-->
<template>
  <div class="vehicle-motorcycle">
    <van-sticky>
      <nav-bar type="vehicle" title="车型选择" @clickBack="$router.back()" />
    </van-sticky>
    <div class="content">
      <div
        class="item-container"
        v-for="(model, index) in carModels"
        :key="index"
      >
        <div class="item" @click="select(model, index)">
          <van-checkbox
            v-model="checked[index]"
            icon-size="16"
            class="check-box"
            :ref="index"
          />
          <div class="item-right">
            <div class="model-name">{{ model.modelName }}</div>
            <div class="item-info">
              <div class="item-info-left">
                <p>车型代码：{{ model.modelCode }}</p>
                <p>上市年份：{{ model.vehicleJingyou.marketDate }}</p>
                <p>
                  座位数：{{ model.seatCount || model.vehicleJingyou.seat }}
                </p>
              </div>
              <div class="item-info-right">
                <p>变速器：{{ model.vehicleJingyou.gearboxType }}</p>
                <p>
                  参考价：￥{{
                    model.purchasePrice || model.vehicleJingyou.price
                  }}
                </p>
                <p>
                  排量：{{
                    model.exhaustScale || model.vehicleJingyou.displacement
                  }}ML
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button" @click="confirm">确定</div>
  </div>
</template>
<script>
import { getCarModel, checkCarModel } from "@src/http/module/vehicle";
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  data() {
    return {
      checked: [false, false],
      carModels: [],
      defaultModel: null,
      firstSelectedModel: null,
      areaCode: ""
    };
  },
  created() {
    let userInfo = this.localStorage.get("userInfo", {});
    let comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    this.getCarModel();
  },
  computed: {
    ...mapState("vehicle", {
      car: ({ car }) => car,
      control: ({ control }) => control
    })
  },
  methods: {
    ...mapMutations("vehicle", ["setCarInfo"]),
    ...mapActions("vehicle", ["calculateActualValue"]),
    getCarModel() {
      let date = new Date();
      date.setDate(date.getDate() + 1);
      getCarModel({
        ...this.car,
        licenseNo: this.car.noLicenseFlag == "1" ? "" : this.car.licenseNo,
        comCode: this.localStorage.get("userInfo", {}).comCode,
        effectiveDate: date.format()
      }).then(
        res => {
          if (res.code === "1") {
            let { carModels = [] } = res.data;
            if (carModels && carModels.length) {
              this.carModels = res.data.carModels;
              this.firstSelectedModel = null;
              this.checked = this.carModels.map(() => false);
            }
            this.defaultModel = {
              vehicleCategory: res.data.vehicleType,
              net: res.data.wholeWeight,
              haulage: res.data.haulage ? res.data.haulage : "0"
            };
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    select(model, index) {
      if (this.checked[index]) {
        this.$refs[index][0].toggle(false);
        this.checked[index] = false;
      } else {
        this.checked.fill(false);
        this.$refs[index][0].toggle(true);
        this.checked[index] = true;
      }
    },
    confirm() {
      let checkIndex = this.checked.findIndex(i => i);
      if (checkIndex === -1) {
        return this.$toast.fail("请选择一款车型");
      }
      // 摩托车和拖拉机不上平台的地区不进行车型校验
      // 摩托车上平台地区：江苏、大连、浙江、宁波、山东
      // 拖拉机上平台地区：江苏、浙江、宁波、山东
      // TODU chenjiafeng ----目前先注释、等提出再上----
      // if (
      //   ["51", "52", "53"].includes(this.car.carKindCode) &&
      //   !["3201", "2102", "3301", "3302", "3701"].includes(this.areaCode)
      // ) {
      //   this.checkSuccess(this.carModels[checkIndex]);
      //   return;
      // }
      // if (
      //   ["61", "62", "63", "64"].includes(this.car.carKindCode) &&
      //   !["3201", "3301", "3302", "3701"].includes(this.areaCode)
      // ) {
      //   this.checkSuccess(this.carModels[checkIndex]);
      //   return;
      // }
      this.carModelCheck(this.carModels[checkIndex]);
    },
    carModelCheck(model) {
      for (const key in model) {
        if (model.hasOwnProperty(key)) {
          const element = model[key];
          if (element != 0 && !element) {
            delete model[key];
          }
        }
      }
      let date = new Date();
      date.setDate(date.getDate() + 1);
      //console.log("%o", model);
      let params = Object.assign(
        {},
        this.$utils.removeEmpty(this.car),
        this.$utils.removeEmpty(model),
        this.mixData()
      );
      checkCarModel({
        ...params,
        comCode: this.localStorage.get("userInfo", {}).comCode,
        licenseNo: this.car.noLicenseFlag == "1" ? "" : this.car.licenseNo,
        effectiveDate: date.format(),
        vinNo: this.car.frameNo
      }).then(
        res => {
          if (res.code === "1") {
            if (res.data.sameFlag === "Y") {
              this.checkSuccess(res.data.carModelList);
            } else {
              this.carModels = res.data.carModelList;
              this.checkFailed();
            }
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    mixData() {
      let checkIndex = this.checked.findIndex(i => i);
      if (checkIndex == -1) return {};
      const carModel = this.carModels[checkIndex];
      const {
        vehicleBrand,
        ecdemicVehicle,
        transfer,
        carInvoiceDate,
        firstyearDate,
        newVehicle,
        certificateNo,
        certificateType,
        certificateDate,
        restricFlag,
        energyType,
        carRegiste,
        vehicleCategory,
        licenseType,
        travelVehicleModel,
        isAboutagri,
        loanVehicleFlag,
        arbitBoardcode,
        arbitBoardname,
        argueSolution,
        carCheckStatus,
        carCheckTime,
        carChecker,
        specialCarFlag,
        vehicleJingyou,
        modelName,
        importFlag,
        userYear,
        tonCount,
        areaFactor,
        industryVehicleCode: industryVehicleCodeDefault,
        seatCount
      } = carModel;
      const {
        seat,
        gearboxType,
        price,
        purchasePrice,
        fullWeight,
        displacement,
        industryModelCode,
        industryVehicleCode,
        industryVehicleName,
        powerTypeCode,
        vehicleCode,
        vehicleClassCode,
        vehicleClassName,
        vehicleType,
        brandCode,
        brandName,
        power
      } = vehicleJingyou;
      let result = {
        vehicleBrand, //厂牌型号
        seatCount: seatCount || seat, //核定载客数
        derailleurType: gearboxType, //变速箱类型
        purchasePrice: price, //新车购置价
        net: fullWeight, //整备质量
        exhaustScale: displacement ? String(parseInt(displacement * 1000)) : "", //排量
        chgOwnerFlag: transfer, //是否过户车
        fuelType: energyType || powerTypeCode, //能源类型
        carRegiste, //备案标志
        ecdemicVehicle, //是否外地车
        carInvoiceDate, //购车发票日期(上海北京地区)
        firstyearDate, ////首年投保时间(上海地区)
        newVehicle, //是否新车
        certificateNo, //车辆来历凭证编号（北京地区）
        certificateType, //车辆来历凭证种类（北京地区）
        certificateDate, //车辆来历凭证所载日期（北京地区）
        restricFlag, //单双号标志
        vehicleCategory, //交管车辆类型
        licenseType, //号牌种类
        areaFactor, //摩托车地区因子
        travelVehicleModel: travelVehicleModel || vehicleBrand, //行驶证车型
        isAboutAgri: isAboutagri, //是否涉农
        // loanVehicleFlag, //是否多年车贷投保标志
        arbitBoardcode: arbitBoardname, //仲裁委员会编码
        //, //仲裁委员会名称
        argueSolution, //争议解决方式
        carCheckStatus, //验车情况 Y:验车 N:未验车
        carCheckTime, //验车时间
        carChecker, //验车人
        specialCarFlag, //特殊车投保标志
        industryModelCode, //行业车型编码
        industryVehicleCode: industryVehicleCodeDefault || industryVehicleCode, //公告型号
        industryVehicleName, //车款名称
        vehicleJingyou, //精友信息
        modelCode: vehicleCode, //
        modelName,
        vehicleClassCode, // 车型种类代码
        vehicleClassName, //车型种类名称
        vehicleType,
        userYear, //使用年限
        brandCode,
        brandName,
        powerScale: power,
        tonCount,
        importFlag: importFlag ? (importFlag == "进口" ? "2" : "1") : "" //车型种类 1.国产/2.进口/合资
      };
      if (this.firstSelectedModel) {
        result = Object.assign({}, result, this.firstSelectedModel);
      } else {
        this.firstSelectedModel = {
          // net: result.net,
          // seatCount: result.seatCount,
          tonCount: result.tonCount
        };
      }
      return this.$utils.removeEmpty(result);
    },
    mixData2() {
      let checkIndex = this.checked.findIndex(i => i);
      if (checkIndex == -1) return {};
      const carModel = this.carModels[checkIndex];
      const { transfer, energyType, vehicleJingyou, importFlag } = carModel;
      const {
        seat,
        gearboxType,
        price,
        purchasePrice,
        fullWeight,
        displacement,
        industryModelCode,
        industryVehicleCode,
        industryVehicleName,
        powerTypeCode,
        vehicleCode,
        vehicleClassCode,
        vehicleClassName,
        vehicleType,
        brandCode,
        brandName
      } = vehicleJingyou;
      let result = {
        seatCount: seat, //核定载客数
        derailleurType: gearboxType, //变速箱类型
        purchasePrice: price, //新车购置价
        net: fullWeight, //整备质量
        exhaustScale: displacement ? parseInt(displacement * 1000) : "", //排量
        chgOwnerFlag: transfer, //是否过户车
        fuelType: energyType || powerTypeCode, //能源类型
        vehicleJingyou, //精友信息
        modelCode: vehicleCode,
        industryModelCode,
        industryVehicleCode,
        industryVehicleName,
        vehicleClassCode,
        vehicleClassName,
        vehicleType,
        brandCode,
        brandName
      };
      //车型种类 1.国产/2.进口/合资
      return Object.assign({}, result, carModel, {
        importFlag: importFlag ? (importFlag == "进口" ? "2" : "1") : ""
      });
    },
    checkFailed() {
      this.checked = this.carModels.map(() => false);
      this.$toast.fail("请重新选择车型");
      // let checkIndex = this.checked.findIndex(i => i);
    },
    checkSuccess(carModelList) {
      let car = this.mixData();
      if (carModelList && carModelList.length) {
        // car.modelCode = carModelList[0].modelCode;
        // car.modelName = carModelList[0].modelName;
        car.industryVehicleCode =
          carModelList[0].industryVehicleCode || car.industryVehicleCode;
        // car.vehicleBrand = carModelList[0].vehicleBrand || car.vehicleBrand;
        // car = Object.assign(car, this.$utils.removeEmpty(carModelList[0]));
      }
      car = Object.assign(car, this.$utils.removeEmpty(this.defaultModel));
      this.setCarInfo({ ...car });
      this.calculateActualValue({});
      this.control.vehicleModelChecked = true;
      this.$router.back();
    }
  }
};
</script>
<style lang="less" scoped>
.vehicle-motorcycle {
  background: #f7f7f7;
}
.content {
  position: relative;
  background: white;
  width: 100%;
  height: calc(100% - 77px - 46px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  .item {
    display: flex;
    flex: 1;
    flex-direction: row;
    padding: 12px 20px 15px;
    overflow: visible;
    /deep/.van-cell__value {
      flex: 2;
    }
    div {
      display: flex;
    }
    .check-box {
      padding: 4px;
    }
    .item-right {
      flex: 1;
      margin-left: 10px;
      display: block;
      .model-name {
        font-size: 14px;
        color: #000000;
        font-weight: 700;
        line-height: 20px;
        margin-bottom: 10px;
      }
      .item-info {
        color: #666666;
        font-size: 13px;
        .item-info-left {
          flex: 1;
          display: block;
          p {
            margin-bottom: 4px;
          }
        }
        .item-info-right {
          flex: 1;
          display: block;
          p {
            margin-bottom: 4px;
          }
        }
      }
    }
  }
  .item-container {
    position: relative;
  }
  .item-container:not(:last-child)::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 46px;
    border-bottom: 1px solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
}
.button {
  width: 100%;
  height: 46px;
  background: linear-gradient(125deg, #2a80ff 0%, #2aa5ff 100%);
  font-size: 16px;
  line-height: 46px;
  color: white;
  text-align: center;
}
</style>
