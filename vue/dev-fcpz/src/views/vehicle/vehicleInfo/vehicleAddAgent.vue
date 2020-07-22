<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-06 11:00:30
 * @Last Modified by: King
 * @Last Modified time: 2020-06-03 19:59:01
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleAddAgent.vue
-->
<template>
  <div class="vehicle-add-agency">
    <van-sticky>
      <nav-bar type="vehicle" title="添加代理人" @clickBack="$router.back()" />
    </van-sticky>
    <div class="content">
      <van-cell class="item" @click="dataPickerShow = true">
        <span slot="title" class="item-title">业务来源</span>
        <div class="item-right">
          <span
            :class="{
              'item-placeholder': !businessNatureCode
            }"
          >
            {{ businessNatureName }}
          </span>
          <van-icon class="item-right-arrow" name="arrow" />
        </div>
      </van-cell>
      <van-cell class="item">
        <span slot="title" class="item-title">代理人代码</span>
        <div class="item-right">
          <input
            class="item-right-input"
            v-model="agentCode"
            placeholder="请输入代理人代码"
            maxlength="20"
          />
          <img
            class="search-image"
            src="../../../assets/vehicle/vehicleInfo/search.png"
            @click="agentQuery"
          />
        </div>
      </van-cell>
      <van-cell class="item">
        <span slot="title" class="item-title">代理人</span>
        <div class="item-right">
          <span>{{ agentName }}</span>
        </div>
      </van-cell>
    </div>
    <confirmBottom @click="addAgent"></confirmBottom>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="businessNatures"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
  </div>
</template>
<script>
import confirmBottom from "@/components/confirmBottom.vue";
import Config from "@src/common/json/newCoreDataDictionary";
import {
  agentQuery,
  searchAgentInfo,
  addAgent
} from "@src/http/module/vehicle";
export default {
  components: {
    confirmBottom
  },
  data() {
    let { params = {}, query = {} } = this.$route;
    let { riskCode: riskCode1 } = query;
    let { riskCode: riskCode2 } = params;
    let riskCode = riskCode1 || riskCode2 || "";
    let businessNatures = Object.keys(Config.businessNature).map(key => ({
      text: Config.businessNature[key],
      value: key
    }));
    businessNatures.unshift({ text: "请选择", value: "" });
    return {
      riskCode,
      businessNatureCode: "",
      businessNatureName: "",
      agentCodev: "",
      agentName: "",
      businessNatures,
      dataPickerShow: false
    };
  },
  computed: {
    agentCode: {
      get: function() {
        return this.agentCodev;
      },
      set: function(v) {
        this.agentCodev = v.toUpperCase();
      }
    }
  },
  methods: {
    dataPickerConfirm(data) {
      this.businessNatureCode = data.value;
      this.businessNatureName = this.$utils.findText(
        this.businessNatures,
        data.value
      );
      this.dataPickerShow = false;
    },
    dataPickerCancel() {
      this.dataPickerShow = false;
    },
    addAgent() {
      //TODO: 校验参数
      let params = {
        agentCode: this.agentCode,
        agentName: this.agentName,
        riskCode: this.riskCode,
        businessSource: this.businessNatureCode,
        businessNatureCode: this.businessNatureCode,
        businessNatureName: this.businessNatureName
      };
      addAgent(params).then(
        res => {
          if (res.code === "1") {
            this.$router.back();
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    agentQuery() {
      let params = {
        agentCode: this.agentCode
      };
      searchAgentInfo(params).then(
        res => {
          if (res.code === "1") {
            let {
              businessSource, //业务来源
              agentCode, //代理人代码
              agentName, //代理人姓名
              permitNo, //许可证号
              exhibitionNo, //执业证书编号
              agencyOrgCode //中介组织机构代码
            } = res.data;
            // this.agentCode = agentCode;
            this.agentName = agentName;
            // this.businessNatureCode = businessSource;
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    }
  }
};
</script>
<style lang="less" scoped>
.content {
  display: flex;
  flex: 1;
  flex-direction: column;
  // height: calc(100% - 77px);
  div {
    display: flex;
    flex-direction: row;
  }
  .van-cell__value {
    overflow: visible;
  }
  .group {
    flex-direction: column;
    background: #f7f7f7;
    padding-bottom: 10px;
  }
  .item {
    height: 48px;
    align-items: center;
    .van-cell__value {
      flex: 2;
    }
    .item-placeholder {
      color: #ababab;
    }
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-right {
      flex: 1;
      word-break: keep-all;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: #333333;
      .item-right-input {
        width: 100%;
        text-align: right;
        border: 0;
        background: transparent;
        opacity: 1;
        font-size: 14px;
        color: #333333;
      }
      .search-image {
        width: 14px;
        height: 14px;
        margin-left: 10px;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
    }
  }
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
