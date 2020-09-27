package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 掌中宝-投保关联信息
 */


@Data
public class ZzbInsureInfo  {
	
	
	@ApiModelProperty("投保信息_掌中宝关联表id")
	private long id ;
	
	@ApiModelProperty("投保信息表id")
    private long insureInfoId;
	
	@ApiModelProperty("店面名称(非必填)")
	private String storeName;
	
	@ApiModelProperty("店铺证件类型:(默认三证合一)(必填)")
	private String storeIdType; 
	
	@ApiModelProperty("店铺证件号码:(必填)")
	private String storeId;	
	
	@ApiModelProperty("雇员人数(必填)")
	private int employeeNum;

	@ApiModelProperty("	建筑面积： 平方米(必填)")
	private double storeArea;
	
	@ApiModelProperty("自有资产：  万元(必填)")
	private BigDecimal ownAssets;
	
	@ApiModelProperty("房屋价值（含装修）万元(必填)")
	private BigDecimal houseValue;
	
	@ApiModelProperty("店内存货价值： (请输入整数)元(必填)")
	private BigDecimal stockValue;
	
	@ApiModelProperty("店内机器设备价值：(请输入整数)元(必填)")
	private BigDecimal equipmentValue;
	
	@ApiModelProperty("店内现金数额：(请输入整数)元(必填)")
	private BigDecimal storeCash;
	
	@ApiModelProperty("联系人姓名(必填)")
	private String contactsName;
	
	@ApiModelProperty("联系人电话(必填)")
	private String contactsMobile;
	
	@ApiModelProperty("联系人地址(必填)")
	private String contactsAddress;
	
	@ApiModelProperty("房间数(旅馆酒店)")
	private int roomNum;
	
	@ApiModelProperty("关系人人性质  3-自然人，4-法人")
	private String insuredNature;
	
	
}