<template>
  <div>
    <popNav popTitle="客户信息" />
    <div class="popBody">
      <div class="backImg">
        <div class="title">投保人信息</div>
      </div>
      <div class="card">
        <template>
          <ul>
            <li>
              <div>投保人类型</div>
              <div v-if="policyHolder.insuredNature">
                {{ policyHolder.insuredNature == "01" ? "个人" : "企业" }}
              </div>
            </li>
            <li>
              <div>投保人姓名</div>
              <div>{{ policyHolder.name }}</div>
            </li>
            <li>
              <div>证件类型</div>
              <div v-if="policyHolder.identityType">
                {{ $utils.findText(identifyTypes, policyHolder.identityType) }}
              </div>
            </li>
            <li>
              <div>证件号码</div>
              <div>{{ policyHolder.identityNo }}</div>
            </li>
            <li v-if="policyHolder.identifyStartDate">
              <div>证件有效起期</div>
              <div>{{ policyHolder.identifyStartDate }}</div>
            </li>
            <li v-if="policyHolder.identifyEndDate">
              <div>证件有效止期</div>
              <div>{{ policyHolder.identifyEndDate }}</div>
            </li>
            <li>
              <div>邮箱</div>
              <div>{{ policyHolder.email }}</div>
            </li>
            <li>
              <div>性别</div>
              <div v-if="policyHolder.sex == '1'">男</div>
              <div v-else-if="policyHolder.sex == '2'">女</div>
              <div v-else></div>
            </li>
            <li>
              <div>职业</div>
              <div v-if="policyHolder.occupation">
                {{ $utils.findText(occupationTypes, policyHolder.occupation) }}
              </div>
            </li>
            <li>
              <div>国籍</div>
              <div v-if="policyHolder.nationality">
                {{
                  $utils.findText(nationalityTypes, policyHolder.nationality)
                }}
              </div>
            </li>
            <li>
              <div>移动电话</div>
              <div class="bt_n">
                <div class="margin_r">{{ policyHolder.phone }}</div>
                <img
                  class="img"
                  src="../../../../assets/common/iphone.png"
                  alt=""
                />
              </div>
            </li>
            <li>
              <div>联系地址</div>
              <div>{{ policyHolder.address }}</div>
            </li>
            <li>
              <div>邮编</div>
              <div>{{ policyHolder.postCode }}</div>
            </li>
            <li>
              <div>投保人与车辆关系</div>
              <div v-if="policyHolder.vehicleRelation == '1'">所有</div>
              <div v-if="policyHolder.vehicleRelation == '2'">使用</div>
              <div v-if="policyHolder.vehicleRelation == '3'">管理</div>
            </li>
          </ul>
        </template>
      </div>
      <div class="background"></div>
      <div class="card">
        <template>
          <h4>被保人信息</h4>
          <ul>
            <li>
              <div>被保险人类型</div>
              <div v-if="insuredPerson.insuredNature">
                {{ insuredPerson.insuredNature == "01" ? "个人" : "企业" }}
              </div>
            </li>
            <li>
              <div>投保人姓名</div>
              <div>{{ insuredPerson.name }}</div>
            </li>
            <li>
              <div>证件类型</div>
              <div v-if="insuredPerson.identityType">
                {{ $utils.findText(identifyTypes, insuredPerson.identityType) }}
              </div>
            </li>
            <li>
              <div>证件号码</div>
              <div>{{ insuredPerson.identityNo }}</div>
            </li>
            <li v-if="insuredPerson.identifyStartDate">
              <div>证件有效起期</div>
              <div>{{ insuredPerson.identifyStartDate }}</div>
            </li>
            <li v-if="insuredPerson.identifyEndDate">
              <div>证件有效止期</div>
              <div>{{ insuredPerson.identifyEndDate }}</div>
            </li>
            <li>
              <div>邮箱</div>
              <div>{{ insuredPerson.email }}</div>
            </li>
            <li>
              <div>性别</div>
              <div v-if="insuredPerson.sex == '1'">男</div>
              <div v-else-if="insuredPerson.sex == '2'">女</div>
              <div v-else></div>
            </li>
            <li>
              <div>职业</div>
              <div v-if="insuredPerson.occupation">
                {{ $utils.findText(occupationTypes, insuredPerson.occupation) }}
              </div>
            </li>
            <li>
              <div>国籍</div>
              <div v-if="insuredPerson.nationality">
                {{
                  $utils.findText(nationalityTypes, insuredPerson.nationality)
                }}
              </div>
            </li>
            <li>
              <div>移动电话</div>
              <div class="bt_n">
                <div class="margin_r">{{ insuredPerson.phone }}</div>
                <img
                  class="img"
                  src="../../../../assets/common/iphone.png"
                  alt=""
                />
              </div>
            </li>
            <li>
              <div>联系地址</div>
              <div>{{ insuredPerson.address }}</div>
            </li>
            <li>
              <div>邮编</div>
              <div>{{ insuredPerson.postCode }}</div>
            </li>
            <li>
              <div>投保人与车辆关系</div>
              <div v-if="insuredPerson.vehicleRelation == '1'">所有</div>
              <div v-if="insuredPerson.vehicleRelation == '2'">使用</div>
              <div v-if="insuredPerson.vehicleRelation == '3'">管理</div>
            </li>
          </ul>
        </template>
      </div>
      <div class="background"></div>
      <div class="card">
        <h4>车主信息</h4>
        <ul>
          <li>
            <div>车主类型</div>
            <div v-if="detailed.vehicleOwnerVO && detailed.vehicleOwnerVO.custType">
              {{
                detailed.vehicleOwnerVO
                  ? detailed.vehicleOwnerVO.custType == "01"
                    ? "个人"
                    : "企业"
                  : ""
              }}
            </div>
          </li>
          <li>
            <div>行驶证车主</div>
            <div>
              {{ detailed.vehicleOwnerVO ? detailed.vehicleOwnerVO.name : "" }}
            </div>
          </li>
          <li>
            <div>证件类型</div>
            <div v-if="detailed.vehicleOwnerVO">
              {{
                $utils.findText(
                  identifyTypes,
                  detailed.vehicleOwnerVO.identityType
                )
              }}
            </div>
          </li>
          <li>
            <div>证件号码</div>
            <div>
              {{
                detailed.vehicleOwnerVO
                  ? detailed.vehicleOwnerVO.identityNo
                  : ""
              }}
            </div>
          </li>
          <li>
            <div>性别</div>
            <div v-if="detailed.vehicleOwnerVO">
              {{
                detailed.vehicleOwnerVO.gender == "1"
                  ? "男"
                  : detailed.vehicleOwnerVO.gender == "2"
                  ? "女"
                  : ""
              }}
            </div>
          </li>
          <li>
            <div>职业</div>
            <div v-if="
                detailed.vehicleOwnerVO && detailed.vehicleOwnerVO.occupation
              ">
              {{
                $utils.findText(
                  occupationTypes,
                  detailed.vehicleOwnerVO.occupation
                )
              }}
            </div>
          </li>
          <li>
            <div>国籍</div>
            <div v-if="detailed.vehicleOwnerVO">
              {{
                $utils.findText(
                  nationalityTypes,
                  detailed.vehicleOwnerVO.nationality
                )
              }}
            </div>
          </li>
          <li>
            <div>移动电话</div>
            <div class="bt_n">
              <div class="margin_r">
                {{
                  detailed.vehicleOwnerVO ? detailed.vehicleOwnerVO.phoneNo : ""
                }}
              </div>
              <img
                class="img"
                src="../../../../assets/common/iphone.png"
                alt=""
              />
            </div>
          </li>
          <li>
            <div>联系地址</div>
            <div>
              {{
                detailed.vehicleOwnerVO ? detailed.vehicleOwnerVO.address : ""
              }}
            </div>
          </li>
          <li>
            <div>邮编</div>
            <div>
              {{
                detailed.vehicleOwnerVO ? detailed.vehicleOwnerVO.postCode : ""
              }}
            </div>
          </li>
        </ul>
      </div>
      <div class="background"></div>
      <div class="card">
        <h4>开票信息</h4>
        <ul>
          <li>
            <div>客户类型</div>
            <div v-if="
                detailed.vehicleInvoice && detailed.vehicleInvoice.clientType
              ">
              {{ detailed.vehicleInvoice.clientType == "1" ? "个人" : "企业" }}
            </div>
          </li>
          <li>
            <div>纳税人类型</div>
            <div v-if="
                detailed.vehicleInvoice && detailed.vehicleInvoice.taxPayerType
              ">
              {{
                detailed.vehicleInvoice.taxPayerType == "4" ? "个人" : "企业"
              }}
            </div>
          </li>
          <li>
            <div>发票类型</div>
            <div v-if="detailed.vehicleInvoice">
              {{
                $utils.findText(
                  invoiceTypes,
                  detailed.vehicleInvoice.invoiceType
                )
              }}
            </div>
          </li>
          <li>
            <div>公司名称</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.taxCompanyName
              }}
            </div>
          </li>
          <li>
            <div>电子邮箱</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.email
              }}
            </div>
          </li>
          <li>
            <div>纳税人识别号</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.taxPayerIdentification
              }}
            </div>
          </li>
          <li>
            <div>税务登记地址</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.taxRegistryAddress
              }}
            </div>
          </li>
          <li>
            <div>税务登记电话</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.taxRegistryPhone
              }}
            </div>
          </li>
          <li>
            <div>开户行</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.bankAgentName
              }}
            </div>
          </li>
          <li>
            <div>账号</div>
            <div>
              {{
                detailed.vehicleInvoice == null
                  ? ""
                  : detailed.vehicleInvoice.bankAccount
              }}
            </div>
          </li>
        </ul>
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
      policyHolder: {},
      insuredPerson: {},
      identifyTypes: "",
      occupationTypes: "",
      nationalityTypes: "",
      invoiceTypes: "",
      isProxyFlag: "",
      detailed: {
        vehicleTaxVo: {}
      }
    };
  },
  mounted () {
    this.$nextTick(() => {
      this.identifyTypes = Object.keys(Config.identifyType).map(key => ({
        text: Config.identifyType[key],
        value: key
      }));
      this.occupationTypes = Object.keys(Config.profession).map(key => ({
        text: Config.profession[key],
        value: key
      }));
      this.nationalityTypes = Object.keys(Config.countryCodeTJ).map(key => ({
        text: Config.countryCodeTJ[key],
        value: key
      }));
      this.invoiceTypes = Object.keys(Config.invoiceType).map(key => ({
        text: Config.invoiceType[key],
        value: key
      }));
      this.detailed = this.$route.params;

      if (this.detailed.relationPeopleList != null) {
        for (let i = 0; i < this.detailed.relationPeopleList.length; i++) {
          if (
            this.detailed.relationPeopleList &&
            this.detailed.relationPeopleList[i].relationPeopleType == "1"
          ) {
            this.policyHolder = this.detailed.relationPeopleList[i];
          }
          if (
            this.detailed.relationPeopleList &&
            this.detailed.relationPeopleList[i].relationPeopleType == "2"
          ) {
            this.insuredPerson = this.detailed.relationPeopleList[i];
            // console.log(this.insuredPerson, "this.insuredPerson==>1589");
          }
        }
      } else {
        this.detailed.relationPeopleList = [];
      }
    });
  }
  // methods: {
  //   //返回上一页
  //   goBack() {
  //     this.$globalBack();
  //   }
  // }
};
</script>
<style scoped lang="less">
.bt_n {
  position: relative;
  width: 150px;
}
.margin_r {
  padding-right: 26px;
  text-align: right;
}
.img {
  width: 17px;
  height: 17px;
  position: absolute;
  top: 1px;
  right: 0px;
}
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
    width: 100%;
    height: 10px;
    background: rgba(245, 245, 245, 1);
  }
  .card {
    background-color: #fff;
    padding: 10px 15px;
    // margin-bottom: 0.3rem;
    h4 {
      padding: 0.3rem 0;
      position: relative;
      font-size: 16px;
      // &::after {
      //   content: "";
      //   position: absolute;
      //   left: 0;
      //   bottom: 0;
      //   right: auto;
      //   top: auto;
      //   height: 1px;
      //   width: 100%;
      //   background-color: #e1e1e1;
      //   display: block;
      //   -webkit-transform-origin: 50% 100%;
      //   transform-origin: 50% 100%;
      // }
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
    padding: 1px 2px;
    border: 1px solid rgb(39, 154, 255);
    border-radius: 5px;
  }
  .InsuranceNum1 {
    position: absolute;
    right: 35px;
    top: 10px;
  }
  display: flex;
  background-color: #fff;
  padding: 0.2rem;
  div:nth-of-type(1) {
    flex: 1;
  }
  div:nth-of-type(2) {
    // text-align: center;
    align-content: center;
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
.van-icon {
  z-index: 9;
  top: 14px;
  left: 18px;
  font-size: 18px;
  position: fixed;
}
</style>
