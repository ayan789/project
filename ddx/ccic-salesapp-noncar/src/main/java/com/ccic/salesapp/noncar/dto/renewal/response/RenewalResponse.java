package com.ccic.salesapp.noncar.dto.renewal.response;

import java.util.List;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RenewalResponse {

	@ApiModelProperty("信息")
	String msg;
	@ApiModelProperty("是否可续保")
	String renewalStatus;
	@ApiModelProperty("是否已续保")
	String isRenewal;
	
	@ApiModelProperty("是否理赔 1理赔  0无理赔")
	String hasClaim;

	@ApiModelProperty("可续保产品")
	List<StoreProduct> productList;
	
	@ApiModelProperty("被保人列表")
	List<PersonInsured> personInsuredList;
	
}
