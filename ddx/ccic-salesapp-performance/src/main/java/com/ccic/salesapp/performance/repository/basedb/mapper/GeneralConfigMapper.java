package com.ccic.salesapp.performance.repository.basedb.mapper;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface GeneralConfigMapper {

    String getConfigValueNoCache(Map<String, String> p);
}
