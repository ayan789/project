package com.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzhilei on 2017/10/16.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MCustomer implements Serializable {

    public String customerId;//id主键
    public String insureId;
    public String planId;
    public String uniqueId;
    public String customerType;//客户类型 1投保人 2被保人 3受益人
    public String title;
    public String titleDesc;
    public String givenName;//名
    public String familyName;//姓
    public String previousFamilyName;//旧姓
    public String name;

    public String gender;//性别,M男  F女
    public String birthday;//生日  yyyy-MM-dd
    public String age;//生日  yyyy-MM-dd
    public String taxRate;//税率
    public String email;
    public String aum;
    public String customerGroup;

    public String titleInEnglish;
    public String givenNameInEnglish;
    public String familyNameInEnglish;
    public String maritalStatus;//婚姻情况
    public String isMarriedRegister;
    public String spouseTitle;
    public String spouseGivenName;
    public String spouseFamilyName;
    public String nationality;//国籍
    public String moreNationality;
    public String countryOfBirth;//出生地
    public String idType;//证件类型
    public String idNumber;//证件号码
    public String passportNumber;
    public String expiryDate;//有效期

    public String isPaySelf;
    public String isSelfSeller;
    public String relationCode;
    public String relationDesc;
    public String height;
    public String weight;
    public String weightChange;
    public String weightChangeValue;
    public String changeReason;
    public String addressType;

    public String currentAddressId;
    public String currentAddressNumber;
    public String currentAddressMoo;
    public String currentAddressVillage;
    public String currentAddressAlley;
    public String currentAddressStreet;
    public List<String> currentAddressArea;
    public String currentAddressDetail;
    public String currentAddressCountry;
    public String currentAddressCountryDesc;
    public String currentAddressTel;
    public String currentAddressTelExt;
    public String currentAddressMobilePhone;
    public String currentAddressEmail;//电子邮箱

    public String houseRegistrationAddress;
    public String houseAddressId;
    public String houseAddressNumber;
    public String houseAddressMoo;
    public String houseAddressVillage;
    public String houseAddressAlley;
    public String houseAddressStreet;
    public List<String> houseAddressArea;
    public String houseAddressDetail;
    public String houseAddressCountry;
    public String houseAddressTel;
    public String houseAddressTelExt;
    public String houseAddressMobilePhone;
    public String houseAddressEmail;

    public String workingAddress;
    public String workingAddressId;
    public String workingAddressNumber;
    public String workingAddressMoo;
    public String workingAddressVillage;
    public String workingAddressAlley;
    public String workingAddressStreet;
    public List<String> workingAddressArea;
    public String workingAddressDetail;
    public String workingAddressCountry;
    public String workingAddressTel;
    public String workingAddressTelExt;
    public String companyName;
    public String workingAddressMobilePhone;
    public String workingAddressEmail;
    public String policyDeliveryAddress;

    public String occupationCode;//职业 code
    public DItem occupation;//职业
    public String position;
    public String duty;
    public String annualIncome;

    public String hasOtherOccupation;
    public String otherOccupationCode;//职业 code
    public DItem otherOccupation;
    public String otherPosition;
    public String otherDuty;
    public String otherAnnualIncome;

    public String fatherTitle;
    public String fatherGivenName;
    public String fatherFamilyName;
    public String motherTitle;
    public String motherGivenName;
    public String motherFamilyName;

    public String refId1;
    public String refName1;
    public String refId2;
    public String refName2;

    public String consentRelation;
    public String consentTitle;
    public String consentTitleDesc;
    public String consentGivenName;
    public String consentFamilyName;
    public String consentMobilePhone;

    public String isPayor;
    public String benefitOrder;//受益顺序
    public String benefitRatio;//受益人比例 0-100的整数
    public String benefitRelation;//与被保人关系


    public String policyMailingAddress;
    public String policyMailingAddressId;

    public String mailingAddress;
    public String mailingAddressId;

    public String bmi;

    public String riskLevel;


    public SCustomer toCustomerSave() {
        SCustomer sCustomer = new SCustomer();
        sCustomer.setCustomerId(customerId);
        sCustomer.setInsureId(insureId);
        sCustomer.setPlanId(planId);
        if (!InsureConfig.CUSTOMER_TYPE_BENEFICIARY.equals(customerType))
            sCustomer.setUniqueId(insureId + "_" + customerType);
        else
            sCustomer.setUniqueId(uniqueId);
        sCustomer.setCustomerType(customerType);
        sCustomer.setTitle(title);
        sCustomer.setName(name);
        sCustomer.setGivenName(givenName);
        sCustomer.setFamilyName(familyName);
        sCustomer.setPreviousFamilyName(previousFamilyName);
        sCustomer.setGender(gender);
        /**
         * 前端传递的日期全都使用公元历，不区分语言环境
         */
        sCustomer.setBirthday(formatDate(birthday));
//        sCustomer.setIsSpecialDate(isSpecialDate(birthday));
        sCustomer.setAge(age);
        sCustomer.setTaxRate(taxRate);
        sCustomer.setEmail(email);
        sCustomer.setAum(aum);
        sCustomer.setCustomerGroup(customerGroup);
        sCustomer.setTitleInEnglish(titleInEnglish);
        sCustomer.setGivenNameInEnglish(givenNameInEnglish);
        sCustomer.setFamilyNameInEnglish(familyNameInEnglish);
        sCustomer.setMaritalStatus(maritalStatus);
        if ("M".equals(maritalStatus)) {
            sCustomer.setIsMarriedRegister(isMarriedRegister);
        } else {
            sCustomer.setIsMarriedRegister("");
        }
        sCustomer.setSpouseTitle(spouseTitle);
        sCustomer.setSpouseGivenName(spouseGivenName);
        sCustomer.setSpouseFamilyName(spouseFamilyName);
        sCustomer.setNationality(nationality);
        sCustomer.setMoreNationality(StringTool.isNull(moreNationality) ? "" : moreNationality);
        sCustomer.setCountryOfBirth(countryOfBirth);
        sCustomer.setIdType(idType);
        if ("2".equals(idType))
            sCustomer.setIdNumber(passportNumber);
        else
            sCustomer.setIdNumber(idNumber);
//        sCustomer.setPassportNumber(passportNumber);
        sCustomer.setExpiryDate(formatDate(expiryDate));
//        sCustomer.setIsSpecialExpiryDate(isSpecialDate(expiryDate));

        AddressEntity currentAddress = new AddressEntity();
        currentAddress.setCustomerId(customerId);
        currentAddress.setAddressType(InsureConfig.ADDRESS_TYPE_CURRENT);
        currentAddress.setId(currentAddressId);
        currentAddress.setNumber(currentAddressNumber);
        currentAddress.setMoo(currentAddressMoo);
        currentAddress.setVillage(currentAddressVillage);
        currentAddress.setAlley(currentAddressAlley);
        currentAddress.setStreet(currentAddressStreet);
        formatAddress(currentAddress, currentAddressArea);
        currentAddress.setDetail(currentAddressDetail);
        currentAddress.setCountry(currentAddressCountry);
        currentAddress.setTel(currentAddressTel);
        currentAddress.setExt(currentAddressTelExt);
        currentAddress.setMobilePhone(currentAddressMobilePhone);
        currentAddress.setEmail(email == null ? currentAddressEmail : email);
        sCustomer.setCurrentAddress(currentAddress);

        sCustomer.setHouseRegistrationAddress(houseRegistrationAddress);
        AddressEntity houseAddress = new AddressEntity();
        houseAddress.setCustomerId(customerId);
        houseAddress.setAddressType(InsureConfig.ADDRESS_TYPE_HOUSE);
        houseAddress.setId(houseAddressId);
        houseAddress.setNumber(houseAddressNumber);
        houseAddress.setMoo(houseAddressMoo);
        houseAddress.setVillage(houseAddressVillage);
        houseAddress.setAlley(houseAddressAlley);
        houseAddress.setStreet(houseAddressStreet);
        formatAddress(houseAddress, houseAddressArea);
        houseAddress.setDetail(houseAddressDetail);
        houseAddress.setCountry(houseAddressCountry);
        houseAddress.setTel(houseAddressTel);
        houseAddress.setExt(houseAddressTelExt);
        houseAddress.setMobilePhone(houseAddressMobilePhone);
        houseAddress.setEmail(houseAddressEmail);
        sCustomer.setHouseAddress(houseAddress);

        sCustomer.setWorkingAddress(workingAddress);
        AddressEntity workAddress = new AddressEntity();
        workAddress.setCustomerId(customerId);
        workAddress.setAddressType(InsureConfig.ADDRESS_TYPE_WORK);
        workAddress.setId(workingAddressId);
        workAddress.setNumber(workingAddressNumber);
        workAddress.setMoo(workingAddressMoo);
        workAddress.setVillage(workingAddressVillage);
        workAddress.setAlley(workingAddressAlley);
        workAddress.setStreet(workingAddressStreet);
        formatAddress(workAddress, workingAddressArea);
        workAddress.setDetail(workingAddressDetail);
        workAddress.setCountry(workingAddressCountry);
        workAddress.setTel(workingAddressTel);
        workAddress.setExt(workingAddressTelExt);
        workAddress.setCompanyName(companyName);
        workAddress.setMobilePhone(workingAddressMobilePhone);
        workAddress.setEmail(workingAddressEmail);
        sCustomer.setWorkAddress(workAddress);

        sCustomer.setPolicyDeliveryAddress(policyDeliveryAddress);
        sCustomer.setOccupationCode(occupationCode);
        sCustomer.setPosition(position);
        sCustomer.setDuty(duty);
        sCustomer.setAnnualIncome(annualIncome);

        sCustomer.setHasOtherOccupation(hasOtherOccupation);
        sCustomer.setOtherOccupationCode(otherOccupationCode);
        sCustomer.setOtherPosition(otherPosition);
        sCustomer.setOtherDuty(otherDuty);
        sCustomer.setOtherAnnualIncome(otherAnnualIncome);

        sCustomer.setIsPaySelf(isPaySelf);
        sCustomer.setRelationCode(relationCode);
        sCustomer.setHeight(height);
        sCustomer.setWeight(weight);
        sCustomer.setWeightChange(weightChange);
        sCustomer.setWeightChangeValue(weightChangeValue);
        sCustomer.setChangeReason(changeReason);
        sCustomer.setAddressType(addressType);

        sCustomer.setFatherTitle(fatherTitle);
        sCustomer.setFatherFamilyName(fatherFamilyName);
        sCustomer.setFatherGivenName(fatherGivenName);
        sCustomer.setMotherTitle(motherTitle);
        sCustomer.setMotherFamilyName(motherFamilyName);
        sCustomer.setMotherGivenName(motherGivenName);

        sCustomer.setRefId1(refId1);
        sCustomer.setRefName1(refName1);
        sCustomer.setRefId2(refId2);
        sCustomer.setRefName2(refName2);

        sCustomer.setConsentRelation(consentRelation);
        sCustomer.setConsentMobilePhone(consentMobilePhone);
        sCustomer.setConsentTitle(consentTitle);
        sCustomer.setConsentFamilyName(consentFamilyName);
        sCustomer.setConsentGivenName(consentGivenName);

        sCustomer.setIsPayor(isPayor);
        sCustomer.setBenefitRatio(benefitRatio);

        sCustomer.setPolicyMailingAddress(policyMailingAddress);
        AddressEntity policyMailingAddress = new AddressEntity();
        policyMailingAddress.setCustomerId(customerId);
        policyMailingAddress.setAddressType(InsureConfig.ADDRESS_TYPE_POLICY_MAILING);
        policyMailingAddress.setId(policyMailingAddressId);
        sCustomer.setPolicyMailAddress(policyMailingAddress);


        sCustomer.setMailingAddress(mailingAddress);
        AddressEntity mailAddress = new AddressEntity();
        mailAddress.setCustomerId(customerId);
        mailAddress.setAddressType(InsureConfig.ADDRESS_TYPE_MAILING);
        mailAddress.setId(mailingAddressId);
        sCustomer.setMailAddress(mailAddress);

        sCustomer.setIsSelfSeller(isSelfSeller);
        sCustomer.setBmi(bmi);
        sCustomer.setRiskLevel(riskLevel);
        return sCustomer;
    }

    public static Date formatDate(String date) {
        if (StringTool.isNull(date)) {
            return null;
        }
        return DateUtils.stringToDate(date, "dd-MM-yyyy");
    }

    public static Date formatThaiDate(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.YEAR, -543);
        date1 = calendar.getTime();
        return date1;
    }

