package com.ccic.salesapp.noncar.dto.request;

import com.ccic.salesapp.noncar.dto.DataVO;
 
/*
 * 影像上传请求信息
 * */
public class ImageUploadRequestVO extends DataVO{
	private String proposalNo;//投保单号
	
	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	
}

