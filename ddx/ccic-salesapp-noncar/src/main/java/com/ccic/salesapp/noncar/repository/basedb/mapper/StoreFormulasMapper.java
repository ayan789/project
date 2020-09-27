package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;


@Mapper
public interface StoreFormulasMapper {

	List<StoreFormulas> toList(Map<String, String> paramMap);

	List<StoreFormulas> searchByProductCode(StoreFormulas storeFormulas);

	List<StoreFormulas> queryFormulasInfoLBU(String formulaCode);
	
	List<StoreFormulas> queryFormulasInfo(Map<String,String> paramMap);

}
