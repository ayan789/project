package com.ccic.salesapp.performance.domain.vo.request;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel("销售行动力评估")
public class AssessRequest {
	
	@NotBlank(message="用户代码不能为空")
	@ApiModelProperty("用户代码")
	String userCode;
	
}
