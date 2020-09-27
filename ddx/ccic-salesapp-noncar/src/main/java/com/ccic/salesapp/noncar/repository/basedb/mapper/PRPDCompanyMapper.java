package com.ccic.salesapp.noncar.repository.basedb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.Prpdcompany;

import java.util.List;

@Mapper
public interface PRPDCompanyMapper {

    String getComName(String param);
    
    Prpdcompany getUpperComCode(String comCode);

    List<String> getlowComCode(String comCode);
}
