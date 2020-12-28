package com.ccic.salesapp.noncar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.request.noncar.PolicyRiskOrderCar;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanCoInsurenceMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanCoInsurerMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanCtDetailMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanCtMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanCtRateMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanDefinitionMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanFormMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanInfoMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanLimitMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanMethodMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanOrgMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanRiMapper;
import com.ccic.salesapp.noncar.repository.databusdb.mapper.TPrdPlanSharedCtGroupMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.PolicyLobList;
import com.ccic.salesapp.noncar.repository.databusdb.po.PolicyTemple;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCt;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtDetail;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanCtRate;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanDefinition;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanForm;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanInfo;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanLimit;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanMethod;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanOrg;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanRi;
import com.ccic.salesapp.noncar.repository.databusdb.po.TPrdPlanSharedCtGroup;
import com.ccic.salessapp.common.core.web.HttpResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "核心推送方案接收", description = "核心推送方案接收", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "noncar")
@Slf4j
public class ProductPlanDefCtl {

	@Autowired
	TPrdPlanDefinitionMapper tPrdPlanDefinitionMapper;

	@Autowired
	TPrdPlanInfoMapper tPrdPlanInfoMapper;

	@Autowired
	TPrdPlanOrgMapper tPrdPlanOrgMapper;

	@Autowired
	TPrdPlanMethodMapper tPrdPlanMethodMapper;

	@Autowired
	TPrdPlanSharedCtGroupMapper tPrdPlanSharedCtGroupMapper;

	@Autowired
	TPrdPlanCoInsurerMapper tPrdPlanCoInsurerMapper;

	@Autowired
	TPrdPlanCoInsurenceMapper tPrdPlanCoInsurenceMapper;

	@Autowired
	TPrdPlanCtMapper tPrdPlanCtMapper;

	@Autowired
	TPrdPlanCtRateMapper tPrdPlanCtRateMapper;

	@Autowired
	TPrdPlanCtDetailMapper tPrdPlanCtDetailMapper;

	@Autowired
	TPrdPlanLimitMapper tPrdPlanLimitMapper;

	@Autowired
	TPrdPlanFormMapper tPrdPlanFormMapper;

	@Autowired
	TPrdPlanRiMapper tPrdPlanRiMapper;

