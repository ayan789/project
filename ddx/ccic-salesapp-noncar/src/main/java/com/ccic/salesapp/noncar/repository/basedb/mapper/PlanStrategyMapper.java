package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;
@Mapper
public interface PlanStrategyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlanStrategy record);

    int insertSelective(PlanStrategy record);

    PlanStrategy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlanStrategy record);

    int updateByPrimaryKey(PlanStrategy record);
    
    List<StoreProduct> planStrategyList(ProductListRequest request);
    
    List<com.ccic.salesapp.noncar.feign.bean.ImageCheckResponse.ImageNodes> selectMustBeUploadImageTypesByStrategyId(Long strategyId);
    
    List<Long> selectPlanIdsByStrategyIdUserCode(Map map);
    
    PlanStrategy selectByStrategyCode(String code);
}