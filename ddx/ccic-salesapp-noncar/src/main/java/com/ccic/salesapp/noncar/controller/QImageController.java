package com.ccic.salesapp.noncar.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ccic.salesapp.noncar.dto.InsuranceApplication;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.request.ImageUploadRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.service.ImageFacade;
import com.ccic.salesapp.noncar.service.StoreProductPlaceUtilService;
import com.ccic.salesapp.noncar.service.impl.ImageFacadeImpl;
import com.ccic.salesapp.noncar.service.impl.StoreProductPlaceUtilServiceImpl;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.LoginUser;

 @RestController
public class QImageController {
	/**
     * 影像上传地址申请
     * */
	 @PostMapping("/imageUpload")
	public HttpResult  imageUpload(Object jsonVO) throws Exception{
    	JsonVO json = (JsonVO) jsonVO;
		LoginUser user=(LoginUser)json.getHead().getLoginUser();//获取当前登录的业务员信息
		ImageUploadRequestVO reqvo=(ImageUploadRequestVO)json.getData();
		if("".equals(reqvo.getProposalNo())||reqvo.getProposalNo()==null){
			HttpResult result = HttpResult.error(0, "投保单号不能为空");
			return result;
		}

		InsuranceApplication application = new InsuranceApplication() ;
		if("LBU".equals(reqvo.getProposalNo().substring(1, 4))||"QPU".equals(reqvo.getProposalNo().substring(1, 4))){//一揽子保险
			application = queryC(reqvo.getProposalNo());    //非车险根据投保单号查询投保单信息
		}else{												//车险
			application = query(reqvo.getProposalNo(),user);//根据投保单号查询投保单信息
		}

		if(application==null) {
			application = new InsuranceApplication();
			application.setStatus("7");
			if(reqvo.getProposalNo().length()<24) {
				application.setComCode(reqvo.getProposalNo().substring(8,16));
			}else {
				application.setComCode(reqvo.getProposalNo().substring(6,14));
			}
			application.setRiskCode(reqvo.getProposalNo().substring(1,4));
		}
		if(application.getStatus().equals(InsuranceApplicationStatus.HEBAOZHONG)){
			HttpResult result = HttpResult.error(0, "核保中的订单不能上传影像");
			return result;
		}
		ImageFacade imageFacade=new ImageFacadeImpl();
		JsonVO jsonvo=imageFacade.uploade(reqvo,application,user);
		json.setData(jsonvo.getData());
		json.setResult(jsonvo.getResult());
		
		HttpResult result = HttpResult.success(json);
		result.setCode("1");
		result.setMsg("查询成功");
		return result;
	}
	
	/**
	 * 非车险投保单信息查询
	 */
	private InsuranceApplication queryC(String proposalNo) throws Exception {
		InsuranceApplication insuranceApp = new InsuranceApplication();
		StoreProductPlaceUtilService stoFacade = new StoreProductPlaceUtilServiceImpl();
		com.ccic.salesapp.noncar.dto.StoreInsureInfo storeInsureInfo = stoFacade.findStoreInsureInfoByInsureNo(proposalNo);
		//非车险投保单信息
		insuranceApp.setStatus(storeInsureInfo.getStatus());
		insuranceApp.setRiskCode(storeInsureInfo.getInsuranceCode());
		insuranceApp.setComCode(storeInsureInfo.getComCode());

		return insuranceApp;
	}
	
	private InsuranceApplication query(String proposalNo,LoginUser user) throws Exception {
		InsuranceApplication insuranceApp = new InsuranceApplication();
		return insuranceApp;
	}

}
