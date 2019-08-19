package com.neo;


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String处理工具
 * 
 * @author macma 2017年4月27日 下午8:27:57
 */
public class StringTool {
    private static Logger       log     = LoggerFactory.getLogger(StringTool.class);

   
    public static boolean isNull(String str) {
        String string = str;
        if (str == null)
            string = "";
        if (string.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isNotNull(String str) {
        String string = str;
        boolean result = true;
        if (str == null)
            string = "";
        if (!string.equals("")) {
            result = true;
        } else {
            result = false;
        }
        // System.out.println(str+":"+result);
        return result;
    }

}
