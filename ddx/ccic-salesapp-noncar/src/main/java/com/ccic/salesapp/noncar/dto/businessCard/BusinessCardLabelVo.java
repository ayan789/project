package com.ccic.salesapp.noncar.dto.businessCard;

import java.util.Date;

import lombok.Data;

@Data
public class BusinessCardLabelVo {

	private Long id;
	private String personalTags;
	private int personalTagsStatus;
	private Long personalTagsId;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updatetime;
}
