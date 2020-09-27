package com.ccic.salesapp.noncar.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.config.SystemConfig;
import com.ccic.salesapp.noncar.dto.EnPolicyListQueryEntity;
import com.ccic.salesapp.noncar.dto.OrderInfoVO;
import com.ccic.salesapp.noncar.dto.OrderListEntity;
import com.ccic.salesapp.noncar.dto.request.OrderListQueryRequestVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.OrderListQueryResponseVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.GeneralConfigMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PrpcmainRenewalDAO;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.InsuranceApplicationDAO;
import com.ccic.salesapp.noncar.service.ConfigService;
import com.ccic.salesapp.noncar.service.OrderListQueryFacade;
import com.ccic.salesapp.noncar.utils.Page;
import com.ccic.salessapp.common.request.LoginUser;
import com.ccic.salessapp.common.utils.ArithUtil;
import com.ccic.salessapp.common.utils.DESEncryptUtil;

/**
 * 订单列表查询
 * 
 * @author qzq
 *
 */
public class NewOrderListQueryFacadeImpl implements OrderListQueryFacade {

	/*
	 * @Autowired InsuranceApplicationDAO insuranceApplicationDAO;
	 * 
	 * @Autowired PrpcmainRenewalDAO prpcmainRenewalDAO;
	 * 
	 * @Autowired GeneralConfigMapper generalConfigMapper;
	 */

