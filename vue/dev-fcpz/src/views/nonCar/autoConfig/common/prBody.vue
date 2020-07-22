<template>
    <div class="prBody2" id="prBody2">
      <h3>body</h3>
      <van-tabs v-model="active">
      <van-tab title="1"></van-tab>
      <van-tab title="2"></van-tab>
      <van-tab title="3"></van-tab>
      <van-tab title="4"></van-tab>
      </van-tabs>
    </div>
</template>

<script>
    export default {
        name: "prBody2",
        data(){
            return{
                active: 2
            }
        },
        mounted() {

        },
        methods:{

        }
    }
</script>

<style>
h3{text-align: center;}
</style>