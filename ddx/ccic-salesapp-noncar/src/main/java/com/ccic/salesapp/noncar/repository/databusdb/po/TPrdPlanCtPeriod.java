package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.math.BigDecimal;
import lombok.Data;


@Data
public class TPrdPlanCtPeriod {
	
    private Long id;

    private Integer planCtPeriodId;

    private Integer planCoverageId;

    private String periodCategory;

    private Integer periodMax;

    private Integer periodMin;

    private BigDecimal periodPremiumRate;


}