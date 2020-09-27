package com.ccic.salesapp.performance.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.performance.domain.mapper.AssessMapper;
import com.ccic.salesapp.performance.domain.vo.request.AssessRequest;
import com.ccic.salesapp.performance.domain.vo.response.AssessResponse;
import com.ccic.salessapp.common.core.web.HttpResult;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssessService {

	@Autowired
	AssessMapper assessMapper;



	/***
	 * 销售行动能力评估
	 * 返回AssessResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HttpResult<AssessResponse> getAssessItemsByUserCode(AssessRequest request){
		AssessResponse res = new AssessResponse();
		try {
			res=assessMapper.getAssessItemsByUserCode(request.getUserCode());
			if(res == null) {
				res = new AssessResponse();
			}
			return HttpResult.success(res);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return HttpResult.error(0, e.getMessage());
		}		
	}

}
