package com.ccic.salesapp.noncar.dto.request.news;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;


public class NewsVo {

    private List<AddArray> addArray;
    private List<Integer> delArray;
    private String storeCode;
    private String userCode;
    private String isInStore;
    private String comCode;
    private List<String> orgCodes;
    @ApiModelProperty("当前页")
	private Integer page = 1;
	@ApiModelProperty("每页数量")
	private Integer size = 10;
	@ApiModelProperty("")
	private String keyWord;
	private String findType;
    public void setAddArray(List<AddArray> addArray) {
         this.addArray = addArray;
     }
     public List<AddArray> getAddArray() {
         return addArray;
     }

    public void setDelArray(List<Integer> delArray) {
         this.delArray = delArray;
     }
     public List<Integer> getDelArray() {
         return delArray;
     }

    public void setStoreCode(String storeCode) {
         this.storeCode = storeCode;
     }
     public String getStoreCode() {
         return storeCode;
     }

    public void setUserCode(String userCode) {
         this.userCode = userCode;
     }
     public String getUserCode() {
         return userCode;
     }
	public String getIsInStore() {
		return isInStore;
	}
	public void setIsInStore(String isInStore) {
		this.isInStore = isInStore;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getFindType() {
		return findType;
	}
	public void setFindType(String findType) {
		this.findType = findType;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public List<String> getOrgCodes() {
		return orgCodes;
	}
	public void setOrgCodes(List<String> orgCodes) {
		this.orgCodes = orgCodes;
	}
    

}