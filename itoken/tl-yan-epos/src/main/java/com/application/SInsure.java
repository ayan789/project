package com.application;

import com.alibaba.fastjson.JSONObject;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
//使用@bulider时，必须添加无参，全参构造方法，否则转json报错
@Builder//使用@bulider时，使用className.bulider().bulid()生成的对象中 属性类型默认的属性值无效，需要在属性上添加@Builder.Default
@NoArgsConstructor
@AllArgsConstructor
public class SInsure {
    public String insureId;//投保单ID
    public String companyId;//公司ID
    public String mainEngineProductId;//主险险种ID
    public String insureNo;//投保单号
    public String policyNo;//保单号
    public String planId;//计划ID
    public String branchCode;//pay information page select
    public String branchName;
    public String branchId;//from LDAP for role structure
    public String agentCode;
    public String agentName;
    public String insureType;
    public Date insureDate;//投保日期
    public Date signDate;//承保日期
    public Date valiDate;//生效日期
    public String innerContNo;//TR number

    public Integer runningStatus;//批处理执行状态
    public String updateCoreFlag;//是否已更新到鑫能核心库。

    public String step;//投保步骤
    public Integer status;//投保单状态
    public String applicantName;//投保人姓名
    public String applicantCertNo;//投保人证件号
    public String insurantName;//被保人姓名
    public String givenName;//名    被保人
    public String familyName;//姓   被保人
    public String stepKey;
    public String productName;//产品名称(首个主险)
    public String totalPremium;//保费合计
    public String coverPath;//保单列表封面图片

    public  String hasTopUpFund;
    public String topUpPremium;

    //受益人类型 1：法定受益人 2：指定受益人 3：投保人
    public String beneficiaryType;

    public String creator;//创建者
    public String userId;//代理人userId
    public Date createTime;//创建时间
    public String updater;//修改者
    public Date updateTime;//修改时间
    public String isDeleted = "N";//是否删除

    public SCustomer applicant;//投保人
    public SCustomer insurant;//被保人
    public SPlan plan;//投保计划
    public SBeneficiary beneficiary;//受益人
    public SInform inform;//告知项
    public SInformData informData;//告知项
    public  SPay pay;//支付信息
    public  List<SFund> premiumFundList;
    public List<SFund> topUpFundList;
    public JSONObject witness;

    public String submittedTime;
    public String paymentStatus;
    public String inSubmission;
    public String processStep;

    public String channel;
    public String caseId;
    //    SUWDecisionData uwDecisionData;
    public SPayCampaign payCompaign;

    public  SInsureFlag insureFlag;

}
