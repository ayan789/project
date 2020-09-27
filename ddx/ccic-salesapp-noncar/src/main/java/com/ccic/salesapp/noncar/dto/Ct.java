package com.ccic.salesapp.noncar.dto;

import java.util.List;

import lombok.Data;
@Data
public class Ct {
		String productName;
		List<NoncarPlanCt> noncarPlanCtList;
}
