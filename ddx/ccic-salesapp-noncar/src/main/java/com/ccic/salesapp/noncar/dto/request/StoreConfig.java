package com.ccic.salesapp.noncar.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class StoreConfig {
	private Long id;				//
	private String storeCode;		//门店代码
	private String productCode;		//产品代码
	private String configState;		//配置状态
	private Integer configSoft;		//配置顺序
	private Long storeId;
}
