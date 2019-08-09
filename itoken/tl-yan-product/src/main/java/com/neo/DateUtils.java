package com.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类 java对日期的操作一直都很不理想,直到jdk1.8之后才有了本质的改变。 如果使用的是jdk1.8之前版本开发的话,推荐使用Joda
 * Time组件。
 * <p>
 * 特别备注:unix的时间戳单位是秒
 *
 * @author xuyi3
 * @2016年7月13日 @下午2:01:00
 * @DateUtils
 * @功能说明:
 * @春风十里不如你
 * @备注
 */
public class DateUtils {

    public static String [] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    public static String [] monthStr = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    public static String [] monthScope = {"ONE_SEVEN","EIGTH_FORTEEN","FIFTEEN_TWENTYONE","TWENTYTWO_TWENTYEIGHT","TWENTYNINE"};
    public static String [] monthScopeSubfix = {"THIRTY", "THIRTY_ONE"};

    public static String[] getMonthStr(String[] array, Locale locale){
        LocaleMessage localeMessage = new LocaleMessage();
        String[] result = new String[array.length];
        for(int i=0;i<array.length;i++){
            result[i] = localeMessage.getMessage(array[i],locale);
        }
        return result;
    }


    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 年-月-日 时:分:秒 显示格式
     */
    // 备注:如果使用大写HH标识使用24小时显示格式,如果使用小写hh就表示使用12小时制格式。
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年-月-日 显示格式
     */
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

//    private static SimpleDateFormat simpleDateFormat;

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(source);
    }

    /**
     * unix时间戳转为指定格式的String类型
     * <p>
     * <p>
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern) {
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    /**
     * 字符串转换为对应日期(可能会报错异常)
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换日期异常", e);
        }
        return date;
    }

    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static Date strToDate(String dstr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);// 小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 日期比较
     * @param DATE1
     * @param DATE2
     * @return DATE1在前返回1，在后返回-1，相等返回0
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public static int compareDate(String DATE1, String DATE2, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 判断日期在某个区间
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String> getMonthScopeByWeek(int year, int month){
        LocaleMessage localeMessage = new LocaleMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        String format = "";
        List<String> result = new ArrayList<>();
        try {
            Date date = sdf.parse(year+"-"+month);
            calendar.setTime(date);

            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int weeks = days/7;
            int daysLeft = days%7;
            if(daysLeft > 0 ) weeks ++;
            logger.info("days : " + days + ", weeks : " + weeks + ", left: " + daysLeft);

            String monthArr[] = getMonthStr(monthStr,LoginUtil.currentLocale());
            String monthScopeArr[] = getMonthStr(monthScope,LoginUtil.currentLocale());
            String monthScopeSubfixArr[] = getMonthStr(monthScopeSubfix,LoginUtil.currentLocale());
            String monthPrefix = monthArr[month-1];
            logger.info("month prefix : " + monthPrefix);

            for(int i=0;i<weeks;i++){
                if(i == 4 && daysLeft > 1) {
                    result.add(monthPrefix + " " + monthScopeArr[i] + " " + localeMessage.getMessage("DASH",LoginUtil.currentLocale()) + " " + monthScopeSubfixArr[daysLeft-2]);
                }else{
                    result.add(monthPrefix + " " + monthScopeArr[i]);
                }
            }
        } catch (ParseException e) {
        }

        return result;
    }

    public static void main(String[] args) {
        getMonthScopeByWeek(2019,9);
    }


}