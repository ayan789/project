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
    
    int insertGroupSalesPlan(GroupSalesPlan record);

    int insertSelective(SalesPlan record);

    SalesPlan selectByPrimaryKey(Integer salesPlanId);

    int updateByPrimaryKeySelective(SalesPlan record);

    int updateByPrimaryKey(SalesPlan record);
    
    List<GroupSalesPlan> selectSalesPlanBygroupPlanCode(GroupPlanVo groupPlanVo);
    
    List<GroupSalesPlan> selectSalesPlanBygroupPlanCodeAllChannel(GroupPlanVo groupPlanVo);
    
    List<GroupSalesPlan> selectSalesPlanByGroupCodeForCarXY(GroupPlanVo groupPlanVo);
    
    List<GroupSalesPlan> selectSalesPlanByGroupCodeAllChannelForCarXY(GroupPlanVo groupPlanVo);
    
    List<HashMap<String,Object>> selectGroupPlanByComChannel(HashMap<String,Object> map);
    
    List<HashMap<String,Object>> selectRenewalCoverageMapping(HashMap<String,Object> map);
    
    void deleteBySalesPlan(HashMap<String,Object> map);
    
    void deleteBySalesPlanChild(HashMap<String,Object> map);
    
    void deleteBySalesCoverage(HashMap<String,Object> map);
    
    void deleteBySalesPlanConfig(HashMap<String,Object> map);
    
    void deleteBySalesPlanCarxy(HashMap<String,Object> map);
    
    void deleteBySalesPlanChildCarxy(HashMap<String,Object> map);
    
    void deleteBySalesCoverageCarxy(HashMap<String,Object> map);
    
    void deleteBySalesPlanConfigCarxy(HashMap<String,Object> map);
    
    String selectChannelBygroupCode(String groupCode);
    
    String selectChannelBygroupPlanCode(String groupPlanCode);
}