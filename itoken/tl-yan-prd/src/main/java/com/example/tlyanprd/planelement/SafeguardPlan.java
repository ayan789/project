package com.example.tlyanprd.planelement;

public class SafeguardPlan {

    private String title ="";
    private String desc ="";
    private FormulaObj formulaObj = new FormulaObj();
    
    private RelatedPersonInfo relatedPersonInfo = new RelatedPersonInfo();
    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setFormulaObj(FormulaObj formulaObj) {
         this.formulaObj = formulaObj;
     }
     public FormulaObj getFormulaObj() {
         return formulaObj;
     }

    public void setRelatedPersonInfo(RelatedPersonInfo relatedPersonInfo) {
         this.relatedPersonInfo = relatedPersonInfo;
     }
     public RelatedPersonInfo getRelatedPersonInfo() {
         return relatedPersonInfo;
     }

}