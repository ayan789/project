package com.ccic.salesapp.noncar.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.order.ChildProduct;
import com.ccic.salesapp.noncar.dto.order.Clause;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.order.OrderWrapper;
import com.ccic.salesapp.noncar.dto.order.group.sales.ChannelOpInfo;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupRequest;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupRequestBody;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupResponse;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupResponseBody;
import com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCoverage;
import com.ccic.salesapp.noncar.dto.order.group.sales.PolicyForm;
import com.ccic.salesapp.noncar.dto.order.group.sales.PolicyLob;
import com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk.LiabilityPolicyRisk;
import com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk.PersonInsuredPolicyRisk;
import com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk.PolicyRisk;
import com.ccic.salesapp.noncar.dto.order.group.sales.policy.risk.PropertyPolicyRisk;
import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salesapp.noncar.dto.order.response.GroupOrderResponse;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderCtMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrgBranchMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PolicyCustomerMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesCoverageMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanChildMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;
import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesCoverage;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlan;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlanChild;
import com.ccic.salesapp.noncar.service.GroupOrderRequestService;
import com.ccic.salesapp.noncar.service.GroupOrderService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.utils.OrderUtil;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.StringUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupOrderServiceImpl implements GroupOrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	
	@Autowired
	OrderCtMapper orderCtMapper;
	
	@Autowired
	PolicyCustomerMapper policyCustomerMapper;
	
	@Autowired
	SalesPlanMapper salesPlanMapper;
	
	@Autowired
	SalesPlanChildMapper salesPlanChildMapper;
	
	@Autowired
	SalesCoverageMapper salesCoverageMapper;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;
	
	SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;
	
	@Autowired
	GroupOrderRequestService groupOrderRequestService;
	
	@Autowired
	OrgBranchMapper orgBranchMapper;
	
	String isPay = "Y";

	/**
	 * 试算/暂存/下单
	 * @param request
	 * @return
	 */
	@Override
	public HttpResult placeOrder(GroupOrderRequest request) {
		checkRequest(request);
		UserToken user = new UserToken();
		user = planStrategyService.getAccessTokenByUserCode(request.getUserCode());
		request.getMap().put("user",user);
		if("price".equals(request.getAction())) {
			return price(request);
		}else  {
			return order(request);
		}
		
	}
	
	/**
	 * 组合方案试算
	 * @param request
	 * @return
	 */
	private HttpResult price(GroupOrderRequest request) {
		GroupOrderResponse response =  new GroupOrderResponse();
		if(null == getInsuredCustomer(request) ) {
			throw new PlatformBaseException("被保人信息不能为空",0);
		}
		
		GroupRequest groupRequest = buildGroupRequest(request);
		try {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			GroupResponse groupResponse =  groupOrderRequestService.requestPrice(groupRequest, uuid);
			if(groupResponse != null && groupResponse.getResponseBody() != null ) {
				GroupResponseBody resbody = (GroupResponseBody) groupResponse.getResponseBody();
				HashMap<String, Double> premiumMap = new HashMap<String, Double>();
				String salesPlanCode = resbody.getSalesPlanCode();
				for (PolicyLob policyLob : resbody.getPolicyLobList()) {
					Map childQuery = new HashMap<String, Object>();
					childQuery.put("salesPlanCode",salesPlanCode);
					childQuery.put("childPlanCode", policyLob.getChildPlanCode());
					childQuery.put("groupPlanCode", resbody.getGroupPlanCode());
					SalesPlanChild salesPlanChild  = salesPlanChildMapper.selectByChildPlanCode(childQuery);
					if(premiumMap.get(salesPlanChild.getProductCode()) == null) {
						premiumMap.put(salesPlanChild.getProductCode(), policyLob.getDuePremium().doubleValue());
					}else {
						premiumMap.put(salesPlanChild.getProductCode(), premiumMap.get(salesPlanChild.getProductCode()) + policyLob.getDuePremium().doubleValue());
					}
				}
				response.setPremiumMap(premiumMap);
				response.setDuePremium(resbody.getPremiumBeforeDiscount().doubleValue());
				response.setDiscountPremium(resbody.getDuePremium().doubleValue());
				return HttpResult.success(response);
			}else {
				throw new PlatformBaseException("试算接口调用异常", 0);
			}
		} catch (Exception e) {
			throw new PlatformBaseException(e.getMessage(), 0);
		}
	}
	
	/**
	 * 计划书生成/暂存/下单
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	private HttpResult order(GroupOrderRequest orderRequest) {
		GroupOrderResponse response =  new GroupOrderResponse();
		response.setDuePremium(1000.0);
		response.setDiscountPremium(100.0);
		UserToken user = new UserToken();
		user = planStrategyService.getAccessTokenByUserCode(orderRequest.getUserCode());
		orderRequest.getMap().put("user",user);
		saveBizData(orderRequest);
		response.setOrder((Order)orderRequest.getMap().get("order"));
		response.setAgentInfo(orderRequest.getAgentInfo());
		response.setCustomerList(orderRequest.getCustomerList());
		response.setChildProductList(orderRequest.getChildProductList());
		if("temp".equals(orderRequest.getAction())) {
			return HttpResult.success(response);
		}else if("plan".equals(orderRequest.getAction())) {
			return HttpResult.success(response);
		}else if("order".equals(orderRequest.getAction())) {
			String orderNo = (String) orderRequest.getMap().get("orderNo");
			GroupRequest request = buildGroupRequest(orderRequest);
			try {
				GroupResponse groupResponse =  groupOrderRequestService.requestOrder(request, orderNo);
				if(groupResponse != null && groupResponse.getResponseBody() != null ) {
					GroupResponseBody resbody = (GroupResponseBody) groupResponse.getResponseBody();
					String submissionNo = null;
					for (PolicyLob policyLob : resbody.getPolicyLobList()) {
						OrderWrapper orderWrapper = getOrderFromOrderWrapperListByChildPlanCode(policyLob.getChildPlanCode(),orderRequest);
						if(orderWrapper != null) {
							Order order =  orderWrapper.getOrder();
							order.setInsureNo(policyLob.getProposalNo());
							order.setPolicyNo(policyLob.getPolicyNo());
							//order.setSumAmount(policyLob.get);
							order.setSumPremium(policyLob.getDuePremium());
							order.setPremiumBeforeDiscount(policyLob.getPremiumBeforeDiscount());
							//order.setOrderStatus(InsuranceApplicationStatus.DAIHEBAO);
							orderMapper.updateByPrimaryKeySelective(order);
							if(StringUtils.isNotBlank(policyLob.getSubmissionNo())) {
								submissionNo = policyLob.getSubmissionNo();
							}
							//更新责任保额
							/*
							 * for (PolicyRisk policyRisk : policyLob.getPolicyRiskList()) { for
							 * (PolicyCoverage policyCoverage : policyRisk.getPolicyCoverageList()) {
							 * OrderCt orderCt = policyCoverage.get } }
							 */
						}
					}
					//更新主订单信息
					Order order = (Order) orderRequest.getMap().get("order");
					order.setInsureNo(submissionNo);
					order.setSumPremium(resbody.getDuePremium());
					//order.setOrderStatus(InsuranceApplicationStatus.DAIHEBAO);
					order.setPremiumBeforeDiscount(resbody.getPremiumBeforeDiscount());
					orderMapper.updateByPrimaryKeySelective(order);
					response.setDuePremium(resbody.getPremiumBeforeDiscount().doubleValue());
					response.setDiscountPremium(resbody.getDuePremium().doubleValue());
					return HttpResult.success(response);
				}
			} catch (Exception e) {
				throw new PlatformBaseException(e.getMessage(), 0);
			}
		}else {
			throw new PlatformBaseException("无效参数action", 0);
		}
		return HttpResult.success(response);
		
	}
	
	
	private void  checkRequest(GroupOrderRequest orderRequest){
		if("price".equals(orderRequest.getAction())) {
			
		}else if ("temp".equals(orderRequest.getAction())) {
			
		}else if("plan".equals(orderRequest.getAction())) {
			
		}else if("order".equals(orderRequest.getAction())) {
			if(StringUtils.isBlank(orderRequest.getIsAgent())) {
				throw new PlatformBaseException("是否代理不能为空", 0);
			}
			if(StringUtils.isBlank(orderRequest.getBusinessNatureCode())) {
				throw new PlatformBaseException("业务来源不能为空", 0);
			}
			if(null == getCustomer(orderRequest)) {
				throw new PlatformBaseException("请录入投保人信息", 0);
			}
			if(null == getInsuredCustomer(orderRequest)) {
				throw new PlatformBaseException("请录入被保人信息", 0);
			}
			if("1".equals(orderRequest.getIsAgent()) && null == orderRequest.getAgentInfo()) {
				throw new PlatformBaseException("代理出单，请录入代理信息", 0);
			}
			if(!"0".equals(orderRequest.getInvoiceFlag()) && null ==orderRequest.getInvoiceInfo()) {
				throw new PlatformBaseException("请录入开票信息", 0);
			}
			if(StringUtils.isBlank(orderRequest.getStartDate())) {
				throw new PlatformBaseException("请录入保障开始日期", 0);
			}
			if(StringUtils.isBlank(orderRequest.getEndDate())) {
				throw new PlatformBaseException("请录入保障结束日期", 0);
			}
			if(null == orderRequest.getNumberOfCopys()) {
				throw new PlatformBaseException("请录入购买份数", 0);
			}
			if(null == orderRequest.getPremiumBeforeDiscount()) {
				throw new PlatformBaseException("折前保费不能为空", 0);
			}
			if(null == orderRequest.getSumPremium()) {
				throw new PlatformBaseException("折前保费不能为空", 0);
			}
		}else {
			throw new PlatformBaseException("无效参数action", 0);
		}
	}
	
	/**
	 * 获取被保人信息
	 * @param orderRequest
	 * @return
	 */
	private Customer getInsuredCustomer(GroupOrderRequest orderRequest) {
		for (Customer customer : orderRequest.getCustomerList()) {
			if("2".equals(customer.getCustomerRoleCode())) {
				return customer;
			}
		}
		return null;
	}
	
	
	
	/**
	 * 获取投保人信息
	 * @param orderRequest
	 * @return
	 */
	private Customer getCustomer(GroupOrderRequest orderRequest) {
		for (Customer customer : orderRequest.getCustomerList()) {
			if("1".equals(customer.getCustomerRoleCode())) {
				return customer;
			}
		}
		return null;
	}
	
	
	
	/**
	 * 保存业信息，主订单及子订单信息
	 * @param orderRequest
	 */
	@Transactional
	private void saveBizData(GroupOrderRequest orderRequest) {
		UserToken user = new UserToken();
		if(orderRequest.getMap().get("user") != null) {
			user = (UserToken)orderRequest.getMap().get("user");
		}
		try {
			Order order = new Order();
			Long orderId = null;
			if(orderRequest.getOrderId() != null && orderRequest.getOrderId() > 0){
				//查询到订单记录
				order = orderMapper.selectByPrimaryKey(orderRequest.getOrderId());
				if(order!= null && StringUtils.isNotBlank(order.getInsureNo())) {
					throw new PlatformBaseException("当前数据已提交，不可重复提交", 0);
				}
				orderId = order.getId();
				//清除上次暂存数据
				orderRequest.getMap().put("orderNo", order.getOrderNo());
				orderRequest.getMap().put("orderId", order.getId());
				orderRequest.getMap().put("order", order);
				order = buildGroupOrder(orderRequest);
				String orderStatus = "plan".equals(orderRequest.getAction()) ? InsuranceApplicationStatus.PLAN : InsuranceApplicationStatus.ZANCUN;
				order.setOrderStatus(orderStatus);
				if("plan".equals(orderRequest.getAction())) {
					order.setDataTag("plan");
				}
				orderMapper.updateByPrimaryKeySelective(order);
			}else {
				String orderNo = OrderUtil.generatorOrderNo(user.getComCode());
				order = buildGroupOrder(orderRequest);
				order.setOrderNo(orderNo);
				//订单状态
				String orderStatus = "plan".equals(orderRequest.getAction()) ? InsuranceApplicationStatus.PLAN : InsuranceApplicationStatus.ZANCUN;
				order.setOrderStatus(orderStatus);
				//是否删除
				order.setIsDelete("0");
				order.setSignStatus("0");
				if("plan".equals(orderRequest.getAction())) {
					order.setDataTag("plan");
				}
				
				orderMapper.insertSelective(order);
				orderId = order.getId();
				orderRequest.getMap().put("orderNo", orderNo);
				orderRequest.getMap().put("orderId", orderId);
				orderRequest.getMap().put("order", order);
			}
			//重置主订单信息
			resetGroupOrder(orderRequest);
			//重置子订单信息
			resetChildOrder(orderId,orderRequest);
		} catch (Exception e) {
			throw new PlatformBaseException(e, 0);
		}
	}
	
	private void resetGroupOrder(GroupOrderRequest orderRequest) {
		Order order = null;
		if(orderRequest.getMap().get("order") != null) {
			order = (Order)orderRequest.getMap().get("order");
		}
		policyCustomerMapper.deleteByOrderId(order.getId());
		invoiceInfoMapper.deleteByOrderId(order.getId());
		//存储投被保人信息
		for (Customer customer : orderRequest.getCustomerList()) {
			PolicyCustomer policyCustomer = new PolicyCustomer();
			customer.setIndiDateOfBirth(customer.getDateOfBirth());
			BeanUtils.copyProperties(customer, policyCustomer);
			policyCustomer.setOrderId(order.getId());
			policyCustomer.setIndiGenderCode("0".equals(customer.getSex())? "2" : customer.getSex());
			policyCustomer.setSocialSecurityFlag("1".equals(customer.getSocialSecurityFlag())?"Y":"N");
			policyCustomerMapper.insertSelective(policyCustomer);
		}
		
		if(null != orderRequest.getInvoiceInfo()) {
			InvoiceInfo invoiceInfo =   orderRequest.getInvoiceInfo();
			com.ccic.salesapp.noncar.dto.InvoiceInfo orderInvoiceInfo = new com.ccic.salesapp.noncar.dto.InvoiceInfo();
			BeanUtils.copyProperties(invoiceInfo, orderInvoiceInfo);
			orderInvoiceInfo.setTaxEmail(invoiceInfo.getEmail());
			orderInvoiceInfo.setTaxMobile(invoiceInfo.getMobile());
			orderInvoiceInfo.setOrderId(order.getId());
			invoiceInfoMapper.insert(orderInvoiceInfo);
		}
	}
	
	
	/**
	 * 重置子订单信息
	 * @param groupOrderId
	 * @param orderRequest
	 * @throws Exception
	 */
	private void resetChildOrder(Long groupOrderId,GroupOrderRequest orderRequest) throws Exception {
		List<Order> childOrderList = orderMapper.selectByRelationOrderId(groupOrderId);
		for (Order order : childOrderList) {
			orderCtMapper.deleteByOrderId(order.getId());
			policyCustomerMapper.deleteByOrderId(order.getId());
			orderMapper.deleteByPrimaryKey(order.getId());
		}
		
		List<OrderWrapper> orderWrapperList =  buildGroupOrderWrapperList(orderRequest);
		for (OrderWrapper orderWrapper : orderWrapperList) {
			saveOrder(orderWrapper);
		}
		orderRequest.getMap().put("orderWrapperList", orderWrapperList);
	}
	
	/**
	 * 
	 * @param childPlanCode
	 * @param orderRequest
	 * @return
	 */
	private OrderWrapper getOrderFromOrderWrapperListByChildPlanCode(String childPlanCode,GroupOrderRequest orderRequest) {
		List<OrderWrapper> orderWrapperList =  (List<OrderWrapper>) orderRequest.getMap().get("orderWrapperList");
		for (OrderWrapper orderWrapper : orderWrapperList) {
			if(childPlanCode.equals(orderWrapper.getOrder().getPlanCode())) {
				return orderWrapper;
			}
		}
		return null;
	}
	
	/***
	 * 保存订单及其附加信息
	 * @param orderWrapper
	 */
	private void  saveOrder(OrderWrapper orderWrapper) {
		//保存订单记录
		orderMapper.insertSelective(orderWrapper.getOrder());
		//存储责任信息
		for (OrderCt orderCt : orderWrapper.getOrderCtList()) {
			orderCt.setOrderId(orderWrapper.getOrder().getId());
			orderCtMapper.insertSelective(orderCt);
		}
		//存储投被保人信息
		for (PolicyCustomer policyCustomer : orderWrapper.getCustomerList()) {
			policyCustomer.setOrderId(orderWrapper.getOrder().getId());
			policyCustomerMapper.insertSelective(policyCustomer);
		}
		//存储开票信息
		if(null != orderWrapper.getInvoiceInfo()) {
			orderWrapper.getInvoiceInfo().setOrderId(orderWrapper.getOrder().getId());
			invoiceInfoMapper.insert(orderWrapper.getInvoiceInfo());
		}
	}
	
	/**
	 * 构建订单包裹类列表
	 * @param orderRequest
	 * @return
	 * @throws Exception
	 */
	private List<OrderWrapper> buildGroupOrderWrapperList(GroupOrderRequest orderRequest) throws Exception{
		List<OrderWrapper> orderWrapperList =new ArrayList<OrderWrapper>();
		for (ChildProduct child : orderRequest.getChildProductList()) {
			SalesPlanChild salesPlanChild =  salesPlanChildMapper.selectByPrimaryKey(child.getChildPlanId());
			if("Y".equals(salesPlanChild.getIsUsed())) {
				orderWrapperList.add(buildGroupOrderWrapper(child,orderRequest));
			}
		}
		return orderWrapperList;
	}
	
	/**
	 * 构建子订单包裹类
	 * @param childProduct
	 * @param orderRequest
	 * @return
	 * @throws Exception
	 */
	private OrderWrapper buildGroupOrderWrapper(ChildProduct childProduct ,GroupOrderRequest orderRequest ) throws Exception {
		OrderWrapper wrapper = new OrderWrapper();
		List<PolicyCustomer> customerList =  new ArrayList<PolicyCustomer>();
		List<OrderCt> orderCtList = new ArrayList<OrderCt>();
		wrapper.setOrder(buildChildOrder(childProduct,orderRequest));
		for (Customer customer : orderRequest.getCustomerList()) {
			PolicyCustomer policyCustomer = new PolicyCustomer();
			customer.setIndiDateOfBirth(customer.getDateOfBirth());
			BeanUtils.copyProperties(customer, policyCustomer);
			policyCustomer.setIndiGenderCode("0".equals(customer.getSex())? "2" : customer.getSex());
			policyCustomer.setSocialSecurityFlag("1".equals(customer.getSocialSecurityFlag())?"Y":"N");
			customerList.add(policyCustomer);
		}
		wrapper.setCustomerList(customerList);
		
		for (ChildProduct product : orderRequest.getChildProductList()) {
			if(product.getPlanCode().equals(childProduct.getPlanCode())) {
				 for (Clause clause : product.getClauseList()) {
					 OrderCt orderCt = new OrderCt();
					 BeanUtils.copyProperties(clause,orderCt);
					 orderCt.setCoverageName(clause.getKindName());
					 orderCt.setRelationId(clause.getCoverageId());
					 orderCt.setRelationType(1);//  2：配置产品出单；1：组合产品出单
					 orderCtList.add(orderCt);
				 } 
				 wrapper.setOrderCtList(orderCtList);
			}
		}
		InvoiceInfo invoiceInfo =   orderRequest.getInvoiceInfo();
		com.ccic.salesapp.noncar.dto.InvoiceInfo orderInvoiceInfo = new com.ccic.salesapp.noncar.dto.InvoiceInfo();
		BeanUtils.copyProperties(invoiceInfo, orderInvoiceInfo);
		orderInvoiceInfo.setTaxEmail(invoiceInfo.getEmail());
		orderInvoiceInfo.setTaxMobile(invoiceInfo.getMobile());
		wrapper.setInvoiceInfo(orderInvoiceInfo);
		
		return wrapper;
	}
	
	/**
	 * 构造主订单
	 * @param orderRequest
	 * @return
	 * @throws ParseException
	 */
	private Order buildGroupOrder(GroupOrderRequest orderRequest) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateTail = " 00:00:00";
		String endDateTail = " 23:59:59";
		UserToken user = new UserToken();
		Order order = new Order();
		if(orderRequest.getMap().get("order") != null) {
			order = (Order)orderRequest.getMap().get("order");
		}
		if(orderRequest.getMap().get("user") != null) {
			user = (UserToken)orderRequest.getMap().get("user");
		}
		order.setUserCode(orderRequest.getUserCode());
		if(StringUtils.isNotBlank(orderRequest.getStartDate())) {
			order.setStartDate(df.parse(orderRequest.getStartDate()+startDateTail));
		}
		if(StringUtils.isNotBlank(orderRequest.getEndDate())) {
			order.setEndDate(df.parse(orderRequest.getEndDate()+endDateTail));
		}
		//方案id
		order.setComCode(user.getComCode());
		order.setpComCode(orgBranchMapper.selectBranchByComCode(user.getComCode()));
		//order.setSumAmount(BigDecimal.valueOf(orderRequest.getSumAmount()));
		order.setSumPremium(BigDecimal.valueOf(orderRequest.getSumPremium()));
		order.setProductCode(orderRequest.getGroupPlanCode());
		order.setPlanCode(orderRequest.getGroupPlanCode());
		order.setStrategyCode(null);
		order.setOperatorCode(user.getUserCode());
		order.setOperatorName(user.getUserName());
		order.setPlanCode(orderRequest.getGroupPlanCode());
		order.setStrategyCode(orderRequest.getSalesPlanCode());
		//SalesPlan salesPlan =  salesPlanMapper.selectByPrimaryKey(orderRequest.getSalesPlanId());
		order.setProductName(orderRequest.getGroupProductName());
		order.setInsuranceCategory("3");//组合方案主订单
		order.setIsNeedUploadImg(null);//是否需要上传影像
		order.setIsNeedSign("1");//是否需要签名
		order.setInvoiceFlag(orderRequest.getInvoiceFlag());//发票标识
		order.setOrderSource(orderRequest.getOrderSource());//订单来源
		order.setInsuranceCount(1);
		order.setNumberOfCopys(1);
		order.setBusinessNatureName(orderRequest.getBusinessNatureName());
		order.setIsGroupPlan("1");
		order.setDataTag("group_main");
		if(null != orderRequest.getPremiumBeforeDiscount()) {
			order.setPremiumBeforeDiscount(BigDecimal.valueOf(orderRequest.getPremiumBeforeDiscount()));
		}
		if(!StringUtils.isBlank(orderRequest.getIsAgent())) {
			order.setIsAgent(Integer.parseInt(orderRequest.getIsAgent()) );
		}
		if(StringUtils.isNotBlank(orderRequest.getRelationPolicyNo())) {
			order.setRelationPolicyNo(orderRequest.getRelationPolicyNo());
		}
		if(orderRequest.getAgentInfo() != null && "1".equals(orderRequest.getIsAgent())) {
			order.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			order.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
			order.setAgentCode(orderRequest.getAgentInfo().getAgentCode());
			order.setAgentName(orderRequest.getAgentInfo().getAgentName());
			order.setAgreementName(orderRequest.getAgentInfo().getAgreementName());
			order.setAgreementNo(orderRequest.getAgentInfo().getAgreementNo());
			order.setHandlerCode2(orderRequest.getAgentInfo().getHandler2Code());
		}
		order.setBusinessSourceCode(orderRequest.getBusinessNatureCode());
		order.setBusinessNatureName(orderRequest.getBusinessNatureName());
		
		Customer customer =  getCustomer(orderRequest);
		order.setApplicantName(customer.getCustomerName());
		order.setMobile(customer.getIndiMobile());
		
		order.setCreateUser(user.getUserCode());
		order.setUpdateUser(user.getUserCode());
		
		String belongToHandlerCode = orderRequest.getUserCode();
		order.setBelongToHandlerCode(belongToHandlerCode);
		order.setHandlerCode(user.getUserCode());
		order.setHandlerName(user.getUserName());
		order.setIsIssueAfterPay("Y".equals(isPay) ? "1" : "0");
		return order;
	}
	
	/**
	 * 构造单个子订单
	 * @param childProduct
	 * @param orderRequest
	 * @return
	 * @throws ParseException
	 */
	private Order buildChildOrder(ChildProduct childProduct ,GroupOrderRequest orderRequest ) throws ParseException{
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateTail = " 00:00:00";
		String endDateTail = " 23:59:59";
		UserToken user = new UserToken();
		Order order = new Order();
		if(orderRequest.getMap().get("user") != null) {
			user = (UserToken)orderRequest.getMap().get("user");
		}
		Order mainOrder = new Order();
		if(orderRequest.getMap().get("order") != null) {
			mainOrder = (Order)orderRequest.getMap().get("order");
		}
		String orderNo = OrderUtil.generatorOrderNo(user.getComCode());
		order.setOrderNo(orderNo);
		order.setUserCode(orderRequest.getUserCode());
		if(StringUtils.isNotBlank(orderRequest.getStartDate())) {
			order.setStartDate(df.parse(orderRequest.getStartDate()+startDateTail));
		}
		if(StringUtils.isNotBlank(orderRequest.getEndDate())) {
			order.setEndDate(df.parse(orderRequest.getEndDate()+endDateTail));
		}
		order.setOrderStatus(InsuranceApplicationStatus.ZANCUN);
		//方案id
		order.setComCode(user.getComCode());
		order.setpComCode(orgBranchMapper.selectBranchByComCode(user.getComCode()));
		//order.setSumAmount(BigDecimal.valueOf(orderRequest.getSumAmount()));
		order.setSumPremium(BigDecimal.valueOf(orderRequest.getSumPremium()));
		order.setProductCode(childProduct.getProductCode());
		order.setPlanCode(childProduct.getPlanCode());
		order.setStrategyCode(orderRequest.getSalesPlanCode());
		order.setOperatorCode(user.getUserCode());
		order.setOperatorName(user.getUserName());
		order.setProductName(childProduct.getChildPlanName());
		if("70000".equals(childProduct.getProductLine())) {
			order.setInsuranceCategory("0");
		}
		if("30000".equals(childProduct.getProductLine())) {
			order.setInsuranceCategory("2");
		}
		order.setIsNeedUploadImg(null);//是否需要上传影像
		order.setIsNeedSign("1");//是否需要签名
		order.setInvoiceFlag(orderRequest.getInvoiceFlag());//发票标识
		order.setOrderSource(orderRequest.getOrderSource());//订单来源
		order.setInsuranceCount(1);
		order.setNumberOfCopys(1);
		order.setIsDelete("0");
		order.setRelationOrderId(mainOrder.getId());
		order.setProductLine(childProduct.getProductLine());
		order.setBusinessNatureName(orderRequest.getBusinessNatureName());
		order.setIsGroupPlan("1");
		order.setDataTag("group_child");
		if(null !=  orderRequest.getPremiumBeforeDiscount()) {
			order.setPremiumBeforeDiscount(BigDecimal.valueOf(orderRequest.getPremiumBeforeDiscount()));
		}
		if(StringUtils.isNotBlank(orderRequest.getRelationPolicyNo())) {
			order.setRelationPolicyNo(orderRequest.getRelationPolicyNo());
		}
		if(!StringUtils.isBlank(orderRequest.getIsAgent())) {
			order.setIsAgent(Integer.parseInt(orderRequest.getIsAgent()) );
		}
		if(orderRequest.getAgentInfo() != null && "1".equals(orderRequest.getIsAgent())) {
			order.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			order.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
			order.setAgentCode(orderRequest.getAgentInfo().getAgentCode());
			order.setAgentName(orderRequest.getAgentInfo().getAgentName());
			order.setAgreementName(orderRequest.getAgentInfo().getAgreementName());
			order.setAgreementNo(orderRequest.getAgentInfo().getAgreementNo());
		}
		order.setBusinessSourceCode(orderRequest.getBusinessNatureCode());
		order.setBusinessNatureName(orderRequest.getBusinessNatureName());
		
		Customer customer =  getCustomer(orderRequest);
		order.setApplicantName(customer.getCustomerName());
		order.setMobile(customer.getIndiMobile());
		
		order.setCreateUser(user.getUserCode());
		order.setUpdateUser(user.getUserCode());
		String belongToHandlerCode = orderRequest.getUserCode();
		order.setBelongToHandlerCode(belongToHandlerCode);
		order.setHandlerCode(user.getUserCode());
		order.setIsIssueAfterPay( "Y".equals(isPay) ? "1" : "0");
		order.setProductName(childProduct.getProductName());
		return order;
	}
	
	
	/**
	 * 构建组合下单请求实体
	 * @param orderRequest
	 * @return
	 */
	private GroupRequest buildGroupRequest(GroupOrderRequest orderRequest) {
		GroupRequest groupRequest = new GroupRequest();
		groupRequest.setRequestHead(buildGroupRequestHead());
		groupRequest.setRequestBody(buildGroupRequestBody(orderRequest));
		return groupRequest;
	}
	
	/**
	 * 构建组合下单请求头
	 * @return
	 */
	private RequestHead buildGroupRequestHead() {
		RequestHead requestHead = new RequestHead();
		requestHead.setConsumerID("supsale");
		requestHead.setConsumerSeqNo(UUID.randomUUID().toString());
		return requestHead;
	}
	
	/**
	 * 构建组合下单请求体
	 * @param orderRequest
	 * @return
	 */
	private GroupRequestBody buildGroupRequestBody(GroupOrderRequest orderRequest) {
		UserToken user = new UserToken();
		if(orderRequest.getMap().get("user") != null) {
			user = (UserToken)orderRequest.getMap().get("user");
		}
		GroupRequestBody body = new GroupRequestBody();
		
		if("price".equals(orderRequest.getAction())) {
			body.setBusinessType("0");
		}
		if("order".equals(orderRequest.getAction())) {
			body.setBusinessType("1");
		}
		
		// 主信息
		body.setBusinessAttribute("0801");// 业务来源属性（替换原先的出单方式，系统来源，系统业务属性）
		body.setBusinessSourceCode(orderRequest.getBusinessNatureCode());// 业务来源1/BusinessNature1，对应归属经办人的业务来源，存放末级的业务原来
		body.setBusinessSource2Code(orderRequest.getBusinessNatureCode());// 业务来源2
		String startDateTail = " 00:00:00";
		String endDateTail = " 23:59:59";

		if(StringUtils.isNotBlank(orderRequest.getStartDate())) {
			body.setEffectiveDate(orderRequest.getStartDate()+startDateTail);// 起保日期/起始生效日期,支持时分
		}
		if(StringUtils.isNotBlank(orderRequest.getEndDate())) {
			body.setExpiryDate(orderRequest.getEndDate()+endDateTail);// 终保日期，保单止期/止保日期/失效日期，不包含这个时间点在内
		}
		body.setProposalDate(datef.format(new Date()));// 投保日期
		body.setIsIssueAfterPay(isPay);// 是否见费出单标志
		body.setGroupPlanCode(orderRequest.getGroupPlanCode());
		body.setSalesPlanCode(orderRequest.getSalesPlanCode());
		body.setChannelByPerson(user.getChannelCode());
		body.setOrgCode(user.getComCode());
		body.setIssueOrgCode(user.getComCode());
		//body.setIssueUserId(user.getUserCode());
		body.setIssueUserCode(user.getUserCode());
		// 经办人，仅在直销业务中使用
		body.setHandlerCode(user.getUserCode());
		//body.setHandlerName(user.getUserName());
		body.setBelongToHandlerCode(user.getUserCode());//归属经办人
		//body.setBelongToHandler2Name(user.getUserName()); // 归属经办人
		body.setBelongToHandler2Code(user.getUserCode()); // 归属经办人
		
		if(StringUtils.isNotBlank(orderRequest.getBusinessNatureCode())) {
			body.setBusinessSourceCode(orderRequest.getBusinessNatureCode());
			body.setBusinessSource2Code(orderRequest.getBusinessNatureCode());
		}
		UserVO uservo = storeProductPlaceUtilService.getHandlerInfo(orderRequest);
		AgentInfoVO agent = null;
		agent = orderRequest.getAgentInfo();
		if (agent != null) {
			if ("1".equals(orderRequest.getIsAgent())) {
				// 代理人ID，仅在非直销业务中使用
				body.setAgentCode(agent.getAgentCode());
				// 代理人协议号
				body.setAgreementNo(agent.getAgreementNo());
				body.setBusinessSourceCode(agent.getBusinessNature());// 业务来源1
				if(StringUtils.isNotBlank(agent.getBusinessNature2())) {
					body.setBusinessSource2Code(agent.getBusinessNature2());//业务来源2
					body.setBelongToHandler2Code(agent.getHandler2Code()); // 经办人2代码
					//body.setBelongToHandler2Name(agent.getHandler2Name()); // 经办人2名称					
				}else {
					body.setBusinessSource2Code(agent.getBusinessNature());//业务来源2
					body.setBelongToHandler2Code(agent.getAgentCode()); // 经办人2代码
					//body.setBelongToHandler2Name(agent.getAgentName()); // 经办人2名称
				}
				// 经办人
				body.setHandlerCode(agent.getAgentCode());
				//body.setHandlerName(agent.getAgentName());
			}
		}
		if(orderRequest.getInvoiceInfo() != null) {
			Map<String ,String> temp = new HashMap<String,String>();
			temp.put("noticeInvoice", "Y"); 
			//body.setTempData(temp);
			
			List<InvoiceInfo> invoiceInfoList = new ArrayList<InvoiceInfo>();
			invoiceInfoList.add(orderRequest.getInvoiceInfo());
			body.setInvoiceInfoList(invoiceInfoList);
		}
		body.setPolicyCustomerList(buildPolicyCustomerList(orderRequest));
		body.setPolicyLobList(buildPolicyLobList(orderRequest));
		body.setChannelOpInfoList(buildChannelOpInfoList(orderRequest));
		body.setPolicyFormList(buildPolicyFormList(orderRequest));
		updateHandlerInfo(body,uservo);
		return body;
	}
	
	/**
	 * 构建特别约定列表
	 * @param orderRequest
	 * @return
	 */
	private List<PolicyForm> buildPolicyFormList(GroupOrderRequest orderRequest) {
		return new ArrayList<PolicyForm>();
	}
	
	
	/**
	 * 构建渠道信息列表
	 * @param orderRequest
	 * @return
	 */
	private List<ChannelOpInfo> buildChannelOpInfoList(GroupOrderRequest orderRequest) {
		List<ChannelOpInfo> channelInfoList = new ArrayList<ChannelOpInfo>();
		//如果是主产品，加到列表中
		for (ChildProduct childProduct : orderRequest.getChildProductList()) {
			SalesPlanChild salesPlanChild =  salesPlanChildMapper.selectByPrimaryKey(childProduct.getChildPlanId());
			if("Y".equals(salesPlanChild.getMasterProduct())) {
				channelInfoList.add(buildChannelOpInfo(childProduct,orderRequest));
			}
		}
		return channelInfoList;
	}
	
	/**
	 * 构建渠道信息
	 * @param childProduct
	 * @param orderRequest
	 * @return
	 */
	private ChannelOpInfo buildChannelOpInfo(ChildProduct childProduct,  GroupOrderRequest orderRequest) {
		UserToken user = new UserToken();
		Order order = new Order();
		if(orderRequest.getMap().get("user") != null) {
			user = (UserToken)orderRequest.getMap().get("user");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ChannelOpInfo channelInfo = new ChannelOpInfo();
		channelInfo.setChannelCode("310073");
		channelInfo.setChannelComCode("310073");
		channelInfo.setChannelComName("移动销售支持");
		channelInfo.setChannelName("大地行");
		channelInfo.setChannelProductCode(childProduct.getProductCode());
		channelInfo.setChannelSeqNo(storeProductPlaceUtilService.getTradeSerialNo());
		//channelInfo.setOperatorCode(user.getUserCode()); // 渠道操作员代码
		channelInfo.setTrxDate(df.format(new Date())); //交易时间
		//channelInfo.setTrxCode("001"); //渠道交易代码
		
		if("1".equals(orderRequest.getIsAgent()) && orderRequest.getAgentInfo() !=null) {
			//channelInfo.setThirdPartyHandlerNo(orderRequest.getAgentInfo().getThirdPartyHandlerNo());
			//channelInfo.setThirdPartyHandlerName(orderRequest.getAgentInfo().getThirdPartyHandlerName());
		}
		return channelInfo;
	}
	
	/**
	 * 构建投被保人客户列表
	 * @param orderRequest
	 * @return
	 */
	private List<com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer> buildPolicyCustomerList(GroupOrderRequest orderRequest) {
		List<com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer> policyCustomerList = new ArrayList<com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer>();
		for (Customer customer : orderRequest.getCustomerList()) {
			policyCustomerList.add(buildPolicyCustomer(customer,orderRequest));
		}
		return policyCustomerList;
	}
	
	/**
	 * 构建客户信息
	 * @param customer
	 * @param orderRequest
	 * @return
	 */
	private com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer buildPolicyCustomer(Customer customer,GroupOrderRequest orderRequest) {
		com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer  policyCustomer = new com.ccic.salesapp.noncar.dto.order.group.sales.PolicyCustomer();
		Customer target =  transformCustomer(customer);
		BeanUtils.copyProperties(target, policyCustomer);
		policyCustomer.setFullAddress(null);
		policyCustomer.setAddress(null);
		return policyCustomer;
	}
	
	
	/**
	 * 构建子产品列表
	 * @param orderRequest
	 * @return
	 */
	private List<PolicyLob>  buildPolicyLobList(GroupOrderRequest orderRequest) {
		List<PolicyLob> policyLobList = new ArrayList<PolicyLob>();
		for (ChildProduct childProduct : orderRequest.getChildProductList()) {
			if(childProduct.getClauseList() != null && !childProduct.getClauseList().isEmpty() ) {
				policyLobList.add(buildPolicyLob(childProduct,orderRequest));
			}
		}
		return policyLobList;
	}
	
	
	/**
	 * 构建产品信息
	 * 
	 * @param childProduct
	 * @param orderRequest
	 * @return
	 */
	private PolicyLob buildPolicyLob(ChildProduct childProduct, GroupOrderRequest orderRequest) {
		PolicyLob policyLob = new PolicyLob();
		SalesPlanChild salesPlanChild =  salesPlanChildMapper.selectByPrimaryKey(childProduct.getChildPlanId());
		policyLob.setIsMainProduct(salesPlanChild.getMasterProduct());
		policyLob.setChildPlanCode(salesPlanChild.getChildPlanCode());
		policyLob.setNumberOfCopies(orderRequest.getNumberOfCopys());
		policyLob.setPolicyRiskList(buildPolicyRiskList(childProduct, orderRequest));
		policyLob.setProductLine(childProduct.getProductLine());
		policyLob.setProductCode(childProduct.getProductCode());
		return policyLob;
	}
	
	
	/**
	 *  32000	船舶保险
	 *  34000	货物运输保险
	 *	40000	信用保险
	 *	37000	企业财产保险
	 *	72000	意外伤害保险
	 *	38000	特殊风险保险
	 *	31000	保证保险
	 *	41000	责任保险
	 *	73000	大病保险
	 *	36000	农业保险
	 *	33000	工程保险
	 *	11000	商业车险
	 *	74000	健康保障委托管理
	 *	35000	家庭财产保险
	 *	18000	交强险
	 *	71000	短期健康保险

	 * @param orderRequest
	 */
	private List<PolicyRisk> buildPolicyRiskList(ChildProduct childProduct,  GroupOrderRequest orderRequest) {
		List<PolicyRisk> policyRiskList = new ArrayList<PolicyRisk>();
		SalesPlanChild salesPlanChild =  salesPlanChildMapper.selectByPrimaryKey(childProduct.getChildPlanId());
		if("Y".equals(salesPlanChild.getIsUsed())) {
			if("30000".equals(childProduct.getProductLine())) {
				for (Clause clause : childProduct.getClauseList()) {
					policyRiskList.add(buildPropertyPolicyRisk(childProduct,clause, orderRequest));
				}
			}else if("30000".equals(childProduct.getProductLine())) {
				for (Clause clause : childProduct.getClauseList()) {
					policyRiskList.add(buildLiabilityPolicyRisk(childProduct,clause, orderRequest));
				}
			}else if("70000".equals(childProduct.getProductLine())) {
				policyRiskList.add(buildPersonInsuredPolicyRisk(childProduct, orderRequest));
			}else {
				throw new PlatformBaseException("不支持的险类"+childProduct.getProductLine(), 0);
			}
		}
		return policyRiskList;
	}
	
	/**
	 * 构建财产险责任信息
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private PolicyRisk buildPropertyPolicyRisk(ChildProduct childProduct ,Clause clause,GroupOrderRequest orderRequest ) {
		PolicyRisk policyRisk = new PropertyPolicyRisk();
		policyRisk =  buildPolicyRiskByClause(childProduct,clause, orderRequest);
		policyRisk.setRegionCode("000000");
		Customer insuredCustomer = getInsuredCustomer(orderRequest);
		insuredCustomer = transformCustomer(insuredCustomer);
		BeanUtils.copyProperties(insuredCustomer, policyRisk);
		policyRisk.setAddress(insuredCustomer.getFullAddress());//调换参数
		policyRisk.setFullAddress(insuredCustomer.getAddress());
		return policyRisk;
	}
	
	/**
	 * 构建责任险标的信息
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private PolicyRisk buildLiabilityPolicyRisk(ChildProduct childProduct ,Clause clause,GroupOrderRequest orderRequest ) {
		PolicyRisk policyRisk = new LiabilityPolicyRisk();
		policyRisk =  buildPolicyRiskByClause(childProduct, clause, orderRequest);
		policyRisk.setAddress(null);
		return policyRisk;
	}
	
	/**
	 * 构建意健险标的信息
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private PolicyRisk buildPersonInsuredPolicyRisk(ChildProduct childProduct ,GroupOrderRequest orderRequest ) {
		PolicyRisk policyRisk = new PersonInsuredPolicyRisk();
		policyRisk =  buildPolicyRisk(childProduct, orderRequest);
		policyRisk.setInsuredName(policyRisk.getCustomerName());
		policyRisk.setAddress(null);
		return policyRisk;
	}
	
	/**
	 * 转核心码表
	 * @param customer
	 * @return
	 */
	private Customer transformCustomer(Customer customer) {
		Customer target = new Customer();
		BeanUtils.copyProperties(customer, target);
		target.setSocialSecurityFlag("1".equals(customer.getSocialSecurityFlag())?"Y":"N");
		target.setIndiGenderCode("1".equals(customer.getSex())?"1":"2");
		return target;
	}
	
	/**
	 * 转前端码表
	 * @param customer
	 * @return
	 */
	private Customer reverseTransformCustomer(Customer customer) {
		Customer target = new Customer();
		BeanUtils.copyProperties(customer, target);
		target.setSocialSecurityFlag("Y".equals(customer.getSocialSecurityFlag())?"1":"0");
		target.setSex("1".equals(customer.getIndiGenderCode())?"1":"0");
		target.setIndiGenderCode("1".equals(customer.getIndiGenderCode())?"1":"0");
		return target;
	}
	
	/**
	 * 构建标的
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private PolicyRisk buildPolicyRisk(ChildProduct childProduct  , GroupOrderRequest orderRequest ) {
		PolicyRisk policyRisk = new PolicyRisk();
		Customer insuredCustomer = getInsuredCustomer(orderRequest);
		insuredCustomer = transformCustomer(insuredCustomer);
		BeanUtils.copyProperties(insuredCustomer, policyRisk);
		policyRisk.setPolicyCoverageList(buildPolicyCoverageList( childProduct,orderRequest));
		String riskType = salesPlanChildMapper.selectRiskTypeByChildPlanId(childProduct.getChildPlanId());
		riskType = StringUtils.isBlank(riskType)? childProduct.getProductLine():riskType;
		policyRisk.setRiskType(riskType);
		//policyRisk.setItemCategory(itemCategory);
		policyRisk.setNumberOfCopies(orderRequest.getNumberOfCopys());
		return policyRisk;
	}
	
	
	/**
	 * 构建财产险标的
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private PolicyRisk buildPolicyRiskByClause(ChildProduct childProduct  ,Clause clause, GroupOrderRequest orderRequest ) {
		PolicyRisk policyRisk = new PolicyRisk();
		Customer insuredCustomer = getInsuredCustomer(orderRequest);
		insuredCustomer = transformCustomer(insuredCustomer);
		BeanUtils.copyProperties(insuredCustomer, policyRisk);
		SalesCoverage  salesCoverage  = salesCoverageMapper.selectByPrimaryKey(clause.getCoverageId());
		clause.setIsMainCoverage(salesCoverage.getMainCt());
		policyRisk.setPolicyCoverageList(buildPolicyCoverageListByClause( childProduct,clause ,orderRequest));
		String riskType = salesPlanChildMapper.selectRiskTypeByChildPlanId(childProduct.getChildPlanId());
		riskType = StringUtils.isBlank(riskType)? childProduct.getProductLine():riskType;
		policyRisk.setRiskType(riskType);
		policyRisk.setItemCategory(salesCoverage.getInsuredType());
		policyRisk.setNumberOfCopies(orderRequest.getNumberOfCopys());
		return policyRisk;
	}
	
	/**
	 * 构建财产险责任列表
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private  List<PolicyCoverage>  buildPolicyCoverageListByClause(ChildProduct childProduct ,Clause clause, GroupOrderRequest orderRequest) {
		List<PolicyCoverage> policyCoverageList  = new ArrayList<PolicyCoverage>();
		policyCoverageList.add(buildPolicyCoverage(clause));
		return policyCoverageList;
	}
	
	
	/**
	 * 构建责任列表
	 * @param clause
	 * @param orderRequest
	 * @return
	 */
	private  List<PolicyCoverage>  buildPolicyCoverageList(ChildProduct childProduct , GroupOrderRequest orderRequest) {
		List<PolicyCoverage> policyCoverageList  = new ArrayList<PolicyCoverage>();
		for (Clause clause : childProduct.getClauseList()) {
			SalesCoverage  salesCoverage  = salesCoverageMapper.selectByPrimaryKey(clause.getCoverageId());
			clause.setIsMainCoverage(salesCoverage.getMainCt());
			policyCoverageList.add(buildPolicyCoverage(clause));
		}
		return policyCoverageList;
	}
	
	/**
	 * 构建单个责任信息
	 * @param clause
	 * @return
	 */
	private PolicyCoverage buildPolicyCoverage(Clause clause ) {
		PolicyCoverage policyCoverage = new PolicyCoverage();
		BeanUtils.copyProperties(clause, policyCoverage);
		policyCoverage.setCoverageCode(clause.getKindCode());
		return policyCoverage;
	}
	
	/**
	 * 更新经办人信息
	 * @param  requestBody GroupRequestBody
	 */
	public void updateHandlerInfo(GroupRequestBody requestBody,UserVO userVO){
		String handlerCode="";
		if(null!=requestBody){
			handlerCode=requestBody.getBelongToHandlerCode();
		}
		
		if("agen".equals(handlerCode.substring(0, 4))){
			if(null!=requestBody && null!=userVO){//意健险
				if(StringUtils.isNotBlank(requestBody.getAgentCode())){//代理信息不为空
					//判断经办人2是否取的默认经办人1
					if(requestBody.getBelongToHandlerCode().equals(requestBody.getBelongToHandler2Code())){
						requestBody.setBelongToHandler2Code(userVO.getUserCode());
						//requestBody.setBelongToHandler2Name(userVO.getUserName());
					}
				}else {
					// agentCode 存在的话  handlerCode取值代理人agentCode，否则同BelongToHandlerCode
					requestBody.setHandlerCode(userVO.getUserCode());//归属经办人
					//requestBody.setHandlerName(userVO.getUserName());//归属经办人
				}
				requestBody.setBelongToHandlerCode(userVO.getUserCode());
				requestBody.setBelongToHandlerName(userVO.getUserName());
				requestBody.setOrgCode(userVO.getCompany());//归属机构
				//requestBody.setIssueOrgCode(userVO.getCompany());//出单机构
			}
		}
	}
	
}
