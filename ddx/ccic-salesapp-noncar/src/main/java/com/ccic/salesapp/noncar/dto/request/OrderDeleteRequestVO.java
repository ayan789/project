package com.ccic.salesapp.noncar.dto.request;
import java.util.List;
import com.ccic.salesapp.noncar.dto.DataVO;






public class OrderDeleteRequestVO extends DataVO{

	private List<String> id;

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}
	
}
