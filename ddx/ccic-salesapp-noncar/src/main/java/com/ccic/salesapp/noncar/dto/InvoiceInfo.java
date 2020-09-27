package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;



@Data
public class InvoiceInfo {
	
	@ApiModelProperty("ID")
	private long id;
	
	@ApiModelProperty("订单号")
	private long orderId;

	@ApiModelProperty("")
	private long insureinfoId;

	@ApiModelProperty("创建日期")
	private Date createDate;
	
	@ApiModelProperty("客户类型")
	private String clientType;
	
	@ApiModelProperty("纳税人类型")
	private String taxPayerType;
	
	@ApiModelProperty("发票类型")
	private String invoiceType;
	
	@ApiModelProperty("发票抬头(公司名称或个人姓名)")
	private String companyName;
	
	@ApiModelProperty("移动电话")
	private String taxMobile;
	
	@ApiModelProperty("电子邮箱")
	private String taxEmail;
	
	@ApiModelProperty("纳税人识别号")
	private String taxPayerNo;
	
	@ApiModelProperty("税务登记地址")
	private String taxRegistryAddress;

	@ApiModelProperty("税务登记电话")
	private String taxRegistryPhone;
	
	@ApiModelProperty("开户银行名称")
	private String bankName;
	
	@ApiModelProperty("开户银行账号")
	private String accountNumber;
	
	private String copyDataFromType;
}
