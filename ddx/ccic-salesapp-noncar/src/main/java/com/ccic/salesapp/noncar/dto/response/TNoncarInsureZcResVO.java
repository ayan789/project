package com.ccic.salesapp.noncar.dto.response;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("投保信息暂存")
public class TNoncarInsureZcResVO{

	@ApiModelProperty("主键")
	private Long id;

	@ApiModelProperty("投保单号")
	private String insureNo;

	@ApiModelProperty("投保信息暂存的json")
	private String jsonText;

	@ApiModelProperty("险种代码")
	private String insuranceCode;

	@ApiModelProperty("方案代码")
	private String formulaCode;

	@ApiModelProperty("记录创建时间")
	private String createTime;
	
	@ApiModelProperty("记录注销日期")
	private String destroyDate;
	
	@ApiModelProperty("订单金额")
	private String orderAmount;
	
	@ApiModelProperty("订单号")
	private String orderNo;
	
	@ApiModelProperty("订单状态(1-待支付；2-支付短信发送成功；3-支付短信发送失败；4-支付失败；5-支付成功)")
	private String orderStatus;
	
	@ApiModelProperty("订单交易时间")
	private String orderTime;

	@ApiModelProperty("实际支付金额")
	private String payAmount;
	
	@ApiModelProperty("支付成功时间")
	private String paySuccessDate;
	
	@ApiModelProperty("支付方式")
	private String payType;

	@ApiModelProperty("校验码")
	private String checkNo;
	
	@ApiModelProperty("最后修改时间")
	private String updateTime;
	
	@ApiModelProperty("投保信息实体bean")
	private StoreOracleRequestVO storeOracleRequestVO;
	
}
