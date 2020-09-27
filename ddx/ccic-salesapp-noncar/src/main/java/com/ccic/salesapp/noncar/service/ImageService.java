package com.ccic.salesapp.noncar.service;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.InsuranceApplication;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.order.response.OrderResponseUrl;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.ImageUploadRequestVO;
import com.ccic.salesapp.noncar.dto.request.UploadSignBookReqVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.UploadSignBookSubmissionVo;
import com.ccic.salessapp.common.request.LoginUser;



public interface ImageService { 
	//获取影像上传页面地址
	public JsonVO uploade(ImageUploadRequestVO req,InsuranceApplication application, LoginUser user) throws Exception;

	public Boolean uploadSignBook(UploadSignBookReqVO uploadSignBookReqVO, StoreInsureInfo storeInsureInfo)throws Exception;

	public Boolean uploadSignBookTwo(UploadSignBookReqVO uploadSignBookReqVO, NoncarOrder noncarOrder) throws Exception;

	public List<String> findByProductClause(String proposalNo);

	public List<String> findBylistReadInform(String proposalNo);

	public List<String> findBylistActoionInform(String proposalNo);

	public NoncarOrder findInsureMobile(String proposalNo);

	public List<PlanAttach> findTbxz(Integer integer);

	public PlanAttach findhealthNotification(Integer planId);

	public Boolean uploadSubmissionSignUpload(UploadSignBookSubmissionVo uploadSignBookSubmissionVo,
			NoncarOrder noncarOrderCombination, List<NoncarOrder> requestNoncarOrder, NoncarOrder noncarOrder) throws Exception;
}
