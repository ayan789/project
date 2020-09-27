package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GeneralConfigMapper {

    List<String> getConfigValueNoCache(Map map);

	Map<String, String> getConfigValueNoCacheTwo(Map<String, String> map);
    
    
}
