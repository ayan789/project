package com.ccic.salesapp.noncar.dto.upload.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("文件上传响应信息")
@Data
public class UploadResponse {
	
	@ApiModelProperty("有效行数")
	private int rows;  
	@ApiModelProperty("雇员清单id")
	private long personListId;
	
}
