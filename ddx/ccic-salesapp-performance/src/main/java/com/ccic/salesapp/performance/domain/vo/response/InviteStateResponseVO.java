package com.ccic.salesapp.performance.domain.vo.response;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

public class InviteStateResponseVO extends DataVO {

	private String id;
	private String state;
	
	private String name;//姓名
	private String idNo;//身份证号
	private String mobile;//手机号码
	private String score;//考试成绩

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
