package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.DefaultDAO;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.PrePayMentRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderRespDTO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.GeneralConfigMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.service.ExtendedWarrantyFacade;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilsService;
import com.ccic.salessapp.common.service.AbstractService;
import com.ccic.salessapp.common.utils.StringUtils;

@Service
public class ExtendedWarrantyFacadeImpl extends AbstractService implements ExtendedWarrantyFacade{
	Log logger = null;
	DefaultDAO dao = new DefaultDAO();	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private StoreProductPlaceUtilsService orderUtil;
	
	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	
	@Autowired
	GeneralConfigMapper generalConfigMapper;
	
	/**
	 * 预付款支付
	 * @throws Exception 
	 */
	@Override
	public JsonVO prePayMent(PrePayMentRequestVO prePayMent) throws Exception {
		JsonVO jsonVO = new JsonVO();
		ResultVO result  = new ResultVO();
		StoreInsureInfo storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(prePayMent.getInsureNo());
		//StoreInsureInfo storeInsureInfo = (StoreInsureInfo)dao.toView("storeInsureInfo.selectBySureInfoNo", prePayMent.getProposalNo());
		if(!"4".equals(storeInsureInfo.getStatus()) && !"0".equals(storeInsureInfo.getStatus())) {
			result.setCode("0");
			result.setMessage("订单已支付");
			jsonVO.setResult(result);
			return jsonVO;			
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("configType", "ZJYBaccountCode");
		String accountCode = null;
		try {
			//accountCode = (String) dao.toView("generalConfig.getConfigValueNoCache", map);
			accountCode = generalConfigMapper.getConfigValueNoCache(map).get(0);
		} catch (Exception e1) {
			logger.error(e1);
		}
		if (StringUtils.isBlank(accountCode)) {
			result.setCode("0");
			result.setMessage("项目编码未配置。");
			jsonVO.setResult(result);
			return jsonVO;
		}
		String[] split = accountCode.split("-");
		accountCode = split[0];
		String itemName = split[1];
		//预付费查询余额
		Double balanceQuery = orderUtil.balanceQuery(result,accountCode);
		if("0".equals(result.getCode())){
			result.setMessage("预付费查询余额"+balanceQuery);
			jsonVO.setResult(result);
			return jsonVO;
		}
		
		if ((balanceQuery.compareTo(Double.parseDouble(storeInsureInfo.getSumPremium())))<0) { 	//额度不够
			result.setCode("0");
			result.setMessage("预付款余额不足,请充值！");
			jsonVO.setResult(result);
			return jsonVO;
		}
		//调用预付费预扣单生成接口
		WithholdOrderRespDTO withholdOrderService = orderUtil.withholdOrderService(result,accountCode,storeInsureInfo);
		if ("0".equals(result.getCode())) {
			jsonVO.setResult(result);
			return jsonVO;
		}
		try {
			//保存预扣单号和项目编码和项目名称
			orderUtil.updateInsureInfoByIdorWithholdNo(prePayMent.getInsureNo(),withholdOrderService,accountCode,itemName);
		} catch (Exception e) {
			logger.error("保存预扣单号失败:",e);
			result.setCode("0");
			result.setMessage("保存预扣单号失败："+e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}

		orderUtil.paySuccess(result,storeInsureInfo,withholdOrderService.getPaymentNo(),accountCode);
		if ("0".equals(result.getCode())) {
			//预付费余额回滚
			orderUtil.withholdStatusPort(storeInsureInfo.getHandlerCode(),withholdOrderService.getWithholdNo(),accountCode,withholdOrderService.getPaymentNo());
			jsonVO.setResult(result);
			return jsonVO;
		}

		try {
			Map<String, String> param=new HashMap<String, String>();
			param.put("insureNo", prePayMent.getInsureNo());
			storeInsureInfoMapper.upPolicyNoInfo(param);
			result.setCode("1");
			result.setMessage("支付完成，请在订单列表查看保单信息。");
		} catch (Exception e) {
			logger.error("支付成功状态更新失败：",e);
			result.setCode("0");
			result.setMessage("支付成功状态更新失败："+e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}
		jsonVO.setResult(result);
		return jsonVO;
	}
	

	
	
	
	/**
	 * 预付款支付
	 * @throws Exception 
	 */
	@Override
	public JsonVO prePayMentTwo(PrePayMentRequestVO prePayMent) throws Exception {
		JsonVO jsonVO = new JsonVO();
		ResultVO result  = new ResultVO();
		NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNo(prePayMent.getInsureNo());
		//StoreInsureInfo storeInsureInfo = (StoreInsureInfo)dao.toView("storeInsureInfo.selectBySureInfoNo", prePayMent.getProposalNo());
		if(!"4".equals(noncarOrder.getOrderStatus()) && !"0".equals(noncarOrder.getOrderStatus())) {
			result.setCode("0");
			result.setMessage("订单已支付");
			jsonVO.setResult(result);
			return jsonVO;			
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("configType", "ZJYBaccountCode");
		String accountCode = null;
		try {
			//accountCode = (String) dao.toView("generalConfig.getConfigValueNoCache", map);
			accountCode = generalConfigMapper.getConfigValueNoCacheTwo(map).get(0);
		} catch (Exception e1) {
			logger.error(e1);
		}
		if (StringUtils.isBlank(accountCode)) {
			result.setCode("0");
			result.setMessage("项目编码未配置。");
			jsonVO.setResult(result);
			return jsonVO;
		}
		String[] split = accountCode.split("-");
		accountCode = split[0];
		String itemName = split[1];
		//预付费查询余额
		Double balanceQuery = orderUtil.balanceQueryTwo(result,accountCode);
		if("0".equals(result.getCode())){
			result.setMessage("预付费查询余额"+balanceQuery);
			jsonVO.setResult(result);
			return jsonVO;
		}
		
		if ((balanceQuery.compareTo(Double.parseDouble(noncarOrder.getSumPremium())))<0) { 	//额度不够
			result.setCode("0");
			result.setMessage("预付款余额不足,请充值！");
			jsonVO.setResult(result);
			return jsonVO;
		}
		//调用预付费预扣单生成接口
		WithholdOrderRespDTO withholdOrderService = orderUtil.withholdOrderServiceTwo(result,accountCode,noncarOrder);
		if ("0".equals(result.getCode())) {
			jsonVO.setResult(result);
			return jsonVO;
		}
		try {
			//保存预扣单号和项目编码和项目名称
			orderUtil.updateInsureInfoByIdorWithholdNoTwo(prePayMent.getInsureNo(),withholdOrderService,accountCode,itemName);
		} catch (Exception e) {
			logger.error("保存预扣单号失败:",e);
			result.setCode("0");
			result.setMessage("保存预扣单号失败："+e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}

		orderUtil.paySuccessTwo(result,noncarOrder,withholdOrderService.getPaymentNo(),accountCode);
		if ("0".equals(result.getCode())) {
			//预付费余额回滚
			orderUtil.withholdStatusPortTwo(noncarOrder.getHandlerCode(),withholdOrderService.getWithholdNo(),accountCode,withholdOrderService.getPaymentNo());
			jsonVO.setResult(result);
			return jsonVO;
		}

		try {
			Map<String, String> param=new HashMap<String, String>();
			param.put("insureNo", prePayMent.getInsureNo());
			storeInsureInfoMapper.upPolicyNoInfo(param);
			result.setCode("1");
			result.setMessage("支付完成，请在订单列表查看保单信息。");
		} catch (Exception e) {
			logger.error("支付成功状态更新失败：",e);
			result.setCode("0");
			result.setMessage("支付成功状态更新失败："+e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}
		jsonVO.setResult(result);
		return jsonVO;
	}

	
	public static void main(String[] args) {
		String accountCode= "P00000176-浙江延保预付费项目";
		String[] split = accountCode.split("-");
		accountCode = split[0];
		String itemName = split[1];
		System.out.println(accountCode);
		System.out.println(itemName);
	}


}
