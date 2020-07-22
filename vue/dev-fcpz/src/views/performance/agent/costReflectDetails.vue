<template>
  <div class="costReflectDetails">
    <div class="costReflectDetails-head">
      <!-- $router.go(-1) -->
      <span @click="globalBack()"><van-icon name="arrow-left"/></span>
      <span>提现</span>
      <span><van-icon name="ellipsis" :size="21" /> </span>
    </div>
    <p class="setAuto">
      <span>当前为{{ isAutomatic == "1" ? "自动" : "手动" }}提现模式</span
      ><span @click="showSetAuto = true">设置</span>
    </p>
    <van-popup
      v-model="showSetAuto"
      position="bottom"
      :style="{ height: '100%' }"
    >
      <set-auto
        :showSetAuto.sync="showSetAuto"
        v-if="showSetAuto"
        @refreshList="refreshList"
      ></set-auto>
    </van-popup>

    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      :error.sync="error"
      error-text="请求失败，点击重新加载"
      @load="onLoad"
      :immediate-check="false"
    >
      <p class="costReflectDetails-formHeader">
        <span>保单号/批单号</span>
        <span>手续费</span>
      </p>
      <p
        class="costReflectDetails-content"
        v-for="(item, i) in resultData"
        :key="i"
      >
        <span> {{ item.certiNo }}</span>
        <span>
          {{
            Number(item.planFee) > 0 ? `+${item.planFee}` : `${item.planFee}`
          }}元</span
        >
        <van-checkbox
          v-model="item.checked"
          @click="feePay(item)"
          :disabled="item.disabled"
          :size="16"
        ></van-checkbox>
      </p>
    </van-list>
    <!-- <p @click="morePage" v-if="finished" class="morePage">查看更多</p> -->
    <div class="costReflectDetails-total">
      <p>
        <span>¥{{ amountAll.toFixed(2) }}</span>
        <span>合计</span>
      </p>
      <van-checkbox
        v-model="checkedAll"
        shape="square"
        icon-size="16"
        :disabled="autoDisabled"
        >全选</van-checkbox
      >
      <van-button color="#3B8AFE" @click="cashOut()" :disabled="autoDisabled"
        >提现</van-button
      >
    </div>
  </div>
</template>

