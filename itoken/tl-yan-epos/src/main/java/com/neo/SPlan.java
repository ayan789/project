package com.neo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Created by yangzhilei on 2017/10/17.
 * 保存数据使用
 */
@Data
public class SPlan {

    String planId;
    String applicant;//投保人信息,json字符串
    String insurant;//被保人信息,json字符串
    String coverPath;//封面链接
    String productName;//产品名称
    String totalPremium;//累计保费
    String givenName;//名    被保人
    String familyName;//姓   被保人
    Date createTime;
    String creator;
    Date updateTime;
    String updater;
    String stepKey;
    String isDeleted = "N";//Y：已删除
    List<SCommodity> commodityList;

}
