package com.ccic.salesapp.noncar.service.impl;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.EInvoiceApplicationForAppDTO;
import com.ccic.salesapp.noncar.dto.ElectronicInvoicingDTO;
import com.ccic.salesapp.noncar.dto.ImgBatch;
import com.ccic.salesapp.noncar.dto.ImgBatchUploadResponseVo;
import com.ccic.salesapp.noncar.feign.FileRequest;
import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.EInvoiceApplicationForAppRequest;
import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.EInvoiceApplicationForAppRequestBodyDTO;
import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.EInvoiceApplicationForAppResponse;
import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.EInvoiceApplicationForAppResponseBodyDTO;
import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.intf.EinvoiceApplicationForApp;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLink;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequest;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryResponse;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.intf.InvoiceLinkQueryPort;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryRequest;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryRequestBodyDTO;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryResponse;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.SuccessDetailDto;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.intf.EinvoiceInquiryServicePort;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.BaseDataDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownRequest;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownResponse;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.ImageNodeDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.MetaDataDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.RequestHeadDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.intf.H5ImgQueryDownPort;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlRequest;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlResponse;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.shorturl.intf.ShortUrlPort;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.QEasyQuote;
import com.ccic.salesapp.noncar.utils.FileUtil;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.google.gson.Gson;
import com.sinosoft.image.client.ImgManagerClient;
import com.sinosoft.image.client.vo.ImgBatchUploadRequestVo;
import com.sinosoft.image.client.vo.ImgBussVo;
import com.sinosoft.image.client.vo.ImgMetaDataVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
/**
 * 非车险保单操作模块
 * 
 * @author wangpeng
 * 
 */

@Slf4j
@Service
public class QEasyQuoteimpl extends AbstractFacade implements QEasyQuote {

	//@Autowired
	EinvoiceApplicationForApp einvoiceApplicationForApp;

	@Autowired
	InvoiceLinkQueryPort invoiceLinkQueryPort;

	@Autowired
	EinvoiceInquiryServicePort einvoiceInquiryServicePort;

	@Autowired
	ShortUrlPort shortUrlPort;

	@Autowired
	H5ImgQueryDownPort h5ImgQueryDownPort;

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

//	@Autowired
//	UploadImageFeignService uploadImageFeignService;
	
	@Value(value = "${esb.file.uploadUrl}")
    private String url;
	
	@Value("${esb.netType.electronicPolicyImpl}")
	String electronicPolicyImpl ;

	public QEasyQuoteimpl() {

	}
	
	
	public static RequestHeadDTO getRequestHeadDTO() {
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		return requestHead;
	}
	
