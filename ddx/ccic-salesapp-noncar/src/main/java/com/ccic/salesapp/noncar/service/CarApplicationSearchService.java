package com.ccic.salesapp.noncar.service;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.ccic.salesapp.noncar.dto.request.CarApplicationSearchRequest;
import com.ccic.salesapp.noncar.dto.response.CarApplicationSearchResponse;


/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CarApplicationSearchService", targetNamespace = "http://service.ccic.com/mss/carapplication/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CarApplicationSearchService {

	/**
	 * 
	 * @param carApplicationSearchRequest
	 * @return returns
	 *         com.ccic.service.mss.carapplication.bean.CarApplicationSearchResponse
	 */
	@WebMethod(action = "/mss/carapplication")
	@WebResult(name = "CarApplicationSearchResponse", targetNamespace = "http://service.ccic.com/mss/carapplication/bean", partName = "CarApplicationSearchResponse")
	public CarApplicationSearchResponse searchCarApplication(
			@WebParam(name = "CarApplicationSearchRequest", targetNamespace = "http://service.ccic.com/mss/carapplication/bean", partName = "CarApplicationSearchRequest")
			CarApplicationSearchRequest carApplicationSearchRequest);

}
