/* ------------------------------------------关于ui的store------------------------------------------ */
//mutations 常量
const types = {
  LOADING: 'LOADING',
  TOAST: 'TOAST'
}

//state 状态
const state = {
  loading: {  //loading对象
    isShow: false,  //是否显示loading
    msg: '' //loading显示信息
  },
  toast: {  //toast对象
    isShow: false,  //是否显示toast
    msg: '' //toast显示信息
  }
}

//getters
const getters = {
  loading: () => {
    return state.loading
  },
  toast: () => {
    return state.toast
  }
}

//mutations
const mutations = {
  [types.LOADING](state, params) {
    state.loading = params;
  },
  [types.TOAST](state, params) {
    state.toast = params;
  }
}

export default {
  state,
  getters,
  mutations
}