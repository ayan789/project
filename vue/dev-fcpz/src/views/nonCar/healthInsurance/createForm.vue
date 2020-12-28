<template>
    <div class="createform">
       <!-- 动态表单元素 -->
      <form-create
        v-model="formCreateApi"
        :rule="formCreateRule"
        :option="option"
      />
      <!-- 出生日期 popup-->
    <div class="date">
      <van-popup
        v-model="showDate"
        position="bottom"
        :style="{ height: '70%' }"
      >
        <van-datetime-picker
          v-model="currentDate"
          type="date"
          @confirm="selectBirthDateConfirm"
          @cancel="showDate = false"
          :min-date="minDate"
          :max-date="maxDate"
        />
      </van-popup>
    </div>
     <!-- 保费 投保确认按钮 -->
    <div class="tabbar">
      <div class="price">
        保费
        <span style="color:#FC820E">1.0</span>
        元
      </div>
      <div class="btn">
        <van-button type="info" @click="enter">立即投保</van-button>
      </div>
    </div>
    </div>
</template>

<script>
import {
  trial,
  getPlanDynamicForms,
  createFromPlaceOrder
} from "@src/http/module/nonCar.js";
import { formatDate } from "@src/utils";
let currentDatePickerCode = "";
//渲染后得到新对象
let newObj = {};
    export default {
        name: "createform",
        data(){
            return{
                formCreateApi: {},
                formCreateRule: [],
                //组件参数配置
                option: {
                  submitBtn: false,
                  resetBtn: false
                  //表单提交事件
                  // onSubmit: function(formData) {
                  //   console.log("formData", JSON.stringify(formData));
                  // }
                },
                formInfo: {"formulaCode":"ETX2050005","invoiceFlag":"0","strategyId":984,"planId":1343,"isDefault":0,"prdPlanCategory":"0","planElementGroup":[{"groupId":5104,"bizId":0,"category":"policy","code":"applicant","name":"投保人信息","sort":0,"planElementList":[{"id":22244,"groupId":5104,"sourceElementId":0,"code":"appliName","groupCode":"applicant","name":"姓名","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/customerName","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"name","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":277,"groupId":3,"sourceElementId":0,"code":"appliCustomerNo","groupCode":"applicant","name":"投保人客户号","requireInput":0,"defaultValue":"","arrayIndex":0,"submitName":"","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"hidden","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":282,"groupId":3,"sourceElementId":0,"code":"appliCustomerRoleCode","groupCode":"applicant","name":"角色","requireInput":0,"defaultValue":"1","arrayIndex":0,"submitName":"/policyCustomerList/[]/customerRoleCode","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"hidden","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22245,"groupId":5104,"sourceElementId":0,"code":"appliIdentifyType","groupCode":"applicant","name":"证件类型","requireInput":1,"defaultValue":"111","arrayIndex":0,"submitName":"/policyCustomerList/[]/idType","sort":1,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"select","eventScript":"","validateType":"idType","placeholder":"","linkElementCode":[],"planElementOptionList":[{"id":92408,"elementId":0,"sourceOptionId":0,"value":"111","label":"身份证","selected":1,"sort":0,"addressTree":[]},{"id":92409,"elementId":0,"sourceOptionId":0,"value":"28","label":"港澳居民居住证","selected":1,"sort":1,"addressTree":[]},{"id":92410,"elementId":0,"sourceOptionId":0,"value":"414","label":"普通护照","selected":1,"sort":2,"addressTree":[]},{"id":92411,"elementId":0,"sourceOptionId":0,"value":"114","label":"军官证","selected":1,"sort":3,"addressTree":[]},{"id":92412,"elementId":0,"sourceOptionId":0,"value":"810","label":"统一社会信用代码","selected":1,"sort":4,"addressTree":[]},{"id":92413,"elementId":0,"sourceOptionId":0,"value":"801","label":"组织机构代码证","selected":1,"sort":5,"addressTree":[]}]},{"id":22246,"groupId":5104,"sourceElementId":0,"code":"appliIdentifyNo","groupCode":"applicant","name":"证件号码","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/idNo","sort":2,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"idNum","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22247,"groupId":5104,"sourceElementId":0,"code":"appliBirthDate","groupCode":"applicant","name":"出生日期","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/indiDateOfBirth","sort":3,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"Date","controlType":"DatePicker","eventScript":"","validateType":"birthday","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22248,"groupId":5104,"sourceElementId":0,"code":"appliSex","groupCode":"applicant","name":"性别","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/indiGenderCode","sort":4,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"select","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[{"id":92414,"elementId":0,"sourceOptionId":0,"value":"1","label":"男","selected":1,"sort":0,"addressTree":[]},{"id":92415,"elementId":0,"sourceOptionId":0,"value":"2","label":"女","selected":1,"sort":1,"addressTree":[]}]},{"id":22249,"groupId":5104,"sourceElementId":0,"code":"appliMobile","groupCode":"applicant","name":"手机号码","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/indiMobile","sort":5,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"phone","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22250,"groupId":5104,"sourceElementId":0,"code":"appliEmail","groupCode":"applicant","name":"邮箱","requireInput":0,"defaultValue":"","arrayIndex":0,"submitName":"/policyCustomerList/[]/email","sort":6,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"email","placeholder":"","linkElementCode":[],"planElementOptionList":[]}]},{"groupId":5105,"bizId":0,"category":"policy","code":"insured","name":"被保人信息\n","sort":1,"planElementList":[{"id":22251,"groupId":5105,"sourceElementId":0,"code":"insuredAppliRelation","groupCode":"insured","name":"您是投保人的","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/polHolderInsuredRelaCode","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"select","eventScript":"","validateType":"relation","placeholder":"","linkElementCode":[],"planElementOptionList":[{"id":92416,"elementId":0,"sourceOptionId":0,"value":"1","label":"本人","selected":1,"sort":0,"addressTree":[]},{"id":92417,"elementId":0,"sourceOptionId":0,"value":"50","label":"父母","selected":1,"sort":1,"addressTree":[]},{"id":92418,"elementId":0,"sourceOptionId":0,"value":"40","label":"子女","selected":1,"sort":2,"addressTree":[]},{"id":92419,"elementId":0,"sourceOptionId":0,"value":"10","label":"配偶","selected":1,"sort":3,"addressTree":[]}]},{"id":278,"groupId":4,"sourceElementId":0,"code":"insuredCustomerNo","groupCode":"insured","name":"被保人客户号","requireInput":0,"defaultValue":"","arrayIndex":0,"submitName":"","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"hidden","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":283,"groupId":4,"sourceElementId":0,"code":"insuredCustomerRoleCode","groupCode":"insured","name":"角色","requireInput":0,"defaultValue":"2","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/customerRoleCode","sort":0,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"hidden","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22252,"groupId":5105,"sourceElementId":0,"code":"insuredName","groupCode":"insured","name":"姓名","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/customerName","sort":1,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"name","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22253,"groupId":5105,"sourceElementId":0,"code":"insuredIdentifyType","groupCode":"insured","name":"证件类型","requireInput":1,"defaultValue":"111","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/idType","sort":2,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"select","eventScript":"","validateType":"insuredIdentifyType","placeholder":"","linkElementCode":[],"planElementOptionList":[{"id":92420,"elementId":0,"sourceOptionId":0,"value":"111","label":"身份证","selected":1,"sort":0,"addressTree":[]},{"id":92421,"elementId":0,"sourceOptionId":0,"value":"28","label":"港澳居民居住证","selected":1,"sort":1,"addressTree":[]},{"id":92422,"elementId":0,"sourceOptionId":0,"value":"414","label":"普通护照","selected":1,"sort":2,"addressTree":[]},{"id":92423,"elementId":0,"sourceOptionId":0,"value":"114","label":"军官证","selected":1,"sort":3,"addressTree":[]},{"id":92424,"elementId":0,"sourceOptionId":0,"value":"810","label":"统一社会信用代码","selected":1,"sort":4,"addressTree":[]},{"id":92425,"elementId":0,"sourceOptionId":0,"value":"801","label":"组织机构代码证","selected":1,"sort":5,"addressTree":[]}]},{"id":22254,"groupId":5105,"sourceElementId":0,"code":"insuredIdentifyNo","groupCode":"insured","name":"证件号码","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/idNo","sort":3,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"name","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22255,"groupId":5105,"sourceElementId":0,"code":"insuredBirthDate","groupCode":"insured","name":"出生日期","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/dateOfBirth","sort":4,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"DatePicker","eventScript":"","validateType":"name","placeholder":"","linkElementCode":[{"code":"insuredBirthDate","type":"trial","linkCode":"insuredAge"}],"planElementOptionList":[]},{"id":22256,"groupId":5105,"sourceElementId":0,"code":"insuredSex","groupCode":"insured","name":"性别","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/indiGenderCode","sort":5,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"select","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[{"id":92426,"elementId":0,"sourceOptionId":0,"value":"1","label":"男","selected":1,"sort":0,"addressTree":[]},{"id":92427,"elementId":0,"sourceOptionId":0,"value":"2","label":"女","selected":1,"sort":1,"addressTree":[]}]},{"id":22257,"groupId":5105,"sourceElementId":0,"code":"insuredMobile","groupCode":"insured","name":"手机号码","requireInput":1,"defaultValue":"","arrayIndex":0,"submitName":"/policyLobList/0/policyRiskList/0/personInsuredList/[]/indiMobile","sort":6,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"name","placeholder":"","linkElementCode":[],"planElementOptionList":[]},{"id":22258,"groupId":5105,"sourceElementId":0,"code":"insuredEmail","groupCode":"insured","name":"邮箱","requireInput":0,"defaultValue":"","arrayIndex":0,"submitName":"","sort":7,"trialFlag":0,"trialSort":0,"minLength":0,"maxLength":100,"minValue":0,"maxValue":100,"elementType":"String","controlType":"input","eventScript":"","validateType":"","placeholder":"","linkElementCode":[],"planElementOptionList":[]}]}],"insuredDate":{"policyStartRange":1,"startDate":"2020-09-18 00:00:00","endDate":"2021-09-17 23:59:59","giCoveragePeriod":1,"giPeriodType":1},"invoiceInfo":null},
                 cardTypeInfo: {
                    type: [],
                    picker: false,
                    typeVal: "身份证"
                  },
                  showDate: false,
                  selectDate: "", //选择日期
                  startDate: "",
                  endDate: "",
                  currentDate: new Date(),
                  minDate: new Date(1920, 0, 1), //出生日期组件默认定义最小值
                  maxDate: new Date(), //出生日期组件默认最大值
                  checkCodeEleList: [],
                  
            }
        },
        mounted() {
          this.getPlanDynamicForms()
        },
        methods:{
          //立即投保
          enter() {
            let params = {
              name:'',
              metaDataList: '', // 动态表单
              startDate: '', // 开始时间
              endDate: '', //formatDate(newEndDate).slice(0, 10), // this.tableData.endDate 截止日期
              timeLimit: '', // 保障期限
              strategyId: '', // 销售方案id
              storeCode: '', // 门店代码
              userCode: '', // 用户代码
              planId: '', // 批次id
              orderId: '', // 暂存订单Id
              sumPremium: '', // 应收保费
              insureNo: '', // 投保单号
              isAgent: '', // 是否代理人 1 是 0 否
              sumAmount: '', // 保额
              formulaCode: '', // 方案代码
              riskCode: '', // 产品代码
              productList: '', // 已选产品
              isTemp: 1, // 暂存标识  1暂存 0提交核心
              isInvoice: '', //是否需要发票
              invoiceFlag: ''
            };
            // 立即投保校验
            createFromPlaceOrder(params)
              .then(res => {
                console.log("res---",res);
                const tableData = this.formCreateApi.formData();
                console.log("tableData---",tableData);
                console.log("投保人姓名:",tableData.appliName);
                console.log("被保人生日:",tableData.insuredBirthDate);
                //console.log("份数:",tableData.commNumOfCopies);
              })
          },
          // 获取表单信息
          getPlanDynamicForms() {
            let params = {
              // strategyId: 125,
              strategyId: 1311,
              cate: "pl",
              category: "policy"
            };
            const me = this;
            getPlanDynamicForms(params).then(res => {
            if (res.code === "1") {
              console.log(res.data[0])
              me.formInfo=res.data[0]
              this.renderForm(me.formInfo);
            }
            });
            //this.renderForm(me.formInfo);
          
          },
           // inputNumber输入框失去焦点事件 （份数等）
            numBlur(type, field, value, maxValue, minValue) {
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
                this.formCreateApi.setValue(field, maxValue + "");
                this.formCreateApi.setValue(field + "1", maxValue + "");
                value = maxValue + "";
              } else if (parseInt(value) <= minValue) {
                this.formCreateApi.setValue(field, minValue + "");
                this.formCreateApi.setValue(field + "1", minValue + "");
                value = minValue + "";
              } else {
                this.formCreateApi.setValue(field, value);
                this.formCreateApi.setValue(field + "1", value);
              }
              this.updatePremiumsNum(field, value);
              // this.$store.dispatch(
              //   "config/updateCommNumOfCopies",
              //   !this.$isNull(value) ? parseInt(value) : 1
              // );
              // this.$emit("updateValueAndPremium", field, value, backReasoning);
            },
          // 格式化时间
          formatDate(date) {
            console.log("date=======", date);
            let month = date.getMonth() + 1;
            if (month < 10) {
              month = "0" + month;
            }
            let beginDate = date.getDate();
            if (beginDate < 10) {
              beginDate = "0" + beginDate;
            }
            return `${date.getFullYear()}-${month}-${beginDate}`;
          },
          roShowDate(a, code) {
            //const formData = this.formCreateApi.formData();
            this.showDate = a;
            currentDatePickerCode = code;
            // currentContentPickerCode   TODO  试算
          },
          //关系校验
          // 判断是初始化进来的
          validateRelationtype(code, val, isInit) {
            console.log('关系校验');
          },
          // 选择出生日期区间方法
          selectBirthDateConfirm(date) {
            if (currentDatePickerCode.toLowerCase().indexOf("birthdate") >= 0) {
              this.formCreateApi.updateRule(currentDatePickerCode + "1", {
                title: this.formatDate(date)
              });
              this.formCreateApi.setValue(
                currentDatePickerCode,
                this.formatDate(date)
              );
              let getAge = this.$getAge(this.formatDate(date));
              let code =
                currentDatePickerCode.toLowerCase().split("birthdate")[0] + "Age";
              console.log("---getAge---", getAge * 365);
              this.updatePremiumsNum(code, getAge * 365);
            } else {
              this.formCreateApi.setValue(
                currentDatePickerCode,
                this.formatDate(date)
              );
            }
             this.showDate = false;
          },
           /**
           * 更新计算保费试算
           * code:对应的影响元素
           * value：对应的影响因子的值 具体的一个值，不带-
           * name: 对应的元素名称
           */
          updatePremiumsNum (code, value, name) {
            console.log('updatePremiumsNum');
            let obj = {
                planId: 1,
                planCoverageIds: 1
              };
              // trial(obj)
              //   .then(res => {
              //       console.log(res);
              //   })
          },
          /**
           * code:对应的表单元素code
           * val：传入信息包含具体值
           */
          changeSelect(code, val) {
            // TODO suncao
            console.log("当前code======" + code);
            console.log("当前val======" + val);
            //元素code
            const codeLow = code.toLowerCase();
            //关系联动
            if (
              (codeLow.includes("relation") || codeLow.includes("polholder")) &&
              code != "keyContactsInsuredRelation"
            ) {
              this.validateRelationtype(code, val);
            }
            // //证件号码联动
            // if(codeLow.includes('identifyno')){
            //   let type = code.split("IdentifyNo")[0] + "IdentifyType";
            //   type = this.formCreateApi.getValue(type);
            //   this.handlerIdNum(code, type, val);
            // }
            // IdentifyNo

            // currentContentPickerCode   TODO  试算
            // if (val == "insuredBirthDate") {

            //   console.log("val", date);
            //   // this.selectDateConfirm2(new Date(date));
            this.roShowDate(false);
            // } else if (val == "appliBirthDate") {
            //   console.log("val", date);
            //   // this.roShowDate(true);
            // }
          },
          validateMobile(val, isRequire, groupName) {
            handlerMobile(val, isRequire, groupName);
          },
          validateEMail(val, isRequire, groupName) {
            console.log("val", val);
            handlerEmail(val, isRequire, groupName);
          },
           // 渲染动态form
          renderForm(info) {
            const formInfo = info || this.formInfo.data;
            console.info("1953 formInfo---", formInfo);
            let planGroup = formInfo.planElementGroup;
            this.initFormData = planGroup; //初始化表单数据
            this.fixsumPremium = this.$store.state.config.detailData.basePremium;
            // 加字段验证 slot事件
            const me = this;
            this.totForm = [];
            for (let index = 0; index < planGroup.length; index++) {
              const element = planGroup[index];
              const planElementList = element.planElementList;
              const initPlanElementList = element.planElementList;
              this.totForm.push(element.planElementList);
              let { code, name, sort, groupId } = element;
              this.formCreateRule.push({
                type: "label",
                field: code,
                title: name,
                sort,
                groupId,
                className: "formTitle",
                children: [
                {
                  className: "titleIcon",
                  name: "img",
                  type: "img",
                  attrs: {
                    src: "",
                    height: "20px"
                  },
                  slot: "prefix",
                  on: {
                    click: () => {
                      console.log("点击了");
                    }
                  }
                }
              ]
              });

              for (let i = 0; i < planElementList.length; i++) {
                const item = planElementList[i];
                this.setOptionList = [];
                const optionInfo = item.planElementOptionList || "";
                if (optionInfo) {
                  this.setOptionList = optionInfo.map(v => ({
                    value: v.value,
                    label: v.label
                  }));
                  for (let item of optionInfo) {
                    this.cardTypeInfo.type.push(item.label);
                  }
                }
                 // hidden 为试算字段不需要展示
                if (item.controlType !== "hidden" || item.controlType !== "") {
                  if (!item.defaultValue) {
                    item.defaultValue = "";
                  }
                  if (item.controlType != "DatePicker") {
                    this.showDate = false;
                  }

                  if (item.controlType === "DatePicker") {
                    this.formCreateRule.push({
                      type: "label",
                      field: item.code,
                      title: item.name,
                      className: "birth",
                      groupName: element.name,
                      value:
                        item.defaultValue != "0" &&
                          item.defaultValue != null &&
                          item.defaultValue != undefined
                          ? item.defaultValue
                          : "",
                      children: [
                        {
                          type: "label",
                          field: item.code + "1",
                          title: item.defaultValue ? item.defaultValue : "  ",
                          className: "birth",
                          children: [
                            {
                              type: "div",
                              children: [
                                {
                                  name: "icon",
                                  type: "img",
                                  className: "tipIcon",
                                  //  this.shengriImg
                                  attrs: {
                                    src: 'http://10.100.124.66:8066/assets/img/beibao.png',
                                    height: "20px"
                                  }
                                }
                              ],
                              slot: "suffix",
                              on: {
                                click: () => {
                                  me.ocrDistinguish(code);
                                  console.log("value 发生变化");
                                }
                              }
                            }
                          ]
                        }
                      ],
                      on: {
                        click: () => {
                          if (item.code.toLowerCase().includes("date")) {
                            console.log("me.roShowDate(true);-------", item.code);
                            me.roShowDate(true, item.code);
                          }
                        }
                      },
                      validate: [
                        {
                          type:
                            item.controlType === "DatePicker"
                              ? "string"
                              : item.elementType.toLowerCase(),
                          required: true
                          // message: `请${
                          //   item.controlType == "input" ? "输入" : "选择"
                          //   }${item.name}`
                        },
                        {
                          validator (rule, value, callback, source, options) {
                            console.log("rule.field=======" + rule.field);
                            const codeLow = rule.field.toLowerCase();
                            const code = rule.field;

                            //出生日期校验
                            if (rule.field == "birthdate") {
                              //TODO
                            }
                          }
                        }
                      ]
                    });
                  } else if (item.controlType == "inputNumber") {
                    let obj = {
                      type: "a-row",
                      field: item.code,
                      title: item.name,
                      className: "formRow",
                      value: item.defaultValue ? item.defaultValue : "1",
                      groupName: element.name,
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
                                item.maxValue,
                                item.minValue,
                                obj.backReasoning
                              );
                            }
                          }
                        },

                        {
                          field: item.code + "1",
                          value: item.defaultValue ? item.defaultValue : "1",
                          groupName: element.name,
                          type: "input",
                          props: {
                            field: item.code + "1",
                            maxLength: item.maxValue,
                            minLength: item.minValue,
                            defaultValue: !this.$isNull(item.defaultValue)
                              ? parseInt(item.defaultValue)
                              : 1
                          },
                          on: {
                            blur: () => {
                              this.numBlur(
                                "",
                                obj.field,
                                obj.children[1].value,
                                item.maxValue,
                                item.minValue,
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
                                item.maxValue,
                                item.inValue,
                                obj.backReasoning
                              );
                            }
                          }
                        }
                      ],
                      options: this.setOptionList || []
                    };
                    this.formCreateRule.push(obj);
                  } 
                  else if (item.controlType == "Cascader") {
                    let obj = {
                      type: "cascader",
                      field: item.code,
                      title: item.name,
                      groupName: element.name,
                      className: "diySelect",
                      value: item.defaultValue ? [item.defaultValue] : [],
                      props: {
                        fieldNames: {
                          label: "text",
                          value: "id",
                          children: "children"
                        },
                        options: [],
                        showSearch: true,
                        placeholder: "请输入关键字进行搜索" + item.name
                      },
                      validate: [
                        {
                          required: item.requireInput ? true : false
                          // message: item.value? "":`请${
                          //   item.controlType == "input" ? "输入" : "选择"
                          //   }${item.name}`,
                          // trigger: 'blur'
                        }
                      ]
                    };
                    item.planElementOptionList.forEach(el => {
                      obj.props.options = el.addressTree;
                    });
                    let nodeList = [];
                    let idArr = [];
                    if (item.defaultValue) {
                      this.findPathById(
                        item.defaultValue,
                        obj.props.options,
                        nodeList
                      );
                      nodeList.forEach(el => {
                        idArr.push(el.id);
                      });
                    }
                    obj.value = idArr;
                    this.formCreateRule.push(obj);
                  } 
                  else {
                    console.log(item.code + "======" + item.defaultValue);
                    // 格式化后的表单数据塞进统一formCreateRule里面
                    this.formCreateRule.push({
                      type:
                        item.controlType === "DatePicker"
                          ? "input"
                          : item.controlType,
                      field: item.code,
                      className:
                        item.controlType === "DatePicker" ||
                        item.controlType === "input"
                          ? "diyInput"
                          : "diySelect",
                      title: item.name,
                      groupName: element.name,
                      value:
                        item.defaultValue != "0" &&
                        item.defaultValue != null &&
                        item.defaultValue != undefined
                          ? item.defaultValue
                          : "",
                      options: this.setOptionList || [],
                      props: {
                        maxLength:
                          item.code.toLowerCase().includes("mobile") ||
                          item.code.toLowerCase().includes("phone")
                            ? 11
                            : item.code.toLowerCase().includes("zipcode")
                            ? 6
                            : 30
                      },
                      attrs: {
                        placeholder:
                          item.code == "appliIdentifyNo" ||
                          item.code == "insuredIdentifyNo"
                            ? "请输入证件号码"
                            : item.code == "appliMobile" ||
                              item.code == "insuredMobile"
                            ? "请输入手机号"
                            : item.code == "appliEmail"
                            ? "请输入邮箱地址，可接收电子保单"
                            : "请输入" + item.name
                      },
                      on: {
                        change: val => {
                          me.changeSelect(item.code, val);
                          // me.changeSelect(item.code, date);
                        },
                        click: () => {
                          if (item.controlType === "DatePicker") {
                            console.log("me.roShowDate(true);-------");
                            me.roShowDate(true, item.code);
                          }
                        }
                      },
                      children: [
                        // item.code.indexOf("IdentifyNo") >= 0
                        //   ? {
                        //       type: "div",
                        //       children: [
                        //         {
                        //           name: "icon",
                        //           type: "img",
                        //           attrs: {
                        //             src: this.cameraIcon,
                        //             height: "20px"
                        //           }
                        //         }
                        //       ],
                        //       slot: "suffix",
                        //       on: {
                        //         click: () => {
                        //           me.ocrDistinguish("appliCard");
                        //           console.log("value 发生变化");
                        //         }
                        //       }
                        //     }
                        //   : ""
                      ],
                      validate: [
                        {
                          type:
                            item.controlType === "DatePicker"
                              ? "string"
                              : item.elementType.toLowerCase(),
                          required: item.requireInput ? true : false
                          // message: item.value? "":`请${
                          //   item.controlType == "input" ? "输入" : "选择"
                          //   }${item.name}`,
                          // trigger: 'blur'
                        },
                        {
                          validator(rule, value, callback, source, options) {
                            // console.log(rule);
                            console.log("rule.field=======" + rule.field);
                            const codeLow = rule.field.toLowerCase();
                            const code = rule.field;
                            //证件号码联动
                            if (codeLow.includes("identifyno")) {
                              let type1 =
                                code.split("IdentifyNo")[0] + "IdentifyType";
                              type1 = me.formCreateApi.getValue(type1);
                              if (type1 == "身份证") {
                                type1 = "111";
                              }
                              if (type1 != "111" || type1 != "01") {
                                me.showOCR = false;
                              } else {
                                me.showOCR = true;
                              }
                              me.handlerIdNum(code, type1, value);
                            }
                          }
                        }
                      ]
                    });
                  }
                }
              }
            }
            // console.table(this.formCreateRule);
            //接口获得的form节点存入store
            // this.$store.commit("config/save_totForm", this.totForm);
            this.checkCodeEleList.splice(0, this.checkCodeEleList.length);
            for (let item of this.totForm) {
              if (item.controlType === "hidden" || item.controlType === "") {
                // console.log(2448, this.checkCodeEleList);
                this.checkCodeEleList.push(item.code);
              }
            }
            console.log(this.formCreateRule);
            console.log("totForm---", this.totForm);
            //判断同投保人等关系调用 todo suncao
            for (let index = 0; index < this.formCreateRule.length; index++) {
              const elementTarget = this.formCreateRule[index];
              let key = elementTarget.field;
              let value = elementTarget.value;
              newObj[key] = value;
              console.log("元素code=====" + elementTarget.field);
            }
            console.log("得到的对象=======" + newObj);
            for (let index = 0; index < this.formCreateRule.length; index++) {
              const elementTarget = this.formCreateRule[index];
              if (elementTarget.field.toLowerCase().includes("relation")) {
                //todo suncao
                // setTimeout(() => {
                //   this.validateRelationtype (elementTarget.field, elementTarget.value, true);
                // }, 2000);
              }
            }
            this.updatePremiumsNum();
          }
          
        }
    }
