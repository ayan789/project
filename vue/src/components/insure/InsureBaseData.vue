<template>
  <div class="con">
    <el-card class="box-card" style="margin-top:10px;">
     <!-- 路径面包屑 开始 -->
    <el-breadcrumb class="base-breadcrumb" separator="/">
      <el-breadcrumb-item>运营平台</el-breadcrumb-item>
      <el-breadcrumb-item>产品销售流程管理</el-breadcrumb-item>
      <el-breadcrumb-item>投保销售流程设置（可新增对应的“分类”，“元素”）</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 路径面包屑 结束 -->
    </el-card>
    
    <el-card class="box-card box-card1">
       <span style="padding: 10px " type="text">分类</span>
      <div v-for="o in 10" :key="o" class="text item">
        {{'列表内容 ' + o }}
        <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
      </div>
      <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
    </el-card>
    <el-card class="box-card box-card1">
       <span style="padding: 10px " type="text">元素</span>
      <div v-for="o in 10" :key="o" class="text item">
        {{'列表内容 ' + o }}
        <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
      </div>
      <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
    </el-card>
    <el-card class="box-card2">
       <span style="padding: 10px " type="text">元素属性</span>
      <el-input></el-input>
      <el-input></el-input>
      <el-input></el-input>
      <el-input></el-input>
      <el-input></el-input>
      <el-input></el-input>
      <el-button>重置</el-button>
      <el-button>保存</el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "insureBaseData",
  data() {
    return {
      msg: "Hello InsureBaseData"
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.box-card1{width:23vw; height: 100vh; float: left;margin-right: 10px;margin-top: 10px;}
.box-card2{width:39vw; height: 100vh; margin-top: 10px;}
.base-breadcrumb{background-color: #fff;}
.top{margin-top: 10px; border: 1px black ;}
</style>
