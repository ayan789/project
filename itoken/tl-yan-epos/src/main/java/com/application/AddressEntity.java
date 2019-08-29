package com.application;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddressEntity {
    private String id;
    private String customerId;//客户ID
    private String addressType;//居住类型
    private String number;//客户编号
    private String moo;
    private String village;
    private String alley;
    private String street;
    private String province;//省代号
    private String district;//区域代号
    private String subDistrict;//小区代号
    private String zipCode;//邮政编码
    private String detail;//详细地址
    private String country;//国家
    private String telephone;//电话号码
    private String tel;
    private String ext;
    private String companyName;
    private String mobilePhone;
    private String email;
    private String creator;
    private Date gmtCreated;
    private String modifier;
    private Date gmtModifed;
    private String isDeleted;
}
