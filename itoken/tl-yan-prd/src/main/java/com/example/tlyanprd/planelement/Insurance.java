package com.example.tlyanprd.planelement;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Insurance {
	private int id;
	private String clauseCode;
	private String kindCode;
	private String desc;
	private String insuranceType;
	private double amount;
	private String title ;
	private boolean isRequired;
	private String type;
	private String unit ;
	private String defaultValue ;
	private int fixedInsure;
	private double insureAmount;
	private List<ValueList> valueList;
	private List<Rates> rates = new ArrayList<Rates>();
    
    
}
