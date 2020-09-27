package com.ccic.salesapp.noncar.feign;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * 2020/03/04
 *
 * @author gaoxiang
 */
@Data
@ApiModel(value = "FileRequest", description = "文件相关接口请求bean")
public class FileRequest {
	/**
	 * 机构号码
	 */
	private String comCode;
	/**
	 * 客户号
	 */
	private String custNo;

	/**
	 * 影像类型代码
	 */
	private String imgType;
	/**
	 * 险类
	 */
	private String riskCode;
	/**
	 * 业务编号(操作员ID_证件号码)
	 */
	private String bussNo;
	/**
	 * 分区年份 格式YYYYMMDD,如果无法获取月份或日信息，月份或日使用00代替
	 */
	private String busiDate;
	private List<FileVo> files;

	@Data
	public static class FileVo {
		/**
		 * base64文件
		 */
		private String file;
		/**
		 * 影像类型代码
		 */
		private String imgType;
		/**
		 * 影像类型名称
		 */
		private String imgTypeName;
		/**
		 * 文件名
		 */
		private String fileName;
	}
}
