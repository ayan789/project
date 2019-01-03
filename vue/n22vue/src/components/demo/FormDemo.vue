<template>
  <div class="con scrolldiv">
    <!-- 路径面包屑 开始 -->
    <el-breadcrumb class="base-breadcrumb" separator="/">
      <el-breadcrumb-item>运营平台</el-breadcrumb-item>
      <el-breadcrumb-item>demo</el-breadcrumb-item>
      <el-breadcrumb-item>form</el-breadcrumb-item>
    </el-breadcrumb>
     <!-- 路径面包屑 结束 -->
     <!-- 搜索form 开始 -->
    <el-form class="base-search-form"  label-width="80px">
      <el-row>
        <el-form-item label="活动名称" class="bit">
          <el-input class="base-input-text " ></el-input>
        </el-form-item>
        <el-form-item label="活动类型" class="bit">
          <el-input class="base-input-text " ></el-input>
        </el-form-item>
      </el-row>
    </el-form>
    <!-- 搜索form 结束 -->
  </div>
</template>

<script>
export default {
  name: 'formDemo',
  data () {
    return {
      msg: 'Hello formDemo'
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/assets/css/main.scss";
.bit {
  width: 270px;
}
.scrolldiv{
    height: 100%;
    overflow-y: scroll;
}
</style>
