package com.ccic.salesapp.performance.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.performance.domain.mapper.AppCheckCodeMapper;
import com.ccic.salesapp.performance.domain.mapper.SUserDao;
import com.ccic.salesapp.performance.domain.vo.dto.AppCheckCode;
import com.ccic.salesapp.performance.domain.vo.dto.SUser;
import com.ccic.salesapp.performance.domain.vo.request.Sendsimplemessageservicerest;
import com.ccic.salesapp.performance.domain.vo.response.SendSimpleMessageResponseDTO;
import com.ccic.salesapp.performance.outService.esb.sms.bean.SendSmsRequest;
import com.ccic.salesapp.performance.outService.esb.sms.bean.SendSmsRequestDTO;
import com.ccic.salesapp.performance.outService.esb.sms.bean.SendSmsResponse;
import com.ccic.salesapp.performance.outService.esb.sms.intf.SendSms;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.service.AbstractService;
import com.ccic.salessapp.common.utils.PropertiesUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.UnirestUtil;

import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

@Service
@Slf4j
public class ModifyUserInfoService extends AbstractService {
	//	private static Log log=LogFactory.getLog(ModifyUserInfoFacadeImpl.class);
	//	DefaultDAO dao = new DefaultDAO();	

	/*
	 *发送短信
	 */
	@Autowired
	AppCheckCodeMapper appCheckCodeMapper;

	//	@Autowired
	//	StoreUserInfoMapper storeUserInfoMapper;

	@Autowired
	SUserDao sUserDao;

	@Autowired
	SendSms service;
	
	@Value("${esb.consumerId.performance}")
	String performance;

	/*
	 * 查询验证码
	 */
	public AppCheckCode getCheckCode(String userCode) {
		Map<String, String> param=new HashMap<String, String>();
		param.put("userCode",userCode);
		AppCheckCode appCheckCode=new AppCheckCode();
		try {
			//appCheckCode = (AppCheckCode) dao.toView("AppCheckCode.selectCodeByUser",param);
			appCheckCode = appCheckCodeMapper.selectCodeByUser(param);
		} catch (Exception e) {
			log.info("查询验证码失败！");
			e.printStackTrace();
		}
		return appCheckCode;
	}


	/**
	 * 生产六位验证码
	 * @return 
	 * @throws CustomException 
	 */
	private String getRandomNum(){
		String s = "";
		while(s.length()<6){
			s+=(int)(Math.random()*10);
		}
		return s;
	}
	/*
	 * 上传头像文件
	 */

	/*
	 * public JsonVO uploadHeadPic(String staffCode,String bytes) { JsonVO jsonVO =
	 * new JsonVO(); ResultVO result=new ResultVO(); //存储图片 String imageName =
	 * staffCode+"headPic.jpg";; SystemConfig sysConfig=new SystemConfig("system");
	 * String pic_path = null;//上传图片 地址 路径 try { pic_path =
	 * sysConfig.getResourceValue("headpic_path");
	 * log.info("上传图片地址pic_path：-----"+pic_path); } catch (GeneralException e) {
	 * e.printStackTrace(); } //平台服务器图片存储地址+服务器文件间隔符号=平台图片根目录。 String imagePath =
	 * pic_path+File.separatorChar;
	 * log.info("---------平台服务器图片存储地址imagePath---------"+imagePath); //保存图片到平台服务器
	 * try { this.uploadImagelocal(staffCode, imagePath, imageName, bytes,
	 * imagePath); log.info("图片上传成功！"); result.setCode("1");
	 * result.setMessage("图片上传成功！"); jsonVO.setResult(result); } catch
	 * (CustomException e) { log.info("图片上传失败！"); e.printStackTrace(); } return
	 * jsonVO; }
	 */


	/**
	 * 平台服务器保存图片
	 */
	/*
	 * private String uploadImagelocal(String staffCode,String imagePath,String
	 * imageName,String images,String imageType) throws CustomException{
	 * 
	 * File dir; String picPath = "";// 相对路径，用于保存到数据库时用
	 * log.info("建立文件夹*******imagePath：-----"+imagePath); dir = new File(imagePath);
	 * try { if (!dir.exists()) { dir.mkdirs(); } } catch (Exception e) {
	 * log.error(e); e.printStackTrace(); }
	 * 
	 * //验证图片是否存在 picPath = imagePath + File.separatorChar + imageName; dir = new
	 * File(picPath); if(dir.exists()){//如果图片已存在就删除，可能出现图片错误上传，然后再次上传的现象
	 * if(dir.delete()){ try {
	 * log.info("平台已删除图片 名称："+imageName+"\n\t 删除时间："+DateUtils.getSysDate()); }
	 * catch (Exception e) { log.error(e); e.printStackTrace(); throw new
	 * CustomException("删除图片异常"+e); } } } images = images.replaceAll(" ", "+");//图片
	 * 
	 * boolean flag=false; try { String endTime = this.getInstanceTime();
	 * log.info("图像开始上传平台时间：-----"+endTime);
	 * 
	 * flag = writeImage(images, picPath);// 上传图片
	 * 
	 * } catch (Exception e) { log.error(e); e.printStackTrace(); throw new
	 * CustomException("上传图片到平台出现异常",e); } if(flag){//上传图片成功 return picPath; }else{
	 * return ""; }
	 * 
	 * }
	 */


