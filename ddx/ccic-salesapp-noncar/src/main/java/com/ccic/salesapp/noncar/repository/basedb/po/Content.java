package com.ccic.salesapp.noncar.repository.basedb.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 内容表
 *
 * @date 2020-11-02
 */
@ApiModel("内容表")
public class Content {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 机构编码(值为all代表所有的分公司都可使用)
     */
    @ApiModelProperty("机构编码(值为all代表所有的分公司都可使用)")
    private String orgCode;

    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 图片(S3访问路径)
     */
    @ApiModelProperty("图片(S3访问路径)")
    private String image;

    /**
     * 内容类型(index:首页 publish:发布内容 banner:轮播 card:贺卡)
     */
    @ApiModelProperty("内容类型(index:首页 publish:发布内容 banner:轮播 card:贺卡)")
    private String type;

    /**
     * 显示位置(start:启动 find:发现)
     */
    @ApiModelProperty("显示位置(start:启动 find:发现)")
    private String location;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 是否可分享
     */
    @ApiModelProperty("是否可分享")
    private Integer shareable;

    /**
     * 状态(0:待提交 1:审核中 2:审核驳回 3:待发布 4:已发布 5:发布已取消 6:已删除)
     */
    @ApiModelProperty("状态(0:待提交 1:审核中 2:审核驳回 3:待发布 4:已发布 5:发布已取消 6:已删除)")
    private Integer status;

    /**
     * 1.是  0.否
     */
    @ApiModelProperty("1.是  0.否")
    private Integer isActive;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date publishTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 创建人工号
     */
    @ApiModelProperty("创建人工号")
    private String createCode;

    /**
     * 阅读数量
     */
    @ApiModelProperty("阅读数量")
    private Integer readCount;

    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createName;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 修改人工号
     */
    @ApiModelProperty("修改人工号")
    private String updateUser;

    /**
     * 链接
     */
    @ApiModelProperty("链接")
    private String url;

    /**
     * 产品代码
     */
    @ApiModelProperty("产品代码")
    private String productCode;

    /**
     * 产品名称
     */
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 发现类型
     */
    @ApiModelProperty("发现类型")
    private String findType;
    
    @ApiModelProperty("是否在门店中")
    private String isInStore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getShareable() {
        return shareable;
    }

    public void setShareable(Integer shareable) {
        this.shareable = shareable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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
        this.updateUser = updateUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFindType() {
        return findType;
    }

    public void setFindType(String findType) {
        this.findType = findType;
    }

	public String getIsInStore() {
		return isInStore;
	}

	public void setIsInStore(String isInStore) {
		this.isInStore = isInStore;
	}
}