package com.neo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzhilei on 2017/10/18.
 * entry_对应MEntry json字符串
 * address对应MAddress json字符串
 */
@Data
public class SCustomer {

    String customerId;//id主键
    String insureId;
    String planId;
    String uniqueId;
    String customerType;//客户类型 1投保人 2被保人 3受益人
    String title;// 1:MS. 2:MRS. 3:MR.
    String name;
    String givenName;//名
    String familyName;//姓
    String previousFamilyName;//旧姓
    String gender;//性别,M男  F女
    Date birthday;//生日  yyyy-MM-dd
    String isSpecialDate;
    String age;
    String taxRate;//税率
    String email;
    String aum;
    String customerGroup;

    String titleInEnglish;
    String givenNameInEnglish;
    String familyNameInEnglish;
    String maritalStatus;//婚姻情况
    String isMarriedRegister;
    String spouseTitle;
    String spouseGivenName;
    String spouseFamilyName;
    String nationality;//国籍
    String moreNationality;
    String countryOfBirth;//出生地
    String idType;//证件类型
    String idNumber;//证件号码
    String passportNumber;
    Date expiryDate;//有效期
    String isSpecialExpiryDate;

    String isPaySelf;
    String isSelfSeller;
    String relationCode;
    String height;
    String weight;
    String weightChange;
    String weightChangeValue;
    String changeReason;
    String addressType;

    AddressEntity currentAddress;
    String houseRegistrationAddress;
    AddressEntity houseAddress;
    String workingAddress;
    AddressEntity workAddress;

    String policyDeliveryAddress;

    String occupationCode;//职业 code
    String position;
    String duty;
    String annualIncome;

    String hasOtherOccupation;
    String otherOccupationCode;
    String otherPosition;
    String otherDuty;
    String otherAnnualIncome;

    String fatherTitle;
    String fatherGivenName;
    String fatherFamilyName;
    String motherTitle;
    String motherGivenName;
    String motherFamilyName;

    String refId1;
    String refName1;
    String refId2;
    String refName2;

    String consentRelation;
    String consentTitle;
    String consentGivenName;
    String consentFamilyName;
    String consentMobilePhone;

    String creator;//创建者
    Date crateTime;//创建时间
    String updater;//修改者
    Date updateTime;//修改时间

    String isPayor;
    String benefitOrder;//受益顺序
    String benefitRatio;//受益人比例 0-100的整数
    String benefitRelation;//与被保人关系

    AddressEntity policyMailAddress;
    String policyMailingAddress;


    AddressEntity mailAddress;
    String mailingAddress;

    String bmi;

    String riskLevel;

    public boolean checkIsSelfSeller(){
        return "Y".equals(isSelfSeller);
    }

    public String getAge() {
        if (StringTool.isNotNull(age)) {
            return age;
        }
        if (birthday != null) {
            return ThailandDateTool.getAge(birthday) + "";
        }
        return "";
    }

