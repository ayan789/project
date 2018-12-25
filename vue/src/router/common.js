import demo from './demo'
import education from './education'
import information from './information'
import insure from './insure'

export default [{
  name: 'layout',
  path: '/layout',
  component: (resovle) => { 
  	require.ensure([], () => resovle(require('@/components/common/Layout')), 'layout')
  },
  children: [
    ...demo,
    ...education,
    ...information,
    ...insure
  ]
}, {
  name: 'login',
  path: '/',
  meta: {
    title: '登录',
  },
  component: (resovle) => { 
  	require.ensure([], () => resovle(require('@/components/common/Login.vue')), 'login')
  },
}, {
  name: 'test',
  path: '/test',
  meta: {
    title: '测试页面',
  },
  component: (resovle) => { 
  	require.ensure([], () => resovle(require('@/components/common/Test')), 'test')
  }
}]