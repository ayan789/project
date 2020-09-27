package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;





/**
 * 关联信息
 * @author Administrator
 *
 */
public class RelaVO extends DataVO {

	private String relaBussinessNo;//关联单号
	private String relaFlag ;//关联标志Y		GL：保单关联  JS：联合销售
	public String getRelaBussinessNo() {
		return relaBussinessNo;
	}
	public void setRelaBussinessNo(String relaBussinessNo) {
		this.relaBussinessNo = relaBussinessNo;
	}
	public String getRelaFlag() {
		return relaFlag;
	}
	public void setRelaFlag(String relaFlag) {
		this.relaFlag = relaFlag;
	}
	
}
