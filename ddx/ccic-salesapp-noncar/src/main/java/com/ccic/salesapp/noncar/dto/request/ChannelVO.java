package com.ccic.salesapp.noncar.dto.request;
import com.ccic.salesapp.noncar.dto.DataVO;

/**
 * 渠道信息VO
 * @author lining
 *
 */
public class ChannelVO extends DataVO{

	private String channelCode; //渠道代码
	private String channelName; //渠道名称
	private String channelComCode; //渠道机构代码
	private String channelComName; //渠道机构名称
	private String channelProductCode; //渠道产品代码
	private String operator; //渠道操作员代码
	private String trxCode; //渠道交易代码
	private String channelSeqNo; //渠道交易流水号
	private String trxDate; //渠道交易日期
	private String regionCode; //地区标识
	private String makeChannel; //出单渠道
	private String passWord; //交易密码
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelComCode() {
		return channelComCode;
	}
	public void setChannelComCode(String channelComCode) {
		this.channelComCode = channelComCode;
	}
	public String getChannelComName() {
		return channelComName;
	}
	public void setChannelComName(String channelComName) {
		this.channelComName = channelComName;
	}
	public String getChannelProductCode() {
		return channelProductCode;
	}
	public void setChannelProductCode(String channelProductCode) {
		this.channelProductCode = channelProductCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getTrxCode() {
		return trxCode;
	}
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	public String getChannelSeqNo() {
		return channelSeqNo;
	}
	public void setChannelSeqNo(String channelSeqNo) {
		this.channelSeqNo = channelSeqNo;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getMakeChannel() {
		return makeChannel;
	}
	public void setMakeChannel(String makeChannel) {
		this.makeChannel = makeChannel;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
