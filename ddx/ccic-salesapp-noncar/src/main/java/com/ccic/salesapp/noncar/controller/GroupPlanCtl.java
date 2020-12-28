package com.ccic.salesapp.noncar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.RecommendRankPlansObj;
import com.ccic.salesapp.noncar.dto.RecommendRankPlansVo;
import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.PolicyVo;
import com.ccic.salesapp.noncar.dto.response.Insure;
import com.ccic.salesapp.noncar.dto.response.InsureRes;
import com.ccic.salesapp.noncar.dto.response.PolicyLob;
import com.ccic.salesapp.noncar.dto.response.PolicyRisk;
import com.ccic.salesapp.noncar.dto.response.RecommendPlanList;
import com.ccic.salesapp.noncar.dto.response.RecommendRankPlanList;
import com.ccic.salesapp.noncar.dto.response.RecommendRankPlansRes;
import com.ccic.salesapp.noncar.dto.response.RecommendedPlanRes;
import com.ccic.salesapp.noncar.dto.salesplan.GroupPlanVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlan;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlanRoot;
import com.ccic.salesapp.noncar.repository.basedb.mapper.SalesPlanMapper;
import com.ccic.salesapp.noncar.service.GroupPlanService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.StringUtils;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "组合方案", description = "组合方案", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "groupPlan")
@Slf4j
public class GroupPlanCtl {
	
	@Autowired
	GroupPlanService groupPlanService;
	
	@Autowired
	PlanStrategyService planStrategyService;
	
	@Autowired
    SalesPlanMapper salesPlanMapper;
	
