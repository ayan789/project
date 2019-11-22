package com.example.yanvolmain01bk01.service;

import com.example.yanvolmain01bk01.domain.Content;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:46:59
 */
public interface ContentService {
	
	Content get(Long id);
	
	List<Content> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Content content);
	
	int update(Content content);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
