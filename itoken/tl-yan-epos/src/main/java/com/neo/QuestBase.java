package com.neo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Data
public class QuestBase {

	public static final String ANSWER_YES = "Y";
	public static final String ANSWER_NO = "N";
	public static final String ANSWER_QUIT = "Q";

	public static final String NAME_KEY = "name";
	public static final String VALUE_KEY = "value";
	public static final String EXTRA_OPTIONS_ARR_KEY = "extraOptions";

	public static final String VALUE_CHILDREN_KEY = "transaction";


	String id;

	String answer;

	String detail;

	public void write(JSONObject jsonObject) {

	}

	public void write(JSONObject jsonObject,int score) {

	}

	public void handle(JSONObject jsonObject, InsureFactors insureFactors) {
		if (!useDefaultHandle()) {
			return;
		}
		JSONArray extraOptions = jsonObject.getJSONArray("extraOptions");
		if (ANSWER_YES.equals(getAnswer())) {
			dealArray(extraOptions, true);
		} else {
			dealArray(extraOptions, false);
		}

	}

	public static void dealArray(JSONArray jsonArray, boolean show) {
		for (int i = 0; i < jsonArray.size(); i++) {
			Object object = jsonArray.get(i);
			if (object instanceof JSONArray) {
				dealArray((JSONArray) object, show);
			} else if (object instanceof JSONObject) {
				InformOperation.operateObjectShow((JSONObject) object, show);
			}
		}
	}



	public boolean useDefaultHandle() {
		return false;
	}

	/**
	 * write value
	 * @param jsonObject
	 * @param value
	 */
	public void objectWriteFromMap(JSONObject jsonObject, Object value) {
		if (jsonObject.containsKey(NAME_KEY)) {

			jsonObject.put(VALUE_KEY, value);
		}
	}

	public void arrayWriteFromMap(JSONArray jsonArray, Map valueMap) {
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			objectWriteFromMap(jsonObject, valueMap.get(jsonObject.get(NAME_KEY)));
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			if (ThailandDateTool.isThai()) {
				cal.add(Calendar.YEAR, 543);
				year = cal.get(Calendar.YEAR);
			}
			if (null != jsonObject.getString("errorMsgRule") && StringTool.isNotNull(jsonObject.getString("maxYear")) ) {
				if (jsonObject.getString("errorMsgRule").contains(jsonObject.getString("maxYear"))) {
					StringBuffer errorMsgRule = new StringBuffer("Please enter a 1910 to " + year + " number");
					jsonObject.put("errorMsgRule", errorMsgRule);
				}
				jsonObject.put("maxYear", year);
			}else if (StringTool.isNotNull(jsonObject.getString("maxYear"))){
				jsonObject.put("maxYear", year);
			}

		}
	}

	public Map arrayReadToMap(JSONArray jsonArray) {
		Map valueMap = new HashMap();
		for (Object object :
				jsonArray) {
			valueMap = objectReadToMap((JSONObject) object, valueMap);
		}

		return valueMap;
	}

	public Map objectReadToMap(JSONObject jsonObject, Map valueMap) {
		if (jsonObject.containsKey(NAME_KEY)) {
			if (valueMap == null) {
				valueMap = new HashMap();
			}
			valueMap.put(jsonObject.get(NAME_KEY), jsonObject.get(VALUE_KEY));
		}
		return valueMap;
	}



}