	/**
	 * 调电子发票短链接查询接口
	 * @return
	 */
	@Override
	public HttpResult toInvoiceLinkQuery(InvoiceLinkQueryRequestDTO request) {
		InvoiceLinkQueryResponse res = null;
		com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.RequestHeadDTO requestHead = new com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.RequestHeadDTO();
		requestHead.setConsumerID("custAPP");
		InvoiceLinkQueryRequest req = new InvoiceLinkQueryRequest();
		req.setRequestHead(requestHead);
		List<InvoiceLinkQueryRequest> InvoiceLinkQueryRequestlist = storeInsureInfoMapper
				.findonePolicyno(request.getPolicyNo());
		if (InvoiceLinkQueryRequestlist.size() == 0 || "".equals(request.getPolicyNo())) {
			return HttpResult.error(0, "保单号为空！");
		}
		InvoiceLinkQueryRequestDTO invoiceLinkQueryRequestDTO = new InvoiceLinkQueryRequestDTO();
		invoiceLinkQueryRequestDTO.setPolicyNo(request.getPolicyNo());
		req.setRequestBody(invoiceLinkQueryRequestDTO);
		try {
			res = invoiceLinkQueryPort.invoiceLinkQuery(req);
			int status = res.getResponseHead().getStatus();
			if (status == 0)// 成功
			{
				InvoiceLink invoiceLink = new InvoiceLink();
				List<InvoiceLink> invoiceLinkList = res.getResponseBody().getInvoiceLinkList();
				for (int i = 0; i < invoiceLinkList.size(); i++) {
					invoiceLink = invoiceLinkList.get(i);
				}
				if (invoiceLinkList.size() == 0) {
					return HttpResult.success("查询成功!无电子发票短链接！");
				}
				InvoiceLinkQueryResponseDTO invoiceLinkQueryResponseDTO = new InvoiceLinkQueryResponseDTO();
				invoiceLinkQueryResponseDTO.getInvoiceLinkList().add(invoiceLink);
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票短链接查询],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票短链接查询],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(res));
				return HttpResult.success(invoiceLinkQueryResponseDTO);
			}
		} catch (Exception e) {
			return HttpResult.error(0, "调电子发票短链接查询接口异常！");
		}
		return HttpResult.success(res);
	}

	/**
	 * 超级APP电子发票开具申请接口
	 * 
	 * @param jsonVO
	 * @return
	 * @throws ParseException
	 */
	@Override
	public HttpResult electronicInvoicing(EInvoiceApplicationForAppDTO request) {
		EInvoiceApplicationForAppResponse res = null;
		com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.RequestHeadDTO requestHead = new com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.bean.RequestHeadDTO();
		requestHead.setConsumerID("custAPP");
		EInvoiceApplicationForAppRequest req = new EInvoiceApplicationForAppRequest();
		req.setRequestHead(requestHead);
		EInvoiceApplicationForAppDTO eInvoiceApplicationForAppDTO = new EInvoiceApplicationForAppDTO();
		List<EInvoiceApplicationForAppDTO> EInvoiceApplicationForAppDTOlist = storeInsureInfoMapper
				.findelectronicInvoicingApp(request);
		for (int i = 0; i < EInvoiceApplicationForAppDTOlist.size(); i++) {
			eInvoiceApplicationForAppDTO = EInvoiceApplicationForAppDTOlist.get(i);
		}
		if (eInvoiceApplicationForAppDTO.getPolicyNo() == null
				|| "".equals(request.getPolicyNo()) && eInvoiceApplicationForAppDTO.getMobileNo() == null
				|| "".equals(request.getMobileNo())) {
			return HttpResult.error(0, "保单号或手机号为空！");
		}
		EInvoiceApplicationForAppRequestBodyDTO eInvoiceApplicationForAppRequestBodyDTO = new EInvoiceApplicationForAppRequestBodyDTO();
		eInvoiceApplicationForAppRequestBodyDTO.setBusinessNo(request.getPolicyNo());
		eInvoiceApplicationForAppRequestBodyDTO.setPhoneNo(request.getMobileNo());
		req.setRequestBody(eInvoiceApplicationForAppRequestBodyDTO);
		try {
			res = einvoiceApplicationForApp.einvoiceApplicationForApp(req);
			int stat = res.getResponseHead().getStatus();
			if (stat == 0) {// 成功
				String successTime = res.getResponseBody().getSuccessTime();
				EInvoiceApplicationForAppResponseBodyDTO eInvoiceApplicationForAppResponseBodyDTO = new EInvoiceApplicationForAppResponseBodyDTO();
				eInvoiceApplicationForAppResponseBodyDTO.setSuccessTime(successTime);
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票开具],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票开具],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(eInvoiceApplicationForAppResponseBodyDTO));
				return HttpResult.success(res);
			}
		} catch (Exception e) {
			return HttpResult.error(0, "调用电子发票开具申请接口异常！");
		}
		return HttpResult.success(res);
	}

	/**
	 * 电子发票信息查询接口 收付费提供电子发票信息接口，供网销系统调用
	 * @return
	 */
	@Override
	public HttpResult einvoiceInquiry(ElectronicInvoicingDTO request) {
		EinvoiceInquiryResponse res = null;
		com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.RequestHeadDTO requestHeadDTO = new com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.RequestHeadDTO();
		requestHeadDTO.setConsumerID("ebiz");
		EinvoiceInquiryRequest req = new EinvoiceInquiryRequest();
		req.setRequestHead(requestHeadDTO);
		ElectronicInvoicingDTO electronicInvoicingDTO = new ElectronicInvoicingDTO();
		List<ElectronicInvoicingDTO> ElectronicInvoicingDTOlist = storeInsureInfoMapper.findeinvoiceInquiry(request);
		for (int i = 0; i < ElectronicInvoicingDTOlist.size(); i++) {
			electronicInvoicingDTO = ElectronicInvoicingDTOlist.get(i);
		}
		if (electronicInvoicingDTO.getPolicyNo() == null || "".equals(request.getPolicyNo())) {
			return HttpResult.error(0, "保单号为空！");
		}
		if (request.getMobileNo() == null || "".equals(request.getMobileNo())) {
			return HttpResult.error(0, "手机号为空！");
		}
//		if (request.getEmail() == null || "".equals(request.getEmail())) {
//			return HttpResult.error(0, "邮箱为空！");
//		}
		EinvoiceInquiryRequestBodyDTO einvoiceInquiryRequestBodyDTO = new EinvoiceInquiryRequestBodyDTO();
		einvoiceInquiryRequestBodyDTO.setPhoneNumber(request.getMobileNo());
		einvoiceInquiryRequestBodyDTO.setPolicyNo(request.getPolicyNo());
//		einvoiceInquiryRequestBodyDTO.setEmail(request.getEmail());
		req.setRequestBody(einvoiceInquiryRequestBodyDTO);
		try {
			res = einvoiceInquiryServicePort.einvoiceInquiryService(req);
			int status = res.getResponseHead().getStatus();
			if (status == 0)// 成功
			{
				SuccessDetailDto successDetailDto = new SuccessDetailDto();
				List<SuccessDetailDto> item = res.getResponseBody().getItem();
				for (int i = 0; i < item.size(); i++) {
					successDetailDto = item.get(i);
				}
				EinvoiceInquiryResponseDTO einvoiceInquiryResponseDTO = new EinvoiceInquiryResponseDTO();
				einvoiceInquiryResponseDTO.getItem().add(successDetailDto);
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票信息查询],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:电子发票信息查询],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(res));
				return HttpResult.success(einvoiceInquiryResponseDTO);
			}
		} catch (Exception e) {
			return HttpResult.error(0, "电子发票信息查询接口异常！");
		}
		return HttpResult.success(res);
	}

	/**
	 * 生成短链地址 ebiz系统，生成短链地址
	 * 
	 * @return
	 */
	@Override
	public HttpResult shorturl(ShortUrlRequest request) {
		ShortUrlResponse res = null;
		com.ccic.salesapp.noncar.outService.esb.shorturl.bean.RequestHeadDTO requestHeadDTO = new com.ccic.salesapp.noncar.outService.esb.shorturl.bean.RequestHeadDTO();
		requestHeadDTO.setConsumerID("core");
		ShortUrlRequest req = new ShortUrlRequest();
		req.setRequestHead(requestHeadDTO);
		ShortUrlRequestDTO shortUrlRequestDTO = new ShortUrlRequestDTO();
		EinvoiceInquiryResponse einvoiceInquiryResponse = new EinvoiceInquiryResponse();
		EinvoiceInquiryResponseDTO einvoiceInquiryResponseDTO = new EinvoiceInquiryResponseDTO();
		req.setRequestBody(shortUrlRequestDTO);
		try {
			res = shortUrlPort.shortUrl(req);
			int stat = res.getResponseHead().getStatus();
			if (stat == 0) {
				String shortUrl = res.getResponseBody().getShortUrl();
				ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO();
				shortUrlResponseDTO.setShortUrl(shortUrl);
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:生成短链],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(req));
				log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:生成短链],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(shortUrlResponseDTO));
				return HttpResult.success(shortUrlResponseDTO);
			}
		} catch (Exception e) {
			return HttpResult.error(0, "生成短链地址异常");
		}
		return HttpResult.success(res);
	}

	

	/**
	 * H5影像查询下载
	 */
	@Override
	public HttpResult h5imgQueryDownService(ImgBatch request) {
		// 请求实体
		H5ImgQueryDownRequest h5ImgQueryDownRequest = new H5ImgQueryDownRequest();
		// 请求头
		RequestHeadDTO requestHeadDTO = QEasyQuoteimpl.getRequestHeadDTO();
		h5ImgQueryDownRequest.setRequestHead(requestHeadDTO);
		
//		ImgBatch imgBatch  = new ImgBatch();
//		List<ImgBatch> ImgBatchlist= storeInsureInfoMapper.findh5imgQueryDownServiceOnePolicy(request.getInsurNo());
//		if (ImgBatchlist.size() == 0 || "".equals(ImgBatchlist) ) {
//			return HttpResult.error(0, "保单为空！");
//		}
		MetaDataDTO metaDataDTO = new MetaDataDTO();
//		for (int i = 0; i < ImgBatchlist.size(); i++) {
//			imgBatch=ImgBatchlist.get(i);
			metaDataDTO.getPageIds().add(request.getPageIds());
			metaDataDTO.setAppCode("UW");
			metaDataDTO.setClassCode("UW_W");
			metaDataDTO.setBusinessNo(request.getInsurNo());
			metaDataDTO.setBusiDate("2020");
//		}
		H5ImgQueryDownRequestDTO h5ImgQueryDownRequestDTO = new H5ImgQueryDownRequestDTO();
		// BaseDataDTO (公共信息)
		BaseDataDTO baseDataDTO = new BaseDataDTO();
		// http.inner http.outer分别对应内网和外网
		baseDataDTO.setNetType(electronicPolicyImpl);
		// 机构号码
		baseDataDTO.setComCode("34011215");
		baseDataDTO.setOperator("8000068256");
		// 操作角色
		baseDataDTO.setOperatorRole("UWNVRole02");
		// MetaDataDTO (扩展信息)
		h5ImgQueryDownRequestDTO.setBaseData(baseDataDTO);
		h5ImgQueryDownRequestDTO.setMetaData(metaDataDTO);
		h5ImgQueryDownRequest.setRequestBody(h5ImgQueryDownRequestDTO);
		H5ImgQueryDownResponse h5ImgQueryDownResponse = new H5ImgQueryDownResponse();
		h5ImgQueryDownResponse = h5ImgQueryDownPort.h5ImgQueryDown(h5ImgQueryDownRequest);
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:文件下载],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(h5ImgQueryDownRequest));
		log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:文件下载],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(h5ImgQueryDownResponse));
		return HttpResult.success(h5ImgQueryDownResponse);
	}

	/**
	 * H5影像上传 调support的 FileController的uploadImage
	 */
