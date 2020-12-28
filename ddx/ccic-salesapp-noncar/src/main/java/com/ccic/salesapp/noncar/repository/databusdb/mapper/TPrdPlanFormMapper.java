package com.ccic.salesapp.noncar.repository.databusdb.mapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.noncar.dto.request.Occupation;
import com.ccic.salesapp.noncar.dto.TreeDO;
import com.ccic.salesapp.noncar.dto.TreeDO2;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElement;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementGroup;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementOption;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanFieldDto;
import com.ccic.salesapp.noncar.dto.request.planelement.ApiMapping;
import com.ccic.salesapp.noncar.dto.request.planelement.BackReasoningFactor;
import com.ccic.salesapp.noncar.dto.request.planelement.BizVo;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblemContent;
import com.ccic.salesapp.noncar.dto.request.planelement.FixedInsureVo;
import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
import com.ccic.salesapp.noncar.dto.request.planelement.Header;
import com.ccic.salesapp.noncar.dto.request.planelement.ImgList;
import com.ccic.salesapp.noncar.dto.request.planelement.Insurance;
import com.ccic.salesapp.noncar.dto.request.planelement.InsuredDate;
import com.ccic.salesapp.noncar.dto.request.planelement.LinkElementCode;
import com.ccic.salesapp.noncar.dto.request.planelement.NumOfCopies;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanButton;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanElementDetailDto;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanTrialFlag;
import com.ccic.salesapp.noncar.dto.request.planelement.RateParams;
import com.ccic.salesapp.noncar.dto.request.planelement.Rates;
import com.ccic.salesapp.noncar.dto.request.planelement.Swipper;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditionsContent;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanForm;

@Component
@Mapper
public interface TPrdPlanFormMapper {
	int deleteByPrimaryKey(Long id);

    int insert(TPrdPlanForm record);

    int insertSelective(TPrdPlanForm record);

    TPrdPlanForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPrdPlanForm record);

    int updateByPrimaryKey(TPrdPlanForm record);
	
	List<PlanFieldDto> selectPlanField(HashMap<String, Object> map);
    
    List<PlanElementGroup> selectPlanElementGroup(HashMap<String, Object> map);

    List<PlanElement> selectPlanElement(HashMap<String, Object> map);

    List<PlanElementOption> selectPlanElementOption(HashMap<String, Object> map);
    
    List<CommonProblemContent> selectCommonProblemContent(HashMap<String, Object> map);
    
    List<CommonProblemContent> selectCommonProblemContentCarXy(HashMap<String, Object> map);
    
    PlanElementDetailDto selectPlanStrategy(HashMap<String, Object> map);
    
    Header selectPlanStrategyHeader(HashMap<String, Object> map);
    
    List<Swipper> selectHeaderSwipper(HashMap<String, Object> map);
    
    List<ImgList> selectPlanStrategyAttach(HashMap<String, Object> map);
    
    List<ImgList> selectPlanStrategyAttachCarXY(HashMap<String, Object> map);
    
    List<ImgList> selectPlanAttachealth(HashMap<String, Object> map);
    
    List<FormulaList> selectPlanInfoFormulas(HashMap<String, Object> map);
    
    List<TermsAndConditionsContent> selectConditionsContent(HashMap<String, Object> map);
    
    List<TermsAndConditionsContent> selectConditionsContentCarXY(HashMap<String, Object> map);
    
    List<TermsAndConditionsContent> selectConditionsContentCarXY2(HashMap<String, Object> map);
    
    List<RateParams> selectPlanRate(HashMap<String, Object> map);
    
    PlanButton selectButton(HashMap<String, Object> map);
    
    List<Insurance> selectPlanCt(HashMap<String, Object> map);
    
    BizVo selectPlanCustomElement(HashMap<String, Object> map);
    
    List<BizVo> selectPlanElementValue(HashMap<String, Object> map);
    
    ApiMapping selectPlanElementApiMapping(HashMap<String, Object> map);
    
    ApiMapping selectPlanHiddenElementApiMapping(HashMap<String, Object> map);
    
    List<LinkElementCode> selectPlanCodeLink(HashMap<String, Object> map);
    
    List<BackReasoningFactor> selectPlanBackReasoningFactor1(HashMap<String, Object> map);
    
    List<BackReasoningFactor> selectPlanBackReasoningFactor2(HashMap<String, Object> map);
    
    String selectPlanRateCode(HashMap<String, Object> map);
    
    List<Rates> selectPlanRateMapping(HashMap<String, Object> map);
    
    List<Rates> selectPlanRateMapping2(HashMap<String, Object> map);
    
    PlanTrialFlag selectPlanTrialFlag(HashMap<String, Object> map);
    
    List<Insurance> list2(Map<String,Object> map);
    
    int selectPlanRateSelected(Map<String,Object> map);
   
    int selectPlanRateRequired(Map<String,Object> map);
    
    List<PlanElementOption> selectAgesOptions(HashMap<String, Object> map);
    
    List<PlanElementOption> selectPeriodOptions(HashMap<String, Object> map);
    
    List<PlanElementOption> selectPeriodSeats(HashMap<String, Object> map);
    
    String selectDefaultAge(Map<String,Object> map);
    
    List<HashMap> selectDefaultRateAge(Map<String,Object> map);
    
    String selectDefaultPeriod(Map<String,Object> map);
    
    List<HashMap> selectDefaultRatePeriod(Map<String,Object> map);
    
    List<HashMap>  selectDefaultRateSeat(Map<String,Object> map);
    
    List<Long> selectPlanRequiredPlanCoverage(HashMap<String, Object> map);
    
    String selectDefaultSeats(Map<String,Object> map);
    
    NumOfCopies selectDefaultNumOfCopies(Map<String,Object> map);
    
    String selectMinPrem1(Map<String,Object> map);
    
    String selectMinPrem2(Map<String,Object> map);
    
    void updateElementMinAge(Map<String,Object> map);
    
    InsuredDate selectInsuredDate(Map<String,Object> map);
    
    InsuredDate selectInsuredDate2(Map<String,Object> map);
    
    List<HashMap<String, Object>> selectPlanRatePrem(HashMap<String, Object> map);
    
    HashMap<String, Object> selectPlanRateRange(HashMap<String, Object> map);
    
    Double selectPlanRateFee(HashMap<String, Object> map);
    
    FixedInsureVo selectPlanInsureFixed(HashMap<String, Object> map);
    
   int selectRateByPlanCoverage(HashMap<String, Object> map);
   
   Double selectPremiumByPlanCoverage(HashMap<String, Object> map);
   
   List<PlanElement> selectElementHidden(HashMap<String, Object> map);
   
   HashMap<String, Object> selectPolicyStartRange(HashMap<String, Object> map);
   
   String selectOrderInvoiceFlag(Long orderId);
   
   List<TreeDO> listTreeDO();
   
   List<TreeDO2> listTreeDO2();

   int deleteByPlanFormId(Long delPlanDefId);
   
   int insertCategory(Occupation category);
   
   int selectTrial(HashMap<String, Object> map);
   
   int selectOrderByPlanId(HashMap<String, Object> map);
   
   List<Swipper> selectSwiperCarXY(HashMap<String, Object> map);
    
   String selectStaffOnlyFlag(String insureNo);
}