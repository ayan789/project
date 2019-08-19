package com.neo;


import lerrain.tool.formula.FormulaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

@Component
@Slf4j
public class PagesMgr {

	static String rootPath = "/";
    static Map<String, Page> pageMap;

    static{
    	init();
    }
    
    public static void init() {
        reload();

        log.info("*********ViewModel加载完成*********" + pageMap);
    }

    public static void reload() {
        pageMap = new HashMap<>();
        load("pages.xml");
//        load("payPage.xml");
    }

    public static void load(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(rootPath + path).getInputStream();
            XmlNode xmlNode = XmlNode.nodeOf(inputStream);
            List<XmlNode> pageNodes = xmlNode.getChildren("page");
            if (pageNodes != null) {
                for (XmlNode node : pageNodes) {
                    parsePage(node);
                }
            }
        } catch (Exception e) {
            log.warn("***", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    static void parseOption(Field field, XmlNode xmlNode) {
        String dictKey = xmlNode.getAttribute("dictKey");
        field.setDictKey(FormulaUtil.formulaOf(dictKey));
        List<FieldOption> options = new ArrayList<>();
//        if (StringTool.isNotNull(dictKey)) {
//            DItem dItem = DictUtils.getDict(dictKey);
//            List<DItem> list = dItem.getItems();
//            if (list != null) {
//                for (DItem item : list) {
//                    options.add(FieldOption.obtain(item.getCode(), item.getName()));
//                }
//            }
//        }
        for (Iterator iterator = xmlNode.getChildren("option").iterator(); iterator.hasNext(); ) {
            XmlNode e = (XmlNode) iterator.next();
            String keyFormula = e.getAttribute("keyFormula");
            String valueFormula = e.getAttribute("valueFormula");
            FieldOption fieldOption = FieldOption.obtain(e.getAttribute("key"), e.getAttribute("value"));
            if (keyFormula != null) {
                fieldOption.keyFormula = FormulaUtil.formulaOf(keyFormula);
            }
            if (valueFormula != null) {
                fieldOption.valueFormula = FormulaUtil.formulaOf(valueFormula);
            }
            String visible = e.getAttribute("visible");
            fieldOption.visible = FormulaUtil.formulaOf(visible);
            options.add(fieldOption);
        }
        field.setOptions(options);
    }

    static void parseFieldRule(Field field, XmlNode xmlNode) {
        List<FieldRule> rules = new ArrayList<>();
        for (Iterator iterator = xmlNode.getChildren("rule").iterator(); iterator.hasNext(); ) {
            XmlNode e = (XmlNode) iterator.next();
            boolean required = !"false".equals(e.getAttribute("required"));
            rules.add(FieldRule.obtain(required, e.getAttribute("message"), e.getAttribute("patternKey"), e.getAttribute("condition")));
        }
        field.setRules(rules);
    }

    static void parseField(Page page, XmlNode xmlNode) {
        Field field = new Field();
        field.setUniqueID(xmlNode.getAttribute("uniqueID"));
        field.setType(xmlNode.getAttribute("type"));
        field.setSubType(xmlNode.getAttribute("subType"));
        field.setLabel(xmlNode.getAttribute("label"));
        field.setPlaceHolder(xmlNode.getAttribute("placeHolder"));
        String maxLength = xmlNode.getAttribute("maxLength");
        if (StringTool.isNotNull(maxLength)) {
            field.setMaxLength(Integer.valueOf(maxLength));
        }
        field.setValue(FormulaUtil.formulaOf(xmlNode.getAttribute("value")));
        field.setDisabled(FormulaUtil.formulaOf(xmlNode.getAttribute("disabled")));
        field.setShow(FormulaUtil.formulaOf(xmlNode.getAttribute("show")));
        if ("false".equals(xmlNode.getAttribute("necessary"))) {
            field.setNecessary(false);
        }
        field.setVisible(FormulaUtil.formulaOf(xmlNode.getAttribute("visible")));
        field.setAttr(parseMap(xmlNode.getAttribute("attr")));
        field.setFieldDecorator(parseMap(xmlNode.getAttribute("fieldDecorator")));

        List<XmlNode> children = xmlNode.getChildren();
        for (XmlNode e : children) {
            if ("rules".equals(e.getName())) {
                parseFieldRule(field, e);
            } else if ("options".equals(e.getName())) {
                parseOption(field, e);
            }
        }
        page.addField(field);
    }

    static void parsePage(XmlNode xmlNode) {
        Page page = new Page();
        String id = xmlNode.getAttribute("id");
        String preCheck = xmlNode.getAttribute("preCheck");
        String locale = xmlNode.getAttribute("locale");
        page.setLocale(LocaleMgr.findLocale(locale));
        page.setPageId(id);
        page.setPreCheck("true".equals(preCheck));
        for (Iterator iterator = xmlNode.getChildren("field").iterator(); iterator.hasNext(); ) {
            parseField(page, (XmlNode) iterator.next());
        }
        addPage(page);
    }

    static Map<String, String> parseMap(String str) {
        Map<String, String> map = new LinkedHashMap<>();
        if (StringTool.isNull(str)) {
            return map;
        }
        String[] arr = str.split(";");
        for (String s : arr) {
            String[] v = s.split(":");
            map.put(v[0].trim(), v[1].trim());
        }

        return map;
    }


     public static void addPage(Page page) {
        pageMap.put(page.getPageId() + "-" + page.getLocale(), page);
    }

    public static Page getPage(String pageId, Locale locale) {
    	System.out.println(locale);
    	System.out.println(pageMap);
        Page page = pageMap.get(pageId + "-" + locale);
        if (page != null) {
            return page.copy();
        }
        return null;
    }

}
