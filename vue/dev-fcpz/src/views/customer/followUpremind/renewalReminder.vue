<template>
  <div class="discount">
    <ul v-if="remindList.length > 0">
      <li v-for="(item, index) in remindList" :key="index">
        <div class="reduced">
          <div @click="toCustomerInfo(item)" class="give">
            {{ item.custName }}
          </div>
          <div class="pastdue">{{ item.remindTitle }}</div>
        </div>
        <div @click="equities(item)" class="warn" v-if="item.tip">
          {{ item.tip }}
        </div>
      </li>
    </ul>

    <div v-else class="discount_noDataView">
      <img :src="this.$imageUrl.noData" alt="" class="discount_img" />
      <div class="discount_title">暂无数据</div>
    </div>

    <van-dialog
      v-model="show"
      title="脱保原因"
      show-cancel-button
      confirm-button-text="保存"
      :closeOnClickOverlay="true"
      :closeOnPopstate="true"
      :beforeClose="confirmClick"
      get-container="#substance"
    >
      <div class="discount_dialog">
        <div class="discount_dialog1">
          脱保原因
        </div>
        <van-field
          readonly
          clickable
          name="picker"
          :value="value"
          label="请选择"
          :right-icon="listShow ? 'arrow-up' : 'arrow-down'"
          input-align="right"
          required
          @click-right-icon="clickright"
        />
        <div v-if="listShow" class="discount_list">
          <van-list>
            <div
              v-for="(item, index) in list"
              :key="index"
              class="discount_listItem"
              @click="clickItem(item)"
            >
              {{ item.label }}
            </div>
          </van-list>
        </div>
        <div class="discount_dialog1">
          详细内容
        </div>
        <van-field
          v-model="reasonDetail"
          rows="3"
          autosize
          type="textarea"
          maxlength="50"
          show-word-limit
          placeholder="请输入脱保原因"
        />
      </div>
    </van-dialog>
  </div>
