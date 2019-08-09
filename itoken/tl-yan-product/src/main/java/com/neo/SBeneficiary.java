package com.neo;

import lombok.Data;

import java.util.List;

/**
 * Created by yangzhilei on 2017/10/19.
 * 受益人信息
 */
@Data
public class SBeneficiary {

    String insureId;
    //受益人类型 1：法定受益人 2：指定受益人 3：投保人
    String beneficiaryType;

    List<SCustomer> beneficiaryList;
}
