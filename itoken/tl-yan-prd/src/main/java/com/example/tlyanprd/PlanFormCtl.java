package com.example.tlyanprd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tlyanprd.PlanElement;
import com.example.tlyanprd.PlanElementDetaiReq;
import com.example.tlyanprd.PlanElementGroup;
import com.example.tlyanprd.PlanElementOption;
import com.example.tlyanprd.PlanFieldDto;
import com.example.tlyanprd.planelement.ApiMapping;
import com.example.tlyanprd.planelement.BackReasoningFactor;
import com.example.tlyanprd.planelement.BizVo;
import com.example.tlyanprd.planelement.Bottom;
import com.example.tlyanprd.planelement.ClaimStatement;
import com.example.tlyanprd.planelement.CommonProblem;
import com.example.tlyanprd.planelement.CommonProblemContent;
import com.example.tlyanprd.planelement.FieldVo;
import com.example.tlyanprd.planelement.FormulaList;
import com.example.tlyanprd.planelement.FormulaObj;
import com.example.tlyanprd.planelement.Header;
import com.example.tlyanprd.planelement.HealthNotice;
import com.example.tlyanprd.planelement.ImgList;
import com.example.tlyanprd.planelement.Insurance;
import com.example.tlyanprd.planelement.LinkElementCode;
import com.example.tlyanprd.planelement.PbBox;
import com.example.tlyanprd.planelement.PlanButton;
import com.example.tlyanprd.planelement.PlanElementDetailDto;
import com.example.tlyanprd.planelement.ProductInfo;
import com.example.tlyanprd.planelement.ProductPoster;
import com.example.tlyanprd.planelement.RateParams;
import com.example.tlyanprd.planelement.Rates;
import com.example.tlyanprd.planelement.RelatedPersonInfo;
import com.example.tlyanprd.planelement.SafeguardDetail;
import com.example.tlyanprd.planelement.SafeguardPlan;
import com.example.tlyanprd.planelement.SharePlatform;
import com.example.tlyanprd.planelement.Swipper;
import com.example.tlyanprd.planelement.TermsAndConditions;
import com.example.tlyanprd.planelement.TermsAndConditionsContent;
import com.example.tlyanprd.planelement.ValueList;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "planForm")
public class PlanFormCtl {

	@Autowired
	TPrdPlanFormMapper tPrdPlanFormMapper;

	//动态表单
	@PostMapping("/getPlanDynamicForms")
	public HttpResult getPlanDynamicForms(@RequestBody FieldVo fieldVo) {
		List<PlanFieldDto> list = new ArrayList<PlanFieldDto>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", fieldVo.getStrategyId());
		list = tPrdPlanFormMapper.selectPlanField(map);
//		fieldVo.setInsureNo("");
//		fieldVo.setCate("pl");
//		fieldVo.setCategory("policy");
		for(int i=0;i<list.size();i++) {
			HttpResult res = buildPlanFields(list.get(i), fieldVo);
			if (res.getCode().equals("0"))
				return res;
		}
		
		
		//PlanFieldDto planFieldDto = new PlanFieldDto();
		// MOCK
		//planFieldDto.setBizId(1);
		//planFieldDto.setPlanId(fieldVo.getPlanId());
		//planFieldDto.setFormulaCode("WTN2050024");
		//planFieldDto.setStrategyId(fieldVo.getStrategyId());
		//FieldVo fieldVo = new FieldVo();
		//fieldVo.setPlanId(1);
		
//		fieldVo.setCategory("policy");
//		fieldVo.setInsureNo("");
//		fieldVo.setCate("pl");
//		HttpResult res = buildPlanFields(planFieldDto, fieldVo);
//		if (res.getCode().equals("0"))
//			return res;
//		list.add(planFieldDto);
//		PlanFieldDto planFieldDto1 = new PlanFieldDto();
//		planFieldDto1.setBizId(1);
//		planFieldDto1.setPlanId(fieldVo.getPlanId());
//		planFieldDto1.setFormulaCode("WTN2050025");
//		planFieldDto1.setStrategyId(fieldVo.getStrategyId());
//		list.add(planFieldDto1);
//		System.out.println(JSONObject.toJSONString(list));
		return HttpResult.success(list,1,"OK");
	}
	
	

