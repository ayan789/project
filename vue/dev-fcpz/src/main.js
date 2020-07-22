/* eslint-disable no-console */
/*
 * @Author:
 * @Page: 入口
 * @Date: 2020-03-07 18:28:38
 * @Last Modified by: King
 * @Last Modified time: 2020-04-19 18:04:08
 * @FilePath: \nonCarCongfig\src\main.js
 */
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "lib-flexible";
import "./common/index.less";
import VCharts from "v-charts";
import VideoPlayer from "vue-video-player";
import Popover from "vue-js-popover";
import animated from "animate.css"; // npm install animate.css --save安装，再引入

require("video.js/dist/video-js.css");
require("vue-video-player/src/custom-theme.css");
Vue.use(VideoPlayer);
import {
  Tab,
  Lazyload,
  Tabs,
  IndexBar,
  Tabbar,
  TabbarItem,
  IndexAnchor,
  Step,
  Steps,
  Sticky,
  NavBar,
  Toast,
  Field,
  Icon,
  Image,
  Checkbox,
  Popup,
  CheckboxGroup,
  DropdownItem,
  DropdownMenu,
  DatetimePicker,
  Row,
  Col,
  Cell,
  CellGroup,
  Button,
  Form,
  Swipe,
  SwipeItem,
  TreeSelect,
  Search,
  Collapse,
  CollapseItem,
  Picker,
  Switch,
  Calendar,
  Rate,
  ActionSheet,
  SwipeCell,
  Progress,
  Dialog,
  List,
  RadioGroup,
  Radio,
  Tag,
  PullRefresh,
  Overlay,
  Area,
} from "vant";

Vue.use(VCharts)
  .use(Form)
  .use(Checkbox)
  .use(Popup)
  .use(CheckboxGroup)
  .use(Tab)
  .use(Tabs)
  .use(Steps)
  .use(Step)
  .use(NavBar)
  .use(Toast)
  .use(Field)
  .use(Icon)
  .use(Image)
  .use(DropdownMenu)
  .use(DatetimePicker)
  .use(DropdownItem)
  .use(Row)
  .use(Col)
  .use(Cell)
  .use(Button)
  .use(CellGroup)
  .use(IndexBar)
  .use(TabbarItem)
  .use(Tabbar)
  .use(IndexAnchor)
  .use(Swipe)
  .use(SwipeItem)
  .use(TreeSelect)
  .use(Search)
  .use(Lazyload)
  .use(Collapse)
  .use(CollapseItem)
  .use(Picker)
  .use(Switch)
  .use(ActionSheet)
  .use(Rate)
  .use(Progress)
  .use(Calendar)
  .use(SwipeCell)
  .use(Sticky)
  .use(List)
  .use(Tag)
  .use(RadioGroup)
  .use(Radio)
  .use(Dialog)
  .use(Popover)
  .use(PullRefresh)
  .use(animated)
  .use(Overlay)
  .use(Area);

import GL_Component from "../src/utils/global"; //引入全局组件
import NavBarTemp from "../src/components/navBar.vue";
Vue.use(GL_Component);
Vue.use((Vue) => Vue.component("nav-bar", NavBarTemp));
Toast.setDefaultOptions("loading", {
  message: "加载中...",
  forbidClick: true,
  duration: 2000,
});
Toast.allowMultiple();
import utils from "@src/utils/index";
import validate from "@src/utils/valiformdata";
import localStorageUtil from "@src/utils/localStorage";
// 增加form-create插件
import formCreate, { maker } from "@form-create/ant-design-vue";
Vue.use(formCreate).use(maker);
// 增加ant-design-vue插件
import Antd from "ant-design-vue";
import LocaleProvider from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
Vue.use(LocaleProvider);
Vue.use(Antd);
Vue.prototype.$utils = utils;
Vue.prototype.$validate = validate;
Vue.config.productionTip = false;
import * as native from "./utils/JSBridge";

