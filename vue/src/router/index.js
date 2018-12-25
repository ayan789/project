// import Vue from 'vue'
// import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Vue from 'vue'
import VueRouter from 'vue-router'
import { Message } from 'element-ui';
import common from './common'

// Vue.use(Router)
Vue.use(VueRouter)

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'HelloWorld',
//       component: HelloWorld
//     }
//   ]
// })
// 新建全局路由对象
const router = new VueRouter({
  routes: [
    //分模块配置[等于一个XXX模块的路由分开配置在XXX.js文件中]
    ...common,
  ]
})

// 路由拦截
router.beforeEach((to, from, next) => {
  console.log('路由拦截')
  if(to.meta.login){ //登录页面-未登录拦截
    Message.error({
      message: "登陆状态异常，请重新登陆"
    });
    next('/') 
  }else{  //无需登录页面或需要登录页面已登录
    //设置标题
    document.title = to.meta.title || "未设置"
    next()
  }
})

export default router
