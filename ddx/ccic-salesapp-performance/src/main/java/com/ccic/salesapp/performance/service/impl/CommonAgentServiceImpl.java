package com.ccic.salesapp.performance.service.impl;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.config.SystemConfig;
import com.ccic.salesapp.performance.domain.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.performance.domain.vo.dto.AgentHandlerInfo;
import com.ccic.salesapp.performance.domain.vo.dto.CommonAgent;
import com.ccic.salesapp.performance.domain.vo.dto.EnchashmentConfig;
import com.ccic.salesapp.performance.domain.vo.dto.FeeQueryVO;
import com.ccic.salesapp.performance.domain.vo.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.performance.domain.vo.dto.InviteUserVO;
import com.ccic.salesapp.performance.domain.vo.dto.JsonVO;
import com.ccic.salesapp.performance.domain.vo.dto.PackNoVO;
import com.ccic.salesapp.performance.domain.vo.dto.PackVO;
import com.ccic.salesapp.performance.domain.vo.dto.PlanFeeDetailVO;
import com.ccic.salesapp.performance.domain.vo.dto.PlanFeePay;
import com.ccic.salesapp.performance.domain.vo.dto.QueryPolicyVo;
import com.ccic.salesapp.performance.domain.vo.dto.ResultVO;
import com.ccic.salesapp.performance.domain.vo.dto.StoreInsureInfo;
import com.ccic.salesapp.performance.domain.vo.request.BillPackToFKRequest;
import com.ccic.salesapp.performance.domain.vo.request.CommonAgentVO;
import com.ccic.salesapp.performance.domain.vo.request.FeePayDetailRequest;
import com.ccic.salesapp.performance.domain.vo.request.FeeVo;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeDetailInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeePayVO;
import com.ccic.salesapp.performance.domain.vo.request.QueryWithdrawalVo;
import com.ccic.salesapp.performance.domain.vo.request.UpdateConfigRequest;
import com.ccic.salesapp.performance.domain.vo.response.CashWithdrawalResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.EnchashmentResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.FeePayDetailResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.InviteDetailResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.InviteListResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.PlanFeeDetailInquiryResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.PlanFeeInquiryResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.PlanFeeVO;
import com.ccic.salesapp.performance.domain.vo.response.QueryConfigResponseVO;
import com.ccic.salesapp.performance.dto.response.DetailDTO;
import com.ccic.salesapp.performance.exception.GeneralException;
import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.PaymentNoApply;
import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.PaymentNoApplyRequest;
import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO;
import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.PaymentNoApplyResponse;
import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.ProposalInfoDTO;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeDTO;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryPort;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryRequest;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryResponse;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeResponseDTO;
import com.ccic.salesapp.performance.outService.esb.Withdrawals.WithdrawalsPort;
import com.ccic.salesapp.performance.outService.esb.Withdrawals.WithdrawalsRequest;
import com.ccic.salesapp.performance.outService.esb.Withdrawals.WithdrawalsRequestDTO;
import com.ccic.salesapp.performance.outService.esb.Withdrawals.WithdrawalsResponse;
import com.ccic.salesapp.performance.repository.basedb.mapper.AgentHandlerMappper;
import com.ccic.salesapp.performance.repository.basedb.mapper.CommonAgentMapper;
import com.ccic.salesapp.performance.repository.basedb.mapper.EnchashmentMapper;
import com.ccic.salesapp.performance.repository.basedb.mapper.GeneralConfigMapper;
import com.ccic.salesapp.performance.repository.basedb.mapper.InsuranceMapper;
import com.ccic.salesapp.performance.repository.basedb.mapper.InviteUserMapper;
import com.ccic.salesapp.performance.repository.basedb.mapper.PlanFeePayMapper;
import com.ccic.salesapp.performance.service.CommonAgentService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.service.AbstractService;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 佣金、手续费提现接口实现
 * @author wangpeng
 *
 */

@Slf4j
@Service
public class CommonAgentServiceImpl extends AbstractService implements CommonAgentService {

	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

	
	@Autowired
	CommonAgentMapper commonAgentMapper;

	@Autowired
	AgentHandlerMappper agentHandlerMappper;

	@Autowired
	InsuranceMapper insuranceMapper;

	@Autowired
	GeneralConfigMapper generalConfigMapper;

	@Autowired
	PlanFeePayMapper planFeePayMapper;

	@Autowired
	PlanFeeInquiryPort  planFeeInquiryPort;

	@Autowired
	WithdrawalsPort withdrawalsPort;
	
	@Autowired
	PaymentNoApply paymentNoApply;

	@Autowired
	EnchashmentMapper enchashmentMapper;

	@Autowired
	InviteUserMapper inviteUserMapper;
	
	//@Value("agentCode")
	String dagentCode="365grdlragen";
	
	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	
	
	

	

	/**
	 * 添加常用代理人
	 * @param agentVO
	 * @return
	 */
	@Override
	public HttpResult<?> addCommonAgent(CommonAgentVO agentVO) {
		JsonVO json = new JsonVO();
		ResultVO  result = new ResultVO();
		CommonAgent agent=new CommonAgent();
		if(StringUtils.isBlank(agentVO.getAgentCode())){
			return HttpResult.error(0,"代理人代码不能为空！");
		}
		if(StringUtils.isBlank(agentVO.getAgentName())){
			return HttpResult.error(0,"代理人名称不能为空！");
		}
		if(StringUtils.isBlank(agentVO.getBussinessSource())){
			return HttpResult.error(0,"业务来源不能为空！");
		}
		agent.setAgentCode(agentVO.getAgentCode());
		agent.setAgentName(agentVO.getAgentName());
		agent.setBusinessSource(agentVO.getBussinessSource());
		agent.setStaffCode(agentVO.getUserCode());
		/*
		 * 校验代理人是否已经存在，校验条件是来源和代理人代码
		 *
		 */
		try{
			List<CommonAgent> agents=commonAgentMapper.searchByAgentCode(agent);
			if(agents!=null && agents.size()>0){
				result.setCode("0");
				result.setMessage("代理人已经存在");
			}else{
				commonAgentMapper.insert(agent);
				result.setCode("1");
				result.setMessage("添加代理人信息成功");
			}
		}catch(Exception e){
			log.error("系统异常："+e);
			result.setCode("0");
			result.setMessage("系统异常");
		}
		json.setResult(result);
		return HttpResult.success(json);
	}

