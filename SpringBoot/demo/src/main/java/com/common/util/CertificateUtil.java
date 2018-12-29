package com.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CertificateUtil {
    /**
     *
     * <p>Title: getSexByCertificateNo</p>
     * <p>Description: 通过身份证号获取性别</p>
     * @param certificateNo F：女 M：男
     * @return
     */
    public static String getSexByCertificateNo(String certificateNo){
        String sexValue="";
        if(certificateNo.length()==15){
            sexValue=Integer.parseInt(certificateNo.substring(14))%2==1?"M":"F";
        }else{
            sexValue=Integer.parseInt(certificateNo.substring(16,17))%2==1?"M":"F";
        }
        return sexValue;
    }
    /**
     *
     * <p>Title: getBirthdayByCertificateNo</p>
     * <p>Description: 通过身份证号获取出生日期</p>
     * @param certificateNo
     * @return
     */
    public static  Date getBirthdayByCertificateNo(String certificateNo){
        String birthday="";
        Date date=new Date();
        if(certificateNo.length()==15){
            String valTwo=certificateNo.substring(6,8);
            String valThree=certificateNo.substring(8,10);
            String valFour=certificateNo.substring(10,12);
            birthday="19"+valTwo+"-"+valThree+"-"+valFour;
        }else{
            String valueOne=certificateNo.substring(6,10);
            String valueTwo=certificateNo.substring(10,12);
            String valueThree=certificateNo.substring(12,14);
            birthday=valueOne+"-"+valueTwo+"-"+valueThree;
        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(birthday);
        }catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        System.out.println( CertificateUtil.getSexByCertificateNo("140700199307069871"));
    }
}

