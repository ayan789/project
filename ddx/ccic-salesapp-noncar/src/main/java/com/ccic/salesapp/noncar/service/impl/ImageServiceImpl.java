package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.config.ConfigConst;
import com.ccic.salesapp.noncar.controller.StoreOrderCtl;
import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.Ct;
import com.ccic.salesapp.noncar.dto.ImgBusiDTO;
import com.ccic.salesapp.noncar.dto.ImgPrevUploadRequestDTO;
import com.ccic.salesapp.noncar.dto.InsuranceApplication;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.NoncarImgRiskVo;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.NoncarPlanCt;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.multiplexingHook.BaseDataDTO;
import com.ccic.salesapp.noncar.dto.multiplexingHook.ImageLinkRequestDTO;
import com.ccic.salesapp.noncar.dto.multiplexingHook.ReuseImagesDTO;
import com.ccic.salesapp.noncar.dto.multiplexingHook.SourceImageDTO;
import com.ccic.salesapp.noncar.dto.multiplexingHook.TargetImageDTO;
import com.ccic.salesapp.noncar.dto.request.ImageUploadRequestVO;
import com.ccic.salesapp.noncar.dto.request.ImgPrevUploadRequest;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.UploadSignBookReqVO;
import com.ccic.salesapp.noncar.dto.request.ZcOrderNoReqVO;
import com.ccic.salesapp.noncar.dto.response.ImageUploadResponseVO;
import com.ccic.salesapp.noncar.dto.response.JsonVO;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.dto.submitMiddleGround.UploadSignBookSubmissionVo;
import com.ccic.salesapp.noncar.feign.SupportFeignService;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ExtendInfo;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ExtendInfoDTO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ImgPrevUploadResponse;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.bean.ImgPrevUploadResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.imgprevupload.intf.ImgPrevUploadPort;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.service.ImageService;
import com.ccic.salesapp.noncar.utils.Data2Zh;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.request.LoginUser;
import com.ccic.salessapp.common.utils.PropertiesUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.UnirestUtil;
import com.itextpdf.html2pdf.jsoup.Jsoup;
import com.itextpdf.html2pdf.jsoup.select.Elements;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

//import com.ccic.salesapp.noncar.feign.MessageFeignController;

import com.sinosoft.image.client.ImgManagerClient;
import com.sinosoft.image.client.vo.ImgBatchUploadRequestVo;
import com.sinosoft.image.client.vo.ImgBatchUploadResponseVo;
import com.sinosoft.image.client.vo.ImgBussVo;
import com.sinosoft.image.client.vo.ImgMetaDataVo;
import com.thoughtworks.xstream.XStream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import kong.unirest.JsonNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

@Slf4j
@Service
public class ImageServiceImpl extends AbstractFacade implements ImageService {

	@Value("${system.sign_confirmationOfInsurance}")
	String sign_confirmationOfInsurance;
	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	@Value("${system.uploadUrl}")
	String uploadUrl;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	SupportFeignService supportFeignService;
	
	@Autowired
	StoreOrderCtl storeOrderCtl;

	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 生成投保确认书，上传确认书。 @throws IOException @throws
	 */
	@Override
	public Boolean uploadSignBook(UploadSignBookReqVO uploadSignBookReqVO, StoreInsureInfo storeInsureInfo)
			throws Exception {
		// SystemConfig cofig=new SystemConfig("system");
		// String sign_confirmationOfInsurance =
		// cofig.getResourceValue("sign_confirmationOfInsurance");
		String imgesfile = sign_confirmationOfInsurance + "/" + storeInsureInfo.getInsureNo() + "/"
				+ storeInsureInfo.getInsureNo() + ".jpg";
		System.out.println(imgesfile);
		// HttpResult result=HttpResult.success();
		/*
		 * JsonVO jsonVO = new JsonVO(); ResultVO result = new ResultVO();
		 */

		File dir = new File(sign_confirmationOfInsurance + "/" + storeInsureInfo.getInsureNo());
		if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
			dir.mkdirs();
		}

		// 先写入图片

		boolean generateImage = generateImage(uploadSignBookReqVO.getSignBytes(), imgesfile);
		if (generateImage == false) {
			/*
			 * result.setCode("0"); result.setMessage("上传签名失败!"); jsonVO.setResult(result);
			 */

			return false;
		}
		long parseLong = Long.parseLong(storeInsureInfo.getId());
		List<AccidentalInjuryInfo> insuredsFamilys= storeInsureInfoMapper.findInsuredsById(parseLong);
		String appliIdentifyType = storeInsureInfo.getAppliIdentifyType();
		String insuredIdentifyType = storeInsureInfo.getInsuredIdentifyType();
		appliIdentifyType = identifyType(appliIdentifyType);
		insuredIdentifyType = identifyType(insuredIdentifyType);
		String startDate = new SimpleDateFormat("yyyy年MM月dd日").format(storeInsureInfo.getStartDate())
				+ storeInsureInfo.getStartTime() + "时";
		String endDate = new SimpleDateFormat("yyyy年MM月dd日").format(storeInsureInfo.getEndDate())
				+ storeInsureInfo.getEndTime() + "时";
		String date = "自" + startDate + "起至" + endDate + "止";
		String createDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		Data2Zh data2Zh = new Data2Zh();
		String sumPremium = data2Zh.capitalization(storeInsureInfo.getSumPremium().split("\\.")[0]);
		Map<String, String> map = new HashMap<String, String>();
		map.put("INSURE_NO", storeInsureInfo.getInsureNo());
		map.put("APPLICANT_NAME", storeInsureInfo.getApplicantName());
		map.put("APPLIIDENTIFY_TYPE", appliIdentifyType);
		map.put("INSUREDIDENTIFY_TYPE", insuredIdentifyType);
		map.put("APPLI_IDENTIFY", storeInsureInfo.getAppliIdentify());
		map.put("INSURED_IDENTIFY", storeInsureInfo.getInsuredIdentify());
		map.put("SUMPREMIUM", sumPremium + "元整（￥"
				+ StringUtils.format2MoneyStr(Double.parseDouble(storeInsureInfo.getSumPremium())) + "元）");
		map.put("START_DATE", date);
		map.put("CREATE_DATE", createDate);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("signImage", imgesfile);
		List<List<String>> listInsureds = new ArrayList<List<String>>();
		if(insuredsFamilys.size() ==0) {
			map.put("INSURED_NAME",storeInsureInfo.getInsuredName());
			map.put("INSUREDIDENTIFY_TYPE",identifyType(storeInsureInfo.getInsuredIdentifyType()));
			map.put("INSURED_IDENTIFY", storeInsureInfo.getInsuredIdentify());
		}else {
	     for (int i = 0; i < insuredsFamilys.size(); i++) {
	        	AccidentalInjuryInfo insured = insuredsFamilys.get(i);
	        	List<String> listInsured = new ArrayList<String>();
	        	int j = i+1;
	        	listInsured.add("被保险人"+j+"名称："+insured.getFamilyName());
	        	listInsured.add("证件类型："+identifyType(insured.getFamilyIdentifyType()));
	        	listInsured.add("证件号："+insured.getFamilyIdentifyNo());
	        	listInsureds.add(listInsured);
	      }
		}
	    Map<String, List<List<String>>> listInsuredMap = new HashMap<String, List<List<String>>>();
	    listInsuredMap.put("insuredList", listInsureds);
		Map<String, Object> o = new HashMap();
		List<List<String>> List = new ArrayList<List<String>>();
		
		if (storeInsureInfo.getInsuranceCode().equals("WTH") && storeInsureInfo.getProductCode().equals("XB2019_F")) {
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");
			
			List<String> list2 = new ArrayList<String>();
			list2.add("大地通达公共交通工具意外伤害保险条款");
			list2.add("公共交通意外伤害-轮船");
			list2.add("10万");
			List<String> list3 = new ArrayList<String>();
			list3.add("大地通达公共交通工具意外伤害保险条款");
			list3.add("公共交通意外伤害-飞机");
			list3.add("100万");
			List<String> list4 = new ArrayList<String>();
			list4.add("大地通达公共交通工具意外伤害保险条款");
			list4.add("公共交通意外伤害-汽车");
			list4.add("10万");
			List<String> list5 = new ArrayList<String>();
			list5.add("大地通达公共交通工具意外伤害保险条款");
			list5.add("公共交通意外伤害-火车（地铁、轻轨）");
			list5.add("10万");
			List<String> list6 = new ArrayList<String>();
			list6.add("个人医疗保险条款（B款）");
			list6.add("一般医疗");
			list6.add("300万");
			List<String> list7 = new ArrayList<String>();
			list7.add("个人医疗保险条款（B款）");
			list7.add("恶性肿瘤或重大手术疾病医疗");
			list7.add("300万");
			
			
			List<String> list8= new ArrayList<String>();
			list8.add("附加特定疾病特需医疗扩展保险条款");
			list8.add("特定疾病特需医疗");
			list8.add("300万");
			List<String> list9 = new ArrayList<String>();
			list9.add("附加扩展质子重离子医疗保险条款");
			list9.add("质子重离子医疗");
			list9.add("100万");

			
			List.add(list1);
			List.add(list2);
			List.add(list3);
			List.add(list4);
			List.add(list5);
			List.add(list6);
			List.add(list7);
			if (org.apache.commons.lang.StringUtils.isNotBlank(storeInsureInfo.getAdditionalCode())) {
				String[] split = storeInsureInfo.getAdditionalCode().split("_");
				for (String str : split) {
					if (str.equals("aKZEXClause")) {
						List.add(list8);
					}
					if (str.equals("aEXTZClause")) {
						List.add(list9);
					}
				}
			}
		}
		
		
		if (storeInsureInfo.getInsuranceCode().equals("WTN") && storeInsureInfo.getProductCode().equals("XB2020_P")) {
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");
			
			List<String> list2 = new ArrayList<String>();
			list2.add("大地通达公共交通工具意外伤害保险条款");
			list2.add("公共交通意外伤害-轮船");
			list2.add("10万");
			List<String> list3 = new ArrayList<String>();
			list3.add("大地通达公共交通工具意外伤害保险条款");
			list3.add("公共交通意外伤害-飞机");
			list3.add("100万");
			List<String> list4 = new ArrayList<String>();
			list4.add("大地通达公共交通工具意外伤害保险条款");
			list4.add("公共交通意外伤害-汽车");
			list4.add("10万");
			List<String> list5 = new ArrayList<String>();
			list5.add("大地通达公共交通工具意外伤害保险条款");
			list5.add("公共交通意外伤害-火车（地铁、轻轨）");
			list5.add("10万");
			
			List<String> list6 = new ArrayList<String>();
			list6.add("中国大地保险个人医疗保险条款（C款）");
			list6.add("重大疾病医疗保险责任");
			list6.add("300万");
			List<String> list7 = new ArrayList<String>();
			list7.add("中国大地保险个人医疗保险条款（C款）");
			list7.add("一般医疗保险责任");
			list7.add("300万");
			
			List<String> list8 = new ArrayList<String>();
			list8.add("中国大地保险附加个人恶性肿瘤赴日医疗保险条款");
			list8.add("恶性肿瘤赴日医疗责任");
			list8.add("200万");
			List<String> list9 = new ArrayList<String>();
			list9.add("中国大地保险附加个人恶性肿瘤院外特种药品费用医疗保险条款");
			list9.add("恶性肿瘤院外特种药品费用医疗责任");
			list9.add("100万");
			List<String> list10 = new ArrayList<String>();
			list10.add("中国大地保险个人医疗保险（C款）附加扩展恶性肿瘤特需医疗保险条款");
			list10.add("扩展恶性肿瘤特需医疗责任");
			list10.add("300万");
			List<String> list11 = new ArrayList<String>();
			list11.add("中国大地保险附加个人恶性肿瘤扩展质子重离子医疗保险条款");
			list11.add("恶性肿瘤扩展质子重离子医疗责任");
			list11.add("100万");
			
			List.add(list1);
			List.add(list2);
			List.add(list3);
			List.add(list4);
			List.add(list5);
			List.add(list6);
			List.add(list7);
			if (org.apache.commons.lang.StringUtils.isNotBlank(storeInsureInfo.getAdditionalCode())) {
				String[] split = storeInsureInfo.getAdditionalCode().split("_");
				for (String str : split) {
					if (str.equals("aKZEXClause")) {
						List.add(list8);
					}
					if (str.equals("aEXTZClause")) {
						List.add(list9);
					}
					if (str.equals("aEXYLClause")) {
						List.add(list10);
					}
					if (str.equals("aEXZZClause")) {
						List.add(list11);
					}
				}
			}
		}
		
