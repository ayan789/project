package com.ccic.salesapp.noncar.dto.request.planelement;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private long id;
	// 父菜单ID，一级菜单为0
	private long pid;
	// 菜单名称
	private String name;


}
