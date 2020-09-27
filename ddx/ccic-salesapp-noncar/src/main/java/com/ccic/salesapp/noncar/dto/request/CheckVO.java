package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;

/**
 * 投保查询校验
 * @author Administrator
 *
 */
public class CheckVO extends DataVO {
	private String renewalFlag;//投保查询校验类型标志 Y  0:非转保业务,1:转保业务                      
	private String checkFlag;//校验平台类别标志Y 参见代码2.1.21；(投保查询校验时新增发送字段),不能为空
	private String querySequenceNo;//投保查询码 (投保查询校验时新增发送字段),不能为空                 
	private String checkCode;//校验码(投保查询校验时新增发送字段)                                     
	private String question;//问题描述                                                                
	private String answer;//问题答案(投保查询校验时新增发送字段),不能为空                             
	public String getRenewalFlag() {
		return renewalFlag;
	}
	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getQuerySequenceNo() {
		return querySequenceNo;
	}
	public void setQuerySequenceNo(String querySequenceNo) {
		this.querySequenceNo = querySequenceNo;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
