package com.ccic.salesapp.noncar.dto.response;

import java.util.List;

import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel("方案")
public class StoreQueryFormulaResponse {
	
	@ApiModelProperty("方案列表")
	private List<StoreFormulas> sfList;
}
