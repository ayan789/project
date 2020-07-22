/*
 * @Author: King
 * @Page:
 * @Date: 2020-03-21 13:34:51
 * @Last Modified by: King
 * @Last Modified time: 2020-05-18 11:34:57
 * @FilePath: /src/store/modules/channel/channel.js
 */
const defaultState = {
  channel: {
    isProxy: "", //是否代理, 1代理 0不代理, 意义同上,冗余字段
    businessNatureCode: "", //业务来源  salesAgentMsgDto里边
    businessNatureName: "", //业务来源名称
    agentCode: "", //代理人代码
    agentName: "", //代理人名称
    agreementNo: "", //代理协议号
    agreementName: "", //代理协议名称
    agentAgreementCode: "", //代理协议号
    agentAgreementName: "", //代理协议名称
    businessNature2: "", //业务来源2
    businessNature2Name: "", //业务来源2名称
    handler2Code: "", //经办人2
    handler2Name: "", //经办人2名称
    businessCode: "", //业务编码
    businessName: "", //业务名称
    projectCode: "", //项目编码
    projectName: "", //项目名称
    thirdBusinessInfo: "", //第三方
    thirdPartyHandlerName: "", // 第三方业务员姓名
    thirdPartyHandlerNo: "", // 第三方业务员工号
    id: "", //主键id
    isDefault: "", //是否默认(1:是,0:否)
    mobile: "", //联系电话
    operator: "", //归属经办人
    operatorPhone: "", //归属经办人电话
    orgCode: "", //组织机构代码编号
    permitNo: "", //许可证号
    exhibitionNo: "", //执业证书编号
    salesCodeName: "", //销售机构名称
    salesIdNo: "", //销售人员身份证号
    salesName: "" //销售人员
  }
};

const getters = {};
const mutations = {
  initChannel(state, channel) {
    return (state.channel = Object.assign({}, Channel, channel));
  },
  setChannel(state, channel) {
    if (!channel) return;
    // utils.removeEmpty(channel)
    return (state.channel = Object.assign({}, state.channel, channel));
  }
};
const actions = {};

export default {
  namespaced: true,
  state: JSON.parse(JSON.stringify(defaultState)),
  getters,
  mutations,
  actions
};

export const Channel = {
  isProxy: "",
  businessNatureCode: "", //业务来源  salesAgentMsgDto里边
  businessNatureName: "", //业务来源名称
  agentCode: "", //代理人代码
  agentName: "", //代理人名称
  agreementNo: "", //代理协议号
  agreementName: "", //代理协议名称
  agentAgreementCode: "", //代理协议号
  agentAgreementName: "", //代理协议名称
  businessNature2: "", //业务来源2
  businessNature2Name: "", //业务来源2名称
  handler2Code: "", //经办人2
  handler2Name: "", //经办人2名称
  businessCode: "", //业务编码
  businessName: "", //业务名称
  projectCode: "", //项目编码
  projectName: "", //项目名称
  thirdBusinessInfo: "", //第三方
  thirdPartyHandlerName: "", // 第三方业务员姓名
  thirdPartyHandlerNo: "", // 第三方业务员工号
  id: "", //主键id
  isDefault: "", //是否默认(1:是,0:否)
  mobile: "", //联系电话
  operator: "", //归属经办人
  operatorPhone: "", //归属经办人电话
  orgCode: "", //组织机构代码编号
  permitNo: "", //许可证号
  exhibitionNo: "", //执业证书编号
  salesCodeName: "", //销售机构名称
  salesIdNo: "", //销售人员身份证号
  salesName: "" //销售人员
};
