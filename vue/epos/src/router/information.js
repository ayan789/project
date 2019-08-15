export default [{
    path: '/layout/information/informationPage1',
    name: 'informationPage1',
    meta: {
      title: '公开信息披露管理1'
    },
    component: (resovle) => {
      require.ensure([], () => resovle(require('@/view/Information/Information1.vue')), 'informationPage1')
    }
  }
  ]
  