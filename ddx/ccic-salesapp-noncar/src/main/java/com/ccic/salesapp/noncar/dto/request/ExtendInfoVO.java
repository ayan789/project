package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;



/**
 * 扩展信息VO
 * @author lining
 *
 */
public class ExtendInfoVO extends DataVO{

	private String key; //配置键
	private String value; //配置值
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
