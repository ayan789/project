package com.ccic.salesapp.noncar.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccic.salesapp.noncar.config.SystemConfig;
import com.ccic.salesapp.noncar.dto.ImgBatch;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ElectronicPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.SendElectronicPolicyRequest;
import com.ccic.salesapp.noncar.dto.request.SendMessageRequest;
import com.ccic.salesapp.noncar.dto.request.ShortUrlRequest;
import com.ccic.salesapp.noncar.dto.response.ElectronicPolicyResponse;
import com.ccic.salesapp.noncar.dto.response.ShortUrlResponse;
import com.ccic.salesapp.noncar.dto.response.VerifyEpolicyResponse;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.BaseDataDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownRequest;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownResponse;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.H5ImgQueryDownResponseDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.MetaDataDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.bean.RequestHeadDTO;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.intf.H5ImgQueryDownPort;
import com.ccic.salesapp.noncar.repository.basedb.mapper.GeneralConfigMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UrlMappingMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.UrlMapping;
import com.ccic.salesapp.noncar.service.ElectronicPolicyService;
import com.ccic.salesapp.noncar.service.SmsMessageService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.request.LoginUser;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.PropertyStrategyWrapper;
import com.ccic.salessapp.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import net.sf.json.util.PropertySetStrategy;

