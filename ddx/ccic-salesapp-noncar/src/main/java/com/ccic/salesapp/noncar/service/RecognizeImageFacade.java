package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.response.JsonVO;
 
/**
 * 图像识别结果获取
 * @author ping
 *
 */
public interface RecognizeImageFacade {

	public abstract JsonVO recognizeImage(String imageType,String imagePath)throws Exception;
	
}
