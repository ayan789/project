<template>
    <div class="prHeader2" id="prHeader2">
        <p>  
            hello: {{author}}
        </p> 
        <van-button id="btn"
            type="info" 
            @click="doStore" 
          >header状态</van-button> 
        <van-button id="btn"
            type="info" 
            @click="doAuthor" 
          >header状态修改</van-button> 
      <h3>Header</h3>
      <h3>{{proHeader}}</h3>
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item><h3>1</h3></van-swipe-item>
      <van-swipe-item><h3>2</h3></van-swipe-item>
      <van-swipe-item><h3>3</h3></van-swipe-item>
      <van-swipe-item><h3>4</h3></van-swipe-item>
    </van-swipe>
    </div>
</template>

<script>
    export default {
       props: {
          proHeader: String
        },
        name: "prHeader2",
        data(){
            return{
                author:'',
            }
        },
        mounted() {

        },
        methods:{
            doStore() {
              this.author=this.$store.state.config.detailData.author;
            },
            doAuthor() {
              console.log('header状态修改')
              this.$store.state.config.detailData.author = 'header'; 
            }
        }
    }
</script>

<style>
h3{text-align: center;}
</style>