const activities = [
  // 营销活动-先跳转到活动列表
  {
    name: "activities",
    path: "/activities",
    redirect: "/activities/activity/marketingActivities"
  },
  // 营销活动:活动列表
  {
    name: "marketingActivities",
    path: "/activities/activity/marketingActivities",
    component: () =>
      import("@src/views/activities/activity/marketingActivities")
  },
  // 营销活动-活动详情
  {
    name: "activityDetail",
    path: "/activities/activity/activityDetail",
    component: () => import("@src/views/activities/activity/activityDetail"),
  },
  // 营销活动-我的福利
  {
    name: "myBenefitsRedEnvelope",
    path: "/activities/myBenefits/myBenefitsRedEnvelope",
    component: () =>
      import("@src/views/activities/myBenefits/myBenefitsRedEnvelope")
  },
  // 营销活动-啄木鸟
  {
    name: "woodpecker",
    path: "/activities/activity/woodpecker",
    component: () =>
      import("@src/views/activities/activity/woodpeckerActivities")
  },
  // 营销活动-勇者无敌
  {
    name: "brave",
    path: "/activities/activity/brave",
    component: () =>
      import("@src/views/activities/activity/braveActivities")
  },

];

export default activities;
