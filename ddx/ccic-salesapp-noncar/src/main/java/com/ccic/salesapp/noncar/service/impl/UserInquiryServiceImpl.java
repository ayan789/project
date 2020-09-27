package com.ccic.salesapp.noncar.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.UserRoleVO;
import com.ccic.salesapp.noncar.dto.UserVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.outService.esb.userservice.bean.UserInquiryRequest;
import com.ccic.salesapp.noncar.outService.esb.userservice.bean.UserInquiryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.userservice.bean.UserInquiryResponse;
import com.ccic.salesapp.noncar.outService.esb.userservice.bean.UserInquiryResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.userservice.common.bean.UserDTO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UserRoalMapper;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.thoughtworks.xstream.XStream;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInquiryServiceImpl  implements com.ccic.salesapp.noncar.service.UserInquiryService {
	
	@Autowired
	com.ccic.salesapp.noncar.outService.esb.userservice.userinquiry.intf.UserInquiryService service;
	
	@Autowired
	UserRoalMapper userRoleMapper;
	/**
	 * 个人信息查询
	 */
	@Override
	public HttpResult userInquiry(String userID)throws Exception{
		HttpResult httpResult =HttpResult.success();
		JsonVO jsonVO=new JsonVO();
		//com.ccic.salesapp.noncar.outService.esb.userservice.userinquiry.intf.UserInquiryService service=(UserInquiryService)this.getBean("userInquiry");
		RequestHeadDTO requestHead = getConsumerID();
		
		UserInquiryRequestDTO requestBodyDTO=new UserInquiryRequestDTO();
		requestBodyDTO.setUsercode(userID);
		
		UserInquiryRequest userInquiryRequest=new UserInquiryRequest();
		userInquiryRequest.setRequestHead(requestHead);
		userInquiryRequest.setRequestBody(requestBodyDTO);
		
		UserInquiryResponse userInquiryResponse=service.getUser(userInquiryRequest);
		
		int stat=userInquiryResponse.getResponseHead().getStatus();//0 成功，1失败
		String appmessage=userInquiryResponse.getResponseHead().getAppMessage();
		String esbmessage=userInquiryResponse.getResponseHead().getEsbMessage();
		log.info("返回状态："+stat);
		log.info("返回app信息："+appmessage);
		log.info("返回esb信息："+esbmessage);
		
		if(stat==0){//验证成功
			UserInquiryResponseDTO userInquiryResponseDTO=userInquiryResponse.getResponseBody();
			UserDTO userDTO=userInquiryResponseDTO.getUser();
			log.info("登录用户名："+userDTO.getUserName());
			
			UserVO userVO=getUserVO(userDTO);
			httpResult.setData(userVO);
			
			httpResult.setCode("1");
			httpResult.setMsg("成功");
		}else{			
			httpResult.setCode("0");
			httpResult.setMsg(appmessage);
		}
		
		return httpResult;
		
	}
	
	
	public RequestHeadDTO getConsumerID(){
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		return requestHead;
	}
	
	public String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public UserVO getUserVO(UserDTO userDTO)throws Exception{
		
		UserVO userVO=new UserVO();
		String userCode = userDTO.getUsercode();
		userVO.setUserCode(userCode); //员工代码
		userVO.setUserName(userDTO.getUserName()); //员工名称	
		userVO.setUserEName(userDTO.getUserEName()); //员工英文名称
		userVO.setCompany(userDTO.getCompany().getComcode()); //归属机构编码
		userVO.setComCName(userDTO.getCompany().getComCName()); //归属机构名称
		userVO.setMakeCompany(userDTO.getMakeCompany().getComcode()); //出单机构编码
		userVO.setMakeCompanyCName(userDTO.getMakeCompany().getComCName()); //出单机构名称
		userVO.setPhone(userDTO.getPhone()); //电话号码
		userVO.setMobile(userDTO.getMobile());  //	手机号码
		userVO.setAddress(userDTO.getAddress());  //通信地址
		userVO.setPostcode(userDTO.getPostcode());  //邮编
		userVO.setEmail(userDTO.getEmail());  //邮箱
		if(userDTO.isValid()){
			userVO.setValid("1");  //有效性
		}else{
			userVO.setValid("0");  //有效性
		}
		
		userVO.setPassword("");//密码,暂时为空
		
		userVO.setComRole("0");
		try {
			/*
			 * List<UserRoleVO> userRole = userRoleMapper.toList(userCode); if
			 * (userRole.size()!=0) { userVO.setComRole("1"); }
			 */	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XStream xs=new XStream();
		log.info("登录返回信息："+xs.toXML(userVO));
		return userVO;
	}
}
