package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class LiftDetail {
	private Double approvalMass;// 核定载重量(KG)
	private int approvalSeatCount;// 核定载客/核定座位数（核定乘客座位数）
	private Date commissioningDate;// 投产日期
	private String liftBrandModel;// 电梯品牌型号
	private int liftCount;// 电梯数量
	private String liftLicenseNo;// 电梯准用证/使用登记证书编号
	private String liftPurpose;// 电梯用途
	private String liftType;// 电梯类型
	private String manuUnit;// 制造单位
	private String manuUnitLicenseNo;// 制造单位许可编号
	private Double speed;// 速度(m/s)
	private String unitNo;// 单位内编号
	private int useSeniority;// 已使用年限
	private Date validThru;// 有效期至
	private Long sequenceNumber;// 序号
	private Map<String, String> tempData;// 临时数据
}
