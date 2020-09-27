package com.ccic.salesapp.performance.domain.vo.response;

import java.util.List;

import com.ccic.salesapp.performance.domain.vo.dto.SalaryItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("薪酬查询")
public class SalaryResponse {
	@ApiModelProperty("总实发")
	String total;
	@ApiModelProperty("薪酬项目列表")
	List<SalaryItem>  salaryItems;
	
}
