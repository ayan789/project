package com.ccic.salesapp.noncar.dto.renewal.response;

import lombok.Data;

@Data
public class AccidentDTO {
	private String registNo;//报案号码
    private String policyNo;//保单号码
    private String insuredName;//被保险人名称
    private String license;//车牌号码
    private String damageTime;//出险日期
    private String caseStatus;//案件状态
    private String damageHour;//出险小时
    private String comCode;//归属机构代码
    private String reportTime;//报案时间
    private String reportHour;//报案小时
    private String damageremark;//出险经过
    private String damageaddress;//出险地点
    private String flag;//推定全损标志
    private String claimNo;//立案号
    private String reporerSeatNo;//报案登记坐席
    private String totalAmount;//总赔款金额
    private String SysStatue;//系统区分
    private String amountCurrency;//赔款金额币别
    private String caseNo;//结案号
}
