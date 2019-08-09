package com.neo;

import java.io.InputStream;
import java.util.*;


import org.springframework.core.io.ClassPathResource;


import lerrain.tool.formula.FormulaUtil;
import net.sf.json.JSONObject;

public class ProgressMgr {
	 static String rootPath = "/";
	 static Map<String, Progress> progressMap;
	 
	public static void main(String[] args) {
	    progressMap = new HashMap<>();
        load("progress.xml");
        JSONObject jsonObject = JSONObject.fromObject(progressMap);
        String json = jsonObject.toString();
        System.out.println("*********ProgressModel加载完成*********" + progressMap);
        System.out.println(json);
	}
	
	public static void load(String path) {
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
            System.out.println("***");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }
	
	static void parseProgress(XmlNode xmlNode) {
        Progress progress = new Progress();
        String locale = xmlNode.getAttribute("locale");
        progress.setLocale(LocaleMgr.findLocale(locale));
        progress.setProgressId(xmlNode.getAttribute("id"));
        for (Iterator iterator = xmlNode.getChildren("step").iterator(); iterator.hasNext(); ) {
            parseStep(progress, (XmlNode) iterator.next(), null);
        }
        addProgress(progress);
    }
	

    static void parseStep(Progress progress, XmlNode xmlNode, Progress.Step parent) {
        Progress.Step step = new Progress.Step();
        step.setLabel(xmlNode.getAttribute("label"));
        step.setStepKey(xmlNode.getAttribute("key"));
        step.setTitle(xmlNode.getAttribute("title"));
        step.setVisible(FormulaUtil.formulaOf(xmlNode.getAttribute("visible")));

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
    
    public static void addProgress(Progress progress) {
        progressMap.put(progress.getProgressId() + "-" + progress.getLocale(), progress);
    }

    public Progress getProgress(String progressId, Locale locale) {
        return progressMap.get(progressId + "-" + locale);
    }
}
