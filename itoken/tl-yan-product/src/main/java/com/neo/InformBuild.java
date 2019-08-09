package com.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.neo.InformMgr.*;

import java.util.*;


@Component
public class InformBuild {

    private static Logger log = LoggerFactory.getLogger(InformBuild.class);


    public JSONObject loadSec(JSONObject source, InsureFactors insureFactors) {
        List<String> removes = new ArrayList<>();
        if (insureFactors.valueOf("SEC_SPOUSE_STATUS == true").booleanValue()||insureFactors.valueOf("SEC_STATUS == false").booleanValue()){
            removes.add("sec1");
        }
        InformOperation.removeByName(source, removes);
        SInformData sInformData = insureFactors.getSInsure().getInformData();
        if (sInformData != null && sInformData.getSecDataObject() != null) {
            QuestContainer container = QuestContainer.buildFromData(sInformData.getSecDataObject());
            container.writeData(source, insureFactors, true);
        } else {
            QuestContainer container = QuestContainer.buildFromSource(source);
            container.writeData(source, insureFactors, false);
        }
        return source;
    }

    @SuppressWarnings("Duplicates")
    public JSONObject loadInform(String type, String insureId) {
        JSONObject source = getInformSource(type);
        InsureFactorsBuild insureFactorsBuild = new InsureFactorsBuild();
        InsureFactors insureFactors = insureFactorsBuild.buildInsureFactors(insureId);
        if (type.equals(INFORM_TYPE_UL_SEC)){
            return  loadSec(source, insureFactors);
        }
        return null;
    }

    JSONObject getInformSource(String type) {
        InformMgr informMgr = new InformMgr();
        JSONObject source = informMgr.getInformSource(type);
        if (source != null) {
            return InformOperation.copyObject(source);
        }
        return null;
    }




}
