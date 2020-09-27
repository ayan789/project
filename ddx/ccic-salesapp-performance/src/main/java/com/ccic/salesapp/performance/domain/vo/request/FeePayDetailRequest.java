package com.ccic.salesapp.performance.domain.vo.request;



public class FeePayDetailRequest{

	private String month;//查询月份
	private String queryType;//查询类型0：按月查询 ，1：批次分页查询， 2：保单分页查询
	private String packNo;//批次号
	private String desagentCode;
	private String userCode;
	private String agentCode;
	private PageVO page;

	public String getDesagentCode() {
		return desagentCode;
	}
	public void setDesagentCode(String desagentCode) {
		this.desagentCode = desagentCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public PageVO getPage() {
		return page;
	}
	public void setPage(PageVO page) {
		this.page = page;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	

}
