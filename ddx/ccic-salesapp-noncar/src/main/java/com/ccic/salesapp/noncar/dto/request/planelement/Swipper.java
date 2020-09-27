package com.ccic.salesapp.noncar.dto.request.planelement;

public class Swipper {

    private int id;
    private String type ="";
    private String value ="";
    private String inUrl ="";
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

    public void setValue(String value) {
         this.value = value;
     }
     public String getValue() {
         return value;
     }
	public String getInUrl() {
		return inUrl;
	}
	public void setInUrl(String inUrl) {
		this.inUrl = inUrl;
	}
     
}