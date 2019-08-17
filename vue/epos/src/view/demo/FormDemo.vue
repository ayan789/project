<template>
  <div class="formDemo">
    <el-col :span="20" class="colDiv">
   
         <h1>{{ msg }}</h1>
        <template>
         <el-date-picker
            v-model="value1"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
        </template>
        <template>
        <el-select v-model="value" placeholder="请选择">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
        </template>
        <template>
        <el-switch
        v-model="value2"
        active-color="#13ce66"
        inactive-color="#ff4949">
        </el-switch>
        </template>
        <template>
        <el-checkbox v-model="checked1" disabled>备选项1</el-checkbox>
        <el-checkbox v-model="checked2" disabled>备选项</el-checkbox>
        </template>
        <template>
              <input v-model="input2">
              </input>
        </template>
         <template>
        <input type="button" @click="get()" value="get test">
         </template>
        <template>
        <input type="button" @click="post()" value="post test">
        </template>
    </el-col>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'FormDemo',
  data () {
    return {
      msg: 'FormDemo',
      value1: '',
      options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: '',
        value2: true,
        checked1: false,
        checked2: true,
        input2: '123'
    }
  },
  methods: {
    get: function (){
      axios.get('http://localhost:4444/getDemo?a=1&b=10', {})
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    post: function (){
      var params = new URLSearchParams();
      params.append('a', 'value1');       //你要传给后台的参数值 key/value
      params.append('b', 'value2');
      this.$axios({
          method: 'post',
          url:'http://localhost:4444/postDemo',
          data:params
      }).then((res)=>{
          console.log(res);
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.demoDiv{
    height:2000px;
    background-color:blue;
}
.colDiv{
  background-color:red;
   height:2000px;
   margin: 0px; padding: 0px;
}
</style>
