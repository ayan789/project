const nonCar = [
  // 非车模块
  {
    name: "nonCar",
    path: "/nonCar",
    redirect: "/nonCar/healthInsurance/insuranceProducts"
  },
  {
    name: "nonCarTest",
    path: "/nonCarTest",
    component: () => import("@src/views/nonCar/healthInsurance/nonCarTest")
  },
  // 搜索列表
  {
    name: "searchList",
    path: "/nonCar/searchList",
    component: () => import("@src/views/nonCar/healthInsurance/searchList")
  },
  // 支付方式
  {
    name: "paymentMethodSheet",
    path: "/nonCar/paymentMethodSheet",
    component: () => import("@src/views/nonCar/common/paymentMethodSheet")
  },
  // 健康险-保单确认（个人版）
  {
    name: "policyConfirm",
    path: "/nonCar/policyConfirm",
    component: () => import("@src/views/nonCar/common/policyConfirm")
  },

  //保障详情
  {
    name: "safeguardDetail",
    path: "/nonCar/safeguardDetail",
    component: () => import("@src/views/nonCar/common/safeguardDetail")
  },
  //健康险-健康及职业告知
  {
    name: "coverage",
    path: "/nonCar/coverage",
    component: () => import("@src/views/nonCar/healthInsurance/coverage")
  },

  //健康险-投保人信息输入
  {
    name: "insuranceInformationInput",
    path: "/nonCar/insuranceInformationInput",
    component: () =>
      import(
        "@src/views/nonCar/common/insuranceInformation/insuranceInformationInput"
      )
  },
  //健康险-投保人信息输入 动态生成
  {
    name: "insuranceInfoInputFormcreate",
    path: "/nonCar/insuranceInfoInputFormcreate",
    component: () =>
      import(
        "@src/views/nonCar/common/insuranceInformation/insuranceInfoInputFormcreate"
      )
  },
  //健康险家庭版
  {
    name: "writeForm",
    path: "/nonCar/writeForm",
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@src/views/nonCar/common/insuranceInformation/writeForm")
  },
  //健康险-投保人信息确认
  {
    name: "insuranceinfoConfirm",
    path: "/nonCar/insuranceinfoConfirm",
    component: () =>
      import(
        "@src/views/nonCar/common/insuranceInformation/insuranceinfoConfirm"
      )
  },

  //发票详情
  {
    name: "invoiceDetail",
    path: "/nonCar/common/invoiceDetail",
    component: () => import("@src/views/nonCar/common/invoice/invoiceDetail")
  },
  //配置保险条款
  {
    name: "prBxtk",
    path: "/nonCar/prBxtk",
    component: () => import("@src/views/nonCar/healthInsurance/prBxtk")
  },
  //健康险-签名确认
  {
    name: "signatureConfirm",
    path: "/nonCar/signatureConfirm",
    component: () => import("@src/views/nonCar/common/signatureConfirm")
  },
  //健康险-签名确认
  {
    name: "prSign",
    path: "/nonCar/prSign",
    component: () => import("@src/views/nonCar/common/prSign")
  },
  // 健康险-选择投保人
  {
    name: "choosePolicyHolder",
    path: "/nonCar/choosePolicyHolder",
    component: () => import("@src/views/nonCar/common/choosePolicyHolder")
  },
  //健康险-百万医疗
  {
    name: "millionMedicalCare",
    path: "/nonCar/millionMedicalCare",
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@src/views/nonCar/healthInsurance/millionMedicalCare")
  },

  //
  {
    name: "stopCancer",
    path: "/nonCar/stopCancer",
    meta: {
      keepAlive: true
    },
    component: () => import("@src/views/nonCar/healthInsurance/stopCancer")
  },
  {
    name: "stopCancerForm",
    path: "/nonCar/stopCancerForm",
    component: () =>
      import("@src/views/nonCar/common/insuranceInformation/stopCancerForm")
  },
  //健康险-百万医疗
  {
    name: "confirm",
    path: "/nonCar/confirm",
    component: () => import("@src/views/nonCar/healthInsurance/confirm")
  },
  //健康险-百万医疗
  {
    name: "prConfirm",
    path: "/nonCar/prConfirm",
    component: () => import("@src/views/nonCar/healthInsurance/prConfirm")
  },
  {
    name: "stopCancerConfirm",
    path: "/nonCar/stopCancerConfirm",
    component: () =>
      import("@src/views/nonCar/healthInsurance/stopCancerConfirm")
  },
  //健康险-百万医疗计划书
  {
    name: "millionPlan",
    path: "/nonCar/millionPlan",
    component: () => import("@src/views/nonCar/healthInsurance/millionPlan")
  },
  //健康险-防癌计划书
  {
    name: "stopCancerPlan",
    path: "/nonCar/stopCancerPlan",
    component: () => import("@src/views/nonCar/healthInsurance/stopCancerPlan")
  },
  //健康险-百万医疗计划书
  {
    name: "planBook",
    path: "/nonCar/planBook",
    component: () => import("@src/views/nonCar/healthInsurance/planBook")
  },
  {
    name: "prPlanBook",
    path: "/nonCar/prPlanBook",
    component: () => import("@src/views/nonCar/healthInsurance/prPlanBook")
  },
  //健康险-百万医疗计划书
  {
    name: "millionPlanHome",
    path: "/nonCar/millionPlanHome",
    component: () => import("@src/views/nonCar/healthInsurance/millionPlanHome")
  },
  //健康险-百万医疗计划书
  {
    name: "healthTell",
    path: "/nonCar/healthTell",
    component: () => import("@src/views/nonCar/healthInsurance/healthTell")
  },
  {
    name: "healthTellNew",
    path: "/nonCar/healthTellNew",
    component: () => import("@src/views/nonCar/healthInsurance/healthTellNew")
  },
  {
    name: "healthTellFamily",
    path: "/nonCar/healthTellFamily",
    component: () =>
      import("@src/views/nonCar/healthInsurance/healthTellFamily")
  },
  // 非车配置计划书
  {
    name: "prPlanInfo",
    path: "/nonCar/prPlanInfo",
    component: () => import("@src/views/nonCar/healthInsurance/prPlanInfo")
  },
  {
    name: "millionInfo",
    path: "/nonCar/millionInfo",
    component: () => import("@src/views/nonCar/healthInsurance/millionInfo")
  },
  //健康险-家庭版
  {
    name: "millionHome",
    path: "/nonCar/millionHome",
    meta: {
      keepAlive: true
    },
    component: () => import("@src/views/nonCar/healthInsurance/millionHome")
  },
  // 财产险-货运无忧
  // {
  //   path: "/nonCar/propertyInsurance/worryFree",
  //   name: "proWorryFree1",
  //   component: () => import("@src/views/nonCar/propertyInsurance/worryFree"),
  // },
  //财产险-投保信息填写
  // {
  //   path: "/nonCar/propertyInsurance/insuranceInformation2",
  //   name: "proInsuranceInformation2",
  //   component: () =>
  //     import("@src/views/nonCar/propertyInsurance/insuranceInformation2"),
  // },
  //财产险-保单确认
  {
    path: "/nonCar/propertyInsurance/propertyInsurancePolicyConfirmation",
    name: "propertyInsurancePolicyConfirmation",
    component: () =>
      import(
        "@src/views/nonCar/propertyInsurance/propertyInsurancePolicyConfirmation"
      )
  },
  // 健康险-保险产品
  {
    path: "/nonCar/healthInsurance/insuranceProducts",
    name: "insuranceProducts",
    component: () =>
      import("@src/views/nonCar/healthInsurance/insuranceProducts")
  },
  {
    path: "/nonCar/healthInsurance/nonCar",
    name: "insuranceProducts2",
    component: () =>
      import("@src/views/nonCar/healthInsurance/insuranceProducts2")
  },
  {
    path: "/nonCar/healthInsurance/createForm",
    name: "createForm",
    component: () =>
      import("@src/views/nonCar/healthInsurance/createForm")
  },
  //货运无忧特別约定
  {
    path: "/nonCar/clauseFine/propertyArgument",
    name: "propropertyArgument",
    component: () => import("@src/views/nonCar/clauseFine/propertyArgument")
  },
  //特別条款
  {
    path: "/nonCar/clauseFine/propertyclause",
    name: "propropertyclause",
    component: () => import("@src/views/nonCar/clauseFine/propertyclause")
  },
  // 非车公共支付-保单支付
  {
    name: "paymentOrder",
    path: "/nonCar/payment/paymentOrder",
    meta: {
      keepAlive: true
    },
    component: () => import("@src/views/nonCar/payment/paymentOrder")
  },
  // //支付页面
  {
    name: "prePayMent",
    path: "/nonCar/prePayMent",
    component: () => import("@src/views/nonCar/common/prePayMent")
  },
  // 支付--二维码支付
  {
    name: "nonPaymentByQRCode",
    path: "/nonCar/payment/paymentByQRCode",
    component: () => import("@src/views/nonCar/payment/paymentByQRCode")
  },
  // 配置支付--二维码支付
  {
    name: "prPaymentByQRCode",
    path: "/nonCar/payment/prPaymentByQRCode",
    component: () => import("@src/views/nonCar/payment/prPaymentByQRCode")
  },
  // 支付--支付宝支付
  {
    name: "nonPaymentByAlipayMessage",
    path: "/nonCar/payment/paymentByAlipayMessage",
    component: () => import("@src/views/nonCar/payment/paymentByAlipayMessage")
  },
  // 非车配置支付--支付宝支付
  {
    name: "prPaymentByAlipayMessage",
    path: "/nonCar/payment/prPaymentByAlipayMessage",
    component: () =>
      import("@src/views/nonCar/payment/prPaymentByAlipayMessage")
  },
  //支付--转发确认支付--财产险
  {
    name: "nonPaymentForward",
    path: "/nonCar/payment/paymentForward",
    component: () => import("@src/views/nonCar/payment/paymentForward")
  },
  //支付--转发确认支付--百万医疗个人版
  {
    name: "nonPaymentForwardPerson",
    path: "/nonCar/payment/paymentForwardPerson",
    component: () => import("@src/views/nonCar/payment/paymentForwardPerson")
  },
  //支付-- 支付成功
  {
    name: "paySuccess",
    path: "/nonCar/paySuccess",
    component: () => import("@src/views/nonCar/payment/paySuccess")
  },
  // 支付--支付失败
  {
    name: "payFail",
    path: "/nonCar/payFail",
    component: () => import("@src/views/nonCar/payment/payFail")
  },
  // 货运无忧投保须知
  {
    path: "/nonCar/clauseFine/propertyInstructions",
    name: "propertyInstructions",
    component: () => import("@src/views/nonCar/clauseFine/propertyInstructions")
  },
  // 货运无忧保险条款列表
  {
    path: "/nonCar/clauseFine/propertycList",
    name: "propertyCList",
    component: () => import("@src/views/nonCar/clauseFine/propertycList")
  },
  //货运无忧责任免除
  {
    path: "/nonCar/clauseFine/Deductible",
    name: "Deductible",
    component: () => import("@src/views/nonCar/clauseFine/Deductible")
  },
  // 百万医疗个人版保险条款
  {
    path: "/nonCar/clauseFine/personalTerms",
    name: "personalTerms",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms")
  },
  // 防癌保险条款
  {
    path: "/nonCar/clauseFine/bxtk",
    name: "bxtk",
    component: () => import("@src/views/nonCar/clauseFine/bxtk")
  },
  // 百万医疗个人版保险条款-个人医疗保险条款（C款）
  {
    path: "/nonCar/clauseFine/personalTerms_Ckuan",
    name: "personalTerms_Ckuan",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms_Ckuan")
  },
  // 百万医疗个人版保险条款-大地通达公共交通工具意外伤害保险条款
  {
    path: "/nonCar/clauseFine/personalTerms_mass",
    name: "personalTerms_mass",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms_mass")
  },
  // 百万医疗个人版保险条款-附加扩展恶性肿瘤特需医疗保险条款
  {
    path: "/nonCar/clauseFine/personalTerms_AdditionalExtensions",
    name: "personalTerms_AdditionalExtensions",
    component: () =>
      import("@src/views/nonCar/clauseFine/personalTerms_AdditionalExtensions")
  },
  // 百万医疗个人版保险条款-附加个人恶性肿瘤院外特种药品费用医疗保险条款
  {
    path: "/nonCar/clauseFine/personalTerms_drugs",
    name: "personalTerms_drugs",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms_drugs")
  },
  // 百万医疗个人版保险条款-附加个人恶性肿瘤扩展质子重离子医疗保险条款
  {
    path: "/nonCar/clauseFine/personalTerms_proton",
    name: "personalTerms_proton",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms_proton")
  },
  // 百万医疗个人版保险条款-附加个人恶性肿瘤扩展质子重离子医疗保险条款
  {
    path: "/nonCar/clauseFine/personalTerms_goTo",
    name: "personalTerms_goTo",
    component: () => import("@src/views/nonCar/clauseFine/personalTerms_goTo")
  },
  // 百万医疗个人版投保须知
  {
    path: "/nonCar/clauseFine/InsureNotice",
    name: "InsureNotice",
    component: () => import("@src/views/nonCar/clauseFine/InsureNotice")
  },
  // 百万医疗个人版投保人声明
  {
    path: "/nonCar/clauseFine/InsureStatement",
    name: "InsureStatement",
    component: () => import("@src/views/nonCar/clauseFine/InsureStatement")
  },
  // 百万医疗个人版特别约定
  {
    path: "/nonCar/clauseFine/Aboutpersonal",
    name: "Aboutpersonal",
    component: () => import("@src/views/nonCar/clauseFine/Aboutpersonal")
  },
  // 百万医疗责任免除
  {
    path: "/nonCar/clauseFine/liabilityExempt",
    name: "liabilityExempt",
    component: () => import("@src/views/nonCar/clauseFine/liabilityExempt")
  },
  //产品海报
  {
    path: "/nonCar/healthInsurance/productPoster",
    name: "productPoster",
    component: () => import("@src/views/nonCar/healthInsurance/productPoster")
  },
  // 财产险-货运无忧---1
  {
    path: "/nonCar/propertyInsurance/worryFree",
    name: "proWorryFree",
    // meta: {
    //   keepAlive: true,
    // },
    component: () => import("@src/views/nonCar/propertyInsurance/worryFree")
  },
  // // 财产险-货运无忧---1
  // {
  //   path: "/nonCar/propertyInsurance/worryFree1",
  //   name: "proWorryFree1",
  //   component: () => import("@src/views/nonCar/propertyInsurance/worryFree1")
  // },
  //财产险-投保信息填写---1
  {
    path: "/nonCar/propertyInsurance/insuranceInformation",
    name: "proInsuranceInformation",
    component: () =>
      import("@src/views/nonCar/propertyInsurance/insuranceInformation")
  },
  //财产险-保单确认----1
  {
    path: "/nonCar/propertyInsurance/propertyInsuranceConfirm",
    name: "propertyInsuranceConfirm",
    component: () =>
      import("@src/views/nonCar/propertyInsurance/propertyInsuranceConfirm")
  },
  //  理赔指南

  {
    path: "/nonCar/common/claim",
    name: "claim",
    component: () => import("@src/views/nonCar/common/claim")
  },
  // 产品配置----产品详情
  {
    path: "/nonCar/prIndex",
    name: "prIndex",
    component: () => import("@src/views/nonCar/autoConfig/prIndex")
  },
  // 产品配置----产品海报
  {
    path: "/nonCar/prPoster",
    name: "prPoster",
    component: () => import("@src/views/nonCar/autoConfig/prPoster")
  },
  // 产品配置----制作计划书
  {
    path: "/nonCar/prPlan",
    name: "prPlan",
    component: () => import("@src/views/nonCar/autoConfig/prPlan")
  },
  // 产品配置----保险条款列表
  {
    path: "/nonCar/autoConfig/clause/clauseTerms",
    name: "clauseTerms",
    component: () => import("@src/views/nonCar/autoConfig/clause/clauseTerms")
  },
  // 产品配置----条款pdf展示
  {
    path: "/nonCar/autoConfig/clause/clausePdf",
    name: "clausePdf",
    component: () => import("@src/views/nonCar/autoConfig/clause/clausePdf")
  },
  // 产品配置----相关DOC展示
  {
    path: "/nonCar/autoConfig/clause/clauseDoc",
    name: "clauseDoc",
    component: () => import("@src/views/nonCar/autoConfig/clause/clauseDoc")
  },
  // 产品配置----健康须知
  {
    path: "/nonCar/autoConfig/healthNotice",
    name: "prHealthNotice",
    component: () => import("@src/views/nonCar/autoConfig/healthNotice")
  },
  //产品配置--- 支付转发确认页面 nonCar\dynamicPayment\paymentForward
  {
    name: "prPaymentForward",
    path: "/nonCar/dynamicPayment/prPaymentForward",
    component: () => import("@src/views/nonCar/dynamicPayment/prPaymentForward")
  },
  // 产品配置---保单支付页面  paymentOrder
  {
    name: "prPaymentOrder",
    path: "/nonCar/dynamicPayment/prPaymentOrder",
    component: () => import("@src/views/nonCar/dynamicPayment/prPaymentOrder")
  }
];

export default nonCar;
