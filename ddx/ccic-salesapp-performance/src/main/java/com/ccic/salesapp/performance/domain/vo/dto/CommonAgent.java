package com.ccic.salesapp.performance.domain.vo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CommonAgent {
	 private String id;
	 private String staffCode;//员工编号
     private String businessSource;//业务来源
     private String agentCode;//代理人代码
     private String agentName;//代理人姓名
     private Date createDate;//创建时间
     private Date updateDate;//更新时间

}
