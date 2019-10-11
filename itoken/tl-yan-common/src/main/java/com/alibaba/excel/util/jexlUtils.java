package com.alibaba.excel.util;


import org.apache.commons.jexl3.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//表达式语言的解析引擎 解析表达式 条件判断 工作流
public class jexlUtils {
    public static void main(String[] args) {
        //1.创建jexl
        JexlEngine jexl = new JexlBuilder().create();
        // 2.做一个jexlContext并把它放入其中, MapConext类clear方法可以清空set过的值
        //JexlContext jc = new MapContext();
        // 3.设置变量的值,这个值可以在执行的表达式中调用
        //jc.set("i",new BigDecimal("1.2"));
        //jc.set("j",new BigDecimal("1"));
        // 4.创建一个将要执行的表达式
        //JexlExpression e = jexl.createExpression("i-j");
        // 5.执行并返回结果
        //Object o = e.evaluate(jc);
        //System.out.println(jc.get("i") + "-"+ jc.get("j") + " = " + o);

        Map<String,Object> map = new HashMap<>();
        map.put("a", new BigDecimal("20.1"));
        map.put("b", new BigDecimal("11.1"));
        JexlExpression expression = jexl.createExpression("(a+b)*(a-b)");
        JexlContext content = new MapContext(map);
        System.out.println(expression.evaluate(content));

        System.out.println((20.1+11.1)*(20.1-11.1));

//        JexlContext jc = new MapContext();
//        jc.set("i",3);
//        jc.set("j",4);
//        JexlExpression e = jexl.createExpression("i-j");
//        Object o = e.evaluate(jc);
//        //一个正则的执行方式
//        jc.set("str", "jacktan");
//        jc.set("HDI_Board", "yes");
//        e = jexl.createExpression("str=~\".*tan\"");
//        o = e.evaluate(jc);
//        System.out.println(o);
//        //执行多个条件
//        e=jexl.createExpression("str=~'.*tan' && str != 'jacktan'");
//        System.out.println(e.evaluate(jc) + "  aaa");
//        e=jexl.createExpression("HDI_Board=='yes'");
//        System.out.println(e.evaluate(jc) + "  bbb");
//
//        e = jexl.createExpression("i<1");
//        System.out.println(e.evaluate(jc));

    }

}
