<template>
  <div class="layout">
  
   <el-menu :default-active="activeIndex"  active-text-color="rgb(255, 152, 0)"  class="el-menu-demo" mode="horizontal" @select="handleSelect"  background-color="rgb(33, 150, 243)" text-color="#fff">
        <el-menu-item style="margin-left:1300px;">首页</el-menu-item>
            <el-submenu index="2">
                <template slot="title">发现</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                <template slot="title">选项4</template>
                <el-menu-item index="2-4-1">选项1</el-menu-item>
                <el-menu-item index="2-4-2">选项2</el-menu-item>
                <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
        </el-submenu>
        <el-menu-item index="3" disabled>动态</el-menu-item>
    </el-menu>
    <div class="leftDiv">
        <el-aside width="250px" >
         <el-menu
          :router="false"
          text-color="#fff"
          active-text-color="#20a0ff"
          unique-opened
          class="el-menu-vertical-demo"
          background-color="rgb(44,44,66)"
          width="250px"
        >
          <template v-for="(menu) of data.children">
            <!-- 无子节点 -->
            <el-menu-item
              v-if="menu.children == undefined || menu.children.length == 0"
              :key="menu.progressId"
              :index="menu.progressId"
            >
              <template  slot="title">
                 <span>{{menu.stepKey}}</span>
              </template>
            </el-menu-item>
            <!-- 有子节点 -->
            <el-submenu v-else :key="menu.progressId" :index="menu.progressId">
              <template  slot="title">
                  <span>{{menu.stepKey}}</span>
              </template>
              <!-- 模块子页面组 -->
              <el-menu-item-group v-for="(module, moduleIndex) in menu.children" :key="moduleIndex">
                <el-menu-item :index="`${menu.stepKey}-${moduleIndex}`" @click="moduleClick(module.url)">
                  <i class="el-icon-star-on"></i>
                  <span>{{module.stepKey}}</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </template>
        </el-menu>
        </el-aside>
    </div>
  <!-- 页面注入区 -->
     <router-view/>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Layout',
  data () {
    return {
      msg: 'Layout',
      activeIndex: '1',
      activeIndex2: '1',
       progressId: "0",
      data:{
            parentId: "00",
            stepKey: "stepModels",
            "status": "1",
            "url": "1",
            children: [
            {
            "progressId": "10",
            "parentId": "0",
            "stepKey": "step_fna",
            "status": "1",
            "url": "1",
            "children": [
            {
            "progressId": "20",
            "parentId": "10",
            "stepKey": "step_fna",
            "status": "1",
            "url": "informationPage1",
            "children": []
            }
            ]
            },
            {
            "progressId": "11",
            "parentId": "0",
            "stepKey": "step_quotation",
            "status": "1",
            "url": "1",
            "children": [
            {
            "progressId": "21",
            "parentId": "11",
            "stepKey": "step_insurant",
            "status": "1",
            "url": "informationPage2",
            "children": []
            },
            {
            "progressId": "22",
            "parentId": "11",
            "stepKey": "step_applicant",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "23",
            "parentId": "11",
            "stepKey": "step_plan",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "24",
            "parentId": "11",
            "stepKey": "step_benefit",
            "status": "1",
            "url": "1",
            "children": []
            }
            ]
            },
            {
            "progressId": "12",
            "parentId": "0",
            "stepKey": "step_application",
            "status": "1",
            "url": "1",
            "children": [
            {
            "progressId": "25",
            "parentId": "12",
            "stepKey": "step_insurant_additional",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "26",
            "parentId": "12",
            "stepKey": "step_applicant_additional",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "27",
            "parentId": "12",
            "stepKey": "step_beneficary",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "28",
            "parentId": "12",
            "stepKey": "step_kyccdd_questions",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "29",
            "parentId": "12",
            "stepKey": "step_health_questions",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "30",
            "parentId": "12",
            "stepKey": "step_sec_notification",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "31",
            "parentId": "12",
            "stepKey": "step_fatca",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "32",
            "parentId": "12",
            "stepKey": "step_lifestyle_questions",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "33",
            "parentId": "12",
            "stepKey": "step_rpq_questions",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "34",
            "parentId": "12",
            "stepKey": "step_rpq_analysis",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "35",
            "parentId": "12",
            "stepKey": "step_premium_fund_allocation",
            "status": "1",
            "url": "1",
            "children": []
            }
            ]
            },
            {
            "progressId": "13",
            "parentId": "0",
            "stepKey": "step_submission",
            "status": "1",
            "url": "1",
            "children": [
            {
            "progressId": "36",
            "parentId": "13",
            "stepKey": "step_uob_information",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "37",
            "parentId": "13",
            "stepKey": "step_payment",
            "status": "1",
            "url": "1",
            "children": []
            },
            {
            "progressId": "38",
            "parentId": "13",
            "stepKey": "step_documents",
            "status": "1",
            "url": "1",
            "children": []
            }
            ]
            }
            ]
            }
    }
  },
  methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        loadProgress(){
            utils.http.get("/insure/loadProgress", this.searchForm)
            .then(res => {
                console.log(res);
            })
        },
        currentUser(){
            utils.http.get("/user/currentUser", this.searchForm)
            .then(res => {
                console.log(res);
            })
        },
         moduleClick(url){
             if(url=="informationPage1" || url=="/layout/information/informationPage1"){
                this.$router.push('/layout/information/informationPage1')
            }
            if(url=="informationPage2" || url=="/layout/information/informationPage2"){
                this.$router.push('/layout/formDemo')
            }
         }
    },
  beforeMount() {
      this.currentUser();
      this.loadProgress();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .leftDiv{
        height:1000px;
        background-color:rgb(44,44,66);
        
        float:left;
    }
    .el-menu {
    border-right: solid 1px #545c64;
    }
</style>
<style>
.el-menu-item.is-active {
   background-color: #fff !important;
   color: black !important;
}
</style>
