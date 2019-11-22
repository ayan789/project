package com.example.yanvolmain01bk01.dao;

import com.example.yanvolmain01bk01.domain.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-21 16:11:23
 */
@Mapper
@Component
public interface UserDao {

	User get(Integer id);
	
	List<User> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(User user);
	
	int update(User user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
