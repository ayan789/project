//package com.ccic.salesapp.noncar.utils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import net.sf.json.JSONObject;
//
//public class ConfigFacade {
//	private static Log log=LogFactory.getLog(ConfigFacade.class);
//
//	
//	
//	/**
//	 * 配置相关查询
//	 * @param vo
//	 * @param sc
//	 * 
//	 * @author wangcw
//	 */
//	public JsonVO getConfigParams(JsonVO vo,ServletContext sc) throws Exception{
//		JsonVO jsonVO = new JsonVO();
//		jsonVO.setHead(vo.getHead());
//		ResultVO resultVO = new ResultVO();
//		ConfigParamsRequestVO req =(ConfigParamsRequestVO) vo.getData();
//		LoginUser loginUser = vo.getHead().getLoginUser();
//		try {
//			ConfigParamsResponseVO configParamsResponseVO = this.getConfigParams(req, loginUser);
//			jsonVO.setData(configParamsResponseVO);
//			resultVO.setCode("1");
//			resultVO.setMessage("查询配置信息成功！");
//			jsonVO.setResult(resultVO);
//		} catch (Exception e) {
//			log.error("配置信息查询异常",e);
//			resultVO.setCode("0");
//			resultVO.setMessage("查询配置信息失败！");
//			jsonVO.setResult(resultVO);
//		}
//		
//		return jsonVO;
//		
//	}
//	
//	public ConfigParamsResponseVO getConfigParams(ConfigParamsRequestVO req,LoginUser loginUser) throws Exception {
//		DAOBase dao = new DefaultDAO();
//		String staffCode = loginUser.getUserCode();//业务员代码
//		String comCode = "";
//		if(StringUtils.isNotBlank(loginUser.getComCode())) {
//			comCode=loginUser.getComCode();
//		}else {
//			comCode=req.getComCode();
//		}
//		if(StringUtils.isBlank(comCode)) {
//			comCode="99999999";
//		}
//		
//		//费改地区
//		List<FeeChangeArea> feeChangeAreas = null;
//		//险别保额档位
//		List<KindAmount> kindAmounts = null;
//		//上平台车船税地区
//		List<PlatformTaxArea> platformTaxAreas = null;
//		//网销o2o地址
//		StringBuffer wxO2OUrl = new StringBuffer();
//		//自动续保开放地区
//		List<String> autoRenewalAreas = null;
//		//组织机构调整开放地区
//		List<String> organizationChangeAreas = null;
//		//电子投保试点地区
//		List<String> electrInsuranceAreas = null;
//		//新华北京地区身份证编码器
//		String xhMachinecode = null;
//		//全面型车损险试点地区
//		List<String> setKindA7Area = null;
//		//续保认领90天平台
//		List<String> renewal90Platform = null;
//		//续保认领60天平台
//		List<String> renewal60Platform = null;
//		//薪酬发放日期
//		String salaryDay = null;
//		//华泰分期试点地区
//		List<String> huataiPeriodArea = null;
//		//期望优惠关闭地区
//		List<String> expectDiscountCloseArea = null;
//		
//		platformTaxAreas = dao.toList("platformTaxArea.getPlatformTaxAreaList",null);
//		ConfigParamsResponseVO configParamsResponseVO = new ConfigParamsResponseVO();
//		configParamsResponseVO.setFeeChangeAreas(feeChangeAreas);
//		configParamsResponseVO.setKindAmounts(kindAmounts);
//		configParamsResponseVO.setPlatformTaxAreas(platformTaxAreas);
//		configParamsResponseVO.setXhMachinecode(xhMachinecode);	//新华北京地区身份证编码器默认值
//		
//		
//		if(StringUtils.isNotBlank(staffCode)){
//			SystemConfig cofig=new SystemConfig("system");
//			wxO2OUrl.append(cofig.getResourceValue("wxO2OUrl"));
//			wxO2OUrl.append("?").append("username=").append(staffCode);
//			wxO2OUrl.append("&md5=").append(MD5.crypt("saleAppUserName="+staffCode));
//			configParamsResponseVO.setWxO2OUrl(wxO2OUrl.toString());
//		}
//		//查询自动续保地区
//		Map<String,String> paraMap = new HashMap<String,String>();
//		paraMap.put("configType", "autoRenewalArea");
//		autoRenewalAreas = dao.toList("generalConfig.getConfigValue",paraMap);
//		configParamsResponseVO.setAutoRenewalAreas(autoRenewalAreas);
//		//查询组织架构调整地区
//		paraMap.clear();
//		paraMap.put("configType", "organizationChangeArea");
//		organizationChangeAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setOrganizationChangeAreas(organizationChangeAreas);
//		//查询电子投保试点地区
//		paraMap.clear();
//		paraMap.put("configType", "electrInsuranceArea");
//		electrInsuranceAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setElectrInsuranceAreas(electrInsuranceAreas);
//		//查询新华北京地区身份证编码器默认值
//		paraMap.clear();
//		paraMap.put("configType", "xh_machinecode");
//		xhMachinecode = (String) dao.toView("generalConfig.getConfigValueNoCache", paraMap);
//		configParamsResponseVO.setXhMachinecode(xhMachinecode);
//		
//		//全面型车损险试点地区
//		paraMap.clear();
//		paraMap.put("configType", "kind_A7_Area");
//		setKindA7Area = dao.toList("generalConfig.getConfigValue",paraMap);
//		configParamsResponseVO.setKindA7Area(setKindA7Area);
//		
//		//续保认领90天平台
//		paraMap.clear();
//		paraMap.put("configType", "90platform");
//		renewal90Platform = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setRenewal90Platform(renewal90Platform);
//		
//		//续保认领60天平台
//		paraMap.clear();
//		paraMap.put("configType", "60platform");
//		renewal60Platform = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setRenewal60Platform(renewal60Platform);
//		
//		if(StringUtils.isNotBlank(comCode)) {
//			paraMap.clear();
//			paraMap.put("configType", "salaryDay");
//			paraMap.put("comCode", comCode.substring(0,4));
//			salaryDay =(String) dao.toView("generalConfig.getConfigValueNoCache",paraMap);
//			configParamsResponseVO.setSalaryDay(salaryDay);				
//		}
//		
//		//华泰分期试点地区
//		paraMap.clear();
//		paraMap.put("configType", "HuataiPeriodArea");
//		huataiPeriodArea = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setHuataiPeriodArea(huataiPeriodArea);
//
//		//期望优惠关闭地区
//		paraMap.clear();
//		paraMap.put("configType", "expectDiscountCloseArea");
//		expectDiscountCloseArea = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setExpectDiscountCloseArea(expectDiscountCloseArea);
//		
//		if(StringUtils.isNotBlank(comCode)) {
//			String licenseNoAbbr = (String)dao.toView("licenseNoAbbr.querylicenseNoAbbr",comCode);
//			configParamsResponseVO.setLicenseNoAbbr(licenseNoAbbr);				
//		}
//
//		//新核心车险上线地区
//		configParamsResponseVO.setNewCoreInsuranceAreas(this.getNewCoreInsuranceAreas("1"));
//		
//		//新核心非车险上线地区
//		configParamsResponseVO.setNewCorePropertyInsuranceAreas(this.getNewCoreInsuranceAreas("2"));
//		
//		//新核心意健险上线地区
//		configParamsResponseVO.setNewCorePersonalInsuranceAreas(this.getNewCoreInsuranceAreas("3"));
//		
//		//支持交强险短期险地区
//		paraMap.clear();
//		paraMap.put("configType", "ciShortAreas");
//		List<String> ciShortAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setCiShortAreas(ciShortAreas);
//		
//		//支持商业险短期险地区
//		paraMap.clear();
//		paraMap.put("configType", "biShortAreas");
//		List<String> biShortAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setBiShortAreas(biShortAreas);
//		
//		//云南车船税特殊开关配置
//		paraMap.clear();
//		paraMap.put("configType", "YN_TAX_CONFIG");
//		String ynTaxConfig = (String) dao.toView("generalConfig.getConfigValueNoCache",paraMap);
//		configParamsResponseVO.setYnTaxConfig(ynTaxConfig);
//		
//		//APP首页配置数据
//		configParamsResponseVO.setHomeConfigResponseVO(getHomeConfig(loginUser));
//		
//		//APP版本号
//		configParamsResponseVO.setAndoridAppVersion((String) dao.toView("generalConfig.getAppVersion","DDBXSAndroid_H5"));
//		configParamsResponseVO.setIosAppVersion((String) dao.toView("generalConfig.getAppVersion","DDBXSIPhone_H5"));
//		
//		//实名缴费标志
//		configParamsResponseVO.setRealPayAreaFlag(this.realPayAreaFlag(comCode.substring(0,4)));
//		//验证码标志
//		configParamsResponseVO.setYzmAreaFlag(this.yzmAreaFlag(comCode.substring(0,4),dao));	
//		
//		//上海客户识别开关配置
//		configParamsResponseVO.setShVerifyFlag(this.shVerifyFlag(comCode.substring(0,4),dao));
//		
//		return configParamsResponseVO;
//	}
//	
//	
//	/**
//	 * elife产品地址 (天地关爱，百万医疗) 
//	 * @param vo
//	 * 
//	 * @author wangcw
//	 */
//	public JsonVO getElifeUrl(JsonVO vo) throws Exception{
//		JsonVO jsonVO = new JsonVO();
//		jsonVO.setHead(vo.getHead());
//		ResultVO resultVO = new ResultVO();
//		ElifeRequestVO req =(ElifeRequestVO) vo.getData();
//		String staffCode = req.getStaffCode();//业务员代码
//		String comCode = req.getComCode();//机构代码
//		String isAgent = req.getIsAgent();//是否代理 1是0否
//		String businessNature = req.getBusinessNature();//业务来源
//		String agentCode = req.getAgentCode();//代理人代码
//		String agentProtocolNo = req.getAgentProtocolNo();//中介协议代码
//		String businessNature2 = req.getBusinessNature2();//业务来源二
//		String handler2Code = req.getHandler2Code();//经办人二代码
//		String handler2Name = req.getHandler2Name();//经办人二名称
//		
//		if(StringUtils.isBlank(staffCode)){
//			resultVO.setCode("0");
//			resultVO.setMessage("业务员代码不能为空！");
//			jsonVO.setResult(resultVO);
//			return jsonVO;
//		}
//		if(StringUtils.isBlank(comCode)){
//			resultVO.setCode("0");
//			resultVO.setMessage("机构代码不能为空！");
//			jsonVO.setResult(resultVO);
//			return jsonVO;
//		}
//		if(StringUtils.isBlank(isAgent)){
//			resultVO.setCode("0");
//			resultVO.setMessage("是否代理标识不能为空！");
//			jsonVO.setResult(resultVO);
//			return jsonVO;
//		}
//		if("1".equals(isAgent)){
//			if(StringUtils.isBlank(businessNature)){
//				resultVO.setCode("0");
//				resultVO.setMessage("业务来源不能为空！");
//				jsonVO.setResult(resultVO);
//				return jsonVO;
//			}
//			if(StringUtils.isBlank(agentCode)){
//				resultVO.setCode("0");
//				resultVO.setMessage("代理人代码不能为空！");
//				jsonVO.setResult(resultVO);
//				return jsonVO;
//			}
//			if(StringUtils.isBlank(agentProtocolNo)){
//				resultVO.setCode("0");
//				resultVO.setMessage("中介协议代码不能为空！");
//				jsonVO.setResult(resultVO);
//				return jsonVO;
//			}
//		}
//		
//		try {
//			SystemConfig cofig=new SystemConfig("system");
//			String bwyl = cofig.getResourceValue("bwylUrl");
//			
//			JSONObject json = new JSONObject();
//			json.put("billmanNo",staffCode);
//			json.put("billmanComeCode",comCode);
//			json.put("operatorNo",staffCode);
//			json.put("operatorComeCode",comCode);
//			json.put("systemCode",WebContants.C_BUSINESS_SOURCE_ATTRIBUTE);
//			if("1".equals(isAgent)){
//				json.put("businessNature",businessNature);
//				JSONObject agentJson = new JSONObject();
//				agentJson.put("agentCode", agentCode);
//				agentJson.put("agentProtocolNo", agentProtocolNo);
//				agentJson.put("businessNature2", businessNature2);
//				agentJson.put("businessKindCode", handler2Code);
//				agentJson.put("businessKindName", handler2Name);
//				json.put("agentInfo", agentJson);
//			}else{
//				json.put("businessNature",WebContants.C_BUSINESS_SOURCE_SALESMAN);
//				json.put("agentInfo", "");
//			}
//			bwyl = bwyl.replace("PARAMJSON", JSONObject.fromObject(json).toString());
//			
//			ElifeResponseVO resVO = new ElifeResponseVO();
//			resVO.setBwyl(bwyl);
//			jsonVO.setData(resVO);
//			resultVO.setCode("1");
//			resultVO.setMessage("成功！");
//			jsonVO.setResult(resultVO);
//		} catch (Exception e) {
//			log.error("elife产品地址查询异常",e);
//			resultVO.setCode("0");
//			resultVO.setMessage("elife产品地址查询失败！");
//			jsonVO.setResult(resultVO);
//		}
//		return jsonVO;
//	}
//	
//	/**
//	 * 长链接转短链接
//	 * @param vo
//	 * 
//	 * @author wangcw
//	 */
//	public JsonVO getShortUrl(JsonVO vo) throws Exception{
//		JsonVO jsonVO = new JsonVO();
//		jsonVO.setHead(vo.getHead());
//		ResultVO resultVO = new ResultVO();
//		ShortUrlRequestVO req =(ShortUrlRequestVO) vo.getData();
//		String url = req.getUrl();//原始链接
//		
//		if(StringUtils.isBlank(url)){
//			resultVO.setCode("0");
//			resultVO.setMessage("原始链接不能为空！");
//			jsonVO.setResult(resultVO);
//			return jsonVO;
//		}
//		
//		try {
//			String urlCode = RandomStringUtils.randomAlphanumeric(10);
//			DAOBase dao = new DefaultDAO();
//			UrlMapping urlMapping = new UrlMapping();
//			urlMapping.setUrlCode(urlCode);
//			urlMapping.setUrlMapping(url);
//			dao.doInsert("urlMapping.toInsert", urlMapping);
//			
//			SystemConfig cofig=new SystemConfig("system");
//			String shortUrl = cofig.getResourceValue("short_url_config")+urlCode;
//			
//			ShortUrlResponseVO resVO = new ShortUrlResponseVO();
//			resVO.setShortUrl(shortUrl);
//			jsonVO.setData(resVO);
//			resultVO.setCode("1");
//			resultVO.setMessage("成功！");
//			jsonVO.setResult(resultVO);
//		} catch (Exception e) {
//			log.error("生成短链接异常",e);
//			resultVO.setCode("0");
//			resultVO.setMessage("操作失败，请重试！");
//			jsonVO.setResult(resultVO);
//		}
//		return jsonVO;
//	}
//
//	
//	/**
//	 * 判断地区是否为新核心试点地区
//	 * @throws Exception 
//	 * 
//	 * */
//	public boolean isNewCore(String comCode,String riskCode) throws Exception{
//		boolean isNewCore=false;//判断是否走新核心接口
//		//总公司机构同上海分公司
//		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
//			comCode="3101";
//		}
//		List<String> newCoreAreas=this.getNewCoreInsuranceAreas(riskCode);
//		if(newCoreAreas!=null&&newCoreAreas.size()>0){
//			for(String area:newCoreAreas){
//				if(area.equals(comCode)){
//					isNewCore=true;
//					break;
//				}
//			}    
//	    }
//		return isNewCore;
//	}
//	
//	public boolean flagAddrInsurance(String comCode) throws Exception {
//		boolean flagNoAddre=false;//判断是否走新核心接口
//		DAOBase dao = new DefaultDAO();
//		Map<String,String> paraMap = new HashMap<String,String>();
//		
//		paraMap.put("configType", "notPalaceOrderArea");
//		
//		List<String> newCoreAreas=new ArrayList<String>();
//		newCoreAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		if(newCoreAreas!=null&&newCoreAreas.size()>0){
//			for(String area:newCoreAreas){
//				if(area.equals(comCode)){
//					flagNoAddre=true;
//					break;
//				}
//			}    
//	    }
//		return flagNoAddre;
//	}
//	
//	private List<String> getNewCoreInsuranceAreas(String riskCode) throws Exception {
//		DAOBase dao = new DefaultDAO();
//		Map<String,String> paraMap = new HashMap<String,String>();
//		String configType="";
//		if("1".equals(riskCode)){//新核心车险试点地区
//			configType="newCoreCarInsuranceArea";
//		}else if("2".equals(riskCode)){//新核心非车险试点地区
//			configType="newCorePropertyInsuranceArea";
//		}else if("3".equals(riskCode)){//新核心意健险试点地区
//			configType="newCorePersonalInsuranceArea";
//		}else{
//			return null;
//		}
//		paraMap.put("configType", configType);
//		
//		List<String> newCoreAreas=new ArrayList<String>();
//		newCoreAreas = dao.toList("generalConfig.getConfigValueNoCache",paraMap);
//		return newCoreAreas;
//	}
//	
//	/**
//	 * 实名缴费标志
//	 * @throws Exception 
//	 * 
//	 * */
//	public String realPayAreaFlag(String comCode) throws Exception{
//		//总公司机构同上海分公司
//		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
//			comCode="3101";
//		}
//		DAOBase dao = new DefaultDAO();
//		Map<String,String> paraMap = new HashMap<String,String>();
//		paraMap.put("configType", "realPayArea");
//		paraMap.put("comCode", comCode);
//		String flag =  (String)dao.toView("generalConfig.getConfigValueNoCache",paraMap);
//		return flag;
//	}
//	
//	/**
//	 * 验证码标志
//	 * @throws Exception 
//	 * 
//	 * */
//	public String yzmAreaFlag(String comCode,DAOBase dao) throws Exception{
//		//总公司机构同上海分公司
//		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
//			comCode="3101";
//		}
//		if(dao==null) {
//			dao = new DefaultDAO();
//		}
//		Map<String,String> paraMap = new HashMap<String,String>();
//		paraMap.put("configType", "YZMArea");
//		paraMap.put("comCode", comCode);
//		String flag = (String)dao.toView("generalConfig.getConfigValueNoCache",paraMap);
//		return flag;
//	}
//	
//	/**
//	 * 上海客户识别开关
//	 * @throws Exception 
//	 * 
//	 * */
//	public String shVerifyFlag(String comCode,DAOBase dao) throws Exception{
//		//总公司机构同上海分公司
//		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
//			comCode="3101";
//		}
//		if(dao==null) {
//			dao = new DefaultDAO();
//		}
//		Map<String,String> paraMap = new HashMap<String,String>();
//		paraMap.put("configType", "sh_verify_flag");
//		String flag = (String)dao.toView("generalConfig.getConfigValueNoCache",paraMap);
//		return flag;
//	}	
//	
//	private HomeConfigResponseVO getHomeConfig(LoginUser userInfo) {
//		HomeConfigResponseVO res = new HomeConfigResponseVO();
//		try { 
//			HomeSettingFacade homeConfig = new HomeSettingFacadeImpl(null);
//			//截取前四位
//			String comCode=userInfo.getComCode().substring(0, 4);
//			List<HomeConfig> configList=  homeConfig.getHomeSettings(comCode);
//			List<HomeConfig> bannersList = new ArrayList<HomeConfig>();
//			List<HomeConfig> menusList = new ArrayList<HomeConfig>();
//			List<HomeConfig> toptitleList = new ArrayList<HomeConfig>();
//			List<HomeConfig> recommendList = new ArrayList<HomeConfig>();
//			ConfigFacade facade=new ConfigFacade();
//			for (HomeConfig homeConfig2 : configList) {
//				if("1".equals(homeConfig2.getType())){// banner 
//					bannersList.add(homeConfig2);
//					continue ;
//				}else if("2".equals(homeConfig2.getType())){//menu 菜单
//					if("agen".equals(userInfo.getUserCode().substring(0, 4))){
//						if("业绩".equals(homeConfig2.getTitle())||"薪酬".equals(homeConfig2.getTitle())){
//							continue;
//						}
//					}else{
//						if("代理人业绩".equals(homeConfig2.getTitle())||"佣金".equals(homeConfig2.getTitle())){
//							continue;
//						}
//					}
//					if(facade.isNewCore(userInfo.getComCode().substring(0,4),"1")){
//						if("家用车".equals(homeConfig2.getTitle())||"非家用车".equals(homeConfig2.getTitle())||"O2O报价".equals(homeConfig2.getTitle())){
//							continue;
//						}
//						if("家用车-新核心".equals(homeConfig2.getTitle())||"非家用车-新核心".equals(homeConfig2.getTitle())){
//							homeConfig2.setTitle(homeConfig2.getTitle().split("-")[0]);
//						}
//					}else{
//						if("家用车-新核心".equals(homeConfig2.getTitle())||"非家用车-新核心".equals(homeConfig2.getTitle())){
//							continue;
//						}
//					}
//					menusList.add(homeConfig2);
//					continue ;
//				}else if("3".equals(homeConfig2.getType())){// 大地头条
//					toptitleList.add(homeConfig2);
//					continue ;
//				}else if("4".equals(homeConfig2.getType())){//热门推荐
//					recommendList.add(homeConfig2);
//					continue ;
//				}else {
//					continue;
//				}
//			}
//			res.setBanners(bannersList);
//			res.setMenus(menusList);
//			res.setToptitle(toptitleList);
//			res.setRecommend(recommendList);
//		 
//		} catch (Exception e) {
//			log.error(e);
//		}
//		return res;
//	}
//}
