package com.neo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by za-wanglinlin on 2018/11/8.
 */
@Data
public class UlSec2 extends QuestBase {

    private final int SEVENTEEN_AGE = 17;
    private final int NINETEEN_AGE = 19;
    private final int TWENTY_AGE = 20;
    private  final String IS_MARRIED_REGISTER = "Y";
    private final String RELATIONSHIP_CHILD = "Child";
    private final String ELDER_BROTHER_SISTER = "Elder brother/sister";
    private final String YOUNGER_BROTHER_SISTER = "Younger brother/sister";
    private final String MARITAL_STATUS_S = "S";
    private final String MARITAL_STATUS_D = "D";
    private final String MARITAL_STATUS_W = "W";
    private final String MARITAL_STATUS = "M";
    private final String IS_MARRIED_REGISTER_N = "N";

    List<Map> items;
    Map itemsMap;
    String flag;
    Boolean isDelete;

    Map buildMap(Item item) {
        Map map = new HashMap();
        map.put("sec2TitleSelect", item.getTitle());
        map.put("sec2GivenName", item.getGivenName());
        map.put("sec2FamilyName", item.getFamilyName());
        map.put("sec2IDTypeSelect", item.getIdType());
        map.put("sec2IDNumberCard", item.getIdNumber());
        map.put("sec2DOB", item.getBirthday());
        map.put("sec2Age", InformOperation.objectToInt(item.getAge()));
        map.put("sec2IDNumberPassport", item.getIDNumberPassport());
        return map;
    }

    Map buildMap(JSONObject jsonObject) {
        Map map = new HashMap();
        map.put("sec2TitleSelect", jsonObject.get("title"));
        map.put("sec2GivenName", jsonObject.get("givenName"));
        map.put("sec2FamilyName", jsonObject.get("familyName"));
        map.put("sec2IDTypeSelect", jsonObject.get("idType"));
        map.put("sec2IDNumberCard", jsonObject.get("idNumber"));
        map.put("sec2DOB", jsonObject.get("birthday"));
        map.put("sec2Age", InformOperation.objectToInt(jsonObject.get("age")));
        map.put("sec2IDNumberPassport", jsonObject.get("iDNumberPassport"));
        return map;
    }

    public void read(JSONObject jsonObject) {
        JSONArray exArray = jsonObject.getJSONArray(EXTRA_OPTIONS_ARR_KEY);
        for (int i = 0; i < exArray.size(); i++) {
            if (items == null) {
                items = new ArrayList<>();
            }
            itemsMap = new HashMap<>();
            JSONObject object = exArray.getJSONObject(i);
            JSONArray itemArray =object.getJSONArray("itemArray");
            isDelete = object.getBoolean("isDelete");
            Map map = arrayReadToMap(itemArray);
            Item item = new Item();
            item.setTitle((String) map.get("sec2TitleSelect"));
            item.setGivenName((String) map.get("sec2GivenName"));
            item.setFamilyName((String) map.get("sec2FamilyName"));
            item.setIdType((String) map.get("sec2IDTypeSelect"));
            item.setIdNumber((String) map.get("sec2IDNumberCard"));
            item.setBirthday((String) map.get("sec2DOB"));
            item.setAge(InformOperation.objectToString(map.get("sec2Age")) );
            item.setIDNumberPassport((String) map.get("sec2IDNumberPassport"));
            /*if (!blankString(item.title, item.givenName, item.familyName,item.idType,item.idNumber,item.birthday,item.age,item.idNumber)) {
                items.add(item);
            }*/
            itemsMap.put("isDelete",isDelete);
            itemsMap.put("item",item);
            items.add(itemsMap);
        }



    }

