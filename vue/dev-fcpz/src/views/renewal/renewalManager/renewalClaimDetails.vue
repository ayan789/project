<template>
  <div>
    <Global_globalNav
      title="更多信息"
      @clickBack="
        () => {
          this.$router.back();
        }
      "
    ></Global_globalNav>
    <div class="contact-details">
      <div class="contact-details--basicInfo">
        <div class="contact-details--carInfo">
          <div class="title">
            <img src="../../../assets/renewal/car.png" />{{
              paramsData.licenseNo
            }}
          </div>
          <van-row type="flex" justify="space-between">
            <van-col span="12" class="details_title">投保人</van-col>
            <van-col span="12" class="details_content">{{
              paramsData.applicantName
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="8" class="details_title">保单号</van-col>
            <van-col span="16" class="details_content">{{
              paramsData.policyNo
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">终止日期</van-col>
            <van-col span="12" class="details_content">{{
              respEndDate
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">任务状态</van-col>
            <van-col span="12" class="details_content">{{
              handleStatusName
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">我司续保状态</van-col>
            <van-col span="12" class="details_content">{{
              trackStatusName
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">被保人</van-col>
            <van-col span="12" class="details_content">{{
              paramsData.insuredName
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">业务风险分类</van-col>
            <van-col span="12" class="details_content">{{
              handler2name
            }}</van-col>
          </van-row>
          <van-row>
            <van-col span="12" class="details_title">总报价次数</van-col>
            <van-col span="12" class="details_content">{{
              quoteTimesSum
            }}</van-col>
          </van-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//获取保单信息
export default {
  data: () => ({
    queryData: {
      frameNo: "37854594574854565",
      licenseNo: "浙",
      orgId: "",
      policyNo: "",
      riskCode: "DFA",
      userId: "",
      isCompulsoryFlag: "",
      is_compulsory_insurance: "",
      respEndDate: ""
    },
    resultData: {
      carResponse: {}
    }
  }),
  filters: {
    //掩码
    filterMask(val, firstLen, endLen) {
      if (val) {
        let len = val.length;
        return (val =
          val.substr(0, firstLen) + "XXXX" + val.substr(len - endLen));
      }
    }
  },
  computed: {
    respEndDate() {
      return this.$route.params.respEndDate;
    },
    handleStatusName() {
      return this.$route.params.handleStatusName;
    },
    trackStatusName() {
      return this.$route.params.trackStatusName;
    },
    handler2name() {
      return this.$route.params.handler2name;
    },
    quoteTimesSum() {
      return this.$route.params.quoteTimesSum || "0";
    },
    paramsData() {
      return { ...this.$route.params };
    }
  },
  async created() {
    // this.queryData = Object.assign({}, this.queryData, {
    //   frameNo: this.$route.params.frameNo || "",
    //   licenseNo: this.$route.params.licenseNo || "",
    //   orgId: this.$route.params.orgId || "",
    //   policyNo: this.$route.params.policyNo || "",
    //   riskCode: this.$route.params.riskCode || "",
    //   userId: this.$route.params.userId || "",
    //   isCompulsoryFlag: this.$route.params.isCompulsoryFlag || "",
    //   is_compulsory_insurance: this.$route.params.is_compulsory_insurance || "",
    //   respEndDate: this.$route.params.respEndDate || ""
    // });
    // await queryRenewalPolicyInfo(this.queryData).then(res => {
    //   if (res.code === "1") {
    //     this.resultData = { ...res.data };
    //   }
    // });
  },
  methods: {}
};
</script>

<style lang="less" scoped>
.contact-details {
  background: #f7f7f7;
  height: 100%;
  border-radius: 15px 15px 0px 0px;
  position: relative;
  margin-top: 80px;
  a {
    color: #666666;
  }
  /deep/.van-row {
    margin-bottom: 6px;
  }
  & > div {
    background: #ffffff;
    margin-bottom: 10px;
    padding: 18px;
    .title {
      margin-bottom: 13px;
      img {
        width: 20px;
        height: 20px;
        vertical-align: middle;
        margin-right: 10px;
      }
    }
  }
  & > div:last-child {
    margin-bottom: 0;
  }
  /deep/.van-cell:not(:last-child)::after {
    border-bottom: none;
  }
  /deep/.van-cell__title {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
  }
  .title {
    font-size: 16px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
  }
  .details_title {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }
  .details_content {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    text-align: right;
  }
  .contact-details--button {
    // position: absolute;
    // bottom: 0;
    // margin-top: 12px;
    position: fixed;
    bottom: 0;
    border-top: 12px solid #f7f7f7;
    padding: 0;
    width: 100%;
    /deep/.van-button {
      width: 50%;
      border: none;
      font-size: 16px;
      font-family: PingFangSC-Regular, PingFang SC;
    }
  }
}
//头部
/deep/ .van-nav-bar {
  background: url(../../../assets/renewal/bavBackground.png) no-repeat 100%,
    100% !important;
  background-size: cover !important;
}
</style>
