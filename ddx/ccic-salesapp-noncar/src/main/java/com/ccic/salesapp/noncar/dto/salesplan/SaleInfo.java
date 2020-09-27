/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel("组合方案基本信息")
public class SaleInfo {
	@ApiModelProperty("保障期限")
    private String giCoveragePeriod;
	@ApiModelProperty("保障类型")
    private String giPeriodType;
	@ApiModelProperty("已售份数")
    private String saleNum;
    public void setGiCoveragePeriod(String giCoveragePeriod) {
         this.giCoveragePeriod = giCoveragePeriod;
     }
     public String getGiCoveragePeriod() {
         return giCoveragePeriod;
     }

    public void setGiPeriodType(String giPeriodType) {
         this.giPeriodType = giPeriodType;
     }
     public String getGiPeriodType() {
         return giPeriodType;
     }

    public void setSaleNum(String saleNum) {
         this.saleNum = saleNum;
     }
     public String getSaleNum() {
         return saleNum;
     }

}