	//元素组节点
	public HttpResult buildPlanFields(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		// fieldVo.getPlanId(),fieldVo.getCategory(),fieldVo.getInsureNo()
		HttpResult res = buildPlanElementGroups(planFieldDto, fieldVo);
		if (res.getCode().equals("0"))
			return res;
		return HttpResult.success(1, "OK");
	}

	//元素分组
	public HttpResult buildPlanElementGroups(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", planFieldDto.getPlanId());
		BizVo biz = tPrdPlanFormMapper.selectPlanCustomElement(map);
		if (biz.getCustomElement() == 1) {
			map.put("category", fieldVo.getCategory());
			map.put("bizType", "plan");
			map.put("bizId", planFieldDto.getPlanId());
		}
		if (biz.getCustomElement() != 1) {
			map.put("category", fieldVo.getCategory());
			map.put("bizType", "strategy");
			map.put("bizId", planFieldDto.getStrategyId());
		}
		List<PlanElementGroup> planElementGroupList = tPrdPlanFormMapper.selectPlanElementGroup(map);
		System.out.println(JSONObject.toJSONString(planElementGroupList));
		HttpResult res = buildPlanElements(planElementGroupList, fieldVo);
		if (res.getCode().equals("0"))
			return res;
		planFieldDto.setPlanElementGroup(planElementGroupList);
		return HttpResult.success(1, "OK");
	}

	//构建元素
	public HttpResult buildPlanElements(List<PlanElementGroup> planElementGroupList, FieldVo fieldVo) {
		for (PlanElementGroup planElementGroup : planElementGroupList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("groupId", planElementGroup.getId());
			List<PlanElement> planElementList = tPrdPlanFormMapper.selectPlanElement(map);
			System.out.println(planElementList.hashCode());
			HttpResult res = buildPlanElementEntry(planElementGroup, planElementList, fieldVo);
			if (res.getCode().equals("0"))
				return res;
			HttpResult res1 = buildPlanElementOptions(planElementGroup.getPlanElementList());
			if (res1.getCode().equals("0"))
				return res;
		}
		return HttpResult.success(1, "OK");
	}

