package com.ccic.salesapp.noncar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardVo;
import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.product.response.ProductListResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AgentSalesmanMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.BusinessCardMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalelawPersonMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.AgentSalesman;
import com.ccic.salesapp.noncar.repository.basedb.mapper.MarketingInterationMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalelawPersonMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UserMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.MarketingInteration;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salesapp.noncar.repository.basedb.po.SalelawPerson;
import com.ccic.salesapp.noncar.repository.basedb.po.User;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanStrategyServiceImpl implements PlanStrategyService {

	@Autowired
	PlanStrategyMapper planStrategyMapper;
	@Autowired
	StoreInfoMapper storeInfoMapper;
	@Autowired
	SalelawPersonMapper SalelawPersonMapper;
	
	@Autowired
	AgentSalesmanMapper agentSalesmanMapper;
	
	@Autowired
	StoreMapper StoreMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	MarketingInterationMapper marketingInterationMapper;
	
	@Autowired
    RedisTemplate<String, String> redisTemplate;
	
	@Value("${system.needPayComCode}")
	String needPayComCode;
	
	@Autowired
	BusinessCardMapper businessCardMapper;
	
	
	@Override
	public HttpResult<ProductListResponse> storePlanStrategyList(ProductListRequest request) {
		HttpResult result = HttpResult.error(0, "未知错误");
		UserToken user = getAccessTokenByUserCode(request.getUserCode());
		if(user != null) {
			if(user.getUserCode().startsWith("agen")) {
				UserToken handler = getAccessTokenByUserCode(user.getHandlerCode());
				user.setChannelCode(handler.getChannelCode());
			}
		}
		if(user != null) {
			request.setChannelCode(user.getChannelCode());
			request.setComCode(user.getComCode());
			request.setUserCode(user.getUserCode());
		}else {
			request.setChannelCode("no right !");
			request.setComCode("no right !");
			request.setUserCode("no right !");
		}
		request.setNeedPayComCode(needPayComCode);
		if("1".equals(request.getIsQueryByPage())) {
			PageHelper.startPage(request.getPage(), request.getSize(),true);
		}
		List<StoreProduct> list = planStrategyMapper.planStrategyList(request);
		ProductListResponse response = new ProductListResponse();
		if("1".equals(request.getIsQueryByPage())) {
			PageInfo pageInfo = new PageInfo(list);
			pageInfo.setList(null);
			response.setPageInfo(pageInfo);
		}
		response.setProductList(list);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("storeCode", request.getStoreCode());
		response.setTotalAccess(StoreMapper.countAccess(map));
		map.put("isToday", "1");
		response.setTodayAccess(StoreMapper.countAccess(map));
		BusinessCardReq businessCardReq = new BusinessCardReq();
		businessCardReq.setUserCode(request.getUserCode());
		BusinessCardVo businessCardVo = businessCardMapper.findBusinessCardByUserCode(businessCardReq);
		if(businessCardVo != null) {
			response.setWechat(businessCardVo.getWechatNumber());
		}
		
		if(StringUtils.isNotBlank(request.getOpenId())) {
			try {
				MarketingInteration marketingInteration = new MarketingInteration();
				marketingInteration.setBranchCode(user.getComCode());
				marketingInteration.setBrowseContent("门店");
				marketingInteration.setHandlerCode(user.getUserCode());
				marketingInteration.setIsContact("0");
				marketingInteration.setHandlerName(user.getUserName());
				marketingInteration.setMarkCode(request.getStoreCode());
				marketingInteration.setOpenid(request.getOpenId());
				marketingInteration.setShareType("3");
				marketingInterationMapper.insertSelective(marketingInteration);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		
		return result.success(response);
	}
	
	
	@Override
	public HttpResult<ProductListResponse> planStrategyList(ProductListRequest request) {
		HttpResult result = HttpResult.error(0, "未知错误");
		UserToken user = getAccessTokenByUserCode(request.getUserCode());
		//新华用户
		if("3".equals(user.getLoginFlag()) ) {
			request.setPlatformFlag("1");
			user = getAgentSalesman(request.getUserCode());
		}
		
		if(user != null) {
			request.setChannelCode(getChannel(user));
			request.setComCode(user.getComCode());
			request.setUserCode(user.getUserCode());
			if(StringUtils.isBlank(getChannel(user))) {
				log.warn("该账号无从人渠道，需要从基本法同步人员信息！");
				request.setChannelCode("no right");
			}
		}else {
			throw new PlatformBaseException("需要从基本法同步人员信息！", 0);
		}
		request.setNeedPayComCode(needPayComCode);
		PageHelper.startPage(request.getPage(), request.getSize(),true);
		List<StoreProduct> list = planStrategyMapper.planStrategyList(request);
		PageInfo pageInfo = new PageInfo(list);
		pageInfo.setList(null);
		ProductListResponse response = new ProductListResponse();
		response.setPageInfo(pageInfo);
		response.setProductList(list);
		return result.success(response);
	}

	
	@Override
	public List<Long> selectPlanIdsByStrategyIdUserToken(Long strategyId,UserToken user) {
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("strategyId", strategyId);
		if(user != null) {
			map.put("userCode",user.getUserCode());
			map.put("comCode",user.getComCode());
			map.put("channelCode",getChannel(user));
			if(StringUtils.isBlank(getChannel(user))) {
				throw new PlatformBaseException("该账号无从人渠道，需要从基本法同步人员信息！", 2);
			}
		}else {
			throw new PlatformBaseException("无该账号信息，需要从基本法同步人员信息！", 2);
		}
		return planStrategyMapper.selectPlanIdsByStrategyIdUserCode(map);
	}
	
	
	@Override
	public List<Long> selectPlanIdsByStrategyCodeUserToken(String strategyCode, UserToken user) {
		if(StringUtils.isBlank(strategyCode)) {
			throw new PlatformBaseException("产品销售代码不能为空", 0);
		}
		
		PlanStrategy planStrategy =  planStrategyMapper.selectByStrategyCode(strategyCode);
		if(planStrategy != null) {
			List<Long> planIds = selectPlanIdsByStrategyIdUserToken(Long.valueOf(planStrategy.getId()),user);
			if(planIds != null && !planIds.isEmpty()) {
				return planIds;
			}else {
				throw new PlatformBaseException("没有配置该产品的出单权限或者该产品已下架", 2);
			}
		}else {
			throw new PlatformBaseException("该产品已失效或已下架", 2);
		}
	}
	
	

	@Override
	public List<Long> selectPlanIdsByStrategyIdUserCode(Long strategyId,String userCode) {
		
		UserToken user = getAccessTokenByUserCode(userCode);
		if(user == null){
			throw new PlatformBaseException("无该账号信息，需要从基本法同步人员信息！", 2);
		}
		return selectPlanIdsByStrategyIdUserToken(strategyId,user);
	}
	
	@Override
	public List<Long> selectPlanIdsByStrategyCodeUserCode(String strategyCode, String userCode) {
		if(StringUtils.isBlank(strategyCode)) {
			throw new PlatformBaseException("产品销售代码不能为空", 0);
		}
		if(StringUtils.isBlank(userCode)) {
			throw new PlatformBaseException("业务员工号不能为空", 0);
		}
		PlanStrategy planStrategy =  planStrategyMapper.selectByStrategyCode(strategyCode);
		if(planStrategy != null) {
			List<Long> planIds = selectPlanIdsByStrategyIdUserCode(Long.valueOf(planStrategy.getId()),userCode);
			if(planIds != null && !planIds.isEmpty()) {
				return planIds;
			}else {
				throw new PlatformBaseException("没有配置该产品的出单权限或者该产品已下架", 2);
			}
		}else {
			throw new PlatformBaseException("该产品已失效或已下架", 2);
		}
	}
	
	@Override
	public UserToken getAccessTokenByUserCode(final String key) {
    	BoundValueOperations<String, String> ops = redisTemplate.boundValueOps("CCIC-SALESAPP-USER:"+CoreContants.USER_KEY+key);
    	String result=ops.get();
    	log.info("["+key+"]缓存中用户信息："+result);
    	Gson gson = new Gson();
    	UserToken user = new UserToken();
    	if(StringUtils.isBlank(result)) {
    		SalelawPerson  person = SalelawPersonMapper.selectByPrimaryKey(key);
    		log.info("["+key+"]缓存无"+key +"用户信息,查询基本法人员表数据：" +gson.toJson(person));
    		if(person == null) {//基本法表中无该用户信息，查询hr表中数据
    			User prpduser = userMapper.selectByPrimaryKey(key);
    			if(prpduser == null) {
    				log.info("["+key+"]基本法无"+key +"用户信息,查询HR表数据：" +gson.toJson(prpduser));
    				throw new PlatformBaseException("未查询到"+key+"信息", 0);
    			}else{
    				user.setComCode(prpduser.getComcode());
        			user.setUserCode(prpduser.getUsercode());
        			user.setIsSaleMan(prpduser.getSaleseqflag());
    				user.setChannelCode("C0000023");
    			}
    		}else {
    			user.setComCode(person.getComcode());
    			user.setUserCode(person.getUsercode());
    			user.setComName(person.getComcanme());
    			user.setChannelCode(person.getChannelcode());
    		}
    		log.info("["+key+"]"+gson.toJson(user));
    	}
    	return StringUtils.isNotBlank(result) ?gson.fromJson(result, UserToken.class):user;
    }
	
	@Override
	public UserToken getAgentSalesman(String agentCode) {
		AgentSalesman agentSalesman =  agentSalesmanMapper.selectDefaultSalesmanByAgentCode(agentCode);
		return getAccessTokenByUserCode(agentSalesman.getSalesmanCode());
	}
	
	@Override
	public String getChannel(UserToken user) {
		String channel = user.getChannelCode();
		if(StringUtils.isBlank(channel) && "1".equals(user.getIsSaleMan()) &&  user.getUserCode().startsWith("9")) {
			channel = "C0000023";
		}
		if(StringUtils.isBlank(channel) && "0".equals(user.getIsSaleMan())) {
			channel = "C0000023";
		}
		if(StringUtils.isBlank(user.getChannelCode())  && user.getUserCode().startsWith("agen")) {
			UserToken handler = getAccessTokenByUserCode(user.getHandlerCode());
			channel = getChannel(handler);
		}
		return channel;
	}
}
