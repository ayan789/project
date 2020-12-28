package com.ccic.salesapp.noncar.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.order.response.GroupOrderDetail;
import com.ccic.salesapp.noncar.dto.salesplan.GroupSalesPlan;

import lombok.Data;

@Data
public class RecommendRankPlansObjResp implements Serializable{
    
    List<Map<String,Object>> mapInsureList = new ArrayList<Map<String,Object>>();
    Map<String,List<InsureRes>> nameMap = new HashMap<>();
    GroupOrderDetail groupOrderDetail;
    List<GroupSalesPlan> groupSalesPlan;
    
}
