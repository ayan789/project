package com.example.yanvolmain01bk01.dao;

import com.example.yanvolmain01bk01.domain.Content;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:46:59
 */
@Mapper
@Component
public interface ContentDao {

	Content get(Long id);
	
	List<Content> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Content content);
	
	int update(Content content);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