    @SuppressWarnings("Duplicates")
    public void write(JSONObject jsonObject) {
        JSONArray exArray = jsonObject.getJSONArray(EXTRA_OPTIONS_ARR_KEY);
        JSONObject object = exArray.getJSONObject(0);
        JSONArray itemArray = null;
        String isDelete = null;
        if (CollectionUtils.isNotEmpty(items)) {
            for (int i = 0; i < items.size(); i++) {
                if (i == 0) {
                    itemArray = object.getJSONArray("itemArray");
                    if (null != items.get(i).get("item")) {
                        JSONObject item = (JSONObject) items.get(i).get("item");
                        Map valueMap = buildMap(item);
                        arrayWriteFromMap(itemArray, valueMap);
                        if (null !=items.get(i).get("isDelete")) {
                            object.put("isDelete", Boolean.valueOf(items.get(i).get("isDelete").toString()));
                        }
                    }
                } else {
                    JSONObject copyObject = InformOperation.copyObject(object);
                    itemArray = copyObject.getJSONArray("itemArray");
                    if (null != items.get(i).get("item")) {
                        JSONObject item = (JSONObject) items.get(i).get("item");
                        Map valueMap = buildMap(item);
                        arrayWriteFromMap(itemArray, valueMap);
                        if (null !=items.get(i).get("isDelete")){
                            copyObject.put("isDelete",Boolean.valueOf(items.get(i).get("isDelete").toString()));
                        }
                        exArray.add(copyObject);

                    }
                }
            }

            flag = "Y";
        }
    }


    @Data
    public static class Item {

        String title;
        String givenName;
        String familyName;
        String idType;
        String idNumber;
        String birthday;
        String age;
        String iDNumberPassport;


    }

    Map buildbeneficiary(MCustomer customer) {
        Map map = new HashMap();
        map.put("sec2TitleSelect", customer.getTitle());
        map.put("sec2GivenName", customer.getGivenName());
        map.put("sec2FamilyName", customer.getFamilyName());
        map.put("sec2IDTypeSelect", customer.getIdType());
        map.put("sec2IDNumberCard", customer.getIdNumber());
        map.put("sec2DOB", customer.getBirthday());
        map.put("sec2Age", InformOperation.objectToInt(customer.getAge()));
        map.put("sec2IDNumberPassport", customer.getIdNumber());
        return map;
    }

