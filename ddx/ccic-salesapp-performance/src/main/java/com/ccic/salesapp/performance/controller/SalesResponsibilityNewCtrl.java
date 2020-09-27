package com.ccic.salesapp.performance.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.domain.mapper.PerfResponsibilityDetailMapper;
import com.ccic.salesapp.performance.domain.mapper.PerfResponsibilityMapper;
import com.ccic.salesapp.performance.domain.mapper.PerformanceCustomerStandardDao;
import com.ccic.salesapp.performance.domain.vo.request.ProgressRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityVo;
import com.ccic.salesapp.performance.feign.SupportFeignService;
import com.ccic.salesapp.performance.service.SalesResponsibilityNewService;
import com.ccic.salessapp.common.core.web.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * <P></P>
 * 
 * @version 1.0
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 09:50:57
 */

@Slf4j
@RestController
@Api(tags = "责任书管理模块", description = "责任书管理模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/salesResponsibility")
public class SalesResponsibilityNewCtrl {

	
	
	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/
	
	@Autowired
	private SalesResponsibilityNewService salesResponsibilityNewService;
	
	@Autowired
	PerfResponsibilityMapper perfResponsibilityMapper;
	
	@Autowired
	PerfResponsibilityDetailMapper perfResponsibilityDetailMapper;
	
	@Autowired
	MessageNoticeFeignClient messageNoticeFeignClient;
	
	@Autowired
	SupportFeignService supportFeignService;
	
	@Autowired
	PerformanceCustomerStandardDao performanceCustomerStandardDao;

