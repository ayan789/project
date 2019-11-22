package com.example.yanvolmain01bk01.service;

import com.example.yanvolmain01bk01.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 * 
 * @author ys
 * @email 704627050@qq.com
 * @date 2019-11-21 16:30:11
 */
public interface ItemService {
	
	Item get(Long id);
	
	List<Item> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Item item);
	
	int update(Item item);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
