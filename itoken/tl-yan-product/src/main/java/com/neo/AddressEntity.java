package com.neo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * za_address 实体类
 * Tue May 22 17:42:21 CST 2018 zhangjunwen
 */
@Data
public class AddressEntity implements Serializable {
    private String id;
    private String customerId;
    private String addressType;
    private String number;
    private String moo;
    private String village;
    private String alley;
    private String street;
    private String province;
    private String district;
    private String subDistrict;
    private String zipCode;
    private String detail;
    private String country;
    private String telephone;
    private String tel;
    private String ext;
    private String companyName;
    private String mobilePhone;
    private String email;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreated;
    private String modifier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModifed;
    private String isDeleted;

}
