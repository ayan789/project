package com.ccic.salesapp.noncar.dto.multiplexingHook;

import java.util.List;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;

import lombok.Data;
@Data
public class ImageLinkRequestDTO  extends RequestBody  {

	private BaseDataDTO baseData;//公共信息
	private SourceImageDTO sourceBatch;//来源批次信息
	private TargetImageDTO targetBatch;//目标批次信息
	private List<ReuseImagesDTO> reuseImage;
}
