package com.ccic.salesapp.noncar.dto.request;


/**
 * 图片上传
 * @author Administrator
 *
 */
public class PicInfoVO extends com.ccic.salesapp.noncar.dto.DataVO{

	private String imageType;//图像类型
    private String bytes;//照片流
    
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	    
}
