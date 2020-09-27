package com.ccic.salesapp.noncar.dto.request;
import java.util.ArrayList;
import java.util.List;
import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.VatInfoVO;
import lombok.Data;




/**
 * 下单接口请求VO
 * @author Administrator
 *
 */
@Data
public class IToProposalRequestVO extends DataVO{

	private String orderNo   ;//订单号  Y 下单时必传  
	private String riskCode  ;//险种代码Y 详见代码2.1.29  
	private String quotationNoCI ;//询价单号  交强险询价单号  
	private String quotationNoBI ;//关联询价单号  商业险询价单号  
	private String proposalNo;//投保单号  投保单修改操作时，投保单号必传
	private String sumPremiun;//总保费  Y   
	private String mergePrint;//意外险合并打印标志Y 意外险合并打印标志，" 分开打印 /不分开打印"



	List<PrivyVO> privy = new ArrayList<PrivyVO>();//联系人信息
	SalesVO sales;//销售人员信息
	private List<CheckVO> checks;//查询校验
//	CarTaxVO carTax;//车船税信息
	List<EngageVO> engage = new ArrayList<EngageVO>();//特别约定
	ProjectChannelVO projectChannel = null;//渠道信息
	List<RelaVO> rela = new ArrayList<RelaVO>();//关联信息
	List<ExtendInfoVO> extendInfo = new ArrayList<ExtendInfoVO>();
	List<CommissionVO> commissions =new ArrayList<CommissionVO>();//手续费信息
	private List<AntiInfoVO> antiInfo = null; //反洗钱信息
	
	private String startDate;//起保日期
	private String endDate;//终保日期
	private String comCode;//归属机构
	private String handlerCode;//经办人代码
	private String handlerName;//经办人名称
	private String jointSaleFlag;//联合销售标识（1-是；0-否）
	private List<JointSaleVO> jointSales;//联合销售信息
	
	private VatInfoVO vatInfo;//增值税信息
	private AutoRenewalVO autoRenewal;//自动续保信息
    private PostInfoVO postInfo;//配送信息


	/*投保人信息*/
	private String applicantName;//投保人姓名
	private String email;//邮箱
	private String mobileNo;//手机号
	private String birthDate ;//出生日期
	private String applicantidentifyType ;//证件类型
	private String applicantidentifyNumber ;//证件号码

	/*被保人信息*/
	private String benefitPolHolderRelaCode;//与投保人关系
	private String insuredName;//被保人姓名
	private String insuredidentifyType ;//证件类型
	private String insured_identify ;//证件号码
	private String insuredmobileNo;//手机号
	private String insuredbirthDate ;//出生日期
	private String socialType ;//社保类型
	

	private String securityInfo;//保障期限

	/*开票信息*/
	private String isInvoice;//是否开票

	private String other;//其他字段

	
	
	
	
}
