package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.request.StoreConfig;
import com.ccic.salesapp.noncar.dto.request.news.NewsVo;
import com.ccic.salesapp.noncar.dto.request.news.SupportOrgBranchResponse;
import com.ccic.salesapp.noncar.dto.request.news.SupportOrgBranchVO;

@Mapper
public interface StoreMapper {

	void doDelete(Map<String, String> pr);

	void doInsert(StoreConfig sc);

	StoreInfo findByStoreId(String storeCode);

	void doTUpdate(Map map);

	StoreConfig findByStoreAndProductCode(String storeCode, String productCode);

	void updateStoreConfigStatus(Map map);
	
	Integer countAccess(Map map);

	StoreConfig findByStoreMinValue(String storeCode);
	
	//查询门店已添加资讯
	List<HashMap<String, Object>> findByStoreNewsList(NewsVo req);

	//删除门店资讯
	int delByStoreNews(Integer contentId);

	//修改门店资讯
	int updateByStoreNews(HashMap<String, Object> map);
	
	//添加门店资讯
	int insertByStoreNews(HashMap<String, Object> map);
	
	//查询门店未添加资讯
	List<HashMap<String, Object>> storeNewsList(NewsVo req);
	
	List<SupportOrgBranchResponse> queryOrgBranchs(SupportOrgBranchVO supportOrgBranchVO);
}
