export default [{
  path: '/layout/formDemo',
  name: 'formDemo',
  meta: {
    title: 'formDemo',
    login: false
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/demo/FormDemo.vue')), 'formDemo')
  }
}]