		if (storeInsureInfo.getInsuranceCode().equals("WTN") && storeInsureInfo.getProductCode().equals("XB2020_F")) {
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");
			
			List<String> list2 = new ArrayList<String>();
			list2.add("大地通达公共交通工具意外伤害保险条款");
			list2.add("公共交通意外伤害-轮船");
			list2.add("10万");
			List<String> list3 = new ArrayList<String>();
			list3.add("大地通达公共交通工具意外伤害保险条款");
			list3.add("公共交通意外伤害-飞机");
			list3.add("100万");
			List<String> list4 = new ArrayList<String>();
			list4.add("大地通达公共交通工具意外伤害保险条款");
			list4.add("公共交通意外伤害-汽车");
			list4.add("10万");
			List<String> list5 = new ArrayList<String>();
			list5.add("大地通达公共交通工具意外伤害保险条款");
			list5.add("公共交通意外伤害-火车（地铁、轻轨）");
			list5.add("10万");
			
			List<String> list6 = new ArrayList<String>();
			list6.add("中国大地保险个人医疗保险条款（C款）");
			list6.add("一般医疗保险责任");
			list6.add("300万");
			List<String> list7 = new ArrayList<String>();
			list7.add("中国大地保险个人医疗保险条款（C款）");
			list7.add("重大疾病医疗保险责任");
			list7.add("300万");
			
			List<String> list8 = new ArrayList<String>();
			list8.add("中国大地保险附加个人恶性肿瘤赴日医疗保险条款");
			list8.add("恶性肿瘤赴日医疗责任");
			list8.add("200万");
			List<String> list9 = new ArrayList<String>();
			list9.add("中国大地保险附加个人恶性肿瘤扩展质子重离子医疗保险条款");
			list9.add("恶性肿瘤扩展质子重离子医疗责任");
			list9.add("100万");
			List<String> list10 = new ArrayList<String>();
			list10.add("中国大地保险附加个人恶性肿瘤院外特种药品费用医疗保险条款");
			list10.add("恶性肿瘤院外特种药品费用医疗责任");
			list10.add("100万");
			List<String> list11 = new ArrayList<String>();
			list11.add("中国大地保险个人医疗保险（C款）附加扩展恶性肿瘤特需医疗保险条款");
			list11.add("扩展恶性肿瘤特需医疗责任");
			list11.add("300万");
			
			List.add(list1);
			List.add(list2);
			List.add(list3);
			List.add(list4);
			List.add(list5);
			List.add(list6);
			List.add(list7);
			if (org.apache.commons.lang.StringUtils.isNotBlank(storeInsureInfo.getAdditionalCode())) {
				String[] split = storeInsureInfo.getAdditionalCode().split("_");
				for (String str : split) {
					if (str.equals("aKZEXClause")) {
						List.add(list8);
					}
					if (str.equals("aEXTZClause")) {
						List.add(list9);
					}
					if (str.equals("aEXYLClause")) {
						List.add(list10);
					}
					if (str.equals("aEXZZClause")) {
						List.add(list11);
					}
				}
			}
		}
		
		
		if (storeInsureInfo.getInsuranceCode().equals("WTN") && storeInsureInfo.getProductCode().equals("FM51")) {
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");

			List<String> list2 = new ArrayList<String>();
			list2.add("中国大地保险个人医疗保险条款（C款）");
			list2.add("一般医疗");
			list2.add("300万");
			List<String> list3 = new ArrayList<String>();
			list3.add("中国大地保险个人医疗保险条款（C款）");
			list3.add("百万医疗");
			list3.add("100万");
			List<String> list4 = new ArrayList<String>();
			list4.add("中国大地保险个人医疗保险条款（C款）");
			list4.add("重大疾病医疗");
			list4.add("300万");
			List<String> list5 = new ArrayList<String>();
			list5.add("大地通达公共交通工具意外伤害保险条款");
			list5.add("公共交通意外伤害-飞机");
			list5.add("100万");
			List<String> list6 = new ArrayList<String>();
			list6.add("大地通达公共交通工具意外伤害保险条款");
			list6.add("公共交通意外伤害-轮船");
			list6.add("10万");
			List<String> list7 = new ArrayList<String>();
			list7.add("大地通达公共交通工具意外伤害保险条款");
			list7.add("公共交通意外伤害-火车（地铁、轻轨）");
			list7.add("10万");
			List<String> list8 = new ArrayList<String>();
			list8.add("大地通达公共交通工具意外伤害保险条款");
			list8.add("公共交通意外伤害-汽车");
			list8.add("10万");

			List<String> list9 = new ArrayList<String>();
			list9.add("附加扩展恶性肿瘤特需医疗保险条款");
			list9.add("扩展恶性肿瘤特需医疗");
			list9.add("与“重大疾病医疗保险责任”共用保额");
			List<String> list10 = new ArrayList<String>();
			list10.add("附加个人恶性肿瘤院外特种药品费用医疗保险条款");
			list10.add("恶性肿瘤院外特种药品费用医疗");
			list10.add("100万");
			List<String> list11 = new ArrayList<String>();
			list11.add("附加个人恶性肿瘤赴日医疗保险条款");
			list11.add("恶性肿瘤赴日医疗");
			list11.add("200万");
			List<String> list12 = new ArrayList<String>();
			list12.add("附加个人恶性肿瘤扩展质子重离子医疗保险条款");
			list12.add("恶性肿瘤扩展质子重离子医疗");
			list12.add("100万");

			/* List<List<String>> List = new ArrayList<List<String>>(); */
			List.add(list1);
			List.add(list2);
			List.add(list3);
			List.add(list4);
			List.add(list5);
			List.add(list6);
			List.add(list7);
			if (org.apache.commons.lang.StringUtils.isNotBlank(storeInsureInfo.getAdditionalCode())) {
				String[] split = storeInsureInfo.getAdditionalCode().split("_");
				for (String str : split) {
					if (str.equals("aKZEXClause")) {
						List.add(list9);
					}
					if (str.equals("aEXTZClause")) {
						List.add(list10);
					}
					if (str.equals("aEXYLClause")) {
						List.add(list11);
					}
					if (str.equals("aEXZZClause")) {
						List.add(list12);
					}
				}
			}
		} else if (storeInsureInfo.getInsuranceCode().equals("WTN") && storeInsureInfo.getProductCode().equals("FM50")) {
			// 表格 一行数据是一个list1
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");

			List<String> list2 = new ArrayList<String>();
			list2.add("个人医疗保险条款（C款）");
			list2.add("一般医疗");
			list2.add("300万");
			List<String> list3 = new ArrayList<String>();
			list3.add("个人医疗保险条款（C款）");
			list3.add("重大疾病医疗");
			list3.add("300万");
			List<String> list4 = new ArrayList<String>();
			list4.add("大地通达公共交通工具意外伤害保险条款");
			list4.add("公共交通意外伤害-飞机");
			list4.add("100万");
			List<String> list5 = new ArrayList<String>();
			list5.add("大地通达公共交通工具意外伤害保险条款");
			list5.add("公共交通意外伤害-轮船");
			list5.add("10万");
			List<String> list6 = new ArrayList<String>();
			list6.add("大地通达公共交通工具意外伤害保险条款");
			list6.add("公共交通意外伤害-火车（地铁、轻轨）");
			list6.add("10万");
			List<String> list7 = new ArrayList<String>();
			list7.add("大地通达公共交通工具意外伤害保险条款");
			list7.add("公共交通意外伤害-汽车");
			list7.add("10万");
			// 附加赴日医疗保险
			List<String> list8 = new ArrayList<String>();
			list8.add("附加个人恶性肿瘤赴日医疗保险条款");
			list8.add("恶性肿瘤赴日医疗");
			list8.add("200万");
			// 附加院外特种药品费用
			List<String> list9 = new ArrayList<String>();
			list9.add("附加个人恶性肿瘤院外特种药品费用医疗保险条款");
			list9.add("恶性肿瘤院外特种药品费用医疗");
			list9.add("100万");
			// 附加恶性肿瘤特需医疗
			List<String> list10 = new ArrayList<String>();
			list10.add("附加扩展恶性肿瘤特需医疗保险条款");
			list10.add("扩展恶性肿瘤特需医疗");
			list10.add("与第2项共用保额");
			// 附加扩展质子重离子医疗
			List<String> list11 = new ArrayList<String>();
			list11.add("附加个人恶性肿瘤扩展质子重离子医疗保险条款");
			list11.add("恶性肿瘤扩展质子重离子医疗");
			list11.add("100万");

			List.add(list1);
			List.add(list2);
			List.add(list3);
			List.add(list4);
			List.add(list5);
			List.add(list6);
			List.add(list7);
			if (org.apache.commons.lang.StringUtils.isNotBlank(storeInsureInfo.getAdditionalCode())) {
				String[] split = storeInsureInfo.getAdditionalCode().split("_");
				for (String str : split) {
					if (str.equals("aEXYLClause")) {
						List.add(list8);
					}
					if (str.equals("aEXTZClause")) {
						List.add(list9);
					}
					if (str.equals("aKZEXClause")) {
						List.add(list10);
					}
					if (str.equals("aEXZZClause")) {
						List.add(list11);
					}
				}
			}
		} else if (storeInsureInfo.getInsuranceCode().equals("WTI")) {
			// 表格 一行数据是一个list1
			List<String> list1 = new ArrayList<String>();
			list1.add("条款名称");
			list1.add("保险责任");
			list1.add("每人保额（元）");

			List<String> list2 = new ArrayList<String>();
			list2.add("个人恶性肿瘤医疗保险条款(CF1100707)");
			list2.add("个人恶性肿瘤医疗费用");
			list2.add("200万");

			List.add(list1);
			List.add(list2);
		}
		Map<String, List<List<String>>> listMap = new HashMap<String, List<List<String>>>();
		listMap.put("eventList", List);

