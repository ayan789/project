package com.ccic.salesapp.performance.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ccic.salesapp.performance.domain.vo.request.KPIRequest;
import com.ccic.salesapp.performance.domain.vo.response.KPIResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class KPIService {

	@Value("${esb.address.saleslawevaluationqueryservicerest}")
	private String saleslawevaluationqueryservicerest;

	@Autowired
	RestTemplate restTemplate;

	/***
	 * 返回 KPIResponse,KPI考核项目及得分占比
	 * 
	 * @return
	 */
	public HttpResult<KPIResponse> getKPI(KPIRequest request) {
		Map<String, String> requestHead = new LinkedHashMap<>();
		String defaultConSumerID = RequestHead.getDefault().getConsumerID().toString();
		requestHead.put("seqNo", "110");
		requestHead.put("consumerID", defaultConSumerID);
		requestHead.put("consumerSeqNo", "9-5DAS54s345s6pma1!23bcv!,,1823s7,w])as81c21");
		Map<String, String> evaluationQueryRequestDTO = new LinkedHashMap<>();
		evaluationQueryRequestDTO.put("userCode", request.getUserCode());
		SimpleDateFormat sf = new SimpleDateFormat("YYYYMM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date mon = calendar.getTime();
		evaluationQueryRequestDTO.put("evaluationMonth", sf.format(mon));
		Map<String, Object> req = new LinkedHashMap<>();
		req.put("requestHead", requestHead);
		req.put("evaluationQueryRequestDTO", evaluationQueryRequestDTO);
		JSONObject object = JSONObject.fromObject(req);
		ResponseEntity<JSONObject> resp = restTemplate.postForEntity(saleslawevaluationqueryservicerest, object,
				JSONObject.class);
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:KPI考核],[入參报文：{}]", LocalDateTime.now(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:KPI考核],[响应报文：{}]", LocalDateTime.now(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(resp.getBody()));
		return HttpResult.success(resp.getBody());
	}
}