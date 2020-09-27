package com.ccic.salesapp.noncar.dto.request;

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
@ApiModel(value = "UploadFileRequest", description = "文件相关接口请求bean")
public class UploadFileRequest {
	/**
     * 网络类型
     */
    private String netType;
	/**
     * 机构号码
     */
    private String comCode;
    /**
     * 操作员代码
     */
    private String operator;
    /**
     * 操作员角色
     */
    private String operatorRole;

    /**
     * 业务类型
     */
    private String appCode;
    /**
     * 分区年份
     * 格式YYYYMMDD,如果无法获取月份或日信息，月份或日使用00代替
     */
    private String busiDate;
    /**
     * 业务编号
     */
    private String 	businessNo;
    /**
     * 影像ID
     */
    private List<String> pageIds;
}
