package com.ccic.salesapp.performance.domain.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.performance.domain.vo.response.AssessResponse;





@Mapper
public interface AssessMapper {

	//销售行动力评估
	AssessResponse getAssessItemsByUserCode(String userCode);
}
