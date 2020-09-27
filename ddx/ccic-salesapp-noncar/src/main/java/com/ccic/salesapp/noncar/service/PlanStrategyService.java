package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.product.response.ProductListResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;

public interface PlanStrategyService {

	/**
	 * 产品列表查询
	 * @param request
	 * @return
	 */
	HttpResult<ProductListResponse> planStrategyList(ProductListRequest request);
	
	/***
	 * 通过销售方案id和用户代码查询可以销售的方案内容
	 * @param strategyId 销售方案id
	 * @param userCode
	 * @return
	 */
	List<Long> selectPlanIdsByStrategyIdUserCode(Long strategyId, String userCode);

	/***
	 * 通过销售方案code和用户代码查询可以销售的方案内容
	 * @param strategyCode 销售方案Code
	 * @param userCode 用户工号
	 * @return
	 */
	List<Long> selectPlanIdsByStrategyCodeUserCode(String strategyCode, String userCode);

	/**
	 * 通过第三方业务员工号查询经办人信息
	 * @param agentCode
	 * @return 经办人的UserToken
	 */
	UserToken getAgentSalesman(String agentCode);

	/**
	 * 通过业务员工号查询用户信息
	 * @param userCode
	 * @return UserToken
	 */
	UserToken getAccessTokenByUserCode(String userCode);

	/**
	 * 通过销售方案id和用户信息查询可以销售的方案内容
	 * @param strategyId
	 * @param user
	 * @return
	 */
	List<Long> selectPlanIdsByStrategyIdUserToken(Long strategyId, UserToken user);
	/**
	 * 通过销售方案code和用户信息查询可以销售的方案内容
	 * @param strategyCode
	 * @param user
	 * @return
	 */
	List<Long> selectPlanIdsByStrategyCodeUserToken(String strategyCode, UserToken user);

}
