package com.ccic.salesapp.noncar.service;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.request.ICarApplicationSearchRequestVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;




/**
 * 未支付投保单列表 接口
 * @author wangpeng
 *
 */


@Service
public interface CarApplicationFacade {

	public JsonVO getCarApplication(ICarApplicationSearchRequestVO carApplicationSearchRequestVO,PageVO pageVO);
}
