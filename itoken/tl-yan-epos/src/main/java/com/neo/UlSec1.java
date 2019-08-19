package com.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by za-wanglinlin on 2018/11/8.
 */
@Data
public class UlSec1 extends QuestBase {


    String title;
    String givenName;
    String familyName;
    String idType;
    String idNumber;
    String birthday;
    String iDNumberPassport;
    String age;

    Map buildMap() {
        Map map = new HashMap();
        map.put("sec1TitleSelect", title);
        map.put("sec1GivenName", givenName);
        map.put("sec1FamilyName", familyName);
        map.put("sec1IDTypeSelect", idType);
        map.put("sec1IDNumberCard", idNumber);
        map.put("sec1DOB", birthday);
        map.put("sec1IDNumberPassport", iDNumberPassport);
        map.put("sec1Age", InformOperation.objectToInt(age));
        return map;
    }

    public void read(JSONObject jsonObject) {
        JSONArray exArray = jsonObject.getJSONArray(EXTRA_OPTIONS_ARR_KEY);
        Map valueMap = arrayReadToMap(exArray);
        title = (String) valueMap.get("sec1TitleSelect");
        givenName = (String) valueMap.get("sec1GivenName");
        familyName = (String) valueMap.get("sec1FamilyName");
        idType = (String) valueMap.get("sec1IDTypeSelect");
        idNumber = (String) valueMap.get("sec1IDNumberCard");
        birthday = (String) valueMap.get("sec1DOB");
        iDNumberPassport = (String)valueMap.get("sec1IDNumberPassport");
        age =InformOperation.objectToString(valueMap.get("sec1Age"));
    }

    public void write(JSONObject jsonObject) {
        Map valueMap = buildMap();
        JSONArray exArray = jsonObject.getJSONArray(EXTRA_OPTIONS_ARR_KEY);
        arrayWriteFromMap(exArray, valueMap);
    }

    public void handle(JSONObject jsonObject, InsureFactors insureFactors) {
        InformOperation.buildOptionsObject(jsonObject);
    }

}
