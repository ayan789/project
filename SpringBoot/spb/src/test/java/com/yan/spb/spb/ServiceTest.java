package com.yan.spb.spb;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ServiceTest extends BaseTest {


    @Autowired
    private UserService userService;

    @Test
    public void dynamicDataSourceTest() throws Exception {
        List<User> userList = userService.getByMap(null);
        System.out.println("--userList---" + userList.toString());

    }
}