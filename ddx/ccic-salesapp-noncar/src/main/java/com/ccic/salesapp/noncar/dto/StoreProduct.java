package com.ccic.salesapp.noncar.dto;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("产品信息")
public class StoreProduct {
	
	@ApiModelProperty("产品id")
	private Long  id;    
	
	@ApiModelProperty("产品代码")
	private String productCode; 
	
	@ApiModelProperty("产品名称")
	private String productName;
	
	@ApiModelProperty("产品简介")
	private String memo;
	
	@ApiModelProperty("销售方案id")
	private Long strategyId;
	
	@ApiModelProperty("产品价格")
	private String productDesc; 
	
	@ApiModelProperty("产品id")
	private String productExplain;
	
	@ApiModelProperty("产品类型")
	private String productType;  
	
	@ApiModelProperty("产品购买地址")
	private String productBuyUrl;
	
	@ApiModelProperty("产品icon地址")
	private String productImgUrl; 
	
	@ApiModelProperty("创建时间")
	private String createDate; 
	
	@ApiModelProperty("产品状态")
	private String state;	
	
	@ApiModelProperty("销售机构")
	private String comCode; 
	
	@ApiModelProperty("新核心标识 1新核心 0老核心")
	private String xinCore;
	
	@ApiModelProperty("新核心标识 1新核心 0老核心")
	private String newCore;
	
	@ApiModelProperty("产品分类：1意健险   2非车险")
	private String productRisk;
	
	@ApiModelProperty("险种类别：0意健险   1非车险")
	private String insuranceCategory;
	
	@ApiModelProperty("业务员工号")
	private String userCode;
	
	@ApiModelProperty("产品描述")
	private String desc;
	
	@ApiModelProperty("销量")
	private String saleSum;
	
	@ApiModelProperty("见费出单标志 ：1:见费出单；0：非见费出单")
	private String isIssueAfterPay;
	
	@ApiModelProperty("投保年龄")
	private String applicantAge;
	
	@ApiModelProperty("宣传简介")
	private String publicityInfo;
	
	@ApiModelProperty("是否有医保  1-有  0-无")
	private String isHealinsurance;
	
	@ApiModelProperty("产品素材信息")
	List<StoreProductMedia> mediaLst;
	
	@ApiModelProperty("首页配置产品详情页面路径")
	private String path;
	
	@ApiModelProperty("适用人群status")
	private String forPeopleStatus;
	
	@ApiModelProperty("是否产品工厂配置的动态表单产品 1：是  0： 否")
	private String productSource;
	
	@ApiModelProperty("是否在门店中显示：1：显示；0：不显示")
	private String isInStore;
	
	@ApiModelProperty("续保产品查询用字段：被保人个数")
	private Integer insuredPersonNum;
}
