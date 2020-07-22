package com.example.tlyanprd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping(value = "planForm")
public class PlanFormCtl {

	@Autowired
	TPrdPlanFormMapper tPrdPlanFormMapper;
	
	//动态表单
	@PostMapping("/getPlanDynamicForms")
	public HttpResult<?> getPlanDynamicForms(@RequestBody FieldVo fieldVo) {
		List<PlanFieldDto> list = new ArrayList<PlanFieldDto>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", fieldVo.getStrategyId());
		list = tPrdPlanFormMapper.selectPlanField(map);
		for(PlanFieldDto planFieldDto:list) {
			HttpResult<?> res = buildPlanFields(planFieldDto, fieldVo);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		}
		return HttpResult.success(list,1,"成功");
	}

	//元素组节点
	public HttpResult<?> buildPlanFields(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		HttpResult<?> res = buildPlanElementGroups(planFieldDto, fieldVo);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		return HttpResult.success(1, "成功");
	}

	//元素分组
	public HttpResult<?> buildPlanElementGroups(PlanFieldDto planFieldDto, FieldVo fieldVo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", planFieldDto.getPlanId());
		BizVo biz = tPrdPlanFormMapper.selectPlanCustomElement(map);
		if (judgeCustomElement(biz,p->biz.getCustomElement()==1)){
			map.put("category", fieldVo.getCategory());
			map.put("bizType", "plan");
			map.put("bizId", planFieldDto.getPlanId());
		}
		if (judgeCustomElement(biz,p->biz.getCustomElement()!=1)){
			map.put("category", fieldVo.getCategory());
			map.put("bizType", "strategy");
			map.put("bizId", planFieldDto.getStrategyId());
		}
		List<PlanElementGroup> planElementGroupList = tPrdPlanFormMapper.selectPlanElementGroup(map);
		HttpResult<?> res = buildPlanElements(planElementGroupList, fieldVo);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planFieldDto.setPlanElementGroup(planElementGroupList);
		return HttpResult.success(1, "成功");
	}
	
	public boolean judgeCustomElement(BizVo bizVo,Predicate<BizVo> p1) {
	        return p1.test(bizVo);
	}
	 
	//构建元素
	public HttpResult<?> buildPlanElements(List<PlanElementGroup> planElementGroupList, FieldVo fieldVo) {
		for (PlanElementGroup planElementGroup : planElementGroupList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("groupId", planElementGroup.getGroupId());
			List<PlanElement> planElementList = tPrdPlanFormMapper.selectPlanElement(map);
			HttpResult<?> res = buildPlanElementEntry(planElementGroup, planElementList, fieldVo);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			HttpResult<?> res1 = buildPlanElementOptions(planElementGroup.getPlanElementList());
			if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		}
		return HttpResult.success(1, "成功");
	}

