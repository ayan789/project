package com.ccic.salesapp.performance.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.mapper.CaptchaMapper;
import com.ccic.salesapp.performance.domain.vo.dto.AppCheckCode;
import com.ccic.salesapp.performance.domain.vo.dto.Captcha;
import com.ccic.salesapp.performance.domain.vo.dto.SUser;
import com.ccic.salesapp.performance.domain.vo.request.CheckRankRequest;
import com.ccic.salesapp.performance.domain.vo.request.SalaryPwdRequest;
import com.ccic.salesapp.performance.domain.vo.request.SalaryPwdUpdateRequest;
import com.ccic.salesapp.performance.domain.vo.request.SalaryQueryLoginRequest;
import com.ccic.salesapp.performance.domain.vo.request.SalaryRequest;
import com.ccic.salesapp.performance.domain.vo.request.SalarySMSRequest;
import com.ccic.salesapp.performance.domain.vo.request.SendSmsRequest;
import com.ccic.salesapp.performance.domain.vo.request.UserInfoRequest;
import com.ccic.salesapp.performance.domain.vo.response.CheckRankResponse;
import com.ccic.salesapp.performance.domain.vo.response.RandomNumResponse;
import com.ccic.salesapp.performance.domain.vo.response.SalaryPwdResponse;
import com.ccic.salesapp.performance.domain.vo.response.SalaryResponse;
import com.ccic.salesapp.performance.service.ModifyUserInfoService;
import com.ccic.salesapp.performance.service.PerformanceEmpAsmtInfoService;
import com.ccic.salesapp.performance.service.SUserService;
import com.ccic.salesapp.performance.service.SalaryService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.MD5;
import com.ccic.salessapp.common.utils.RSAUtils;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.ValidationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "薪酬查询", description = "薪酬查询", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "progress")
@Slf4j
public class SalaryCtl {

    @Autowired
    SalaryService salaryService;
    @Autowired
    SUserService suserService;
    
    @Autowired
    PerformanceEmpAsmtInfoService performanceEmpAsmtInfoService;
    
    @Autowired
    ModifyUserInfoService modifyUserInfoService;
    
    @Autowired
    CaptchaMapper captchaMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    
    
    
    private static final int IMG_WIDTH=146;  
	private static final int IMG_HEIGHT=30;
	private static final int DISTURB_LINE_SIZE = 15;  
	private int xyresult;  
	private String randomString; 
	private Random random = new Random();
	private static final String CVCNUMBERS = "\u96F6\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341\u4E58\u9664\u52A0\u51CF";  
	//  private static final String [] CVCNUMBERS2 = "零, 壹, 贰, 叁, 肆, 伍, 陆, 柒, 捌, 玖, 拾".split(",");  
	private static final String CVCNUMBERS2 = "\u96f6\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396\u62fe\u4e58\u9664\u52a0\u51cf";  
	private Font font = new Font("DejaVu", Font.BOLD, 18);  
	private static final Map<String, Integer> OPMap = new HashMap<String, Integer>();  
	static{  
		  OPMap.put("*", 11);  
		  OPMap.put("/", 12);  
		  OPMap.put("+", 13);  
		  OPMap.put("-", 14);  
	}
	
