package com.ccic.salesapp.performance.domain.vo.dto;

import lombok.Data;

import com.ccic.salesapp.performance.domain.vo.request.PageVO;

@Data
public class QueryPolicyVo {
	private String userCode;
	private String agentCode;
	private PageVO page;
}
