package com.neo;

/**
 * Created by yangzhilei on 2017/10/24.
 */
public class InsureConfig {

    public static final String UL = "ul";
    public static final String OL = "ol";

    public static final String PREMIUM_FUND_ALLOCATION = "PREMIUM";
    public static final String TOP_UP_FUND_ALLOCATION = "TOP_UP";

    public static final String COMMON_RELATION_SELF = "01";//本人关系code

    public static final String COMMON_CERT_TYPE_ID = "01";//身份证code

    public static final String[] QUOTATION = {"step_insurant", "step_applicant", "step_plan", "step_benefit"};

    public static final String[] APPLICATION = {"step_insurant_additional", "step_applicant_additional", "step_beneficary", "step_lifestyle_questions", "step_health_questions", "step_fatca", "step_kyccdd_questions","step_payment", "step_documents"};

//    public static final String[] SUBMISSION = {"step_payment", "step_documents"};

    public static final int TAB_ALL = 0;//全部
    public static final int TAB_INPUTINSURE = 1;//录入中
    public static final int TAB_READYPAY = 2;//待缴费
    public static final int TAB_UNDERWRITING = 3;//核保中
    public static final int TAB_FINISH = 4;//已完成
    public static final int TAB_HANDMANDE = 5;//手工单

    //投保单中客户类型
    public static final String CUSTOMER_TYPE_APPLICANT = "1";//投保人
    public static final String CUSTOMER_TYPE_INSURANT = "2";//被保人
    public static final String CUSTOMER_TYPE_BENEFICIARY = "3";//受益人

    //投被保人录入地址类型
    public static final String ADDRESS_TYPE_CURRENT = "1";
    public static final String ADDRESS_TYPE_HOUSE = "2";
    public static final String ADDRESS_TYPE_WORK = "3";
    public static final String ADDRESS_TYPE_POLICY_MAILING = "4";
    public static final String ADDRESS_TYPE_MAILING = "5";


    //投保单中受益人类型
    public static final String BENEFICIARY_TYPE_LEGAL = "1";//法定
    public static final String BENEFICIARY_TYPE_DESIGNATED = "2";//指定
    public static final String BENEFICIARY_TYPE_APPLICANT = "3";//投保人

    //投保单步骤
    public static final String INSURE_STEP_APPLICANT = "step_applicant";//投保人
    public static final String INSURE_STEP_INSURANT = "step_insurant";//被保人
    public static final String INSURE_STEP_PLAN = "step_plan";//险种编辑
    public static final String INSURE_STEP_BENEFICIARY = "step_beneficiary";//受益人
    public static final String INSURE_STEP_INFORM_APPLICANT = "step_inform_applicant";//投保人告知
    public static final String INSURE_STEP_INFORM_INSURANT = "step_inform_insurant";//被保人告知
    public static final String INSURE_STEP_PAY_INPUT = "step_pay_input";//支付信息
    public static final String INSURE_STEP_UPLOAD_IMAGE = "step_upload_image";//投保资料上传
    public static final String INSURE_STEP_PREVIEW = "step_preview";//投保单预览
    public static final String INSURE_STEP_HAND_MAND = "step_hand_made";//手工单

    //投保单状态
    public static final int STATUS_UNFINISH = 1000;//未完成
//    public static final int STATUS_SUBMIT = 1001;//已提交 待核保
//    public static final int STATUS_UNDERWRITING = 1002;//核保中
//    public static final int STATUS_READYPAY = 1011;//待支付
//    public static final int STATUS_PAYFAIL = 1012;//支付失败
//    public static final int STATUS_PAYING = 1013;//支付中

    public static final int STATUS_PRE_SUBMIT = 1050;//PRE SUBMIT
    public static final int STATUS_SUBMIT = 1051;//SUBMIT
    public static final int STATUS_PAY_INFO_SUBMIT = 1052;//payInfo submit

    public static final int STATUS_SUBMITTING = 1040;//submitting
    public static final int STATUS_PAY_INFO_SUBMITTING = 1041;//pay info submitting

    //人工核保、已承保、承保失败
    public static final int STATUS_ARTIFICIAL = 2001;//人工核保
    public static final int STATUS_INSURED = 2003;//已承保
    public static final int STATUS_INSURED_FAIL = 2004;//承保失败
    public static final int STATUS_PAYED = 2005;//支付成功 待承保

    //手工单录入完成
    public static final int STATUS_HAND_SUBMIT = 3000;//手工单提交

