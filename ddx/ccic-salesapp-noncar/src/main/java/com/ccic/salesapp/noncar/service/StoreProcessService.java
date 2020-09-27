package com.ccic.salesapp.noncar.service;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.StoreInfo;
import com.ccic.salesapp.noncar.dto.StoreProduct;
import com.ccic.salesapp.noncar.dto.UserVO;


@Service
public interface StoreProcessService {

	StoreInfo findStoreByCode(String storeCode);

	StoreProduct findProductByProductCode(String proCode);

	UserVO getAgentHandlerInfo(String userCode);

	List<StoreProduct> getAllStoreProduct(Map<String,Object> map);

}
