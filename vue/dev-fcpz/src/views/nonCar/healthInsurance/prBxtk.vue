<template>
  <div class="prBxtk">
    <globalNav
      :title="globalNav.title"
      @clickBack="backToPrePage"
    ></globalNav>
    <a
      class="row"
      v-for="(item,i) in productClause"
      :key="i"
      @click="go(item.url)"
    >{{item.title}}</a>
  </div>
</template>

<script>
import globalNav from "@/components/globalNav";

export default {
  components: {
    globalNav
  },
  data () {
    return {
      productClause: [],
      globalNav: {
        title: "保险条款"
      },
    }
  },
  created () {
    this.productClause = this.$route.params.productClause
  },
  methods: {
    go (aa) {
      window.location.href = aa
    },
    backToPrePage () {
      this.$globalBack();
    },
  }
}
</script>

<style scoped>
.prBxtk {
  box-sizing: border-box;
  margin-top: 2rem;
}
.row {
  display: block;
  padding: 0.2rem;
  box-sizing: border-box;
}
</style>