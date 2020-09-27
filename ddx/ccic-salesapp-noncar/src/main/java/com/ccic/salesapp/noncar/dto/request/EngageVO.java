package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 特别约定
 * 从数据库中查询列表 返回给手机端数据
 * @author mengxp
 *
 */
public class EngageVO extends DataVO {

	private String clauseCode;// 约定代码 Y                             
	private String clauseName;// 约定名称  
	private String clauseDesc;// 条款内容 Y 约定长度为100VARCHAR2        
	private String clauseType;// 特约类别 Y 0：交强险特约，1：商业险特约	
    private String quatationType;//报价类型:1传统2网销 	
    private String modifyFlag;//是否可前台修改:1是 0否
    private String underwriteFlag;//修改后是否手工核保:1是 0否
    private String requirement;//使用条件
	
	
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
	
	public String getClauseDesc() {
		return clauseDesc;
	}
	public void setClauseDesc(String clauseDesc) {
		this.clauseDesc = clauseDesc;
	}
	
	public String getClauseType() {
		return clauseType;
	}
	public void setClauseType(String clauseType) {
		this.clauseType = clauseType;
	}
	public String getQuatationType() {
		return quatationType;
	}
	public void setQuatationType(String quatationType) {
		this.quatationType = quatationType;
	}
	public String getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public String getUnderwriteFlag() {
		return underwriteFlag;
	}
	public void setUnderwriteFlag(String underwriteFlag) {
		this.underwriteFlag = underwriteFlag;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
}
