// 客户管理
const customer = [{
  name: 'customer',
  path: '/customer',
  redirect: 'customer/myCustomer'
},
  //理赔进度和详情
  {
    path: '/customer/customerClaimProgress',
    name: 'customerClaimProgress',
    component: () =>
      import('@src/views/customer/followUpremind/customerClaimProgress')
  },
  //生日贺卡
  {
    path: '/customer/birthdayCelebrate',
    name: 'birthdayCelebrate',
    component: () =>
      import('@src/views/customer/birthdayCelebrate/birthdayCelebrate')
  },
  //   客户信息
  {
    path: '/customer/customerEquity',
    name: 'customerEquity',
    component: () =>
      import('@src/views/customer/InteractiveRights/customerEquity.vue'),
  },
  //   编辑客户
  {
    path: '/customer/compileCustomer',
    name: 'compileCustomer',
    component: () =>
      import('@src/views/customer/InteractiveRights/compileCustomer.vue')
  },
  //   互动纪录--新增
  {
    path: '/customer/interactAddNew',
    name: 'interactAddNew',
    component: () =>
      import('@src/views/customer/InteractiveRights/interactAddNew.vue')
  },
  //   互动纪录--编辑
  {
    path: '/customer/interactEdit',
    name: 'interactEdit',
    component: () =>
      import('@src/views/customer/InteractiveRights/interactEdit.vue')
  },
  //   新增互动纪录
  {
    path: '/customer/interactInput',
    name: 'interactInput',
    component: () =>
      import('@src/views/customer/InteractiveRights/interactInput.vue')
  },
  //   影像上传
  {
    path: '/customer/portraitUpload',
    name: 'portraitUpload',
    component: () =>
      import('@src/views/customer/InteractiveRights/portraitUpload.vue')
  },
  //   销售助手
  {
    path: '/customer/salesAssistant',
    name: 'salesAssistant',
    component: () =>
      import('@src/views/customer/InteractiveRights/salesAssistant.vue')
  },
  //   客户服务-跟进提醒
  {
    path: '/customer/followRemind',
    name: 'followRemind',
    component: () =>
      import('@src/views/customer/followUpremind/followRemind.vue')
  },
  //权益提醒
  {
    path: '/customer/registeredView',
    name: 'registeredView',
    component: () =>
      import('@src/views/customer/followUpremind/registeredView.vue')
  },
  //带注册提醒
  {
    path: '/customer/queryRightsRemind',
    name: 'queryRightsRemind',
    component: () =>
      import('@src/views/customer/followUpremind/queryRightsRemind.vue')
  },
  //   客户管理-我客户
  {
    path: '/customer/myCustomer',
    name: 'myCustomer',
    component: () =>
      import('@src/views/customer/customerManagement/myCustomer.vue')
  },
  //   客户管理-新增客户
  {
    path: '/customer/increased',
    name: 'increased',
    component: () =>
      import('@src/views/customer/customerManagement/increased.vue')
  },
  //   客户管理-添加标签
  {
    path: '/customer/addLabel',
    name: 'addLabel',
    component: () =>
      import('@src/views/customer/customerManagement/addLabel.vue')
  },
  //   客户管理-搜索页
  {
    path: '/customer/customerSearch',
    name: 'customerSearch',
    component: () =>
      import('@src/views/customer/customerManagement/customerSearch.vue')
  },
  //   查违章-客户授权
  {
    path: '/customer/authorization',
    name: 'authorization',
    component: () =>
      import('@src/views/customer/claimIllegal/authorization.vue')
  },
  //   理赔查询-列表
  {
    path: '/customer/claimOrders',
    name: 'claimOrders',
    component: () => import('@src/views/customer/claimIllegal/claimOrders.vue')
  },
  //   理赔查询-搜索
  {
    path: '/customer/claimQuery',
    name: 'claimQuery',
    component: () => import('@src/views/customer/claimIllegal/claimQuery.vue')
  },
  //   查违章-未授权
  {
    path: '/customer/noAuthorization',
    name: 'noAuthorization',
    component: () =>
      import('@src/views/customer/claimIllegal/noAuthorization.vue')
  },
  //隐私服务协议
  {
    path: '/customer/privacyDetal',
    name: 'privacyDetal',
    component: () =>
      import('@src/views/customer/claimIllegal/privacyDetal.vue')
  },
  //客户积分 integralInfoView
  {
    path: '/customer/customerStartLevel',
    name: 'customerStartLevel',
    component: () =>
      import('@src/views/customer/InteractiveRights/customerStartLevel.vue')
  },
  //积分详情
  {
    path: '/customer/integralInfoView',
    name: 'integralInfoView',
    component: () =>
      import('@src/views/customer/InteractiveRights/integralInfoView.vue')
  },
  //保险信息
  {
    path: '/customer/insuranceInfoView',
    name: 'insuranceInfoView',
    component: () =>
      import('@src/views/customer/customerManagement/insuranceInfoView.vue')
  },
  //如何提升积分
  {
    path: '/customer/howToIntegralView',
    name: 'howToIntegralView',
    component: () =>
      import('@src/views/customer/InteractiveRights/howToIntegralView.vue')
  },
  //车辆信息
  {
    path: '/customer/carListView',
    name: 'carListView',
    component: () =>
      import('@src/views/customer/InteractiveRights/carListView.vue')
  },
  //注册会员带有邀请按钮
  {
    path: '/customer/RegisteredManView',
    name: 'RegisteredManView',
    component: () =>
      import('@src/views/customer/followUpremind/RegisteredManView.vue')
  },
  //注册会员无邀请按钮
  {
    path: '/customer/RegisteredManViewItem',
    name: 'RegisteredManViewItem',
    component: () =>
      import('@src/views/customer/followUpremind/RegisteredManViewItem.vue')
  },
  //加载content
  {
    path: '/customer/contentView',
    name: 'contentView',
    component: () =>
      import('@src/views/customer/followUpremind/contentView')
  },
];

export default customer;
