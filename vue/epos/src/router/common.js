import demo from './demo'
import information from './information'


export default [{
  name: 'layout',
  path: '/layout',
  component: (resovle) => { 
  	require.ensure([], () => resovle(require('@/view/common/Layout')), 'layout')
  },
  children: [
    ...demo,
    ...information
  ]
}]