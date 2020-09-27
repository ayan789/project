/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Auto-generated: 2020-05-26 13:50:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonInclude(value=Include.NON_NULL)
public class SafeguardDetail {

    private String title;
    private String desc;
    private List<ImgList> content;
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
	public List<ImgList> getContent() {
		return content;
	}
	public void setContent(List<ImgList> content) {
		this.content = content;
	}

     

}