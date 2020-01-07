package com.example.tlyannoif;


import java.util.function.Predicate;

public class PredicateUtil {
    public static boolean judgeString(String str, Predicate<String> p) {
        if (null == str) {
            return false;
        }
        return p.test(str);
    }

    public static boolean judgeNoString(String str, Predicate<String> p) {
        if (null == str) {
            return false;
        }
        return p.negate().test(str);
    }

    public static boolean judgeAndDoubleString(String str, Predicate<String> p1, Predicate<String> p2) {
        if (null == str) {
            return false;
        }
        return p1.and(p2).test(str);
    }

    public static boolean judgeOrDoubleString(String str, Predicate<String> p1, Predicate<String> p2) {
        if (null == str) {
            return false;
        }
        return p1.or(p2).test(str);
    }

    public static boolean judge(String str, Predicate<String> p1, Predicate<String> p2,Predicate<String> p3,Predicate<String> p4,Predicate<String> p5) {
        return (p1.and(p2)).or(p3.and(p4)).or(p5).test(str);
    }

    public static void main(String[] args) {
        String testString = "abcde";
        int p1 = 10 , p2 = 100 , p3 = 1000 , p4 = 10000 , p5 = 0;
        System.out.println(PredicateUtil.judge(testString,
                p->p1>=100000,
                p->p2>=100000,
                p->p3>=10 && p3 != 1000,
                p->p4>=10,
                p->p.equals("abcde") || p5==1));
    }


    //        PredicateUtil predicateUtil = new PredicateUtil();
//    public Boolean checkParam1(String p){
//        System.out.println("checkParam1..."+p);
//       return Integer.parseInt(p)>=1000000;
//    }
//
//    public Boolean checkParam2(String p){
//        System.out.println("checkParam2..."+p);
//        return Integer.parseInt(p)>=10000000;
//    }
//
//    public Boolean checkParam3(String p){
//        System.out.println("checkParam3..."+p);
//        return Integer.parseInt(p)>=10 && Integer.parseInt(p) != 1000;
//    }
//
//    public Boolean checkParam4(String p){
//        System.out.println("checkParam4..."+p);
//        return Integer.parseInt(p)>=10;
//    }
//
//    public Boolean checkParam5(String p){
//        System.out.println("checkParam5..."+p);
//        return Integer.parseInt(p)==1;
//    }

}