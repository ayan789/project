package com.ccic.salesapp.noncar.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ccic.salesapp.noncar.config.SystemConfig;
import com.ccic.salesapp.noncar.controller.StoreOrderCtl;
import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.PupilName;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.ZzbInsureInfo;
import com.ccic.salesapp.noncar.dto.request.CarInfoVO;
import com.ccic.salesapp.noncar.dto.request.StoreOracleRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreOrderDeleteRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.request.ZcOrderNoReqVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.CarInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InvoiceInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PupilNameMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreAccidentalinjuryMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreFormulasMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.TNoncarInsureinfoZcMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.ZzbInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;
import com.ccic.salesapp.noncar.service.AbstractFacade;
import com.ccic.salesapp.noncar.utils.StoreProductPlaceUtilFacade;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.request.UserToken;
import com.ccic.salessapp.common.utils.ArithUtil;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.DateUtils;
import com.ccic.salessapp.common.utils.StringUtils;
import com.ccic.salessapp.common.utils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 下单方法公共类实现
 * 
 * @author wangpeng
 *
 */

@Slf4j
@Service("StoreProductPlaceUtilFacade")
public class StoreProductPlaceUtilFacadeImpl extends AbstractFacade implements StoreProductPlaceUtilFacade {

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	@Autowired
	StoreFormulasMapper storeFormulasMapper;

	@Autowired
	ZzbInsureInfoMapper zzbInsureInfoMapper;

	@Autowired
	StoreAccidentalinjuryMapper storeAccidentalinjuryMapper;

	@Autowired
	CarInfoMapper carInfoMapper;

	@Autowired
	PupilNameMapper pupilNameMapper;

	@Autowired
	InvoiceInfoMapper invoiceInfoMapper;

	@Autowired
	private SqlSession session;

	@Autowired
	private TNoncarInsureinfoZcMapper tNoncarInsureinfoZcMapper;

	@Autowired
	StoreOrderCtl storeOrderCtl;

	/**
	 * 根据投保单号投保信息
	 */
	@Override
	public StoreInsureInfo findStoreInsureInfoByInsureNo(String insureNo) {

		StoreInsureInfo storeInsureInfo = null;

		try {

			storeInsureInfo = storeInsureInfoMapper.selectBySureInfoNo(insureNo);

		} catch (Exception e) {
			log.error("根据投保单号查询投保单信息失败！", e);
		}
		return storeInsureInfo;
	}

