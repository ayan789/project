<template>
  <div>
    <popNav popTitle="联合销售" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">意外险</div>
      </div>
      <div class="rowQu">
        <div 
          v-if="
            detailed.wealchAccApplicationVO &&
              detailed.wealchAccApplicationVO.applicationNo
          "
          class="InsuranceNum copyColor"
          @click="InsurancePolicy"
          data-clipboard-action="copy"
          :data-clipboard-text="
            detailed.wealchAccApplicationVO == null
              ? ''
              : detailed.wealchAccApplicationVO.applicationNo
          "
        >
          复制
        </div>
        <div class="left_40">投保单号</div>
        <div class="InsuranceNum1">
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.applicationNo
          }}
        </div>
      </div>
      <div class="rowQu">
        <div
          class="applicationFormNum copyColor"
          @click="applicationForm"
          data-clipboard-action="copy"
          v-if="
            detailed.wealchAccApplicationVO &&
              detailed.wealchAccApplicationVO.policyNo
          "
          :data-clipboard-text="
            detailed.wealchAccApplicationVO == null
              ? ''
              : detailed.wealchAccApplicationVO.policyNo
          "
        >
          复制
        </div>
        <div class="left_40">保单号</div>
        <div class="InsuranceNum1">
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.policyNo
          }}
        </div>
      </div>
      <div class="background"></div>

      <div class="rowQu">
        <div>方案名称</div>
        <div>
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.formulaName
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>购买份数</div>
        <div>
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.buyNum
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>总保费(元)</div>
        <div>
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.premium
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>总保额(元)</div>
        <div>
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.amount
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>是否代理</div>
        <div v-if="detailed.wealchAccApplicationVO">
          {{ isProxyFlag == "1" ? "是" : "否" }}
        </div>
      </div>
      <div class="rowQu">
        <div>业务来源</div>
        <div>
          {{
            detailed.nonCarChannelVo == null
              ? ""
              : detailed.nonCarChannelVo.businessNatureName
          }}
        </div>
      </div>
      <template>
        <div
          v-if="isProxyVal == '0'"
          class="rowQu"
        >
          <div>直销/直团渠道</div>
          <div>{{ detailed.wealchAccApplicationVO.agentAgreementName }}</div>
        </div>
        <div v-else>
          <div class="rowQu">
            <div>代理人代码</div>
            <div>
              {{
                detailed.wealchAccApplicationVO == null
                  ? ""
                  : detailed.wealchAccApplicationVO.agentCode
              }}
            </div>
          </div>
          <div class="rowQu">
            <div>代理人姓名</div>
            <div>
              {{
                detailed.wealchAccApplicationVO == null
                  ? ""
                  : detailed.wealchAccApplicationVO.agentName
              }}
            </div>
          </div>
        </div>
      </template>
      <div class="rowQu">
        <div>业务风险分类</div>
        <div>
          {{
            detailed.nonCarChannelVo == null
              ? ""
              : detailed.nonCarChannelVo.handler2Name
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>代理协议</div>
        <div>
          {{
            detailed.wealchAccApplicationVO == null
              ? ""
              : detailed.wealchAccApplicationVO.agentAgreementName
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>姓名</div>
        <div>
          {{
            detailed.wealthAccInsurant == null
              ? ""
              : detailed.wealthAccInsurant.insurantName
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>证件类型</div>
        <div v-if="detailed.wealthAccInsurant">
          {{
            $utils.findText(
              identifyTypes,
              detailed.wealthAccInsurant.identityType
            )
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>证件号码</div>
        <div>
          {{
            detailed.wealthAccInsurant == null
              ? ""
              : detailed.wealthAccInsurant.identityNo
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>手机号码</div>
        <div>
          {{
            detailed.wealthAccInsurant == null
              ? ""
              : detailed.wealthAccInsurant.phone
          }}
        </div>
      </div>

      <div class="rowQu">
        <div>合并打印</div>
        <div v-if="detailed.wealchAccApplicationVO">
          {{ detailed.wealchAccApplicationVO.mergePrint == "1" ? "是" : "否" }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";
import Config from "@src/common/json/newCoreDataDictionary";

export default {
  components: {
    popNav
  },
  data () {
    return {
      isProxyFlag: "",
      // 证件类型枚举
      identifyTypes: "",
      isProxyVal: "",

      detailed: {
        vehicleTaxVo: {},
        vehicleChannelVO: {},
        wealchAccApplicationVO: {}
      }
    };
  },
  mounted () {
    this.$nextTick(() => {
      this.detailed = this.$route.params;
      if (this.detailed.vehicleChannelVO) {
        this.isProxyFlag = this.detailed.vehicleChannelVO.isProxy;
      }
      this.isProxyVal = this.detailed.wealchAccApplicationVO.isProxy;
      this.identifyTypes = Object.keys(Config.identifyType).map(key => ({
        text: Config.identifyType[key],
        value: key
      }));
    });
  },
  methods: {
    //返回上一页
    goBack () {
      this.$globalBack();
    },
    // 投保单号
    InsurancePolicy () {
      let _this = this;
      let clipboard = new this.clipboard(".InsuranceNum");
      clipboard.on("success", function (e) {
        _this.$toast("复制成功");
      });
      clipboard.on("error", function (e) {
        _this.$toast("复制失败");
      });
    },
    //  保单号
    applicationForm () {
      let _this = this;
      let clipboard = new this.clipboard(".applicationFormNum");
      clipboard.on("success", function (e) {
        _this.$toast("复制成功");
      });
      clipboard.on("error", function (e) {
        _this.$toast("复制失败");
      });
    }
  }
};
</script>
<style scoped lang="less">
.popBody {
  margin-top: 64px;
  background: #ffffff;
  left: 0;
  bottom: 0;
  top: 0;
  position: absolute;
  width: 100%;
  margin-right: 150px;
  padding-top: 10px;
  // .van-tabs {
  //   width: 240px;
  // }
  .background {
    height: 14px;
    background: #efeff4;
  }
  .card {
    background-color: #fff;
    padding: 0.2rem;
    // margin-bottom: 0.3rem;
    h3 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 16px;
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: 0;
        right: auto;
        top: auto;
        height: 1px;
        width: 100%;
        background-color: #e1e1e1;
        display: block;
        -webkit-transform-origin: 50% 100%;
        transform-origin: 50% 100%;
      }
    }
    li {
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      padding: 0.1rem 0;
    }
  }
}

.rowQu {
  position: relative;
  .InsuranceNum,
  .applicationFormNum {
    position: absolute;
    right: 4px;
    top: 8px;
    padding: 1px 6px;
    font-size: 12px;
    // border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
  }
  .InsuranceNum1 {
    position: absolute;
    right: 44px;
    top: 10px;
  }
  display: flex;
  background-color: #fff;
  padding: 0.2rem 15px;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    flex: 3;
    text-align: right;
    // align-content: center;
  }
  .left_40 {
    text-align: left !important;
  }
  .rowQuLeft {
    flex: 1;
  }
  .rowQuRight {
    flex: 4;
    text-align: right;
  }
}
.backImg {
  height: 68px;
  background: url("../../../../assets/common/backCar.png") no-repeat;
  background-size: 100% 68px;
  .title {
    font-size: 18px;
    font-weight: 500;
    color: #000;
    line-height: 64px;
    padding-left: 15px;
  }
}
.background {
  width: 100%;
  height: 10px;
  background: rgba(245, 245, 245, 1);
}
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
.rowQu {
  font-size: 14px;
}
.copyColor {
    color: #3b8afe;
}
</style>
