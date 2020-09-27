package com.ccic.salesapp.noncar.service.impl;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.ccic.salesapp.noncar.dto.CarApplicationDTO;
import com.ccic.salesapp.noncar.dto.CarApplicationSearchRequestDTO;
import com.ccic.salesapp.noncar.dto.CarApplicationSearchResponseDTO;
import com.ccic.salesapp.noncar.dto.request.AbstractFacade;
import com.ccic.salesapp.noncar.dto.request.CarApplicationSearchRequest;
import com.ccic.salesapp.noncar.dto.request.ICarApplicationSearchRequestVO;
import com.ccic.salesapp.noncar.dto.request.ICarApplicationVO;
import com.ccic.salesapp.noncar.dto.response.CarApplicationSearchResponse;
import com.ccic.salesapp.noncar.dto.response.ICarApplicationSearchResponseVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;
import com.ccic.salesapp.noncar.service.CarApplicationSearchService;
import com.ccic.salesapp.noncar.service.CarApplicationService;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.thoughtworks.xstream.XStream;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

/**
 * 获取未支付投保单列表  
 * @author 孟秀萍
 *
 */
@Slf4j
@Service
public class CarApplicationServiceImpl  implements CarApplicationService {
	
	//@Autowired
	CarApplicationSearchService service;
	
	public RequestHeadDTO getConsumerID(){
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		return requestHead;
	}
	
	
	
	@Override
	public JsonVO getCarApplication(ICarApplicationSearchRequestVO carApplicationSearchRequestVO,PageVO pageVO) {
				RequestHeadDTO requestHead = this.getConsumerID();
				//CarApplicationSearchService service = (CarApplicationSearchService) this.getBean("carApplication");
				CarApplicationSearchRequestDTO carApplicationSearchRequestDTO = new CarApplicationSearchRequestDTO();
				//page
				carApplicationSearchRequestDTO.setCurrentPage(pageVO.getPage());
				carApplicationSearchRequestDTO.setPageSize(pageVO.getSize());
				
				carApplicationSearchRequestDTO.setApplicationNo(carApplicationSearchRequestVO.getApplicationNo());  //投保单号
				carApplicationSearchRequestDTO.setInsuredName(carApplicationSearchRequestVO.getInsuredName());//投保人姓名
				carApplicationSearchRequestDTO.setStaffCode(carApplicationSearchRequestVO.getStaffCode());//8000143178  5001020205
				//上下限日期		
				//carApplicationSearchRequestDTO.setMaxRespEndDate(mCalendar);
				//carApplicationSearchRequestDTO.setMinRespEndDate(nCalendar);
						
				CarApplicationSearchRequest carApplicationSearchRequest = new CarApplicationSearchRequest();
				carApplicationSearchRequest.setRequestHead(requestHead);
				carApplicationSearchRequest.setRequestBody(carApplicationSearchRequestDTO);
				XStream xs=new XStream();
				log.info("请求信息："+xs.toXML(carApplicationSearchRequest));
				CarApplicationSearchResponse response = service.searchCarApplication(carApplicationSearchRequest);
				int status = response.getResponseHead().getStatus();
				String appMessage = response.getResponseHead().getAppMessage();
				String esbMessage = response.getResponseHead().getEsbMessage();
				
				JsonVO jsonVO=new JsonVO();		
				ResultVO result=new ResultVO();
				PageVO page = new PageVO();
				log.info("返回状态："+status);
				if(status==0){//成功
					log.info("ESB返回信息："+esbMessage);
					log.info("ESB返回信息："+appMessage);
					CarApplicationSearchResponseDTO carApplicationSearchResponseDTO = response.getResponseBody();
					log.info("carApplicationSearchResponseDTO"+xs.toXML(carApplicationSearchResponseDTO));
					ICarApplicationSearchResponseVO iCarApplicationSearchResponseVO	= new ICarApplicationSearchResponseVO();
					List<ICarApplicationVO> carApplications = getCarApplications(carApplicationSearchResponseDTO.getApplications());
					iCarApplicationSearchResponseVO.setCarApplications(carApplications);
					
					jsonVO.setData(iCarApplicationSearchResponseVO);
					page.setPage(carApplicationSearchResponseDTO.getCurrentPage());
					page.setSize(carApplicationSearchResponseDTO.getPageSize());
					page.setPages(carApplicationSearchResponseDTO.getTotalPage());
					page.setRows(carApplicationSearchResponseDTO.getTotalSize());
					jsonVO.setPage(page);
					result.setCode("1");
					result.setMessage("获取投保单信息成功");
					jsonVO.setResult(result);
				}else {
					log.info("返回app信息："+appMessage);
					log.info("返回esb信息："+esbMessage);
					
					result.setCode("0");
					result.setMessage(esbMessage);
					jsonVO.setResult(result);
				}
				log.info("投保单返回JSON"+xs.toXML(jsonVO));
		    
				return jsonVO;
			}

	public List<ICarApplicationVO> getCarApplications(List<CarApplicationDTO> applications){
		List<ICarApplicationVO> iCarApplicationList = new ArrayList<ICarApplicationVO>();
		for (int i = 0; i < applications.size(); i++) {
			ICarApplicationVO iCarApplicationVO = new ICarApplicationVO();
			CarApplicationDTO carApplicationDTO = applications.get(i);
			iCarApplicationVO.setApplicantName(carApplicationDTO.getApplicantName());
			iCarApplicationVO.setApplicationNo(carApplicationDTO.getApplicationNo());
			iCarApplicationVO.setInsuredCertNo(carApplicationDTO.getInsuredCertNo());
			iCarApplicationVO.setInsuredName(carApplicationDTO.getInsuredName());
			iCarApplicationVO.setPayNo(carApplicationDTO.getPayNo());
			iCarApplicationVO.setStatus(carApplicationDTO.getStatus());
			iCarApplicationVO.setSumAmount(carApplicationDTO.getSumAmount());
			iCarApplicationVO.setSumPremium(carApplicationDTO.getSumPremium());
			iCarApplicationVO.setRespEndDate(carApplicationDTO.getRespEndDate().toString());
			iCarApplicationVO.setRespStartDate(carApplicationDTO.getRespStartDate().toString());
			iCarApplicationList.add(iCarApplicationVO);
		}
		return iCarApplicationList;
	}
}
	