	@PostMapping(value = "synJointSalesProductPlanInfo")
	@ApiOperation(value = "核心推送方案接收", notes = "核心推送方案接收", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpResult<?> synJointSalesProductPlanInfo(@RequestBody String requestStr) {
		TPrdPlanDefinition productPlanDef = JSON.parseObject(requestStr, TPrdPlanDefinition.class);
		if (productPlanDef == null) {
			log.info("推送方案报文为NULL!");
			return HttpResult.error(0, "推送方案报文为NULL!");
		}

		log.info("-------------请求报文为：" + productPlanDef + "-------------");

		//根据PlanDefId删除该json数据之前入库的所有数据;
		Long delPlanDefId = productPlanDef.getPlanDefId();
		//删除planCt和planCtDetial表数据
		tPrdPlanInfoMapper.deleteByPlanCtDetialDefId(delPlanDefId);
		tPrdPlanInfoMapper.deleteByPlanDefId(delPlanDefId);
		//删除t_prd_plan_definition表数据
		tPrdPlanDefinitionMapper.deleteByPlanDefinitionId(delPlanDefId);
		//删除t_prd_plan_form表数据
		tPrdPlanFormMapper.deleteByPlanFormId(delPlanDefId);
		//删除t_prd_plan_shared_ct_group表数据
		tPrdPlanSharedCtGroupMapper.deleteByPlanSharedCtGroupId(delPlanDefId);
		//删除t_prd_plan_org表数据
		tPrdPlanOrgMapper.deleteByPlanOrgId(delPlanDefId);
		//删除t_prd_plan_method表数据
		tPrdPlanMethodMapper.deleteByPlanMethodId(delPlanDefId);

		try {
			tPrdPlanDefinitionMapper.insert(productPlanDef);
			Long planDefId = productPlanDef.getPlanDefId();

			TPrdPlanInfo tPrdPlanInfo = productPlanDef.getProductPlanInfo();
			if (tPrdPlanInfo != null) {
				tPrdPlanInfo.setPlanDefId(planDefId);
				tPrdPlanInfoMapper.insert(tPrdPlanInfo);
			}

			PolicyTemple policyTemple = productPlanDef.getPolicyTemple();
			if (policyTemple != null) {
				List<PolicyLobList> policyLobList = policyTemple.getPolicyLobList();
				for (PolicyLobList policyLob : policyLobList) {
					List<TPrdPlanForm> productPlanFormList = policyLob.getProductPlanFormList();
					if (CollectionUtils.isNotEmpty(productPlanFormList)) {
						for (TPrdPlanForm productPlanForm : productPlanFormList) {
							productPlanForm.setPlandefid(planDefId);
							tPrdPlanFormMapper.insert(productPlanForm);
						}
					}

					List<TPrdPlanSharedCtGroup> planSharedGroupList = policyLob.getPlanSharedGroupList();
					if (CollectionUtils.isNotEmpty(planSharedGroupList)) {
						for (TPrdPlanSharedCtGroup planSharedGroup : planSharedGroupList) {
							planSharedGroup.setPlanDefId(planDefId);
							tPrdPlanSharedCtGroupMapper.insert(planSharedGroup);
						}
					}

					//第一大节点PolicyRiskList
					List<PolicyRiskOrderCar> policyRiskList = policyLob.getPolicyRiskList();
					if (CollectionUtils.isNotEmpty(policyRiskList)) {
						TPrdPlanCt tPrdPlanCt = null;
						//						if(i< 4) {
						for (PolicyRiskOrderCar policyRisk : policyRiskList) {
							List<PolicyRiskOrderCar> childPlanCoverageList = policyRisk.getPolicyCoverageList();
							if (childPlanCoverageList != null && childPlanCoverageList.size() > 0) {
								for (PolicyRiskOrderCar planCoverageLst : childPlanCoverageList) {
									planCoverageLst.setPlanDefId(planDefId);
									Map<String, String> tempData = planCoverageLst.getTempData();
									String productElementCode = tempData.get("ProductElementCode");
									String productElementName = tempData.get("ProductElementName");
									Date date = new Date();
									String num = ("" + date.getTime()).substring(0, 13);
									Long l = Long.parseLong(num);
									planCoverageLst.setPlanCoverageId(l);
									planCoverageLst.setPlanCoverageName(productElementName);
									planCoverageLst.setProductElementCode(productElementCode);
									tPrdPlanCtMapper.insertChildPlanCoverage(planCoverageLst);
									tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId(planCoverageLst.getPlanCoverageId());

								}
							}

							List<PolicyRiskOrderCar> childPlanCoverageListTempData = policyRisk.getPolicyCoverageList();
							if (childPlanCoverageListTempData != null && childPlanCoverageListTempData.size() > 0) {
								for (PolicyRiskOrderCar planCoverageLstTempData : childPlanCoverageListTempData) {
									Map<String, String> tempDataF = planCoverageLstTempData.getTempData();
									if (planCoverageLstTempData.getTempData().get("ctCode") != null) {
										String ctCode = tempDataF.get("ctCode");
										String ctName = tempDataF.get("ctName");
										Date date = new Date();
										String num = ("" + date.getTime()).substring(0, 13);
										Long l1 = Long.parseLong(num);
										planCoverageLstTempData.setPlanCoverageId(l1);
										Long planCoverageId = tPrdPlanCt.getPlanCoverageId();
										planCoverageLstTempData.setParentPlanCoverageId(planCoverageId);
										planCoverageLstTempData.setPlanCoverageName(ctName);
										planCoverageLstTempData.setProductElementCode(ctCode);
										tPrdPlanCtMapper.insertChildPlanCoverage(planCoverageLstTempData);
										tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId(planCoverageLstTempData.getPlanCoverageId());
									}else{
										List<PolicyRiskOrderCar> policyCoverageList = planCoverageLstTempData.getPolicyCoverageList();
										if(policyCoverageList !=null && policyCoverageList.size()>0) {
											for (PolicyRiskOrderCar PolicyCoverageListTwoTempData : policyCoverageList) {
												Map<String, String> tempData = PolicyCoverageListTwoTempData.getTempData();
												if (planCoverageLstTempData.getTempData().get("ProductElementCode") != null) {
													String ctCode = tempData.get("ProductElementCode");
													String ctName = tempData.get("ProductElementName");
													Date date = new Date();
													String num = ("" + date.getTime()).substring(0, 13);
													Long l1 = Long.parseLong(num);
													planCoverageLstTempData.setPlanCoverageId(l1);
													Long planCoverageId = tPrdPlanCt.getPlanCoverageId();
													planCoverageLstTempData.setParentPlanCoverageId(planCoverageId);
													planCoverageLstTempData.setPlanCoverageName(ctName);
													planCoverageLstTempData.setProductElementCode(ctCode);
													tPrdPlanCtMapper.insertChildPlanCoverage(planCoverageLstTempData);
													tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId(planCoverageLstTempData.getPlanCoverageId());
												}
											}
										}
									}
								}
							}

							List<PolicyRiskOrderCar> childPlanCoverageListPlanCt = policyRisk.getPolicyCoverageList();
							if (childPlanCoverageListPlanCt != null && childPlanCoverageListPlanCt.size() > 0) {
								for (PolicyRiskOrderCar planCoverageLstCt : childPlanCoverageListPlanCt) {
									Map<String, String> tempData = planCoverageLstCt.getTempData();
									String productElementCode = tempData.get("ProductElementCode");
									String productClauseCategory = tempData.get("ClauseCategory");
									if(StringUtils.isNotBlank(planCoverageLstCt.getIsMainCoverage())) {
										String productIsMainCoverage = planCoverageLstCt.getIsMainCoverage();
										productIsMainCoverage = identifyTypeOrder(productIsMainCoverage);
										planCoverageLstCt.setClauseProperty(productIsMainCoverage);
									}
									planCoverageLstCt.setClauseCode(productElementCode);
									planCoverageLstCt.setProductElementCode(productElementCode);
									List<PolicyRiskOrderCar> policyRiskList1 = policyLob.getSharedCoverageGroupList();
									BigDecimal sumInsuredMax = null;
									BigDecimal premiumMax = null;
									BigDecimal adjustedPremium = null;
									String productElementCodePlanCta = null;
									List<PolicyRiskOrderCar> childPlanCoverageListPlanCta = policyRisk.getPolicyCoverageList();
									for (PolicyRiskOrderCar policyRiskOrderCarPlanCta : childPlanCoverageListPlanCta) {
										List<PolicyRiskOrderCar> policyCoverageList = policyRiskOrderCarPlanCta.getPolicyCoverageList();
										PolicyRiskOrderCar policyRiskOrderCar = policyCoverageList.get(0);
										if(policyRiskOrderCar.getSumInsured() != null) {
											sumInsuredMax = new BigDecimal(policyRiskOrderCar.getSumInsured());
										}
										if(policyRiskOrderCar.getUnitSumInsured() != null) {
											sumInsuredMax = new BigDecimal(policyRiskOrderCar.getUnitSumInsured());
										}
										if(policyRiskOrderCar.getPremiumRate() != null) {
											premiumMax = new BigDecimal(policyRiskOrderCar.getPremiumRate());
										}else {
											premiumMax = new BigDecimal(productPlanDef.getPolicyTemple().getPolicyLobList().get(0).getDuePremium());
										}
										if(policyRiskOrderCar.getAdjustedPremium() != null) {
											adjustedPremium = new BigDecimal(policyRiskOrderCar.getAdjustedPremium());
										}
										Map<String, String> tempDataPlanCta = policyRiskOrderCar.getTempData();
										productElementCodePlanCta = tempDataPlanCta.get("ProductElementCode");
									}
									planCoverageLstCt.setSumInsuredMax(sumInsuredMax);
									planCoverageLstCt.setSumInsuredMin(sumInsuredMax);
									planCoverageLstCt.setSumInsuredDefualt(sumInsuredMax);
									if(adjustedPremium!=null) {
										planCoverageLstCt.setPremiumOrrate("1");
										planCoverageLstCt.setPremiumMax(adjustedPremium);
										planCoverageLstCt.setPremiumMin(adjustedPremium);
										planCoverageLstCt.setPremiumDefault(adjustedPremium);
									}else {
										planCoverageLstCt.setPremiumOrrate("2");
										planCoverageLstCt.setPremiumMax(premiumMax);
										planCoverageLstCt.setPremiumMin(premiumMax);
										planCoverageLstCt.setPremiumDefault(premiumMax);
									}
									tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId1(planCoverageLstCt.getParentPlanCoverageId());
									Long planCoverageId = tPrdPlanCt.getPlanCoverageId();
									planCoverageLstCt.setPlanCtdetailId(tPrdPlanCt.getParentPlanCoverageId());
									planCoverageLstCt.setPlanCoverageId(planCoverageId);
									tPrdPlanCtDetailMapper.insertTPrdPlanCtDetail(planCoverageLstCt);
									System.out.println(planCoverageLstCt);
								}
							}
						}

						//第二大节点SharedCoverageGroupList
						List<PolicyRiskOrderCar> policyRiskList2 = policyLob.getSharedCoverageGroupList();
						if (CollectionUtils.isNotEmpty(policyRiskList2)) {
							for (PolicyRiskOrderCar policyRiskCar : policyRiskList2) {
								List<PolicyRiskOrderCar> policyCoverageList = policyRiskCar.getPolicyCoverageList();
								if(CollectionUtils.isNotEmpty(policyCoverageList)) {
									PolicyRiskOrderCar policyRiskOrderCar = null;
									for (PolicyRiskOrderCar policyLobList2 : policyCoverageList) {
										List<PolicyRiskOrderCar> policyCoverageList2 = policyLobList2.getPolicyCoverageList();
										if(CollectionUtils.isNotEmpty(policyCoverageList2)) {
											for (int i = 0; i < policyCoverageList2.size(); i++) {
												policyRiskOrderCar = policyCoverageList2.get(i);
												policyRiskOrderCar.setPlanDefId(planDefId);
												Map<String, String> tempData = policyRiskOrderCar.getTempData();
												String additionalProductElementCode = tempData.get("ProductElementCode");
												String additionalProductElementName = tempData.get("ProductElementName");
												
												Map<String, String> tempData2 = policyLobList2.getTempData();
												String tempDataClauseCode = tempData2.get("ProductElementCode");
												String tempDataClauseName = tempData2.get("ProductElementName");
												Date date = new Date();
												String num = ("" + date.getTime()).substring(0, 13);
												Long l2 = Long.parseLong(num);
												Long longNull =null;
												policyRiskOrderCar.setParentPlanCoverageId(longNull);
												policyRiskOrderCar.setPlanCoverageId(l2);
												policyRiskOrderCar.setProductElementCode(tempDataClauseCode);
												policyRiskOrderCar.setPlanCoverageName(tempDataClauseName);
												if(StringUtils.isNotBlank(additionalProductElementCode)) {
													tPrdPlanCtMapper.insertChildPlanCoverage(policyRiskOrderCar);
													tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId(policyRiskOrderCar.getPlanCoverageId());
												}
												
												Date date1 = new Date();
												String num1 = ("" + date1.getTime()).substring(0, 13);
												Long l3 = Long.parseLong(num1);
												policyRiskOrderCar.setPlanCoverageId(l3);
												Long planCoverageId1 = tPrdPlanCt.getPlanCoverageId();
												policyRiskOrderCar.setParentPlanCoverageId(planCoverageId1);
												policyRiskOrderCar.setProductElementCode(additionalProductElementCode);
												policyRiskOrderCar.setPlanCoverageName(additionalProductElementName);
												if(StringUtils.isNotBlank(additionalProductElementCode)) {
													tPrdPlanCtMapper.insertChildPlanCoverage(policyRiskOrderCar);
												}
												
												BigDecimal sumInsuredMax = null;
												BigDecimal premiumMax = null;
												if(policyRiskOrderCar.getSumInsured()!=null) {
													 sumInsuredMax = new BigDecimal(policyRiskOrderCar.getSumInsured());
												}
												if(policyRiskOrderCar.getUnitSumInsured()!=null) {
													 sumInsuredMax =  new BigDecimal(policyRiskOrderCar.getUnitSumInsured());
												}
												if(policyRiskOrderCar.getPremiumRate()!=null) {
													premiumMax = new BigDecimal(policyRiskOrderCar.getPremiumRate());
												}
												policyRiskOrderCar.setSumInsuredMax(sumInsuredMax);
												policyRiskOrderCar.setSumInsuredMin(sumInsuredMax);
												policyRiskOrderCar.setSumInsuredDefualt(sumInsuredMax);
												if(policyRiskOrderCar.getAdjustedPremium()!=null) {
													policyRiskOrderCar.setPremiumOrrate("1");
													BigDecimal adjustedPremium = new BigDecimal(policyRiskOrderCar.getAdjustedPremium());
													policyRiskOrderCar.setPremiumMax(adjustedPremium);
													policyRiskOrderCar.setPremiumMin(adjustedPremium);
													policyRiskOrderCar.setPremiumDefault(adjustedPremium);
												}else {
													policyRiskOrderCar.setPremiumOrrate("2");
													policyRiskOrderCar.setPremiumMax(premiumMax);
													policyRiskOrderCar.setPremiumMin(premiumMax);
													policyRiskOrderCar.setPremiumDefault(premiumMax);
												}
												policyRiskOrderCar.setClauseCode(tempDataClauseCode);
												policyRiskOrderCar.setPlanCoverageId(l2);
												if(StringUtils.isNotBlank(additionalProductElementCode)) {
													Long planCoverageId = tPrdPlanCt.getPlanCoverageId();
													policyRiskOrderCar.setPlanCtdetailId(planCoverageId);
													policyRiskOrderCar.setPlanCoverageId(l3);
													if(StringUtils.isNotBlank(policyRiskOrderCar.getIsMainCoverage())) {
														String productIsMainCoverage = policyRiskOrderCar.getIsMainCoverage();
														productIsMainCoverage = identifyTypeOrder(productIsMainCoverage);
														policyRiskOrderCar.setClauseProperty(productIsMainCoverage);
													}else {
														policyRiskOrderCar.setClauseProperty("2");
													}
													tPrdPlanCtDetailMapper.insertTPrdPlanCtDetail(policyRiskOrderCar);
												}
											}
										}
									}
								List<String> list = new ArrayList();
								List<String> listOne = new ArrayList();
								for (PolicyRiskOrderCar policyLobList2 : policyCoverageList) {
									List<PolicyRiskOrderCar> policyCoverageList2 = policyLobList2.getPolicyCoverageList();
									if(CollectionUtils.isNotEmpty(policyCoverageList2)) {
										for (int i = 0; i < policyCoverageList2.size(); i++) {
											policyRiskOrderCar = policyCoverageList2.get(i);
											policyRiskOrderCar.setPlanDefId(planDefId);
											Map<String, String> tempData = policyRiskOrderCar.getTempData();
											String aaCode = tempData.get("ProductElementCode");
											list.add(aaCode);
											Map<String, String> tempData2 = policyLobList2.getTempData();
											String tempDataClauseCode = tempData2.get("ProductElementCode");
											listOne.add(tempDataClauseCode);
										}
									}
								}
								policyRiskOrderCar = policyCoverageList.get(0);
								PolicyRiskOrderCar policyRiskOrderCar2 = null;
								for (int j = 0; j < policyCoverageList.size(); j++) {
									policyRiskOrderCar2 = policyCoverageList.get(j);
									if(policyRiskOrderCar2.getProductElementCode() != null) {
										policyRiskOrderCar.setPlanDefId(planDefId);
										Map<String, String> tempData = policyRiskOrderCar2.getTempData();
										String additionalProductElementCode = tempData.get("ProductElementCode");
										String additionalProductElementName = tempData.get("ProductElementName");
										String additionalIsOptional = tempData.get("IsOptional");
										Date date = new Date();
										String num = ("" + date.getTime()).substring(0, 13);
										Long l2 = Long.parseLong(num);
										Long longNull =null;
										policyRiskOrderCar.setParentPlanCoverageId(longNull);
										policyRiskOrderCar.setPlanCoverageId(l2);
										policyRiskOrderCar.setProductElementCode(additionalProductElementCode);
										policyRiskOrderCar.setPlanCoverageName(additionalProductElementName);
										policyRiskOrderCar.setIsOptional(additionalIsOptional);
										PolicyRiskOrderCar policyRiskOrderCarList = policyCoverageList.get(0);
										policyRiskOrderCarList.setPlanDefId(planDefId);
										String additionalctCode = tempData.get("ctCode");
										String additionalctName = tempData.get("ctName");
										String additionalIsOptional1 = tempData.get("IsOptional");
										boolean anyMatchOne = listOne.stream().anyMatch(s -> Objects.equals(s, additionalProductElementCode));
										boolean anyMatch = list.stream().anyMatch(s -> Objects.equals(s, additionalctCode));
										if(anyMatch == true && anyMatchOne == true) {
											continue;
										}
										tPrdPlanCtMapper.insertChildPlanCoverage(policyRiskOrderCar);
										tPrdPlanCt = tPrdPlanCtMapper.findParentPlanCoverageId(policyRiskOrderCar.getPlanCoverageId());
										Date date1 = new Date();
										String num1 = ("" + date1.getTime()).substring(0, 13);
										Long l3 = Long.parseLong(num1);
										policyRiskOrderCarList.setPlanCoverageId(l3);
										Long planCoverageId1 = tPrdPlanCt.getPlanCoverageId();
										policyRiskOrderCarList.setIsOptional(additionalIsOptional1);
										policyRiskOrderCarList.setParentPlanCoverageId(planCoverageId1);
										policyRiskOrderCarList.setProductElementCode(additionalctCode);
										policyRiskOrderCarList.setPlanCoverageName(additionalctName);
										tPrdPlanCtMapper.insertChildPlanCoverage(policyRiskOrderCarList);
										List<PolicyRiskOrderCar> policyCoverageListOrderlist = policyRiskOrderCar2.getPolicyCoverageList();
										for (int k = 0; k < policyCoverageListOrderlist.size(); k++) {
											PolicyRiskOrderCar policyRiskOrderCar3 = new PolicyRiskOrderCar();
											policyRiskOrderCar3 = policyCoverageListOrderlist.get(k);
											BigDecimal sumInsuredMax = null;
											BigDecimal premiumMax = null;
											if(policyRiskOrderCar3.getSumInsured()!=null) {
												 sumInsuredMax = new BigDecimal(policyRiskOrderCar3.getSumInsured());
											}
											if(policyRiskOrderCar3.getUnitSumInsured()!=null) {
												 sumInsuredMax =  new BigDecimal(policyRiskOrderCar3.getUnitSumInsured());
											}
											if(policyRiskOrderCar3.getPremiumRate()!=null) {
												premiumMax = new BigDecimal(policyRiskOrderCar3.getPremiumRate());
											}
											policyRiskOrderCar3.setSumInsuredMax(sumInsuredMax);
											policyRiskOrderCar3.setSumInsuredMin(sumInsuredMax);
											policyRiskOrderCar3.setSumInsuredDefualt(sumInsuredMax);
											if(policyRiskOrderCar3.getAdjustedPremium()!=null) {
												policyRiskOrderCar3.setPremiumOrrate("1");
												BigDecimal adjustedPremium = new BigDecimal(policyRiskOrderCar3.getAdjustedPremium());
												policyRiskOrderCar3.setPremiumMax(adjustedPremium);
												policyRiskOrderCar3.setPremiumMin(adjustedPremium);
												policyRiskOrderCar3.setPremiumDefault(adjustedPremium);
											}else {
												policyRiskOrderCar3.setPremiumOrrate("2");
												policyRiskOrderCar3.setPremiumMax(premiumMax);
												policyRiskOrderCar3.setPremiumMin(premiumMax);
												policyRiskOrderCar3.setPremiumDefault(premiumMax);
											}
											Map<String, String> tempDataRiskCode = policyRiskOrderCar2.getTempData();
											String tempDataClauseCode = tempDataRiskCode.get("clauseCode");
											policyRiskOrderCar3.setClauseCode(tempDataClauseCode);
											policyRiskOrderCar3.setPlanCoverageId(l3);
											Long planCoverageId = tPrdPlanCt.getPlanCoverageId();
											policyRiskOrderCar3.setPlanCtdetailId(planCoverageId);
											if(StringUtils.isNotBlank(policyRiskOrderCar3.getIsMainCoverage())) {
												String productIsMainCoverage = policyRiskOrderCar3.getIsMainCoverage();
												productIsMainCoverage = identifyTypeOrder(productIsMainCoverage);
												policyRiskOrderCar3.setClauseProperty(productIsMainCoverage);
											}else {
												policyRiskOrderCar3.setClauseProperty("2");
											}
											tPrdPlanCtDetailMapper.insertTPrdPlanCtDetail(policyRiskOrderCar3);
										}
									}
								}
							}else {
								
							}

							}
						}
					}
				}
			}

			List<TPrdPlanOrg> tPrdPlanOrg = productPlanDef.getProductPlanOrgList();
			if (tPrdPlanOrg != null && tPrdPlanOrg.size() > 0) {
				for (int i = 0; i < tPrdPlanOrg.size(); i++) {
					TPrdPlanOrg planOrg = tPrdPlanOrg.get(i);
					planOrg.setPlanDefId(planDefId);
					tPrdPlanOrgMapper.insert(planOrg);
				}
			}
			

			List<TPrdPlanMethod> tPrdPlanMethod = productPlanDef.getProductPlanMethodList();
			if (tPrdPlanMethod != null && tPrdPlanMethod.size() > 0) {
				for (TPrdPlanMethod planMethod : tPrdPlanMethod) {
					planMethod.setPlanDefId(planDefId);
					planMethod.setBuizBusinessLine(tPrdPlanInfo.getBuizBusinessLine());
					planMethod.setBuizProductLine(tPrdPlanInfo.getBuizSecondLine());
					tPrdPlanMethodMapper.insert(planMethod);
				}
			}

			List<TPrdPlanSharedCtGroup> tPrdPlanSharedCtGroup = productPlanDef.getPlanSharedGroupList();
			if (tPrdPlanSharedCtGroup != null && tPrdPlanSharedCtGroup.size() > 0) {
				for (TPrdPlanSharedCtGroup planSharedCtGroup : tPrdPlanSharedCtGroup) {
					planSharedCtGroup.setPlanDefId(planDefId);
					tPrdPlanSharedCtGroupMapper.insert(planSharedCtGroup);
				}
			}

			List<TPrdPlanCt> planCoverageList = productPlanDef.getPlanCoverageList();
			if (planCoverageList != null && planCoverageList.size() > 0) {
				//有就删除
				for (TPrdPlanCt coverageList : planCoverageList) {
					List<TPrdPlanLimit> tPrdPlanLimit = coverageList.getPlanLimitList();
					if (tPrdPlanLimit != null && tPrdPlanLimit.size() > 0) {
						tPrdPlanLimitMapper.deletePlanLimitById(coverageList.getPlanCoverageId());
					}
					List<TPrdPlanCtRate> tPrdPlanCtRate = coverageList.getPlanCoverageRateList();
					if (tPrdPlanCtRate != null && tPrdPlanCtRate.size() > 0) {
						tPrdPlanCtRateMapper.deletePlanCtRateById(coverageList.getPlanCoverageId());
					}
				}
						
				for (TPrdPlanCt coverageList : planCoverageList) {
					coverageList.setPlanDefId(planDefId);
					Long planSharedId = coverageList.getPlanSharedId();
					for (TPrdPlanSharedCtGroup planSharedCtGroup : tPrdPlanSharedCtGroup) {
						if(planSharedId.equals(planSharedCtGroup.getPlanSharedId())) {
						   coverageList.setSharedGroupType(planSharedCtGroup.getGroupType());
						}
					}
					tPrdPlanCtMapper.insert(coverageList);
					this.getChild(1, coverageList, planDefId,productPlanDef);
				}
			}

			List<TPrdPlanForm> tPrdPlanForm = productPlanDef.getProductPlanFormList();
			if (tPrdPlanForm != null && tPrdPlanForm.size() > 0) {
				for (TPrdPlanForm planForm : tPrdPlanForm) {
					planForm.setPlandefid(planDefId);
					tPrdPlanFormMapper.insert(planForm);
				}
			}

			List<TPrdPlanRi> tPrdPlanRi = productPlanDef.getProductPlanRiList();
			if (tPrdPlanRi != null && tPrdPlanRi.size() > 0) {
				for (TPrdPlanRi planRi : tPrdPlanRi) {
					planRi.setPlanDefId(planDefId);
					tPrdPlanRiMapper.insert(planRi);
				}
			}

		} catch (Exception e) {
			log.error("程序异常：", e);
			return HttpResult.error(0, "程序异常：" + e);
		}
		log.info("推送方案成功!");
		return HttpResult.success(1, "推送方案成功!");
	}

	// 转换主附险 Y 转1
	public static String identifyTypeOrder(String productIsMainCoverage) {
		if (productIsMainCoverage.equals("Y")) {
			productIsMainCoverage = "1";
		}
		return productIsMainCoverage;
	}

	// 递归查询子节点ChildPlanCoverageList
	public void getChild(int n, TPrdPlanCt coverageList, Long planDefId, TPrdPlanDefinition productPlanDef) {
		if (n < 4) {
			List<TPrdPlanCt> childPlanCoverageList = coverageList.getChildPlanCoverageList();
			List<TPrdPlanSharedCtGroup> planSharedGroupList = productPlanDef.getPlanSharedGroupList();
			Long planSharedId = coverageList.getPlanSharedId();
			for (TPrdPlanSharedCtGroup planSharedCtGroup : planSharedGroupList) {
				if(planSharedId.equals(planSharedCtGroup.getPlanSharedId())) {
				   coverageList.setSharedGroupType(planSharedCtGroup.getGroupType());
				}
			}
			if (childPlanCoverageList != null && childPlanCoverageList.size() > 0) {
				for (TPrdPlanCt planCoverageLst : childPlanCoverageList) {
					planCoverageLst.setPlanDefId(planDefId);
					planCoverageLst.setParentPlanCoverageId(coverageList.getPlanCoverageId());
					planCoverageLst.setSharedGroupType(coverageList.getSharedGroupType());
					tPrdPlanCtMapper.insert(planCoverageLst);

					TPrdPlanCtDetail productPlanCoverageDetail = planCoverageLst.getProductPlanCoverageDetail();
					if (productPlanCoverageDetail != null) {
						productPlanCoverageDetail.setPlanCoverageId(planCoverageLst.getPlanCoverageId());
					}
					tPrdPlanCtDetailMapper.insert(productPlanCoverageDetail);

					List<TPrdPlanLimit> tPrdPlanLimit = planCoverageLst.getPlanLimitList();
					if (tPrdPlanLimit != null && tPrdPlanLimit.size() > 0) {
						for (TPrdPlanLimit planLimit : tPrdPlanLimit) {
							planLimit.setPlanCoverageId(planCoverageLst.getPlanCoverageId());
							tPrdPlanLimitMapper.insert(planLimit);
						}
					}

					List<TPrdPlanCtRate> tPrdPlanCtRate = planCoverageLst.getPlanCoverageRateList();
					if (tPrdPlanCtRate != null && tPrdPlanCtRate.size() > 0) {
						for (TPrdPlanCtRate planCtRate : tPrdPlanCtRate) {
							planCtRate.setPlanCoverageId(planCoverageLst.getPlanCoverageId());
							tPrdPlanCtRateMapper.insert(planCtRate);
						}
					}
					getChild(n, planCoverageLst, planDefId, productPlanDef);
				}
			}
		} else {
			return;
		}
	}
}
