<template> 
    <input @click="sendMsg" type="button" value="给父组件传递值">
    </input>
</template>
<script>
export default {
    name: 'YsInput',
    data () {
        return {
            //将msg传递给父组件
            msg: "我是子组件的msg",
        }
    },
    methods:{
         sendMsg(){
             //func: 是父组件指定的传数据绑定的函数，this.msg:子组件给父组件传递的数据
             this.$emit('func',this.msg)
         }
     }  
}
</script>

<style scoped>

</style>