package com.neo;


import java.util.Locale;

public class LocaleMgr {

    static final Locale LOCALE_TH = new Locale("th", "th");

    public static Locale findLocale(String language) {
        if ("th".equals(language)) {
            return LOCALE_TH;
        }
        if ("zh".equals(language)) {
            return Locale.CHINA;
        }
        return Locale.US;
    }
}
