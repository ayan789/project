package com.ccic.salesapp.noncar.dto.submitMiddleGround;

import com.ccic.salesapp.noncar.dto.DataVO;

import lombok.Data;
@Data
public class UploadSignBookSubmissionVo extends DataVO{
	private String signBytes;	//签名图片码
	private String insuranceNo;	//投保单号
	private String verificationCode;	//短信验证码
	private String submissionNo;
	
}
