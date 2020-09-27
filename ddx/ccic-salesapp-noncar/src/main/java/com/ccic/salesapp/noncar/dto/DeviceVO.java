package com.ccic.salesapp.noncar.dto;





/**
 * 新增设备
 * @author Administrator
 *
 */
public class DeviceVO extends DataVO{

	private String serialno ;//序列号 Y
	private String deviceName;//新增设备名称Y
	private String deviceCount;//新增设备数量
	private String devicePurchasePrice;//新增设备新件购置价 Y
	private String buydate;//购置日期 格式：yyyy-MM-dd
	private String deviceActualValue ;//新增设备实际价值 
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(String deviceCount) {
		this.deviceCount = deviceCount;
	}
	public String getDevicePurchasePrice() {
		return devicePurchasePrice;
	}
	public void setDevicePurchasePrice(String devicePurchasePrice) {
		this.devicePurchasePrice = devicePurchasePrice;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public String getDeviceActualValue() {
		return deviceActualValue;
	}
	public void setDeviceActualValue(String deviceActualValue) {
		this.deviceActualValue = deviceActualValue;
	}
	
}
