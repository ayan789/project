

package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import lombok.Data;

@Data
public class SalesMainMsgDto implements java.io.Serializable {
    private SalesAgentMsgDto salesAgentMsgDto;

    private SalesAgreementMsgDto[] salesAgreementMsgDto;



}
