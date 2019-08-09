package com.neo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created by zhangjunwen on 2018/5/8
 *
 * @author zhangjunwen
 * @date 2018/5/8
 */
@Slf4j
public class LocaleMessage {

	public static final Locale THAI = new Locale("th", "TH");

	private MessageSource messageSource;

	private void getMessageSource() {

		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();

		resourceBundleMessageSource.setBasenames("i18n/messages", "i18n/info");
		resourceBundleMessageSource.setDefaultEncoding("utf-8");

		messageSource = resourceBundleMessageSource;
	}

	/**
	 * 获取指定Locale的信息
	 *
	 * @param code
	 * @param locale
	 * @return
	 */
	public String getMessage(String code, Locale locale) {


		return getMessage(code, locale, "");
	}

	/**
	 * 默认Locale en_US
	 *
	 * @param code
	 * @return
	 */
	public String getMessage(String code) {
		Locale locale = LoginUtil.currentLocale();
		if (locale == null) {
			locale = Locale.US;
		}
		return getMessage(code, locale);
	}

	/**
	 * 指定默认信息
	 *
	 * @param code
	 * @param defaultMessage
	 * @return
	 */
	public String getMessage(String code, String defaultMessage) {

		return getMessage(code, Locale.US, defaultMessage);
	}

	public String getMessage(String code, Locale locale, String defaultMessage) {

		if (messageSource == null) {
			getMessageSource();
		}
		if (defaultMessage == null) {
			defaultMessage = "";
		}

		return messageSource.getMessage(code, null, defaultMessage, locale);
	}


}
