package com.ccic.salesapp.noncar.dto;
import java.util.List;
import com.ccic.salesapp.noncar.dto.request.ExtendInfoVO;


/**
 * 精友整车库信息
 * @author 孟秀萍
 *
 */
public class VehicleJingyouVO extends DataVO{
	
	 private String industryModelCode;//行业车型编码
	 private String industryVehicleCode;//公告型号
	 private String industryVehicleName;//车款名称
	 private String vehicleCode;//车辆编码               
	 private String vehicleName;//车型名称               
	 private String factoryCode;//厂家编码               
	 private String factoryName;//厂家名称               
	 private String brandCode;//品牌编码                 
	 private String brandName;//品牌名称                 
	 private String familyCode;//车系编码                
	 private String familyName;//车系名称                
	 private String vehicleClassCode;//车型种类编码      
	 private String vehicleClassName;//车型种类名称      
	 private String vehicleType;//种类                   
	 private String displacement;//排气量                
	 private String seat;//核定载客（座位）              
	 private String tonnage;//核定载质量（吨位）         
	 private String gearboxType;//变速箱类型             
	 private String price;//新车购置价     
	 private String purchasePriceTax;//新车购置价(含税) 1
	 private String purchasePrice;//不含税新车购置价 2
	 private String hfCode;//车船税减免类型代码 3
	 private String hfName;//车船税减免类型代码说明 4 2013-04-02 更改
	 private String pricetype;//价格类型      01 新车购置价02 新车购置价(含税)03 类比车型价04 类比车型价(含税           
	 private String antiTheft;//防盗装置    0-无;1-有             
	 private String airbagNum;//气囊数                   
	 private String marketDate;//上市年份                
	 private String riskFlags;//车型风险标识   0-正常车型;1-稀有车型;2-特异车型;3-老旧车型;4-配件疑难          
	 private String stopFlag;//是否停产                  
	 private String fullWeight;//整备质量                
	 private String rate;//承保系数                      
	 private String searchCode;//速查码,                 
	 private String power;//功率,                        
	 private String fitsRiskRate;//车型配件风险系数      
	 private String groupFitsRate;//车组绝对换件指数     
	 private String groupRepairRate;//车组绝对修理指数   
	 private String carType;//货车分类                   
	 private String bdRiskFlag1;//自定义风险标识1        
	 private String bdRiskFlag2;//自定义风险标识2        
	 private String bdRiskFlag3;//自定义风险标识3        
	 private String bdRiskFlag4;//自定义风险标识4        
	 private String remark;//备注                        
	 private String flag;//标识    
	 private List<ExtendInfoVO> extendInfo;
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getVehicleClassCode() {
		return vehicleClassCode;
	}
	public void setVehicleClassCode(String vehicleClassCode) {
		this.vehicleClassCode = vehicleClassCode;
	}
	public String getVehicleClassName() {
		return vehicleClassName;
	}
	public void setVehicleClassName(String vehicleClassName) {
		this.vehicleClassName = vehicleClassName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getTonnage() {
		return tonnage;
	}
	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}
	public String getGearboxType() {
		return gearboxType;
	}
	public void setGearboxType(String gearboxType) {
		this.gearboxType = gearboxType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPricetype() {
		return pricetype;
	}
	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}
	public String getAntiTheft() {
		return antiTheft;
	}
	public void setAntiTheft(String antiTheft) {
		this.antiTheft = antiTheft;
	}
	public String getAirbagNum() {
		return airbagNum;
	}
	public void setAirbagNum(String airbagNum) {
		this.airbagNum = airbagNum;
	}
	public String getMarketDate() {
		return marketDate;
	}
	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}
	public String getRiskFlags() {
		return riskFlags;
	}
	public void setRiskFlags(String riskFlags) {
		this.riskFlags = riskFlags;
	}
	public String getStopFlag() {
		return stopFlag;
	}
	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}
	public String getFullWeight() {
		return fullWeight;
	}
	public void setFullWeight(String fullWeight) {
		this.fullWeight = fullWeight;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSearchCode() {
		return searchCode;
	}
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getFitsRiskRate() {
		return fitsRiskRate;
	}
	public void setFitsRiskRate(String fitsRiskRate) {
		this.fitsRiskRate = fitsRiskRate;
	}
	public String getGroupFitsRate() {
		return groupFitsRate;
	}
	public void setGroupFitsRate(String groupFitsRate) {
		this.groupFitsRate = groupFitsRate;
	}
	public String getGroupRepairRate() {
		return groupRepairRate;
	}
	public void setGroupRepairRate(String groupRepairRate) {
		this.groupRepairRate = groupRepairRate;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getBdRiskFlag1() {
		return bdRiskFlag1;
	}
	public void setBdRiskFlag1(String bdRiskFlag1) {
		this.bdRiskFlag1 = bdRiskFlag1;
	}
	public String getBdRiskFlag2() {
		return bdRiskFlag2;
	}
	public void setBdRiskFlag2(String bdRiskFlag2) {
		this.bdRiskFlag2 = bdRiskFlag2;
	}
	public String getBdRiskFlag3() {
		return bdRiskFlag3;
	}
	public void setBdRiskFlag3(String bdRiskFlag3) {
		this.bdRiskFlag3 = bdRiskFlag3;
	}
	public String getBdRiskFlag4() {
		return bdRiskFlag4;
	}
	public void setBdRiskFlag4(String bdRiskFlag4) {
		this.bdRiskFlag4 = bdRiskFlag4;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPurchasePriceTax() {
		return purchasePriceTax;
	}
	public void setPurchasePriceTax(String purchasePriceTax) {
		this.purchasePriceTax = purchasePriceTax;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getHfCode() {
		return hfCode;
	}
	public void setHfCode(String hfCode) {
		this.hfCode = hfCode;
	}
	public String getHfName() {
		return hfName;
	}
	public void setHfName(String hfName) {
		this.hfName = hfName;
	}
	public String getIndustryModelCode() {
		return industryModelCode;
	}
	public void setIndustryModelCode(String industryModelCode) {
		this.industryModelCode = industryModelCode;
	}
	public String getIndustryVehicleCode() {
		return industryVehicleCode;
	}
	public void setIndustryVehicleCode(String industryVehicleCode) {
		this.industryVehicleCode = industryVehicleCode;
	}
	public String getIndustryVehicleName() {
		return industryVehicleName;
	}
	public void setIndustryVehicleName(String industryVehicleName) {
		this.industryVehicleName = industryVehicleName;
	}
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	  


}