	/**
	 * 获取订单列表
	 */
	@Override
	public List<StoreInsureInfo> storeListQueryDao(StoreQueryListRequestVO listQueryReqVO) {

		List<StoreInsureInfo> list = new ArrayList<StoreInsureInfo>();

		try {
			list = storeInsureInfoMapper.selectOrderList(listQueryReqVO);

			Date nowDate = new Date();

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SystemConfig cofig = new SystemConfig("system");
					String desPassword = cofig.getResourceValue("desPassword");
					list.get(i).setDesProposalNo(DESEncryptUtil.encrypt(list.get(i).getInsureNo(), desPassword));// des加密后的投保单号
					if ((list.get(i).getStartDate() != null && list.get(i).getStartDate().getTime() > nowDate.getTime())
							|| "FM29".equals(list.get(i).getProductCode())
							|| "FM07".equals(list.get(i).getProductCode())) {
						list.get(i).setPayFlag("1");
					} else {
						list.get(i).setPayFlag("0");
					}
				}
			}

		} catch (Exception e) {
			log.error("订单列表查询异常!：", e);
		}
		return list;
	}

	/**
	 * 根据投保单号查询订单详情和产品信息
	 * 
	 * @throws Exception
	 */
	@Override
	public StoreInsureInfoFormulasResponseVO findStoreInsureInfoAndFormulasByInsureNo(String insureNo)
			throws Exception {

		StoreInsureInfoFormulasResponseVO storeInsureInfoFormulas = null;

		try {
			storeInsureInfoFormulas = storeInsureInfoMapper.selectInsureInfoAndFormulas(insureNo);

			List<StoreFormulas> list = null;
			StoreInsureInfoFormulasResponseVO storeInsureInfoFormulas2 = null;
			Map<String, String> paramMap = new HashMap<String, String>();

			if (storeInsureInfoFormulas != null) {
				paramMap.put("formulaCode", storeInsureInfoFormulas.getFormulaCode());// 方案代码

				BigDecimal id = new BigDecimal(storeInsureInfoFormulas.getId().toString());
				storeInsureInfoFormulas2 = storeInsureInfoMapper.selectInsureInfoById(id);// 关联投保单ID

				if (storeInsureInfoFormulas2 != null) {
					storeInsureInfoFormulas.setSumPremium(
							String.valueOf(ArithUtil.add(Double.parseDouble(storeInsureInfoFormulas.getSumPremium()),
									Double.parseDouble(storeInsureInfoFormulas2.getSumPremium()))));
					paramMap.put("formulaCode2", storeInsureInfoFormulas2.getFormulaCode());
				}
				if (insureNo.length() >= 24) {
					paramMap.put("newCore", "1");
				} else {
					paramMap.put("newCore", "0");
				}
				paramMap.put("productCode", storeInsureInfoFormulas.getProductCode());// 产品代码
				ZzbInsureInfo zzbInsureInfo = null;
				if (("LBU").equals(storeInsureInfoFormulas.getInsuranceCode())
						|| ("QPU").equals(storeInsureInfoFormulas.getInsuranceCode())) {
					list = storeFormulasMapper.queryFormulasInfoLBU(storeInsureInfoFormulas.getFormulaCode());

					Map<String, Object> param = new HashMap<String, Object>();
					param.put("insureInfoId", storeInsureInfoFormulas.getId());

					zzbInsureInfo = zzbInsureInfoMapper.selectByInsuredInfoId(param);
				} else {

					list = storeFormulasMapper.queryFormulasInfo(paramMap);

				}

				BigDecimal dec1 = new BigDecimal(storeInsureInfoFormulas.getId().toString());
				List<AccidentalInjuryInfo> accidentalInjuryInfos = storeAccidentalinjuryMapper.doSelect(dec1);

				BigDecimal dec2 = new BigDecimal(storeInsureInfoFormulas.getId().toString());
				CarInfoVO carInfoVO = carInfoMapper.queryCarInfo(dec2);

				BigDecimal dec3 = new BigDecimal(storeInsureInfoFormulas.getId().toString());
				List<PupilName> pupilName = pupilNameMapper.doSelect(dec3);

				// 发票信息
				BigDecimal dec4 = new BigDecimal(storeInsureInfoFormulas.getId().toString());
				InvoiceInfo invoiceInfo = invoiceInfoMapper.queryInvoiceInfo(dec4);

				// 是否代理字段及代理人信息
				String orderNo = storeInsureInfoFormulas.getOrderNo();
				ZcOrderNoReqVO zcReq = new ZcOrderNoReqVO();
				zcReq.setOrderNo(orderNo);
				HttpResult<?> ZcResVO = storeOrderCtl.queryZc(zcReq);
				TNoncarInsureZcResVO tNoncarInsureZcResVO = (TNoncarInsureZcResVO) ZcResVO.getData();

				AgentInfoVO agentInfo = null;
				String IsAgent = "";
				if (tNoncarInsureZcResVO != null) {
					StoreOracleRequestVO stu = tNoncarInsureZcResVO.getStoreOracleRequestVO();
					IsAgent = stu.getIsAgent();
					agentInfo = stu.getAgentInfo();
					if (("WTN").equals(storeInsureInfoFormulas.getInsuranceCode())) {
						Map<String, String> content = new HashMap<String, String>();
						content.put("aKZEXClause", stu.getaKZEXClause());
						content.put("aEXTZClause", stu.getaEXTZClause());
						content.put("aEXYLClause", stu.getaEXYLClause());
						content.put("aEXZZClause", stu.getaEXZZClause());
						storeInsureInfoFormulas.setContent(content);
					}
				}

				storeInsureInfoFormulas.setIsAgent(IsAgent);
				storeInsureInfoFormulas.setAgentInfo(agentInfo);

				storeInsureInfoFormulas.setPupilNameList(pupilName);
				storeInsureInfoFormulas.setCarInfoVO(carInfoVO);
				storeInsureInfoFormulas.setInvoiceInfo(invoiceInfo);
				storeInsureInfoFormulas.setFormulas(list);
				storeInsureInfoFormulas.setAccidentalInjuryInfos(accidentalInjuryInfos);
				storeInsureInfoFormulas.setZzbInsureInfo(zzbInsureInfo);// zzb返回客户门店信息

				String startDate = storeInsureInfoFormulas.getStartDate();
				String productCode = list != null && list.size() > 0 ? list.get(0).getProductCode() : "";

				if (startDate != null && !"".equals(startDate)) {
					long time = DateUtils.parse(startDate.substring(0, 10), "yyyy-MM-dd").getTime();
					if ((time > new Date().getTime()) || "FM29".equals(productCode) || "FM07".equals(productCode)) {
						storeInsureInfoFormulas.setPayFlag("1");
					} else {
						storeInsureInfoFormulas.setPayFlag("0");
					}
				}

			}

		} catch (Exception e) {
			log.error("订单详情查询异常!:", e);
			throw e;
		}
		return storeInsureInfoFormulas;
	}

	/**
	 * 通过投保单 更新保单信息
	 */
	@Override
	@Transactional
	public int upPolicyNo(String insureNo, String policyNo) {
		int row = 0;
		Map<String, String> param = new HashMap<String, String>();
		param.put("insureNo", insureNo);
		param.put("policyNo", policyNo);
		try {

			row = storeInsureInfoMapper.upPolicyNoInfo(param);

		} catch (Exception e) {
			log.error("投保单信息更新失败！", e);
		}
		return row;
	}

	/**
	 * 删除订单
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public HttpResult<?> deleteStoreOrder(StoreOrderDeleteRequestVO reqvo) throws Exception {

		try {
			if ("0".equals(reqvo.getIsMetaData())) {
				List<String> ids = reqvo.getId();
				List<String> zcIds = new ArrayList<String>();
				List<String> insureinfoIds = new ArrayList<String>();
				for (String id : ids) {
					if ("T".equals(id.charAt(0) + "")) {
						id = id.replace("T", "");
						zcIds.add(id);
					} else {
						insureinfoIds.add(id);
						// 如果已经生成投保单，需要连带更新暂存订单状态
						StoreInsureInfo insureInfo = storeInsureInfoMapper.selectById(Long.parseLong(id));
						if (insureInfo != null) {
							TNoncarInsureZcResVO res = tNoncarInsureinfoZcMapper.queryZc(insureInfo.getOrderNo());
							if (res != null) {
								zcIds.add(res.getId() + "");
							}
						}

					}
				}

				String channelCode = reqvo.getChannelCode() != null ? reqvo.getChannelCode() : "";
				String userCode = reqvo.getUserCode() != null ? reqvo.getUserCode() : "";

				Map<String, Object> map = new HashMap<String, Object>();

				if (StringUtils.isNotBlank(userCode)) {
					map.put("nclUserCode", userCode);
				} else {
					map.put("operatorNo", userCode);
				}
				if (!insureinfoIds.isEmpty()) {
					map.put("idList", insureinfoIds);
					storeInsureInfoMapper.doDeleteById(map);
					log.info("删除订单" + insureinfoIds);
				}

				try {
					UserToken user = TokenUtil.getToken();
					map = new HashMap<String, Object>();
					if (user == null) {
						return HttpResult.error(0, "登录信息已失效！");
					}
					map.put("storeCode", user.getStoreCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!zcIds.isEmpty()) {
					map.put("idList", zcIds);
					storeInsureInfoMapper.doDeleteZCById(map);
					log.info("删除暂存订单" + zcIds);
				}
				return HttpResult.success("Deleting completed！", 1, "删除订单成功！");
			} else {
				List<String> ids = reqvo.getId();
				List<String> zcIds = new ArrayList<String>();
				List<String> insureinfoIds = new ArrayList<String>();
				for (String id : ids) {
					if ("T".equals(id.charAt(0) + "")) {
						id = id.replace("T", "");
						zcIds.add(id);
					} else {
						insureinfoIds.add(id);
					}
				}
				String channelCode = reqvo.getChannelCode() != null ? reqvo.getChannelCode() : "";
				String userCode = reqvo.getUserCode() != null ? reqvo.getUserCode() : "";
				Map<String, Object> map = new HashMap<String, Object>();
				if (StringUtils.isNotBlank(userCode)) {
					map.put("nclUserCode", userCode);
				} else {
					map.put("operatorNo", userCode);
				}
				if (!insureinfoIds.isEmpty()) {
					map.put("idList", insureinfoIds);
					storeInsureInfoMapper.doDeleteByIdTwo(map);
					log.info("删除订单" + insureinfoIds);
				}

				try {
					UserToken user = TokenUtil.getToken();
					map = new HashMap<String, Object>();
					if (user == null) {
						return HttpResult.error(0, "登录信息已失效！");
					}
					map.put("storeCode", user.getStoreCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!zcIds.isEmpty()) {
					map.put("idList", zcIds);
					storeInsureInfoMapper.doDeleteZCById(map);
					log.info("删除暂存订单" + zcIds);
				}
				return HttpResult.success("Deleting completed！", 1, "删除订单成功！");
			}
		} catch (Exception e) {

			log.error("删除订单异常,请确认参数无误！", e);
			return HttpResult.error(0, "删除订单异常,请确认参数无误！！");

		}
	}
}