//	public HttpResult uploadImage(FileRequest fileRequest) {
//		log.info("***********************************调接口开始进行上传*****************************************************");
//		HttpResult imgBatchUploadResponseVo = null;
//		try {
//			imgBatchUploadResponseVo = uploadImageFeignService.uploadImage(fileRequest);
//			log.info("[业务时间{}],[{}方法],[doImageQueryDownRequest:H5影像上传],[入參报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(fileRequest));
//			log.info("[业务时间{}],[{}方法],[doImageQueryDownRespons:H5影像上传],[响应报文：{}]", LocalDateTime.now(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSONObject.fromObject(imgBatchUploadResponseVo));
//			log.info("*********************************************调接口结束*****************************************************************");
//			Object data = imgBatchUploadResponseVo.getData();
//			Map<String, Object> map = JSONObject.fromObject(data);
//			Map<String, Object> m = new LinkedHashMap<String, Object>();
//			for (String key : map.keySet()) {
//				m.put(key, map.get(key));
//			}
//			Gson gson = new Gson();
//			com.ccic.salesapp.noncar.dto.ImgBatchUploadResponseVo[] imgBatchUploadResponse = {};
//			imgBatchUploadResponse = gson.fromJson(m.get("successDatas").toString(), imgBatchUploadResponse.getClass());
//			ImgBatch imgBatch = new ImgBatch();
//			for (ImgBatchUploadResponseVo imgBatchUploadResponseVo2 : imgBatchUploadResponse) {
//				imgBatch.setInsurNo(fileRequest.getBussNo());
//				imgBatch.setPageIds(imgBatchUploadResponseVo2.getImgId());
//				imgBatch.setAppCode(imgBatchUploadResponseVo2.getImgType());
//				storeInsureInfoMapper.insertResponeFileload(imgBatch);
//			}
//			return imgBatchUploadResponseVo;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return HttpResult.error(0,"上传失败");
//	}
	
	
	 public com.sinosoft.image.client.vo.ImgBatchUploadResponseVo uploadImage(FileRequest fileRequest) {
		 try {
//           String url="http://10.98.14.52:5212/h5img-wsc";
//           String url="http://fimage.ccic-test.com.cn:5212/h5img-wsc";
           ImgBatchUploadRequestVo batchUploadVo = new ImgBatchUploadRequestVo();
           // 机构号码
           batchUploadVo.setComCode(fileRequest.getComCode());
           // 操作员ID1
           batchUploadVo.setOperator(fileRequest.getCustNo());
           // 操作角色
           batchUploadVo.setOperatorRole("CJXSRole01");
           /** 业务信息 */
           ImgBussVo bussVo = new ImgBussVo();
           bussVo.setBussType("CJXS");
           bussVo.setRiskCode(fileRequest.getRiskCode());
           bussVo.setBussNo(fileRequest.getBussNo());
           bussVo.setBussDate(fileRequest.getBusiDate());
           bussVo.setBussCom(fileRequest.getComCode());
           batchUploadVo.setBussVo(bussVo);
           /** 影像上传时的影像描述信息 */
           List<ImgMetaDataVo> uploadDataVos = new ArrayList<>();
           for(FileRequest.FileVo fileVo : fileRequest.getFiles()){
               ImgMetaDataVo uploadDataVo = new ImgMetaDataVo();
               // 设置这个文件所属的单证类型 身份证 CJXS001 行驶证 CJXS002 其他 CJXS099
               uploadDataVo.setImgType(fileVo.getImgType());
               uploadDataVo.setImgTypeName(fileVo.getImgTypeName());
               uploadDataVo.setValidFlag(1);
               File file = FileUtil.decoderBase64File(fileVo.getFile(), fileVo.getFileName());
               uploadDataVo.setUploadFile(file);
               uploadDataVos.add(uploadDataVo);
           }
           batchUploadVo.setUploadDataVos(uploadDataVos);
           ImgManagerClient imgClient = new ImgManagerClient(url);
           com.sinosoft.image.client.vo.ImgBatchUploadResponseVo responseDataVo=imgClient.batchUpload(batchUploadVo);
           return responseDataVo;
       } catch (Exception e) {
       }
		return null;
   }



	public void addUploadImageMessage(ImgBatch imgBatch) {
		storeInsureInfoMapper.addUploadImageMessage(imgBatch);
	}
}