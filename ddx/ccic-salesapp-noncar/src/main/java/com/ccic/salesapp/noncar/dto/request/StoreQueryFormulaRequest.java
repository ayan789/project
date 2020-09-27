package com.ccic.salesapp.noncar.dto.request;

import lombok.Data;

@Data
public class StoreQueryFormulaRequest {
	private String proCode; //方案代码
	private String storeCode; //门店代码
	private String userCode;//大地业务员工号、 第三方员工工号
}
