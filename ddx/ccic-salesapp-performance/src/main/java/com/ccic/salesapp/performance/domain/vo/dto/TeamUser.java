package com.ccic.salesapp.performance.domain.vo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("团队成员")
public class TeamUser {
	public String userCode;
	public String userName;
	public String realPay = "0";
}
