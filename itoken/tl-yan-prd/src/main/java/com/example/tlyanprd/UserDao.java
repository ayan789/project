package com.example.tlyanprd;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    List<User> getByMap(Map<String, Object> map);

}
