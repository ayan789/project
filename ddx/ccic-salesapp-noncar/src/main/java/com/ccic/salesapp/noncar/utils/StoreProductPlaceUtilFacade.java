package com.ccic.salesapp.noncar.utils;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.StoreOrderDeleteRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salessapp.common.core.web.HttpResult;



/**
 * 
 * 下单方法公共类
 * @author wangpeng
 *
 */

@Service
public interface StoreProductPlaceUtilFacade {

	/**
	 * 根据投保单号投保信息
	 * @param chanceId
	 * @return
	 */
	public StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo);


	/**
	 * 获取订单列表
	 * @param storeQueryListRequestVO listQueryReqVO
	 * @return
	 */
	public	List<StoreInsureInfo> storeListQueryDao(StoreQueryListRequestVO listQueryReqVO);


	/**
	 * 查询订单详情和产品信息
	 * @param proposalNo
	 */
	public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String proposalNo)throws Exception;

	/**
	 * 通过投保单 更新保单信息
	 * @param proposalNo
	 */
	public int upPolicyNo(String insureNo,String policyNo);

	/**
	 * 删除订单
	 * @param proposalNo
	 */
	public HttpResult<?> deleteStoreOrder(StoreOrderDeleteRequestVO reqvo)throws Exception;


	//	/**
	//	 * 保单 下单记录信息
	//	 * @param insureInfo
	//	 * @return
	//	 * @throws Exception 
	//	 */
	//	public StoreInsureInfo saveInsureInfo( StoreInsureInfo info,StoreOracleRequestVO storeReqVO,ResultVO result,UserVO userVO) throws Exception;
	//	/**
	//	 * 更新 下单记录信息
	//	 * @param insureInfo
	//	 * @return
	//	 */
	//	public int updateInsureInfo(StoreInsureInfo insureInfo,ResultVO result);
	//	
	//	/**
	//	 * 申请支付号并保存记录
	//	 * @param insureInfo
	//	 * @param result
	//	 * @return
	//	 */
	//	public StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result);
	//	
	//	/**
	//	 * 货运险试算
	//	 * @param req
	//	 * @return
	//	 */
	//	public ProposalGenerateResponseBody  quoteProposalGenerateInfo(ProposalGenerateRequestBody proposalGenerateRequestBody,ResultVO result,UserVO userVO);
	//
	//	/**
	//	 * 意健险试算
	//	 * @param req
	//	 * @return
	//	 */
	//	//public QuotePriceResponseDTO quotePriceInfo(QuotePriceRequestDTO quotePriceRequestDTO,ResultVO result,UserVO userVO);
	//	
	//	/**
	//	 * 意健险提交核保接口
	//	 * 
	//	 */
	//	//public void preUndwrt( String buinessno,ResultVO result);
	//	/**
	//	 * 财产险提交核保接口
	//	 * @param InsureNo 投保单号
	//	 * @param riskCode 险种代码
	//	 */
	//	public void SubmitUnderwrite(String InsureNo,String riskCode,ResultVO result);
	//	
	//	

	//	/**
	//	 * 财产险试算
	//	 * @param req
	//	 * @return
	//	 */
	//	//public ProposalGenerateResponseDTO quotePropertyGenerateInfo(ProposalGenerateRequestDTO proposalGenerateRequestDTO,ResultVO result,UserVO userVO);
	//	
	//	/**
	//	 * 获取方案信息
	//	 * @param req
	//	 * @return
	//	 */
	//	//public StoreFormulas getProductFormula(String formulaCode,String productCode, String newCore);
	//	
	//	
	//	/**
	//	 * 获取方案列表表
	//	 * @param productCode
	//	 * @return
	//	 */
	//	public List<StoreFormulas> getProductFormulaList(String productCode, String newCore);
	//	/**
	//	 * 获取序列号
	//	 * @param req
	//	 * @return
	//	 */
	//	
	//	 public	String getTradeSerialNo();
	//	 	
	//	 public	List<StoreInsureInfo> storeListQueryDao(StoreQueryListRequestVO listQueryReqVO);
	//	
	//	
	//	 /**
	//	  * 查询代理信息
	//	  * @param req
	//	  * @return
	//	  */
	//	 public AgentInfoVO selectAgentInfo(StoreOracleRequestVO req,ResultVO result);
	//	public void getHandlerInfo(StoreOracleRequestVO req);

	//	/*public JsonVO deleteStoreOrder(StoreOrderDeleteRequestVO reqvo,LoginUser loginUser)throws Exception;
	//	public List<Store4sInfoVO> queryStore4sInfoDao();
	//	public Store4sInfoVO check4sStorepwdDao(Store4sInfoRequestVO store4sInfo);
	//	public void add4sStoreTokenDao(Store4sTokenInfoVO store4sTokenInfo);
	//	public Store4sTokenInfoVO check4sStoreTokenDao(Store4sInfoRequestVO store4sInfo);
	//	public Double balanceQuery(ResultVO result, String accountCode);
	//	public void updateInsureInfoById(String id, ResultVO result);
	//	public WithholdOrderRespDTO withholdOrderService(ResultVO result, String accountCode, StoreInsureInfo storeInsureInfo);
	//	public void paySuccess(ResultVO result, StoreInsureInfo storeInsureInfo, String withholdNo,String accountCode);
	//	public void updateInsureInfoByIdorWithholdNo(String id, WithholdOrderRespDTO withholdOrderResp , String accountCode,String itemName) throws Exception;
	//	public void withholdStatusPort(String operatorcode, String withholdNo, String accountCode,String paymentNo);
	//	public int updateSignStatus(StoreInsureInfo storeInsureInfo);*/
	// 
}
