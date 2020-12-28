package com.ccic.salesapp.noncar.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("投被保人信息")
@Data
public class Customer {

	@ApiModelProperty("年龄")
	Long age;
	
	@ApiModelProperty("年龄")
	Long indiAge;
	
	@ApiModelProperty("性别")
	String sex;
	
	@ApiModelProperty("性别")
	String indiGenderCode;
	
	@ApiModelProperty("社保标识")
	String socialSecurityFlag;
	
	@ApiModelProperty("客户姓名")
	String customerName;
	
	@ApiModelProperty("投被保人标识")
	String customerRoleCode;
	
	@ApiModelProperty("生日")
	String dateOfBirth;
	
	@ApiModelProperty("出生日期")
	String indiDateOfBirth;
	
	@ApiModelProperty("证件号码")
	String idNo;
	
	@ApiModelProperty("证件类型")
	String idType;
	
	@ApiModelProperty("投保人与被保人关系")
	String polHolderInsuredRelaCode;
	
	@ApiModelProperty("个人机构 个人01 机构02")
	String partyCategory;
	
	@ApiModelProperty("手机号")
	String indiMobile;
	
	@ApiModelProperty("社保类型")
	String medicalInsuranceCode;
	
	@ApiModelProperty("地址")
	String address;
	
	@ApiModelProperty("标的地址")
	String fullAddress;
	
	@ApiModelProperty("是否主被保人 Y/N")
	String isMainInsured;
	
}
