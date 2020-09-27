package com.ccic.salesapp.noncar.dto.request.accidentquote;

import lombok.Data;

@Data
public class TourGroup {
	// 序号
		private Long sequenceNumber;
		// 前往国家或地区中文或英文＊
		private String destinationCountryCode;
		// 前往国家或地区名称
		private String travelCountryName;
		// 旅团号
		private String tourGroupNo;
		// 旅游路线
		private String travelRoute;
		// 邮轮号
		private String cruiseNo;
}
