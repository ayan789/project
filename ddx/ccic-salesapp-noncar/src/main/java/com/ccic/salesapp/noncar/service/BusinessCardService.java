package com.ccic.salesapp.noncar.service;

import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardAddTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardDelTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateTagsReq;
import com.ccic.salessapp.common.core.web.HttpResult;

@Service
public interface BusinessCardService {
	
	/**
	 * 个人名片
	 * @param businessCardReq
	 * @return
	 */
	HttpResult<?> getBusinessCard(BusinessCardReq businessCardReq);
	/**
	 * 更新名片内容
	 * @param businessCardUpdateReq
	 * @return
	 */
	HttpResult<?> updateOtherBusinessCard(BusinessCardUpdateReq businessCardUpdateReq);
}
