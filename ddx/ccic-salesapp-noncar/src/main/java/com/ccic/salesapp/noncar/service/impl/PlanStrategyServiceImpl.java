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
import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.product.response.ProductListResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AgentSalesmanMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanStrategyMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalelawPersonMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.AgentSalesman;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
import com.ccic.salesapp.noncar.repository.basedb.po.SalelawPerson;
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
    RedisTemplate<String, String> redisTemplate;
	
	@Value("${system.needPayComCode}")
	String needPayComCode;
	
	
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
    	log.info(result);
    	Gson gson = new Gson();
    	UserToken user = new UserToken();
    	if(StringUtils.isBlank(result)) {
    		SalelawPerson  person = SalelawPersonMapper.selectByPrimaryKey(key);
    		log.info(gson.toJson(person));
    		if(person == null) {
    			user.setChannelCode("C0000023");
    		}
    		user.setComCode(person.getComcode());
    		user.setUserCode(person.getUsercode());
    		user.setComName(person.getComcanme());
    		user.setChannelCode(person.getChannelcode());
    		log.info(gson.toJson(user));
    		return user;
    	}
    	return StringUtils.isNotBlank(result) ?gson.fromJson(result, UserToken.class):user;
    }
	
	@Override
	public UserToken getAgentSalesman(String agentCode) {
		AgentSalesman agentSalesman =  agentSalesmanMapper.selectDefaultSalesmanByAgentCode(agentCode);
		return getAccessTokenByUserCode(agentSalesman.getSalesmanCode());
	}
	
	private String getChannel(UserToken user) {
		String channel = user.getChannelCode();
		if(StringUtils.isBlank(channel) && "1".equals(user.getIsSaleMan()) &&  user.getUserCode().startsWith("9")) {
			channel = "C0000023";
		}
		if(StringUtils.isBlank(channel) && "0".equals(user.getIsSaleMan())) {
			channel = "C0000023";
		}
		if(StringUtils.isBlank(user.getChannelCode())  && user.getUserCode().startsWith("agen")) {
			UserToken handler = getAccessTokenByUserCode(user.getHandlerCode());
			channel = handler.getChannelCode();
		}
		return channel;
	}
}
