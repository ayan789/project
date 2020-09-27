/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.request.planelement;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2020-05-26 13:50:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Bottom {

    private int id;
    private String type;
    private String desc;
    private List<PbBox> pbBox = new ArrayList<PbBox>();
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }
	public List<PbBox> getPbBox() {
		return pbBox;
	}
	public void setPbBox(List<PbBox> pbBox) {
		this.pbBox = pbBox;
	}
     
}