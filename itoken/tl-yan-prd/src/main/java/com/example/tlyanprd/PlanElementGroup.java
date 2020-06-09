package com.example.tlyanprd;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class PlanElementGroup {
	
	private int id;
	private int planId;
	private String planBatch; 
	private String bizType;
	private int bizId;
	private int sourceGroupId;
	private String category;
	private String code;
	private String name;
	private int sort;
	
	
	List<PlanElement> planElementList =  new ArrayList<PlanElement>();
	
}
