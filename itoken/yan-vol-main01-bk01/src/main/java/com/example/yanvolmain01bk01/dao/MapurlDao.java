package com.example.yanvolmain01bk01.dao;

import com.example.yanvolmain01bk01.domain.Mapurl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-22 10:55:18
 */
@Mapper
@Component
public interface MapurlDao {

	Mapurl get(String mapurlid);
	
	List<Mapurl> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Mapurl mapurl);
	
	int update(Mapurl mapurl);
	
	int remove(String mapurlid);
	
	int batchRemove(String[] mapurlids);
}
