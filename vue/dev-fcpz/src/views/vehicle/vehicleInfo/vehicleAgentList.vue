<!--
 * @Author: heweiwei
 * @Page: 车辆信息-代理人列表
 * @since: 2020-02-20 11:05:03
 * @Last Modified by: King
 * @Last Modified time: 2020-06-17 14:54:46
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleAgentList.vue
-->
<template>
  <div class="vehicle-agency">
    <van-sticky>
      <nav-bar
        title="常用代理人"
        type="vehicle"
        @clickBack="back()"
        right-icon="add"
        right-text="添加代理人"
        @clickRight="toAddAgent"
      />
    </van-sticky>
    <div :class="['content', disabled ? 'disabled' : '']">
      <van-swipe-cell
        class="item-swipe"
        v-for="(item, index) in list"
        :key="index"
        :disabled="item.agentCode.indexOf('P') !=-1 ?true:false"
      >
        <div
          :class="{
            'item-content': true,
            'item-content-selected': checked[index]
          }"
          @click.stop="onSelectItem(item, index)"
        >
          <van-checkbox
            v-show="!disabled"
            v-model="checked[index]"
            class="check-box"
          />
          <div
            :class="{
              'agency-item': true,
              'agency-default': item.isDefault === '1',
              'agency-selected': checked[index]
            }">
            <div
              class="item"
              @click.stop="setDefaultAgent(item, index)"
          >
              {{ item.isDefault === "1" ? "默认" : "设为" }}
            </div>
            <div>
              {{ item.isDefault === "1" ? "代理人" : "默认" }}
            </div>
          </div>
          <div class="agency">
            <div class="agency-name">{{ item.agentName }}</div>
            <span>{{ item.businessSource | businessNatureName }}</span>
            <div class="agency-code">{{ item.agentCode }}</div>
          </div>
          <!-- <div class="agency-background"></div> -->
        </div>
        <div
          slot="right"
          class="swipe-delete-button"
          @click.stop="deleteAgent(item, index)"
        >
          删除
        </div>
      </van-swipe-cell>
    </div>
    <div v-show="!disabled" class="agency-bottom">
      <div
        class="agency-confirm"
        @click="confirm"
      >确定</div>
    </div>
  </div>
</template>
<script>
import {
  setDefaultAgent,
  agentQueryList,
  deleteAgent
} from "@src/http/module/vehicle";
import { SwipeCell } from "vant";
import { mapState, mapMutations } from "vuex";
import Config from "@src/common/json/newCoreDataDictionary";

