package com.ccic.salesapp.noncar.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.AgentHandlerInfo;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.AgentHandlerInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreProductMapper;
import com.ccic.salesapp.noncar.service.StoreProcessService;
import com.ccic.salesapp.noncar.service.UserInquiryService;
import com.ccic.salessapp.common.core.web.HttpResult;
import lombok.extern.slf4j.Slf4j;




@Service
@Slf4j
public class StoreProcessServiceImpl implements StoreProcessService {

	@Autowired
	StoreInfoMapper storeInfoMapper;

	@Autowired
	StoreProductMapper storeProductMapper;

	@Autowired
	AgentHandlerInfoMapper agentHandlerInfoMapper;

	@Autowired
	UserInquiryService userInquiryService;

	@Override
	public StoreInfo findStoreByCode(String storeCode) {
		StoreInfo sto=null;
		try {
			sto= storeInfoMapper.findStoreByCode(storeCode);
			//如果是总公司地区 机构代码修改为：31011504
			if(null!=sto){
				String comCode=sto.getUserComCode();
				if("3100".equals(comCode.substring(0,4)) ||
						"3103".equals(comCode.substring(0,4)) ||
						"3112".equals(comCode.substring(0,4)) ){
					sto.setUserComCode("31011504");

				}
			}
		} catch (Exception e) {
			log.error("获取用户门店信息异常",e);
		}
		return sto;
	}

	@Override
	public StoreProduct findProductByProductCode(String productCode) {
		StoreProduct storeProduct = null;
		try {
			storeProduct = storeProductMapper.getProduct(productCode);
		} catch (Exception e) {
			log.error("",e);
		}
		return storeProduct;
	}

	@Override
	public UserVO getAgentHandlerInfo(String agentUserCode) {
		UserVO u = null;
		try {
			AgentHandlerInfo ah = agentHandlerInfoMapper.selectAgentHandlerInfoByUserCode(agentUserCode);
			HttpResult<?> result = userInquiryService.userInquiry(ah.getHandlerCode());
			if("1".equals(result.getCode())){
				u =(UserVO) result.getData();
			}
		} catch (Exception e) {
			log.error("",e);
		}
		return u;
	}

	@Override
	public List<StoreProduct> getAllStoreProduct(Map<String,Object> map) {

		List<StoreProduct> list=null;

		try{ 
			list = storeProductMapper.getAll(map);
		}catch(Exception e){
			log.error("数据库查t_noncar_product表异常:！："+e);
		}
		return list;
	}
}