<template>
  <div>
    <van-nav-bar
      left-arrow
      @click-left="back()"
      @click-right="
        () => {
          this.edit();
        }
      "
    >
      <div slot="left">
        <span v-if="!flag"><van-icon name="arrow-left"/></span>
        <span v-else @click="checkAll">{{
          !!isCheckAll ? "全选" : "全不选"
        }}</span>
      </div>
      <div slot="title">
        <span v-if="!flag">跟踪记录</span>
        <span v-else>选中{{ checkNum }}条</span>
      </div>
      <div slot="right">
        <span>{{ !flag ? "编辑" : "取消" }}</span>
      </div>
    </van-nav-bar>
    <div class="renwal-record">
      <van-list
        v-model="loading"
        :finished="finished"
        @load="onLoad"
        :error.sync="error"
        :immediate-check="true"
        error-text="请求失败，点击重新加载"
      >
        <template #finished>
          <div
            v-show="Array.isArray(resultData) && resultData.length == 0"
            class="noData"
          ></div>
        </template>
        <div
          class="record-content"
          v-for="(item, index) in resultData"
          :key="index"
        >
          <van-row>
            <van-col span="6">
              <span class="record-content_title">联系日期</span>
              <van-popup v-model="item.showCalendar" position="bottom">
                <van-datetime-picker
                  v-model="showDate"
                  type="date"
                  @cancel="item.showCalendar = false"
                  @confirm="
                    date => {
                      disabled = false;
                      confirmDate(date, index);
                      item.showCalendar = false;
                    }
                  "
                />
              </van-popup>
            </van-col>
            <van-col span="12">
              <span
                class="record-content_item"
                v-longTouch="
                  () => {
                    showCalendar_(item);
                  }
                "
                >{{ item.createTime | filterDate }}</span
              >
            </van-col>
            <van-col class="record-content--checkout">
              <van-checkbox
                icon-size="20"
                v-model="item.isDeb"
                checked-color="#3B8AFE"
                v-if="item.showEdit"
              ></van-checkbox>
            </van-col>
          </van-row>
          <van-row>
            <van-col span="24">
              <van-field
                v-model="item.recordContent"
                rows="1"
                autosize
                type="textarea"
                placeholder="请输入"
                show-word-limit
                maxlength="50"
                :readonly="item.readonlyInput"
                v-longTouch="
                  () => {
                    longpress(index, item);
                  }
                "
                @input="
                  () => {
                    disabled = false;
                  }
                "
              >
                <div slot="label" class="record-content_title">
                  联系内容
                </div>
              </van-field>
            </van-col>
          </van-row>
        </div>
      </van-list>

      <div class="record-operation">
        <van-button type="default" @click="addDialog = true" v-if="!delButton"
          >新增</van-button
        >
        <van-button
          type="info"
          v-if="!delButton"
          :disabled="disabled"
          @click="save"
          >保存</van-button
        >
        <van-button
          type="info"
          v-if="delButton"
          @click="delData"
          :style="{ width: '100%' }"
          >删除</van-button
        >
      </div>
      <record-dialog
        :addDialog.sync="addDialog"
        v-if="addDialog"
        @add="addRecord"
      ></record-dialog>
    </div>
  </div>
</template>

<script>
import { Form, Calendar, CheckboxGroup, Checkbox, Dialog, List } from "vant";
import longTouch from "@src/utils/vueDirectives";
//获取跟踪记录列表 批量删除跟踪记录
import {
  renewTraceRecordQuery,
  deleteRenewTraceRecordList,
  insertRenewTraceRecord,
  updateRenewalTraceRecordList
} from "@src/http/module/renewal.js";
import * as native from "../../../utils/JSBridge";

