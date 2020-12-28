package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardPersonalTagsList;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardVo;

@Mapper
public interface BusinessCardMapper {

	BusinessCardVo findBusinessCardByUserCode(BusinessCardReq businessCardReq);

	List<BusinessCardPersonalTagsList> findBusinessCardPersonalTagsByUserCode(BusinessCardReq businessCardReq);

	void addTags(Map map);

	void updateTags(BusinessCardUpdateTagsReq businessCardUpdateTagsReq);

	void updateOtherBusinessCard(BusinessCardVo businessCardVo);

	void delTags(String userCode);

	BusinessCardVo findByUserCode(String userCode);

	void insertBusinessVo(BusinessCardVo businessCardVo);

	BusinessCardVo findByUserCodeTagsId();


}