	/**
	 * 上传图片到平台服务器
	 * @param image		图片信息
	 * @param picPath	保存图片路径
	 * @return
	 * @throws Exception
	 */
	public boolean writeImage(String image,String picPath) throws Exception{
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out=null;
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(image);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			out = new FileOutputStream(picPath);
			out.write(bytes);
			return true;
		} catch (Exception e) {
			log.error("－－－－－－－－－－－－图片保存平台失败 path："+picPath+"\n");
			e.printStackTrace();
			return false;
		}finally{
			if(out!=null)
				out.close();
		}
	}


	public SUser getUserMobile(String userCode) {
		List<SUser>userInfoList = new ArrayList<SUser>();
		SUser userInfo = new SUser(); 
		Map<String, String> param=new HashMap<String, String>();
		param.put("userCode",userCode);
		try {
			userInfoList =sUserDao.selectByUser(param);
			if(userInfoList!=null&&userInfoList.size()>0) {
				userInfo=userInfoList.get(0);
			}
		} catch (Exception e) {
			log.info("查询用户手机号失败！");
			e.printStackTrace();
		}
		return userInfo;
	}

	public HttpResult sendsimplemessageservicerest(com.ccic.salesapp.performance.domain.vo.request.SendSmsRequest smsRequest,
			String templat) {
		HttpResult result = HttpResult.success();
		Request request = new Request();
		RequestHead requestHead =new RequestHead();
		requestHead.setConsumerID(performance);
		Sendsimplemessageservicerest requestBody = new Sendsimplemessageservicerest();
		requestBody.setChannel(getSubChannelAndMessageType().getChannel());
		requestBody.setSubChannel(getSubChannelAndMessageType().getSubChannel());
		requestBody.setMessageType(getSubChannelAndMessageType().getMessageType());
		requestBody.setComcode(smsRequest.getComcode());
		requestBody.setTargetType(getSubChannelAndMessageType().getTargetType());
		requestBody.setTarget(smsRequest.getMobile());
		String checkCode = getRandomNum();
		String content="";
		if(StringUtils.isNotBlank(templat)){
			content = templat.replace("checkCode", checkCode);
		}else{
			content = "大地保险修改手机号验证码为"+checkCode;
		}
		requestBody.setContent(content);
		requestBody.setPriority(getSubChannelAndMessageType().getPriority());
		request.setRequestHead(requestHead);
		request.setRequestBody(requestBody);
		log.info("#######普通短信请求参数："+JSON.toJSONString(request));
		JsonNode jsonNode = UnirestUtil.postForJson(PropertiesUtil.getProperty("esb.address.sendsimplemessageservicerest", null), request);
		log.info("#######普通短信响应参数："+jsonNode);
		SendSimpleMessageResponseDTO response = JSON.parseObject(jsonNode.toString(), SendSimpleMessageResponseDTO.class);
		int status = response.getResponseHead().getStatus();
		String appMessage=response.getResponseHead().getAppMessage();
		String esbMessage=response.getResponseHead().getEsbMessage();
		log.info("--status:"+status+"--appMessage:"+appMessage+"--esbMessage"+esbMessage);
		int count = 0;
		String longTime = String.valueOf(System.currentTimeMillis());
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("userCode", smsRequest.getUserId());
		param.put("checkCode", checkCode);
		param.put("longTime", longTime);
		param.put("mobile", smsRequest.getMobile());
		//验证码发送成功
		if(status==0){
			try {
				//执行更新
				count = appCheckCodeMapper.upCheckCode(param);
				if(count==0){
					//执行插入
					AppCheckCode codeReq = new AppCheckCode();
					codeReq.setCheckCode(checkCode);
					codeReq.setLongTime(longTime);
					codeReq.setMobile(smsRequest.getMobile());
					codeReq.setUserCode(smsRequest.getUserId());
					count = appCheckCodeMapper.insert(codeReq);
				}
			} catch (Exception e) {
				result.setCode("0");
				result.setMsg("验证码入库更新失败");
				e.printStackTrace();
			}
			result.setCode("1");
			result.setMsg("验证码短信发送成功");
		}else{
			result.setCode("0");
			result.setMsg("验证码短信发送失败："+appMessage);
		}
		return result;	
	}


	public static Sendsimplemessageservicerest getSubChannelAndMessageType() {
		Sendsimplemessageservicerest requestBody = new Sendsimplemessageservicerest();
		requestBody.setSubChannel("106");
		requestBody.setMessageType("10001");
		requestBody.setChannel("sms");
		requestBody.setTargetType("1");
		String str="3";
		requestBody.setPriority(new BigInteger(str.getBytes()));
		return requestBody;
	}

}
