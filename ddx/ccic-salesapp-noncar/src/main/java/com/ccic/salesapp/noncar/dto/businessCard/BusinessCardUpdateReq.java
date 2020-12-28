package com.ccic.salesapp.noncar.dto.businessCard;

import java.util.List;

import lombok.Data;

@Data
public class BusinessCardUpdateReq {

	private Long personalTagsId;
	private String slogan;
	private String wechatNumber;
	private String personalProfile;
	private String workEducationExperience;
	private String areasOfExpertise;
	private String honorsAndCertificates;
	private String other;
	private String userCode;
	private List<BusinessCardPersonalTagsList> personalTagsList;
}
