package com.neo;


import java.util.List;

/**
 * Created by yangzhilei on 2017/10/17.
 */
public class MRule {

    public final static int LEVEL_ALERT = 101;                //规则不通过时仅提示警告
    public final static int LEVEL_FAIL = 102;                //规则不通过时产品在当前参数选择下禁止购买

    public String productName;//险种名称
    public List<MRuleItem> failList;
    public List<MRuleItem> alertList;

    public boolean hasFail() {
        return failList != null && failList.size() > 0;
    }

    public boolean hasAlert() {
        return alertList != null && alertList.size() > 0;
    }

    public static class MRuleItem {

        public int level = LEVEL_FAIL;//规则等级
        public String desc;//规则描述

    }
}