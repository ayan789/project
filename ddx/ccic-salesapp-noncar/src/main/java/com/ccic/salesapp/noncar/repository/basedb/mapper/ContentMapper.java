package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.store.request.ContentRequest;
import com.ccic.salesapp.noncar.repository.basedb.po.Content;
@Mapper
public interface ContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Content record);

    Content selectByPrimaryKey(Integer id);

    List<Content> selectAll();

    int updateByPrimaryKey(Content record);
    
    List<Content> selectContentList(ContentRequest request);
}