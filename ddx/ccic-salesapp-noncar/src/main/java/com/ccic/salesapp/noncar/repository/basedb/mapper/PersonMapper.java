package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.repository.basedb.po.Person;

@Mapper
public interface PersonMapper {

	int insertSelective(Person person);
	
	List<Person> selectByPersonListId(long personListId);
	
	Integer newPersonListId();
	
	int updateOrderIdByPersonListId(Person person);
}
