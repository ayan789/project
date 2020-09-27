package com.ccic.salessapp.common.core.advice;

import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public final class SpecialWord {
    /**
     * @param result
     * @return
     */
    public static Object apply(Object result) {
        try {
            Optional<Object> optional=Optional.ofNullable(result);
            if(optional.isPresent()){
                objectParse(result);
            }
        }catch (Exception e){
            throw new PlatformBaseException("特殊字符过滤失败",-1);
        }
        return result;
    }


    public static void objectParse(Object obj) throws Exception {
        List<Field> allField = allField(obj);
        for (Field field : allField) {
            field.setAccessible(true);
            Class<?> typeClazz = field.getType();
            matchFieldType(obj, field, typeClazz);
        }
    }

    public static List<Field> allField(Object object) {
        List<Field> result = new ArrayList<>();
        Class<?> clazz = object.getClass();
        while (null!=clazz) {
            if (clazz == Object.class) break;
            Field[] declaredFields = clazz.getDeclaredFields();
            result.addAll(Arrays.asList(declaredFields));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    public static <T> void matchFieldType(Object obj, Field field, T clazz) throws Exception {
            T param = (T) field.get(obj);
            if (param == null) return;
            if (clazz == List.class) {
                List p = (List) param;
                for (Object o : p) {
                    objectParse(o);
                }
            } else if (clazz == String.class) {
                String value=ChartFilterUtil.stripXss((String)field.get(obj));
                setValue(obj, field,value);
            } else if (clazz == Map.class) {
                Map map = (Map) param;
                for (Object o : map.keySet()) {
                    objectParse(o);
                }
            }
    }

    public static void setValue(Object object, Field field, Object param) throws IllegalAccessException {
        if (!field.isAccessible()) {
            throw new IllegalAccessException("modify the field fail.");
        }
        field.set(object, param);
    }
}