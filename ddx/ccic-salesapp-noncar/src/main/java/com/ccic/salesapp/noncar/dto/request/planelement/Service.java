/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.List;

/**
 * Auto-generated: 2020-05-26 13:50:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Service {

    private String id;
    private String label;
    private String type;
    private boolean isRequired;
    private List<ValueList> valueList;
    private String defaultValue;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setLabel(String label) {
         this.label = label;
     }
     public String getLabel() {
         return label;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setIsRequired(boolean isRequired) {
         this.isRequired = isRequired;
     }
     public boolean getIsRequired() {
         return isRequired;
     }

    public void setValueList(List<ValueList> valueList) {
         this.valueList = valueList;
     }
     public List<ValueList> getValueList() {
         return valueList;
     }

    public void setDefaultValue(String defaultValue) {
         this.defaultValue = defaultValue;
     }
     public String getDefaultValue() {
         return defaultValue;
     }

}