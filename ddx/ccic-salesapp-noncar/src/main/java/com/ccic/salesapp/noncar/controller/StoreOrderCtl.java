package com.ccic.salesapp.noncar.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreCertiNoticeRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreOrderDeleteRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryDetailReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.request.TNoncarInsureZcReqVO;
import com.ccic.salesapp.noncar.dto.request.ZcOrderNoReqVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreProductMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.TNoncarInsureinfoZcMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.StoreOrderService;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.core.support.RequestContext;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;


/**
 * 
 * 非车险订单管理
 * @author wangpeng
 *
 */


@Slf4j
@RestController
@Api(tags = "订单管理模块", description = "订单管理模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "StoreOrder")
public class StoreOrderCtl {


	@Autowired
	StoreOrderService storeOrderService;

	@Autowired
	TNoncarInsureinfoZcMapper tNoncarInsureinfoZcMapper;

	@Autowired
	StoreInfoMapper storeInfoMapper;
	
	@Autowired
	StoreProductMapper storeProductMapper;
	
	@Autowired
	StoreProductPlaceUtilService storeProductPlaceUtilService;



	@PostMapping(value = "/storeOrderListQuery")
	@ApiOperation(value = "门店订单列表查询", notes = "门店订单列表查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> storeOrderListQuery(@RequestBody StoreQueryListRequestVO reqvo) throws Exception {
		return storeOrderService.storeOrderListQuery(reqvo);
	}


	@PostMapping(value = "/storeOrderDetail")
	@ApiOperation(value = "订单详情", notes = "订单详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<StoreInsureInfoFormulasResponseVO> storeOrderDetail(@RequestBody StoreQueryDetailReqVO req) throws Exception {
		return storeOrderService.storeOrderDetail(req);
	}

	@PostMapping(value = "/certiNotice")
	@ApiOperation(value = "门店保单回写", notes = "门店保单回写", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> certiNotice(@RequestBody StoreCertiNoticeRequestVO reqvo,String userCode) throws Exception {


		return storeOrderService.certiNotice(reqvo);
	}

	@PostMapping(value = "/deleteStoreOrder")
	@ApiOperation(value = "删除订单", notes = "删除订单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> deleteStoreOrder(@RequestBody StoreOrderDeleteRequestVO reqvo) throws Exception {


		return storeOrderService.deleteStoreOrder(reqvo);
	}

	@PostMapping(value = "/forwardPayProductInsureInfo")
	@ApiOperation(value = "订单转发支付查询信息", notes = "订单转发支付查询信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<StoreInsureInfoFormulasResponseVO> forwardPayProductInsureInfo(@RequestBody ForwardPayInsureInfoReqVO reqvo) throws Exception {


		return storeOrderService.forwardPayProductInsureInfo(reqvo);
	}

	@PostMapping(value = "/insureStatusZc")
	@ApiOperation(value = "投保信息暂存接口", notes = "投保信息暂存接口", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> insureStatusZc(@RequestBody TNoncarInsureZcReqVO reqvo) throws Exception {
		
		String isInnerService =  RequestContext.getHeader(CoreContants.X_INNER_SERVICE);

		TNoncarInsureZcResVO res=new TNoncarInsureZcResVO();
		//若无该暂存信息则新增,有则更新
		String orderNo=reqvo.getOrderNo();
		if(orderNo==null||"".equals(orderNo)){

			//生成订单号start
			String storeCode=reqvo.getStoreOracleRequestVO().getStoreCode();
			StoreInfo storeInfo=storeInfoMapper.findStoreByCode(storeCode);
			String comcode=storeInfo!=null?storeInfo.getUserComCode():"";
			
			Date date = new Date();
			String num = ("" + date.getTime()).substring(0, 12);
			String currenYear = new SimpleDateFormat("yyyy").format(new Date());
			StringBuffer orderNum = new StringBuffer();
			if(comcode.length()>=4){
				orderNum.append(comcode.substring(0, 4));// 归属机构前四位
			}else{
				orderNum.append("");
			}	
			orderNum.append(currenYear);// 四位年份
			orderNum.append(num);// 12位流水号
			orderNum.append(RandomStringUtils.randomNumeric(4));// 4位随机数
			//暂存订单信息封装保存
			StoreOracleRequestVO request=reqvo.getStoreOracleRequestVO();
			if("true".equals(isInnerService)) {
				request.setIsFilterInOrderList("1");
			}else {
				request.setIsFilterInOrderList("0");
			}
			TNoncarInsureZcReqVO req=new TNoncarInsureZcReqVO();
			try{
				StoreFormulas formula = storeProductPlaceUtilService.getProductFormula(
						request.getFormulaCode(), request.getProductCode(),"1");
				StoreProduct product =  storeProductMapper.getProduct(request.getProductCode());
				request.setAddRiskAmountJson(formula.getAddRiskAmountJson());
				request.setAddRiskPremiumsJson(formula.getAddRiskPremiumsJson());
				request.setAmountInfoJson(formula.getAmountInfoJson());
				request.setPremiumsJson(formula.getPremiumsJson());
				request.setExtensionJson(formula.getExtensionJson());
				request.setProductName(product.getProductName());
				JSONObject json = JSONObject.fromObject(request);
				req.setJsonText(json.toString());
				req.setInsureNo(request.getInsureNo());
				req.setOrderNo(orderNum.toString());
				req.setFormulaCode(request.getFormulaCode());
				tNoncarInsureinfoZcMapper.insertZc(req);
			}catch(Exception e){
				log.error("投保信息暂存异常！",e);
				return HttpResult.error(0,e.getMessage());
			}		
			res.setOrderNo(orderNum.toString());
			return HttpResult.success(res,1,"投保信息暂存成功！");
		}else{
			int result=0;
			try{
				StoreOracleRequestVO request = reqvo.getStoreOracleRequestVO();
				StoreFormulas formula = storeProductPlaceUtilService.getProductFormula(
						request.getFormulaCode(), request.getProductCode(),"1");
				StoreProduct product =  storeProductMapper.getProduct(request.getProductCode());
				request.setAddRiskAmountJson(formula.getAddRiskAmountJson());
				request.setAddRiskPremiumsJson(formula.getAddRiskPremiumsJson());
				request.setAmountInfoJson(formula.getAmountInfoJson());
				request.setPremiumsJson(formula.getPremiumsJson());
				request.setExtensionJson(formula.getExtensionJson());
				request.setProductName(product.getProductName());
				JSONObject json = JSONObject.fromObject(request);
				reqvo.setJsonText(json.toString());
				result=tNoncarInsureinfoZcMapper.updateZcInfo(reqvo);
			}catch(Exception e){
				log.error("更新暂存信息异常！",e);
			}
			if(result>0){
				res.setOrderNo(orderNo);
				return HttpResult.success(res, 1, "更新暂存信息成功！");
			}else{
				return HttpResult.error(0, "更新暂存信息失败！");
			}
		}
	}

	@PostMapping(value = "/queryZc")
	@ApiOperation(value = "根据订单号获取暂存信息", notes = "根据订单号获取暂存信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryZc(@RequestBody ZcOrderNoReqVO reqvo) throws Exception {

		String orderNo=reqvo.getOrderNo();
		if(orderNo==null||"".equals(orderNo)){
			return HttpResult.error(0, "orderNo不可为空！");
		}

		try{
			TNoncarInsureZcResVO res=tNoncarInsureinfoZcMapper.queryZc(orderNo);
			if(res!=null){
				String jsonText=res.getJsonText();
				StoreOracleRequestVO stu = JSON.parseObject(jsonText, StoreOracleRequestVO.class);
				stu.setStartDate(stu.getStartDate().replace("T", " "));
				stu.setEndDate(stu.getEndDate().replace("T", " "));
				res.setJsonText("暂存未下单投保信息数据已解析至storeOracleRequestVO,若为null则为无此信息。");
				res.setStoreOracleRequestVO(stu);			
			}
			return HttpResult.success(res, 1, "获取暂存信息成功！");
		}catch(Exception e){
			log.error("获取暂存信息异常！",e);
			return HttpResult.error(0, "获取暂存信息失败！");
		}
	}

}

