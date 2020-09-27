package com.ccic.salesapp.performance.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.performance.utils.PageUtils;
import com.ccic.salesapp.performance.utils.Query;
import com.ccic.salesapp.performance.utils.R;
import com.ccic.salesapp.performance.domain.vo.dto.SMessage;
import com.ccic.salesapp.performance.service.SMessageService;
import com.ccic.salesapp.performance.domain.vo.request.MessageVo;
import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <P></P>
 * 消息表
 * @version 1.0
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:46:25
 */

@Slf4j
@RestController
@Api(tags = "消息通知模块", description = "消息通知模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/sMessage")
public class SMessageCtl {

	
	/*************************特别强调：
	 * 1、status 转码，转成  1:可用   0：不可用
	 * 2、属性对象不能为空
	 * 3、实现增量同步
	 **************************/

	@Autowired
	private SMessageService sMessageService;
	
	@PostMapping("/getMessageType")
	@ApiOperation(value = "消息大类", notes = "消息大类", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getMessageType(@RequestBody MessageVo messageVo) {
		//查询消息大类
		log.info("入参:{}"+JSONObject.toJSONString(messageVo));
		try {
			//return R.ok().data(sMessageService.getMessageType(messageVo));
			HttpResult result= HttpResult.success();
            result.setCode("1");
            result.setData(sMessageService.getMessageType(messageVo));
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResult result= HttpResult.error(0, "获取失败");
			return result;
		}
		
	}
	
	@GetMapping("/getMessagelist")
	@ApiOperation(value = "消息子列表", notes = "消息子列表", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult getMessagelist(MessageVo messageVo) {
		//查询消息列表接口 消息未读数 notReadNum
		log.info("入参:{}"+JSONObject.toJSONString( messageVo));
		try {
			//return R.ok().data(sMessageService.getMessageList(messageVo));
			HttpResult result= HttpResult.success();
            result.setCode("1");
            result.setData(sMessageService.getMessageList(messageVo));
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResult result= HttpResult.error(0, "获取失败");
			return result;
		}	
	}
	
	@GetMapping("/messageChange")
	@ApiOperation(value = "消息状态变更", notes = "消息状态变更", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult messageChange(SMessage sMessage) {
		log.info("入参:{}"+JSONObject.toJSONString(sMessage));
		try {
			sMessage.setStatus("1");
			sMessageService.update(sMessage);
			//return R.ok().message("已阅读");
			HttpResult result= HttpResult.success();
            result.setCode("1");
            result.setMsg("已阅读");
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResult result= HttpResult.error(0, "变更失败");
			return result;
		}	
	}
	
	@PostMapping("/messageSend")
	@ApiOperation(value = "消息任务栏推送", notes = "消息任务栏推送", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult messageSend(String messageId) {
		log.info("入参:{}"+JSONObject.toJSONString(messageId));
		try {
			//消息推送逻辑
			//return R.ok().message("已推送");
			HttpResult result= HttpResult.success();
            result.setCode("1");
            result.setMsg("已推送");
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			HttpResult result= HttpResult.error(0, "推送失败");
			return result;
		}
	}


}
