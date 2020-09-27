package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("排名")
public class RankVo {
	@ApiModelProperty("业务员工号")
	private String userCode;
	@ApiModelProperty("团队号")
	private String teamCode;
	@ApiModelProperty("月：M 季度：Q 年：Y")
	private String timeFlag;
	@ApiModelProperty("个人：P  团队：T")
	private String rankFlag;
	@ApiModelProperty("团队：T 分公司：F 总公司：Z")
	private String teamComFlag;
	@ApiModelProperty(hidden = true)
	private String owner;
	@ApiModelProperty(hidden = true)
	private String realpayDate1;
	@ApiModelProperty(hidden = true)
	private String realpayDate2;
	@ApiModelProperty(hidden = true)
	private String isNowFormatStr;
	@ApiModelProperty(hidden = true)
	private String isYestFormatStr;
	
	

	private String channelCode;
	
}
