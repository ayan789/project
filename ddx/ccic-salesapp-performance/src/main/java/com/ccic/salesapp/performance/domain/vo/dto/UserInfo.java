package com.ccic.salesapp.performance.domain.vo.dto;

import com.ccic.salesapp.performance.domain.vo.request.UserInfoSaveRequest;
import lombok.Data;

@Data
public class UserInfo {
	private String Id;
	private String userCode;
	private String userName;
	private String userEname;
	private String deptName;
	private String deptCodeMake;
	private String deptNameMake;
	private String userTel;
	
//    private long userId;
//    private String username;
//    private String name;

//    public static UserInfo from(UserInfoSaveRequest request){
//        UserInfo userInfo=new UserInfo();
//        userInfo.setName(request.getName());
//        userInfo.setUsername(request.getUserName());
//        return userInfo;
//    }
}
