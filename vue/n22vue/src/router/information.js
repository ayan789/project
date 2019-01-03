export default [{
  path: '/layout/information/informationPage1',
  name: 'informationPage1',
  meta: {
    title: '公开信息披露管理1'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/Information/Information1.vue')), 'informationPage1')
  }
},
{
  path: '/layout/information/informationPage2',
  name: 'informationPage2',
  meta: {
    title: '公开信息披露管理2'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/Information/Information2.vue')), 'informationPage2')
  }
},
{
  path: '/layout/information/informationPage3',
  name: 'informationPage3',
  meta: {
    title: '公开信息披露管理3'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/Information/Information3.vue')), 'informationPage3')
  }
}
]
