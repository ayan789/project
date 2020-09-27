package com.ccic.salesapp.noncar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.dto.request.PolicyVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupPlanVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlanRoot;
import com.ccic.salesapp.noncar.service.GroupPlanService;
import com.ccic.salesapp.noncar.service.PlanStrategyService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;

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
    @ApiOperation(value = "保单列表", notes = "保单列表", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult policyList(@RequestBody PolicyVo policyVo){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			PageVO page = new PageVO();
			page.setPage(policyVo.getPageNo());
			page.setSize(policyVo.getPageSize());
			int count = 100;
			page.init(count);
			int offset = page.getPage() * page.getSize();
			int limit = page.getSize();
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("submissionNo", policyVo.getSubmissionNo());
			return groupPlanService.policyList(map);
		} catch (Exception e) {
			log.error("追踪号:"+uuid+getStackTraceInfo(e));
			return HttpResult.error(0, "保单列表查询失败,追踪号:"+uuid);
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
	
}