</script>

<style lang="less" scoped>
/deep/.ant-form-item-label {
width: 28% !important;
float: left !important;
padding: 10px;
}

/deep/.ant-form-item-control-wrapper {
width: 72% !important;
float: right !important;
}
/deep/.birth {
    background-color: #fff;
}
/deep/.ant-input,
/deep/.ant-input-focused,
/deep/.ant-input-selection:focus,
/deep/.ant-input-selection:active,
/deep/.ant-input-selection:hover
{
  border: none;
  text-align: right;
  font-size: 0.42333rem;
  border-bottom: 1px solid white !important;
  box-shadow: none;
}
/deep/.ant-input-number-handler-wrap {
  border: none;
}
/deep/.ant-input-number {
  border: none;
}
/deep/.ant-select-focused,
/deep/.ant-select-selection,
/deep/.ant-select-selection:focus,
/deep/.ant-select-selection:active,
/deep/.ant-select-selection:hover {
  text-align: right;
  font-size: 0.42333rem;
  border: none;
  box-shadow: none;
}
/deep/.has-error.ant-input:not([disabled]):hover,
/deep/.has-error .ant-input, /deep/.has-error .ant-input:hover{
  border: none;
  text-align: right;
  font-size: 0.42333rem;
  box-shadow: none;
}
/deep/.ant-form-item-control {
  background: none;
}
/deep/.ant-row{background-color: #fff;}
.tabbar {
    position: fixed;
    -webkit-overflow-scroll: touch;
    bottom: 0;
    display: flex;
    width: 100%;
    height: 45px;
    z-index: 1;
    .price {
      flex: 1;
      text-align: center;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #fff;
      font-size: 16px;

      span {
        color: #fc820e;
        font-size: 17px;
        margin: 0 11px;
      }
    }

    .btn {
      flex: 1;
      font-size: 16px;
      button {
        width: 100%;
      }
    }
  }

  /deep/.birth {
    border-bottom: 1px solid #ebedf0;
    margin-bottom: 0;
    display: flex;
    .ant-form-item-label,
    .ant-form-item-control-wrapper {
      flex: 1;
    }
    .ant-form-item-control-wrapper {
      text-align: right;
      .birth {
        border-bottom: none;
      }

      /deep/.ant-form-item-label {
        text-align: right;
        width: 100%;
      }
      .ant-form-item-control-wrapper {
        flex: 0;
      }
    }
    .ant-col-24 {
      height: auto;
    }
  }

  /deep/.ant-row {padding: 5px 5px; font-size: 14px;}
  /deep/.ant-form-item-label > label{color: #646566;}
  /deep/.ant-form {padding-bottom: 45px;}
  /deep/.ant-form label {font-size: 0.42333rem;}
  /deep/.ant-select-selection-selected-value {float: right;}
  // /deep/.ant-row {border-bottom: 1px solid #ebedf0;}
  *{padding:0;margin:0;}
  /deep/.ant-row {
    padding-left: 0px;
    padding-top: 0px;
    padding-right: 0px;
    margin: 0;
    overflow: auto;
    overflow-x: hidden;
  }
  /deep/.formTitle .ant-form-item-label > label {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 0.66667rem;
  }
  /deep/.formTitle{
      width: 500px;
      background: #f7f7f7;
      padding-top: 30px;
  }
  /deep/.ant-col-24 {
    flex: 1;
    text-align: center;
}
/deep/.diyInput{
    border-bottom: 1px solid #ebedf0;
}
/deep/.diySelect{
    border-bottom: 1px solid #ebedf0;
}
/deep/.formRow{
    border-bottom: 1px solid #ebedf0;
}
/deep/.ant-cascader-picker-show-search .ant-cascader-input.ant-input {
    border: none;
    box-shadow: none;
}
/deep/.labelItem .titleIcon{ background: #f7f7f7;}
</style>