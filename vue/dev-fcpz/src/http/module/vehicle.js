/*
 * @Author: King
 * @Page: 车险投保API
 * @since: 2020-03-02 16:00:11
 * @Last Modified by: King
 * @Last Modified time: 2020-06-28 16:04:02
 * @FilePath: /src/http/module/vehicle.js
 */

import { post } from '../axios'

/***************************** 车辆 *****************************/
/**
 * 获取默认争议解决方式
 * @param {*} params
 */
export const getArgueSolution = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/getArgueSolution',
    params,
    {
      vehicle: true,
    }
  )
/**
 * 根据车架号获取车辆信息
 * @param {*} params
 */
export const carInfoByFrameNo = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/searchByFrameNo',
    params,
    {
      vehicle: true,
    }
  )
/**
 * 根据车架号获取车辆信息
 * @param {*} params
 */
export const carInfoByLicenseNo = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/searchByLicenseNo',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取暂存订单中车辆信息
 * @param {*} params
 */
export const carInfoByOrderId = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/getVehicleInfoByOrderId',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 根据车型页面提供的信息生成订单
 * @param {*} params
 */
export const saveToOrder = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/saveTemporaryOrder',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 计算车辆实际价值
 * @param {*} params
 */
export const calculateActualValue = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicle/calculateActualValue',
    params,
    {
      vehicle: true,
    }
  )

/***************************** 渠道 *****************************/
/**
 * 渠道信息查询
 * @param {*} params
 */
export const getPQCode = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/channel/getPQCode', params, {
    vehicle: true,
  })

/**
 * 查询代理协议信息
 * @param {*} params
 */
export const agentQuery = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/channel/agentQuery', params, {
    vehicle: true,
  })

/**
 * 设置默认渠道信息
 * @param {*} params
 */
// export const setDefaultChannel = (params) =>
//   post(
//     (process.env.VUE_APP_CSV_URL || '') + '/channel/setDefautChannel',
//     params,
//     {
//       vehicle: true,
//     }
//   )

/**
 * 获取默认渠道信息
 * @param {*} params
 */
// export const getDefaultChannel = (params) =>
//   post(
//     (process.env.VUE_APP_CSV_URL || '') + '/channel/getDefautChannel',
//     params,
//     {
//       vehicle: true,
//     }
//   )

/***************************** 代理人 *****************************/
/**
 * 查询代理人列表
 * @param {*} params
 */
export const agentQueryList = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/commonAgent/searchAgentList',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 设置默认代理人
 * @param {*} params
 */
export const setDefaultAgent = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/commonAgent/defaultAgent',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取默认代理人
 * @param {*} params
 */
export const getDefaultAgent = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/commonAgent/getDefaultAgent',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 添加/编辑常用代理人
 * @param {*} params
 */
export const addAgent = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/commonAgent/addAgent', params, {
    vehicle: true,
  })

/**
 * 删除代理人
 * @param {*} params
 */
export const deleteAgent = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/commonAgent/deleteAgent',
    params,
    {
      vehicle: true,
    }
  )

/***************************** 车型 *****************************/
/**
 * 车型查询
 * @param {*} params
 */
export const getCarModel = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicleModel/getCarModel',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 车型校验
 * @param {*} params
 */
export const checkCarModel = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/vehicleModel/checkCarModel',
    params,
    {
      vehicle: true,
    }
  )

/***************************** 联合销售 *****************************/
/**
 * 校验联合销售
 * @param {*} params
 */
export const checkAccidentalEstateInsurance = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') +
      '/insurance/checkAccidentalEstateInsurance',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取联合销售列表
 * @param {*} params
 */
export const getAccidentalEstateInsurance = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') +
      '/insurance/getAccidentalEstateInsurance',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取联合销售详情
 * @param {*} params
 */

export const getAccidentalEstateInsuranceDetail = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') +
      '/insurance/getAccidentalEstateInsuranceDetail',
    params,
    {
      vehicle: true,
    }
  )

/***************************** 报价 *****************************/
/**
 * 获取报价信息
 * @param {*} params
 */
export const getQuoteInfo = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/quote/getInfo', params, {
    vehicle: true,
  })

/**
 * 获取合并打印设置
 * @param {*} params h
 */
export const getMergePrint = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/quote/getMergePrintSwitch',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取商业险列表
 * @deprecated
 * @param {*} params
 */
export const getCommercialList = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/quote/getCommercial', params, {
    vehicle: true,
  })

/**
 * 保存报价信息
 * @param {*} params
 */
export const saveQuote = (params) => {
  return post((process.env.VUE_APP_CSV_URL || '') + '/quote/saveInfo', params, {
    vehicle: true,
  })
}

/**
 * 分享码
 * @param {*} params
 */
export const getShareCode = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/order/getShareCode', params, {
    vehicle: true,
  })

/**
 * 商业计划书分享码
 * @param {*} params
 */
