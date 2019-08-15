<template>
  <div class="information1">
    <div class="information1Div">
         <h1>{{ msg }}</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Information1',
  data () {
    return {
      msg: 'Information1'
    }
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
