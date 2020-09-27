package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ccic.salesapp.performance.domain.vo.dto.PlanFeeDetailVO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 保险代理人dao
 */
@Mapper
public interface InsuranceMapper {
     List<PlanFeeDetailVO> planFeeDetailInquiry(@Param("agentCode") String agentCode, @Param("handlerCode") String handlerCode,@Param("offset") int offset,@Param("limit") int limit);

    int planFeeDetailInquiryNum(@Param("agentCode") String agentCode, @Param("handlerCode") String handlerCode);

    BigDecimal pendingEffectFeeQuery(Map<String, String> paraMap);
}
