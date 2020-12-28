package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.List;

import com.ccic.salesapp.noncar.dto.request.planelement.InsuredDate;

import lombok.Data;

@Data
public class PlanFieldDto {
	//private int bizId;//业务ID
	private String formulaCode ;//方案CODE
	private String invoiceFlag = "0";
	private int strategyId;
	private int planId;
	private int isDefault;
	private String scene;
	private String prdPlanCategory;
	//private String planBatch;
	private List<PlanElementGroup> planElementGroup;
	private InsuredDate insuredDate= new InsuredDate();
	private com.ccic.salesapp.noncar.dto.InvoiceInfo invoiceInfo;

}