//    String isSpecialDate(String date) {
//        if (StringTool.isNull(date)) {
//            return "N";
//        }
//        if (ThailandDateTool.isThai()) {
//            Date date1 = DateUtils.stringToDate(date, "dd-MM-yyyy");
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date1);
//            calendar.add(Calendar.YEAR, -543);
//            int year = calendar.get(Calendar.YEAR);
//            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
//                return "N";
//            }
//            int month = calendar.get(Calendar.MONTH) + 1;//获取月份
//            int day = calendar.get(Calendar.DATE);//获取日
//            if (month == 2 && day == 29)
//                return "Y";
//        }
//        return "N";
//    }

    void formatAddress(AddressEntity address, List<String> area) {
        if (CollectionUtils.isNotEmpty(area)) {
            address.setProvince(area.get(0));
            address.setDistrict(area.get(1));
            address.setSubDistrict(area.get(2));
            address.setZipCode(area.get(3));
        }
    }

    public static boolean checkBirthdayAndAge(MCustomer mCustomer) {
        if (StringTool.isNotNull(mCustomer.birthday)) {
            mCustomer.age = getAge(mCustomer.birthday)+"";
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.stringToDate(mCustomer.birthday, "dd-MM-yyyy"));
            if (calendar.getTimeInMillis() > new Date().getTime() || Integer.parseInt(mCustomer.age) < 0) {
                return true;
            }
            if (StringTool.isNotNull(mCustomer.expiryDate)) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(DateUtils.stringToDate(mCustomer.expiryDate, "dd-MM-yyyy"));
                if (calendar1.getTimeInMillis() < new Date().getTime())
                    return true;
            }
        }
        return false;
    }

    public static int getAge(String birthday) {
        Date birth = DateUtils.stringToDate(birthday, "dd-MM-yyyy");
        return ThailandDateTool.getAge(birth);
    }

    public static boolean isAdult(String age) {
        if (StringTool.isNotNull(age)) {
            if (Integer.parseInt(age) >= 17)
                return true;
        }
        return false;
    }

    /**
     * 默认客户信息
     */
    public static MCustomer buildDefault() {
        MCustomer mCustomer = new MCustomer();
        mCustomer.birthday = "1980-01-01";
        mCustomer.gender = "M";
        return mCustomer;
    }

    public static MCustomer getBirthdayAndAgeByFna(JSONObject data) {
        JSONObject fna1 = data.getJSONObject("fna1");
        JSONArray jsonArray = fna1.getJSONArray("options");
        MCustomer customer = new MCustomer();
        if (null != jsonArray && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject ob = jsonArray.getJSONObject(i);
                if ("Fna1DOB".equals(ob.get("name"))) {
                    customer.birthday = DateUtils.DateToString(DateUtils.stringToDate(ob.getString("value"),"dd-MM-yyyy"),"yyyy-MM-dd");
                }
                if ("Fna1Age".equals(ob.get("name"))) {
                    customer.age = ob.getString("value");
                }
            }
        }
        return customer;
    }

}
