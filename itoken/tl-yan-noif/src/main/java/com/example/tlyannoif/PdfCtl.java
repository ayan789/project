package com.example.tlyannoif;


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

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.thoughtworks.xstream.XStream;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

@RestController
@Slf4j
public class PdfCtl {
	
	protected static Logger logger = LoggerFactory.getLogger(PdfCtl.class);
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	String sign_confirmationOfInsurance="/deployments/file/sign";
	
	@PostMapping("/sign/upload2")
	public void uploadSignBookTwo(@RequestBody UploadSignBookReqVO uploadSignBookReqVO){
		String noncarOrderJson = "{\"applicantName\":\"发的\",\"comCode\":\"34011902\",\"createTime\":1597142384000,\"customerId\":\"12780\",\"customerName\":\"发的\",\"customerRoleCode\":\"1\",\"endDate\":1628697599000,\"handlerCode\":\"8000506130\",\"handlerCode2\":\"8000506130\",\"id\":\"1000000009789\",\"idNo\":\"110101199003073554\",\"idType\":\"111\",\"indiMobile\":\"13512313121\",\"insuranceCategory\":\"2\",\"insureNo\":\"TYEF20340119020012400099\",\"insureType\":\"UW_Y\",\"isDelete\":\"0\",\"isIssueAfterPay\":\"1\",\"isNeedSign\":\"0\",\"isNeedUploadImg\":\"1\",\"mobile\":\"13512313121\",\"needSign\":0,\"operatorCode\":\"8000506130\",\"operatorName\":\"武慧\",\"orderId\":\"1000000009789\",\"orderNo\":\"340120201597142139448407\",\"orderStatus\":\"2\",\"partyCategory\":\"01\",\"planId\":1374,\"policyStartRange\":1,\"policyStartType\":1,\"productCode\":\"YEF\",\"productName\":\"货运无忧\",\"signStatus\":\"1\",\"startDate\":1597161600000,\"strategyId\":999,\"strategyType\":1,\"sumPremium\":\"900.00\",\"uploadPhoto\":1,\"userCode\":\"8000506130\",\"validStatus\":1}";
		NoncarOrder noncarOrder = JSONObject.parseObject(noncarOrderJson, NoncarOrder.class);
		System.out.println(System.currentTimeMillis());
		try {
			//查询投保信息
			Boolean c = uploadSignBookTwo(uploadSignBookReqVO,noncarOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean uploadSignBookTwo(UploadSignBookReqVO uploadSignBookReqVO, NoncarOrder noncarOrder)
			throws Exception {
		System.out.println("sign_confirmationOfInsurance:"+sign_confirmationOfInsurance);
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
		String noncarOrderJson = "{\"customerName\":\"发的\",\"idNo\":\"110101199003073554\",\"idType\":\"居民身份证\"}";
		NoncarOrder noncar = JSONObject.parseObject(noncarOrderJson, NoncarOrder.class);
		List<NoncarOrder> noncarOrderappli = new ArrayList<NoncarOrder>();
		noncarOrderappli.add(noncar);
		// 切换被保人证件类型
		String noncarOrderInsuredJson = "{\"customerName\":\"发的\",\"idNo\":\"110101199003073554\",\"idType\":\"居民身份证\"}";
		NoncarOrder noncarOrderInsur = JSONObject.parseObject(noncarOrderInsuredJson, NoncarOrder.class);
		List<NoncarOrder> noncarOrderInsured = new ArrayList<NoncarOrder>();
		noncarOrderInsured.add(noncarOrderInsur);
		// 联络人
		List<NoncarOrder> noncarOrderInsuredrole = new ArrayList<NoncarOrder>();
		// 受益人
		List<NoncarOrder> noncarOrderInsuredr = new ArrayList<NoncarOrder>();
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
		String schemeCodeJson1 = "{\"coverageName\":\"国内公路货运损失\",\"insured\":10000.00,\"planCoverageName\":\"公路货物运输定额保险（B款）\"}";
		NoncarPlanCt noncarPlanCt1 = JSONObject.parseObject(schemeCodeJson1, NoncarPlanCt.class);
		String schemeCodeJson2 = "{\"coverageName\":\"第三者责任\",\"insured\":2000000.00,\"planCoverageName\":\"附加第三者责任保险条款\"}";
		NoncarPlanCt noncarPlanCt2 = JSONObject.parseObject(schemeCodeJson2, NoncarPlanCt.class);
		List<NoncarPlanCt> schemeCode = new ArrayList<NoncarPlanCt>();
		schemeCode.add(noncarPlanCt1);
		schemeCode.add(noncarPlanCt2);
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
				+ StringUtils2.format2MoneyStr(Double.parseDouble(noncarOrder.getSumPremium())) + ConfigConst.MONEY_YU);
		dataMap.put("START_DATE", date);
		dataMap.put("CREATE_DATE", createDate);
		dataMap.put("signImage", imgesfile);
		dataMap.put("IconImage", iconimageone);
		dataMap.put("IcImage", iconimagethree);
		dataMap.put("IImage", iconimagetwo);
		dataMap.put("list", ListSumArray);
		Integer planId = noncarOrder.getPlanId();
		String planAttachJson1 = "{\"attachCode\":\"insurance_notice\",\"attachInUrl\":\"http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/e1d9d01b-1ec1-487c-bc9e-45f008dfe7f6.docx\",\"attachName\":\"货运无忧产品介绍、特点、投保须知.docx\",\"attachType\":\"read_inform\",\"attachUrl\":\"https://msupsale.ccic-net.com.cn/api/cst/s3/browse/a7fd807b-6a72-4d5c-8d06-9733c2d4da6c.docx||https://msupsale.ccic-net.com.cn/api/cst/s3/browse/2de6b6eb-fc5a-4131-8654-724e673b99fd.pdf\",\"bizId\":1374,\"bizType\":\"plan\",\"recordStatus\":0}";
		String planAttachJson2 = "{\"attachCode\":\"insurance_declaration\",\"attachInUrl\":\"http://mobilesales-supsale-test.caas.ccic-test.com.cn/api/cst/s3/browse/88681cfd-e1c0-45c4-b8f4-5aa31dad5e00.docx\",\"attachName\":\"货运无忧产品介绍、特点、投保须知.docx\",\"attachType\":\"read_inform\",\"attachUrl\":\"https://msupsale.ccic-net.com.cn/api/cst/s3/browse/d73611fc-1aca-4931-9fba-5e9727cf84f6.docx||https://msupsale.ccic-net.com.cn/api/cst/s3/browse/1d792c3c-f0f0-4eab-a643-a35c5d6c7790.pdf\",\"bizId\":1374,\"bizType\":\"plan\",\"recordStatus\":0}";
		PlanAttach planAttach1 = JSONObject.parseObject(planAttachJson1, PlanAttach.class);
		PlanAttach planAttach2 = JSONObject.parseObject(planAttachJson2, PlanAttach.class);
		List<PlanAttach> planAttachList =  new ArrayList<PlanAttach>();
		planAttachList.add(planAttach1);
		planAttachList.add(planAttach2);
		PlanAttach planAttachlist = null;
		List<String> list = new ArrayList<String>();
		String htmlUrl = null;
		for (int i = 0; i < planAttachList.size(); i++) {
			planAttachlist = planAttachList.get(i);
			Map m1 = new HashMap();
			htmlUrl = docxToHtml(planAttachlist.getAttachInUrl());
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
		// 去除span标签的font-family
		String regEx2 = ConfigConst.CSS_SPAN;
		Pattern p2 = Pattern.compile(regEx2);
		Matcher m2 = p2.matcher(templateContent.toString());
		String okContent2 = null;
		if (m2.find()) {
			okContent2 = m2.replaceAll(ConfigConst.CSS_SPAN_FU);
		}
		// 正则表达式 去掉DIV标签里面所有
		String regEx = ConfigConst.CSS_DIV;
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(okContent2.toString());
		String okContent = null;
		if (m.find()) {
			okContent = m.replaceAll(ConfigConst.CSS_DIV_FU);
		}
		// 正则去除html所有<br>标签
		String regEx1 = ConfigConst.CSS_BR_STYLE;
		Pattern p1 = Pattern.compile(regEx1);
		Matcher m1 = p1.matcher(okContent.toString());
		String okContent1 = null;
		// head里面增加全局style设置字体 加前面的<body> </body>替换掉后面的</html>
		String replaceStyleBody = null;
		if (okContent.contains(ConfigConst.CSS_BR)) {
			if (m1.find()) {
				okContent1 = m1.replaceAll("");
			}
			if (okContent1.contains(ConfigConst.CSS_HEAD)) {
				replaceStyleBody = okContent1.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
			}
		}
		if (StringUtils2.isBlank(okContent1)) {
			if (okContent.contains(ConfigConst.CSS_HEAD)) {
				replaceStyleBody = okContent.replaceAll(ConfigConst.CSS_HEAD, ConfigConst.CSS_HEAD_BODY_PDF);
			}
		}
		// System.out.println(replaceStyleBody);
		String format = sdf.format(new Date());
		String fileame = sign_confirmationOfInsurance + "/" + format + ".html";
		FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
		byte tag_bytes[] = replaceStyleBody.getBytes();
		fileoutputstream.write(tag_bytes);
		fileoutputstream.close();
		urlpdf(fileame, noncarOrder.getInsureNo());
		// 上传投保确认书去影像系统
		//batchUploadTwo(noncarOrder);
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
	
	public void urlpdf(String htmlFilePath, String stringInsureNo) {
		try {
			// 中文字体存储路径
			String chineseFontPath = sign_confirmationOfInsurance + "/font/simsun.ttc";
			String url = sign_confirmationOfInsurance + "/" + stringInsureNo + ".pdf";
			// html转pdf
			html2pdf(htmlFilePath, url, chineseFontPath);
			System.out.println("##################html转pdf成功！##################" + url);
		} catch (Exception e) {
			logger.error("html转换为pdf失败", e);
		}
	}
	
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
	

}
