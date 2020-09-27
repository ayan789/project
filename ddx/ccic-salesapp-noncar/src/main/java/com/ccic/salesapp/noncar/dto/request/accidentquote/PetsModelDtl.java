package com.ccic.salesapp.noncar.dto.request.accidentquote;

import lombok.Data;

@Data
public class PetsModelDtl {
	private String age; //周岁   
	private String petsVariety; //品种          
	private String petsName; //姓名              
	private String petsColour; //颜色            
	private String petsGender; //性别            
	private String dateOfBirth; //出生日期           
	private String contraceptionOrNot; //是否节育    
	private String permitNumber; //许可证号码          
	private String immuneSyndromeNumber; //免疫证号码  
	private String chipNumber; //芯片号码            
	private String ortherMessage; //其他信息或说明    
}
