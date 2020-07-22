package com.bootdo.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MenuDO2 implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private Long id;
	// 父菜单ID，一级菜单为0
	private Long pid;
	// 菜单名称
	private String name;


}
