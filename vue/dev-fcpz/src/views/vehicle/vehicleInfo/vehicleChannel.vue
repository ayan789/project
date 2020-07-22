<!--
 * @Author: heweiwei
 * @Page: 渠道信息
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-06-29 14:07:20
 * @FilePath: /src/views/vehicle/vehicleInfo/vehicleChannel.vue
-->
<template>
  <div class="vehicle-channel-informatization">
    <van-sticky>
      <nav-bar type="vehicle" title="渠道信息" @clickBack="back" />
    </van-sticky>
    <div class="content">
      <van-cell
        class="item"
        required
        is-link
        @click="toAgentList"
        v-if="channel.isProxy == '1'"
      >
        <span slot="title" class="item-title">代理人</span>
        <div class="item-right">
          <span class="item-right-long">{{ channel.agentName }}</span>
        </div>
      </van-cell>
      <van-cell
        v-if="channel.isProxy == '1'"
        class="item"
        required
        @click="setPicker('agentAgreementCode')"
      >
        <span slot="title" class="item-title">代理协议</span>
        <div class="item-right">
          <span
            :class="{
              'item-right-long': true,
              'item-placeholder': !channel.agentAgreementCode
            }"
          >
            {{ channel.agentAgreementName }}
          </span>
          <van-icon class="item-right-arrow" name="arrow" />
        </div>
      </van-cell>
      <van-cell
        v-if="channel.isProxy == '0'"
        class="item"
        @click="setPicker('businessNatureCode')"
      >
        <span slot="title" class="item-title">直销/直团渠道</span>
        <div class="item-right">
          <span
            :class="{
              'item-right-long': true,
              'item-placeholder': !channel.businessNatureCode
            }"
          >
            {{ channel.businessNatureName }}
          </span>
          <van-icon class="item-right-arrow" name="arrow" />
        </div>
      </van-cell>
      <van-cell class="item" @click="setPicker('handler2Code')">
        <span slot="title" class="item-title">业务风险分类</span>
        <div class="item-right">
          <span
            :class="{
              'item-right-long': true,
              'item-placeholder': !channel.businessNature2Code
            }"
          >
            {{ channel.businessNature2Name }}
          </span>
          <van-icon class="item-right-arrow" name="arrow" />
        </div>
      </van-cell>
      <!-- 青海、内蒙古特有字段 -->
      <van-cell
        class="item"
        v-if="areaCode == '1501' || areaCode == '6301'"
        required
      >
        <span slot="title" class="item-title">归属经办人电话</span>
        <div class="item-right">
          <input
            v-model="channel.operatorPhone"
            class="item-right-input"
            placeholder="请输入"
            maxlength="11"
          />
        </div>
      </van-cell>
      <van-cell
        class="item"
        v-if="
          (areaCode == '3201' || areaCode == '3301') &&
            channel.businessNatureCode != '2401' &&
            channel.isProxy == '1'
        "
        required
      >
        <span slot="title" class="item-title">中介机构代码</span>
        <div class="item-right">
          <input
            v-model="channel.agencyOrgCode"
            class="item-right-input"
            placeholder="请输入"
            maxlength="15"
          />
        </div>
      </van-cell>
      <van-cell
        class="item"
        @click="!!channel.handler2Code ? setPicker('businessCode') : ''"
      >
        <span slot="title" class="item-title">业务编码</span>
        <div class="item-right" v-if="!!channel.handler2Code">
          <span
            :class="{
              'item-placeholder': !channel.businessCode
            }"
          >
            {{ channel.businessCode || "请选择" }}
          </span>
          <van-icon class="item-right-arrow" name="arrow" />
        </div>
        <div class="item-right" v-else>
          <input
            :disabled="!!channel.handler2Code"
            @input="changeBusinessCode"
            v-model="channel.businessCode"
            class="item-right-input"
            placeholder="请输入"
            maxlength="15"
          />
        </div>
      </van-cell>
      <van-cell class="item">
        <span slot="title" class="item-title">业务名称</span>
        <span class="item-right">{{ channel.businessName }}</span>
      </van-cell>
      <van-cell class="item">
        <span slot="title" class="item-title">项目编码</span>
        <span class="item-right" v-if="channel.businessCode">
          {{ channel.projectCode }}
        </span>
        <div v-else class="item-right">
          <input
            v-model="channel.projectCode"
            @input="changeProjectCode"
            class="item-right-input"
            placeholder="请输入"
            maxlength="15"
          />
        </div>
      </van-cell>
      <van-cell class="item">
        <span slot="title" class="item-title">项目名称</span>
        <span class="item-right">{{ channel.projectName }}</span>
      </van-cell>
      <!-- 福建地区必填 -->
      <!-- 公司直邮/车商渠道/银行渠道/经纪渠道/寿险代理 -->
      <van-cell
        class="item"
        :required="areaCode == '3501' && thirdPartyRequired"
      >
        <span slot="title" class="item-title">第三方业务员姓名</span>
        <div class="item-right">
          <input
            class="item-right-input"
            v-model="channel.thirdPartyHandlerName"
            placeholder="请输入第三方业务员姓名"
            maxlength="20"
          />
        </div>
      </van-cell>
      <!-- 福建地区必填 -->
      <!-- 公司直邮/车商渠道/银行渠道/经纪渠道/寿险代理 -->
      <van-cell
        class="item"
        :required="areaCode == '3501' && thirdPartyRequired"
      >
        <span slot="title" class="item-title">第三方业务员工号</span>
        <div class="item-right">
          <input
            class="item-right-input"
            v-model="channel.thirdPartyHandlerNo"
            placeholder="请输入第三方业务员工号"
            maxlength="20"
          />
        </div>
      </van-cell>
      <!-- 广东、甘肃、深圳、陕西、福建特有字段 -->
      <!-- 销售人员姓名 执业证书编号 -->
      <!-- 业务来源为专业代理、兼业代理、经纪业务、个人代理，必填 -->
      <!-- 根据代理人代码从销管获取的信息不可修改，手动填写的可修改。 -->
      <!-- @赵未 更改 2020年05月12日11:01:45 陕西地区不区分是否代理 -->
      <van-cell
        class="item"
        v-show="
          areaCode == '6101' ||
            areaCode == '3501' ||
            (channel.isProxy == '1' && salesInfoArea)
        "
        :required="areaCode == '6101' || salesInfoRequired"
      >
        <span slot="title" class="item-title">销售人员姓名</span>
        <div class="item-right">
          <input
            :disabled="presetSalesInfo.salesName"
            class="item-right-input"
            v-model="channel.salesName"
            placeholder="请输入销售人员姓名"
          />
        </div>
      </van-cell>
      <!-- 深圳特有字段 销售人员身份证号 -->
      <van-cell
        class="item"
        v-show="channel.isProxy == '1' && areaCode == '4403'"
      >
        <span slot="title" class="item-title">销售人员身份证号</span>
        <div class="item-right">
          <input
            class="item-right-input"
            v-model="channel.salesIdNo"
            placeholder="请输入销售人员身份证号"
          />
        </div>
      </van-cell>
      <!-- 执业证书编号 同销售人员姓名 -->
      <!-- @赵未 更改 2020年05月12日11:01:45 陕西地区不区分是否代理 -->
      <van-cell
        class="item"
        v-show="
          personalAgent &&
            (areaCode == '6101' || (channel.isProxy == '1' && salesInfoArea))
        "
        :required="areaCode == '6101' || salesInfoRequired"
      >
        <span slot="title" class="item-title">执业证书编号</span>
        <div class="item-right">
          <input
            :disabled="presetSalesInfo.exhibitionNo"
            class="item-right-input"
            v-model="channel.exhibitionNo"
            placeholder="请输入执业证书编号"
          />
        </div>
      </van-cell>
      <!-- 联系电话--广东特有 -->
      <van-cell class="item" v-show="areaCode == '4401'">
        <span slot="title" class="item-title">联系电话</span>
        <div class="item-right">
          <input
            class="item-right-input"
            v-model="channel.mobile"
            maxlength="11"
            placeholder="请输入联系电话"
          />
        </div>
      </van-cell>
      <!-- 许可证号 -->
      <van-cell
        class="item"
        v-show="
          !personalAgent &&
            (areaCode == '6101' || (channel.isProxy == '1' && salesInfoArea))
        "
        :required="areaCode == '6101' || salesInfoRequired"
      >
        <span slot="title" class="item-title">许可证号</span>
        <div class="item-right">
          <input
            :disabled="presetSalesInfo.permitNo"
            class="item-right-input"
            v-model="channel.permitNo"
            placeholder="请输入许可证号"
          />
        </div>
      </van-cell>
      <!-- 陕西特有字段，业务来源为专业代理、兼业代理、经纪业务、个人代理，必填 -->
      <!-- 根据代理人代码从销管获取的联系电话不可修改，手动填写的可修改。 -->
      <!-- @赵未 更改 2020年05月12日11:01:45 陕西地区不区分是否代理 -->
      <van-cell
        class="item"
        v-show="areaCode == '6101'"
        :required="areaCode == '6101' || salesInfoRequired"
      >
        <span slot="title" class="item-title">销售机构名称</span>
        <div class="item-right">
          <input
            :disabled="presetSalesInfo.salesCodeName"
            class="item-right-input"
            v-model="channel.salesCodeName"
            placeholder="请输入销售机构名称"
            @input="handelSalesInput"
          />
        </div>
      </van-cell>
      <!-- 陕西特有字段，业务来源为专业代理、兼业代理、经纪业务、个人代理，必填 -->
      <!-- 根据代理人代码从销管获取的联系电话不可修改，手动填写的可修改。 -->
      <!-- @赵未 更改 2020年05月12日11:01:45 陕西地区不区分是否代理 -->
      <van-cell
        class="item"
        v-show="areaCode == '6101'"
        :required="areaCode == '6101' || salesInfoRequired"
      >
        <span slot="title" class="item-title">联系电话</span>
        <div class="item-right">
          <input
            :disabled="presetSalesInfo.mobile"
            class="item-right-input"
            v-model="channel.mobile"
            maxlength="11"
            placeholder="请输入联系电话"
          />
        </div>
      </van-cell>
      <van-cell class="item">
        <div slot="title" class="item-title">
          <van-checkbox
            class="check-box"
            icon-size="16"
            v-model="isDefaultChecked"
            shape="square"
          >
            设为默认渠道
          </van-checkbox>
        </div>
      </van-cell>
    </div>
    <div class="bottom-container">
      <confirmBottom style="position: absolute;" @click="saveChannel" />
    </div>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :title="pickerTitle"
        :default-index="pickerDefaultIndex"
        :columns="pickerData"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      >
        <template #columns-top>
          <form action="/">
            <van-search
              v-model="searchValue"
              show-action
              placeholder="请输入搜索关键词"
              @search="onSearch"
              @cancel="onCancel"
            />
          </form>
        </template>
      </van-picker>
    </van-popup>
  </div>
