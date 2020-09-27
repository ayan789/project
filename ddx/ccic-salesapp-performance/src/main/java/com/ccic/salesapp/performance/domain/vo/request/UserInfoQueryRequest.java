package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息查询")
public class UserInfoQueryRequest {
    @ApiModelProperty("页码")
    private int pageNo;
    @ApiModelProperty("每页大小")
    private int pageSize;
}
