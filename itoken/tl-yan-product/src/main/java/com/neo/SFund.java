package com.neo;

import lombok.Data;

/**
 * @author za-zhouzhiqiang
 * @create 2018/11/19
 **/
@Data
public class SFund {

    public String fundId;
    public String insureId;
    public String fundCode;
    public String fundPercent;
    public String fundAllocationType;

    public String higherRisk;
    public String exchangeRateRisk;
    public String fundAMC;


}
