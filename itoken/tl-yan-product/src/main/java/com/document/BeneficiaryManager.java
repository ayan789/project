package com.document;

import com.neo.MCustomer;

import java.util.List;

public interface BeneficiaryManager {
    List<MCustomer> getBeneficiaryCustomerList(String insureId);
}
