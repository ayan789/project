package com.ccic.salesapp.noncar.repository.databusdb.po;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


@Data
public class TPrdPlanForm {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="PlanFormId")
    private String planformid;

	@JSONField(name="PlanDefId")
    private Long plandefid;

	@JSONField(name="CustomFormTitle")
    private String customformtitle;

	@JSONField(name="CustomFormContent")
    private String customformcontent;

	@JSONField(name="FormCode")
    private String formcode;

	@JSONField(name="FormCategory")
    private String formcategory;

	@JSONField(name="ProductElementId")
    private String productelementid;

	@JSONField(name="ProductElementCode")
    private String productelementcode;

	@JSONField(name="CustomFormDescription")
    private String customformdescription;

 
}