<template> 
    <div class="ysInput">
     <el-input @change="sendMsg()" style="width:200px;" v-model="msg"></el-input>
    {{fudata}}
    </div>
</template>
<script>
export default {
    name: 'YsInput',
    props: ['fudata'],//父容器像子容器传值
    data () {
        return {
            //将msg传递给父组件
            msg: "子组件向父组件传值",
        }
    },
    methods:{
         sendMsg(){
             //func: 是父组件指定的传数据绑定的函数，this.msg:子组件给父组件传递的数据
             this.$emit('func',this.msg)
         }
     },
   
}
</script>

<style scoped>

</style>