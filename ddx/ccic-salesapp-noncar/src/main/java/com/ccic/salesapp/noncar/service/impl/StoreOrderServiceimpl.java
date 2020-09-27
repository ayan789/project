package com.ccic.salesapp.noncar.service.impl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreCertiNoticeRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreOrderDeleteRequestVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryDetailReqVO;
import com.ccic.salesapp.noncar.dto.request.StoreQueryListRequestVO;
import com.ccic.salesapp.noncar.dto.response.StoreInsureInfoFormulasResponseVO;
import com.ccic.salesapp.noncar.dto.response.StoreListQueryResponseVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.TNoncarInsureinfoZcMapper;
import com.ccic.salesapp.noncar.service.StoreOrderService;
import com.ccic.salesapp.noncar.utils.StoreProductPlaceUtilFacade;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.DESEncryptUtil;
import com.ccic.salessapp.common.utils.StringUtils;



/**
 * 
 * 订单管理模块Service实现
 * 
 * @author wangpeng
 * 
 */

@Slf4j
@Service
public class StoreOrderServiceimpl extends com.ccic.salesapp.noncar.service.AbstractFacade implements StoreOrderService{


	@Autowired
	StoreProductPlaceUtilFacade stu;

	@Autowired
	TNoncarInsureinfoZcMapper tNoncarInsureinfoZcMapper;

	@Value("${system.forwardPay}")
	String forwardPay;

	/**
	 * 门店订单列表查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HttpResult<StoreListQueryResponseVO> storeOrderListQuery(StoreQueryListRequestVO reqvo) {

		log.info("---------------Start请求报文为："+reqvo+"---------------");

		if (StringUtils.isEmpty(reqvo.getUserCode())) {

			return HttpResult.error(0,"业务员编码不可为空！");
		}
		if (StringUtils.isEmpty(reqvo.getInsuranceType())) {

			return HttpResult.error(0,"订单类型不可为空！");
		}
		if (StringUtils.isEmpty(reqvo.getStatus())) {

			return HttpResult.error(0,"订单状态不可为空！");
		}

		StoreListQueryResponseVO resvo = new StoreListQueryResponseVO();

		try{
			Integer page=reqvo.getPage();
			Integer size=reqvo.getSize();
			Integer offset=0;
			if(page>1){
				offset=(page)*size!=0?(page-1)*size:0;
			}else if(page==1){
				offset=1-1;
			}
			reqvo.setPage(offset);//起始页

			List<StoreInsureInfo> reslst=stu.storeListQueryDao(reqvo);
			
			reqvo.setSize(0);
			List<StoreInsureInfo> resRow=stu.storeListQueryDao(reqvo);
			resvo.setRows(resRow.size());//获取实际总条数
			Integer pages=size>0?(int)Math.ceil((resRow.size()*1.0)/size):1;//总页数
			
			resvo.setStList(reslst);
			resvo.setPages(pages);
			log.info("---------------END返回报文为："+resvo+"---------------");
			return HttpResult.success(resvo,1,"门店订单列表查询成功！");
		}catch(Exception e){
			log.error("门店订单列表查询异常！：",e);
			return HttpResult.error(0, "门店订单列表查询失败！");
		}	
	}

	/**
	 * 订单详情
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HttpResult<StoreInsureInfoFormulasResponseVO> storeOrderDetail(StoreQueryDetailReqVO req) {

		log.info("---------------请求报文为："+req+"---------------");

		// 查询订单信息
		try {
			StoreInsureInfoFormulasResponseVO fins = stu.findStoreInsureInfoAndFormulasByInsureNo(req.getInsureNo());

			log.info("---------------返回报文为："+fins+"---------------");
			return HttpResult.success(fins,1,"订单详情查询成功！");

		} catch (Exception e) {
			log.error("订单查询异常",e.fillInStackTrace());
			return HttpResult.error(0, "查询订单信息失败！");

		}
	}


	/**
	 * 门店保单回写
	 */
	@Override
	public HttpResult<?> certiNotice(StoreCertiNoticeRequestVO reqvo) {

		log.info("---------------请求报文为："+reqvo+"---------------");

		if (StringUtils.isEmpty(reqvo.getInsureNo())) {

			return HttpResult.error(0, "投保单号不能为空！");
		}
		if (StringUtils.isEmpty(reqvo.getPolicyNo())) {

			return HttpResult.error(0, "保单号不能为空！");
		}

		// 根据投保单号获取投保信息
		StoreInsureInfo insureInfo = stu.findStoreInsureInfoByInsureNo(reqvo
				.getInsureNo());


		if (null != insureInfo) {
			int count = stu.upPolicyNo(reqvo.getInsureNo(), reqvo.getPolicyNo());

			log.info("---------------返回报文为："+count+"---------------");
			return HttpResult.error(count,1, "移动销售支持H5门店保单回写成功!");
		}else{
			return HttpResult.error(0, "移动销售支持H5门店保单回写失败!");
		}


	}


	/**
	 * 删除订单
	 */
	@Override
	public HttpResult<?> deleteStoreOrder(StoreOrderDeleteRequestVO reqvo) throws Exception {

		if (reqvo.getId() == null || reqvo.getId().size() <= 0 ) {

			return HttpResult.error(0,"请选择要删除的订单！");
		}

		return stu.deleteStoreOrder(reqvo);
	}


	/**
	 * 订单转发支付查询信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HttpResult<StoreInsureInfoFormulasResponseVO> forwardPayProductInsureInfo(ForwardPayInsureInfoReqVO reqvo) {

		log.info("---------------Start请求报文为："+reqvo+"---------------");

		String forwardDate = reqvo.getForwardDate();
		String proposalNo = reqvo.getProposalNo();
		String status=reqvo.getStatus();

		if (StringUtils.isBlank(forwardDate) || StringUtils.isBlank(proposalNo)||StringUtils.isBlank(status)) {

			return HttpResult.error(0, "请求参数不可为空！");
		}

		StoreInsureInfoFormulasResponseVO fins=null;
		String desForwardDate="";
		String desProposalNo="";
		long time=0;

		if("0".equals(status)){

			try{
				desForwardDate = DESEncryptUtil.decrypt(forwardDate, forwardPay);
				desProposalNo = DESEncryptUtil.decrypt(proposalNo, forwardPay);

				time = System.currentTimeMillis() - Long.parseLong(desForwardDate);

			}catch(Exception e){
				log.error("入参为DES密文类型！",e);
				return HttpResult.error(0, "入参为DES密文类型！");
			}

			int hours = (int) (time / (1000 * 60 * 60));
			if (hours > 24) {

				return HttpResult.error(0, "链接已失效！");
			}

			// 查询订单信息
			try {
				fins = stu
						.findStoreInsureInfoAndFormulasByInsureNo(desProposalNo);
			} catch (Exception e) {

				log.error("订单转发支付查询信息异常！：",e);

				return HttpResult.error(0,"订单转发支付查询信息失败！");
			}	

		}else if("1".equals(status)){

			// 查询订单信息
			try {
				fins = stu
						.findStoreInsureInfoAndFormulasByInsureNo(proposalNo);

			} catch (Exception e) {

				log.error("订单转发支付查询信息异常！：",e);

				return HttpResult.error(0,"订单转发支付查询信息失败！");
			}	
		}else{
			return HttpResult.error(0, "请输入正确的status值：0为转发支付查询接口,1为复制投保单接口！");
		}

		log.info("---------------END返回报文为："+fins +"---------------");

		return HttpResult.success(fins,1,"订单转发支付查询成功！");
	}
}
