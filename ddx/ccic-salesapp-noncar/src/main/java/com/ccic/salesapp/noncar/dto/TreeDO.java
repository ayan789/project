package com.ccic.salesapp.noncar.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TreeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private long pid;
	private String name;


}
