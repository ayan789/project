package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCoInsurer;


@Mapper
public interface TPrdPlanCoInsurerMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanCoInsurer record);

    int insertSelective(TPrdPlanCoInsurer record);

    TPrdPlanCoInsurer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanCoInsurer record);

    int updateByPrimaryKey(TPrdPlanCoInsurer record);
}