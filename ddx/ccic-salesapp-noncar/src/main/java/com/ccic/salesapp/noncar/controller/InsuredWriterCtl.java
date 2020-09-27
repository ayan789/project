package com.ccic.salesapp.noncar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.noncar.dto.EInvoiceApplicationForAppDTO;
import com.ccic.salesapp.noncar.dto.ElectronicInvoicingDTO;
import com.ccic.salesapp.noncar.dto.ImgBatch;
import com.ccic.salesapp.noncar.feign.FileRequest;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequest;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlRequest;
import com.ccic.salesapp.noncar.service.QEasyQuote;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.sinosoft.image.client.vo.ImgBatchUploadResponseVo;
import com.sinosoft.image.client.vo.ImgMetaDataVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "保单操作模块", description = "保单操作模块", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "carCode")
public class InsuredWriterCtl {

	@Autowired
	QEasyQuote qEasyQuote;

//	 @PostMapping(value = "getPaymentCode")
//	    @ApiOperation(value = "非车险下单即转投保单", notes = "非车险下单即转投保单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult getPaymentCode(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }
//	    
//	    @PostMapping(value = "getPaymentCode1")
//	    @ApiOperation(value = "投保单列表查询", notes = "投保单列表查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult getCarApplition(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }
//	    
//	    @PostMapping(value = "orderListQuery")
//	    @ApiOperation(value = "订单查询", notes = "订单查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult orderListQuery(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }
//	    
//	    @PostMapping(value = "orderDetailsInquery")
//	    @ApiOperation(value = "订单详情", notes = "订单详情", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult orderDetailsInquery(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }
//	    
//	    @PostMapping(value = "operateOrder")
//	    @ApiOperation(value = "删除订单", notes = "删除订单", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult operateOrder(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }
//	    
//	    @PostMapping(value = "forwardPayInsureInfo")
//	    @ApiOperation(value = "订单信息转发投保信息", notes = "订单信息转发投保信息", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	    public HttpResult forwardPayInsureInfo(@RequestBody IToProposalRequestVO request) throws Exception {
//	   
//	    	
//	    	return HttpResult.success(qEasyQuote.toProposal(request));
//	    }

	@PostMapping(value = "toInvoiceLinkQuery")
	@ApiOperation(value = "电子发票", notes = "电子发票", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult toInvoiceLinkQuery(@RequestBody InvoiceLinkQueryRequestDTO request) throws Exception {
		return qEasyQuote.toInvoiceLinkQuery(request);
	}

	@PostMapping(value = "electronicInvoicing")
	@ApiOperation(value = "电子发票开具申请接口", notes = "电子发票开具申请接口", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult electronicInvoicing1(@RequestBody EInvoiceApplicationForAppDTO request) throws Exception {
		return qEasyQuote.electronicInvoicing(request);
	}

	@PostMapping(value = "einvoiceInquiry")
	@ApiOperation(value = "电子发票信息查询", notes = "电子发票信息查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult einvoiceInquiry(@RequestBody ElectronicInvoicingDTO request) throws Exception {
		return qEasyQuote.einvoiceInquiry(request);
	}

	@PostMapping(value = "shorturl")
	@ApiOperation(value = "生成短链地址", notes = "生成短链地址", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult shorturl(@RequestBody ShortUrlRequest request) throws Exception {
		return qEasyQuote.shorturl(request);
	}

	@PostMapping(value = "h5imgQueryDownService")
	@ApiOperation(value = "H5影像查询下载", notes = "H5影像查询下载", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult h5imgQueryDownService(@RequestBody ImgBatch request) throws Exception {
		return qEasyQuote.h5imgQueryDownService(request);
	}

	@PostMapping(value = "uploadImage")
	@ApiOperation(value = "H5影像上传", notes = "影像上传", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult uploadImage(@RequestBody FileRequest fileRequest) {
		ImgBatchUploadResponseVo resp = qEasyQuote.uploadImage(fileRequest);
		List<ImgMetaDataVo> successDatas = resp.getSuccessDatas();
		ImgMetaDataVo imgMetaDataVo = new ImgMetaDataVo();
		for (int i = 0; i < successDatas.size(); i++) {
			imgMetaDataVo = successDatas.get(i);
		}
		ImgBatch imgBatch = new ImgBatch();
		imgBatch.setAppCode(imgMetaDataVo.getImgType());
		imgBatch.setPageIds(imgMetaDataVo.getImgId().toString());
		imgBatch.setInsurNo(fileRequest.getBussNo());
		qEasyQuote.addUploadImageMessage(imgBatch);
		return HttpResult.success(resp);
	}

}
