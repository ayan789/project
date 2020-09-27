package com.ccic.salesapp.noncar.dto.request;

import java.util.List;
 

/**
 * 图像识别返回信息
 * @author ping
 *
 */
public class RecognizeImageResponseVO extends com.ccic.salesapp.noncar.dto.DataVO{
	private List<FieldVO> recognizedFields;
	
	public List<FieldVO> getRecognizedFields() {
		return recognizedFields;
	}
	public void setRecognizedFields(List<FieldVO> recognizedFields) {
		this.recognizedFields = recognizedFields;
	}
	
}
