package com.neo;

import com.document.BeneficiaryManager;
import com.document.BeneficiaryMangerImpl;
import com.document.OccupationEnum;
import com.document.OccupationName;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsureFactorsBuild {

    private final int SEVENTEEN_AGE = 17;

    private final int NINETEEN_AGE = 19;

    private final int TWENTY_AGE = 20;

    private final String MARITAL_STATUS = "M";

    private  final String IS_MARRIED_REGISTER = "Y";

    private final String RELATIONSHIP_FATHER="Father";

    private final String RELATIONSHIP_MOTHER="Mother";

    public InsureFactors buildInsureFactors(String insureId) {
        InsureManager insureManager = new InsureMangerImpl();
        SInsure sInsure = insureManager.loadFullInsure(insureId);
        if (sInsure == null)
            return null;
        return buildInsureFactors(sInsure);
    }

    @SuppressWarnings("Duplicates")
    public InsureFactors buildInsureFactors(SInsure sInsure) {
        InsureFactors insureFactors = new InsureFactors(sInsure);
        String planId = sInsure.getPlanId();
        String insureType = sInsure.getInsureType();
        if (StringTool.isNotNull(planId)) {
//            MPlan mPlan = planService.loadPlanModel(planId);
            //入参
            MPlan mPlan = new MPlan();
            if (mPlan != null) {
                String applicationType = mPlan.applicationType;
                insureFactors.set("MODEL_PLAN", mPlan);
                insureFactors.set("APPLICATION_TYPE", applicationType);
                //The question must be compulsorily when buying these specific riders HX,HI,CM,HP,OI,OD2
                boolean health8 = false;
                String productIds = "HS,HI,CM,HP,OI,OD2,HA";
                for (MCommodity mCommodity : mPlan.commodityList) {
                    if ("PB".equals(mCommodity.engineProductId)) {
                        insureFactors.set("SELECT_PB", "PB");
                    }
                    if ("HA".equals(mCommodity.engineProductId)) {
                        insureFactors.set("SELECT_HA", "HA");
                    }
                    if (productIds.indexOf(mCommodity.engineProductId) != -1) {
                        health8 = true;
                        continue;
                    }
                }
                insureFactors.set("HEALTH8", health8);
                int size = mPlan.commodityList.size();
                boolean addRisk = false;
                if (size > 1) {
                    addRisk = true;
                }
                insureFactors.set("ADD_RISK", addRisk);

//                if (CollectionUtil.isNotEmpty(mPlan.commodityList)) {
                    insureFactors.set("PROD_CODE", mPlan.commodityList.get(0).engineProductId);
//                }
            }
        }

        insureFactors.set("INSURE_TYPE",insureType);
        String hasTopUpFund = sInsure.getHasTopUpFund();
        insureFactors.set("HAS_TOP_UP_FUND",hasTopUpFund);
        if (StringTool.isNotNull(sInsure.getInsureId())) {
//            boolean rpqMandatoryByFund = insureServiceImpl.checkComplexFund(sInsure.getInsureId());
            boolean rpqMandatoryByFund = true;
            insureFactors.set("RPQ_MANDATORY_BYFUND", rpqMandatoryByFund);
        }
//        String channel = LoginUtil.getAgentChannel();
        String channel = "014";
        insureFactors.set("AGENT_CHANNEL",channel);
        // add risk level
        if(sInsure.getInsurant()!=null) {
            insureFactors.set("RISK_LEVEL", sInsure.getInsurant().getRiskLevel());
        }
        getSecStatus(sInsure,insureFactors);        Map map = annualIncomeandOccupationRange(sInsure.getInsureId());
        insureFactors.set("ANNUAL_INCOME",map.get("annualIncome"));
        insureFactors.set("OCCUPATION_NAME",map.get("occupationName"));


        return insureFactors;
    }


    @SuppressWarnings("Duplicates")
    public Map annualIncomeandOccupationRange(String insureId) {
        Map map = new HashMap<>();
        String annualIncome = null;
        String occupationName = null;
        InsureManager insureManager = new InsureMangerImpl();
        SCustomer sCustomer = insureManager.loadInsurant(insureId);
        if (null != sCustomer) {
            int income = StringTool.isNotNull(sCustomer.getAnnualIncome()) ? Integer.parseInt(sCustomer.getAnnualIncome()) : 0;
            if (income < 500000) {
                annualIncome = "A";
            } else if (income >= 500000 && income <= 1000000) {
                annualIncome = "B";
            } else if (income >= 1000001 && income <= 2000000) {
                annualIncome = "C";
            } else if (income >= 2000001 && income <= 3000000) {
                annualIncome = "D";
            } else if (income >= 3000001 && income <= 4000000) {
                annualIncome = "E";
            } else if (income >= 4000001 && income <= 5000000) {
                annualIncome = "F";
            } else if (income > 5000000) {
                annualIncome = "G";
            }
            OccupationEnum occupationEnum = OccupationEnum.getOccupationCode(sCustomer.getOccupationCode());

            if (null != occupationEnum) {
                String  occupationGrouppingCode = occupationEnum.grouppingCode;
                OccupationName name = OccupationName.getGrouppingCode(occupationGrouppingCode);
                if (null!= name){
                    occupationName =  name.occupationName;
                }

            }
        }
        map.put("annualIncome", annualIncome);
        map.put("occupationName", occupationName);
        return map;
    }

    @SuppressWarnings("Duplicates")
    private InsureFactors getSecStatus(SInsure sInsure,InsureFactors insureFactors) {
        SCustomer insurant = sInsure.getInsurant();
        SCustomer payor = sInsure.getApplicant();
        boolean secStatus = false;
        boolean secSpouseStatus = false;
        boolean secChildStatus = false;
        if (null != insurant && null != payor) {
            int age = insurant.getAge() != null ? Integer.parseInt(insurant.getAge()) : 0;
            String relationship = payor.getRelationCode();
            String payorMaritalStatus = payor.getMaritalStatus();
            String payorIsMarriedRegister = payor.getIsMarriedRegister();
            String insurantMaritalStatus = insurant.getMaritalStatus();
            String insurantIsMarriedRegister = insurant.getIsMarriedRegister();
            boolean ageflag = age >= SEVENTEEN_AGE && age <= NINETEEN_AGE;
            boolean payorMaritalFlag = MARITAL_STATUS.equals(payorMaritalStatus) && IS_MARRIED_REGISTER.equals(payorIsMarriedRegister);
            boolean insurantMaritalFlag = MARITAL_STATUS.equals(insurantMaritalStatus) && IS_MARRIED_REGISTER.equals(insurantIsMarriedRegister);
            boolean  juvenileCase = age >=SEVENTEEN_AGE && age <=NINETEEN_AGE && MARITAL_STATUS.equals(insurantMaritalStatus) ;
            boolean relationshipFlag = RELATIONSHIP_FATHER.equals(relationship) || RELATIONSHIP_MOTHER.equals(relationship);
            if (age < SEVENTEEN_AGE && relationshipFlag && payorMaritalFlag) {
                secStatus = true;
            }
            if (ageflag && relationshipFlag && !IS_MARRIED_REGISTER.equals(insurantIsMarriedRegister)) {
                secStatus = true;
            }
            if (age >= SEVENTEEN_AGE && insurantMaritalFlag) {
                secStatus = true;
            }
            if (age >= TWENTY_AGE && !IS_MARRIED_REGISTER.equals(insurantIsMarriedRegister)) {
                secStatus = true;
                secSpouseStatus = true;
            }
            if (ageflag && relationshipFlag && payorMaritalFlag ){
                secStatus = true;
            }
            //获取受益人的相关信息
            BeneficiaryManager beneficiaryManager = new BeneficiaryMangerImpl();
            List<MCustomer> beneficiary =  beneficiaryManager.getBeneficiaryCustomerList(sInsure.getInsureId());
            if (CollectionUtils.isNotEmpty(beneficiary)) {
                for (MCustomer mCustomer : beneficiary) {
                    secChildStatus = true;//显示小孩信息
                }
            }
            insureFactors.set("SEC_STATUS", secStatus);
            insureFactors.set("SEC_SPOUSE_STATUS", secSpouseStatus);
            insureFactors.set("SEC_CHILD_STATUS", secChildStatus);
            insureFactors.set("insuredAge", age);
            insureFactors.set("beneficiary",beneficiary);
            insureFactors.set("sInsure",sInsure);
        }

        return insureFactors;
    }

}
