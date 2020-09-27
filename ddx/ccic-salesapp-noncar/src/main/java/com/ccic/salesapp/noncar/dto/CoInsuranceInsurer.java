package com.ccic.salesapp.noncar.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class CoInsuranceInsurer {
	//保险人当事人ID(通过该字段判断主共保公司是否是大地)
		private int insurerPartyId;
		//是否主保险人
		private String isLeader;
		//关联保单号码
		private String refPolicyNo;
		//共保公司分支机构名称
		private String coInsurerBranchName;
		//比例
		private double shareRate;
		//临时数据
		private Map<String,String> tempData;
		
		//开票信息
		List<com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo> invoiceInfoList;
}
