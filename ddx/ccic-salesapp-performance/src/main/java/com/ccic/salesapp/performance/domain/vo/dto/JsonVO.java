package com.ccic.salesapp.performance.domain.vo.dto;
import java.util.List;

import lombok.Data;

import com.ccic.salesapp.performance.domain.vo.request.PageVO;
import com.ccic.salesapp.performance.domain.vo.response.EnchashmentResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.FeePayDetailResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.InviteListResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.InviteStateResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.InviteUserResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.ModifyModbileResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.PlanFeeInquiryResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.QueryConfigResponseVO;
import com.ccic.salesapp.performance.domain.vo.response.RankResponseVo;
import com.ccic.salesapp.performance.domain.vo.response.UpdateUserScoreResponseVo;
import com.fasterxml.jackson.annotation.JsonInclude;




@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class JsonVO  {
	HeadVO head = new HeadVO();
	PageVO page ;
	ResultVO result;
	UserVO data;
	PlanFeeInquiryResponseVO planFeeInquiryResponseVO;
	EnchashmentResponseVO enchashmentResponseVO;
	QueryConfigResponseVO queryConfigResponseVO;
	FeePayDetailResponseVO feePayDetailResponseVO;
	InviteListResponseVO inviteListResponseVO;
	ModifyModbileResponseVo modifyModbileResponseVo;
	InviteUserResponseVO inviteUserResponseVO;
	InviteStateResponseVO inviteStateResponseVO;
	UpdateUserScoreResponseVo updateUserScoreResponseVo;
	com.ccic.salesapp.performance.domain.vo.request.UpdateUserScoreRequestVo UpdateUserScoreRequestVo;
	List list;
	
	RankResponseVo rankResponseVo;
	
}
