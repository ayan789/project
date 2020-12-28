package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分支机构及上级机构关系表
 *
 * @date 2020-10-28
 */
@ApiModel("分支机构及上级机构关系表")
public class OrgBranch {
    /**
     * 
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 分支机构编码
     */
    @ApiModelProperty("分支机构编码")
    private String orgCode;

    /**
     * 上级机构编码
     */
    @ApiModelProperty("上级机构编码")
    private String upOrgCode;

    /**
     * 上级机构是否是分公司(2:总公司,1:是 0:否)
     */
    @ApiModelProperty("上级机构是否是分公司(2:总公司,1:是 0:否)")
    private String upBranch;

    /**
     * 机构层级深度
     */
    @ApiModelProperty("机构层级深度")
    private Integer deep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUpOrgCode() {
        return upOrgCode;
    }

    public void setUpOrgCode(String upOrgCode) {
        this.upOrgCode = upOrgCode;
    }

    public String getUpBranch() {
        return upBranch;
    }

    public void setUpBranch(String upBranch) {
        this.upBranch = upBranch;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }
}