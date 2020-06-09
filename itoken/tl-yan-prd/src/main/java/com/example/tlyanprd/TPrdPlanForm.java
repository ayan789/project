package com.example.tlyanprd;
import lombok.Data;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class TPrdPlanForm {
	
	@JsonProperty("Id")
    private Long id;

	@JsonProperty("PlanFormId")
    private String planformid;

	@JsonProperty("PlanDefId")
    private Long plandefid;

	@JsonProperty("CustomFormTitle")
    private String customformtitle;

	@JsonProperty("CustomFormContent")
    private String customformcontent;

	@JsonProperty("FormCode")
    private String formcode;

	@JsonProperty("FormCategory")
    private String formcategory;

	@JsonProperty("ProductElementId")
    private String productelementid;

	@JsonProperty("ProductElementCode")
    private String productelementcode;

	@JsonProperty("CustomFormDescription")
    private String customformdescription;

 
}