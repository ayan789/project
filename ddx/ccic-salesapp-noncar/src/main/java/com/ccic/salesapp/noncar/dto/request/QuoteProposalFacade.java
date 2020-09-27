package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salessapp.common.request.LoginUser;


/**
 * 下单接口
 * @author meng
 *
 */

public interface QuoteProposalFacade{

	public JsonVO QuoteProposal(IToProposalRequestVO toProposalRequestVO,String userCode, LoginUser user)throws Exception;
	
}
