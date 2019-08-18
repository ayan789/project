<template>
  <div class="formDemo">
    <el-col :span="20" class="colDiv">
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
              <el-input style="width:100px;" v-model="input2">
              </el-input>
        </template>
        <template>
        <el-button type="primary" @click="get()" style="width:100px;">get</el-button>
         </template>
        <template>
        <el-button type="primary" @click="post()" style="width:100px;">post</el-button>
        </template>
        <template>
          <YsInput @func="getMsgFormSon" :fudata="fudata"></YsInput>
          <Child :fudata="msgFormSon"></Child>
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
            <el-button type="primary" @click="resetForm('ruleForm2')">重置</el-button>
            <el-button type="primary" @click="add">新增</el-button>
            <el-button type="primary" @click="removes">删除</el-button>
            <el-button type="primary">搜索</el-button>
            <el-button type="primary" @click="edits">编辑</el-button>
            <el-button type="primary" @click="change">get方式传参跳转</el-button>
            <el-button type="primary" @click="change2">post方式传参跳转</el-button>
          </el-form-item>
         <template>
            <el-table
              type="index"
               @selection-change="handlerSelectionChange"
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column type="selection" width="55" align="center"></el-table-column>
              <el-table-column
                type="index"
                label="序号"
                :show-overflow-tooltip="true"
                align="center"
                width="50"
              ></el-table-column>
              <el-table-column
                prop="date"
                label="日期"
                width="180">
              </el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
                width="180">
              </el-table-column>
              <el-table-column
                prop="address"
                label="地址">
              </el-table-column>
              <el-table-column label="操作" align="center" width="470">
                <template slot-scope="scope">
                  <el-button type="primary" @click="detail(scope.row)" size="medium">详情</el-button>
                  <el-button type="infor" @click="edit(scope.row)"  size="medium">编辑</el-button>
                  <el-button type="danger" @click="remove(scope.row)" size="medium">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>

        </el-form>
    </el-col>
  </div>
</template>

<script>
import axios from 'axios'
import YsInput from '@/view/common/YsInput.vue'
import Child from '@/view/common/Child.vue'
export default {
  name: 'FormDemo',
  data () {
    return {
      msg: 'FormDemo',
      value1: '',
      inputvalue:'',
      
      multipleSelection: [], //当前多选列表: [], //当前多选列表
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
        id:'666',
        msgFormSon: "this is msg",
        fudata:'fudata',
        value: '',
        value2: true,
        checked1: false,
        checked2: true,
        input2: '123',
        searchForm: {
          a:'123',
          b:'456'
        },
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
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
  components: {
        'YsInput':YsInput,
        'Child':Child
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
    },
    //增
    add() {
      console.log("跳转到新增页面");
    },
     //删(批量)
    removes() {
      console.log("携带数据并批量删除");
      console.log(this.multipleSelection);
      this.open6();
    },
    //table checkbox改变事件
    handlerSelectionChange(val) {
      console.log("tablecheckbox改变事件");
      console.log(val);
      this.multipleSelection = val;
    },
    edits() {
      console.log("携带数据编辑");
      console.log(this.multipleSelection);
    },
     //改
    edit(row) {
      console.log("携带数据并跳转到编辑页面");
      console.log(row);
    },
     //详情
    detail(row) {
      console.log("携带数据并查看详情");
      console.log(row);
    },
     //删
    remove(row) {
      console.log("携带数据并删除");
      console.log(row);
    },
    open6() {
        this.$message({
          showClose: true,
          message: '删除成功',
          type: 'success'
        });
    },
    change(){
      this.$router.push({  //核心语句
        path:'/layout/information/informationPage1',   // * post能用path or name 参数页面刷新还有
        query:{           // get传参只能用query 
          id:this.id ,  
        }
      })
    },
    change2(){
      this.$router.push({  //核心语句
        name:'informationPage1',   // * post只能用name 参数页面刷新就没了
        params: {                  // post传参只能用params
          id:this.id ,  
          }
      })
    },
    getMsgFormSon(data){
        this.msgFormSon = data
        console.log(this.msgFormSon)
    },
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
