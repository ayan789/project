package com.ccic.salesapp.noncar.dto.request.news;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SupportOrgBranchResponse implements Serializable {

    private Integer id;

    private String orgCode;

    private String upOrgCode;

    @ApiModelProperty(value = "upBranch", name = "上级机构是否是分公司(2:总公司,1:是 0:否)", example = "1")
    private String upBranch;
}
