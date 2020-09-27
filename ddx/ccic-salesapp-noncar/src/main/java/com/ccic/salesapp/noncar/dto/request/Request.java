package com.ccic.salesapp.noncar.dto.request;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

public class Request {

	private RequestHead requestHead;
	private AccidentPolicyRequest requestBody;

	public RequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHead requestHead) {
		this.requestHead = requestHead;
	}

	

	public AccidentPolicyRequest getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(AccidentPolicyRequest requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "Request [requestHead=" + requestHead + ", requestBody=" + requestBody + "]";
	}

}