    //支付状态
    public static final int PAY_STATUS_SUCCESS = 10000;//支付成功
    public static final int PAY_STATUS_FAIL = 10001;//支付失败
    public static final int PAY_STATUS_UNKNOWN = 10002;//支付不确定
    public static final int PAY_STATUS_PAYING = 10003;//支付中，已提交支付

    public static boolean isCanNotChange(int status) {
        return status == STATUS_SUBMIT || status == STATUS_SUBMIT || status == STATUS_PAY_INFO_SUBMIT || status == STATUS_SUBMITTING || status == STATUS_PAY_INFO_SUBMITTING;
    }

    public static String getStepDesc(String step) {
        if (INSURE_STEP_APPLICANT.equals(step) || INSURE_STEP_INSURANT.equals(step)) {
            return "客户信息";
        }
        if (INSURE_STEP_PLAN.equals(step)) {
            return "投保计划";
        }
        if (INSURE_STEP_BENEFICIARY.equals(step)) {
            return "受益人";
        }
        if (INSURE_STEP_INFORM_APPLICANT.equals(step)) {
            return "投保人告知";
        }
        if (INSURE_STEP_INFORM_INSURANT.equals(step)) {
            return "被保人告知";
        }
        if (INSURE_STEP_PAY_INPUT.equals(step)) {
            return "支付信息";
        }
        if (INSURE_STEP_UPLOAD_IMAGE.equals(step)) {
            return "投保资料上传";
        }
        if (INSURE_STEP_PREVIEW.equals(step)) {
            return "投保单预览";
        }
        if (INSURE_STEP_HAND_MAND.equals(step)) {
            return "手工单";
        }
        return "未知步骤";
    }

//    public static String underWriteResult(int step) {
//        if (step == STATUS_ARTIFICIAL) {
//            return "自核不通过";
//        } else if (step == STATUS_UNFINISH || step == STATUS_SUBMIT) {
//            return "录入中";
//        } else {
//            return "自核通过";
//        }
//    }

//    public static Integer[] getStatusByTabId(int tabId) {
//        switch (tabId) {
//            case TAB_INPUTINSURE:
//                return new Integer[]{STATUS_UNFINISH};
//            case TAB_READYPAY:
//                return new Integer[]{STATUS_SUBMIT, STATUS_READYPAY, STATUS_PAYFAIL, STATUS_PAYING};
//            case TAB_UNDERWRITING:
//                return new Integer[]{STATUS_UNDERWRITING};
//            case TAB_FINISH:
//                return new Integer[]{STATUS_ARTIFICIAL, STATUS_INSURED, STATUS_INSURED_FAIL, STATUS_PAYED};
//            case TAB_HANDMANDE:
//                return new Integer[]{STATUS_HAND_SUBMIT};
//        }
//        return null;
//    }

    public static boolean canEdit(int status, String isDeleted) {
        return isDeleted != "Y" && status == STATUS_UNFINISH && status != STATUS_HAND_SUBMIT;
    }

    public static boolean canDelete(int status, String isDeleted) {
        return isDeleted != "Y" && status == STATUS_UNFINISH && status != STATUS_HAND_SUBMIT;
    }

    public static boolean canCopy(int status, String isDeleted) {
        return isDeleted != "Y" && status != STATUS_HAND_SUBMIT;
    }

    public static boolean canLook(int status, String isDeleted) {
        return isDeleted != "Y" && status != STATUS_UNFINISH;
    }

    public static String getStatusDesc(int status) {
        switch (status) {
            case STATUS_UNFINISH:
                return "录入中";
            case STATUS_SUBMIT:
                return "待核保";
            case STATUS_ARTIFICIAL:
                return "人工核保";
            case STATUS_INSURED:
                return "已承保";
            case STATUS_PAYED:
                return "待承保";
            case STATUS_INSURED_FAIL:
                return "承保失败";
            case STATUS_HAND_SUBMIT:
                return "手工单已提交";
        }
        return "未知状态";
    }

    public static String getUnderWriteResult(int status) {
        if (status == STATUS_ARTIFICIAL) {
            return "自核不通过";
        } else if (status == STATUS_UNFINISH) {
            return "录入中";
        } else if (status == STATUS_SUBMIT) {
            return "待核保";
        } else {
            return "自核通过";
        }
    }

    public static boolean isApplicant(String customerType) {
        return CUSTOMER_TYPE_APPLICANT.equals(customerType);
    }

    public static boolean isInsurant(String customerType) {
        return CUSTOMER_TYPE_INSURANT.equals(customerType);
    }
}
