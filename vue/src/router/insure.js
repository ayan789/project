export default [{
  path: '/layout/insure/InsureBaseData',
  name: 'insureBaseData',
  meta: {
    title: '投保基础数据设置'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/insure/InsureBaseData.vue')), 'insureBaseData')
  }
},
{
  path: '/layout/insure/InsureStep',
  name: 'insureStep',
  meta: {
    title: '投保流程设置'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/insure/InsureStep.vue')), 'insureStep')
  }
},
{
  path: '/layout/insure/ExamManager',
  name: 'examManager',
  meta: {
    title: '考试管理'
  },
  component: (resovle) => {
    require.ensure([], () => resovle(require('@/components/insure/ExamManager.vue')), 'examManager')
  }
}
]
