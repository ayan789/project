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
public class TermsAndConditions {

    private String title;
    private String desc;
    private List<TermsAndConditionsContent> content;
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
	public List<TermsAndConditionsContent> getContent() {
		return content;
	}
	public void setContent(List<TermsAndConditionsContent> content) {
		this.content = content;
	}

     
}