package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class Insurance {
	private long id;
	private long pid;
	private String name;
	private String clauseName;
	private String clauseCode;//主险
	private String kindName;
	private String kindCode;//附加险
	private String desc;//保障说明
	private String additional;//附加说明
	private String insuranceType;
	private double amount;
	private String ifCaltotalSum;
	private int linkSeats;
	private String title ;
	private boolean isRequired;
	private String type;
	private String unit ;
	private String defaultValue ="0";
	private int fixedInsure;//1 定额定费 0 限额限费
	private double insureAmount;
	private List<ValueList> valueList;
	private List<Rates> rates = new ArrayList<Rates>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClauseName() {
		return clauseName;
	}
	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}
	public String getClauseCode() {
		return clauseCode;
	}
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getKindCode() {
		return kindCode;
	}
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAdditional() {
		return additional;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getIfCaltotalSum() {
		return ifCaltotalSum;
	}
	public void setIfCaltotalSum(String ifCaltotalSum) {
		this.ifCaltotalSum = ifCaltotalSum;
	}
	public int getLinkSeats() {
		return linkSeats;
	}
	public void setLinkSeats(int linkSeats) {
		this.linkSeats = linkSeats;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public int getFixedInsure() {
		return fixedInsure;
	}
	public void setFixedInsure(int fixedInsure) {
		this.fixedInsure = fixedInsure;
	}
	public double getInsureAmount() {
		return insureAmount;
	}
	public void setInsureAmount(double insureAmount) {
		this.insureAmount = insureAmount;
	}
	public List<ValueList> getValueList() {
		return valueList;
	}
	public void setValueList(List<ValueList> valueList) {
		this.valueList = valueList;
	}
	public List<Rates> getRates() {
		return rates;
	}
	public void setRates(List<Rates> rates) {
		this.rates = rates;
	}
	
}
