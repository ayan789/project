const order = [
  // 非车模块
  {
    name: "order",
    path: "/order",
    redirect: "/order/orderList/orderList",
  },
  // 保单查询表单
  {
    path: "/order/findOrder/findOrder",
    name: "findOrder",
    component: () => import("@src/views/order/findOrder/findOrder"),
  },
  // 保单查询列表
  {
    path: "/order/findOrder/detailList",
    name: "detailList",
    component: () => import("@src/views/order/findOrder/detailList"),
  },
  // 保单查询详情-车险
  {
    path: "/order/findOrder/detailOrder",
    name: "detailOrder",
    component: () => import("@src/views/order/findOrder/detailOrder"),
  },
  // 保单查询详情-财产险
  {
    path: "/order/findOrder/detailOrderNo",
    name: "detailOrderNo",
    component: () => import("@src/views/order/findOrder/detailOrderNo"),
  },
  // 保单查询详情-意健险
  {
    path: "/order/findOrder/detailOrderY",
    name: "detailOrderY",
    component: () => import("@src/views/order/findOrder/detailOrderY"),
  },
  // 车险列表-电子发票客户授权
  {
    path: "/order/orderList/electronicInvoice",
    name: "orderList",
    component: () => import("@src/views/order/orderList/electronicInvoice"),
  },
  // 车险列表-电子保单客户授权
  {
    path: "/order/orderList/authorization",
    component: () => import("@src/views/order/orderList/authorization"),
  },
  // 新增订单列表--覆盖现有的
  {
    path: "/order/orderList/orderList",
    name: "orderLists",
    component: () => import("@src/views/order/orderList/orderLists"),
  },
  // 财产险-订单详情-已失效
  {
    path: "/order/propertyInsurance/orderDetailUnused",
    name: "proOrderDetailUnused",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailUnused"),
  },
  // 财产险-订单详情-未支付
  {
    path: "/order/propertyInsurance/orderDetailUnPay",
    name: "proOrderDetailUnPay",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailUnPay"),
  },
  // 财产险-订单详情-暂存-未下单
  {
    path: "/order/propertyInsurance/orderDetailTempSave",
    name: "proOrderDetailTempSave",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailTempSave"),
  },
  // 财产险-订单详情-暂存-已下单
  {
    path: "/order/propertyInsurance/orderDetailTempSaveOrdered",
    name: "proOrderDetailTempSaveOrdered",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailTempSaveOrdered"),
  },
  // 财产险-订单详情-待核保
  {
    path: "/order/propertyInsurance/orderDetailUnderwritten",
    name: "proOrderDetailUnderwritten",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailUnderwritten"),
  },

  // 财产险-订单详情-核保完成
  {
    path: "/order/propertyInsurance/orderDetailUnderwriting",
    name: "proOrderDetailUnderwriting",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailUnderwriting"),
  },
  // 财产险-订单详情-保障中
  {
    path: "/order/propertyInsurance/orderDetailGuaranteed",
    name: "proOrderDetailGuaranteed",
    component: () =>
      import("@src/views/order/propertyInsurance/orderDetailGuaranteed"),
  },
  // 财产险-影像上传
  {
    path: "/order/propertyInsurance/orderPortraitUpload",
    name: "proOrderPortraitUpload",
    component: () =>
      import("@src/views/order/propertyInsurance/orderPortraitUpload"),
  },

  // 健康险-订单详情-已失效
  {
    path: "/order/healthInsurance/orderDetailUnused",
    name: "heOrderDetailUnused",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailUnused"),
  },
  // 健康险-订单详情-未支付
  {
    path: "/order/healthInsurance/orderDetailUnPay",
    name: "heOrderDetailUnPay",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailUnPay"),
  },
  // 健康险-订单详情-暂存-未下单
  {
    path: "/order/healthInsurance/orderDetailTempSave",
    name: "heOrderDetailTempSave",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailTempSave"),
  },
  // 健康险-订单详情-暂存-已下单
  {
    path: "/order/healthInsurance/orderDetailTempSaveOrdered",
    name: "heOrderDetailTempSaveOrdered",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailTempSaveOrdered"),
  },
  // 健康险-订单详情-待核保
  {
    path: "/order/healthInsurance/orderDetailUnderwritten",
    name: "heOrderDetailUnderwritten",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailUnderwritten"),
  },

  // 健康险-订单详情-核保完成
  {
    path: "/order/healthInsurance/orderDetailUnderwriting",
    name: "heOrderDetailUnderwriting",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailUnderwriting"),
  },
  // 健康险-订单详情-保障中
  {
    path: "/order/healthInsurance/orderDetailGuaranteed",
    name: "heOrderDetailGuaranteed",
    component: () =>
      import("@src/views/order/healthInsurance/orderDetailGuaranteed"),
  },
  // 车险 - 订单详情
  {
    path: "/order/vehicleInsurance/insureOrderDetail",
    name: "insureOrderDetail",
    component: () =>
      import("@src/views/order/vehicleInsurance/insureOrderDetail"),
  },
  // 车险 - 订单详情-车船税
  {
    path: "/vehicleTtax",
    name: "vehicleTtax",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/vehicleTtax"),
  },
  // 车险 - 订单详情-整单优惠
  {
    path: "/fullOffer",
    name: "fullOffer",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/fullOffer"),
  },
  // 车险 - 订单详情-险别信息
  {
    path: "/InsuranceInformation",
    name: "InsuranceInformation",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/InsuranceInformation"),
  },
  // 车险 - 订单详情-渠道信息
  {
    path: "/channelInformation",
    name: "channelInformation",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/channelInformation"),
  },
  // 车险 - 订单详情-联合销售
  {
    path: "/machanceAssociation",
    name: "machanceAssociation",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/machanceAssociation"),
  },
  // 车险 - 订单详情-车辆信息
  {
    path: "/vehicleInformation",
    name: "vehicleInformation",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/vehicleInformation"),
  },
  // 车险 - 订单详情-特别约定
  {
    path: "/specialAgreement",
    name: "specialAgreement",
    meta: {
      keepAlive: true,
    },
    component: () =>
      import("@src/views/order/vehicleInsurance/common/specialAgreement"),
  },
  // 车险 - 订单详情-平台信息
  {
    path: "/platformInformation",
    name: "platformInformation",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/platformInformation"),
  },
  // 车险 - 订单详情-客户信息
  {
    path: "/customerInfo",
    name: "customerInfo",
    component: () =>
      import("@src/views/order/vehicleInsurance/common/customerInfo"),
  },
  // 车险 - 订单详情复制
  {
    path: "/order/vehicleInsurance/orderDetailCopy",
    name: "orderDetailCopy",
    component: () =>
      import("@src/views/order/vehicleInsurance/orderDetailCopy"),
  },
  //健康险,财产险-更多信息
  {
    name: "orderMoreInformation",
    path: "/order/common/moreInformation",
    component: () => import("@src/views/order/common/moreInformation"),
  },

  //电子保单操作-发票链接过期
  {
    name: "eleInvoiceApplicationOverdue",
    path: "/order/elePolicy/invoiceApplicationOverdue",
    component: () =>
      import("@src/views/order/elePolicy/invoiceApplicationOverdue"),
  },
  //电子保单操作-电子发票pdf预览
  {
    name: "eleInvoicePreview",
    path: "/order/elePolicy/invoicePreview",
    component: () => import("@src/views/order/elePolicy/invoicePreview"),
  },
  //电子保单操作-未开具电子发票
  {
    name: "eleInvoiceApplicationUnOpened",
    path: "/order/elePolicy/invoiceApplicationUnOpened",
    component: () =>
      import("@src/views/order/elePolicy/invoiceApplicationUnOpened"),
  },
  //电子保单操作-电子保单在线pdf在线预览
  {
    name: "elePolicyPreview",
    path: "/order/elePolicy/policyPreview",
    component: () => import("@src/views/order/elePolicy/policyPreview"),
  },
  //电子保单操作-电子保单在线pdf在线预览
  {
    name: "elePolicyPreview1",
    path: "/order/elePolicy/policyPreview",
    component: () => import("@src/views/order/elePolicy/policyPreview1"),
  },
  // 非车配置 --- 动态的订单详情 --- 保障中
  {
    path: "/order/dynamicOrderDetail/guaranteed",
    name: "guaranteed",
    component: () => import("@src/views/order/dynamicOrderDetail/guaranteed"),
  },
  // 非车配置 --- 动态的订单详情 --- 暂存已下单
  {
    path: "/order/dynamicOrderDetail/tempSaveOrdered",
    name: "tempSaveOrdered",
    component: () =>
      import("@src/views/order/dynamicOrderDetail/tempSaveOrdered"),
  },
  // 非车配置 --- 动态的订单详情 --- 暂存未下单
  {
    path: "/order/dynamicOrderDetail/tempSave",
    name: "tempSave", 
    component: () => import("@src/views/order/dynamicOrderDetail/tempSave"),
  },
  // 非车配置 --- 动态的订单详情 --- 核保完成
  {
    path: "/order/dynamicOrderDetail/underwriting",
    name: "underwriting",
    component: () => import("@src/views/order/dynamicOrderDetail/underwriting"),
  },
  // 非车配置 --- 动态的订单详情 --- 待核保
  {
    path: "/order/dynamicOrderDetail/underwritten",
    name: "underwritten",
    component: () => import("@src/views/order/dynamicOrderDetail/underwritten"),
  },
  // 非车配置 --- 动态的订单详情 --- 待支付
  {
    path: "/order/dynamicOrderDetail/unPay",
    name: "unPay",
    component: () => import("@src/views/order/dynamicOrderDetail/unPay"),
  },
  // 非车配置 --- 动态的订单详情 --- 已失效
  {
    path: "/order/dynamicOrderDetail/unused",
    name: "unused",
    component: () => import("@src/views/order/dynamicOrderDetail/unused"),
  },
];

export default order;
