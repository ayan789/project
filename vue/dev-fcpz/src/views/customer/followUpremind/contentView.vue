<template>
  <div v-html="$route.query.content">
  </div>
</template>

<script>
export default {
  name: "contentView"
};
</script>

<style scoped>

</style>