	/**
	 * 更新常用代理人信息
	 * @param agentVO
	 * @return
	 */
	@Override
	public HttpResult<?> updateCommonAgent(CommonAgentVO agentVO) {
		JsonVO json = new JsonVO();
		ResultVO  result = new ResultVO();
		if(StringUtils.isBlank(agentVO.getId())){
			return HttpResult.error(0,"id不能为空");
		}
		if(StringUtils.isBlank(agentVO.getAgentCode())){
			return HttpResult.error(0,"代理人代码不能为空！");
		}
		if(StringUtils.isBlank(agentVO.getAgentName())){
			return HttpResult.error(0,"代理人代码不能为空！");
		}
		if(StringUtils.isBlank(agentVO.getBussinessSource())){
			return HttpResult.error(0,"业务来源不能为空！");
		}
		CommonAgent agent=new CommonAgent();
		agent.setStaffCode(agentVO.getUserCode());
		agent.setId(agentVO.getId());
		agent.setAgentCode(agentVO.getAgentCode());
		agent.setAgentName(agentVO.getAgentName());
		agent.setBusinessSource(agentVO.getBussinessSource());
		try{
			commonAgentMapper.update(agent);
			result.setCode("1");
			result.setMessage("更新代理人信息成功");
		}catch(Exception e){
			//log.error("更新代理人信息异常",e);
			HttpResult.error(0,"更新代理人信息异常");
		}
		json.setResult(result);
		return HttpResult.success(json);
	}

	/**
	 * 代理人手续费查询
	 * @param planFeeInquiryRequestVO
	 * @return
	 */
	@Override
	public HttpResult<?> planFeeInquiry(PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		JsonVO jsonVO = null;
		try {
			String queryType = planFeeInquiryRequestVO.getQueryType();//01-查询个人代理所有未结算的手续费金额,02-根据归属经办人查询下属个人代理所有未结算的手续费金额

			if (StringUtils.isBlank(queryType)) {
				return HttpResult.error(0,"查询类型不能为空");
			}	
			jsonVO =planFeeInquiryImpl(planFeeInquiryRequestVO);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			return HttpResult.error(0,"未查询到代理人手续费");
		}
		return HttpResult.success(jsonVO);
	}
	
	/**
	 * 当日手续费明细查询
	 * @param requestVO
	 * @return
	 */
	@Override
	public HttpResult<?> planFeeDetailInquiry(PlanFeeDetailInquiryRequestVO requestVO) {
		try {
			String agentCode=requestVO.getAgentCode();
			String handlerCode=requestVO.getHandlerCode();
			PlanFeeDetailInquiryResponseVO responseVO =  new PlanFeeDetailInquiryResponseVO();
			int offset = (requestVO.getPage().getPage()-1)*requestVO.getPage().getSize();
			int limit = requestVO.getPage().getSize();
			List<PlanFeeDetailVO> planFeeDetails = insuranceMapper.planFeeDetailInquiry(agentCode,handlerCode,offset,limit);
			int count = insuranceMapper.planFeeDetailInquiryNum(agentCode,handlerCode);
			requestVO.getPage().init(count);
			responseVO.setPage(requestVO.getPage());
			responseVO.setPlanFeeDetails(planFeeDetails);
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("agentCode", agentCode);
			paraMap.put("handlerCode", handlerCode);
			BigDecimal pendingEffectFee = insuranceMapper.pendingEffectFeeQuery(paraMap);
			if(pendingEffectFee!=null){
				responseVO.setPlanFeeSum(com.ccic.salessapp.common.utils.StringUtils.format2MoneyStr(pendingEffectFee.doubleValue()));//手续费明细合计
			}else{
				responseVO.setPlanFeeSum("0.00");//手续费明细合计
			}
			return HttpResult.success(responseVO);
		} catch (Exception e) {
			log.error("查询异常"+e);
			return  HttpResult.error(0,"查询异常");
		}
	}

	/**
	 *手续费提现
	 * @param requestVO
	 * @return
	 */
	@Override
	public HttpResult<?> billPackToFK(BillPackToFKRequest requestVO) {
		if(!"1".equals(requestVO.getCheckAllFlag()) && requestVO.getPolicyNOList().size()<1){
			return HttpResult.error(0,"请选择提现保单");
		}

		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("configType", "no_pay_fee_date");
		try {
			String noPayFeeDate = generalConfigMapper.getConfigValueNoCache(paraMap);
			int startDate = Integer.parseInt(noPayFeeDate.split("-")[0]);
			int endDate = Integer.parseInt(noPayFeeDate.split("-")[1]);
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int nowDay = c.get(Calendar.DAY_OF_MONTH);
			int nowDayWeek = c.get(Calendar.DAY_OF_WEEK)-1;
			int nowHour = c.get(Calendar.HOUR_OF_DAY);

			if(nowDay>=startDate && nowDay<=endDate){
				return HttpResult.error(0,startDate+"日至"+endDate+"日不能提现手续费");
			}
			if(nowDayWeek==5 && nowHour>=20 && nowHour<=24){
				return HttpResult.error(0,"每周五20-24点不能提现手续费");
			}
		} catch (Exception e) {
			log.error("提现异常"+e);
			return HttpResult.error(0,"提现异常");
		}

		try {

			return billPackToFKBuild(requestVO);
			
		} catch (Exception e) {
			log.error("提现异常"+e);
			return HttpResult.error(0,"提现异常");
		}

	}

	/**
	 * 提现页面初始化查询
	 * @param
	 * @return
	 */
	@Override
	public HttpResult<?> queryPolicyList(QueryPolicyVo queryPolicyVo) {
		//PageVO pageVO = new PageVO();
		Integer pageSize=queryPolicyVo.getPage().getSize();
		Integer currentPage=queryPolicyVo.getPage().getPage();
		if(pageSize==null){
			return HttpResult.error(0,"请输入页记录数！");
		}
		if(currentPage==null){
			return HttpResult.error(0,"请输入当前页数！");
		}
		try {
			JsonVO jsonVO1=queryPolicyListBuild(queryPolicyVo);
			return HttpResult.success(jsonVO1);
		} catch (Exception e) {
			return HttpResult.error(0,"提现页面初始化查询异常");
		}
	}

	/**
	 * 修改提现设置
	 * @param requestVO
	 * @return
	 */
	@Override
	public HttpResult<?> updateConfig(UpdateConfigRequest requestVO) {
		if(requestVO.getIsAutomatic()==null){
			return HttpResult.error(0,"是否提现不能为空");
		}
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("configType", "no_pay_fee_date");
		try {
			String noPayFeeDate = generalConfigMapper.getConfigValueNoCache(paraMap);
			int startDate = Integer.parseInt(noPayFeeDate.split("-")[0]);
			int endDate = Integer.parseInt(noPayFeeDate.split("-")[1]);
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int nowDay = c.get(Calendar.DAY_OF_MONTH);

			if(nowDay>=startDate && nowDay<=endDate){
				return HttpResult.error(0,"手工提现功能关闭期间不允许修改设置！");
			}
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}

		try {
			return updateConfigByAgentCode(requestVO, requestVO.getAgentCode());
			//return updateConfig(requestVO,requestVO.getUserCode());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return HttpResult.error(0,"修改提现配置信息异常");
		}

	}

