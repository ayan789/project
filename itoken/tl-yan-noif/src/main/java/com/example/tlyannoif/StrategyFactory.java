package com.example.tlyannoif;



import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class StrategyFactory {

    private static StrategyFactory strategyFactory = new StrategyFactory();
    private StrategyFactory(){}
    public static StrategyFactory getInstance(){
        return strategyFactory;
    }

    private static HashMap<Integer,String>  source_map = new HashMap<>();

    static {
        Reflections reflection = new Reflections("com.example.tlyannoif");//使用Reflections工具类扫描对应包下的注解
        Set<Class<?>> classSet = reflection.getTypesAnnotatedWith(Pay.class);//获取在类文件头部的pay注解

        for(Class clazz : classSet){
            Pay pay = (Pay)clazz.getAnnotation(Pay.class);
            source_map.put(pay.channelId(),clazz.getCanonicalName());
        }
    }

    //核心 工厂生产对象
    public Strategy create(int channelId) throws Exception{
       String clazz = source_map.get(channelId);
       Class clazz_ = Class.forName(clazz);
       return (Strategy)clazz_.newInstance();
    };
}
