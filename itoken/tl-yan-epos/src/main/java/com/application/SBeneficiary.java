package com.application;

import java.util.List;

public class SBeneficiary {
    public String insureId;
    //受益人类型 1：法定受益人 2：指定受益人 3：投保人
    public String beneficiaryType;

    public List<SCustomer> beneficiaryList;
}
