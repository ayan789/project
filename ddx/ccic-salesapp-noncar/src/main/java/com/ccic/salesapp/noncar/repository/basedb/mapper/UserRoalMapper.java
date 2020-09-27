package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.UserRoleVO;
@Mapper
public interface UserRoalMapper {

	List<UserRoleVO> toList(String userCode);

}
