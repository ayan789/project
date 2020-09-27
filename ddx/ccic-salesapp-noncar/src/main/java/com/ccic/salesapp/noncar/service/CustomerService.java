package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.repository.basedb.po.AppCustomer;

public interface CustomerService {
    AppCustomer findCustomer(String custNo, String custType);
}
