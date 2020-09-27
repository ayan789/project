package com.ccic.salesapp.noncar.dto.request;
import java.util.ArrayList;
import java.util.List;
import com.ccic.salesapp.noncar.dto.DataVO;


/**
 * 渠道项目信息
 * @author Administrator
 *
 */
public class ProjectChannelVO extends DataVO {

	private String projectSerialNo ;//项目编号
	private String projectName;//项目名称
	private String repairChannelCode;//业务编码
	private String carscheme ;//服务套餐代码
	List<ExtendInfoVO> extendInfo = new ArrayList<ExtendInfoVO>();
	
	private String repairChannelName;//业务编码名称
	
	public String getProjectSerialNo() {
		return projectSerialNo;
	}
	public void setProjectSerialNo(String projectSerialNo) {
		this.projectSerialNo = projectSerialNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRepairChannelCode() {
		return repairChannelCode;
	}
	public void setRepairChannelCode(String repairChannelCode) {
		this.repairChannelCode = repairChannelCode;
	}
	public String getCarscheme() {
		return carscheme;
	}
	public void setCarscheme(String carscheme) {
		this.carscheme = carscheme;
	}
	public List<ExtendInfoVO> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(List<ExtendInfoVO> extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getRepairChannelName() {
		return repairChannelName;
	}
	public void setRepairChannelName(String repairChannelName) {
		this.repairChannelName = repairChannelName;
	}
	
}
