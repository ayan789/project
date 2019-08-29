package com.example.tlyanepos;

import com.application.SBeneficiary;
import com.application.SCustomer;
import com.application.SInformData;
import com.application.SPay;

public interface SInsureService {
    public SPay getSpay(Long userId);
    public SInformData getSInformData(Long userId);
    public SCustomer getScustomer(Long userId);
    public SBeneficiary getSBeneficiary(Long userId);

}
