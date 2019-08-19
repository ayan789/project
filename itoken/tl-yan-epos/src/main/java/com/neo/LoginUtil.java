package com.neo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liulei on 2017-04-28.
 */
@Slf4j
public class LoginUtil {

//	private static final Integer TIMEOUT = 3600 * 2;

//	public static final String SUPER_USER_ID = "pltEposAdmin";
//	public static final String SUPER_USER_PASSWORD = "SalEs@0!8uPup";

	public static final String UOB_CHANNEL = "024";
	public static final String SCB_CHANNEL = "014";
	public static final String AGENCY_CHANNEL = "000";
	public static final String TBANK_CHANNEL = "065";

	private LoginUtil() {

	}


	public static Locale currentLocale() {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//		Locale locale = getLocale(request);
//		if (locale != null) {
//			log.info("locale message - " + locale);
//			return locale;
//		} else {
//			log.info("no locale message - " + locale);
			return Locale.US;
//		}
	}

	public static Locale getLocale(HttpServletRequest request) {

		Locale locale;

		String header = Constant.Login.ACCEPT_LANGUAGE;

		String language = CookieUtil.getValueByName(request, header);

		if (org.apache.commons.lang.StringUtils.isEmpty(language)) {

//			locale = RequestContextUtils.getLocale(request);
			locale = Locale.US;
		} else {
			String[] split = language.split("_");
			if (split.length > 1) {
				locale = new Locale(split[0], split[1]);
			} else {
				locale = new Locale(split[0]);
			}

		}


		return locale;
	}

	

}
