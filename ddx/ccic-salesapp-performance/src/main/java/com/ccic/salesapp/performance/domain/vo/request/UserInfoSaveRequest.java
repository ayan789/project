package com.ccic.salesapp.performance.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息保存")
public class UserInfoSaveRequest {
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty("姓名")
    private String name;
}
