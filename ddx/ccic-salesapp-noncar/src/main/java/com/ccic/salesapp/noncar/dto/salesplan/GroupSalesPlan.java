/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ApiModel("组合方案详细信息")
public class GroupSalesPlan {
	@ApiModelProperty("主键id")
	private int salesPlanId;
	@ApiModelProperty("销售方案代码")
    private String salesPlanCode;
	@ApiModelProperty("销售方案名称")
    private String salesPlanName;
	@ApiModelProperty("销售方案类型")
    private String salesType;
	@ApiModelProperty("组合方案代码")
    private String groupPlanCode;
	@ApiModelProperty("组合方案名称")
    private String groupPlanName;
	@ApiModelProperty("方案状态")
    private String status;
	@ApiModelProperty("生效日期")
    private String effectiveDate;
	@ApiModelProperty("截至日期")
    private String expiryDate;
	@ApiModelProperty("子方案信息")
    private List<PlanChild> salesChildPlans;
	@ApiModelProperty("是否默认")
	private String isDefault;
	@ApiModelProperty("机构")
	 private String orgCode;
	@ApiModelProperty("从人渠道")
	 private String channelByPerson;
	
    
    
    

}