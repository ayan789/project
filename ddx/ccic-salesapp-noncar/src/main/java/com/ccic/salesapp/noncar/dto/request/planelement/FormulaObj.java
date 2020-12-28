/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.HashMap;
import java.util.List;


import lombok.Data;

/**
 * Auto-generated: 2020-05-26 13:50:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class FormulaObj {
    
    private List<Insurance> mainInsurance;
    private List<Insurance> subsidiaryInsurance;
    private List<HashMap<String,Object>> renewalCoverageMapping;


}