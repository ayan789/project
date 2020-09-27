package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.noncar.repository.basedb.po.PolicyCustomer;

@Component
@Mapper
public interface PolicyCustomerMapper {


	int deleteByPrimaryKey(Long customerId);

    int insert(PolicyCustomer record);

    int insertSelective(PolicyCustomer record);

    PolicyCustomer selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(PolicyCustomer record);

    int updateByPrimaryKey(PolicyCustomer record);
    
    int deleteByOrderId(Long orderId);
  
}