package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;

public class PlanStrategy {
    private Integer id;

    private Integer strategyType;

    private Integer isMain;

    private String code;

    private String name;

    private Integer hasElement;

    private String scene;

    private String crowd;

    private String org;

    private String memo;

    private String publicityInfo;

    private Integer planReverse;

    private String planReverseFactor;

    private Integer videoType;

    private String videoUrl;

    private Integer shareAble;

    private Integer showPoster;

    private Integer showProposal;

    private Integer needSign;

    private Integer uploadPhoto;

    private String insureType;

    private Integer policyStartType;

    private Integer policyStartRange;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getHasElement() {
        return hasElement;
    }

    public void setHasElement(Integer hasElement) {
        this.hasElement = hasElement;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd == null ? null : crowd.trim();
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org == null ? null : org.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getPublicityInfo() {
        return publicityInfo;
    }

    public void setPublicityInfo(String publicityInfo) {
        this.publicityInfo = publicityInfo == null ? null : publicityInfo.trim();
    }

    public Integer getPlanReverse() {
        return planReverse;
    }

    public void setPlanReverse(Integer planReverse) {
        this.planReverse = planReverse;
    }

    public String getPlanReverseFactor() {
        return planReverseFactor;
    }

    public void setPlanReverseFactor(String planReverseFactor) {
        this.planReverseFactor = planReverseFactor == null ? null : planReverseFactor.trim();
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getShareAble() {
        return shareAble;
    }

    public void setShareAble(Integer shareAble) {
        this.shareAble = shareAble;
    }

    public Integer getShowPoster() {
        return showPoster;
    }

    public void setShowPoster(Integer showPoster) {
        this.showPoster = showPoster;
    }

    public Integer getShowProposal() {
        return showProposal;
    }

    public void setShowProposal(Integer showProposal) {
        this.showProposal = showProposal;
    }

    public Integer getNeedSign() {
        return needSign;
    }

    public void setNeedSign(Integer needSign) {
        this.needSign = needSign;
    }

    public Integer getUploadPhoto() {
        return uploadPhoto;
    }

    public void setUploadPhoto(Integer uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType == null ? null : insureType.trim();
    }

    public Integer getPolicyStartType() {
        return policyStartType;
    }

    public void setPolicyStartType(Integer policyStartType) {
        this.policyStartType = policyStartType;
    }

    public Integer getPolicyStartRange() {
        return policyStartRange;
    }

    public void setPolicyStartRange(Integer policyStartRange) {
        this.policyStartRange = policyStartRange;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}