<template>
  <div id="person-information-card-property">
    <globalNav title="车辆证件扫描" @clickBack="clickBack" />
    <div class="info">
      <van-cell class="item">
        <span slot="title" class="item-title">是否代理</span>
        <div class="item-right">
          <whether-select
            :disabled="isAgentUser"
            v-model="projectChannel.isProxy"
            :options="[
              { text: '代理', value: '1' },
              { disabled: isAgentUser, text: '非代理', value: '0' }
            ]"
            @change="changeAgentChannel"
          />
        </div>
      </van-cell>
      <van-cell
        class="item"
        v-if="projectChannel.isProxy === '0'"
        required
        @click="toVehicleChannel"
      >
        <span slot="title" class="item-title">直销/直团渠道</span>
        <div class="item-right">
          <span
            :class="{
              'item-placeholder': !projectChannel.businessNatureCode
            }"
            >{{ projectChannel.businessNatureName }}</span
          >
          <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
        </div>
      </van-cell>
      <van-cell
        class="item"
        v-if="projectChannel.isProxy === '1'"
        required
        is-link
        @click="toVehicleChannel"
      >
        <span slot="title" class="item-title">代理人</span>
        <span class="item-right">{{ projectChannel.agentName }}</span>
      </van-cell>
      <van-cell class="group">
        <div slot="title" class="group-title">
          <img
            width="16px"
            height="16px"
            src="../../../assets/vehicle/vehicleInfo/vehicle.png"
          />
          车辆信息
        </div>
        <div class="group-right" @click="OCRDrive">
          <van-icon name="photograph" class="photograph" color="#3B8AFE" />
          <span class="group-right-text">行驶证识别</span>
        </div>
      </van-cell>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.carOwnerImage"
          :src="queryData.carOwnerImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">行驶证车主</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.carOwner"
              placeholder="请输入行驶证车主"
              maxlength="7"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.licenseNoImage"
          :src="queryData.licenseNoImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">车牌号</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.licenseNo"
              placeholder="请输入车牌号"
              maxlength="11"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.frameNoImage"
          :src="queryData.frameNoImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">车架号</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.frameNo"
              maxlength="17"
              placeholder="请输入车架号"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.enrollDateImage"
          :src="queryData.enrollDateImage"
        />
        <van-cell class="item" required is-link @click="changeDatePickerType1">
          <span slot="title" class="item-title">初登日期</span>
          <div slot="default" class="item-right">
            <span
              :class="{
                'drop-item-placeholder': !queryData.enrollDate
              }"
            >
              {{ queryData.enrollDate | date1 }}
            </span>
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.engineNoImage"
          :src="queryData.engineNoImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">发动机号</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.engineNo"
              placeholder="请输入发动机号"
              maxlength="10"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.vehicleBrandImage"
          :src="queryData.vehicleBrandImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">厂牌型号</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.vehicleBrand"
              placeholder="请输入厂牌型号"
              maxlength="20"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.seatCountImage"
          :src="queryData.seatCountImage"
        />
        <van-cell class="item">
          <span slot="title" class="item-title">核定载客数</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.seatCount"
              placeholder="请输入核定载客数"
              maxlength="3"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <img
          class="item-ocr-image"
          v-if="queryData.carKindCodeImage"
          :src="queryData.carKindCodeImage"
        />
        <van-cell class="item" required>
          <span slot="title" class="item-title">交管车辆类型</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              readonly
              v-model="queryData.carKindCode"
              placeholder="请输入交管车辆类型"
            />
          </div>
        </van-cell>
      </div>
      <van-cell class="group">
        <div slot="title" class="group-title">
          <img
            width="16px"
            height="16px"
            src="../../../assets/vehicle/vehicleInfo/carowner.png"
          />
          车主信息
        </div>
        <div class="group-right" @click="OCRIdentity">
          <van-icon name="photograph" class="photograph" color="#3B8AFE" />
          <span class="group-right-text">证件识别</span>
        </div>
      </van-cell>
      <div class="item-container">
        <van-cell class="item" required>
          <span slot="title" class="item-title">车主姓名</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.cardName"
              placeholder="请输入车主姓名"
              maxlength="7"
            />
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <van-cell class="item" required>
          <span slot="title" class="item-title">证件类型</span>
          <div slot="default" class="item-right">
            <span
              :class="{
                'drop-item-placeholder': !queryData.identityType
              }"
            >
              <!-- {{ queryData.identityType ? queryData.identityType : '请选择' }} -->
              {{ queryData.identityType == "111" ? "身份证" : "请选择" }}
            </span>
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <van-cell class="item" required>
          <span slot="title" class="item-title">证件号码</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.identityNo"
              placeholder="请输入证件号码"
              maxlength="18"
              @input="e => input(e, 'identityNo')"
            />
          </div>
        </van-cell>
      </div>
      <!-- 安徽 贵州(安顺中支) 特有 -->
      <div
        class="item-container"
        v-if="areaCode == '5201' && comCName.indexOf('安顺中') !== -1"
      >
        <van-cell
          class="item"
          is-link
          :required="queryData.identityType == '111'"
          :value="queryData.identifyStartDate"
          @click="setPicker('identifyStartDate')"
        >
          <span slot="title" class="item-title">证件有效起期</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !queryData.identifyStartDate
              }"
            >
              {{ queryData.identifyStartDate | date1 }}
            </span>
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <!-- 证件有效止期/长期有效 -->
        <van-cell
          v-if="areaCode == '5201' && comCName.indexOf('安顺中') !== -1"
          class="item"
          :required="queryData.identityType == '111'"
          @click="carOwnerIdentifyLongTerm ? '' : setPicker('identifyEndDate')"
        >
          <span slot="title" class="item-title">证件有效止期</span>
          <div class="item-right">
            <span
              :class="{
                'item-placeholder': !queryData.identifyEndDate
              }"
            >
              {{ queryData.identifyEndDate | date1 }}
            </span>
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            <van-checkbox
              :disabled="!(queryData.identityType == '111' && carOwnerAge > 46)"
              class="item-right-checkbox"
              icon-size="14"
              v-model="carOwnerIdentifyLongTerm"
              @click.stop=""
              @change="v => (queryData.identifyEndDate = v ? '9999-12-31' : '')"
            >
              <span>长期有效</span>
            </van-checkbox>
          </div>
        </van-cell>
      </div>
      <div
        class="item-container"
        v-if="areaCode == '5201' || areaCode == '5301'"
      >
        <van-cell
          class="item"
          is-link
          required
          @click="setPicker('occupation')"
        >
          <span slot="title" class="item-title">职业</span>
          <div class="item-right">
            <span :class="{ 'item-placeholder': !queryData.occupation }">
              {{ $utils.findText(occupationList, queryData.occupation) }}
            </span>
          </div>
        </van-cell>
      </div>
      <div class="item-container">
        <van-cell class="item" required>
          <span slot="title" class="item-title">移动电话</span>
          <div slot="default" class="item-right">
            <input
              class="item-right-input"
              v-model="queryData.phoneNo"
              maxlength="11"
              placeholder="请输入移动电话"
            />
          </div>
        </van-cell>
      </div>
      <van-cell class="group">
        <div slot="title" class="group-title">
          <img
            width="16px"
            height="16px"
            src="../../../assets/vehicle/vehicleInfo/plan.png"
          />
          投保方案
        </div>
      </van-cell>
      <van-tabs @click="changeActiveTab">
        <van-tab
          v-for="tab in tabsObj"
          :key="tab.keyValue"
          :title="tab.title"
          :name="tab.id"
          :class="[tab.isActive ? 'tab-item-active' : '', 'tab-item']"
          @click="getTab(tab.id, tab.keyValue)"
        ></van-tab>
      </van-tabs>
      <div class="jType-container" v-show="showPlanBlock">
        <div class="jType" v-if="trafficShow">
          <div class="headerTitle">
            <img
              class="img"
              :src="require('@/assets/vehicle/businessPlan/planIcon.png')"
            />
            交强险
          </div>
          <div class="item-container">
            <van-cell class="item">
              <span slot="title" class="item-title">
                保险期限
              </span>
              <div
                class="item-right"
                :style="plan.trafficIsEffective == '1' ? 'color:#969799' : ''"
              >
                <span
                  class="dateSpan"
                  @click.stop="
                    changeDatePickerType('trafficEffectiveStartDate')
                  "
                >
                  {{ plan.trafficEffectiveStartDate | date }}</span
                >
                <img
                  src="../../../assets/vehicle/businessPlan/calender.png"
                  alt=""
                />
                <span class="dateSpan" style="margin:0 5px">-</span>
                <span
                  class="dateSpan"
                  @click.stop="changeDatePickerType('trafficEffectiveEndDate')"
                >
                  {{ plan.trafficEffectiveEndDate | date }}
                </span>
                <img
                  src="../../../assets/vehicle/businessPlan/calender.png"
                  alt=""
                />
              </div>
            </van-cell>
          </div>
          <div class="jType-contian">
            <van-row type="flex" justify="center" class="small">
              <van-col span="11">险种</van-col>
              <van-col span="5"></van-col>
              <van-col span="5" align="right">保费(元)</van-col>
            </van-row>
            <van-row class="smallContent" type="flex" justify="center">
              <van-col span="11">交强险</van-col>
              <van-col span="5"></van-col>
              <van-col span="5">{{ trafficSumInsured }}</van-col>
            </van-row>
            <van-row class="smallContent" type="flex" justify="center">
              <van-col span="11">车船税</van-col>
              <van-col span="5"></van-col>
              <van-col span="5"></van-col>
            </van-row>
          </div>
        </div>
        <div class="jType" v-if="businessShow">
          <div class="headerTitle">
            <img
              class="img"
              :src="require('@/assets/vehicle/businessPlan/planIcon.png')"
            />
            商业险
          </div>
          <div class="item-container">
            <van-cell
              class="item"
              @click="changeDatePickerType('businessEffectiveStartDate')"
            >
              <span slot="title" class="item-title">保险期限</span>
              <div
                class="item-right"
                :style="plan.businessIsEffective == '1' ? 'color:#969799' : ''"
              >
                <span
                  class="dateSpan"
                  @click.stop="
                    changeDatePickerType('businessEffectiveStartDate')
                  "
                >
                  {{ plan.businessEffectiveStartDate | date }}
                </span>
                <img
                  src="../../../assets/vehicle/businessPlan/calender.png"
                  alt=""
                />
                <span class="dateSpan" style="margin:0 5px">-</span>
                <span
                  class="dateSpan"
                  @click.stop="changeDatePickerType('businessEffectiveEndDate')"
                >
                  {{ plan.businessEffectiveEndDate | date }}
                </span>
                <img
                  src="../../../assets/vehicle/businessPlan/calender.png"
                  alt=""
                />
              </div>
            </van-cell>
          </div>
          <div class="jType-contian">
            <van-row type="flex" justify="center" class="small">
              <van-col span="12">险种</van-col>
              <van-col span="5">保额(元)</van-col>
              <van-col span="5" align="right">保费(元)</van-col>
            </van-row>
            <van-row
              class="smallContent"
              type="flex"
              justify="center"
              v-for="(item, index) in tabDataS.businessRisks"
              :key="index"
            >
              <van-col span="12"
                >{{ item.kindName | kindName
                }}{{
                  item.kindCode == "C100121" ||
                  item.kindCode == "C100124" ||
                  item.kindCode == "C100125" ||
                  item.kindCode == "C100122" ||
                  item.kindCode == "C100123"
                    ? "(不计免赔)"
                    : ""
                }}</van-col
              >
              <van-col span="5">{{ item.amount }}</van-col>
              <van-col span="5" align="right">{{ item.premium }}</van-col>
            </van-row>
          </div>
        </div>
        <div class="jType" v-if="wealthShow">
          <div class="headerTitle">
            <img
              class="img"
              :src="require('@/assets/vehicle/businessPlan/planIcon.png')"
            />
            联合销售
          </div>
          <div class="jType-contian">
            <van-row type="flex" justify="center" class="small">
              <van-col span="6" align="center">险种</van-col>
              <van-col span="6" align="center">方案</van-col>
              <van-col span="6" align="center">份数</van-col>
              <van-col span="6" align="center">保费(元)</van-col>
            </van-row>
            <van-row class="smallContent" type="flex" justify="center">
              <van-col span="6" align="center">{{
                tabDataC.wealthAccFormulaGroupName
              }}</van-col>
              <van-col span="6" align="center">{{
                tabDataC.wealthAccFormulaName
              }}</van-col>
              <van-col span="6" align="center">{{
                tabDataC.wealthAccBuyNum
              }}</van-col>
              <van-col span="6" align="center"></van-col>
            </van-row>
          </div>
        </div>
      </div>
      <div class="orc-downbox">
        <van-icon
          class="ocr-down"
          :class="showPlanBlock ? 'ocr-down-rotate' : ''"
          name="arrow-down"
          @click="showPlanBlock = !showPlanBlock"
        />
      </div>
      <van-button
        text="获取报价"
        @click="submitDefaultInfo"
        class="signature-submit-button"
      />
      <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
        <van-picker
          show-toolbar
          :columns="pickerData"
          :default-index="defaultIndex"
          @confirm="dataPickerConfirm"
          @cancel="dataPickerCancel"
        />
      </van-popup>
      <!-- 时间选择 -->
      <van-popup v-model="datePickerShow1" position="bottom">
        <van-datetime-picker
          type="date"
          v-model="currentDate"
          :min-date="minDate1"
          :max-date="maxDate1"
          @confirm="onDatePickerConfirm1"
          @cancel="datePickerShow1 = false"
        />
      </van-popup>
      <!-- 证件类型选择 -->
      <!-- 日期区间选择 -->
      <van-popup v-model="datePickerShow" position="bottom">
        <van-datetime-picker
          :type="
            datePickerType == 'identifyStartDate' ||
            datePickerType == 'identifyEndDate'
              ? 'date'
              : 'datetime'
          "
          v-model="currentDate"
          :min-date="minDate"
          :max-date="maxDate"
          @confirm="onDatePickerConfirm"
          @cancel="datePickerType = ''"
        />
      </van-popup>
      <van-dialog
        v-model="showReInsured"
        title="重复投保提示"
        class="reinsured-dialog"
        :show-confirm-button="reInsureItemHasEndTime"
        show-cancel-button
        confirm-button-text="重新报价"
        @confirm="startTimeWithReinsuredItem"
      >
        <div class="reinsured-content-tip">
          温馨提示: 点击重新报价，系统自动按照平台返回的终保日期进行重新报价。
        </div>
        <div class="reinsured-content">
          <div
            class="card"
            v-for="(reInsureItem, index) in currData.reInsureItem"
            :key="index"
          >
            <div class="cell cell-title">
              <div>
                {{
                  reInsureItem.type == "traffic"
                    ? "交强险重复投保"
                    : reInsureItem.type == "business"
                    ? "商业险重复投保"
                    : ""
                }}
              </div>
            </div>
            <div class="cell">
              <div>重复投保单号</div>
              <div>{{ reInsureItem.policyNo }}</div>
            </div>
            <div class="cell">
              <div>车牌号</div>
              <div>{{ reInsureItem.licensePlateNo }}</div>
            </div>
            <div class="cell">
              <div>车架号</div>
              <div>{{ reInsureItem.vin }}</div>
            </div>
            <div class="cell">
              <div>保险公司</div>
              <div>{{ reInsureItem.insurerName }}</div>
            </div>
            <div class="cell long-info">
              <div>险种信息</div>
              <div>{{ reInsureItem.coverageItemList }}</div>
            </div>
            <div class="cell">
              <div>起保日期</div>
              <div>{{ reInsureItem.effectiveDate }}</div>
            </div>
            <div class="cell">
              <div>终保日期</div>
              <div>{{ reInsureItem.expireDate }}</div>
            </div>
            <div class="cell">
              <div>承保地区</div>
              <div>{{ reInsureItem.insurerArea }}</div>
            </div>
            <div class="cell">
              <div>重复投保公司种类</div>
              <div>{{ reInsureItem.insurerCompanyType }}</div>
            </div>
          </div>
        </div>
      </van-dialog>
    </div>
  </div>
