package com.neo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 年龄计算
 */
public class ThailandDateTool {

    public static int getAgeByBirthDay(Date birthday) {
        Calendar birthdayCal = Calendar.getInstance();
        birthdayCal.setTimeInMillis(birthday.getTime());
        int yob = birthdayCal.get(Calendar.YEAR);
        int mob = birthdayCal.get(Calendar.MONTH) + 1;
        int dob = birthdayCal.get(Calendar.DAY_OF_MONTH);
        if (isThai()) {
            yob = yob - 543;
        }

        return calculateAge(yob, mob, dob);
    }

    public static int getAge(Date birthday) {
        Calendar birthdayCal = Calendar.getInstance();
        birthdayCal.setTimeInMillis(birthday.getTime());
        int yob = birthdayCal.get(Calendar.YEAR);
        int mob = birthdayCal.get(Calendar.MONTH) + 1;
        int dob = birthdayCal.get(Calendar.DAY_OF_MONTH);

        return calculateAge(yob, mob, dob);
    }

    public static int calculateAge(int yob, int mob, int dob) {
        int age = -1;
        Calendar todayCal = Calendar.getInstance();
        int yoi = todayCal.get(Calendar.YEAR);
        int moi = todayCal.get(Calendar.MONTH) + 1;
        int doi = todayCal.get(Calendar.DAY_OF_MONTH);

        int ddif = doi - dob;
        int mdif = moi - mob;
        int ydif = yoi - yob;

        if (Math.abs(mdif) <= 5) {
            age = ydif;
        } else if (mdif == 6 && ddif >= 0) {
            age = ydif + 1;
        } else if (mdif == 6 && ddif < 0) {
            age = ydif;
        } else if (mdif >= 7 && mdif <= 11) {
            age = ydif + 1;
        } else if (mdif == -6 && ddif >= 0) {
            age = ydif;
        } else if (mdif == -6 && ddif < 0) {
            age = ydif - 1;
        } else if (mdif >= -11 && mdif <= -7) {
            age = ydif - 1;
        }

        return age;
    }

    public static int getDay(Date birthday) {
        Calendar cal = Calendar.getInstance();
        return (int) ((cal.getTimeInMillis() - birthday.getTime()) / 1000 / 3600 / 24);
    }

    public static boolean isThai() {
        Locale locale = LoginUtil.currentLocale();
        String localeStr = locale.toString();
        if (StringTool.isNotNull(localeStr) && "th_TH".toLowerCase().equals(localeStr.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static String formatThaiDate() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+7:00");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }

    public static String formatToThaiTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
        return simpleDateFormat.format(date);
    }

    public static String formatToThai(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 543);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
        return simpleDateFormat.format(calendar.getTime());
    }


    public static Date formatThaiToDate(String value) {
        Date date = DateUtils.stringToDate(value, "dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -543);
        date = calendar.getTime();
        return date;
    }
    public static String formatToThaiDMY(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 543);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(calendar.getTime());
    }


    public static String getCurrentThailandDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
        return simpleDateFormat.format(new Date());
    }

    public static void main(String[] args) {
        Calendar birthdayCal = Calendar.getInstance();
        int yob = birthdayCal.get(Calendar.YEAR);
        int mob = birthdayCal.get(Calendar.MONTH) + 1;
        int dob = birthdayCal.get(Calendar.DAY_OF_MONTH);
        System.out.println("y:" + yob + " m:" + mob + " d:" + dob);

        try {
            System.out.println(getAge(new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-17")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
