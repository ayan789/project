package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class MachineDetail {
	private String annualInspectionCertNo ;//年检证书编号
	private String cisternType ;//容器类型
	private Date commissioningDate ;//投产日期
	private String engineNo ;//发动机号
	private String equipmentName ;//设备名称
	private String equipmentType ;//设备类型，锅炉/农机代码不一样
	private Date factoryDate ;//出厂日期
	private String factoryNo ;//出厂编号
	private String isSingle ;//单台/多台设备
	private String machineLicenseNo ;//《设备使用证》号
	private Date machineLicenseNoValidThru ;//《设备使用证》有效期
	private String manuUnitLicenseNo ;//制造单位许可编号
	private String manufacturer ;//生产厂家、制造单位
	private String model ;//型号/规格/机型
	private String modelCode ;//型号/规格/机型
	private Double newEquiPurchPrice ;//新设备购置价
	private String productionPurpose ;//生产用途
	private String specialEquipLicenseNo ;//特种设备使用登记证书编号
	private Date specialEquipLicvalidThru ;//特种设备有效期至
	private String specialEquipType ;//特种设备类型
	private int tonnage ;//吨位
	private String unitNo ;//单位内编号
	private String vin ;//车架号/VIN码
	private int volume ;//容积
	private Long sequenceNumber ;//序号
    private Map<String, String> tempData;// 临时数据
}
