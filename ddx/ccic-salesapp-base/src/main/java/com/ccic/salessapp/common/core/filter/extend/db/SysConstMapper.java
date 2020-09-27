package com.ccic.salessapp.common.core.filter.extend.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysConstMapper {

    @Results(id = "selectByServiceId",value={
            @Result(column="const_key", property="constKey"),
            @Result(column="const_value", property="constValue")
    })
    @Select("SELECT const_key,const_value FROM `t_sys_const_config` WHERE service_id=#{serviceId}")
    List<Map<String,String>> selectByServiceId(String serviceId);


    @Results(id = "selectByServiceIdAndKey",value={
            @Result(column="const_key", property="constKey"),
            @Result(column="const_value", property="constValue")
    })
    @Select("SELECT const_key,const_value FROM `t_sys_const_config` WHERE service_id=#{serviceId} and const_key=#{constKey}")
    Map<String,String> selectByServiceIdAndKey(Map<String,Object> data);
}
