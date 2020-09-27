package com.ccic.salesapp.noncar.dto.request;

import lombok.Data;

@Data
public class ApplyPayVo {
	String insureNo;
	String isIssueAfterPay="0"; //0非见费 1见费
}
