<template>
  <el-card class="box-card" style="margin-top:10px;">
  <div class="container" style=" background-color: #fff;">
    <!-- 路径面包屑 开始 -->
    <el-breadcrumb class="base-breadcrumb" separator="/">
      <el-breadcrumb-item>运营平台</el-breadcrumb-item>
      <el-breadcrumb-item>公开信息披露管理</el-breadcrumb-item>
      <el-breadcrumb-item>公开信息披露通用管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 路径面包屑 结束 -->
    <el-form
      class="base-search-form"
      :inline="true"
      ref="searchForm"
      :model="searchForm"
      label-width="80px"
      :rules="rules"
    >
      <el-row>
       
        
        <el-form-item label="状态" prop="stateType">
          <el-select class="base-select" v-model="searchForm.stateType" placeholder="请选择">
            <el-option
              v-for="item in optionsArr.stateType"
              :key="item.code"
              :label="item.desc"
              :value="item.code"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="actName">
          <el-input class="base-input-text" v-model="searchForm.actName"></el-input>
        </el-form-item>
        <el-form-item label="一级菜单" prop="levelMenuOne">
          <el-select class="base-select" v-model="searchForm.levelMenuOne" placeholder="请选择">
            <el-option
              v-for="item in optionsArr.levelMenu"
              :key="item.code"
              :label="item.desc"
              :value="item.code"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="二级菜单" prop="levelMenuTwo">
          <el-select class="base-select" v-model="searchForm.levelMenuTwo" placeholder="请选择">
            <el-option
              v-for="item in levelMenuTwoData"
              :key="item.code"
              :label="item.desc"
              :value="item.code"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-row>
      <el-row style="margin-top:20px;">
        <el-form-item label="生效时间" prop="startTime">
          <el-date-picker
            v-model="searchForm.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm"
            format="yyyy-MM-dd HH:mm"
            style="width:200px;"
            placeholder="请选择日期时间(起)"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="endTime">
          <el-date-picker
            v-model="searchForm.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm"
            format="yyyy-MM-dd HH:mm"
            style="width:200px;"
            placeholder="请选择日期时间(止)"
          ></el-date-picker>
        </el-form-item>
        <!-- 功能按钮组 开始 -->
        <div class="base-search-form-btn-wrap"></div>
      </el-row>
    </el-form>

    <!-- 功能按钮组 开始 -->
    <div class="base-search-form-btn-wrap">
      <el-button type="primary" @click="search">搜索</el-button>
      <el-button type="infor" @click="handerResetSearchForm('searchForm')">重置</el-button>
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="danger" @click="removes">删除</el-button>
      <el-button type="primary" @click="publish('1')">发布</el-button>
      <el-button type="danger" @click="publishCacel('4')">取消发布</el-button>
    </div>
    <!-- 功能按钮组 结束 -->
    <!-- <el-main v-loading="state.ui.loading.isShow"> -->
      <el-main  v-loading="state.ui.loading.isShow" :element-loading-text="state.ui.loading.loadingMsg" class="mainTable">
      <!-- 搜索结果table 开始 -->
      <el-table
        class="base-result-table"
        :data="tableData"
        @selection-change="handlerSelectionChange"
        @cell-click="handlerSetCurrentRow"
        @cell-dblclick="handlerCellDblclick"
        border
        stripe
        style="width: 100%"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column
          type="index"
          label="序号"
          :show-overflow-tooltip="true"
          align="center"
          width="50"
        ></el-table-column>
        <!-- <el-table-column label="操作" align="center" width="150">
            <template slot-scope="scope">
            <el-button @click="edit(scope.row)" type="primary" size="mini">编辑</el-button>
            <el-button @click="detail(scope.row)" type="infor" size="mini">详情</el-button>
            <el-button @click="remove(scope.row)" type="danger" size="mini">删除</el-button>
          </template>
        </el-table-column>-->
        <el-table-column label="操作" align="center" width="170">
          <template slot-scope="scope">
            <el-button @click="detail(scope.row)" type="primary" size="medium">详情</el-button>
            <el-button @click="edit(scope.row)" type="infor" size="medium">编辑</el-button>
            <el-button @click="remove(scope.row)" type="danger" size="medium">删除</el-button>
          </template>
        </el-table-column>
        <el-table-column
          prop="titleName"
          label="标题"
          :show-overflow-tooltip="true"
          align="center"
          width="320"
        >
         <template slot-scope="{row,$index}">
            <div @click="{{chengenum(row,$index)}}" >
              <el-input v-if="editable[row,$index]" @blur="blurnum(row,$index)" v-model="grade"></el-input>
              <span v-else>{{row.titleName}}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="menuNameOne"
          label="一级菜单"
          :show-overflow-tooltip="true"
          align="center"
        >
          <!-- <template slot-scope="scope"> -->
          <!-- {{scope.row.stateType | commonFilter('stateType')}} -->
          <!-- </template> -->
        </el-table-column>
        <el-table-column
          prop="menuNameTwo"
          label="二级菜单"
          :show-overflow-tooltip="true"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="effectiveTime"
          label="生效时间"
          :show-overflow-tooltip="true"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="invalidTime"
          label="失效时间"
          :show-overflow-tooltip="true"
          align="center"
        ></el-table-column>
        <el-table-column prop="statusType" label="状态" :show-overflow-tooltip="true" align="center">
          <template slot-scope="scope">{{scope.row.statusType | commonFilter('stateTypeByIAE')}}</template>
        </el-table-column>
        <!-- <el-table-column prop="titleName" label="标题">
          <template slot-scope="{row,$index}">
            <div @click="{{chengenum(row,$index)}}" >
              <el-input v-if="editable[row,$index]" @blur="blurnum(row,$index)" v-model="grade"></el-input>
              <span v-else>{{row.statusType}}</span>
            </div>
          </template>
        </el-table-column> -->
        <!-- <el-table-column prop="createBy" label="创建人" :show-overflow-tooltip="true" align="center"></el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          :show-overflow-tooltip="true"
          align="center"
        ></el-table-column>
        <el-table-column prop="updateBy" label="修改人" :show-overflow-tooltip="true" align="center"></el-table-column>
        <el-table-column
          prop="updateTime"
          label="修改时间"
          :show-overflow-tooltip="true"
          align="center"
        ></el-table-column>-->
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="right" inline class="demo-table-expand">
              <el-form-item label="创建人">
                <span>{{ props.row.createBy==undefined?'暂无': props.row.createBy}}</span>
              </el-form-item>
              <el-form-item label="创建时间">
                <span>{{ props.row.updateTime==undefined?'暂无': props.row.updateTime }}</span>
              </el-form-item>
              <el-form-item label="修改人">
                <span>{{ props.row.updateBy==undefined?'暂无': props.row.updateBy }}</span>
              </el-form-item>
              <el-form-item label="修改时间">
                <span>{{ props.row.updateTime==undefined?'暂无': props.row.updateTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <div class="base-search-form-btn-wrap">
      <!-- <el-button type="primary" @click="publish('1')">发布</el-button>
      <el-button type="danger" @click="publishCacel('4')">取消发布</el-button>-->
      <!-- <el-button type="primary" @click="add">新增</el-button>
      <el-button type="danger" @click="remove()">删除</el-button>-->
    </div>
    <!-- 分页组件 -->
    <div class="base-result-pager pd-md">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5 , 10, 20, 50, 100]"
        :current-page.sync="searchForm.pageNo"
        :page-size.sync="searchForm.pageSize"
        :total="searchForm.total"
        @size-change="handleSizeChange"
        @current-change="handlerCurrentChange"
      ></el-pagination> 
    </div>
    <!-- 搜索结果table 结束 -->
    <!-- 用户详情 -->
    <el-dialog width="650px">
      <demo-detail></demo-detail>
    </el-dialog>
  </div>
  </el-card>
</template>

<script>
import store from "@/store";
export default {
  name: "information1",
  data() {
    return {
      attr:null,
      attrB:null,
      grade:'',
      editable: [],
      state: this.$store.state, //state状态对象
      msg: "Information",
      expireTimeOption: {
        disabledDate(date) {
          //disabledDate 文档上：设置禁用状态，参数为当前日期，要求返回 Boolean
          let today = new Date();
          let oneday = 1000 * 60 * 60 * 24;
          let yesterday = new Date(today - oneday);
          return date.getTime() <= yesterday.getTime();
        }
      },
      //下拉选项集合
      optionsArr: {
        stateType: codeData.stateType, //状态
        levelMenu: codeData.InforLevelMenu //菜单
      },
      //搜索form
      searchForm: {
        levelMenuOne: "", //一级菜单levelMenuOne
        levelMenuTwo: "", //二级菜单
        actName: "", //标题
        stateType: "", //状态
        startTime: null, //生效时间
        endTime: null, //失效时间
        pageNo: 1, //当前是第几页
        pageSize: 10, //一页多少条数据,
        total: null
      },
      tableData: [], //列表数组
      levelMenuTwoData: [], //二级菜单的内容  需要通过点击以及菜单来获取
      multipleSelection: [], //当前多选列表: [], //当前多选列表
      //表单验证rules
      rules: {
        // actName: [
        //   { required: true, message: "请输入标题", trigger: "blur" },
        //   {
        //     min: 0,
        //     message: "标题至少0个字符",
        //     trigger: "change"
        //   }
        // ]
      }
    };
  },
  mounted() {
    console.log(codeData);
    console.log(tableData);
    //进入页面先查询一次
    this.search();
  },
  methods: {
    //增
    add() {
      console.log("跳转到新增页面");
    },
    //删
    remove(row) {
      console.log("携带数据并删除");
      console.log(row);
    },
    //删(批量)
    removes() {
      console.log("携带数据并批量删除");
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
    //查
    search() {
      //form校验
      this.$refs["searchForm"].validate(valid => {
        console.log(valid); //校验表格
        if (valid) {
          console.log("关键字搜索");
          console.log(this.searchForm); //关键字搜索
          utils.http
            .get("notice_1/_23bd6e41e40511e8", this.searchForm)
            .then(res => {
              console.log("搜索得到的数据:");
              console.log(res);
              let resultAll = res.data.data.rows;
              this.tableData = resultAll; //获取到当前页的数据
              this.searchForm.total = res.data.data.total; //获取到总条数
              // console.log(res);
            })
            .catch(res => {
              this.$message.error("获取数据失败，请检查网络!");
            });
        } else {
          this.$alert("请将信息填写完整", "提示", {
            confirmButtonText: "确定"
          });
          return;
        }
      });

      // let resultAll = tableData.data.rows //获取到当前页的数据
      // this.tableData = resultAll
      // console.log(this.tableData)
    },
    //发布
    publish(status) {
      console.log("携带数据和标识并发布");
      console.log(status);
      console.log(this.multipleSelection);
    },
    //取消发布
    publishCacel(status) {
      console.log("携带数据和标识并取消发布");
      console.log(status);
      console.log(this.multipleSelection);
    },
    //重置搜索框
    handerResetSearchForm(formName) {
      this.$refs[formName].resetFields();
    },
    //table checkbox改变事件
    handlerSelectionChange(val) {
      console.log("tablecheckbox改变事件");
      console.log(val);
      this.multipleSelection = val;
    },
     handlerSetCurrentRow(val,column) {
      console.log("选中当前行");
      console.log(val); 
      console.log(column); 
      console.log(column.property); 
      console.log(this.attr=column.property); 

      console.log(this.attrB)
      console.log(val.titleId)
      console.log(this.grade)
    },
    handlerCellDblclick(val) {
      console.log("离开当前行");
      console.log(val); 
    },
    //当前每页显示条数发生改变
    handleSizeChange(val) {
      this.searchForm.pageSize = val
      this.search()
    },
    //当前页数改变事件
    handlerCurrentChange(val) {
      this.search()
    },
    chengenum(row,index) {
      console.log(row)
      console.log(index)
      this.editable[index] = true
      console.log('row.titleName')
      console.log(row.titleName)
      this.grade=row.titleName
      this.$set(this.editable, index, true)
    },
    blurnum(row,index) {
      this.attrB=this.attr
      console.log('bbbbbbbbb')
      console.log(row)
      console.log(this.attrB)
      console.log(row.titleId)
      console.log(this.grade)
      
       utils.http
            .get("notice_1/upd", {"titleId":row.titleId,"titleName":this.grade})
            .then(res => {
              console.log("修改成功");
              
            })
            .catch(res => {
              this.$message.error("获取数据失败，请检查网络!");
            });

      this.$set(this.editable, index, false)
     
      this.search();
    }
  },
  watch: {
    //点击一级菜单的时候对应拿到二级菜单的内容
    "searchForm.levelMenuOne"(val) {
      console.log(val);
      for (var i = 0; i < this.optionsArr.levelMenu.length; i++) {
        switch (val) {
          case " ":
            this.levelMenuTwoData = this.optionsArr.levelMenu[0].children;
            break;
          case "c4307348e3f211e88":
            this.levelMenuTwoData = this.optionsArr.levelMenu[1].children;
            break;
          case "3c4322570e3f211e88":
            this.levelMenuTwoData = [];
            break;
          case "c433c5d0e3f211e88":
            this.levelMenuTwoData = [];
            break;
          case "c4355b11e3f211e88":
            this.levelMenuTwoData = this.optionsArr.levelMenu[4].children;
            break;
        }
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/css/main.scss";
.container {
//  height: 680px;
 height:80vh;
 overflow-y: auto;
}
.base-search-form-btn-wrap{
  float:right;
  margin-right: 20px;
}
.mainTable{
   clear:both 
}
.base-result-pager{
margin-bottom: 20px;
}
</style>