	//元素录入
	public HttpResult<?> buildPlanElementEntry(PlanElementGroup planElementGroup, List<PlanElement> planElementList,
			FieldVo fieldVo) {
		List<PlanElement> planElementCopy = new ArrayList<PlanElement>();
		for (PlanElement planElement : planElementList) {
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("elementCode",planElement.getCode());
			map2.put("groupId", planElementGroup.getGroupId());
			PlanTrialFlag planTrialFlag = tPrdPlanFormMapper.selectPlanTrialFlag(map2);
			if(planTrialFlag!=null) planElement.setTrialFlag(1);
			
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
					map1.put("groupId", planElementGroup.getGroupId());
					ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
					if (apiMapping != null)
						planElement.setSubmitName(apiMapping.getApiNodes());
					//调整-2020/06/05 针对不同功能关联节点
					map1.put("cate", fieldVo.getCate());
					List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
					planElement.setLinkElementCode(linkElementCode);
					planElementCopy.add(planElement);
				} else if (bizVoList.size() > 1) {
					for (int i = 0; i < bizVoList.size(); i++) {
						if (i == 0) {
							planElement.setDefaultValue(bizVoList.get(0).getElementValue());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("elementCode", planElement.getCode());
							map1.put("groupId", planElementGroup.getGroupId());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							planElement.setArrayIndex(bizVoList.get(0).getArrayIndex());
							//调整-2020/06/05 针对不同功能关联节点
							map1.put("cate", fieldVo.getCate());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
							planElement.setLinkElementCode(linkElementCode);
							planElementCopy.add(planElement);
						} else {
							String objectToJson = JSON.toJSONString(planElement);
							//将Json字符串反序列化为Java对象
							PlanElement planElement2 = JSON.parseObject(objectToJson, PlanElement.class);
							planElement2.setDefaultValue(bizVoList.get(i).getElementValue());
							planElement2.setArrayIndex(bizVoList.get(i).getArrayIndex());
							//调整-2020/06/05 映射核心报文节点
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							map1.put("elementCode", planElement.getCode());
							map1.put("groupId", planElementGroup.getGroupId());
							ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
							if (apiMapping != null)
								planElement.setSubmitName(apiMapping.getApiNodes());
							//调整-2020/06/05 针对不同功能关联节点
							map1.put("cate", fieldVo.getCate());
							List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
							planElement.setLinkElementCode(linkElementCode);
							planElementCopy.add(planElement2);
						}
					}
				}
			} else {
				//调整-2020/06/05 映射核心报文节点
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("elementCode", planElement.getCode());
				map1.put("groupId", planElementGroup.getGroupId());
				ApiMapping apiMapping = tPrdPlanFormMapper.selectPlanElementApiMapping(map1);
				if (apiMapping != null)
					planElement.setSubmitName(apiMapping.getApiNodes());
				//调整-2020/06/05 针对不同功能关联节点
				map1.put("cate", fieldVo.getCate());
				List<LinkElementCode> linkElementCode = tPrdPlanFormMapper.selectPlanCodeLink(map1);
				planElement.setLinkElementCode(linkElementCode);
				planElementCopy.add(planElement);
			}
		}
		//调整-2020/06/05 重构排序
		buildSort(planElementCopy);
		planElementGroup.setPlanElementList(planElementCopy);
		return HttpResult.success(1, "成功");
	}

	//排序
	public HttpResult<?> buildSort(List<PlanElement> list) {
		// 排序
		Collections.sort(list, new Comparator<PlanElement>() {
			@Override
			public int compare(PlanElement o1, PlanElement o2) {
				int flag = ("" + o1.getArrayIndex()).compareTo("" + o2.getArrayIndex()); //升降 o2.getName().compareTo(o1.getName())//降序
				if (flag == 0) {
					flag = o1.getSort() - o2.getSort();
				}
				return flag;
			}
		});
		return HttpResult.success(1, "成功");
	}

	//选择项
	public HttpResult<?> buildPlanElementOptions(List<PlanElement> planElementList) {
		for (PlanElement planElement : planElementList) {
			if ("select".equals(planElement.getControlType()) || "checkBox".equals(planElement.getControlType())) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("elementId", planElement.getId());
				List<PlanElementOption> planElementOptionList = tPrdPlanFormMapper.selectPlanElementOption(map);
				planElement.setPlanElementOptionList(planElementOptionList);
			}
		}
		return HttpResult.success(1, "成功");
	}

	/**产品详情
	复制投保单
	计划书
	*/
	@PostMapping("/getPlanElementDetail")
	public HttpResult<?> getPlanElementDetail(@RequestBody PlanElementDetaiReq planElementDetaiReq) {
		PlanElementDetailDto planElementDetailDto = new PlanElementDetailDto();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		planElementDetailDto = tPrdPlanFormMapper.selectPlanStrategy(map);
		// 反推
		HttpResult<?> res = buildBackReasoningFactor(planElementDetailDto, planElementDetaiReq);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		// 头部信息
		HttpResult<?> res1 = buildHeader(planElementDetailDto, planElementDetaiReq);
		if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		// 底部信息
		HttpResult<?> res2 = buildBottom(planElementDetailDto, planElementDetaiReq);
		if(judgeHttpResult(res2,p->"0".equals(res2.getCode()))) return res2;
		// 常见问题
		HttpResult<?> res3 = buildCommonProblem(planElementDetailDto, planElementDetaiReq);
		if(judgeHttpResult(res3,p->"0".equals(res3.getCode()))) return res3;
		// 方案列表
		HttpResult<?> res4 = buildFormulaList(planElementDetailDto, planElementDetaiReq);
		if(judgeHttpResult(res4,p->"0".equals(res4.getCode()))) return res4;
		return HttpResult.success(planElementDetailDto);
	}

	//反推
	public HttpResult<?> buildBackReasoningFactor(PlanElementDetailDto planElementDetailDto,
		PlanElementDetaiReq planElementDetaiReq) {
		if(StringUtils.isNotBlank(planElementDetailDto.getPlanReverseFactor())) {
			//获得反推要素
			List<BackReasoningFactor> backReasoningFactorList = new ArrayList<BackReasoningFactor>();
			BackReasoningFactor backReasoningFactor1 = new BackReasoningFactor();
			backReasoningFactor1.setFactor(planElementDetailDto.getPlanReverseFactor());
			backReasoningFactor1.setFactorValue("1");
			//defect 根据Value+Code去匹配所有方案ID
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("strategyId", planElementDetailDto.getStrategyId());
			map.put("sheBaoType", 1);
			PlanBackReasoningFactor planBackReasoningFactor = tPrdPlanFormMapper.selectPlanBackReasoningFactor(map);
			if(planBackReasoningFactor!=null) backReasoningFactor1.setPlanId(planBackReasoningFactor.getPlanId());
			BackReasoningFactor backReasoningFactor2 = new BackReasoningFactor();
			backReasoningFactor2.setFactor(planElementDetailDto.getPlanReverseFactor());
			backReasoningFactor2.setFactorValue("0");
			map.put("sheBaoType", 0);
			planBackReasoningFactor = tPrdPlanFormMapper.selectPlanBackReasoningFactor(map);
			if(planBackReasoningFactor!=null) backReasoningFactor2.setPlanId(planBackReasoningFactor.getPlanId());
			backReasoningFactorList.add(backReasoningFactor1);
			backReasoningFactorList.add(backReasoningFactor2);
			planElementDetailDto.setBackReasoningFactor(backReasoningFactorList);
		}
		return HttpResult.success(1, "成功");
	}

	//方案列表
	public HttpResult<?> buildFormulaList(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		List<FormulaList> formulaList = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		formulaList = tPrdPlanFormMapper.selectPlanInfoFormulas(map);
		HttpResult<?> res = buildFormulaDetail(formulaList,planElementDetailDto,planElementDetaiReq);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setFormulaList(formulaList);
		return HttpResult.success(1, "成功");
	}

	//方案详情
	public HttpResult<?> buildFormulaDetail(List<FormulaList> formulaList,PlanElementDetailDto planElementDetailDto,PlanElementDetaiReq planElementDetaiReq) {
		for (FormulaList formula : formulaList) {
			//保障计划
			HttpResult<?> res = buildSafeguardPlan(formula,planElementDetaiReq);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			//产品简介
			HttpResult<?> res1 = buildProductInfo(formula,planElementDetaiReq);
			if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
			//保障详情
			HttpResult<?> res2 = buildSafeguardDetail(formula,planElementDetaiReq);
			if(judgeHttpResult(res2,p->"0".equals(res2.getCode()))) return res2;
			//理赔说明
			HttpResult<?> res3 = buildClaimStatement(formula,planElementDetaiReq);
			if(judgeHttpResult(res3,p->"0".equals(res3.getCode()))) return res3;
			//条款须知
			HttpResult<?> res4 = buildTermsAndConditions(formula,planElementDetaiReq);
			if(judgeHttpResult(res4,p->"0".equals(res4.getCode()))) return res4;
			//产品海报
			HttpResult<?> res5 = buildProductPoster(formula,planElementDetailDto,planElementDetaiReq);
			if(judgeHttpResult(res5,p->"0".equals(res5.getCode()))) return res5;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("attachType", "actoion_inform");
			map.put("planId", formula.getPlanId());
			map.put("attachName", "《健康告知》");
			List<ImgList> healthList = tPrdPlanFormMapper.selectPlanAttachealth(map);
			if(healthList!=null) {
				formula.setNeedHealthNotice(true);
				//健康须知
				HttpResult<?> res6 = buildHealthNotice(formula,healthList);
				if(judgeHttpResult(res6,p->"0".equals(res6.getCode()))) return res6;
			}
			else {
				formula.setNeedHealthNotice(false);
			}
		}
		return HttpResult.success(1, "成功");
	}

	//头部信息
	public HttpResult<?> buildHeader(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		Header header = new Header();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		header = tPrdPlanFormMapper.selectPlanStrategyHeader(map);
		//轮播图
		HttpResult<?> res = buildSwipper(header, planElementDetailDto);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setHeader(header);
		return HttpResult.success(1, "成功");
	}

	//产品简介
	public HttpResult<?> buildProductInfo(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		ProductInfo productInfo = new ProductInfo();
		productInfo.setTitle("产品简介");
		productInfo.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "productIntroduction");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		productInfo.setContent(imgList);
		formulaList.setProductInfo(productInfo);
		return HttpResult.success(1, "成功");
	}

	//保障详情
	public HttpResult<?> buildSafeguardDetail(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		SafeguardDetail safeguardDetail = new SafeguardDetail();
		safeguardDetail.setTitle("保障详情");
		safeguardDetail.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "securityDetails");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		safeguardDetail.setContent(imgList);
		formulaList.setSafeguardDetail(safeguardDetail);
		return HttpResult.success(1, "成功");
	}

	//理赔说明
	public HttpResult<?> buildClaimStatement(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		ClaimStatement claimStatement = new ClaimStatement();
		claimStatement.setTitle("理赔说明");
		claimStatement.setDesc("");
		List<ImgList> imgList = new ArrayList<ImgList>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		map.put("attachType", "claimStatement");
		imgList = tPrdPlanFormMapper.selectPlanStrategyAttach(map);
		claimStatement.setContent(imgList);
		formulaList.setClaimStatement(claimStatement);
		return HttpResult.success(1, "成功");
	}

	//常见问题
	public HttpResult<?> buildCommonProblem(PlanElementDetailDto planElementDetailDto,
		PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		CommonProblem commonProblem = new CommonProblem();
		commonProblem.setTitle("常见问题");
		commonProblem.setDesc("");
		// 常见问题内容
		HttpResult<?> res = buildCommonProblemContent(commonProblem, planElementDetaiReq);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		planElementDetailDto.setCommonProblem(commonProblem);
		return HttpResult.success(1, "成功");
	}

	//条款须知
	public HttpResult<?> buildTermsAndConditions(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		TermsAndConditions termsAndConditions = new TermsAndConditions();
		termsAndConditions.setTitle("条款须知");
		termsAndConditions.setDesc("");
		// 条款须知内容
		buildTermsAndConditionsContent(termsAndConditions,formulaList);
		formulaList.setTermsAndConditions(termsAndConditions);
		return HttpResult.success(1, "成功");
	}
	
	//展业夹图:第一张轮播图
	public String buildZyjImg(PlanElementDetailDto planElementDetailDto) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		Header header = tPrdPlanFormMapper.selectPlanStrategyHeader(map);
		if(header!=null) {
			List<Swipper> swipperList = new ArrayList<Swipper>();
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("strategyId", planElementDetailDto.getStrategyId());
			swipperList = tPrdPlanFormMapper.selectHeaderSwipper(map2);
			return swipperList.get(0).getValue();
		}
		return null;
	}

	//底部按钮
	public HttpResult<?> buildBottom(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq) {
		PlanButton planButton = buttonShow(planElementDetaiReq);
		List<Bottom> bottomList = new ArrayList<Bottom>();
		Bottom bottom1 = new Bottom();
		bottom1.setId(1);
		bottom1.setType("text");
		bottom1.setDesc("保费(元)");
		bottomList.add(bottom1);
		if (planElementDetaiReq.getCode().equals("compute")
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
				String img = buildZyjImg(planElementDetailDto);
				if(img!=null) pbBo1.setImgUrl(img);	
				pbBoxList.add(pbBo1);
			}
			if (1 == planButton.getProposal()) {
				PbBox pbBo2 = new PbBox();
				pbBo2.setCode("prospectus");
				pbBo2.setName("去制作计划书");
				String img = buildZyjImg(planElementDetailDto);
				if(img!=null) pbBo2.setImgUrl(img);	
				pbBoxList.add(pbBo2);
			}
			bottom2.setPbBox(pbBoxList);
			bottomList.add(bottom2);
		}
		if (planElementDetaiReq.getCode().equals("compute")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType("button");
			bottom3.setDesc("我要投保");
			bottomList.add(bottom3);
		} else if (planElementDetaiReq.getCode().equals("prospectus")) {
			Bottom bottom3 = new Bottom();
			bottom3.setId(3);
			bottom3.setType("button");
			bottom3.setDesc("生成计划书");
			bottomList.add(bottom3);
		}
		planElementDetailDto.setBottom(bottomList);
		return HttpResult.success(1, "成功");
	}

	//产品海报
	public HttpResult<?> buildProductPoster(FormulaList formulaList,PlanElementDetailDto planElementDetailDto,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		ProductPoster productPoster = new ProductPoster();
		productPoster.setTitle("产品海报");
		List<ImgList> imgList = new ArrayList<ImgList>();
		ImgList imgList1 = new ImgList();
		imgList1.setId(1);
		String img = buildZyjImg(planElementDetailDto);
		if(img!=null) imgList1.setImg(img);
		ImgList imgList2 = new ImgList();
		imgList2.setId(2);
		if(img!=null) imgList2.setImg(img);
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
		return HttpResult.success(1, "成功");
	}

	//健康须知
	public HttpResult<?> buildHealthNotice(FormulaList formulaList,List<ImgList> healthList) {
		for(ImgList health:healthList) {
			HealthNotice healthNotice = new HealthNotice();
			healthNotice.setTitle("《健康告知》");
			healthNotice.setUrl(health.getImg());
			healthNotice.setContent("");
			formulaList.setHealthNotice(healthNotice);
		}
		return HttpResult.success(1, "成功");
	}

	//条款须知内容
	public HttpResult<?> buildTermsAndConditionsContent(TermsAndConditions termsAndConditions,FormulaList formulaList) {
		// defect 产品详情与计划书差异化展示
		List<TermsAndConditionsContent> contentList = new ArrayList<TermsAndConditionsContent>();
		TermsAndConditionsContent content = new TermsAndConditionsContent();
		content.setId(1);
		content.setTitle("保险条款");
		content.setLink("");
		List<TermsAndConditionsContent> list = new ArrayList<TermsAndConditionsContent>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("planId", formulaList.getPlanId());
		list = tPrdPlanFormMapper.selectConditionsContent(map);
		content.setChlidren(list);
		TermsAndConditionsContent content2 = new TermsAndConditionsContent();
		content2.setId(2);
		content2.setTitle("投保须知");
		content2.setLink("planid_s3.pdf");
		TermsAndConditionsContent content3 = new TermsAndConditionsContent();
		content3.setId(3);
		content3.setTitle("投保声明");
		content3.setLink("planid_s3.pdf");
		TermsAndConditionsContent content4 = new TermsAndConditionsContent();
		content4.setId(4);
		content4.setTitle("特别声明");
		content4.setLink("planid_s3.pdf");
		TermsAndConditionsContent content5 = new TermsAndConditionsContent();
		content5.setId(5);
		content5.setTitle("责任免除");
		content5.setLink("planid_s3.pdf");
		contentList.add(content);
		contentList.add(content2);
		contentList.add(content3);
		contentList.add(content4);
		contentList.add(content5);
		termsAndConditions.setContent(contentList);
		return HttpResult.success(1, "成功");
	}

	//常见问题内容
	public HttpResult<?> buildCommonProblemContent(CommonProblem commonProblem, PlanElementDetaiReq planElementDetaiReq) {
		List<CommonProblemContent> contentList = new ArrayList<CommonProblemContent>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		contentList = tPrdPlanFormMapper.selectCommonProblemContent(map);
		commonProblem.setContent(contentList);
		return HttpResult.success(1, "成功");
	}

	//轮播图
	public HttpResult<?> buildSwipper(Header header, PlanElementDetailDto planElementDetailDto) {
		List<Swipper> swipperList = new ArrayList<Swipper>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetailDto.getStrategyId());
		swipperList = tPrdPlanFormMapper.selectHeaderSwipper(map);
		header.setSwipper(swipperList);
		return HttpResult.success(1, "成功");
	}

	//保障计划
	public HttpResult<?> buildSafeguardPlan(FormulaList formulaList,PlanElementDetaiReq planElementDetaiReq) {
		//详情与计划书一致
		SafeguardPlan safeguardPlan = new SafeguardPlan();
		safeguardPlan.setTitle("保障计划");
		safeguardPlan.setDesc("");
		//保障计划
		HttpResult<?> res = buildFormulaObj(safeguardPlan, formulaList);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		//关系人信息
		HttpResult<?> res1 = buildRelatedPersonInfo(safeguardPlan,planElementDetaiReq, formulaList);
		if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		formulaList.setSafeguardPlan(safeguardPlan);
		return HttpResult.success(1, "成功");
	}

	//保障计划
	public HttpResult<?> buildFormulaObj(SafeguardPlan safeguardPlan, FormulaList formulaList) {
		FormulaObj formulaObj = new FormulaObj();
		//主条款
		HttpResult<?> res = buildMainInsurance(formulaObj, formulaList);
		if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
		//附加条款
		HttpResult<?> res1 = buildSubsidiaryInsurance(formulaObj, formulaList);
		if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
		safeguardPlan.setFormulaObj(formulaObj);
		return HttpResult.success(1, "成功");
	}

	//关系人信息
	public HttpResult<?> buildRelatedPersonInfo(SafeguardPlan safeguardPlan,PlanElementDetaiReq planElementDetaiReq, FormulaList formulaList) {
		RelatedPersonInfo relatedPersonInfo = new RelatedPersonInfo();
//		if("compute".equals(planElementDetaiReq.getCode())) {
			// defect 产品详情与计划书差异化展示
			// ...
			// 动态表单获取
			PlanFieldDto planFieldDto = new PlanFieldDto();
			planFieldDto.setStrategyId(formulaList.getStrategyId());
			planFieldDto.setPlanId(formulaList.getPlanId());
			FieldVo fieldVo = new FieldVo();
			fieldVo.setPlanId(formulaList.getPlanId());
			fieldVo.setCategory(planElementDetaiReq.getCode());
			fieldVo.setCate("ss");
			//fieldVo.setInsureNo("TWTI20340100100000000002");
			HttpResult<?> res = buildPlanFields(planFieldDto, fieldVo);
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			//优先取plan里的元素，plan没有去销售方案下的元素 标识 plan_info custom_element
			relatedPersonInfo.setForm(planFieldDto.getPlanElementGroup());
			safeguardPlan.setRelatedPersonInfo(relatedPersonInfo);
//		}
//		else if("prospectus".equals(planElementDetaiReq.getCode())) {
//			relatedPersonInfo.setForm(null);
//			safeguardPlan.setRelatedPersonInfo(relatedPersonInfo);
//		}
		return HttpResult.success(1, "成功");
	}

	//主条款
	public HttpResult<?> buildMainInsurance(FormulaObj formulaObj, FormulaList formulaList) {
		List<Insurance> mainInsuranceList = new ArrayList<Insurance>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("property", 1);
		map.put("planId", formulaList.getPlanId());
		mainInsuranceList = tPrdPlanFormMapper.selectPlanCt(map);
		for (int i = 0; i < mainInsuranceList.size(); i++) {
			mainInsuranceList.get(i).setInsuranceType("1");
			mainInsuranceList.get(i).setType("collage");
			mainInsuranceList.get(i).setDefaultValue("1");
			mainInsuranceList.get(i).setRequired(true);
			//定额定费 限额限费 标示 ct fixed_insure
			if (mainInsuranceList.get(i).getFixedInsure() != 1) {
				HttpResult<?> res = buildRates(mainInsuranceList.get(i), formulaList);
				if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			}
		}
		formulaObj.setMainInsurance(mainInsuranceList);
		return HttpResult.success(1, "成功");
	}

	//附加条款
	public HttpResult<?> buildSubsidiaryInsurance(FormulaObj formulaObj, FormulaList formulaList) {
		List<Insurance> subsidiaryInsuranceList = new ArrayList<Insurance>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("property", 2);
		map.put("planId", formulaList.getPlanId());
		subsidiaryInsuranceList = tPrdPlanFormMapper.selectPlanCt(map);
		for (int i = 0; i < subsidiaryInsuranceList.size(); i++) {
			subsidiaryInsuranceList.get(i).setInsuranceType("0");
			subsidiaryInsuranceList.get(i).setType("checkBox");
			subsidiaryInsuranceList.get(i).setDefaultValue("0");
			subsidiaryInsuranceList.get(i).setRequired(true);
			//选项值
			HttpResult<?> res = buildValueList(subsidiaryInsuranceList.get(i));
			if(judgeHttpResult(res,p->"0".equals(res.getCode()))) return res;
			if (subsidiaryInsuranceList.get(i).getFixedInsure() != 1) {
				//费率
				HttpResult<?> res1 = buildRates(subsidiaryInsuranceList.get(i), formulaList);
				if(judgeHttpResult(res1,p->"0".equals(res1.getCode()))) return res1;
			}
		}
		formulaObj.setSubsidiaryInsurance(subsidiaryInsuranceList);
		return HttpResult.success(1, "成功");
	}

	//选项值
	public HttpResult<?> buildValueList(Insurance insurance) {
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
		return HttpResult.success(1, "成功");
	}

	//费率
	public HttpResult<?> buildRates(Insurance insurance, FormulaList formulaList) {
//		List<RateParams> rateParamsList = new ArrayList<RateParams>();
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("planId", formulaList.getPlanId());
//		rateParamsList = tPrdPlanFormMapper.selectPlanRate(map);
//		List<Rates> ratesList = new ArrayList<Rates>();
//		//defect 试算要素是年龄和社保
//		for (int i = 0; i < rateParamsList.size(); i++) {
//			Rates rates = new Rates();
//			rates.setKey(rateParamsList.get(i).getRateAgeMin() + "-" + rateParamsList.get(i).getRateAgeMax() + "_"
//					+ rateParamsList.get(i).getIsHaveSheBao());
//			rates.setPremium(rateParamsList.get(i).getPremium());
//			ratesList.add(rates);
//		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		String codes = tPrdPlanFormMapper.selectPlanRateCode(map);
		map.put("codes", codes);
		List<Rates> ratesList = tPrdPlanFormMapper.selectPlanRateMapping(map);
		insurance.setRates(ratesList);
		return HttpResult.success(1, "成功");
	}

	//底部按钮
	public PlanButton buttonShow(PlanElementDetaiReq planElementDetaiReq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", planElementDetaiReq.getStrategyId());
		PlanButton planButton = tPrdPlanFormMapper.selectButton(map);
		return planButton;
	}
	
	//错误返回码0
	public boolean judgeHttpResult(HttpResult<?> httpResult,Predicate<HttpResult<?>> p) {
	        return p.test(httpResult);
	}
	
	@PostMapping("/ss")
	public double selectPlanRatePrem(@RequestBody PlanRate list) {
		double fee = 0;
		for(RateVo rateVo : list.getRate()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("planCoverageIds", list.getPlanCoverageIds());
			map.put("planId", list.getPlanId());
			map.put("rateAgeMin", rateVo.getRateAgeMin());
			map.put("rateAgeMax", rateVo.getRateAgeMax());
			map.put("periodMin", rateVo.getPeriodMin());
			map.put("periodMax", rateVo.getPeriodMax());
			map.put("rateSeatMin", rateVo.getRateSeatMin());
			map.put("rateSeatMax", rateVo.getRateSeatMax());
			double  a = tPrdPlanFormMapper.selectPlanRateFee(map);
			System.out.println("责任保费");
			fee = fee+a;
		}
		System.out.println(fee);
		return fee;
	}
	
	
	
