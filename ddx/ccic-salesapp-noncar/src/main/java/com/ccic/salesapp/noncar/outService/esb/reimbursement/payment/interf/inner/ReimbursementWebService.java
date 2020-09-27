/**
 * ReimbursementWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.interf.inner;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesMainMsgDto;

public interface ReimbursementWebService extends java.rmi.Remote {
    public SalesMainMsgDto dealRequest(SalesMainMsgDto salesMainMsgDto) throws java.rmi.RemoteException;
    public java.lang.String dealRequest(java.lang.String inputXml) throws java.rmi.RemoteException;
}
