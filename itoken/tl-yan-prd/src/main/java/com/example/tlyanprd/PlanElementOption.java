package com.example.tlyanprd;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class PlanElementOption {
	private int id;
	private int elementId;
	private int sourceOptionId;
	private String value;
	private String label;
	private int selected;
	private int sort;
	private List<PlanElementOption> children = new ArrayList<PlanElementOption>();
}
