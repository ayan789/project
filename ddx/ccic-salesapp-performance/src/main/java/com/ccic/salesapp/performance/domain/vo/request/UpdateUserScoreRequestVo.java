package com.ccic.salesapp.performance.domain.vo.request;
import java.util.Map;

import com.ccic.salesapp.performance.domain.vo.dto.DataVO;

public class UpdateUserScoreRequestVo extends DataVO {
	private Map<String,String> personAnswers;
	private String id;//用户id，主键
	public Map<String, String> getPersonAnswers() {
		return personAnswers;
	}
	public void setPersonAnswers(Map<String, String> personAnswers) {
		this.personAnswers = personAnswers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
