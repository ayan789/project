<template>
  <div class="insure-appoint-confirm">
    <nav-bar type="vehicle" title="约定内容" @clickBack="closeAgreement" />
    <div class="content">
      <div class="insure-appoint-list">
        <div class="father" v-if="insurancePlan.businessChecked == '1'">
          <div class="title">商业险特别约定条款</div>
          <div class="horTitles">
            <div class="horA">选择</div>
            <div class="horB">特约代码</div>
            <div class="horC">特约名称</div>
            <div class="horD">内容</div>
          </div>
          <div
            class="engages"
            v-for="(app, i) in bizEngages"
            :key="'business' + i"
          >
            <div class="appoint" @click.stop="select(app, 'business')">
              <van-checkbox v-model="app.selected" />
              <div class="appCode">{{ app.specialAgreementCode }}</div>
              <div class="appName">{{ app.specialAgreementName }}</div>
              <div class="detail" @click.stop="showDetail(app, i, 'business')">
                详情
              </div>
            </div>
          </div>
        </div>
        <div class="devideLine" />
        <div class="father" v-if="insurancePlan.trafficChecked == '1'">
          <div class="title">交强险特别约定条款</div>
          <div class="horTitles">
            <div class="horA">选择</div>
            <div class="horB">特约代码</div>
            <div class="horC">特约名称</div>
            <div class="horD">内容</div>
          </div>
          <div class="engages" v-for="(app, i) in engages" :key="'traffic' + i">
            <div class="appoint" @click.stop="select(app, 'traffic')">
              <van-checkbox v-model="app.selected" />
              <div class="appCode">{{ app.specialAgreementCode }}</div>
              <div class="appName">{{ app.specialAgreementName }}</div>
              <div class="detail" @click.stop="showDetail(app, i, 'traffic')">
                详情
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="bottomBtn">
        <p @click="sureAction">确定</p>
      </div>
    </div>
    <!-- 特别约定 -->
    <van-popup
      v-model="popupShow"
      position="bottom"
      :style="{ height: '100%', background: '#f0f2f5', borderRadius: '0' }"
    >
      <div class="super" :style="{ background: 'white' }">
        <nav-bar
          type="vehicle"
          title="特别约定"
          @clickBack="popupShow = false"
        />
        <div class="father">
          <textarea
            maxlength="300"
            cols="30"
            rows="10"
            style="font-size: 14px;"
            placeholder="请输入特别约定内容"
            v-model="currentDesc"
            v-on:input="inputText"
            :readonly="!modifyFlag"
          ></textarea>
          <h3 class="hintNum">{{ inputNumber }}/300</h3>
        </div>
        <div class="bottom">
          <div id="clear" v-show="modifyFlag" @click="clearAll">清空</div>
          <div id="sure" @click="submitText">确定</div>
        </div>
      </div>
    </van-popup>
    <!-- 增值服务特别约定 -->
    <van-popup
      v-model="addAgreementShow"
      position="bottom"
      :style="{ height: '100%', background: 'white', borderRadius: '0' }"
    >
      <insureAppoint
        :engage="currentItem"
        @closeInsureAppoint="closeSpecialAgreement"
      />
    </van-popup>
  </div>
</template>

