package com.neo;

/**
 * Created by zhangjunwen on 2017/10/23
 *
 * @author zhangjunwen
 * @date 2017/10/23
 */
public enum DictionaryEnum {

    COMMON("common","static/dict/common/"),
    COMPANY_COMMONTH("th_TH", "static/dict/th_TH/"),

    ;
    private String companyId;
    private String path;

    DictionaryEnum(String companyId, String path) {
        this.companyId = companyId;
        this.path = path;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getPath() {
        return path;
    }
}
