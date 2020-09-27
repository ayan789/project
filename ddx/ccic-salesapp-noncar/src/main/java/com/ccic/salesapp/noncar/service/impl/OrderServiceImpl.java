package com.ccic.salesapp.noncar.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.controller.PayCtl;
import com.ccic.salesapp.noncar.controller.PlanFormCtl;
import com.ccic.salesapp.noncar.dto.AccidentUnderwritingResponse;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.PolicyCustomer;
import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.order.request.Element;
import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.order.request.Product;
import com.ccic.salesapp.noncar.dto.order.response.ElementGroup;
import com.ccic.salesapp.noncar.dto.order.response.OrderDetailResponse;
import com.ccic.salesapp.noncar.dto.order.response.OrderListResponse;
import com.ccic.salesapp.noncar.dto.order.response.OrderResponse;
import com.ccic.salesapp.noncar.dto.request.InsuredGroup;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyLob;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyRisk;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.request.noncar.SharedCoverageGroup;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.response.PropertyTrialResponse;
import com.ccic.salesapp.noncar.dto.response.SubmitUnderwriteResponse;
import com.ccic.salesapp.noncar.feign.SupportFeignService;
import com.ccic.salesapp.noncar.feign.bean.ImageCheckResponse;
import com.ccic.salesapp.noncar.feign.bean.ImageCheckResponse.ImageNodes;
import com.ccic.salesapp.noncar.feign.bean.ImageDTO;
import com.ccic.salesapp.noncar.outService.esb.noCarUnderwriting.SubmitUnderwriteRequest;
import com.ccic.salesapp.noncar.repository.basedb.mapper.ApiLogMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.BussLogMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderCtMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderDetailMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PolicyCustomerMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.RenewalDatabusExpiredDataMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.ApiLog;
import com.ccic.salesapp.noncar.repository.basedb.po.BussLog;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderDetail;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.DoBeforeRequestService;
import com.ccic.salesapp.noncar.service.OrderService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.PropertyTrialService;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.SubmitUnderwritService;
import com.ccic.salesapp.noncar.utils.JsonUtil;
import com.ccic.salesapp.noncar.utils.OrderUtil;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.StringUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.TokenUtil;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	PropertyTrialService propertyTrialService;
	
	@Autowired
	AccidentQuoteService accidentQuoteService;
	
	@Autowired
	SupportFeignService supportFeignService;
	
	@Autowired
	AccidentUnderwritingService accidentUnderwritingService;
	
	@Autowired
	SubmitUnderwritService underwritService;
	
	@Autowired
	StoreProcessService storeProcessService;
	
	SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;
	
	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	OrderDetailMapper orderDetailMapper;
	
	@Autowired
	ApiLogMapper apiLogMapper;
	
	@Autowired
	BussLogMapper bussLogMapper;
	
	@Autowired
	OrderCtMapper orderCtMapper;
	
	@Autowired
	PlanStrategyMapper planStrategyMapper;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
	PlanInfoMapper planInfoMapper;
	
	@Autowired
	PolicyCustomerMapper policyCustomerMapper;
	
	@Value("${spring.profiles.active}")
	String active;
	
	@Autowired
	PlanFormCtl planFormCtl;
	
	@Autowired
	PayCtl payCtl;
	
	@Autowired
	RenewalDatabusExpiredDataMapper renewalDatabusExpiredDataMapper;
	
	@Autowired
	DoBeforeRequestService doBeforeRequestService;
	

	/***
	 * 下单处理全流程*
	 * 存储业务数据信息*
    	
    	存储业务日志*
    	
    	整合下单报文*
    	
    	下单试算*
    	
    	提交核保
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public HttpResult<OrderResponse> placeOrder(OrderRequest orderRequest) {
		orderRequest.setMap(new HashMap<String,Object>());
		HttpResult<OrderResponse> result = HttpResult.error(0, "");
		OrderResponse orderResponse = new OrderResponse();
		UserToken user = planStrategyService.getAccessTokenByUserCode(orderRequest.getUserCode());
		StoreInfo storeInfo = null;
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken = planStrategyService.getAgentSalesman(user.getUserCode());
			storeInfo = new StoreInfo();
			storeInfo.setStoreCode(user.getUserCode());
			storeInfo.setUserComCode(handlerToken.getComCode());
			storeInfo.setUserCode(handlerToken.getUserCode());
			storeInfo.setUserName(handlerToken.getUserName());
		}else {
			storeInfo = storeProcessService.findStoreByCode(orderRequest.getStoreCode());
			if (storeInfo == null) {
				throw new PlatformBaseException("未查询到门店信息", 0);
			}
		}
		orderRequest.getMap().put("storeInfo", storeInfo);
		
		PlanStrategy planStrategy = planStrategyMapper.selectByPrimaryKey(orderRequest.getStrategyId().intValue());
		PlanInfo planInfo = planInfoMapper.selectByPrimaryKey(orderRequest.getPlanId().intValue());
		orderRequest.getMap().put("planStrategy", planStrategy);
		orderRequest.getMap().put("planInfo", planInfo);
		orderRequest.setPlanCategory(planInfo.getPrdPlanCategory());
		orderResponse.setImgType(planStrategy.getInsureType());
		//试算要素
		List<Element> computeList = planInfoMapper.selectComputeElementsByPlanId(planInfo.getId());
		List<Long> computeIds = new ArrayList<Long>();
		for (Element element : computeList) {
			computeIds.add(element.getId());
		}
		orderRequest.getMap().put("computeList", computeList);
		orderRequest.getMap().put("computeIds", computeIds);
		
		//构建下单报文前进行的检查
		checkBeforeRequestBuild(orderRequest);
		
		List<Element> list = orderRequest.getMetaDataList();
		
		List<String> notUploadImages = new ArrayList<String>();
		try {
			//存储业务数据信息
			saveBizData(orderRequest);
			
			Order order = (Order)orderRequest.getMap().get("order");
			notUploadImages = getNotUploadImages(order.getId());
			orderResponse.setNotUploadImgList(notUploadImages);
			if(StringUtils.isNotBlank(orderRequest.getInsureNo())) {
				underwriting(orderRequest.getInsureNo());
				OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
				orderDetailRequest.setOrderId(order.getId());
				HttpResult odResult = orderDetail(orderDetailRequest);
				OrderDetailResponse odResponse = (OrderDetailResponse) odResult.getData();
				BeanUtils.copyProperties(odResponse, orderResponse);
				orderResponse.setElementGroupList(odResponse.getElementList());
				orderResponse.setOrderNo(order.getOrderNo());
				orderResponse.setOrderId(order.getId());
				result.setCode("1");
				result.setData(orderResponse);
				result.setMsg("订单查询完成");
				return result;//若是暂存，不继续执行
			}
			//存储业务日志
			//saveBizLog(bussLog);
		} catch (ParseException e2) {
			e2.printStackTrace();
			result.setCode("0");
			result.setData(orderResponse);
			result.setMsg("保存业务信息失败："+ e2.getMessage());
			return result;
		}
		
		Order order = (Order)(orderRequest.getMap().get("order"));
		//初始化下单请求数据
		RequestBody initRequest = null;
		if("0".equals(orderRequest.getPlanCategory())) {
			initRequest = initHeaRequestData(orderRequest, storeInfo);
		}else if("2".equals(orderRequest.getPlanCategory())) {
			initRequest = initNocarRequest(orderRequest, storeInfo);
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = null;
		try {
			node = (ObjectNode) mapper.readTree(mapper.writeValueAsString(initRequest));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result.setCode("0");
			result.setMsg("初始化订单信息失败"+ e1.getMessage());
			return result;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result.setCode("0");
			result.setMsg("初始化订单信息失败"+ e1.getMessage());
			return result;
		}
		
		//整合下单报文
		try {
			JsonUtil util = new JsonUtil();
			Map<String,Object> imap = new HashMap<String,Object>();
			for (Element element : list) {
				if(StringUtils.isNotBlank(element.getSubmitName())) {
					String[] nodes = element.getSubmitName().split(",");
					int index = 0;
					for (int i = 0; i < nodes.length; i++) {
						if(!computeIds.contains(element.getId()) ) {
							index = getArrayIndex(imap,element.getGroupCode(), nodes[i].trim(),element.getArrayIndex());
						}
						util.setJsonPointerValue(node,  JsonPointer.compile(nodes[i].replace("[]", index+"")), new TextNode(element.getValue()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("0");
			result.setMsg("初始化订单信息失败"+ e.getMessage());
			return result;
		}
    	
    	String jsonRequest = null;
		try {
			jsonRequest = mapper.writeValueAsString(node);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			result.setCode("0");
			result.setMsg("初始化订单信息失败"+ e1.getMessage());
			return result;
		}
		DoBeforeRequestService doBeforeRequest = null;
		Response response =	 null;
		//下单试算
    	if("0".equals(orderRequest.getPlanCategory())) {//意健险
    		try {
    			Gson gson = new Gson();
    			AccidentQuoteRequest request = gson.fromJson(jsonRequest, AccidentQuoteRequest.class);
    			doBeforeRequestService.doBefore(request,orderRequest);
    			orderRequest.getMap().put("request", request);
    			
    			checkAfterRequestBuild(orderRequest);
    			
    			if("1".equals(orderRequest.getIsTemp())) {
    				updateBizData(orderRequest,request,response);
    				order = orderMapper.selectByPrimaryKey(order.getId());
    				//查出订单详情
    				OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
    				orderDetailRequest.setOrderId(order.getId());
    				HttpResult odResult = orderDetail(orderDetailRequest);
    				OrderDetailResponse odResponse = (OrderDetailResponse) odResult.getData();
    				BeanUtils.copyProperties(odResponse, orderResponse);
    				orderResponse.setElementGroupList(odResponse.getElementList());
    				orderResponse.setOrderNo(order.getOrderNo());
    				orderResponse.setOrderId(order.getId());
    				result.setCode("1");
    				result.setData(orderResponse);
    				result.setMsg("暂存数据完成");
    				return result;//若是暂存，不继续执行
    			}
    			
    			response =	accidentQuoteService.accidentQuote(request,(String)orderRequest.getMap().get("orderNo"), result, null);
    			//更新业务数据
    			updateBizData(orderRequest,request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new PlatformBaseException("数据未能提交："+ e.getMessage(),e, 0);
    		}
    	}else if("2".equals(orderRequest.getPlanCategory())) {//财产险
    		Gson gson = new Gson();
    		PropertyTrialRequest request = gson.fromJson(jsonRequest, PropertyTrialRequest.class);
    		
    		try {
				doBeforeRequestService.doBefore(request,orderRequest);
			} catch (Exception e1) {
				throw new PlatformBaseException("下单预处理请求失败："+e1.getMessage(), 0);
			}
    		orderRequest.getMap().put("request", request);
			checkAfterRequestBuild(orderRequest);
    		
    		try {
    			if("1".equals(orderRequest.getIsTemp())) {
    				updateBizData(orderRequest,request,response);
    				//查出订单详情
    				OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
    				orderDetailRequest.setOrderId(order.getId());
    				HttpResult odResult = orderDetail(orderDetailRequest);
    				OrderDetailResponse odResponse = (OrderDetailResponse) odResult.getData();
    				BeanUtils.copyProperties(odResponse, orderResponse);
    				orderResponse.setElementGroupList(odResponse.getElementList());
    				
    				orderResponse.setOrderNo(order.getOrderNo());
    				orderResponse.setOrderId(order.getId());
    				orderResponse.setOrder(order);
    				result.setCode("1");
    				result.setData(orderResponse);
    				result.setMsg("暂存数据完成");
    				return result;//若是暂存，不继续执行
    			}
    			response =	propertyTrialService.propertyTrial(request,(String)orderRequest.getMap().get("orderNo"), result,new UserVO() , orderRequest.getAgentInfo());
    			//更新业务数据
    			updateBizData(orderRequest,request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    			result.setCode("0");
    			result.setMsg("提交核心失败："+ e.getMessage());
    			return result;
    		}
    	}
    	
    	if(response.getResponseHead().getStatus() != 0) {
    		result.setCode("0");
			result.setMsg(response.getResponseHead().getAppMessage());
			return result;
    	}
    	
    	OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
		orderDetailRequest.setOrderId(order.getId());
		HttpResult odResult = orderDetail(orderDetailRequest);
		OrderDetailResponse odResponse = (OrderDetailResponse) odResult.getData();
		BeanUtils.copyProperties(odResponse, orderResponse);
		orderResponse.setElementGroupList(odResponse.getElementList());
		orderResponse.setOrderNo(order.getOrderNo());
		orderResponse.setOrderId(order.getId());
    	orderResponse.setOrder(order);
    	
    	//判断是否可以直接提交核保，如果无需上传影像
    	if(notUploadImages.size() > 0) {
    		orderResponse.setNotUploadImgList(notUploadImages);
    		result.setCode("1");
    		result.setData(orderResponse);
    		result.setMsg("数据提交核心成功,请上传"+ notUploadImages);
    		//有影像未上传，不能核保
    		return result;
    	}
    	
    	//不需要上传影像，不需要签名 下单后直接提交核保
    	underwriting(order.getInsureNo());
    	
		
		return result.success(orderResponse);
	}
	
	/***
	 * 获取未上传的影像名称列表
	 * @param planStrategy
	 * @return
	 */
	@Override
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
	
	/**
	 * 初始化意健险下单参数
	 * @param orderRequest
	 * @param storeInfo
	 * @return
	 */
	public RequestBody initHeaRequestData(OrderRequest orderRequest,StoreInfo storeInfo) {
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		AccidentQuoteRequest request = new AccidentQuoteRequest();
		// 投保单号
		request.setProposalNo(orderRequest.getInsureNo());
		// 业务来源属性
		request.setBusinessAttribute("0801");
		// 保单性质（团单标记，区分个团） 01 个人 02团体
		request.setPolicyNature("01");
		if(planInfo != null && StringUtils.isNotBlank(planInfo.getPolicyNature())) {
			request.setPolicyNature(planInfo.getPolicyNature());
		}
		// 分户类型(被保险人清单类型)
		request.setInsuredListType("1");
		request.setInsuredListType(planInfo.getPlanListType());
		// 产品代码
		request.setProductCode(orderRequest.getRiskCode());
		// 请求业务类型
		request.setBusinessType("1");//1-新保、2-修改、3-从联单生成、4-续保，默认为1
		if("Y".equals(orderRequest.getIsRenewal()) ) {
			request.setBusinessType("4");//续保
			request.setHistoryPolicyNo(orderRequest.getRelationPolicyNo());
		}
		// 业务来源1
		request.setBusinessSourceCode("0101");
		// 业务来源2
		request.setBusinessSource2Code("0101");
		
		if(StringUtils.isNotBlank(orderRequest.getBusinessNatureCode())) {
			request.setBusinessSourceCode(orderRequest.getBusinessNatureCode());
			request.setBusinessSource2Code(orderRequest.getBusinessNatureCode());
		}

		if (storeInfo != null) {
			request.setOrgCode(storeInfo.getUserComCode());// 归属机构代码
			request.setIssueUserCode(storeInfo.getUserCode());// 出单员代码
			request.setIssueOrgCode(storeInfo.getUserComCode());// 出单机构代码
			// 归属经办人，仅在直销业务中使用
			request.setHandlerCode(storeInfo.getUserCode());
			request.setHandlerName(storeInfo.getUserName());
			request.setBelongToHandlerCode(storeInfo.getUserCode());
			;// 归属经办人编码
			request.setBelongToHandlerName(storeInfo.getUserName());// 归属经办人名称
			request.setBelongToHandler2Code(storeInfo.getUserCode());// 业务风险分类
			request.setBelongToHandler2Name(storeInfo.getUserName());// 业务风险分类名称
		}

		AgentInfoVO agent = null;
		agent = orderRequest.getAgentInfo();
		if (agent != null) {
			if ("1".equals(orderRequest.getIsAgent())) {
				// 代理人ID
				request.setAgentCode(agent.getAgentCode());
				// 代理人协议号
				request.setAgreementNo(agent.getAgreementNo());
				request.setBusinessSourceCode(agent.getBusinessNature());// 业务来源
				if (StringUtils.isNotBlank(agent.getBusinessNature2())) {
					request.setBusinessSource2Code(agent
							.getBusinessNature2());// 业务来源2
					request.setBelongToHandler2Code(agent
							.getHandler2Code()); // 经办人2代码
					request.setBelongToHandler2Name(agent
							.getHandler2Name()); // 经办人2名称
				} else {
					request.setBusinessSource2Code(agent
							.getBusinessNature());// 业务来源2
					request
							.setBelongToHandler2Code(agent.getAgentCode()); // 经办人2代码
					request
							.setBelongToHandler2Name(agent.getAgentName()); // 经办人2名称
				}
				// 经办人
				request.setHandlerCode(agent.getAgentCode());
				request.setHandlerName(agent.getAgentName());
			}
		}
		
		String startDateTail = "T00:00:00";
		String endDateTail = "T23:59:59";
		
		// 起保日期
		request.setEffectiveDate(orderRequest.getStartDate()+startDateTail);
		// 终保日期
		request.setExpiryDate(orderRequest.getEndDate()+endDateTail);
		// 投保日期
		request.setProposalDate(datef.format(new Date()));
		// 是否定期结算标识
		request.setIsRegularSettlement("N");
		// 保额
		request.setSumInsured(String.valueOf(orderRequest.getSumAmount()));
		// 应收保费
		request.setDuePremium(String.valueOf(orderRequest.getSumPremium()));
		// 总折扣率
		request.setTotalDiscountRate(null);
		// 保额币种
		request.setSiCurrencyCode("CNY");
		// 保费币种
		request.setPremiumCurrencyCode("CNY");
		// 短期费率
		request.setShortRate("1");
		// 是否涉农标识
		request.setAgricultureRelaType("N");
		// 共保标记
		request.setCoInsuranceType("01");
		// 联保标记
		request.setInternalCoInsuranceType("01");
		// 争议解决方式
		request.setArgueSolutionType("01");
		// 司法管辖
		request.setJudicalScopeCode("01");
		// 是否见费出单标志
		request.setIsIssueAfterPay("Y");
		String isBillAfterPay = isBillAfterPay(orderRequest.getPlanId(), storeInfo.getUserComCode()) ? "Y" : "N";
		request.setIsIssueAfterPay(isBillAfterPay);
		
		// 外部渠道出单时的附加信息
		List<ChannelOpInfo> channelOpInfoList = new ArrayList<ChannelOpInfo>();
		ChannelOpInfo channelOpInfo = new ChannelOpInfo();
		// 渠道代码
		channelOpInfo.setChannelCode("310073");
		// 渠道机构代码
		channelOpInfo.setChannelComCode("310073");
		// 渠道机构名称
		channelOpInfo.setChannelComName("移动销售支持");
		// 渠道名称
		channelOpInfo.setChannelName("移动销售支持");
		// 渠道产品代码
		channelOpInfo.setChannelProductCode(orderRequest.getRiskCode());// 产品代码
		// 渠道交易流水号
		channelOpInfo.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());
		// 渠道交易日期
		channelOpInfo.setTrxDate(datef.format(new Date()));
		// 渠道交易代码
		channelOpInfo.setTrxCode("001");
		
		if("1".equals(orderRequest.getIsAgent()) && orderRequest.getAgentInfo() !=null) {
			channelOpInfo.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			channelOpInfo.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
		}
		channelOpInfoList.add(channelOpInfo);
		request.setChannelOpInfoList(channelOpInfoList);
		
		if(orderRequest.getInvoiceInfo() != null) {
			List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
			invoiceInfoList.add(orderRequest.getInvoiceInfo());
			request.setInvoiceInfoList(invoiceInfoList);
		}
		
		
		
		
		List<PolicyCoverage> policyCoverageList1 = new ArrayList<PolicyCoverage>();
		
		List<Product> productList = orderRequest.getProductList();
		Map<String,List<Product>> ctGroupMap = new HashMap<String,List<Product>>();
		for (Product product : productList) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("planId", orderRequest.getPlanId());
			param.put("cList", new String[] {product.getKindCode()});
			List<String> groupTypes = orderCtMapper.selectOrderCtGroupType(param);
			if(groupTypes != null && !groupTypes.isEmpty()) {
				String cgt = groupTypes.get(0);
				if("3".equals(cgt)) {
					product.setInsuredGroupType("2");//家庭分组
				}
				//其他情况默认普通分组
			}
			
			List<Product> groupProductList = ctGroupMap.get(product.getInsuredGroupType());
			if(groupProductList == null) {
				groupProductList = new ArrayList<Product>();
			}
			groupProductList.add(product);
			ctGroupMap.put(product.getInsuredGroupType(), groupProductList);
		}
		
		List<PolicyRisk> policyRiskList = new ArrayList<PolicyRisk>();
		PolicyRisk policyRisk = new PolicyRisk();
		int i = 1;
		int securityNumDou = 1;
		securityNumDou = getInsuredCount(orderRequest.getMetaDataList());
		if(securityNumDou <= 0) {//若未填写被保人信息，默认1
			securityNumDou = getTotalInsuredCount(orderRequest.getMetaDataList());
		}
		if(securityNumDou <= 0) {//若未填写被保人信息，默认1
			securityNumDou = 1;
		}
		List<InsuredGroup> insuredGroupList = new ArrayList<InsuredGroup>();
		for (Iterator iterator = ctGroupMap.keySet().iterator(); iterator.hasNext();) {
			String group = (String) iterator.next();
			List<Product> groupProductList = ctGroupMap.get(group);
			InsuredGroup insuredGroup = new InsuredGroup();
			insuredGroup.setInsuredGroupType(group);
			insuredGroup.setInsuredGroupNo(i+"");
			insuredGroup.setPlanCode(orderRequest.getFormulaCode().substring(0, 10));
			insuredGroup.setNumberOfCopies("1");//份数默认1
			insuredGroup.setNumberOfCopies(orderRequest.getFormulaSize() == null ? getNumberOfCopies(orderRequest) + "" : orderRequest.getFormulaSize() + "");
			insuredGroup.setFamilyCount(1l);
			insuredGroup.setInsuredCount(String.valueOf(securityNumDou));
			
			for (Product product : groupProductList) {
				PolicyCoverage coverage1 = new PolicyCoverage();
				// 条款代码
				coverage1.setClauseCode(product.getClauseCode());
				// 责任代码
				coverage1.setKindCode(product.getKindCode());
				// 保额
				if(product.getInsured() != null) {
					coverage1.setSumInsured(product.getInsured()*securityNumDou);
				}
				// 应收保费
				if(product.getDuePremium() != null) {
					coverage1.setDuePremium(Double.valueOf("1.0")*securityNumDou*product.getDuePremium());
				}
				policyCoverageList1.add(coverage1);
				
			}
			insuredGroup.setPolicyCoverageList(policyCoverageList1);
			i++;
			insuredGroupList.add(insuredGroup);
			policyRisk.setInsuredGroupList(insuredGroupList);
		}
		
		
		policyRiskList.add(policyRisk);
		List<PolicyLob> policyLobList = new ArrayList<PolicyLob>();
		PolicyLob policyLob = new PolicyLob();
		policyLob.setPolicyRiskList(policyRiskList);
		
		policyLobList.add(policyLob);
		request.setPolicyLobList(policyLobList);
		
		return request;
	}
	
	/**
	 * 初始化财产险下单参数
	 * @param orderRequest
	 * @param storeInfo
	 * @return
	 */
	public RequestBody initNocarRequest(OrderRequest orderRequest,StoreInfo storeInfo) {
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		PropertyTrialRequest requestDTO = new PropertyTrialRequest();
		// 渠道信息
		List<com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo> channelList = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo>();
		com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo channelInfo = new com.ccic.salesapp.noncar.dto.request.noncar.ChannelOpInfo();
		channelInfo.setChannelCode("310073");
		channelInfo.setChannelComCode("310073");
		channelInfo.setChannelComName("移动销售支持");
		channelInfo.setChannelName("移动销售支持");
		channelInfo.setChannelProductCode(orderRequest.getRiskCode());
		channelInfo.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());
		channelInfo.setOperatorCode(storeInfo.getUserCode()); // 渠道操作员代码
		channelInfo.setTrxDate(datef.format(new Date())); //交易时间
		channelInfo.setTrxCode("001"); //渠道交易代码
		
		if("1".equals(orderRequest.getIsAgent()) && orderRequest.getAgentInfo() !=null) {
			channelInfo.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			channelInfo.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
		}
		channelList.add(channelInfo);
		requestDTO.setChannelOpInfoList(channelList);
		
		// 主信息
		requestDTO.setBusinessAttribute("0801");// 业务来源属性（替换原先的出单方式，系统来源，系统业务属性）
		requestDTO.setBusinessSourceCode(StringUtils.isBlank(orderRequest.getBusinessNatureCode())  ? "0101" :orderRequest.getBusinessNatureCode());// 业务来源1/BusinessNature1，对应归属经办人的业务来源，存放末级的业务原来
		requestDTO.setBusinessSource2Code(StringUtils.isBlank(orderRequest.getBusinessNatureCode())  ? "0101" :orderRequest.getBusinessNatureCode());// 业务来源2
		requestDTO.setAgricultureRelaType("N"); // 涉农标识
		requestDTO.setCoInsuranceType("01");// 共保标记（主共/从共）
		
		if(planInfo != null && StringUtils.isNotBlank(planInfo.getPolicyNature())) {
			requestDTO.setPolicyNature(planInfo.getPolicyNature());
		}
		
		String startDateTail = "T00:00:00";
		String endDateTail = "T23:59:59";

		requestDTO.setEffectiveDate(orderRequest.getStartDate()+startDateTail);// 起保日期/起始生效日期,支持时分
		requestDTO.setExpiryDate(orderRequest.getEndDate()+endDateTail);// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
														// datef.format(calendar.getTime())
		requestDTO.setInternalCoInsuranceType("01");// 联保标记（主联/从联）(DC:联共保类型代码)
		requestDTO.setIsIssueAfterPay("Y");// 是否见费出单标志
		requestDTO.setJudicalScopeCode("01");// 中华人民共和国管辖(港澳台除外)
		requestDTO.setOverseaBusinessType("1");// 境内外业务标识 境内1
		requestDTO.setPlanCode(orderRequest.getFormulaCode());// 方案代码
		requestDTO.setProductCode(orderRequest.getRiskCode());// 产品代码
		requestDTO.setProposalDate(datef.format(new Date()));// 投保日期
		requestDTO.setSiCurrencyCode("CNY");// 保额币种
		requestDTO.setArgueSolutionType("01"); // 争议解决方式 诉讼01
		requestDTO.setPremiumCurrencyCode("CNY");
		requestDTO.setInsuredListType("1"); // 分户类型 1标准有分户 2约定有分户
		requestDTO.setInsuredListType(planInfo.getPlanListType());
		requestDTO.setIsIssueAfterPay("Y");// 是否见费出单标志
		String isBillAfterPay = isBillAfterPay(orderRequest.getPlanId(), storeInfo.getUserComCode()) ? "Y" : "N";
		requestDTO.setIsIssueAfterPay(isBillAfterPay);
		requestDTO.setOrgCode(storeInfo.getUserComCode());
		requestDTO.setIssueOrgCode(storeInfo.getUserComCode());
		requestDTO.setIssueUserId(storeInfo.getUserCode());
		// 经办人，仅在直销业务中使用
		requestDTO.setHandlerCode(storeInfo.getUserCode());
		requestDTO.setHandlerName(storeInfo.getUserName());
		requestDTO.setBelongToHandlerCode(storeInfo.getUserCode());//归属经办人
		requestDTO.setBelongToHandler2Name(storeInfo.getUserName()); // 归属经办人
		requestDTO.setBelongToHandler2Code(storeInfo.getUserCode()); // 归属经办人
		
		if(StringUtils.isNotBlank(orderRequest.getRelationPolicyNo())) {
			requestDTO.setHistoryPolicyNo(orderRequest.getRelationPolicyNo());
		}
		
		if(StringUtils.isNotBlank(orderRequest.getBusinessNatureCode())) {
			requestDTO.setBusinessSourceCode(orderRequest.getBusinessNatureCode());
			requestDTO.setBusinessSource2Code(orderRequest.getBusinessNatureCode());
		}
		
		AgentInfoVO agent = null;
		agent = orderRequest.getAgentInfo();
		if (agent != null) {
			if ("1".equals(orderRequest.getIsAgent())) {
				// 代理人ID，仅在非直销业务中使用
				requestDTO.setAgentCode(agent.getAgentCode());
				// 代理人协议号
				requestDTO.setAgreementNo(agent.getAgreementNo());
				requestDTO.setBusinessSourceCode(agent.getBusinessNature());// 业务来源1
				if(StringUtils.isNotBlank(agent.getBusinessNature2())) {
					requestDTO.setBusinessSource2Code(agent.getBusinessNature2());//业务来源2
					requestDTO.setBelongToHandler2Code(agent.getHandler2Code()); // 经办人2代码
					requestDTO.setBelongToHandler2Name(agent.getHandler2Name()); // 经办人2名称					
				}else {
					requestDTO.setBusinessSource2Code(agent.getBusinessNature());//业务来源2
					requestDTO.setBelongToHandler2Code(agent.getAgentCode()); // 经办人2代码
					requestDTO.setBelongToHandler2Name(agent.getAgentName()); // 经办人2名称
				}
				// 经办人
				requestDTO.setHandlerCode(agent.getAgentCode());
				requestDTO.setHandlerName(agent.getAgentName());
			}
		}
		if(orderRequest.getInvoiceInfo() != null) {
			Map<String ,String> temp = new HashMap<String,String>();
			temp.put("noticeInvoice", "Y"); 
			requestDTO.setTempData(temp);
			
			List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
			invoiceInfoList.add(orderRequest.getInvoiceInfo());
			requestDTO.setInvoiceInfoList(invoiceInfoList);
		}
		
		//产品信息
		List<com.ccic.salesapp.noncar.dto.PolicyLob> lobList = new ArrayList<com.ccic.salesapp.noncar.dto.PolicyLob>();
		com.ccic.salesapp.noncar.dto.PolicyLob lob = new com.ccic.salesapp.noncar.dto.PolicyLob();
		lob.setEffectiveDate(orderRequest.getStartDate());
		lob.setExpiryDate(orderRequest.getEndDate());
		lob.setProductCode(orderRequest.getRiskCode());
		lob.setPlanCode(orderRequest.getFormulaCode());
		lob.setEachPersonCopies(orderRequest.getFormulaSize() == null ? getNumberOfCopies(orderRequest)  : orderRequest.getFormulaSize() ); //份数 默认1
		
		List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk> riskList = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk>();
		com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk risk  = new com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk();
		List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk> policyRiskList = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk>();
		//经济型
		//条款信息
		//主险1 - 标的信息
		//PolicyRisk risk = new PolicyRisk();
		risk.setEffectiveDate(orderRequest.getStartDate());
		risk.setExpiryDate(orderRequest.getEndDate());
		risk.setInsuredCategory(""); //标的类别
		risk.setInsuredDescription("");
		//条款信息1
		List <com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> policyCoverageList1 = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage>();
		List<Product> productList = getNocarProductList(orderRequest);
		for (Product product : productList) {
			//条款信息1
			com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage coverage1 = new com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage();
			coverage1.setEffectiveDate(orderRequest.getStartDate());  
			coverage1.setExpiryDate(orderRequest.getEndDate());         
			coverage1.setProductElementCode(product.getClauseCode());

			coverage1.setNumberOfCopies(lob.getEachPersonCopies()+"");
			coverage1.setTotalNumberOfCopies(lob.getEachPersonCopies()+"");
			policyCoverageList1.add(coverage1);
			//责任信息
			List <com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> policyCoverageList01 = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage>();
			for (String kindCode : product.getKindCodes()) {
				com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage coverage01 = new com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage();
				coverage01.setEffectiveDate(orderRequest.getStartDate());  
				coverage01.setExpiryDate(orderRequest.getEndDate());         
				coverage01.setProductElementCode(kindCode);
				policyCoverageList01.add(coverage01);
			}
			coverage1.setPolicyCoverageList(policyCoverageList01);
		}
		risk.setPolicyCoverageList(policyCoverageList1);
		policyRiskList.add(risk);
		
		lob.setPolicyRiskList(policyRiskList);
		
		lobList.add(lob);
		requestDTO.setPolicyLobList(lobList);
		return requestDTO;
	}
	
	/***
	 * 保存业务数据
	 * @return
	 * @throws ParseException 
	 */
	public HttpResult saveBizData(OrderRequest orderRequest) throws ParseException {
		Map<String,Object> map =  orderRequest.getMap();
		Long  orderId = null;
		Order order = null;
		StoreInfo storeInfo = (StoreInfo)map.get("storeInfo");
		String comcode=storeInfo!=null?storeInfo.getUserComCode():null;
		/**若订单号不为空，更新操作*/
		if(!StringUtil.isNullorEmpty(orderRequest.getOrderNo())) {
			//查询到订单记录
			order = orderMapper.selectByOrderNo(orderRequest.getOrderNo());
			orderId = order.getId();
			//清除上次暂存数据
			orderDetailMapper.deleteByOrderId(order.getId());
			map.put("orderNo", orderRequest.getOrderNo());
			map.put("orderId", order.getId());
			map.put("order", order);
			orderRequest.setMap(map);
			order = buildOrder(orderRequest);
    		orderMapper.updateByPrimaryKeySelective(order);
		}else if(!StringUtil.isNullorEmpty(orderRequest.getInsureNo())) {
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("insureNo", orderRequest.getInsureNo());
			order = orderMapper.selectByInsureNo(params);
			orderId = order.getId();
			orderDetailMapper.deleteByOrderId(order.getId());
			map.put("orderNo", orderRequest.getOrderNo());
			map.put("orderId", order.getId());
			map.put("order", order);
			orderRequest.setMap(map);
			order = buildOrder(orderRequest);
    		orderMapper.updateByPrimaryKeySelective(order);
		}else if(orderRequest.getOrderId() != null && orderRequest.getOrderId() > 0){
			//查询到订单记录
			order = orderMapper.selectByPrimaryKey(orderRequest.getOrderId());
			orderId = order.getId();
			//清除上次暂存数据
			orderDetailMapper.deleteByOrderId(order.getId());
			map.put("orderNo", orderRequest.getOrderNo());
			map.put("orderId", order.getId());
			map.put("order", order);
			orderRequest.setMap(map);
			order = buildOrder(orderRequest);
    		orderMapper.updateByPrimaryKeySelective(order);
		}else {
			String orderNo = OrderUtil.generatorOrderNo(comcode);
			order = buildOrder(orderRequest);
			order.setOrderNo(orderNo);
			//订单状态
			order.setOrderStatus(InsuranceApplicationStatus.ZANCUN);
			//是否删除
			order.setIsDelete("0");
			order.setSignStatus("0");
			
			orderMapper.insertSelective(order);
			orderId = order.getId();
			map.put("orderNo", orderNo);
			map.put("orderId", orderId);
			map.put("order", order);
		}
		//存储最新数据
		List<Element> elementList =  orderRequest.getMetaDataList();
		for (Element ele : elementList) {
			OrderDetail od = new OrderDetail();
			od.setOrderId(orderId);
			od.setGroupId(ele.getGroupId());
			od.setArrayIndex(ele.getArrayIndex());
			od.setElementId(ele.getId());
			od.setElementValue(ele.getValue());
			od.setDisplayValue(ele.getDisplayValue());
			od.setLabel(ele.getName());
			od.setGroupCode(ele.getGroupCode());
			orderDetailMapper.insertSelective(od);
		}
		
		//清除上次暂存数据
		if(StringUtils.isBlank(orderRequest.getInsureNo())) {//数据提交核心后更新 
			orderCtMapper.deleteByOrderId(orderId);
			List<Product> productList = orderRequest.getProductList();
			for (Product ct : productList) {
				OrderCt orderCt = new OrderCt();
				orderCt.setOrderId(orderId);
				orderCt.setClauseCode(ct.getClauseCode());
				orderCt.setKindCode(ct.getKindCode());
				orderCt.setDuePremium(ct.getDuePremium() == null ? null:BigDecimal.valueOf(ct.getDuePremium()) );
				orderCt.setInsured(ct.getInsured() == null ? null : BigDecimal.valueOf(ct.getInsured()));
				HashMap<String,Object> b = new HashMap<String,Object>();
				b.put("clauseCode",ct.getClauseCode());
				b.put("planId", orderRequest.getPlanId());
				orderCt.setClauseName(orderCtMapper.selectCtName(b));
				orderCt.setCoverageName(ct.getProductName());
				orderCtMapper.insertSelective(orderCt);
			}
		}
		
		invoiceInfoMapper.deleteByOrderId(orderId);
		/**若开票信息不为空，存储开票信息*/
		if (orderRequest.getInvoiceInfo() != null && StringUtils.isNotBlank(orderRequest.getInvoiceInfo().getCompanyName())) {	//保存开票信息
			com.ccic.salesapp.noncar.dto.InvoiceInfo invoiceInfo = new com.ccic.salesapp.noncar.dto.InvoiceInfo();
			//invoiceInfo.setInsureinfoId(Long.parseLong(insureInfo.getId()));
			invoiceInfo.setOrderId(orderId);
			invoiceInfo.setClientType(orderRequest.getInvoiceInfo().getCustomerType());
			invoiceInfo.setTaxPayerType(orderRequest.getInvoiceInfo().getTaxPayerType());
			invoiceInfo.setInvoiceType(orderRequest.getInvoiceInfo().getInvoiceType());
			invoiceInfo.setCompanyName(orderRequest.getInvoiceInfo().getCompanyName());
			invoiceInfo.setTaxMobile(orderRequest.getInvoiceInfo().getMobile());
			invoiceInfo.setTaxEmail(orderRequest.getInvoiceInfo().getEmail());
			invoiceInfo.setTaxPayerNo(orderRequest.getInvoiceInfo().getTaxPayerNo());
			invoiceInfo.setTaxRegistryAddress(orderRequest.getInvoiceInfo().getTaxRegisterAddress());
			invoiceInfo.setTaxRegistryPhone(orderRequest.getInvoiceInfo().getTaxRegisterTel());
			invoiceInfo.setBankName(orderRequest.getInvoiceInfo().getDepositeBankName());
			invoiceInfo.setAccountNumber(orderRequest.getInvoiceInfo().getAccountNo());
			
			invoiceInfoMapper.insert( invoiceInfo);
		}
		return null;
	}
	
	private Order buildOrder(OrderRequest orderRequest) throws ParseException{
		UserToken user = planStrategyService.getAccessTokenByUserCode(orderRequest.getUserCode());
		String comCode = null;
		String userCode = orderRequest.getUserCode();
		String userName = user.getUserName();
		if(user != null && "3".equals(user.getLoginFlag())) {
			UserToken handlerToken =  planStrategyService.getAgentSalesman(user.getUserCode());
			comCode = handlerToken.getComCode();
			
		}else {
			StoreInfo storeInfo = storeProcessService.findStoreByCode(orderRequest.getStoreCode());
			userCode = storeInfo.getUserCode();
			comCode = storeInfo.getUserComCode();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateTail = " 00:00:00";
		String endDateTail = " 23:59:59";
		PlanStrategy planStrategy = (PlanStrategy) orderRequest.getMap().get("planStrategy");
		Order order = new Order();
		if(orderRequest.getMap().get("order") != null) {
			order = (Order)orderRequest.getMap().get("order");
		}
		order.setUserCode(userCode);
		order.setStartDate(df.parse(orderRequest.getStartDate()+startDateTail));
		order.setEndDate(df.parse(orderRequest.getEndDate()+endDateTail));
		//方案id
		order.setPlanId(orderRequest.getPlanId());
		order.setComCode(comCode);
		order.setpComCode(user.getBranchCode());
		order.setSumAmount(BigDecimal.valueOf(orderRequest.getSumAmount()));
		order.setSumPremium(BigDecimal.valueOf(orderRequest.getSumPremium()));
		order.setProductCode(orderRequest.getRiskCode());
		order.setPlanCode(orderRequest.getFormulaCode());
		order.setStrategyCode(planStrategy.getCode());
		order.setOperatorCode(userCode);
		order.setOperatorName(userName);
		order.setProductName(planStrategy.getName());
		order.setStrategyId(orderRequest.getStrategyId());
		order.setInsuranceCategory(orderRequest.getPlanCategory());
		order.setIsNeedUploadImg(planStrategy.getUploadPhoto()+"");//是否需要上传影像
		order.setIsNeedSign(planStrategy.getNeedSign()+"");//是否需要签名
		order.setInvoiceFlag(orderRequest.getInvoiceFlag());//发票标识
		order.setOrderSource(orderRequest.getOrderSource());//订单来源
		order.setInsuranceCount(getInsuredCount(orderRequest.getMetaDataList()));
		order.setNumberOfCopys(orderRequest.getFormulaSize() == null ? getNumberOfCopies(orderRequest) : orderRequest.getFormulaSize());
		if(!StringUtils.isBlank(orderRequest.getIsAgent())) {
			order.setIsAgent(Integer.parseInt(orderRequest.getIsAgent()) );
		}
		if(StringUtils.isNotBlank(orderRequest.getRelationPolicyNo())) {
			order.setRelationPolicyNo(orderRequest.getRelationPolicyNo());
		}
		if("3".equals(user.getLoginFlag())) {
			order.setPlatformFlag("1");
			order.setPlatformUserCode(userCode);
		}
		if(orderRequest.getAgentInfo() != null && "1".equals(orderRequest.getIsAgent())) {
			order.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			order.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
		}
		return order;
	}
	
	/**
	 * 更新业务信息
	 * 1:投保人 2:被保险人 3:车主关键联系人 4:关键联络人 5:受益人
	 * @param orderRequest
	 */
	public void updateBizData(OrderRequest orderRequest,RequestBody request,Response response) {
		//先删除原有记录
		Order order = (Order)(orderRequest.getMap().get("order"));
		Long orderId = order.getId();
		policyCustomerMapper.deleteByOrderId(orderId);
		
		String isIssueAfterPay = "Y";
		String mobile = null;
		String applicantName = null;
		String handlerCode = null;
		String handlerName = null;
		String agentCode = null;
		String agentName = null;
		String agreementNo = null;
		String agreementName = null;
		String handler2Code = null;
		String businessSourceCode = null;
		String businessSource2Code = null;
		String businessAttribute = null;
		String policyNature = null;
		
		String sumInsured = null;
		String duePremium = null;
		Double vat = null;
		Double beforeVatPremium = null;
		if(orderRequest.getAgentInfo() != null) {
			agreementName = orderRequest.getAgentInfo().getAgreementName();
			agentName = orderRequest.getAgentInfo().getAgentName();
		}
		//更新头被保人信息  t_noncar_policy_customer
		if(request instanceof AccidentQuoteRequest) {
			//意外 健康险
			AccidentQuoteRequest req = (AccidentQuoteRequest) request;
			//投保人信息
			List<com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCustomer>  customerList = req.getPolicyCustomerList();
			
			applicantName = customerList.get(0).getCustomerName();
			handlerCode = req.getHandlerCode();
			handlerName = req.getHandlerName();
			handler2Code = req.getBelongToHandler2Code();
			agentCode = req.getAgentCode();
			agreementNo = req.getAgreementNo();
			
			businessSourceCode = req.getBusinessSourceCode();
			businessSource2Code = req.getBusinessSource2Code();
			businessAttribute = req.getBusinessAttribute();
			policyNature = req.getPolicyNature();
			
			for (com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyCustomer policyCustomer : customerList) {
				com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer customer = new com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer();
				BeanUtils.copyProperties(policyCustomer, customer);
				customer.setOrderId(orderId);
				mobile = policyCustomer.getIndiMobile()== null ? policyCustomer.getTel() : policyCustomer.getIndiMobile();
				policyCustomerMapper.insertSelective(customer);
			}
			
			if( req.getPolicyLobList() != null && req.getPolicyLobList().get(0) != null && req.getPolicyLobList().get(0).getPolicyRiskList() != null && req.getPolicyLobList().get(0).getPolicyRiskList().get(0) != null && req.getPolicyLobList().get(0).getPolicyRiskList().get(0).getPersonInsuredList()!=null) {
				List<com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured> personInsuredList = req.getPolicyLobList().get(0).getPolicyRiskList().get(0).getPersonInsuredList();
				for (com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured personInsured : personInsuredList) {
					com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer customer = new com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer();
					BeanUtils.copyProperties(personInsured, customer);
					customer.setOrderId(orderId);
					policyCustomerMapper.insertSelective(customer);
				}
			}
			
			//份数//保障人数
			//insuredCount = req.getinsu
			//是否见费出单
			isIssueAfterPay = req.getIsIssueAfterPay();
			
		}else if (request instanceof PropertyTrialRequest){
			PropertyTrialRequest req = (PropertyTrialRequest) request;
			List<PolicyCustomer> policyCustomerList = req.getPolicyCustomerList();
			
			//投保人手机号
			mobile = policyCustomerList.get(0).getIndiMobile();
			applicantName = policyCustomerList.get(0).getCustomerName();
			handlerCode = req.getBelongToHandlerCode();
			handler2Code = req.getBelongToHandler2Code();
			agentCode = req.getAgentCode();
			agreementNo = req.getAgreementNo();
			
			if(orderRequest.getAgentInfo() != null) {
				agreementName = orderRequest.getAgentInfo().getAgreementName();
				agentName = orderRequest.getAgentInfo().getAgentName();
			}
			businessSourceCode = req.getBusinessSourceCode();
			businessSource2Code = req.getBusinessSource2Code();
			businessAttribute = req.getBusinessAttribute();
			policyNature = req.getPolicyNature();
			
			for (PolicyCustomer policyCustomer : policyCustomerList) {
				com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer customer = new com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer();
				BeanUtils.copyProperties(policyCustomer, customer);
				customer.setOrderId(orderId);
				if("1".equals(customer.getCustomerRoleCode())) {
					mobile = policyCustomer.getIndiMobile()== null ? policyCustomer.getTel() : policyCustomer.getIndiMobile();
					customer.setIndiMobile(mobile);
				}
				policyCustomerMapper.insertSelective(customer);
			}
			
			//份数//保障人数
			//是否见费出单
			isIssueAfterPay = req.getIsIssueAfterPay();
		}
		
		//更新订单
		//Order order = orderMapper.selectByPrimaryKey(orderId);
		if(response != null ) {
			if(response.getResponseBody() instanceof AccidentQuoteResponse) {
				AccidentQuoteResponse res = (AccidentQuoteResponse) response.getResponseBody();
				order.setInsureNo(res.getProposalNo());
				order.setOrderStatus(InsuranceApplicationStatus.DAIHEBAO);
				order.setPolicyNature(res.getPolicyNature());
				try {
					vat = res.getVat().doubleValue();
					beforeVatPremium =  res.getBeforeVatPremium().doubleValue();
					sumInsured = res.getSumInsured();
					duePremium = res.getDuePremium();
					order.setSumAmount(BigDecimal.valueOf(Double.parseDouble(sumInsured)));
					order.setSumPremium(BigDecimal.valueOf(Double.parseDouble(duePremium)));
					/*
					 * List<PolicyCoverage> list =
					 * res.getPolicyLobList().get(0).getPolicyRiskList().get(0).getInsuredGroupList(
					 * ).get(0).getPolicyCoverageList(); if(list != null) {
					 * orderCtMapper.deleteByOrderId(orderId); for (PolicyCoverage policyCoverage :
					 * list) { OrderCt ct = new OrderCt();
					 * ct.setClauseCode(policyCoverage.getClauseCode());
					 * ct.setClauseName(policyCoverage.getClauseName()); ct.setBeforeVatPremium(new
					 * BigDecimal(policyCoverage.getBeforeVatPremium())); ct.setVat((new
					 * BigDecimal(policyCoverage.getVat())));
					 * ct.setCoverageName(policyCoverage.getKindName());
					 * ct.setKindCode(policyCoverage.getKindCode());
					 * ct.setInsured(BigDecimal.valueOf(policyCoverage.getSumInsured()));
					 * ct.setDuePremium(BigDecimal.valueOf(policyCoverage.getDuePremium()));
					 * ct.setOrderId(orderId); orderCtMapper.insertSelective(ct); } }
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(response.getResponseBody() instanceof PropertyTrialResponse) {
				PropertyTrialResponse res = (PropertyTrialResponse) response.getResponseBody();
				order.setInsureNo(res.getProposalNo());
				order.setOrderStatus(InsuranceApplicationStatus.DAIHEBAO);
				order.setPolicyNature(res.getPolicyNature());
				vat = res.getVat();
				beforeVatPremium = res.getBeforeVatPremium();
				sumInsured = res.getSumInsured()+"";
				duePremium = res.getDuePremium()+"";
				order.setSumAmount(BigDecimal.valueOf(Double.parseDouble(sumInsured)));
				order.setSumPremium(BigDecimal.valueOf(Double.parseDouble(duePremium)));
				/*
				try {
					List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk> Risklist =  res.getPolicyLobList().get(0).getPolicyRiskList();
					List<SharedCoverageGroup> groupList =  res.getPolicyLobList().get(0).getSharedCoverageGroupList();
					orderCtMapper.deleteByOrderId(orderId);
					for (com.ccic.salesapp.noncar.dto.request.noncar.PolicyRisk risk : Risklist) {
						String insuredItemName = risk.getInsuredItemName();
						
						List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> coverageList = risk.getPolicyCoverageList();
						for (com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage coverage :coverageList) {
							String customFormTitle = coverage.getCustomFormTitle();
							String clauseCode = coverage.getClauseCode();
							List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> scoverageList =coverage.getPolicyCoverageList();
							for (com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage policyCoverage : scoverageList) {
								OrderCt ct = new OrderCt();
								ct.setClauseCode(clauseCode);
								ct.setBeforeVatPremium(BigDecimal.valueOf(policyCoverage.getDuePremium()));
								ct.setVat(BigDecimal.valueOf(policyCoverage.getVat()));
								ct.setInsuredItemName(insuredItemName);
								ct.setClauseName(customFormTitle);
								ct.setCoverageName(policyCoverage.getCustomFormTitle());
								ct.setKindCode(policyCoverage.getProductElementCode());
								ct.setInsured(BigDecimal.valueOf(policyCoverage.getSumInsured()));
								ct.setDuePremium(BigDecimal.valueOf(policyCoverage.getDuePremium()));
								ct.setOrderId(orderId);
								orderCtMapper.insertSelective(ct);
							}
						}
					}
					
					for (SharedCoverageGroup group : groupList) {
						
						List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> coverageList = group.getPolicyCoverageList();
						for (com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage coverage :coverageList) {
							String customFormTitle = coverage.getCustomFormTitle();
							String clauseCode = coverage.getClauseCode();
							List<com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage> scoverageList =coverage.getPolicyCoverageList();
							for (com.ccic.salesapp.noncar.dto.request.noncar.PolicyCoverage policyCoverage : scoverageList) {
								OrderCt ct = new OrderCt();
								ct.setClauseCode(clauseCode);
								ct.setBeforeVatPremium(BigDecimal.valueOf(policyCoverage.getDuePremium()));
								ct.setVat(BigDecimal.valueOf(policyCoverage.getVat()));
								ct.setInsuredItemName(policyCoverage.getInsuredName());
								ct.setClauseName(customFormTitle);
								ct.setCoverageName(policyCoverage.getCustomFormTitle());
								ct.setKindCode(policyCoverage.getProductElementCode());
								ct.setInsured(BigDecimal.valueOf(policyCoverage.getSumInsured()));
								ct.setDuePremium(BigDecimal.valueOf(policyCoverage.getDuePremium()));
								ct.setOrderId(orderId);
								orderCtMapper.insertSelective(ct);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		}
		
		//是否见费出单
		if("Y".equals(isIssueAfterPay)) {
			order.setIsIssueAfterPay("1");
		}else {
			order.setIsIssueAfterPay("0");
		}
		order.setHandlerCode(handlerCode);
		order.setHandlerCode2(handler2Code);
		order.setAgentCode(agentCode);
		order.setAgreementName(agreementName);
		order.setAgreementNo(agreementNo);
		order.setHandlerName(handlerName);
		order.setBusinessAttribute(businessAttribute);
		order.setBusinessSourceCode(businessSourceCode);
		order.setBusinessSource2Code(businessSource2Code);
		order.setPolicyNature(policyNature);
		order.setAgentName(agentName);
		order.setBeforeVatPremium(beforeVatPremium);
		order.setVat(vat);
		order.setMobile(mobile);
		order.setApplicantName(applicantName);
		orderMapper.updateByPrimaryKeySelective(order);
		orderRequest.getMap().put("order", order);
		
	}
	
	/***
	 * 保存业务日志
	 * @return
	 */
	public int saveBizLog(BussLog bussLog) {
		
		return bussLogMapper.insert(bussLog);
	}
	
	
	/***
	 * 保存业务日志
	 * @return
	 */
	public int saveApiLog(ApiLog apiLog) {
		return apiLogMapper.insert(apiLog);
	}
	
	
	public HttpResult formationOrderRequestJson() {
		return null;
	} 
	

	/**
	 * 组织财产险产品信息
	 * @param orderRequest
	 * @return
	 */
	public List<Product> getNocarProductList(OrderRequest orderRequest){
		List<Product> list = orderRequest.getProductList();
		List<Product> result  = new ArrayList<Product>();
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		for (Product product : list) {
			if(map.get(product.getClauseCode()) != null) {
			  List<String> kindcodes =	map.get(product.getClauseCode());
			  kindcodes.add(product.getKindCode());
			  map.put(product.getClauseCode(),kindcodes);
			}else{
				List<String> kindcodes = new ArrayList<String>();
				kindcodes.add(product.getKindCode());
				map.put(product.getClauseCode(),kindcodes);
			}
		}
		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			Product p =new Product();
			String key = (String) iterator.next();
			p.setClauseCode(key);
			p.setKindCodes(map.get(key));
			result.add(p);
		}
		
		
		return result;
	}
	
	@Override
	public boolean isBillAfterPay(Long planId,String comCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("planId", planId);
		map.put("comCode", comCode);
		long isBillAfterPay =  orderMapper.isBillAfterPay(map);
		if(isBillAfterPay == 0l) {
			return false;
		}
		return true;
	}
	
	
	/***
	 * 核保状态查询
	 * @param insureNo
	 * @return
	 */
	public HttpResult underwriteStatusInquiry(String insureNo){
		
		return null;
	}
	
	/**
	 * 在构建请求报文前对录入参数进行检查
	 */
	private void checkBeforeRequestBuild(OrderRequest orderRequest) throws PlatformBaseException {
		//起期可调整范围
		checkStartDate(orderRequest);
		//投保份数
		checkNumberOfCopies(orderRequest);
		//保障期限
	}
	
	/**
	 * 在构建请求报文后对参数进行检查
	 */
	private void checkAfterRequestBuild(OrderRequest orderRequest) throws PlatformBaseException {
		//投保年龄
		checkInsuredAge(orderRequest);
		//有无社保
		checkMedicalInsuranceCode(orderRequest);
	}
	
	//若方案配置需要有社保检查被保人社保是否有社保
	private void checkMedicalInsuranceCode(OrderRequest orderRequest) throws PlatformBaseException{
	}
	
	//投保年龄检查
	private void checkInsuredAge(OrderRequest orderRequest) throws PlatformBaseException{
		//2:周岁 1 天
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		if(planInfo.getInsuredAgeLowerlimit() == null ||  planInfo.getInsuredAgeUpperlimit() == null) {
			return ;
		}
		Date lowerLimit = calDate(orderRequest.getStartDate(), planInfo.getInsuredAgeLowertype(), planInfo.getInsuredAgeLowerlimit());
		Date upperLimit = calDate(orderRequest.getStartDate(), planInfo.getInsuredAgeUppertype(), planInfo.getInsuredAgeUpperlimit());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("0".equals(orderRequest.getPlanCategory())) {
			AccidentQuoteRequest req = (AccidentQuoteRequest)orderRequest.getMap().get("request");
			if( req.getPolicyLobList() != null && req.getPolicyLobList().get(0) != null && req.getPolicyLobList().get(0).getPolicyRiskList() != null && req.getPolicyLobList().get(0).getPolicyRiskList().get(0) != null && req.getPolicyLobList().get(0).getPolicyRiskList().get(0).getPersonInsuredList()!=null) {
				List<com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured> personInsuredList = req.getPolicyLobList().get(0).getPolicyRiskList().get(0).getPersonInsuredList();
				for (com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured personInsured : personInsuredList) {
					if(personInsured != null && StringUtils.isNotBlank(personInsured.getDateOfBirth())) {
						Date birth = null;
						try {
							birth = sdf.parse(personInsured.getDateOfBirth());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(lowerLimit.before(birth) || upperLimit.after(birth)) {
							throw new PlatformBaseException("该方案要求被保人年龄在"+planInfo.getInsuredAgeLowerlimit()+ ageUnit(planInfo.getInsuredAgeLowertype())+"-"+planInfo.getInsuredAgeUpperlimit()+ageUnit(planInfo.getInsuredAgeUppertype())+",请检查被保人"+ personInsured.getCustomerName()+"出生日期是否在"+sdf.format(upperLimit)+"与"+sdf.format(lowerLimit)+"之间",0);
						}
					}
				}
			}
		}
		
	}
	
	private String ageUnit(String type) {
		if("1".equals(type)) {
			return "天";
		}
		if("2".equals(type)) {
			return "周岁";
		}
		return "";
	}
	
	
	private Date calDate(String startDate,  String type, int limit ) {
		Integer t = null;
		if("2".equals(type)) {
			t = Calendar.YEAR;
		}else if("1".equals(type)) {
			t = Calendar.DATE;
		}
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(t, -1*limit);
		return cal.getTime();
	}
	
	/**
	 * 检查起保日期
	 * @param orderRequest
	 * @return
	 */
	private void checkStartDate(OrderRequest orderRequest) throws PlatformBaseException {
		PlanStrategy planStrategy = (PlanStrategy)orderRequest.getMap().get("planStrategy");
		if(planStrategy.getPolicyStartRange() != null && planStrategy.getPolicyStartRange() > 0) {
			Calendar startDate = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startDate.setTime(sdf.parse(orderRequest.getStartDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, planStrategy.getPolicyStartRange());
			
			if(cal.compareTo(startDate) < 0) {
				throw new PlatformBaseException( "该方案起保日期最晚可调整至"+sdf.format(cal.getTime()),0);
			}
		}
	}
	
	
	/**
	 * 检查起保日期
	 * @param orderRequest
	 * @return
	 */
	private void checkStartEndDate(OrderRequest orderRequest) throws PlatformBaseException {
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		planInfo.getGiPeriodType();//
		planInfo.getGiCoveragePeriod();
	}
	
	//检查份数
	private void  checkNumberOfCopies(OrderRequest orderRequest) throws PlatformBaseException{
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		int n = getNumberOfCopies(orderRequest);
		planInfo.getCopyMax();
		planInfo.getCopyMin();
		if(n > planInfo.getCopyMax() ) {
			throw new PlatformBaseException( "该方案最大投保份数"+planInfo.getCopyMax(),0);
		}
		if( n <  planInfo.getCopyMin()) {
			throw new PlatformBaseException( "该方案最小投保份数"+planInfo.getCopyMin(),0);
		}
	}
	
	private int getNumberOfCopies(OrderRequest orderRequest) {
		int c = 1;
		List<Element> elements = orderRequest.getMetaDataList();
		List<Long> computeIds = (List<Long>)orderRequest.getMap().get("computeIds");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (Element element : elements) {
			if(element.getSubmitName() != null && element.getSubmitName().indexOf("numberOfCopies") > -1) {
				if(computeIds.contains(element.getId())) {
					map.put("compute", Integer.parseInt(element.getValue()));
				}else {
					map.put("notCompute", Integer.parseInt(element.getValue()));
				}
			}
		}
		
		if(map.containsKey("notCompute")) {
			return map.get("notCompute");
		}
		
		if(map.containsKey("compute")) {
			return map.get("compute");
		}
		
		return c;
	}
	
	
	/**
	 * 获取提交节点数组索引
	 * @param map
	 * @param submitName
	 * @return
	 */
	private int getArrayIndex(Map<String,Object> map,String group ,String submitName,int index) {
		if(map == null) {
			map = new HashMap<String,Object>();
		}
		String item = new String(submitName);
		
		String nodeKey = item.split("\\[")[0]+ group;
		String indexKey = nodeKey+"/" +index;
		Integer offset = (Integer)map.get(nodeKey);
		if(offset == null) {
			map.put(nodeKey, 0);
		}
		
		if(offset == null) {
			map.put(indexKey, 1);
		}
		
		Map<String,HashSet<String>> groupMap = (Map<String, HashSet<String>>) map.get("groupMap");
		if(groupMap == null) {
			groupMap = new HashMap<String,HashSet<String>>();
		}
		HashSet<String> set = groupMap.get(item.split("\\[")[0]);
		if(set == null ) {
			set = new HashSet<String>();
		}
		set.add(group);
		groupMap.put(item.split("\\[")[0], set);
		map.put("groupMap", groupMap);
		int t = 0;
		for (String groupItem : set) {
			t += (Integer)map.get(item.split("\\[")[0]+groupItem+"/"+index);
		}
		t = t -1;
		log.info(submitName+":"+((Integer)map.get(nodeKey) + t ) );
		log.info("map:"+map );
		return ((Integer)map.get(nodeKey) + t );
	}
	
	/**
	 * 统计被保人数量
	 * @return
	 */
	private int getInsuredCount(List<Element> elementList) {
		int c = 0;
		for (Element element : elementList) {
			//非空  包含角色字段 角色未被保人 附属被保险人
			if(StringUtils.isNotBlank(element.getSubmitName()) && element.getSubmitName().contains("customerRoleCode") && ("2".equals(element.getValue()) || "6".equals(element.getValue()) ) ) {
				c++;
			}
		}
		return c;
	}
	
	
	/**
	 * 获取总被保人数量//座位数
	 * @return
	 */
	private int getTotalInsuredCount(List<Element> elementList) {
		int c = 0;
		for (Element element : elementList) {
			//非空  包含角色字段 角色未被保人 附属被保险人
			if(StringUtils.isNotBlank(element.getSubmitName()) && element.getSubmitName().contains("totalInsuredCount") ) {
				try {
					c = Integer.parseInt(element.getValue());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return c;
	}
	
	
	
	public AccidentQuoteRequest getHeaHistoryPolicyInfo(String PolicyNo) throws Exception {
		 return accidentQuoteService.getHistoryPolicyInfo(PolicyNo);
	}
	
	public void getNoncarHistoryPolicyInfo() {
		
	}
	
	
	@Override
	public HttpResult<Object> underwriting(String insureNo) {
		HttpResult<Object> result = HttpResult.error(0, "未知");
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("insureNo", insureNo);
		Order order = orderMapper.selectByInsureNo(map);
		
		if("1".equals(order.getIsNeedSign()) &&  !"1".equals(order.getSignStatus())) {
			result.setCode("2");//需要签名
			result.setMsg("请先完成签名");
			return result;
		}
		
		if("1".equals(order.getIsNeedUploadImg()) ) {
			List<String> imgList = getNotUploadImages(order.getId());
			if(imgList != null && imgList.size() > 0) {
				result.setCode("3");//需要上传影像
				map.put("notUploadImgList", imgList);
				result.setData(map);
				result.setMsg("请先上传影像"+imgList);
				return result;
			}
		}
		
		//提交核保
		if("0".equals(order.getInsuranceCategory())) {
			Response acciResp = null;
			try {
				acciResp = accidentUnderwritingService.underwriting(order.getProductCode(),insureNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (acciResp.getResponseHead() != null) {
				if(acciResp.getResponseHead().getStatus() == 0){
					order = orderMapper.selectByPrimaryKey(order.getId());
					order.setOrderStatus(InsuranceApplicationStatus.YIHEBAO);
					order.setUnderwriteDate(new Date());
					AccidentUnderwritingResponse res = (AccidentUnderwritingResponse)acciResp.getResponseBody();
					if(StringUtils.isNotBlank(res.getPolicyNo())) {
						order.setPolicyNo(res.getPolicyNo());
						order.setPolicyCreateDate(new Date());
						order.setOrderStatus(InsuranceApplicationStatus.SHENGXIAO);
						Map<String, Object> r = new HashMap<String, Object>();
						r.put("policyNo", order.getPolicyNo());
						result.setData(r);
						if(StringUtils.isNotBlank(order.getRelationPolicyNo())) {
							Map params = new HashMap<String, Object>();
							params.put("policyNo",order.getRelationPolicyNo());
							params.put("newPolicyNo", order.getPolicyNo());
							renewalDatabusExpiredDataMapper.updateNewPolicyNoByPolicyNo(params);
						}
					}
					orderMapper.updateByPrimaryKeySelective(order);
					result.setCode("1");
				}else {
					result.setCode("0");
					result.setMsg(acciResp.getResponseHead().getAppMessage());
					log.info("#########意健险---############");
					return result;
				}
			}
		}
		else if("2".equals(order.getInsuranceCategory()))  {
			try {
				// 封装核保数据
				SubmitUnderwriteRequest requestBody = new SubmitUnderwriteRequest();
				requestBody.setProposalNo(insureNo);
				// 提交核保
				Response acciResp = new Response();
				acciResp = underwritService.submitUW(requestBody);
				if(acciResp.getResponseHead()!=null){
					if(acciResp.getResponseHead().getStatus()==0){
						result.setCode("1");
						order = orderMapper.selectByPrimaryKey(order.getId());
						order.setOrderStatus(InsuranceApplicationStatus.YIHEBAO);
						order.setUnderwriteDate(new Date());
						SubmitUnderwriteResponse res = (SubmitUnderwriteResponse)acciResp.getResponseBody();
						if(StringUtils.isNotBlank(res.getPolicyNo())) {
							order.setPolicyNo(res.getPolicyNo());
							order.setPolicyCreateDate(new Date());
							order.setOrderStatus(InsuranceApplicationStatus.SHENGXIAO);
							Map<String, Object> r = new HashMap<String, Object>();
							r.put("policyNo", order.getPolicyNo());
							result.setData(r);
							if(StringUtils.isNotBlank(order.getRelationPolicyNo())) {
								Map params = new HashMap<String, Object>();
								params.put("policyNo",order.getRelationPolicyNo());
								params.put("newPolicyNo", order.getPolicyNo());
								renewalDatabusExpiredDataMapper.updateNewPolicyNoByPolicyNo(params);
							}
						}
						orderMapper.updateByPrimaryKeySelective(order);
					}else {
						result.setCode("0");
						result.setMsg(acciResp.getResponseHead().getAppMessage());
						return result;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("0".equals(order.getIsNeedSign()) && "0".equals(order.getIsNeedUploadImg())) {
			try {
				//核保成功提醒
				payCtl.custAuthSuceedNoticeTwo(new String[] {insureNo});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("1".equals(order.getIsNeedUploadImg())) {
			try {
				//上传影像提醒
				payCtl.custPhotoUploadNoticeTwo(new String[] {insureNo});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
    		
		return result;
	}

	@Override
	public HttpResult orderList(StoreQueryListRequestVO orderRequest) {
		if (StringUtils.isEmpty(orderRequest.getUserCode())) {

			return HttpResult.error(0,"业务员编码不可为空！");
		}
		if (StringUtils.isEmpty(orderRequest.getInsuranceType())) {

			return HttpResult.error(0,"订单类型不可为空！");
		}
		if (StringUtils.isEmpty(orderRequest.getStatus())) {

			return HttpResult.error(0,"订单状态不可为空！");
		}

		OrderListResponse  res = new OrderListResponse();

		try{
			PageHelper.startPage(orderRequest.getPage(), orderRequest.getSize(),true);
			List<StoreInsureInfo> orderList = orderMapper.orderList(orderRequest);
			
			UserToken user = planStrategyService.getAccessTokenByUserCode(orderRequest.getUserCode());
			
			for (StoreInsureInfo storeInsureInfo : orderList) {
				if("1".equals(storeInsureInfo.getOrderSource()) && null != storeInsureInfo.getStrategyId()) {
					PlanStrategy planStrategy = planStrategyMapper.selectByPrimaryKey(Integer.valueOf(storeInsureInfo.getStrategyId()));
					if(planStrategy != null) {
						List<Long> onlinPlanList = null;
						try {
							onlinPlanList = planStrategyService.selectPlanIdsByStrategyCodeUserToken(planStrategy.getCode(),user);
						} catch (PlatformBaseException e) {
							e.printStackTrace();
						}
						storeInsureInfo.setOnlinePlanNum(onlinPlanList == null ? 0 : onlinPlanList.size());
					}else {
						storeInsureInfo.setOnlinePlanNum(0);
					}
					
				}else {
					storeInsureInfo.setOnlinePlanNum(999);
				}
			}
			PageInfo pageInfo = new PageInfo(orderList);
			pageInfo.setList(null);
			res.setOrderList(orderList);
			res.setPageInfo(pageInfo);
			return HttpResult.success(res,1,"门店订单列表查询成功！");
		}catch(Exception e){
			log.error("门店订单列表查询异常！：",e);
			return HttpResult.error(0, "门店订单列表查询失败！");
		}	
	}
	
	

	@Override
	public HttpResult orderDetail(OrderDetailRequest request) {
		Order order = orderMapper.selectByPrimaryKey(request.getOrderId());
		List<OrderDetail> elementList = orderDetailMapper.selectOrderDetailByOrderId(request.getOrderId());
		LinkedHashMap<String,List<OrderDetail>> groupListMap = new LinkedHashMap<String, List<OrderDetail>>();
		for (OrderDetail orderDetail : elementList) {
			if(groupListMap.containsKey(orderDetail.getGroupName())) {
				List<OrderDetail> list = groupListMap.get(orderDetail.getGroupName());
				list.add(orderDetail);
			}else {
				List<OrderDetail> list = new ArrayList<OrderDetail>();
				list.add(orderDetail);
				groupListMap.put(orderDetail.getGroupName(), list);
			}
		}
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
		AgentInfoVO agentInfo = new AgentInfoVO();
		agentInfo.setAgentCode(order.getAgentCode());
		//agentInfo.setAgentName(order.geta);
		agentInfo.setAgreementNo(order.getAgreementNo());
		agentInfo.setAgreementName(order.getAgreementNo());
		//agentInfo.setBusinessNature(order.get);
		response.setAgentInfo(agentInfo);
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
		response.setInvoiceInfo(invoiceInfo);
		response.setOrder(order);
		
		PlanStrategy planStrategy = planStrategyMapper.selectByPrimaryKey(order.getStrategyId().intValue());
		response.setNotUploadImgList(getNotUploadImages(order.getId()));
		TermsAndConditions termsAndConditions = new TermsAndConditions() ;
		FormulaList formulaList = new FormulaList();
		formulaList.setPlanId(order.getPlanId().intValue());
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
		
		
		return HttpResult.success(response);
	}
	
	
}