<script>
import insureAppoint from "./insureAppoint";
import { getEngages } from "@src/http/module/vehicle";
import { Dialog, Toast } from "vant";
import { mapState } from "vuex";
export default {
  name: "insureAppointConfirm",
  components: {
    insureAppoint
  },
  methods: {
    // 弹出编辑框
    showDetail(item, index, type) {
      this.currentItem = item;
      // 增值服务特别约定
      if (item.specialAgreementCode == "A00193") {
        this.currentType = type;
        this.addAgreementShow = true;
      }
      // 非增值特约
      else if (item.specialAgreementCode == "A00176") {
        this.popupShow = true;
        this.modifyFlag = item.modifyFlag == "1" ? true : false;
        this.currentDesc = item.specialContent || "";
        this.inputNumber = this.currentDesc.length;
      } else {
        this.popupShow = true;
        this.modifyFlag = item.modifyFlag == "1" ? true : false;
        this.currentDesc = item.specialContent || "";
        this.inputNumber = this.currentDesc.length;
      }
    },
    //编辑框确定点击事件
    submitText() {
      this.currentItem.specialContent = this.currentDesc;
      this.popupShow = false;
      // 如果清空编辑框，则取消勾选
      if (!this.currentDesc) {
        this.currentItem.selected = false;
      } else {
        this.currentItem.selected = true;
      }
    },
    // 清空编辑框
    clearAll() {
      this.currentItem.specialContent = this.currentDesc = "";
      this.inputNumber = 0;
    },
    // 特约弹框确定
    sureAction() {
      let bizNoSelected = this.bizEngages.every(item => {
        return !item.selected;
      });
      let tsNoSelected = this.engages.every(item => {
        return !item.selected;
      });
      if (bizNoSelected && tsNoSelected) {
        // Toast("请选择特约");
        // return;
      }
      this.$emit("closeAgreement", {
        bizEngages: this.bizEngages.filter(item => item.selected),
        engages: this.engages.filter(item => item.selected)
      });
    },
    closeAgreement() {
      this.initList();
      this.$emit("closeAgreement");
    },
    // 编辑框输入事件
    inputText() {
      this.inputNumber = this.currentDesc.length;
    },
    // 特约选中事件
    select(agreement, type) {
      // 勾选时要判断特约内容是否为空，为空则不能选中
      // 增值服务特别约定
      if (agreement.specialAgreementCode == "A00193") {
        this.currentItem = agreement;
        this.currentType = type;
        this.addAgreementShow = true;
      } else if (
        !agreement.specialContent &&
        agreement.specialAgreementCode == "A00176"
      ) {
        Dialog.alert({ title: "提示信息", message: "特约内容不能为空" }).then(
          () => {
            this.currentItem = agreement;
            // 非增值特约
            this.popupShow = true;
            this.modifyFlag = agreement.modifyFlag == "1" ? true : false;
            this.currentDesc = agreement.specialContent || "";
            this.inputNumber = this.currentDesc.length;
          }
        );
        // this.addAgreementShow = true;
        agreement.selected = false;
      } else {
        agreement.selected = !agreement.selected;
      }
      this.$forceUpdate();
    },
    closeSpecialAgreement(engages) {
      this.currentItem = Object.assign(this.currentItem, engages);
      // if (this.currentType == "traffic") {
      // } else if (this.currentType == "business") {
      // }
      this.addAgreementShow = false;
    },
    async init() {
      try {
        let res = await getEngages({ orderId: this.orderId });
        if (res.code == "1" && res.data) {
          this.initData = res.data;
          this.initList();
        } else {
          Toast(res.msg);
        }
      } catch (e) {
        //console.log(e);
      }
    },
    initList() {
      const {
        engages: selectedEngages,
        bizEngages: selectedBizEngages
      } = this.engageList;
      this.bizEngages = this.initData.bizEngages.map(item => ({
        ...item,
        selected: !!selectedBizEngages.find(
          selectedItem =>
            selectedItem.specialAgreementCode == item.specialAgreementCode
        )
      }));
      this.engages = this.initData.engages.map(item => ({
        ...item,
        selected: !!selectedEngages.find(
          selectedItem =>
            selectedItem.specialAgreementCode == item.specialAgreementCode
        )
      }));
    }
  },
  data() {
    return {
      /*.....非增值特约弹框信息 START......*/
      inputNumber: 0, // 输入内容计数
      popupShow: false,
      currentDesc: "", // 输入内容
      modifyFlag: false, // 特约是否可修改：false（不能），true（能）
      /*.....END......*/
      /*.....增值特约弹框信息 START......*/
      addAgreementShow: false,
      /*.....END......*/
      currentItem: {}, // 当前选中特约
      currentType: "",
      selectStyle: {
        background: "#3b8afe",
        border: "1px solid #3B8AFE"
      },
      unSelectStyle: {
        background: "white",
        border: "1px solid rgba(200,201,204,1)"
      },
      bizEngages: [],
      engages: [],
      initData: {}
    };
  },
  watch: {
    orderId(...args) {
      if (args.length > 1) {
        if (args[0] != args[1]) {
          console.warn("");
          this.init();
        }
      }
    },
    engageList: async function(engageList) {
      try {
        let res = {
          code: "1",
          data: { bizEngages: this.bizEngages, engages: this.engages }
        };
        if (
          (Array.isArray(this.bizEngages) && this.bizEngages.length) ||
          (Array.isArray(this.engages) && this.engages.length)
        ) {
        } else {
          res = await getEngages({ orderId: this.orderId });
        }
        if (res.code == "1" && res.data) {
          const {
            engages: selectedEngages,
            bizEngages: selectedBizEngages
          } = engageList;
          this.bizEngages = res.data.bizEngages.map(item => ({
            ...item,
            selected: !!selectedBizEngages.find(
              selectedItem =>
                selectedItem.specialAgreementCode == item.specialAgreementCode
            )
          }));
          this.engages = res.data.engages.map(item => ({
            ...item,
            selected: !!selectedEngages.find(
              selectedItem =>
                selectedItem.specialAgreementCode == item.specialAgreementCode
            )
          }));
        } else {
          Toast(res.msg);
        }
      } catch (e) {
        //console.log(e);
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      engageList: ({ engageList }) => engageList,
      insurancePlan: ({ insurancePlan }) => insurancePlan
    })
  },
  mounted() {
    this.init();
  }
};
</script>
<style scoped>
* {
  text-align: left;
  text-overflow: ellipsis;
  white-space: pre-wrap;
}
.insure-appoint-confirm {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.fullSpace {
  width: 100vw;
  height: 15px;
  border-radius: 15px 15px 0px 0px;
  background-color: white;
  top: 65px;
  z-index: 1000;
  position: fixed;
}
.content {
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  background: white;
}
.insure-appoint-list {
}
.devideLine {
  width: 100vw;
  height: 15px;
  background: rgba(247, 247, 247, 1);
  margin-top: 18px;
}
.title {
  height: 48px;
  font-size: 14px;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  line-height: 48px;
  /* margin: 0px 15px; */
  text-align: left;
}
.horTitles {
  /* margin: 0 15px; */
  height: 30px;
  background-color: #f6f7f8;
}
.horA,
.horB,
.horC,
.horD {
  float: left;
  text-align: center;
  line-height: 30px;
  height: 30px;
  font-size: 12px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
}
.horA {
  width: 60px;
}
.horB {
  width: 70px;
}
.horD {
  width: 60px;
}
.horC {
  width: calc(100% - 190px);
}
.appoint {
  /* margin: 0 15px; */
  height: 48px;
  background: white;
  border-bottom: 0.5px solid #e5e6e8;
}
.appCode {
  width: 70px;
}
.appName {
  width: calc(100% - 192px);
}

.van-checkbox,
.appCode,
.appName,
.detail {
  float: left;
  height: 48px;
  line-height: 22px;
  text-align: center;
  font-size: 13px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  display: flex;
  justify-content: center;
  align-items: center;
}
.appCode {
  margin-left: 18px;
}
.detail {
  width: 60px;
  color: rgba(59, 138, 254, 1);
}
.appName {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  /* 最大显示两行 */
  -webkit-line-clamp: 2;
  overflow: hidden;
}
.van-checkbox {
  margin-left: 20px;
}
.bottom {
  width: 100%;
  background: #f0f2f5;
  padding: 30px 0;
  display: flex;
  flex: 1;
  align-items: center;
  justify-content: space-around;
  flex-wrap: wrap;
}
.bottom > h3 {
  height: 46px;
  background: rgba(59, 138, 254, 1);
  border-radius: 23px;
  margin-top: 5px;
  width: calc(100% - 30px);
  text-align: center;
  line-height: 46px;
  font-size: 16px;
  font-weight: 400;
  color: white;
}
.father {
  /* background: white; */
  margin: 0 15px;
  /* calc() = calc(四则运算)
需要注意的是，运算符前后都需要保留一个空格，例如：height: calc(100vh - 10px)；
如果写成 height：calc(100vh-10px); 是不会生效的奥 */
  width: calc(100% - 30px);
  /*height: 225px;*/
  /* border: 1px solid rgba(229, 229, 229, 1); */
  /* border-radius: 5px; */
}
textarea {
  margin: 15px;
  resize: none;
  margin-top: 5px;
  /* calc() = calc(四则运算)
需要注意的是，运算符前后都需要保留一个空格，例如：height: calc(100vh - 10px)；
如果写成 height：calc(100vh-10px); 是不会生效的奥 */
  width: calc(100% - 30px);
  border: none;
  outline: 0;
  display: block;
  height: 169px;
  overflow: auto;
  display: block;
  line-height: 18px;
}

#clear,
#sure {
  width: calc(50% - 30px);
  border-radius: 46px;
  float: left;
  height: 46px;
  line-height: 46px;
  font-size: 16px;
  text-align: center;
}
#sure {
  background: rgba(59, 138, 254, 1);
  color: white;
  float: right;
}
#clear {
  background: white;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  border: 1px solid rgba(200, 201, 204, 1);
}

.hintNum {
  text-align: right;
  margin-right: 10px;
  padding-bottom: 10px;
}
</style>
<style lang="less" scoped>
.bottomBtn {
  background: #f7f7f7;
  padding: 29px 15px 15px;
  margin-top: 18px;
  p {
    width: 100%;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
    color: white;
    height: 46px;
    line-height: 46px;
    font-size: 16px;
    text-align: center;
    border-radius: 23px;
  }
}
</style>