export const getBizPlanShareCode = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/plan/getBizPlanShareCode',
    params,
    {
      vehicle: true,
    }
  )
/**
 * 获取订单存储的报价信息
 * @deprecated
 * @param {*} params
 */
export const quoteOrder = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + `/quote/order/${params.orderId}`,
    params,
    {
      vehicle: true,
    }
  )

/**
 * 精确报价
 * @param {*} params
 */
export const preciseQuotation = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/quote/preciseQuotation',
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取报价活动奖励接口
 * @param {*} params
 */
export const getAwardInfo = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/quote/getAwardInfo', params, {
    vehicle: true,
  })
/**
 * 获取报价页面配置信息
 * @param {*} params
 */
export const quoteConfig = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + `/quote/config/${params.comCode}`,
    params,
    {
      vehicle: true,
    }
  )

/**
 * 获取投被保人信息
 * @param {*} params
 */
export const getRelationPeople = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/user/getByOrderId', params, {
    vehicle: true,
  })

/**
 * 更新投被保人信息
 * @param {*} params
 */
export const updateRelationPeople = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/proposal/insuredInfo', params, {
    vehicle: true,
  })
/*
 * 星级权益接口
 * @param {*} params
 */
export const getOrderIntegral = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + `/customer/getOrderIntegral`,
    params,
    {
      vehicle: true,
    }
  )
/*
 * 获取特约列表
 * @param {*} params
 */
export const getEngages = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + `/engage/getChannelEngages`,
    params,
    {
      vehicle: true,
    }
  )

/*
 * 提交订单接口
 * @param {*} params
 */
export const submitOrder = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + `/proposal/submitOrder`, params, {
    vehicle: true,
  })

/*
 * 提交核保接口
 * @param {*} params
 */
export const submitAudit = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + `/proposal/submitUnderwrite`,
    params,
    {
      vehicle: true,
    }
  )

export const getPayNo = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/payment/getPayNo', params, {
    vehicle: true,
  })
/**
 * 获取支付页
 * @param {*} params
 * flag  1:二维码支付    2:直接进快钱 3:兴业银行支付宝 4:兴业银行微信
 *       5:直接访问支付宝手机网页支付 6:银行卡快捷支付 7:微信支付
 *       8:招行扫码支付  9:POS支付    其它：集中收款主页
 * {orderId:订单id,userCode:业务员编号,flag}
 */
export const getPaymentUrl = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/payment/getPaymentUrl', params, {
    vehicle: true,
  })

/**
 * 转发支付
 * @param {*} params
 */
export const getPaymentRelayUrl = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/payment/getPaymentRelayUrl',
    params,
    {
      vehicle: true,
    }
  )

export const getBizPlan = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/plan/getBizPlan', params, {
    vehicle: true,
  })

export const getShareBizPlan = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/plan/shareBizPlan', params, {
    vehicle: true,
  })
/**
 * 获取分享信息
 * @param {*} params
 */
export const getShareInfo = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/share/info', params, {
    vehicle: true,
  })

/**
 * 获取车船税配置
 * @param {*} params
 */
export const getTaxConfig = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/quote/getTaxConfig', params, {
    vehicle: true,
  })

/**
 * 获取特约信息
 * @param {*} params
 */
export const getEngageList = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/engage/getEngageList', params, {
    vehicle: true,
  })

/**
 * 发送短信验证码
 * @param {*} params
 */
export const sendVerifyCode = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/verifyCode/send', params, {
    vehicle: true,
  })

/**
 * 是否显示投保验证码
 * @param {*} params
 */
export const getCheckNoSwitch = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/dict/getCheckNoSwitch', params, {
    vehicle: true,
  })

/**
 * Q码维护列表
 * @param {*} params
 */
export const getPQList = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/channel/getPQList', params, {
    vehicle: true,
  })

/**
 * Q码修改
 * @param {*} params
 */
export const setPQShow = (params) =>
  post((process.env.VUE_APP_CSV_URL || '') + '/channel/setPQShow', params, {
    vehicle: true,
  })

/**
 * 实名认证处理
 * @param {*} params
 */
export const getPeopleSwitch = (params) =>
  post(
    (process.env.VUE_APP_CSV_URL || '') + '/proposal/getPeopleSwitch',
    params,
    {
      vehicle: true,
    }
  );

/**
 * 代理人信息查询
 * @param {*} params
 */
export const searchAgentInfo = params =>
  post(
    (process.env.VUE_APP_CSV_URL || "") + "/commonAgent/searchAgentInfo",
    params,
    {
    vehicle: true
    }
  );

/**
 * 代理人信息查询
 * @param {*} params
 */
export const sendMessage = params =>
  post(
    (process.env.VUE_APP_CSV_URL || "") + "/msg/send",
    params,
    {
      vehicle: true
    }
  );
