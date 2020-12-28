package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.order.Clause;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;
@Mapper
public interface OrderCtMapper {

	int deleteByPrimaryKey(Long id);

	int insert(OrderCt record);

	int insertSelective(OrderCt record);

	OrderCt selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OrderCt record);

	int updateByPrimaryKey(OrderCt record);

    int deleteByOrderId(Long orderId);
    
    List<OrderCt> selectByOrderId(Long orderId);
    
    List<Clause> selectCtByOrder(Long orderId);
    
    int updateByOrderIdKindCode(OrderCt record);
    
    List<String> selectOrderCtGroupType(Map map);
    
    String selectCtName(Map map);
    
    OrderCt selectByOrderIdCoverageId(Map map);
    
}