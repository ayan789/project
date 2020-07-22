package com.example.tlyanprd;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.tlyanprd.planelement.ApiMapping;
import com.example.tlyanprd.planelement.BizVo;
import com.example.tlyanprd.planelement.CommonProblemContent;
import com.example.tlyanprd.planelement.FormulaList;
import com.example.tlyanprd.planelement.Header;
import com.example.tlyanprd.planelement.ImgList;
import com.example.tlyanprd.planelement.Insurance;
import com.example.tlyanprd.planelement.LinkElementCode;
import com.example.tlyanprd.planelement.PlanButton;
import com.example.tlyanprd.planelement.PlanElementDetailDto;
import com.example.tlyanprd.planelement.RateParams;
import com.example.tlyanprd.planelement.Rates;
import com.example.tlyanprd.planelement.Swipper;
import com.example.tlyanprd.planelement.TermsAndConditionsContent;

@Component
@Mapper
public interface TPrdPlanFormMapper {
	
List<PlanFieldDto> selectPlanField(HashMap<String, Object> map);
    
    List<PlanElementGroup> selectPlanElementGroup(HashMap<String, Object> map);

    List<PlanElement> selectPlanElement(HashMap<String, Object> map);

    List<PlanElementOption> selectPlanElementOption(HashMap<String, Object> map);
    
    List<CommonProblemContent> selectCommonProblemContent(HashMap<String, Object> map);
    
    PlanElementDetailDto selectPlanStrategy(HashMap<String, Object> map);
    
    Header selectPlanStrategyHeader(HashMap<String, Object> map);
    
    List<Swipper> selectHeaderSwipper(HashMap<String, Object> map);
    
    List<ImgList> selectPlanStrategyAttach(HashMap<String, Object> map);
    
    List<ImgList> selectPlanAttachealth(HashMap<String, Object> map);
    
    List<FormulaList> selectPlanInfoFormulas(HashMap<String, Object> map);
    
    List<TermsAndConditionsContent> selectConditionsContent(HashMap<String, Object> map);
    
    List<RateParams> selectPlanRate(HashMap<String, Object> map);
    
    PlanButton selectButton(HashMap<String, Object> map);
    
    List<Insurance> selectPlanCt(HashMap<String, Object> map);
    
    BizVo selectPlanCustomElement(HashMap<String, Object> map);
    
    List<BizVo> selectPlanElementValue(HashMap<String, Object> map);
    
    ApiMapping selectPlanElementApiMapping(HashMap<String, Object> map);
    
    List<LinkElementCode> selectPlanCodeLink(HashMap<String, Object> map);
    
    PlanBackReasoningFactor selectPlanBackReasoningFactor(HashMap<String, Object> map);
    
    String selectPlanRateCode(HashMap<String, Object> map);
    
    List<Rates> selectPlanRateMapping(HashMap<String, Object> map);
    
    PlanTrialFlag selectPlanTrialFlag(HashMap<String, Object> map);
    
    List<HashMap<String, Object>> selectPlanRatePrem(HashMap<String, Object> map);
    
//    HashMap<String, Object> selectPlanRateRange(HashMap<String, Object> map);
    
    Double selectPlanRateFee(HashMap<String, Object> map);
    
    
}