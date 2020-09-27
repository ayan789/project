package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.salesplan.GroupPlanVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlan;
import com.ccic.salesapp.noncar.repository.basedb.po.SalesPlan;

@Mapper
public interface SalesPlanMapper {
    int deleteByPrimaryKey(Integer salesPlanId);

    int insert(SalesPlan record);
    
    int insert2(GroupSalesPlan record);

    int insertSelective(SalesPlan record);

    SalesPlan selectByPrimaryKey(Integer salesPlanId);

    int updateByPrimaryKeySelective(SalesPlan record);

    int updateByPrimaryKey(SalesPlan record);
    
    List<GroupSalesPlan> selectSalesPlanBygroupPlanCode(GroupPlanVo groupPlanVo);
    
    List<HashMap<String,Object>> selectGroupPlanByComChannel(HashMap<String,Object> map);
    
    void deleteBySalesPlan(HashMap<String,Object> map);
    
    void deleteBySalesPlanChild(HashMap<String,Object> map);
    
    void deleteBySalesCoverage(HashMap<String,Object> map);
    
    void deleteBySalesPlanConfig(HashMap<String,Object> map);
}