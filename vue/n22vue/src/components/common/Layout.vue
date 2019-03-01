<template>
  <el-container>
    <load :isShow="isShow"></load>
    <!-- header头部 开始 -->
    <el-header height="55px">
      <!-- <img  alt="logo"> -->
      <div class="user-info">
        <div class="ope-item">
          <i class="fa fa-user"></i>
          {{userName}}
        </div>
        <div class="ope-item" @click="loginOut">
          <i class="fa fa-lock"></i>
          注销
        </div>
      </div>
    </el-header>
    <!-- header头部 结束 -->
    <!-- 主内容区 开始 -->
    <el-container id="bottom-container">
      <!-- 侧边栏 开始 -->
      <el-aside width="200px">
        <el-menu
          :router="false"
          text-color="#666"
          active-text-color="#20a0ff"
          unique-opened
        >
          <template v-for="(menu) of data.children">
            <!-- 无子节点 -->
             <!-- :route="{name: menu.url}" -->
            <el-menu-item
              v-if="menu.children == undefined || menu.children.length == 0"
              :key="menu.id"
              :index="menu.id"
            >
              <template slot="title">
                <i class="el-icon-star-on"></i>
                {{menu.label}}
              </template>
            </el-menu-item>
            <!-- 有子节点 -->
            <el-submenu v-else :key="menu.id" :index="menu.id">
              <template slot="title">
                <i class="el-icon-star-on"></i>
                {{menu.label}}
              </template>
              <!-- 模块子页面组 -->
              <el-menu-item-group v-for="(module, moduleIndex) in menu.children" :key="moduleIndex">
                <!-- :route="{name: module.url} -->
                <el-menu-item :index="`${menu.id}-${moduleIndex}`" >
                  <i class="el-icon-star-on"></i>
                  <span @click="moduleClick(module.url)">{{module.label}}</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>
      <!-- 侧边栏 结束 -->
      <!-- 右侧主内容 开始 -->
      <el-main style=" padding:10px; background-color: #f0f0f0; ">
        <!--v-on:aassdd @aassdd子传父 v-bind:tagsList :tagsList父传子 -->
        <v-tags :tagsList="tagsList" @aassdd="listenToMyBoy" ></v-tags>
        <hr class="hrClass">
        <!-- 页面注入区 -->
        <router-view/>
      </el-main>
      <!-- 右侧主内容 结束 -->
    </el-container>
    <!-- 主内容区 结束 -->
    
  </el-container>
</template>

<script>
import vTags from "./Tags.vue";
import store from "@/store";
import bus from "./bus";

