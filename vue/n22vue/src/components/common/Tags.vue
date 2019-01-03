<template>
   <el-card class="box-card" v-if="showTags">
  <div class="tags" >
    <ul>
      <li
        class="tags-li"
        v-for="(item,index) in tagsList"
        :class="{'active': isActive(item.path)}"
        :key="index"
      >
        <router-link :to="item.path" class="tags-li-title">{{item.title}}</router-link>
        <span class="tags-li-icon" @click="closeTags(index)">
          <i class="el-icon-close"></i>
        </span>
      </li>
    </ul>
    <div class="tags-close-box">
      <el-dropdown @command="handleTags">
        <el-button size="mini" type="primary" style="margin-top:15px;">
          标签选项
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu size="small" slot="dropdown">
          <el-dropdown-item command="other">关闭其他</el-dropdown-item>
          <el-dropdown-item command="all">打开所有</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
   </el-card>
</template>

<script>
import bus from "./bus";

export default {
  props: ["tagsList"],
  data() {
    return {
      // showTags: true,
      // tagsList: [
      //   { title: "系统首页", path: "/layout/formDemo", name: "dashboard" },
      //   // { title: "基础表格", path: "/table", name: "basetable" },
      //   // { title: "tab选项卡", path: "/tabs", name: "tabs" }
      // ]
    };
  },
  methods: {
    // 关闭单个标签
    closeTags(index) {
      if(index+1==this.tagsList.length){
          if(this.$route.fullPath==this.tagsList[index].path){
             this.$router.push(this.tagsList[index-1].path);
            this.tagsList.splice(index, 1)[0];
          }
          else{
            this.tagsList.splice(index, 1)[0];
          }
      }
      else{
          if(this.$route.fullPath==this.tagsList[index].path){
            this.tagsList.splice(index, 1)[0];
            this.$router.push(this.tagsList[index].path);
          }
          else{
            this.tagsList.splice(index, 1)[0];
          }
      }
      

      // console.log(this.$route.fullPath)
      // console.log(index)
      // console.log(this.tagsList.length)
      // const delItem = this.tagsList.splice(index, 1)[0];
      // const delItem = this.tagsList.splice(index, 1)[0];
      // const item = this.tagsList[index]
      // ? this.tagsList[index]
      // : this.tagsList[index + 1];
      // if (item) {
      // delItem.path === this.$route.fullPath && this.$router.push(this.tagsList[index - 1].path);
      // this.$router.push(delItem.path);
      // } else {
      // this.$router.push("/layout/education/eduManagement");
      // }
    },
    isActive(path) {
      console.log("em");
      console.log(path);
      console.log(this.$route.fullPath);
      return path === this.$route.fullPath;
      // if (path == "/tabs") {
      //   return true;
      // } else {
      //   return false;
      // }
      // return path === this.$route.fullPath;
    },
    // 设置标签
    setTags(route) {
      console.log(this.tagsList);
      console.log(route.fullPath);
      if (route.fullPath == "/layout/formDemo") {
        this.tagsList = [
          { title: "formDemo1", path: "/formDemo1", name: "formDemo1" },
          { title: "formDemo2", path: "/formDemo2", name: "formDemo2" },
          { title: "formDemo3", path: "/formDemo3", name: "formDemo3" }
        ];
      }
      if (route.fullPath == "/layout/information/informationPage1") {
        // this.tagsList = [
        //   {
        //     title: "informationPage1",
        //     path: "/layout/information/informationPage1",
        //     name: "informationPage1"
        //   },
        //   {
        //     title: "informationPage2",
        //     path: "/layout/information/informationPage2",
        //     name: "informationPage2"
        //   },
        //   {
        //     title: "informationPage3",
        //     path: "/layout/information/informationPage3",
        //     name: "informationPage3"
        //   }
        // ];
      }
      if (route.fullPath == "/layout/education/eduManagement") {
        // this.tagsList = [];
      }
      bus.$emit("tags", this.tagsList);
      // const isExist = this.tagsList.some(item => {
      //   return item.path === route.fullPath;
      // });
      // console.log(isExist);
      // if (!isExist) {
      //   if (this.tagsList.length >= 2) {
      //     //删除首个元素
      //     this.tagsList.shift();
      //     console.log(this.tagsList);
      //   }
      //   this.tagsList.push({
      //     title: route.meta.title,
      //     path: route.fullPath,
      //     name: route.matched[1].components.default.name
      //   });
      // }
    },
    handleTags(command) {
      command === "other" ? this.closeOther() : this.openAll();
    },
    // 关闭其他标签
    closeOther() {
      const curItem = this.tagsList.filter(item => {
        return item.path === this.$route.fullPath;
      });
      this.tagsList = curItem;
    },
    // 关闭全部标签
    closeAll() {
      this.tagsList = [];
      // this.$router.push('/');
    },
     // 打开全部标签
    openAll() {
       let fullPath=this.$route.fullPath
       if (fullPath == "/layout/formDemo") {
        this.tagsList = [
          { title: "formDemo1", path: "/formDemo1", name: "formDemo1" },
          { title: "formDemo2", path: "/formDemo2", name: "formDemo2" },
          { title: "formDemo3", path: "/formDemo3", name: "formDemo3" }
        ];
      }
      if (fullPath == "/layout/information/informationPage1" || fullPath == "/layout/information/informationPage2" || fullPath == "/layout/information/informationPage3") {
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
      }
      this.$emit('aassdd', this.tagsList)
    },
  },
  computed: {
    showTags() {
      return this.tagsList.length > 0;
    }
  },
  watch: {
    //监听路由变化
    $route(newValue, oldValue) {
      console.log(newValue);
      console.log(oldValue);
      this.setTags(newValue);
    }
  },
  created() {},
  mounted(){
    // console.log('子zujian')
    // this.tagsList=this.messageList
    // console.log(this.tagsList)
  },
};
</script>


<style scoped>
.tags {
  position: relative;
  
  overflow: hidden;
  background: #fff;
  padding-right: 120px;
  /* box-shadow: 0 5px 10px #ddd; */
}

.tags ul {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  display: flex;
}

.tags-li {
  margin: 3px 5px 2px 3px;
  border-radius: 3px;
  font-size: 12px;
  overflow: hidden;
  cursor: pointer;
  height: 23px;
  line-height: 23px;
  border: 1px solid #e9eaec;
  background: #fff;
  padding: 0 5px 0 12px;
  vertical-align: middle;
  color: #666;
  -webkit-transition: all 0.3s ease-in;
  -moz-transition: all 0.3s ease-in;
  transition: all 0.3s ease-in;
}

.tags-li:not(.active):hover {
  background: #f8f8f8;
}

.tags-li.active {
  color: #fff;
}

.tags-li-title {
  float: left;
  /* max-width: 80px; */
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right: 5px;
  color: #666;
}

.tags-li.active .tags-li-title {
  color: #fff;
}

.tags-close-box {
  position: absolute;
  right: 0;
  top: 0;
  box-sizing: border-box;
  padding-top: 1px;
  text-align: center;
  width: 500px;
  height: 30px;
  background: #fff;
  z-index: 10;
}
.active {
  background-color: #5cb8fc;
}
a {
  text-decoration: none;
}
.el-card__body {
    padding: 0px !important;
}
</style>
