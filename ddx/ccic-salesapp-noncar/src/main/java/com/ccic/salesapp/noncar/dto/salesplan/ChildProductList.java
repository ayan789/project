/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

/**
 * Auto-generated: 2020-09-09 14:41:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ChildProductList {

    private String planCode;
    private List<ClauseList> clauseList;
    public void setPlanCode(String planCode) {
         this.planCode = planCode;
     }
     public String getPlanCode() {
         return planCode;
     }

    public void setClauseList(List<ClauseList> clauseList) {
         this.clauseList = clauseList;
     }
     public List<ClauseList> getClauseList() {
         return clauseList;
     }

}