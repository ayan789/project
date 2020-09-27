package com.ccic.salesapp.noncar.repository.databusdb.po;
import com.alibaba.fastjson.annotation.JSONField;

public class TPrdPlanSharedCtGroup {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="PlanSharedId")
    private Long planSharedId;

	@JSONField(name="PlanDefId")
    private Long planDefId;

	@JSONField(name="GroupType")
    private String groupType;

	@JSONField(name="GroupDescription")
    private String groupDescription;

	@JSONField(name="MainPer")
    private String mainPer;

	@JSONField(name="RiderPer")
    private String riderPer;

	@JSONField(name="DiscountPer")
    private String discountPer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanSharedId() {
        return planSharedId;
    }

    public void setPlanSharedId(Long planSharedId) {
        this.planSharedId = planSharedId;
    }

    public Long getPlanDefId() {
        return planDefId;
    }

    public void setPlanDefId(Long planDefId) {
        this.planDefId = planDefId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }

    public String getMainPer() {
        return mainPer;
    }

    public void setMainPer(String mainPer) {
        this.mainPer = mainPer == null ? null : mainPer.trim();
    }

    public String getRiderPer() {
        return riderPer;
    }

    public void setRiderPer(String riderPer) {
        this.riderPer = riderPer == null ? null : riderPer.trim();
    }

    public String getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(String discountPer) {
        this.discountPer = discountPer == null ? null : discountPer.trim();
    }
}