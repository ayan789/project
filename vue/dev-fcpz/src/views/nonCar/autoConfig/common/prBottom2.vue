<template>
    <div class="bottom2" id="bottom2">
      <h3>bottom</h3>
      <van-button id="btn"
            type="info" 
            @click="doInsure" 
          >确认</van-button>
          <p></p>
      <van-button id="btn"
            type="info" 
            @click="doLocalStorage" 
          >存储</van-button>
      <van-button id="btn"
            type="info" 
            @click="doStore" 
          >bottom状态</van-button> 
          <van-button id="btn"
            type="info" 
            @click="doAuthor" 
          >bottom状态修改</van-button> 
          <p>  
            Copyright © {{author}} - 2020 All rights reserved  
          </p> 
          <!-- 输入任意文本 -->
          <van-field v-model="text" label="文本" />
          <!-- 输入手机号，调起手机号键盘 -->
          <van-field v-model="tel" type="tel" label="手机号" />
          <!-- 允许输入正整数，调起纯数字键盘 -->
          <van-field v-model="digit" type="digit" label="整数" />
          <!-- 允许输入数字，调起带符号的纯数字键盘 -->
          <van-field v-model="number" type="number" label="数字" />
          <!-- 输入密码 -->
          <van-field v-model="password" type="password" label="密码" />
    </div>
</template>

<script>
    export default {
        name: "bottom2",
        data(){
            return{
              author:'',
              result: ['0','1','2','3'],
              tel: '',
              text: '',
              digit: '',
              number: '',
              password: '',
            }
        },
        mounted() {
        },
        methods:{
            doInsure () {
             this.$emit("doInsure");
            },
            doLocalStorage() {
              console.log('doLocalStorage')
              localStorage.setItem("getresult",this.result); //设置name为john
            },
            doStore() {
              this.author=this.$store.state.config.detailData.author;
            },
            doAuthor() {
              console.log('bottom状态修改')
              this.$store.state.config.detailData.author = 'bottom'; 
            }
        }  
      
    }
</script>

<style>
h3{text-align: center;}
#btn{width: 100%;}
</style>