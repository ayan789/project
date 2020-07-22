<!--
 * @Description: 保障计划关系人信息
 * @Date: 2020-05-18 14:14:53
 * @Author: zhangminjie
 * @LastEditTime: 2020-06-28 21:24:14
 * @FilePath: \nonCarCongfig\src\views\nonCar\autoConfig\common\safePlan_personInfo.vue
-->
<template>
  <div
    class="safePlan_personInfoPlan"
    id="safePlan_personInfoPlan"
  >
    <div class="personInfo">
      <form-create
        :rule="rule"
        v-model="fApi"
        :option="options"
      />
      <van-cell-group v-show="!isSharePage">
        <van-cell
          class="minTitle"
          title="渠道信息"
        />
        <van-cell title="是否代理">
          <div solt="default">
            <button
              :disabled="isDisabled"
              :class="[isProxy ? 'blue' : 'gray']"
              @click="changeIsProxy('1')"
            >
              是
            </button>
            <button
              :disabled="isDisabled"
              :class="[!isProxy ? 'blue' : 'gray']"
              @click="changeIsProxy('0')"
            >
              否
            </button>
          </div>
        </van-cell>
        <van-cell
          v-if="isProxy && isDisabled"
          title="代理人"
          :value="channelInfo.agentName"
          is-link
        />
        <van-cell
          v-if="isProxy && !isDisabled"
          title="代理人"
          :value="channelInfo.agentName"
          is-link
          @click="toVehicleChannel"
        />
        <van-cell
          title="直销/直团渠道"
          :value="
            channelInfo.businessNatureName == '请选择'
              ? ''
              : channelInfo.businessNatureName
          "
          is-link
          v-if="!isProxy"
          @click="toVehicleChannel"
        />
      </van-cell-group>
    </div>
  </div>
