package com.neo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzhilei on 2017/10/19.
 */
@Data
public class SInsure {

    public static final int RUNNING_STATUS_NO = 0;//未跑批
    public static final int RUNNING_STATUS_YES = 1;//跑批中

    String insureId;//投保单ID
    String companyId;//公司ID
    String mainEngineProductId;//主险险种ID
    String insureNo;//投保单号
    String policyNo;//保单号
    String planId;//计划ID
    String branchCode;//pay information page select
    String branchName;
    String branchId;//from LDAP for role structure
    String agentCode;
    String agentName;
    String insureType;
    Date insureDate;//投保日期
    Date signDate;//承保日期
    Date valiDate;//生效日期
    String innerContNo;//TR number

    Integer runningStatus;//批处理执行状态
    String updateCoreFlag;//是否已更新到鑫能核心库。

    String step;//投保步骤
    Integer status;//投保单状态
    String applicantName;//投保人姓名
    String applicantCertNo;//投保人证件号
    String insurantName;//被保人姓名
    String givenName;//名    被保人
    String familyName;//姓   被保人
    String stepKey;
    String productName;//产品名称(首个主险)
    String totalPremium;//保费合计
    String coverPath;//保单列表封面图片

    String hasTopUpFund;
    String topUpPremium;

    //受益人类型 1：法定受益人 2：指定受益人 3：投保人
    String beneficiaryType;

    String creator;//创建者
    String userId;//代理人userId
    Date createTime;//创建时间
    String updater;//修改者
    Date updateTime;//修改时间
    String isDeleted = "N";//是否删除

    SCustomer applicant;//投保人
    SCustomer insurant;//被保人
    SPlan plan;//投保计划
    SBeneficiary beneficiary;//受益人
    SInform inform;//告知项
    SInformData informData;//告知项
    SPay pay;//支付信息
    List<SFund> premiumFundList;
    List<SFund> topUpFundList;
    JSONObject witness;

    String submittedTime;
    String paymentStatus;
    String inSubmission;
    String processStep;

    String channel;
    String caseId;
    String uwDecisionData;

    /**
     * 是否代理人为同一人
     */
    public boolean checkIsSelfSeller() {
        if (applicant != null && applicant.checkIsSelfSeller()) {
            return true;
        }
        if (insurant != null && insurant.checkIsSelfSeller()) {
            return true;
        }
        return false;
    }

    /**
     * 是否本人投保
     */
    public boolean checkForSelf() {

        if (applicant == null && insurant == null) {
            return false;
        }

        if (insurant != null && applicant != null && applicant.getCustomerId().equals(insurant.getCustomerId())) {
            return true;
        }

//        MEntry entryRelation = insurant != null ? insurant.toCustomerModel().entryRelation : null;
//
//        if (entryRelation == null) {
//            if(applicant!=null){
//                entryRelation = applicant.toCustomerModel().entryRelation;
//            }
//        }
//
//        if (entryRelation == null) {
//            return false;
//        }
//
//        DItem commonRelationItem = DictUtils.transformCommonDict(companyId, "insuredRelation", entryRelation.value);
//        if (commonRelationItem != null) {
//            String code = commonRelationItem.getCode();
//            if (InsureConfig.COMMON_RELATION_SELF.equals(code)) {
//                return true;
//            }
//        }

        return false;
    }

    public static void bulidProcessStep(SInsure sInsure, String stepKey) {
        String processStep = sInsure.getProcessStep();
        if (StringTool.isNotNull(processStep)) {
            String[] steps = processStep.split(",");
            ArrayList<String> stepList = new ArrayList<>(Arrays.asList(steps));
            if (CollectionUtils.isNotEmpty(stepList)) {
                if (!stepList.contains(stepKey)) {
                    stepList.add(stepKey);
                    StringBuffer sb = new StringBuffer();
                    for (String step : stepList){
                        sb.append(step).append(",");
                    }
                    sInsure.setProcessStep(sb.toString());
                }
            }
        } else {
            processStep = stepKey+",";
            sInsure.setProcessStep(processStep);
        }
    }

    public static void reset(SInsure sInsure, AgentUserDTO agentUserDTO) {
        sInsure.setInsureDate(null);
        sInsure.setPolicyNo(null);
        sInsure.setInsureNo(null);
        sInsure.setAgentCode(agentUserDTO.getAgentCode());
        sInsure.setAgentName(agentUserDTO.getFullName().replaceAll("[\\t\\n\\r]", " "));
        sInsure.setBranchId(agentUserDTO.getBranch());
//        sInsure.setBranchName(agentUserDTO.getBranchName());
        sInsure.setStatus(InsureConfig.STATUS_UNFINISH);
        sInsure.setIsDeleted("N");
        sInsure.setUserId(agentUserDTO.getUserId());
        sInsure.setCreator(agentUserDTO.getAgentCode());
        sInsure.setCreateTime(new Date());
        sInsure.setUpdater(agentUserDTO.getAgentCode());
        sInsure.setUpdateTime(new Date());
        sInsure.setChannel(agentUserDTO.getChannel());
    }
}
