package com.ccic.salesapp.noncar.dto.multiplexingHook;

import lombok.Data;

@Data
public class ReuseImagesDTO {

	private String sourceImageType;
	private String targetImageType;
	private boolean isCovered;
}
