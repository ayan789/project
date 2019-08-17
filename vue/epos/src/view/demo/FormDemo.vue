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
        <el-form :model="ruleForm2" status-icon ref="ruleForm2" label-width="100px" class="demo-ruleForm"  :rules="rules">
          <el-form-item prop="name"  style="width:400px;">
            <span v-text="ruleForm2_name"></span>
            <el-input type="text" v-model="ruleForm2.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="pass"  style="width:400px;">
            <span v-text="ruleForm2_pass"></span>
            <el-input type="text" v-model="ruleForm2.pass" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="age"  style="width:400px;">
            <span v-text="ruleForm2_age"></span>
            <el-input type="text" v-model="ruleForm2.age"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm()">提交</el-button>
            <el-button @click="resetForm('ruleForm2')">重置</el-button>
          </el-form-item>
        </el-form>
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
        input2: '123',
        searchForm: {
          a:'123',
          b:'456'
        },
        ruleForm2:{
          name:'',
          pass:'',
          age:''
        },
        ruleForm2_name:'用户名',
        ruleForm2_pass:'密码',
        ruleForm2_age:'年龄',
        //表单验证rules
        rules: {
          name: [
             { required: true, message: "请输入姓名", trigger: "blur" },
             {
               min: 0,
               message: "标题至少0个字符",
               trigger: "change"
             }
           ],
           pass: [
             { required: true, message: "请输入密码", trigger: "blur" },
             {
               min: 0,
               message: "标题至少0个字符",
               trigger: "change"
             }
           ],
           age: [
             { required: true, message: "请输入年龄", trigger: "blur" },
             {
               min: 0,
               message: "标题至少0个字符",
               trigger: "change"
             }
           ]
        }
    }
  },
  methods: {
    get: function (){
      utils.http.get("/getDemo", this.searchForm)
      .then(res => {
          console.log(res);
      })
    },
    post: function (){
      utils.http.post("/postDemo", this.searchForm)
      .then(res => {
          console.log(res);
      })
    },
    submitForm: function (){
       this.$refs["ruleForm2"].validate(valid => {
        console.log(valid); //校验表格
        if (valid) {
          console.log('submitForm');
        }
       });
    },
     resetForm(formName){
       console.log(formName);
       //重置
      this.$refs[formName].resetFields();
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
   height:2000px;
   margin: 0px; padding: 0px;
}
</style>
