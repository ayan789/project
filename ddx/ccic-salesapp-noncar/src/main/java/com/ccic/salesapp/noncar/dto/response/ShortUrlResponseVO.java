package com.ccic.salesapp.noncar.dto.response;

import com.ccic.salesapp.noncar.dto.request.DataVO;

/**
 * ���������ɷ��ض���
 * @author wangcw
 *
 */
public class ShortUrlResponseVO extends DataVO{

	private String shortUrl;//������

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

}
