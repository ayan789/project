package com.ccic.salesapp.noncar.utils;


public class Data2Zh {
    final static private String NUMBER[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    final static private String NUMBER2[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    final static private String CBit[] = { "", "拾", "佰", "仟" };

    /*
     * 将数值大写
     */
    public  String capitalization(String szNum) {
        StringBuilder resstr = new StringBuilder();
        String tmpstr = szNum.trim();
        int sl = tmpstr.length();
        int sp = 0;
        int dotpos = tmpstr.indexOf('.');
        if (dotpos != -1) {
            while (sl > 1 && tmpstr.charAt(sl - 1) == '0')
                sl--;
            if (tmpstr.charAt(sl - 1) == '.')
                sl--;
            if (sl != tmpstr.length()) {
                tmpstr = tmpstr.substring(0, sl);
            }
        } else
            dotpos = sl;
        if (sl < 1)
            return NUMBER[0];
        if (tmpstr.charAt(0) == '-') {
            resstr.append("负");
            sp = 1;
        }
        String integerNum = tmpstr.substring(sp, dotpos - sp);
        String decimalNum = "";
        if (dotpos + 1 < sl)
            decimalNum = tmpstr.substring(dotpos + 1);
        sl = integerNum.length();
        sp = 0;
        while (sp < sl && integerNum.charAt(sp) == '0')
            sp++;
        if (sp > 0)
            integerNum = integerNum.substring(sp);
        int inl = integerNum.length();
        if (inl > 0) {
            int h = (inl - 1) % 4;
            int j = (inl - 1) / 4 + 1;
            sp = 0;
            boolean allzero = false;
            boolean preallzero = false;
            for (; j > 0; j--) {
                int k = h;
                h = 3;
                boolean preiszero = allzero;
                allzero = true;
                for (; k >= 0; k--, sp++) {
                    if (integerNum.charAt(sp) == '0')
                        preiszero = true;
                    else {
                        allzero = false;
                        if (preiszero)
                            resstr.append("零");
                        preiszero = false;
                        resstr.append(NUMBER[(byte) (integerNum.charAt(sp)) - 48]).append(CBit[k]);
                    }
                } // end for k
                if (/* j!=0 && */ j % 2 == 0) {
                    if (!allzero)
                        resstr.append("万");
                } else {
                    if (!allzero || !preallzero) {
                        int repyi = j / 2;
                        for (int i = 0; i < repyi; i++)
                            resstr.append("亿");
                    }
                }
                preallzero = allzero;
            } // end for j
        } else
            resstr.append("零");

        int dnl = decimalNum.length();
        if (dnl > 0) {
            resstr.append("点");
            for (int i = 0; i < dnl; i++) {
                resstr.append(NUMBER[(byte) (decimalNum.charAt(i)) - 48]);
            }
        }
        return resstr.toString();
    }

}

