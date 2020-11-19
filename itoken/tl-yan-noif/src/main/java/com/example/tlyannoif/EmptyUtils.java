package com.example.tlyannoif;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class EmptyUtils {
	
	public static void main(String[] args) {
		//List不为空
		List<StreamUtils> list = null;
		System.out.println(CollectionUtils.isEmpty(list));
		//String不为空
		String str = "" ;
		System.out.println(StringUtils.isEmpty(str));
		//Object不为空
		StreamUtils streamUtils = new StreamUtils();
		System.out.println(EmptyUtils.objCheckIsNull(streamUtils));
	}
	
	/**
     * description:定义一个System.out.println的开关（纯属个人习惯）
     **/
	private static int objectNullSystemOutFlag = 1;
	
	public static boolean objCheckIsNull(Object object) {
    	if (object == null) {
            return true;
        }
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        //定义返回结果，默认为true
        boolean flag = true;
        for (Field field : fields) {
        	//设置权限（很重要，否则获取不到private的属性，不了解的同学补习一下反射知识）
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType = field.getGenericType();
                //得到属性名
                fieldName = field.getName();
                //打印输出(调试用可忽略)
                if (objectNullSystemOutFlag == 1) {
                    System.out.println("属性类型：" + fieldType + ",属性名：" + fieldName + ",属性值：" + fieldValue);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有一个属性值不为null 就返回false 表示对象不为null
            if (fieldValue != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