	/**
	 *查询用户的提现配置
	 * @return
	 */
	@Override
	public HttpResult<?> queryWithdrawalConfig(QueryWithdrawalVo queryWithdrawalVo) {

		JsonVO jsonVO=new JsonVO();
		ResultVO result=new ResultVO();
		QueryConfigResponseVO responseVO=new QueryConfigResponseVO();
		//查询代理人代码
		AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByAgentCode(queryWithdrawalVo.getAgentCode());
		
		String agentCode=agentHandlerInfo.getAgentCode();
		if(agentCode==null||"".equals(agentCode)){
			return HttpResult.error(0,"查询代理人信息为空");
		}
		EnchashmentConfig enchashmentConfig=null;
		//查询提现配置信息
		enchashmentConfig=enchashmentMapper.selectConfigByAgentCode( agentCode);
		if(enchashmentConfig!=null){
			responseVO.setIsAutomatic(enchashmentConfig.getIsAutomatic());
			responseVO.setFrequency(enchashmentConfig.getFrequency());
		}
		else{
			responseVO.setIsAutomatic("2");//提现配置模式初始化为手动提现
		}
		result.setCode("1");
		result.setMessage("提现配置查询成功");
		jsonVO.setResult(result);
		jsonVO.setQueryConfigResponseVO(responseVO);
		return HttpResult.success(jsonVO);
	}

