package com.ccic.salesapp.performance.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.performance.domain.vo.request.SalaryRequest;
import com.ccic.salesapp.performance.domain.vo.response.SalaryResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class SalaryService {

	@Value("${esb.address.salesLawSalaryListQuery}")
	private String salesLawSalaryListQuery;

	@Autowired
	RestTemplate restTemplate;

	/***
	 * 返回SalaryResponse,薪酬项目及总实发
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public HttpResult<SalaryResponse> salaryQuery(SalaryRequest request) {
		Map<String, String> requestHead = new LinkedHashMap<>();
		String defaultConSumerID = RequestHead.getDefault().getConsumerID().toString();
		requestHead.put("consumerID", defaultConSumerID);
		requestHead.put("consumerSeqNo", "p@ogb$w8261-7D497-12#dsBNmSDnbw#,vcyq,w1+rnl");
		Map<String, String> salaryListQueryRequestDTO = new LinkedHashMap<>();
		salaryListQueryRequestDTO.put("userCode", request.getUserCode());
		salaryListQueryRequestDTO.put("evaluationMonthRange", request.getEvaluationMonthRange());
		Map<String, Object> req = new LinkedHashMap<>();
		req.put("requestHead", requestHead);
		req.put("salaryListQueryRequestDTO", salaryListQueryRequestDTO);
		JSONObject object = JSONObject.fromObject(req);
		ResponseEntity<JSONObject> resp = restTemplate.postForEntity(salesLawSalaryListQuery, object, JSONObject.class);
		Object object3 = resp.getBody().get("responseHeadDTO");
		JSONObject jsonObject2 = resp.getBody().getJSONObject("responseHeadDTO");
		Object object4 = jsonObject2.get("status");
		String string2 = object4.toString();
		if(string2.equals("2")) {
			return HttpResult.success("未查询到有效数据，请检查");
		}
		JSONArray set = resp.getBody().getJSONArray("salaryResponseDTOList");
		JSONObject jsonObject = set.getJSONObject(0);
		List<String> list = new ArrayList<String>();
		for (Object object2 : set) {
			Map<String, Object> map = (Map<String, Object>) object2;
			String string = map.get("actualPay").toString();
			list.add(string);
		}
		BigDecimal actualPayNum = new BigDecimal(0);
		for(String s:list) {
			BigDecimal count=new BigDecimal(s);
			actualPayNum = actualPayNum.add(count);
		}
		Map<String,Double> result = new HashMap<String,Double>();
		for (int i = 0; i < set.size(); i++) {
			JSONArray index = set.getJSONObject(i).getJSONArray("salaryIndicatorDTOList");
			for (Object object2 : index) {
				Map map = (Map) object2;
				if(result.containsKey(map.get("salaryName"))) {
				  double value = Double.parseDouble(result.get(map.get("salaryName")).toString()) + Double.parseDouble(map.get("salaryResult").toString());
				  result.put(map.get("salaryName").toString(), value);
				}
				else{
					if (Double.parseDouble(map.get("salaryResult").toString()) == 0.0) {
						result.remove(map.get("salaryName").toString());
					}else {
						result.put(map.get("salaryName").toString(), Double.parseDouble(map.get("salaryResult").toString()));
					}
				}
			}
		}
		JSONObject jSONObject = JSONObject.fromObject(result);
		Map<String, Object> mp= (Map<String, Object>)jSONObject;
		List<String> listmap = new ArrayList<String>();
		for(String key:mp.keySet()) {
			Map<String,Object> map  = new HashMap<String, Object>();
			map.put("salaryName", key);
			map.put("salaryResult", mp.get(key));
			String fromObject = JSONObject.fromObject(map).toString();
			listmap.add(fromObject);
		}
		JSONObject accumulate = new JSONObject();
		accumulate.accumulate("actualPayNum", actualPayNum);
		accumulate.accumulate("salaryIndicatorDTOList", listmap);
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:薪酬],[入參报文：{}]", LocalDateTime.now(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:薪酬],[响应报文：{}]", LocalDateTime.now(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(accumulate));
		return HttpResult.success(accumulate);
	}
}
