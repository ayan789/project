/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class ProductInfo {

    private String title;
    private String desc;
    private List<ImgList> content = new ArrayList<ImgList>();
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