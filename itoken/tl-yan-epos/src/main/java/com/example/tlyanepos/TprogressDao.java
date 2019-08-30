package com.example.tlyanepos;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TprogressDao {
    List<Tprogress> getByProgressMap();

    List<Tprogress>  getByProgressOne();
}
