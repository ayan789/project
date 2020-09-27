package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.Date;

import lombok.Data;

@Data
public class InsuredDate {
	 
	 private Integer policyStartRange;
	 private String startDate;//保障开始日期
	 private String endDate;//保障截止日期
	 private Integer giCoveragePeriod;//保险期间
	 private Integer giPeriodType;//保险期间类型
}
