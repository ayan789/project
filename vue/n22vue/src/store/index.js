import Vue from 'vue'
import Vuex from 'vuex'
// import demo from './demo'
// import common from './common'
import ui from './ui'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    // demo: demo,
    // common: common,
    ui: ui
  }
})