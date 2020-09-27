package com.ccic.salesapp.noncar.service;

public interface HttpService {

	String postJsonRequest(String bussNo, String reqJson, String uri) throws Exception;

	String postGroupRequest(String bussNo, String reqJson, String uri) throws Exception;

}
