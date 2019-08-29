package com.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SCommodity {
    String commodityId;
    String parentCommodityId;
    String engineProductId;
    String productCode;
    String companyId;
    String planId;
    String amount;
    String premium;
    String quantity;
    String additional;//附加信息，json字符串
}
