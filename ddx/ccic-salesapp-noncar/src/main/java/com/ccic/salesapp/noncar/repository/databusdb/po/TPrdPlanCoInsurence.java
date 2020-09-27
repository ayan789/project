package com.ccic.salesapp.noncar.repository.databusdb.po;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TPrdPlanCoInsurence {
	
	@JsonProperty("Id")
    private Long id;

	@JsonProperty("PlanCoinsuranceId")
    private String planCoinsuranceId;

	@JsonProperty("PlanDefId")
    private String planDefId;

	@JsonProperty("PlanCoType")
    private String planCoType;

	@JsonProperty("CoinsuanceType")
    private String coinsuanceType;

	@JsonProperty("InternalCoinsuanceType")
    private String internalCoinsuanceType;

	@JsonProperty("LeaderTotalPremium")
    private String leaderTotalPremium;

	@JsonProperty("LeaderTotalSi")
    private String leaderTotalSi;

}