package com.alibaba.excel.util;


import org.apache.commons.jexl3.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//表达式语言的解析引擎 解析表达式 条件判断 工作流
public class jexlUtils {
    public static void main(String[] args) {
        //1.创建jexl
//        JexlEngine jexl = new JexlBuilder().create();
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

//        Map<String,Object> map = new HashMap<>();
//        map.put("a", new BigDecimal("20.1"));
//        map.put("b", new BigDecimal("11.1"));
//        JexlExpression expression = jexl.createExpression("(a+b)*(a-b)");
//        JexlContext content = new MapContext(map);
//        System.out.println(expression.evaluate(content));
//
//        System.out.println((20.1+11.1)*(20.1-11.1));

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

        jexlUtils.a8();


    }

    public static void a1(){
        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("if (A1 == 1 && DS == 'abc') { Perms = 'a10'; } else if (A1 == 2 && DS == 'abc') { Perms = 'a20'; }  else { Perms = 'a30'; }", "A1","DS");
        JexlContext jc = new MapContext();
        e.execute(jc, 2,"abcabc");
        Object result = jc.get("Perms");
        System.out.println(result);


    }

    public static void a2(){
        JexlEngine JEXL = new JexlBuilder().create();
        Book book = new Book();
        book.setName("我爱中国");
        JexlScript e = JEXL.createScript(" book.name == '我爱中国' ?'我爱中国':'quux'");
        JexlContext jc = new MapContext();
        jc.set("book",book);
        Object o = e.execute(jc);
        System.out.println(o);
    }

    public static void a3(){
        //=~ 匹配 .*A 已A结尾 A.* 已A开头 !~ 不匹配
        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("str =~ match ");
        JexlContext jc = new MapContext();
        jc.set("str","ABCabc4567EFD");
        jc.set("match","ABC.*EFDj");
        e.execute(jc);
        Object o = e.execute(jc);
        System.out.println(o);
    }

    public static void a4(){
        //=~ 匹配 .*A 已A结尾 A.* 已A开头 !~ 不匹配
        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("str =~ match ");
        JexlContext jc = new MapContext();
        jc.set("str","zhangsan-001@gmail.com");
        jc.set("match","^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        e.execute(jc);
        Object o = e.execute(jc);
        System.out.println(o);
    }

    public static void a5(){
        Book book2 = new Book();
        book2.setName("我爱中国");
        Book book3 = new Book();
        book3.setName("我爱中国2");
        Book book4 = new Book();
        book4.setName("我爱中国3");

        List<Book> list = new ArrayList<>();
        list.add(book2);
        list.add(book3);
        list.add(book4);

        List<Book> list2 = new ArrayList<>();

        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("for(var i : 10) { list2[i].name = 'aa'+i ; }");
        JexlContext jc = new MapContext();
        jc.set("list2", list2);
        Object result = e.execute(jc);
//        Object result = jc.get("list2");
        System.out.println(result);
    }


    public static void a6(){
        long A = new Date().getTime();
        System.out.println(A);
        //1570873944690
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(format1.format(new Date(new Date().getTime())));

        format1.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        System.out.println(format1.format(new Date(new Date().getTime())));

        format1.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        System.out.println(format1.format(new Date(new Date().getTime())));

        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
    }


    public static void a7(){
        Book book = new Book();
        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("book.getBooks(loop,A1)");
        JexlContext jc = new MapContext();
        jc.set("book",book);
        jc.set("loop",10);
        jc.set("A1","打开手机大奖");
        Object o = e.execute(jc);
        System.out.println("结果："+o);
    }

    public static void a8(){
        Book book = new Book();
        JexlEngine JEXL = new JexlBuilder().create();
        JexlScript e = JEXL.createScript("book.checkBook(loop,A1)");
        JexlContext jc = new MapContext();
        jc.set("book",book);
        jc.set("loop",10);
        jc.set("A1","打开手机大奖");
        Object o = e.execute(jc);
        Boolean a = (Boolean) o;
        if(a==true){
            System.out.println("OK");
        }
        else{
            System.out.println("NO");
        }
    }

}
