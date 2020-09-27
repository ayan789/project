package com.ccic.salessapp.common.outService.rest.common.bean;

public class Request {

	private RequestHead requestHead;
	private RequestBody requestBody;

	public RequestHead getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "Request [requestHead=" + requestHead + ", requestBody=" + requestBody + "]";
	}

}
