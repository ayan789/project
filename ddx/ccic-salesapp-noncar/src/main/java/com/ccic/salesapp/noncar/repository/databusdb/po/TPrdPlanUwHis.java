package com.ccic.salesapp.noncar.repository.databusdb.po;

import java.util.Date;

public class TPrdPlanUwHis {
    private Long id;

    private Integer planUwHisId;

    private Integer planChgId;

    private Integer planDefId;

    private String uwStatus;

    private String uwLevel;

    private String uwOperator;

    private Date uwTime;

    private Integer uwOrg;

    private String uwDecision;

    private String uwDesc;

    private String uwCurLevel;

    private String uwMaxLevel;

    private String uwPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlanUwHisId() {
        return planUwHisId;
    }

    public void setPlanUwHisId(Integer planUwHisId) {
        this.planUwHisId = planUwHisId;
    }

    public Integer getPlanChgId() {
        return planChgId;
    }

    public void setPlanChgId(Integer planChgId) {
        this.planChgId = planChgId;
    }

    public Integer getPlanDefId() {
        return planDefId;
    }

    public void setPlanDefId(Integer planDefId) {
        this.planDefId = planDefId;
    }

    public String getUwStatus() {
        return uwStatus;
    }

    public void setUwStatus(String uwStatus) {
        this.uwStatus = uwStatus == null ? null : uwStatus.trim();
    }

    public String getUwLevel() {
        return uwLevel;
    }

    public void setUwLevel(String uwLevel) {
        this.uwLevel = uwLevel == null ? null : uwLevel.trim();
    }

    public String getUwOperator() {
        return uwOperator;
    }

    public void setUwOperator(String uwOperator) {
        this.uwOperator = uwOperator == null ? null : uwOperator.trim();
    }

    public Date getUwTime() {
        return uwTime;
    }

    public void setUwTime(Date uwTime) {
        this.uwTime = uwTime;
    }

    public Integer getUwOrg() {
        return uwOrg;
    }

    public void setUwOrg(Integer uwOrg) {
        this.uwOrg = uwOrg;
    }

    public String getUwDecision() {
        return uwDecision;
    }

    public void setUwDecision(String uwDecision) {
        this.uwDecision = uwDecision == null ? null : uwDecision.trim();
    }

    public String getUwDesc() {
        return uwDesc;
    }

    public void setUwDesc(String uwDesc) {
        this.uwDesc = uwDesc == null ? null : uwDesc.trim();
    }

    public String getUwCurLevel() {
        return uwCurLevel;
    }

    public void setUwCurLevel(String uwCurLevel) {
        this.uwCurLevel = uwCurLevel == null ? null : uwCurLevel.trim();
    }

    public String getUwMaxLevel() {
        return uwMaxLevel;
    }

    public void setUwMaxLevel(String uwMaxLevel) {
        this.uwMaxLevel = uwMaxLevel == null ? null : uwMaxLevel.trim();
    }

    public String getUwPosition() {
        return uwPosition;
    }

    public void setUwPosition(String uwPosition) {
        this.uwPosition = uwPosition == null ? null : uwPosition.trim();
    }
}