package com.example.tlyanepos;

import com.neo.DocumentDefine;
import com.neo.LocaleMgr;
import com.neo.XmlNode;
import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.FormulaUtil;
import lerrain.tool.formula.Value;
import lerrain.tool.script.InsureFactors;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.*;

public class ProgressMgr {

    String rootPath = "/";
    Map<String, ProgressDefine> progressMap = new HashMap<>();




//    public void init() {
//
//    }

    public Map getProgressList() {
        progressMap = new HashMap<>();
        load("progress.xml");
        System.out.println("*********ProgressModel加载完成*********" + progressMap);
        return progressMap;
    }

    public void load(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(rootPath + path).getInputStream();
            XmlNode xmlNode = XmlNode.nodeOf(inputStream);
            List<XmlNode> pageNodes = xmlNode.getChildren("progress");
            if (pageNodes != null) {
                for (XmlNode node : pageNodes) {
                    parseProgress(node);
                }
            }
        } catch (Exception e) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    void parseProgress(XmlNode xmlNode) {
        ProgressDefine progress = new ProgressDefine();
        String locale = xmlNode.getAttribute("locale");
        progress.setLocale(LocaleMgr.findLocale(locale));
        progress.setProgressId(xmlNode.getAttribute("id"));
        for (Iterator iterator = xmlNode.getChildren("step").iterator(); iterator.hasNext(); ) {
            parseStep(progress, (XmlNode) iterator.next(), null);
        }
        addProgress(progress);
    }

    void parseStep(ProgressDefine progress, XmlNode xmlNode, ProgressDefine.Step parent) {
        ProgressDefine.Step step = new ProgressDefine.Step();
        step.setLabel(xmlNode.getAttribute("label"));
        step.setStepKey(xmlNode.getAttribute("key"));
        step.setTitle(xmlNode.getAttribute("title"));
        step.setVisible(FormulaUtil.formulaOf(xmlNode.getAttribute("visible")));

        InsureFactors insureFactors = new InsureFactors();
        Formula visible = step.visible;
        if (visible == null || Value.booleanOf(visible, insureFactors)) {
            if (parent != null) {
                parent.addChild(step);
                step.setParentStepKey(parent.getStepKey());
            }

            if (parent == null) {
                progress.addStep(step);
            }

            for (Iterator iterator = xmlNode.getChildren("step").iterator(); iterator.hasNext(); ) {
                parseStep(progress, (XmlNode) iterator.next(), step);
            }
        }

    }

    public void addProgress(ProgressDefine progress) {
        progressMap.put(progress.getProgressId() + "-" + progress.getLocale(), progress);
    }

    public ProgressDefine getProgress(String progressId, Locale locale) {
        return progressMap.get(progressId + "-" + locale);
    }

}
