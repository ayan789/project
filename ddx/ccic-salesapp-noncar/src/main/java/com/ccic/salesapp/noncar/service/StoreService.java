package com.ccic.salesapp.noncar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.request.MyStoreRequest;
import com.ccic.salesapp.noncar.dto.request.news.NewsVo;
import com.ccic.salesapp.noncar.dto.request.news.SupportOrgBranchVO;
import com.ccic.salesapp.noncar.dto.store.request.ContentRequest;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface StoreService {

	/**
	 * 更新门店信息
	 * 
	 * @param store
	 * @return
	 */
	public boolean updateStore(StoreInfo sto);

	/**
	 * 给门店添加商品
	 * 更新门店配置产品顺序
	 * @param storeCode
	 * @param productCode
	 * @return
	 */
	public HttpResult addSotreProduct(String storeCode, List<Map<String, String>> list);

	/**
	 * 删除门店产品
	 * 
	 * @param storeCode
	 * @param productCodes
	 * @return
	 */
	public HttpResult delSotreProduct(String storeCode, List<String> productCodes);
	
	//查询门店已添加资讯
	public List<HashMap<String, Object>> findByStoreNewsList(NewsVo req);
	
	//删除门店资讯
	public int delByStoreNews(Integer contentId);
	
	//修改门店资讯
	public int updateByStoreNews(HashMap<String, Object> map);
	
	//添加门店资讯
	public int insertByStoreNews(HashMap<String, Object> map);
	
	//查询门店未添加资讯
	List<HashMap<String, Object>> storeNewsList(NewsVo req);
	
	Map<String, String> queryOrgBranchUp(SupportOrgBranchVO supportOrgBranchVO);
	
	//新闻配置
	HttpResult<?> newsSet(NewsVo reqvo);
	
	//门店资讯列表
	HttpResult<?> contentList(ContentRequest request);
	
	HttpResult<?> myStoreHomeSet(MyStoreRequest reqvo);
}
