package com.ccic.salesapp.noncar.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
@ApiModel("产品素材信息")
public class StoreProductMedia {
	
	@ApiModelProperty("素材类型")
	private String materialType;
	
	@ApiModelProperty("素材路径")
	private String materialPath;
	
}
