/*
 * @Author: King
 * @Page:
 * @Date: 2020-03-24 15:05:12
 * @Last Modified by: King
 * @Last Modified time: 2020-06-10 19:38:23
 * @FilePath: /src/router/index.js
 */
import Vue from "vue";
import VueRouter from "vue-router";
//业绩
import performances from "./module/performance";
//续保
import renewal from "./module/renewal";
//客户管理
import customers from "./module/customer";
//车险
import vehicle from "./module/vehicle";
//非车险
import nonCar from "./module/nonCar";
// 营销活动
import activities from "./module/activities";
// 订单管理
import order from "./module/order";
//保单查询
import policy from "./module/policy";
// import Test from "../Test.vue";
import store from "../store";

Vue.use(VueRouter);

const routes = [
  ...performances,
  ...renewal,
  ...customers,
  ...vehicle,
  ...nonCar,
  ...activities,
  ...order,
  {
    name: "index",
    path: "/",
    alias: "/index",
    component: () => import("@src/views/index")
  },
  // {
  //   name: "test",
  //   path: "/test",
  //   component: () => import("@src/Test")
  // },
  {
    name: "shell",
    path: "/shell",
    component: () => import("@src/views/ShellFunDemo")
  },
  {
    name: "login",
    path: "/login",
    component: () => import("@src/views/login/login")
  },
  {
    name: "download",
    path: "/download",
    component: () => import("@src/views/download/download")
  }
];
const router = new VueRouter({
  mode: "hash", //history hash
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return {
        selector: to.hash
      };
    }
    return false;
  }
});

// 记录用户浏览页面的地址的轨迹
router.routesArr = [];
// 后退到上一层页面，不是上一个历史
router.backward = function(stepNum) {
  typeof stepNum !== "number" && (stepNum = 1);
  stepNum = Math.abs(stepNum);
  let index = router.routesArr.length - 1 - stepNum;
  let route = router.routesArr[0];
  if (index > 0) {
    route = router.routesArr[index];
  }
  router.push({
    name: route.name,
    // path: route.path,
    params: {
      isBack: true
    },
    query: route.query
  });
};
// 判断某一个路由是否在轨迹中出现
router.getIndexOf = path => {
  for (let i in router.routesArr) {
    let route = router.routesArr[i];
    if (route.path === path) {
      return i;
    }
  }
  return -1;
};
/* 路由异常错误处理，尝试解析一个异步组件时发生错误，重新渲染目标页面 */
// router.onError(error => {
//   const pattern = /Loading chunk (\d)+ failed/g;
//   const isChunkLoadFailed = error.message.match(pattern);
//   const targetPath = router.history.pending.fullPath;
//   if (isChunkLoadFailed) {
//     router.replace(targetPath);
//   }
//   if (/ERR_CONNECTION_REFUSED/.test(error.message)) {
//     alert("网络繁忙,请稍后重试");
//   }
// });
// 记录用户浏览页面的地址的轨迹，只保留单一节点
router.beforeEach((to, from, next) => {
  console.log(window.location.href);
  var ua = navigator.userAgent.toLowerCase();
  console.log(ua);
  if (vehicle.find(item => item.name == from.name)) {
    if (!vehicle.find(item => item.name == to.name)) {
      store.commit("vehicle/initVehicle");
    }
  }
  if (to.path === "/") {
    if (window.WebViewJavascriptBridge) {
      Vue.prototype.$native.openHome("", data => {});
    }
  }
  let url = window.location.href;
  if (/\?.+#/.test(url)) {
    // 判断是否携带了 from 参数，这一步灵活变通，只要能判断出是从微信分享链接进来的就 OK
    url = url.replace(/\?.+#/, "#"); // 利用正则表达式去掉微信携带的 ?from=singlemessage&isappinstalled=0 这串参数，如果这串参数对于你当前的页面有用处的话，可以重新拼接到你正常的链接后面去
    window.location.href = url; // 重定向到正常链接
    return;
  }
  let index = router.getIndexOf(to.path);
  to.params.__back__ = false;
  if (index !== -1) {
    router.routesArr.splice(index, 900);
    to.params.__back__ = true;
  }
  router.routesArr.push(to);
  next();
});

export default router;
