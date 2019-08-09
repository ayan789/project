package com.neo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by yangzhilei on 2017/10/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MPlan {

    public String planId;//计划id
    public MCustomer applicant;//投保人
    public MCustomer insurant;//被保人
    public List<MCommodity> commodityList;//产品列表
    public List<MRule> ruleList;//规则校验结果
    public String firstPremium;//首期保费合计
    public String totalPremium;//总保费

    public String applicationType;// GIO/SIO/Full
}