<template>
    <div class="book">
      <h3>{{msg}}</h3>
      <prHeader2 :proHeader="proHeader"></prHeader2>
      <prBody></prBody>
      <h3>{{msg2}}</h3>
      <prBottom2 @doInsure="doInsure"></prBottom2>
      <van-button id="btn"
            type="info" 
            @click="doCreateForm" 
          >create form</van-button> 
    </div>
</template>

<script>
import prHeader2 from "../autoConfig/common/prHeader2";
import prBody from "../autoConfig/common/prBody";
import prBottom2 from "../autoConfig/common/prBottom2";
    export default {
      components: {
          prHeader2,
          prBody,
          prBottom2
        },
        name: "book",
        data(){
            return{
              msg:'nonCar',
              msg2:'',
              proHeader: 'nonCar->proHeader',
            }
        },
        mounted() {
        },
        methods:{
          doInsure () {
             this.msg2='prBottom->nonCar';
          },
          doCreateForm(){
              this.$router.push({
              name: "createForm",
              query: {
                orderId: '123456'
              }
            })
          }
        }
    }
</script>

<style>
h3{text-align: center;}
</style>