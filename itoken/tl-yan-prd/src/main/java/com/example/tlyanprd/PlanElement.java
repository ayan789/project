package com.example.tlyanprd;

import java.util.ArrayList;
import java.util.List;

import com.example.tlyanprd.planelement.LinkElementCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class PlanElement {
	private int id;
	private int groupId;
	private int sourceElementId;
	private String code ="";
	private String name ="";
	private int requireInput;
	private String defaultValue ="";
	private int arrayIndex;
	private String submitName="";
	
	private int sort;
	private int trialFlag;//试算要素(1:是 0:否)
	private int trialSort;//试算要素排序
	
	List<PlanElementOption> planElementOptionList = new ArrayList<PlanElementOption>();
	
	private int minLength;
	private int maxLength;
	private int minValue;
	private int maxValue;
	private String elementType ="";
	private String controlType ="";
	private String eventScript ="";
	private String validateType ="";
	private String placeholder ="";
	
	private List<LinkElementCode> linkElementCode = new ArrayList<LinkElementCode>();
	
	
	 
}
