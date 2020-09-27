package com.ccic.salesapp.noncar.service;
import java.util.List;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salessapp.common.request.LoginUser;


public interface OrderOperateFacade {
	public JsonVO operateOrder(List<String> id,LoginUser loginUser);
}