	/**
	 * 订单列表
	 * 
	 * @throws SQLException
	 * 
	 * @throws Exception
	 *//*
		 * @Override public JsonVO getOrderList(OrderListQueryRequestVO requestVO,
		 * PageVO pageVO,LoginUser loginUser) throws SQLException { JsonVO jsonVO = new
		 * JsonVO(); ResultVO resultVO = new ResultVO(); OrderListQueryResponseVO
		 * responseVO = new OrderListQueryResponseVO(); List<OrderInfoVO> orderInfoList
		 * = new ArrayList<OrderInfoVO>(); String staffCode =
		 * loginUser.getUserCode();//经纪人工号 String startDate =
		 * requestVO.getStartDate();//报价起期 String endDate =
		 * requestVO.getEndDate();//报价止期 String licenseNo =
		 * requestVO.getLicenseNo();//车牌号 String underWriteStatus =
		 * requestVO.getUnderWriteStatus();//核保状态 String quotationMode =
		 * requestVO.getQuotationMode();//报价模式 String applicantName =
		 * requestVO.getApplicantName();//投保人姓名 String currentDate =
		 * requestVO.getCurrentDate();//查询月份 String platformFlag =
		 * requestVO.getPlatformFlag();//平台标识 1-新华出单 String nclUserCode =
		 * requestVO.getNclUserCode();//新华用户标识 try { OrderListEntity orderListReq = new
		 * OrderListEntity(); if (staffCode != null&&!"".equals(staffCode)) {
		 * if(StringUtils.isNotBlank(loginUser.getChannelCode())){
		 * orderListReq.setNclUserCode(staffCode);
		 * if("1".equals(loginUser.getChannelCode())) {
		 * orderListReq.setPlatformFlag("1"); } }else{
		 * orderListReq.setOperatorEmpno(staffCode); } } if (startDate !=
		 * null&&!"".equals(startDate)) { orderListReq.setStartDate(startDate); } if
		 * (endDate != null&&!"".equals(endDate)) { orderListReq.setEndDate(endDate); }
		 * if (licenseNo != null&&!"".equals(licenseNo)) {
		 * orderListReq.setLicenseNo(licenseNo); } if (underWriteStatus !=
		 * null&&!"".equals(underWriteStatus)) {
		 * orderListReq.setStatus(underWriteStatus); } if (quotationMode !=
		 * null&&!"".equals(quotationMode)) { if("DDH".equals(quotationMode)){
		 * quotationMode="DDG"; }else if("DDK".equals(quotationMode)){
		 * quotationMode="DDC"; } orderListReq.setQuotationMode(quotationMode); } if
		 * (applicantName != null&&!"".equals(applicantName)) {
		 * orderListReq.setApplicantName(applicantName); } if(currentDate !=
		 * null&&!"".equals(currentDate)){ orderListReq.setCurrentDate(currentDate); }
		 * orderListReq.setMinStartDate(requestVO.getMinStartDate());
		 * orderListReq.setMaxStartDate(requestVO.getMaxStartDate());
		 * orderListReq.setPolicyNo(requestVO.getPolicyNo()); List<OrderListEntity>
		 * orderList = insuranceApplicationDAO.selectByOrderListQueryList(orderListReq);
		 * Map<String,String> paraMap = new HashMap<String,String>();
		 * paraMap.put("configType", "check_code_expiry_time"); long expiryTime =
		 * Long.parseLong(generalConfigMapper.getConfigValueNoCache(paraMap).toString())
		 * ; Date nowDate = new Date(); if (orderList!= null && orderList.size()>0) {
		 * SystemConfig cofig=new SystemConfig("system"); String desPassword =
		 * cofig.getResourceValue("desPassword");
		 * 
		 * ConfigService configFacade = new ConfigServiceImpl(); Map<String,String>
		 * yzmAreaFlagMap = new HashMap<String,String>();
		 * 
		 * for (OrderListEntity order : orderList) { OrderInfoVO orderInfoVO = new
		 * OrderInfoVO(); orderInfoVO.setId(order.getId());
		 * orderInfoVO.setOrderNo(order.getOrderNo());// 订单号
		 * orderInfoVO.setProposalNo(order.getProposalNo());// 投保单号
		 * orderInfoVO.setDesProposalNo(DESEncryptUtil.encrypt(order.getProposalNo(),
		 * desPassword));//des加密后的投保单号 orderInfoVO.setPolicyNo(order.getPolicyNo());//
		 * 保单号 orderInfoVO.setDesPolicyNo(DESEncryptUtil.encrypt(order.getPolicyNo(),
		 * desPassword)); orderInfoVO.setOperateDate(order.getOperateDate());// 报价日期
		 * orderInfoVO.setNewcoreFlag(order.getNewcoreFlag());//新核心出单标志
		 * if(order.getEffectDate()!=null &&
		 * order.getEffectDate().getTime()>nowDate.getTime()) {
		 * orderInfoVO.setPayFlag("1"); }else { orderInfoVO.setPayFlag("0"); } String
		 * businessNature = order.getBusinessNature();//业务来源 if
		 * (StringUtils.isNotBlank(businessNature)) { if
		 * ("DDK".equals(order.getRiskCode()) || "DDC".equals(order.getRiskCode())) {
		 * orderInfoVO.setQuotationType("1");//报价类型:0-直销；1-网销；2-个代；3-代理 }else
		 * if("2".equals(businessNature.substring(0, 1)) ||
		 * "8101".equals(businessNature)){ if ("P".equals(order.getAgentCode()))
		 * {//代理人代码 orderInfoVO.setQuotationType("2");//个代业务 }else{
		 * orderInfoVO.setQuotationType("3");//代理业务 } }else{
		 * orderInfoVO.setQuotationType("0");//直销业务 } } if
		 * (orderInfoVO.getQuotationType()==null||"".equals(orderInfoVO.getQuotationType
		 * ())) { orderInfoVO.setQuotationType("0");//0直销 }
		 * orderInfoVO.setLicenseNo(order.getLicenseNo());// 车牌号
		 * orderInfoVO.setPayApplyNo(order.getPayApplyNo());// 支付号
		 * orderInfoVO.setIdentityCode(order.getIdentityCode());// 支付验证码
		 * 
		 * double sumPrenium=0;//合计保费 if (order.getSumPremium()!=null) { sumPrenium =
		 * order.getSumPremium(); } if(order.getSumTax()!=null){ sumPrenium =
		 * ArithUtil.add(sumPrenium, order.getSumTax()); }
		 * 
		 * orderInfoVO.setUnderWriteStatus(order.getStatus());// 核保状态
		 * orderInfoVO.setRiskCode(order.getRiskCode());// 险种
		 * orderInfoVO.setJointSales(order.getJointSales());// 联合销售标识
		 * if("1".equals(orderInfoVO.getJointSales())){
		 * orderInfoVO.setEYZapplicationNo(order.getEYZapplicationNo());// 意外险投保单号
		 * orderInfoVO.setEYZpolicyNo(order.getEYZpolicyNo());// 意外险保单号 if
		 * (order.getEYZpremium()!=null) {
		 * orderInfoVO.setEYZpremium(order.getEYZpremium());// 意外险保费 sumPrenium =
		 * ArithUtil.add(sumPrenium, order.getEYZpremium()); }
		 * orderInfoVO.setYEJapplicationNo(order.getYEJapplicationNo());// 随车险投保单号
		 * orderInfoVO.setYEJpolicyNo(order.getYEJpolicyNo());// 随车险保单号
		 * if(order.getYEJpremium()!=null){
		 * orderInfoVO.setYEJpremium(order.getYEJpremium());// 随车险保费 sumPrenium =
		 * ArithUtil.add(sumPrenium, order.getYEJpremium()); }
		 * orderInfoVO.setWAEapplicationNo(order.getWAEapplicationNo());// 健康险投保单号
		 * orderInfoVO.setWAEpolicyNo(order.getWAEpolicyNo());// 健康险保单号
		 * if(order.getWAEpremium()!=null){
		 * orderInfoVO.setWAEpremium(order.getWAEpremium());// 健康险保费 sumPrenium =
		 * ArithUtil.add(sumPrenium, order.getWAEpremium()); } }
		 * orderInfoVO.setSumPremium(String.valueOf(sumPrenium));//合计保费
		 * if(StringUtils.isEmpty(order.getIsSuccess())||"0".equals(order.getIsSuccess()
		 * )){ orderInfoVO.setEYZpremium(0); orderInfoVO.setWAEpremium(0);
		 * orderInfoVO.setYEJpremium(0); orderInfoVO.setSumPremium(null); }
		 * orderInfoVO.setInsuredName(order.getInsuredName());// 被保险人姓名
		 * orderInfoVO.setCarOwner(order.getCarOwner());//车主名称 String subComCode =
		 * order.getComeCode().substring(0, 4); String yzmAreaFlag = null;
		 * if(StringUtils.isNotBlank(yzmAreaFlagMap.get(subComCode))) { yzmAreaFlag =
		 * yzmAreaFlagMap.get(subComCode); }else {
		 * 
		 * yzmAreaFlag = configFacade.yzmAreaFlag(subComCode,generalConfigMapper);
		 * yzmAreaFlag = StringUtils.isNotBlank(yzmAreaFlag)?yzmAreaFlag:"0";
		 * yzmAreaFlagMap.put(subComCode, yzmAreaFlag); }
		 * orderInfoVO.setYzmAreaFlag(yzmAreaFlag);//验证码标志 if ("1101".equals(subComCode)
		 * && ("3".equals(order.getAppliyInsuredNature())||"01".equals(order.
		 * getAppliyInsuredNature()) ||
		 * "3".equals(order.getInsuredNature())||"01".equals(order.getInsuredNature())))
		 * { if("1".equals(order.getMachinecodeStatus())){
		 * orderInfoVO.setFlag("1");//身份验证码成功标志 }else {
		 * orderInfoVO.setFlag("0");//身份验证码失败 } }else if(("4601".equals(subComCode) &&
		 * ("1".equals(yzmAreaFlag) || "2".equals(yzmAreaFlag))) &&
		 * "85".equals(order.getCarUseNatureCode()) &&
		 * ("3".equals(order.getCarOwnerNature())||"01".equals(order.getCarOwnerNature()
		 * )) &&
		 * ("A0".equals(order.getCarKindCode())||"11".equals(order.getCarKindCode())) &&
		 * Integer.parseInt(order.getSeatCount())<10 &&
		 * ((("3".equals(order.getAppliyInsuredNature())||"01".equals(order.
		 * getAppliyInsuredNature())) &&
		 * ((!"02".equals(order.getAppliyIdType())&&!"113".equals(order.getAppliyIdType(
		 * ))) || !"1".equals(order.getQuotationType()))) ||
		 * (("3".equals(order.getInsuredNature())||"01".equals(order.getInsuredNature())
		 * ) && ((!"02".equals(order.getInsuredIdType())&&!"113".equals(order.
		 * getInsuredIdType())) || !"1".equals(order.getQuotationType()))))){
		 * if("1".equals(order.getMachinecodeStatus()) &&
		 * order.getApplyMessageDate()!=null &&
		 * (order.getApplyMessageDate().getTime()+expiryTime*60*60*1000)>=new
		 * Date().getTime()){ orderInfoVO.setFlag("3");//身份验证码成功标志 }else {
		 * orderInfoVO.setFlag("4");//身份验证码失败 } }else if(("1".equals(yzmAreaFlag) ||
		 * "2".equals(yzmAreaFlag)) && "85".equals(order.getCarUseNatureCode()) &&
		 * ("3".equals(order.getCarOwnerNature())||"01".equals(order.getCarOwnerNature()
		 * )) &&
		 * ("A0".equals(order.getCarKindCode())||"11".equals(order.getCarKindCode())) &&
		 * Integer.parseInt(order.getSeatCount())<10 &&
		 * ((("3".equals(order.getAppliyInsuredNature())||"01".equals(order.
		 * getAppliyInsuredNature()))) ||
		 * (("3".equals(order.getInsuredNature())||"01".equals(order.getInsuredNature())
		 * )))){ if("1".equals(order.getMachinecodeStatus()) &&
		 * order.getApplyMessageDate()!=null &&
		 * (order.getApplyMessageDate().getTime()+expiryTime*60*60*1000)>=new
		 * Date().getTime()){ orderInfoVO.setFlag("3");//身份验证码成功标志 }else {
		 * orderInfoVO.setFlag("4");//身份验证码失败 } }else{ orderInfoVO.setFlag("2");//无需验证 }
		 * if(order.getCommisionamntTci() != null){
		 * orderInfoVO.setCommisionamntTci(order.getCommisionamntTci());//交强险佣金 新华 }
		 * if(order.getCommisionamntVci() != null){
		 * orderInfoVO.setCommisionamntVci(order.getCommisionamntVci());//商业险佣金 新华 }
		 * if(order.getManagementfeeTci() != null){
		 * orderInfoVO.setManagementfeeTci(order.getManagementfeeTci());//交强险手续费 新华 }
		 * if(order.getManagementfeeVci() != null){
		 * orderInfoVO.setManagementfeeVci(order.getManagementfeeVci());//商业险手续费 新华 }
		 * if(order.getPremiumTci() != null){
		 * orderInfoVO.setPremiumTci(order.getPremiumTci()); } if(order.getPremiumVci()
		 * != null){ orderInfoVO.setPremiumVci(order.getPremiumVci()); }
		 * orderInfoVO.setProjectCode(order.getProjectCode());//项目编码
		 * orderInfoVO.setHuataiPaymentStatus(order.getHuataiPaymentStatus());//华泰分期申请状态
		 * orderInfoVO.setHuataiPaymentStatusName(order.getHuataiPaymentStatusName());//
		 * 华泰分期申请状态 orderInfoList.add(orderInfoVO); }
		 * 
		 * 
		 * } //生效状态下增加查询非移动端出单保单 if("7".equals(underWriteStatus) &&
		 * !"1".equals(platformFlag)) { EnPolicyListQueryEntity
		 * enPolicyListQueryEntity=new EnPolicyListQueryEntity();
		 * enPolicyListQueryEntity.setStaffCode(staffCode);//保单归属经办人
		 * enPolicyListQueryEntity.setInsuranceType("999");//用来设置排序规则，无业务意义
		 * enPolicyListQueryEntity.setApplicantName(requestVO.getApplicantName());//投保人
		 * enPolicyListQueryEntity.setLicenseNo(requestVO.getLicenseNo());//车牌号
		 * enPolicyListQueryEntity.setPolicyNo(requestVO.getPolicyNo());//保单号
		 * enPolicyListQueryEntity.setMinPayStartDate(requestVO.getMinStartDate());//
		 * 起保日期下限
		 * enPolicyListQueryEntity.setMaxPayStartDate(requestVO.getMaxStartDate());//
		 * 起保日期上限 PageVO pageVo = new PageVO(); pageVo.setPage(1); pageVo.setSize(200);
		 * Page
		 * page=prpcmainRenewalDAO.selectByPolicyPerQueryPage(enPolicyListQueryEntity
		 * ,pageVo); List<EnPolicyListQueryEntity> policyList =
		 * (List<EnPolicyListQueryEntity>) page.getItems();
		 * 
		 * if(policyList != null && policyList.size() > 0){ List<OrderInfoVO> orderList1
		 * = getCustPolicy(policyList); //去除重复部分 for(int i=0;i<orderInfoList.size();i++)
		 * { for(int j=0;j<orderList1.size();j++) {
		 * if(orderList1.get(j).getPolicyNo().equals(orderInfoList.get(i).getPolicyNo())
		 * ) { orderList1.remove(j); break; } } } orderInfoList.addAll(orderList1); } }
		 * responseVO.setOrderList(orderInfoList); jsonVO.setData(responseVO);
		 * resultVO.setCode("1"); resultVO.setMessage("订单列表查询成功！");
		 * jsonVO.setResult(resultVO); } catch (Exception e) {
		 * //log.error("订单列表查询失败：系统异常！", e); resultVO.setCode("0");
		 * resultVO.setMessage("订单列表查询失败：系统异常！" + e.getMessage());
		 * jsonVO.setResult(resultVO); } return jsonVO; }
		 * 
		 * private List<OrderInfoVO> getCustPolicy(List<EnPolicyListQueryEntity>
		 * enPolicyList) throws Exception { List<OrderInfoVO> orderList = new
		 * ArrayList<OrderInfoVO>(); SystemConfig cofig=new SystemConfig("system");
		 * String desPassword = cofig.getResourceValue("desPassword"); for (int i = 0; i
		 * < enPolicyList.size(); i++) { OrderInfoVO orderInfoVO = new OrderInfoVO();
		 * EnPolicyListQueryEntity policyEntity = enPolicyList.get(i);
		 * orderInfoVO.setInsuredName(policyEntity.getInsuredName());
		 * orderInfoVO.setCarOwner(policyEntity.getInsuredName());//取被保人
		 * orderInfoVO.setPolicyNo(policyEntity.getPolicyNo());
		 * orderInfoVO.setSumPremium(policyEntity.getPremium());
		 * orderInfoVO.setLicenseNo(policyEntity.getLicenseNo());
		 * orderInfoVO.setUnderWriteStatus("7");// 状态
		 * orderInfoVO.setRiskCode(policyEntity.getRiskCode());// 险种
		 * orderInfoVO.setJointSales("0");// 联合销售标识
		 * orderInfoVO.setProposalNo(policyEntity.getProposalNo());//投保单号
		 * orderInfoVO.setDesPolicyNo(DESEncryptUtil.encrypt(policyEntity.getPolicyNo(),
		 * desPassword)); orderList.add(orderInfoVO); } return orderList; }
		 */

}
