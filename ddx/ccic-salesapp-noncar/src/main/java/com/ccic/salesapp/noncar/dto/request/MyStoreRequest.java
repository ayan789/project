package com.ccic.salesapp.noncar.dto.request;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MyStoreRequest {

	String storeCode;

	List<String> delArray;

	List<Map<String, String>> addArray;

	String isInStore;
	
	String isQueryByPage;
	
	String orderBy;
	
	String userCode;
}
