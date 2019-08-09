package com.neo;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lerrain.tool.formula.FormulaUtil;
import lerrain.tool.formula.Value;
import javax.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;

@Component
public class DocumentMgr {

	//hide for temprary
	Map<String, List<GroupDocumentDefine>> groupDocDefineMap = new HashMap<>();
	Map<String, List<GroupDocumentDefine>> ulGroupDocDefineMap = new HashMap<>();

	public void init() {
		load("/static/document/document.xml");
		load("/static/document/document_th.xml");

		//after submit
		//OL
		loadPostDocumentDefine("/static/document/document_group.xml");

		//UL
		loadULPostDocumentDefine("/static/document/document_group_ul.xml");

	}

	Map<String, List<DocumentDefine>>  map = new HashMap<>();
	 
//	@PostConstruct
//	public  void init() {
//		load("document.xml");
//	}

	void load(String path) {
		InputStream inputStream = null;
		try {
			inputStream = new ClassPathResource(path).getInputStream();
			XmlNode xmlNode = XmlNode.nodeOf(inputStream);
			String language = xmlNode.getAttribute("language");

			List<DocumentDefine> docList = map.get(language);
			if (docList == null) {
				docList = new ArrayList<>();
				map.put(language, docList);
			}

			List<XmlNode> childList = xmlNode.getChildren("document");
			for (XmlNode child : childList) {
				docList.add(parseDocument(child));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}
	
	public List<DocumentDefine> getDocumentList(Factors factors) {
		load("document.xml");
        List<DocumentDefine> docList = getDocuments();
        List<DocumentDefine> result = new ArrayList<>();
        for (DocumentDefine documentDef : docList) {
            Formula visible = documentDef.visible;
            try {
                if (visible == null || Value.booleanOf(visible, factors)) {
                    result.add(documentDef);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }



	
	public List<DocumentDefine> getDocuments() {
        return map.get(LanguageUtil.getLanguage());
    }

	
	 DocumentDefine parseDocument(XmlNode xmlNode) {

	        String code = xmlNode.getAttribute("code");
	        String visible = xmlNode.getAttribute("visible");
	        String max = xmlNode.getAttribute("max");
	        String min = xmlNode.getAttribute("min");
	        String name = xmlNode.getAttribute("name");
	        String type = xmlNode.getAttribute("type");
	        String docType = xmlNode.getAttribute("docType");
	        String needTopup = xmlNode.getAttribute("needTopup");
	        String value = xmlNode.getAttribute("value");
		 	String text = xmlNode.getText();

	        //hide for temprary
	        String sequenceStr = xmlNode.getAttribute("sequence");
	        int sequence;
	        if(StringTool.isNull(sequenceStr)){
	            sequence = -1;
	        }else{
	            sequence = Integer.valueOf(sequenceStr);
	        }

	        if (StringTool.isNull(visible)) {
	            visible = xmlNode.getText();
	        }

	        //hide for temprary
	        DocumentDefine documentDef = DocumentDefine.build(code, visible, type,sequence);

	        documentDef.setDocName(name);
	        documentDef.setDocType(docType);
	        documentDef.setNeedTopup(needTopup);

	     String a = "1.0";
		 String b = "5.0";
		 String c = "1.0";

		 System.out.println(value);
		 Formula f = FormulaUtil.formulaOf("return " + value);
		 Factors p = new Factors()
		 {
			 public Object get(String name)
			 {
				 System.out.println(name);
				 if ("A1".equals(name))
					 return  new BigDecimal(a);
				 if ("A2".equals(name))
					 return  new BigDecimal(b);
				 if ("A3".equals(name))
					 return  new BigDecimal(c);
				 return null;
			 }
		 };

//		 System.out.println(f.run(p));

		 	documentDef.setValue(f.run(p)+"");

		 if (StringTool.isNotNull(text)) {
				 documentDef.setText(text);
			 }

		 if (StringTool.isNotNull(max)) {
	            documentDef.setMax(Integer.valueOf(max));
	        }

	        if (StringTool.isNotNull(min)) {
	            documentDef.setMin(Integer.valueOf(min));
	        }

	        String needSigns = xmlNode.getAttribute("needSigns");
	        if (StringTool.isNotNull(needSigns)) {
	            String[] arr = needSigns.split(",");
	            documentDef.addNeedSigns(arr);
	        }

	        return documentDef;
	    }

	//hide for temprary
	public List<GroupDocumentDefine> getGroupDocumentDefineList(boolean isUL,Factors factors){
		List<GroupDocumentDefine> groupDefineList = isUL ? getULGroupDocumentDefines() : getGroupDocumentDefines();
		List<GroupDocumentDefine> result = new ArrayList<>();

		for(GroupDocumentDefine groupDefine : groupDefineList){
			List<DocumentDefine> documentDefineList = new ArrayList<>();
			GroupDocumentDefine group = new GroupDocumentDefine(groupDefine.getGroupName(),groupDefine.getCode(),groupDefine.isExpand(),documentDefineList);

			for(DocumentDefine define : groupDefine.getDocumentDefineList()){
				Formula visible = define.visible;
				if (visible == null || Value.booleanOf(visible, factors)) {
					documentDefineList.add(define);
				}
			}
			result.add(group);
		}
		return result;
	}

	public void loadPostDocumentDefine(String path) {
		InputStream inputStream = null;
		try {
			inputStream = new ClassPathResource(path).getInputStream();
			XmlNode xmlNode = XmlNode.nodeOf(inputStream);
			String language = xmlNode.getAttribute("language");

			List<GroupDocumentDefine> groupDocList = groupDocDefineMap.get(language);
			if (groupDocList == null) {
				groupDocList = new ArrayList<>();
				groupDocDefineMap.put(language, groupDocList);
			}

			List<XmlNode> groupList = xmlNode.getChildren("group");
			for (XmlNode group : groupList) {
				GroupDocumentDefine groupDocumentDefine = new GroupDocumentDefine();
				groupDocumentDefine.setGroupName(group.getAttribute("name"));
				groupDocumentDefine.setCode(group.getAttribute("code"));
				groupDocumentDefine.setExpand(Boolean.valueOf(group.getAttribute("expand")));

				List<DocumentDefine> documentDefineList = new ArrayList<>();
				List<XmlNode> childList = group.getChildren("document");
				for(XmlNode child : childList){
					documentDefineList.add(parseDocument(child));
				}
				groupDocumentDefine.setDocumentDefineList(documentDefineList);

				groupDocList.add(groupDocumentDefine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Read document_group_ul.xml
	 * Create the document define with group
	 * @param path
	 */
	//hide for temprary
	void loadULPostDocumentDefine(String path) {
		InputStream inputStream = null;
		try {
			inputStream = new ClassPathResource(path).getInputStream();
			XmlNode xmlNode = XmlNode.nodeOf(inputStream);
			String language = xmlNode.getAttribute("language");

			List<GroupDocumentDefine> groupDocList = ulGroupDocDefineMap.get(language);
			if (groupDocList == null) {
				groupDocList = new ArrayList<>();
				ulGroupDocDefineMap.put(language, groupDocList);
			}

			List<XmlNode> groupList = xmlNode.getChildren("group");
			for (XmlNode group : groupList) {
				GroupDocumentDefine groupDocumentDefine = new GroupDocumentDefine();
				groupDocumentDefine.setGroupName(group.getAttribute("name"));
				groupDocumentDefine.setCode(group.getAttribute("code"));
				groupDocumentDefine.setExpand(Boolean.valueOf(group.getAttribute("expand")));

				List<DocumentDefine> documentDefineList = new ArrayList<>();
				List<XmlNode> childList = group.getChildren("document");
				for(XmlNode child : childList){
					documentDefineList.add(parseDocument(child));
				}
				groupDocumentDefine.setDocumentDefineList(documentDefineList);

				groupDocList.add(groupDocumentDefine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}

	public List<GroupDocumentDefine> getULGroupDocumentDefines() {
		return ulGroupDocDefineMap.get(LanguageUtil.getLanguage());
	}

	public List<GroupDocumentDefine> getGroupDocumentDefines() {
		return groupDocDefineMap.get(LanguageUtil.getLanguage());
	}

}