//	List<RatePremVo> ratePremList = new ArrayList<RatePremVo>();
//	HashMap<String, Object> map = new HashMap<String, Object>();
//	map.put("planId", planFieldDto.getPlanId());
//	 List<HashMap<String, Object>> list = tPrdPlanFormMapper.selectPlanRatePrem(map);
//	 for(HashMap<String, Object> obj:list) {
//		 if(obj.get("rateAgeMaxType")!=null ) {
//			 System.out.println(obj.get("rateAgeMin")+","+obj.get("rateAgeMax")+":"+obj.get("premium")); 
//		 }
//		 if(obj.get("ratePeriod")!=null ) {
//			 System.out.println(obj.get("periodMin")+","+obj.get("periodMax")+":"+obj.get("premium"));
//		 }
//		 
//	 }
//	 for(int i=0;i<list.size();i++) {
//		 for(int j=0;j<list.size();j++) {
//			 Integer rateAgeMinType_i = null;
//			 Integer rateAgeMaxType_i = null;
//			 Integer rateAgeMinType_j = null;
//			 Integer rateAgeMaxType_j = null;
//			 Integer ratePeriod_i = null;
//			 Integer ratePeriod_j = null;
//			 if(list.get(i).get("rateAgeMinType")!=null)
//				 rateAgeMinType_i = Integer.parseInt(list.get(i).get("rateAgeMinType")+"");
//			 if(list.get(i).get("rateAgeMaxType")!=null)
//				 rateAgeMaxType_i = Integer.parseInt(list.get(i).get("rateAgeMaxType")+"");
//			 //System.out.println(j);
//			 if(list.get(j).get("rateAgeMinType")!=null)
//				 rateAgeMinType_j = Integer.parseInt(list.get(j).get("rateAgeMinType")+"");
//			 if(list.get(j).get("rateAgeMaxType")!=null)
//				 rateAgeMaxType_j = Integer.parseInt(list.get(j).get("rateAgeMaxType")+"");
//			 if(list.get(i).get("ratePeriod")!=null) {
//				 ratePeriod_i = Integer.parseInt(list.get(i).get("ratePeriod")+"");
//			 }
//			 if(list.get(j).get("ratePeriod")!=null) {
//				 ratePeriod_j = Integer.parseInt(list.get(j).get("ratePeriod")+"");
//			 }
//			 
//			 Long rateAgeMin_i = null;
//			 if(rateAgeMinType_i!=null && rateAgeMinType_i==1)
//				 rateAgeMin_i = Long.parseLong(list.get(i).get("rateAgeMin")+"");
//			 else if(rateAgeMinType_i!=null && rateAgeMinType_i==2)
//				 rateAgeMin_i = Long.parseLong(list.get(i).get("rateAgeMin")+"")*365;
//			 
//			 Long rateAgeMax_i = null;
//			 if(rateAgeMaxType_i!=null && rateAgeMaxType_i==1)
//			  rateAgeMax_i = Long.parseLong(list.get(i).get("rateAgeMax")+"");
//			 else if(rateAgeMaxType_i!=null && rateAgeMaxType_i==2)
//				 rateAgeMax_i = Long.parseLong(list.get(i).get("rateAgeMax")+"")*365;
//		
//			 Long rateAgeMin_j = null;
//			 if(rateAgeMinType_j!=null && rateAgeMinType_j==1)
//				 rateAgeMin_j = Long.parseLong(list.get(j).get("rateAgeMin")+"");
//			 else if(rateAgeMinType_j!=null && rateAgeMinType_j==2)
//				 rateAgeMin_j = Long.parseLong(list.get(j).get("rateAgeMin")+"")*365;
//			 
//			 Long rateAgeMax_j  = null;
//			 if(rateAgeMaxType_j!=null && rateAgeMaxType_j==1)
//			  rateAgeMax_j = Long.parseLong(list.get(j).get("rateAgeMax")+"");
//			 else if(rateAgeMaxType_i!=null && rateAgeMaxType_i==2)
//				 rateAgeMax_j = Long.parseLong(list.get(j).get("rateAgeMax")+"")*365;
//			 
//			 Long periodMin_i = null;
//			 if(ratePeriod_i!=null && ratePeriod_i==9)
//				 periodMin_i = Long.parseLong(list.get(i).get("periodMin")+"");
//			 
//			 Long periodMin_j = null;
//			 if(ratePeriod_j!=null && ratePeriod_j==9)
//				 periodMin_j = Long.parseLong(list.get(j).get("periodMin")+"");
//			 
//			 Long periodMax_i = null;
//			 if(ratePeriod_i!=null && ratePeriod_i==9)
//				 periodMax_i = Long.parseLong(list.get(i).get("periodMax")+"");
//			 
//			 Long periodMax_j = null;
//			 if(ratePeriod_j!=null && ratePeriod_j==9)
//				 periodMax_j = Long.parseLong(list.get(j).get("periodMax")+"");
//			 
//			 if(list.get(j).get("rateAgeMaxType")!=null ) {
//				 HashMap<String, Object> map_age = new HashMap<String, Object>();
//				 map_age.put("planId", list.get(j).get("planId"));
//				 map_age.put("maxType", list.get(j).get("rateAgeMaxType"));
//				 map_age.put("maxValue", list.get(j).get("rateAgeMax"));
//				 map_age.put("minType", list.get(j).get("rateAgeMinType"));
//				 map_age.put("minValue", list.get(j).get("rateAgeMin"));
//				 map_age.put("rangeType", "age");
//				 HashMap<String, Object> list_ff2 = tPrdPlanFormMapper.selectPlanRateRange(map_age);
//				 if(list_ff2!=null) {
//					 if((rateAgeMin_j>= rateAgeMin_i &&  rateAgeMax_j < rateAgeMax_i) || (rateAgeMin_j> rateAgeMin_i && rateAgeMax_j<= rateAgeMax_i)) {
//						 RatePremVo ratePremVo = new RatePremVo();
//						 ratePremVo.setPlanId(Integer.parseInt(list.get(j).get("planId")+""));
//						 ratePremVo.setRateAgeMin(Long.parseLong(list.get(j).get("rateAgeMin")+""));
//						 ratePremVo.setRateAgeMax(Long.parseLong(list.get(j).get("rateAgeMax")+""));
//						 ratePremVo.setPremium(Double.parseDouble(list.get(j).get("premium")+"")+Double.parseDouble(list.get(i).get("premium")+""));
//						 ratePremList.add(ratePremVo);
//						 System.out.print (list.get(j).get("rateAgeMin")+"-"+list.get(j).get("rateAgeMax")+":");
//						 System.out.println(Double.parseDouble(list.get(j).get("premium")+"")+Double.parseDouble(list.get(i).get("premium")+""));
//					 }
//				 }
//			 }
//			 
//			 if(list.get(j).get("ratePeriod")!=null ) {
//				 HashMap<String, Object> map_age = new HashMap<String, Object>();
//				 map_age.put("planId", list.get(j).get("planId"));
//				 map_age.put("maxType", list.get(j).get("ratePeriod"));
//				 map_age.put("maxValue", list.get(j).get("periodMax"));
//				 map_age.put("minType", list.get(j).get("ratePeriod"));
//				 map_age.put("minValue", list.get(j).get("periodMin"));
//				 map_age.put("rangeType", "period");
//				 HashMap<String, Object> list_ff2 = tPrdPlanFormMapper.selectPlanRateRange(map_age);
//				 
//				 if(list_ff2!=null) {
//					 if((periodMin_j>= periodMin_i &&  periodMax_j < periodMax_i) || (periodMin_j> periodMin_i &&  periodMax_j <= periodMax_i)) {
//						 System.out.print(list.get(j).get("periodMin")+"-"+list.get(j).get("periodMax")+":");
//						 System.out.println(Double.parseDouble(list.get(j).get("premium")+"")+Double.parseDouble(list.get(i).get("premium")+""));
//					 }
//				 }
//			 }
//			 
//			 
//			 
//		 }
//		 
//	 }
//	System.out.println("OK");
//	System.out.println(JSONObject.toJSONString(ratePremList));
//	return ratePremList;
	
	
	
	
//	 区间过滤
//	 Integer rateAgeMinType_i = null;
//	 Integer rateAgeMaxType_i = null;
//	 Integer ratePeriod_i = null;
//	 Long rateAgeMin_i = null;
//	 Long rateAgeMax_i = null;
//	 Long periodMin_i = null;
//	 Long periodMax_i = null;
//	 
//	 Integer rateAgeMinType_j = null;
//	 Integer rateAgeMaxType_j = null;
//	 Integer ratePeriod_j = null;
//	 Long rateAgeMin_j = null;
//	 Long rateAgeMax_j  = null;
//	 Long periodMin_j = null;
//	 Long periodMax_j = null;
//	 
//	 if(list.get(i).get("rateAgeMinType")!=null) rateAgeMinType_i = Integer.parseInt(list.get(i).get("rateAgeMinType")+"");
//	 if(list.get(i).get("rateAgeMaxType")!=null) rateAgeMaxType_i = Integer.parseInt(list.get(i).get("rateAgeMaxType")+"");
//	 if(list.get(i).get("ratePeriod")!=null) ratePeriod_i = Integer.parseInt(list.get(i).get("ratePeriod")+"");
//	 if(rateAgeMinType_i!=null && rateAgeMinType_i==1) rateAgeMin_i = Long.parseLong(list.get(i).get("rateAgeMin")+"");
//	 else if(rateAgeMinType_i!=null && rateAgeMinType_i==2) rateAgeMin_i = Long.parseLong(list.get(i).get("rateAgeMin")+"")*365;
//	 if(rateAgeMaxType_i!=null && rateAgeMaxType_i==1) rateAgeMax_i = Long.parseLong(list.get(i).get("rateAgeMax")+"");
//	 else if(rateAgeMaxType_i!=null && rateAgeMaxType_i==2) rateAgeMax_i = Long.parseLong(list.get(i).get("rateAgeMax")+"")*365;
//	 if(ratePeriod_i!=null && ratePeriod_i==9) periodMin_i = Long.parseLong(list.get(i).get("periodMin")+"");
//	 if(ratePeriod_i!=null && ratePeriod_i==9) periodMax_i = Long.parseLong(list.get(i).get("periodMax")+"");
//	 
//	 if(list.get(j).get("rateAgeMinType")!=null) rateAgeMinType_j = Integer.parseInt(list.get(j).get("rateAgeMinType")+"");
//	 if(list.get(j).get("rateAgeMaxType")!=null) rateAgeMaxType_j = Integer.parseInt(list.get(j).get("rateAgeMaxType")+"");
//	 if(list.get(j).get("ratePeriod")!=null) ratePeriod_j = Integer.parseInt(list.get(j).get("ratePeriod")+"");
//	 if(rateAgeMinType_j!=null && rateAgeMinType_j==1) rateAgeMin_j = Long.parseLong(list.get(j).get("rateAgeMin")+"");
//	 else if(rateAgeMinType_j!=null && rateAgeMinType_j==2) rateAgeMin_j = Long.parseLong(list.get(j).get("rateAgeMin")+"")*365;
//	 if(rateAgeMaxType_j!=null && rateAgeMaxType_j==1) rateAgeMax_j = Long.parseLong(list.get(j).get("rateAgeMax")+"");
//	 else if(rateAgeMaxType_j!=null && rateAgeMaxType_j==2) rateAgeMax_j = Long.parseLong(list.get(j).get("rateAgeMax")+"")*365;
//	 if(ratePeriod_j!=null && ratePeriod_j==9) periodMin_j = Long.parseLong(list.get(j).get("periodMin")+"");
//	 if(ratePeriod_j!=null && ratePeriod_j==9) periodMax_j = Long.parseLong(list.get(j).get("periodMax")+"");
//	 if(list.get(j).get("rateAgeMaxType")!=null ) {
//		 
//	 }
	
	
	@Autowired
    private RedisUtils redisUtils;
    @RequestMapping(value = "/catch")
    public void hello(@RequestBody PlanRate list){
    	 //String yan = MD5.crypt(list.getStrategyId()+"|"+list.getPlanId()+"|"+list.getNode()+"|"+list.getUserCode());
    	 String  key="PLAN:"+list.getStrategyId()+"|"+list.getPlanId()+"|"+list.getNode()+"|"+list.getUserCode();
    	 boolean hasKey = redisUtils.exists(key);
    	 if(hasKey){
           Object object =  redisUtils.get(key);
           String str = object.toString();
           System.out.println("str:"+str);
    	 }
    	 else{
    		 redisUtils.set(key,JSONObject.toJSONString(list),3L,TimeUnit.DAYS);
    	 }
    }
    
   
    

	
}

