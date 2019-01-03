<template>
  <div class="con">
    <div style="height: 300px;">
      
      <el-card class="box-card step-branch">
      <el-steps direction="vertical" :active="1">
        <el-step title="步骤 1"></el-step>
        <el-step title="步骤 2"></el-step>
        <el-step title="步骤 3"></el-step>
        <el-step title="步骤 4"></el-step>
        <el-step title="步骤 5"></el-step>
      </el-steps>
      </el-card>
      <el-card class="box-card step-branch-main">
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "insureStep",
  data() {
    return {
      msg: "Hello insureStep"
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
.step-branch{
  width:340px;
  height: 100vh;
  float: left;
  background-color: #fff;
}
.step-branch-main{
  width: 70vm;
  height: 100vh;
  margin-left: 350px;
  background-color: #fff;
  margin-top: 10px;
}
</style>
