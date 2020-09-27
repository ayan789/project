package com.ccic.salesapp.noncar.controller;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

/**
 * @author JiaBin.Wang
 * @date 2019/12/2 14:12
 */
@Data
public class Demo {
    private int id;
    private String name;

    public static List<Demo> getDemo1() {
        Demo demo = new Demo();
        demo.setId(11);
        demo.setName("方案1");
        Demo demo2 = new Demo();
        demo2.setId(13);
        demo2.setName("方案2");
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo);
        demoList.add(demo2);
        return demoList;
    }

    public static List<Long> getDemo2() {
        List<Long> demoList = new ArrayList<>();
        demoList.add(11L);
        demoList.add(14L);
        demoList.add(15L);
        demoList.add(16L);
        return demoList;
    }

    public static void main(String[] args) {
        List<Demo> formulaList = getDemo1();
        List<Long> plans = getDemo2();

        List<Demo> formulaList2 = formulaList.stream()
                .filter(item -> plans.stream().map(e -> e.intValue()).collect(Collectors.toList())
                        .contains(item.getId())).collect(Collectors.toList());
        
        System.out.println(JSONObject.toJSON(formulaList2));
//        intersection.forEach(System.out::println);
    }
}