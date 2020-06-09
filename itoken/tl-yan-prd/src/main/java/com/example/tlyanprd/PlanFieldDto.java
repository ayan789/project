package com.example.tlyanprd;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PlanFieldDto {
	private int bizId;//业务ID
	private String formulaCode ="";//方案CODE
	private int strategyId;
	private int planId;
	private String planBatch ="";
	private List<PlanElementGroup> planElementGroup = new ArrayList<PlanElementGroup>();

}