	//元素录入
	public HttpResult buildPlanElementEntry(PlanElementGroup planElementGroup, List<PlanElement> planElementList,
			FieldVo fieldVo) {
		System.out.println(planElementList.hashCode());
		List<PlanElement> aa = new ArrayList<PlanElement>();
		for (PlanElement planElement : planElementList) {
			if (StringUtils.isNotBlank(fieldVo.getInsureNo())) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("elementId", planElement.getId());
				map.put("insureNo", fieldVo.getInsureNo());
				List<BizVo> bizVoList = tPrdPlanFormMapper.selectPlanElementValue(map);
				if (bizVoList == null)
					return HttpResult.error(0, "未查询到录入信息");
				else if (bizVoList.size() == 1) {
					planElement.setDefaultValue(bizVoList.get(0).getElementValue());
					planElement.setArrayIndex(bizVoList.get(0).getArrayIndex());
					//调整-2020/06/05 映射核心报文节点
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("elementCode", planElement.getCode());
					ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
					if (apiMapping != null)
						planElement.setSubmitName(apiMapping.getApiNodes());
					//调整-2020/06/05 针对不同功能关联节点
					map1.put("cate", fieldVo.getCate());
					List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
					planElement.setLinkElementCode(linkElementCode);
					aa.add(planElement);
				} else if (bizVoList.size() > 1) {
					for (int i = 0; i < bizVoList.size(); i++) {
						if (i == 0) {
							planElement.setDefaultValue(bizVoList.get(0).getElementValue());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("elementCode", planElement.getCode());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							planElement.setArrayIndex(bizVoList.get(0).getArrayIndex());
							//调整-2020/06/05 针对不同功能关联节点
							map1.put("cate", fieldVo.getCate());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
							planElement.setLinkElementCode(linkElementCode);
							aa.add(planElement);
						} else {
							String objectToJson = JSON.toJSONString(planElement);
							System.out.println(objectToJson);
							//将Json字符串反序列化为Java对象
							PlanElement planElement2 = JSON.parseObject(objectToJson, PlanElement.class);
							planElement2.setDefaultValue(bizVoList.get(i).getElementValue());
							planElement2.setArrayIndex(bizVoList.get(i).getArrayIndex());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("elementCode", planElement.getCode());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							//调整-2020/06/05 针对不同功能关联节点
							map1.put("cate", fieldVo.getCate());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
							planElement.setLinkElementCode(linkElementCode);
							aa.add(planElement2);
						}
					}
				}
			} else {
				//调整-2020/06/05 映射核心报文节点
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("elementCode", planElement.getCode());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
				if (apiMapping != null)
					planElement.setSubmitName(apiMapping.getApiNodes());
				//调整-2020/06/05 针对不同功能关联节点
				map1.put("cate", fieldVo.getCate());
				List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
				planElement.setLinkElementCode(linkElementCode);
				aa.add(planElement);
			}
		}
		//调整-2020/06/05 重构排序
		buildSort(aa);
		planElementGroup.setPlanElementList(aa);
		return HttpResult.success(1, "OK");
	}

	//排序
	public HttpResult buildSort(List<PlanElement> list) {
		// 排序
		Collections.sort(list, new Comparator<PlanElement>() {
			@Override
			public int compare(PlanElement o1, PlanElement o2) {
				int flag = ("" + o1.getArrayIndex()).compareTo("" + o2.getArrayIndex()); // 升降 o2.getName().compareTo(o1.getName())//降序
				if (flag == 0) {
					flag = o1.getSort() - o2.getSort();
				}
				return flag;
			}
		});
		return HttpResult.success(1, "OK");
	}

	//选择项
	public HttpResult buildPlanElementOptions(List<PlanElement> planElementList) {
		for (PlanElement planElement : planElementList) {
			if ("select".equals(planElement.getControlType()) || "checkBox".equals(planElement.getControlType())) {
				System.out.println(JSONObject.toJSONString(planElement));
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("elementId", planElement.getId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectPlanElementOption(map);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
		}
		return HttpResult.success(1, "OK");
	}

	/**产品详情
	复制投保单
	计划书
	*/
	@PostMapping("/getPlanElementDetail")
	public HttpResult getPlanElementDetail(@RequestBody PlanElementDetaiReq planElementDetaiReq) {
//		planElementDetaiReq.setStrategyId(2);
//		planElementDetaiReq.setCode("planDetail");
		PlanElementDetailDto planElementDetailDto = new PlanElementDetailDto();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		planElementDetailDto = tPrdPlanFormMapper.selectPlanStrategy(map);
		// 反推
		HttpResult res = buildBackReasoningFactor(planElementDetailDto, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		// 头部信息
		res = buildHeader(planElementDetailDto, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		// 底部信息
		res = buildBottom(planElementDetailDto, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		// 常见问题
		res = buildCommonProblem(planElementDetailDto, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		// 方案列表
		res = buildFormulaList(planElementDetailDto, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		return HttpResult.success(planElementDetailDto);
	}

	//反推
	public HttpResult buildBackReasoningFactor(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		
		if(StringUtils.isNotBlank(planElementDetailDto.getPlanReverseFactor())) {
			//# defect 获得反推要素
			List<BackReasoningFactor> backReasoningFactorList = new ArrayList<BackReasoningFactor>();
			BackReasoningFactor backReasoningFactor1 = new BackReasoningFactor();
			backReasoningFactor1.setFactor("insuredIsHaveShebao");
			backReasoningFactor1.setFactorValue("Y");
			backReasoningFactor1.setPlanId(1);
			BackReasoningFactor backReasoningFactor2 = new BackReasoningFactor();
			backReasoningFactor2.setFactor("insuredIsHaveShebao");
			backReasoningFactor2.setFactorValue("N");
			backReasoningFactor2.setPlanId(2);
			backReasoningFactorList.add(backReasoningFactor1);
			backReasoningFactorList.add(backReasoningFactor2);
			planElementDetailDto.setBackReasoningFactor(backReasoningFactorList);
		}
		
		return HttpResult.success(1, "OK");
	}

	//方案列表
	public HttpResult buildFormulaList(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		List<FormulaList> formulaList = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		formulaList = tPrdPlanFormMapper.selectPlanInfoFormulas(map);
		HttpResult res = buildFormulaDetail(formulaList);
		if (res.getCode().equals("0"))
			return res;
		planElementDetailDto.setFormulaList(formulaList);
		return HttpResult.success(1, "OK");
	}

	// 方案详情
	public HttpResult buildFormulaDetail(List<FormulaList> formulaList) {
		for (FormulaList formula : formulaList) {
			// 保障计划
			HttpResult res = buildSafeguardPlan(formula);
			if (res.getCode().equals("0"))
				return res;
			// 产品简介
			res = buildProductInfo(formula);
			if (res.getCode().equals("0"))
				return res;
			// 保障详情
			res = buildSafeguardDetail(formula);
			if (res.getCode().equals("0"))
				return res;
			// 理赔说明
			res = buildClaimStatement(formula);
			if (res.getCode().equals("0"))
				return res;
			// 条款须知
			res = buildTermsAndConditions(formula);
			if (res.getCode().equals("0"))
				return res;
			// 产品海报
			res = buildProductPoster(formula);
			if (res.getCode().equals("0"))
				return res;
			// 健康须知
			res = buildHealthNotice(formula);
			if (res.getCode().equals("0"))
				return res;
			formula.setNeedHealthNotice(true);
		}
		return HttpResult.success(1, "OK");
	}

	// 头部信息
	public HttpResult buildHeader(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		Header header = new Header();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		header = tPrdPlanFormMapper.selectPlanStrategyHeader(map);
		// 轮播图
		HttpResult res = buildSwipper(header, planElementDetailDto);
		if (res.getCode().equals("0"))
			return res;
		planElementDetailDto.setHeader(header);
		return HttpResult.success(1, "OK");
	}

	// 产品简介
	public HttpResult buildProductInfo(FormulaList formulaList) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setTitle("产品简介");
		productInfo.setDesc("花小钱，免大灾，承保货物运输过程中遭受的货物损失或招致的第三者经济赔偿责任");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "productIntroduction");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		productInfo.setContent(imgList);
		formulaList.setProductInfo(productInfo);
		return HttpResult.success(1, "OK");
	}

	// 保障详情
	public HttpResult buildSafeguardDetail(FormulaList formulaList) {
		SafeguardDetail safeguardDetail = new SafeguardDetail();
		safeguardDetail.setTitle("保障详情");
		safeguardDetail.setDesc("承保货物运输过程中遭受的货物损失或招致的第三者经济赔偿责任");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "securityDetails");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		safeguardDetail.setContent(imgList);
		formulaList.setSafeguardDetail(safeguardDetail);
		return HttpResult.success(1, "OK");
	}

	// 理赔说明
	public HttpResult buildClaimStatement(FormulaList formulaList) {
		ClaimStatement claimStatement = new ClaimStatement();
		claimStatement.setTitle("理赔说明");
		claimStatement.setDesc("全程指导，4步快速理赔");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "claimStatement");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		claimStatement.setContent(imgList);
		formulaList.setClaimStatement(claimStatement);
		return HttpResult.success(1, "OK");
	}

	// 常见问题
	public HttpResult buildCommonProblem(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		CommonProblem commonProblem = new CommonProblem();
		commonProblem.setTitle("常见问题");
		commonProblem.setDesc("");
		// 常见问题内容
		HttpResult res = buildCommonProblemContent(commonProblem, planElementDetaiReq);
		if (res.getCode().equals("0"))
			return res;
		planElementDetailDto.setCommonProblem(commonProblem);
		return HttpResult.success(1, "OK");
	}

	// 条款须知
	public HttpResult buildTermsAndConditions(FormulaList formulaList) {
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		termsAndConditions.setTitle("条款须知");
		termsAndConditions.setDesc("");
		// 条款须知内容
		buildTermsAndConditionsContent(termsAndConditions);
		formulaList.setTermsAndConditions(termsAndConditions);
		return HttpResult.success(1, "OK");
	}

	// 底部按钮
	public HttpResult buildBottom(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		PlanButton planButton = buttonShow(planElementDetaiReq);
		List<Bottom> bottomList = new ArrayList<Bottom>();
		Bottom bottom1 = new Bottom();
		bottom1.setId(1);
		bottom1.setType("text");
		bottom1.setDesc("保费(元)");
		bottomList.add(bottom1);
		if (planElementDetaiReq.getCode().equals("planDetail")
				&& (1 == planButton.getPoster() || 1 == planButton.getProposal())) {
			Bottom bottom2 = new Bottom();
			bottom2.setId(2);
			bottom2.setType("button");
			bottom2.setDesc("展业夹");
			List<PbBox> pbBoxList = new ArrayList<PbBox>();
			if (1 == planButton.getPoster()) {
				PbBox pbBo1 = new PbBox();
				pbBo1.setCode("poster");
				pbBo1.setName("去分享海报");
				pbBo1.setLinkUrl("http://mobilesales-supsale-test.caas.ccic-test.com.cn/assets/img/r.png");
				pbBoxList.add(pbBo1);
			}
			if (1 == planButton.getProposal()) {
				PbBox pbBo2 = new PbBox();
				pbBo2.setCode("prospectus");
				pbBo2.setName("去制作计划书");
				pbBo2.setLinkUrl("http://mobilesales-supsale-test.caas.ccic-test.com.cn/assets/img/r.png");
				pbBoxList.add(pbBo2);
			}
			bottom2.setPbBox(pbBoxList);
			bottomList.add(bottom2);
		}
		if (planElementDetaiReq.getCode().equals("planDetail")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType("button");
			bottom3.setDesc("我要投保");
			bottomList.add(bottom3);
		} else if (planElementDetaiReq.getCode().equals("planBook")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType("button");
			bottom3.setDesc("生成计划书");
			bottomList.add(bottom3);
		}
		planElementDetailDto.setBottom(bottomList);
		return HttpResult.success(1, "OK");
	}

	// 产品海报
	public HttpResult buildProductPoster(FormulaList formulaList) {
		ProductPoster productPoster = new ProductPoster();
		productPoster.setTitle("产品海报");
		List<ImgList> imgList = new ArrayList<ImgList>();
		ImgList imgList1 = new ImgList();
		imgList1.setId(1);
		imgList1.setImg("img.png");
		ImgList imgList2 = new ImgList();
		imgList2.setId(2);
		imgList2.setImg("img.png");
		imgList.add(imgList1);
		imgList.add(imgList2);
		productPoster.setImgList(imgList);
		List<SharePlatform> sharePlatformList = new ArrayList<SharePlatform>();
		SharePlatform sharePlatform1 = new SharePlatform();
		sharePlatform1.setId(0);
		sharePlatform1.setValue("微信好友");
		SharePlatform sharePlatform2 = new SharePlatform();
		sharePlatform2.setId(1);
		sharePlatform2.setValue("微信朋友圈");
		SharePlatform sharePlatform3 = new SharePlatform();
		sharePlatform3.setId(2);
		sharePlatform3.setValue("下载");
		sharePlatformList.add(sharePlatform1);
		sharePlatformList.add(sharePlatform2);
		sharePlatformList.add(sharePlatform3);
		productPoster.setSharePlatform(sharePlatformList);
		formulaList.setProductPoster(productPoster);
		return HttpResult.success(1, "OK");
	}

	// 健康须知
	public HttpResult buildHealthNotice(FormulaList formulaList) {
		HealthNotice healthNotice = new HealthNotice();
		healthNotice.setTitle("健康须知");
		healthNotice.setUrl("健康及职业告知.pdf");
		healthNotice.setContent("本人确认以上填写的各项内容均真实，未发生健康与职业声明中所有情形；日后如发现有不实告知，保险公司有权按《保险法》等有关法律规定解除或部分解除保险合同。");
		formulaList.setHealthNotice(healthNotice);
		return HttpResult.success(1, "OK");
	}

	// 条款须知内容
	public HttpResult buildTermsAndConditionsContent(TermsAndConditions termsAndConditions) {
		List<TermsAndConditionsContent> contentList = new ArrayList<TermsAndConditionsContent>();
		TermsAndConditionsContent content = new TermsAndConditionsContent();
		content.setId(1);
		content.setTitle("保险条款");
		content.setLink("");
		content.setType("list");
		List<TermsAndConditionsContent> list = new ArrayList<TermsAndConditionsContent>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		list = tPrdPlanFormMapper.selectConditionsContent(map);
		content.setChlidren(list);
		TermsAndConditionsContent content2 = new TermsAndConditionsContent();
		content2.setId(2);
		content2.setTitle("投保须知");
		content2.setLink("XXX.pdf");
		content2.setType("link");
		TermsAndConditionsContent content3 = new TermsAndConditionsContent();
		content3.setId(3);
		content3.setTitle("投保声明");
		content3.setLink("XXX.pdf");
		content3.setType("link");
		TermsAndConditionsContent content4 = new TermsAndConditionsContent();
		content4.setId(4);
		content4.setTitle("特别声明");
		content4.setLink("XXX.pdf");
		content4.setType("link");
		TermsAndConditionsContent content5 = new TermsAndConditionsContent();
		content5.setId(5);
		content5.setTitle("责任免除");
		content5.setLink("XXX.pdf");
		content5.setType("link");
		contentList.add(content);
		contentList.add(content2);
		contentList.add(content3);
		contentList.add(content4);
		contentList.add(content5);
		termsAndConditions.setContent(contentList);
		return HttpResult.success(1, "OK");
	}

	// 常见问题内容
	public HttpResult buildCommonProblemContent(CommonProblem commonProblem, PlanElementDetaiReq planElementDetaiReq) {
		List<CommonProblemContent> contentList = new ArrayList<CommonProblemContent>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		contentList = tPrdPlanFormMapper.selectCommonProblemContent(map);
		commonProblem.setContent(contentList);
		return HttpResult.success(1, "OK");
	}

	// 轮播图
	public HttpResult buildSwipper(Header header, PlanElementDetailDto planElementDetailDto) {
		List<Swipper> swipperList = new ArrayList<Swipper>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		swipperList = tPrdPlanFormMapper.selectHeaderSwipper(map);
		header.setSwipper(swipperList);
		return HttpResult.success(1, "OK");
	}

	// 保障计划
	public HttpResult buildSafeguardPlan(FormulaList formulaList) {
		SafeguardPlan safeguardPlan = new SafeguardPlan();
		safeguardPlan.setTitle("保障计划");
		safeguardPlan.setDesc("");
		// 保障计划
		HttpResult res = buildFormulaObj(safeguardPlan, formulaList);
		if (res.getCode().equals("0"))
			return res;
		// 关系人信息
		res = buildRelatedPersonInfo(safeguardPlan, formulaList, "compute");
		if (res.getCode().equals("0"))
			return res;
		formulaList.setSafeguardPlan(safeguardPlan);
		return HttpResult.success(1, "OK");
	}

	// 保障计划
	public HttpResult buildFormulaObj(SafeguardPlan safeguardPlan, FormulaList formulaList) {
		FormulaObj formulaObj = new FormulaObj();
		// 主条款
		HttpResult res = buildMainInsurance(formulaObj, formulaList);
		if (res.getCode().equals("0"))
			return res;
		// 附加条款
		res = buildSubsidiaryInsurance(formulaObj, formulaList);
		if (res.getCode().equals("0"))
			return res;
		safeguardPlan.setFormulaObj(formulaObj);
		return HttpResult.success(1, "OK");
	}

	// 关系人信息
	public HttpResult buildRelatedPersonInfo(SafeguardPlan safeguardPlan, FormulaList formulaList, String code) {
		RelatedPersonInfo relatedPersonInfo = new RelatedPersonInfo();
		// #defect 产品详情与计划书差异化展示
		// ...
		// 动态表单获取
		PlanFieldDto planFieldDto = new PlanFieldDto();
		planFieldDto.setStrategyId(formulaList.getStrategyId());
		FieldVo fieldVo = new FieldVo();
		fieldVo.setPlanId(formulaList.getPlanId());
		fieldVo.setCategory(code);
		fieldVo.setCate("ss");
		//fieldVo.setInsureNo("TWTI20340100100000000002");
		HttpResult res = buildPlanFields(planFieldDto, fieldVo);
		if (res.getCode().equals("0"))
			return res;
		// 优先取plan里的元素，plan没有去销售方案下的元素 标识 plan_info custom_element
		relatedPersonInfo.setForm(planFieldDto.getPlanElementGroup());
		safeguardPlan.setRelatedPersonInfo(relatedPersonInfo);
		return HttpResult.success(1, "OK");
	}

	// 主条款
	public HttpResult buildMainInsurance(FormulaObj formulaObj, FormulaList formulaList) {
		List<Insurance> mainInsuranceList = new ArrayList<Insurance>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("property", 1);
		map.put("planId", formulaList.getPlanId());
		mainInsuranceList = tPrdPlanFormMapper.selectPlanCt(map);
		for (int i = 0; i < mainInsuranceList.size(); i++) {
			mainInsuranceList.get(i).setId(0);
			mainInsuranceList.get(i).setInsuranceType("1");
			mainInsuranceList.get(i).setAmount(3000000.00);
			mainInsuranceList.get(i).setUnit("元/人");
			mainInsuranceList.get(i).setType("collage");
			mainInsuranceList.get(i).setDefaultValue("1");
			mainInsuranceList.get(i).setRequired(true);
			mainInsuranceList.get(i).setDesc("住院医疗、特殊门诊医疗、门诊手术医疗、住院前30天/出院后30天门急诊医疗费用");
			// 定额定费 限额限费 标示 ct fixed_insure
			if (mainInsuranceList.get(i).getFixedInsure() != 1) {
				HttpResult res = buildRates(mainInsuranceList.get(i), formulaList);
				if (res.getCode().equals("0"))
					return res;
			}
		}
		formulaObj.setMainInsurance(mainInsuranceList);
		return HttpResult.success(1, "OK");
	}

	// 附加条款
	public HttpResult buildSubsidiaryInsurance(FormulaObj formulaObj, FormulaList formulaList) {
		List<Insurance> subsidiaryInsuranceList = new ArrayList<Insurance>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("property", 2);
		map.put("planId", formulaList.getPlanId());
		subsidiaryInsuranceList = tPrdPlanFormMapper.selectPlanCt(map);
		for (int i = 0; i < subsidiaryInsuranceList.size(); i++) {
			subsidiaryInsuranceList.get(i).setInsuranceType("0");
			subsidiaryInsuranceList.get(i).setId(0);
			subsidiaryInsuranceList.get(i).setAmount(3000000.00);
			subsidiaryInsuranceList.get(i).setDesc("二级以上(含)公立医院特需部或VIP部(不包含观察室、联合病房和康复病房)治疗恶性肿瘤而发生的合理费用");
			subsidiaryInsuranceList.get(i).setType("checkBox");
			subsidiaryInsuranceList.get(i).setDefaultValue("0");
			subsidiaryInsuranceList.get(i).setRequired(true);
			// 选项值
			HttpResult res = buildValueList(subsidiaryInsuranceList.get(i));
			if (res.getCode().equals("0"))
				return res;
			if (subsidiaryInsuranceList.get(i).getFixedInsure() != 1) {
				// 费率
				res = buildRates(subsidiaryInsuranceList.get(i), formulaList);
				if (res.getCode().equals("0"))
					return res;
			}
		}
		formulaObj.setSubsidiaryInsurance(subsidiaryInsuranceList);
		return HttpResult.success(1, "OK");
	}

	// 选项值
	public HttpResult buildValueList(Insurance insurance) {
		List<ValueList> valueList = new ArrayList<ValueList>();
		ValueList valueList1 = new ValueList();
		valueList1.setId(1);
		valueList1.setValue(true);
		ValueList valueList2 = new ValueList();
		valueList2.setId(0);
		valueList2.setValue(false);
		valueList.add(valueList1);
		valueList.add(valueList2);
		insurance.setValueList(valueList);
		return HttpResult.success(1, "OK");
	}

	// 费率
	public HttpResult buildRates(Insurance insurance, FormulaList formulaList) {
		List<RateParams> rateParamsList = new ArrayList<RateParams>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		rateParamsList = tPrdPlanFormMapper.selectPlanRate(map);
		List<Rates> ratesList = new ArrayList<Rates>();
		for (int i = 0; i < rateParamsList.size(); i++) {
			Rates rates = new Rates();
			rates.setKey(rateParamsList.get(i).getRateAgeMin() + "-" + rateParamsList.get(i).getRateAgeMax() + "_"
					+ rateParamsList.get(i).getIsHaveSheBao());
			rates.setPremium(rateParamsList.get(i).getPremium());
			ratesList.add(rates);
		}
		insurance.setRates(ratesList);
		return HttpResult.success(1, "OK");
	}

	//底部按钮
	public PlanButton buttonShow(PlanElementDetaiReq planElementDetaiReq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		PlanButton planButton = tPrdPlanFormMapper.selectButton(map);
		return planButton;
	}
}
