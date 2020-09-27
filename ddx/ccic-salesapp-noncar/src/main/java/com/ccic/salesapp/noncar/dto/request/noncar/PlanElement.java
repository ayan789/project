package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.ArrayList;
import java.util.List;

import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.request.planelement.LinkElementCode;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree;

import lombok.Data;

@Data
public class PlanElement {
	private int id;
	private int groupId;
	private int sourceElementId;
	private String code ="";
	private String groupCode ="";
	private String name ="";
	private int requireInput;
	private String defaultValue ="";
	private int arrayIndex;
	private String submitName="";
	
	private int sort;
	private int trialFlag;//试算要素(1:是 0:否)
	private int trialSort;//试算要素排序
	
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
	
	List<PlanElementOption> planElementOptionList = new ArrayList<PlanElementOption>();
	
	
	
	
	 
}
