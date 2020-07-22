const state = {
  userCode: ""
};

const mutations = {
  SET_USERCODE: (state, data) => {
    state.userCode = data;
  }
};

export default {
  namespaced: true,
  state,
  mutations
};
