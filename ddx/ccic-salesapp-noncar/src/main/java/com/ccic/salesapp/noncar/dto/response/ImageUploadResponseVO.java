package com.ccic.salesapp.noncar.dto.response;

import java.util.List;
 

import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ExtendInfo;
 
/*
 * 影像上传返回信息
 * */
public class ImageUploadResponseVO  extends DataVO{
   private String url;//链接地址
   private String token;//token
   private List <ExtendInfo> extend;//扩展信息
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public List<ExtendInfo> getExtend() {
	return extend;
}
public void setExtend(List<ExtendInfo> extend) {
	this.extend= extend;
}

   
}
