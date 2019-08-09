package com.neo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InformOperation {

    public static Integer objectToInt(Object object) {
        Integer result = null;
        if (object == null) {

        } else {

            result = (int)object;
        }


        return result;
    }

    public static void removeByName(JSONObject jsonObject, List<String> names) {
        if (names != null) {
            for (String name : names) {
                jsonObject.remove(name);
            }
        }
    }

    public static void removeByName(JSONArray jsonArray, String... names) {

        StringBuilder builder = new StringBuilder();
        builder.append(",");
        for (String name : names) {
            builder.append(name + ",");
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            Object obj = jsonArray.get(i);
            if (obj instanceof JSONObject &&
                    builder.toString().contains("," + ((JSONObject) obj).getString("name") + ",")) {
                jsonArray.remove(i);
                i--;
            }
        }

    }

    public static JSONObject findByName(JSONArray jsonArray, String name) {
        if (jsonArray == null || name == null) {
            return null;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            Object obj = jsonArray.get(i);
            if (obj instanceof JSONObject) {
                if (name.equals(((JSONObject) obj).getString("name"))) {
                    return (JSONObject) obj;
                }
            }
        }
        return null;
    }

    public static void operateObjectShow(JSONObject jsonObject, boolean show) {
        jsonObject.put("show", show);
    }

    public static void operateArrayShow(JSONArray jsonArray, boolean show, String... names) {
        for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            operateObjectShow(object, show);
        }
    }

    public static <T> T copyObject(T source) {
        T result = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(source);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            result = (T) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void buildOptionsObject(JSONObject jsonObject) {
        Object dictKey = jsonObject.get("dictKey");
        if (dictKey != null && !"".equals(dictKey)) {
            JSONArray jsonArray = buildOptions(dictKey.toString());
            if (jsonArray != null) {
                jsonObject.put("options", jsonArray);
            }
        }
        Set entrySet = jsonObject.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object object = entry.getValue();
            if (object instanceof JSONObject) {
                buildOptionsObject((JSONObject) object);
            } else if (object instanceof JSONArray) {
                buildOptionsArray((JSONArray) object);
            }
        }
    }

    static void buildOptionsArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            Object object = jsonArray.get(i);
            if (object instanceof JSONObject) {
                buildOptionsObject((JSONObject) object);
            } else if (object instanceof JSONArray) {
                buildOptionsArray((JSONArray) object);
            }
        }
    }

    public static JSONArray buildOptions(String dictKey) {
        JSONArray jsonArray = new JSONArray();
        DItem dItem = DictUtils.getDict(dictKey);
        if (dItem == null) {
            return null;
        }
        List<DItem> items = dItem.getItems();
        for (int i = 0; i < items.size(); i++) {
            DItem item = items.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label", item.getName());
            jsonObject.put("value", item.getCode());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static JSONObject findInArray(String name, JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (name.equals(jsonObject.getString("name"))) {
                return jsonObject;
            }
        }
        return null;
    }


    public static JSONArray buildTableArray(JSONArray jsonArray) {
        JSONArray result = new JSONArray();

        if (jsonArray == null) {
            return result;
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            result.add(jsonObject.getString("label"));
        }

        return result;
    }

    public static String objectToString(Object object) {
        String r = null;
        if (object != null) {

            r = object.toString();
        }

        return r;

    }

}
