const renewal = [
  {
    name: "renewal",
    path: "/renewal",
    redirect: "/renewalList"
  },
  //续保订单管理
  {
    name: "renewalList",
    path: "/renewalList",
    component: () =>
      import("@src/views/renewal/renewalManager/renewalList.vue"),
    meta: {
      keepAlive: true
    }
  },
  //订单管理筛选
  {
    name: "renewalContactListScreen",
    path: "/renewalContactListScreen",
    component: () =>
      import("@src/views/renewal/renewalManager/renewalContactListScreen.vue")
  },
  //续保订单详情
  {
    name: "renewalContactDetails",
    path: "/renewalContactDetails",
    component: () =>
      import("@src/views/renewal/renewalManager/renewalContactDetails.vue")
  },
  //续保认领更多信息
  {
    name: "renewalClaimDetails",
    path: "/renewalClaimDetails",
    component: () =>
      import("@src/views/renewal/renewalManager/renewalClaimDetails.vue")
  },
  //我的续保跟踪记录
  {
    name: "renewalTrackRecord",
    path: "/renewalTrackRecord",
    component: () =>
      import("@src/views/renewal/renewalManager/renewalTrackRecord.vue")
  },
  //操作轨迹
  {
    name: "operationTrajectory",
    path: "/operationTrajectory",
    component: () =>
      import("@src/views/renewal/renewalManager/operationTrajectory.vue")
  },
  //续保消息
  {
    name: "renewalNotice",
    path: "/renewalNotice",
    component: () =>
      import("@src/views/renewal/renewalNotice/renewalNotice.vue")
  },
  //续保消息提醒入口
  {
    name: "renewalTipsNotice",
    path: "/renewalTipsNotice",
    component: () =>
      import("@src/views/renewal/renewalNotice/renewalTipsNotice.vue")
  },
  //续保消息提醒详情
  {
    name: "renwalTipsDetails",
    path: "/renwalTipsDetails",
    component: () =>
      import("@src/views/renewal/renewalNotice/renwalTipsDetails.vue")
  }
];
export default renewal;
