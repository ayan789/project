package com.ccic.salesapp.noncar.dto.response;
import java.util.List;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("产品列表")
public class StoreResponse {
	
	
	@ApiModelProperty("产品列表")
	private List<StoreProduct> allList;
	
	@ApiModelProperty("总页数")
	private Integer pages;  
	
	@ApiModelProperty("总条数")
	private Integer rows;  
	
}
