package com.ccic.salesapp.noncar.dto.order.group.sales;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelOpInfo {
		//渠道代码
		private String channelCode;
		//渠道机构代码
		private String channelComCode;
		//渠道机构名称
		private String channelComName;
		//渠道名称
		private String channelName;
		//渠道产品代码
		private String channelProductCode;
		//渠道交易流水号
		private String channelSeqNo;
		//渠道交易日期
		private String trxDate;
		//代理人资格证号
		private String agentQualificationNo;
		//职业证号
		private String occupationLicNo;
		//渠道操作员代码
		//private String operatorCode;
		//销售人员姓名
		private String salesmanName;
		//渠道交易代码
		private String trxCode;
		//销售人员职业证号
		private String salesOccupationLicNo;
		//销售机构地址
		private String salesComAddress;
		//联系电话
		private String contactTel;
		//展业证号
		private String exhibitionCertificateNo;
		//中介机构联系方式
		private String intermediaryContactMode;
		//中介机构地址
		private String intermediaryAddress;
		//地区代码
//			private String regionCode;
		
		@ApiModelProperty("第三方业务员工号")
		private String thirdPartyHandlerNo;// 第三方业务员工号
		@ApiModelProperty("第三方业务员姓名")
		private String thirdPartyHandlerName;// 第三方业务员姓名
}
