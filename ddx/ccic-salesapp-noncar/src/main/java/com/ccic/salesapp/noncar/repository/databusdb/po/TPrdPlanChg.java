package com.ccic.salesapp.noncar.repository.databusdb.po;
import java.util.Date;
import lombok.Data;

@Data
public class TPrdPlanChg {
	
    private Long id;

    private Integer planChgId;

    private Integer planDefId;

    private Date planExpiryDate;

    private String submitBy;

    private String effectFlag;

    private String planStatus;

    private String lastFlag;

 
}