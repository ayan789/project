package com.example.tlyannoif;

import lombok.Data;

@Data
public class BaseVo {

	ThreadLocal<String> uuidLocal = new ThreadLocal<String>();
	
}
