package com.ccic.salesapp.noncar.repository.basedb.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.EInvoiceApplicationForAppDTO;
import com.ccic.salesapp.noncar.dto.ElectronicInvoicingDTO;
import com.ccic.salesapp.noncar.dto.ImgBatch;
import com.ccic.salesapp.noncar.dto.NoncarImgRiskVo;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.NoncarPlanCt;
import com.ccic.salesapp.noncar.dto.PaymentInformationVo;
import com.ccic.salesapp.noncar.dto.PlanAttach;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.dto.submitUwVo.RespSubmitUwVo;
import com.ccic.salesapp.noncar.dto.submitUwVo.SubmitProposalInfoList;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.bean.InvoiceLinkQueryRequest;
import com.ccic.salesapp.noncar.repository.basedb.po.PlanStrategy;

@Mapper
public interface StoreInsureInfoMapper {

	StoreInsureInfo selectById(Long id);

	StoreInsureInfo selectBySureInfoNo(String insureNo);

	void updateInsureInfoById(Map<String, String> map);

	int doUpdate(StoreInsureInfo insureInfo1);

	void doInsert(StoreInsureInfo insureInfo);

	List<StoreInsureInfo> selectOrderList(StoreQueryListRequestVO listQueryReqVO);

	StoreInsureInfoFormulasResponseVO selectInsureInfoAndFormulas(String proposalNo);

	StoreInsureInfoFormulasResponseVO selectInsureInfoById(BigDecimal sumPremium);

	int upPolicyNoInfo(Map<String, String> param);

	void doDeleteById(Map<String, Object> map);

	int updateSignStatus(StoreInsureInfo storeInsureInfo);

	int upPayStsate(Map<String, Object> map);

	int upNoPayStsate(Map<String, Object> map);

	int upUnderwritingStatus(Map<String, Object> map);

	StoreInsureInfo selectByPolicyNo(String policyNo);

	List<EInvoiceApplicationForAppDTO> findelectronicInvoicingApp(EInvoiceApplicationForAppDTO request);

	List<InvoiceLinkQueryRequest> findonePolicyno(String policyNo);

	void insertResponeFileload(ImgBatch imgBatch);

	List<ImgBatch> findh5imgQueryDownServiceOnePolicy(String InsurNo);

	int updateInsureInfoByIdorWithholdNo(Map<String, String> map);

	void addUploadImageMessage(ImgBatch imgBatch);

	List<ElectronicInvoicingDTO> findeinvoiceInquiry(ElectronicInvoicingDTO request);

	int uploadImageFlag(Map<String, Object> map);

	void doDeleteZCById(Map<String, Object> map);

	void fupNoPayStsate(Map<String, Object> map);

	void fupPolicyNoInfo(Map<String, String> param);

	NoncarOrder selectBynoncarOrder(String insureNo);

	NoncarOrder selectNoncarOrderByInsureNo(String insureNo);

	List<NoncarOrder> findInsuredIdType(String orderId);

	int updateSignStatusTwo(NoncarOrder noncarOrder);

	PlanStrategy selectByStrategyId(Integer strategyId);

	List<PlanAttach> findByPlanIdOrUrl(Integer planId);


//	NoncarPlanCt findByResponsibilityCode(Integer planId);

	List<PlanAttach> findByProductClause(String proposalNo);

	List<PlanAttach> findBylistReadInform(String proposalNo);

	List<PlanAttach> findBylistActoionInform(String proposalNo);

	NoncarPlanCt findByAmountInsured(Map<String, Object> map);

	int upUnderwritingStatusTwo(Map<String, Object> map);

	int upPayStsateTwo(Map<String, Object> map);

	NoncarOrder selectBySureInfoNoTwo(ApplyPayVo applyPayVo);

	TNoncarInsureZcResVO queryZc(String orderId);

	NoncarOrder queryZcTwo(String orderId);

	int updateZcInfoTwo(NoncarOrder req);

	int doUpdateTwo(NoncarOrder noncarOrder);

	List<NoncarPlanCt> findBySchemeCode(Map map);

	NoncarOrder findInsureByPolicyMobile(String proposalNo);

	NoncarOrder findPaymentInformationList(String proposalNo);

	List<PaymentInformationVo> findByInsureName(String proposalNo);

	void doDeleteByIdTwo(Map<String, Object> map);

	NoncarImgRiskVo findByNocarImgRisk(String riskCodeProductCode);

	List<PlanAttach> findInsuranceNoticeMap(Integer integer);

	PlanAttach findfindTbxzz(String proposalNo);

	PlanAttach findhealthNotificationList(Integer planId);

	List<NoncarOrder> findInsuredIdTyperole(String orderId);

	List<NoncarOrder> findInsuredIdTyper(String orderId);

	List<NoncarOrder> findInsuredIdTypeapp(String orderId);

	void updateSubmitMiddleStatus(String proposalNo);
	void updatechargeTime(Map map);

	List<AccidentalInjuryInfo> findInsuredsById(Long id);

	NoncarOrder queryZcSubmission(String orderId);

	NoncarOrder selectNoncarOrderByInsureNoSubmission(String insureNo);

	void updateStatusOrderByRelationId(Long relationOrderId);

	List<NoncarOrder> findNoncarOrderByRelationOrderId(String id);

	NoncarOrder findApplicaIdTypeSubmission(String id);

	NoncarOrder findInsuredIdTypeSubmission(String id);

	List<NoncarOrder> findTargetInsureNoByRelationOrderId(Long relationOrderId, String insureNo);

	int updateSignStatusSubmission(NoncarOrder noncarOrder);

	List<SubmitProposalInfoList> findByProposalNoAndProductLine(String submissionNo);

	NoncarOrder selectByNocarInfoNo(String submissionNo);

	List<NoncarPlanCt> findProductCodeAndNameByCouse(Map map);

	NoncarImgRiskVo findByNocarImgRiskAndRiskType(String riskCodeProductCode, String insuranceCategory);

	NoncarOrder findByOrderIsIssueAfterPay(String submissionNo);

	void fupPolicyNoInfoSubmitMidd(Map<String, String> param);
	
    void updateSignStatusSubmissionByRelationOrderId(String id);

	void PolicyNoInfoSubmitMiddJf(Map<String, String> param);

	void updateSubmitMiddleStatusJf(String submissionNo);

	NoncarOrder selectNoncarOrderBySubmission(String submission);

	List<NoncarPlanCt> findBySchemeCodeZh(Map map);
}
