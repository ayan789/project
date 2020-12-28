package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;

public class MarketingInteration {
    private Long id;

    private String handlerCode;

    private String handlerName;

    private String branchCode;

    private String markCode;

    private String openid;

    private Date browseTime;

    private String shareType;

    private String browseContent;

    private String isContact;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(String handlerCode) {
        this.handlerCode = handlerCode == null ? null : handlerCode.trim();
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName == null ? null : handlerName.trim();
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode == null ? null : branchCode.trim();
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode == null ? null : markCode.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public String getBrowseContent() {
        return browseContent;
    }

    public void setBrowseContent(String browseContent) {
        this.browseContent = browseContent == null ? null : browseContent.trim();
    }

    public String getIsContact() {
        return isContact;
    }

    public void setIsContact(String isContact) {
        this.isContact = isContact == null ? null : isContact.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}