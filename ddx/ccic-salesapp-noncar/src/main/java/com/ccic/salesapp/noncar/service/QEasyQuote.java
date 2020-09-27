package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.EInvoiceApplicationForAppDTO;
import com.ccic.salesapp.noncar.dto.ElectronicInvoicingDTO;
import com.ccic.salesapp.noncar.dto.ImgBatch;
import com.ccic.salesapp.noncar.feign.FileRequest;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequest;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryRequest;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.bean.EinvoiceInquiryRequestBodyDTO;
import com.ccic.salesapp.noncar.outService.esb.shorturl.bean.ShortUrlRequest;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.sinosoft.image.client.vo.ImgBatchUploadResponseVo;

/**
 * 非车险保单操作模块
 * 
 * @author wangpeng
 * 
 */
public interface QEasyQuote {

	// 调电子发票短链接查询接口
	public HttpResult toInvoiceLinkQuery(InvoiceLinkQueryRequestDTO request);

	// 超级APP电子发票开具申请接口
	public HttpResult electronicInvoicing(EInvoiceApplicationForAppDTO request);

	// 电子发票信息查询接口
	public HttpResult einvoiceInquiry(ElectronicInvoicingDTO request);

	// 生成短链地址
	public HttpResult shorturl(ShortUrlRequest request);

	// H5影像查询下载
	public HttpResult h5imgQueryDownService(ImgBatch request);

	// H5影像上传 调support的 FileController的uploadImage
	ImgBatchUploadResponseVo uploadImage(FileRequest fileRequest);

	public void addUploadImageMessage(ImgBatch imgBatch);


	
	

	

}
