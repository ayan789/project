import Vue from 'vue'

import Router from 'vue-router';
// import HelloWorld from '@/components/HelloWorld'
// 1、引入组件,进行路由切换组件
// import Home from '@/view/Home.vue'
// import Index from '@/view/Index.vue'
import common from './common'

// 2.通过Vue的use方法注入Router
Vue.use(Router)
// 3.创建 router 实例，然后传 `routes` 配置
const router = new Router({
    // 指定路由选中时的样式类名
    linkActiveClass: 'active',
    hashbang: true, // 将路径格式化为#!开头
    history: true, // 启用HTML5 history模式，可以使用pushState和replaceState来管理记录
    /**
     * 4.@desc 路由配置配置具体的路径
     */
    routes: [
        // {
        //     path: '/Index',
        //     component: Index
        // },
        // {
        //     path: '/Home',
        //     component: Home
        // }
        //分模块配置[等于一个XXX模块的路由分开配置在XXX.js文件中]
        ...common,
    ]

  })

// 5.导出路由
export default router
