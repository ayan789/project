package com.ccic.salesapp.performance.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.performance.domain.mapper.B2BBUSI_T_APP_S_PRPSPLANFEE_DAY_Mapper;
import com.ccic.salesapp.performance.domain.mapper.B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL_Mapper;
import com.ccic.salesapp.performance.domain.mapper.B2BBUSI_T_APP_S_SALELAW_PERSON_Mapper;
import com.ccic.salesapp.performance.domain.mapper.SPrpsplanfeeDayDao;
import com.ccic.salesapp.performance.domain.vo.dto.PrpsPlanFeeDay;
import com.ccic.salesapp.performance.domain.vo.dto.SaleLawPersonDetail;
import com.ccic.salesapp.performance.domain.vo.dto.SalelawPerson;
import com.ccic.salesapp.performance.domain.vo.request.IPerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.request.PerformanceQueryTypeRequest;
import com.ccic.salesapp.performance.domain.vo.request.PerformanceRequest;
import com.ccic.salesapp.performance.domain.vo.response.IPerformanceResponse;
import com.ccic.salesapp.performance.domain.vo.response.PerformanceResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.ArithUtil;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PerformanceInfoService {
    @Autowired
    B2BBUSI_T_APP_S_SALELAW_PERSON_Mapper b2BBUSITAppSSalelawPersonMapper;

    @Autowired
    B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL_Mapper b2BBUSITAppSSalelawPersondetailMapper;

    @Autowired
    B2BBUSI_T_APP_S_PRPSPLANFEE_DAY_Mapper b2BBUSITAppSPrpsplanfeeDayMapper;
    
    @Autowired
    SPrpsplanfeeDayDao SPrpsplanfeeDayMapper;
    
    public HttpResult<PerformanceResponse> getPerformance(PerformanceRequest request) {
    	PerformanceResponse response = new PerformanceResponse();
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("dateStart", request.getDateStart());
    	map.put("dateEnd", request.getDateEnd());
    	map.put("userCode", request.getUserCode());
    	response = SPrpsplanfeeDayMapper.selectByUserCodeDate(map);
    	if(response == null) {
    		response = new PerformanceResponse();
    	}
    	return HttpResult.success(response);
    }
    
    public HttpResult<PerformanceResponse> getTeamPerformance(PerformanceRequest request) {
    	PerformanceResponse response = new PerformanceResponse();
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("dateStart", request.getDateStart());
    	map.put("dateEnd", request.getDateEnd());
    	map.put("userCode", request.getUserCode());
    	response = SPrpsplanfeeDayMapper.selectTeamPerformanceByUserCodeDate(map);
    	if(response == null) {
    		response = new PerformanceResponse();
    	}
    	return HttpResult.success(response);
    }
    
    
    public HttpResult<PerformanceResponse> getPerformanceByQueryType(PerformanceQueryTypeRequest request) {
    	PerformanceResponse result = SPrpsplanfeeDayMapper.getPerformanceByQueryType(request);
    	if(result == null) {
    		result = new PerformanceResponse();
    	}
    	
    	return HttpResult.success(result);
    }

    public HttpResult getPerformanceInfo(IPerformanceRequest request,String userCode) {
        //-----------------这块要看看怎么获取工号
        //String userCode = vo.getHead().getLoginUser().getUserCode();//业务员工号



        //DAOBase dao = new DefaultDAO();
        /*JsonVO jsonVO = new JsonVO();
        ResultVO rlt = new ResultVO();*/
        //IPerformanceRequestVO requestVO = (IPerformanceRequestVO) vo.getData();
        IPerformanceResponse responseVO = new IPerformanceResponse();
        Map<String, String> paramMap = new HashMap<String, String>();
        DecimalFormat dl = new DecimalFormat("###,##0.00");
        dl.setRoundingMode(RoundingMode.HALF_UP); // 设置HALF_UP四舍五入
        try {
            String searchFlag = request.getSearchFlag();//1-日业绩，2-周业绩，3-月业绩，4-年业绩，5-高级查询
            String searchMonth = request.getSearchMonth();
            String searchStartDate = request.getSearchStartDate();
            String searchEndDate = request.getSearchEndDate();

            //SalelawPerson salelawPerson = (SalelawPerson) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSON.selectByUserCode", userCode);
            SalelawPerson salelawPerson=b2BBUSITAppSSalelawPersonMapper.selectByUserCode(userCode);
            if (salelawPerson != null) {
                responseVO.setUserName(salelawPerson.getUsername());//用户名称
                responseVO.setComName(salelawPerson.getComcanme());//机构名称
                responseVO.setChannelName(salelawPerson.getChannelname());//渠道名称
                responseVO.setPersonTypeId(salelawPerson.getPersontypeid());//岗位代码
                responseVO.setRankName(salelawPerson.getRankname());//职级名称
                responseVO.setAbType(salelawPerson.getAbtype());//A/B模式
            }

            Double renewalRate = b2BBUSITAppSSalelawPersonMapper.selectRenewalRateByUserCode(userCode);
            //Double renewalRate = (Double) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSON.selectRenewalRateByUserCode", userCode);
            responseVO.setRenewalRate(renewalRate != null ? String.valueOf(ArithUtil.mul(renewalRate, 100)) : "");//续保率（%）

            paramMap.clear();
            paramMap.put("userCode", userCode);
            paramMap.put("type", "4");
            SaleLawPersonDetail saleLawPersonDetail=b2BBUSITAppSSalelawPersondetailMapper.selectByUserCode(paramMap);
            //SaleLawPersonDetail saleLawPersonDetail = (SaleLawPersonDetail) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL.selectByUserCode", paramMap);
            if (saleLawPersonDetail != null) {
                responseVO.setMonthPremiumPlan(saleLawPersonDetail.getMonthsumpremiumplan() != null ? String.valueOf(dl.format(ArithUtil.div(saleLawPersonDetail.getMonthsumpremiumplan().doubleValue(), 10000, 2))) : "0.00");//当月累计考核保费计划（万元）
            } else {
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "1");
                saleLawPersonDetail=b2BBUSITAppSSalelawPersondetailMapper.selectByUserCode(paramMap);
                //saleLawPersonDetail = (SaleLawPersonDetail) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL.selectByUserCode", paramMap);
                if (saleLawPersonDetail != null) {
                    responseVO.setMonthPremiumPlan(saleLawPersonDetail.getCurrentmonthpremiumplan() != null ? String.valueOf(dl.format(ArithUtil.div(saleLawPersonDetail.getCurrentmonthpremiumplan().doubleValue(), 10000, 2))) : "0.00");//当月累计考核保费计划（万元）
                } else {
                    responseVO.setMonthPremiumPlan("");
                }
            }
            paramMap.put("type", "2");
            saleLawPersonDetail=b2BBUSITAppSSalelawPersondetailMapper.selectByUserCode(paramMap);
            //saleLawPersonDetail = (SaleLawPersonDetail) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL.selectByUserCode", paramMap);
            if (saleLawPersonDetail != null) {
                responseVO.setYearPremiumPlan(saleLawPersonDetail.getYearsumpremiumplan() != null ? String.valueOf(dl.format(ArithUtil.div(saleLawPersonDetail.getYearsumpremiumplan().doubleValue(), 10000, 2))) : "0.00");//当年累计考核保费计划（万元）
            } else {
                responseVO.setYearPremiumPlan("");
            }


            PrpsPlanFeeDay prpsPlanFeeDay = null;
            PrpsPlanFeeDay prpsPlanFeeDay2 = null;
            if ("1".equals(searchFlag)) {//日业绩查询
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "0");
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCode(paramMap);
                //prpsPlanFeeDay = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCode", paramMap);
            } else if ("2".equals(searchFlag)) {//周业绩
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "1");
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCode(paramMap);
               // prpsPlanFeeDay = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCode", paramMap);
            } else if ("3".equals(searchFlag)) {//月业绩
                searchMonth = searchMonth.replace("-", "");
                paramMap.clear();
                paramMap.put("userCode", userCode);
                //paramMap.put("type", "2");
                paramMap.put("searchMonth", searchMonth);
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCodeMonth(paramMap);
                //prpsPlanFeeDay = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCodeMonth", paramMap);

                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "0");
                paramMap.put("searchMonth", searchMonth);
                saleLawPersonDetail= b2BBUSITAppSSalelawPersondetailMapper.selectByUserCode(paramMap);
                //saleLawPersonDetail = (SaleLawPersonDetail) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL.selectByUserCode", paramMap);
                if (saleLawPersonDetail != null) {
                    responseVO.setStandardPremiumAll(saleLawPersonDetail.getMonthstandardpremium() != null ? dl.format(saleLawPersonDetail.getMonthstandardpremium()).toString() : "0.00");//考核保费(总)
                    responseVO.setStandardPremiumRate(String.valueOf(saleLawPersonDetail.getMonthpremiumreachrate().multiply(new BigDecimal(100)).setScale(0)));//考核保费达成率(总)
                } else {
                    responseVO.setStandardPremiumAll("");
                    responseVO.setStandardPremiumRate("");
                }
            } else if ("4".equals(searchFlag)) {//年业绩
                //统计截止T-1日
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "3");
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCodeYear(paramMap);
                //prpsPlanFeeDay = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCodeYear", paramMap);

                //统计截止上月最后一天
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "4");
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCodeYear(paramMap);
                //prpsPlanFeeDay2 = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCodeYear", paramMap);

                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "3");
                saleLawPersonDetail=b2BBUSITAppSSalelawPersondetailMapper.selectByUserCode(paramMap);
                //saleLawPersonDetail = (SaleLawPersonDetail) dao.toView("B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL.selectByUserCode", paramMap);
                if (saleLawPersonDetail != null) {
                    responseVO.setStandardPremiumAll(saleLawPersonDetail.getSumstandardpremium() != null ? dl.format(saleLawPersonDetail.getSumstandardpremium()).toString() : "0.00");//考核保费(总)
                    responseVO.setStandardPremiumRate(String.valueOf(saleLawPersonDetail.getYearpremiumreachrate().multiply(new BigDecimal(100)).setScale(0)));//考核保费达成率(总)
                } else {
                    responseVO.setStandardPremiumAll("");
                    responseVO.setStandardPremiumRate("");
                }
            } else if ("5".equals(searchFlag)) {//高级查询
                paramMap.clear();
                paramMap.put("userCode", userCode);
                paramMap.put("type", "5");
                paramMap.put("searchStartDate", searchStartDate);
                paramMap.put("searchEndDate", searchEndDate);
                prpsPlanFeeDay=b2BBUSITAppSPrpsplanfeeDayMapper.selectByUserCode(paramMap);
                //prpsPlanFeeDay = (PrpsPlanFeeDay) dao.toView("B2BBUSI_T_APP_S_PRPSPLANFEE_DAY.selectByUserCode", paramMap);
            }

            if (prpsPlanFeeDay != null) {
                responseVO.setRealPremiumAll(dl.format(prpsPlanFeeDay.getPayreffee()).toString());//实收保费(总)
                responseVO.setRealPremiumCar(dl.format(prpsPlanFeeDay.getPayreffeeCar()).toString());//实收保费(车险)
                responseVO.setRealPremiumNoCar(dl.format(prpsPlanFeeDay.getPayreffeeNocar()).toString());//实收保费(非车险)
                responseVO.setRealPremiumAcc(dl.format(prpsPlanFeeDay.getPayreffeeAcc()).toString());//实收保费(意外险)
                responseVO.setRealPremiumHea(dl.format(prpsPlanFeeDay.getPayreffeeHea()).toString());//实收保费(健康险)
            } else {
                responseVO.setRealPremiumAll("0.00");//实收保费(总)
                responseVO.setRealPremiumCar("0.00");//实收保费(车险)
                responseVO.setRealPremiumNoCar("0.00");//实收保费(非车险)
                responseVO.setRealPremiumAcc("0.00");//实收保费(意外险)
                responseVO.setRealPremiumHea("0.00");//实收保费(健康险)
            }

            if (prpsPlanFeeDay2 != null) {
                responseVO.setRealPremiumAll2(dl.format(prpsPlanFeeDay2.getPayreffee()).toString());//实收保费(总)
                responseVO.setRealPremiumCar2(dl.format(prpsPlanFeeDay2.getPayreffeeCar()).toString());//实收保费(车险)
                responseVO.setRealPremiumNoCar2(dl.format(prpsPlanFeeDay2.getPayreffeeNocar()).toString());//实收保费(非车险)
                responseVO.setRealPremiumAcc2(dl.format(prpsPlanFeeDay2.getPayreffeeAcc()).toString());//实收保费(意外险)
                responseVO.setRealPremiumHea2(dl.format(prpsPlanFeeDay2.getPayreffeeHea()).toString());//实收保费(健康险)
            } else {
                responseVO.setRealPremiumAll2("0.00");//实收保费(总)
                responseVO.setRealPremiumCar2("0.00");//实收保费(车险)
                responseVO.setRealPremiumNoCar2("0.00");//实收保费(非车险)
                responseVO.setRealPremiumAcc2("0.00");//实收保费(意外险)
                responseVO.setRealPremiumHea2("0.00");//实收保费(健康险)
            }

            HttpResult result= HttpResult.success(responseVO);
            result.setCode("1");
            result.setMsg("查询成功");
            return result;
            //jsonVO.setData(responseVO);
           // rlt.setCode("1");
        } catch (Exception ex) {
            return HttpResult.error(0,"系统错误");
            //rlt.setCode("0");
            //rlt.setMessage("系统错误。");
            //log.error(ex);
        }
        //jsonVO.setResult(rlt);
        //return jsonVO;

}
}

