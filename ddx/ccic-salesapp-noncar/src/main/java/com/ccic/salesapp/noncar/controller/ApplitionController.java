package com.ccic.salesapp.noncar.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.request.ICarApplicationSearchRequestVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.service.CarApplicationService;
import com.ccic.salesapp.noncar.service.impl.CarApplicationServiceImpl;
import com.ccic.salessapp.common.core.web.HttpResult;
 
@RestController
public class ApplitionController {
	
	/**
	 * 投保单列表查询
	 * 
	 * @param jsonVO
	 * @return
	 * @author 孟秀萍
	 */
	@PostMapping("/getCarApplition")
	public HttpResult getCarApplition(Object jsonVO) {
		
		JsonVO json = (JsonVO) jsonVO;
		PageVO pageVO = (PageVO) json.getPage();
		
		ICarApplicationSearchRequestVO iCarApplicationSearchRequestVO = new ICarApplicationSearchRequestVO();
		iCarApplicationSearchRequestVO = (ICarApplicationSearchRequestVO) json.getData();
		int pageSize = pageVO.getSize();
		int currentPage = pageVO.getPage();
		String size = pageSize + "";
		String page = currentPage + "";
		String staffCode = iCarApplicationSearchRequestVO.getStaffCode();
		if (staffCode == null || "".equals(staffCode)) {
			HttpResult result = HttpResult.error(0, "员工代码不能为空");
			return result;
		}
		if ("0".equals(size)) {
			HttpResult result = HttpResult.error(0, "请输入页记录数");
			return result;
		}
		if ("0".equals(page)) {
			HttpResult result = HttpResult.error(0, "请输入当前页数");
			return result;
		}
		CarApplicationService carApplicationFacade = new CarApplicationServiceImpl();
		try {
			JsonVO jsonVO2 = carApplicationFacade.getCarApplication(iCarApplicationSearchRequestVO, pageVO);
			json.setData(jsonVO2.getData());
			json.setResult(jsonVO2.getResult());
			json.setPage(jsonVO2.getPage());
		} catch (Exception e) {
			HttpResult result = HttpResult.error(0, "获取投保单异常");
			return result;
		}
		return null;
	}

}
