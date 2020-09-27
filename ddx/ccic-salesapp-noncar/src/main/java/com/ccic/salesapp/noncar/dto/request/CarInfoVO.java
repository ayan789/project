package com.ccic.salesapp.noncar.dto.request;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;



@Data
public class CarInfoVO {

	
	@ApiModelProperty("主键")
	private BigDecimal id;

	@ApiModelProperty("投保信息表ID")
	private BigDecimal insureinfoId;

	@ApiModelProperty("车牌号码")
	private String licenseplateno;

	@ApiModelProperty("车架号")
	private String vinno;

	@ApiModelProperty("车辆品牌")
	private String vehicleBrand;
	
	@ApiModelProperty("厂牌型号")
	private String brandModel;

	@ApiModelProperty("车主")
	private String carOwner; 	

	@ApiModelProperty("证件类型")
	private String identifyType;	

	@ApiModelProperty("证件号码")
	private String identifyNumber;	

	@ApiModelProperty("手机号码")
	private String phoneNumber;		

	@ApiModelProperty("原厂质保起期")
	private String enrollDate;		

	@ApiModelProperty("排量")
	private String exhaustScale;	

	@ApiModelProperty("车颜色")
	private String colorCode;		

	@ApiModelProperty("发动机号")
	private String engineNo;		

	@ApiModelProperty("车型")
	private String vehicleType;		

	@ApiModelProperty("吨位")
	private String tonnage;
	
	@ApiModelProperty("吨位")
	private String approvalQuality;

	@ApiModelProperty("出厂日期")
	private String productionDate;		

	@ApiModelProperty("生产厂家")
	private String manuFacturer;		

	@ApiModelProperty("购置价")
	private String purchasePrice;		

	@ApiModelProperty("购买日期")
	private String makedate;	
	
	@ApiModelProperty("车龄")
	private String vehicleAge;
	
	@ApiModelProperty("车型")
	String vehicleModelCode;//车型

	
}