package com.ccic.salesapp.noncar.dto.response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;




@ApiModel("门店订单列表查询response")
@Data
public class StoreListQueryResponseVO extends DataVO{
	
	
	@ApiModelProperty("StoreInsureInf")
	private List<StoreInsureInfo> stList;
	
	@ApiModelProperty("总页数")
	private Integer pages;  
	
	@ApiModelProperty("总条数")
	private Integer rows;  
	
	
	
}
