package com.ccic.salesapp.performance.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.mapper.UserMonthlyReportMapper;
import com.ccic.salesapp.performance.domain.vo.dto.UserMonthlyReportInfo;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;
import com.ccic.salesapp.performance.service.MonthlyReportTaskService;
import com.ccic.salesapp.performance.utils.DateDiffMonth;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "月度报告", description = "月度报告", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "monthlyReport")
public class UserStorieCtl {
	
	@Autowired
	UserMonthlyReportMapper userStorieMapper;
	
	@PostMapping(value = "/index")
	@ApiOperation(value = "根据用户查询月度报告", notes = "根据用户查询月度报告", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> index(@RequestBody UserMonthlyReportInfo userStoriesVo){
		try {
			//查询用户list
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("userCode", userStoriesVo.getUserCode());
			List<UserMonthlyReportInfo> userStories = userStorieMapper.selectUserStories(map);
			return HttpResult.success(userStories,1, "OK");
		} catch (Exception e) {
			log.error(e.getMessage());
			return HttpResult.error(0, "ERROR");
		}
	}
	
	@PostMapping(value = "/info")
	@ApiOperation(value = "根据用户查询月度报告", notes = "根据用户查询月度报告", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> info(@RequestBody UserMonthlyReportInfo userStoriesVo) throws ParseException{
		try {
			//查询用户详情
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", userStoriesVo.getId());
			UserMonthlyReportInfo userStories = userStorieMapper.selectUserStoriesDetail(map);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			Date start = df.parse("2020-05-01");//上线时间
			int monthStep = DateDiffMonth.getMonth(start, userStories.getCreateTime());
			userStories.setMonthStep(monthStep);
			return HttpResult.success(userStories,1, "OK");
		} catch (Exception e) {
			log.error(e.getMessage());
			return HttpResult.error(0, "ERROR");
		}
	}
	
	@Autowired
	MonthlyReportTaskService monthlyReportTaskService;
	
	//月度报告补偿
	@PostMapping(value = "/batch")
	private HttpResult<?> batch() {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
	    int year = cal.get(Calendar.YEAR);
	    cal.setTime(new Date());
	    cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String last= format.format(cal.getTime());
		map.put("realpayDate", last);//生产上获取当前时间
		map.put("monthStep", month);
		map.put("isYear", year);
		//删除查询出来的数据
		//userStorieMapper.deleteMonthlyReportDirtyData(map);
		PageVO page = new PageVO();
		page.setPage(1);
		page.setSize(100);
		int count = userStorieMapper.selectUserCodesNum(map);
		page.init(count);
		for(int i=0;i<page.getPages();i++) {
			int offset = i * page.getSize();
			int limit = page.getSize();
			map.put("offset", offset);
			map.put("limit", limit);
			List<String> userCodes = userStorieMapper.selectUserCodes(map);
			
			monthlyReportTaskService.insertListService(userCodes, map,i);
		}
		return HttpResult.success(1, "OK");
	}

	@PostMapping(value = "/sendMessage")
	private HttpResult<?> sendMessage() {
    	System.out.println("MonthlyReportPushTask");
    	Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH); 
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("month", month);
    	PageVO page = new PageVO();
		page.setPage(1);
		page.setSize(100);
		int count = userStorieMapper.selectMonthlyReportUserCodesNum(map);
		page.init(count);
    	for(int i=0;i<page.getPages();i++) {
			int offset = 0;
			int limit = page.getSize();
			map.put("offset", offset);
			map.put("limit", limit);
			List<String> userCodes = userStorieMapper.selectMonthlyReportUserCodes(map);
			if(userCodes!=null && userCodes.size()>0) {
				map.put("userCodes", userCodes);
				monthlyReportTaskService.updateListSendMsgService(userCodes, map);
			}
    	}
    	return HttpResult.success(1, "OK");
	}
	
	
	
}
