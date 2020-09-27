package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.dto.request.CarInfoVO;

@Mapper
public interface CarInfoMapper {

	void insert(CarInfoVO carInfoVO);
	
	CarInfoVO queryCarInfo(BigDecimal id);

}
