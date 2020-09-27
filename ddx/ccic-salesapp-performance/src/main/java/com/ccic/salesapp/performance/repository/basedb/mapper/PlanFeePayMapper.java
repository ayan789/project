package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.FeeQueryVO;
import com.ccic.salesapp.performance.domain.vo.dto.PackNoVO;
import com.ccic.salesapp.performance.domain.vo.dto.PlanFeePay;
import com.ccic.salesapp.performance.domain.vo.request.FeeVo;

import java.util.List;
import java.util.Map;


/**
 * 手续费
 */
@Mapper
public interface PlanFeePayMapper {

    Double sumPlanFee(Map<String, String> p);

    FeeQueryVO queryBillFeeByUserCode(Map<String, String> paraMap);

    FeeQueryVO queryBillFee(Map<String, String> paraMap);

    List<PlanFeePay> listByAgentCode(PlanFeePay planFeePay);
    
    List<PlanFeePay> listByAgentCode(FeeVo feeVo);

    void toUpdate(PlanFeePay planFeePay);

    List<PackNoVO> queryPackNo(Map<String, Object> paramMap);
    
    int queryPackNoNum(Map<String, String> paramMap);

    List<PlanFeePay> queryPolicy(Map<String, Object> paramMap);
    
    int queryPolicyNum(Map<String, String> paramMap);
    
    int updatePlanFee(PlanFeePay planFeePay);
    
    double agentFee(FeeVo feeVo);
}
