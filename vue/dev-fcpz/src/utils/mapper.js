/**
 * Created by yuyang
 */

export default {
  custType: [
    { text: "个人客户", value: "1", typevalue: "GR" },
    { text: "团体客户", value: "2", typevalue: "TT" }
  ],

  cardType_person: [
    { text: "身份证", value: "01", typevalue: "GR" },
    { text: "户口本", value: "02", typevalue: "GR" },
    { text: "护照", value: "03", typevalue: "GR" },
    { text: "军官证", value: "04", typevalue: "GR" },
    { text: "外国人永久居留证", value: "16", typevalue: "GR" },
    { text: "港澳台居民居住证", value: "00", typevalue: null }
  ],

  cardType_business:[
    { text: "组织机构代码证", value: "01", typevalue: "GR" },
    { text: "三证合一代码", value: "02", typevalue: "GR" },
    { text: "其他", value: "03", typevalue: "GR" },
  ],

  addressType: [
    { text: "家庭地址", value: "01", typevalue: "GR" },
    { text: "公司地址", value: "02", typevalue: "GR" },
    { text: "其他", value: "03", typevalue: "GR" }
  ],

  unitNatureType: [
    { text: "国家机关", value: "01", typevalue: "GR" },
    { text: "党政机关", value: "02", typevalue: "GR" },
    { text: "社会团体", value: "03", typevalue: "GR" },
    { text: "基层群众自治组织", value: "04", typevalue: "GR" },
    { text: "事业单位", value: "05", typevalue: "GR" }
  ],

  myCustomerType: [
    {
      text: "客户筛选", value: 'policy_tag'
    },
    {
      text: "团体证件类型", value: 'certificate_type_business'
    },
    {
      text: "个人证件类型", value: 'certificate_type_person'
    }
  ],
  cancelRenewal: [
    { value: "RSA", label: "报废" },
    { value: "RSB", label: "车辆办理转籍到外省" },
    { value: "RSC", label: "车辆办理停驶" },
    { value: "RSD", label: "保费太贵" },
    { value: "RSE", label: "人为脱保" },
    { value: "RSJ", label: "暂时不开" },
    { value: "RSG", label: "追踪不及时脱保" },
    { value: "RSH", label: "过户联系不上脱保" },
    { value: "RSI", label: "保其他公司" },
    { value: "RSK", label: "其他原因" }
  ]
};
