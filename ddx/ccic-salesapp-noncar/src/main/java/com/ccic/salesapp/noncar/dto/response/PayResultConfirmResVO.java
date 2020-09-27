package com.ccic.salesapp.noncar.dto.response;
import com.ccic.salesapp.noncar.dto.DataVO;


/**
 * 支付成功后支付状态回写
 * @author wangcw
 *
 */
public class PayResultConfirmResVO extends DataVO{

	private String url;//跳转url

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
