package com.ccic.salesapp.noncar.service.impl;

import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.controller.GroupPlanCtl;
import com.ccic.salesapp.noncar.controller.PlanFormCtl;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.ForwardPayUrl;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.RecommendRankPlansObj;
import com.ccic.salesapp.noncar.dto.RecommendRankPlansVo;
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
import com.ccic.salesapp.noncar.dto.request.PolicyDto;
import com.ccic.salesapp.noncar.dto.request.PolicyVo;
import com.ccic.salesapp.noncar.dto.request.PrivyVO;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.ClaimStatement;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblem;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblemContent;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.Header;
import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.ImgList;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanFormConstant;
import com.ccic.salesapp.noncar.dto.request.planelement.ProductInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.SafeguardDetail;
import com.ccic.salesapp.noncar.dto.request.planelement.Swipper;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditionsContent;
import com.ccic.salesapp.noncar.dto.response.GetPayUrlResponseVO;
import com.ccic.salesapp.noncar.dto.response.InsureRes;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.PlanDetail2FamilyResp;
import com.ccic.salesapp.noncar.dto.response.RecommendRankPlansObjResp;
import com.ccic.salesapp.noncar.dto.response.RecommendRankPlansRes;
import com.ccic.salesapp.noncar.dto.response.PolicyRes;
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
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanFormMapper;
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
import com.ccic.salessapp.common.utils.StringUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.UnirestUtil;
import com.google.common.collect.Lists;
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
	
	@Transactional(rollbackFor = {RuntimeException.class, Error.class})
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
		String salesPlanCode = "";
		for(GroupSalesPlan groupSalesPlan:groupSalesPlanList) {
			String code = groupSalesPlan.getGroupPlanCode();
			if(code!=null && salesPlanCode!=null) {
				if(!code.equals(salesPlanCode)) {
					map.put("groupPlanCode", code);
					salesPlanMapper.deleteBySalesCoverage(map);
					salesPlanMapper.deleteBySalesPlanChild(map);
					salesPlanMapper.deleteBySalesPlan(map);
					salesPlanMapper.deleteBySalesPlanConfig(map);
				}
				salesPlanCode=code;
			}
		}
		
		for(GroupSalesPlan groupSalesPlan:groupSalesPlanList) {
			String code = groupSalesPlan.getGroupCode();
			if(code!=null && salesPlanCode!=null) {
				if(!code.equals(salesPlanCode)) {
					map.put("groupCode", code);
					salesPlanMapper.deleteBySalesCoverageCarxy(map);
					salesPlanMapper.deleteBySalesPlanChildCarxy(map);
					salesPlanMapper.deleteBySalesPlanCarxy(map);
					salesPlanMapper.deleteBySalesPlanConfigCarxy(map);
				}
				salesPlanCode=code;
			}
		}
		
		
		for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
			buildcomAndChennelConfig(groupSalesPlan);
			salesPlanMapper.insertGroupSalesPlan(groupSalesPlan);
			if(groupSalesPlan.getSalesChildPlans()!=null) {
				for(PlanChild planChild:groupSalesPlan.getSalesChildPlans()) {
					salesplanchildMapper.insertPlanChild(planChild);
					if(planChild.getSalesChildPlanCts()!=null) {
						for(PlanCoverage planCoverage:planChild.getSalesChildPlanCts()) {
							salesCoverageMapper.insertPlanCoverage(planCoverage);
						}
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
		groupPlanVo.setChannelCode(planStrategyService.getChannel(user));
		groupPlanVo.setComCode(user.getComCode());
		String channel = salesPlanMapper.selectChannelBygroupPlanCode(groupPlanVo.getGroupPlanCode());
 		List<GroupSalesPlan> groupSalesPlanList = null;
 		if(StringUtils.isNotBlank(channel)) {
 			groupSalesPlanList = salesPlanMapper.selectSalesPlanBygroupPlanCode(groupPlanVo);
 		}
 		else {
 			groupSalesPlanList = salesPlanMapper.selectSalesPlanBygroupPlanCodeAllChannel(groupPlanVo);
 		}
		if(groupSalesPlanList==null || groupSalesPlanList.size()==0)
			return HttpResult.error( 2, "您目前无权限查看");
		request.getHeader("X-Access-Token");
		String token = request.getHeader("X-Access-Token");
		groupSalesPlanRoot.setToken(token.replaceAll("\"", ""));
		groupSalesPlanRoot.setGroupSalesPlan(groupSalesPlanList);
		for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
			List<PlanChild> planChildList = new ArrayList<PlanChild>();
			planChildList = salesplanchildMapper.selectBySalesPlanChildList(groupSalesPlan.getSalesPlanId());
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
			
			if(1==order.getIsAgent()) {
			    AgentInfoVO agentInfo = new AgentInfoVO();
			    agentInfo.setAgentCode(order.getAgentCode());
			    agentInfo.setAgentName(order.getAgentName());
			    agentInfo.setAgreementNo(order.getAgreementNo());
			    agentInfo.setAgreementName(order.getAgreementName());
			    agentInfo.setBusinessNature(order.getBusinessSourceCode());
			    agentInfo.setHandler2Code(order.getHandlerCode2());
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
				List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
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
	TPrdPlanFormMapper tPrdPlanFormMapper;
	
	@Override
	public HttpResult planDetail2CarXY(GroupPlanVo groupPlanVo,HttpServletRequest request) {
		GroupSalesPlanRoot groupSalesPlanRoot = new GroupSalesPlanRoot();
		//车+x+y组合产品的有效期限
		SaleInfo saleInfo = new SaleInfo();
		saleInfo.setGiCoveragePeriod("1");//保障期限
		saleInfo.setGiPeriodType("1");//保障类型
		//根据组合代码查询组合产品信息
		HashMap<String, Object> map_sale = new HashMap<String, Object>();
		map_sale.put("groupCode", groupPlanVo.getGroupCode());
		HashMap<String,Object> map_product = planStrategyMapper.selectGroupProductByGroupCode(groupPlanVo.getGroupCode());
		int saleNum = orderMapper.selectSaleNum(map_sale);
		saleInfo.setSaleNum(saleNum+"");//已售份数
		//判断业务员所属机构和渠道是否有访问权限
		UserToken user = planStrategyService.getAccessTokenByUserCode(groupPlanVo.getUserCode());
		groupPlanVo.setChannelCode(user.getChannelCode());//所属渠道
		groupPlanVo.setComCode(user.getComCode());//所属机构
		Header header = new Header();
		header.setBasePremium(Double.parseDouble(map_product.get("basePrice")+""));//基础保费
		header.setTitle(map_product.get("groupName")+"");//组合代码
		header.setDesc(map_product.get("desc")+"");//描述
		//组合产品已出售份数
		header.setSaleNum(Integer.parseInt(map_product.get("saleNum")+"")) ;
		saleInfo.setSaleNum(map_product.get("saleNum")+"") ;
		HashMap<String,Object> mapSwiper = new HashMap<String, Object>();
		mapSwiper.put("code", groupPlanVo.getGroupCode());
		mapSwiper.put("attachType", "swiper");
		//组合产品详情的banner图片
		List<Swipper> swip = tPrdPlanFormMapper.selectSwiperCarXY(mapSwiper);
		if(CollectionUtils.isNotEmpty(swip)) {
			header.setSwipper(swip);
		}
		//封装产品详情头部信息
		groupSalesPlanRoot.setHeader(header);
		groupPlanVo.setChannelCode(user.getChannelCode());
		groupPlanVo.setComCode(user.getComCode());
 		String channel = salesPlanMapper.selectChannelBygroupCode(groupPlanVo.getGroupCode());
 		List<GroupSalesPlan> groupSalesPlanList = null;
 		//如果中台没同步渠道信息只匹配机构
 		if(StringUtils.isNotBlank(channel)) {
 			groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeForCarXY(groupPlanVo);
 		}
 		else {
 			groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeAllChannelForCarXY(groupPlanVo);
 		}
		if(groupSalesPlanList==null || groupSalesPlanList.size()==0)
			return HttpResult.error( 2, "您目前无权限查看");
		groupSalesPlanRoot.setGroupSalesPlan(groupSalesPlanList);
		//组合产品下产品简介
		HashMap<String,Object> mapAt= new HashMap<String, Object>();
		mapAt.put("code", groupPlanVo.getGroupCode());
		mapAt.put("attachType", "product_intro");
		List<ImgList> listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
		ProductInfo productInfo = new ProductInfo();
		productInfo.setTitle("产品简介");
		if(CollectionUtils.isNotEmpty(listAt)) {
			productInfo.setContent(listAt);
		}
		//组合产品下保障说明
		groupSalesPlanRoot.setProductInfo(productInfo);
		SafeguardDetail safeguardDetail = new SafeguardDetail();
		mapAt.put("attachType", "insure_desc");//保障说明
		listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
		ClaimStatement claimStatement = new ClaimStatement();
		//组合产品下理赔说明
		mapAt.put("attachType", "claim_guide");//理赔说明
		listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
		claimStatement.setTitle("理赔说明");
		if(CollectionUtils.isNotEmpty(listAt)) {
			claimStatement.setContent(listAt);
		}
		groupSalesPlanRoot.setClaimStatement(claimStatement);
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		
		List<TermsAndConditionsContent> list_termsAndConditions = new ArrayList<TermsAndConditionsContent>();
		//组合产品下保险条款
		List<TermsAndConditionsContent> list_conditions_re = tPrdPlanFormMapper.selectConditionsContentCarXY2(mapAt);
		TermsAndConditionsContent termsAndConditionsRe = new TermsAndConditionsContent();
		termsAndConditionsRe.setTitle("保险条款");
		termsAndConditionsRe.setChlidren(list_conditions_re);
		list_termsAndConditions.add(termsAndConditionsRe);
		
		List<TermsAndConditionsContent> list_termsAndConditionsOthers = tPrdPlanFormMapper.selectConditionsContentCarXY(mapAt);
		list_termsAndConditions.addAll(list_termsAndConditionsOthers);
		
		termsAndConditions.setContent(list_termsAndConditions);
		groupSalesPlanRoot.setTermsAndConditions(termsAndConditions);
		//组合产品下健康须知
		termsAndConditions.setTitle("条款须知");
		HealthNotice healthNotice = new HealthNotice();
		mapAt.put("attachType", "actoion_inform");//健康须知
		listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
		healthNotice.setTitle("健康须知");
		if(CollectionUtils.isNotEmpty(listAt)) {
			healthNotice.setInUrl(listAt.get(0).getImgIn());
			healthNotice.setUrl(listAt.get(0).getImg());
			groupSalesPlanRoot.setIsNeedHealthNotice(true);
		}
		else {
			groupSalesPlanRoot.setIsNeedHealthNotice(false);
		}
		groupSalesPlanRoot.setHealthNotice(healthNotice);
		//组合产品下常见问题
		List<CommonProblemContent> contentList = new ArrayList<CommonProblemContent>();
		CommonProblem commonProblem = new CommonProblem();
		commonProblem.setTitle("常见问题");
		contentList = tPrdPlanFormMapper.selectCommonProblemContentCarXy(mapAt);
		commonProblem.setContent(contentList);
		groupSalesPlanRoot.setCommonProblem(commonProblem);
		//组合产品下子产品信息
		for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
			List<PlanChild> planChildList = new ArrayList<PlanChild>();
			planChildList = salesplanchildMapper.selectBySalesPlanChildList(groupSalesPlan.getSalesPlanId());
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
			//子产品下的责任
			for(PlanChild planChild:planChildList) {
				List<PlanCoverage> planCoverageList = salesCoverageMapper.selectByChildPlanId(planChild.getChildPlanId());
				planChild.setSalesChildPlanCts(planCoverageList);
			}
		}
		//组合方案基本信息
		groupSalesPlanRoot.setSaleInfo(saleInfo);
		if(groupPlanVo.getOrderId()!=null) {
			String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
			//if("plan".equals(dataTag)) {
				groupSalesPlanRoot.setUserInfo(user);
			//}
			GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
			Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
			groupOrderDetail.setOrder(order);
			
			if(1==order.getIsAgent()) {
				AgentInfoVO agentInfo = new AgentInfoVO();
				agentInfo.setAgentCode(order.getAgentCode());//代理人代码
				agentInfo.setAgentName(order.getAgentName());//代理人名称
				agentInfo.setAgreementNo(order.getAgreementNo());//代理人协议号
				agentInfo.setAgreementName(order.getAgreementName());//代理人协议名称
				agentInfo.setBusinessNature(order.getBusinessSourceCode());
			    agentInfo.setHandler2Code(order.getHandlerCode2());
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
				childProduct.setPlanCode(orderList.get(i).getPlanCode());//方案代码
				childProduct.setProductCode(orderList.get(i).getProductCode());//产品代码
				childProduct.setProductName(orderList.get(i).getProductName());//产品名称
				childProduct.setProductLine(orderList.get(i).getProductLine());//产品线
				List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
				childProduct.setClauseList(clauseList);//条款列表
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
					map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");//投保单号
					map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");//订单号
					map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");//保单号
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
		if(1==order.getIsAgent()) {
			AgentInfoVO agentInfo = new AgentInfoVO();
			agentInfo.setAgentCode(order.getAgentCode());
			//agentInfo.setAgentName(order.geta);
			agentInfo.setAgreementNo(order.getAgreementNo());
			agentInfo.setAgreementName(order.getAgreementNo());
			agentInfo.setBusinessNature(order.getBusinessSourceCode());
		    agentInfo.setHandler2Code(order.getHandlerCode2());
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
			proposalNo = DESEncryptUtil.encrypt(proposalNo,desPassword);
			GetPayUrlResponseVO resVO = new GetPayUrlResponseVO();
			String payUrl = null;
			String basePayUrl = mobileQuickPayUrl+"?consumerID="+consumerID+"&systemSourceCode="+systemSourceCode+"&paymentNo="+desPayNo+"&checkNo="+desCheckNo+"&ext="+proposalNo+"-UNITE";
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
		String proposalNo = applyPayVo.getInsureNo();
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
				if(StringUtils.isNotBlank(groupSalesPlan.getGroupCode()))
				salesPlanConfig.setGroupCode(groupSalesPlan.getGroupCode());
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
				if(StringUtils.isNotBlank(groupSalesPlan.getGroupCode()))
				salesPlanConfig.setGroupCode(groupSalesPlan.getGroupCode());
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
	public HttpResult policyList(PolicyVo policyVo) {
	        if(StringUtil.isNullorEmpty(policyVo.getSearchType()))
	            policyVo.setSearchType("10");
	        PolicyDto policyDto = new PolicyDto();
	        RequestHead requestHead = new RequestHead();
	        requestHead.setConsumerID("supsale");
	        policyDto.setRequestBody(policyVo);
	        policyDto.setRequestHead(requestHead);
	        log.info("调用保单列表接口开始，请求报文："+ JSONObject.toJSONString(policyDto));
	    	JsonNode jsonNode = UnirestUtil.postForJson(policyListUrl, policyDto);
	    	log.info("调用保单列表接口结束，响应报文："+ JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies"));
			return HttpResult.success(JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies"), 1, "OK");
	}

    @Override
    public HttpResult recommendRankPlans(RecommendRankPlansVo recommendRankPlansVo) {
        RecommendRankPlansObj recommendRankPlansObj = new RecommendRankPlansObj();
        //调用中台生活无忧家庭版方案推荐接口
        String reqJson = "{\"requestHead\":{\"consumerID\":\"supsale\"},\"requestBody\":{\"groupPlanCode\":\"XYZ2050222\",\"requestType\":1,\"orgCode\":\"34010000\",\"channelByPerson\":\"C0000015\",\"policyCustomerList\":[{\"sequenceNumber\":1,\"indiAge\":30,\"polHolderInsuredRelaCode\":\"00\",\"isMainInsured\":\"Y\"},{\"sequenceNumber\":2,\"indiAge\":55,\"polHolderInsuredRelaCode\":\"02\",\"isMainInsured\":\"N\"},{\"sequenceNumber\":3,\"indiAge\":5,\"polHolderInsuredRelaCode\":\"03\",\"isMainInsured\":\"N\"}]}}";
        JSONObject request = JSONObject.parseObject(reqJson);
        System.out.println("调用提交组合核保接口开始，请求报文："+ JSONObject.toJSONString(request));
        JsonNode jsonNode = UnirestUtil.postForJson("http://10.1.12.59:8080/daditestgroup/ccicsit/getrecommendrankplansservicerest/Restful", request);
        String jsonstr = jsonNode.getObject().get("responseBody")+"";
        System.out.println(jsonstr);
        RecommendRankPlansRes recommendRankPlansRes = new RecommendRankPlansRes();
        recommendRankPlansRes = JSONObject.parseObject(jsonstr, RecommendRankPlansRes.class);
        System.out.println(JSONObject.toJSONString(recommendRankPlansRes));
        //根据 policyLobList t_noncar_sales_plan 里的 polHolderInsuredRelaCode 累加 childPlanCode
        recommendRankPlansObj.setRecommendRankPlanList(recommendRankPlansRes.getRecommendRankPlanList());
        System.out.println(recommendRankPlansObj.getRecommendRankPlanList());
        buildFor(recommendRankPlansObj);
        Map<String,Object> mapInsure = new HashMap<String, Object>();
        System.out.println(recommendRankPlansObj.getSalesPlanCode());
        GroupPlanVo groupPlanVo = new GroupPlanVo();
        List<GroupSalesPlan> groupSalesPlanList = null;
        List<GroupSalesPlan> groupSalesPlantw = new ArrayList<GroupSalesPlan>();
        for(String item : recommendRankPlansObj.getSalePlanCodelist()) {
            System.out.println("=====");
            System.out.println(item);
            recommendRankPlansObj.setRelationMap(recommendRankPlansObj.getMapList().stream().filter(s->item.equals(s.getSalesPlanCode()))
                    .collect(Collectors.groupingBy(InsureRes::getPolHolderInsuredRelaCode)));
            System.out.println(recommendRankPlansObj.getRelationMap());
            mapInsure.put(item, recommendRankPlansObj.getRelationMap());
            
            groupPlanVo.setGroupCode(item);
            if(StringUtils.isNotBlank(recommendRankPlansObj.getRecommendRankPlansVo().getChannelByPerson())) {
              groupPlanVo.setChannelCode(recommendRankPlansObj.getRecommendRankPlansVo().getChannelByPerson());
              groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeForCarXY(groupPlanVo);
          }
          else {
              groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeAllChannelForCarXY(groupPlanVo);
          }
          
          if(groupSalesPlanList==null || groupSalesPlanList.size()==0)
              System.out.println("无权限查看");
          else
              groupSalesPlantw.add(groupSalesPlanList.get(0));
        }
        recommendRankPlansObj.setGroupSalesPlan(groupSalesPlantw);
        recommendRankPlansObj.getMapInsureList().add(mapInsure);
        RecommendRankPlansObjResp recommendRankPlansObjResp = new RecommendRankPlansObjResp();
        recommendRankPlansObjResp.setMapInsureList(recommendRankPlansObj.getMapInsureList());
        if(StringUtils.isNotBlank(recommendRankPlansVo.getOrderId())) {
            groupPlanVo.setOrderId(recommendRankPlansVo.getOrderId());
        }
        for(GroupSalesPlan groupSalesPlan : recommendRankPlansObj.getGroupSalesPlan()) {
          HashMap<String,Object> mapAt= new HashMap<String, Object>();
          mapAt.put("code", groupSalesPlan.getSalesPlanCode());
          TermsAndConditions termsAndConditions = new TermsAndConditions();
          List<TermsAndConditionsContent> list_termsAndConditions = new ArrayList<TermsAndConditionsContent>();
          //组合产品下保险条款
          List<TermsAndConditionsContent> list_conditions_re = tPrdPlanFormMapper.selectConditionsContentCarXY2(mapAt);
          TermsAndConditionsContent termsAndConditionsRe = new TermsAndConditionsContent();
          termsAndConditionsRe.setTitle("保险条款");
          termsAndConditionsRe.setChlidren(list_conditions_re);
          list_termsAndConditions.add(termsAndConditionsRe);
          
          List<TermsAndConditionsContent> list_termsAndConditionsOthers = tPrdPlanFormMapper.selectConditionsContentCarXY(mapAt);
          list_termsAndConditions.addAll(list_termsAndConditionsOthers);
          
          termsAndConditions.setContent(list_termsAndConditions);
          termsAndConditions.setTitle("条款须知");
          groupSalesPlan.setTermsAndConditions(termsAndConditions);
          List<PlanChild> planChildList = new ArrayList<PlanChild>();
          planChildList = salesplanchildMapper.selectBySalesPlanChildList(groupSalesPlan.getSalesPlanId());
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
          //子产品下的责任
          for(PlanChild planChild:planChildList) {
              List<PlanCoverage> planCoverageList = salesCoverageMapper.selectByChildPlanId(planChild.getChildPlanId());
              planChild.setSalesChildPlanCts(planCoverageList);
          }
      }
      //组合方案基本信息
      if(groupPlanVo.getOrderId()!=null) {
          String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
          //if("plan".equals(dataTag)) {
              //groupSalesPlanRoot.setUserInfo(user);
          //}
          GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
          Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
          groupOrderDetail.setOrder(order);
          
          if("1".equals(order.getIsAgent())) {
              AgentInfoVO agentInfo = new AgentInfoVO();
              agentInfo.setAgentCode(order.getAgentCode());//代理人代码
              agentInfo.setAgentName(order.getAgentName());//代理人名称
              agentInfo.setAgreementNo(order.getAgreementNo());//代理人协议号
              agentInfo.setAgreementName(order.getAgreementName());//代理人协议名称
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
              childProduct.setPlanCode(orderList.get(i).getPlanCode());//方案代码
              childProduct.setProductCode(orderList.get(i).getProductCode());//产品代码
              childProduct.setProductName(orderList.get(i).getProductName());//产品名称
              childProduct.setProductLine(orderList.get(i).getProductLine());//产品线
              List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
              childProduct.setClauseList(clauseList);//条款列表
              childProductList.add(childProduct);
          }
          groupOrderDetail.setChildProductList(childProductList);
          List<HashMap<String,Object>> maplist = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(groupPlanVo.getOrderId()));
          if(maplist!=null && maplist.size()>0) {
              List<Map<String,String>> list = new ArrayList<Map<String,String>>();
              for(HashMap<String,Object> orderMapperMap : maplist) {
                  Map<String,String> map_insure = new HashMap<String, String>();
                  map_insure.put("planCode", orderMapperMap.get("planCode")+"");
                  map_insure.put("planName", orderMapperMap.get("planName")+"");
                  map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");//投保单号
                  map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");//订单号
                  map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");//保单号
                  list.add(map_insure);
              }
          }
      }
      recommendRankPlansObjResp.setGroupSalesPlan(recommendRankPlansObj.getGroupSalesPlan());
      
      if(groupPlanVo.getOrderId()!=null) {
          String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
          //if("plan".equals(dataTag)) {
              //groupSalesPlanRoot.setUserInfo(user);
          //}
          GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
          Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
          groupOrderDetail.setOrder(order);
          
          if("1".equals(order.getIsAgent())) {
              AgentInfoVO agentInfo = new AgentInfoVO();
              agentInfo.setAgentCode(order.getAgentCode());//代理人代码
              agentInfo.setAgentName(order.getAgentName());//代理人名称
              agentInfo.setAgreementNo(order.getAgreementNo());//代理人协议号
              agentInfo.setAgreementName(order.getAgreementName());//代理人协议名称
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
              childProduct.setPlanCode(orderList.get(i).getPlanCode());//方案代码
              childProduct.setProductCode(orderList.get(i).getProductCode());//产品代码
              childProduct.setProductName(orderList.get(i).getProductName());//产品名称
              childProduct.setProductLine(orderList.get(i).getProductLine());//产品线
              List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
              childProduct.setClauseList(clauseList);//条款列表
              childProductList.add(childProduct);
          }
          groupOrderDetail.setChildProductList(childProductList);
          recommendRankPlansObjResp.setGroupOrderDetail(groupOrderDetail);
          List<HashMap<String,Object>> maplist = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(groupPlanVo.getOrderId()));
          if(maplist!=null && maplist.size()>0) {
              List<Map<String,String>> list = new ArrayList<Map<String,String>>();
              for(HashMap<String,Object> orderMapperMap : maplist) {
                  Map<String,String> map_insure = new HashMap<String, String>();
                  map_insure.put("planCode", orderMapperMap.get("planCode")+"");
                  map_insure.put("planName", orderMapperMap.get("planName")+"");
                  map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");//投保单号
                  map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");//订单号
                  map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");//保单号
                  list.add(map_insure);
              }
              recommendRankPlansObjResp.getGroupOrderDetail().setInsureNos(list);
          }
      }
        
        recommendRankPlansObjResp.setGroupSalesPlan(recommendRankPlansObj.getGroupSalesPlan());
        return HttpResult.success(recommendRankPlansObjResp);
    }
    
    
//   public HttpResult buidQWE(RecommendRankPlansObjResp recommendRankPlansObjResp,RecommendRankPlansObj recommendRankPlansObj,RecommendRankPlansVo recommendRankPlansVo){
//        List<GroupSalesPlan> groupSalesPlanList = null;
//        GroupPlanVo groupPlanVo = new GroupPlanVo();
//        if(StringUtils.isNotBlank(recommendRankPlansVo.getOrderId())) {
//            groupPlanVo.setOrderId(recommendRankPlansVo.getOrderId());
//        }
//        groupPlanVo.setComCode(recommendRankPlansObj.getRecommendRankPlansVo().getOrgCode());
//       
//        String groupCode = "SXYZ20502220002";
//        groupPlanVo.setGroupCode(groupCode);
//        
//        if(StringUtils.isNotBlank(recommendRankPlansObj.getRecommendRankPlansVo().getChannelByPerson())) {
//            groupPlanVo.setChannelCode(recommendRankPlansObj.getRecommendRankPlansVo().getChannelByPerson());
//            groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeForCarXY(groupPlanVo);
//        }
//        else {
//            groupSalesPlanList = salesPlanMapper.selectSalesPlanByGroupCodeAllChannelForCarXY(groupPlanVo);
//        }
//        
//        if(groupSalesPlanList==null || groupSalesPlanList.size()==0)
//            return HttpResult.error( 2, "您目前无权限查看");
//        else
//            recommendRankPlansObj.setGroupSalesPlan(groupSalesPlanList);
//        for(GroupSalesPlan groupSalesPlan : groupSalesPlanList) {
//            HashMap<String,Object> mapAt= new HashMap<String, Object>();
//            mapAt.put("code", groupSalesPlan.getSalesPlanCode());
//            TermsAndConditions termsAndConditions = new TermsAndConditions();
//            List<TermsAndConditionsContent> list_termsAndConditions = new ArrayList<TermsAndConditionsContent>();
//            //组合产品下保险条款
//            List<TermsAndConditionsContent> list_conditions_re = tPrdPlanFormMapper.selectConditionsContentCarXY2(mapAt);
//            TermsAndConditionsContent termsAndConditionsRe = new TermsAndConditionsContent();
//            termsAndConditionsRe.setTitle("保险条款");
//            termsAndConditionsRe.setChlidren(list_conditions_re);
//            list_termsAndConditions.add(termsAndConditionsRe);
//            
//            List<TermsAndConditionsContent> list_termsAndConditionsOthers = tPrdPlanFormMapper.selectConditionsContentCarXY(mapAt);
//            list_termsAndConditions.addAll(list_termsAndConditionsOthers);
//            
//            termsAndConditions.setContent(list_termsAndConditions);
//            termsAndConditions.setTitle("条款须知");
//            groupSalesPlan.setTermsAndConditions(termsAndConditions);
//            List<PlanChild> planChildList = new ArrayList<PlanChild>();
//            planChildList = salesplanchildMapper.selectBySalesPlanChildList(groupSalesPlan.getSalesPlanId());
//            if(groupPlanVo.getOrderId()!=null) {
//                Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
//                if(order.getStrategyCode()!=null && groupSalesPlan != null && groupSalesPlan.getSalesPlanCode()!=null && groupSalesPlan.getSalesPlanCode().equals(order.getStrategyCode())) {
//                    groupSalesPlan.setIsDefault("1");
//                }
//                List<HashMap<String,BigDecimal>> map_prem_list = orderMapper.selectPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//                for(PlanChild pc : planChildList) {
//                    //详情与计划书一致
//                    //pc.setTermsAndConditions(buildTermsAndConditions(groupSalesPlan));
//                    //pc.setIsNeedHealthNotice(true);
//                    //pc.setHealthNotice(buildHealthNotice(groupSalesPlan));
//                    for(HashMap<String,BigDecimal> m : map_prem_list) {
//                        if((m.get("childPlanCode")+"").equals(pc.getChildPlanCode())) {
//                            pc.setIsDefault("1");
//                        }
//                    }
//                }
//            }
//            groupSalesPlan.setSalesChildPlans(planChildList);
//            //子产品下的责任
//            for(PlanChild planChild:planChildList) {
//                List<PlanCoverage> planCoverageList = salesCoverageMapper.selectByChildPlanId(planChild.getChildPlanId());
//                planChild.setSalesChildPlanCts(planCoverageList);
//            }
//        }
//        //组合方案基本信息
//        if(groupPlanVo.getOrderId()!=null) {
//            String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            //if("plan".equals(dataTag)) {
//                //groupSalesPlanRoot.setUserInfo(user);
//            //}
//            GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
//            Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
//            groupOrderDetail.setOrder(order);
//            
//            if("1".equals(order.getIsAgent())) {
//                AgentInfoVO agentInfo = new AgentInfoVO();
//                agentInfo.setAgentCode(order.getAgentCode());//代理人代码
//                agentInfo.setAgentName(order.getAgentName());//代理人名称
//                agentInfo.setAgreementNo(order.getAgreementNo());//代理人协议号
//                agentInfo.setAgreementName(order.getAgreementName());//代理人协议名称
//                groupOrderDetail.setAgentInfo(agentInfo);
//            }
//            if(order.getStartDate()!=null) {
//                Long t1 = order.getStartDate().getTime();
//                Long t2 = new Date().getTime();
//                groupOrderDetail.setIntervalDate(t1-t2);
//            }
//            com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo invoiceInfo = invoiceInfoMapper.selectInvoiceInfoByOrderId2(order.getId());
//            if(invoiceInfo!=null)
//            groupOrderDetail.setInvoiceInfo(invoiceInfo);
//            List<Order> orderList = orderMapper.selectByRelationOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            Map<String,Object> map = new HashMap<String, Object>();
//            map.put("id", Long.parseLong(groupPlanVo.getOrderId()));
//            List<Customer> policyCustomerList = orderMapper.selectByOrderId(map);
//            Map<String,BigDecimal> map_sumpre  = orderMapper.selectSumPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            if(map_sumpre.get("duePremium")!=null)
//            groupOrderDetail.setDuePremium(map_sumpre.get("duePremium").doubleValue());
//            groupOrderDetail.setSalesPlanCode(order.getStrategyCode());
//            groupOrderDetail.setDiscountPremium(Double.parseDouble(map_sumpre.get("discountPremium")+""));
//            List<HashMap<String,BigDecimal>> map_prem_list = orderMapper.selectPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            HashMap<String,Double> m1 = new HashMap<String, Double>();
//            for(HashMap<String,BigDecimal> m : map_prem_list) {
//                m1.put(m.get("key")+"",m.get("value").doubleValue());
//            }
//            groupOrderDetail.setPremiumMap(m1);
//            groupOrderDetail.setCustomerList(policyCustomerList);
//            List<ChildProduct> childProductList = new ArrayList<ChildProduct>();
//            for(int i=0;i<orderList.size();i++) {
//                ChildProduct childProduct = new ChildProduct(); 
//                childProduct.setPlanCode(orderList.get(i).getPlanCode());//方案代码
//                childProduct.setProductCode(orderList.get(i).getProductCode());//产品代码
//                childProduct.setProductName(orderList.get(i).getProductName());//产品名称
//                childProduct.setProductLine(orderList.get(i).getProductLine());//产品线
//                List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
//                childProduct.setClauseList(clauseList);//条款列表
//                childProductList.add(childProduct);
//            }
//            groupOrderDetail.setChildProductList(childProductList);
//            List<HashMap<String,Object>> maplist = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(groupPlanVo.getOrderId()));
//            if(maplist!=null && maplist.size()>0) {
//                List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//                for(HashMap<String,Object> orderMapperMap : maplist) {
//                    Map<String,String> map_insure = new HashMap<String, String>();
//                    map_insure.put("planCode", orderMapperMap.get("planCode")+"");
//                    map_insure.put("planName", orderMapperMap.get("planName")+"");
//                    map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");//投保单号
//                    map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");//订单号
//                    map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");//保单号
//                    list.add(map_insure);
//                }
//            }
//        }
//        recommendRankPlansObjResp.setGroupSalesPlan(recommendRankPlansObj.getGroupSalesPlan());
//        
//        if(groupPlanVo.getOrderId()!=null) {
//            String dataTag = orderMapper.selectDataTagByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            //if("plan".equals(dataTag)) {
//                //groupSalesPlanRoot.setUserInfo(user);
//            //}
//            GroupOrderDetail groupOrderDetail = new GroupOrderDetail();
//            Order order = orderMapper.selectByPrimaryKey(Long.parseLong(groupPlanVo.getOrderId()));
//            groupOrderDetail.setOrder(order);
//            
//            if("1".equals(order.getIsAgent())) {
//                AgentInfoVO agentInfo = new AgentInfoVO();
//                agentInfo.setAgentCode(order.getAgentCode());//代理人代码
//                agentInfo.setAgentName(order.getAgentName());//代理人名称
//                agentInfo.setAgreementNo(order.getAgreementNo());//代理人协议号
//                agentInfo.setAgreementName(order.getAgreementName());//代理人协议名称
//                groupOrderDetail.setAgentInfo(agentInfo);
//            }
//            if(order.getStartDate()!=null) {
//                Long t1 = order.getStartDate().getTime();
//                Long t2 = new Date().getTime();
//                groupOrderDetail.setIntervalDate(t1-t2);
//            }
//            com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo invoiceInfo = invoiceInfoMapper.selectInvoiceInfoByOrderId2(order.getId());
//            if(invoiceInfo!=null)
//            groupOrderDetail.setInvoiceInfo(invoiceInfo);
//            List<Order> orderList = orderMapper.selectByRelationOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            Map<String,Object> map = new HashMap<String, Object>();
//            map.put("id", Long.parseLong(groupPlanVo.getOrderId()));
//            List<Customer> policyCustomerList = orderMapper.selectByOrderId(map);
//            Map<String,BigDecimal> map_sumpre  = orderMapper.selectSumPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            if(map_sumpre.get("duePremium")!=null)
//            groupOrderDetail.setDuePremium(map_sumpre.get("duePremium").doubleValue());
//            groupOrderDetail.setSalesPlanCode(order.getStrategyCode());
//            groupOrderDetail.setDiscountPremium(Double.parseDouble(map_sumpre.get("discountPremium")+""));
//            List<HashMap<String,BigDecimal>> map_prem_list = orderMapper.selectPremiumByOrderId(Long.parseLong(groupPlanVo.getOrderId()));
//            HashMap<String,Double> m1 = new HashMap<String, Double>();
//            for(HashMap<String,BigDecimal> m : map_prem_list) {
//                m1.put(m.get("key")+"",m.get("value").doubleValue());
//            }
//            groupOrderDetail.setPremiumMap(m1);
//            groupOrderDetail.setCustomerList(policyCustomerList);
//            List<ChildProduct> childProductList = new ArrayList<ChildProduct>();
//            for(int i=0;i<orderList.size();i++) {
//                ChildProduct childProduct = new ChildProduct(); 
//                childProduct.setPlanCode(orderList.get(i).getPlanCode());//方案代码
//                childProduct.setProductCode(orderList.get(i).getProductCode());//产品代码
//                childProduct.setProductName(orderList.get(i).getProductName());//产品名称
//                childProduct.setProductLine(orderList.get(i).getProductLine());//产品线
//                List<Clause> clauseList = orderCtMapper.selectCtByOrder(orderList.get(i).getId());
//                childProduct.setClauseList(clauseList);//条款列表
//                childProductList.add(childProduct);
//            }
//            groupOrderDetail.setChildProductList(childProductList);
//            recommendRankPlansObjResp.setGroupOrderDetail(groupOrderDetail);
//            List<HashMap<String,Object>> maplist = orderMapper.selectByOrderIdPaymentNoApply(Long.parseLong(groupPlanVo.getOrderId()));
//            if(maplist!=null && maplist.size()>0) {
//                List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//                for(HashMap<String,Object> orderMapperMap : maplist) {
//                    Map<String,String> map_insure = new HashMap<String, String>();
//                    map_insure.put("planCode", orderMapperMap.get("planCode")+"");
//                    map_insure.put("planName", orderMapperMap.get("planName")+"");
//                    map_insure.put("insureNo", orderMapperMap.get("insureNo")+"");//投保单号
//                    map_insure.put("orderNo",  orderMapperMap.get("orderNo")+"");//订单号
//                    map_insure.put("policyNo", orderMapperMap.get("policyNo")+"");//保单号
//                    list.add(map_insure);
//                }
//                recommendRankPlansObjResp.getGroupOrderDetail().setInsureNos(list);
//            }
//        }
//        return HttpResult.success(recommendRankPlansObjResp);
//    }

    @Override
    public HttpResult planDetail2Family(GroupPlanVo groupPlanVo, HttpServletRequest request) {
        //head
        Header header = new Header();
        HashMap<String, Object> map_sale = new HashMap<String, Object>();
        map_sale.put("groupCode", groupPlanVo.getGroupCode());
        HashMap<String,Object> map_product = planStrategyMapper.selectGroupProductByGroupCode(groupPlanVo.getGroupCode());
        header.setBasePremium(Double.parseDouble(map_product.get("basePrice")+""));//基础保费
        header.setTitle(map_product.get("groupName")+"");//组合代码
        header.setDesc(map_product.get("desc")+"");//描述
        //组合产品已出售份数
        header.setSaleNum(Integer.parseInt(map_product.get("saleNum")+"")) ;
        HashMap<String,Object> mapSwiper = new HashMap<String, Object>();
        mapSwiper.put("code", groupPlanVo.getGroupCode());
        mapSwiper.put("attachType", "swiper");
        //组合产品详情的banner图片
        List<Swipper> swip = tPrdPlanFormMapper.selectSwiperCarXY(mapSwiper);
        if(CollectionUtils.isNotEmpty(swip)) {
            header.setSwipper(swip);
        }
        log.info("header:" + JSONObject.toJSONString(header));
        //产品简介 产品理赔 产品保障
        //组合产品下产品简介
        HashMap<String,Object> mapAt= new HashMap<String, Object>();
        mapAt.put("code", groupPlanVo.getGroupCode());
        mapAt.put("attachType", "product_intro");
        List<ImgList> listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setTitle("产品简介");
        if(CollectionUtils.isNotEmpty(listAt)) {
            productInfo.setContent(listAt);
        }
        //组合产品下保障说明
        log.info("productInfo:" + JSONObject.toJSONString(productInfo));
        SafeguardDetail safeguardDetail = new SafeguardDetail();
        mapAt.put("attachType", "insure_desc");//保障说明
        listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
        ClaimStatement claimStatement = new ClaimStatement();
        //组合产品下理赔说明
        mapAt.put("attachType", "claim_guide");//理赔说明
        listAt = tPrdPlanFormMapper.selectPlanStrategyAttachCarXY(mapAt);
        claimStatement.setTitle("理赔说明");
        if(CollectionUtils.isNotEmpty(listAt)) {
            claimStatement.setContent(listAt);
        }
        log.info("claimStatement:" + JSONObject.toJSONString(claimStatement));
        //常见问题
        List<CommonProblemContent> contentList = new ArrayList<CommonProblemContent>();
        CommonProblem commonProblem = new CommonProblem();
        commonProblem.setTitle("常见问题");
        contentList = tPrdPlanFormMapper.selectCommonProblemContentCarXy(mapAt);
        commonProblem.setContent(contentList);
        log.info("commonProblem:" + JSONObject.toJSONString(commonProblem));
        
        PlanDetail2FamilyResp planDetail2FamilyResp = new PlanDetail2FamilyResp();
        planDetail2FamilyResp.setHeader(header);
        planDetail2FamilyResp.setClaimStatement(claimStatement);
        planDetail2FamilyResp.setCommonProblem(commonProblem);
        planDetail2FamilyResp.setProductInfo(productInfo);
        return HttpResult.success(planDetail2FamilyResp);
    }
    
    @SuppressWarnings("javadoc")
    public static void buildFor(RecommendRankPlansObj recommendRankPlansObj) {
        recommendRankPlansObj.getRecommendRankPlanList().stream().forEach(item -> {
            recommendRankPlansObj.setRecommendPlanList(item.getRecommendPlanList());
            buildRecommendPlan(recommendRankPlansObj);
        });
    }
    
    @SuppressWarnings("javadoc")
    public static void buildRecommendPlan(RecommendRankPlansObj recommendRankPlansObj) {
        List<String> strList = Lists.newArrayList();
        recommendRankPlansObj.getRecommendPlanList().stream().forEach(item -> {
            recommendRankPlansObj.setSalesPlanCode(item.getSalesPlanCode());
            strList.add(item.getSalesPlanCode());
            recommendRankPlansObj.setPolicyLobList(item.getPolicyLobList());
            GroupPlanCtl.buildPolicyLob(recommendRankPlansObj);
            
        });
        System.out.println(JSONObject.toJSONString(strList));
        recommendRankPlansObj.setSalePlanCodelist(strList);
    }
	
    public HttpResult policyListStaffOnly(String uuid,PolicyVo policyVo) {
        if(StringUtil.isNullorEmpty(policyVo.getSearchType()))
            policyVo.setSearchType("10");
        PolicyDto policyDto = new PolicyDto();
        RequestHead requestHead = new RequestHead();
        requestHead.setConsumerID("supsale");
        policyDto.setRequestBody(policyVo);
        policyDto.setRequestHead(requestHead);
        //证件类型映射
        if(!StringUtil.isNullorEmpty(policyVo.getIndiIdType())) {
            Map<String,String> map = new HashMap<>();
            map.put("01", "111");//身份证
            map.put("02", "113");//户口薄
            map.put("03", "414");//护照
            map.put("04", "114");//中国人民解放军军官证
            map.put("27", "553");//外国人永久居留证
            map.put("07", "801");//组织机构代码证
            map.put("19", "994");//未知企业证件
            map.put("21", "810");//统一社会信用代码
            map.put("28", "28");//港澳台居民居住证
            policyVo.setIndiIdType(map.get(policyVo.getIndiIdType()));
        }
        log.info("调用保单列表接口开始，请求报文：" + uuid + JSONObject.toJSONString(policyDto));
        JsonNode jsonNode = UnirestUtil.postForJson(policyListUrl, policyDto);
        log.info("调用保单列表接口结束，响应报文：" + uuid + jsonNode.toString());
        if(jsonNode.getObject().has("responseBody")){
            log.info("policies info：" + uuid + JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies"));
            List<PolicyRes> policyResList = JSONArray.parseArray(JSONObject.parseObject(jsonNode.getObject().get("responseBody")+"").get("policies").toString(), PolicyRes.class);
            log.info("policyResList:"+ JSONObject.toJSONString(policyResList));
            policyResList = policyResList.stream().filter(s->"WTH".equals(s.getProductCode()) || "WTN".equals(s.getProductCode())).filter(s->"2".equals(s.getPolicyStatus())).filter(s-> ((timesBetween(s.getExpiryDate(),new Date()).get("timeFlag")==1) || (timesBetween(s.getExpiryDate(),new Date()).get("timeFlag")==2 && timesBetween(s.getExpiryDate(),new Date()).get("Day")<15))).collect(Collectors.toList());
            policyResList.stream().forEach( x -> {
                if(x.getInsuredName().contains(",")) {
                    x.setProductName("百万医疗家庭版");
                }
                else{
                    x.setProductName("百万医疗个人版");
                }
                x.setTimeMap(timesBetween(x.getExpiryDate(), new Date()));
            });
            if(policyResList.size()==0) {
                return HttpResult.error( 0 , "未查询到保单信息");
            }
            else {
                return HttpResult.success(policyResList, 1, "OK");
            }
        }
        else {
            return HttpResult.error( 0 , "未查询到保单信息");
        }
    }
    
    
    
    public static Map<String,Long> timesBetween(Date sdate,Date bdate) { 
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff = 0;
        Map<String, Long> timeMap = new HashMap<String, Long>();
        try {
           sdate= df.parse(df.format(sdate));
           bdate= df.parse(df.format(bdate));
           long stime = sdate.getTime();
           long btime = bdate.getTime();
           if(stime>btime){
              diff = stime - btime;
              timeMap.put("timeFlag", 1L);//"剩余"
           }
           else{
              diff = btime - stime; 
              timeMap.put("timeFlag", 2L);//"过期" 
           }
          day = diff/(24*60*60*1000);
          hour = diff/(60*60*1000) - day*24;
          min = diff/(60*1000) - day*24*60 - hour*60;
          sec = diff/1000-day*24*60*60-hour*60*60-min*60;
        } catch (ParseException e) {
           e.printStackTrace();
        }
        timeMap.put("Day", day);
        timeMap.put("Hour", hour);
        //timeMap.put("Min", min);
        //timeMap.put("Sec", sec);
        return timeMap;
        }

}
