package com.ccic.salesapp.noncar.service;
import com.ccic.salesapp.noncar.dto.InsuranceApplication;
import com.ccic.salesapp.noncar.dto.request.ImageUploadRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salessapp.common.request.LoginUser;

public interface ImageFacade { 
	//获取影像上传页面地址
	public JsonVO uploade(ImageUploadRequestVO req,InsuranceApplication application, LoginUser user) throws Exception;
    
}
