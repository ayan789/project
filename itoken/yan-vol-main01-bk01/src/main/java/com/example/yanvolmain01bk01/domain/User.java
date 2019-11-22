package com.example.yanvolmain01bk01.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-21 16:11:23
 */
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String address;
	//
	private String mobile;
	//
	private Date createtime;
	//
	private Integer role;

}
