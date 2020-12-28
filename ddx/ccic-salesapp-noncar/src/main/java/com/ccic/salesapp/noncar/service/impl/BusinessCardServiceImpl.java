package com.ccic.salesapp.noncar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardAddTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardDelTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardPersonalTagsList;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardUpdateTagsReq;
import com.ccic.salesapp.noncar.dto.businessCard.BusinessCardVo;
import com.ccic.salesapp.noncar.repository.basedb.mapper.BusinessCardMapper;
import com.ccic.salesapp.noncar.service.BusinessCardService;
import com.ccic.salessapp.common.core.web.HttpResult;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BusinessCardServiceImpl implements BusinessCardService {

	@Autowired
	BusinessCardMapper businessCardMapper;

	@Override
	public HttpResult<?> getBusinessCard(BusinessCardReq businessCardReq) {
		log.info("getBusinessCard(请求参数):"+JSON.toJSONString(businessCardReq));
		if(StringUtils.isBlank(businessCardReq.getUserCode())) {
			return HttpResult.error(0, "usercode不能为空！");
		}
		//查询除个人标签外所有信息
		BusinessCardVo businessCardVo = null;
		try {
			businessCardVo = businessCardMapper.findBusinessCardByUserCode(businessCardReq);
			try {//查询个人标签
				List<BusinessCardPersonalTagsList> businessCardPersonalTags = businessCardMapper.findBusinessCardPersonalTagsByUserCode(businessCardReq);
				businessCardVo.setPersonalTagsList(businessCardPersonalTags);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "查询失败！");
		}
		log.info("getBusinessCard(返回参数):"+JSON.toJSONString(HttpResult.success(businessCardVo,1,"成功！")));
		return HttpResult.success(businessCardVo,1,"查询成功！");
	}
 
	@Override
	public HttpResult<?> updateOtherBusinessCard(BusinessCardUpdateReq businessCardUpdateReq) {
		log.info("updateOtherBusinessCard(请求参数):"+JSON.toJSONString(businessCardUpdateReq));
		//判断入参不为空
		if(StringUtils.isBlank(businessCardUpdateReq.getUserCode())) {
			return HttpResult.error(0, "usercode不能为空！");
		}
		BusinessCardVo businessCardVo = new BusinessCardVo();
		businessCardVo.setSlogan(businessCardUpdateReq.getSlogan());
		businessCardVo.setWechatNumber(businessCardUpdateReq.getWechatNumber());
		businessCardVo.setPersonalProfile(businessCardUpdateReq.getPersonalProfile());
		businessCardVo.setWorkEducationExperience(businessCardUpdateReq.getWorkEducationExperience());
		businessCardVo.setAreasOfExpertise(businessCardUpdateReq.getAreasOfExpertise());
		businessCardVo.setHonorsAndCertificates(businessCardUpdateReq.getHonorsAndCertificates());
		businessCardVo.setOther(businessCardUpdateReq.getOther());
		businessCardVo.setUserCode(businessCardUpdateReq.getUserCode());
		businessCardVo.setPersonalTagsId(businessCardUpdateReq.getPersonalTagsId());
		try {
			//更新之前先删除之前usercode下的标签
			businessCardMapper.delTags(businessCardUpdateReq.getUserCode());
			BusinessCardVo businessCardV = businessCardMapper.findByUserCode(businessCardUpdateReq.getUserCode());
			if(businessCardV!=null) {
				//更新插入传入的名片信息
				businessCardMapper.updateOtherBusinessCard(businessCardVo);
			} else {
				BusinessCardVo businessCardId = businessCardMapper.findByUserCodeTagsId();
				Long personalTagsId = businessCardId.getPersonalTagsId();
				Long aPersonalTagsId= personalTagsId+1;
				businessCardVo.setPersonalTagsId(aPersonalTagsId);
				businessCardMapper.insertBusinessVo(businessCardVo);
			}
			//更新插入个人名片里的list标签
				List<BusinessCardPersonalTagsList> personalTagsList = businessCardUpdateReq.getPersonalTagsList();
				//去重
				List<BusinessCardPersonalTagsList> newList = personalTagsList.stream().distinct().collect(Collectors.toList()); 
				for (int i = 0; i < newList.size(); i++) {
					Map map = new HashMap();
					map.put("personalTags", newList.get(i).getPersonalTags());
					map.put("personalTagsStatus", 0);
					map.put("personalTagsId", businessCardVo.getPersonalTagsId());
					businessCardMapper.addTags(map);
				}
		} catch (Exception e) {
			e.printStackTrace();
			return HttpResult.error(0, "更新名片内容失败！");
		}
		return HttpResult.success(1,"更新名片内容成功！");
	}
}
