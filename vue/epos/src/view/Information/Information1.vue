<template>
  <div class="information1">
    <div class="information1Div">
         <h1>{{ msg }}</h1>
         <el-button type="primary" @click="getlocalStorage">获取缓存</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Information1',
  data () {
    return {
      msg: 'Information1',
      indexUrl: '',
    }
  },
  methods: {
    getlocalStorage(){
      this.indexUrl = localStorage.getItem('index')
      console.log(this.indexUrl)
    }
  },
  created(){  //生命周期里接收参数
      console.log(this.$route.query.id)   //get接受参数关键代码
      // html 取参  $route.query.id
      // script 取参  this.$route.query.id
      console.log(this.$route.params.id)  //post接受参数关键代码
      // html 取参  $route.params.id
      // script 取参  this.$route.params.id
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.information1Div{
    height:2000px;
    width:1500px;
}
</style>
