package com.ccic.salessapp.common.outService.rest.common.bean;

/**
 * 用于替代老系统的Response
 * @see Response
 */
public class RestfulResponse <T> {
    private ResponseHead responseHead;
    private T responseBody;

    public ResponseHead getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(ResponseHead responseHead) {
        this.responseHead = responseHead;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }
}
