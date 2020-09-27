package com.ccic.salesapp.noncar.service;
import com.ccic.salesapp.noncar.dto.request.ICarApplicationSearchRequestVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;

/**
 * 未支付投保单列表 接口
 * @author 孟秀萍
 *
 */
public interface CarApplicationService {
	
	public JsonVO getCarApplication(ICarApplicationSearchRequestVO iCarApplicationSearchRequestVO,PageVO pageVO);
}
