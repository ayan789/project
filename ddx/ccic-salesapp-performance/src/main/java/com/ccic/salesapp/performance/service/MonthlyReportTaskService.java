package com.ccic.salesapp.performance.service;

import java.util.HashMap;
import java.util.List;

public interface MonthlyReportTaskService {
	
	void insertListService(List<String> userCodes,HashMap<String, Object> map,int step);
	
	void updateListSendMsgService(List<String> userCodes,HashMap<String, Object> map);

}
