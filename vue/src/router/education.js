export default [{
  path: '/layout/education/eduManagement',
  name: 'eduManagement',
  meta: {
    title: '消费者教育管理',
    // login: true
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/education/EduManagement.vue')), 'eduManagement')
  }
}]