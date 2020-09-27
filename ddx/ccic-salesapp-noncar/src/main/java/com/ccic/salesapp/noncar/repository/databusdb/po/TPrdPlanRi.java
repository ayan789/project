package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class TPrdPlanRi {
	
	@JSONField(name="Id")
    private Long id;

	@JSONField(name="PlanRiId")
    private Long planRiId;

	@JSONField(name="PlanDefId")
    private Long planDefId;

	@JSONField(name="CtCode")
    private String ctCode;

	@JSONField(name="RiReinsurer")
    private String riReinsurer;

	@JSONField(name="TreatyId")
    private Long treatyId;

	@JSONField(name="TreatyCode")
    private String treatyCode;

	@JSONField(name="TreatyName")
    private String treatyName;

	@JSONField(name="CommisionName")
    private String commisionName;

	@JSONField(name="CommisionId")
    private Long commisionId;

	@JSONField(name="CommisionRate")
    private BigDecimal commisionRate;


}