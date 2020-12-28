package com.ccic.salesapp.noncar.repository.basedb.po;

public class SalesPlanConfig {
    private Integer id;

    private String type;

    private Integer salesPlanId;

    private String groupPlanCode;

    private String code;
    
    private String groupCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSalesPlanId() {
        return salesPlanId;
    }

    public void setSalesPlanId(Integer salesPlanId) {
        this.salesPlanId = salesPlanId;
    }

    public String getGroupPlanCode() {
        return groupPlanCode;
    }

    public void setGroupPlanCode(String groupPlanCode) {
        this.groupPlanCode = groupPlanCode == null ? null : groupPlanCode.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
    
    
}