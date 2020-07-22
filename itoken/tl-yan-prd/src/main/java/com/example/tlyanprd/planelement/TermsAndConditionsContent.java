package com.example.tlyanprd.planelement;

import java.util.ArrayList;
import java.util.List;

import com.example.tlyanprd.PrdConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class TermsAndConditionsContent {
	private Integer id;
	private String code;
	private String title;
	private String link;
	private String type;
	private List<TermsAndConditionsContent> chlidren = new ArrayList<TermsAndConditionsContent>();
}
