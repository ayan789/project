package com.ccic.salesapp.noncar.dto.request.planelement;

import lombok.Data;

@Data
public class BizVo {
	
	int id;//元数据归档
	int strategyId;//产品ID
	int customElement;//是否自定义元素
	String elementValue;//元素值
	int arrayIndex;//元素下标
	
}
