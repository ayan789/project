package com.ccic.salesapp.noncar.dto.order.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("提交元素")
public class Element {
	
	@ApiModelProperty("元素id")
	private Long id;
	
	@ApiModelProperty("批次id")
	private String planId;
	
	@ApiModelProperty("分组id")
	private Long groupId;
	
	@ApiModelProperty("分组Code")
	private String groupCode;
	
	@ApiModelProperty("元素名称")
	private String name;
	
	@ApiModelProperty("元素数据类型")
	private String dataType;
	
	@ApiModelProperty("提交节点名称")
	private String submitName;
	
	@ApiModelProperty("元素值")
	private String value;
	
	@ApiModelProperty("元素显示值")
	private String displayValue;
	
	@ApiModelProperty("如果多个相同元素，提供索引")
	private Integer arrayIndex = 0;

}
