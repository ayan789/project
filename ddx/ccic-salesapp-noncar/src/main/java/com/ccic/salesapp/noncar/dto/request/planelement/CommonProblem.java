package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.ArrayList;
import java.util.List;

public class CommonProblem {

    private String title ="";
    private String desc ="";
    private List<CommonProblemContent> content = new ArrayList<CommonProblemContent>();
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
	public List<CommonProblemContent> getContent() {
		return content;
	}
	public void setContent(List<CommonProblemContent> content) {
		this.content = content;
	}

    
}