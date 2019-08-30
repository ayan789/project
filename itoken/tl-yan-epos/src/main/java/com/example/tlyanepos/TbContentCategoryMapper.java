package com.example.tlyanepos;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TbContentCategoryMapper {
    List<TbContentCategory> selectByExample(TbContentCategoryExample example);
}
