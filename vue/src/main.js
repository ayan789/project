// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'


// utils挂载到window全局对象中
import utils from '@/utils'
window.utils = utils


// element-ui引入--暂时全局引入
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// tableData挂载到window全局对象中
import tableData from '@/assets/data/tableData'
window.tableData = tableData

// code码表挂载到window全局对象中
import codeData from '@/assets/data/codeData'
window.codeData = codeData

//全局filter引入
import '@/filters/globalFilter.js'

Vue.config.productionTip = false

import store from './store'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
