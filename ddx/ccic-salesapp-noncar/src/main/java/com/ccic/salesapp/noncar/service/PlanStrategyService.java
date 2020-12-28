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
	/**
	 * 门店用产品列表查询
	 * @param request
	 * @return
	 */
	HttpResult<ProductListResponse> storePlanStrategyList(ProductListRequest request);
	/**
	 * 获取从人渠道，若无从人渠道将按一下规则取默认值
	 * （1）对于归属经办人是销售人员的：
			a.   如果从人渠道为空，则根据归属经办人取基本法系统中从人渠道属性（含四级机构负责人、重客虚拟等所有从人渠道），记录从人渠道标志；
			b.   如果从人渠道仍为空，则判断归属经办人工号为“9”开头，即视为兼岗工号，从人渠道记录为非销渠道（渠道代码为：C0000023）。
			c.   经过上述a.b.；两步之后如果从人渠道仍为空，则系统不允许出单，提示：“正在从销售基本法系统同步该业务员归属渠道，请稍后出单”。
	         （2）对于归属经办人是非销人员的，从人渠道记录为非销渠道（渠道代码为：C0000023）。
	 * @param user
	 * @return
	 */
	String getChannel(UserToken user);

}
