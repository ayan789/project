package com.ccic.salesapp.noncar.dto.request.news;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SupportOrgBranchVO implements Serializable {

    private String orgCode;

    @ApiModelProperty(value = "upBranch", name = "上级机构是否是分公司(2:总公司,1:是 0:否)", example = "1")
    private String upBranch;


    private List<String> upBranchs;

    public static SupportOrgBranchVO withOrgCode(String orgCode){
        SupportOrgBranchVO supportOrgBranchVO = new SupportOrgBranchVO();
        supportOrgBranchVO.setOrgCode(orgCode);
        return supportOrgBranchVO;
    }
}
