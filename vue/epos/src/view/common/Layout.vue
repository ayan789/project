<template>
  <div class="layout">
  jjj
  <!-- 页面注入区 -->
  <div style=" padding:300px; background-color: #f0f0f0; ">
     <router-view/>
  </div>
  </div>
</template>

<script>
export default {
  name: 'Layout',
  data () {
    return {
      msg: 'Layout'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
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
</style>
