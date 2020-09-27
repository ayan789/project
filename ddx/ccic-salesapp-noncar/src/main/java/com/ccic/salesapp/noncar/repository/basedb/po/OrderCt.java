package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;

public class OrderCt {
    private Long id;

	private Long orderId;

	private String clauseCode;

	private String kindCode;

	private BigDecimal duePremium;

	private BigDecimal insured;

	private BigDecimal beforeVatPremium;

	private BigDecimal vat;

	private String insuredItemName;

	private String clauseName;

	private String coverageName;

	private Integer relationId;

	private Integer relationType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getClauseCode() {
		return clauseCode;
	}

	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode == null ? null : clauseCode.trim();
	}

	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode == null ? null : kindCode.trim();
	}

	public BigDecimal getDuePremium() {
		return duePremium;
	}

	public void setDuePremium(BigDecimal duePremium) {
		this.duePremium = duePremium;
	}

	public BigDecimal getInsured() {
		return insured;
	}

	public void setInsured(BigDecimal insured) {
		this.insured = insured;
	}

	public BigDecimal getBeforeVatPremium() {
		return beforeVatPremium;
	}

	public void setBeforeVatPremium(BigDecimal beforeVatPremium) {
		this.beforeVatPremium = beforeVatPremium;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public String getInsuredItemName() {
		return insuredItemName;
	}

	public void setInsuredItemName(String insuredItemName) {
		this.insuredItemName = insuredItemName == null ? null : insuredItemName.trim();
	}

	public String getClauseName() {
		return clauseName;
	}

	public void setClauseName(String clauseName) {
		this.clauseName = clauseName == null ? null : clauseName.trim();
	}

	public String getCoverageName() {
		return coverageName;
	}

	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName == null ? null : coverageName.trim();
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	
	private String productName;
	
	private String insureUnit;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getInsureUnit() {
		return insureUnit;
	}

	public void setInsureUnit(String insureUnit) {
		this.insureUnit = insureUnit;
	}

}