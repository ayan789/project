package com.ccic.salesapp.noncar.dto;

import lombok.Data;

@Data
public class Coins {
	private String mainProposalNo;//主投保单号码
	private String coinsCode;//联/共保人代码
	private String coinsName;//联/共保人名称
	private String coinsType;//联/共保类型（身份）
	private String coinsRate;//联/共保份额比例
}
