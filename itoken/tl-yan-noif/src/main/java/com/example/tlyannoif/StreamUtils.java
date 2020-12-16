package com.example.tlyannoif;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class StreamUtils {
	private String name;
	private String gender;
    private Integer age;
    
	public static void main(String[] args) {
        List<StreamUtils> list = Lists.newArrayList();
        list.add(new StreamUtils("张三","男",21));
        list.add(new StreamUtils("李四","男",20));
        list.add(new StreamUtils("王五","女",22));
        
        List<StreamUtils> list2 = list.stream().filter(s->"男".equals(s.getGender()) && s.getAge() >= 19).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list2));
       
        List<StreamUtils> list3 = list.stream().map( x -> {
        	StreamUtils lea = new StreamUtils();
        	lea.setName(x.getName());
        	lea.setAge(x.getAge()+1);
            return lea;
        }).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list3));
        
        List<StreamUtils> list4 = list.stream().sorted(Comparator.comparing(StreamUtils::getAge)).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(JSONObject.toJSONString(list4));
        
        List<StreamUtils> list5 = list.stream().sorted(Comparator.comparing(StreamUtils::getAge).reversed()).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(JSONObject.toJSONString(list5));
        
        List<StreamUtils> list6 = list.stream().skip(1).limit(2).distinct().collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(list6));
        
        list.forEach(i -> i.setAge(i.getAge()*2));
        System.out.println(JSONObject.toJSONString(list));
        
        Map<String, StreamUtils> list_map = new HashMap<>(list.size());
        list.stream().forEach( x -> list_map.put(x.getName(), x));
        System.out.println(JSONObject.toJSONString(list_map));
        
        Map<String, List<StreamUtils>> group = list.stream().collect(Collectors.groupingBy(StreamUtils::getGender));
        System.out.println(JSONObject.toJSONString(group));
        
        list.stream().forEach( x -> {
        	System.out.println(123);
        });
        
	}

	
	public StreamUtils(String name,String gender, Integer age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}


	public StreamUtils() {
		super();
	}

}
