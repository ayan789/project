package com.neo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Data
public class QuestContainer {
    private static Logger log = LoggerFactory.getLogger(QuestContainer.class);
    private static final String RPQ_ANA = "rpqAnalysis1";
    private static final String RPQ_QUE = "rpqFna1";

    static Map<String, Class<? extends QuestBase>> clsMap = new HashMap<>();

    static {
        clsMap.put("sec1", UlSec1.class);
        clsMap.put("sec2", UlSec2.class);
    }

    public static Class<? extends QuestBase> getQuestCls(String id) {
        return clsMap.get(id);
    }

    LinkedHashMap<String, QuestBase> map = new LinkedHashMap();

    public void put(String id, QuestBase quest) {
        map.put(id, quest);
    }

    public QuestBase getQuest(String id) {
        return map.get(id);
    }

    public String outputData() {
        return JSON.toJSONString(map);
    }

    public static QuestContainer buildFromSource(JSONObject jsonObject) {
        QuestContainer questContainer = new QuestContainer();

        Set entrySet = jsonObject.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String id = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof JSONObject) {
                JSONObject object = (JSONObject) obj;
                Class<? extends QuestBase> cls = getQuestCls(id);
                try {
                    QuestBase questBase = cls.newInstance();
                    questBase.write(object);
                    questContainer.put(id, questBase);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return questContainer;
    }

    public JSONObject writeData(JSONObject jsonObject, InsureFactors insureFactors, boolean writeData) {
        Set entrySet = jsonObject.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String id = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof JSONObject) {
                JSONObject object = (JSONObject) obj;
                QuestBase questBase = getQuest(id);
                if (questBase == null) {
                    try {
                        questBase = getQuestCls(id).newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (questBase != null) {
                    if (writeData) {
                        questBase.write(object);
                    }
                    questBase.handle(object, insureFactors);
                }
            }
        }
        return jsonObject;
    }
    public JSONObject writeData(JSONObject jsonObject, InsureFactors insureFactors, boolean writeData,int score) {
        Set entrySet = jsonObject.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String id = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof JSONObject) {
                JSONObject object = (JSONObject) obj;
                QuestBase questBase = getQuest(id);
                if (questBase == null) {
                    try {
                        questBase = getQuestCls(id).newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (questBase != null) {
                    if (writeData) {
                        if (RPQ_ANA.equals(id) || RPQ_QUE.equals(id)) {
                            questBase.write(object, score);
                        } else {
                            questBase.write(object);
                        }
                    } else {
                        if (RPQ_ANA.equals(id) || RPQ_QUE.equals(id)) {
                            questBase.write(object, score);
                        }
                    }
                    questBase.handle(object, insureFactors);
                }
            }
        }
        return jsonObject;
    }

    public static QuestContainer buildFromData(JSONObject jsonObject) {
        QuestContainer questContainer = new QuestContainer();
        Set entrySet = jsonObject.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof JSONObject) {
                JSONObject object = (JSONObject) obj;
                questContainer.put(key, JSON.toJavaObject(object, getQuestCls(key)));
            }
        }
        return questContainer;
    }

}
