export default {
  state: () => ({
    memberInfo: {
      memberId: '',
      memberName: '',
    },
    progress: null,
  }),

  getters: {
    getProgress: (state) => {
      return state.progress;
    }
  },

  mutations: {
    commitProgress: (state, progress) => {
      state.progress = progress;
    },
  },

  actions: {
    setProgress: ({commit}, progress) => {
      commit('commitProgress', progress);
    },
  },


}
