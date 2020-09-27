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
public class GroupSalesPlanInfoRoot {

    private String salesPlanCode;
    private String groupPlanCode;
    private List<CustomerList> customerList;
    private List<ChildProductList> ChildProductList;
    public void setSalesPlanCode(String salesPlanCode) {
         this.salesPlanCode = salesPlanCode;
     }
     public String getSalesPlanCode() {
         return salesPlanCode;
     }

    public void setGroupPlanCode(String groupPlanCode) {
         this.groupPlanCode = groupPlanCode;
     }
     public String getGroupPlanCode() {
         return groupPlanCode;
     }

    public void setCustomerList(List<CustomerList> customerList) {
         this.customerList = customerList;
     }
     public List<CustomerList> getCustomerList() {
         return customerList;
     }

    public void setChildProductList(List<ChildProductList> ChildProductList) {
         this.ChildProductList = ChildProductList;
     }
     public List<ChildProductList> getChildProductList() {
         return ChildProductList;
     }

}