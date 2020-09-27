package com.ccic.salesapp.noncar.service.impl;

import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.controller.PlanFormCtl;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.ForwardPayUrl;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.order.ChildProduct;
import com.ccic.salesapp.noncar.dto.order.Clause;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.order.response.ElementGroup;
import com.ccic.salesapp.noncar.dto.order.response.GroupOrderDetail;
import com.ccic.salesapp.noncar.dto.order.response.GroupOrderResponse;
import com.ccic.salesapp.noncar.dto.order.response.OrderDetailResponse;
import com.ccic.salesapp.noncar.dto.order.response.OrderResponseUrl;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.IPreciseQuoteRequestVO;
import com.ccic.salesapp.noncar.dto.request.JointSaleVO;
import com.ccic.salesapp.noncar.dto.request.PrivyVO;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.ClaimStatement;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.ImgList;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanFormConstant;
import com.ccic.salesapp.noncar.dto.request.planelement.ProductInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.SafeguardDetail;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditionsContent;
import com.ccic.salesapp.noncar.dto.response.GetPayUrlResponseVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVOTwo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupPlanVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlan;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlanInfoRoot;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlanRoot;
import com.ccic.salesapp.noncar.dto.salesplan.PlanChild;
import com.ccic.salesapp.noncar.dto.salesplan.PlanCoverage;
import com.ccic.salesapp.noncar.dto.salesplan.SaleInfo;
import com.ccic.salesapp.noncar.feign.PayResultFeignService;
import com.ccic.salesapp.noncar.feign.SupportFeignService;
import com.ccic.salesapp.noncar.feign.bean.ImageCheckResponse;
import com.ccic.salesapp.noncar.feign.bean.ImageDTO;
import com.ccic.salesapp.noncar.feign.bean.PayResultConfirmRequestVO;
import com.ccic.salesapp.noncar.feign.bean.ImageCheckResponse.ImageNodes;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderCtMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderDetailMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PolicyCustomerMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesCoverageMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanChildMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanConfigMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderDetail;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlan;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlanConfig;
import com.ccic.salesapp.noncar.service.GroupPlanService;
import com.ccic.salesapp.noncar.service.ImageService;
import com.ccic.salesapp.noncar.service.OrderDetailsInqueryFacade;
import com.ccic.salesapp.noncar.service.PaymentNoApplyFacade;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salessapp.common.config.WebContants;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.PropertiesUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.UnirestUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupPlanServiceIml implements GroupPlanService {
	
	@Autowired
	SalesPlanMapper salesPlanMapper;
	@Autowired
	SalesPlanChildMapper salesplanchildMapper;
	@Autowired
	SalesCoverageMapper salesCoverageMapper;
	@Autowired
	SalesPlanConfigMapper salesPlanConfigMapper; 
	
	@Value("${esb.address.policyListUrl}")
	String policyListUrl;
	
	@Override
	public void groupPlanSync(HttpServletRequest request,HttpServletResponse response) throws IOException {
			Map<String,Object> responseMap = new HashMap<String,Object>();
			Map<String,Object> responseHead = new HashMap<String,Object>();
			responseHead.put("consumerSeqNo", "");
			responseHead.put("status",0);
			responseHead.put("esbCode","ESB-000000");
			responseHead.put("esbMessage", "ESB服务成功");
			responseHead.put("appCode","0");
			responseHead.put("appMessage","数据同步成功");
			responseMap.put("responseHead", responseHead);
			PrintWriter out = null;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			InputStreamReader isr = null;
			BufferedReader inBufReader = null;
			String result = "";
			try {
			isr = new InputStreamReader(request.getInputStream(), "UTF-8");
			inBufReader =new BufferedReader(isr);
			String str = null;  
			StringBuffer sBuffer = new StringBuffer();  
			while ((str = inBufReader.readLine()) != null) {  
				sBuffer.append(str);
			}		    
			String reqStr = URLDecoder.decode(sBuffer.toString(),"UTF-8");//请求信息
			log.info("推送报文:"+reqStr);
		List<GroupSalesPlan> groupSalesPlanList = (List<GroupSalesPlan>) JSONArray.parseArray(reqStr, GroupSalesPlan.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("groupPlanCode", groupSalesPlanList.get(0).getGroupPlanCode());
		salesPlanMapper.deleteBySalesCoverage(map);
		salesPlanMapper.deleteBySalesPlanChild(map);
		salesPlanMapper.deleteBySalesPlan(map);
		salesPlanMapper.deleteBySalesPlanConfig(map);
		for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
			buildcomAndChennelConfig(groupSalesPlan);
			salesPlanMapper.insert2(groupSalesPlan);
			if(groupSalesPlan.getSalesChildPlans()!=null) {
				for(PlanChild planChild:groupSalesPlan.getSalesChildPlans()) {
					salesplanchildMapper.insert2(planChild);
					for(PlanCoverage planCoverage:planChild.getSalesChildPlanCts()) {
						salesCoverageMapper.insert2(planCoverage);
					}
				}
			}
		}
		response.setContentType("application/json; charset=UTF-8");
		result = "{\"responseHead\":{\"consumerSeqNo\":\"supsale\",\"status\":0,\"seqNo\":\"\",\"providerSeqNo\":\"\",\"esbCode\":\"ESB服务成功\",\"esbMessage\":\"ESB服务成功\",\"appCode\":\"0\",\"appMessage\":\"数据同步成功\"}}";
		log.info("响应报文:"+result);
        out = response.getWriter();
	    out.print(result);
		if(out!=null){
			out.close();
		}
		if(inBufReader!=null){
			inBufReader.close();
		}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Autowired
	PolicyCustomerMapper policyCustomerMapper;
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public HttpResult groupPlanDetail(GroupPlanVo groupPlanVo,HttpServletRequest request) {
		groupPlanVo.setGroupPlanCode("XYZ2050210");
		//HttpServletRequest request = null;
		GroupSalesPlanRoot groupSalesPlanRoot = new GroupSalesPlanRoot();
		SaleInfo saleInfo = new SaleInfo();
		saleInfo.setGiCoveragePeriod("1");
		saleInfo.setGiPeriodType("1");
		HashMap<String, Object> map_sale = new HashMap<String, Object>();
		map_sale.put("planCode", groupPlanVo.getGroupPlanCode());
		int saleNum = orderMapper.selectSaleNum(map_sale);
		saleInfo.setSaleNum(saleNum+"");
		UserToken user = planStrategyService.getAccessTokenByUserCode(groupPlanVo.getUserCode());
		groupPlanVo.setChannelCode(user.getChannelCode());
		groupPlanVo.setComCode(user.getComCode());
		List<GroupSalesPlan> groupSalesPlanList = salesPlanMapper.selectSalesPlanBygroupPlanCode(groupPlanVo);
//		if(groupSalesPlanList==null || groupSalesPlanList.size()==0)
//			return HttpResult.error( 2, "您目前无权限查看");
		request.getHeader("X-Access-Token");
		String token = request.getHeader("X-Access-Token");
		groupSalesPlanRoot.setToken(token.replaceAll("\"", ""));
		groupSalesPlanRoot.setGroupSalesPlan(groupSalesPlanList);
		for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
			List<PlanChild> planChildList = new ArrayList<PlanChild>();
			planChildList = salesplanchildMapper.selectByPrimaryKey2(groupSalesPlan.getSalesPlanId());
			if(groupPlanVo.getOrderId()!=null) {
				Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
				if(order.getStrategyCode()!=null && groupSalesPlan != null && groupSalesPlan.getSalesPlanCode()!=null && groupSalesPlan.getSalesPlanCode().equals(order.getStrategyCode())) {
					groupSalesPlan.setIsDefault("1");
				}
				List<HashMap<String,BigDecimal>> map_prem_list = orderMapper.selectPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
				for(PlanChild pc : planChildList) {
					//详情与计划书一致
					//pc.setTermsAndConditions(buildTermsAndConditions(groupSalesPlan));
					//pc.setIsNeedHealthNotice(true);
					//pc.setHealthNotice(buildHealthNotice(groupSalesPlan));
					for(HashMap<String,BigDecimal> m : map_prem_list) {
						if((m.get("childPlanCode")+"").equals(pc.getChildPlanCode())) {
							pc.setIsDefault("1");
						}
					}
				}
			}
			groupSalesPlan.setSalesChildPlans(planChildList);
			for(PlanChild planChild:planChildList) {
				List<PlanCoverage> planCoverageList = salesCoverageMapper.selectByChildPlanId(planChild.getChildPlanId());
				planChild.setSalesChildPlanCts(planCoverageList);
			}
		}
		groupSalesPlanRoot.setSaleInfo(saleInfo);
		if(groupPlanVo.getOrderId()!=null) {
			String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
			//if("plan".equals(dataTag)) {
				groupSalesPlanRoot.setUserInfo(user);
			//}
			GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
			Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
			groupOrderDetail.setOrder(order);
			
			if("1".equals(order.getIsAgent())) {
				AgentInfoVO agentInfo = new AgentInfoVO();
				agentInfo.setAgentCode(order.getAgentCode());
				agentInfo.setAgentName(order.getAgentName());
				agentInfo.setAgreementNo(order.getAgreementNo());
				agentInfo.setAgreementName(order.getAgreementName());
				groupOrderDetail.setAgentInfo(agentInfo);
			}
			if(order.getStartDate()!=null) {
				Long t1 = order.getStartDate().getTime();
				Long t2 = new Date().getTime();
				groupOrderDetail.setIntervalDate(t1-t2);
			}
			com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo invoiceInfo = invoiceInfoMapper.selectInvoiceInfoByOrderId2(order.getId());
			if(invoiceInfo!=null)
			groupOrderDetail.setInvoiceInfo(invoiceInfo);
			List<Order> orderList = orderMapper.selectByRelationOrderId(Long.parseLong(groupPlanVo.getOrderId()));
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", Long.parseLong(groupPlanVo.getOrderId()));
			List<Customer> policyCustomerList = orderMapper.selectByOrderId(map);
			Map<String,BigDecimal> map_sumpre  = orderMapper.selectSumPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
			if(map_sumpre.get("duePremium")!=null)
			groupOrderDetail.setDuePremium(map_sumpre.get("duePremium").doubleValue());
			groupOrderDetail.setSalesPlanCode(order.getStrategyCode());
			groupOrderDetail.setDiscountPremium(Double.parseDouble(map_sumpre.get("discountPremium")+""));
			List<HashMap<String,BigDecimal>> map_prem_list = orderMapper.selectPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
			HashMap<String,Double> m1 = new HashMap<String, Double>();
			for(HashMap<String,BigDecimal> m : map_prem_list) {
				m1.put(m.get("key")+"",m.get("value").doubleValue());
			}
			groupOrderDetail.setPremiumMap(m1);
			groupOrderDetail.setCustomerList(policyCustomerList);
			List<ChildProduct> childProductList = new ArrayList<ChildProduct>();
			for(int i=0;i<orderList.size();i++) {
				ChildProduct childProduct = new ChildProduct();	
				childProduct.setPlanCode(orderList.get(i).getPlanCode());
				childProduct.setProductCode(orderList.get(i).getProductCode());
				childProduct.setProductName(orderList.get(i).getProductName());
				childProduct.setProductLine(orderList.get(i).getProductLine());
				List<Clause> clauseList = orderCtMapper.selectByOrderId2(orderList.get(i).getId());
				childProduct.setClauseList(clauseList);
				childProductList.add(childProduct);
			}
			groupOrderDetail.setChildProductList(childProductList);
			groupSalesPlanRoot.setGroupOrderDetail(groupOrderDetail);
			List<HashMap<String,Object>> maplist = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(groupPlanVo.getOrderId()));
			if(maplist!=null && maplist.size()>0) {
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				for(HashMap<String,Object> orderMapperMap : maplist) {
					Map<String,String> map_insure = new HashMap<String, String>();
					map_insure.put("planCode", orderMapperMap.get("planCode")+"");
					map_insure.put("planName", orderMapperMap.get("planName")+"");
					map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");
					map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");
					map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");
					list.add(map_insure);
				}
				groupSalesPlanRoot.getGroupOrderDetail().setInsureNos(list);
			}
		}
		return HttpResult.success(groupSalesPlanRoot,1,"OK");
	}
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	OrderDetailMapper orderDetailMapper;
	
	@Autowired
	OrderCtMapper orderCtMapper;
	
	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;
	
	@Autowired
	PlanStrategyMapper planStrategyMapper;
	
	@Autowired
	PlanStrategyService planStrategyService;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public HttpResult stagCopyInsure(OrderDetailRequest request) {
		Order order = orderMapper.selectByPrimaryKey(request.getOrderId());
		LinkedHashMap<String,List<OrderDetail>> groupListMap = new LinkedHashMap<String, List<OrderDetail>>();
		List<ElementGroup> groupList = new ArrayList<ElementGroup>();
		for (Iterator iterator = groupListMap.keySet().iterator(); iterator.hasNext();) {
			String  groupName = (String) iterator.next();
			ElementGroup group = new ElementGroup();
			group.setGroupName(groupName);
			group.setElementList(groupListMap.get(groupName));
			groupList.add(group);
		}
		OrderDetailResponse response = new OrderDetailResponse();
		BeanUtils.copyProperties(order, response);
		response.setElementList(groupList);
		List<OrderCt> productList=  orderCtMapper.selectByOrderId(request.getOrderId());
		response.setProductList(productList);
		if("1".equals(order.getIsAgent())) {
			AgentInfoVO agentInfo = new AgentInfoVO();
			agentInfo.setAgentCode(order.getAgentCode());
			//agentInfo.setAgentName(order.geta);
			agentInfo.setAgreementNo(order.getAgreementNo());
			agentInfo.setAgreementName(order.getAgreementNo());
			//agentInfo.setBusinessNature(order.get);
			response.setAgentInfo(agentInfo);
		}
		response.setBusinessNatureCode(order.getBusinessSourceCode());
		response.setBusinessNatureName("");
		response.setSumAmount(order.getSumAmount());
		response.setSumPremium(order.getSumPremium());
		response.setOrderNo(order.getOrderNo());
		response.setInsureNo(order.getInsureNo());
		SimpleDateFormat startF = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat endF = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		response.setStartDate(startF.format(order.getStartDate()));
		response.setEndDate(endF.format(order.getEndDate()));
		Long intervalDate = order.getStartDate().getTime() - new Date().getTime();
		if(intervalDate < 0 ) {intervalDate = 0l;}
		response.setIntervalDate(intervalDate); 
		com.ccic.salesapp.noncar.dto.InvoiceInfo invoiceInfo = invoiceInfoMapper.selectInvoiceInfoByOrderId(order.getId());
		if(invoiceInfo!=null)
		response.setInvoiceInfo(invoiceInfo);
		response.setOrder(order);
		response.setNotUploadImgList(getNotUploadImages(order.getId()));
		TermsAndConditions termsAndConditions = new TermsAndConditions() ;
		FormulaList formulaList = new FormulaList();
		planFormCtl.buildTermsAndConditionsContent(termsAndConditions, formulaList);
		response.setTermsAndConditions(termsAndConditions);
		response.setOnlinePlanNum(0);
		List<Long> onlinPlanList = null;
		try {
			onlinPlanList = planStrategyService.selectPlanIdsByStrategyCodeUserCode(order.getStrategyCode(),order.getOperatorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setOnlinePlanNum(onlinPlanList == null ? 0 : onlinPlanList.size());
		return HttpResult.success(response,1,"OK");
	}
	
	@Value("${spring.profiles.active}")
	String active;
	
	@Autowired
	SupportFeignService supportFeignService;
	
	/***
	 * 获取未上传的影像名称列表
	 * @param planStrategy
	 * @return
	 */
	public List<String> getNotUploadImages(Long orderId) {
		if("dev".equals(active.toLowerCase())) {
			return new ArrayList<String>();
		}
		Order order = orderMapper.selectByPrimaryKey(orderId);
		PlanStrategy planStrategy = planStrategyMapper.selectByPrimaryKey(order.getStrategyId().intValue());
		if(1 == planStrategy.getUploadPhoto()) {
    		//检查影像是否上传完整
			List<ImageNodes> checkImgs = planStrategyMapper.selectMustBeUploadImageTypesByStrategyId((long)planStrategy.getId());
			List<String> checkImageCodeList = new ArrayList<String>();
			ArrayList<String> notUploadImages = new ArrayList<String>();
			ArrayList<ImageNodes> uploadImages = new ArrayList<ImageNodes>();
			//有投保单号 再去影像系统获取已经上传影像
			if(StringUtils.isNotBlank(order.getInsureNo())) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImgType(planStrategy.getInsureType());
				SimpleDateFormat sf = new SimpleDateFormat("yyyy");
				imageDTO.setBusiDate(sf.format(new Date())+"0000");
				imageDTO.setClassCode(order.getProductCode());
				imageDTO.setComCode(order.getComCode());
				imageDTO.setBussNo(order.getInsureNo());
				log.info("影像检查请求参数："+JSONObject.toJSONString(imageDTO));
				HttpResult imgCheckResult =supportFeignService.imageCheck(imageDTO);
				log.info("影像检查响应结果："+JSONObject.toJSONString(imgCheckResult));
				ImageCheckResponse.ResponseBody checkbody = JSONObject.parseObject(JSONObject.toJSONString(imgCheckResult.getData()), ImageCheckResponse.ResponseBody.class) ;
				if(checkbody == null ) {
					return new ArrayList<String>();
				}
				if(checkbody.getImageNodes() != null) {
					uploadImages = checkbody.getImageNodes();
					for (ImageNodes imageNode : uploadImages) {
						checkImageCodeList.add(imageNode.getNodeId());
					}
				}
			}
			for (int i = 0; i < checkImgs.toArray().length; i++) {
				ImageNodes item = checkImgs.get(i);
				String code = item.getNodeId();
				String name = item.getNodeName();
				if(!checkImageCodeList.contains(code)) {
					notUploadImages.add(name);
				}
			}
    		return notUploadImages;
    	}else {
    		return new ArrayList<String>();
    	}
	}
	
	@Autowired
	PlanFormCtl planFormCtl;
	
	@Autowired
	ImageService signimpl;

	@Override
	public HttpResult signInit(ForwardPayInsureInfoReqVO forwardPayInsureInfo) {
		HttpResult result= HttpResult.success();
		NoncarOrder noncarOrder = stu.findNoncarOrderByInsureNo(forwardPayInsureInfo.getProposalNo());
		//条款代码
		OrderResponseUrl response = new OrderResponseUrl();
		TermsAndConditions termsAndConditions = new TermsAndConditions() ;
		FormulaList formulaList = new FormulaList();
		formulaList.setPlanId(noncarOrder.getPlanId());
		PlanElementDetaiReq planElementDetaiReq = new PlanElementDetaiReq();
		planElementDetaiReq.setStrategyId(noncarOrder.getStrategyId());
		planFormCtl.buildTermsAndConditionsContent(termsAndConditions, formulaList);
		response.setTermsAndConditions(termsAndConditions);
		//投保人手机号
		String mobile = noncarOrder.getIndiMobile();
		String isIssueAfterPay = noncarOrder.getIsIssueAfterPay();
		//健康告知
		PlanAttach planAttach = signimpl.findhealthNotification(noncarOrder.getPlanId());
		Map healthNotificationM = new HashMap();
		if(planAttach != null && StringUtils.isNotBlank(planAttach.getAttachCode())) {
			healthNotificationM.put("title", "健康告知");
			String attachUrl = attachUrl(planAttach.getAttachUrl());
			healthNotificationM.put("url", attachUrl);
		}
		response.setHealthNotification(healthNotificationM);
		String forwardDate = forwardPayInsureInfo.getForwardDate();
		String proposalNo = forwardPayInsureInfo.getProposalNo();
		if (StringUtils.isBlank(forwardDate) || StringUtils.isBlank(proposalNo)) {
			return HttpResult.error(0, "请求参数不正确");
		}
		System.out.println(System.currentTimeMillis());
		response.setMobile(mobile);
		response.setIsIssueAfterPay(isIssueAfterPay);
		result.setCode("1");
		result.setData(response);
		result.setMsg("SUCCESS");
		return result;
	}

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	
	@Autowired
	PaymentNoApplyFacade spl;
	
	@Override
	public HttpResult applyPayment(ApplyPayVo applyPayVo) {
		ResultVO result = new ResultVO();
		StoreOracleResponseVOTwo storeOracleResponseVO = new StoreOracleResponseVOTwo();
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectBySureInfoNoTwo(applyPayVo);// insureNo
		NoncarOrder saveApplyPayment = spl.saveApplyPaymentThree(noncarOrder, result);
		if ("1".equals(result.getCode())) {
			storeOracleResponseVO.setNoncarOrder(saveApplyPayment);
			HttpResult res = HttpResult.success(storeOracleResponseVO);
			res.setCode("1");
			res.setMsg("申请成功");
			return res;
		} else {
			return HttpResult.error(0, "申请支付号失败");
		}
	}
	
	@Value("${system.forwardPay}")
	String forwardPay;
	
	@Value("${system.mobileQuickPayUrl}")
	String mobileQuickPayUrl;
	
	@Value("${system.systemSourceCode}")
	String systemSourceCode;
	
	@Autowired
	StoreProductPlaceUtilService stu;

	@Override
	public HttpResult genePaymentLink(GetPayUrlRequestVO requestVO) {
		try {
			String proposalNo = requestVO.getProposalNo();
			String proposalNoOrgin = proposalNo;
			String paymentNo = requestVO.getPaymentNo();
			String checkNo = requestVO.getCheckNo();
			String flag = requestVO.getFlag();
			if(proposalNo==null || "".equals(proposalNo)){
				return HttpResult.error(0,"投保单号不能为空！");
			}
			if(paymentNo==null || "".equals(paymentNo)){
				return HttpResult.error(0,"支付号不能为空！");
			}
			if(checkNo==null || "".equals(checkNo)){
				return HttpResult.error(0,"验证码不能为空！");
			}
			String consumerID = null;
			consumerID="supsale";
			String desPayNo = DESEncryptUtil.encrypt(paymentNo,desPassword);
			String desCheckNo= DESEncryptUtil.encrypt(checkNo,desPassword);
			proposalNo = DESEncryptUtil.encrypt(proposalNo+"-"+"UNITE",desPassword);
			GetPayUrlResponseVO resVO = new GetPayUrlResponseVO();
			String payUrl = null;
			String basePayUrl = mobileQuickPayUrl+"?consumerID="+consumerID+"&systemSourceCode="+systemSourceCode+"&paymentNo="+desPayNo+"&checkNo="+desCheckNo+"&ext="+proposalNo;
			if ("1".equals(flag)) { // 二维码支付
				payUrl = basePayUrl;
				String qrCode = encode(payUrl);
				resVO.setQrCode(qrCode);
			} else if ("2".equals(flag)) { //直接访问快钱
				payUrl = basePayUrl+ "&goDirect=KUAIQIAN";
			} else if ("3".equals(flag)) { //直接访问兴业银行支付宝
				payUrl = basePayUrl+ "&goDirect=CIB_ALIPAYSCAN";
			} else if ("4".equals(flag)) { //直接访问兴业银行微信
				payUrl = basePayUrl+ "&goDirect=CIB_WECHATSCAN";
			} else if ("5".equals(flag)) {  //直接访问支付宝手机网页支付
				payUrl = basePayUrl+ "&goDirect=ALIPAYTI";
				if("TDD".equals(proposalNoOrgin.substring(0,3))){
					this.getInsureInfo(proposalNoOrgin, resVO);
					String qrCode = encode(mobileQuickPayUrl+"?consumerID="+consumerID+"&systemSourceCode="+systemSourceCode+"&paymentNo="+desPayNo+"&checkNo="+desCheckNo+"&goDirect=ALIPAYTI");
					resVO.setQrCode(qrCode);
				}
			} else if ("6".equals(flag)) {// 银行卡快捷支付
				payUrl = basePayUrl + "&goDirect=QUICKPAY";
			} else if ("7".equals(flag)) {// 微信支付
				payUrl = basePayUrl + "&goDirect=WECHAT";
			} else if ("8".equals(flag)) {// 招行扫码支付
				payUrl = basePayUrl + "&goDirect=CMBC_SCAN";
			} else if ("9".equals(flag)) {// POS支付
				payUrl = null;
			}else { // 其他情况，先访问主页面 
				payUrl = basePayUrl;
			}
			resVO.setUrl(payUrl);
			log.info("返回体参数：{}"+ new Object[]{resVO});
			return HttpResult.success(resVO,1,"成功");
		} catch (Exception e) {
			log.error("接口调用失败！", e);
			return HttpResult.error(0,"接口调用失败！");
		}
	}
	
	OrderDetailsInqueryFacade orderDetailFacade;
	
	private void getInsureInfo(String proposalNo,GetPayUrlResponseVO resVO) throws Exception{
		//调用查询订单详情方法,获取订单信息
		JsonVO orderDetailJsonVO = orderDetailFacade.OrderDetailsInquery(null,proposalNo, null, null,null);
		IPreciseQuoteRequestVO orderDetail = (IPreciseQuoteRequestVO) orderDetailJsonVO.getData();		
		//获取投保人信息
		List<PrivyVO> privys = orderDetail.getPrivy();
		if (privys.size()>0) {
			for (int i = 0; i < privys.size(); i++) {
				PrivyVO privy = privys.get(i);
				if(WebContants.C_INSURANCE_RELATIONSHIP_POLICYHOLDER.equals(privy.getInsuredFlag())) {
					resVO.setApplicantName(privy.getInsuredName());
					resVO.setApplicantIdentifyType(privy.getIdentifyType());
					resVO.setApplicantIdentifyNo(privy.getIdentifyNumber());
					break;
				}	
			}
		}
		resVO.setStartDate(orderDetail.getStartDate());
		resVO.setEndDate(orderDetail.getEndDate());
		resVO.setCiSumPremium(orderDetail.getCiSumPremium());
		resVO.setBiSumPremium(orderDetail.getBiSumPremium());
		resVO.setSumCartax(orderDetail.getSumCartax());
		if("1".equals(orderDetail.getJointSaleFlag())){
			for(JointSaleVO jointSaleVO : orderDetail.getJointSales()){
				if("E".equals(jointSaleVO.getJoinRiskCode().substring(0,1))){
					resVO.setJoinSaleE(jointSaleVO.getFormulaName()+"_"+jointSaleVO.getSumPremium());
				}else if("YEJ".equals(jointSaleVO.getJoinRiskCode())){
					resVO.setJoinSaleY(jointSaleVO.getFormulaName()+"_"+jointSaleVO.getSumPremium());
				}
			}
		}
		resVO.setReceivingSide("中国大地财产保险股份有限公司");
	}
	
	/**
	 * 二维码生成
	 * 
	 * @param contents
	 * @return
	 * @author ligh
	 * @throws Exception
	 */
	private String encode(String contents) throws Exception {
		// 二维码颜色
		final int BLACK = 0xFF000000;// 0xFFFF0000，红色
		// 二维码背景色
		final int WHITE = 0xFFFFFFFF;// 0xFF0000FF，蓝色
		// 注：二维码颜色色差大，扫描快，但如果"BLACK'设置为黑色外其他颜色，可能无法扫描
		// 二维码图片宽度
		final int WIDTH = 240;
		// 二维码图片高度
		final int HEIGHT = WIDTH;
		// 二维码格式参数
		final EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		/*
		 * 二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
		 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级； 选择M，扫描速度快。
		 */
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 二维码边界空白大小 1,2,3,4 (4为默认,最大)
		hints.put(EncodeHintType.MARGIN, 1);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MAX_SIZE, 350);
		hints.put(EncodeHintType.MIN_SIZE, 150);

		BufferedImage image = null;
		ByteArrayOutputStream stream = null;
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			matrix = deleteWhite(matrix);// 去除白边
			image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);

			int width = matrix.getWidth();
			int height = matrix.getHeight();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
				}
			}
			// 添加logo
			Graphics2D gs = image.createGraphics();
			 ClassPathResource resource=new ClassPathResource("logo.png"); 
			Image img = ImageIO.read(resource.getInputStream());
			int widthLogo = img.getWidth(null);// img.getWidth(null)>image.getWidth()*2/10?(image.getWidth()*2/10):img.getWidth(null);
			int heightLogo = img.getHeight(null);// img.getHeight(null)>image.getHeight()*2/10?(image.getHeight()*2/10):img.getWidth(null);
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;
			gs.drawImage(img, x, y, widthLogo, heightLogo, null);
			gs.dispose();
			img.flush();
			image.flush();
			// 写入流
			stream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", stream);
			byte[] data = stream.toByteArray();
			// 通过base64转码字符串
			Base64Encoder base64 = new Base64Encoder();
			String qrCode = base64.encode(data);
			System.out.println("data:image/jpg;base64,"+qrCode);
			return qrCode;
		} catch (Exception e) {
			log.error("生成支付二维码失败！", e);
			throw new Exception("生成支付二维码失败！");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
	}
	
	private BitMatrix deleteWhite(BitMatrix matrix){  
		int[] rec = matrix.getEnclosingRectangle();  
		int resWidth = rec[2] + 1;  
		int resHeight = rec[3] + 1;  

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
		resMatrix.clear();  
		for (int i = 0; i < resWidth; i++) {  
			for (int j = 0; j < resHeight; j++) {  
				if (matrix.get(i + rec[0], j + rec[1]))  
					resMatrix.set(i, j);  
			}  
		}  
		return resMatrix;  
	}
	
	@Value("${system.desPassword}")
	String desPassword;
	
	@Autowired
	PayResultFeignService payResultFeignService;
	
	@Autowired
	PaymentNoApplyFacade paymentNoApplyFacade;
	

	@Override
	public HttpResult paySyncNotice(ApplyPayVo applyPayVo) throws Exception {
		String proposalNo = DESEncryptUtil.decrypt(applyPayVo.getInsureNo(), desPassword);
		//String proposalNo = applyPayVo.getInsureNo();
		proposalNo=proposalNo.split("-")[0];
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("insureNo", proposalNo);
		orderMapper.updateByInsureNo(map);
		Order order = orderMapper.selectByInsureNo(map);
		map.put("id", order.getId());
		System.out.println(JSONObject.toJSONString(map));
		orderMapper.updateByRelationOrderId(map);
		return HttpResult.success();
	}
	
	// 0非车 1车险
	public static String vehiclePayRedirect(String applicationNo) {
		return applicationNo.startsWith("TD") == true ? "1" : "0";
	}
	
	public String attachUrl(String urls) {
		 if(urls!=null) {
			 String[] u=urls.split("\\|\\|");
	 	     if(u.length==1)return u[0];
	 	     else if(u.length==2)return u[1];
	 	     else return null;
		 }
		 return null;
	}
	
	public void buildcomAndChennelConfig(GroupSalesPlan groupSalesPlan) {
		String comCodes = (groupSalesPlan.getOrgCode() == null) ? "" : groupSalesPlan.getOrgCode();
		String chennels = (groupSalesPlan.getChannelByPerson() == null) ? "" : groupSalesPlan.getChannelByPerson();
		String[] comCodeArr = comCodes.split(",");
		String[] chennelArr = chennels.split(",");
		if(comCodeArr!=null && comCodes.length()>0) {
			for(String comCode : comCodeArr) {
				SalesPlanConfig salesPlanConfig = new SalesPlanConfig();
				salesPlanConfig.setType("1");
				salesPlanConfig.setSalesPlanId(groupSalesPlan.getSalesPlanId());
				salesPlanConfig.setGroupPlanCode(groupSalesPlan.getGroupPlanCode());
				salesPlanConfig.setCode(comCode);
				salesPlanConfigMapper.insertSelective(salesPlanConfig);
			}
		}
		if(chennelArr!=null && chennels.length()>0) {
			for(String chennelCode : chennelArr) {
				SalesPlanConfig salesPlanConfig = new SalesPlanConfig();
				salesPlanConfig.setType("2");
				salesPlanConfig.setSalesPlanId(groupSalesPlan.getSalesPlanId());
				salesPlanConfig.setGroupPlanCode(groupSalesPlan.getGroupPlanCode());
				salesPlanConfig.setCode(chennelCode);
				salesPlanConfigMapper.insertSelective(salesPlanConfig);
			}
		}
	}

	@Override
	public HttpResult groupPlanList(HashMap<String, Object> map) {
		List<HashMap<String,Object>> groupSalesPlanList = salesPlanMapper.selectGroupPlanByComChannel(map);
		return HttpResult.success(groupSalesPlanList, 1, "OK");
	}
	
	//条款须知
	public TermsAndConditions buildTermsAndConditions(GroupSalesPlan groupSalesPlan) {
		String salesPlanCode = groupSalesPlan.getSalesPlanCode();
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		termsAndConditions.setTitle(PlanFormConstant.ITEMS_NOTICE_TITIE);
		termsAndConditions.setDesc("");
		List<TermsAndConditionsContent> content = new ArrayList<TermsAndConditionsContent>();
		TermsAndConditionsContent termsAndConditionsContent1 = new TermsAndConditionsContent();
		termsAndConditionsContent1.setTitle("保险条款");
		termsAndConditionsContent1.setLink("");
		List<TermsAndConditionsContent> chlidren = new ArrayList<TermsAndConditionsContent>();
		TermsAndConditionsContent chlidren1 = new TermsAndConditionsContent();
		//chlidren1.setCode("CF1101262");
		chlidren1.setLink(salesPlanCode+"_中国大地保险家庭成员意外伤害保险条款.pdf");
		TermsAndConditionsContent chlidren2 = new TermsAndConditionsContent();
		//chlidren2.setCode("CF2100255");
		chlidren2.setLink(salesPlanCode+"_附加救护车费用保险条款.pdf");
		TermsAndConditionsContent chlidren3 = new TermsAndConditionsContent();
		//chlidren3.setCode("CF2100271");
		chlidren3.setLink(salesPlanCode+"_附加食物中毒保险条款.pdf");
		chlidren.add(chlidren1);
		chlidren.add(chlidren2);
		chlidren.add(chlidren3);
		termsAndConditionsContent1.setChlidren(chlidren);
		TermsAndConditionsContent termsAndConditionsContent2 = new TermsAndConditionsContent();
		termsAndConditionsContent2.setTitle("投保须知");
		termsAndConditionsContent2.setLink(salesPlanCode+"_投保须知.pdf");
		TermsAndConditionsContent termsAndConditionsContent3 = new TermsAndConditionsContent();
		termsAndConditionsContent3.setTitle("条款须知");
		termsAndConditionsContent3.setLink(salesPlanCode+"_条款须知.pdf");
		content.add(termsAndConditionsContent1);
		content.add(termsAndConditionsContent2);
		content.add(termsAndConditionsContent3);
		termsAndConditions.setContent(content);
		// 条款须知内容
		return termsAndConditions;
	}
	
	//健康须知
	public HealthNotice buildHealthNotice(GroupSalesPlan groupSalesPlan) {
		String salesPlanCode = groupSalesPlan.getSalesPlanCode();
		ImgList health = new ImgList();
		health.setImg(salesPlanCode+"_健康须知.pdf");
		HealthNotice healthNotice = new HealthNotice();
		healthNotice.setTitle(PlanFormConstant.HEALTH_NOTICE_TITIE);
		healthNotice.setUrl(health.getImg());
		healthNotice.setInUrl(health.getImgIn());
		healthNotice.setContent("");
		return healthNotice;
	}

	@Override
	public HttpResult policyList(HashMap<String, Object> map) {
			String reqJson = "{\"requestBody\":{\"searchType\":\"10\",\"submissionNo\":\""+map.get("submissionNo")+"\"},\"requestHead\":{\"consumerID\":\"supsale\"}}";
			JSONObject request = JSONObject.parseObject(reqJson);
			System.out.println("调用提交组合核保接口开始，请求报文："+ JSONObject.toJSONString(request));
	    	JsonNode jsonNode = UnirestUtil.postForJson(policyListUrl, request);
	    	System.out.println("调用提交组合核保接口结束，响应报文："+ JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies"));
			return HttpResult.success(JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies"), 1, "OK");
	}
	
}