		o.put("datemap", map);
		o.put("imgmap", map2);
		o.put("list", listMap);
		o.put("listInsured", listInsuredMap);
		pdfout(o, storeInsureInfo);
		// 上传投保确认书去影像系统
		batchUpload(storeInsureInfo);

		// jsonVO.setResult(result);
		return true;
	}

	public static boolean generateImage(String imgStr, String imagesFile) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = imagesFile;// 新生成的图片
			out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			return true;
		} catch (Exception e) {
			// log.error(e);
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 转换证件类型为中文
	public static String identifyType(String identifyType) {
		if (identifyType.equals("01")) {
			identifyType = "身份证";
		} else if (identifyType.equals("03")) {
			identifyType = "护照";
		} else if (identifyType.equals("28")) {
			identifyType = "港澳台居民居住证";
		} else if (identifyType.equals("02")) {
			identifyType = "户口簿";
		} else if (identifyType.equals("113")) {
			identifyType = "户口簿";
		}
		return identifyType;
	};

	// 利用模板生成pdf
	public void pdfout(Map<String, Object> o, StoreInsureInfo storeInsureInfo) throws Exception {
		// SystemConfig cofig=new SystemConfig("system");

		// String sign_confirmationOfInsurance =
		// cofig.getResourceValue("sign_confirmationOfInsurance");
		// 模板路径
		// String templatePath = java.net.URLDecoder.decode("sign_s.pdf","utf-8");
		// System.out.println(templatePath);
		// Resource pdfresource = resourceLoader.getResource("classpath:"+"sign_s.pdf");
		Resource pdfresource = null;
		if (storeInsureInfo.getInsuranceCode().equals("WTN") && storeInsureInfo.getProductCode().equals("FM51")) {
			pdfresource = resourceLoader.getResource("classpath:" + "sign_family_upgrade.pdf");
		} else if (storeInsureInfo.getInsuranceCode().equals("WTN")
				&& storeInsureInfo.getProductCode().equals("FM50")) {
			pdfresource = resourceLoader.getResource("classpath:" + "sign_s2020.pdf");
		} else if (storeInsureInfo.getInsuranceCode().equals("WTI")
				&& storeInsureInfo.getProductCode().equals("WTI01")) {
			pdfresource = resourceLoader.getResource("classpath:" + "sign_cancer.pdf");
		} else if (storeInsureInfo.getInsuranceCode().equals("WTN")
				&& storeInsureInfo.getProductCode().equals("XB2020_P")) {
			pdfresource = resourceLoader.getResource("classpath:" + "sign_s2020_new.pdf");
		} else if (storeInsureInfo.getInsuranceCode().equals("WTH")
				&& storeInsureInfo.getProductCode().equals("XB2019_P")) {
			pdfresource = resourceLoader.getResource("classpath:" + "sign_s_new.pdf");
		} 
		
		
		// 封装数据
		ZcOrderNoReqVO  reqvo = new ZcOrderNoReqVO();
		reqvo.setOrderNo(storeInsureInfo.getOrderNo());
		// 调用暂存接口获取数据
		HttpResult<TNoncarInsureZcResVO> queryZc = (HttpResult<TNoncarInsureZcResVO>) storeOrderCtl.queryZc(reqvo);
		// 映射StoreOracleRequestVO
		StoreOracleRequestVO storeOracleRequestVO = queryZc.getData().getStoreOracleRequestVO();
		// 比较是否有新增被保人
		if ("1".equals(storeOracleRequestVO.getHasNewInsured())) {
			if (storeInsureInfo.getInsuranceCode().equals("WTH") && storeInsureInfo.getProductCode().equals("XB2019_F")) {
				pdfresource = resourceLoader.getResource("classpath:" + "sign_family_new.pdf");
			} else if (storeInsureInfo.getInsuranceCode().equals("WTN")	&& storeInsureInfo.getProductCode().equals("XB2020_F")) {
				pdfresource = resourceLoader.getResource("classpath:" + "sign_family_upgrade_new.pdf");
			}
		}else {
			if (storeInsureInfo.getInsuranceCode().equals("WTH") && storeInsureInfo.getProductCode().equals("XB2019_F")) {
				pdfresource = resourceLoader.getResource("classpath:" + "sign_family_new_wu.pdf");
			} else if (storeInsureInfo.getInsuranceCode().equals("WTN")	&& storeInsureInfo.getProductCode().equals("XB2020_F")) {
				pdfresource = resourceLoader.getResource("classpath:" + "sign_family_upgrade_new_new_wu.pdf");
			}
		}
		
		Resource simsun = resourceLoader.getResource("classpath:" + "simsun.ttc");
		InputStream inputStream = pdfresource.getInputStream();
		InputStream simsunInputStream = simsun.getInputStream();
		// 生成的新文件路径
		String newPDFPath = sign_confirmationOfInsurance + "/" + storeInsureInfo.getInsureNo() + "/"
				+ storeInsureInfo.getInsureNo() + ".pdf";
		System.out.println(newPDFPath);

		PdfReader reader = null;
		FileOutputStream out = null;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper = null;
		Document doc = null;
		try { // ↓↓↓↓↓这个是字体文件
			BaseFont bf = BaseFont.createFont("simsun.ttc" + ",1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(inputStream);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			form.addSubstitutionFont(bf);
			Map<String, String> datemap = (Map<String, String>) o.get("datemap");
			for (String key : datemap.keySet()) {
				String value = datemap.get(key);
				form.setField(key, value);
				System.out.printf("%s,%s\n", key, value);
			}
			// 图片类的内容处理
			Map<String, String> imgmap = (Map<String, String>) o.get("imgmap");
			for (String key : imgmap.keySet()) {
				String value = imgmap.get(key);
				String imgpath = value;
				int pageNo = form.getFieldPositions(key).get(0).page;
				Rectangle signRect = form.getFieldPositions(key).get(0).position;
				float x = signRect.getLeft();
				float y = signRect.getBottom();
				// 根据路径读取图片
				Image image = Image.getInstance(imgpath);
				// 获取图片页面
				PdfContentByte under = stamper.getOverContent(pageNo);
				// 图片大小自适应
				image.scaleToFit(signRect.getWidth(), signRect.getHeight());
				// 添加图片
				image.setAbsolutePosition(x, y);
				under.addImage(image);
			}
			// 表格类
			Map<String, List<List<String>>> listMap = (Map<String, List<List<String>>>) o.get("list");
			for (String key : listMap.keySet()) {
				List<List<String>> lists = listMap.get(key);
				int pageNo = form.getFieldPositions(key).get(0).page;
				PdfContentByte pcb = stamper.getOverContent(pageNo);
				Rectangle signRect = form.getFieldPositions(key).get(0).position;
				// 表格位置
				int column = lists.get(0).size();
				int row = lists.size();
				PdfPTable table = new PdfPTable(column);
				float tatalWidth = signRect.getRight() - signRect.getLeft() - 1;
				int size = lists.get(0).size();
				float width[] = new float[size];
				for (int i = 0; i < size; i++) {
					if (i == 0) {
						width[i] = 200f;
					} else if (i == 1) {
						width[i] = 190f;
					} else {
						width[i] = 100f;
					}
				}
				table.setTotalWidth(width);
				table.setLockedWidth(true);
				table.setKeepTogether(true);
				table.setSplitLate(false);
				table.setSplitRows(true);
				Font FontProve = new Font(bf, 10, 0);
				// 表格数据填写
				for (int i = 0; i < row; i++) {
					List<String> list = lists.get(i);
					for (int j = 0; j < column; j++) {
						Paragraph paragraph = new Paragraph(String.valueOf(list.get(j)), FontProve);
						PdfPCell cell = new PdfPCell(paragraph);
						cell.setBorderWidth(1);
						cell.setVerticalAlignment(Element.ALIGN_CENTER);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setLeading(0, (float) 1.4);
						table.addCell(cell);
					}
				}
				table.writeSelectedRows(0, -1, signRect.getLeft(), signRect.getTop(), pcb);
			}
			 // 表格类
            Map<String, List<List<String>>> listInsuredMap =  (Map<String, List<List<String>>>) o.get("listInsured");
            try {
				for (String key : listInsuredMap.keySet()) {
				    List<List<String>> lists = listInsuredMap.get(key);
				    int pageNo = form.getFieldPositions(key).get(0).page;
				    PdfContentByte pcb = stamper.getOverContent(pageNo);
				    Rectangle signRect = form.getFieldPositions(key).get(0).position;
				    //表格位置
				    int column = lists.get(0).size();
				    int row = lists.size();
				    PdfPTable table =  new PdfPTable(column);
				    float tatalWidth = signRect.getRight() - signRect.getLeft() - 1;
				    int size = lists.get(0).size();
				    float width[] = new float[size];
				    for(int i=0;i<size;i++){
				        if(i==0){
				            width[i]=152f;
				        }else if (i==1) {
				        	width[i]=155f;
						}else{
				            width[i]=183f;
				        }
				    }
				    table.setTotalWidth(width);
				    table.setLockedWidth(true);
				    table.setKeepTogether(true);
				    table.setSplitLate(false);
				    table.setSplitRows(true);
				    Font FontProve = new Font(bf, 12, 0);
				    //表格数据填写
				    for(int i=0;i<row;i++){
				        List<String> list = lists.get(i);
				        for(int j=0;j<column;j++){
				            Paragraph paragraph = new Paragraph(String.valueOf(list.get(j)), FontProve);
				            PdfPCell cell = new PdfPCell(paragraph);
				            cell.setBorderWidth(0);
				            cell.setMinimumHeight(20); // 设置单元格高度
				            cell.setVerticalAlignment(Element.ALIGN_CENTER); //垂直居中
				            cell.setLeading(0, (float) 1.4);
				            table.addCell(cell);
				        }
				    }
				    table.writeSelectedRows(0, -1, signRect.getLeft(), signRect.getTop(), pcb);
				}
			} catch (Exception e) {
			}
			stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
			stamper.close();
			doc = new Document();
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = null;
			/// 循环是处理成品只显示一页的问题
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
				copy.addPage(importPage);
			}
			doc.close();
			log.info("生成pdf文件完成~~~~~~~~~~ " + newPDFPath);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (stamper != null) {
				try {
					stamper.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (doc != null) {
				try {
					doc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 生成的pdf上传到影像系统
	public void batchUpload(StoreInsureInfo storeInsureInfo) throws Exception {
		// SystemConfig cofig = new SystemConfig("system");
		/*
		 * String uploadUrl = null; String downLoadUrl = null; uploadUrl =
		 * cofig.getResourceValue("uploadUrl"); downLoadUrl =
		 * cofig.getResourceValue("sign_confirmationOfInsurance");
		 */

		// 测试环境http://10.98.13.71:5212/h5img-wsc
		// 生产环境//http://10.98.68.65:5212/h5img-wsc
		ImgManagerClient client = new ImgManagerClient(uploadUrl);
		// 电子保单上传到影像系统
		ImgBatchUploadRequestVo batchUploadRequestVo = new ImgBatchUploadRequestVo();
		batchUploadRequestVo.setComCode("31000000");
		batchUploadRequestVo.setComName("大地保险");
		batchUploadRequestVo.setOperator(storeInsureInfo.getHandlerCode());
		batchUploadRequestVo.setOperatorRole("RSXRole002");// CLRole01

		/** 业务信息 **/
		ImgBussVo bussVo = new ImgBussVo();
		bussVo.setBussType("UW");
		bussVo.setRiskCode("UW_W");
		bussVo.setBussNo(storeInsureInfo.getInsureNo());
		Calendar calendar = Calendar.getInstance();
		if (storeInsureInfo.getInsureNo().length() == 24) {
			bussVo.setBussDate(String.valueOf(calendar.get(calendar.YEAR)).substring(0, 2)
					+ storeInsureInfo.getInsureNo().substring(4, 6) + "0000");// 批次所属年份 年份+0000
		} else {
			bussVo.setBussDate(storeInsureInfo.getInsureNo().substring(4, 8) + "0000");
		}
		bussVo.setBussCom(storeInsureInfo.getComCode()); // 13010000
		batchUploadRequestVo.setBussVo(bussVo);

		/** 需要删除的影像对象，只需要赋值imgId */
		if (StringUtils.isNotBlank(storeInsureInfo.getImgId())) {
			List<ImgMetaDataVo> deleteDataVos = new ArrayList<ImgMetaDataVo>();
			ImgMetaDataVo deleteVo01 = new ImgMetaDataVo();
			deleteVo01.setImgId((long) Integer.parseInt(storeInsureInfo.getImgId()));
			deleteDataVos.add(deleteVo01);
			batchUploadRequestVo.setDeleteDataVos(deleteDataVos);
		}

		/** 影像上传时的影像描述信息 */
		List<ImgMetaDataVo> uploadDataVos = new ArrayList<ImgMetaDataVo>();

		// 电子投保单
		File dir = new File(sign_confirmationOfInsurance + "/" + storeInsureInfo.getInsureNo() + "/"
				+ storeInsureInfo.getInsureNo() + ".pdf");
		if (dir.exists()) {
			ImgMetaDataVo uploadDataVo = new ImgMetaDataVo();
			uploadDataVo.setImgType("UWW001");
			uploadDataVo.setImgTypeName("投保单");
			uploadDataVo.setUploadNode("uploadNode");
			uploadDataVo.setValidFlag(1);
			uploadDataVo.setUploadFile(dir);
			uploadDataVos.add(uploadDataVo);
		}

		batchUploadRequestVo.setUploadDataVos(uploadDataVos);

		XStream xs = new XStream();
		log.info("新影像上传1　发送xml= " + xs.toXML(batchUploadRequestVo));

		ImgBatchUploadResponseVo response = client.batchUpload(batchUploadRequestVo);
		log.info("新影像上传2　返回xml= " + xs.toXML(response));

		if (response != null && response.getStatusCode() == 1 && response.getSuccessDatas().size() >= 1) {
			Long imgId = response.getSuccessDatas().get(0).getImgId();
			storeInsureInfo.setImgId(imgId + "");
			/*
			 * result.setCode("1"); result.setMessage("上传签名成功！");
			 */

			log.info("------新影像返回代码：" + response.getStatusCode() + " \t " + "返回信息：" + "新影像上传投保确认书成功！！！");
		} else {
			/*
			 * result.setCode("0"); result.setMessage("上传签名异常!");
			 */
			log.info("------新影像返回错误代码：" + response.getStatusCode() + "  " + "错误信息：" + response.getErrorMsg());
		}
	}

	@Override
	public JsonVO uploade(ImageUploadRequestVO req, InsuranceApplication application, LoginUser user) throws Exception {
		JsonVO jsonVO = new JsonVO();
		ResultVO result = new ResultVO();
		ImgPrevUploadRequestDTO requestDTO = new ImgPrevUploadRequestDTO();
		requestDTO.setNetType("http.outer");// 浏览器端网络类型
		requestDTO.setOperatorRole("UWDRole02");// 操作角色
		if (application.getStatus().equals(InsuranceApplicationStatus.SHENGXIAO)
				|| application.getStatus().equals(InsuranceApplicationStatus.YIZHIFU)) {
			requestDTO.setOperatorRole("UWDRole03");
		}
		requestDTO.setOperator(user.getUserCode());// 操作员ID
		requestDTO.setOperatorName(user.getUserName());// 操作员姓名
		String comCode = application.getComCode();// 机构代码
		String riskCode = application.getRiskCode();// 险种代码
		requestDTO.setComCode(comCode);// 机构号码
		ImgBusiDTO imgBusiDTO = new ImgBusiDTO();// 业务信息
		imgBusiDTO.setAppCode("UW");// 业务类型

		if ("LBU".equals(req.getProposalNo().substring(1, 4)) || "QPU".equals(req.getProposalNo().substring(1, 4))) {// 一揽子保险
			imgBusiDTO.setClassCode("UW_L");
			requestDTO.setOperatorRole("UWNVRole02");// 操作角色
		} else { // 车险
			imgBusiDTO.setClassCode("UW_D");
			requestDTO.setOperatorRole("UWDRole02");// 操作角色
			if (application.getStatus().equals(InsuranceApplicationStatus.SHENGXIAO)
					|| application.getStatus().equals(InsuranceApplicationStatus.YIZHIFU)) {
				requestDTO.setOperatorRole("UWDRole03");
			}
		}

		imgBusiDTO.setBusinessNo(req.getProposalNo());// 投保单号
		imgBusiDTO.setBusiComCode(comCode);// 批次所属机构
		if (req.getProposalNo().length() < 24) {
			String reDate = req.getProposalNo().substring(4, 8);
			imgBusiDTO.setBusiDate(reDate + "0000");// 批次年份
		} else {
			String reDate = datef.format(new Date());
			imgBusiDTO.setBusiDate(reDate.substring(0, 4) + "0000");
		}
		requestDTO.setImgBusiDto(imgBusiDTO);
		ImgPrevUploadRequest request = new ImgPrevUploadRequest();
		RequestHeadDTO requestHead = this.getConsumerID();
		requestHead.setSeqNo("");
		requestHead.setRiskCode(riskCode);// 险种代码
		request.setRequestHead(requestHead);
		request.setRequestBody(requestDTO);
		ImgPrevUploadPort service = (ImgPrevUploadPort) this.getBean("imageUpload");
		ImgPrevUploadResponse response = null;
		try {
			response = service.imgPrevUpload(request);
			if (response.getResponseHead().getStatus() != 0) {
				result.setCode("0");
				result.setMessage("调用影像上传地址申请接口，返回状态为失败。返回信息：" + response.getResponseHead().getAppMessage());
				jsonVO.setResult(result);
				return jsonVO;
			}
		} catch (Exception e) {
			// log.error("调用影像上传地址申请接口异常：", e);
			result.setCode("0");
			result.setMessage("调用影像上传地址申请接口异常：" + e.getMessage());
			jsonVO.setResult(result);
			return jsonVO;
		}
		ImgPrevUploadResponseDTO responseDTO = response.getResponseBody();
		if (responseDTO != null) {
			ImageUploadResponseVO resp = new ImageUploadResponseVO();
			resp.setUrl(responseDTO.getUrl());
			resp.setToken(responseDTO.getToken());
			if (responseDTO.getExtendFields().size() > 0) {
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
	 */
	private List<ExtendInfo> listUploadExtends(List<ExtendInfoDTO> extendFields) {
		List<ExtendInfo> list = new ArrayList<ExtendInfo>();
		for (ExtendInfoDTO extend : extendFields) {
			ExtendInfo extendInfo = new ExtendInfo();
			extendInfo.setKey(extend.getKey());
			extendInfo.setValue(extend.getValue());
			list.add(extendInfo);
		}
		return list;
	}

	/**
	 * 生成投保确认书，上传确认书。 @throws IOException @throws
	 */
	@Override
	public Boolean uploadSignBookTwo(UploadSignBookReqVO uploadSignBookReqVO, NoncarOrder noncarOrder)
			throws Exception {
		String imgesfile = sign_confirmationOfInsurance + "/" + noncarOrder.getInsureNo() + "/"
				+ noncarOrder.getInsureNo() + ".jpg";
		File dir = new File(sign_confirmationOfInsurance + "/" + noncarOrder.getInsureNo());
		if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
			dir.mkdirs();
		}
		File fontDir = new File(sign_confirmationOfInsurance + "/font");
		if (!fontDir.exists() && !fontDir.isDirectory()) {// 判断字体文件目录是否存在
			fontDir.mkdirs();
		}
		File font = new File(sign_confirmationOfInsurance + "/font/simsun.ttc");
		if (!font.exists()) {
			Resource simsun = resourceLoader.getResource("classpath:" + "simsun.ttc");
			InputStream in = simsun.getInputStream();
			FileOutputStream out = new FileOutputStream(font);
			IOUtils.copy(in, out);
		}
		// 先写入图片
		boolean generateImage = generateImage(uploadSignBookReqVO.getSignBytes(), imgesfile);
		if (generateImage == false) {
			return false;
		}
		// 投保人
		List<NoncarOrder> noncarOrderappli = storeInsureInfoMapper.findInsuredIdTypeapp(noncarOrder.getOrderId());
		// 切换被保人证件类型
		List<NoncarOrder> noncarOrderInsured = storeInsureInfoMapper.findInsuredIdType(noncarOrder.getOrderId());
		// 联络人
		List<NoncarOrder> noncarOrderInsuredrole = storeInsureInfoMapper
				.findInsuredIdTyperole(noncarOrder.getOrderId());
		// 受益人
		List<NoncarOrder> noncarOrderInsuredr = storeInsureInfoMapper.findInsuredIdTyper(noncarOrder.getOrderId());
		// 开始时间
		String startDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(noncarOrder.getStartDate());
		String endDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(noncarOrder.getEndDate());
		// 拼接转换日期
		String date = ConfigConst.FROM + startDate + "0" + ConfigConst.TIME + ConfigConst.TO_ZH + endDate + "24"
				+ ConfigConst.TIME + ConfigConst.ZHI;
		String createDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(new Date());
		Data2Zh data2Zh = new Data2Zh();
		// 获取总保费，转换为大写
		String sumPremium = data2Zh.capitalization(noncarOrder.getSumPremium().split("\\.")[0]);
		// 定集合存数据
		List<List<String>> List = new ArrayList<List<String>>();
		List<List<String>> ListSumArray = new ArrayList<List<String>>();
		// 条款责任
		Map map = new HashMap();
		map.put("orderId", noncarOrder.getOrderId());
		List<NoncarPlanCt> schemeCode = storeInsureInfoMapper.findBySchemeCode(map);
		for (NoncarPlanCt noncarPlanCtc : schemeCode) {
			String planCoverageName = noncarPlanCtc.getPlanCoverageName();
			String coverageName = noncarPlanCtc.getCoverageName();
			String insureamount = noncarPlanCtc.getInsured().stripTrailingZeros().toPlainString();
			Scanner scan = new Scanner(insureamount);
			long num = scan.nextLong();
			String valueOf = null;
			double n = (double) num / 10000;
			if (Math.round(n) - n == 0) {
				valueOf = String.valueOf((long) n + ConfigConst.MONEY_WA);
			} else {
				valueOf = String.valueOf(n + ConfigConst.MONEY_WA);
			}
			String arraySum = planCoverageName + "," + coverageName + "," + valueOf;
			String[] split = arraySum.split(",");
			for (String string : split) {
				List lists = new ArrayList();
				lists.add(string);
				ListSumArray.add(lists);
			}
		}
		Configuration configuration = new Configuration();
		Writer out = null;
		// step2 获取模版路径
		// String TEMPLATE_PATH = ConfigConst.URL_SRC;
		Resource temp = resourceLoader.getResource("classpath:" + "index.ftl");
		InputStream inputStream = temp.getInputStream();
		byte[] bytes1 = new byte[0];
		bytes1 = new byte[inputStream.available()];
		inputStream.read(bytes1);
		String tempContent = new String(bytes1);

		File iconimageDir = new File(sign_confirmationOfInsurance + "/iconimage");
		if (!iconimageDir.exists() && !iconimageDir.isDirectory()) {// 判断img文件目录是否存在
			iconimageDir.mkdirs();
		}
		File iconimagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_06.png");
		File icImagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_05.png");
		File iImagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_04.png");
		if (!iconimagefile.exists() && !icImagefile.exists() && !iImagefile.exists()) {
			Resource iconImage = resourceLoader.getResource("classpath:" + "img_06.png");
			Resource icImage = resourceLoader.getResource("classpath:" + "img_05.png");
			Resource iImage = resourceLoader.getResource("classpath:" + "img_04.png");
			InputStream inOne = iconImage.getInputStream();
			InputStream inTwo = icImage.getInputStream();
			InputStream inThree = iImage.getInputStream();
			FileOutputStream outOne = new FileOutputStream(iconimagefile);
			FileOutputStream outTwo = new FileOutputStream(icImagefile);
			FileOutputStream outThree = new FileOutputStream(iImagefile);
			IOUtils.copy(inOne, outOne);
			IOUtils.copy(inTwo, outTwo);
			IOUtils.copy(inThree, outThree);
		}
		String iconimageone = sign_confirmationOfInsurance + "/iconimage/img_06.png";
		String iconimagetwo = sign_confirmationOfInsurance + "/iconimage/img_05.png";
		String iconimagethree = sign_confirmationOfInsurance + "/iconimage/img_04.png";
		// step3 创建数据模型
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("INSURE_NO", noncarOrder.getInsureNo());
		dataMap.put("APPLICANTList", noncarOrderappli);
		dataMap.put("INSUREDList", noncarOrderInsured);
		dataMap.put("INSUREDROLEList", noncarOrderInsuredrole);
		dataMap.put("INSUREDRList", noncarOrderInsuredr);
		dataMap.put("SUMPREMIUM", sumPremium + ConfigConst.MONEY_ZH
				+ StringUtils.format2MoneyStr(Double.parseDouble(noncarOrder.getSumPremium())) + ConfigConst.MONEY_YU);
		dataMap.put("START_DATE", date);
		dataMap.put("CREATE_DATE", createDate);
		dataMap.put("signImage", imgesfile);
		dataMap.put("IconImage", iconimageone);
		dataMap.put("IcImage", iconimagethree);
		dataMap.put("IImage", iconimagetwo);
		dataMap.put("list", ListSumArray);
		Integer planId = noncarOrder.getPlanId();
		List<PlanAttach> planAttachList = storeInsureInfoMapper.findByPlanIdOrUrl(planId);
		PlanAttach planAttachlist = null;
		List<String> list = new ArrayList<String>();
		String htmlUrl = null;
		for (int i = 0; i < planAttachList.size(); i++) {
			planAttachlist = planAttachList.get(i);
			String attachInUrl = planAttachlist.getAttachInUrl();
			String fileExtension = getFileExtension(attachInUrl);
			Map m1 = new HashMap();
			if(".doc".equals(fileExtension)) {
				htmlUrl = dox(attachInUrl);
			}else if(".docx".equals(fileExtension)) {
				htmlUrl = docxToHtml(attachInUrl);
			}
			m1.put(planAttachlist.getAttachCode(), htmlUrl);
			Object json = JSON.toJSON(m1);
			list.add(json.toString());
		}
		String urlStr = null;
		Elements content = null;
		Elements contenthtml = null;
		Elements contentspan = null;
		String body = null;
		// 找到list集合里所有的body信息 存储到listbody
		List<String> listbody = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			urlStr = list.get(i);
			Object parse = JSONObject.parse(urlStr);
			Map mp = (Map) parse;
			Iterator<Map.Entry<String, String>> entrys = mp.entrySet().iterator();
			String value = null;
			Map ma = new HashMap();
			while (entrys.hasNext()) {
				Map.Entry<String, String> entry = entrys.next();
				System.out.println(entry.getKey() + entry.getValue());
				value = entry.getValue();
				File input = new File(value);
				com.itextpdf.html2pdf.jsoup.nodes.Document doc = Jsoup.parse(input, "UTF-8");
				// 找到body的内容
				content = doc.getElementsByTag(ConfigConst.CSS_BODY);
				body = content.get(0).html().toString();
				ma.put(entry.getKey(), body);
				Object json = JSON.toJSON(ma);
				listbody.add(json.toString());
			}
		}
		try {
			JSONArray parseArray = JSONObject.parseArray(listbody.toString());
			String string = parseArray.getString(0);
			Object parse = JSONObject.parse(string);
			Map mp = (Map) parse;
			Iterator<Map.Entry<String, String>> entrys = mp.entrySet().iterator();
			while (entrys.hasNext()) {
				Map.Entry<String, String> entry = entrys.next();
				dataMap.put(entry.getKey(), entry.getValue());
			}
			try {
				JSONArray parseArrayOne = JSONObject.parseArray(listbody.toString());
				String stringOne = parseArrayOne.getString(1);
				Object parseOne = JSONObject.parse(stringOne);
				Map mpOne = (Map) parseOne;
				Iterator<Map.Entry<String, String>> entrysOne = mpOne.entrySet().iterator();
				while (entrysOne.hasNext()) {
					Map.Entry<String, String> entryOne = entrysOne.next();
					dataMap.put(entryOne.getKey(), entryOne.getValue());
				}
			} catch (Exception e1) {
			}
			try {
				JSONArray parseArrayTwo = JSONObject.parseArray(listbody.toString());
				String stringTwo = parseArrayTwo.getString(2);
				Object parseTwo = JSONObject.parse(stringTwo);
				Map mpTwo = (Map) parseTwo;
				Iterator<Map.Entry<String, String>> entrysTwo = mpTwo.entrySet().iterator();
				while (entrysTwo.hasNext()) {
					Map.Entry<String, String> entryTwo = entrysTwo.next();
					dataMap.put(entryTwo.getKey(), entryTwo.getValue());
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// step4 加载模版文件
		Template template = new Template("template", new StringReader(tempContent));
		// step5 生成数据
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConst.Y_M_D_H_S);
		File docFile = new File(sign_confirmationOfInsurance + "/" + sdf.format(new Date()) + ".html");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
		template.process(dataMap, out);
		System.out.println("##################文件创建成功 !##################" + docFile);
		String templateContent = "";
		FileInputStream fileinputstream = new FileInputStream(docFile.toString());// 读取Html文件
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes);
//		System.out.println(templateContent);
		// 去除span标签的font-family
		String regEx2 = ConfigConst.CSS_SPAN;
		Pattern p2 = Pattern.compile(regEx2);
		Matcher m2 = p2.matcher(templateContent.toString());
		String okContent2 = null;
		if (m2.find()) {
			okContent2 = m2.replaceAll(ConfigConst.CSS_SPAN_FU);
		}
		// 正则去除a标签
		Pattern pA = Pattern.compile("<a[^<]*?>");
		Matcher mA = pA.matcher(okContent2.toString());
		okContent2 = mA.replaceAll("");
		Pattern pA1 = Pattern.compile("</a>");
		Matcher mA1 = pA1.matcher(okContent2);
		okContent2 = mA1.replaceAll("");
		// 正则表达式 去掉DIV标签里面所有
		String regEx = ConfigConst.CSS_DIV;
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(okContent2.toString());
		String okContent = null;
		String okContent1 = null;
		String replaceStyleBody = null;
		if (m.find()) {
			okContent = m.replaceAll(ConfigConst.CSS_DIV_FU);
			// 正则去除html所有<br>标签
			String regEx1 = ConfigConst.CSS_BR_STYLE;
			Pattern p1 = Pattern.compile(regEx1);
			Matcher m1 = p1.matcher(okContent.toString());
			if (okContent.contains(ConfigConst.CSS_BR)) {
				if (m1.find()) {
					okContent1 = m1.replaceAll(ConfigConst.CSS_SBR);
				}
				if (okContent1.contains(ConfigConst.CSS_HEAD)) {
					replaceStyleBody = okContent1.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
				}
			}
			if (StringUtils.isBlank(okContent1)) {
				if (okContent.contains(ConfigConst.CSS_HEAD)) {
					replaceStyleBody = okContent.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
				}
			}
		}else {
			// 正则去除html所有<br>标签
			String regEx1 = ConfigConst.CSS_BR_STYLE;
			Pattern p1 = Pattern.compile(regEx1);
			Matcher m1 = p1.matcher(okContent2.toString());
			if (okContent2.contains(ConfigConst.CSS_BR)) {
				if (m1.find()) {
					okContent1 = m1.replaceAll(ConfigConst.CSS_SBR);
				}
				if (okContent1.contains(ConfigConst.CSS_HEAD)) {
					replaceStyleBody = okContent1.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
				}
			}
			if (StringUtils.isBlank(okContent1)) {
				if (okContent2.contains(ConfigConst.CSS_HEAD)) {
					replaceStyleBody = okContent2.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
				}
			}
		}
//		 System.out.println(replaceStyleBody);
		String format = sdf.format(new Date());
		String fileame = sign_confirmationOfInsurance + "/" + format + ".html";
		FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
		byte tag_bytes[] = replaceStyleBody.getBytes();
		fileoutputstream.write(tag_bytes);
		fileoutputstream.close();
		urlpdf(fileame, noncarOrder.getInsureNo());
		// 上传投保确认书去影像系统
		batchUploadTwo(noncarOrder);
		return true;
	}
	 private static String getFileExtension(String fileName) {
	        int idx = fileName.lastIndexOf(".");
	        if (idx < 0)
	            return "";
	        return fileName.substring(idx);
	    }
	// 生成的pdf上传到影像系统
	public void batchUploadTwo(NoncarOrder noncarOrder) throws Exception {
		System.out.println(noncarOrder);
		String productCode = noncarOrder.getProductCode();
		String substringProductCode = productCode.substring(0, 1);
		StringBuffer bufProductCode = new StringBuffer();
		bufProductCode.append("UW_");
		bufProductCode.append(substringProductCode);
		String riskCodeProductCode = bufProductCode.toString();
		// 查询承保类型
		NoncarImgRiskVo noncarImgRiskVo = storeInsureInfoMapper.findByNocarImgRiskAndRiskType(riskCodeProductCode,noncarOrder.getInsuranceCategory());
		String documentCode = noncarImgRiskVo.getDocumentCode();
		String riskCode = noncarImgRiskVo.getRiskCode();
		String underwritingOperatorRole = noncarImgRiskVo.getUnderwritingOperatorRole();
		ImgManagerClient client = new ImgManagerClient(uploadUrl);
		// 电子保单上传到影像系统
		ImgBatchUploadRequestVo batchUploadRequestVo = new ImgBatchUploadRequestVo();
		ImgBussVo bussVo = new ImgBussVo();
		batchUploadRequestVo.setComCode(ConfigConst.COM_CODE);
		batchUploadRequestVo.setComName(ConfigConst.APPLICATION_COMNAME);
		batchUploadRequestVo.setOperator(noncarOrder.getHandlerCode());
		bussVo.setBussType(ConfigConst.INSURACNCE_TYPE_UW);
		bussVo.setRiskCode(riskCode);
		batchUploadRequestVo.setOperatorRole(underwritingOperatorRole);// CLRole01
		bussVo.setBussNo(noncarOrder.getInsureNo());
		Calendar calendar = Calendar.getInstance();
		if (noncarOrder.getInsureNo().length() == 24) {
			bussVo.setBussDate(String.valueOf(calendar.get(calendar.YEAR)).substring(0, 2)
					+ noncarOrder.getInsureNo().substring(4, 6) + "0000");// 批次所属年份 年份+0000
		} else {
			bussVo.setBussDate(noncarOrder.getInsureNo().substring(4, 8) + "0000");
		}
		bussVo.setBussCom(noncarOrder.getComCode()); // 13010000
		batchUploadRequestVo.setBussVo(bussVo);

		/** 需要删除的影像对象，只需要赋值imgId */
		if (StringUtils.isNotBlank(noncarOrder.getImgId())) {
			List<ImgMetaDataVo> deleteDataVos = new ArrayList<ImgMetaDataVo>();
			ImgMetaDataVo deleteVo01 = new ImgMetaDataVo();
			deleteVo01.setImgId((long) Integer.parseInt(noncarOrder.getImgId()));
			deleteDataVos.add(deleteVo01);
			batchUploadRequestVo.setDeleteDataVos(deleteDataVos);
		}
		/** 影像上传时的影像描述信息 */
		List<ImgMetaDataVo> uploadDataVos = new ArrayList<ImgMetaDataVo>();
		// 电子投保单
		File dir = new File(sign_confirmationOfInsurance + "/" + noncarOrder.getInsureNo() + ".pdf");
		if (dir.exists()) {
			ImgMetaDataVo uploadDataVo = new ImgMetaDataVo();
			uploadDataVo.setImgType(documentCode);
			uploadDataVo.setImgTypeName(ConfigConst.APPLICATION_IMG_TYPE_NAME);
			uploadDataVo.setUploadNode(ConfigConst.APPLICATION_UPLOAD_NODE);
			uploadDataVo.setValidFlag(ConfigConst.APPLICATION_UPLOAD_FILE);
			uploadDataVo.setUploadFile(dir);
			uploadDataVos.add(uploadDataVo);
		}
		batchUploadRequestVo.setUploadDataVos(uploadDataVos);
		XStream xs = new XStream();
		log.info("新影像上传1　发送xml= " + xs.toXML(batchUploadRequestVo));
		ImgBatchUploadResponseVo response = client.batchUpload(batchUploadRequestVo);
		log.info("新影像上传2　返回xml= " + xs.toXML(response));
		if (response != null && response.getStatusCode() == 1 && response.getSuccessDatas().size() >= 1) {
			Long imgId = response.getSuccessDatas().get(0).getImgId();
			noncarOrder.setImgId(imgId + "");
			log.info("------新影像返回代码：" + response.getStatusCode() + " \t " + "返回信息：" + "新影像上传投保确认书成功！！！");
		} else {
			log.info("------新影像返回错误代码：" + response.getStatusCode() + "  " + "错误信息：" + response.getErrorMsg());
		}
	}

	protected static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

	public void urlpdf(String htmlFilePath, String stringInsureNo) {
		try {
			// 中文字体存储路径
			String chineseFontPath = sign_confirmationOfInsurance + "/font/simsun.ttc";
			String url = sign_confirmationOfInsurance + "/" + stringInsureNo + ".pdf";
			// html转pdf
			html2pdf(htmlFilePath, url, chineseFontPath);
			System.out.println("##################html转pdf成功！##################" + url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("html转换为pdf失败");
		}
	}

	/**
	 *
	 * @param htmlFile        html文件存储路径
	 * @param pdfFile         生成的pdf文件存储路径
	 * @param chineseFontPath 中文字体存储路径
	 */
	public static void html2pdf(String htmlFile, String pdfFile, String chineseFontPath) {
		// step 1
		String url;
		OutputStream os = null;
		try {
			url = new File(htmlFile).toURI().toURL().toString();
			os = new FileOutputStream(pdfFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);
			// 解决中文不显示问题
			ITextFontResolver fontResolver = renderer.getFontResolver();
			try {
				fontResolver.addFont(chineseFontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			renderer.layout();
			renderer.createPDF(os);
		} catch (MalformedURLException e) {
			logger.warn(e.toString(), e);
		} catch (FileNotFoundException e) {
			logger.warn(e.toString(), e);
		} catch (com.lowagie.text.DocumentException e) {
			logger.warn(e.toString(), e);
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.warn(e.toString(), e);
				}
			}
		}
	}

	/**
	* 转换doc
	* @param filePath
	* @return 
	* @throws Exception
	*/
	public String dox(String filepath) throws Exception{
	   String s = UUID.randomUUID().toString();
		// 去掉-
	   String aString = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
       String htmlName = aString + ".html";
	   String htmlUrl = sign_confirmationOfInsurance + "/" + htmlName;

	   // 判断html文件是否存在
	   File htmlFile = new File(htmlUrl);
	   java.net.URL url = new java.net.URL(filepath);
	   InputStream input = new BufferedInputStream(url.openStream());
	   HWPFDocument wordDocument = new HWPFDocument(input);
	   
	   WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	   //解析word文档
	   wordToHtmlConverter.processDocument(wordDocument);
	   org.w3c.dom.Document htmlDocument = (org.w3c.dom.Document) wordToHtmlConverter.getDocument();
	   OutputStream outStream = new FileOutputStream(htmlFile);
	   DOMSource domSource = new DOMSource(htmlDocument);
	   StreamResult streamResult = new StreamResult(outStream);
	   TransformerFactory factory = TransformerFactory.newInstance();
	   Transformer serializer = factory.newTransformer();
	   serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	   serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	   serializer.setOutputProperty(OutputKeys.METHOD, "html");
	   serializer.transform(domSource, streamResult);
	   outStream.close();
	   return htmlUrl;
	 }
	
	
	/**
	* 转换docx
	* @param fileUrl
	 * @return 
	* @throws Exception
	*/
	public String docxToHtml(String fileUrl) throws IOException {
		String s = UUID.randomUUID().toString();
		// 去掉-
		String aString = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
		String htmlName = aString + ".html";
		String htmlUrl = sign_confirmationOfInsurance + "/" + htmlName;
		// 判断html文件是否存在
		File htmlFile = new File(htmlUrl);
		// 1) 加载word文档生成 XWPFDocument对象
		java.net.URL url = new java.net.URL(fileUrl);
		InputStream input = new BufferedInputStream(url.openStream());
		XWPFDocument document = new XWPFDocument(input);
		XHTMLOptions options = XHTMLOptions.create();
		OutputStream out = new FileOutputStream(htmlFile);
		XHTMLConverter.getInstance().convert(document, out, options);
		return htmlUrl;
	}
	 
	/**
	 * 保险条款
	 */
	@Override
	public List<String> findByProductClause(String proposalNo) {
		List<PlanAttach> listProductClause = storeInsureInfoMapper.findByProductClause(proposalNo);
		List<String> list = new ArrayList<String>();
		Map<String, Object> maps = new HashMap<>();
		for (PlanAttach planAttach : listProductClause) {
			maps.put("title", planAttach.getAttachName());
			maps.put("url", planAttach.getAttachUrl());
			Object json = JSON.toJSON(maps);
			list.add(json.toString());
		}
		return list;
	}

	/**
	 * 阅读告知（投保人声明，投保须知）
	 */
	@Override
	public List<String> findBylistReadInform(String proposalNo) {
		List<PlanAttach> listReadInform = storeInsureInfoMapper.findBylistReadInform(proposalNo);
		List<String> list = new ArrayList<String>();
		Map<String, Object> maps = new HashMap<>();
		for (PlanAttach planAttach : listReadInform) {
			maps.put("title", planAttach.getAttachName());
			maps.put("url", planAttach.getAttachUrl());
			Object json = JSON.toJSON(maps);
			list.add(json.toString());
		}
		return list;
	}

	/**
	 * 操作告知 （健康告知）
	 */
	@Override
	public List<String> findBylistActoionInform(String proposalNo) {
		List<PlanAttach> listActoionInform = storeInsureInfoMapper.findBylistActoionInform(proposalNo);
		List<String> list = new ArrayList<String>();
		Map<String, Object> maps = new HashMap<>();
		for (PlanAttach planAttach : listActoionInform) {
			maps.put("title", planAttach.getAttachName());
			maps.put("url", planAttach.getAttachUrl());
			Object json = JSON.toJSON(maps);
			list.add(json.toString());
		}
		return list;
	}

	@Override
	public NoncarOrder findInsureMobile(String proposalNo) {
		NoncarOrder noncarOrder = storeInsureInfoMapper.findPaymentInformationList(proposalNo);
		return noncarOrder;
	}

	@Override
	public List<PlanAttach> findTbxz(Integer integer) {
		List<PlanAttach> listActoionInform1 = storeInsureInfoMapper.findInsuranceNoticeMap(integer);
		return listActoionInform1;
	}

	@Override
	public PlanAttach findhealthNotification(Integer planId) {
		PlanAttach planAttach = storeInsureInfoMapper.findhealthNotificationList(planId);
		return planAttach;
	}
	
	@Override
	public Boolean uploadSubmissionSignUpload(UploadSignBookSubmissionVo uploadSignBookSubmissionVo,
			NoncarOrder noncarOrderCombination, List<NoncarOrder> noncarOrderList, NoncarOrder noncarOrder)
			throws Exception {
		String imgesfile = sign_confirmationOfInsurance + "/" + noncarOrderCombination.getInsureNo() + "/"
				+ noncarOrderCombination.getInsureNo() + ".jpg";
		System.out.println(imgesfile);
		File dir = new File(sign_confirmationOfInsurance + "/" + noncarOrderCombination.getInsureNo());
		if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
			dir.mkdirs();
		}
		
		File fontDir = new File(sign_confirmationOfInsurance + "/font");
		if (!fontDir.exists() && !fontDir.isDirectory()) {// 判断字体文件目录是否存在
			fontDir.mkdirs();
		}
		File font = new File(sign_confirmationOfInsurance + "/font/simsun.ttc");
		if (!font.exists()) {
			Resource simsun = resourceLoader.getResource("classpath:" + "simsun.ttc");
			InputStream in = simsun.getInputStream();
			FileOutputStream out = new FileOutputStream(font);
			IOUtils.copy(in, out);
		}
		
		boolean generateImage = generateImage(uploadSignBookSubmissionVo.getSignBytes(), imgesfile);
		if (generateImage == false) {
			return false;
		}
		// 投保人
		NoncarOrder noncarOrderappli = storeInsureInfoMapper.findApplicaIdTypeSubmission(noncarOrderCombination.getId());
		// 切换被保人证件类型
		NoncarOrder noncarOrderInsured = storeInsureInfoMapper.findInsuredIdTypeSubmission(noncarOrderCombination.getId());
		// 开始时间
		String startDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(noncarOrderCombination.getStartDate());
		String endDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(noncarOrderCombination.getEndDate());
		// 拼接转换日期
		String date = ConfigConst.FROM + startDate + "0" + ConfigConst.TIME + ConfigConst.TO_ZH + endDate + "24"
				+ ConfigConst.TIME + ConfigConst.ZHI;
		String createDate = new SimpleDateFormat(ConfigConst.YEAR_MONTH_DAY).format(new Date());
		Data2Zh data2Zh = new Data2Zh();
		String sumPremium = data2Zh.capitalization(noncarOrderCombination.getSumPremium().split("\\.")[0]);
		File iconimageDir = new File(sign_confirmationOfInsurance + "/iconimage");
		if (!iconimageDir.exists() && !iconimageDir.isDirectory()) {// 判断img文件目录是否存在
			iconimageDir.mkdirs();
		}
		File iconimagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_06.png");
		File icImagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_05.png");
		File iImagefile = new File(sign_confirmationOfInsurance + "/iconimage/img_04.png");
		if (!iconimagefile.exists() && !icImagefile.exists() && !iImagefile.exists()) {
			Resource iconImage = resourceLoader.getResource("classpath:" + "img_06.png");
			Resource icImage = resourceLoader.getResource("classpath:" + "img_05.png");
			Resource iImage = resourceLoader.getResource("classpath:" + "img_04.png");
			InputStream inOne = iconImage.getInputStream();
			InputStream inTwo = icImage.getInputStream();
			InputStream inThree = iImage.getInputStream();
			FileOutputStream outOne = new FileOutputStream(iconimagefile);
			FileOutputStream outTwo = new FileOutputStream(icImagefile);
			FileOutputStream outThree = new FileOutputStream(iImagefile);
			IOUtils.copy(inOne, outOne);
			IOUtils.copy(inTwo, outTwo);
			IOUtils.copy(inThree, outThree);
		}
		String iconimageone = sign_confirmationOfInsurance + "/iconimage/img_06.png";
		String iconimagetwo = sign_confirmationOfInsurance + "/iconimage/img_05.png";
		String iconimagethree = sign_confirmationOfInsurance + "/iconimage/img_04.png";
		Writer out = null;
		Map<String, Object> o = new HashMap();
		// 条款责任
		List<Ct> ListSumArray = new ArrayList<Ct>();
		for (NoncarOrder noncarOrderlst : noncarOrderList) {
			Ct ct = new Ct();
			ct.setProductName(noncarOrderlst.getProductName());
			Map map = new HashMap();
			map.put("orderId", noncarOrderlst.getId());
			List<NoncarPlanCt> schemeCode = storeInsureInfoMapper.findBySchemeCodeZh(map);
			ct.setNoncarPlanCtList(schemeCode);
			ListSumArray.add(ct);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("INSURE_NO", uploadSignBookSubmissionVo.getSubmissionNo());
		map.put("APPLICANT_NAME", noncarOrderappli.getCustomerName());
        map.put("INSURED_NAME", noncarOrderInsured.getCustomerName());
		map.put("APPLIIDENTIFY_TYPE", noncarOrderappli.getIdType());
		map.put("INSUREDIDENTIFY_TYPE", noncarOrderInsured.getIdType());
		map.put("APPLI_IDENTIFY", noncarOrderappli.getIdNo());
		map.put("INSURED_IDENTIFY", noncarOrderInsured.getIdNo());
		map.put("SUMPREMIUM", sumPremium + "元整（￥"
				+ StringUtils.format2MoneyStr(Double.parseDouble(noncarOrderCombination.getSumPremium())) + "元）");
		map.put("START_DATE", date);
		map.put("CREATE_DATE", createDate);
		map.put("signImage", imgesfile);
		map.put("IconImage", iconimageone);
		map.put("IcImage", iconimagethree);
		map.put("IImage", iconimagetwo);
		map.put("list", ListSumArray);
		map.put("str",noncarOrderCombination.getStrategyCode());
		
		
		/**投保须知，健康告知**/
		if("SXYZ20502100001".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270001.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270001",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "BasicEdition.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("BasicEdition",tempContent);
		}else if("SXYZ20502100002".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270002.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270002",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "EliteEdition.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("EliteEdition",tempContent);
		}else if("SXYZ20502100003".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270003.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270003",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionOne.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionOne",tempContent);
		}else if("SXYZ20502100004".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270004.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270004",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionTwo.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionTwo",tempContent);
		}else if("SXYZ20502100005".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270005.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270005",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionThree.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionThree",tempContent);
		}else if("SXYZ20502100006".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270006.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270006",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionFour.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionFour",tempContent);
		}else if("SXYZ20502100007".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270007.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270007",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionFive.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionFive",tempContent);
		}else if("SXYZ20502100008".equals(noncarOrder.getStrategyCode())) {
			Resource InsuranceNoticeTemp = resourceLoader.getResource("classpath:" + "InsuranceNoticeSXYZ20500270008.ftl");
			InputStream InsuranceNoticeInputStream = InsuranceNoticeTemp.getInputStream();
			byte[] InsuranceNoticeBytes = new byte[0];
			InsuranceNoticeBytes = new byte[InsuranceNoticeInputStream.available()];
			InsuranceNoticeInputStream.read(InsuranceNoticeBytes);
			String InsuranceNoticeTempContent = new String(InsuranceNoticeBytes);
			map.put("InsuranceNoticeSXYZ20500270008",InsuranceNoticeTempContent);
			Resource temp = resourceLoader.getResource("classpath:" + "OptionalVersionSix.ftl");
			InputStream inputStream = temp.getInputStream();
			byte[] bytes1 = new byte[0];
			bytes1 = new byte[inputStream.available()];
			inputStream.read(bytes1);
			String tempContent = new String(bytes1);
			map.put("OptionalVersionSix",tempContent);
		}
		
		o.put("datemap", map);
		
		// step2 获取模版路径
		Resource temp = resourceLoader.getResource("classpath:" + "confirm.ftl");
		InputStream inputStream = temp.getInputStream();
		byte[] bytes1 = new byte[0];
		bytes1 = new byte[inputStream.available()];
		inputStream.read(bytes1);
		String tempContent = new String(bytes1);
		// step4 加载模版文件
		Template template = new Template("template", new StringReader(tempContent));
		// step5 生成数据
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConst.Y_M_D_H_S);
		File docFile = new File(sign_confirmationOfInsurance + "/" + sdf.format(new Date()) + ".html");
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
		template.process(map, out);
		System.out.println("##################文件创建成功 !##################" + docFile);
		// html转换pdf
		urlpdf(docFile.toString(), noncarOrderCombination.getInsureNo());
		// 上传投保确认书去影像系统
		batchUploadSubmission(noncarOrderCombination);
		/** 目标批次信息 **/
		List<NoncarOrder> requestNoncarOrder = storeInsureInfoMapper.findTargetInsureNoByRelationOrderId(noncarOrderCombination.getRelationOrderId(),noncarOrderCombination.getInsureNo());
		for (NoncarOrder noncarOrderimageMultiplexingHook: requestNoncarOrder) {
			imageMultiplexingHook(noncarOrderCombination,noncarOrderimageMultiplexingHook);
		}
		return true;
	}
	
	// 生成的pdf上传到影像系统
	public void batchUploadSubmission(NoncarOrder noncarOrderCombination) throws Exception {
		ImgManagerClient client = new ImgManagerClient(uploadUrl);
		/** 拼接字符串 **/
		String productCode = noncarOrderCombination.getProductCode();
		String substringProductCode = productCode.substring(0, 1);
		StringBuffer bufProductCode = new StringBuffer();
		bufProductCode.append("UW_");
		bufProductCode.append(substringProductCode);
		String riskCodeProductCode = bufProductCode.toString();
		/** 查询承保类型 **/
		NoncarImgRiskVo noncarImgRiskVo = storeInsureInfoMapper.findByNocarImgRiskAndRiskType(riskCodeProductCode,noncarOrderCombination.getInsuranceCategory());
		String documentCode = noncarImgRiskVo.getDocumentCode();
		String riskCode = noncarImgRiskVo.getRiskCode();
		/** 电子保单上传到影像系统 **/
		ImgBatchUploadRequestVo batchUploadRequestVo = new ImgBatchUploadRequestVo();
		batchUploadRequestVo.setComCode(ConfigConst.COM_CODE);
		batchUploadRequestVo.setComName(ConfigConst.APPLICATION_COMNAME);
		batchUploadRequestVo.setOperator(noncarOrderCombination.getUserCode());
		batchUploadRequestVo.setOperatorRole(noncarImgRiskVo.getUnderwritingOperatorRole());
		/** 业务信息 **/
		ImgBussVo bussVo = new ImgBussVo();
		bussVo.setBussType(ConfigConst.INSURACNCE_TYPE_UW);
		bussVo.setRiskCode(riskCode);
		bussVo.setBussNo(noncarOrderCombination.getInsureNo());
		Calendar calendar = Calendar.getInstance();
		if (noncarOrderCombination.getInsureNo().length() == 24) {
			bussVo.setBussDate(String.valueOf(calendar.get(calendar.YEAR)).substring(0, 2)
					+ noncarOrderCombination.getInsureNo().substring(4, 6) + "0000");// 批次所属年份 年份+0000
		} else {
			bussVo.setBussDate(noncarOrderCombination.getInsureNo().substring(4, 8) + "0000");
		}
		bussVo.setBussCom(noncarOrderCombination.getComCode()); // 13010000
		batchUploadRequestVo.setBussVo(bussVo);
		/** 需要删除的影像对象，只需要赋值imgId */
		if (StringUtils.isNotBlank(noncarOrderCombination.getImgId())) {
			List<ImgMetaDataVo> deleteDataVos = new ArrayList<ImgMetaDataVo>();
			ImgMetaDataVo deleteVo01 = new ImgMetaDataVo();
			deleteVo01.setImgId((long) Integer.parseInt(noncarOrderCombination.getImgId()));
			deleteDataVos.add(deleteVo01);
			batchUploadRequestVo.setDeleteDataVos(deleteDataVos);
		}
		/** 影像上传时的影像描述信息 */
		List<ImgMetaDataVo> uploadDataVos = new ArrayList<ImgMetaDataVo>();
		/** 电子投保单 */
		File dir = new File(sign_confirmationOfInsurance + "/" + noncarOrderCombination.getInsureNo() + ".pdf");
		if (dir.exists()) {
			ImgMetaDataVo uploadDataVo = new ImgMetaDataVo();
			uploadDataVo.setImgType(documentCode);
			uploadDataVo.setImgTypeName(ConfigConst.APPLICATION_IMG_TYPE_NAME);
			uploadDataVo.setUploadNode(ConfigConst.APPLICATION_UPLOAD_NODE);
			uploadDataVo.setValidFlag(ConfigConst.APPLICATION_UPLOAD_FILE);
			uploadDataVo.setUploadFile(dir);
			uploadDataVos.add(uploadDataVo);
		}
		batchUploadRequestVo.setUploadDataVos(uploadDataVos);
		XStream xs = new XStream();
		log.info("新影像上传1　发送xml= " + xs.toXML(batchUploadRequestVo));
		ImgBatchUploadResponseVo response = client.batchUpload(batchUploadRequestVo);
		log.info("新影像上传2　返回xml= " + xs.toXML(response));
		if (response != null && response.getStatusCode() == 1 && response.getSuccessDatas().size() >= 1) {
			Long imgId = response.getSuccessDatas().get(0).getImgId();
			noncarOrderCombination.setImgId(imgId + "");
			log.info("------新影像返回代码：" + response.getStatusCode() + " \t " + "返回信息：" + "新影像上传投保确认书成功！！！");
		} else {
			log.info("------新影像返回错误代码：" + response.getStatusCode() + "  " + "错误信息：" + response.getErrorMsg());
		}
	}
	
	/**
	 * 影像复用挂接
	 * @param noncarOrderCombination
	 * @param noncarOrderimageMultiplexingHook
	 * @throws Exception
	 */
	public void imageMultiplexingHook(NoncarOrder noncarOrderCombination, NoncarOrder noncarOrderimageMultiplexingHook) throws Exception {
		/** 拼接字符串 **/
		String productCode = noncarOrderCombination.getProductCode();
		String substringProductCode = productCode.substring(0, 1);
		StringBuffer bufProductCode = new StringBuffer();
		bufProductCode.append("UW_");
		bufProductCode.append(substringProductCode);
		String riskCodeProductCode = bufProductCode.toString();
		/** 查询承保类型 **/
		NoncarImgRiskVo noncarImgRiskVo = storeInsureInfoMapper.findByNocarImgRiskAndRiskType(riskCodeProductCode,noncarOrderCombination.getInsuranceCategory());
		String documentCode = noncarImgRiskVo.getDocumentCode();
		String underwritingOperatorRole = noncarImgRiskVo.getUnderwritingOperatorRole();
		Request request = new Request();
		RequestHead requestHead =new RequestHead();
		requestHead.setConsumerID("supsale");
		
		ImageLinkRequestDTO imageLinkRequestDTO = new ImageLinkRequestDTO();
		
		/** 公共信息 **/
		BaseDataDTO baseData = new BaseDataDTO();
		baseData.setComCode(noncarOrderCombination.getComCode());
		baseData.setOperator(noncarOrderCombination.getHandlerCode());
		baseData.setOperatorRole(underwritingOperatorRole);
		imageLinkRequestDTO.setBaseData(baseData);
		
		/** 来源批次信息 **/
		SourceImageDTO sourceImage = new SourceImageDTO();
		sourceImage.setAppCode(ConfigConst.INSURACNCE_TYPE_UW);
		Calendar calendar = Calendar.getInstance();
		if (noncarOrderCombination.getInsureNo().length() == 24) {
			sourceImage.setBusinessDate(String.valueOf(calendar.get(calendar.YEAR)).substring(0, 2)
					+ noncarOrderCombination.getInsureNo().substring(4, 6) + "0000");// 批次所属年份 年份+0000
		} else {
			sourceImage.setBusinessDate(noncarOrderCombination.getInsureNo().substring(4, 8) + "0000");
		}
		sourceImage.setBusinessNo(noncarOrderCombination.getInsureNo());
		sourceImage.setClassCode(riskCodeProductCode);
		imageLinkRequestDTO.setSourceBatch(sourceImage);
		
		
		String productCodeTarget = noncarOrderimageMultiplexingHook.getProductCode();
		String substringProductCodeTarget = productCodeTarget.substring(0, 1);
		StringBuffer bufProductCodeTarget = new StringBuffer();
		bufProductCodeTarget.append("UW_");
		bufProductCodeTarget.append(substringProductCodeTarget);
		String riskCodeProductCodeTarget = bufProductCodeTarget.toString();
		/** 查询承保类型 **/
		NoncarImgRiskVo noncarImgRiskVoTarget = storeInsureInfoMapper.findByNocarImgRiskAndRiskType(riskCodeProductCodeTarget,noncarOrderimageMultiplexingHook.getInsuranceCategory());
		String documentCodeTarget = noncarImgRiskVoTarget.getDocumentCode();
		String riskCodeTarget = noncarImgRiskVoTarget.getRiskCode();
		/** 目标批次信息 **/
		TargetImageDTO targetImage = new TargetImageDTO();
		targetImage.setAppCode(ConfigConst.INSURACNCE_TYPE_UW);
		if (noncarOrderimageMultiplexingHook.getInsureNo().length() == 24) {
			targetImage.setBusinessDate(String.valueOf(calendar.get(calendar.YEAR)).substring(0, 2)
					+ noncarOrderimageMultiplexingHook.getInsureNo().substring(4, 6) + "0000");// 批次所属年份 年份+0000
		} else {
			targetImage.setBusinessDate(noncarOrderimageMultiplexingHook.getInsureNo().substring(4, 8) + "0000");
		}
		targetImage.setBusinessNo(noncarOrderimageMultiplexingHook.getInsureNo());
		targetImage.setClassCode(riskCodeTarget);
		imageLinkRequestDTO.setTargetBatch(targetImage);
		
		/** 单证复用对应关系 **/
		List<ReuseImagesDTO> reuseImagesList = new ArrayList<ReuseImagesDTO>();
		ReuseImagesDTO reuseImages = new ReuseImagesDTO();
		reuseImages.setSourceImageType(documentCode);//来源
		reuseImages.setTargetImageType(documentCodeTarget);//目标
		reuseImages.setCovered(false);//默认false不覆盖，true：覆盖，false：不覆盖
		reuseImagesList.add(reuseImages);
		imageLinkRequestDTO.setReuseImage(reuseImagesList);
		
		request.setRequestHead(requestHead);
		request.setRequestBody(imageLinkRequestDTO);
		log.info("#######影像复用挂接请求参数："+JSON.toJSONString(request));
		JsonNode jsonNode = UnirestUtil.postForJson(PropertiesUtil.getProperty("esb.address.imagelinkservicerest", null), request);
		log.info("#######影像复用挂接响应参数："+jsonNode);
	}
}
