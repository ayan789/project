package com.ccic.salesapp.noncar.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TreeDO2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String name;
	private String grage;


}