    public void handle(JSONObject jsonObject, InsureFactors insureFactors) {
        InformOperation.buildOptionsObject(jsonObject);
        int insuredAge = 0;
        if (null != insureFactors.get("insuredAge")) {
            insuredAge = Integer.parseInt(insureFactors.get("insuredAge").toString());
        }
        if (!"Y".equals(flag)&&insureFactors.valueOf("SEC_CHILD_STATUS == true").booleanValue()) {
            List<MCustomer> customerList = new ArrayList<>();
            if (null != insureFactors.get("beneficiary")) {
                List<MCustomer> beneficiary = (List<MCustomer>) insureFactors.get("beneficiary");
                SInsure sInsure = new SInsure();
                if(null != insureFactors.get("sInsure")){
                    sInsure = (SInsure) insureFactors.get("sInsure");
                }

                int age = sInsure.getInsurant().getAge() != null ? Integer.parseInt(sInsure.getInsurant().getAge()) : 0;
                boolean ageflag = age >= SEVENTEEN_AGE && age <= NINETEEN_AGE;
                boolean ageFlag_2 = age <= SEVENTEEN_AGE;
                boolean ageFlag_3 = age >= NINETEEN_AGE;
                boolean marriedFlag_1 = MARITAL_STATUS.equals(sInsure.getInsurant().getMaritalStatus()) || MARITAL_STATUS_S.equals(sInsure.getInsurant().getMaritalStatus()) ||  MARITAL_STATUS_D.equals(sInsure.getInsurant().getMaritalStatus()) || MARITAL_STATUS_W.equals(sInsure.getInsurant().getMaritalStatus() ) ;
                boolean marriedRegisterFlag_1 = IS_MARRIED_REGISTER_N.equals(sInsure.getInsurant().getIsMarriedRegister()) || sInsure.getInsurant().getIsMarriedRegister().isEmpty();
                boolean marriedRegisterFlag_2 = IS_MARRIED_REGISTER.equals(sInsure.getInsurant().getIsMarriedRegister());
                for (MCustomer mCustomer : beneficiary) {
                    String relationShip = mCustomer.relationCode;
                    int beneficiaryAge = StringTool.isNotNull(mCustomer.age) ? Integer.parseInt(mCustomer.age) : 0;
                    boolean relationshipFlag_1 = ELDER_BROTHER_SISTER.equals(relationShip) || YOUNGER_BROTHER_SISTER.equals(relationShip);
                    boolean relationshipFlag_2 = RELATIONSHIP_CHILD.equals(relationShip);
                    boolean beneficiaryAgeFlag = TWENTY_AGE > beneficiaryAge;
                    if(ageflag && marriedFlag_1 && marriedRegisterFlag_1 && relationshipFlag_1 && beneficiaryAgeFlag){
                        customerList.add(mCustomer);
                        jsonObject.put("maxChildNo", 3);
                    }
                    else if(ageflag && marriedRegisterFlag_2 && relationshipFlag_2 && beneficiaryAgeFlag){
                        customerList.add(mCustomer);
                        jsonObject.put("maxChildNo", 3);
                    }
                    else if(ageFlag_2 && relationshipFlag_1 && beneficiaryAgeFlag){
                        customerList.add(mCustomer);
                        jsonObject.put("maxChildNo", 3);
                    }
                    else if(ageFlag_3 && relationshipFlag_2 && beneficiaryAgeFlag){
                        customerList.add(mCustomer);
                        jsonObject.put("maxChildNo", 3);
                    }

//                    if (insuredAge >= TWENTY_AGE && RELATIONSHIP_CHILD.equals(relationShip) && beneficiaryAge < TWENTY_AGE) {
//                        customerList.add(mCustomer);
//                        jsonObject.put("maxChildNo", 3);
//                    } else if (insuredAge < TWENTY_AGE && (ELDER_BROTHER_SISTER.equals(relationShip) || YOUNGER_BROTHER_SISTER.equals(relationShip)) && beneficiaryAge < TWENTY_AGE) {
//                        customerList.add(mCustomer);
//                        jsonObject.put("maxChildNo", 3);
//                    }
//                    else if (insuredAge >= SEVENTEEN_AGE && insuredAge <= NINETEEN_AGE && !IS_MARRIED_REGISTER.equals(isMarriedRegisterFlag)){
//                        customerList.add(mCustomer);
//                        jsonObject.put("maxChildNo", 3);
//                    }

                }
            }

            JSONArray exArray = jsonObject.getJSONArray(EXTRA_OPTIONS_ARR_KEY);
            JSONArray itemArray = null;
            if (CollectionUtils.isNotEmpty(customerList)) {
                int maxChildNo = 0;
                int childNoSize = Integer.parseInt(jsonObject.get("maxChildNo").toString());
                if (customerList.size() > childNoSize) {
                    maxChildNo = childNoSize;
                } else if (customerList.size() <= childNoSize) {
                    maxChildNo = customerList.size();
                }

                JSONObject object = exArray.getJSONObject(0);
                for (int i = 0; i < maxChildNo; i++) {
                    if (i == 0) {
                        itemArray = object.getJSONArray("itemArray");
                        Map valueMap = buildbeneficiary(customerList.get(i));
                        arrayWriteFromMap(itemArray, valueMap);
                    } else {
                        JSONObject copyObject = InformOperation.copyObject(object);
                        itemArray = copyObject.getJSONArray("itemArray");
                        Map valueMap = buildbeneficiary(customerList.get(i));
                        arrayWriteFromMap(itemArray, valueMap);
                        exArray.add(copyObject);
                    }
                }
            }
            for (int j =0 ;j<exArray.size();j++){
                JSONObject object = exArray.getJSONObject(j);
                object.put("isDelete",false);
            }
        } else if (insuredAge >= TWENTY_AGE) {
            jsonObject.put("maxChildNo", 3);
        } else if (insuredAge < TWENTY_AGE) {
            jsonObject.put("maxChildNo", 3);
        }

    }
}
