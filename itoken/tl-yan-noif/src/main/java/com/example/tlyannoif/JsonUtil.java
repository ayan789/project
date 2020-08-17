package com.example.tlyannoif;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws JsonProcessingException, IOException {
//		JsonUtil ju = new JsonUtil();
//		JsonPointer pointer = JsonPointer.compile("/policyLobList/0/policyRiskList/0/insuredGroupList/0/numberOfCopies");
//		RequestBody initRequest = null;
//		initRequest = new AccidentQuoteRequest();
//		ObjectNode node = (ObjectNode) mapper.readTree(mapper.writeValueAsString(initRequest));
//		JsonNode value =  new TextNode("1");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/policyLobList/0/policyRiskList/0/insuredGroupList/1/numberOfCopies");
//		value =  new TextNode("2");
//		ju.setJsonPointerValue(node, pointer, value);
//		System.out.println(node);
		
//		JsonUtil ju = new JsonUtil();
//		JsonPointer pointer = JsonPointer.compile("/duto1");
//		Duto initRequest = null;
//		initRequest = new Duto();
//		ObjectNode node = (ObjectNode) mapper.readTree(mapper.writeValueAsString(initRequest));
//		JsonNode value =  new TextNode("1");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/duto2");
//		value =  new TextNode("2");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/dbos/0/dbo1");
//		value =  new TextNode("1");
//		ju.setJsonPointerValue(node, pointer, value);
//		pointer = JsonPointer.compile("/dbos/0/dbo2");
//		value =  new TextNode("2");
//		ju.setJsonPointerValue(node, pointer, value);
//		System.out.println(node);
		
		JsonUtil ju = new JsonUtil();
		String jsonString = "{\"duto1\":\"1\",\"duto2\":\"2\",\"duto3\":null,\"duto4\":null,\"duto5\":null,\"dbos\":[{\"dbo1\":\"1\",\"dbo2\":\"2\"}]}";
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonString);
	    Map<String,Object> map = ju.listNames("", actualObj);
	    System.out.println(JSONObject.toJSONString(map));
		 
	}

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