@Service
@Slf4j
public class ElectronicPolicyImpl implements ElectronicPolicyService {

	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	public static JsonConfig jsonConfig = new JsonConfig();
	static{
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){  
		    @Override  
		    public boolean apply(Object source, String name, Object value)  
		    {  
		    	return value == null;
		    }
		    
		});
		jsonConfig.addIgnoreFieldAnnotation(Request.class);
		
	}
	
	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;
	
	@Autowired
	UrlMappingMapper urlMappingMapper;
	
	@Autowired
	H5ImgQueryDownPort h5ImgQueryDownPort;
	
	@Value("${system.electronicPolicy_downloadUrl}")
	String servletUrl;
	
	@Value("${system.ePolicyPassword}")
	String desPassword;
	
	@Value("${system.mail.send.host}")
	String host; // smtp服务器
	@Value("${system.mail.send.port}")
	String port; // smtp服务器
	
	@Value("${system.short_url_config}")
	String short_url_config;
	
	@Value("${system.eltric_policy}")
	String savePath;
	
	@Value("${esb.address.electronicPolicy}")
	String electronicPolicy ;
	
	@Value("${esb.netType.electronicPolicyImpl}")
	String electronicPolicyImpl ;
	
	@Value("${esb.netType.electronicPolicy}")
	String netType;
	
	@Autowired
	GeneralConfigMapper generalConfigMapper;
	
	@Autowired
	SmsMessageService smsMessageService;
	
	@Override
	public HttpResult electronicPolicy(ElectronicPolicyRequest reqVO,String netType) throws Exception {
		HttpResult result = HttpResult.success();
		result.setCode("0");
		com.ccic.salesapp.noncar.dto.request.VerifyEpolicyRequest request=new com.ccic.salesapp.noncar.dto.request.VerifyEpolicyRequest();
		StoreInsureInfo storeInsureInfo=storeInsureInfoMapper.selectByPolicyNo( reqVO.getPolicyNo());
		request.setCertiType("P");//单证类型
		request.setCertiNo(reqVO.getPolicyNo());//单证号
		request.setChannelType("0");//
		request.setInsuredFlag("2");
		request.setInsuredName(storeInsureInfo.getApplicantName());
		request.setIdentifyNumber(storeInsureInfo.getAppliIdentify());
		Response response=new Response();
		try {
			response= electronicPolicy(request);
			VerifyEpolicyResponse responseBody=(VerifyEpolicyResponse)response.getResponseBody();
			if(response.getResponseHead().getStatus()==0 && responseBody!=null
					&& StringUtils.isNotBlank(responseBody.getImageId())) {
				H5ImgQueryDownRequestDTO requestBody = new H5ImgQueryDownRequestDTO();
				MetaDataDTO mata = new MetaDataDTO();
				mata.setAppCode("UW");//
				mata.setClassCode("UW_Y");//3  D 车   Y财产   
				String reDate=datef.format(new Date());			
				mata.setBusiDate(reDate.substring(0,4));
				mata.setBusinessNo(storeInsureInfo.getInsureNo());
				mata.setValidMinutes(BigInteger.valueOf(60));
				mata.getPageIds().add(responseBody.getImageId());
				requestBody.setMetaData(mata);			
				BaseDataDTO base = new BaseDataDTO();
				base.setNetType(electronicPolicyImpl);
				base.setComCode(storeInsureInfo.getComCode());
				base.setOperator(storeInsureInfo.getOperatorCode());
				//base.setOperatorName("李佳文");
				base.setOperatorRole("UWDZRole02"); //  
				requestBody.setBaseData(base);
				H5ImgQueryDownRequest requestDTO = new H5ImgQueryDownRequest();
				RequestHeadDTO requestHead = this.getConsumerID();
				requestDTO.setRequestHead(requestHead);
				requestDTO.setRequestBody(requestBody);
				//H5ImgQueryDownPort sevice2=(H5ImgQueryDownPort)this.getBean("electronicPolicy");
				try {
					String reqJson = JSONObject.fromObject(requestDTO,jsonConfig).toString();
			    	log.info("调用图像下载接口，请求报文："+ reqJson);
					H5ImgQueryDownResponse response2 = h5ImgQueryDownPort.h5ImgQueryDown(requestDTO);
					String resJson = JSONObject.fromObject(response2,jsonConfig).toString();
					log.info("调用图像下载接口，响应报文："+ resJson);
					H5ImgQueryDownResponseDTO responseBody2=(H5ImgQueryDownResponseDTO)response2.getResponseBody();
					if(response2.getResponseHead().getStatus()==0 && responseBody2!=null
							&& responseBody2.getImageNodes().size()>0){
						ElectronicPolicyResponse responseVO=new ElectronicPolicyResponse();
						
						//SystemConfig config=new SystemConfig("system");
						//String servletUrl = config.getResourceValue("electronicPolicy_downloadUrl");
						//String desPassword = config.getResourceValue("ePolicyPassword");
						StringBuffer lookUrl=new StringBuffer(servletUrl);
						lookUrl.append("?policyNo=").append(DESEncryptUtil.encrypt(reqVO.getPolicyNo(),desPassword));
						long timestamp =new Date().getTime();
						lookUrl.append("&timestamp=").append(timestamp);
						lookUrl.append("&flag=1");
						responseVO.setLookUrl(lookUrl.toString());
						responseVO.setDownLoadUrl(StringEscapeUtils.unescapeHtml(responseBody2.getImageNodes().get(0).getPicPath()));
						result.setData(responseVO);
						result.setCode("1");
						result.setMsg("电子保单下载地址获取成功！");
					}else {
						result.setCode("0");
						result.setMsg("电子保单生成中，请稍后查看！");
					}
				} catch (Exception e) {
					result.setCode("0");
					result.setMsg("获取电子保单地址异常");
				}
			}else {
				result.setCode("0");
				result.setMsg("电子保单生成中，请稍后查看！");
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("获取电子保单异常");	
		}
		//jsonVO.setResult(result);
		return result;
	}

	public RequestHeadDTO getConsumerID(){
		RequestHeadDTO requestHead = new RequestHeadDTO();
		requestHead.setConsumerID("supsale");
		return requestHead;
	}
	/**
	 * 发送电子保单
	 * 
	 * 
	 * **/
	@Override
	public HttpResult sendElectronicPolicy(SendElectronicPolicyRequest reqVO,LoginUser user) throws Exception {
		HttpResult result = HttpResult.success();
		try {
			String isSuccess = "0";
			if("1".equals(reqVO.getType())) {
				isSuccess = this.sendEmail(reqVO);
			}else {
				boolean isRepeat = false;
				//boolean isRepeat = SmsCheckUtil.isRepeatSendByMobile(reqVO.getMobile());
				if(!isRepeat) {
					isSuccess = sendMobileMessage(reqVO, user);
				}else {
					result.setCode("0");
					result.setMsg("请稍后重新发送，不要频繁操作！");
					//jsonVO.setResult(result);
					return result;
				}
				
			}
			if("1".equals(isSuccess)) {
				result.setCode("1");
				result.setMsg("发送成功");
				//jsonVO.setResult(result);	
			}else {
				result.setCode("0");
				result.setMsg("发送失败");
				//jsonVO.setResult(result);
			}
		} catch (Exception e) {
			result.setCode("0");
			result.setMsg("发送异常："+e.getMessage());
			//jsonVO.setResult(result);
		}
		return result;
	}
	
	//发送邮件
	public String sendEmail(SendElectronicPolicyRequest reqVO) throws Exception {
		String isSuccess = "0";
		ElectronicPolicyRequest electronicPolicyRequestVO=new ElectronicPolicyRequest();
		electronicPolicyRequestVO.setPolicyNo(reqVO.getPolicyNo());
		//ElectronicPolicyFacade facade=new ElectronicPolicyFacadeImpl();
		HttpResult jsonVO=electronicPolicy(electronicPolicyRequestVO,netType);
		if("1".equals(jsonVO.getCode())) {
		    //下载电子保单
			//SystemConfig config=new SystemConfig("system");
			//String savePath= config.getResourceValue("eltric_policy");
			String fileName = reqVO.getPolicyNo()+".pdf";
			ElectronicPolicyResponse responseVO=(ElectronicPolicyResponse)jsonVO.getData();
			String urlStr=responseVO.getDownLoadUrl();
			File file=dawnLoadPdf(savePath,urlStr,fileName);
			if(file!=null) {
				boolean rs = sendMail(file, reqVO.getEmail());
				//删除本次电子保单
				if (rs) {
					isSuccess="1";
					file.delete();
				}
			}
		}
		return isSuccess;
	}
	
	public File dawnLoadPdf(String savePath, String urlStr, String fileName) {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlStr);	
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(60 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 得到输入流
			inputStream = conn.getInputStream();
			// 获取自己数组
			byte[] getData = readInputStream(inputStream);
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			fos = new FileOutputStream(file);
			fos.write(getData);
			fos.flush();
			return file;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 发送邮件 
	 * @return
	 * @throws Exception 
	 */
	public boolean sendMail(File file,String email) throws Exception {
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("configType", "electronic_policy");
		String feeBackEmail = generalConfigMapper.getConfigValueNoCache(paraMap).get(0);
		String[] feeBackEmailArr = feeBackEmail.split(",");
		
		String user = feeBackEmailArr[1]; // 用户名
		String pwd = feeBackEmailArr[2]; // 密码
		String from = feeBackEmailArr[0]; // 发件人地址
		String to = email; // 收件人地址
		
		String subject = "大地保险电子保单"; // 邮件标题
		Properties props = new Properties();
		// 设置发送邮件的邮件服务器的属性
		props.put("mail.smtp.host", host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条\6r4wq	
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.connectiontimeout", "60000");
		props.put("mail.smtp.timeout", "60000");
		props.put("mail.smtp.writetimeout", "60000");
		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);
		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		//session.setDebug(true);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		Transport transport = null;
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// 加载标题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();
			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			String content = "尊敬的客户您好，您的电子保单以附件形式发送，请注意查收！";
			contentPart.setContent(content, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			
			// 附件部分
			contentPart = new MimeBodyPart();
	        // 设置要发送附件的文件路径
	        DataSource source = new FileDataSource(file);
	        contentPart.setDataHandler(new DataHandler(source));
	        // messageBodyPart.setFileName(filename);
	        // 处理附件名称中文（附带文件路径）乱码问题
	        contentPart.setFileName(MimeUtility.encodeText(file.getName()));
	        multipart.addBodyPart(contentPart);				
			
			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(host, user, pwd);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			log.error("邮件发送失败：",e);
			return false;
		}finally {
			if(transport!=null) {
				try {
					transport.close();
				} catch (Exception e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		return true;
	}
	
	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
	
	public String sendMobileMessage(SendElectronicPolicyRequest reqVO,LoginUser user) throws Exception {
		String isSuccess = "0";
		SystemConfig config = new SystemConfig("system");
		try {
			//String servletUrl=config.getResourceValue("electronicPolicy_downloadUrl");
			//String desPassword = config.getResourceValue("ePolicyPassword");
			StringBuffer downLoadUrl=new StringBuffer(servletUrl);
			downLoadUrl.append("?policyNo=").append(DESEncryptUtil.encrypt(reqVO.getPolicyNo(),desPassword));
			long timestamp =new Date().getTime();
			downLoadUrl.append("&timestamp=").append(timestamp);
			//转换成短链接
			//ConfigFacade facade=new ConfigFacade();
			//com.estar.app.chinare.sell.jsonvo.JsonVO json1=new com.estar.app.chinare.sell.jsonvo.JsonVO();
			
			ShortUrlRequest req =new ShortUrlRequest();
			req.setUrl(downLoadUrl.toString());
			HttpResult result = getShortUrl(req);
			StringBuffer content=new StringBuffer();
			content.append("您好！您的保单").append(reqVO.getPolicyNo());
			content.append("。投保成功,您可以拨打我公司全国服务热线95590进行查询或者报案，或者点击链接 ");
			if("1".equals(result.getCode())) {
				ShortUrlResponse resVO1 =(ShortUrlResponse)result.getData();
				content.append(resVO1.getShortUrl());
			}else {
				content.append(downLoadUrl);
			}
			content.append(" 进行电子保单的下载，链接有效期7天（中国大地保险）。");
			SendMessageRequest request =new SendMessageRequest();
			request.setComCode(user.getComCode());
			request.setMessage(content.toString());
			request.setMobile(reqVO.getMobile());
			//SendMessageFacade facade2=new SendMessageFacadeImpl();
			try {
				result = smsMessageService.sendMessage(request,user);
				if("1".equals(result.getCode())) {
					isSuccess="1";
				}
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return isSuccess;
	}
	
	
	/**
	 * 保单验证
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	public com.ccic.salessapp.common.outService.rest.common.bean.Response electronicPolicy(com.ccic.salesapp.noncar.dto.request.VerifyEpolicyRequest requestBody) throws Exception{	
		Request request = new Request();
    	RequestHead requestHead= new RequestHead();
    	requestHead.setConsumerID("mobile");
    	request.setRequestHead(requestHead);
    	request.setRequestBody(requestBody);
    	
    	String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info("调用核心电子保单验证接口开始，请求报文："+ reqJson);
		String resJson = postJsonRequest(reqJson, electronicPolicy);
		log.info("调用核心电子保单验证接口结束，响应报文："+ resJson);
		
		ResponseHead responseHead = null;
		VerifyEpolicyResponse responseBody = null;
		JSONObject json = JSONObject.fromObject(resJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("responseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			// 声明JsonConfig对象
			JsonConfig cfg = new JsonConfig();
			//  设置属性包装器
			cfg.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
			cfg.setRootClass(VerifyEpolicyResponse.class);
			responseBody = (VerifyEpolicyResponse) JSONObject.toBean(json.getJSONObject("responseBody"), cfg);
		}
		
		Response response = new Response();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		
    	return response;
	}
	
	/**
	 * 长链接转短链接
	 * @param vo
	 * 
	 * @author wangcw
	 */
	public HttpResult getShortUrl(ShortUrlRequest req) throws Exception{
		HttpResult result = HttpResult.success();
		
		String url = req.getUrl();//原始链接
		
		if(StringUtils.isBlank(url)){
			result.setCode("0");
			result.setMsg("原始链接不能为空！");
			//jsonVO.setResult(resultVO);
			return result;
		}
		
		try {
			String urlCode = RandomStringUtils.randomAlphanumeric(10);
			//DAOBase dao = new DefaultDAO();
			UrlMapping urlMapping = new UrlMapping();
			urlMapping.setUrlCode(urlCode);
			urlMapping.setUrlMapping(url);
			urlMappingMapper.toInsert(urlMapping);
			
			String shortUrl = short_url_config+urlCode;
			
			ShortUrlResponse resVO = new ShortUrlResponse();
			resVO.setShortUrl(shortUrl);
			result.setData(resVO);
			result.setCode("1");
			result.setMsg("成功！");
		} catch (Exception e) {
			log.error("生成短链接异常",e);
			result.setCode("0");
			result.setMsg("操作失败，请重试！");
		}
		return result;
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param reqJson :请求的字符串
	 * @param uri :请求路径
	 * @param charset :编码
	 * @return
	 * @throws Exception 
	 */
	public String postJsonRequest(String reqJson, String uri, String charset) throws Exception {
		HttpClient  client=new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		client.getHttpConnectionManager().getParams().setSoTimeout(60000);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("mobile", "mobile"));
		log.info("##########请求地址：" +uri);
		PostMethod method=new PostMethod(uri);
		StringBuffer response= new StringBuffer();  
		try {
			method.setRequestHeader("Content-Type", "application/json");
			RequestEntity entity = new StringRequestEntity(reqJson,"application/json",charset);
			method.setRequestEntity(entity);
 			int statusCode = client.executeMethod(method);
 			BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));  
 			String line;  
 			while ((line = reader.readLine()) != null) {  
                response.append(line);  
            }  
            reader.close(); 
			//log.info("调用核心---返回报文： resp{"+response+"}");
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Method failed: " + method.getStatusLine());
			}
		} catch (Exception e) {		
			log.error("http请求异常：",e);
			throw e;
		}
		return response.toString(); // 返回
	}
	public String postJsonRequest(String reqJson, String uri) throws Exception {
		return postJsonRequest(reqJson,uri,"utf-8");
	}
	
}
