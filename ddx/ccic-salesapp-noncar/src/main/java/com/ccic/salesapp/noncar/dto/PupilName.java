package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PupilName {
	
	@ApiModelProperty("监护人ID")
	private long id;
	
	@ApiModelProperty("")
	private long insureinfoId;
	
	@ApiModelProperty("监护人姓名")
	private String pupilName;


}
