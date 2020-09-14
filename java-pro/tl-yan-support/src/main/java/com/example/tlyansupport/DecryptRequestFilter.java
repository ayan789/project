package com.example.tlyansupport;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class DecryptRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		PltHttpServletRequestWrapper wrapper = null; 
		wrapper = new PltHttpServletRequestWrapper(request);
		filterChain.doFilter(wrapper, response);
		
	}
	
	 public class PltHttpServletRequestWrapper extends HttpServletRequestWrapper {

		public PltHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
			String body = null;
			String source = null;
			try {
				source = IOUtils.toString(request.getInputStream(), "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			 System.out.println(source);
			 if("1".equals(source)) {
				 body = source;
			 }
			//body = StringUtils.isNotBlank(source) ? AESUtil.decrypt(source, key) : "";
	        RequestContext.setBody(body);
		}
		 
	 }

}