	@PostMapping(value = "groupPlanSync")
    @ApiOperation(value = "组合方案同步", notes = "组合方案同步", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void groupPlanSync(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			groupPlanService.groupPlanSync(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("application/json; charset=UTF-8");
			String result = "{\"responseHead\":{\"consumerSeqNo\":\"supsale\",\"status\":1,\"seqNo\":\"\",\"providerSeqNo\":\"\",\"esbCode\":\"ESB服务失败\",\"esbMessage\":\"ESB服务失败\",\"appCode\":\"0\",\"appMessage\":\"数据同步失败\"}}";
			log.info("响应报文:"+result);
			PrintWriter out = response.getWriter();
		    out.print(result);
			if(out!=null){
				out.close();
			}
		}
	}
	
	@PostMapping(value = "groupPlanList")
    @ApiOperation(value = "组合方案列表", notes = "组合方案详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<GroupSalesPlanRoot> groupPlanList(@RequestBody GroupPlanVo groupPlanVo){
		UserToken user = planStrategyService.getAccessTokenByUserCode(groupPlanVo.getUserCode());
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("channelCode", user.getChannelCode());
		map.put("comCode", user.getComCode());
		return groupPlanService.groupPlanList(map);
	}

	@PostMapping(value = "groupPlanDetail")
    @ApiOperation(value = "组合方案详情&暂存和复制投保单", notes = "组合方案详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<GroupSalesPlanRoot> groupPlanDetail(@RequestBody GroupPlanVo groupPlanVo,HttpServletRequest request){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.groupPlanDetail(groupPlanVo,request);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+groupPlanVo.getUserCode()+":"+"groupPlanDetail:"+getStackTraceInfo(e));
			return HttpResult.error(0, "组合方案查询失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "planDetail2CarXY")
    @ApiOperation(value = "组合方案详情&暂存和复制投保单", notes = "组合方案详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<GroupSalesPlanRoot> planDetail2CarXY(@RequestBody GroupPlanVo groupPlanVo,HttpServletRequest request){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.planDetail2CarXY(groupPlanVo,request);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+groupPlanVo.getUserCode()+":"+"groupPlanDetail:"+getStackTraceInfo(e));
			return HttpResult.error(0, "组合方案查询失败,追踪号:"+uuid);
		}
	}
	
	
	
	@PostMapping(value = "stagCopyInsure")
    @ApiOperation(value = "暂存和复制投保单", notes = "暂存和复制投保单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult stagCopyInsure(@RequestBody OrderDetailRequest request){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.stagCopyInsure(request);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "暂存和复制投保单查询失败,追踪号:"+uuid);
		}
		
	}
	
	@PostMapping(value = "signInit")
    @ApiOperation(value = "签名初始化", notes = "签名初始化", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult signInit(@RequestBody ForwardPayInsureInfoReqVO forwardPayInsureInfo){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.signInit(forwardPayInsureInfo);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "签名初始化查询失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "applyPayment")
    @ApiOperation(value = "申请支付号", notes = "申请支付号", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult applyPayment(@RequestBody ApplyPayVo applyPayVo){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.applyPayment(applyPayVo);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "申请支付号失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "genePaymentLink")
    @ApiOperation(value = "生成支付链接", notes = "生成支付链接", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult genePaymentLink(@RequestBody GetPayUrlRequestVO request){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.genePaymentLink(request);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "生成支付链接失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "paySyncNotice")
    @ApiOperation(value = "支付同步通知", notes = "支付同步通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult paySyncNotice(@RequestBody ApplyPayVo applyPayVo){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.paySyncNotice(applyPayVo);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "支付同步通知失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "policyList")
    @ApiOperation(value = "保单列表(联合销售)", notes = "保单列表(联合销售)", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult policyList(@RequestBody PolicyVo policyVo){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			return groupPlanService.policyList(policyVo);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "保单列表查询失败,追踪号:"+uuid);
		}
	}
	
	@PostMapping(value = "policyListStaffOnly")
    @ApiOperation(value = "保单列表(员工通道)", notes = "保单列表(员工通道)", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult policyListStaffOnly(@RequestBody PolicyVo policyVo){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            return groupPlanService.policyListStaffOnly(uuid , policyVo);
        } catch (Exception e) {
            log.error("追踪号:"+uuid+getStackTraceInfo(e));
            return HttpResult.error(0, "未查询到保单信息");
        }
    }
	
    public static String getStackTraceInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ex) {
            return "printStackTrace()转换错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    @PostMapping(value = "planDetail2Family")
    @ApiOperation(value = "家庭版推荐-初始化信息", notes = "家庭版推荐-初始化信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult planDetail2Family(@RequestBody GroupPlanVo groupPlanVo,HttpServletRequest request){
        return groupPlanService.planDetail2Family(groupPlanVo,request);
    }
    
    @PostMapping(value = "recommendRankPlans")
    @ApiOperation(value = "家庭版推荐", notes = "家庭版推荐", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult recommendRankPlans(@RequestBody RecommendRankPlansVo recommendRankPlansVo){
        //家庭版推荐接口请求信息
        //家庭版推荐接口放回信息
        //根据 policyLobList t_noncar_sales_plan 里的 polHolderInsuredRelaCode 累加 childPlanCode 
        //SELECT * from t_noncar_sales_plan where sales_plan_code='' 基础版 自选1 2
        //根据sales_plan_code查处对应的方案信息
        return groupPlanService.recommendRankPlans(recommendRankPlansVo);
    }
    
    @SuppressWarnings("javadoc")
    public static void buildFor(RecommendRankPlansObj recommendRankPlansObj) {
        recommendRankPlansObj.getRecommendRankPlanList().stream().forEach(item -> {
            recommendRankPlansObj.setRecommendPlanList(item.getRecommendPlanList());
            GroupPlanCtl.buildRecommendPlan(recommendRankPlansObj);
        });
    }
    
    @SuppressWarnings("javadoc")
    public static void buildRecommendPlan(RecommendRankPlansObj recommendRankPlansObj) {
        recommendRankPlansObj.getRecommendPlanList().stream().forEach(item -> {
            recommendRankPlansObj.setSalesPlanCode(item.getSalesPlanCode());
            recommendRankPlansObj.getSalePlanCodelist().add(item.getSalesPlanCode());
            recommendRankPlansObj.setPolicyLobList(item.getPolicyLobList());
            GroupPlanCtl.buildPolicyLob(recommendRankPlansObj);
        });
    }
    
    @SuppressWarnings("javadoc")
    public static void buildPolicyLob(RecommendRankPlansObj recommendRankPlansObj) {
        recommendRankPlansObj.getPolicyLobList().stream().forEach(item -> {
          System.out.println("子方案代码：" + item.getChildPlanCode());
          recommendRankPlansObj.setChildPlanCode(item.getChildPlanCode());
          recommendRankPlansObj.getChildPlanCodelist().add(item.getChildPlanCode());
          recommendRankPlansObj.setPolicyRiskList(item.getPolicyRiskList());
          GroupPlanCtl.buildPolicyRisk(recommendRankPlansObj);
        });
    }
    
    public static void buildPolicyRisk(RecommendRankPlansObj recommendRankPlansObj) {
        recommendRankPlansObj.getPolicyRiskList().stream().forEach(item -> {
              InsureRes insureRes = new InsureRes();
              System.out.println("子方案代码："+recommendRankPlansObj.getChildPlanCode());
              System.out.println("与被保人关系："+item.getPolHolderInsuredRelaCode());
              recommendRankPlansObj.getRelaCodelist().add(item.getPolHolderInsuredRelaCode());
              insureRes.setChildPlanCode(recommendRankPlansObj.getChildPlanCode());
              insureRes.setPolHolderInsuredRelaCode(item.getPolHolderInsuredRelaCode());
              insureRes.setSalesPlanCode(recommendRankPlansObj.getSalesPlanCode());
              recommendRankPlansObj.getMapList().add(insureRes);
          });
    }
	
}
