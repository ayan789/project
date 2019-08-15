export default [{
    path: '/layout/formDemo',
    name: 'formDemo',
    meta: {
      title: 'formDemo'
    },
    component: (resovle) => {
      require.ensure([], () => resovle(require('@/view/demo/FormDemo.vue')), 'formDemo')
    }
  }]
  