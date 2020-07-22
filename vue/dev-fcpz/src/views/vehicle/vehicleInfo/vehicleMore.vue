<!--
 * @Author: heweiwei
 * @Page: 车辆信息-更多信息
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-06-19 19:37:03
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleMore.vue
-->
<template>
  <div class="vehicle-more-info">
      <nav-bar type="vehicle" title="更多信息" @clickBack="back" />
    <div class="content">
      <div class="group">
        <!-- 新车购置价 -->
        <van-cell class="item" required>
          <span slot="title" class="item-title">新车购置价</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="car.purchasePrice"
              maxlength="10"
              placeholder="0"
              @input="e => input(e, 'purchasePrice')"
              @focus="e => (e.target.placeholder = '')"
              @blur="
                e => {
                  changePurchasePrice(e);
                  e.target.placeholder = '0';
                }
              "
            />
            <span class="item-right-input-unit">元</span>
          </div>
        </van-cell>
        <!-- 车辆实际价 -->
        <van-cell class="item">
          <span slot="title" class="item-title">车辆实际价</span>
          <div class="item-right">
            <input
              disabled
              maxlength="10"
              class="item-right-input"
              :value="Number(car.actualValue).toFixed(2)"
              placeholder="0"
            />
            <span class="item-right-input-unit">元</span>
          </div>
        </van-cell>
        <!-- 使用年限 -->
        <van-cell class="item">
          <span slot="title" class="item-title">使用年限</span>
          <div class="item-right">
            <input
              disabled
              maxlength="3"
              class="item-right-input"
              v-model="car.userYear"
              placeholder="0"
            />
            <span class="item-right-input-unit">年</span>
          </div>
        </van-cell>
      </div>
      <div class="group">
        <!-- 能源类型 -->
        <van-cell class="item" required @click="setPicker('fuelType')">
          <span slot="title" class="item-title">能源类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.fuelType }">
              {{ $utils.findText(fuelTypes, car.fuelType) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 整备质量
          车辆种类为【客车、低速载货汽车、挂车、特种车】时非必填，
          车辆种类为【货车】时必填，
          车辆种类为【摩托车、拖拉机】时不显示该字段
          TUDO chenjiafeng
         -->
        <van-cell
          class="item"
          v-if="vehicleType !== '3'"
          :required="car.carKindCode == '21'"
        >
          <span slot="title" class="item-title">整备质量</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="car.net"
              placeholder="请输入整备质量"
              maxlength="11"
              @input="e => input(e, 'net')"
              @focus="e => (e.target.placeholder = '')"
              @blur="netBlur"
            />
            <span class="item-right-input-unit">kg</span>
          </div>
        </van-cell>
        <!-- 核定载质量
          车辆种类为【客车、低速载货汽车、特种车】时非必填，
          车辆种类为【货车】时必填，
          车辆种类为【摩托车、拖拉机、挂车】时不显示该字段
          TODU chenjiafeng
         -->
        <van-cell
          class="item"
          v-if="vehicleType !== '3' && car.carKindCode !== '31'"
          :required="car.carKindCode == '21'"
        >
          <span slot="title" class="item-title">核定载质量</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="car.tonCount"
              placeholder="请输入核定载质量"
              maxlength="11"
              @input="e => input(e, 'tonCount')"
              @focus="e => (e.target.placeholder = '')"
              @blur="tonCountBlur"
            />
            <span class="item-right-input-unit">kg</span>
          </div>
        </van-cell>
        <!-- 牵引总质量
          车辆种类为【客车、低速载货汽车、特种车、摩托车、拖拉机、货车】时不显示；
          车辆种类为【挂车】显示本字段，必填
          TUDO chenjiafeng
         -->
        <van-cell
          class="item"
          v-if="car.carKindCode == '31'"
          :required="car.carKindCode == '31'"
        >
          <span slot="title" class="item-title">牵引总质量</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="car.haulage"
              placeholder="请输入牵引总质量"
              maxlength="11"
              @input="e => input(e, 'haulage')"
              @focus="e => (e.target.placeholder = '')"
              @blur="haulageBlur"
            />
            <span class="item-right-input-unit">kg</span>
          </div>
        </van-cell>
        <!-- 排量：
          车辆种类为【客车、摩托车】时显示，且必填
          车辆种类为【货车、低速载货汽车、挂车、特种车】时显示，非必填
          如地区有“拖拉机地区费率分类”（安徽、广西、湖北、湖南、贵州、四川地区），
          并且车辆种类选择“运输拖拉机”或“兼用拖拉机”，则该地区录单页面上车辆信息模块的“功率、排量”字段均不显示
          TUDO chenjiafeng
        -->
        <div>
          <van-cell
            class="item"
            :required="['11', '51', '52', '53'].includes(car.carKindCode)"
            v-if="
              ['11', '51', '52', '53', '21', '22', '31'].includes(
                car.carKindCode
              ) || vehicleType == '4'
            "
          >
            <span slot="title" class="item-title">排量</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="car.exhaustScale"
                placeholder="请输入排量"
                maxlength="8"
                @input="e => input(e, 'exhaustScale')"
                @focus="e => (e.target.placeholder = '')"
                @blur="exhaustScaleBlur"
              />
              <span class="item-right-input-unit">ML</span>
            </div>
          </van-cell>
        </div>
        <!-- 功率
          车辆种类为【客车、货车、低速载货汽车、挂车、特种车】时显示，非必填
          车辆种类为【拖拉机】时显示，必填
          如地区有“拖拉机地区费率分类”（安徽、广西、湖北、湖南、贵州、四川地区），
          并且车辆种类选择“运输拖拉机”或“兼用拖拉机”，则该地区录单页面上车辆信息模块的“功率、排量”字段均不显示
          TUDO chenjiafeng
         -->
        <div
          v-if="
            (areaCode == '3401' ||
              areaCode == '4501' ||
              areaCode == '4201' ||
              areaCode == '4301' ||
              areaCode == '5201' ||
              areaCode == '5101') &&
              (car.carKindCode == '61' ||
                car.carKindCode == '62' ||
                car.carKindCode == '63' ||
                car.carKindCode == '64')
          "
        ></div>
        <div v-else>
          <van-cell
            class="item"
            :required="['61', '62', '63', '64'].includes(car.carKindCode)"
            v-if="
              ['11', '21', '22', '31', '61', '62', '63', '64'].includes(
                car.carKindCode
              ) || vehicleType == '4'
            "
          >
            <span slot="title" class="item-title">功率</span>
            <div class="item-right">
              <input
                class="item-right-input"
                v-model.number="car.powerScale"
                placeholder="请输入功率"
                maxlength="8"
                @input="e => input(e, 'powerScale')"
                @focus="e => (e.target.placeholder = '')"
                @blur="powerScaleBlur"
              />
              <span class="item-right-input-unit">KW</span>
            </div>
          </van-cell>
        </div>
        <!-- 摩托车地区因子
          江苏、安徽、湖南、浙江、宁波---摩托车特有字段--只显示不允许修改
         -->
        <van-cell
          class="item"
          v-if="
            (areaCode == '3201' ||
              areaCode == '3401' ||
              areaCode == '4301' ||
              areaCode == '3301' ||
              areaCode == '3302') &&
              (car.carKindCode == '51' ||
                car.carKindCode == '52' ||
                car.carKindCode == '53')
          "
        >
          <span slot="title" class="item-title">摩托车地区因子</span>
          <div class="item-right">
            <input
              readonly
              maxlength="3"
              class="item-right-input"
              v-model="car.areaFactor"
            />
          </div>
        </van-cell>
        <!-- 
          拖拉机地区费率分类
          安徽、广西、湖北、湖南、贵州、四川地区--车辆种类选择：运输型拖拉机、兼用型拖拉机 时显示且必填
          TODU chenjiafeng
        -->
        <van-cell
          class="item"
          v-if="rateClassificationRequired"
          required
          @click="setPicker('rateClassification')"
        >
          <span slot="title" class="item-title">拖拉机地区费率分类</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.rateClassification }">
              {{ $utils.findText(rateClassifications, car.rateClassification) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
      </div>
      <div class="group">
        <!-- 分地区设置 -->
        <van-cell
          v-if="vehicleType == '1'"
          class="item"
          @click="'1' ? '' : setPicker('carKindCode')"
        >
          <span slot="title" class="item-title">车辆种类</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.carKindCode }">
              {{ $utils.findText(carKindCodes, car.carKindCode) }}
            </span>
            <!-- <van-icon class="item-right-arrow" name="arrow" /> -->
          </div>
        </van-cell>
        <van-cell class="item" v-if="vehicleType == '1'">
          <span slot="title" class="item-title">车辆使用性质</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.useNatureCode }">
              {{ $utils.findText(useNatureCodes, car.useNatureCode) }}
            </span>
          </div>
        </van-cell>
        <!-- 交管车辆类型 -->
        <!-- 上海不显示该字段 TODU chenjiafeng -->
        <van-cell
          v-if="areaCode !== '3101' && vehicleType == '1'"
          class="item"
          required
          @click="setPicker('vehicleCategory')"
        >
          <span slot="title" class="item-title">交管车辆类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.vehicleCategory }">
              {{ $utils.findText(vehicleCategorys, car.vehicleCategory) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 号牌类型
          车辆种类为【客车、货车、低速载货汽车、挂车、特种车】时，默认为【小型汽车号牌】，
          车辆种类为【摩托车、拖拉机、三轮汽车】时，不显示本字段，摩托车--上海广东显示，
          广东、四川地区挪到车牌号下面，这边不显示
          TUDO chenjiafeng
        -->
        <van-cell
          class="item"
          :required="car.noLicenseFlag != 1"
          v-if="vehicleType == '1'"
          @click="
            car.noLicenseFlag != 1
              ? setPicker('licenseType')
              : $dialog.alert({ message: '未上牌时不允许录入号牌类型' })
          "
        >
          <span slot="title" class="item-title">号牌类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.licenseType }">
              {{ $utils.findText(licenseTypes, car.licenseType) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 号牌底色
          车辆种类为【客车、货车、低速载货汽车、挂车、特种车】时根据号牌类型自动带出号牌底色，页面不显示号牌底色字段
          车辆种类为【摩托车、拖拉机】时显示本字段，枚举值为全量枚举值，默认为【黄】
          上海、广东--摩托车-不显示该字段
          TUDO chenjiafeng
         -->
        <van-cell
          class="item"
          required
          v-if="
            ['61', '62', '63', '64'].includes(car.carKindCode) ||
              (['51', '52', '53'].includes(car.carKindCode) &&
                areaCode !== '4401' &&
                areaCode !== '3101')
          "
          @click="setPicker('licenseColorCode')"
        >
          <span slot="title" class="item-title">号牌底色</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.licenseColorCode }">
              {{ $utils.findText(licenseColorCodes, car.licenseColorCode) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
        <!-- 行驶证车型 -->
        <van-cell class="item" v-if="vehicleType == '1'" required>
          <span slot="title" class="item-title">行驶证车型</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="car.travelVehicleModel"
              placeholder="请输入"
              maxlength="50"
              @input="e => input(e, 'travelVehicleModel')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入')"
            />
          </div>
        </van-cell>
        <!-- 行驶证车辆类型 -->
        <!-- <van-cell class="item" required @click="setPicker('poCategory')">
          <span slot="title" class="item-title">行驶证车辆类型</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.poCategory }">
              {{ $utils.findText(poCategorys, car.poCategory) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell> -->
      </div>
      <!-- 打印使用性质
        江苏和厦门地区当【请选择车辆类型】弹出框选择【摩托车/拖拉机/三轮汽车】时，显示本字段
        下拉框枚举值为：不区分营业非营业、非营业、营业，可修改
        厦门地区默认显示为【非营业】，江苏地区默认显示为【不区分营业非营业】，可修改
        -->
      <div
        class="group"
        v-if="(areaCode == '3502' || areaCode == '3201') && vehicleType == '3'"
      >
        <van-cell
          class="item"
          required
          @click="setPicker('printUseNatureCode')"
        >
          <span slot="title" class="item-title">打印使用性质</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.printUseNatureCode }">
              {{ $utils.findText(printUseNatureCodes, car.printUseNatureCode) }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" />
          </div>
        </van-cell>
      </div>
      <div class="group">
        <!-- 是否涉农 -->
        <van-cell class="item">
          <span slot="title" class="item-title">是否涉农</span>
          <div class="item-right">
            <whether-select
              v-model="car.isAboutAgri"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </div>
        </van-cell>
        <!-- 是否多年车贷投保 -->
        <!-- <van-cell class="item">
          <span slot="title" class="item-title">是否多年车贷投保</span>
          <div class="item-right">
            <whether-select
              v-model="car.loanVehicleFlag"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </div>
        </van-cell> -->
        <!-- 争议解决方式 -->
        <van-cell class="item">
          <span slot="title" class="item-title">争议解决方式</span>
          <div class="item-right">
            <whether-select
              v-model="car.argueSolution"
              :options="[
                { text: '诉讼', value: '01' },
                { text: '仲裁', value: '02' }
              ]"
              @change="argueSolutionChange"
            />
          </div>
        </van-cell>
        <!-- 仲裁委员会 -->
        <van-cell
          class="item"
          v-if="car.argueSolution == '02'"
          @click="arbitBoardcodeDisabled ? '' : setPicker('arbitBoardcode')"
        >
          <span slot="title" class="item-title">仲裁委员会</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.arbitBoardcode }">
              {{ car.arbitBoardname }}
            </span>
            <van-icon
              v-show="!arbitBoardcodeDisabled"
              class="item-right-arrow"
              name="arrow"
            />
          </div>
        </van-cell>
      </div>
      <div class="group">
        <!-- 验车情况 -->
        <van-cell class="item">
          <span slot="title" class="item-title">验车情况</span>
          <div class="item-right">
            <whether-select
              v-model="car.carCheckStatus"
              :options="[
                { text: '已验车', value: '1' },
                { text: '未验车', value: '0' }
              ]"
            />
          </div>
        </van-cell>
        <!-- 验车人 -->
        <van-cell class="item" v-if="car.carCheckStatus == '1'">
          <span slot="title" class="item-title">验车人</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model="car.carChecker"
              maxlength="20"
              placeholder="请输入验车人"
              @input="e => input(e, 'carChecker')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入验车人')"
            />
          </div>
        </van-cell>
        <!-- 验车时间 -->
        <van-cell
          class="item"
          is-link
          v-if="car.carCheckStatus == '1'"
          @click="datePickerShow = true"
        >
          <span slot="title" class="item-title">验车时间</span>
          <div class="item-right">
            <span>{{ car.carCheckTime | date }}</span>
          </div>
        </van-cell>
        <!-- 后补时间 -->
        <van-cell class="item" v-if="car.carCheckStatus == 0">
          <span slot="title" class="item-title">后补时间</span>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="car.complementTime"
              placeholder="请输入1-7天候补时间"
              maxlength="1"
              @input="e => input(e, 'complementTime')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入1-7天候补时间')"
            />
            <span class="item-right-input-unit">天</span>
          </div>
        </van-cell>
      </div>
      <div class="group">
        <!-- 特殊车投保标志 -->
        <van-cell
          class="item"
          @click="car.chgOwnerFlag == '1' ? '' : setPicker('specialCarFlag')"
          v-if="vehicleType !== '3'"
        >
          <span slot="title" class="item-title">特殊车投保标志</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !car.specialCarFlag }">
              {{ $utils.findText(specialCarFlags, car.specialCarFlag) }}
            </span>
            <van-icon
              v-show="car.chgOwnerFlag != '1'"
              class="item-right-arrow"
              name="arrow"
            />
          </div>
        </van-cell>
      </div>
    </div>
    <div style="height:80px;width:100%;background:#f7f7f7">
      <confirmBottom @click="back" />
    </div>
    <van-popup v-model="datePickerShow" position="bottom">
      <van-datetime-picker
        type="date"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onDatePickerConfirm"
        @cancel="datePickerShow = false"
      />
    </van-popup>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData"
        :default-index="defaultIndex"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      >
        <template #columns-top>
          <form action="/">
            <van-search
              v-model="searchValue"
              show-action
              placeholder="请输入搜索关键词"
              @search="onSearch"
              @cancel="onCancel"
            />
          </form>
        </template>
      </van-picker>
    </van-popup>
  </div>
</template>
<script>
import WhetherSelect from "./whetherSelect.vue";
import confirmBottom from "@/components/confirmBottom.vue";
import Config from "@src/common/json/newCoreDataDictionary";
import { CellGroup } from "vant";
import { mapState, mapActions } from "vuex";
import _ from "lodash";
export default {
  name: "vehicle-more",
  components: {
    confirmBottom,
    [CellGroup.name]: CellGroup,
    [WhetherSelect.name]: WhetherSelect
  },
  data() {
    return {
      areaCode: "",
      searchValue: "",
      datePickerShow: false,
      currentDate: new Date(),
      minDate: new Date(2000, 0, 1),
      maxDate: new Date(),
      //能源类型
      fuelTypes: Object.keys(Config.fuelType).map(key => ({
        text: Config.fuelType[key],
        value: key
      })),
      //交管车辆类型
      // vehicleCategorys: Object.keys(Config.vehicleCategory).map(key => ({
      //   text: Config.vehicleCategory[key],
      //   value: key
      // })),
      //号牌种类
      // licenseTypes: Object.keys(Config.licenseType).map(key => ({
      //   text: Config.licenseType[key],
      //   value: key
      // })),
      // 号牌底色
      licenseColorCodes: Object.keys(Config.licenseColorCode).map(key => ({
        text: Config.licenseColorCode[key],
        value: key
      })),
      //车辆种类
      carKindCodes: Object.keys(Config.carKindCode).map(key => ({
        text: Config.carKindCode[key],
        value: key
      })),
      useNatureCodes: Object.keys(Config.useNatureCode).map(key => ({
        text: Config.useNatureCode[key],
        value: key
      })),
      //行驶证车辆类型
      poCategorys: Object.keys(Config.poCategory).map(key => ({
        text: Config.poCategory[key],
        value: key
      })),
      //仲裁委员会
      arbitBoardnames: Object.keys(Config.arbitBoardname).map(key => ({
        text: Config.arbitBoardname[key],
        value: key
      })),
      //特殊车投保标志
      specialCarFlags: Object.keys(Config.specialCarFlag).map(key => ({
        text: Config.specialCarFlag[key],
        value: key
      })),
      // 打印使用性质
      printUseNatureCodes: Object.keys(Config.printUseNatureCode).map(key => ({
        text: Config.printUseNatureCode[key],
        value: key
      })),
      filterPoCategoryText: "",
      defaultPurchasePrice: "",
      dataPickerType: "",
      pickerData: [],
      defaultIndex: 0
    };
  },
  created() {
    let userInfo = this.localStorage.get("userInfo", {});
    let comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    //打印使用性质--厦门地区默认显示为【非营业】，江苏地区默认显示为【不区分营业非营业】，可修改
    if (this.areaCode == "3502") {
      this.car.printUseNatureCode = "200";
    }
    if (this.areaCode == "3201") {
      this.car.printUseNatureCode = "000";
    }
    console.log("vehicle more page created!");
  },
  watch: {
    "car.carCheckStatus"(carCheckStatus) {
      if (carCheckStatus == "1") {
        this.car.carChecker = this.localStorage.get("userInfo", {}).userName;
        this.car.carCheckTime = new Date().format();
      }
    },
    "car.licenseType"(licenseType) {
      switch (licenseType) {
        case "02":
        case "18":
          this.car.licenseColorCode = "1";
          break;
        case "03":
        case "04":
        case "05":
        case "06":
          this.car.licenseColorCode = "2";
          break;
        case "20":
        case "22":
        case "23":
        case "24":
        case "32":
          this.car.licenseColorCode = "3";
          break;
        case "01":
        case "13":
          this.car.licenseColorCode = "4";
          break;
        case "52":
          this.car.licenseColorCode = "6";
          break;
        case "51":
          this.car.licenseColorCode = "7";
          break;
        case "99":
          this.car.licenseColorCode = "8";
          break;
        default:
          break;
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      car: ({ car }) => car,
      control: ({ control }) => control,
      vehicleType: ({ vehicleType }) => vehicleType
    }),
    arbitBoardcodeDisabled() {
      const userInfo = this.localStorage.get("userInfo", {});
      const { comCode } = userInfo;
      const areaCode = comCode.substr(0, 4);
      return areaCode == "1301";
    },
    specialCarFlagText() {
      return Config.specialCarFlag[this.car.specialCarFlag];
    },
    dataPickerShow: {
      get: function() {
        return !!this.pickerData.length;
      },
      set: function() {
        this.pickerData = [];
        this.dataPickerType = "";
      }
    },
    rateClassificationRequired() {
      //安徽、广西、湖北、湖南、贵州、四川地区--车辆种类选择：运输型拖拉机、兼用型拖拉机 时显示且必填
      if (
        this.areaCode == "3401" ||
        this.areaCode == "4501" ||
        this.areaCode == "4201" ||
        this.areaCode == "4301" ||
        this.areaCode == "5201" ||
        this.areaCode == "5101"
      ) {
        if (
          this.car.carKindCode == "61" ||
          this.car.carKindCode == "62" ||
          this.car.carKindCode == "63" ||
          this.car.carKindCode == "64"
        ) {
          return true;
        }
      }
      return false;
    },
    rateClassifications() {
      // 拖拉机地区费率分类
      if (this.car.carKindCode == "63" || this.car.carKindCode == "64") {
        //运输型拖拉机
        if (this.areaCode == "3401") {
          // 安徽
          let list = {
            "01": "单缸发动机",
            "02": "两缸发动机",
            "06": "三缸、四缸（60KW以下）"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "4501") {
          //广西
          let list = {
            "01": "单缸发动机",
            "02": "两缸发动机",
            "07": "三缸（不超标）发动机",
            "08": "四缸（或三缸超标）发动机",
            "09": "五缸及以上发动机"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "4201") {
          //湖北
          let list = {
            "01": "单缸发动机",
            "02": "两缸发动机",
            "03": "三缸发动机"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "4301") {
          //湖南
          let list = {
            "01": "单缸发动机",
            "02": "两缸发动机",
            "03": "三缸发动机",
            "04": "四缸发动机",
            "05": "四缸以上发动机"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "5201") {
          //贵州
          let list = {
            "10": "二缸以下（含二缸）",
            "11": "二缸以上"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "5101") {
          //四川
          let list = {
            "12": "标准14.7KW及以下",
            "13": "标准14.7KW及以上",
            "14": "非标准14.7KW及以下",
            "15": "非标准14.7KW及以上"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        }
      }
      if (this.car.carKindCode == "61" || this.car.carKindCode == "62") {
        // 兼用型拖拉机
        if (this.areaCode == "3401" || this.areaCode == "4501") {
          // 安徽、广西
          let list = {
            "18": "14.7KW及以下",
            "19": "14.7KW以上"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "4201" || this.areaCode == "4301") {
          //湖北、湖南
          let list = {
            "16": "手扶拖拉机",
            "17": "小型盘式/牵引挂车轮式拖拉机"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "5201") {
          //贵州
          let list = {
            "16": "手扶拖拉机",
            "20": "盘式拖拉机（含手扶变形运输机）"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        } else if (this.areaCode == "5101") {
          //四川
          let list = {
            "18": "14.7KW及以下"
          };
          return Object.keys(list).map(key => ({
            text: list[key],
            value: key
          }));
        }
      }
      return [];
    },
    // 车辆交管类型与车辆使用性质联动 TUDO chenjiafeng
    vehicleCategorys() {
      if (this.car.useNatureCode) {
        // 家用车、货车/客车/挂车
        if (this.vehicleType == "1" || this.vehicleType == "2") {
          // 车辆使用性质-106-营业挂车、250非营业挂车
          if (
            this.car.useNatureCode == "106" ||
            this.car.useNatureCode == "250"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS106).map(key => ({
                text: Config.vehicleCategoryJS106[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory106).map(key => ({
                text: Config.vehicleCategory106[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-104营业货车
          if (this.car.useNatureCode == "104") {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS104).map(key => ({
                text: Config.vehicleCategoryJS104[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory104).map(key => ({
                text: Config.vehicleCategory104[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-240-非营业货运
          if (this.car.useNatureCode == "240") {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS240).map(key => ({
                text: Config.vehicleCategoryJS240[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory240).map(key => ({
                text: Config.vehicleCategory240[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-95预约出租客运、101-营业出租租赁、85-家庭自用、220-非营业企业、230-非营业机关
          if (
            this.car.useNatureCode == "95" ||
            this.car.useNatureCode == "101" ||
            this.car.useNatureCode == "85" ||
            this.car.useNatureCode == "220" ||
            this.car.useNatureCode == "230"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS95).map(key => ({
                text: Config.vehicleCategoryJS95[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory95).map(key => ({
                text: Config.vehicleCategory95[key],
                value: key
              }));
            }
          }
          // 车辆使用性质-102营业城市公交、103营业公路客运、105-旅游客运
          if (
            this.car.useNatureCode == "102" ||
            this.car.useNatureCode == "103" ||
            this.car.useNatureCode == "105"
          ) {
            // 江苏上海北京特有 TODU chenjiafeng
            if (
              this.areaCode == "3201" ||
              this.areaCode == "3101" ||
              this.areaCode == "1101"
            ) {
              return Object.keys(Config.vehicleCategoryJS102).map(key => ({
                text: Config.vehicleCategoryJS102[key],
                value: key
              }));
            } else {
              return Object.keys(Config.vehicleCategory102).map(key => ({
                text: Config.vehicleCategory102[key],
                value: key
              }));
            }
          }
        }
        // 特种车
        if (this.vehicleType == "4") {
          // 100-营业、200-非营业
          if (
            this.car.useNatureCode == "100" ||
            this.car.useNatureCode == "200"
          ) {
            if (this.areaCode == "3201") {
              //特种车，江苏--车辆交管类型枚举
              return Object.keys(Config.vehicleCategoryJS100).map(key => ({
                text: Config.vehicleCategoryJS100[key],
                value: key
              }));
            } else if (this.areaCode == "3101" || this.areaCode == "1101") {
              //特种车，上海北京--车辆交管类型枚举
              let vehicleCategoryJS100 = Object.keys(
                Config.vehicleCategoryJS100
              ).map(key => ({
                text: Config.vehicleCategoryJS100[key],
                value: key
              }));
              // 北京上海不包括 S-特种作业专用车、Z-专项作业车、Z12-大型载货专项作业车
              vehicleCategoryJS100 = vehicleCategoryJS100.filter(
                item =>
                  item.value !== "S" &&
                  item.value !== "Z" &&
                  item.value !== "Z12"
              );
              return vehicleCategoryJS100;
            } else {
              // 全国含特殊地区
              return Object.keys(Config.vehicleCategory100).map(key => ({
                text: Config.vehicleCategory100[key],
                value: key
              }));
            }
          }
        }
        // 摩托车/拖拉机/三轮汽车
        if (this.vehicleType == "3") {
          return Object.keys(Config.vehicleCategory3).map(key => ({
            text: Config.vehicleCategory3[key],
            value: key
          }));
        }
      }
      return [];
    },
    // 号牌类型
    licenseTypes() {
      if (
        ["11", "21", "22", "31"].includes(this.car.carKindCode) ||
        this.vehicleType == "4"
      ) {
        return Object.keys(Config.licenseTypes).map(key => ({
          text: Config.licenseTypes[key],
          value: key
        }));
      }
      // 上海广东-摩托车
      if (this.areaCode == "4401" || this.areaCode == "3101") {
        if (["51", "52", "53"].includes(this.car.carKindCode)) {
          return Object.keys(Config.licenseTypeMo).map(key => ({
            text: Config.licenseTypeMo[key],
            value: key
          }));
        }
      }
      return [];
    }
  },
  filters: {
    date(date) {
      return (date || "").split(" ")[0];
    }
  },
  methods: {
    ...mapActions("vehicle", ["calculateActualValue"]),
    input(e, type) {
      //console.log(e.target.value, type);
      if (type == "complementTime") {
        if (e.target.value < 0) {
          e.target.value = 0;
          this.car.complementTime = 0;
        }
        if (e.target.value > 7) {
          e.target.value = 7;
          this.car.complementTime = 7;
        }
      }
    },
    // 整备质量计算
    netBlur(e) {
      this.car.net = String(this.car.net);
      if (/^\d{1,8}(\.\d+)?$/.test(this.car.net)) {
        console.warn("整备质量符合正则");
        console.log(this.car.net.indexOf("."), this.car.net.length);
        let dotIndex = this.car.net.indexOf(".");
        if (dotIndex != -1 && dotIndex < this.car.net.length - 3) {
          let lastNumber = this.car.net.charAt(dotIndex + 3);
          console.log(lastNumber);
          if (lastNumber > 4) {
            this.car.net = this.car.net.substr(0, dotIndex + 3);
            this.car.net = Number(this.car.net) + 0.01;
          }
          console.log(this.car.net);
          this.car.net = Number(this.car.net).toFixed(2);
        }
      } else {
        this.car.net = "";
        this.$toast("请输入正确的整备质量");
        e.target.focus();
      }
      e.target.placeholder = "请输入整备质量";
    },
    // 计算核定载质量
    tonCountBlur(e) {
      this.car.tonCount = String(this.car.tonCount);
      if (/^\d{1,8}(\.\d+)?$/.test(this.car.tonCount)) {
        console.warn("核定载质量符合正则");
        console.log(this.car.tonCount.indexOf("."), this.car.tonCount.length);
        let dotIndex = this.car.tonCount.indexOf(".");
        if (dotIndex != -1 && dotIndex < this.car.tonCount.length - 3) {
          let lastNumber = this.car.tonCount.charAt(dotIndex + 3);
          console.log(lastNumber);
          if (lastNumber > 4) {
            this.car.tonCount = this.car.tonCount.substr(0, dotIndex + 3);
            this.car.tonCount = Number(this.car.tonCount) + 0.01;
          }
          console.log(this.car.tonCount);
          this.car.tonCount = Number(this.car.tonCount).toFixed(2);
        }
      } else {
        this.car.tonCount = "";
        this.$toast("请输入正确的核定载质量");
        e.target.focus();
      }
      e.target.placeholder = "请输入核定载质量";
    },
    // 计算牵引总质量
    haulageBlur(e) {
      this.car.haulage = String(this.car.haulage);
      if (/^\d{1,8}(\.\d+)?$/.test(this.car.haulage)) {
        console.warn("核定牵引总质量");
        console.log(this.car.haulage.indexOf("."), this.car.haulage.length);
        let dotIndex = this.car.haulage.indexOf(".");
        if (dotIndex != -1 && dotIndex < this.car.haulage.length - 3) {
          let lastNumber = this.car.haulage.charAt(dotIndex + 3);
          console.log(lastNumber);
          if (lastNumber > 4) {
            this.car.haulage = this.car.haulage.substr(0, dotIndex + 3);
            this.car.haulage = Number(this.car.haulage) + 0.01;
          }
          console.log(this.car.haulage);
          this.car.haulage = Number(this.car.haulage).toFixed(2);
        }
      } else {
        this.car.haulage = "";
        this.$toast("请输入正确的牵引总质量");
        e.target.focus();
      }
      e.target.placeholder = "请输入牵引总质量";
    },
    // 排量
    exhaustScaleBlur(e) {
      this.car.exhaustScale = String(this.car.exhaustScale);
      if (/^\d{1,4}(\.\d+)?$/.test(this.car.exhaustScale)) {
        console.warn("排量");
        console.log(
          this.car.exhaustScale.indexOf("."),
          this.car.exhaustScale.length
        );
        let dotIndex = this.car.exhaustScale.indexOf(".");
        if (dotIndex != -1 && dotIndex < this.car.exhaustScale.length - 4) {
          let lastNumber = this.car.exhaustScale.charAt(dotIndex + 4);
          console.log(lastNumber);
          if (lastNumber > 4) {
            this.car.exhaustScale = this.car.exhaustScale.substr(
              0,
              dotIndex + 4
            );
            this.car.exhaustScale = Number(this.car.exhaustScale) + 0.001;
          }
          console.log(this.car.exhaustScale);
          this.car.exhaustScale = Number(this.car.exhaustScale).toFixed(3);
        }
      } else {
        this.car.exhaustScale = "";
        this.$toast("请输入正确的排量");
        e.target.focus();
      }
      e.target.placeholder = "请输入排量";
    },
    // 功率计算
    powerScaleBlur(e) {
      this.car.powerScale = String(this.car.powerScale);
      if (/^\d{1,6}(\.\d+)?$/.test(this.car.powerScale)) {
        let dotIndex = this.car.powerScale.indexOf(".");
        if (dotIndex != -1 && dotIndex < this.car.powerScale.length - 2) {
          let lastNumber = this.car.powerScale.charAt(dotIndex + 2);
          console.log(lastNumber);
          if (lastNumber > 4) {
            this.car.powerScale = this.car.powerScale.substr(0, dotIndex + 2);
            this.car.powerScale = Number(this.car.powerScale) + 0.1;
          }
          this.car.powerScale = Number(this.car.powerScale).toFixed(1);
        }
      } else {
        this.car.powerScale = "";
        this.$toast("请输入正确的功率值");
        e.target.focus();
      }
      e.target.placeholder = "请输入功率";
    },
    argueSolutionChange(argueSolution) {
      //归属机构代码（13010000）下，争议解决方式选择仲裁时，仲裁机构赋默认值“A18-石家庄仲裁委员会”，仲裁选择下拉按钮隐藏。
      if (argueSolution == "02") {
        this.car.arbitBoardcode = "A18";
        this.car.arbitBoardname = "石家庄仲裁委员会";
      }
    },
    changePurchasePrice(e) {
      this.car.purchasePrice = Number(
        String(this.car.purchasePrice).replace(/[^0-9]/g, "")
      );
      let range = this.purchasePriceRange();
      if (!range.max) {
        this.car.purchasePrice = "0";
        return;
      }
      if (this.car.purchasePrice <= range.min) {
        this.$dialog
          .alert({
            title: "新车购置价不符合规则",
            message: "新车购置价不能低于" + range.min
          })
          .then(() => {
            e.target.focus();
          });
      } else if (this.car.purchasePrice >= range.max) {
        this.$dialog
          .alert({
            title: "新车购置价不符合规则",
            message: "新车购置价不能高于" + range.max
          })
          .then(() => {
            e.target.focus();
          });
      } else {
        this.calculateActualValue({});
      }
    },
    purchasePriceRange() {
      /**
      1.新车购置价从新核心返回后可以修改，范围是：与使用年限有关；
      2.如果车龄大于等于1年，允许上浮60%，下浮35%；
      3.小于1年，允许上浮60%和下浮30%；
      4.新能源车时，下浮比例不做约束，上浮同上述
       */
      let range = {};
      if (!this.control.vehicleModelChecked) {
        this.$dialog.alert({ message: "车辆尚未定型" }).then(() => this.back());
        return range;
      }
      let defaultPurchasePrice = this.car.vehicleJingyou.purchasePrice;
      range.max = defaultPurchasePrice * 1.6;
      range.min = -1;
      //当新核心返回的能源类型值为“汽油”、“柴油”及“无”或者空字符串时，判定为非新能源车辆，其余字段值均判定为新能源车辆
      //D1: "汽油",D2: "柴油",
      if (
        this.car.fuelType == "D1" ||
        this.car.fuelType == "D2" ||
        this.car.fuelType == ""
      ) {
        if (this.car.useYear >= 1) {
          range.min = defaultPurchasePrice * 0.65;
        } else {
          range.min = defaultPurchasePrice * 0.7;
        }
      }
      return range;
    },
    back() {
      this.$listeners.cancel();
    },
    onDatePickerConfirm(date) {
      this.car.carCheckTime = date.format("yyyy-MM-dd HH:mm:ss");
      this.datePickerShow = false;
    },
    onSearch(searchValue) {
      let pickerData = this.pickerData.filter(({ text, value }) => {
        return (
          String(text).indexOf(searchValue) != -1 ||
          String(value).indexOf(searchValue) != -1
        );
      });
      if (pickerData.length == 0) {
        this.$toast("没有匹配的选项");
      } else {
        this.pickerData = pickerData;
      }
    },
    onCancel() {
      this.setPicker(this.dataPickerType);
    },
    setPicker(dataPickerType) {
      switch (dataPickerType) {
        case "fuelType": {
          this.pickerData = this.fuelTypes;
          break;
        }
        case "carKindCode": {
          this.pickerData = this.carKindCodes;
          break;
        }
        case "vehicleCategory": {
          this.pickerData = this.vehicleCategorys;
          break;
        }
        case "licenseType": {
          this.pickerData = this.licenseTypes;
          break;
        }
        case "licenseColorCode": {
          this.pickerData = this.licenseColorCodes;
          break;
        }
        case "poCategory": {
          this.pickerData = this.poCategorys;
          break;
        }
        case "arbitBoardcode": {
          this.pickerData = this.arbitBoardnames;
          break;
        }
        case "specialCarFlag": {
          this.pickerData = this.specialCarFlags;
          break;
        }
        case "rateClassification": {
          this.pickerData = this.rateClassifications;
          break;
        }
        case "printUseNatureCode": {
          this.pickerData = this.printUseNatureCodes;
          break;
        }
      }
      this.pickerData = [{ text: "请选择", value: "" }, ...this.pickerData];
      this.defaultIndex = this.pickerData.findIndex(
        item => item.value == _.result(this.car, dataPickerType, "")
      );
      // console.log(this.defaultIndex, _.result(this.car, dataPickerType, ""));
      this.dataPickerType = dataPickerType;
    },
    dataPickerConfirm(data) {
      this.car[this.dataPickerType] = data.value;
      if (this.dataPickerType == "arbitBoardcode") {
        this.car.arbitBoardname = data.text;
      }
      this.dataPickerType = "";
      this.pickerData = [];
    },
    dataPickerCancel() {
      this.dataPickerType = "";
      this.pickerData = [];
    }
  }
};
</script>
<style lang="less" scoped>
.vehicle-more-info {
  width: 100%;
  height: 100%;
}
.content {
  width: 100%;
  height: calc(100% - 77px - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  /deep/.van-cell__value {
    overflow: visible;
  }
  .group {
    flex-direction: column;
    background: #f7f7f7;
    padding-bottom: 10px;
  }
  .item {
    height: 48px;
    align-items: center;
    /deep/.van-cell__value {
      flex: 2;
    }
    .item-placeholder {
      color: #ababab;
    }
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-right {
      flex: 1;
      flex-direction: row;
      word-break: keep-all;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: #333333;
      .item-right-input {
        width: 100%;
        text-align: right;
        border: 0;
        background: transparent;
        opacity: 1;
        font-size: 14px;
        color: #333333;
      }
      .item-right-input-unit {
        margin-left: 4px;
      }
      .item-right-button {
        align-items: center;
        pointer-events: auto;
      }
      .item-right-button-text {
        color: #3b8afe;
        font-size: 14px;
        margin: 0 8px;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
    }
  }
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
