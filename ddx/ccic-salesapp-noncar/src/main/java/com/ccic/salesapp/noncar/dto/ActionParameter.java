package com.ccic.salesapp.noncar.dto;
import javax.servlet.ServletContext;
import javax.servlet.http.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ActionParameter {
//	private ActionMapping mapping;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private FormBase form;
	private HttpSession session;
	private ServletContext sc;
	public ServletContext getSc() {
		return sc;
	}
	public void setSc(ServletContext sc) {
		this.sc = sc;
	}
	public ActionParameter() {
	}
	public FormBase getForm() {
		return form;
	}
	public void setForm(FormBase form) {
		this.form = form;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

}