export default {
  name: "layout",
  data() {
    return { 
      isShow:false,
      userName: "红孩儿",
      tagsList:[
          { title: "aa", path: "/layout/formDemo", name: "dashboard" },
          { title: "bb", path: "/table", name: "basetable" },
          { title: "cc", path: "/tabs", name: "tabs" }
        ],
      data: {
        id: "464a9e03029e11e98e6f487b6bd31e7d",
        pid: "0",
        status: "1",
        url: "",
        label: "菜单管理",
        children: [
          {
            id: "3a1907f14029e11e98e6f487b6bd31e7d",
            pid: "464a9e03029e11e98e6f487b6bd31e7d",
            status: "1",
            url: "",
            label: "消费者教育管理",
            children: [
              {
                id: "a973429302a011e98e6f487b6bd31e7d",
                pid: "3a1907f14029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "eduManagement",
                label: "消费者教育列表",
                children: []
              }
            ]
          },
          {
            id: "23a2bc60029f11e98e6f487b6bd31e7d",
            pid: "464a9e03029e11e98e6f487b6bd31e7d",
            status: "1",
            url: "",
            label: "公开信息披露管理",
            children: [
              {
                id: "8503ad8a029f11e98e6f487b6bd31e7d",
                pid: "23a2bc60029f11e98e6f487b6bd31e7d",
                status: "1",
                url: "informationPage1",
                label: "公开信息披露列表",
                children: []
              }
            ]
          },
          {
            id: "8f86bd20029e11e98e6f487b6bd31e7d",
            pid: "464a9e03029e11e98e6f487b6bd31e7d",
            status: "1",
            url: "",
            label: "活动管理",
            children: [
              {
                id: "a18f8ffd02a011e98e6f487b6bd31e7d",
                pid: "8f86bd20029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "formDemo",
                label: "活动列表",
                children: []
              },
              {
                id: "a5a638a702a011e98e6f487b6bd31e7d",
                pid: "8f86bd20029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "www",
                label: "邀请记录",
                children: []
              }
            ]
          },
          {
            id: "bbd67e16029e11e98e6f487b6bd31e7d",
            pid: "464a9e03029e11e98e6f487b6bd31e7d",
            status: "1",
            url: "",
            label: "关于瑞华",
            children: [
              {
                id: "ae6f620c02a011e98e6f487b6bd31e7d",
                pid: "bbd67e16029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "www",
                label: "新闻中心",
                children: []
              },
              {
                id: "b3b1374a02a011e98e6f487b6bd31e7d",
                pid: "bbd67e16029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "www",
                label: "媒体发布稿",
                children: []
              }
            ]
          },
          {
            id: "cde85e5d029e11e98e6f487b6bd31e7d",
            pid: "464a9e03029e11e98e6f487b6bd31e7d",
            status: "1",
            url: "eduManagement",
            label: "系统管理",
            children: []
          },
          {
            id: "cde85e5d029e11e98e6f487b6bd31296",
            pid: "464a9e03029e11e98e6f487b6bd3182",
            status: "1",
            url: "www",
            label: "产品销售流程管理",
            children: [
              {
                id: "ae6f620c02a011e98e6f487b6bd31e7d",
                pid: "bbd67e16029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "insureBaseData",
                label: "投保基础数据设置",
                children: []
              },
              {
                id: "ae6f620c02a011e98e6f487b6bd31e7d",
                pid: "bbd67e16029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "insureStep",
                label: "投保销售流程设置",
                children: []
              },
               {
                id: "ae6f620c02a011e98e6f487b6bd31e7d",
                pid: "bbd67e16029e11e98e6f487b6bd31e7d",
                status: "1",
                url: "examManager",
                label: "考试管理",
                children: []
              }
            ]
          }
        ]
      },
      msg: "Hello layout",
      menus: {
        //菜单列表，此处以后可能需要通过接口获取，现在暂时前台配置
        公开信息披露管理: {
          icon: "el-icon-menu",
          moduleList: [
            {
              icon: "el-icon-tickets",
              pathDesc: "公开信息披露管理",
              pathName: "informationPage"
            }
          ]
        },
        活动管理: {
          icon: "el-icon-menu",
          moduleList: [
            {
              icon: "el-icon-tickets",
              pathDesc: "活动列表",
              pathName: "activityList"
            },
            {
              icon: "el-icon-tickets",
              pathDesc: "邀请记录",
              pathName: "invitationRecord"
            }
          ]
        },
        消费者教育管理: {
          icon: "el-icon-menu",
          moduleList: [
            {
              icon: "el-icon-tickets",
              pathDesc: "消费者教育管理",
              pathName: "eduManagement"
            }
          ]
        },
        关于瑞华: {
          icon: "el-icon-star-on",
          moduleList: [
            {
              icon: "el-icon-tickets",
              pathDesc: "新闻中心",
              pathName: "newsCenter"
            },
            {
              icon: "el-icon-tickets",
              pathDesc: "媒体发布稿",
              pathName: "mediaRelease"
            }
          ]
        },
        系统管理: {
          icon: "el-icon-menu",
          moduleList: [
            {
              icon: "el-icon-tickets",
              pathDesc: "活动配置",
              pathName: "activityConf"
            }
          ]
        }
      }
    };
  },
  methods: {
    listenToMyBoy: function (somedata){
    console.log('子传父')
    console.log(somedata)
    this.tagsList=somedata
   },
    //退出登录
    loginOut() {
      console.log("退出登录");
    },
    moduleClick(url){
      console.log("moduleClick");
      console.log(url);
      if(url=="informationPage1" || url=="/layout/information/informationPage1"){
        this.tagsList = [
          {
            title: "informationPage1",
            path: "/layout/information/informationPage1",
            name: "informationPage1"
          },
          {
            title: "informationPage2",
            path: "/layout/information/informationPage2",
            name: "informationPage2"
          },
          {
            title: "informationPage3",
            path: "/layout/information/informationPage3",
            name: "informationPage3"
          }
        ];
        this.$router.push('/layout/information/informationPage1')
      }
      if(url=="eduManagement" || url=="/layout/education/eduManagement"){
        this.tagsList = [
          {
            title: "q",
            path: "/layout/information/informationPage1",
            name: "/informationPage1"
          },
          {
            title: "e",
            path: "/layout/information/informationPage2",
            name: "informationPage2"
          },
          {
            title: "t",
            path: "/layout/information/informationPage3",
            name: "informationPage3"
          }
        ];
        this.$router.push('/layout/education/eduManagement')
      }
     
      
    }
  },
  components: {
    vTags
  },
  created() {
    // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
      bus.$on('tags', msg => {
        console.log('emmm')
        console.log(msg)
        // if(msg.length == 0){
        //   this.showTg=false
        // }
        // else{
        //    this.showTg=true
        // }
        //  console.log(this.showTg)
          // let arr = [];
          // for(let i = 0, len = msg.length; i < len; i ++){
          //     msg[i].name && arr.push(msg[i].name);
          // }
          // this.tagsList = arr;
      })
  },
  mounted() {
    if (document.body.clientWidth < 1500) {
      this.collapseChage()
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/css/main.scss";
@import "@/assets/css/vars.scss";
.el-container {
  height: 100vh;
  .el-header {
    background: linear-gradient(to right, $light-blue, $blue);
    img {
      width: 150px;
      height: 35px;
      margin-top: 10px;
    }
    .user-info {
      display: block;
      float: right;
      color: white;
      .ope-item {
        display: inline-block;
        height: 55px;
        line-height: 55px;
        text-align: center;
        cursor: pointer;
        margin-right: 20px;
      }
    }
  }
  .el-container {
    .el-aside {
    }
  }
}
.el-menu-item {
  margin-top: -15px;

  span {
    margin-left: 3px;
  }
}
.hrClass {
  border: 0.5px rgb(233, 220, 220) solid;
}

.el-card__body {
    padding: 0px !important;
}

</style>
