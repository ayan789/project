package com.ccic.salesapp.noncar.service;


import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;


import java.util.List;

/**
 * 下单方法公共类
 * @author Administrator
 *
 */
public interface ZzbProductPlaceUtilFacade {
	
	
	/**
	 * 保单 下单记录信息
	 * @param insureInfo
	 * @return
	 * @throws Exception 
	 */
	//public StoreInsureInfo saveInsureInfo(StoreInsureInfo info, ZzbOracleRequestVO storeReqVO, ResultVO result, UserVO userVO) throws Exception;
	/**
	 * 更新 下单记录信息
	 * @param insureInfo
	 * @return
	 */
	//public int updateInsureInfo(StoreInsureInfo insureInfo, ResultVO result);

	/**
	 * 申请支付号并保存记录
	 * @param insureInfo
	 * @param result
	 * @return
	 */
	//public StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo, ResultVO result);

	/**
	 * 货运险试算
	 * @param req
	 * @return
	 */
	//public ProposalGenerateResponseBody  quoteProposalGenerateInfo(ProposalGenerateRequestBody proposalGenerateRequestBody, ResultVO result, UserVO userVO);

	/**
	 * 意健险试算
	 * @param req
	 * @return
	 */
	//public QuotePriceResponseDTO quotePriceInfo(QuotePriceRequestDTO quotePriceRequestDTO, ResultVO result, UserVO userVO);

	/**
	 * 意健险提交核保接口
	 *
	 */
	//public void preUndwrt(String buinessno, ResultVO result);
	/**
	 * 财产险提交核保接口
	 * @param InsureNo 投保单号
	 * @param riskCode 险种代码
	 */
	//public void SubmitUnderwrite(String InsureNo, String riskCode, ResultVO result);
	/**
	 * 根据投保单号投保信息
	 * @param chanceId
	 * @return
	 */
	//public StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo);


	/**
	 * 通过投保单 更新保单信息
	 * @param insureNo
	 * @param policyNo
	 */
	//public int upPolicyNo(String insureNo, String policyNo);
	/**
	 * 财产险试算
	 * @param req
	 * @return
	 */
	//public ProposalGenerateResponseDTO quotePropertyGenerateInfo(ProposalGenerateRequestDTO proposalGenerateRequestDTO, ResultVO result, UserVO userVO);

	/**
	 * 获取方案信息
	 * @param req
	 * @return
	 */
	//public StoreFormulas getProductFormula(String formulaCode, String productCode, String newCore);
	/**
	 * 获取方案信息
	 * @param
	 * @return
	 */
	public StoreFormulas getProductCodeFormula(String formulaCode);


	/**
	 * 获取方案列表表
	 * @param productCode
	 * @return
	 */
	//public List<StoreFormulas> getProductFormulaList(String productCode, String comCode);
	/**
	 * 获取序列号
	 * @param req
	 * @return
	 */

	 //public	String getTradeSerialNo();
	 	/**
		 * 获取订单列表
		 * @param storeQueryListRequestVO listQueryReqVO
		 * @return
		 */
	 //public	List<StoreInsureInfo> storeListQueryDao(StoreQueryListRequestVO listQueryReqVO);


	 /**
	  * 查询代理信息
	  * @param req
	  * @return
	  */
	// public AgentInfoVO selectAgentInfo(ZzbOracleRequestVO req, ResultVO result);
	//public void getHandlerInfo(ZzbOracleRequestVO req);
	/**
	 * 查询订单详情和产品信息
	 * @param proposalNo
	 */
	//public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String proposalNo)throws Exception;

	/**
	* @Title: getZzbPremium
	* @Description: 根据省份代码和人数、产品代码 返回保费
	* @param    provinceCode ：省份代码     ,employeeNum：员工数 ，产品代码:productCode
	* @return Double    返回类型
	* @throws
	*/
	//public Double getZzbPremium(String provinceCode, int employeeNum, String productCode);

	/**
	* @Title: getZzbAmount
	* @Description: 根据人数计算保额
	* @param    employeeNum：员工数
	* @return Double    返回类型
	* @throws
	*/
	//public Double getZzbAmount(int employeeNum);
	
	/**
	 * 根据投保单号投保信息(掌中宝)
	 * @param insureNo
	 * @return
	 */
	//public JsonVO findZzbInsureInfoVOByInsureNo(String insureNo);

}
