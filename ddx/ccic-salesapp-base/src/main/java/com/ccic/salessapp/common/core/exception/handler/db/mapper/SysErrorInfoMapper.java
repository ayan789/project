package com.ccic.salessapp.common.core.exception.handler.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SysErrorInfoMapper {

    @Insert("INSERT INTO `t_sys_error_info` (`service_id`, `http_status`, `http_status_msg`, `error_code`, `error_msg`, `exception_info`, `exception_class`,`exception_msg`," +
            "`request_uri`,`request_id`) VALUES (#{serviceId}, #{httpStatus}, #{httpStatusMsg}, #{errorCode}, #{errorMsg}, #{exceptionInfo}, #{exceptionClass}, #{exceptionMsg}, " +
            "#{requestUri},#{requestId})")
    void insert(Map<String,Object> data);
}
