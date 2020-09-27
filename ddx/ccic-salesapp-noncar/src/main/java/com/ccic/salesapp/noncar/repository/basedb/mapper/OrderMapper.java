package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.order.Customer;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
@Mapper
public interface OrderMapper {

	int deleteByPrimaryKey(Long id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

    long isBillAfterPay(Map map);
    
    Order selectByOrderNo(String orderNo);
    
    List<StoreInsureInfo> orderList(StoreQueryListRequestVO request);
    
    Order selectByInsureNo(HashMap<String, Object> map);
    
    List<Order> selectByRelationOrderId(Long id);
    
    List<Customer> selectByOrderId(Map<String,Object> map);
    
    List<HashMap<String,Object>> selectByOrderIdPaymentNoApply(Long id);

	String selectDataTagByOrderId(Long id);
	
	HashMap<String,BigDecimal> selectSumPremiumByOrderId(Long id);
	
	List<HashMap<String,BigDecimal>> selectPremiumByOrderId(Long id);
	
	int updateByRelationOrderId(HashMap<String, Object> map);
    
	int updateByInsureNo(HashMap<String, Object> map);
	
	List<HashMap<String, Object>> selectPolicyList(HashMap<String, Object> map);
	
	int selectPolicyListCount(HashMap<String, Object> map);
	
	int selectSaleNum(HashMap<String, Object> map);
	
//	HashMap<String, Object> selectPolicyList(HashMap<String, Object> map);
	
    
}