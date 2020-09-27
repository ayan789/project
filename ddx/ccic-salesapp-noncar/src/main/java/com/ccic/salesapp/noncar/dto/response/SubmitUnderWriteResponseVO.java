package com.ccic.salesapp.noncar.dto.response;

import lombok.Data;

@Data
public class SubmitUnderWriteResponseVO {
	private String uwType;//结果类型 1-拒保，2-返回录单员修改，3-自动核保，4-人工核保，9-返回修改
}
