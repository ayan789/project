package com.ccic.salesapp.noncar.dto.businessCard;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BusinessCardVo {
	
	private Long id;
	private String slogan;
	private String wechatNumber;
	private Long personalTagsId;
	private String personalProfile;
	private String workEducationExperience;
	private String areasOfExpertise;
	private String honorsAndCertificates;
	private String other;
	private String userCode;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updatetime;
	private List<BusinessCardPersonalTagsList> personalTagsList;
}
