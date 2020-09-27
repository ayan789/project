package com.ccic.salesapp.noncar.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	 public void setJsonPointerValue(ObjectNode node, JsonPointer pointer, JsonNode value) {
	     JsonPointer parentPointer = pointer.head();
	     JsonNode parentNode = node.at(parentPointer);
	     String fieldName = pointer.last().toString().substring(1);

	     if (parentNode.isMissingNode() || parentNode.isNull()) {
	         parentNode = StringUtils.isNumeric(fieldName) ? mapper.createArrayNode() : mapper.createObjectNode();
	         setJsonPointerValue(node,parentPointer, parentNode); // recursively reconstruct hierarchy
	     }

	     if (parentNode.isArray()) {
	         ArrayNode arrayNode = (ArrayNode) parentNode;
	         int index = Integer.valueOf(fieldName);
	         // expand array in case index is greater than array size (like JavaScript does)
	         for (int i = arrayNode.size(); i <= index; i++) {
	             arrayNode.addNull();
	         }
	         arrayNode.set(index, value);
	     } else if (parentNode.isObject()) {
	         ((ObjectNode) parentNode).set(fieldName, value);
	     } else {
	         throw new IllegalArgumentException("`" + fieldName + "` can't be set for parent node `"
	                 + parentPointer + "` because parent is not a container but " + parentNode.getNodeType().name());
	     }
	 }
	 
	 /***
	  * 获取json节点key与value的映射
	  * @param path 初始路径
	  * @param json 要读取的json
	  * @return
	  */
	 public Map<String,Object> listNames(String path,JsonNode json) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 if(path==null) {
			 path = "";
		 }
		 if(json ==null) {
			 return map;
		 }
		 if(json.isValueNode()) {
			 if(json.isFloat()) {
				 map.put(path, json.asDouble());
			 }else {
				 map.put(path, json.asText());
			 }
		 }else  if(json.isObject()) {
			 Iterator<String> nodeNames = json.fieldNames();
			 for (Iterator iterator = nodeNames; iterator.hasNext();) {
				String fieldName = (String) iterator.next();
				String supath = path + "/" + fieldName;
				Map oMap  = listNames( supath, json.findValue(fieldName));
				map.putAll(oMap);
			}
		 }else if(json.isArray()) {
			for (int i = 0; i < json.size(); i++) {
				String supath =  path + "/" + i;
				Map oMap  = listNames( supath, json.get(i));
				map.putAll(oMap);
			}
		 }
		return map;
	 }
}
