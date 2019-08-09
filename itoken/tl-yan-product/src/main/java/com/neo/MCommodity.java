package com.neo;


import com.document.MEntry;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzhilei on 2017/10/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MCommodity {

    public String commodityId;//产品id
    public String companyId;//公司id
    public String engineProductId;//对应引擎中险种id
    public String productCode;//产品code
    public String productFullCode;//产品code
    public String name;//产品名称
    public String abbrName;//产品简称
    public String engName;
    public String riskType;//险种类型   main:主险    rider:附加险
    public boolean hasRider;//是否有附加险
    public String input;//录入项：amount/premium
    public List<MProductParam> paramList;//录入项列表
    public String premium;//首期保费
    public String basicAnnualPremium;//标准年保费
    public String collectionPremium;//首期保费
    public double firstStandardPremium;
    public double firstOccupationPremium;
    public Map premiumModFac;//不同缴别的保费
    public String amount;//保险金额
    public String productType;//产品类型
    public String payFreqDesc;//交费频次描述
    public String payFreqCode;//交费频次描述
    public String payDesc;//交费期间描述
    public String insureDesc;//保险期间描述
    public String clausePath;//条款路径
    public String clauseAbsolutePath;//条款全局路径
    public String coverPath;//预览顶部图片路径
    public String coverAbsolutePath;//预览图片路径
    public boolean btnRemove = true;
    public boolean isUl;
    public String occupationLevel;
    public int sequence;//temp sort

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MProductParam {

        public String inputType;
        public String label;//标签名
        public String key;//key值
        public List<MEntry> itemList;//可选项 code:desc
        public String value;//属性值
        public String text;
        public String maxLength;
        public boolean show = true;
        public boolean canEdit = true;//是否可编辑

        public void initDefault() {

            if (StringTool.isNull(inputType)) {
                this.inputType = ProductConfig.getInputType(key);
            }

            if (StringTool.isNull(label)) {
                this.label = ProductConfig.getLabel(key);
            }
        }

    }

}
