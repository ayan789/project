package com.ccic.salesapp.noncar.dto.request.accidentquote;

import lombok.Data;

@Data
public class VisaInfo {
	private String startVisaSerialNo;//	起始流水号
	private String endVisaSerialNo;//	终止流水号
	private String visaCode;//	单证版本号
}
