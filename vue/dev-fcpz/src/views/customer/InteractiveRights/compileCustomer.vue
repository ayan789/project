<template>
  <div class="compileCustomer">
    <div class="containerIncreased">
      <ul>
        <div class="background"></div>
        <li>
          <div class="customer">
            <div class="customerLeft">客户类型</div>
            <div class="customerRight">
              个人客户
              <van-icon class="customerRighticon" name="arrow" />
            </div>
          </div>
        </li>
        <li class="contactNumber">
          <div class="contact">
            <van-field v-model="value" label="姓名" placeholder input-align="right" />
            <van-icon class="customerRighticon phoneIcon" name="arrow" />
          </div>
        </li>
        <li>
          <div class="customer">
            <div class="customerLeft">证件类型</div>
            <div class="customerRight">
              身份证
              <van-icon class="customerRighticon" name="arrow" />
            </div>
          </div>
        </li>
        <li>
          <van-field v-model="IDnumber" label="身份证号" placeholder="请输入证件号" input-align="right" />
        </li>
        <li>
          <van-field v-model="phoneNumber" label="手机号" placeholder input-align="right" />
        </li>
        <li>
          <van-field v-model="licenseNumber" label="车牌号" placeholder input-align="right" />
        </li>
        <li>
          <van-field v-model="vinNumber" label="车架号" placeholder input-align="right" />
        </li>
        <li>
          <van-field v-model="engineNvumer" label="发动机号" placeholder input-align="right" />
        </li>
        <li>
          <van-field v-model="emailNumber" label="邮箱" placeholder="请输入" input-align="right" />
        </li>
        <li>
          <van-field v-model="occupationNumber" label="职业" placeholder="请输入" input-align="right" />
        </li>
        <li class="contactNumber">
          <div class="contact">
            <van-field v-model="contactNumber" label="联系地址" placeholder input-align="right" />
            <van-icon class="phoneIcon" name="plus" />
          </div>
        </li>
        <li>
          <van-field v-model="remarkNumber1" label="备注1" placeholder input-align="right" />
        </li>
        <li class="labelNumber">
          <van-field v-model="labelNumber" label="标签" placeholder input-align="right" />
        </li>
        <li>
          <van-field v-model="remarkNumber" label="备注1" placeholder input-align="right" />
        </li>
        <div class="background"></div>
        <div class="background"></div>
        <li>
          <div class="customer">
            <div class="customerLeft uploading">影像上传</div>
            <div class="customerRight">
              <van-icon class="customerRighticon" name="arrow" />
            </div>
          </div>
        </li>
        <div class="compileCustomerBottom">
          <div class="confirm" @click="submitClick">确定</div>
        </div>
      </ul>
    </div>
  </div>
</template>
<script>
import { Field } from "vant";
import {
  addOrUpdateSinglecustomeromer,
  addOrUpdateTeamCustomer
} from "../../../http/module/customerApi";

export default {
  components: {
    [Field.name]: Field
  },
  data() {
    return {
      value: "安科技",
      IDnumber: "",
      phoneNumber: "13564335664",
      licenseNumber: "甘F-AX586",
      vinNumber: "HUXHSNIDMSHSN",
      engineNvumer: "182938584839384",
      emailNumber: "",
      occupationNumber: "",
      contactNumber: "浦东新区",
      remarkNumber1: "公司地址",
      remarkNumber: "公司地址",
      labelNumber: "添加标签对客户进行分类"
    };
  },

  methods: {
    async submitClick() {
      let params = {
        id: "", //主键 ID
        custNo: "", //客户号
        customerName: "", //姓名
        certificateType: "", //证件类型
        certificateNo: "", //证件号码
        mobileNum: "", //手机号
        carNo: "", //车牌
        vin: "", //车架号
        brandNo: "", //厂牌型号
        engineNo: "", //发动机号
        emaill: "", //邮箱
        profession: "", //职业
        address: "", //联系地址1
        addressType: "" //地址类型
      };
      let res = await addOrUpdateSinglecustomeromer(params);
      //console.log(res, "==addOrUpdateSinglecustomeromer");

      let params_team = {
        userCode: "", //业务号
        custNo: "", //客户号
        custName: "", //姓名
        certificateType: "", //证件类型
        certificateNo: "", //证件号码
        mobileNum: "", //手机号
        emaill: "", //邮箱
        unitNature: "", //单位性质
        businessScope: "", //企业经营范围
        contractedProducts: "", //可承保产品
        address: "", //联系地址1
        remark: "" //备注1
      };
      let res_s = await addOrUpdateTeamCustomer(params_team);
      //console.log(res_s, "==addOrUpdateTeamCustomer");
    }
  }
};
</script>
<style lang="less">
.containerIncreased {
  ul {
    li {
      padding-left: 15px;
      padding-right: 15px;
      .customer {
        height: 48px;
        line-height: 48px;
        border-bottom: 1px solid #e5e6e8;
        .customerLeft,
        .customerRight {
          font-size: 15px;
        }
        .customerLeft {
          float: left;
          color: rgba(51, 51, 51, 1);
        }
        .uploading {
          font-size: 14px;
          font-weight: 600;
          color: rgba(51, 51, 51, 1);
        }
        .customerRight {
          position: relative;
          float: right;
          color: #666666;
          padding-right: 19px;
          .customerRighticon {
            position: absolute;
            right: 0px;
            top: 17px;
          }
        }
      }
    }
    li.phoneNumber {
      .phone {
        position: relative;
        .phoneIcon {
          position: absolute;
          top: 15px;
          right: 0;
        }
      }
    }
    li.contactNumber {
      .contact {
        position: relative;
        .phoneIcon {
          position: absolute;
          top: 15px;
          right: 0;
        }
      }
    }
    .background {
      height: 10px;
      background: #f7f7f7;
    }
    .containerBottom {
      height: 71px;
      background: #f7f7f7;
      padding: 30px 15px 0 15px;
      .confirm {
        width: 345px;
        line-height: 44px;
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        font-size: 16px;
        color: rgba(255, 255, 255, 1);
        text-align: center;
      }
    }
  }
}
.compileCustomer {
  .containerIncreased {
    .compileCustomerBottom {
      background: #f7f7f7;
      padding: 33px 15px 0 15px;
      height: 69px;
      .confirm {
        width: 345px;
        line-height: 46px;
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        font-size: 16px;
        color: rgba(255, 255, 255, 1);
        text-align: center;
      }
    }
  }
}
</style>
