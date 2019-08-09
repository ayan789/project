package com.neo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.Data;

@Data
public class SInformData {

    String insureId;
    String lifeData;
    String healthData;
    String fatcaData;
    String kycData;
    String fnaData;
    String questionnaireData;
    String analysisData;
    String secData;
    String knowledgeAssessment;

    JSONObject lifeDataObject;
    JSONObject healthDataObject;
    JSONObject fatcaDataObject;
    JSONObject kycDataObject;
    JSONObject fnaDataObject;
    JSONObject questionnaireDataObject;
    JSONObject analysisDataObject;
    JSONObject secDataObject;
    JSONObject knowledgeAssessmentObject;

    public void covert() {
        if (lifeData != null) {
            lifeDataObject = JSON.parseObject(lifeData, Feature.OrderedField);
        }
        if (healthData != null) {
            healthDataObject = JSON.parseObject(healthData, Feature.OrderedField);
        }
        if (fatcaData != null) {
            fatcaDataObject = JSON.parseObject(fatcaData, Feature.OrderedField);
        }
        if (kycData != null) {
            kycDataObject = JSON.parseObject(kycData, Feature.OrderedField);
        }
        if (fnaData != null) {
            fnaDataObject = JSON.parseObject(fnaData, Feature.OrderedField);
        }
        if (questionnaireData != null) {
            questionnaireDataObject = JSON.parseObject(questionnaireData, Feature.OrderedField);
        }
        if (analysisData != null) {
            analysisDataObject = JSON.parseObject(analysisData, Feature.OrderedField);
        }
        if (secData != null) {
            secDataObject = JSON.parseObject(secData, Feature.OrderedField);
        }
        if (knowledgeAssessment !=null){
            knowledgeAssessmentObject = JSON.parseObject(knowledgeAssessment, Feature.OrderedField);
        }
        lifeData = null;
        healthData = null;
        fatcaData = null;
        kycData = null;
        fnaData = null;
        questionnaireData = null;
        analysisData = null;
        secData = null;
        knowledgeAssessment = null;
    }
}