<script>
import { Checkbox, List, Dialog, Toast } from "vant";
import {
  queryWithdrawalConfig,
  queryPolicyList,
  billPackToFK,
  planFeeInquiry
} from "@src/http/module/performance.js";
export default {
  components: {
    [Checkbox.name]: Checkbox,
    [List.name]: List,
    [Dialog.Component.name]: Dialog.Component,
    [Toast.name]: Toast,
    //设置自动
    setAuto: () => import("./setAuto")
  },
  data: () => ({
    loading: false,
    finished: false,
    error: false,
    checkedAll: false, //是否全选
    showSetAuto: false, //设置自动模式弹出框
    isAutomatic: "", //1 自动 2 手动 ---提现
    sumPlanFee: "", //总金额
    formData: {
      page: { size: 10, page: 1 },
      userCode: "",
      agentCode: "",
      handlerCode: ""
    },
    resultData: [], //提现数据
    checkedData: [],
    cashOutData: {}, //选中数据
    policyNOList: [], //
    amountAll: 0.0 //全选择金额
  }),
  computed: {
    autoDisabled: {
      get() {
        let flag = this.resultData.every(v => v.itemAttribute === "4");
        return this.isAutomatic === "1" || flag ? true : false;
      }
    }
  },
  async created() {
    //全局获取缓存数据
    this.$globalSetLocalStorage();
    /**
     * 页面初始化
     * 确保列表渲染出来去判断
     * 用户选择自动提现，则所有保单默认全选，且无法取消；同时，提现按钮为灰，不可用
     */
    setTimeout(() => {
      this.formData = {
        page: { size: 10, page: 1 },
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
        agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
        handlerCode:
          JSON.parse(localStorage.getItem("userInfo")).handlerCode || ""
      };
      this.queryList().then(() => {
        this.queryConig();
      });
    }, 800);
  },
  watch: {
    resultData: {
      handler(val) {
        if (!this.checkedAll) {
          this.amountAll = 0.0;
          this.checkedData = val.reduce((prev, cur) => {
            if (cur.checked === true && cur.itemAttribute != "4") {
              prev = [...prev, cur];
              this.amountAll += parseFloat(cur.planFee);
            }
            return prev;
          }, []);
        }
      },
      deep: true
    },
    checkedAll(val) {
      this.resultData.forEach(item => {
        if (Number(item.planFee) > 0 && item.itemAttribute != "4") {
          item.checked = val;
        }
      });
      this.amountAll = val ? Number(this.sumPlanFee) : (this.amountAll = 0.0);
    }
  },
  methods: {
    /* --修改--添加 */
    globalBack() {
      if (window.WebViewJavascriptBridge) {
        this.$native.closeWeb(data => {});
      } else {
        this.$globalBack();
      }
    },
    refreshList() {
      this.formData.page = Object.assign({}, this.formData.page, {
        size: 10,
        page: 1
      });
      this.resultData = [...[]];
      this.queryList().then(() => {
        this.queryConig();
      });
    },
    //全选
    // onChange(flag) {
    //   if (flag) {
    //     this.checkedData = this.resultData.reduce((prev, cur) => {
    //       this.amountAll = 0.0;
    //       if (cur.checked === true && cur.itemAttribute != "4") {
    //         prev = [...prev, cur];
    //         this.amountAll += parseFloat(cur.planFee);
    //       }
    //       if (Number(cur.planFee) > 0 && cur.itemAttribute != "4") {
    //         cur.checked = flag;
    //       }
    //       return prev;
    //     }, []);
    //     console.log(this.amountAll, "====");
    //   } else {
    //     this.resultData.forEach(item => {
    //       if (Number(item.planFee) > 0 && item.itemAttribute != "4") {
    //         item.checked = flag;
    //       }
    //       this.amountAll = 0.0;
    //     });
    //   }
    // },
    //提现
    cashOut() {
      const sumFeePayMonth = JSON.parse(localStorage.getItem("sumFeePayMonth"));
      const minPayFee = JSON.parse(localStorage.getItem("minPayFee"));
      //TODO 后续上生产打开
      if (this.amountAll <= minPayFee || this.amountAll == 0.0) {
        this.$toast.fail("可提现手续费小于提现限额，目前不能提现!");
        return false;
      }
      if (this.amountAll + sumFeePayMonth >= 30900 && this.checkedAll) {
        Dialog.confirm({
          message: `本笔金额提现后，当月提现总金额为${this.amountAll +
            sumFeePayMonth}元，超过30900元，税率将大幅提高，请确认是否要继续提现！`,
          confirmButtonText: "确定",
          showCancelButton: true,
          cancelButtonText: "取消"
        })
          .then(() => {
            if (this.checkedAll) {
              this.formData;
            } else {
              this.checkedData.map(v => {
                if (v.checked === true && v.itemAttribute !== "4" && v.certiId)
                  this.policyNOList.push(v.certiId);
              });
            }
            this.formData = Object.assign({}, this.formData, {
              page: {
                page: 1,
                size: 10
              }
            });
            this.resultData = [...[]];
            this.billPackToFK_();
          })
          .catch(() => {});
      } else if (this.amountAll < 30900 && this.checkedAll) {
        Dialog.confirm({
          message: `是否确认要提现全部金额？`,
          confirmButtonText: "确定",
          showCancelButton: true,
          cancelButtonText: "取消"
        })
          .then(() => {
            if (this.checkedAll) {
              this.formData;
            } else {
              this.checkedData.map(v => {
                if (v.checked === true && v.itemAttribute !== "4" && v.certiId)
                  this.policyNOList.push(v.certiId);
              });
            }
            this.formData = Object.assign({}, this.formData, {
              page: {
                page: 1,
                size: 10
              }
            });
            this.resultData = [...[]];
            this.billPackToFK_();
          })
          .catch(() => {});
      } else {
        Dialog.confirm({
          message: `是否确认提现${this.amountAll.toFixed(2)}元？`,
          confirmButtonText: "确定",
          showCancelButton: true,
          cancelButtonText: "取消"
        })
          .then(() => {
            if (this.checkedAll) {
              this.formData;
            } else {
              this.checkedData.map(v => {
                if (v.checked === true && v.itemAttribute !== "4" && v.certiId)
                  this.policyNOList.push(v.certiId);
              });
            }
            this.formData = Object.assign({}, this.formData, {
              page: {
                page: 1,
                size: 10
              }
            });
            this.resultData = [...[]];
            this.billPackToFK_();
          })
          .catch(() => {});
      }
    },
    feePay(val) {
      this.cashOutData = { ...val };
    },
    onLoad() {
      this.queryList().then(() => {
        this.QueryPlanFeeInquiry();
        this.queryConig();
      });
      this.formData.page.page += 1;
    },
    //代理人手续费提现接口
    async billPackToFK_() {
      const params = {
        checkAllFlag: this.checkedAll ? "1" : "0",
        policyNOList: this.policyNOList,
        userCode: JSON.parse(localStorage.getItem("userInfo")).userCode || "",
        agentCode: JSON.parse(localStorage.getItem("userInfo")).agentCode || "",
        handlerCode:
          JSON.parse(localStorage.getItem("userInfo")).handlerCode || ""
      };
      if (this.checkedAll) delete params["policyNOList"];
      await billPackToFK(params).then(res => {
        const vm = this;
        if (res.code === "1") {
          this.$dialog.alert({
            message: "提现成功",
            callback: () => {
              this.queryList().then(() => {
                vm.queryConig();
              });
            }
          });
        } else {
          this.$dialog.alert({
            message: res.msg,
            callback: () => {
              this.queryList().then(() => {
                vm.queryConig();
              });
            }
          });
        }
      });
    },
    //代理人手续费查询接口
    async QueryPlanFeeInquiry() {
      await planFeeInquiry({ queryType: "03" }).then(res => {});
    },
    //查询用户提现配置
    async queryConig() {
      await queryWithdrawalConfig(this.formData).then(res => {
        if (res.code === "1") {
          this.isAutomatic = res.data.queryConfigResponseVO.isAutomatic;
          this.isAutomatic === "1"
            ? this.resultData.forEach(v => {
                v.checked = true;
                v.disabled = true;
              })
            : this.resultData.forEach(v => {
                if (v.itemAttribute === "4") {
                  // v.checked = true;
                  v.disabled = true;
                }
                if (Number(v.planFee) <= 0) {
                  v.disabled = true;
                  v.checked = true;
                }
              });
        }
      });
    },
    //提现初始化
    async queryList() {
      await queryPolicyList(this.formData)
        .then(res => {
          if (res.code === "1") {
            this.sumPlanFee = res.data.enchashmentResponseVO.sumPlanFee;
            if (res.data.hasOwnProperty("list")) {
              res.data.list.forEach(v => {
                v.checked = false;
              });
              this.checkedAll = false;
              this.loading = false;
              this.resultData.push(...res.data.list);
            } else {
              //数据加载完毕
              this.finished = true;
            }
          }
        })
        .catch(err => {
          this.error = true;
        });
    }
  }
};
</script>

