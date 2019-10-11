package com.alibaba.excel.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 如何将一个字符串公式，计算出结果
 * @version V1.0
 */
public class RunJsOnJava {

    //js计算时精度丢失 把小数放到位整数 乘倍数 再缩小回原来倍数 除倍数
    public static void main(String[] args) throws ScriptException {
        RunJsOnJava rjj = new RunJsOnJava();
        rjj.test01();
    }

    public void test01() throws ScriptException {
        String formula = "(a+b)*(a-b)";
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("a", new BigDecimal("20.1"));
        map.put("b", new BigDecimal("11.1"));
        Double result =  (Double) this.calculateResultByFormula(formula, map);
        System.out.println(result);  //280.80000000000007
    }

        /**
         29
         * 通过字符串公式，和参数，计算出结果
         30
         * @param formula
        31
         * @param paramMap
        32
         * @return
        33
         * @throws ScriptException
        34
         */
    public Object calculateResultByFormula (String formula,Map<String,Object> paramMap) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("js");
        for (String key : paramMap.keySet()) {
            formula = formula.replaceAll(key, paramMap.get(key).toString());
        }
        //此时 formula="(20.1+11.1)*(20.1-11.1)"
        Object result = scriptEngine.eval(formula); // 运行js脚本
        return result;
    }
}