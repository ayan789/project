package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PlanElementDetailDto {

	private Integer strategyId;
    private String productCode ="";
    private String productName ="";
   // private String riskCode ="";
	private String backReasoning ="";
	private String planReverseFactor ="";
	private String policyStartType ="";
	private String policyStartRange ="";
	private int isWatermark;
	private Long defaultPlanId=-1L;
	private  List<BackReasoningFactor> backReasoningFactor = new ArrayList<BackReasoningFactor>();
	
	private Header header = new Header();
    private List<Bottom> bottom = new ArrayList<Bottom>();
    private CommonProblem commonProblem = new CommonProblem();
    private List<FormulaList> formulaList = new ArrayList<FormulaList>();
   
}