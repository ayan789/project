package com.ccic.salesapp.performance.domain.vo.request;

import lombok.Data;

@Data
public class IPerformanceRequest {
	String searchFlag;//1-日业绩，2-周业绩，3-月业绩，4-年业绩，5-高级查询
	String searchMonth;//统计月份(月业绩)
	String searchStartDate;//查询开始时间(高级查询)
	String searchEndDate;//查询结束时间(高级查询)
	
}
