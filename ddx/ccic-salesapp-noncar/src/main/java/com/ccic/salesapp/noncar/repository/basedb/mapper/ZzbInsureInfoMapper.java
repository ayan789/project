package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.dto.ZzbInsureInfo;


@Mapper
public interface ZzbInsureInfoMapper {
	
	
	ZzbInsureInfo selectByInsuredInfoId(Map<String, Object> param);
	
	
}