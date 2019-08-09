package com.neo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by zhangjunwen on 2017/10/23
 *
 * @author zhangjunwen
 * @date 2017/10/23
 */
@Slf4j
public class DictUtils {


	private static final String TH_TH = "th_TH";

	/**
	 * 获取通用字典（鑫能）
	 *
	 * @param key
	 * @return
	 */
	public static DItem getDict(String key) {


		return getDict(key,null);
	}

	public static DItem getDict(String key,Locale locale) {

		if(locale == null){
			locale = LoginUtil.currentLocale();
		}
		String localeStr = locale.toString();

		if (StringUtils.isNotEmpty(localeStr) && TH_TH.toLowerCase().equals(localeStr.toLowerCase())) {
			return Dictionary.getDictByKey(TH_TH, key);
		}

		return Dictionary.getDictByKey(DictionaryEnum.COMMON.getCompanyId(), key);
	}

	public static Map<String, DItem> getDictByCompanyId(String CompanyId) {

		return Dictionary.getDictByCompanyId(CompanyId);
	}

	public static DItem getDictByKey(String companyId, String key) {
		if (StringUtils.isEmpty(companyId)) {
			return getDict(key);
		}
		if (StringUtils.isNotEmpty(companyId) && "en_US".equals(companyId)) {
			companyId = "common";
		}
		return Dictionary.getDictByKey(companyId, key);
	}

	public static DItem matchCode(String key, String code) {

//		List<DItem> items = getDict(key).getItems();

		return matchCode(key, code,null);
	}

	public static DItem matchCode(String key, String code,Locale locale) {

		List<DItem> items = getDict(key,locale).getItems();

		return matchCode(items, code);
	}

	public static DItem matchCode(List<DItem> items, String code) {

		if (StringUtils.isNotEmpty(code) && items != null && items.size() > 0) {
			for (DItem dItem : items) {
				if (code.equals(dItem.getCode())) {

					return dItem;
				}
			}
		}

		return null;
	}

	public static DItem matchName(String key, String name) {
		List<DItem> items = getDict(key).getItems();

		if (StringUtils.isNotEmpty(name) && items != null && items.size() > 0) {
			for (DItem dItem : items) {
				if (name.equals(dItem.getName())) {

					return dItem;
				}
			}
		}

		return null;

	}

	/**
	 * 转换成通用字典
	 *
	 * @param dItem
	 * @return
	 */
	public static DItem transformCommonDict(DItem dItem) {

		String commonKey = dItem.getCommonKey();
		if (StringUtils.isEmpty(commonKey)) {
			return null;
		}

		return getDict(commonKey);
	}

	/**
	 * 转换成通用字典
	 *
	 * @param
	 * @return
	 */
	public static DItem transformCommonDict(String companyId, String dictKey, String code) {
		DItem dItem;
		DItem dictByKey = getDictByKey(companyId, dictKey);
		if (dictByKey != null) {
			dItem = matchCode(dictByKey.getItems(), code);
			return transformCommonDict(dItem);
		} else {
			return null;
		}

	}

	public static void main(String[] args) {

		String[] jsonObject = matchFrontEndDict("bankTransferToCustomer", "024");
		System.out.println(JSON.toJSONString(jsonObject));
	}


	public static JSONArray getFrontEndDict(String key) {
		Locale locale = LoginUtil.currentLocale();
		if ("th_TH".toUpperCase().equals(locale.toString().toUpperCase())) {

			key = key + "_thai.json";
		} else {
			key = key + "_en.json";
		}

		return FrontEndDictionary.getResultDict().get(key);
	}

	public static String getBankName(String paymentMethod, String bankCode){ // 1: cash, 2:cheque, 3:credit card, 4: cash plus, 5:ddba
		String fileName = "";
		switch(paymentMethod){
			case "3": fileName="bankDirectDebitCreditCard";break;
			case "5": fileName="bankDirectDebitBankAccount";break;
			default: break;
		}
		if(StringUtils.isEmpty(fileName)) return fileName;

		String[] jsonObject = matchFrontEndDict(fileName, bankCode);
		return JSON.toJSONString(jsonObject[0]);
	}

	public static String[] matchFrontEndDict(String key, String frontEndValue) {

		try {

			JSONArray dictArr = getFrontEndDict(key);
			if (dictArr == null || StringUtils.isEmpty(frontEndValue)) {
				return null;
			}
			String[] split = frontEndValue.split(",");
			String[] resultArr = new String[split.length];
			matchItem(dictArr, split, resultArr, 0);

			return resultArr;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	private static void matchItem(JSONArray dictArr, String[] valueArr, String[] resultArr, int index) {
		int length = valueArr.length;
		if (index > length - 1) {
			return;
		}
		String value = valueArr[index];

		if (null == dictArr || dictArr.size() == 0) {
			return;
		}

		for (Object dictObj : dictArr) {
			JSONObject jsonObject = (JSONObject) dictObj;
			if (jsonObject.getString("value").equals(value)) {
				resultArr[index] = jsonObject.getString("label");

				if (index != length - 1) {
					int nextIndex = index + 1;
					matchItem(jsonObject.getJSONArray("children"), valueArr, resultArr, nextIndex);
				}
				return;

			}

		}

		return;

	}


}
