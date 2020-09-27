package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SMessage;
import com.ccic.salesapp.performance.domain.vo.request.MessageVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 消息表
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:46:25
 */
@Mapper
@Component
public interface SMessageDao {

	SMessage get(BigDecimal id);
	
	List<SMessage> list(Map<String,Object> map);
	
	List<Map> getMessageType(MessageVo messageVo);
	
	List<Map> getMessageList(MessageVo messageVo);
	
	int count(Map<String,Object> map);
	
	int save(SMessage sMessage);
	
	int update(SMessage sMessage);
	
	int remove(BigDecimal ID);
	
	int batchRemove(BigDecimal[] ids);
}

