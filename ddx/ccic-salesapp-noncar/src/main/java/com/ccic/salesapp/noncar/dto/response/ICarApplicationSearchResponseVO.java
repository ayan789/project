package com.ccic.salesapp.noncar.dto.response;
import java.util.ArrayList;
import java.util.List;

import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.request.ICarApplicationVO;

/**
 * 投保单 返回信息
 * @author 孟秀萍
 *
 */
public class ICarApplicationSearchResponseVO extends DataVO{

	List<ICarApplicationVO> carApplications = new ArrayList<ICarApplicationVO>();

	public List<ICarApplicationVO> getCarApplications() {
		return carApplications;
	}

	public void setCarApplications(List<ICarApplicationVO> carApplications) {
		this.carApplications = carApplications;
	} 
}
