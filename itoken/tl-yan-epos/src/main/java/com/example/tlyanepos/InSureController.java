package com.example.tlyanepos;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InSureController {

    /**流程一 */
    @GetMapping("/user/currentUser")
    public String currentUser () {
        System.out.println("currentUser");
        return "OK";
    }

    @GetMapping("/insure/loadProgress")
    public String loadProgress () {
        System.out.println("loadProgress");
        return "OK";
    }

    @GetMapping("/insure/inform/loadInform")
    public String loadInform () {
        System.out.println("loadInform");
        return "OK";
    }

    /**流程二 */
    @PostMapping("/insure/inform/saveInform")
    public String saveInform () {
        System.out.println("saveInform");
        return "OK";
    }

    @PostMapping("/insure/progress/nextStep")
    public String nextStep () {
        System.out.println("nextStep");
        return "OK";
    }

    //currentUser
    //loadProgress

    @PostMapping("/dict/getDictItem")
    public String getDictItem () {
        System.out.println("getDictItem");
        return "OK";
    }

    @PostMapping("/ins/plan/loadInsurant")
    public String loadInsurant () {
        System.out.println("loadInsurant");
        return "OK";
    }

    /**流程三 */
    @PostMapping("/ins/plan/saveCustomer")
    public String saveCustomer () {
        System.out.println("saveCustomer");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress
    //getDictItem

    @PostMapping("/ins/plan/loadApplicant")
    public String loadApplicant () {
        System.out.println("loadApplicant");
        return "OK";
    }

    /**流程四 */
    //saveCustomer
    //nextStep
    //loadProgress
    //currentUser
    //loadProgress

    @PostMapping("/ins/plan/loadPlan")
    public String loadPlan () {
        System.out.println("loadPlan");
        return "OK";
    }

    /**流程五 */
    @PostMapping("/ins/plan/savePlan")
    public String savePlan () {
        System.out.println("savePlan");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress
    //loadProgress

    @PostMapping("/ins/plan/previewPlan")
    public String previewPlan () {
        System.out.println("previewPlan");
        return "OK";
    }

    /**流程六 */
    @PostMapping("/ins/plan/bulidBenefitDocumentList")
    public String bulidBenefitDocumentList () {
        System.out.println("bulidBenefitDocumentList");
        return "OK";
    }

    @PostMapping("/insure/genApplicationNo")
    public String genApplicationNo () {
        System.out.println("genApplicationNo");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress
    //getDictItem
    //loadInsurant

    /**流程七 */
    @PostMapping("/insure/customer/saveInsurant")
    public String saveInsurant () {
        System.out.println("saveInsurant");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress
    //getDictItem
    //loadApplicant

    /**流程八 */
    @PostMapping("/apply/insure/customer/saveApplicant")
    public String saveApplicant () {
        System.out.println("saveApplicant");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress

    @PostMapping("/insure/beneficiary/getBeneficiaryCustomerList")
    public String getBeneficiaryCustomerList () {
        System.out.println("getBeneficiaryCustomerList");
        return "OK";
    }

    /**流程九 */
    @PostMapping("/insure/beneficiary/checkBeneficiaryRule")
    public String checkBeneficiaryRule () {
        System.out.println("checkBeneficiaryRule");
        return "OK";
    }

    //nextStep
    //currentUser
    //loadProgress
    //loadInform

    /**流程十 */
    //saveInform
    //nextStep
    //currentUser
    //loadProgress
    //loadInform

    /**流程十一 */
    //currentUser
    //loadProgress
    @PostMapping("/insure/pay/loadPayPage")
    public String loadPayPage () {
        System.out.println("loadPayPage");
        return "OK";
    }

    /**流程十二 */
    @PostMapping("/insure/pay/savePayInfo")
    public String savePayInfo () {
        System.out.println("savePayInfo");
        return "OK";
    }

    @PostMapping("/insure/loadSummaryPop")
    public String loadSummaryPop () {
        System.out.println("loadSummaryPop");
        return "OK";
    }

    @PostMapping("/insure/preInsureSubmit")
    public String preInsureSubmit () {
        System.out.println("preInsureSubmit");
        return "OK";
    }

    //nextStep
    //loadProgress
    //currentUser

    @PostMapping("/insure/document/loadDocument")
    public String loadDocument () {
        System.out.println("loadDocument");
        return "OK";
    }

    @PostMapping("/insure/document/loadAllSigns")
    public String loadAllSigns () {
        System.out.println("loadAllSigns");
        return "OK";
    }





}
