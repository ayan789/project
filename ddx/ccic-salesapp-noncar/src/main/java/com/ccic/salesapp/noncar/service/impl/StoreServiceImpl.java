package com.ccic.salesapp.noncar.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ccic.salesapp.noncar.config.ConfigConst;
import com.ccic.salesapp.noncar.controller.StoreCtl;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.product.request.ProductListRequest;
import com.ccic.salesapp.noncar.dto.request.AbstractFacade;
import com.ccic.salesapp.noncar.dto.request.MyStoreRequest;
import com.ccic.salesapp.noncar.dto.request.StoreConfig;
import com.ccic.salesapp.noncar.dto.request.news.AddArray;
import com.ccic.salesapp.noncar.dto.request.news.NewsVo;
import com.ccic.salesapp.noncar.dto.request.news.SupportOrgBranchResponse;
import com.ccic.salesapp.noncar.dto.request.news.SupportOrgBranchVO;
import com.ccic.salesapp.noncar.dto.store.request.ContentRequest;
import com.ccic.salesapp.noncar.dto.store.response.ContentResponse;
import com.ccic.salesapp.noncar.dto.store.response.NewsResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.ContentMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.Content;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.StoreService;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StoreServiceImpl extends AbstractFacade implements StoreService {

	@Autowired
	StoreMapper storeMapper;
	
	@Autowired
	ContentMapper contentMapper;
	
	@Autowired
	StoreProcessService storeProcessService;
	
	@Autowired
	StoreCtl storeCtl;

	@Override
	public boolean updateStore(StoreInfo sto) {
		try {
			Map map = new HashMap();
			map.put("storeName", sto.getStoreName());
			map.put("storeRegionCode", sto.getStoreRegionCode());
			map.put("userComCode", sto.getUserComCode());
			map.put("storeCode", sto.getStoreCode());
			storeMapper.doTUpdate(map);// 更新门店信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public HttpResult addSotreProduct(String storeCode, List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			for (String key : map.keySet()) {
				StoreConfig sc = new StoreConfig();
				sc.setProductCode(key);
				String values = map.get(key);
				int value = 0;
				if (values != null && !values.isEmpty()) {
					if ("-1".equals(values)) {
						StoreConfig minSoft = storeMapper.findByStoreMinValue(storeCode);
						if(minSoft == null) {
							sc.setConfigSoft(-1);
						}else {
							Integer configSoft = minSoft.getConfigSoft();
							value = Integer.parseInt(values)+(configSoft);
							sc.setConfigSoft(value);
						}
					} else {
						value = Integer.parseInt(values);
						sc.setConfigSoft(value);
					}
				}
				sc.setConfigState("0");
				sc.setStoreCode(storeCode);
				StoreInfo scc = storeMapper.findByStoreId(storeCode);
				sc.setStoreId(scc.getId());
				if (StringUtils.isNotBlank(storeCode) && StringUtils.isNotBlank(sc.getProductCode())) {
					StoreConfig sconfig = storeMapper.findByStoreAndProductCode(storeCode, sc.getProductCode());
					if (sconfig != null) {
						Map mp = new HashMap();
						mp.put("storeCode", storeCode);
						mp.put("productCode", sc.getProductCode());
						mp.put("configSoft", sc.getConfigSoft());
						storeMapper.updateStoreConfigStatus(mp);
					} else {
						try {
							storeMapper.doInsert(sc);
						} catch (Exception e) {
							return HttpResult.error(0, "插入更新失败！");
						}
					}
				}
			}
		}
		return HttpResult.success(1,"插入更新成功。");
	}

	@Override
	public HttpResult delSotreProduct(String storeCode, List<String> productCodes) {
		if (productCodes.size() < 1)return HttpResult.error(0, "productCode为空。");
		Map<String, String> pr = new HashMap<String, String>();
		pr.put("storeCode", storeCode);
		for (int i = 0; i < productCodes.size(); i++) {
			pr.put("proCode", productCodes.get(i));
			StoreConfig findByStoreAndProductCode = storeMapper.findByStoreAndProductCode(storeCode, productCodes.get(i));
			try {
				if(findByStoreAndProductCode == null) {
					StoreConfig sc = new StoreConfig();
					sc.setProductCode(productCodes.get(i));
					sc.setConfigState("1");
					sc.setStoreCode(storeCode);
					StoreInfo findByStoreId = storeMapper.findByStoreId(storeCode);
					sc.setStoreId(findByStoreId.getId());
					storeMapper.doInsert(sc);
				}else {
					storeMapper.doDelete(pr);
				}
			} catch (Exception e) {
				return HttpResult.success(0,"删除失败。");
			}
		}
		return HttpResult.success(1,"删除成功。");
	}

	@Override
	public List<HashMap<String, Object>> findByStoreNewsList(NewsVo req) {
		return storeMapper.findByStoreNewsList(req);
	}
	
	@Override
	public int delByStoreNews(Integer contentId) {
		return storeMapper.delByStoreNews(contentId);
	}

	@Override
	public int updateByStoreNews(HashMap<String, Object> map) {
		return storeMapper.updateByStoreNews(map);
	}

	@Override
	public int insertByStoreNews(HashMap<String, Object> map) {
		return storeMapper.insertByStoreNews(map);
	}

	@Override
	public List<HashMap<String, Object>> storeNewsList(NewsVo req) {
		return storeMapper.storeNewsList(req);
	}

	@Override
	public Map<String, String> queryOrgBranchUp(SupportOrgBranchVO supportOrgBranchVO) {
		 List<String> upBranchs = Lists.newArrayList();
	        upBranchs.add("1");
	        supportOrgBranchVO.setUpBranchs(upBranchs);
	        List<SupportOrgBranchResponse> supportOrgBranchResponses = storeMapper.queryOrgBranchs(supportOrgBranchVO);
	        if (CollectionUtils.isEmpty(supportOrgBranchResponses)) {
	            log.error("【当前账号分公司,首页未配置，请联系业务人员后台配置分支机构与分公司映射没有，请配置】");
	            // throw new PlatformBaseException("【分支机构-分公司关系未维护，请联系系统业务人员跑批配置维护】", 144);
	            return null;
	        }
	        //去重
//	        List<SupportOrgBranchResponse> unique = supportOrgBranchResponses.stream().collect(
//	                Collectors.collectingAndThen(
//	                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SupportOrgBranchResponse::getUpOrgCode))), ArrayList::new));
	        // 过滤获取分公司
//	        List<SupportOrgBranchResponse> orgFilterList = supportOrgBranchResponses.stream().filter(p -> p.getUpBranch().equals("1")).collect(Collectors.toList());
//	        if (CollectionUtils.isEmpty(orgFilterList)) {
//	            // 分公司没配置，获取总公司
//	            orgFilterList = supportOrgBranchResponses.stream().filter(p -> p.getUpBranch().equals("2")).collect(Collectors.toList());
	//
//	            if (CollectionUtils.isEmpty(orgFilterList)) {
//	                log.error("【分支机构与分公司映射没有，请配置】");
//	                throw new PlatformBaseException("【分支机构与分公司映射没有，请配置】", 144);
//	            }
//	        }
	        Map<String, String> orgMap = supportOrgBranchResponses.stream().
	                collect(Collectors.toMap(SupportOrgBranchResponse::getUpOrgCode, SupportOrgBranchResponse::getUpBranch, (k1, k2) -> k1));
	        // List<String> orgCodes = supportOrgBranchResponses.stream().map(SupportOrgBranchResponse::getUpOrgCode).collect(Collectors.toList());
	        if (CollectionUtils.isEmpty(orgMap)) {
	        	log.error("【分支机构-分公司关系未维护，请联系系统业务人员跑批配置维护】");
	        }
	        return orgMap;
	}

	@Override
	public HttpResult<?> newsSet(NewsVo reqvo) {
		List<Integer> getDelArray = null;
		List<AddArray> addArrayList = null;
		// 判断门店代码不为空
		if (StringUtils.isEmpty(reqvo.getStoreCode())) {
			return HttpResult.error(0, "门店代码为空！");
		}
		if (!CollectionUtils.isEmpty(reqvo.getAddArray()) && reqvo.getAddArray().get(0).getSoft()>=0) {
			//更新新闻
			addArrayList = reqvo.getAddArray();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("storeCode", reqvo.getStoreCode()+"");
			for(AddArray addArray : addArrayList) {
				map.put("contentId",addArray.getContentId());
				map.put("soft",addArray.getSoft());
				updateByStoreNews(map);
			}
		}
		else if (!CollectionUtils.isEmpty(reqvo.getAddArray()) && reqvo.getAddArray().get(0).getSoft()<0) {
			//添加资讯
			addArrayList = reqvo.getAddArray();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("storeCode", reqvo.getStoreCode()+"");
			int step = 0;
			//现有资讯顺序重置
			NewsVo newsVo = new NewsVo();
			newsVo.setPage(ConfigConst.PageInit);
			newsVo.setSize(ConfigConst.PageLong);
			newsVo.setStoreCode(reqvo.getStoreCode());
			PageHelper.startPage(newsVo.getPage(), newsVo.getSize(),true);
			List<HashMap<String, Object>> storeNewsList = findByStoreNewsList(newsVo);
			for(int i = 0 ; i < storeNewsList.size(); i++) {
				map.put("contentId",storeNewsList.get(i).get("contentId"));
				map.put("soft",step+i+1);
				updateByStoreNews(map);
			}
			//反转添加资讯列表
			Collections.reverse(addArrayList);
			for(int i = 0 ; i < addArrayList.size(); i++) {
				map.put("contentId",addArrayList.get(i).getContentId());
				step = addArrayList.get(i).getSoft()-i;
				map.put("soft",step);
				insertByStoreNews(map);
			}
		}
		else if (reqvo.getDelArray()!=null && reqvo.getDelArray().size()>0) {
			//删除资讯
			getDelArray = reqvo.getDelArray();
			for(Integer contentId : getDelArray) {
				delByStoreNews(contentId);
			}
		}
		if("1".equals(reqvo.getIsInStore())) {
			NewsResponse response = new NewsResponse();
			PageHelper.startPage(reqvo.getPage(), reqvo.getSize(),true);
			//查询资讯列表【门店下】
			List<HashMap<String, Object>> storeNewsList = findByStoreNewsList(reqvo);
			PageInfo pageInfo = new PageInfo(storeNewsList);
			pageInfo.setList(null);
			response.setPageInfo(pageInfo);
			response.setStoreNewsList(storeNewsList);
			return HttpResult.success(response,1,"门店下资讯查询成功");
		}
		else {
			NewsResponse response = new NewsResponse();
			PageHelper.startPage(reqvo.getPage(), reqvo.getSize(),true);
			//查询资讯列表【ALL】
			// 当前账号分支机构的分公司或总公司
			SupportOrgBranchVO supportOrgBranchVO = new SupportOrgBranchVO();
			supportOrgBranchVO.setOrgCode(reqvo.getComCode());
	        Map<String, String> orgMap = queryOrgBranchUp(supportOrgBranchVO);
	        // 设置分公司或总公司(发现)
	        List<String> orgCodes = Lists.newArrayList();
	        if (!CollectionUtils.isEmpty(orgMap)) {
	            orgCodes = orgMap.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toList());
	        }
	        orgCodes.add("all");
	        reqvo.setOrgCodes(orgCodes);
			List<HashMap<String, Object>> storeNewsList = storeNewsList(reqvo);
			PageInfo pageInfo = new PageInfo(storeNewsList);
			pageInfo.setList(null);
			response.setPageInfo(pageInfo);
			response.setStoreNewsList(storeNewsList);
			return HttpResult.success(response,1,"所有资讯查询成功");
		}
	}

	@Override
	public HttpResult<?> contentList(ContentRequest request) {
		// 判断门店代码不为空
		if (StringUtils.isEmpty(request.getStoreCode())) {
			throw new PlatformBaseException("门店代码不能为空", 0);
		}
		ContentResponse response = new ContentResponse();
		PageHelper.startPage(request.getPage(), request.getSize(),true);
		List<Content> list = contentMapper.selectContentList(request);
		PageInfo pageInfo = new PageInfo(list);
		pageInfo.setList(null);
		response.setPageInfo(pageInfo);
		response.setContentList(list);
		return HttpResult.success(response);
	}

	@Override
	public HttpResult<?> myStoreHomeSet(MyStoreRequest reqvo) {
		// 判断门店代码不为空
		if (StringUtils.isEmpty(reqvo.getStoreCode())) {
			return HttpResult.error(0, "门店代码为空！");
		}
		// 查询门店信息
		StoreInfo sto = storeProcessService.findStoreByCode(reqvo.getStoreCode());
		try {
			if (sto != null) {
				updateStore(sto);// 更新门店
			}
			if (reqvo.getDelArray().size() != 0) {
				delSotreProduct(reqvo.getStoreCode(), reqvo.getDelArray());// 删除产品
			}
			if (reqvo.getAddArray().get(0).size() != 0) {
				addSotreProduct(reqvo.getStoreCode(), reqvo.getAddArray());// 添加产品&排序
			}
		} catch (Exception e) {
			return HttpResult.error(0, e.getMessage());
		}
		// 调用产品列表查询
		ProductListRequest request = new ProductListRequest();
		request.setStoreCode(reqvo.getStoreCode());
		request.setIsInStore(reqvo.getIsInStore());
		request.setOrderBy(reqvo.getOrderBy());
		request.setIsQueryByPage(reqvo.getIsQueryByPage());
		request.setUserCode(reqvo.getUserCode());
		return storeCtl.productList(request);
	}

}
