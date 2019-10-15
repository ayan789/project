package com.example.tlyannoif;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserDao {

    User getById(Integer id);

    int insertUserBatch(List<User > userList);

    int updateUserBatch(List<User > userList);

}

