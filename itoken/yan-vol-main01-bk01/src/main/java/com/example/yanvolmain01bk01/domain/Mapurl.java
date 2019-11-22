package com.example.yanvolmain01bk01.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:55:18
 */
@Data
public class Mapurl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String mapurlid;
	//
	private Date createdate;
	//
	private String createuserid;
	//
	private Integer isdelete;
	//
	private Date deletedate;
	//
	private String deleteuserid;
	//
	private String controller;
	//
	private Integer intercept;
	//
	private String pclink;
	//
	private String mobilelink;
	//
	private String method;
	//
	private Integer paramsize;
	//
	private String methodt;
	//
	private String methodh;

}
