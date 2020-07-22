// 客户管理
const vehicle = [
  //    车险
  //   车辆信息
  {
    name: "vehicle",
    path: "/vehicle",
    ///vehicleInfo/vehicleInformatization
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@/views/vehicle/vehicleInfo/vehicleInformatization")
  },
  // 车辆类型选择
  {
    name: "selectVehicle",
    path: "/selectVehicle",
    component: () => import("@/views/vehicle/selectVehicle")
  },
  //   车型
  {
    path: "/vehicleInfo/vehicleModels",
    name: "vehicleModels",
    component: () => import("@/views/vehicle/vehicleInfo/vehicleModels.vue")
  },
  //   车辆信息-更多
  {
    path: "/vehicleInfo/vehicleMore",
    name: "vehicleMore",
    component: () => import("@/views/vehicle/vehicleInfo/vehicleMore.vue")
  },
  //   渠道信息
  {
    path: "/vehicleInfo/vehicleChannel",
    name: "vehicleChannel",
    meta: {
      keepAlive: true
    },
    component: () => import("@/views/vehicle/vehicleInfo/vehicleChannel.vue")
  },
  // // 车险 - 保单批改
  // {
  //   path: "/order/insureConfirm/orderInsureCorrect",
  //   name: "orderInsureCorrect",
  //   component: () =>
  //     import("@src/views/order/insureConfirm/orderInsureCorrect")
  // },
  //  Q码维护
  {
    path: "/QCodeMaintain",
    name: "QCodeMaintain",
    component: () => import("@src/views/vehicle/vehicleInfo/QCodeMaintain.vue")
  },
  //   代理人
  {
    path: "/vehicleAgentList",
    name: "vehicleAgentList",
    meta: {
      keepAlive: true
    },
    component: () => import("@/views/vehicle/vehicleInfo/vehicleAgentList.vue")
  },
  //   添加代理人
  {
    path: "/vehicleAddAgent",
    name: "vehicleAddAgent",
    component: () => import("@/views/vehicle/vehicleInfo/vehicleAddAgent.vue")
  },

  //   选择产品模块--获取信息
  {
    path: "/selectProduct/insurancePlans",
    name: "insurancePlans",
    component: () => import("@/views/vehicle/selectProduct/insurancePlans.vue"),
    meta: {
      keepAlive: true
    }
  },
  //   整单优惠
  {
    path: "/fullpreferential",
    name: "fullpreferential",
    component: () =>
      import("@/views/vehicle/selectProduct/fullpreferential.vue")
  },
  //   整单优惠
  {
    path: "/heavygoodsRating",
    name: "heavygoodsRating",
    component: () =>
      import("@/views/vehicle/selectProduct/heavygoodsRating.vue"),
    meta: {
      keepAlive: true
    }
  },
  //   车船税
  {
    path: "/selectProduct/vehicleTax",
    name: "vehicleTax",
    component: () => import("@/views/vehicle/selectProduct/vehicleTax.vue")
  },
  //   商业险选择
  {
    path: "/selectProduct/vehicleCommercial",
    name: "vehicleCommercial",
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@/views/vehicle/selectProduct/vehicleCommercial.vue")
  },
  //   联合销售选择
  {
    path: "/selectProduct/vehicleWealthAccRisk",
    name: "vehicleWealthAccRisk",
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@/views/vehicle/selectProduct/vehicleWealthAccRisk.vue")
  },
  //   报价次数
  {
    path: "/quotationNumber",
    name: "quotationNumber",
    component: () => import("@/views/vehicle/selectProduct/quotationNumber.vue")
  },
  //   商业险浮动系数及原因
  {
    path: "/floatingCoefficient",
    name: "floatingCoefficient",
    component: () =>
      import("@/views/vehicle/selectProduct/floatingCoefficient.vue")
  },
  //   新增设备损失险
  {
    path: "/equipmentDamage",
    name: "equipmentDamage",
    component: () => import("@/views/vehicle/selectProduct/equipmentDamage.vue")
  },
  //  投被保人信息
  {
    path: "/insureInformation",
    name: "insureInformation",
    meta: {
      keepAlive: true
    },
    component: () => import("@/views/vehicle/selectProduct/insureInformation")
  },
  // 上年理赔信息
  {
    path: "/lastClaim",
    name: "lastClaim",
    component: () => import("@/views/vehicle/selectProduct/lastClaim")
  },
  //  投保确认
  {
    path: "/vehicle/insureConfirm/vehicleInsureConfirm",
    name: "vehicleInsureConfirm",
    meta: {
      keepAlive: true
    },
    component: () =>
      import("@/views/vehicle/insureConfirm/vehicleInsureConfirm")
  },
  //   星级权益
  {
    path: "/vehicle/insureConfirm/insureStarRight",
    name: "insureStarRight",
    component: () => import("@/views/vehicle/insureConfirm/insureStarRight")
  },
  {
    path: "/vehicle/insureConfirm/oldSatr",
    name: "oldSatr",
    component: () => import("@/views/vehicle/insureConfirm/oldSatr")
  },
  //   更多信息
  {
    path: "/insureMoreInfo",
    name: "insureMoreInfo",
    component: () => import("@/views/vehicle/insureConfirm/insureMoreInfo")
  },
  //   核保信息
  {
    path: "/insureConfirm",
    name: "insureConfirm",
    component: () => import("@/views/vehicle/insureConfirm/insureConfirm")
  },

  //   保单批改
  {
    path: "/orderInsureCorrect",
    name: "orderInsureCorrect",
    component: () => import("@/views/vehicle/insureConfirm/orderInsureCorrect")
  },

  //   投保确认-约定内容
  {
    path: "/insureAppointContent",
    name: "insureAppointContent",
    component: () =>
      import("@/views/vehicle/insureConfirm/insureAppointContent")
  },
  //   投保确认-约定内容
  {
    path: "/insureAppointConfirm",
    name: "insureAppointConfirm",
    component: () =>
      import("@/views/vehicle/insureConfirm/insureAppointConfirm")
  },
  /*

  //   增值服务特别约定
  */
  {
    path: "/insureAppoint",
    name: "insureAppoint",
    component: () => import("@/views/vehicle/insureConfirm/insureAppoint")
  },
  //   增值套餐A
  {
    path: "/insureAdditionService",
    name: "insureAdditionService",
    component: () =>
      import("@/views/vehicle/insureConfirm/insureAdditionService")
  },
  //   反洗钱信息
  {
    path: "/insureAntiMoney",
    name: "insureAntiMoney",
    component: () => import("@/views/vehicle/insureConfirm/insureAntiMoney")
  },
  // //   增值套餐A
  // {
  //   path: '/insureAdditionService',
  //   name: 'insureAdditionService',
  //   component: () => import('@/views/vehicle/insureConfirm/insureAdditionService')
  // }
  //   特别约定-增值服务特别约定
  {
    path: "/insureInfomation",
    name: "insureInfomation",
    component: () => import("@/views/vehicle/insureConfirm/insureInfomation")
  },
  // 商业计划书
  // {
  //   path: "/proposal",
  //   name: "proposal",
  //   component: () => import("@/views/vehicle/insureConfirm/proposal")
  // },
  // 车险影像上传
  {
    path: "/vehicle/insureConfirm/vehiclePortraitUpload",
    name: "vehiclePortraitUpload",
    component: () =>
      import("@/views/vehicle/insureConfirm/vehiclePortraitUpload")
  },
  //   核保信息
  {
    path: "/orderVehicleOCR",
    name: "orderVehicleOCR",
    component: () => import("@/views/vehicle/businessPlan/orderVehicleOCR")
  },
  //车辆证件扫描结果
  {
    path: "/carOCR",
    name: "carOCR",
    meta: {
      keepAlive: true
    },
    component: () => import("@/views/vehicle/businessPlan/carOCR")
  },

  //   支付宝短信支付
  {
    path: "/paymentByAlipayMessage",
    name: "paymentByAlipayMessage",
    component: () => import("@/views/vehicle/payment/paymentByAlipayMessage")
  },
  //   银行卡支付
  {
    path: "/paymentByBankCard",
    name: "paymentByBankCard",
    component: () => import("@/views/vehicle/payment/paymentByBankCard")
  },
  //   二维码支付
  {
    path: "/paymentByQRCode",
    name: "paymentByQRCode",
    component: () => import("@/views/vehicle/payment/paymentByQRCode")
  },
  {
    path: "/paymentByCA",
    name: "paymentByCA",
    component: () => import("@/views/vehicle/payment/paymentByCA")
  },
  //    验证码申请P1页面
  {
    path: "/paymentCodeFailed",
    name: "paymentCodeFailed",
    component: () => import("@/views/vehicle/payment/paymentCodeFailed")
  },

  // //    订单支付
  // {
  //   path: "/paymentOrder",
  //   name: "paymentOrder",
  //   component: () => import("@/views/vehicle/payment/paymentOrder")
  // },
  //    补录验证码
  {
    path: "/paymentRecordCode",
    name: "paymentRecordCode",
    component: () => import("@/views/vehicle/payment/paymentRecordCode")
  },
  //    选择支付方式
  {
    path: "/paymentToast",
    name: "paymentToast",
    component: () => import("@/views/vehicle/payment/paymentToast")
  },
  //    选择微信打开
  {
    path: "/paymentToastWechat",
    name: "paymentToastWechat",
    component: () => import("@/views/vehicle/payment/paymentToastWechat")
  },
  //    获取验证码弹窗
  {
    path: "/paymentVertifyCode",
    name: "paymentVertifyCode",
    component: () => import("@/views/vehicle/payment/paymentVertifyCode")
  },
  // 商业计划书-内部
  {
    path: "/businessPlanInside",
    name: "businessPlanInside",
    component: () => import("@/views/vehicle/businessPlan/businessPlanInside")
  },
  // 保单支付
  {
    name: "paymentIndex",
    path: "/payment/paymentIndex",
    meta: {
      keepAlive: true
    },
    component: () => import("@/views/vehicle/payment/paymentIndex")
  },
  // 商业计划书-分享
  {
    path: "/businessPlanShare",
    name: "businessPlanShare",
    component: () => import("@/views/vehicle/businessPlan/businessPlanShare")
  },
  // 人工核保
  {
    path: "/temp",
    name: "temp",
    component: () => import("@/views/vehicle/insureConfirm/temp")
  },
  // 报价分享
  {
    path: "/quoteShare",
    name: "quoteShare",
    component: () => import("@/views/vehicle/common/quoteShare")
  },
  // 报价分享
  {
    path: "/quoteInfoShare",
    name: "quoteInfoShare",
    component: () => import("@/views/vehicle/common/quoteInfoShare")
  },
  // 保单详情分享
  {
    path: "/insureInfo",
    name: "insureInfo",
    component: () => import("@/views/vehicle/common/insureInfo")
  },
  // 支付分享
  {
    path: "/paymentShare",
    name: "paymentShare",
    component: () => import("@/views/vehicle/common/paymentShare")
  }
];

export default vehicle;
