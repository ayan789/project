package com.neo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * Created by yangzhilei on 2017/10/18.
 */
@Slf4j
@Data
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

    SCommodityAdditional commodityAdditional;

    List<SCommodityParam> commodityParamList;

    public void setCommodityAdditional(SCommodityAdditional commodityAdditional) {
        this.commodityAdditional = commodityAdditional;
        if (commodityAdditional != null) {
            this.additional = JSONObject.toJSONString(commodityAdditional);
        }
    }

    public SCommodityAdditional getCommodityAdditional() {
        if (commodityAdditional != null) {
            return commodityAdditional;
        }
        if (StringTool.isNull(additional)) {
            return null;
        }
        try {
            return JSONObject.parseObject(additional).toJavaObject(SCommodityAdditional.class);
        } catch (Exception e) {
            log.error("产品附加信息转换失败", e);
            return null;
        }
    }

    @Data
    public static class SCommodityAdditional {

        String firstStandardPremium;
        String firstOccupationPremium;
        String collectionPremium;//首期保费
        String annual;
        String semiAnnual;
        String quarterly;
        String monthly;
        String payFreqIntv;
        String payMode;
        String payValue;
        String payPeriod;
        String insureMode;
        String insureValue;
        String insurePeriod;
        String renewalFlag;//续保标志 0-否/1-是
        String productValidate;//险种生效时间
        List<SDuty> dutyList;

    }

    @Data
    public static class SDuty {

        String code;
        String amount;
        String premium;
        String name;

    }
}