</template>
<script>
import confirmBottom from "@/components/confirmBottom.vue";
import {
  getPQCode,
  agentQuery,
  //setDefaultChannel
} from "@src/http/module/vehicle";
import { mapState, mapMutations } from "vuex";
import Config from "@src/common/json/newCoreDataDictionary";
import _ from "lodash";
export default {
  components: {
    confirmBottom
  },
  data() {
    /**NOTE
     * channelChange事件传递选定的渠道信息, 接收参数为当前页面的渠道信息
     * riskCode 车险当前默认为DEA, 非车险需要传入该参数,以正确调用接口
     */
    const { query = {}, params = {} } = this.$route;
    let { riskCode: riskCode1 } = query;
    let { riskCode: riskCode2 } = params;
    let riskCode = riskCode1 || riskCode2 || "DEA";
    // const { confirm } = params;
    return {
      // confirm,
      riskCode: riskCode || "DEA",
      // 风险分类数组
      salesShadowAgentMsgList: [],
      // 代理人协议数组
      salesAgreementMsgList: [],
      businessNatures: [
        { text: "业务员销售（直销）", value: "0101" },
        { text: "业务员销售（直团）", value: "7101" },
        { text: "门店直销", value: "0201" }
      ],
      businessList: [],
      list: [],
      //请求数据,该数据似乎没必要保存...
      requestData: null,
      dataPickerType: "",
      pickerTitle: "",
      pickerDefaultIndex: 0,
      pickerData: [],
      presetSalesInfo: {
        salesName: false,
        exhibitionNo: false,
        permitNo: false,
        salesCodeName: false,
        mobile: false
      },
      searchValue: ""
    };
  },
  mounted() {
    this.channel.businessNatureName =
      this.$route.query.businessNatureName || this.channel.businessNatureName;
  },
  activated() {
    const { query = {}, params = {} } = this.$route;
    const { riskCode } = query;
    // const { confirm } = params;
    if (riskCode) {
      this.riskCode = riskCode;
    }
    if (confirm) {
      this.confirm = confirm;
    }
    this.channel.agreementNo = this.channel.agentAgreementCode;
    this.channel.agreementName = this.channel.agentAgreementName;
    this.channel.businessNature2 = this.channel.businessNature2Code;
    this.getPQCode();
    this.salesAgreementMsgList = [];
    this.salesShadowAgentMsgList = [];
    this.agentQuery();
  },
  deactivated() {},
  computed: {
    ...mapState("channel", {
      channel: ({ channel }) => channel
    }),
    areaCode() {
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      return comCode.substr(0, 4);
    },
    personalAgent() {
      let v = this.channel.businessNature || this.channel.businessNatureCode;
      return v == "2401";
    },
    salesNameFromHandlerName() {
      let v = this.channel.businessNature || this.channel.businessNatureCode;
      //直销渠道/公司直销/直团渠道
      return v == "7101" || v == "0101" || v == "0301";
    },
    salesInfoArea() {
      let userInfo = this.localStorage.get("userInfo", {});
      let comCode = userInfo.comCode || "";
      let areaCode = comCode.substr(0, 4);
      if (
        areaCode == "4401" ||
        areaCode == "6201" ||
        areaCode == "4403" ||
        areaCode == "6101"
      ) {
        return true;
      }
      return false;
    },
    salesInfoRequired() {
      //业务来源为专业代理、兼业代理、经纪业务、个人代理
      let v = this.channel.businessNature || this.channel.businessNatureCode;
      if (
        v == "2101" ||
        v == "2205" ||
        v == "6700" ||
        v == "2105" ||
        v == "2102" ||
        v == "2401"
      ) {
        return true;
      }
      return false;
    },
    thirdPartyRequired() {
      // 公司直邮/车商渠道/银行渠道/经纪渠道/寿险代理
      let v = this.channel.businessNature || this.channel.businessNatureCode;
      if (v) {
        if (
          v.indexOf("030") == 0 ||
          v.indexOf("220") == 0 ||
          v.indexOf("230") == 0 ||
          v.indexOf("250") == 0 ||
          v.indexOf("260") == 0
        ) {
          return true;
        }
      }
      return false;
    },
    isDefaultChecked: {
      get: function() {
        return this.channel.isDefault == "1";
      },
      set: function(v) {
        this.channel.isDefault = v ? "1" : "0";
      }
    },
    dataPickerShow: {
      get: function() {
        return !!this.dataPickerType;
      },
      set: function() {
        this.dataPickerType = "";
        this.pickerData = [];
      }
    }
  },
  watch: {
    "channel.agentAgreementCode"(v) {
      this.channel.agreementNo = v;
    },
    "channel.agentAgreementName"(v) {
      this.channel.agreementName = v;
    },
    "channel.businessNature2Code"(v) {
      this.channel.businessNature2 = v;
    },
    "channel.thirdPartyHandlerName"(v) {
      // 不能录入中英文状态下的单/双引号。TUDO chenjiafeng
      this.channel.thirdPartyHandlerName = v.replace(/'|"|’|‘|“|”/g, "");
    }
  },
  methods: {
    ...mapMutations("channel", ["setChannel", "initChannel"]),
    back() {
      this.$root.$off("channelChange");
      this.$router.back();
      this.initChannel({});
    },
    handelSalesInput() {
      this.channel.salesCodeName = this.channel.salesCodeName.replace(
        /[0-9]/g,
        ""
      );
    },
    changeBusinessCode(e) {
      let businessCode = e.target.value.trim();
      let item = this.list.find(item => item.businessCode === businessCode);
      item = item || {
        businessName: "",
        projectCode: "",
        projectName: ""
      };
      this.setChannel(Object.assign({}, this.channel, item));
    },
    changeProjectCode(e) {
      let projectCode = e.target.value.trim();
      let item = this.list.find(item => item.projectCode === projectCode);
      item = item || {
        projectName: ""
      };
      this.setChannel(
        Object.assign({}, this.channel, {
          projectCode,
          projectName: item.projectName
        })
      );
    },
    setPicker(type) {
      switch (type) {
        case "agentAgreementCode": {
          // this.pickerTitle = "代理协议";
          this.pickerData = this.salesAgreementMsgList;
          break;
        }
        case "businessNatureCode": {
          // this.pickerTitle = "业务来源";
          this.pickerData = this.businessNatures;
          break;
        }
        case "handler2Code": {
          // this.pickerTitle = "业务风险分类";
          this.pickerData = this.salesShadowAgentMsgList;
          break;
        }
        case "businessCode": {
          // this.pickerTitle = "业务编码";
          this.pickerData = this.businessList;
          console.log(!!this.channel.handler2Code);
          break;
        }
        default:
          break;
      }
      this.pickerData = [{ text: "请选择", value: "" }, ...this.pickerData];
      let pickerDefaultIndex = this.pickerData.findIndex(
        item => item.value == this.channel[type]
      );
      this.pickerDefaultIndex =
        pickerDefaultIndex == -1 ? 0 : pickerDefaultIndex;
      this.dataPickerType = type;
    },
    dataPickerConfirm(data) {
      this.channel[this.dataPickerType] = data.value;
      switch (this.dataPickerType) {
        case "agentAgreementCode": {
          this.channel.agentAgreementName = this.$utils.findText(
            this.salesAgreementMsgList,
            data.value
          );
          break;
        }
        case "businessNatureCode": {
          let businessNatures = Object.keys(Config.businessNature).map(key => ({
            text: Config.businessNature[key],
            value: key
          }));
          this.channel.businessNatureName = this.$utils.findText(
            [...this.businessNatures, ...businessNatures],
            data.value
          );
          this.agentQuery();
          break;
        }
        case "handler2Code": {
          let salesShadowAgentMsg = this.salesShadowAgentMsgList.find(
            i => i.shadowAgentCode == data.value
          );
          if (salesShadowAgentMsg) {
            this.channel.businessNature2Code =
              salesShadowAgentMsg.shadowAgentType;
            this.channel.businessNature2Name =
              salesShadowAgentMsg.shadowAgentName;
            // this.channel.handler2Code = salesShadowAgentMsg.shadowAgentCode;
            this.channel.handler2Name = salesShadowAgentMsg.shadowAgentName;
          } else {
            this.channel.businessNature2Code = "";
            this.channel.businessNature2Name = "";
            this.channel.handler2Name = "";
          }
          break;
        }
        case "businessCode": {
          let item = this.list.find(item => item.businessCode === data.value);
          item = item || {
            businessCode: "",
            businessName: "",
            projectCode: "",
            projectName: ""
          };
          this.setChannel(Object.assign({}, this.channel, item));
          break;
        }
        default:
          break;
      }
      // this.channel[this.dataPickerType.replace("Code", "Name")] = data.text;
      this.dataPickerShow = false;
    },
    dataPickerCancel() {
      this.dataPickerShow = false;
    },
    onSearch(searchValue) {
      let pickerData = this.pickerData.filter(({ text, value }) => {
        return (
          String(text).indexOf(searchValue) != -1 ||
          String(value).indexOf(searchValue) != -1
        );
      });
      if (pickerData.length == 0) {
        this.$toast("没有匹配的选项");
      } else {
        this.pickerData = pickerData;
      }
    },
    onCancel() {
      this.setPicker(this.dataPickerType);
    },
    toAgentList() {
      let query = { riskCode: this.riskCode };
      this.$router.push({ name: "vehicleAgentList", query });
    },
    saveDate() {
      localStorage.setItem(
        "agentInfo",
        JSON.stringify(
          Object.assign(
            {},
            {
              comCode: this.comCode,
              businessNature: this.businessNatureCode,
              agentCode: this.channel.agentCode,
              agentName: this.channel.agentName,
              agreementNo: this.channel.agentAgreementCode,
              agreementName: this.channel.agentAgreementName,
              businessNature2: this.channel.businessNature2Code,
              handler2Code: this.channel.handler2Code,
              handler2Name: this.channel.handler2Name
            },
            this.channel
          )
        )
      );
    },
    getPQCode() {
      let params = { comCode: this.localStorage.get("userInfo", {}).comCode };
      getPQCode(params).then(
        res => {
          if (res.code === "1") {
            let { qpcodeList = [] } = res.data;
            if (qpcodeList) {
              this.list = qpcodeList || [];
              this.businessList = this.list.map(item => ({
                text: item.businessName,
                value: item.businessCode
              }));
            }
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {}
      );
    },
    agentQuery() {
      /**requestType
       * 01:查询代理人协议信息
       * 02:查询业务风险分类 （N码）
       * 03:查询代理人协议信息（模糊查询）
       */
      // let params = {
      //   requestType: "02",
      //   agentCode: "8000189135" || this.agent.agentCode,
      //   comCode: "01",
      //   businessNature: "01",
      //   riskCode: "01"
      // };
      let userInfo = this.localStorage.get("userInfo", {});
      //1 不代理 2 代理
      let requestType = this.channel.isProxy == "0" ? "02" : "01";
      let agentCode =
        this.channel.isProxy == "0"
          ? userInfo.userCode
          : this.channel.agentCode;
      let businessSource = this.channel.businessNatureCode;
      if (!agentCode || !businessSource) {
        return;
      }
      let params = {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        requestType,
        riskCode: this.riskCode,
        businessSource,
        agentCode,
        agreementNo: "", //this.channel.agentAgreementCode,
        agreementName: "" //this.channel.agentAgreementName
      };
      agentQuery(params).then(
        res => {
          if (res.code === "1" && res.data) {
            let { salesMainMsgDto = {} } = res.data;
            this.requestData = salesMainMsgDto;
            let {
              salesAgentMsgDto = {},
              salesAgreementMsgList = []
            } = salesMainMsgDto;
            let {
              salesShadowAgentMsgList = [],
              comCode,
              businessNature,
              agreementNo,
              agreementName
            } = salesAgentMsgDto;
            let agreementIsVaild = false;
            let shadowAgentIsVaild = false;
            const userInfo = JSON.parse(localStorage.getItem("userInfo"));
            salesShadowAgentMsgList = salesShadowAgentMsgList || [];

            salesShadowAgentMsgList.push({
              shadowAgentType: "0101",
              shadowAgentCode: userInfo.handlerCode,
              shadowAgentName: userInfo.handlerName
            });
            //增加代理人数据到业务风险分类枚举值里面
            salesShadowAgentMsgList.push({
              shadowAgentType: salesAgentMsgDto.businessNature || "0101",
              shadowAgentCode: salesAgentMsgDto.agentCode,
              shadowAgentName: salesAgentMsgDto.agentName
            });
            salesShadowAgentMsgList = _.uniqWith(
              salesShadowAgentMsgList,
              _.isEqual
            );
            if (salesShadowAgentMsgList.length) {
              salesShadowAgentMsgList.forEach(item => {
                if (item.shadowAgentCode == this.channel.handler2Code) {
                  shadowAgentIsVaild = true;
                  this.channel.handler2Name = item.shadowAgentName;
                  this.channel.businessNature2Code = item.shadowAgentType;
                  this.channel.businessNature2Name = item.shadowAgentName;
                }
                item.text = item.shadowAgentCode + "-" + item.shadowAgentName;
                item.value = item.shadowAgentCode;
              });
            }
            this.salesShadowAgentMsgList = [...salesShadowAgentMsgList];
            if (!shadowAgentIsVaild) {
              this.channel.handler2Code = userInfo.handlerCode;
              this.channel.handler2Name = userInfo.handlerName;
              this.channel.businessNature2Code = "0101";
              this.channel.businessNature2Name = userInfo.handlerName;
              shadowAgentIsVaild = true;
            }

            if (Array.isArray(salesAgreementMsgList)) {
              salesAgreementMsgList.forEach(item => {
                if (item.agreementNo == this.channel.agentAgreementCode) {
                  agreementIsVaild = true;
                }
                item.text = item.agreementName;
                item.value = item.agreementNo;
              });
              this.salesAgreementMsgList = [...salesAgreementMsgList];
            }
            //console.log(this.salesAgreementMsgList);
            this.comCode = comCode; //归属机构
            this.channel.businessNatureCode =
              businessNature || this.channel.businessNatureCode; //业务来源
            let businessNatures = Object.keys(Config.allbusinessNature).map(
              key => ({
                text: Config.allbusinessNature[key],
                value: key
              })
            );
            this.channel.businessNatureName = this.$utils.findText(
              [...this.businessNatures, ...businessNatures],
              this.channel.businessNatureCode
            );
            if (!agreementIsVaild) {
              if (this.salesAgreementMsgList.length) {
                this.channel.agentAgreementCode = this.salesAgreementMsgList[0].agreementNo;
                this.channel.agentAgreementName = this.salesAgreementMsgList[0].agreementName;
              } else {
                this.channel.agentAgreementCode = agreementNo; //代理人协议号
                this.channel.agentAgreementName = agreementName; //代理人协议名称
              }
            }

            let channel = { ...salesAgentMsgDto };
            for (const key in this.presetSalesInfo) {
              if (this.presetSalesInfo.hasOwnProperty(key)) {
                channel[key] = salesAgentMsgDto[key] || "";
                if (
                  salesAgentMsgDto.hasOwnProperty(key) &&
                  salesAgentMsgDto[key]
                ) {
                  this.presetSalesInfo[key] = true;
                } else {
                  this.presetSalesInfo[key] = false;
                }
                //陕西个代
                if (this.areaCode == "6101" && this.personalAgent) {
                  this.presetSalesInfo[key] = true;
                }
                if (key == "salesName") {
                  if (this.salesNameFromHandlerName) {
                    channel.salesName = userInfo.handlerName;
                  } else if (this.personalAgent) {
                    channel.salesName = channel.agentName;
                  }
                }
              }
            }
            if (
              salesAgentMsgDto.hasOwnProperty("orgCode") &&
              salesAgentMsgDto.orgCode
            ) {
              channel.agencyOrgCode = salesAgentMsgDto.orgCode;
            }
            if (
              this.areaCode == "6101" ||
              this.areaCode == "3501" ||
              this.areaCode == "4401" ||
              (channel.isProxy == "1" && this.salesInfoArea)
            ) {
              channel.salesCodeName = salesAgentMsgDto.comName;
              channel.mobile = salesAgentMsgDto.phoneNumber;
            }
            // else {
            //   channel.agencyOrgCode = "";
            // }
            delete channel.salesShadowAgentMsgList;

            this.setChannel(channel);
          } else {
            if (this.channel.isProxy == "1") {
              const agentName = this.channel.agentName || "";
              const hintName = agentName ? `【${agentName}】` : "";
              const hint = `<p style='color:#F00'>代理人${hintName}不可用，请检查代理人状态或者更换其他代理人</p>\n`;

              this.$dialog.alert({ message: hint + res.msg });
            } else {
              let salesShadowAgentMsgList = [];
              salesShadowAgentMsgList.push({
                shadowAgentType: "0101",
                shadowAgentCode: userInfo.handlerCode,
                shadowAgentName: userInfo.handlerName,
                text: userInfo.handlerName,
                value: userInfo.handlerCode
              });
              this.salesShadowAgentMsgList = [...salesShadowAgentMsgList];
              this.channel.handler2Code = userInfo.handlerCode;
              this.channel.handler2Name = userInfo.handlerName;
              this.channel.businessNature2Code = "0101";
              this.channel.businessNature2Name = userInfo.handlerName;
            }
          }
        },
        error => {}
      );
    },
    saveChannel() {
      //NOTE: 校验? suncao 修改userCode
      if (!this.validate()) {
        return;
      }
      let channel = Object.assign({}, this.channel, {
        comCode: this.localStorage.get("userInfo", {}).comCode,
        userCode: this.localStorage.get("userInfo", {}).userCode
      });

      // let confirm = this.confirm || (() => {});
      // if (this.isDefaultChecked) {
      //   setDefaultChannel(channel).then(
      //     res => {
      //       if (res.code === "1") {
      //         this.$root.$emit("channelChange", Object.assign({}, channel));
      //         this.saveDate();
      //         // confirm(channel);
      //         this.$router.back();
      //         this.initChannel({});
      //       } else {
      //         this.$dialog.alert({ message: res.msg });
      //       }
      //     },
      //     error => {}
      //   );
      // } else {
      //   this.$root.$emit("channelChange", Object.assign({}, channel));
      //   // confirm(channel);
      //   this.saveDate();
      //   this.$router.back();
      //   this.initChannel({});
      // }
    },
    validate() {
      const props = {
        isProxy: { required: "是否代理" }
      };
      //陕西
      if (this.areaCode == "6101") {
        props.salesCodeName = { required: "销售机构名称" };
        props.mobile = { required: "联系电话", phoneormobile: "不符合规则" };
        props.salesName = { required: "销售人员姓名" };
        if (this.personalAgent) {
          props.exhibitionNo = { required: "执业证书编号" };
        } else {
          props.permitNo = { required: "许可证号" };
        }
      }
      // 广东
      if (this.areaCode == "4401") {
        props.mobile = { phoneormobile: "联系电话不符合规则" };
      }
      //青海 内蒙古
      if (this.areaCode == "1501" || this.areaCode == "6301") {
        props.operatorPhone = { required: "归属经办人电话" };
      }
      if (this.channel.isProxy == "0") {
        //不代理
      } else if (this.channel.isProxy == "1") {
        //代理
        props.agentCode = { required: "代理人" };
        props.agentAgreementCode = { required: "代理人协议" };
        if (this.salesInfoArea && this.salesInfoRequired) {
          props.salesName = { required: "销售人员姓名" };
          if (this.personalAgent) {
            props.exhibitionNo = { required: "执业证书编号" };
          } else {
            props.permitNo = { required: "许可证号" };
          }
        }
        //深圳
        if (this.areaCode == "4403") {
          props.salesIdNo = { required: "销售人员身份证号" };
        }
        //江苏、浙江
        let code =
          this.channel.businessNature || this.channel.businessNatureCode;
        if (
          (this.areaCode == "3201" || this.areaCode == "3301") &&
          code != "2401"
        ) {
          props.agencyOrgCode = {
            required: "中介机构代码",
            agencyOrgCode: "注意倒数第二位一定是“-”"
          };
        }
      }
      //福建
      if (this.areaCode == "3501") {
        props.salesName = { required: "销售人员姓名" };
        if (this.thirdPartyRequired) {
          props.thirdPartyHandlerName = { required: "第三方业务员姓名" };
          props.thirdPartyHandlerNo = { required: "第三方业务员工号" };
        }
      }
      //某些特殊字段清空值
      // //销售机构名称
      // if (!props.hasOwnProperty("salesCodeName")) {
      //   this.channel.salesCodeName = "";
      // }
      //销售人员姓名
      if (!props.hasOwnProperty("salesName")) {
        this.channel.salesName = "";
      }
      // //联系电话
      // if (!props.hasOwnProperty("mobile")) {
      //   this.channel.mobile = "";
      // }
      // //执业证书编号
      // if (!props.hasOwnProperty("exhibitionNo")) {
      //   this.channel.exhibitionNo = "";
      // }
      // //许可证号
      // if (!props.hasOwnProperty("permitNo")) {
      //   this.channel.permitNo = "";
      // }
      // //中介机构代码
      // if (!props.hasOwnProperty("agencyOrgCode")) {
      //   this.channel.agencyOrgCode = "";
      // }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(props[prop], this.channel[prop]);
          if (!status) {
            console.error(prop, status, msg);
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(error);
        return false;
      }
      return true;
    }
  }
};
</script>
<style lang="less" scoped>
.content {
  width: 100%;
  height: calc(100% - 77px - 80px);
  overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
    flex-direction: row;
  }
  .group {
    flex-direction: column;
    background: #f7f7f7;
    padding-bottom: 10px;
  }
  .item {
    min-height: 48px;
    align-items: center;
    /deep/.van-cell__value {
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
      .item-right-button {
        align-items: center;
        pointer-events: auto;
      }
      .item-right-button-text {
        color: #3b8afe;
        font-size: 14px;
        margin: 0 8px;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
      .item-right-long {
        word-break: break-all;
        text-align: left;
      }
    }
  }
  .bottom-container {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 80px;
  }
}

/deep/.van-picker-column__item {
  white-space: normal;
  text-overflow: initial;
  height: 44px;
  line-height: initial;
  overflow: visible;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
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