export default {
  directives: { longTouch },
  components: {
    [Form.name]: Form,
    [Calendar.name]: Calendar,
    [CheckboxGroup.name]: CheckboxGroup,
    [Checkbox.name]: Checkbox,
    [Dialog.name]: Dialog,
    [List.name]: List,
    //新增记录弹框
    recordDialog: () => import("./renewalTrackRecordDialog")
  },
  data: () => ({
    minDate: new Date(2019, 0, 0),
    showDate: new Date(),
    date: "",
    message: "",
    isCheckAll: false, //是都全选
    checkNum: "", //选中条数
    addDialog: false, //控制新增弹框弹出
    delButton: false, //控制点击编辑后可以删除
    showEdit: false, //控制点击编辑后单选框显示
    flag: false, //控制点击编辑后单选框显示
    disabled: true, //保存按钮默认禁选编辑后可以保存
    addData: {
      recordContent: "", //联系内容
      check: "", //是否选中
      createTime: "", //联系日期
      showCalendar: false, //是否显示日历控件
      readonly: true //是否只读，长按后可编辑
    }, //弹框新增数据
    resultData: [], //跟踪记录数据
    queryData: {
      pageNum: 1,
      pageSize: 10,
      userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || ""
    },
    delList: [], //选中删除的
    loading: false,
    finished: false,
    error: false,
    resultDataTemp: []
  }),
  created() {
    //页面初始化获取跟踪记录列表
    // this.queryRecordList();
  },
  computed: {
    policyNo() {
      return this.$route.params.policyNo || "";
    },
    frameNo() {
      return this.$route.params.frameno || "";
    },
    licenseNo() {
      return this.$route.params.licenseNo || "";
    },
    respEndDate() {
      return this.$route.params.respEndDate || "";
    },
    riskCode() {
      return this.$route.params.riskCode || "";
    },
    compulsoryFlag() {
      return this.$route.params.compulsoryFlag || "";
    },
    is_compulsory_insurance() {
      return this.$route.params.is_compulsory_insurance || "";
    },
    userCode() {
      return JSON.parse(localStorage.getItem("userInfo")).userCode || "";
    },
    paramsData() {
      return { ...this.$route.params };
    }
  },
  filters: {
    filterDate(val) {
      // eslint-disable-next-line no-useless-escape
      if (!val) return "";
      return val.split("T")[0];
    }
  },
  methods: {
    //长按日期
    showCalendar_(item) {
      item.showCalendar = !item.showCalendar;
    },
    //长按时间
    longpress(index, item) {
      // item.readonly = false;
      this.resultData[index].readonlyInput = false;
      this.$nextTick(() => {
        // this.resultData[index].readonlyInput = false;
      });
    },
    onLoad() {
      this.queryRecordList();
    },
    //新增跟踪记录
    addRecord(val) {
      val.createTime = new Date(val.createTime).format("yyyy-MM-dd");
      this.resultData.push({ ...val });
      let params = {
        createTime: val.createTime,
        compulsoryFlag: this.compulsoryFlag,
        frameNo: this.frameNo,
        is_compulsory_insurance: this.is_compulsory_insurance,
        licenseNo: this.licenseNo,
        policyNo: this.policyNo,
        recordContent: val.recordContent,
        respEndDate: this.respEndDate,
        riskCode: this.riskCode,
        userCode: this.userCode
      };
      insertRenewTraceRecord({ ...params }).then(res => {
        if (res.code === "1") {
          //console.log("新增跟踪记录成功");
          this.$toast.success("新增跟踪记录成功!");
          // this.queryRecordList();
        }
      });
    },
    //编辑按钮
    edit() {
      this.flag = !this.flag;
      Object.keys(this.resultData).forEach(index => {
        this.$set(this.resultData[index], "showEdit", this.flag);
        this.$set(this.resultData[index], "isDeb", false);
        this.$set(this.resultData[index], "readonlyInput", true);
      });
      this.disabled = true;
      this.delButton = !this.delButton;
    },
    //保存编辑记录
    save() {
      let obj = [];
      if (this.resultData.lenght === this.resultDataTemp.length) {
        //筛选出修改的数据
        obj = this.resultData.filter(
          (item, index) =>
            item.createTime != this.resultDataTemp[index].createTime ||
            item.recordContent != this.resultDataTemp[index].recordContent
        );
      } else {
        obj = this.resultData;
      }
      obj.forEach((item, index) => {
        obj[index].compulsoryFlag = this.compulsoryFlag;
        obj[index].frameNo = this.frameNo;
        // obj[index].is_compulsory_insurance = this.is_compulsory_insurance;
        obj[index].licenseNo = this.licenseNo;
        obj[index].compulsoryFlag = this.compulsoryFlag;
        obj[index].respEndDate = this.respEndDate;
        obj[index].riskCode = this.riskCode;
        obj[index].userCode = localStorage.getItem("userCode");
        delete obj[index].deleted;
        delete obj[index].createUser;
        delete obj[index].updateUser;
        delete obj[index].showCalendar;
        delete obj[index].readonly;
        delete obj[index].showEdit;
        delete obj[index].isDeb;
        delete obj[index].updateTime;
      });
      updateRenewalTraceRecordList({
        recordList: [...obj],
        userCode: localStorage.getItem("userCode")
      }).then(res => {
        if (res.code === "1") {
          this.disabled = true;
          this.resultData.forEach(item => (item.readonlyInput = true));
          this.$dialog.alert({
            message: "保存成功!"
          });
        } else {
          this.$dialog.alert({
            message: res.msg || res.message
          });
        }
      });
    },
    //是否全选
    checkAll() {
      this.isCheckAll = !this.isCheckAll;
      Object.values(this.resultData).forEach(
        item => (item.isDeb = !this.isCheckAll)
      );
    },
    //删除按钮
    delData() {
      //是否选中
      if (!this.resultData.some(item => item.isDeb === true)) {
        this.$dialog.alert({
          message: "请至少选中一条数据！"
        });
        return false;
      }
      Dialog.confirm({
        title: "",
        message: "是否确认删除？"
      })
        .then(() => {
          this.resultData = [
            ...Object.values(this.resultData).filter(item => item.isDeb != true)
          ];
          this.deleteRecordList();
        })
        .catch(() => {});
    },
    //选择完日期
    confirmDate(date, i) {
      this.$set(
        this.resultData[i],
        "createTime",
        new Date(date).format("yyyy-MM-dd")
      );
    },
    //获取跟踪记录
    async queryRecordList() {
      const vm = this;
      this.queryData = Object.assign({}, this.queryData, {
        policyNo: this.policyNo,
        licenseNo: this.licenseNo,
        respEndDate: this.respEndDate,
        frameNo: this.frameNo,
        riskCode: this.riskCode,
        compulsoryFlag: this.compulsoryFlag
      });
      await renewTraceRecordQuery(this.queryData)
        .then(res => {
          //console.log(res);
          if (res.code === "1" && res.data) {
            this.resultData.push(...res.data.content);
            Object.keys(this.resultData).forEach(index => {
              // this.$set(this.resultData[index], "showEdit", this.flag);
              // this.$set(this.resultData[index], "isDeb", false);
              this.$set(this.resultData[index], "showCalendar", false);
              this.$set(this.resultData[index], "readonlyInput", true);
              // this.$set(
              //   this.resultData[index],
              //   "createTime",
              //   this.resultData[index].createTime.split("T")[0]
              // );
            });
            this.loading = false;
            this.queryData.pageNum += 1;
            //console.log(this.resultData);
            //深拷贝一份数据 保存时作比较
            this.resultDataTemp = JSON.parse(JSON.stringify(this.resultData));
            if (this.resultData.length >= res.data.totalElements) {
              this.finished = true;
            }
          } else {
            this.$dialog.alert({
              message: res.msg || res.message,
              callback: () => {
                vm.$router.back();
              }
            });
          }
        })
        .catch(err => {
          this.error = true;
        });
    },
    //批量删除跟踪记录
    async deleteRecordList() {
      this.delList.forEach((item, index) => {
        this.delList[index].compulsoryFlag = this.compulsoryFlag;
        this.delList[index].frameNo = this.frameNo;
        this.delList[
          index
        ].is_compulsory_insurance = this.is_compulsory_insurance;
        this.delList[index].licenseNo = this.licenseNo;
        this.delList[index].compulsoryFlag = this.compulsoryFlag;
        this.delList[index].respEndDate = this.respEndDate;
        this.delList[index].riskCode = this.riskCode;
        delete this.delList[index].deleted;
        delete this.delList[index].createUser;
        delete this.delList[index].updateUser;
        delete this.delList[index].showCalendar;
        delete this.delList[index].readonly;
        delete this.delList[index].showEdit;
        delete this.delList[index].isDeb;
        delete this.delList[index].updateTime;
      });
      deleteRenewTraceRecordList({
        recordList: [...this.delList]
      }).then(res => {
        if (res.code === "1") {
          this.$dialog.alert({
            message: "删除成功"
          });
        } else {
          this.$dialog.alert({
            message: res.msg || res.message
          });
        }
      });
    },
    back() {
      if (this.flag) return false;
      this.$router.back();
    }
  },
  watch: {
    flag(val) {
      this.isCheckAll = val == false;
    },
    resultData: {
      handler(newVal, oldVal) {
        this.delList = Object.values(this.resultData).filter(
          item => item.isDeb === true
        );
        this.checkNum = this.delList.length;
        // if (JSON.stringify(newVal) != JSON.stringify(oldVal)) {
        let isCheck = Object.values(this.resultData).filter(
          item => item.isDeb === false
        );
        if (isCheck.length === 0) this.isCheckAll = false;
        if (isCheck.length === this.resultData.length) this.isCheckAll = true;
        if (oldVal.length == 0) {
          return false;
        }
        // }
      },
      deep: true
    }
  }
};
</script>