	/**
	 * 查询提现明细记录
	 * @param requestVO
	 * @return
	 */
	@Override
	public HttpResult<?> feePayDetailQuery(FeePayDetailRequest requestVO) {
		
		JsonVO jsonVO=new JsonVO();
		ResultVO result=new ResultVO();
	
		FeePayDetailResponseVO responseVO=new FeePayDetailResponseVO();
		SystemConfig cofig=new SystemConfig("system");
		String dagentCode = null;
		try {
			dagentCode = cofig.getResourceValue("agentCode");
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			log.error("查询提现明细记录异常！"+e);
		}
		String agentCode=requestVO.getAgentCode();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("agentCode", agentCode);
		
		if(StringUtils.isNotBlank(requestVO.getMonth())){
			String[] sp = requestVO.getMonth().split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR,Integer.parseInt(sp[0]));
	        //设置月份
			calendar.set(Calendar.MONTH, Integer.parseInt(sp[1])-1);
        	calendar.set(Calendar.DAY_OF_MONTH, 2);
        	paramMap.put("startDay", datef.format(calendar.getTime()));	
        	calendar.add(Calendar.MONTH, 1);
        	calendar.set(Calendar.DAY_OF_MONTH, 1);
        	paramMap.put("endDay", datef.format(calendar.getTime()));//下月的1号
		}
		if("0".equals(requestVO.getQueryType())){//按月查询
			Double sumFeePayMonth = null;
			Double sumTaxMonth = null;
			Double sumFeeAccountMonth = null;
			FeeQueryVO feeQueryVO = planFeePayMapper.queryBillFee(paramMap);
			if(feeQueryVO!=null){
				sumFeePayMonth = feeQueryVO.getSumBillFee();
				sumTaxMonth = feeQueryVO.getSumBillTax();
				sumFeeAccountMonth = feeQueryVO.getSumAccountFee();
			}
			responseVO.setSumFeePayMonth(sumFeePayMonth==null?"0.00":new DecimalFormat("#0.00").format(sumFeePayMonth));
			responseVO.setSumFeeAccountMonth(sumFeeAccountMonth==null?"0.00":new DecimalFormat("#0.00").format(sumFeeAccountMonth));
			responseVO.setSumTaxDeductionMonth(sumTaxMonth==null?"0.00":new DecimalFormat("#0.00").format(sumTaxMonth));
				
			//按月查询默认查5个批次
			PageVO page1=new PageVO();
			page1.setPage(1);
			page1.setSize(2);
			List<PackVO> list = null;
			try {
			//批次查询
			  list=queryPackNo(paramMap,page1);
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode("0");
				result.setMessage("数据列表查询异常");
			}
			
			responseVO.setPacks(list);
			for(PackVO pack : responseVO.getPacks()) {
				System.out.println(pack.getPackSumFee()); 
			}
			result.setCode("1");
			result.setMessage("按月查询成功");
			jsonVO.setResult(result);
			jsonVO.setFeePayDetailResponseVO(responseVO);
		}else if("1".equals(requestVO.getQueryType())){
			List<PackVO> list = null;
			try {
				 list=queryPackNo(paramMap,requestVO.getPage());
			} catch (Exception e) {
				log.error("数据列表查询异常",e);
				result.setCode("0");
				result.setMessage("数据列表查询异常");
		    }
			responseVO.setPacks(list);
			result.setCode("1");
			result.setMessage("更多批次查询成功");
			jsonVO.setResult(result);
			jsonVO.setFeePayDetailResponseVO(responseVO);
		}else if("2".equals(requestVO.getQueryType())){
			List<PackVO> packList=new ArrayList<PackVO>();
			PackVO packVO=new PackVO();
			packVO.setPlanFeePays(queryFeePayList(requestVO.getPackNo(),paramMap,requestVO.getPage()));
			packVO.setPackNo(requestVO.getPackNo());
			packList.add(packVO);
			responseVO.setPacks(packList);
			result.setCode("1");
			result.setMessage("更多保单查询成功");
			jsonVO.setResult(result);
			jsonVO.setFeePayDetailResponseVO(responseVO);
		}
		return HttpResult.success(jsonVO);

	}

	/**
	 * 代理人注册信息查询
	 * @param planFeeInquiryRequestVO
	 * @return
	 */
	@Override
	public HttpResult<?> queryInviteUserDetail(PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {

		JsonVO jsonVO = new JsonVO();
		ResultVO  result = new ResultVO();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			InviteListResponseVO userDetailList = new InviteListResponseVO();
			List<InviteDetailResponseVO> voList = new ArrayList<InviteDetailResponseVO>();
			InviteDetailResponseVO userVO = new InviteDetailResponseVO();
			//根据邀请人工号查询被邀请人信息集合i
			PageVO page = planFeeInquiryRequestVO.getPage();
			int offset = (page.getPage()-1)*page.getSize();
			int limit = page.getSize();
			List<InviteUserVO> detailList = inviteUserMapper.selectByInviteUsercode(planFeeInquiryRequestVO.getUserCode(),offset,limit);
			int count = inviteUserMapper.selectByInviteUsercodeNum(planFeeInquiryRequestVO.getUserCode()) ;
			page.init(count);
			if (detailList != null && detailList.size() !=0) {
				if (detailList != null && detailList.size() > 0) {
					for(InviteUserVO inviteUserVO:detailList){
						userVO = new InviteDetailResponseVO();
						userVO.setPcode(inviteUserVO.getPcode());
						userVO.setQualificationsCode(inviteUserVO.getQualificationsCode());
						userVO.setUserName(inviteUserVO.getUserName());
						userVO.setExamScore(inviteUserVO.getExamScore());
						userVO.setExamState(inviteUserVO.getExamState());
						if (inviteUserVO.getExamDate() != null) {
							userVO.setExamDate(sdf.format(inviteUserVO.getExamDate()));
						}
						userVO.setState(inviteUserVO.getExamineState());
						userVO.setResult(inviteUserVO.getExamineResult());
						if (inviteUserVO.getUpdateDate() != null) {
							userVO.setUpdateDate(sdf.format(inviteUserVO.getUpdateDate()));
						}
						voList.add(userVO);
					}
				}
				userDetailList.setPage(page);
				userDetailList.setList(voList);
				result.setCode("1");
				result.setMessage("查询成功");
				jsonVO.setResult(result);
				jsonVO.setInviteListResponseVO(userDetailList);
			}
		} catch (Exception e) {
			return HttpResult.error(0,"查询代理人注册信息失败");
		}
		return HttpResult.success(jsonVO);

	}

	//提现批次分页查询
	private List<PackVO> queryPackNo(Map<String,String> paramMap, PageVO pageVO) {
		List<PackNoVO> list=new ArrayList<PackNoVO>();
		List <PackVO> packList=new ArrayList<PackVO>();
		//每个批次默认查询10调保单
		PageVO page=new PageVO();
		page.setPage(1);
		page.setSize(10);
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("startDay", paramMap.get("startDay"));
			map.put("endDay", paramMap.get("endDay"));
			map.put("agentCode", paramMap.get("agentCode"));
			map.put("offset", (pageVO.getPage()-1)*pageVO.getSize());
			map.put("limit",pageVO.getSize());
			list = planFeePayMapper.queryPackNo(map);
			int count = planFeePayMapper.queryPackNoNum(paramMap);
			for(PackNoVO p:list){
				PackVO packVO=new PackVO();
				packVO.setPackNo(p.getPackNo());
				packVO.setPackageDate(datef.format(p.getPackageDate()));
				//每个批次的保单查询
				packVO.setPackSumFee(new DecimalFormat("#0.00").format(p.getPackSumFee()));
				packVO.setPlanFeePays(queryFeePayList(p.getPackNo(),paramMap,page));
				packList.add(packVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("提现明细批次查询异常");
		}
		return packList;
	}
		
	//提现保单分页查询
	private List<PlanFeePayVO> queryFeePayList(String packNo, Map<String,String> paramMap, PageVO pageVO) {
		  List<PlanFeePayVO> pflist=new ArrayList <PlanFeePayVO>();
		  paramMap.put("packNo", packNo);
		  try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("packNo", packNo);
			map.put("agentCode", paramMap.get("agentCode"));
			map.put("offset", (pageVO.getPage()-1)*pageVO.getSize());
			map.put("limit",pageVO.getSize());
			List<PlanFeePay> list = planFeePayMapper.queryPolicy(map);
			int count = planFeePayMapper.queryPolicyNum(paramMap);
			pflist=formatPlanFee(list);
		  } catch (Exception e) {
			e.printStackTrace();
			log.error("提现明细保单查询异常");
		}
		  return pflist;
		}

	//更新提现设置方法
	public HttpResult updateConfig(UpdateConfigRequest requestVO,String userCode) throws Exception {
		HttpResult result = HttpResult.success();
		//查询代理人代码
		AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByUserCode(userCode);
		if(agentHandlerInfo.getAgentCode()==null||"".equals(agentHandlerInfo.getAgentCode())){
			result.setCode("0");
			result.setMsg("查询代理人信息为空");
			return result;
		}
		EnchashmentConfig enchashmentConfig=null;
		//查询提现配置信息
		enchashmentConfig=enchashmentMapper.selectConfigByAgentCode(agentHandlerInfo.getAgentCode());
		com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO requestHead= new com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		WithdrawalsRequest request=new WithdrawalsRequest();
		WithdrawalsRequestDTO requestDTO=new WithdrawalsRequestDTO();
		if("1".equals(requestVO.getIsAutomatic())){//自动提现
			requestDTO.setSettleType("Y");//结算类型 N 手动结算  Y  自动结算
			requestDTO.setValidStatus("1");//是否有效
			requestDTO.setSettleCycle(requestVO.getFrequency());//结算频率
		}
		if("2".equals(requestVO.getIsAutomatic())){//手动提现
			requestDTO.setSettleType("Y");//结算类型 N 手动结算  Y  自动结算
			requestDTO.setValidStatus("0");//是否有效
		}
		requestDTO.setAgentCode(agentHandlerInfo.getAgentCode());
		request.setRequestBody(requestDTO);
		request.setRequestHead(requestHead);

		//log.info("#### 调用手续费提现接口start,请求信息："+xs.toXML(request));
		WithdrawalsResponse response=null;
		try{
			System.out.println("#### 调用手续费提现接口接口end###"+JSONObject.toJSONString(request));
			response=withdrawalsPort.withdrawals(request);
			System.out.println(JSONObject.toJSONString(response));
			log.info("#### 调用手续费提现接口接口end###"+JSONObject.toJSONString(response));
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMsg("调用提现设置接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
				return result;
			}
		}catch (Exception e) {
			log.error("调用提现设置接口异常：", e);
			result.setCode("0");
			result.setMsg("调用提现设置接口异常："+e.getMessage());
			return result;
		}
		//查看提现设置表该代理人是否已经存在 不存在就存入设置，存在就更新记录
		if(enchashmentConfig==null){
			EnchashmentConfig config=new EnchashmentConfig();
			config.setAgentCode(agentHandlerInfo.getAgentCode());//存代理人代码
			config.setIsAutomatic(requestVO.getIsAutomatic());//是否自动提现
			config.setFrequency(requestVO.getFrequency());//提现频率
			enchashmentMapper.insert(config);
		}
		else{
			EnchashmentConfig config=new EnchashmentConfig();
			config.setAgentCode(agentHandlerInfo.getAgentCode());
			config.setIsAutomatic(requestVO.getIsAutomatic());
			config.setFrequency(requestVO.getFrequency());
			enchashmentMapper.update( config);

		}
		result.setCode("1");
		result.setData(response.getResponseBody());
		result.setMsg(response.getResponseHead().getAppMessage());
		return result;
	}
	
	public HttpResult updateConfigByAgentCode(UpdateConfigRequest requestVO,String agentCode) throws Exception {
		System.out.println("###根据代理人工号修改提现设置###");
		HttpResult result = HttpResult.success();
		//查询代理人代码
		AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByAgentCode(agentCode);
		if(agentHandlerInfo.getAgentCode()==null||"".equals(agentHandlerInfo.getAgentCode())){
			result.setCode("0");
			result.setMsg("查询代理人信息为空");
			return result;
		}
		EnchashmentConfig enchashmentConfig=null;
		//查询提现配置信息
		enchashmentConfig=enchashmentMapper.selectConfigByAgentCode(agentHandlerInfo.getAgentCode());
		com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO requestHead= new com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		WithdrawalsRequest request=new WithdrawalsRequest();
		WithdrawalsRequestDTO requestDTO=new WithdrawalsRequestDTO();
		if("1".equals(requestVO.getIsAutomatic())){//自动提现
			requestDTO.setSettleType("Y");//结算类型 N 手动结算  Y  自动结算
			requestDTO.setValidStatus("1");//是否有效
			requestDTO.setSettleCycle(requestVO.getFrequency());//结算频率
		}
		if("2".equals(requestVO.getIsAutomatic())){//手动提现
			requestDTO.setSettleType("Y");//结算类型 N 手动结算  Y  自动结算
			requestDTO.setValidStatus("0");//是否有效
		}
		requestDTO.setAgentCode(agentHandlerInfo.getAgentCode());
		request.setRequestBody(requestDTO);
		request.setRequestHead(requestHead);

		//log.info("#### 调用手续费提现接口start,请求信息："+xs.toXML(request));
		WithdrawalsResponse response=null;
		try{
			System.out.println("#### 调用手续费提现接口接口end###"+JSONObject.toJSONString(request));
			response=withdrawalsPort.withdrawals(request);
			System.out.println(JSONObject.toJSONString(response));
			//log.info("#### 调用手续费提现接口接口end###"+JSONObject.fromObject(response).toString());
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMsg("调用提现设置接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
				return result;
			}
		}catch (Exception e) {
			log.error("调用提现设置接口异常：", e);
			result.setCode("0");
			result.setMsg("调用提现设置接口异常："+e.getMessage());
			return result;
		}
		//查看提现设置表该代理人是否已经存在 不存在就存入设置，存在就更新记录
		if(enchashmentConfig==null){
			EnchashmentConfig config=new EnchashmentConfig();
			config.setAgentCode(agentHandlerInfo.getAgentCode());//存代理人代码
			config.setIsAutomatic(requestVO.getIsAutomatic());//是否自动提现
			config.setFrequency(requestVO.getFrequency());//提现频率
			enchashmentMapper.insert(config);
		}
		else{
			EnchashmentConfig config=new EnchashmentConfig();
			config.setAgentCode(agentHandlerInfo.getAgentCode());
			config.setIsAutomatic(requestVO.getIsAutomatic());
			config.setFrequency(requestVO.getFrequency());
			enchashmentMapper.update( config);

		}
		result.setCode("1");
		result.setData(response.getResponseBody());
		result.setMsg(response.getResponseHead().getAppMessage());
		return result;
	}

	//查询可提现保单
	public JsonVO queryPolicyListBuild(QueryPolicyVo queryPolicyVo) throws Exception {
		JsonVO jsonVO=new JsonVO();
		ResultVO result=new ResultVO();
		EnchashmentResponseVO responseVO=new EnchashmentResponseVO();
		List<PlanFeePay> list=null;
		//查询代理人代码
		AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByAgentCode(queryPolicyVo.getAgentCode());
		String agentCode=agentHandlerInfo.getAgentCode();
		if(agentCode==null||"".equals(agentCode)){
			result.setCode("0");
			result.setMessage("查询代理人信息为空");
			jsonVO.setResult(result);
			return jsonVO;
		}
		try{
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("agentCode", agentCode);
			//paraMap.put("queryFlag", "1");
			Double sumPlanFee=planFeePayMapper.sumPlanFee(paraMap);
			responseVO.setSumPlanFee(sumPlanFee!=null?String.valueOf(sumPlanFee):"0");
			FeeVo feeVo1 = new FeeVo();
			feeVo1.setAgentCode(agentCode);
			double fee= planFeePayMapper.agentFee(feeVo1);
			responseVO.setAllFee(fee+"");
			PlanFeePay planFeePay=new PlanFeePay();
			planFeePay.setAgentCode(agentCode);
			FeeVo feeVo = new FeeVo();
			int start = (queryPolicyVo.getPage().getPage()-1)*queryPolicyVo.getPage().getSize();
			feeVo.setAgentCode(agentCode);
			feeVo.setStart(start);
			feeVo.setPageSize(queryPolicyVo.getPage().getSize());
			System.out.println(feeVo.getPageSize());
			list = planFeePayMapper.listByAgentCode(feeVo);	
		}catch (Exception e) {
			return jsonVO;
		}
		if(list!=null && list.size()>0){
			responseVO.setPolicyList(formatPlanFee(list));
		}
		
		result.setCode("1");
		result.setMessage("手续费保单查询成功");
		jsonVO.setResult(result);
		jsonVO.setEnchashmentResponseVO(responseVO);
		jsonVO.setList(list);
		return jsonVO;
	}
	
	private List<PlanFeePayVO> formatPlanFee(List<PlanFeePay> list) {

		List<PlanFeePayVO> pflist=new ArrayList<PlanFeePayVO>();
		for(PlanFeePay p:list){
			PlanFeePayVO planFeePayVO=new PlanFeePayVO();
			planFeePayVO.setPlanFee(new DecimalFormat("#0.00").format(p.getPlanFee()));
			planFeePayVO.setCertiId(p.getCertiId());
			planFeePayVO.setCertiNo(p.getCertiNo());
			pflist.add(planFeePayVO);
		}
		return pflist;

	}

	//手续费提现
	public HttpResult billPackToFKBuild(BillPackToFKRequest requestVO)throws Exception{
		HttpResult result = HttpResult.success();
		RequestHeadDTO requestHead=this.getConsumerID();
		WithdrawalsRequest request=new WithdrawalsRequest();
		WithdrawalsRequestDTO requestDTO=new WithdrawalsRequestDTO();
		requestDTO.setSettleType("N");//结算类型 N 手动结算  Y  自动结算
		requestDTO.setAgentCode(requestVO.getAgentCode());
		billPack(requestDTO,requestVO);
		request.setRequestBody(requestDTO);

		com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO headdto=
				new com.ccic.salesapp.performance.outService.esb.Withdrawals.RequestHeadDTO();

		headdto.setConsumerID(requestHead.getConsumerID());
		request.setRequestHead(headdto);
		//log.info("#### 调用手续费提现接口start,请求信息："+xs.toXML(request));
		WithdrawalsResponse response=null;
		List<DetailDTO> detail = null;
		try{
			System.out.println("#### 调用手续费提现接口接口start###"+JSONObject.toJSONString(request));
			if(requestDTO.getCertiIdList()==null || requestDTO.getCertiIdList().size()==0)
				return HttpResult.error(0, "非可结算的手续费");
			response=withdrawalsPort.withdrawals(request);
			System.out.println("#### 调用手续费提现接口接口end###"+JSONObject.toJSONString(response));
			log.info("#### 调用手续费提现接口接口end###"+ JSONObject.toJSONString(response));
			if (response.getResponseHead().getStatus()!=0) {
				return HttpResult.error(0, "调用手续费提现接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
			}
			//0
		}catch (Exception e) {
			log.error("调用手续费提现接口接口异常：", e);
			return HttpResult.error(0, "调用手续费提现接口接口异常："+e.getMessage());
		}
		updateWithdrawalsFlag(detail,requestVO.getPolicyNOList(),requestVO.getAgentCode());
		return result.success(response.getResponseBody(), 1, response.getResponseHead().getAppMessage());
	}
	//打包选择的业务号
	private void billPack(WithdrawalsRequestDTO requestDTO,BillPackToFKRequest requestVO) throws Exception {
		if("1".equals(requestVO.getCheckAllFlag())){
			PlanFeePay planFeePay=new PlanFeePay();
			planFeePay.setAgentCode(requestDTO.getAgentCode());
			List<PlanFeePay> list = planFeePayMapper.listByAgentCode(planFeePay);
			if(list!=null && list.size()>0){
				List<String> policyNOList = new ArrayList<String>();
				for(PlanFeePay p:list){
					if(StringUtils.isNotBlank(p.getCertiId())){
						policyNOList.add(p.getCertiId());
					}
				}
				requestVO.setPolicyNOList(policyNOList);
			}
		}

		int listSize=requestVO.getPolicyNOList().size()/100;
		int m=requestVO.getPolicyNOList().size()%100;
		if(m!=0){
			listSize=listSize+1;
		}
		for(int i=0;i<listSize;i++){
			StringBuilder idList=new StringBuilder("");
			for(int j=0;j<100;j++){
				idList.append(requestVO.getPolicyNOList().get(i*100+j));
				if(j<99&&(i*100+j)<requestVO.getPolicyNOList().size()-1){
					idList.append(",");
				}
				if((i*100+j)==requestVO.getPolicyNOList().size()-1){
					break;
				}
			}
			requestDTO.getCertiIdList().add(idList.toString());
		}
	}

	//更新提现状态
	private void updateWithdrawalsFlag(List<DetailDTO> detail, List<String> policyNOList, String agentCode)
			throws Exception {
		try {
			for(String certiId:policyNOList){
				PlanFeePay planFeePay=new PlanFeePay();
				planFeePay.setCertiId(certiId);
				planFeePay.setWithdrawalsFlag("9");
				if (detail!= null && detail.size()>0) {
					for (DetailDTO detailDTO : detail) {
						if (detailDTO.getCertiId().equals(certiId) && "1".equals(detailDTO.getFlag())) {
							planFeePay.setWithdrawalsFlag("5");
							planFeePay.setWithdrawalsMsg(detailDTO.getMessage());
							break;
						}

					}
				}
				planFeePay.setAgentCode(agentCode);
				planFeePayMapper.toUpdate(planFeePay);
			}
		}catch(Exception e){
			log.error("更新提现状态异常："+e);
		}
	}

	//代理人手续费查询子方法1
	public JsonVO planFeeInquiryImpl(PlanFeeInquiryRequestVO requestVO)throws Exception{
		String userCode=null;
		if (StringUtils.isNotBlank(requestVO.getAgentCode())) {
			AgentHandlerInfo agentHandlerInfo = agentHandlerMappper.selectAgentHandlerInfo(requestVO.getAgentCode());
			if (StringUtils.isBlank(requestVO.getHandlerCode())) {
				requestVO.setHandlerCode(agentHandlerInfo.getHandlerCode());
			}
			userCode= agentHandlerInfo.getUserCode();
		}	
		DecimalFormat df=new DecimalFormat("#0.00");
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		JsonVO jsonVO=new JsonVO();
		ResultVO result=new ResultVO();
		String agentCode=null;
		String handlercode=null;
		PlanFeeInquiryResponseVO responseVO = new PlanFeeInquiryResponseVO();
		if("01".equals(requestVO.getQueryType())){
			agentCode=requestVO.getAgentCode();
			AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByAgentCode(agentCode);
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("agentCode", agentCode);
			if(StringUtils.isNotBlank(userCode)) {
				paraMap.put("handlerCode", "agen".equals(userCode.substring(0, 4))?null:userCode);
			}
			BigDecimal pendingEffectFee = insuranceMapper.pendingEffectFeeQuery(paraMap);

			if(pendingEffectFee!=null){
				responseVO.setPendingEffectFee(com.ccic.salessapp.common.utils.StringUtils.format2MoneyStr(pendingEffectFee.doubleValue()));//待生效手续费
			}else{
				responseVO.setPendingEffectFee("0.00");//待生效手续费
			}

			Map<String,String> p = new HashMap<String,String>();
			p.put("configType", "agent_minPayFee");
			p.put("comCode", agentHandlerInfo.getComCode());
			String minPayFee = generalConfigMapper.getConfigValueNoCache(p);
			if(StringUtils.isBlank(minPayFee)){
				minPayFee="500";//默认500
			}
			responseVO.setMinPayFee(minPayFee);
			
			//提现开关 0 可提现 1 不可提现
			p.put("configType", "not_allowed_pay");
			if(StringUtils.isNotBlank(agentHandlerInfo.getComCode())) {
				p.put("comCode", agentHandlerInfo.getComCode().substring(0, 4)+"0000");
			}
			String notAllowedPay = generalConfigMapper.getConfigValueNoCache(p);
			if(StringUtils.isBlank(notAllowedPay)){
				notAllowedPay="0";//默认可提现
			}
			responseVO.setNotAllowedPay(notAllowedPay);

			List<PlanFeeVO> planFees = new ArrayList<PlanFeeVO>();
			PlanFeeVO planFeeVO = new PlanFeeVO();
			p.clear();
			p.put("agentCode", agentCode);
			Double sumPlanFee = planFeePayMapper.sumPlanFee(p);
			planFeeVO.setPlanFee(sumPlanFee==null?"0.00":df.format(sumPlanFee));

			Double sumFeePayYear=0.00;		//年提现总额
			Double sumTaxYear=0.00;			//年扣税总额
			Double sumFeeAccountYear=0.00;	//年到账总额
			Double sumFeePayMonth=0.00;		//月提现总额
			Double sumTaxMonth=0.00;		//月扣税总额
			Double sumFeeAccountMonth=0.00;	//月到账总额

			String monthStartDay = null;//
			String monthEndDay = null;//
			String yearStartDay = null;//
			String yearEndDay = null;//
			Calendar calendar = Calendar.getInstance();
			Calendar calendarYear = Calendar.getInstance();
			int today = calendar.get(Calendar.DAY_OF_MONTH);
			if (today >= 2) {	//当前日期大于等于2号
				calendar.set(Calendar.DAY_OF_MONTH, 2);
				monthStartDay = datef.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				monthEndDay = datef.format(calendar.getTime());//下月的1号
			}else{
				monthEndDay = datef.format(calendar.getTime());
				calendar.add(Calendar.MONTH, -1);
				calendar.set(Calendar.DAY_OF_MONTH, 2);
				monthStartDay = datef.format(calendar.getTime());
			}
			if(today >= 2 || calendarYear.get(Calendar.MONTH)>0){
				calendarYear.set(Calendar.MONTH, 0);
				calendarYear.set(Calendar.DAY_OF_MONTH, 2);
				yearStartDay = datef.format(calendarYear.getTime());
				calendarYear.add(Calendar.YEAR, 1);
				calendarYear.set(Calendar.DAY_OF_MONTH, 1);
				yearEndDay = datef.format(calendarYear.getTime());
			}else{
				yearEndDay = datef.format(calendarYear.getTime());
				calendarYear.add(Calendar.YEAR, -1);
				calendarYear.set(Calendar.DAY_OF_MONTH, 2);
				yearStartDay = datef.format(calendarYear.getTime());
			}
			paraMap.clear();
				paraMap.put("handlerCode",requestVO.getHandlerCode());
				paraMap.put("agentCode",requestVO.getAgentCode());
				paraMap.put("startDay", yearStartDay);
				paraMap.put("endDay", yearEndDay);
				FeeQueryVO feeQueryVO = planFeePayMapper.queryBillFeeByUserCode(paraMap);

				if(feeQueryVO!=null){
					sumFeePayYear = feeQueryVO.getSumBillFee();
					sumTaxYear = feeQueryVO.getSumBillTax();
					sumFeeAccountYear = feeQueryVO.getSumAccountFee();
				}

				paraMap.put("startDay", monthStartDay);
				paraMap.put("endDay", monthEndDay);
				feeQueryVO = planFeePayMapper.queryBillFeeByUserCode(paraMap);

				if(feeQueryVO!=null){
					sumFeePayMonth = feeQueryVO.getSumBillFee();
					sumTaxMonth = feeQueryVO.getSumBillTax();
					sumFeeAccountMonth = feeQueryVO.getSumAccountFee();
				}	


				paraMap.put("startDay", monthStartDay);
				paraMap.put("endDay", monthEndDay);


			planFeeVO.setSumFeePayYear(sumFeePayYear==null?"0.00":df.format(sumFeePayYear));
			planFeeVO.setSumFeeAccountYear(sumFeeAccountYear==null?"0.00":df.format(sumFeeAccountYear));
			planFeeVO.setSumFeePayMonth(sumFeePayMonth==null?"0.00":df.format(sumFeePayMonth));
			planFeeVO.setSumFeeAccountMonth(sumFeeAccountMonth==null?"0.00":df.format(sumFeeAccountMonth));
			planFeeVO.setSumTaxDeductionYear(sumTaxYear==null?"0.00":df.format(sumTaxYear));
			planFeeVO.setSumTaxDeductionMonth(sumTaxMonth==null?"0.00":df.format(sumTaxMonth));
			planFees.add(planFeeVO);
			responseVO.setPlanFees(planFees);
			//this.planFeeInquiryInterface(responseVO, "03", agentCode, null);
		}else if("02".equals(requestVO.getQueryType())){//根据归属经办人查询下属个人代理所有未结算的手续费金额
			handlercode=requestVO.getHandlerCode();
			this.planFeeInquiryInterface(responseVO, "02", null, handlercode);
		}else if("03".equals(requestVO.getQueryType())){
			AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByUserCode(userCode);
			if(agentHandlerInfo!=null){
				agentCode=agentHandlerInfo.getAgentCode();
				this.planFeeInquiryInterface(responseVO, "03", agentCode, null);
			}	
		}
		result.setCode("1");
		result.setMessage("成功");
		jsonVO.setPlanFeeInquiryResponseVO(responseVO);
		return jsonVO;
	}

	//手续费查询子方法2
	public void planFeeInquiryInterface(PlanFeeInquiryResponseVO responseVO,String queryType,String agentCode,String handlerCode) throws Exception{

		RequestHeadDTO requestHead=this.getConsumerID();
		
		com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.RequestHeadDTO headdto=
				new com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.RequestHeadDTO();
		headdto.setConsumerID(requestHead.getConsumerID());
		requestHead.setConsumerID("supsale");
		PlanFeeInquiryRequest request = new PlanFeeInquiryRequest();
		com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeRequestDTO requestDTO=new 
				com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeRequestDTO();

		requestDTO.setQueryType(queryType);
		requestDTO.setAgentCode(agentCode);
		requestDTO.setHandlerCode(handlerCode);
		
		request.setRequestHead(headdto);
		request.setRequestBody(requestDTO);
		
		

		PlanFeeInquiryResponse response = planFeeInquiryPort.planFeeInquiry(request);//调销管系统手续费查询接口
		
		PlanFeeResponseDTO responseDTO = null;

		if(response.getResponseHead().getStatus()==0){
			responseDTO = response.getResponseBody();
			if(responseDTO.getPlanFeeInfo()!=null && responseDTO.getPlanFeeInfo().size()>0){
				List<PlanFeeVO> planFees = new  ArrayList<PlanFeeVO>();
				for(PlanFeeDTO p:responseDTO.getPlanFeeInfo()){
					PlanFeeVO planFeeVO = new PlanFeeVO();
					planFeeVO.setAgentCode(p.getAgentCode());
					planFeeVO.setAgentName(p.getAgentName());
					if("02".equals(queryType)){
						Map<String,String> paraMap = new HashMap<String,String>();
						paraMap.put("agentCode", p.getAgentCode());
						Double sumPlanFee = planFeePayMapper.sumPlanFee(paraMap);
						planFeeVO.setPlanFee(sumPlanFee==null?"0.00":com.ccic.salessapp.common.utils.StringUtils.format2MoneyStr(sumPlanFee));
					}else{
						planFeeVO.setPlanFee(p.getPlanFee()==null?"0.00":com.ccic.salessapp.common.utils.StringUtils.format2MoneyStr(p.getPlanFee().doubleValue()));
					}
					planFeeVO.setDesagentCode(DESEncryptUtil.encrypt(p.getAgentCode(),dagentCode)); //加密代理人code
					planFees.add(planFeeVO);
				}
				if(responseVO.getPlanFees()!=null){
					responseVO.getPlanFees().addAll(planFees);
				}else{
					responseVO.setPlanFees(planFees);
				}
			}
		}else{
			throw new Exception("手续费信息查询失败："+response.getResponseHead().getAppMessage());
		}

	}

	
	@Override
	public HttpResult saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result) {
		PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new PaymentNoApplyRequestDTO();
		ProposalInfoDTO proposalInfoDTO = new ProposalInfoDTO();
		proposalInfoDTO.setCertiNo(insureInfo.getInsureNo());//投保单号
		proposalInfoDTO.setComCode(insureInfo.getComCode());//机构代码
		proposalInfoDTO.setInsuranceCode(insureInfo.getInsuranceCode());//险种代码
		proposalInfoDTO.setEffectDate(datef.format(insureInfo.getStartDate()));//起保日期
		proposalInfoDTO.setAmount(Double.parseDouble(insureInfo.getSumPremium()));//金额
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));
		paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
		paymentNoApplyRequestDTO.setSystemCode("00");//申请系统
		paymentNoApplyRequestDTO.setPayType("1");//支付方式
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));//汇总金额
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("申请支付号成功");//响应信息
		RequestHeadDTO head=new RequestHeadDTO();
		PaymentNoApplyRequest request=new PaymentNoApplyRequest();
		head.setConsumerID("mobile");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(paymentNoApplyRequestDTO);
		//payment=(PaymentNoApply)this.getBean("paymentNoApply");
		PaymentNoApplyResponse response = null;
		try {
			System.out.println(JSONObject.toJSONString(request));
			response = paymentNoApply.paymentNoApply(request);
			System.out.println(JSONObject.toJSONString(response)); 
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
			}else{
				insureInfo.setPayApplyNo(response.getResponseBody().getPayApplyNo());//获取支付号
				insureInfo.setIdentityCode(response.getResponseBody().getIdentityCode());//获取验证码
				insureInfo.setStatus(InsuranceApplicationStatus.DAIZHIFU);//待支付
			}
		} catch (Exception e) {
			log.error("调用申请支付号接口异常：", e);
			result.setCode("0");
			result.setMessage("调用申请支付号接口异常："+e.getMessage());	
		}
		updateInsureInfo(insureInfo, result);//调用保存下单方法更新记录
		HttpResult res = HttpResult.success("");
		res.setCode("1");
		res.setData(response.getResponseBody());
		res.setMsg("查询成功");
		return res;
		//return insureInfo;
	}
	

	public int updateInsureInfo(StoreInsureInfo insureInfo,
			ResultVO result) {
		int row =0;
		try {
			row = storeInsureInfoMapper.doUpdate(insureInfo);
		} catch (Exception e) {
			log.info("投保单信息更新失败！");
		}
		return (Integer) row;
	}

	@Override
	public HttpResult<?> queryCashWithdrawal(PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		log.info("planFeeInquiryRequest-json:"+JSONObject.toJSONString(planFeeInquiryRequestVO));
//		String userCode = planFeeInquiryRequestVO.getUserCode();
		//String handlerCode = planFeeInquiryRequestVO.getHandlerCode();
		String agentCode=planFeeInquiryRequestVO.getAgentCode();
		DecimalFormat df=new DecimalFormat("#0.00");
//		if (!"agen".equals(userCode.substring(0, 4))) {	//判断是否有特定的代理人Code
//			String desagentCode = DESEncryptUtil.decrypt(planFeeInquiryRequestVO.getDesagentCode(), dagentCode);//解密代理人code
//			agentCode = desagentCode;
//		}else{
//			AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByUserCode(userCode);
//			agentCode=agentHandlerInfo.getAgentCode();
//		}
		
		if(agentCode==null) {
			return HttpResult.error(0, "未查询到提现数据");
		}
		Map<String,String> p = new HashMap<String,String>();
		PlanFeeVO planFeeVO = new PlanFeeVO();
		p.clear();
		p.put("agentCode", agentCode);
		Double sumPlanFee = planFeePayMapper.sumPlanFee(p);
		planFeeVO.setPlanFee(sumPlanFee==null?"0.00":df.format(sumPlanFee));
		CashWithdrawalResponseVo cashWithdrawalResponseVo = new CashWithdrawalResponseVo();
		java.math.BigDecimal a = new java.math.BigDecimal(Double.parseDouble(planFeeVO.getPlanFee()));
		double planFee = a.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		cashWithdrawalResponseVo.setPlanFeeN(planFee);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("agentCode", agentCode);
		//paramMap.put("handlerCode", handlerCode);
		FeeQueryVO feeQueryVO = planFeePayMapper.queryBillFeeByUserCode(paramMap);
		if(feeQueryVO!=null) {
			java.math.BigDecimal b = new java.math.BigDecimal(feeQueryVO.getSumBillFee());
			double sumBillFee = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
			cashWithdrawalResponseVo.setPlanFeeY(sumBillFee);
		}
		return HttpResult.success(cashWithdrawalResponseVo, 1, "查询成功") ;
	}
	
	@Override
	public HttpResult<?> queryCashWithdrawalByAgentCode(PlanFeeInquiryRequestVO planFeeInquiryRequestVO) {
		log.info("通过代理人查询");
		log.info(JSONObject.toJSONString(planFeeInquiryRequestVO));
		String agentCode=planFeeInquiryRequestVO.getAgentCode();
		DecimalFormat df=new DecimalFormat("#0.00");
		if(agentCode==null) {
			return HttpResult.error(0, "未查询到提现数据");
		}
		Map<String,String> p = new HashMap<String,String>();
		PlanFeeVO planFeeVO = new PlanFeeVO();
		p.clear();
		p.put("agentCode", agentCode);
		Double sumPlanFee = planFeePayMapper.sumPlanFee(p);
		planFeeVO.setPlanFee(sumPlanFee==null?"0.00":df.format(sumPlanFee));
		CashWithdrawalResponseVo cashWithdrawalResponseVo = new CashWithdrawalResponseVo();
		java.math.BigDecimal a = new java.math.BigDecimal(Double.parseDouble(planFeeVO.getPlanFee()));
		double planFee = a.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		cashWithdrawalResponseVo.setPlanFeeN(planFee);
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("agentCode", agentCode);
		//paramMap.put("handlerCode", handlerCode);
		FeeQueryVO feeQueryVO = planFeePayMapper.queryBillFeeByUserCode(paramMap);
		if(feeQueryVO!=null) {
			java.math.BigDecimal b = new java.math.BigDecimal(feeQueryVO.getSumBillFee());
			double sumBillFee = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
			cashWithdrawalResponseVo.setPlanFeeY(sumBillFee);
		}
		return HttpResult.success(cashWithdrawalResponseVo, 1, "查询成功") ;
	}
	
	public FeePayDetailResponseVO monthFee(PlanFeeInquiryRequestVO planFeeInquiryRequestVO,String userCode,String month){
		String agentCode = "";
		if (!"agen".equals(userCode.substring(0, 4))) {	//判断是否有特定的代理人Code
			String desagentCode = DESEncryptUtil.decrypt(planFeeInquiryRequestVO.getDesagentCode(), dagentCode);//解密代理人code
			agentCode = desagentCode;
		}else{
			AgentHandlerInfo agentHandlerInfo =agentHandlerMappper.selectAgentHandlerInfoByUserCode(userCode);
			agentCode=agentHandlerInfo.getAgentCode();
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("agentCode", agentCode);
		if(StringUtils.isNotBlank(month)){
			String[] sp = month.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR,Integer.parseInt(sp[0]));
	        //设置月份
			calendar.set(Calendar.MONTH, Integer.parseInt(sp[1])-1);
	    	calendar.set(Calendar.DAY_OF_MONTH, 2);
	    	paramMap.put("startDay", datef.format(calendar.getTime()));	
	    	calendar.add(Calendar.MONTH, 1);
	    	calendar.set(Calendar.DAY_OF_MONTH, 1);
	    	paramMap.put("endDay", datef.format(calendar.getTime()));//下月的1号
		}
		if (!"agen".equals(userCode.substring(0, 4))) {	//判断来源是经办人登陆还是个人代理人登陆
			//经办人登陆个人代理人查询佣金
			paramMap.put("handler1Code", userCode);
		}
			//按月查询默认查5个批次
			PageVO page1=new PageVO();
			page1.setPage(1);
			page1.setSize(2);
			List<PackVO> list = null;
			try {
			//批次查询
			  list=queryPackNo(paramMap,page1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		FeePayDetailResponseVO responseVO=new FeePayDetailResponseVO();
		responseVO.setPacks(list);
		return responseVO;
	}
	
	public List<String> getMonthBetween(String minDate, String maxDate)  {
	    try {
			ArrayList<String> result = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();
			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
			Calendar curr = min;
			while (curr.before(max)) {
			 result.add(sdf.format(curr.getTime()));
			 curr.add(Calendar.MONTH, 1);
			}
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return null;
	  }
	
	public String getYear(Date time) throws Exception {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	    SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
	    String time1 = format3.format(time);
	    Date startTime = format.parse(time1 + "-01");
	    String dates = format.format(startTime);
	    return dates;
	}
	
	public String getYearMonth(Date time) throws Exception {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	    String dates = format.format(time);
	    return dates;
	}

	
}