</template>
<script>
import { mapMutations } from "vuex";
import {
  getDefaultChannel,
  getDefaultAgent,
  agentQuery
} from "@src/http/module/vehicle";
import Config from "@src/common/json/newCoreDataDictionary";
import Vue from "vue";
import { Stepper } from "vant";
// import {
//   FormModel as AFormModel,
//   Button as AButton,
//   Col as ACol
// } from "ant-design-vue";
let channelInfoParam = {
  isProxy: false,
  businessNatureCode: "",
  businessNatureName: "",
  agentCode: "",
  agentName: "",
  agreementNo: "",
  agreementName: "",
  agentAgreementCode: "",
  agentAgreementName: "",
  businessNature2Name: "",
  handler2Code: "",
  handler2Name: "",
  businessCode: "",
  businessName: "",
  projectCode: "",
  projectName: "",
  thirdBusinessInfo: "",
  id: "",
  isDefault: "",
  mobile: "",
  operator: "",
  orgCode: "",
  practisingCertificateNo: "",
  salesCodeName: "",
  salesIdNo: "",
  salesName: "",
  businessSourceName: "",
  inputDate: "",
  addressName: "",
  businessNature: "",
  comCode: "",
  permitNo: "",
  phoneNumber: "",
  riskCode: ""
};
export default {
  components: {
    // [AFormModel.name]: AFormModel,
    // [AButton.name]: AButton,
    // [ACol.name]: ACol
    [Stepper.name]: Stepper
  },
  props: {
    personInfoForm: Array,
    backReasoningFactor: Array,
    riskCode: String,
    fromRoute: Object,
    isSharePage: String
  },

  data () {
    return {
      channelInfo: channelInfoParam,
      isProxy: false,
      isDisabled: false,
      productInfo: {
        riskCode: "",
        isAgent: "",
        businessNatureName: "", //直销
        businessNatureCode: "", //转换
        formulaSize: 1, //保险份数
        agentInfo: {
          agentCode: "",
          agentName: "",
          agreementNo: "",
          agreementName: "",
          businessNature: "",
          businessNature2: "",
          handler2Code: "",
          handler2Name: ""
        }
      },
      btnChecked: true, //社保
      isSheBao: "", // 是否有社保
      fApi: {},
      options: {
        form: {
          hideRequiredMark: false,
          layout: "horizontal",
          labelAlign: "right",
          labelCol: {
            span: 2
          },
          wrapperCol: {
            span: 22
          },
          colon: undefined,
          validateOnRuleChange: true
        },
        onSubmit: formData => {
          alert(JSON.stringify(formData));
        },
        submitBtn: false,
        resetBtn: false
      },
      agentInfo: {
        agentCode: "",
        agentName: "",
        agreementName: "",
        agreementNo: "",
        businessNature: "",
        businessNature2: "",
        handler2Code: "",
        handler2Name: ""
      },
      rule: [],
      formulaList: [],
      safeguardPlanObj: {},
      ageFrom: {},
      ageArr: [],
      iteratNum: 0,
      iteration: {
        num: 0
      },
      personInfoArr: []
    };
  },
  watch: {
    personInfoForm: {
      handler () {
        if (this.personInfoForm.length > 0) {
          this.personInfoArr = this.personInfoForm;
          this.iteration = {
            num: 0
          };
          this.ageFrom = {};
          this.renderFrom();
        }
      },
      deep: true
    },
    frRoute: {
      handler () {
        return this.fromRoute;
      },
      deep: true
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.productInfo.isAgent = this.$store.state.config.productInfo.isAgent;
      // console.log(196, this.productInfo.isAgent);
      if (this.$isAgent()) {
        this.isDisabled = true;
        this.isProxy = this.$isAgent();
        channelInfoParam = localStorage.getItem("agentInfo")
          ? JSON.parse(localStorage.getItem("agentInfo"))
          : {};
        this.channelInfo = channelInfoParam;
      } else {
        this.isDisabled = false;
        this.isProxy = false;
        this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
        this.isProxy = this.productInfo.isAgent == "1" ? true : false;
      }

      let productInfo = this.$store.state.config.productInfo;
      for (let k in productInfo) {
        this.productInfo[k] = productInfo[k];
      }
      this.$root.$once("channelChange", channel => {
        //TODO suncao取到渠道信息进行赋值,渲染不正确
        this.channelInfo = channel;
        channelInfoParam = channel;
        // console.log(499, this.channelInfo);
        channelInfoParam.riskCode = this.riskCode;
        this.isProxy = this.channelInfo.isProxy == "1" ? true : false;
        this.channelInfo = channelInfoParam;
        // this.setChannelInfo();
      });
      //初始是否代理：否
      if (this.isProxy) {
        this.changeIsProxy("1");
      } else {
        this.changeIsProxy("0");
      }
    });
  },
  destroyed () {
    this.channelInfo.isProxy = "";
    this.channelInfo.businessNatureName = "";
    this.channelInfo.businessNatureCode = "";
  },

  methods: {
    // 渲染form表单
    renderFrom (activeEdition) {
      if (activeEdition) {
        this.productInfo.planId = activeEdition;
      }
      if (
        !this.$isNull(this.fromRoute.name) &&
        (this.fromRoute.name.indexOf("vehicle") >= 0 ||
          this.fromRoute.name.indexOf("clause") >= 0)
      ) {
        this.rule.splice(0, this.rule.length);
        this.rule = this.$store.state.config.detailData.formData;
        for (let i = 0, len = this.rule.length; i < len; i++) {
          let obj = this.rule[i];
          if (obj.children && obj.children.length > 0) {
            for (let n = 0; n < obj.children.length; n++) {
              obj.children[n].on = {
                click: () => {
                  if (obj.field.indexOf("insuredIsHaveShebao") >= 0) {
                    this.changeShebao(n, obj.field, obj.backReasoning);
                  }
                  if (obj.field.indexOf("channelIsProxy") >= 0) {
                    this.changeProxyStyle(n, obj.field);
                  }
                }
              };
            }
          }
          if (obj.options && obj.options.length > 0) {
            obj.on = {
              change: value => {
                this.changeSelect(obj.field, value, obj.backReasoning);
              }
            };
          }
        }

        this.fApi.reload(this.rule);
        return;
      }
      this.personInfoArr = this.personInfoForm;
      this.rule.splice(0, this.rule.length);
      let _rule = [];
      let trialArr = [];
      let array = [];
      for (let i = 0, len = this.personInfoForm.length; i < len; i++) {
        let {
          bizId,
          planId,
          id,
          code: field,
          name,
          groupId,
          planElementList
        } = this.personInfoForm[i];
        if (field == "channel") {
          continue;
        }
        _rule.push({
          bizId: bizId,
          planId: planId,
          id: id,
          groupId,
          type: "label",
          field: field,
          title: name,
          className: "formTitle"
        });
        if (field == "insured") {
          let len = this.iteratNum == 0 ? 1 : this.iteratNum;

          for (let s = len; s > 0; s--) {
            this.iteration.num++;
            for (let j = 0, leng = planElementList.length; j < leng; j++) {
              let {
                controlType,
                bizId,
                planId,
                id,
                code,
                name,
                trialFlag,
                defaultValue,
                planElementOptionList,
                arrayIndex,
                submitName,
                trialSort,
                sort,
                groupId,
                elementType
              } = this.personInfoForm[i].planElementList[j];
              for (let k = 0; k < this.backReasoningFactor.length; k++) {
                if (
                  this.backReasoningFactor[k].hasOwnProperty("factor") &&
                  code == this.backReasoningFactor[k].factor
                ) {
                  this.personInfoForm[i].planElementList[j].backReasoning = "1";
                }
              }
              if (controlType == "select") {
                if (code == "insuredAge") {
                  let obj1 = {
                    bizId: bizId,
                    planId: planId,
                    id: id,
                    groupId,
                    className: "from_field_select",
                    type: "select",
                    field: this.iteration.num + "_" + code,
                    title: this.iteration.num + " " + name,
                    trialFlag,
                    arrayIndex,
                    submitName,
                    trialSort,
                    elementType,
                    defaultValue,
                    value: defaultValue,
                    index: this.iteration.num,
                    sort,
                    backReasoning: planElementList[j].backReasoning,
                    col: {
                      span: 24
                    },
                    options: [],
                    props: {
                      placement: "bottom"
                    },
                    on: {
                      change: (value, option) => {
                        this.changeSelect(
                          obj1.field,
                          value,
                          obj1.backReasoning
                        );
                        // alert(`change!![${this.fApi.getValue(code)}]`);
                      }
                    }
                  };

                  for (
                    let k = 0, length = planElementOptionList.length;
                    k < length;
                    k++
                  ) {
                    let { value, label, selected } = planElementOptionList[k];
                    obj1.options.push({
                      value: value,
                      label: label,
                      selected,
                      disabled: false
                    });
                  }
                  if (s == 1) {
                    this.iteration[code] = obj1;
                  }
                  if (len == 1) {
                    obj1.title = name;
                    this.iteration[code] = obj1;
                  }
                  array.push(obj1);
                } else {
                  let obj = {
                    bizId: bizId,
                    planId: planId,
                    id: id,
                    className: "from_field_select",
                    type: "select",
                    field: this.iteration.num + "_" + code,
                    title: this.iteration.num + " " + name,
                    trialFlag,
                    arrayIndex,
                    submitName,
                    trialSort,
                    index: this.iteration.num,
                    elementType,
                    value: defaultValue,
                    backReasoning: planElementList[j].backReasoning,
                    col: {
                      span: 24
                    },
                    options: [],
                    props: {
                      placement: "bottom"
                    },
                    on: {
                      change: (value, option) => {
                        this.changeSelect(obj.field, value, obj.backReasoning);
                        // alert(`change!![${this.fApi.getValue(code)}]`);
                      }
                    }
                  };
                  for (
                    let k = 0, length = planElementOptionList.length;
                    k < length;
                    k++
                  ) {
                    let { value, label, selected } = planElementOptionList[k];
                    obj.options.push({
                      value: value,
                      label: label,
                      selected,
                      disabled: false
                    });
                  }
                  if (s == 1) {
                    this.iteration[code] = obj;
                  }
                  if (len == 1) {
                    obj.title = name;
                    this.iteration[code] = obj;
                  }
                  array.push(obj);
                  this.$store.dispatch(
                    "config/updateSecurityNum",
                    defaultValue
                  );
                  this.iteratNum = !this.$isNull(defaultValue)
                    ? parseInt(defaultValue)
                    : 1;
                }
              }
              if (controlType == "checkBox") {
                let obj = {
                  bizId: bizId,
                  planId: planId,
                  id: id,
                  groupId,
                  className: "from_field_col",
                  type: "a-col",
                  value: defaultValue,
                  defaultValue,
                  field: this.iteration.num + "_" + code,
                  title: this.iteration.num + " " + name,
                  trialFlag,
                  arrayIndex,
                  submitName,
                  trialSort,
                  index: this.iteration.num,
                  sort,
                  elementType,
                  children: [],
                  backReasoning: planElementList[j].backReasoning
                };
                // if (code == "insuredIsHaveShebao") {
                //   this.$store.dispatch("config/updateSheBaoType", defaultValue);
                // }
                let arr = [];
                if (planElementOptionList.length == 1) {
                  arr = [
                    {
                      className:
                        defaultValue == planElementOptionList[0].value
                          ? "form_field_btn_active"
                          : "form_field_btn_text",
                      value: planElementOptionList[0].value,
                      type: "a-button",
                      id: 0,
                      field:
                        this.iteration.num +
                        "_" +
                        planElementOptionList[0].label,
                      selected: planElementOptionList[0].selected,
                      children: [planElementOptionList[0].label],
                      backReasoning: this.personInfoForm[i].planElementList[j]
                        .backReasoning,
                      on: {
                        click: () => {
                          this.changeShebao(
                            0,
                            obj.field,
                            this.personInfoForm[i].planElementList[j]
                              .backReasoning
                          );
                        }
                      }
                    }
                  ];
                }
                if (planElementOptionList.length == 2) {
                  arr = [
                    {
                      className:
                        defaultValue == planElementOptionList[0].value
                          ? "form_field_btn_active"
                          : "form_field_btn_text",
                      value: planElementOptionList[0].value,
                      type: "a-button",
                      id: 0,
                      field:
                        this.iteration.num +
                        "_" +
                        planElementOptionList[0].label,
                      selected: planElementOptionList[0].selected,
                      children: [planElementOptionList[0].label],
                      backReasoning: this.personInfoForm[i].planElementList[j]
                        .backReasoning,
                      on: {
                        click: () => {
                          this.changeShebao(
                            0,
                            obj.field,
                            this.personInfoForm[i].planElementList[j]
                              .backReasoning
                          );
                        }
                      }
                    },
                    {
                      id: 1,
                      className:
                        defaultValue == planElementOptionList[1].value
                          ? "form_field_btn_active"
                          : "form_field_btn_text",
                      value: planElementOptionList[1].value,
                      type: "a-button",
                      field:
                        this.iteration.num +
                        "_" +
                        planElementOptionList[1].label,
                      selected: planElementOptionList[1].selected,
                      children: [planElementOptionList[1].label],
                      backReasoning: this.personInfoForm[i].planElementList[j]
                        .backReasoning,
                      on: {
                        click: () => {
                          this.changeShebao(
                            1,
                            obj.field,
                            this.personInfoForm[i].planElementList[j]
                              .backReasoning
                          );
                        }
                      }
                    }
                  ];
                }
                if (s == 1) {
                  this.iteration[code] = obj;
                }
                if (len == 1) {
                  obj.title = name;
                  this.iteration[code] = obj;
                }
                obj.children.push(...arr);
                // _rule.push(obj);
                array.push(obj);
              }
              if (controlType == "input") {
                let obj = {
                  bizId: bizId,
                  planId: planId,
                  id: id,
                  groupId,
                  className: "from_field_input",
                  type: "input",
                  value: defaultValue,
                  defaultValue,
                  field: this.iteration.num + "_" + code,
                  title: this.iteration.num + " " + name,
                  trialFlag,
                  sort,
                  arrayIndex,
                  submitName,
                  index: this.iteration.num,
                  trialSort,
                  elementType,
                  backReasoning: planElementList[j].backReasoning
                };
                if (s == 1) {
                  this.iteration[code] = obj;
                }
                if (len == 1) {
                  obj.title = name;
                  this.iteration[code] = obj;
                }
                array.push(obj);
              }
            }
          }
          _rule.push(...array);
        } else if (field == "channel") {
          continue;
        } else {
          for (let j = 0, leng = planElementList.length; j < leng; j++) {
            let {
              controlType,
              bizId,
              planId,
              id,
              code,
              name,
              trialFlag,
              defaultValue,
              planElementOptionList,
              arrayIndex,
              submitName,
              trialSort,
              sort,
              groupId,
              elementType
            } = this.personInfoForm[i].planElementList[j];
            if (trialFlag == 1) {
              trialArr.push(code);
            }
            for (let k = 0; k < this.backReasoningFactor.length; k++) {
              if (
                this.backReasoningFactor[k].hasOwnProperty("factor") &&
                code == this.backReasoningFactor[k].factor
              ) {
                this.personInfoForm[i].planElementList[j].backReasoning = "1";
              }
            }
            if (controlType == "select") {
              if (code.indexOf("commSecurityNum") >= 0) {
                let obj = {
                  bizId: bizId,
                  planId: planId,
                  id: id,
                  groupId,
                  className: "from_field_select",
                  type: "select",
                  field: code,
                  title: name,
                  trialFlag,
                  arrayIndex,
                  submitName,
                  trialSort,
                  elementType,
                  defaultValue,
                  sort,
                  value: defaultValue,
                  backReasoning: planElementList[j].backReasoning,
                  col: {
                    span: 24
                  },
                  options: [],
                  props: {
                    placement: "bottom"
                  },
                  on: {
                    change: (value, option) => {
                      this.changeSelect(obj.field, value, obj.backReasoning);
                      // alert(`change!![${this.fApi.getValue(code)}]`);
                    }
                  }
                };
                for (
                  let k = 0, length = planElementOptionList.length;
                  k < length;
                  k++
                ) {
                  let { value, label, selected } = planElementOptionList[k];
                  obj.options.push({
                    value: value,
                    label: label,
                    selected,
                    disabled: false
                  });
                }
                _rule.push(obj);
                this.$store.dispatch("config/updateSecurityNum", defaultValue);
                this.iteratNum = !this.$isNull(defaultValue)
                  ? parseInt(defaultValue)
                  : 1;
              }
              if (code.indexOf("commSecurityPeriod") >= 0) {
                let obj = {
                  bizId: bizId,
                  planId: planId,
                  id: id,
                  groupId,
                  className: "from_field_select",
                  type: "select",
                  field: code,
                  title: name,
                  trialFlag,
                  arrayIndex,
                  submitName,
                  trialSort,
                  elementType,
                  defaultValue,
                  sort,
                  value: defaultValue,
                  backReasoning: planElementList[j].backReasoning,
                  col: {
                    span: 24
                  },
                  options: [],
                  props: {
                    placement: "bottom"
                  },
                  on: {
                    change: (value, option) => {
                      this.changeSelect(obj.field, value, obj.backReasoning);
                      // alert(`change!![${this.fApi.getValue(code)}]`);
                    }
                  }
                };
                for (
                  let k = 0, length = planElementOptionList.length;
                  k < length;
                  k++
                ) {
                  let { value, label, selected } = planElementOptionList[k];
                  obj.options.push({
                    value: value,
                    label: label,
                    selected,
                    disabled: false
                  });
                }
                _rule.push(obj);
                this.$store.dispatch("config/updateDetailData", {
                  commSecurityPeriod: defaultValue
                });
              }
            }
            if (controlType == "input") {
              let obj = {
                bizId: bizId,
                planId: planId,
                id: id,
                groupId,
                className: "from_field_input",
                type: "input",
                value: defaultValue,
                defaultValue,
                field: code,
                title: name,
                trialFlag,
                sort,
                arrayIndex,
                submitName,
                index: 0,
                trialSort,
                elementType,
                backReasoning: planElementList[j].backReasoning
              };
              _rule.push(obj);
            }
            if (controlType == "checkBox") {
              let obj = {
                bizId: bizId,
                planId: planId,
                groupId,
                id: id,
                className: "from_field_col",
                title: name,
                type: "a-col",
                value: defaultValue,
                field: code,
                defaultValue,
                trialFlag,
                arrayIndex,
                submitName,
                sort,
                trialSort,
                elementType,
                backReasoning: planElementList[j].backReasoning,
                children: []
              };
              if (code == "insuredIsHaveShebao") {
                this.$store.dispatch("config/updateSheBaoType", defaultValue);
              }
              let arr = [];
              if (planElementOptionList.length == 1) {
                arr = [
                  {
                    className:
                      defaultValue == planElementOptionList[0].value
                        ? "form_field_btn_active"
                        : "form_field_btn_text",
                    value: planElementOptionList[0].value,
                    type: "a-button",
                    field: planElementOptionList[0].label,
                    selected: planElementOptionList[0].selected,
                    children: [planElementOptionList[0].label],
                    on: {
                      click: () => {
                        // this.fApi.updateRule(planElementOptionList[0].label, {
                        //   className: "form_field_btn_active",
                        //   selected: 1
                        // });

                        if (code == "channelIsProxy") {
                          // this.isProxy =
                          //   planElementOptionList[0].value == "Y" ? true : false;
                          this.changeProxyStyle(0, obj.field);
                        }
                      }
                    }
                  }
                ];
              }
              if (planElementOptionList.length == 2) {
                arr = [
                  {
                    className:
                      defaultValue == planElementOptionList[0].value
                        ? "form_field_btn_active"
                        : "form_field_btn_text",
                    value: planElementOptionList[0].value,
                    type: "a-button",
                    field: planElementOptionList[0].label,
                    selected: planElementOptionList[0].selected,
                    children: [planElementOptionList[0].label],
                    on: {
                      click: () => {
                        // this.fApi.updateRule(planElementOptionList[1].label, {
                        //   className: "form_field_btn_text",
                        //   selected: 0
                        // });
                        // this.fApi.updateRule(planElementOptionList[0].label, {
                        //   className: "form_field_btn_active",
                        //   selected: 1
                        // });

                        if (code == "channelIsProxy") {
                          // this.isProxy =
                          //   planElementOptionList[0].value == "Y" ? true : false;
                          this.changeProxyStyle(0, obj.field);
                        }
                      }
                    }
                  },
                  {
                    className:
                      defaultValue == planElementOptionList[1].value
                        ? "form_field_btn_active"
                        : "form_field_btn_text",
                    value: planElementOptionList[1].value,
                    type: "a-button",
                    field: planElementOptionList[1].label,
                    selected: planElementOptionList[1].selected,
                    children: [planElementOptionList[1].label],
                    on: {
                      click: () => {
                        // this.fApi.updateRule(planElementOptionList[0].label, {
                        //   className: "form_field_btn_text",
                        //   selected: 0
                        // });
                        // this.fApi.updateRule(planElementOptionList[1].label, {
                        //   className: "form_field_btn_active",
                        //   selected: 1
                        // });
                        if (code == "channelIsProxy") {
                          this.changeProxyStyle(1, obj.field);
                        }
                      }
                    }
                  }
                ];
              }
              obj.children.push(...arr);
              _rule.push(obj);
            }
            if (controlType == "inputNumber") {
              let { minValue, maxValue } = this.personInfoForm[
                i
              ].planElementList[j];
              let obj = {
                bizId: 1,
                planId: 1,
                id: 1,
                type: "a-row",
                field: code,
                title: name,
                className: "formRow",
                value: defaultValue ? defaultValue : "1",
                trialFlag,
                arrayIndex,
                groupId,
                submitName,
                trialSort,
                sort,
                elementType,
                defaultValue,
                backReasoning: planElementList[j].backReasoning,
                props: {
                  span: 24,
                  type: "flex",
                  align: "middle",
                  justify: "space-around"
                },
                children: [
                  {
                    type: "a-icon",
                    props: {
                      type: "minus"
                    },
                    field: "minus",
                    title: "",
                    on: {
                      click: () => {
                        this.numBlur(
                          "minus",
                          obj.field,
                          obj.children[1].value,
                          maxValue,
                          minValue,
                          obj.backReasoning
                        );
                      }
                    }
                  },

                  {
                    field: code + "1",
                    value: defaultValue ? defaultValue : "1",
                    trialFlag,
                    arrayIndex,
                    groupId,
                    submitName,
                    trialSort,
                    sort,
                    elementType,
                    defaultValue,
                    type: "input",
                    props: {
                      field: code + "1",
                      maxLength: maxValue,
                      minLength: minValue,
                      defaultValue: !this.$isNull(defaultValue)
                        ? parseInt(defaultValue)
                        : 1
                    },
                    on: {
                      blur: () => {
                        this.numBlur(
                          "",
                          obj.field,
                          obj.children[1].value,
                          maxValue,
                          minValue,
                          obj.backReasoning
                        );
                      }
                    }
                  },

                  {
                    type: "a-icon",
                    props: {
                      type: "plus"
                    },
                    field: "plus",
                    title: "",
                    on: {
                      click: () => {
                        this.numBlur(
                          "plus",
                          obj.field,
                          obj.children[1].value,
                          maxValue,
                          minValue,
                          obj.backReasoning
                        );
                      }
                    }
                  }
                ]
              };
              this.$store.dispatch(
                "config/updateCommNumOfCopies",
                defaultValue ? parseInt(defaultValue) : 1
              );
              _rule.push(obj);
            }
          }
        }
      }
      this.rule.push(..._rule);
      for (let i = 0; i < this.rule.length; i++) {
        let { field, index, sort, value, type, backReasoning } = this.rule[i];
        if (field.indexOf("commSecurityNum") >= 0) {
          this.changeSelect(field, value, true, backReasoning);
          return;
        }

        if (this.rule[i].trialFlag == 1) {
          let obj = {
            field,
            index,
            sort,
            value,
            children: []
          };
          if (type == "select" && this.rule[i].options.length > 0) {
            let optionsArr = [];
            this.rule[i].options.forEach(item => {
              optionsArr.push(item.value);
            });
            obj.children = [...optionsArr];
          }
          trialArr.push(obj);
        }
      }
      let insuredArr = [];
      let object = {};
      object.insuredArr = [];
      object.type = "";
      for (let i = 0; i < array.length; i++) {
        let index =
          array[i].field.indexOf("_") >= 0 ? array[i].field.split("_")[0] : 0;
        let data = {
          field: array[i].field,
          value: array[i].defaultValue,
          index: index
        };
        insuredArr.push(data);
      }
      object.insuredArr = insuredArr;
      let trialArray = this.getRateKey(trialArr);
      this.$store.dispatch("config/updateInsuredObj", object);
      this.$store.dispatch("config/updateTrialArr", trialArray);
      this.$store.dispatch("config/updateFormData", this.rule);
    },
    getRateKey (trialArr) {
      var listArr = [];
      trialArr.forEach(function (el) {
        for (var i = 0; i < listArr.length; i++) {
          if (!el.index) {
            el.index = i;
          }
          // 对比相同的字段key，相同放入对应的数组
          if (listArr[i].index == el.index) {
            listArr[i].listInfo.push({
              field: el.field,
              index: el.index,
              children: el.children
            });
            return;
          }
        }

        // 第一次对比没有参照，放入参照
        listArr.push({
          index: el.index,
          listInfo: [
            {
              field: el.field,
              index: el.index,
              children: el.children
            }
          ]
        });
      });
      return listArr;
    },
    // 跳到代理渠道页面
    toVehicleChannel () {
      let query = {
        riskCode: this.riskCode
      };
      this.$root.$once("channelChange", channel => {
        this.channelInfo = channel;
      });
      this.setChannel(
        Object.assign(this.channelInfo, { isProxy: this.isProxy ? "1" : "0" })
      );
      this.productInfo.isAgent = this.isProxy ? "1" : "0";
      this.$store.dispatch("config/updateProductInfo", this.productInfo);
      this.$store.dispatch("config/updateFormData", this.rule);
      this.fApi.destroy();
      this.$router.push({
        name: "vehicleChannel",
        query: query
      });
    },
    changeProxyStyle (id, code) {
      let obj = this.fApi.getRule(code);
      let { children } = obj;
      for (let i = 0; i < children.length; i++) {
        children[i].className = "form_field_btn_text";
        children[i].selected = 0;
      }
      this.fApi.updateRule(obj.children[id].field, {
        className: "form_field_btn_active",
        selected: 1
      });
      let value = this.fApi.getValue(obj.children[id].field);
      let formData = this.$store.state.config.detailData.formData;
      let obj1 = formData.find(item => {
        return item.field == code;
      });
      obj1.value = value;
      this.changeIsProxy(this.fApi.getValue(obj.children[id].field));
    },
    // 是否代理
    changeIsProxy (type) {
      // console.log(995, type);
      if (type == "1") {
        this.isProxy = true;
        this.productInfo.isAgent = "1";
        this.channelInfo.agentName = "";
        this.channelInfo.agentCode = "";
        channelInfoParam.isProxy = "1";
        this.channelInfo.isProxy = "1";
        this.getDefaultChannel();
      } else {
        this.isProxy = false;
        this.productInfo.isAgent = "0";
        this.channelInfo.agentName = "";
        this.channelInfo.businessNatureName = "";
        this.channelInfo.businessNatureCode = "";
        channelInfoParam.isProxy = "0";
        this.channelInfo.isProxy = "0";
        this.getDefaultChannel();
      }
    },
    ...mapMutations("channel", ["setChannel"]),
    // 获取默认渠道
    getDefaultChannel () {
      getDefaultChannel({ isProxy: this.channelInfo.isProxy })
        .then(
          res => {
            if (res.code === "1") {
              if (res.data.isProxy == "1") {
                if (this.$isAgent()) return;
                if (res.data.isProxy == "1") {
                  this.getDefaultAgent();
                }
              } else {
                if (this.$isAgent()) return;
                if (this.channelInfo.isProxy == "1") {
                  this.getDefaultAgent();
                } else {
                  this.channelInfo.channelInfo = "0";
                  this.channelInfo.businessNature =
                    res.data.businessNatureCode || "0101";
                  this.channelInfo.businessNatureCode =
                    res.data.businessNatureCode || "0101";
                  this.channelInfo.businessNatureName =
                    res.data.businessNatureName || "业务员销售（直销）";
                }
              }
            } else {
              this.$dialog.alert({ message: res.msg });
            }
          },
          error => { }
        )
        .finally(() => {
          console.warn("代理人用户", this.$isAgent());
          if (this.$isAgent()) {
            const userInfo = this.localStorage.get("userInfo", {});
            this.channelInfo.isProxy = "1";
            this.channelInfo.agentCode = userInfo.agentCode;
            this.channelInfo.agentName = userInfo.agentName;
            this.channelInfo.businessNatureCode = "2401";
            this.channelInfo.agentAgreementCode = "";
            this.channelInfo.agentAgreementName = "";
            this.channelInfo.handlerCode = "";
            this.channelInfo.handlerName = "";
            this.agentQuery(true);
          }
          // this.setChannelInfo();
        });
    },
    getDefaultAgent () {
      getDefaultAgent({}).then(res => {
        this.agentInfo.agentName = res.data.agentName ? res.data.agentName : "";
        this.agentInfo.agentCode = res.data.agentCode ? res.data.agentCode : "";
        this.agentInfo.businessNature = res.data.businessSource
          ? res.data.businessSource
          : "";
        this.agentInfo.businessNatureCode = res.data.businessSource
          ? res.data.businessSource
          : "";
        this.agentInfo.agentAgreementCode = res.data.agentAgreementCode
          ? res.data.agentAgreementCode
          : "";
        this.agentInfo.agentAgreementName = res.data.agentAgreementName
          ? res.data.agentAgreementName
          : "";
        this.channelInfo.agentName = res.data.agentName
          ? res.data.agentName
          : "";
        this.channelInfo.agentCode = res.data.agentCode
          ? res.data.agentCode
          : "";
        this.channelInfo.businessNature = res.data.businessSource
          ? res.data.businessSource
          : "";
        this.channelInfo.businessNatureCode = res.data.businessSource
          ? res.data.businessSource
          : "";
        this.channelInfo.agentAgreementCode = res.data.agentAgreementCode
          ? res.data.agentAgreementCode
          : "";
        this.channelInfo.agentAgreementName = res.data.agentAgreementName
          ? res.data.agentAgreementName
          : "";
        for (const key in res.data) {
          if (res.data.hasOwnProperty(key)) {
            const value = res.data[key];
            if (this.agentInfo.hasOwnProperty(key) && value) {
              this.agentInfo[key] = value;
            }
          }
        }
        this.agentQuery(false);
      });
    },
    agentQuery (flag) {
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
      // console.log(777, userInfo);
      //1 不代理 2 代理
      let requestType = this.agentInfo.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.agentInfo.isProxy == "0"
          ? userInfo.userCode
          : this.agentInfo.agentCode;
      let businessSource = this.agentInfo.businessNatureCode;
      this.agentInfo.handler2Code = userInfo.handlerCode;
      this.agentInfo.handler2Name = userInfo.handlerName;
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: channelInfoParam.riskCode,
        businessSource,
        agentCode,
        agreementNo: this.agentInfo.agentAgreementCode,
        agreementName: this.agentInfo.agentAgreementName
      };
      if (flag) {
        // console.log(800, this.channelInfo);
        params.riskCode = this.channelInfo.riskCode;
        params.businessSource = this.channelInfo.businessNatureCode;
        params.agentCode = this.channelInfo.agentCode;
      } else {
        if (!agentCode || !businessSource) {
          console.warn("无代理人, 无业务来源");
          // this.setChannelInfo();
          return;
          // return this.getDefaultAgent();
        }
      }

      agentQuery(params).then(
        res => {
          if (res.code === "1" && res.data) {
            let { salesMainMsgDto = {} } = res.data;
            this.requestData = salesMainMsgDto;
            let {
              salesAgentMsgDto = {},
              salesAgreementMsgList = []
            } = salesMainMsgDto;
            let { agentName, businessNature } = salesAgentMsgDto;
            let agreementIsVaild = false;
            if (Array.isArray(salesAgreementMsgList)) {
              salesAgreementMsgList.forEach(item => {
                if (item.agreementNo == this.agentInfo.agentAgreementCode) {
                  agreementIsVaild = true;
                }
              });
            }
            //console.log(this.salesAgreementMsgList);
            this.agentInfo.agentCode = agentCode; //归属机构
            this.agentInfo.agentName = agentName; //归属机构
            this.agentInfo.businessNatureCode =
              businessNature || this.agentInfo.businessNatureCode; //业务来源
            for (const key in salesAgentMsgDto) {
              if (salesAgentMsgDto.hasOwnProperty(key)) {
                const value = salesAgentMsgDto[key];
                if (this.agentInfo.hasOwnProperty(key) && value) {
                  this.agentInfo[key] = value;
                }
              }
            }
            let businessNatures = Object.keys(Config.businessNature).map(
              key => ({
                text: Config.businessNature[key],
                value: key
              })
            );
            businessNatures.push({ text: "业务员销售（直销）", value: "0101" });
            businessNatures.push({ text: "业务员销售（直团）", value: "7101" });
            businessNatures.push({ text: "门店直销", value: "0201" });
            this.agentInfo.businessNatureName = this.$utils.findText(
              businessNatures,
              this.agentInfo.businessNatureCode
            );
            // this.agentInfo.agentAgreementCode = agreementNo; //代理人协议号
            // this.agentInfo.agentAgreementName = agreementName; //代理人协议名称
            if (!agreementIsVaild) {
              //代理人协议无效
              if (salesAgreementMsgList.length) {
                //默认首个有效
                this.agentInfo.agreementNo =
                  salesAgreementMsgList[0].agreementNo;
                this.agentInfo.agreementName =
                  salesAgreementMsgList[0].agreementName;
              } else {
                //无代理协议
                this.agentInfo.agreementNo = "";
                this.agentInfo.agreementName = "";
              }
              // agentInfoParam = this.agentInfo;
              // console.log(this.agentInfo,"ahdjasdashdasjsahd")
              for (let key in this.agentInfo) {
                this.channelInfo[key] = this.agentInfo[key];
              }
              // console.log(869, this.channelInfo,this.agentInfo);
              // this.setChannelInfo();
            }
          } else {
            if (this.agentInfo.isProxy == "1") {
              this.$dialog.alert({ message: res.msg });
            }
          }
        },
        error => { }
      );
    },
    // 为渠道信息赋值
    setChannelInfo () {
      let {
        agentCode,
        agentName,
        agreementNo,
        businessNature,
        businessNature2Code,
        handler2Code,
        handler2Name,
        businessNatureName,
        businessNatureCode,
        agreementName
      } = this.channelInfo;
      this.productInfo.agentInfo.agentCode = agentCode;
      this.productInfo.agentInfo.agentName = agentName;
      this.productInfo.agentInfo.agreementName = agreementName;
      this.productInfo.agentInfo.agreementNo = agreementNo;
      this.productInfo.agentInfo.businessNature = businessNature;
      this.productInfo.agentInfo.businessNature2 = businessNature2Code;
      this.productInfo.agentInfo.handler2Code = handler2Code;
      this.productInfo.agentInfo.handler2Name = handler2Name;
      this.productInfo.businessNatureName =
        businessNatureName == "请选择" ? "" : businessNatureName;
      this.productInfo.businessNatureCode = businessNatureCode;
      this.productInfo.isAgent = this.isProxy ? "1" : "0";

      let obj = JSON.parse(JSON.stringify(this.productInfo));
      this.$store.dispatch("config/updateProductInfo", obj);
    },
    // select方法
    changeSelect (code, value, backReasoning) {
      // let c = code.substring(10, code.length);
      // console.log(917, code, value);
      // 更改被保人年龄做的操作
      if (code.indexOf("insuredAge") >= 0) {
        let index = code.indexOf("_") >= 0 ? code.split("_")[0] : 0;
        let insuredArr = [];
        let object = {};
        object.insuredArr = [];
        object.type = "";
        let data = {
          field: code,
          value: value,
          index: index
        };
        insuredArr.push(data);
        object.insuredArr = insuredArr;
        this.$store.dispatch("config/updateInsuredObj", object);
        // this.$store.dispatch("config/updateFormData", this.rule);
        this.$emit("selectInsuredAge", code, value, backReasoning);
      } else if (code.indexOf("commSecurityNum") >= 0) {
        // console.log(1441, code, value, backReasoning);
        let securityNum = this.$store.state.config.detailData.securityNum;
        securityNum = !this.$isNull(securityNum) ? parseInt(securityNum) : 0;
        value = !this.$isNull(value) ? parseInt(value) : 0;
        this.$store.dispatch("config/updateSecurityNum", value);
        if (value < securityNum) {
          this.changeSecurityNum(
            code,
            securityNum - value,
            false,
            backReasoning
          );
        } else if (value > securityNum) {
          this.changeSecurityNum(
            code,
            value - securityNum,
            true,
            backReasoning
          );
        } else {
          this.changeSecurityNum(code, value, true, backReasoning, "init");
        }
      } else if (code.indexOf("commSecurityPeriod") >= 0) {
        this.$store.dispatch("config/updateDetailData", {
          commSecurityPeriod: value
        });
        this.$emit("selectOther", code, value, backReasoning);
      } else {
        this.$emit("selectOther", code, value, backReasoning);
      }
    },
    // 改变保障人数
    changeSecurityNum (code, value, flag, backReasoning, initFlag) {
      if (initFlag == "init") {
        let o = {};
        let arr = [];
        let obj1 = {};
        for (let i = 0; i < value - 1; i++) {
          this.iteration.num++;
          for (let k in this.iteration) {
            if (k != "num") {
              let obj = {};
              o = JSON.parse(JSON.stringify(this.iteration[k]));
              obj[k] = JSON.parse(JSON.stringify(this.iteration[k]));
              if (this.iteration[k].title.indexOf(" ") >= 0) {
                obj[k].title =
                  this.iteration.num +
                  " " +
                  this.iteration[k].title.split(" ")[1];
              } else {
                obj[k].title =
                  this.iteration.num + " " + this.iteration[k].title;
              }
              if (this.iteration[k].field.indexOf("_") >= 0) {
                obj[k].field =
                  this.iteration.num +
                  "_" +
                  this.iteration[k].field.split("_")[1];
              } else {
                obj[k].field =
                  this.iteration.num + "_" + this.iteration[k].field;
              }
              if (obj[k].children && obj[k].children.length > 0) {
                for (let n = 0; n < obj[k].children.length; n++) {
                  if (obj[k].children[n].field.indexOf("_") >= 0) {
                    obj[k].children[n].field =
                      this.iteration.num +
                      "_" +
                      this.iteration[k].children[n].field.split("_")[1];
                  } else {
                    obj[k].children[n].field =
                      this.iteration.num + "_" + obj[k].children[n].field;
                  }
                  obj[k].children[n].on = {
                    click: () => {
                      this.changeShebao(n, obj[k].field, obj[k].backReasoning);
                    }
                  };
                  obj[k].children[n].className = "form_field_btn_text";
                  if (obj[k].children[n].value == obj[k].value) {
                    obj[k].children[n].className = "form_field_btn_active";
                  }
                }
              }
              if (obj[k].options && obj[k].options.length > 0) {
                obj[k].on = {
                  change: (value, option) => {
                    this.changeSelect(
                      obj[k].field,
                      value,
                      obj[k].backReasoning
                    );
                    // alert(`change!![${this.fApi.getValue(code)}]`);
                  }
                };
              }
              obj[k].value = this.iteration[k].defaultValue;
              obj[k].index = this.iteration.num;
              arr.push(obj[k]);
              obj1[k] = obj[k];
            }
          }
        }
        let insuredArr = [];
        let object = {};
        object.insuredArr = [];
        object.type = "";
        let index = this.rule.findIndex(item => {
          return item.field == o.field;
        });
        for (let i = 0, len = arr.length; i < len; i++) {
          // this.fApi.append(arr[i], o.field, false);

          let index =
            arr[i].field.indexOf("_") >= 0 ? arr[i].field.split("_")[0] : 0;
          let data = {
            field: arr[i].field,
            value: arr[i].defaultValue,
            index: index
          };
          insuredArr.push(data);
        }
        this.rule.splice(index + 1, 0, ...arr);
        object.insuredArr = insuredArr;
        this.$store.dispatch("config/updateInsuredObj", object);

        for (let k in obj1) {
          this.iteration[k] = obj1[k];
        }
        let trialArr = [];
        for (let i = 0; i < this.rule.length; i++) {
          if (this.rule[i].trialFlag == 1) {
            let { field, index, sort, value, type } = this.rule[i];
            let obj = {
              field,
              index,
              sort,
              value,
              children: []
            };
            if (type == "select" && this.rule[i].options.length > 0) {
              let optionsArr = [];
              this.rule[i].options.forEach(item => {
                optionsArr.push(item.value);
              });
              obj.children = [...optionsArr];
            }
            trialArr.push(obj);
          }
        }
        let trialArray = this.getRateKey(trialArr);
        this.$store.dispatch("config/updateTrialArr", trialArray);
        this.$store.dispatch("config/updateFormData", this.rule);
        this.$emit("changeSecurityNum", code, value, backReasoning);
        return;
      }
      if (flag) {
        let o = {};
        let arr = [];
        let obj1 = {};
        for (let i = 0; i < value; i++) {
          this.iteration.num++;
          for (let k in this.iteration) {
            if (k != "num") {
              let obj = {};
              o = JSON.parse(JSON.stringify(this.iteration[k]));
              obj[k] = JSON.parse(JSON.stringify(this.iteration[k]));
              if (this.iteration[k].title.indexOf(" ") >= 0) {
                obj[k].title =
                  this.iteration.num +
                  " " +
                  this.iteration[k].title.split(" ")[1];
              } else {
                obj[k].title =
                  this.iteration.num + " " + this.iteration[k].title;
              }
              if (this.iteration[k].field.indexOf("_") >= 0) {
                obj[k].field =
                  this.iteration.num +
                  "_" +
                  this.iteration[k].field.split("_")[1];
              } else {
                obj[k].field =
                  this.iteration.num + "_" + this.iteration[k].field;
              }
              if (obj[k].children && obj[k].children.length > 0) {
                for (let n = 0; n < obj[k].children.length; n++) {
                  if (obj[k].children[n].field.indexOf("_") >= 0) {
                    obj[k].children[n].field =
                      this.iteration.num +
                      "_" +
                      this.iteration[k].children[n].field.split("_")[1];
                  } else {
                    obj[k].children[n].field =
                      this.iteration.num + "_" + obj[k].children[n].field;
                  }
                  obj[k].children[n].on = {
                    click: () => {
                      this.changeShebao(n, obj[k].field, obj[k].backReasoning);
                    }
                  };
                  obj[k].children[n].className = "form_field_btn_text";
                  if (obj[k].children[n].value == obj[k].value) {
                    obj[k].children[n].className = "form_field_btn_active";
                  }
                }
              }
              if (obj[k].options && obj[k].options.length > 0) {
                obj[k].on = {
                  change: (value, option) => {
                    this.changeSelect(
                      obj[k].field,
                      value,
                      obj[k].backReasoning
                    );
                    // alert(`change!![${this.fApi.getValue(code)}]`);
                  }
                };
              }
              obj[k].value = this.iteration[k].defaultValue;
              obj[k].index = this.iteration.num;
              arr.unshift(obj[k]);
              obj1[k] = obj[k];
            }
          }
        }
        let insuredArr = [];
        let object = {};
        object.insuredArr = [];
        object.type = "";
        for (let i = 0, len = arr.length; i < len; i++) {
          this.fApi.append(arr[i], o.field, false);
          let index =
            arr[i].field.indexOf("_") >= 0 ? arr[i].field.split("_")[0] : 0;
          let data = {
            field: arr[i].field,
            value: arr[i].defaultValue,
            index: index
          };
          insuredArr.push(data);
        }
        object.insuredArr = insuredArr;
        this.$store.dispatch("config/updateInsuredObj", object);

        for (let k in obj1) {
          this.iteration[k] = obj1[k];
        }
      } else {
        let arr = [];
        let obj1 = {};
        for (let i = value; i > 0; i--) {
          for (let k in this.iteration) {
            if (k != "num") {
              let obj = {};
              obj[k] = JSON.parse(JSON.stringify(this.iteration[k]));

              if (this.iteration[k].title.indexOf(" ") >= 0) {
                obj[k].title =
                  this.iteration.num +
                  " " +
                  this.iteration[k].title.split(" ")[1];
              } else {
                obj[k].title =
                  this.iteration.num + " " + this.iteration[k].title;
              }
              if (this.iteration[k].field.indexOf("_") >= 0) {
                obj[k].field =
                  this.iteration.num +
                  "_" +
                  this.iteration[k].field.split("_")[1];
              } else {
                obj[k].field =
                  this.iteration.num + "_" + this.iteration[k].field;
              }
              if (obj[k].children && obj[k].children.length > 0) {
                for (let n = 0; n < obj[k].children.length; n++) {
                  if (obj[k].children[n].field.indexOf("_") >= 0) {
                    obj[k].children[n].field =
                      this.iteration.num +
                      "_" +
                      this.iteration[k].children[n].field.split("_")[1];
                  } else {
                    obj[k].children[n].field =
                      this.iteration.num + "_" + obj[k].children[n].field;
                  }
                  obj[k].children[n].on = {
                    click: () => {
                      this.changeShebao(n, obj[k].field, obj[k].backReasoning);
                    }
                  };
                }
              }
              if (obj[k].options && obj[k].options.length > 0) {
                obj[k].on = {
                  change: (value, option) => {
                    this.changeSelect(
                      obj[k].field,
                      value,
                      obj[k].backReasoning
                    );
                    // alert(`change!![${this.fApi.getValue(code)}]`);
                  }
                };
              }
              obj[k].value = this.iteration[k].defaultValue;
              arr.unshift(obj[k]);
              obj1[k] = obj[k];
            }
          }
          this.iteration.num--;
        }
        let insuredArr = [];
        let object = {};
        object.insuredArr = [];
        object.type = "delete";
        for (let i = 0, len = arr.length; i < len; i++) {
          let index =
            arr[i].field.indexOf("_") >= 0 ? arr[i].field.split("_")[0] : 0;
          let data = {
            field: arr[i].field,
            value: arr[i].defaultValue,
            index: index
          };
          insuredArr.push(data);
          this.fApi.removeField(arr[i].field);
        }
        object.insuredArr = insuredArr;
        this.$store.dispatch("config/updateInsuredObj", object);
        for (let k in obj1) {
          if (this.iteration[k].title.indexOf(" ") >= 0) {
            obj1[k].title =
              this.iteration.num + " " + this.iteration[k].title.split(" ")[1];
          } else {
            obj1[k].title = this.iteration.num + " " + this.iteration[k].title;
          }
          if (this.iteration[k].field.indexOf("_") >= 0) {
            obj1[k].field =
              this.iteration.num + "_" + this.iteration[k].field.split("_")[1];
          } else {
            obj1[k].field = this.iteration.num + "_" + this.iteration[k].field;
          }
          if (obj1[k].children && obj1[k].children.length > 0) {
            for (let n = 0; n < obj1[k].children.length; n++) {
              if (obj1[k].children[n].field.indexOf("_") >= 0) {
                obj1[k].children[n].field =
                  this.iteration.num +
                  "_" +
                  this.iteration[k].children[n].field.split("_")[1];
              } else {
                obj1[k].children[n].field =
                  this.iteration.num + "_" + obj1[k].children[n].field;
              }
              obj1[k].children[n].on = {
                click: () => {
                  this.changeShebao(n, obj1[k].field, obj1[k].backReasoning);
                }
              };
              obj1[k].children[n].className = "form_field_btn_text";
              if (obj1[k].children[n].value == obj1[k].value) {
                obj1[k].children[n].className = "form_field_btn_active";
              }
            }
          }
          if (obj1[k].options && obj1[k].options.length > 0) {
            obj1[k].on = {
              change: (value, option) => {
                this.changeSelect(obj1[k].field, value, obj1[k].backReasoning);
                // alert(`change!![${this.fApi.getValue(code)}]`);
              }
            };
          }
          obj1[k].value = obj1[k].defaultValue;
          obj1[k].index = this.iteration.num;
        }
        for (let k in obj1) {
          this.iteration[k] = obj1[k];
        }
      }
      let trialArr = [];
      for (let i = 0; i < this.rule.length; i++) {
        if (this.rule[i].trialFlag == 1) {
          let { field, index, sort, value, type } = this.rule[i];
          let obj = {
            field,
            index,
            sort,
            value,
            children: []
          };
          if (type == "select" && this.rule[i].options.length > 0) {
            let optionsArr = [];
            this.rule[i].options.forEach(item => {
              optionsArr.push(item.value);
            });
            obj.children = [...optionsArr];
          }
          trialArr.push(obj);
        }
      }
      let trialArray = this.getRateKey(trialArr);
      this.$store.dispatch("config/updateTrialArr", trialArray);
      this.$store.dispatch("config/updateFormData", this.rule);
      this.$emit("changeSecurityNum", code, value, backReasoning);
    },
    // 更改社保操作
    changeShebao (id, code, backReasoning) {
      // console.log(1255, id, code, backReasoning);
      let obj = this.fApi.getRule(code);
      let { children } = obj;
      for (let i = 0; i < children.length; i++) {
        children[i].className = "form_field_btn_text";
        children[i].selected = 0;
      }
      this.fApi.updateRule(obj.children[id].field, {
        className: "form_field_btn_active",
        selected: 1
      });
      let shebao = this.fApi.getValue(obj.children[id].field);
      let index = code.indexOf("_") >= 0 ? code.split("_")[0] : 0;
      let insuredArr = [];
      let object = {};
      object.insuredArr = [];
      object.type = "";
      let data = {
        field: code,
        value: shebao,
        index: index
      };
      insuredArr.push(data);
      object.insuredArr = insuredArr;
      this.$store.dispatch("config/updateInsuredObj", object);
      // this.$store.dispatch("config/updateFormData", this.rule);

      this.$emit("changeShebao", code, shebao, backReasoning);
    },
    // 更改保险份数
    changeCommNumOfCopies (code, value, backReasoning) {
      this.$store.dispatch(
        "config/updateCommNumOfCopies",
        !this.$isNull(value) ? parseInt(value) : 1
      );
      this.$emit("changeCommNumOfCopies", code, value, backReasoning);
    },
    // inputNumber输入框失去焦点事件
    numBlur (type, field, value, maxValue, minValue, backReasoning) {
      const reg = /^[0-9]*$/;

      value = reg.test(value) ? value : minValue;
      if (type && type == "plus") {
        let tempValue = parseInt(value);
        tempValue++;
        value = tempValue + "";
      }
      if (type && type == "minus") {
        let tempValue1 = parseInt(value);
        tempValue1--;
        value = tempValue1 + "";
      }

      if (parseInt(value) >= maxValue) {
        this.fApi.setValue(field, maxValue + "");
        this.fApi.setValue(field + "1", maxValue + "");
        value = maxValue + "";
      } else if (parseInt(value) <= minValue) {
        this.fApi.setValue(field, minValue + "");
        this.fApi.setValue(field + "1", minValue + "");
        value = minValue + "";
      } else {
        this.fApi.setValue(field, value);
        this.fApi.setValue(field + "1", value);
      }
      this.$store.dispatch(
        "config/updateCommNumOfCopies",
        !this.$isNull(value) ? parseInt(value) : 1
      );
      this.$emit("changeCommNumOfCopies", field, value, backReasoning);
    }
  }
};
</script>
<style lang="less" scope>
#safePlan_personInfoPlan {
  background: rgba(36, 128, 234, 1);
  height: auto !important;
  padding-bottom: 5px;

  .personInfo {
    width: 355px;
    background: rgb(255, 255, 255);
    border-radius: 8px;
    margin: 10px auto 0;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    line-height: 20px;
    // padding: 5px 0;
    box-sizing: border-box;
    padding: 10px 15px;
    // /deep/.ant-form-item:last-child {
    //   border-bottom: 1px solid transparent;
    // }
    .formRow {
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #ebedf0;
      .ant-form-item-label {
        width: 60%;
      }
      .ant-form-item-control-wrapper {
        width: 40%;
        .ant-col-24 {
          flex: 1;
          .ant-form-item-control-wrapper {
            width: 100%;
            text-align: center;
            .ant-input {
              padding: 5px;
              text-align: center;
            }
            .anticon {
              font-size: 18px;
              font-weight: 400;
              color: #969799;
            }
          }
        }
      }
    }
    .formTitle {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(17, 17, 17, 1);
      line-height: 46px;
      /deep/.ant-form-item label {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(17, 17, 17, 1);
        line-height: 46px;
      }
      .ant-form-item-label > label {
        font-size: 17px;
        font-family: PingFangSC-Medium, PingFang SC;
        font-weight: 500;
        color: rgba(17, 17, 17, 1);
        line-height: 46px;
      }
    }
    .minTitle {
      font-size: 17px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(17, 17, 17, 1);
      line-height: 46px;
    }
    /deep/ [class*="van-hairline"]::after {
      border: none;
    }
    .blue {
      background: rgba(238, 245, 255, 1);
      color: rgba(43, 121, 237, 1);
    }
    .van-checkbox {
      float: right;
    }
    .inputRow {
      .ant-col-24 {
        text-align: center;
      }
      .ant-row {
        display: flex;
        align-items: center;
      }
    }
    .infoGroup {
      .ant-row-flex-middle {
        background-color: #fff !important;
        padding: 0 !important;
        border-radius: 0 !important;
        margin-bottom: 0 !important;
        .ant-col-20 {
          width: 100%;
        }
        .ant-col-2 {
          display: none;
        }
      }
    }
    .numInput {
      .ant-input {
        text-align: center;
      }
    }
    .select-button {
      width: 60px;
      height: 27px;
      margin-left: 10px;
      background: #eeeeee;
      border-radius: 6px;
      color: #b9b9b9;
      border: 1px solid #eeeeee;
      font-size: 11px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      line-height: 27px;
      white-space: nowrap;
      padding: 0;
    }
    .selected-button {
      color: #2b79ed;
      background: #eef5ff;
    }
    .ant-form-item {
      margin-bottom: 5px;
    }
    .from_field_input {
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #ebedf0;

      .ant-form-item-label {
        width: 25%;
      }
      .ant-form-item-control-wrapper {
        width: 75%;
      }
      .ant-input {
        border: none;
        text-align: right;
      }

      .ant-input:hover {
        border: none;
      }
      .has-error .ant-input,
      .has-error .ant-input:hover {
        border: none;
        background: none;
      }
      .ant-input:focus {
        border: none;
        box-shadow: none;
      }
      .ant-form-explain {
        text-align: right;
      }
      .ant-form-item-control {
        background: none;
      }
    }
    .from_field_select {
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #ebedf0;
      .ant-form-item-label {
        width: 65%;
      }
      .ant-form-item-control-wrapper {
        width: 35%;
      }
      .ant-select-selection {
        background: none;
        border: none;
      }
      .ant-select-focused .ant-select-selection,
      .ant-select-selection:focus,
      .ant-select-selection:active,
      .ant-select-selection:hover {
        border: none;
        box-shadow: none;
      }
      .ant-form-item-control {
        background: none;
      }
    }
    .from_field_inputNum {
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #ebedf0;
      .ant-form-item-label {
        width: 70%;
      }
      .ant-form-item-control-wrapper {
        width: 30%;
      }
      .ant-input-number-handler-wrap {
        border: none;
      }
      .ant-input-number:hover {
        border: none;
        box-shadow: none;
      }
      .ant-input-number {
        border: none;
      }
      .ant-input-number-input {
        text-align: center;
      }
      .ant-form-item-control {
        background: none;
      }
      .ant-input-number {
        background: none;
      }
    }
    .ant-form-item.from_field_col {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .from_field_col {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 0;
      border-bottom: 1px solid #ebedf0;
      .ant-form-item-label {
        width: 30%;
      }
      .ant-form-item-control-wrapper {
        width: 70%;
      }
      .ant-form-item-control {
        width: 100%;
        background: none;
        .ant-form-item-children {
          display: block;
          width: 100%;
        }
      }
      .ant-form-item-children > .ant-col {
        width: 100%;
        justify-content: flex-end;
        align-items: center;
        display: flex;
      }
      .ant-col-24 {
        width: auto;
      }
      .ant-btn {
        width: 54px;
        height: 27px;
        line-height: 27px;
        border-radius: 3px;
        border: 1px solid rgba(238, 238, 238, 1);
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(185, 185, 185, 1);
        padding: 0;
      }
      .ant-btn:first-child {
        margin-right: 5px;
      }
    }
    .form_field_btn_active {
      .ant-btn {
        background: rgba(238, 245, 255, 1);
        color: rgba(43, 121, 237, 1);
        border: none;
      }
    }
    .form_field_btn_text {
      .ant-btn {
        width: 54px;
        height: 27px;
        line-height: 27px;
        border-radius: 3px;
        border: 1px solid rgba(238, 238, 238, 1);
        font-size: 12px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(185, 185, 185, 1);
        padding: 0;
      }
    }
    .van-cell {
      padding: 10px 0;
    }
    button {
      width: 54px;
      height: 27px;
      border-radius: 3px;
      border: 1px solid rgba(238, 238, 238, 1);
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(185, 185, 185, 1);
    }
    button:first-child {
      margin-right: 15px;
    }
    .blue {
      background: rgba(238, 245, 255, 1);
      color: rgba(43, 121, 237, 1);
    }
    .gray {
      width: 54px;
      height: 27px;
      line-height: 0.72rem;
      border-radius: 3px;
      border: 1px solid #eeeeee;
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #b9b9b9;
      padding: 0;
      background-color: #fff;
    }
  }
}
</style>
