<template> 
    <div class="child">
    {{fudata}}
    </div>
</template>
<script>
export default {
    name: 'Child',
    props: ['fudata'],//父容器像子容器传值
    data () {
        return {
        }
    },
    methods:{
     },
   
}
</script>

<style scoped>

</style>