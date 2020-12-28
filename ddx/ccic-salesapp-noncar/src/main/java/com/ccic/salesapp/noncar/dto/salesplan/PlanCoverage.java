/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel("方案责任信息")
@Data
public class PlanCoverage {
	@ApiModelProperty("主键id")
	private int coverageId;
	@ApiModelProperty("责任代码")
    private String coverageCode;
	@ApiModelProperty("责任名称")
    private String coverageName;
	@ApiModelProperty("条款代码")
    private String clauseCode;
	@ApiModelProperty("条款名称")
    private String clauseName;
	@ApiModelProperty("子方案代码")
    private String childPlanId;
	@ApiModelProperty("保额")
    private String sumInsured;
	@ApiModelProperty("折前保额")
    private String baseSumInsured;
	@ApiModelProperty("标的类型")
    private String insuredType;
	@ApiModelProperty("标的名称")
    private String insuredName;
	@ApiModelProperty("责任类型")
    private String mainCt;
	@ApiModelProperty("险种")
    private String riskType;
	@ApiModelProperty("险类")
    private String insuranceType;
	private String ctGroupType;
	
	private String sumInsuredFields;
	
	
    

}