</template>
<script>
import mapper from "../../../utils/mapper";
import { updateUnRenewReason } from "../../../http/module/renewal";
import { Toast } from "vant";
import { doneTaskClick } from "../../../http/module/customerApi";
import { mapState } from "vuex";
import { trackMap } from "@src/utils/index.js";
export default {
  data() {
    return {
      show: false,
      value: "",
      list: mapper.cancelRenewal,
      listShow: false,
      reasonType: "",
      policyNo: "",
      reasonDetail: "",
      riskCode: "",
      id: "",
      custNo: ""
    };
  },
  props: {
    remindList: Array
  },

  computed: {
    ...mapState(["customerData"])
  },

  methods: {
    async confirmClick(action, done) {
      console.log(action, "===");
      if (action === "confirm") {
        if (this.value) {
          if (
            (this.reasonType === "RSK" && !this.reasonDetail) ||
            (this.reasonType === "RSI" && !this.reasonDetail)
          ) {
            Toast("请填写具体的脱保原因");
            return done(false);
          } else {
            let params = {
              policyNo: this.policyNo,
              reasonType: this.reasonType,
              riskCode: "",
              reasonDetail: this.reasonDetail,
              userCode: localStorage.getItem("userCode")
            };
            let res = await updateUnRenewReason(params);
            if (res.code == "1") {
              let params = {
                remindId: this.id
              };
              doneTaskClick(params);
              //刷新跟进提醒页面
              let params_ = {
                userCode: localStorage.getItem("userCode"), //业务员号
                custNo: "", //客户号
                remindType: this.customerData.active //提醒类型
              };
              this.$store.dispatch(
                "customerData/getfollowRemindDatat",
                params_
              );
              this.show = false;
              done();
            } else {
              Toast(res.msg);
              done(false);
            }
          }
        } else {
          Toast("请选择脱保类型!");
          return done(false);
        }
      } else {
        return done();
      }
    },
    clickItem(item) {
      this.value = item.label;
      this.reasonType = item.value;
      this.listShow = false;
      console.log(item, "===item");
    },
    //显示下拉菜单
    clickright() {
      this.listShow = !this.listShow;
    },
    toCustomerInfo(item) {
      console.log(item, "=====");
      // 跳转客户信息的详情
      this.$router.push({
        name: "customerEquity",
        query: {
          viewType: "1",
          custNo: item.custNo,
          custSourceType: "2",
          custType: "1"
        }
      });
    },
    equities(item) {
      console.log(item, "ooo===");
      // 提醒类型
      switch (item.remindType) {
        case "4":
          // 提醒
          {
            let params_6 = {
              custNo: item.custNo,
              createUser: localStorage.getItem("userCode"),
              type: "6",
              title: "权益提醒",
              userCode: localStorage.getItem("userCode")
            };
            this.$store.dispatch(
              "customerData/addOrUpdateInteractions",
              params_6
            );
            this.$router.push({
              name: "registeredView",
              query: { item: item }
            });
          }
          break;
        case "5":
          // 转发推荐码
          // this.flag = true;
          {
            let params_8 = {
              custNo: item.custNo,
              createUser: localStorage.getItem("userCode"),
              type: "8",
              title: "未注册提醒",
              userCode: localStorage.getItem("userCode")
            };
            this.$store.dispatch(
              "customerData/addOrUpdateInteractions",
              params_8
            );
            this.$router.push({
              name: "RegisteredManView",
              query: { item: item }
            });
          }
          break;
        case "3":
          // 送上祝福
          {
            let params_5 = {
              custNo: item.custNo,
              createUser: localStorage.getItem("userCode"),
              type: "5",
              title: "生日提醒",
              userCode: localStorage.getItem("userCode")
            };
            this.$store.dispatch(
              "customerData/addOrUpdateInteractions",
              params_5
            );
            this.$router.push({
              name: "birthdayCelebrate",
              query: { item: item }
            });
          }
          break;
        case "2":
          this.policyNo = item.policyNo;
          this.custNo = item.custNo;
          this.id = item.id;
          if (item.outOfInsuranceStatus == "1") {
            this.show = true;
          } else {
            let params_7 = {
              custNo: item.custNo,
              createUser: localStorage.getItem("userCode"),
              type: "7",
              title: "出险提醒",
              userCode: localStorage.getItem("userCode")
            };
            this.$store.dispatch(
              "customerData/addOrUpdateInteractions",
              params_7
            );
            // 跟进 ==> 跳转续保详情 renewalContactDetails
            this.$router.push({
              name: "renewalContactDetails",
              query: { item: item }
            });
          }
          break;
        case "1":
          // 查看 ==> 理赔详情
          this.$router.push({
            name: "customerClaimProgress",
            query: { item: item }
          });
          break;
      }
    }
  }
};
</script>
<style lang="less" spoced>
.discount {
  background-color: #ffffff;
  padding: 0 15px;

  /deep/ .van-cell {
    background-color: #f5f5f5;
    border-radius: 7px;
  }

  /deep/ .van-overlay {
    z-index: 20000 !important;
  }

  /deep/ .van-dialog {
    z-index: 20005 !important;
  }

  ul {
    li {
      height: 72px;
      border-bottom: 1px solid #e5e6e8;
      padding-top: 12px;
      overflow: hidden;
      position: relative;

      .img {
        width: 44px;
        height: 44px;
        margin-top: 3px;
        background: PINK;
        float: left;
      }

      .reduced {
        float: left;
        padding-left: 11px;

        .give {
          height: 26px;
          font-size: 14px;
          font-weight: 500;
          color: rgba(51, 51, 51, 1);
          line-height: 26px;
        }

        .pastdue {
          height: 26px;
          font-size: 13px;
          font-weight: 500;
          color: rgba(153, 153, 153, 1);
          line-height: 26px;
        }
      }

      .warn {
        padding: 4px 13px;
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        border-radius: 12px;
        border: 1px solid #1e46f6;
        color: #279aff;
        width: 60px;
        text-align: center;
      }
    }

    li:nth-last-child(1) {
      border-bottom: transparent;
    }
  }

  .discount_dialog {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 10px;

    .discount_list {
      position: absolute;
      z-index: 19;
      top: 140px;
      width: 100%;
      height: 120px;
      -webkit-overflow-scrolling: touch;
      overflow-scrolling: touch;
      overflow-y: scroll;
    }

    .discount_listItem {
      background-color: white;
      padding: 0px 10px;
      font-size: 14px;
      color: #333333;
    }

    .discount_dialog1 {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      font-size: 14px;
      color: #333333;
      width: 100%;
      margin: 8px 0px;
    }
  }
}

.discount_noDataView {
  display: flex;
  flex-direction: column;
  align-items: center;

  .discount_img {
    width: 50%;
    height: 50%;
    background-size: contain;
  }

  .discount_title {
    font-size: 14px;
    color: #e5e5e5;
  }
}
</style>