</template>
<script>
import globalNav from "@/components/globalNav.vue";
import WhetherSelect from "../vehicleInfo/whetherSelect";
import { mapState, mapMutations } from "vuex";
import {
  getVehicleDefaultInfo,
  submitVehicleDefaultInfo
} from "@src/http/module/product.js";
import {
  getDefaultChannel,
  agentQuery,
  getDefaultAgent
} from "@src/http/module/vehicle";
import Config from "@src/common/json/newCoreDataDictionary";
const ValidateRules = {
  businessNatureCode: { required: "代理信息" },
  carOwner: { required: "行驶证车主", name: "不符合规则" },
  licenseNo: { required: "车牌号", licenseNo2: "不符合规则" },
  frameNo: { required: "车架号", vin: "不符合规则" },
  enrollDate: { required: "初登日期" },
  engineNo: { required: "发动机号" },
  vehicleBrand: { required: "厂牌型号" },
  cardName: { required: "车主姓名", name: "不符合规则" },
  identityNo: { required: "证件号码", idcard: "不符合规则" },
  phoneNo: { required: "移动电话", mobile: "不符合规则" }
};
export default {
  components: {
    globalNav,
    [WhetherSelect.name]: WhetherSelect
  },
  data: () => ({
    areaCode: "",
    comCName: "",
    riskCode: "DEA",
    isAgentUser: false,
    //重复投保提示
    showReInsured: false,
    // 获取的默认数据
    alldata: "", //所有数据
    currData: "", //当前数据
    licenseNoImage: "../../../assets/performance/icon4.png",
    frameNoImage: "../../../assets/performance/icon4.png",
    //保险期限数据
    plan: {
      businessEffectiveEndDate: "",
      businessEffectiveStartDate: "",
      trafficEffectiveEndDate: "",
      trafficEffectiveStartDate: ""
    },
    // 各险种数据
    tabDataS: {
      //商业险数据
      businessRisks: []
    },
    tabDataC: {
      //联合销售数据
      wealthAccFormulaGroupName: "",
      wealthAccFormulaName: "",
      wealthAccBuyNum: "1",
      wealthAccPremium: ""
    },
    //交强险数据
    trafficSumInsured: "",
    // 初登日期
    datePickerShow1: false,
    currentDate1: new Date(),
    currentDate: new Date(),
    minDate: new Date(),
    maxDate: new Date(2050, 10, 1),
    minDate1: new Date(1970, 10, 1),
    maxDate1: new Date(),
    // 套餐切换
    tabsObj: [
      // { id: 0, title: '简约版', isActive: true, keyValue: 's' },
      // { id: 1, title: '经济版', isActive: false, keyValue: 'e' },
      // { id: 2, title: '豪华版', isActive: false, keyValue: 'l' }
    ],
    // 日期区间
    datePickerType: "",
    trafficShow: false,
    businessShow: false,
    wealthShow: false,
    showPlanBlock: false,
    pickerData: [],
    defaultIndex: 0,
    occupationList: Object.keys(Config.profession).map(key => ({
      text: Config.profession[key],
      value: key
    })),
    carOwnerAge: 0,
    //debug数据
    reInsureItem: [
      {
        type: "traffic",
        applicantionId: "",
        billDate: "",
        coverageItemList: "",
        effectiveDate: "",
        engineNo: "",
        expireDate: "2020-06-10 00:00:00",
        insurerArea: "",
        insurerCode: "",
        insurerCompanyType: "",
        licensePlateColorCode: "",
        licensePlateNo: "",
        licensePlateType: "",
        policyNo: "",
        vin: ""
      },
      {
        type: "business",
        applicantionId: "",
        billDate: "",
        coverageItemList:
          "车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险",
        effectiveDate: "",
        engineNo: "",
        expireDate: "2020-06-17 00:00:00",
        insurerArea: "",
        insurerCode: "",
        insurerCompanyType: "",
        licensePlateColorCode: "",
        licensePlateNo: "",
        licensePlateType: "",
        policyNo: "",
        vin: ""
      },
      {
        type: "business",
        applicantionId: "",
        billDate: "",
        coverageItemList:
          "车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险, 车辆损失保险, 第三者责任保险",
        effectiveDate: "",
        engineNo: "",
        expireDate: "",
        insurerArea: "",
        insurerCode: "",
        insurerCompanyType: "",
        licensePlateColorCode: "",
        licensePlateNo: "",
        licensePlateType: "",
        policyNo: "",
        vin: ""
      }
    ]
  }),
  mounted() {
    if (this.$isAgent()) {
      this.isAgentUser = true;
    } else {
      this.isAgentUser = false;
    }
    this.getDefaultInfo();
  },
  activated() {
    let userInfo = this.localStorage.get("userInfo", {});
    let comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    this.comCName = userInfo.comCName || "";
    if (
      !this.projectChannel.agentAgreementCode &&
      !this.projectChannel.businessNatureCode
    ) {
      const defaultChannel = {
        isProxy: "1"
      };
      this.setProjectChannel(defaultChannel);
      this.getDefaultChannel();
    }
    if (this.$route.params.stringParams) {
      let params = JSON.parse(this.$route.params.stringParams);
      this.queryData.carOwner = params.carOwner;
      this.queryData.carOwnerImage = params.carOwnerImage;
      this.queryData.licenseNo = params.licenseNo;
      this.queryData.licenseNoImage = params.licenseNoImage;
      this.queryData.frameNo = params.frameNo;
      this.queryData.frameNoImage = params.frameNoImage;
      this.queryData.enrollDate = params.enrollDate;
      this.queryData.enrollDateImage = params.enrollDateImage;
      this.queryData.engineNo = params.engineNo;
      this.queryData.engineNoImage = params.engineNoImage;
      this.queryData.vehicleBrand = params.vehicleBrand;
      this.queryData.vehicleBrandImage = params.vehicleBrandImage;
    }
    // this.getDefaultInfo();
  },
  computed: {
    ...mapState("vehicle", {
      queryData: ({ queryData }) => queryData,
      projectChannel: ({ projectChannel }) => projectChannel,
      control: ({ control }) => control
    }),
    ...mapState("channel", {
      channel: ({ channel }) => channel
    }),
    orderId: {
      get: function() {
        return this.$store.state.orderId;
      },
      set: function(val) {
        this.$store.state.orderId = val;
      }
    },
    datePickerShow: {
      get: function() {
        return !!this.datePickerType;
      },
      set: function() {
        this.datePickerType = "";
      }
    },
    dataPickerShow: {
      get: function() {
        return !!this.pickerData.length;
      },
      set: function() {
        this.pickerData = [];
      }
    },
    carOwnerIdentifyLongTerm: {
      get: function() {
        return this.queryData.identifyLongTerm == "1";
      },
      set: function(v) {
        console.log(
          this.queryData.identityType == "111" && this.carOwnerAge > 46
        );
        this.queryData.identifyLongTerm = v ? "1" : "0";
      }
    },
    reInsureItemHasEndTime() {
      return (
        this.currData.reInsureItem &&
        !!this.currData.reInsureItem.find(item => !!item.expireDate)
      );
    }
  },
  watch: {
    "queryData.carOwner"(val) {
      this.queryData.carOwner = val.toUpperCase();
    },
    "queryData.licenseNo"(val) {
      this.queryData.licenseNo = val.toUpperCase();
    },
    "queryData.frameNo"(val) {
      this.queryData.frameNo = val.toUpperCase();
    },
    "queryData.engineNo"(val) {
      this.queryData.engineNo = val.toUpperCase();
    },
    "queryData.vehicleBrand"(val) {
      this.queryData.vehicleBrand = val.toUpperCase();
    }
  },
  filters: {
    date(date) {
      return date && date.length
        ? date.substring(0, date.length - 3).replace(/-/g, "/")
        : "请选择";
    },
    date1(date) {
      return (date || "").split(" ")[0].replace(/-/g, "/") || "请选择";
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "setOrderId",
      "setQueryData",
      "initProjectChannel",
      "setProjectChannel"
    ]),
    ...mapMutations("channel", ["setChannel"]),
    // 获取投保方案默认数据
    async getDefaultInfo() {
      await getVehicleDefaultInfo({}).then(res => {
        if (res.code === "1") {
          this.alldata = res.data;
          this.currData = res.data[0];
          this.trafficChecked =
            this.currData.trafficChecked == "1" ? true : false;
          this.businessChecked =
            this.currData.businessChecked == "1" ? true : false;
          this.wealthAccChecked =
            this.currData.wealthAccChecked == "1" ? true : false;
          this.tabsObj = [];
          if (res.data.length > 0) {
            for (let i = 0; i < res.data.length; i++) {
              let obj = {};
              if (res.data[i].planCode == "s") {
                obj.id = i + 1;
                obj.title = "简约版";
                obj.isActive = false;
                obj.keyValue = "s";
              } else if (res.data[i].planCode == "e") {
                obj.id = i + 1;
                obj.title = "经济版";
                obj.isActive = false;
                obj.keyValue = "e";
              } else if (res.data[i].planCode == "l") {
                obj.id = i + 1;
                obj.title = "豪华版";
                obj.isActive = false;
                obj.keyValue = "l";
              }
              this.tabsObj.push(obj);
              this.tabsObj[0].isActive = true;
            }
          }
          // 默认数据赋值
          this.plan.trafficEffectiveStartDate = this.currData.trafficEffectiveStartDate;
          this.plan.trafficEffectiveEndDate = this.currData.trafficEffectiveEndDate;
          this.plan.businessEffectiveStartDate = this.currData.businessEffectiveStartDate;
          this.plan.businessEffectiveEndDate = this.currData.businessEffectiveEndDate;
          this.tabDataS.businessRisks = this.currData.businessRisks;
          this.tabDataC.wealthAccFormulaGroupName = this.currData.wealthAccFormulaGroupName;
          this.tabDataC.wealthAccFormulaName = this.currData.wealthAccFormulaName;
          this.tabDataC.wealthAccPremium = this.currData.wealthAccPremium;
          this.trafficShow =
            this.currData.trafficChecked === "1" ? true : false;
          this.businessShow =
            this.currData.businessChecked === "1" ? true : false;
          this.wealthShow =
            this.currData.wealthAccChecked === "1" ? true : false;
          this.fixDate();
        }
      });
    },
    // 是否代理切换
    changeAgentChannel(isProxy) {
      this.initProjectChannel({ isProxy });
      this.getDefaultChannel();
    },
    getDefaultChannel() {
      getDefaultChannel({ isProxy: this.projectChannel.isProxy }).then(
        res => {
          if (res.code === "1") {
            if (res.data.isProxy) {
              this.setProjectChannel(res.data);
              if (res.data.isProxy == "1") {
                this.agentQuery();
              }
            } else {
              if (this.projectChannel.isProxy == "1") {
                this.getDefaultAgent();
              } else {
                this.setProjectChannel({
                  isProxy: "0",
                  businessNature: "0101",
                  businessNatureCode: "0101",
                  businessNatureName: "业务员销售（直销）"
                });
              }
            }
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    getDefaultAgent() {
      getDefaultAgent({}).then(
        res => {
          if (res.code === "1") {
            if (res.data.isDefault) {
              this.projectChannel.agentName = res.data.agentName;
              this.projectChannel.agentCode = res.data.agentCode;
              this.projectChannel.businessNature = res.data.businessSource;
              this.projectChannel.agentAgreementCode =
                res.data.agentAgreementCode;
              this.projectChannel.agentAgreementName =
                res.data.agentAgreementName;
              this.projectChannel.permitNo = res.data.permitNo;
              this.projectChannel.exhibitionNo = res.data.exhibitionNo;
              this.agentQuery();
            }
          }
        },
        error => {}
      );
    },
    agentQuery() {
      /**requestType
       * 01:查询代理人协议信息
       * 02:查询业务风险分类 （N码）
       * 03:查询代理人协议信息（模糊查询）
       */
      // let params = {
      //   requestType: "02",
      //   agentCode: "8000189135" || this.agent.agentCode,
      //   comCode: "01",
      //   businessNature: "01",
      //   riskCode: "01"
      // };
      let userInfo = this.localStorage.get("userInfo", {});
      //1 不代理 2 代理
      let requestType = this.projectChannel.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.projectChannel.isProxy == "0"
          ? userInfo.userCode
          : this.projectChannel.agentCode;
      let businessSource = this.projectChannel.businessNatureCode;
      if (!agentCode || !businessSource) {
        console.warn("无代理人, 无业务来源");
        return;
        // return this.getDefaultAgent();
      }
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: this.riskCode,
        businessSource,
        agentCode,
        agreementNo: this.projectChannel.agentAgreementCode,
        agreementName: this.projectChannel.agentAgreementName
      };
      agentQuery(params).then(
        res => {
          if (res.code === "1" && res.data) {
            let { salesMainMsgDto = {} } = res.data;
            this.requestData = salesMainMsgDto;
            let {
              salesAgentMsgDto = {},
              salesAgreementMsgList = []
            } = salesMainMsgDto;
            let {
              // comCode,
              // agreementNo,
              // agreementName,
              businessNature
            } = salesAgentMsgDto;
            let agreementIsVaild = false;
            if (Array.isArray(salesAgreementMsgList)) {
              salesAgreementMsgList.forEach(item => {
                if (
                  item.agreementNo == this.projectChannel.agentAgreementCode
                ) {
                  agreementIsVaild = true;
                }
              });
            }
            //console.log(this.salesAgreementMsgList);
            // this.comCode = comCode; //归属机构
            this.projectChannel.businessNatureCode =
              businessNature || this.projectChannel.businessNatureCode; //业务来源
            let businessNatures = Object.keys(Config.businessNature).map(
              key => ({
                text: Config.businessNature[key],
                value: key
              })
            );
            businessNatures.push({ text: "业务员销售（直销）", value: "0101" });
            businessNatures.push({ text: "业务员销售（直团）", value: "7101" });
            businessNatures.push({ text: "门店直销", value: "0201" });
            this.projectChannel.businessNatureName = this.$utils.findText(
              businessNatures,
              this.projectChannel.businessNatureCode
            );
            // this.projectChannel.agentAgreementCode = agreementNo; //代理人协议号
            // this.projectChannel.agentAgreementName = agreementName; //代理人协议名称
            if (!agreementIsVaild && salesAgreementMsgList.length) {
              this.projectChannel.agentAgreementCode =
                salesAgreementMsgList[0].agreementNo;
              this.projectChannel.agentAgreementName =
                salesAgreementMsgList[0].agreementName;
            } else {
              this.projectChannel.agentAgreementCode = "";
              this.projectChannel.agentAgreementName = "";
            }
          } else {
            if (this.projectChannel.isProxy == "1") {
              this.$dialog.alert({ message: res.msg });
            }
          }
        },
        error => {}
      );
    },
    toVehicleChannel() {
      const query = { riskCode: "DEA" };
      const params = { confirm: this.setProjectChannel };
      this.$root.$once("channelChange", channel =>
        this.setProjectChannel(channel)
      );
      this.setChannel(this.projectChannel);
      this.$router.push({ name: "vehicleChannel", query, params });
    },
    // 校验日期格式
    checkDate(dateStr) {
      let trs = /^(\d{4})-(\d{2})-(\d{2})[ ](\d{2}):(\d{2}):(\d{2})$/;
      if (!trs.test(dateStr)) {
        return false;
      } else {
        return true;
      }
    },
    // 校验
    validate() {
      const {
        trafficChecked,
        businessChecked,
        wealthAccChecked
      } = this.currData;
      const { isProxy } = this.projectChannel;
      let props = JSON.parse(JSON.stringify(ValidateRules));
      if (trafficChecked == "1") {
        props = Object.assign(props, {
          trafficEffectiveStartDate: { required: "交强险起保日期" },
          trafficEffectiveEndDate: { required: "交强险终保日期" }
        });
      }
      if (businessChecked == "1") {
        props = Object.assign(props, {
          businessEffectiveStartDate: { required: "商业险起保日期" },
          businessEffectiveEndDate: { required: "商业险终保日期" }
        });
      }
      if (wealthAccChecked == "1") {
        /** 联合销售校验 */
      }
      if (isProxy === "0") {
        //不代理
      } else if (isProxy === "1") {
        //代理
        props["agentCode"] = { required: "代理人" };
      }
      // 云南贵州特有
      if (this.areaCode == "5201" || this.areaCode == "5301") {
        props["occupation"] = { required: "职业" };
      }
      // 贵州安顺特有
      if (this.areaCode == "5201" && this.comCName.indexOf("安顺中") !== -1) {
        props["identifyStartDate"] = { required: "证件有效起期" };
        props["identifyEndDate"] = { required: "证件有效止期" };
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            this.queryData[prop] ||
              this.currData[prop] ||
              this.projectChannel[prop]
          );
          if (!status) {
            console.error(prop, status, msg);
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(error);
        return false;
      }
      return true;
    },
    //提交扫描车辆信息
    async submitDefaultInfo() {
      this.queryData.carKindCode = "K33";
      this.currData.trafficEffectiveStartDate = this.currData.hasOwnProperty(
        "trafficChecked"
      )
        ? this.plan.trafficEffectiveStartDate
        : "";
      this.currData.trafficEffectiveEndDate = this.currData.hasOwnProperty(
        "trafficChecked"
      )
        ? this.plan.trafficEffectiveEndDate
        : "";
      this.currData.businessEffectiveStartDate = this.currData.hasOwnProperty(
        "businessChecked"
      )
        ? this.plan.businessEffectiveStartDate
        : "";
      this.currData.businessEffectiveEndDate = this.currData.hasOwnProperty(
        "businessChecked"
      )
        ? this.plan.businessEffectiveEndDate
        : "";
      this.currData.businessRisks = this.tabDataS.businessRisks;
      this.currData.wealthAccFormulaGroupName = this.tabDataC.wealthAccFormulaGroupName;
      this.currData.wealthAccFormulaName = this.tabDataC.wealthAccFormulaName;
      this.currData.wealthAccPremium = this.tabDataC.wealthAccPremium;
      this.currData.wealthAccBuyNum = "1";
      // 删除传参时中的image参数,因querData需存store,故另外赋变量
      let paramsQueryData = JSON.parse(JSON.stringify(this.queryData));
      for (let key in paramsQueryData) {
        if (key.indexOf("Image") != -1) {
          delete paramsQueryData[key];
        }
      }
      // console.log(
      //   JSON.stringify({
      //     ...paramsQueryData,
      //     insurancePlan: {
      //       ...this.currData
      //     },
      //     channel: {
      //       ...this.projectChannel
      //     }
      //   })
      // );
      if (
        this.queryData.cardName &&
        this.queryData.cardName != this.queryData.carOwner
      ) {
        this.$dialog.alert({ message: "车主姓名与行驶证所有人不一致，请修改" });
        return;
      }
      if (
        this.queryData.enrollDate &&
        !this.checkDate(this.queryData.enrollDate)
      ) {
        this.$dialog.alert({ message: "日期格式不正确，请重新选择" });
        return;
      }
      if (this.validate()) {
        await submitVehicleDefaultInfo({
          ...paramsQueryData,
          insurancePlan: {
            ...this.currData
          },
          channel: {
            ...this.projectChannel
          }
        }).then(res => {
          if (res.code === "1") {
            let {
              insurancePlan,
              trafficReinsureInfo //交强险重复投保信息
            } = res.data;
            let { reInsureItem } = insurancePlan || {};
            reInsureItem = reInsureItem || [];
            reInsureItem = reInsureItem.map(item => ({
              ...item,
              type: "business"
            }));
            if (trafficReinsureInfo && typeof trafficReinsureInfo == "object") {
              const trafficReinsuredItem = {
                type: "traffic",
                applicantionId: "",
                billDate: "",
                coverageItemList: "",
                effectiveDate: trafficReinsureInfo.effectiveStartDate,
                engineNo: trafficReinsureInfo.engineNo,
                expireDate: trafficReinsureInfo.effectiveEndDate,
                insurerArea: trafficReinsureInfo.area,
                insurerCode: "",
                insurerCompanyType: "",
                licensePlateColorCode: "",
                licensePlateNo: trafficReinsureInfo.licenseNo,
                licensePlateType: trafficReinsureInfo.licenseType,
                policyNo: trafficReinsureInfo.policyNo,
                vin: trafficReinsureInfo.frameNo
              };
              reInsureItem.unshift(trafficReinsuredItem);
            }
            if (Array.isArray(reInsureItem) && reInsureItem.length) {
              this.currData.reInsureItem = reInsureItem;
              this.showReInsured = true;
              return;
            }

            this.orderId = res.data.orderId;
            // 存订单号
            this.setOrderId(this.orderId);
            // 存扫描信息
            this.setQueryData(this.queryData);
            this.$router.push({
              name: "vehicleInsureConfirm",
              params: { ocrFlag: "1" }
            });
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        });
      }
    },
    startTimeWithReinsuredItem() {
      const reInsureItem = this.currData.reInsureItem;
      if (Array.isArray(reInsureItem) && reInsureItem.length) {
        //TODO: 重复发投保时间处理
        //effectiveDate   起保日期
        //expireDate      终保日期
        const trafficReInsureItem = reInsureItem.filter(
          item => item.type == "traffic"
        );
        const businessReInsureItem = reInsureItem.filter(
          item => item.type == "business"
        );
        trafficReInsureItem.map(item => {
          if (item.expireDate) {
            const now = new Date().format("yyyy-MM-dd");
            const itemEndDate = item.expireDate.substr(0, 10);
            if (now <= itemEndDate) {
              const startDate = new Date(itemEndDate.replace(/-/g, "/"));
              this.currData.trafficEffectiveStartDate = this.plan.trafficEffectiveStartDate = startDate.format(
                "yyyy-MM-dd 00:00:00"
              );
              const endDate = new Date(startDate);
              endDate.setFullYear(startDate.getFullYear() + 1);
              endDate.setDate(startDate.getDate() - 1);
              this.currData.trafficEffectiveEndDate = this.plan.trafficEffectiveEndDate = endDate.format(
                "yyyy-MM-dd 23:59:59"
              );
            }
          }
        });
        businessReInsureItem.map(item => {
          if (item.expireDate) {
            const now = new Date().format("yyyy-MM-dd");
            const itemEndDate = item.expireDate.substr(0, 10);
            if (now <= itemEndDate) {
              const startDate = new Date(itemEndDate.replace(/-/g, "/"));
              this.currData.businessEffectiveStartDate = this.plan.businessEffectiveStartDate = startDate.format(
                "yyyy-MM-dd 00:00:00"
              );
              const endDate = new Date(startDate);
              endDate.setFullYear(startDate.getFullYear() + 1);
              endDate.setDate(startDate.getDate() - 1);
              this.currData.businessEffectiveEndDate = this.plan.businessEffectiveEndDate = endDate.format(
                "yyyy-MM-dd 23:59:59"
              );
            }
          }
        });
      }
      this.currData.reInsureItem = [];
      this.showReInsured = false;
      this.submitDefaultInfo();
    },
    input(e, type) {
      switch (type) {
        case "identityNo": {
          let { status } = this.$validate(
            { idcard: "不符合规则" },
            this.queryData.identityNo
          );
          if (status) {
            if (this.queryData.identityNo) {
              let year = this.queryData.identityNo.substr(6, 4);
              this.carOwnerAge = new Date().getFullYear() - year;
            }
          }
          break;
        }
        default:
          break;
      }
      // e.target.value = e.target.value.toUpperCase();
    },
    setPicker(type) {
      switch (type) {
        case "occupation":
          this.pickerData = this.occupationList;
          break;
        case "identifyStartDate": {
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          this.datePickerType = type;
          return;
        }
        case "identifyEndDate": {
          this.minDate = new Date();
          this.maxDate = new Date(2100, 11, 31);
          this.datePickerType = type;
          return;
        }
        default:
          break;
      }
    },
    dataPickerConfirm(data) {
      this.queryData.occupation = data.value;
      this.pickerData = [];
    },
    dataPickerCancel() {
      this.pickerData = [];
    },
    onDatePickerConfirm1(date) {
      this.queryData.enrollDate = date.format();
      this.datePickerShow1 = false;
    },
    isValidDate(date) {
      return date instanceof Date && !isNaN(date.getTime());
    },
    changeDatePickerType1() {
      this.datePickerShow1 = true;
      this.currentDate1 = this.isValidDate(new Date());
    },
    // 切换投保方案
    changeActiveTab(name) {
      this.currData = this.alldata[name - 1]; //当前数据
      this.currData.planCode = this.tabsObj[name - 1].keyValue;
      this.plan.trafficEffectiveStartDate = this.currData.trafficEffectiveStartDate;
      this.plan.trafficEffectiveEndDate = this.currData.trafficEffectiveEndDate;
      this.plan.businessEffectiveStartDate = this.currData.businessEffectiveStartDate;
      this.plan.businessEffectiveEndDate = this.currData.businessEffectiveEndDate;
      this.tabDataS.businessRisks = this.currData.businessRisks;
      this.tabDataC.wealthAccFormulaGroupName = this.currData.wealthAccFormulaGroupName;
      this.tabDataC.wealthAccFormulaName = this.currData.wealthAccFormulaName;
      this.tabDataC.wealthAccPremium = this.currData.wealthAccPremium;
      if (this.currData.trafficChecked == "1") {
        this.trafficShow = true;
      } else {
        this.trafficShow = false;
      }
      if (this.currData.businessChecked == "1") {
        this.businessShow = true;
      } else {
        this.businessShow = false;
      }
      if (this.currData.wealthAccChecked == "1") {
        this.wealthShow = true;
      } else {
        this.wealthShow = false;
      }
      //判断是否已选择日期
      if (
        !this.alldata[name - 1].trafficEffectiveStartDate ||
        !this.alldata[name - 1].trafficEffectiveEndDate ||
        !this.alldata[name - 1].businessEffectiveStartDate ||
        !this.alldata[name - 1].businessEffectiveEndDate
      ) {
        this.fixDate();
      }
    },
    changeDatePickerType(type) {
      switch (type) {
        case "trafficEffectiveStartDate": {
          let minDate = new Date();
          minDate.setMinutes(minDate.getMinutes() + 1);
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 100);
          this.maxDate = maxDate;
          break;
        }
        case "trafficEffectiveEndDate": {
          let defaultMinDate = new Date();
          defaultMinDate.setDate(defaultMinDate.getDate() + 1);
          defaultMinDate.setHours(0);
          defaultMinDate.setMinutes(0);
          defaultMinDate.setSeconds(0);
          let minDate = this.plan.trafficEffectiveStartDate
            ? new Date(this.plan.trafficEffectiveStartDate.replace(/-/g, "/"))
            : defaultMinDate;
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 1);
          this.maxDate = maxDate;
          break;
        }
        case "businessEffectiveStartDate": {
          let minDate = new Date();
          minDate.setMinutes(minDate.getMinutes() + 1);
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 100);
          this.maxDate = maxDate;
          break;
        }
        case "businessEffectiveEndDate": {
          let defaultMinDate = new Date();
          defaultMinDate.setDate(defaultMinDate.getDate() + 1);
          defaultMinDate.setHours(0);
          defaultMinDate.setMinutes(0);
          defaultMinDate.setSeconds(0);
          let minDate = this.plan.businessEffectiveStartDate
            ? new Date(this.plan.businessEffectiveStartDate.replace(/-/g, "/"))
            : defaultMinDate;
          this.minDate = minDate;
          let maxDate = new Date(minDate);
          maxDate.setFullYear(maxDate.getFullYear() + 1);
          this.maxDate = maxDate;
          break;
        }
        default:
          break;
      }
      this.currentDate = new Date(this.plan[type] || null);
      this.datePickerType = type;
    },
    onDatePickerConfirm(date) {
      switch (this.datePickerType) {
        case "identifyStartDate": {
          this.queryData.identifyStartDate = date.format("yyyy-MM-dd");
          break;
        }
        case "identifyEndDate": {
          this.queryData.identifyEndDate = date.format("yyyy-MM-dd");
          break;
        }
        case "trafficEffectiveStartDate": {
          if (
            this.plan.trafficEffectiveStartDate ==
            this.plan.businessEffectiveStartDate
          ) {
            this.setStartDate("business", date, this.currData.planCode);
          }
          this.setStartDate("traffic", date, this.currData.planCode);
          break;
        }

        case "businessEffectiveStartDate": {
          this.setStartDate("business", date, this.currData.planCode);
          break;
        }
        case "trafficEffectiveEndDate": {
          let startHour = "";
          let format = "yyyy-MM-dd 24:00:00";
          if (this.plan.trafficEffectiveStartDate) {
            startHour = this.plan.trafficEffectiveStartDate.substr(11, 2);
          }
          if (startHour == date.getHours() && startHour != "00") {
            format = "yyyy-MM-dd HH:mm:00";
          }
          this.plan[this.datePickerType] = date.format(format);
          break;
        }
        case "businessEffectiveEndDate": {
          this.plan[this.datePickerType] = date.format("yyyy-MM-dd 24:00:00");
          // this.setEndDate("traffic", date);
          break;
        }
        default:
          this.plan[this.datePickerType] = date.format();
          break;
      }
      this.datePickerType = "";
    },
    // 设置默认保险时间
    fixDate() {
      let date = new Date();
      date.setHours(0);
      date.setMinutes(0);
      // if (date > this.plan.trafficEffectiveStartDate) {
      if (this.plan.trafficIsEffective != "1") {
        date.setDate(date.getDate() + 1);
      }
      this.setStartDate("traffic", date, this.currData.planCode);
      date = new Date();
      // }
      date.setHours(0);
      date.setMinutes(0);
      // if (date > this.plan.businessEffectiveStartDate) {
      if (this.plan.businessIsEffective != "1") {
        date.setDate(date.getDate() + 1);
      }
      this.setStartDate("business", date, this.currData.planCode);
      // }
    },
    setStartDate(type, date, code) {
      let nextDate = new Date(date);
      nextDate.setFullYear(date.getFullYear() + 1);

      let startFormat = "yyyy-MM-dd HH:mm:00";
      let endFormat = "yyyy-MM-dd HH:mm:00";
      if (date.getHours() == "00") {
        nextDate.setDate(nextDate.getDate() - 1);
        endFormat = "yyyy-MM-dd 24:00:00";
      }
      if (type == "business") {
        endFormat = "yyyy-MM-dd 24:00:00";
      }
      if (this.currData.hasOwnProperty(type + "Checked")) {
        this.plan[type + "EffectiveStartDate"] = date.format(startFormat);
        this.plan[type + "EffectiveEndDate"] = nextDate.format(endFormat);
      }
      if (code === "s") {
        if (this.alldata.length) {
          this.alldata[0][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[0][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        }
      }
      if (code === "e") {
        if (this.alldata.length == 1) {
          this.alldata[0][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[0][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        } else if (this.alldata.length == 2) {
          if (this.alldata[0].planCode == "s") {
            this.alldata[1][type + "EffectiveStartDate"] = date.format(
              startFormat
            );
            this.alldata[1][type + "EffectiveEndDate"] = nextDate.format(
              endFormat
            );
          } else if (this.alldata[0].planCode == "e") {
            this.alldata[0][type + "EffectiveStartDate"] = date.format(
              startFormat
            );
            this.alldata[0][type + "EffectiveEndDate"] = nextDate.format(
              endFormat
            );
          }
        } else if (this.alldata.length == 3) {
          this.alldata[1][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[1][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        }
      }
      if (code === "l") {
        if (this.alldata.length == 1) {
          this.alldata[0][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[0][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        } else if (this.alldata.length == 2) {
          this.alldata[1][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[1][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        } else if (this.alldata.length == 3) {
          this.alldata[2][type + "EffectiveStartDate"] = date.format(
            startFormat
          );
          this.alldata[2][type + "EffectiveEndDate"] = nextDate.format(
            endFormat
          );
        }
      }
    },
    setEndDate(type, date) {
      // date.setDate(date.getDate() + 1);
      let beforDate = new Date(date);
      beforDate.setFullYear(date.getFullYear() - 1);
      // beforDate.setDate(date.getDate() - 1);
      let startFormat = "yyyy-MM-dd HH:mm:ss";
      let endFormat = startFormat;
      if (this.plan[type + "IsEffective"] == "1") {
        if (type == "traffic") {
          startFormat = "yyyy-MM-dd 23:00:00";
          endFormat = startFormat;
        } else {
          startFormat = "yyyy-MM-dd 23:59:59";
          endFormat = "yyyy-MM-dd 24:00:00";
        }
      }
      this.plan[type + "EffectiveStartDate"] = beforDate.format(startFormat);
      this.plan[type + "EffectiveEndDate"] = date.format(endFormat);
    },
    OCRDrive: function() {
      let jsonObject = {
        identifyType: "0", //"0" - 识别行驶证; "1" - 识别身份证
        side: "face" //"face"：正面；"back": 反面;
      };
      /**
       *姓名                  name                String
       *姓名位置图片          namePoints          Base64
       *号牌号码              cardNo              String
       *号牌号码位置图片      cardNoPoints        Base64
       *住址                  address             String
       *住址位置图片          addressPoints       Base64
       *发证日期              issueDate           String
       *发证日期位置图片      issueDatePoints     Base64
       *车辆类型              vehicleType         String
       *车辆类型位置图片      vehicleTypePoints   Base64
       *使用性质              useCharacte         String
       *使用性质位置图片      useCharactePoints   Base64
       *品牌型号              model               String
       *品牌型号位置图片      modelPoints         Base64
       *车辆识别代号          vin                 String
       *车辆识别代号位置图片  vinPoints           Base64
       *车辆识别代号          enginePN            String
       *车辆识别代号位置图片  enginePNPoints      Base64
       *注册日期              registerDate        String
       *注册日期位置图片      registerDatePoints  Base64
       *正反面                side                String
       */
      this.$native.identifyOCR(jsonObject, data => {
        let result = JSON.parse(data);
        if (result.resultCode == "1") {
          // 车主姓名
          this.queryData.carOwner = result.responseBody.name
            ? result.responseBody.name
            : "";
          this.queryData.carOwnerImage = result.responseBody.namePoints
            ? "data:image/png;base64," + result.responseBody.namePoints
            : "";
          // 车牌号
          this.queryData.licenseNo = result.responseBody.cardNo
            ? result.responseBody.cardNo
            : "";
          this.queryData.licenseNoImage = result.responseBody.cardNoPoints
            ? "data:image/png;base64," + result.responseBody.cardNoPoints
            : "";
          //车架号
          this.queryData.frameNo = result.responseBody.vin
            ? result.responseBody.vin
            : "";
          this.queryData.frameNoImage = result.responseBody.vinPoints
            ? "data:image/png;base64," + result.responseBody.vinPoints
            : "";
          //初登日期
          this.queryData.enrollDate = result.responseBody.registerDate
            ? result.responseBody.registerDate + " 00:00:00"
            : "";
          this.queryData.enrollDateImage = result.responseBody
            .registerDatePoints
            ? "data:image/png;base64," + result.responseBody.registerDatePoints
            : "";
          //发动机号
          this.queryData.engineNo = result.responseBody.enginePN
            ? result.responseBody.enginePN
            : "";
          this.queryData.engineNoImage = result.responseBody.enginePNPoints
            ? "data:image/png;base64," + result.responseBody.enginePNPoints
            : "";
          //厂牌型号
          this.queryData.vehicleBrand = result.responseBody.model
            ? result.responseBody.model.replace("牌", "")
            : "";
          this.queryData.vehicleBrandImage = result.responseBody.modelPoints
            ? "data:image/png;base64," + result.responseBody.modelPoints
            : "";
        }
      });
    },
    OCRIdentity() {
      let jsonObject = {
        identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
        side: "face" //"face"：正面；"back": 反面;
      };
      this.$native.identifyOCR(jsonObject, data => {
        let tempData = JSON.parse(data);
        if (tempData.resultCode == "1") {
          const { name, cardNo } = tempData.responseBody;
          this.queryData.identityNo = cardNo;
          this.queryData.cardName = name;
          this.queryData.identityType = "111";
        }
      });
    },
    clickBack() {
      this.$router.back();
    }
  }
};
</script>

<style lang="less" scoped>
/deep/ .van-cell__value {
  flex: 2;
  font-size: 14px;
  color: #333;
  overflow: visible;
  // word-break: keep-all;
}
#person-information-card-property {
  background: white;
  margin-bottom: 100px;
}
.info {
  margin-top: 80px;
  .group {
    height: 48px;
    background: #f7f7f7;
    .group-title {
      font-size: 14px;
      color: #333333;
      font-weight: 600;
      flex-direction: row;
      align-items: center;
      img {
        width: 22px;
        height: 22px;
        margin-right: 8px;
        vertical-align: middle;
      }
    }
    .group-right {
      flex: 1;
      justify-content: flex-end;
      align-items: center;
    }
    .group-right-text {
      margin-right: 10px;
      font-size: 12px;
      font-weight: 400;
      color: #3b8afe;
      float: right;
      line-height: 28px;
    }
    .van-icon {
      margin-top: 7px;
      float: right;
    }
  }
  /deep/ .van-tabs__line {
    background: #3b8afe;
    width: 28px !important;
    height: 6px;
    border-radius: 3px;
  }
  .small {
    font-size: 12px;
    color: #8e8e8e;
  }
  .smallContent {
    font-size: 14px;
    color: #666;
  }
  .van-tabs {
    height: 46px;
    padding-bottom: 12px;
    background-color: #fff;
    border-bottom: 0.5px solid #e5e6e8;
    /deep/ .van-tabs__wrap::after {
      border-width: 0 0;
    }
    /deep/ .van-tab {
      color: #666;
    }
    /deep/ .van-tab--active {
      font-size: 16px;
      color: #333;
    }
  }
}
.item-container {
  position: relative;
  background: white;
  .item-ocr-image {
    float: right;
    vertical-align: top;
    height: 27px;
    margin-top: 13px;
    margin-right: 15px;
    border: 1px solid;
  }
  .item-right-drop {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  .item {
    height: 48px;
    align-items: center;
    line-height: 48px;
    .item-title {
      align-items: center;
      font-size: 14px;
      color: #333333;
      height: 48px;
      line-height: 48px;
      .item-checkbox {
        font-size: 15px;
        .item-checkbox-text {
          font-size: 15px;
          font-weight: 500;
          line-height: 21px;
        }
      }
      .item-title-icon {
        margin-left: 8px;
        align-self: center;
      }
    }
    .van-cell__value {
      .item-right {
        flex: 1;
        display: flex;
        // word-break: keep-all;
        white-space: nowrap;
        align-items: center;
        justify-content: flex-end;
        font-size: 14px;
        height: 48px;
        line-height: 48px;
        .van-checkbox {
          margin-left: 5px;
        }
        .dateSpan {
          height: 48px;
          line-height: 48px;
          font-size: 12px;
          display: inline-block;
          margin: 0 2px;
          color: #666;
        }
        .item-right-input {
          flex: 1;
          text-align: right;
          border: 0;
          background: transparent;
          width: 100%;
          line-height: 1.5;
        }
        img {
          width: 14px;
          vertical-align: top;
          margin-left: 5px;
        }
        .item-right-premium {
          margin-right: 12px;
          color: orange;
        }
      }
    }
  }
}
.drop-item-placeholder {
  color: #ababab;
}
.item-right-arrow {
  margin-left: 8px;
}
.item-placeholder {
  color: #ababab;
}
.drop-item {
  pointer-events: none;
}
.item-right-button {
  align-items: center;
  pointer-events: auto;
}
.item-right-button-text {
  color: #3b8afe;
  font-size: 12px;
  margin: 0 8px;
}
.item-container:not(:last-child)::after {
  position: absolute;
  box-sizing: border-box;
  content: " ";
  pointer-events: none;
  right: 0;
  bottom: 0;
  left: 16px;
  border-bottom: 1px solid #ebedf0;
  -webkit-transform: scaleY(0.5);
  transform: scaleY(0.5);
}
.signature-submit-button {
  width: 345px;
  height: 46px;
  background: #3b8afe;
  border-radius: 46px;
  margin: 20px 15px;
  font-size: 16px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: white;
  span {
    line-height: 46px;
    height: 100%;
  }
}
.jType-container {
  background-color: #fff;
  padding: 0 0 10px 0;
  .headerTitle {
    padding: 0 15px;
    height: 46px;
    line-height: 46px;
    font-size: 14px;
    background-color: #fff;
    font-weight: 500;
    .img {
      width: 16px;
      height: auto;
      margin-right: 8px;
      margin-top: 14.5px;
      vertical-align: top;
    }
  }
  .item-container {
    margin-bottom: 12px;
  }
  .van-cell__value {
    flex: 1.5;
    overflow: visible;
  }
  .jType-contian {
    padding: 0 15px;
    background-color: #fff;
    .small {
      background-color: #f7f7f7;
      line-height: 1;
      padding: 14px 0 12px;
    }
    .smallContent {
      background-color: #f7f7f7;
      line-height: 1;
      padding-bottom: 12px;
    }
  }
}
.orc-downbox {
  text-align: center;
  padding-top: 10px;
  background-color: #fff;
  .ocr-down {
    font-size: 26px;
    color: #bfbfbf;
  }
  .ocr-down-rotate {
    transform: rotate(180deg);
  }
}
.reinsured-dialog {
  .reinsured-content-tip {
    font-size: 14px;
    color: red;
    padding: 8px 20px;
    background: white;
  }
  .reinsured-content {
    height: 300px;
    overflow-y: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    background: #f7f7f7;
    div {
      display: flex;
    }
    .card {
      display: flex;
      flex-direction: column;
      margin: 10px 0;
      background: white;
      padding: 10px 20px;
      .cell {
        position: relative;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        font-size: 14px;
        min-height: 32px;
        padding-bottom: 6px;
        margin-bottom: 6px;
        max-height: 1024px;
        overflow: hidden;
        div:first-child {
          color: #454545;
        }
        div:last-child {
          color: #7a7a7a;
          word-wrap: wrap;
          word-break: break-all;
          flex-wrap: wrap;
          max-width: 160px;
          flex: 1 0 100%;
        }
      }
      .cell:not(:last-child)::after {
        position: absolute;
        box-sizing: border-box;
        content: " ";
        pointer-events: none;
        right: 0;
        bottom: 0;
        left: 0;
        border-bottom: 1px solid #ebedf0;
        -webkit-transform: scaleY(0.5);
        transform: scaleY(0.5);
      }
      .cell-title {
        color: black;
        font-weight: 500;
      }
      .long-info {
        height: auto;
        word-wrap: wrap;
        word-break: break-all;
        div:nth-child(2) {
          max-width: 200px;
        }
      }
    }
  }
}
</style>
