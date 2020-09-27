package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.StoreProductMedia;




@Mapper
public interface StoreProductMapper {

	StoreProduct getProduct(String productCode);

	List<StoreProduct> getAll(Map<String,Object> paramMap);
	/**
	 * 是否见费出单 1 ：见费出单 0：非见费出单
	 * @param paramMap
	 * @return
	 */
   Map isIssueAfterPay(Map<String,Object> paramMap);

	//根据产品代码获取产品素材信息
	List<StoreProductMedia>getMedia(String productCode);
	
}