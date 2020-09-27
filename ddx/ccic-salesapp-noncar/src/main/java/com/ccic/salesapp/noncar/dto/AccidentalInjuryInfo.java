package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
public class AccidentalInjuryInfo {
	
	@ApiModelProperty("id")
	private long id;
	
	@ApiModelProperty("")
	private long insureInfoId;
	
	@ApiModelProperty("")
	private String familyName;
	
	@ApiModelProperty("")
	private String familyIdentifyType;
	
	@ApiModelProperty("")
	private String familyIdentifyNo;
	
	@ApiModelProperty("")
	private String familyRelation;
	
	@ApiModelProperty("手机号码")
	private String mobile;
	
	@ApiModelProperty("社保类型")
	private String insuredSheBaoType;
	
	@ApiModelProperty("被保险人出生日期")
	private String insuredBirthDate;
	
	@ApiModelProperty("被保险人年龄")
	private String age;
	
	@ApiModelProperty("被保险人邮箱")
	private String insuredEmail;
}