	@PostConstruct 
	public void init() {
		log.debug("执行验证码字体加载");
		try {
	    	org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+"cukaiti.ttf");
			InputStream fontStream = resource.getInputStream();
			font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = font.deriveFont(Font.PLAIN,18);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostMapping(value = "salaryQuery")
    @ApiOperation(value = "薪酬查询", notes = "薪酬查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<SalaryResponse> salaryQuery(@RequestBody SalaryRequest request) {
        return salaryService.salaryQuery(request);
    }
    
	@PostMapping(value = "checkRank")
    @ApiOperation(value = "职级考核", notes = "职级考核", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<CheckRankResponse> checkRank(@RequestBody CheckRankRequest request) {
    	ValidationUtils.validate(request);
        return HttpResult.success(performanceEmpAsmtInfoService.selectRankCheck(request.getUserCode()));
    }

    @PostMapping(value = "isHaveSetSalaryPwd")
    @ApiOperation(value = "是否设置薪酬密码", notes = "是否设置薪酬密码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult<SalaryPwdResponse> isHaveSetSalaryPwd(@RequestBody SalaryPwdRequest request)  { 
    	  ValidationUtils.validate(request);
    	  SalaryPwdResponse response = new SalaryPwdResponse();
		  SUser user = suserService.selectByUserCode(request.getUserCode()); 
		  if(user != null && StringUtils.isNotBlank(user.getSalaryPassword())){
			  response.setIsSetPwd("1");
		  }else{
			  response.setIsSetPwd("0");
		  } 
		  return HttpResult.success(response); 
     }


    @PostMapping(value = "salelawSalaryUpdatePwd")
    @ApiOperation(value = "修改薪酬密码", notes = "修改薪酬密码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult salelawSalaryUpdatePwd(@RequestBody SalaryPwdUpdateRequest updateVO){ 
		String userID = updateVO.getUserCode();
		String newPassword = updateVO.getNewPassword();
		String smsCode = updateVO.getSmsCode();		//前端获取的短信验证码
		log.info("新密码密文"+newPassword);
		
		
		 AppCheckCode checkCode = modifyUserInfoService.getCheckCode(userID);
		 if(checkCode==null || !smsCode.equals(checkCode.getCheckCode())){ 
			 return HttpResult.error(0, "手机验证码不正确，请重新输入");
		 }
		 
		try {
			newPassword = MD5.crypt(RSAUtils.decryptByPrivate(newPassword));
			SUser user = new SUser();
			user.setUserCode(userID);
			user.setSalaryPassword(newPassword);
			suserService.updateSalaryPwd(user);
			return HttpResult.success();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return HttpResult.error(0, "密码设置失败，请重试");
		}
    }
    
    @PostMapping(value = "getUserInfo")
    @ApiOperation(value = "用户信息查询", notes = "用户信息查询", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult getUserInfo(@RequestBody UserInfoRequest request) {
    	SUser storeUser = modifyUserInfoService.getUserMobile(request.getUserCode());
    	return HttpResult.success(storeUser);
    }
    
    /**
	 * 发送短信验证码
	 * @param jsonVO
	 * @return
	 */
    @PostMapping(value = "sendSalarySMS")
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult sendSalarySMS(@RequestBody SalarySMSRequest request){
    	HttpResult result = HttpResult.success();
		String valicode = request.getCaptcha().getCaptcha();	//图片验证码
		String randomNum = request.getCaptcha().getRandomNum();	//随机验证码
		
		SUser storeUser = modifyUserInfoService.getUserMobile(request.getUserCode());
		if(StringUtils.isBlank(storeUser.getUserTel())){
			result.setCode("0");
			result.setMsg("请您先在“我的”信息栏下输入手机号码");
			return result;
		}
		
		Captcha captcha = null;
		try {
			captcha =  captchaMapper.selectByRandom(randomNum);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		Captcha captcha2 = new Captcha();
		captcha2.setStatus(0);
		captcha2.setRandomNum(randomNum);
		try {
			captchaMapper.update(captcha2);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		if(captcha==null || !valicode.equals(captcha.getCaptcha())){
			result.setCode("0");
			result.setMsg("图片验证码不正确，请重新输入");
			return result;
		}

		try {
			//判断是否频繁点击
			boolean isPass = false;
			//boolean isPass = SmsCheckUtil.isDoubleClickByUserCode(loginUser.getUserCode());
			if(!isPass){
				//请求获取验证码接口
				SendSmsRequest smsRequest = new SendSmsRequest();
				smsRequest.setComcode(request.getComCode());
				smsRequest.setMobile(storeUser.getUserTel());
				smsRequest.setUserId(request.getUserCode());
				
				return modifyUserInfoService.sendsimplemessageservicerest(smsRequest,"您本次的验证码为checkCode。");
			}else{
				result.setCode("0");
				result.setMsg("请稍后，不要频繁操作！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);			
			result.setCode("0");
			result.setMsg("发送验证码异常");
		}
		return result;
	}
    
    @PostMapping(value = "salelawSalaryLogin")
    @ApiOperation(value = "薪酬查询登录", notes = "薪酬查询登录", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult salelawSalaryLogin(@RequestBody SalaryQueryLoginRequest userVO){
		String salaryPwd = userVO.getSalaryPassword(); //前端获取的查询密码
		SUser user = null;
		try {
			user = suserService.selectByUserCode(userVO.getUserCode()); 
			if(user == null) {
				return HttpResult.error(0, "该用户不存在");	
			}
			String salaryPassword = user.getSalaryPassword();
			if(StringUtils.isBlank(salaryPassword)){
				return HttpResult.error(0, "您尚未设置薪酬查询密码");	
			}
			salaryPwd = RSAUtils.decryptByPrivate(salaryPwd);
			if(!MD5.crypt(salaryPwd).equals(salaryPassword)){
				return HttpResult.error(0, "密码不正确，请重新输入");	
			}else{
				return HttpResult.success();
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return HttpResult.error(0, "异常："+e.getMessage());
		}
	}

    
    /**
	 * 获取图片验证码
	 * @param vo
	 * @param sc
	 * @return
     * @throws IOException 
	 * @throws Exception
	 */
    @PostMapping(value = "captcha")
    @ApiOperation(value = "图形验证码", notes = "图形验证码", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<RandomNumResponse> userGetCaptcha() throws IOException
	{
		ByteArrayOutputStream out = null;
		try{
			BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);  
		    Graphics g = image.getGraphics();  
		    g.setColor(Color.WHITE);  
		    
		    g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);  
		    g.setColor(getRandomColor(200,250));  
		    g.drawRect(0, 0, IMG_WIDTH-2, IMG_HEIGHT-2);  
		      
		    g.setColor(getRandomColor(110, 133));  
		    for(int i =0;i < DISTURB_LINE_SIZE; i++){  
		        drawDisturbLine1(g);  
		        drawDisturbLine2(g);  
		    }  
		    getRandomMathString();  
		    //验证码转换算法
		    randomString= VerificationCodeChange((Graphics2D)g);
		    drawRandomString((Graphics2D)g,"\u7B49\u4E8E\uFF1F", 3);  
		    g.dispose();
		    out = new ByteArrayOutputStream();
		    ImageIO.write(image, "JPEG", out);
		    byte[] bytes = out.toByteArray();
		    String base64bytes = new String (Base64.getEncoder().encode(bytes));
		    String src = "data:image/png;base64," + base64bytes;
		    
		    //插入验证码记录
		    String random = RandomStringUtils.randomAlphanumeric(10);
			Captcha captcha = new Captcha();
			captcha.setRandomNum(random);
			captcha.setStatus(1);
			captcha.setCaptcha(Integer.toString(xyresult));
			captchaMapper.insert(captcha);
			RandomNumResponse randomNum = new RandomNumResponse();
			randomNum.setSrc(src);
			randomNum.setRandomNum(random);
			return HttpResult.success(randomNum);
		}catch(Exception ex){
			log.error("系统错误:"+ex.getMessage(), ex);
			return HttpResult.error(0, "系统错误:"+ex.getMessage());
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	public Color getRandomColor(int fc,int bc){  
	      if(fc > 255){  
	          fc = 255;  
	      }  
	      if(bc > 255){  
	          bc = 255;  
	      }  
	      int r = fc+random.nextInt(bc -fc - 16);  
	      int g = fc+random.nextInt(bc - fc - 14);  
	      int b = fc+random.nextInt(bc - fc - 18);  
	      return new Color(r, g, b);  
	  }  
	
	public void drawDisturbLine1(Graphics g){  
	      int x1 = random.nextInt(IMG_WIDTH);  
	      int y1 = random.nextInt(IMG_HEIGHT);  
	      int x2 = random.nextInt(13);  
	      int y2 = random.nextInt(15);  
	      g.drawLine(x1, y1, x1 + x2, y1 + y2);  
	  }  
	    
	  public void drawDisturbLine2(Graphics g){  
	      int x1 = random.nextInt(IMG_WIDTH);  
	      int y1 = random.nextInt(IMG_HEIGHT);  
	      int x2 = random.nextInt(13);  
	      int y2 = random.nextInt(15);  
	      g.drawLine(x1, y1, x1 - x2, y1 - y2);  
	  }  
	  
	  private void getRandomMathString(){  
          int xx = random.nextInt(10);  
          int yy = random.nextInt(10);  
          StringBuilder suChinese =  new StringBuilder();  
          int Randomoperands = (int) Math.round(Math.random()*2);  
          if(Randomoperands ==0){  
              this.xyresult = yy * xx;  
              suChinese.append(yy);  
              suChinese.append("*");  
              suChinese.append(xx);  
          }else if(Randomoperands ==1){  
              if(!(xx==0) && yy%xx ==0){  
                  this.xyresult = yy/xx;  
                  suChinese.append(yy);  
                  suChinese.append("/");  
                  suChinese.append(xx);  
              }else{  
                  this.xyresult = yy + xx;  
                  suChinese.append(yy);  
                  suChinese.append("+");  
                  suChinese.append(xx);  
              }  
          }else if(Randomoperands ==2){  
                  this.xyresult = yy - xx;  
                  suChinese.append(yy);  
                  suChinese.append("-");  
                  suChinese.append(xx);  
          }else{  
                  this.xyresult = yy + xx;  
                  suChinese.append(yy);  
                  suChinese.append("+");  
                  suChinese.append(xx);  
          }  
	      this.randomString = suChinese.toString();  
	  }  
	  
	  private String  VerificationCodeChange(Graphics2D g){
	  	   int y = random.nextInt(3);  //转换策略
	  	   StringBuffer logsu = new StringBuffer(); 
	       for(int j=0,k = randomString.length(); j < k; j++){  
	        int chid = 0;
	        String ch=null;
	        if(j==1){  
	            chid = OPMap.get(String.valueOf(randomString.charAt(j))); 
	            ch = String.valueOf(CVCNUMBERS.charAt(chid));  
	        }else{  
	      	  if(y==0){
	      		  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	      		  ch = String.valueOf(CVCNUMBERS2.charAt(chid));  
	      	  }
	      	  if(y==1){
	      		  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	      		  ch = String.valueOf(CVCNUMBERS.charAt(chid));  
	      	  }
	      	  if(y==2){
	      		  if(j==0){
	      			  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	          		  ch = String.valueOf(CVCNUMBERS2.charAt(chid));  
	      		  }else{
	      			  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	          		  ch = String.valueOf(CVCNUMBERS.charAt(chid)); 
	      		  }
	      	  }
	      	  if(y==3){
	      		  if(j==2){
	      			  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	          		  ch = String.valueOf(CVCNUMBERS2.charAt(chid));  
	      		  }else{
	      			  chid = Integer.parseInt(String.valueOf(randomString.charAt(j))); 
	          		  ch = String.valueOf(CVCNUMBERS.charAt(chid)); 
	      		  }
	      	  }
	        }  
	        logsu.append(ch);  
	        drawRandomString((Graphics2D)g,ch, j);  
	      }  
	      return logsu.toString();
	  }
	  
	  public void drawRandomString(Graphics2D g,String randomvcch,int i){
		  g.setFont(font);
	      int rc = random.nextInt(255);  
	      int gc = random.nextInt(255);  
	      int bc = random.nextInt(255);  
	      g.setColor(new Color(rc, gc, bc));  
	      int x = random.nextInt(3);  
	      int y = random.nextInt(2);  
	      g.translate(x, y);  
	      int degree = new Random().nextInt() % 15;  
	      g.rotate(degree * Math.PI / 180, 5+i*25, 20);  
	      g.drawString(randomvcch, 5+i*25, 20);  
	      g.rotate(-degree * Math.PI / 180, 5+i*25, 20);  
	  }  
}
