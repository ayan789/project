package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class GuaranteeGroup {
	//保障类型(全球/国际/国际增强/大陆及港澳台/大陆及港澳台增强保障)
		private String guaranteeType;
		//投保类型(单人/夫妇/亲子/家庭保障)
		private String applicationType;
		//保费
		private BigDecimal premium;
		//户数
		private Long householdCount;
}