<style lang="less" scoped>
.costReflectDetails {
  &-head {
    padding: 0 16px;
    background: linear-gradient(
      115deg,
      rgba(30, 70, 246, 1) 0%,
      rgba(39, 154, 255, 1) 100%
    );
    .van-icon {
      vertical-align: middle;
    }
    height: 64px;
    line-height: 64px;
    text-align: center;
    span {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
    span:nth-child(1) {
      float: left;
    }
    span:nth-child(3) {
      float: right;
      font-size: 14px;
    }
  }
  .setAuto {
    padding: 10px 15px;
    display: flex;
    justify-content: space-between;
    font-size: 16px;
    margin-bottom: 10px;
  }
  &-formHeader {
    padding-top: 10px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(142, 142, 142, 1);
    padding-left: 15px;
    background: #fff;
    span:nth-child(2) {
      float: right;
      margin-right: 90px;
    }
  }
  &-content {
    padding: 14px 15px;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
    color: #666;
    background: #fff;
    align-items: center;
    /* 修改--添加 */
    height: 40px;
    span:nth-child(1) {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }
    span:nth-child(2) {
      font-size: 15px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
  }
  &-total {
    background: #fff;
    padding-left: 15px;
    display: -webkit-box;
    display: flex;
    display: -webkit-flex;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: center;
    font-size: 14px;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 54px;
    span {
      margin: 0 10px;
    }
    /deep/.van-button--normal {
      flex: 1;
      height: 54px;
      text-align: center;
    }
    p:nth-child(1) {
      margin-right: 50px;
      span {
        display: block;
        font-size: 11px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(153, 153, 153, 1);
      }
      span:nth-child(1) {
        font-size: 18px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: rgba(252, 130, 14, 1);
      }
    }
    /deep/.van-checkbox {
      height: 54px;
      background: rgba(238, 245, 255, 1);
      padding: 0 19px;
    }
  }
  /deep/.van-list {
    margin-bottom: 50px;
  }
}
</style>
