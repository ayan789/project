package com.ccic.salesapp.noncar.dto.request.planelement;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Header {

    private String isShare ="";
    private List<Swipper> swipper = new ArrayList<Swipper>();
    private int saleNum;
    private String title ="";
    private String desc ="";
    private double basePremium;

    
}