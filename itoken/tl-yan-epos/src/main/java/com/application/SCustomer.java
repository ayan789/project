package com.application;

import java.util.Date;

public class SCustomer {

    public String customerId;//id主键
    public String insureId;
    public String planId;
    public String uniqueId;
    public String customerType;//客户类型 1投保人 2被保人 3受益人
    public  String title;// 1:MS. 2:MRS. 3:MR.
    public String name;
    public String givenName;//名
    public  String familyName;//姓
    public String previousFamilyName;//旧姓
    public String gender;//性别,M男  F女
    public Date birthday;//生日  yyyy-MM-dd
    public String isSpecialDate;
    public String age;
    public  String taxRate;//税率
    public  String email;
    public String aum;
    public String customerGroup;
    public String financialAssessment;
    public String financialAssessmentReason;

    public String titleInEnglish;
    public String givenNameInEnglish;
    public String familyNameInEnglish;
    public  String maritalStatus;//婚姻情况
    public  String isMarriedRegister;
    public String spouseTitle;
    public String spouseGivenName;
    public String spouseFamilyName;
    public String nationality;//国籍
    public String moreNationality;
    public String countryOfBirth;//出生地
    public  String idType;//证件类型
    public  String idNumber;//证件号码
    public  String passportNumber;
    public  Date expiryDate;//有效期
    public String isSpecialExpiryDate;

    public  String isPaySelf;
    public  String isSelfSeller;
    public  String relationCode;
    public String height;
    public  String weight;
    public  String weightChange;
    public  String weightChangeValue;
    public  String changeReason;
    public  String addressType;

    public AddressEntity currentAddress;
    public  String houseRegistrationAddress;
    public  AddressEntity houseAddress;
    public  String workingAddress;
    public AddressEntity workAddress;

    public String policyDeliveryAddress;

    public  String occupationCode;//职业 code
    public  String position;
    public  String duty;
    public String annualIncome;

    public  String hasOtherOccupation;
    public String otherOccupationCode;
    public String otherPosition;
    public String otherDuty;
    public String otherAnnualIncome;

    public String fatherTitle;
    public  String fatherGivenName;
    public String fatherFamilyName;
    public String motherTitle;
    public String motherGivenName;
    public String motherFamilyName;

    public  String refId1;
    public String refName1;
    public  String refId2;
    public  String refName2;

    public String consentRelation;
    public String consentTitle;
    public  String consentGivenName;
    public  String consentFamilyName;
    public  String consentMobilePhone;

    public  String creator;//创建者
    public  Date crateTime;//创建时间
    public String updater;//修改者
    public Date updateTime;//修改时间

    public String isPayor;
    public String benefitOrder;//受益顺序
    public String benefitRatio;//受益人比例 0-100的整数
    public String benefitRelation;//与被保人关系

    public  AddressEntity policyMailAddress;
    public  String policyMailingAddress;


    public AddressEntity mailAddress;
    public String mailingAddress;

    public String bmi;

    public String riskLevel;

    public String laser;

}