import {
  globalBack,
  GetRequest,
  getIP,
  setLocalStorageData,
  isNull,
  getCodeOrName,
  isAgent,
  isWeiXin,
  checkBirth,
  getAge,
  getNum,
  getDayNum,
  getDayNumFutrue,
  isDevMode,
  getToRoute,
} from "./utils/index";
import ImageUrl from "./utils/globalImg";
import clipboard from "clipboard";
import html2canvas from "html2canvas";
Vue.prototype.$html2canvas = html2canvas;
Vue.prototype.clipboard = clipboard;
Vue.prototype.$imageUrl = ImageUrl;
Vue.prototype.$native = native;
Vue.prototype.$localStorage = JSON.parse(localStorage.getItem("userInfo"));
Vue.prototype.$globalBack = globalBack;
Vue.prototype.$globalSetLocalStorage = setLocalStorageData;
Vue.prototype.$getIP = getIP;
Vue.prototype.$GetRequest = GetRequest;
Vue.prototype.$isNull = isNull;
Vue.prototype.$isAgent = isAgent;
Vue.prototype.$isWeiXin = isWeiXin; //判断当前是否为微信环境
Vue.prototype.localStorage = localStorageUtil;
Vue.prototype.NAV_BAR_HEIGHT = 77;
Vue.prototype.$getCodeOrName = getCodeOrName;
Vue.prototype.$rootPath = getIP() || "https://msupsale-test.ccic-net.com.cn/#";
Vue.prototype.$checkBirth = checkBirth;
Vue.prototype.$getAge = getAge;
Vue.prototype.$getIdNum = getNum;
Vue.prototype.$getDayNum = getDayNum;
Vue.prototype.$getDayNumFutrue = getDayNumFutrue;
Vue.prototype.$getToRoute = getToRoute;


import vConsole from "vconsole";

if (window.location.host != "msupsale.ccic-net.com.cn" || isDevMode()) {
  Vue.prototype.$vConsole = new vConsole();
}
window.NAV_BAR_HEIGHT = 77;
router.afterEach(() => {
  // 进入页面后，滚动到顶部
  window.scrollTo(0, 0);
});
//全局获取缓存数据
// setLocalStorageData();
Vue.filter("money", function(value) {
  if (isNaN(Number(value))) return "￥0.00";
  return "￥" + Number.parseFloat(value).toLocaleString();
});
Vue.filter("kindName", function(kindName) {
  if (kindName.indexOf("车上人员责任保险") != -1) {
    if (kindName.indexOf("司机") != -1) {
      return "司机险";
    } else if (kindName.indexOf("乘客") != -1) {
      return "乘客险";
    }
  }
  return kindName;
});
Vue.filter("insuranceDate", function(date) {
  return date.length
    ? date.substring(0, date.length - 3).replace(/-/g, "/")
    : "请选择";
});
Vue.filter("insuranceEndDate", function(date) {
  return date.length
    ? date
        .substring(0, date.length - 3)
        .replace(/-/g, "/")
        .replace("23:59", "24:00")
    : "请选择";
});
Vue.directive("drag", {
  //drag自定义指令
  bind: function(el, binding) {
    let agent = window.navigator.userAgent.toLowerCase(),
      start,
      move,
      end;
    if (
      ["android", "iphone", "symbianos", "windows phone", "ipad", "ipod"].some(
        (i) => agent.indexOf(i) > 0
      )
    ) {
      start = "touchstart";
      move = "touchmove";
      end = "touchend";
    } else {
      start = "mousedown";
      move = "mousemove";
      end = "mouseup";
    }

    el.addEventListener(start, (e) => {
      //读取事件开始时，触摸或鼠标按下的坐标，以及容器当前所在位置，并声明两个函数对象，供后面添加和取消监听用
      let cur = e.touches
          ? {
              x: e.touches[0].clientX,
              y: e.touches[0].clientY,
            }
          : {
              x: e.clientX,
              y: e.clientY,
            },
        x = el.offsetLeft,
        y = el.offsetTop,
        prevent = function() {
          event.preventDefault();
        },
        moveFun = function(e) {
          if (e.touches) {
            let right = innerWidth - e.touches[0].clientX - cur.x + x;
            right = Math.max(right, 0);
            right = Math.min(right, innerWidth - el.offsetWidth);
            right += "px";
            el.style.right = right;
            let bottom = innerHeight - e.touches[0].clientY - cur.y + y;
            bottom = Math.max(bottom, 0);
            bottom = Math.min(bottom, innerHeight - el.offsetHeight);
            bottom += "px";
            el.style.bottom = bottom;
          } else {
            let right = innerWidth - e.clientX - cur.x + x;
            right = Math.max(right, 0);
            right = Math.min(right, innerWidth - el.offsetWidth);
            right += "px";
            el.style.right = right;
            let bottom = innerHeight - e.clientY - cur.y + y;
            bottom = Math.max(bottom, 0);
            bottom = Math.min(bottom, innerHeight - el.offsetHeight);
            bottom += "px";
            el.style.bottom = bottom;
          }
          //阻止移动端屏幕移动
          window.addEventListener("touchmove", prevent, {
            passive: false,
          });
        };
      //监听move并修改位置
      el.addEventListener(move, moveFun);
      //声明一个移除处理函数对象
      let endFun = function() {
        el.removeEventListener(move, moveFun);
        el.removeEventListener(move, endFun);
        window.removeEventListener("touchmove", prevent);
      };
      //监听到事件结束时，移除start以外的监听
      el.addEventListener(end, endFun);
    });
  },
});
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
