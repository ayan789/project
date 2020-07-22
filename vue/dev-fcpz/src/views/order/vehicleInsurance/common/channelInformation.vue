<template>
  <div>
    <popNav popTitle="渠道信息" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">渠道信息</div>
      </div>
      <div class="rowQu">
        <div>是否代理</div>
        <div v-if="detailed.vehicleChannelVO">{{ isProxyFlag == "1" ? "是" : "否" }}</div>
      </div>
      <template>
        <div v-if="isProxyFlag == '0'" class="rowQu">
          <div >直销/直团渠道</div>
          <div>{{ detailed.vehicleChannelVO.businessNatureName }}</div>
        </div>
        <div v-else>
          <div class="rowQu">
            <div>代理人</div>
            <div>
              {{
              detailed.vehicleChannelVO == null
              ? ""
              : detailed.vehicleChannelVO.agentName
              }}
            </div>
          </div>
          <div class="rowQu">
            <div class="rowQuLeft">代理协议</div>
            <div class="rowQuRight">
              {{
              detailed.vehicleOrder == null
              ? ""
              : detailed.vehicleOrder.agentAgreementName
              }}
            </div>
          </div>
        </div>
      </template>
      <div v-if="detailed.vehicleChannelVO && detailed.vehicleChannelVO.salesName" class="rowQu">
        <div>销售人员姓名</div>
        <div>{{ detailed.vehicleChannelVO.salesName }}</div>
      </div>
      <div
        v-if="
          detailed.vehicleChannelVO &&
            detailed.vehicleChannelVO.thirdBusinessInfo
        "
        class="rowQu"
      >
        <div>第三方业务员</div>
        <div>{{ detailed.vehicleChannelVO.thirdBusinessInfo }}</div>
      </div>
      <div class="rowQu">
        <div>业务风险分类</div>
        <div>
          {{
          detailed.vehicleChannelVO == null
          ? ""
          : detailed.vehicleChannelVO.businessNature2Name
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>业务编码</div>
        <div>
          {{
          detailed.vehicleChannelVO == null
          ? ""
          : detailed.vehicleChannelVO.businessCode
          }}
        </div>
      </div>
      <div class="rowQu">
        <div>项目名称</div>
        <div>
          {{
          detailed.vehicleChannelVO == null
          ? ""
          : detailed.vehicleChannelVO.projectName
          }}
        </div>
      </div>
      <div
        v-if="
          detailed.vehicleChannelVO && detailed.vehicleChannelVO.operatorPhone
        "
        class="rowQu"
      >
        <div>归属经办人电话</div>
        <div>{{ detailed.vehicleChannelVO.operatorPhone }}</div>
      </div>
      <div
        v-if="
          detailed.vehicleChannelVO && detailed.vehicleChannelVO.operatorPhone
        "
        class="rowQu"
      >
        <div>执业证书编号</div>
        <div>{{ detailed.vehicleChannelVO.operatorPhone }}</div>
      </div>
      <div v-if="detailed.vehicleChannelVO && detailed.vehicleChannelVO.mobile" class="rowQu">
        <div>联系电话</div>
        <div>{{ detailed.vehicleChannelVO.mobile }}</div>
      </div>
      <div v-if="detailed.vehicleChannelVO && detailed.vehicleChannelVO.permitNo" class="rowQu">
        <div>许可证号</div>
        <div>{{ detailed.vehicleChannelVO.permitNo }}</div>
      </div>
    </div>
  </div>
</template>
<script>
import popNav from "@/components/popNav.vue";

export default {
  components: {
    popNav
  },
  data() {
    return {
      isProxyFlag: "",
      detailed: {
        vehicleTaxVo: {}
      },
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.detailed = this.$route.params;
      if (this.detailed.vehicleChannelVO) {
          this.isProxyFlag = this.detailed.vehicleChannelVO.isProxy;
        }
    });
  },
  methods: {
    //返回上一页
    goBack() {
      this.$globalBack();
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
}
.rowQu {
  position: relative;
  // .InsuranceNum,
  // .applicationFormNum {
  //   position: absolute;
  //   right: 4px;
  //   top: 8px;
  //   // padding: 1px 2px;
  //   border: 1px solid rgb(39, 154, 255);
  //   border-radius: 5px;
  // }
  // .InsuranceNum1 {
  //   position: absolute;
  //   right: 35px;
  //   top: 10px;
  // }
  display: flex;
  background-color: #fff;
  padding:  0.2rem 15px ;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    // text-align: center;
    align-content: center;
    flex: 2;
    text-align: right;
  }
  // .rowQuLeft {
  //   flex: 1;
  // }
  // .rowQuRight {
  //   flex: 2;
  //   text-align: right;
  // }
}
.backImg {
  height: 68px;
  background: url("../../../../assets/common/backCar.png") no-repeat;
  background-size: 100% 68px;
  .title {
    font-size: 16px;
    font-weight: 500;
    color: #000;
    line-height: 75px;
    padding-left: 15px;
  }
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
</style>
