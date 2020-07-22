/*
 * @Author: King
 * @Page:
 * @Date: 2020-03-11 20:55:50
 * @Last Modified by: King
 * @Last Modified time: 2020-03-16 16:09:51
 * @FilePath: /src/utils/global.js
 */
import globalNav from "../components/globalNav"; //头部导航栏

function globalComponent(Vue) {
  if (globalComponent.installed) {
    return;
  }
  Vue.component("Global_globalNav", globalNav);
}

export default globalComponent;
