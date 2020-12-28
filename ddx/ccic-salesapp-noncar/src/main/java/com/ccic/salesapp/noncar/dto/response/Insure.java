package com.ccic.salesapp.noncar.dto.response;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Insure {

    private List<Map<String,List<String>>> mapList;
    
    
}