export default {
  components: {
    [SwipeCell.name]: SwipeCell
  },
  data() {
    return {
      riskCode: "",
      list: [],
      checked: []
    };
  },
  created() {
    this.agentQueryList();
  },
  activated() {
    let { params = {}, query = {} } = this.$route;
    let { riskCode: riskCode1 } = query;
    let { riskCode: riskCode2 } = params;
    let riskCode = riskCode1 || riskCode2 || "DEA";
    this.riskCode = riskCode;
    this.agentQueryList();
  },
  computed: {
    ...mapState("channel", {
      channel: ({ channel }) => channel
    }),
    disabled() {
      const { query = {} } = this.$route;
      const { source } = query;
      return source == "shell";
    }
  },
  filters: {
    businessNatureName(businessNatureCode) {
      return Config.businessNature[businessNatureCode];
    }
  },
  methods: {
    ...mapMutations("channel", ["setChannel"]),
    back() {
      const { query = {} } = this.$route;
      const { source } = query;
      if (source == "shell") {
        // this.$router.push({ path: "/" });
        this.$native.closeWeb();
      } else {
        this.$router.back();
      }
    },
    toAddAgent() {
      let query = { riskCode: this.riskCode };
      this.$router.push({ name: "vehicleAddAgent", query });
    },
    onSelectItem(item, index) {
      if (this.disabled) return;
      this.checked = this.checked.map((v, i) => i === index);
    },
    agentQueryList() {
      agentQueryList({}).then(
        res => {
          if (res.code === "1") {
            this.list = res.data || [];
            this.checked = this.list.map(() => false);
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    setDefaultAgent(item, index) {
      this.onSelectItem(item, index);
      if (item.isDefault !== "1") {
        item.isDefault = "1";
        setDefaultAgent(item).then(
          res => {
            if (res.code === "1") {
              //NOTE: 刷新数据
              this.agentQueryList();
            } else {
              this.$dialog.alert({ message: res.msg });
            }
          },
          error => {}
        );
      } else {
        this.$toast.fail("已经是默认代理人");
      }
    },
    deleteAgent(item, index) {
      this.$dialog
        .confirm({ message: "确认删除" })
        .then(() => {
          // on confirm
          deleteAgent(item).then(
            res => {
              if (res.code === "1") {
                this.agentQueryList();
              } else {
                this.$dialog.alert({ title: "删除失败", message: res.msg });
              }
            },
            error => {}
          );
        })
        .catch(() => {
          // on cancel
        });
    },
    confirm() {
      if (this.disabled) return;
      let agent = this.list[this.checked.findIndex(checked => checked)];
      if (agent) {
        this.channel.agentCode = agent.agentCode;
        this.channel.agentName = agent.agentName;
        this.channel.agentAgreementCode = agent.agentAgreement || "";
        this.channel.agentAgreementName = agent.agentAgreementName || "";
        this.channel.businessNatureCode = agent.businessSource || "";
        this.channel.businessSource = agent.businessSource || "";
        this.channel.businessSourceCode = agent.businessSource || "";
        this.channel.businessSourceName =
          Config.businessNature[agent.businessSource] || "";
        // this.setChannel(this.channel);
        this.back();
      } else {
        this.$toast.fail("请选中一个代理人");
      }
    }
  }
};
</script>
<style lang="less" scoped>
.vehicle-agency {
  height: 100%;
  .content {
    background: white;
    height: calc(100% - 77px - 60px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    div {
      display: flex;
      align-items: center;
    }
    .item-swipe {
      pointer-events: none;
      .item-content {
        pointer-events: auto;
        width: 375px;
        min-height: 90px;
        flex-direction: row;
        // align-items: center;
        background: #fff;
        color: black;
        .check-box {
          margin-left: 12px;
        }
        .agency-item {
          width: 50px;
          min-height: 50px;
          border-radius: 50%;
          text-align: center;
          margin-left: 15px;
          color: #005bac;
          background: white;
          border: 1px solid #bed8ff;
          flex-direction: column;
          justify-content: center;
        }
        .agency-default {
          color: #024078;
          background: #cee2ff;
          border: 1px solid #a1beff;
        }
        .agency-selected {
          background: #279aff88;
        }
        .agency {
          margin: 0 10px;
          flex-direction: column;
          align-items: flex-start;
          flex: 1;
          .agency-name {
            font-size: 14px;
            font-weight: 600;
            color: #333333;
            line-height: 20px;
          }
          span {
            font-size: 12px;
            color: #666666;
            line-height: 17px;
          }
          .agency-code {
            font-size: 12px;
            color: #666666;
            line-height: 20px;
          }
        }
        .agency-background {
          top: 80px;
          left: 0;
          height: 10px;
          width: 100%;
          background: #f7f7f7;
          z-index: 99;
        }
      }
      .item-content-selected {
        background: #279aff88;
      }
      .swipe-delete-button {
        pointer-events: auto;
        margin-left: 4px;
        width: 60px;
        height: 100%;
        background: #fb3232;
        justify-content: center;
        align-items: center;
        color: white;
        font-size: 14px;
      }
    }
  }
  .disabled {
    height: calc(100% - 77px);
  }
  .agency-bottom {
    background: white;
    padding: 10px;
    .agency-confirm {
      height: 40px;
      margin: 0px 20px;
      width: calc(100% - 40px);
      background: rgba(59, 138, 254, 1);
      border-radius: 23px;
      font-size: 16px;
      line-height: 40px;
      text-align: center;
      color: white;
    }
  }
}
</style>
