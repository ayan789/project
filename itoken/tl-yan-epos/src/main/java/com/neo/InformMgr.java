package com.neo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class InformMgr {

    public static final String INFORM_TYPE_LIFE = "life";
    public static final String INFORM_TYPE_HEALTH = "health";
    public static final String INFORM_TYPE_KYC = "kyc";
    public static final String INFORM_TYPE_FATCA = "fatca";
    public static final String INFORM_TYPE_FNA = "fna";


    public static final String INFORM_TYPE_UL_LIFE = "ullife";
    public static final String INFORM_TYPE_UL_HEALTH = "ulhealth";
    public static final String INFORM_TYPE_UL_FATCA = "ulfatca";
    public static final String INFORM_TYPE_UL_QUESTIONNAIRE = "rpqQuestionnaire";
    public static final String INFORM_TYPE_UL_ANALYSIS = "rpqAnalysis";
    public static final String INFORM_TYPE_UL_SEC = "secNotification";
    public static final String INFORM_TYPE_UL_KYC = "ulkyc";
    public static final String INFORM_TYPE_UL_ASSESSMENT = "knowledgeAssessment";

    Map<String, List<Define>> map = new HashMap<>();

    @PostConstruct
    public void init() {
        add(new Define(INFORM_TYPE_LIFE, "/static/inform/life.json", Locale.US));
        add(new Define(INFORM_TYPE_LIFE, "/static/inform/life_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_HEALTH, "/static/inform/health.json", Locale.US));
        add(new Define(INFORM_TYPE_HEALTH, "/static/inform/health_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_KYC, "/static/inform/kyc.json", Locale.US));
        add(new Define(INFORM_TYPE_KYC, "/static/inform/kyc_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_FATCA, "/static/inform/fatca.json", Locale.US));
        add(new Define(INFORM_TYPE_FATCA, "/static/inform/fatca_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_FNA, "/static/inform/fna.json", Locale.US));
        add(new Define(INFORM_TYPE_FNA, "/static/inform/fna_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_HEALTH, "/static/inform/ulhealth.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_HEALTH, "/static/inform/ulhealth_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_LIFE, "/static/inform/ullife.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_LIFE, "/static/inform/ullife_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_FATCA, "/static/inform/ulfatca.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_FATCA, "/static/inform/ulfatca_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_KYC, "/static/inform/ulkyc.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_KYC, "/static/inform/ulkyc_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_QUESTIONNAIRE, "/static/inform/rpqQuestionnaire.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_QUESTIONNAIRE, "/static/inform/rpqQuestionnaire_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_ANALYSIS, "/static/inform/rpqAnalysis.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_ANALYSIS, "/static/inform/rpqAnalysis_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_SEC, "/static/inform/secNotification.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_SEC, "/static/inform/secNotification_th.json", LocaleMessage.THAI));

        add(new Define(INFORM_TYPE_UL_ASSESSMENT, "/static/inform/knowledgeAssessment.json", Locale.US));
        add(new Define(INFORM_TYPE_UL_ASSESSMENT, "/static/inform/knowledgeAssessment_th.json", LocaleMessage.THAI));
    }

    void add(Define define) {
        String type = define.type;
        List<Define> list = map.get(type);
        if (list == null) {
            list = new ArrayList<>();
            map.put(type, list);
        }
        list.add(define);
    }

    JSONObject getInformSource(String type) {

        Locale currentLocale = LoginUtil.currentLocale();
        List<Define> list = map.get(type);

        for (Define define : list) {
            if (currentLocale.getLanguage().equals(define.locale.getLanguage())) {
                return define.jsonObject;
            }
        }

        return null;
    }

    public static JSONObject loadJSON(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(path).getInputStream();
            String json = FileTool.loadInput(inputStream);
            if (StringTool.isNotNull(json)) {
                return JSON.parseObject(json, Feature.OrderedField);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    static class Define {

        String type;
        String path;
        Locale locale;
        JSONObject jsonObject;

        public Define(String type, String path, Locale locale) {
            this.type = type;
            this.path = path;
            this.locale = locale;
            this.jsonObject = loadJSON(path);
        }

    }
}
