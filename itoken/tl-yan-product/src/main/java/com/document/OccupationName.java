package com.document;


import org.apache.commons.lang.StringUtils;

/**
 * Created by za-wanglinlin on 2019/1/29.
 */
public enum OccupationName {

    BUSINESSOWNER("Business Owner", "1"),
    CIVILSERVANT("Civil Servant", "2"),
    CompanyEmployee("Company Employee", "3"),
    STATEENTERPRISEEMPLOYEE("State Enterprise Employee", "4"),
    STUDENT("Student", "5"),
    OTHERS("Others", "6");

    public String occupationName;
    public String grouppingCode;

    OccupationName(String occupationName, String grouppingCode) {
        this.occupationName = occupationName;
        this.grouppingCode = grouppingCode;
    }


    public static OccupationName getGrouppingCode(String grouppingCode) {
        OccupationName[] enums = values();
        OccupationName[] codenums = enums;
        int var3 = enums.length;
        for (int i = 0; i < var3; ++i) {
            OccupationName e = codenums[i];
            if (StringUtils.equals(e.grouppingCode, grouppingCode)) {
                return e;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.print(getGrouppingCode("1").occupationName);
    }

}
