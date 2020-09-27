package com.ccic.salesapp.noncar.dto.request.noncar;

import java.util.ArrayList;
import java.util.List;

import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.request.planelement.Tree;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class PlanElementOption<T> {
	private int id;
	private int elementId;
	private int sourceOptionId;
	private String value;
	private String label;
	private int selected;
	private int sort;
	List<Tree<T>> addressTree = new ArrayList<Tree<T>>();
	
}
