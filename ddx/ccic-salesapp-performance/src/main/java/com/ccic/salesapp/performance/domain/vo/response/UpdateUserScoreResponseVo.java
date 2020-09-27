package com.ccic.salesapp.performance.domain.vo.response;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

public class UpdateUserScoreResponseVo extends DataVO {

	private String score;//分数

	private String name;//姓名
	private String idNo;//身份证号
	private String mobile;//手机号码

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

}
