import store from '@/store';
// 业绩模块;
const performances = [
  //个人和团队业绩
  {
    name: "performance",
    path: "/performance",
    component: () => import("@src/views/performance/index"),
    // beforeEnter: (to, from, next) => {
    //   /**
    //    * 进入业绩页面判读userCode是否包含字母 有字母是代理人
    //    * 如果是代理人跳代理业绩人页面
    //    */
    //   let userCode = store.state.loginInfo.userCode;
    //   // console.log("state", store.state);
    //   console.log(userCode);
    //   if (/^[^A-Za-z]*$/g.test(userCode)) {
    //     next();
    //   } else {
    //     next({ path: "/agentPerformance" });
    //   }
    // }
  },
  //代理人业绩
  {
    name: "agentPerformance",
    path: "/agentPerformance",
    component: () =>
      import("@src/views/performance/performance/agentPerformance")
  },
  //薪酬查询登录
  {
    name: "salaryQueryLogin",
    path: "/performance/salary/salaryQueryLogin",
    component: () => {
      return import("@src/views/performance/salary/salaryQueryLogin");
    }
  },
  //薪酬查询更改密码
  {
    name: "salaryChangeQueryPassword",
    path: "/performance/salary/salaryChangeQueryPassword",
    component: () => {
      return import("@src/views/performance/salary/salaryChangeQueryPassword");
    }
  },
  //责任书列表
  {
    name: "responsibilityList",
    path: "/performance/responsibility/responsibilityList",
    component: () => {
      return import("@src/views/performance/responsibility/responsibilityList");
    }
  },
  //责任书编辑
  {
    name: "responsibilityEdit",
    path: "/performance/responsibility/responsibilityEdit",
    component: () => {
      return import("@src/views/performance/responsibility/responsibilityEdit");
    },
    meta: {
      keepAlive: true
    }
  },
  //责任书详情-已签署
  {
    name: "responsibilityDetail",
    path: "/performance/responsibility/responsibilityDetail",
    component: () => {
      return import(
        "@src/views/performance/responsibility/responsibilityDetail"
      );
    }
  },
  //承诺书
  {
    name: "promiseBook",
    path: "/performance/responsibility/promiseBook",
    component: () => {
      return import("@src/views/performance/responsibility/promiseBook");
    }
  },
  //排名详情
  {
    name: "rankDetail",
    path: "/rankDetail",
    component: () => import("@src/views/performance/performance/rankDetail")
  },
  //高级搜索
  {
    name: "rateSearch",
    path: "/rateSearch",
    component: () => import("@src/views/performance/performance/rateSearch")
  },
  //团队个人信息
  {
    name: "memberInfo",
    path: "/memberInfo",
    component: () => import("@src/views/performance/performance/memberInfo")
  },
  //个人代理人
  {
    name: "personal_agent",
    path: "/personal_agent",
    component: () => import("@src/views/performance/agent/personal_agent"),
    meta: {
      keepAlive: true
    }
  },
  //手续费提现
  {
    name: "costReflect",
    path: "/costReflect",
    component: () => import("@src/views/performance/agent/costReflect")
  },
  //提现详情
  {
    name: "costReflectDetails",
    path: "/costReflectDetails",
    component: () => import("@src/views/performance/agent/costReflectDetails")
  },
  //收入明细
  {
    name: "incomeDetails",
    path: "/incomeDetails",
    component: () => import("@src/views/performance/agent/incomeDetails")
  }
];

export default performances;