<style lang="less" scoped>
.renwal-record {
  padding: 0 15px;
  // background: #fff;
  // margin-top: 50px;
  height: calc(100vh - 46px);
  overflow-y: scroll;
  .van-list {
    margin-bottom: 46px;
  }
  .record-content {
    background: #fff;
    padding: 15px 15px;
    border-radius: 8px;
    margin: 10px 0;
    // margin-bottom: 10px;
    /deep/.van-cell {
      padding: 0;
    }
    /deep/ .van-cell__value {
      margin-left: -20px;
      background: rgba(245, 245, 245, 1);
      border-radius: 4px;
      min-height: 41px;
    }
    /deep/.van-field__body {
      padding: 10px;
    }
    .record-content_item {
      width: 172px;
      height: 36px;
      line-height: 36px;
      background: rgba(245, 245, 245, 1);
      border-radius: 4px;
      display: inline-block;
      font-size: 14px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      padding-left: 13px;
      margin-bottom: 10px;
    }
    .record-content--checkout {
      margin-left: 55px;
      margin-top: 10px;
    }
    .record-content_title {
      font-size: 14px;
      font-family: PingFang-SC-Medium, PingFang-SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      vertical-align: -8px;
    }
  }
  .record-operation {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    /deep/.van-button {
      width: 50%;
    }
    /deep/ .van-button--default {
      height: 46px;
      background: rgba(255, 255, 255, 1);
      border: 1px solid rgba(229, 229, 229, 1);
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(102, 102, 102, 1);
    }
    /deep/ .van-button--info {
      height: 46px;
      background: rgba(59, 138, 254, 1);
    }
  }
  /deep/.van-popup {
    border-radius: 10px;
  }
}
.noData {
  width: 180px;
  height: 150px;
  margin: 0 auto;
  margin-top: 76px;
  background: url("../../../assets/renewal/renewalListData.png") no-repeat;
  background-size: cover;
  position: relative;
  // img {
  //   width: 180px;
  //   height: 150px;
  //   display: block;
  // }
  &::after {
    left: 50%;
    position: absolute;
    bottom: -50px;
    transform: translateX(-50%);
    content: "暂无数据";
    display: block;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
  }
}
</style>
