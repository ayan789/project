package com.ccic.salesapp.noncar.dto.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel("产品列表查询")
public class StoreRequest {
	
	@ApiModelProperty("大地业务员工号 、新华用户工号")
	private String userCode;
	
	@ApiModelProperty("门店代码")
	private String storeCode;	
	
	@ApiModelProperty("产品类型")
	private String  productType;

	@ApiModelProperty("搜索关键词")
	private String keyWord;
	
	@ApiModelProperty("排序 0-产品价格(正) 1-产品价格(负) 2-上架时间(正) 3-上架时间(负) "
			+ "4-销量(正) 5-销量(负)")
	private String keyOrderBy;
	
	@ApiModelProperty("当前页")
	private Integer page;
	
	@ApiModelProperty("每页数量")
	private Integer size;
	
	@ApiModelProperty("适用人群status")
	private String forPeopleStatus;
	
	

}
