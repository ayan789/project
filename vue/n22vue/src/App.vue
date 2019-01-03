<template>
  <div id="app">
     <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  background-color: #fff;
  width: 100%;
  height: calc(100vh );
  overflow: hidden;
}
</style>
