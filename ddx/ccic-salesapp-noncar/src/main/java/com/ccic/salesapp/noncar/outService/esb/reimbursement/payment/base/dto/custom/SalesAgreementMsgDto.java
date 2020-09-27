/**
 * SalesAgreementMsgDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom;

public class SalesAgreementMsgDto  implements java.io.Serializable {
    private double TCIDefaultTopcommission;

    private double TCITopcommission;

    private java.lang.String agreementName;

    private java.lang.String agreementNo;

    private java.lang.String comCode;

    private double defaultTopcommission;

    private java.lang.String endDate;

    private java.lang.String startDate;

    private double topcommission;

    public SalesAgreementMsgDto() {
    }

    public SalesAgreementMsgDto(
           double TCIDefaultTopcommission,
           double TCITopcommission,
           java.lang.String agreementName,
           java.lang.String agreementNo,
           java.lang.String comCode,
           double defaultTopcommission,
           java.lang.String endDate,
           java.lang.String startDate,
           double topcommission) {
           this.TCIDefaultTopcommission = TCIDefaultTopcommission;
           this.TCITopcommission = TCITopcommission;
           this.agreementName = agreementName;
           this.agreementNo = agreementNo;
           this.comCode = comCode;
           this.defaultTopcommission = defaultTopcommission;
           this.endDate = endDate;
           this.startDate = startDate;
           this.topcommission = topcommission;
    }


    /**
     * Gets the TCIDefaultTopcommission value for this SalesAgreementMsgDto.
     * 
     * @return TCIDefaultTopcommission
     */
    public double getTCIDefaultTopcommission() {
        return TCIDefaultTopcommission;
    }


    /**
     * Sets the TCIDefaultTopcommission value for this SalesAgreementMsgDto.
     * 
     * @param TCIDefaultTopcommission
     */
    public void setTCIDefaultTopcommission(double TCIDefaultTopcommission) {
        this.TCIDefaultTopcommission = TCIDefaultTopcommission;
    }


    /**
     * Gets the TCITopcommission value for this SalesAgreementMsgDto.
     * 
     * @return TCITopcommission
     */
    public double getTCITopcommission() {
        return TCITopcommission;
    }


    /**
     * Sets the TCITopcommission value for this SalesAgreementMsgDto.
     * 
     * @param TCITopcommission
     */
    public void setTCITopcommission(double TCITopcommission) {
        this.TCITopcommission = TCITopcommission;
    }


    /**
     * Gets the agreementName value for this SalesAgreementMsgDto.
     * 
     * @return agreementName
     */
    public java.lang.String getAgreementName() {
        return agreementName;
    }


    /**
     * Sets the agreementName value for this SalesAgreementMsgDto.
     * 
     * @param agreementName
     */
    public void setAgreementName(java.lang.String agreementName) {
        this.agreementName = agreementName;
    }


    /**
     * Gets the agreementNo value for this SalesAgreementMsgDto.
     * 
     * @return agreementNo
     */
    public java.lang.String getAgreementNo() {
        return agreementNo;
    }


    /**
     * Sets the agreementNo value for this SalesAgreementMsgDto.
     * 
     * @param agreementNo
     */
    public void setAgreementNo(java.lang.String agreementNo) {
        this.agreementNo = agreementNo;
    }


    /**
     * Gets the comCode value for this SalesAgreementMsgDto.
     * 
     * @return comCode
     */
    public java.lang.String getComCode() {
        return comCode;
    }


    /**
     * Sets the comCode value for this SalesAgreementMsgDto.
     * 
     * @param comCode
     */
    public void setComCode(java.lang.String comCode) {
        this.comCode = comCode;
    }


    /**
     * Gets the defaultTopcommission value for this SalesAgreementMsgDto.
     * 
     * @return defaultTopcommission
     */
    public double getDefaultTopcommission() {
        return defaultTopcommission;
    }


    /**
     * Sets the defaultTopcommission value for this SalesAgreementMsgDto.
     * 
     * @param defaultTopcommission
     */
    public void setDefaultTopcommission(double defaultTopcommission) {
        this.defaultTopcommission = defaultTopcommission;
    }


    /**
     * Gets the endDate value for this SalesAgreementMsgDto.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this SalesAgreementMsgDto.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the startDate value for this SalesAgreementMsgDto.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this SalesAgreementMsgDto.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the topcommission value for this SalesAgreementMsgDto.
     * 
     * @return topcommission
     */
    public double getTopcommission() {
        return topcommission;
    }


    /**
     * Sets the topcommission value for this SalesAgreementMsgDto.
     * 
     * @param topcommission
     */
    public void setTopcommission(double topcommission) {
        this.topcommission = topcommission;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesAgreementMsgDto)) return false;
        SalesAgreementMsgDto other = (SalesAgreementMsgDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.TCIDefaultTopcommission == other.getTCIDefaultTopcommission() &&
            this.TCITopcommission == other.getTCITopcommission() &&
            ((this.agreementName==null && other.getAgreementName()==null) || 
             (this.agreementName!=null &&
              this.agreementName.equals(other.getAgreementName()))) &&
            ((this.agreementNo==null && other.getAgreementNo()==null) || 
             (this.agreementNo!=null &&
              this.agreementNo.equals(other.getAgreementNo()))) &&
            ((this.comCode==null && other.getComCode()==null) || 
             (this.comCode!=null &&
              this.comCode.equals(other.getComCode()))) &&
            this.defaultTopcommission == other.getDefaultTopcommission() &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            this.topcommission == other.getTopcommission();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Double(getTCIDefaultTopcommission()).hashCode();
        _hashCode += new Double(getTCITopcommission()).hashCode();
        if (getAgreementName() != null) {
            _hashCode += getAgreementName().hashCode();
        }
        if (getAgreementNo() != null) {
            _hashCode += getAgreementNo().hashCode();
        }
        if (getComCode() != null) {
            _hashCode += getComCode().hashCode();
        }
        _hashCode += new Double(getDefaultTopcommission()).hashCode();
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        _hashCode += new Double(getTopcommission()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesAgreementMsgDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://custom.dto.base.payment.sinosoft.com", "SalesAgreementMsgDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TCIDefaultTopcommission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TCIDefaultTopcommission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TCITopcommission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TCITopcommission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agreementName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agreementName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agreementNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agreementNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultTopcommission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "defaultTopcommission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topcommission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "topcommission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
