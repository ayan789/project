package com.ccic.salesapp.performance.domain.vo.response;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

public class InviteDetailResponseVO extends DataVO {

	private String pcode;
	private String qualificationsCode;
	private String userName;
	private String examScore;
	private String examDate;
	private String examState;//考试状态
	private String state;//审核状态
	private String result;//审核结果
	private String updateDate;

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getQualificationsCode() {
		return qualificationsCode;
	}

	public void setQualificationsCode(String qualificationsCode) {
		this.qualificationsCode = qualificationsCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExamScore() {
		return examScore;
	}

	public void setExamScore(String examScore) {
		this.examScore = examScore;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getExamState() {
		return examState;
	}

	public void setExamState(String examState) {
		this.examState = examState;
	}
	

}