package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class Patent {
	private String paten ;//专利权人
	private Date patentAnnouncedDate ;//专利公告日
	private Date patentApplyDate ;//专利申请日
	private String patentInventor ;//发明人/设计人
	private String patentName ;//专利名称
	private String patentNo ;//专利号
	private String patentType ;//专利类型
	private Long sequenceNumber ;//序号
	private Map<String, String> tempData;// 临时数据
}
