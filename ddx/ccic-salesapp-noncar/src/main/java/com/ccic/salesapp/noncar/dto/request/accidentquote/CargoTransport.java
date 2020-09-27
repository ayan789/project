package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Map;

import lombok.Data;
@Data
public class CargoTransport {
	//适用产品
		private String flightProduct;
		//运输方式
		private String transportModeCode;
		//限额币种
		private String siCurrencyCode;
		//运输限额（每批次）
		private double transportLimit;
		//最大船龄
		private int maxShipAge;
		//最小总吨
		private double minSumTon;
		//加收老船费率
		private double oldShipPremiumRate;
		private Long sequenceNumber;// 序号
		private Map <String,String> tempData;// 临时数据
}