	//责任书展示
	@PostMapping("/getSalesResponsibility")
	@ApiOperation(value = "询责任书列表,领导签名图片查询,获取责任书目标值", notes = "询责任书列表,领导签名图片查询,获取责任书目标值", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private HttpResult<?> getSalesResponsibility(@RequestBody SalesResponsibilityVo salesResponsibilityVo) {
		log.info("入参:{}",JSONObject.toJSONString(salesResponsibilityVo));
		//获取责任书列表&签名&责任书目标值
		return salesResponsibilityNewService.salesResponsibilityList(salesResponsibilityVo);
	}
	
	//责任书录入
	@PostMapping("/saveSalesResponsibility")
	@ApiOperation(value = "责任书保存更新", notes = "责任书保存更新", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private HttpResult<?> saveSalesResponsibility(@RequestBody SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		log.info("入参:{}",JSONObject.toJSONString(salesResponsibilityRequestVo));
		//责任id不为空则责任书保存 责任id空则责任书更新
		return salesResponsibilityNewService.salesResponsibilitySave(salesResponsibilityRequestVo);
	}
	
	//成员进度
	@PostMapping("/getMonthlyQuarterlyProgress")
	@ApiOperation(value = "月季度达成进度", notes = "月季度达成进度", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> getMonthlyQuarterlyProgress(@RequestBody ProgressRequestVo req) {
		log.info("入参:{}",JSONObject.toJSONString(req));
		//业绩进度查询（月&季度）
		return salesResponsibilityNewService.getMonthlyQuarterlyProgress(req);
	}
	
	//成员进度
	@PostMapping("/getAnnualProgress")
	@ApiOperation(value = "年度达成进度", notes = "年度达成进度", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> getAnnualProgress(@RequestBody ProgressRequestVo req) {
		log.info("入参:{}",JSONObject.toJSONString(req));
		//业绩进度查询（年度 1-12月）
		return salesResponsibilityNewService.getAnnualProgress(req);
	}
	
	//排名
	@PostMapping("/getpersAndTeamRanking")
	@ApiOperation(value = "业绩排名", notes = "业绩排名", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> getpersAndTeamRanking(@RequestBody RankVo req) {
		log.info("入参:{}",JSONObject.toJSONString(req));
		if(req.getUserCode()!=null) {
			req.setChannelCode(perfResponsibilityMapper.getChannelCode(req.getUserCode()));
		}
		//业绩排名(个人在团队｜个人在分公司｜团队在分公司｜团队在总公司)
		return salesResponsibilityNewService.getpersAndTeamRanking(req);
	}
	
	//责任书初始化
	@PostMapping(value = "/hasResponsibilityStatus")
	@ApiOperation(value = "责任书生成状态", notes = "责任书生成状态", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> hasResponsibilityStatus(@RequestBody SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		log.info("入参:{}",JSONObject.toJSONString(salesResponsibilityRequestVo));
		//初始化责任书并返回责任书信息
		return salesResponsibilityNewService.hasResponsibilityStatus(salesResponsibilityRequestVo);
	}
	
	//排名和月总保费年总保费
	@PostMapping(value = "/queryRankingM2YPremium")
	@ApiOperation(value = "查询个人渠道分公司排名和月总保费年总保费", notes = "查询个人渠道分公司排名和月总保费年总保费", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> queryRankingM2YPremium(@RequestBody RankVo rankVo) {
		log.info("入参:{}",JSONObject.toJSONString(rankVo));
		//个人渠道分公司排名|月总保费|年总保费
		return salesResponsibilityNewService.queryRankingM2YPremium(rankVo);
	}
	
	//消息发送
	@PostMapping(value = "/responsibilityNoticePush")
	@ApiOperation(value = "责任书消息通知", notes = "责任书消息通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> responsibilityNoticePush(@RequestBody String[] userCodes) {
		log.info("入参:{}",JSONObject.toJSONString(userCodes));
		//消息发送到通知栏
		return salesResponsibilityNewService.responsibilityNoticePush(userCodes);
	}
	
	//消息发送
	@PostMapping(value = "/responsibilityNotice")
	@ApiOperation(value = "责任书消息通知", notes = "责任书消息通知", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> responsibilityNotice(@RequestBody SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		log.info("入参:{}",JSONObject.toJSONString(salesResponsibilityRequestVo));
		//消息发送到APP
		return salesResponsibilityNewService.responsibilityNotice(salesResponsibilityRequestVo.getUserCode());
	}
	
	//客户对标数
	@PostMapping("/customerStandard")
	public HttpResult<?> getCustomerStandardTotal(@RequestBody SalesResponsibilityRequestVo salesResponsibilityRequestVo) {
		log.info("入参:{}",JSONObject.toJSONString(salesResponsibilityRequestVo));
		//客户对标数
		salesResponsibilityNewService.custStandardTotal();
		return HttpResult.success( 1, "调用成功");
	}
	
	@PostMapping(value = "/isTeamleader")
	public HttpResult<?> isTeamleader(@RequestBody SalesResponsibilityVo salesResponsibilityVo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", salesResponsibilityVo.getUserCode());
		int count = perfResponsibilityMapper.isTeamleader(map);
		return HttpResult.success(count, 1, "调用成功");
	}
	
	@PostMapping(value = "/cusTotal")
	public void cusTotal() {
		//模拟
		log.info("开始..."); 
		perfResponsibilityMapper.updateCustomerStandardCapacity();
		perfResponsibilityMapper.updateCustomerStandardChannel();
		perfResponsibilityMapper.updateCustomerTotalStandard();
		log.info("完成..."); 
	}
	
	@PostMapping(value = "/teamerChange")
	public void teamerChange(@RequestBody SalesResponsibilityVo salesResponsibilityVo) {
		//模拟
		log.info("开始...");
		List<HashMap<String,Object>> list = perfResponsibilityMapper.teamInfo();
		for(HashMap<String,Object> map : list) {
			if(map.get("teamladerCode") != null && !map.get("teamladerCode").equals("")
				&& map.get("lastDayTeamladerCode") != null && !map.get("lastDayTeamladerCode").equals(""))
				{
					System.out.println("执行:"+ map.get("teamladerCode"));
					System.out.println(map.get("lastDayTeamladerCode"));
					perfResponsibilityMapper.updateTeamerResponsibility(map);
					perfResponsibilityMapper.updatePserResponsibility(map);
					perfResponsibilityMapper.updateTeamer(map);
				}
			else {
				System.out.println("不执行:"+ map.get("teamladerCode"));
				System.out.println(map.get("lastDayTeamladerCode"));
			}
			
    	}
		log.info("完成...");
	}
	
	@PostMapping(value = "/noExistsCustomer")
	public void noExistsCustomer() {
		//模拟
		log.info("开始...");
		perfResponsibilityMapper.noExistsCustomer();
		log.info("完成...");
	}
	
}
