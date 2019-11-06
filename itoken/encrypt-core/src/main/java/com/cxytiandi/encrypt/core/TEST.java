package com.cxytiandi.encrypt.core;

import org.springframework.util.StringUtils;

public class TEST {
    public static void main(String[] args) {
        String aaa = "/book/list/1_get";

        boolean a = StringUtils.startsWithIgnoreCase(aaa,"/book/");
        boolean b = StringUtils.endsWithIgnoreCase(aaa,"_get");

        System.out.println(a && b);


        String aa = "put:/book/abc/";
        String aaa1 =  aa.split(":")[0];
        String aaa2 =  aa.split(":")[1];
        System.out.println(aaa1);
        System.out.println(aaa2);
    }
}
