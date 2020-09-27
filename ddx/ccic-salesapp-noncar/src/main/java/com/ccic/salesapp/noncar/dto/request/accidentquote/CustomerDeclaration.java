package com.ccic.salesapp.noncar.dto.request.accidentquote;

import lombok.Data;

@Data
public class CustomerDeclaration {

    Long declarationId;
    Long policyId;
    Long sequenceNumber;
	//告知形式
	String declarationForm;
	//健康告知结果
	String declarationResult;
	//特殊告知类型
	String declarationType;
	
}
