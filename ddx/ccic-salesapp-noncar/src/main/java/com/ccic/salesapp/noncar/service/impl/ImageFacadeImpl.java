package com.ccic.salesapp.noncar.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import com.ccic.salesapp.noncar.dto.ImgBusiDTO;
import com.ccic.salesapp.noncar.dto.ImgPrevUploadRequestDTO;
import com.ccic.salesapp.noncar.dto.InsuranceApplication;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.request.AbstractFacade;
import com.ccic.salesapp.noncar.dto.request.ImageUploadRequestVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.request.ImgPrevUploadRequest;
import com.ccic.salesapp.noncar.dto.response.ImageUploadResponseVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ExtendInfo;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ExtendInfoDTO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ImgPrevUploadResponse;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ImgPrevUploadResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.intf.ImgPrevUploadPort;
import com.ccic.salesapp.noncar.service.ImageFacade;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.request.LoginUser;


@Slf4j
public class ImageFacadeImpl extends AbstractFacade implements ImageFacade {
	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	ImgPrevUploadPort service;

	@Override
	public JsonVO uploade(ImageUploadRequestVO req,InsuranceApplication application,LoginUser user) throws Exception{
		JsonVO jsonVO = new JsonVO();
		ResultVO result = new ResultVO();
		ImgPrevUploadRequestDTO requestDTO=new ImgPrevUploadRequestDTO();
		requestDTO.setNetType("http.outer");//浏览器端网络类型
		requestDTO.setOperatorRole("UWDRole02");//操作角色
		if(application.getStatus().equals(InsuranceApplicationStatus.SHENGXIAO) || application.getStatus().equals(InsuranceApplicationStatus.YIZHIFU)){
			requestDTO.setOperatorRole("UWDRole03");
		}
		requestDTO.setOperator(user.getUserCode());//操作员ID
		requestDTO.setOperatorName(user.getUserName());//操作员姓名
		String comCode=application.getComCode();//机构代码
		String riskCode=application.getRiskCode();//险种代码
		requestDTO.setComCode(comCode);//机构号码
		ImgBusiDTO imgBusiDTO=new ImgBusiDTO();//业务信息
		imgBusiDTO.setAppCode("UW");//业务类型
		
		if("LBU".equals(req.getProposalNo().substring(1,4))||"QPU".equals(req.getProposalNo().substring(1, 4))){// 一揽子保险
		imgBusiDTO.setClassCode("UW_L");
		requestDTO.setOperatorRole("UWNVRole02");//操作角色
		}else{												//车险
			imgBusiDTO.setClassCode("UW_D");
			requestDTO.setOperatorRole("UWDRole02");//操作角色
			if(application.getStatus().equals(InsuranceApplicationStatus.SHENGXIAO) || application.getStatus().equals(InsuranceApplicationStatus.YIZHIFU)){
				requestDTO.setOperatorRole("UWDRole03");
			}
		}
		
		imgBusiDTO.setBusinessNo(req.getProposalNo());//投保单号
		imgBusiDTO.setBusiComCode(comCode);//批次所属机构
		if(req.getProposalNo().length()<24){
		    String reDate=req.getProposalNo().substring(4,8);
		    imgBusiDTO.setBusiDate(reDate+"0000");//批次年份
		}else{
			String reDate=datef.format(new Date());			
			imgBusiDTO.setBusiDate(reDate.substring(0,4)+"0000");
		}    
		requestDTO.setImgBusiDto(imgBusiDTO);
		ImgPrevUploadRequest request=new ImgPrevUploadRequest();
		RequestHeadDTO requestHead = this.getConsumerID();
		requestHead.setSeqNo("");
		requestHead.setRiskCode(riskCode);//险种代码
		request.setRequestHead(requestHead);
		request.setRequestBody(requestDTO);
		//ImgPrevUploadPort service=(ImgPrevUploadPort)this.getBean("imageUpload");
		ImgPrevUploadResponse response=null;
		try {
		    response=service.imgPrevUpload(request);
		    if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用影像上传地址申请接口，返回状态为失败。返回信息："+response.getResponseHead().getAppMessage());
				jsonVO.setResult(result);
				return jsonVO;
			}
		}catch (Exception e) {
			log.error("调用影像上传地址申请接口异常：", e);
			result.setCode("0");
			result.setMessage("调用影像上传地址申请接口异常："+e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}
		ImgPrevUploadResponseDTO responseDTO=response.getResponseBody();
		if(responseDTO!=null){
			ImageUploadResponseVO resp=new ImageUploadResponseVO();
			resp.setUrl(responseDTO.getUrl());
			resp.setToken(responseDTO.getToken());
			if(responseDTO.getExtendFields().size()>0){
			   resp.setExtend(listUploadExtends(responseDTO.getExtendFields()));
			}
			jsonVO.setData(resp);
		    result.setCode("1");
			result.setMessage("获取影像上传地址成功");
			jsonVO.setResult(result);
		}
		return jsonVO;
		
	}
	
	/*
	 * 遍历扩展字段值
	 * */
	private List<ExtendInfo> listUploadExtends(List<ExtendInfoDTO> extendFields) {
		List <ExtendInfo> list=new ArrayList<ExtendInfo>();
		for(ExtendInfoDTO extend:extendFields){
			ExtendInfo extendInfo=new ExtendInfo();
			extendInfo.setKey(extend.getKey());
			extendInfo.setValue(extend.getValue());
			list.add(extendInfo);
		}
		return list;
	}

}
