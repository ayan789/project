package com.example.tlyannoif;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DictionaryEnum {

    COMMON("common","static/dict/common/","aaa"),
    COMPANY_COMMONTH("th_TH", "static/dict/th_TH/","bbb"),
    COMPANY_ALEX("th_Alex", "static/dict/th_Alex/","Alex"),
    ;

    private String companyId;
    private String path;
    private String value;


}
