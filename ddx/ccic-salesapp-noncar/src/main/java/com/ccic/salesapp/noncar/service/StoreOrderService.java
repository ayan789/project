package com.ccic.salesapp.noncar.service;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreCertiNoticeRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreOrderDeleteRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryDetailReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.StoreListQueryResponseVO;
import com.ccic.salessapp.common.core.web.HttpResult;


/**
 * 
 * 非车险订单管理Service
 * @author wangpeng
 *
 */

@Service
public interface StoreOrderService {

	// 门店订单列表查询
	public HttpResult<StoreListQueryResponseVO> storeOrderListQuery(StoreQueryListRequestVO reqvo);

	// 订单详情
	public HttpResult<StoreInsureInfoFormulasResponseVO> storeOrderDetail(StoreQueryDetailReqVO req);

	// 门店保单回写
	public HttpResult<?> certiNotice(StoreCertiNoticeRequestVO reqvo);

	// 删除订单
	public HttpResult<?> deleteStoreOrder(StoreOrderDeleteRequestVO reqvo)throws Exception ;

	//订单转发支付查询信息
	public HttpResult<StoreInsureInfoFormulasResponseVO> forwardPayProductInsureInfo(ForwardPayInsureInfoReqVO reqvo);

	
}
 

