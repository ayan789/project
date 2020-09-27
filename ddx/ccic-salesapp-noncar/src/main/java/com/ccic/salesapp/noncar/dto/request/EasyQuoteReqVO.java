package com.ccic.salesapp.noncar.dto.request;
import java.util.ArrayList;
import java.util.List;
import com.ccic.salesapp.noncar.dto.DataVO;
/**
 * 简单报价输入VO
 * @author lining
 *
 */
public class EasyQuoteReqVO extends DataVO{
	 
	private String orderNo; //订单号   第二次或者第N次精确报价时必传	
	private String riskCode; //险种代码
	private String makeCom; //出单机构
	private String comCode; //归属机构
	private String handlerCode; //经办员
	private String businessNature; //业务来源
	private String businessAttribute; //业务来源属性
	private String operateDate; //交易日期
	private String startDate; //起保日期
	private String endDate; //终保日期
	private String shortRateFlag; //商业险短期费率计算方式 1：全年；2：按月；3：按日
	private CarVO car=null; //车辆信息
	private List<KindVO> kind = new ArrayList<KindVO>(); //险别信息
	private String business; //被保险人性质
	private String unitType;//被保险人单位性质
	private ChannelVO channelDTO=null;
	private List<ExtendInfoVO> extendInfo = new ArrayList<ExtendInfoVO>();
	
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getMakeCom() {
		return makeCom;
	}
	public void setMakeCom(String makeCom) {
		this.makeCom = makeCom;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getBusinessAttribute() {
		return businessAttribute;
	}
	public void setBusinessAttribute(String businessAttribute) {
		this.businessAttribute = businessAttribute;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getShortRateFlag() {
		return shortRateFlag;
	}
	public void setShortRateFlag(String shortRateFlag) {
		this.shortRateFlag = shortRateFlag;
	}
	
	public List<KindVO> getKind() {
		return kind;
	}
	public void setKind(List<KindVO> kind) {
		this.kind = kind;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public ChannelVO getChannelDTO() {
		return channelDTO;
	}
	public void setChannelDTO(ChannelVO channelDTO) {
		this.channelDTO = channelDTO;
	}
	public CarVO getCar() {
		return car;
	}
	public void setCar(CarVO car) {
		this.car = car;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