    public MCustomer toCustomerModel() {
        MCustomer mCustomer = new MCustomer();
        mCustomer.customerId = customerId;
        mCustomer.insureId = insureId;
        mCustomer.planId = planId;
        mCustomer.customerType = customerType;
        mCustomer.title = title;
        if (StringTool.isNotNull(title)) {
            DItem titleItem = DictUtils.matchCode("title", title);
            if (titleItem != null)
                mCustomer.titleDesc = titleItem.getName();
        }
        mCustomer.name = name;
        mCustomer.givenName = givenName;
        mCustomer.familyName = familyName;
        mCustomer.previousFamilyName = previousFamilyName;
        mCustomer.gender = gender;
        mCustomer.birthday = formatDate(birthday);
        mCustomer.age = getAge();
        mCustomer.taxRate = taxRate;
        mCustomer.email = email;
        mCustomer.aum = aum;
        mCustomer.customerGroup = customerGroup;
        mCustomer.titleInEnglish = titleInEnglish;
        mCustomer.givenNameInEnglish = givenNameInEnglish;
        mCustomer.familyNameInEnglish = familyNameInEnglish;
        mCustomer.maritalStatus = maritalStatus;
        mCustomer.isMarriedRegister = isMarriedRegister;
        mCustomer.spouseTitle = spouseTitle;
        mCustomer.spouseGivenName = spouseGivenName;
        mCustomer.spouseFamilyName = spouseFamilyName;
        mCustomer.nationality = nationality;
        mCustomer.moreNationality = moreNationality;
        mCustomer.countryOfBirth = countryOfBirth;
        mCustomer.idType = idType;
        if ("2".equals(idType))
            mCustomer.passportNumber = idNumber;
        else
            mCustomer.idNumber = idNumber;
        mCustomer.expiryDate = formatDate(expiryDate);

        if (currentAddress != null) {
            mCustomer.currentAddressId = currentAddress.getId();
            mCustomer.currentAddressNumber = currentAddress.getNumber();
            mCustomer.currentAddressMoo = currentAddress.getMoo();
            mCustomer.currentAddressVillage = currentAddress.getVillage();
            mCustomer.currentAddressAlley = currentAddress.getAlley();
            mCustomer.currentAddressStreet = currentAddress.getStreet();
            mCustomer.currentAddressArea = formatAddress(currentAddress);
            mCustomer.currentAddressDetail = currentAddress.getDetail();
            mCustomer.currentAddressCountry = currentAddress.getCountry();
            if (StringTool.isNotNull(currentAddress.getCountry()))
                mCustomer.currentAddressCountryDesc = DictUtils.matchCode("countryCode", currentAddress.getCountry()).getName();
            mCustomer.currentAddressTel = currentAddress.getTel();
            mCustomer.currentAddressTelExt = currentAddress.getExt();
            mCustomer.currentAddressMobilePhone = currentAddress.getMobilePhone();
        }
        mCustomer.currentAddressEmail = email;

        mCustomer.houseRegistrationAddress = houseRegistrationAddress;
        if (houseAddress != null) {
            mCustomer.houseAddressId = houseAddress.getId();
            mCustomer.houseAddressNumber = houseAddress.getNumber();
            mCustomer.houseAddressMoo = houseAddress.getMoo();
            mCustomer.houseAddressVillage = houseAddress.getVillage();
            mCustomer.houseAddressAlley = houseAddress.getAlley();
            mCustomer.houseAddressStreet = houseAddress.getStreet();
            mCustomer.houseAddressArea = formatAddress(houseAddress);
            mCustomer.houseAddressDetail = houseAddress.getDetail();
            mCustomer.houseAddressCountry = houseAddress.getCountry();
            mCustomer.houseAddressTel = houseAddress.getTel();
            mCustomer.houseAddressTelExt = houseAddress.getExt();
            mCustomer.houseAddressMobilePhone = houseAddress.getMobilePhone();
            mCustomer.houseAddressEmail = houseAddress.getEmail();
        }
        mCustomer.workingAddress = workingAddress;
        if (workAddress != null) {
            mCustomer.workingAddressId = workAddress.getId();
            mCustomer.workingAddressNumber = workAddress.getNumber();
            mCustomer.workingAddressMoo = workAddress.getMoo();
            mCustomer.workingAddressVillage = workAddress.getVillage();
            mCustomer.workingAddressAlley = workAddress.getAlley();
            mCustomer.workingAddressStreet = workAddress.getStreet();
            mCustomer.workingAddressArea = formatAddress(workAddress);
            mCustomer.workingAddressDetail = workAddress.getDetail();
            mCustomer.workingAddressCountry = workAddress.getCountry();
            mCustomer.workingAddressTel = workAddress.getTel();
            mCustomer.workingAddressTelExt = workAddress.getExt();
            mCustomer.companyName = workAddress.getCompanyName();
            mCustomer.workingAddressMobilePhone = workAddress.getMobilePhone();
            mCustomer.workingAddressEmail = workAddress.getEmail();
        }
        mCustomer.policyDeliveryAddress = policyDeliveryAddress;
        mCustomer.occupationCode = occupationCode;
        mCustomer.occupation = DictUtils.matchCode("occupation", occupationCode);
        mCustomer.position = position;
        mCustomer.duty = duty;
        mCustomer.annualIncome = annualIncome;
        mCustomer.hasOtherOccupation = hasOtherOccupation;
        mCustomer.otherOccupationCode = otherOccupationCode;
        if (StringTool.isNotNull(otherOccupationCode))
            mCustomer.otherOccupation = DictUtils.matchCode("occupation", otherOccupationCode);
        mCustomer.otherPosition = otherPosition;
        mCustomer.otherDuty = otherDuty;
        mCustomer.otherAnnualIncome = otherAnnualIncome;

        mCustomer.fatherTitle = fatherTitle;
        mCustomer.fatherFamilyName = fatherFamilyName;
        mCustomer.fatherGivenName = fatherGivenName;
        mCustomer.motherTitle = motherTitle;
        mCustomer.motherFamilyName = motherFamilyName;
        mCustomer.motherGivenName = motherGivenName;

        mCustomer.isPaySelf = isPaySelf;
        mCustomer.relationCode = relationCode;
        if (StringTool.isNotNull(relationCode)) {
            if (InsureConfig.CUSTOMER_TYPE_BENEFICIARY.equals(customerType)) {
                DItem relationBeneficiary = DictUtils.matchCode("relationshipBeneficiary", relationCode);
                if (relationBeneficiary != null)
                    mCustomer.relationDesc = relationBeneficiary.getName();
            } else {
                DItem relation = DictUtils.matchCode("relationshipPayorAdult", relationCode);
                if (relation != null)
                    mCustomer.relationDesc = relation.getName();
            }
        }
        mCustomer.height = height;
        mCustomer.weight = weight;
        mCustomer.weightChange = weightChange;
        mCustomer.weightChangeValue = weightChangeValue;
        mCustomer.changeReason = changeReason;
        mCustomer.addressType = addressType;

        mCustomer.refId1 = refId1;
        mCustomer.refName1 = refName1;
        mCustomer.refId2 = refId2;
        mCustomer.refName2 = refName2;

        mCustomer.consentRelation = consentRelation;
        mCustomer.consentMobilePhone = consentMobilePhone;
        mCustomer.consentTitle = consentTitle;
        if (StringTool.isNotNull(consentTitle)) {
            DItem consentTitleItem = DictUtils.matchCode("title", consentTitle);
            if (consentTitleItem != null)
                mCustomer.consentTitleDesc = consentTitleItem.getName();
        }
        mCustomer.consentFamilyName = consentFamilyName;
        mCustomer.consentGivenName = consentGivenName;

        mCustomer.isPayor = isPayor;
        mCustomer.benefitRatio = benefitRatio;


        mCustomer.policyMailingAddress = policyMailingAddress;
        if (policyMailAddress != null) {
            mCustomer.policyMailingAddressId = policyMailAddress.getId();
        }


        mCustomer.mailingAddress = mailingAddress;
        if (mailAddress != null) {
            mCustomer.mailingAddressId = mailAddress.getId();
        }

        mCustomer.isSelfSeller = isSelfSeller;
        mCustomer.bmi = bmi;
        mCustomer.riskLevel = riskLevel;
        return mCustomer;
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
//        if (ThailandDateTool.isThai()) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            calendar.add(Calendar.YEAR, 543);
//            if ("Y".equals(isSpecialDate))
//                calendar.add(Calendar.DATE, 1);
//            date = calendar.getTime();
//        }
        return DateUtils.DateToString(date, "yyyy-MM-dd");
    }

    List<String> formatAddress(AddressEntity address) {
        List<String> list = new ArrayList<>();
        if (address != null && StringTool.isNotNull(address.getProvince())) {
            list.add(address.getProvince());
            list.add(address.getDistrict());
            list.add(address.getSubDistrict());
            list.add(address.getZipCode());
        }
        return list;
    }

    public static boolean isJuvenileForUl(SCustomer sInsurant) {
        int insurantAge = Integer.parseInt(sInsurant.getAge());
        if (insurantAge < 17 || (insurantAge >= 17 && insurantAge <= 19 && "N".equalsIgnoreCase(sInsurant.getIsMarriedRegister())))
            return true;
        return false;
    }

    public static void main(String[] args) {
//        SCustomer tCus = new SCustomer();
//        tCus.birthday = new Date();
//        System.out.println(tCus.toJsonString());
        Date date = DateUtils.stringToDate("29-02-2532", "dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -543);
        date = calendar.getTime();
        System.out.println(date);
        calendar.add(Calendar.YEAR, 543);
        Date date1 = calendar.getTime();
        System.out.println(DateUtils.DateToString(date1, "yyyy-MM-dd"));
    }
}
