package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.PolicyCustomer;
import com.ccic.salesapp.noncar.dto.PropertyTrialRequest;
import com.ccic.salesapp.noncar.dto.order.request.Element;
import com.ccic.salesapp.noncar.dto.order.request.OrderRequest;
import com.ccic.salesapp.noncar.dto.order.request.Product;
import com.ccic.salesapp.noncar.dto.request.InsuredGroup;
import com.ccic.salesapp.noncar.dto.request.accidentquote.AccidentQuoteRequest;
import com.ccic.salesapp.noncar.dto.request.accidentquote.CustomerDeclaration;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PersonInsured;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyLob;
import com.ccic.salesapp.noncar.dto.request.accidentquote.PolicyRisk;
import com.ccic.salesapp.noncar.dto.request.accidentquote.Vehicle;
import com.ccic.salesapp.noncar.repository.basedb.mapper.OrderCtMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PersonMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PlanMethodMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.UtilMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.Person;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanInfo;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanMethod;
import com.ccic.salesapp.noncar.service.AccidentQuoteService;
import com.ccic.salesapp.noncar.service.DoBeforeRequestService;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import com.ccic.salessapp.common.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoBeforeRequestServiceImpl implements DoBeforeRequestService {
	
	@Autowired
	UtilMapper utilMapper;
	
	@Autowired
	AccidentQuoteService accidentQuoteService;
	
	@Autowired
	OrderCtMapper orderCtMapper;
	
	@Autowired
	PlanMethodMapper planMethodMapper;
	
	@Autowired
	PersonMapper personMapper;

	@Override
	public void doBefore(AccidentQuoteRequest request,OrderRequest orderRequest) throws Exception {
		//指定机构归属到上海分公司
		//2020-10-21 核心代理人查询不到取消这个处理逻辑
		//spComCode(request,orderRequest); 
		//根据职业代码为职业类别赋值
		doWithPersonInsured(request,orderRequest);
		//处理标的分组
		doWithInsuredGroup(request,orderRequest);
		//EXD产品引擎号和车架号，如果是上牌的车，填写了车牌号车架号和引擎号可以不填，默认送17个0和6个0
		doWithEXD(request,orderRequest);
	}
	
	/**
	 * EXD产品引擎号和车架号，如果是上牌的车，填写了车牌号车架号和引擎号可以不填，默认送17个0和6个0
	 * @param request
	 * @param orderRequest
	 */
	public void doWithEXD(AccidentQuoteRequest request,OrderRequest orderRequest) {
		if("EXD".equals(request.getProductCode())) {
			List<PolicyLob> policyLobList = request.getPolicyLobList();
			for (PolicyLob policyLob : policyLobList) {
				List<Vehicle>  vehicleList = policyLob.getVehicleList();
				if(vehicleList != null) {
					for (Vehicle vehicle : vehicleList) {
						if(!"新车未上牌".equals(vehicle.getLicenseNo())) {
							if(StringUtils.isBlank(vehicle.getEngineNo())) {
								vehicle.setEngineNo("000000");
							}
							if(StringUtils.isBlank(vehicle.getVin())) {
								vehicle.setVin("00000000000000000");
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 处理标的分组
	 * @param request
	 * @param orderRequest
	 */
	public void doWithInsuredGroup(AccidentQuoteRequest request,OrderRequest orderRequest) {
		String insuredListType = request.getInsuredListType();
		List<PolicyLob> policyLobList = request.getPolicyLobList();
		
		for (PolicyLob policyLob : policyLobList) {
			List<PolicyRisk> policyRiskList =  policyLob.getPolicyRiskList();
			List<Vehicle>  vehicleList = policyLob.getVehicleList();
			Long t = 0l;
			if(vehicleList != null) {
				for (Vehicle vehicle : vehicleList) {
					if(vehicle != null && vehicle.getApprovalSeatCount() != null) {
						t = t + (vehicle.getApprovalSeatCount() == null ? 0 : vehicle.getApprovalSeatCount());
					}
				}
			}
			
			for (PolicyRisk policyRisk : policyRiskList) {
				List<InsuredGroup> InsuredGroupList = policyRisk.getInsuredGroupList();
				for (InsuredGroup insuredGroup : InsuredGroupList) {
					//约定有分户  且 分组下被保人数量为0 ，取座位数
					if("2".equals(insuredListType) &&  "0".equals(insuredGroup.getInsuredCount())  ) {
						insuredGroup.setInsuredCount(t+"");
					}
				}
			}
		}
	}
	
	
	/**
	 * 处理被保人
	 * @param request
	 * @throws Exception 
	 */
	public void doWithPersonInsured(AccidentQuoteRequest request,OrderRequest orderRequest) throws Exception {
		
		PlanInfo planInfo = (PlanInfo)orderRequest.getMap().get("planInfo");
		List<PersonInsured> checkPersonInsuredList = new ArrayList<PersonInsured>();
		if("Y".equals(orderRequest.getIsRenewal()) && StringUtils.isNotBlank(orderRequest.getRelationPolicyNo())) {
			//待检查被保人列表
			AccidentQuoteRequest hisreq = accidentQuoteService.getHistoryPolicyInfo(orderRequest.getRelationPolicyNo());
			checkPersonInsuredList = hisreq.getPolicyLobList().get(0).getPolicyRiskList().get(0).getPersonInsuredList();
		}
		
		List<String> ct = new ArrayList<String>();
		List<Product> productList =  orderRequest.getProductList();
		for (Product product : productList) {
			ct.add(product.getKindCode());
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("planId", orderRequest.getPlanId());
		param.put("cList", ct);
		List<String> groupTypes = orderCtMapper.selectOrderCtGroupType(param);
		String guaranteeType = null;
		
		
		List<PolicyLob> policyLobList = request.getPolicyLobList();
		for (PolicyLob policyLob : policyLobList) {
			List<PolicyRisk> policyRiskList =  policyLob.getPolicyRiskList();
			for (PolicyRisk policyRisk : policyRiskList) {
				List<PersonInsured> personInsuredList = policyRisk.getPersonInsuredList();
				if(personInsuredList == null) continue;
				for (PersonInsured personInsured : personInsuredList) {
					if(StringUtils.isNotBlank(personInsured.getOccupationCode())) {
						String occupationType = utilMapper.getOccupationGrage(personInsured.getOccupationCode());
						personInsured.setOccupationType(occupationType);
					}
					
					//若方案必须有社保 则默认给被保人设置有社保标识
					PlanMethod planMethod =  planMethodMapper.selectByPlanId(planInfo.getId());
					if(StringUtils.isBlank(personInsured.getSocialSecurityFlag()) && planMethod != null && 1== planMethod.getHasSocialInsurance()) {
						personInsured.setSocialSecurityFlag("Y");
					}
					if(StringUtils.isBlank(personInsured.getSocialSecurityFlag()) && planMethod != null && 2== planMethod.getHasSocialInsurance()) {
						personInsured.setSocialSecurityFlag("N");
					}
					
					//健康告知
					if("Y".equals(orderRequest.getIsDeclaration()) || ( "Y".equals(orderRequest.getIsRenewal()) && StringUtils.isNotBlank(orderRequest.getRelationPolicyNo()) ) /*&& accidentQuoteService.isNewInsured(personInsured, checkPersonInsuredList)*/) {
						List<CustomerDeclaration> customerDeclarationList =new ArrayList<CustomerDeclaration>();
						CustomerDeclaration customerDeclaration = new CustomerDeclaration();
						customerDeclaration.setDeclarationForm("1");
						customerDeclaration.setDeclarationResult("01");
						customerDeclaration.setDeclarationType("01");
						customerDeclarationList.add(customerDeclaration);
						personInsured.setCustomerDeclarationList(customerDeclarationList);
					}
					
					if(groupTypes != null && !groupTypes.isEmpty()) {
						if(groupTypes.size() > 1) {
							//区分被保人保障类型（1-普通分组，2-家庭分组，3-兼有）。例如被保人只享有家庭分组的保障，则传2；如果家庭分组和普通分组都享有，则传3
							guaranteeType = "3";
							personInsured.setGuaranteeType(guaranteeType);
						}else if(groupTypes.size() == 1){
							//T_PRD_PLAN_SHARED_CT_GROUP表GROUP_TYPE字段，1:公共分组 2:一般分组 3：家庭分组
							
							if("3".equals(groupTypes.get(0)) ) {
								guaranteeType = "2";//家庭分组
							}else {
								guaranteeType = "1";//普通分组
							}
							personInsured.setGuaranteeType(guaranteeType);
						}
					}
					
				}
			}
		}
	}
	
	
	@Override
	public void doBefore(PropertyTrialRequest request,OrderRequest orderRequest) {
		
		//spComCode(request,orderRequest);
		
		List<PolicyCustomer> policyCustomerList = request.getPolicyCustomerList();
		for (PolicyCustomer policyCustomer : policyCustomerList) {
			if ("801".equals(policyCustomer.getIdType()) || "994".equals(policyCustomer.getIdType()) || "810".equals(policyCustomer.getIdType())) {
				policyCustomer.setPartyCategory("02"); // 个人01 单位02
				policyCustomer.setOrgContactPersonName(policyCustomer.getCustomerName()); //机构-联系人姓名        
				policyCustomer.setOrgContactPersonTel(policyCustomer.getIndiMobile());
				policyCustomer.setOrgRegistrationTel(policyCustomer.getIndiMobile()); 
				policyCustomer.setOrgContactEmail(policyCustomer.getEmail());
			}else {
				policyCustomer.setPartyCategory("01"); // 个人01 单位02
			}
		}

	}
	
	/***
	 * 指定机构归属到上海分公司
	 * @param request
	 * @param orderRequest
	 */
	public void spComCode(com.ccic.salessapp.common.outService.rest.common.bean.RequestBody request,OrderRequest orderRequest) {
		List<String> spComCodeList = new ArrayList<String>();
		spComCodeList.add("3100");
		//spComCodeList.add("3101");
		spComCodeList.add("3103");
		spComCodeList.add("3109");
		spComCodeList.add("3112");
		if(request instanceof PropertyTrialRequest) {
			PropertyTrialRequest req = (PropertyTrialRequest)request;
			
			for (String string : spComCodeList) {
				if(req.getOrgCode().startsWith(string)) {
					req.setOrgCode("31010000");
				}
			}
		}
		
		if(request instanceof AccidentQuoteRequest) {
			AccidentQuoteRequest req = (AccidentQuoteRequest)request;
			
			for (String string : spComCodeList) {
				if(req.getOrgCode().startsWith(string)) {
					req.setOrgCode("31010000");
				}
			}
		}
	}

	@Override
	public void beforeSaveData(OrderRequest orderRequest) throws Exception {
		List<Element> list = orderRequest.getMetaDataList();
		for (int i =0 ;i < list.size();i++) {
			Element element = list.get(i);
			//处理雇员清单 不在订单详情中显示
			if("empList".equals(element.getCode())) {
				//list.set(i, null);
			}
			//模板不存库
			if("empManifestTemp".equals(element.getCode())) {
				//list.set(i, null);
			}
			
		}
		//list.removeAll(Collections.singleton(null));
		
	}

	@Override
	public void afterInitRequestBody(RequestBody request, OrderRequest orderRequest) {
		//非车
		if(request instanceof PropertyTrialRequest) {
			PropertyTrialRequest req = (PropertyTrialRequest) request;
			afterInitRequestBody(req,orderRequest);
		}
		//意健
		if(request instanceof AccidentQuoteRequest) {
			AccidentQuoteRequest req = (AccidentQuoteRequest) request;
			afterInitRequestBody(req,orderRequest);
		}
	}
	
	
	private void afterInitRequestBody(AccidentQuoteRequest request, OrderRequest orderRequest) {
		
	}
	
	
	private void afterInitRequestBody(PropertyTrialRequest request, OrderRequest orderRequest) {
		List<Element> list = orderRequest.getMetaDataList();
		int personNum = 1;
		for (int i =0 ;i < list.size();i++) {
			Element element = list.get(i);
			//处理雇员清单 不在订单详情中显示
			if("empList".equals(element.getCode())) {
				String personListId = element.getValue();
				List<Person> personList =  personMapper.selectByPersonListId(Long.parseLong(personListId));
				List<com.ccic.salesapp.noncar.dto.request.noncar.Person> noncarPersonList = new ArrayList<com.ccic.salesapp.noncar.dto.request.noncar.Person>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				com.ccic.salesapp.noncar.dto.PolicyLob policyLob = request.getPolicyLobList().get(0);
				for (Person person : personList) {
					//111-居民身份证
					person.setPersonIdType(person.getPersonIdType().split("-")[0]);
					com.ccic.salesapp.noncar.dto.request.noncar.Person p = new com.ccic.salesapp.noncar.dto.request.noncar.Person();
					org.springframework.beans.BeanUtils.copyProperties(person, p);
					try {
						if(person.getEffectiveDate() != null) {
							p.setEffectiveDate(sdf.format(person.getEffectiveDate()));
						}
						if(person.getExpiryDate() != null) {
							p.setExpiryDate(sdf.format(person.getExpiryDate()));
						}
					} catch (Exception e) {
						throw new PlatformBaseException(e, 0);
					}
					noncarPersonList.add(p);
				}
				personNum = noncarPersonList.size();
				policyLob.setPersonListList(noncarPersonList);
				if(policyLob.getTotalNumberOfCopies()==null) {
					policyLob.setTotalNumberOfCopies("1");
				}
				policyLob.getPolicyRiskList().get(0).setInsuredCount(personNum+"");
				policyLob.setTotalNumberOfCopies((Integer.parseInt(policyLob.getTotalNumberOfCopies())*personNum)+"");
				//关联订单与人员列表信息
				try {
					Person person = new Person();
					person.setPersonListId(Long.parseLong(personListId));
					person.setOrderId((Long)(orderRequest.getMap().get("orderId")));
					personMapper.updateOrderIdByPersonListId(person);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
				}
				break;
			}
		}
	}
	
	
	
}
