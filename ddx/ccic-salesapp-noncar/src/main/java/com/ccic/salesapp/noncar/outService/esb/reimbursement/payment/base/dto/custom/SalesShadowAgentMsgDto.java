/**
 * SalesShadowAgentMsgDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom;

public class SalesShadowAgentMsgDto  implements java.io.Serializable {
    private java.lang.String shadowAgentCode;

    private java.lang.String shadowAgentName;

    private java.lang.String shadowAgentType;

    public SalesShadowAgentMsgDto() {
    }

    public SalesShadowAgentMsgDto(
           java.lang.String shadowAgentCode,
           java.lang.String shadowAgentName,
           java.lang.String shadowAgentType) {
           this.shadowAgentCode = shadowAgentCode;
           this.shadowAgentName = shadowAgentName;
           this.shadowAgentType = shadowAgentType;
    }


    /**
     * Gets the shadowAgentCode value for this SalesShadowAgentMsgDto.
     * 
     * @return shadowAgentCode
     */
    public java.lang.String getShadowAgentCode() {
        return shadowAgentCode;
    }


    /**
     * Sets the shadowAgentCode value for this SalesShadowAgentMsgDto.
     * 
     * @param shadowAgentCode
     */
    public void setShadowAgentCode(java.lang.String shadowAgentCode) {
        this.shadowAgentCode = shadowAgentCode;
    }


    /**
     * Gets the shadowAgentName value for this SalesShadowAgentMsgDto.
     * 
     * @return shadowAgentName
     */
    public java.lang.String getShadowAgentName() {
        return shadowAgentName;
    }


    /**
     * Sets the shadowAgentName value for this SalesShadowAgentMsgDto.
     * 
     * @param shadowAgentName
     */
    public void setShadowAgentName(java.lang.String shadowAgentName) {
        this.shadowAgentName = shadowAgentName;
    }


    /**
     * Gets the shadowAgentType value for this SalesShadowAgentMsgDto.
     * 
     * @return shadowAgentType
     */
    public java.lang.String getShadowAgentType() {
        return shadowAgentType;
    }


    /**
     * Sets the shadowAgentType value for this SalesShadowAgentMsgDto.
     * 
     * @param shadowAgentType
     */
    public void setShadowAgentType(java.lang.String shadowAgentType) {
        this.shadowAgentType = shadowAgentType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesShadowAgentMsgDto)) return false;
        SalesShadowAgentMsgDto other = (SalesShadowAgentMsgDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.shadowAgentCode==null && other.getShadowAgentCode()==null) || 
             (this.shadowAgentCode!=null &&
              this.shadowAgentCode.equals(other.getShadowAgentCode()))) &&
            ((this.shadowAgentName==null && other.getShadowAgentName()==null) || 
             (this.shadowAgentName!=null &&
              this.shadowAgentName.equals(other.getShadowAgentName()))) &&
            ((this.shadowAgentType==null && other.getShadowAgentType()==null) || 
             (this.shadowAgentType!=null &&
              this.shadowAgentType.equals(other.getShadowAgentType())));
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
        if (getShadowAgentCode() != null) {
            _hashCode += getShadowAgentCode().hashCode();
        }
        if (getShadowAgentName() != null) {
            _hashCode += getShadowAgentName().hashCode();
        }
        if (getShadowAgentType() != null) {
            _hashCode += getShadowAgentType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesShadowAgentMsgDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://custom.dto.base.payment.sinosoft.com", "SalesShadowAgentMsgDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shadowAgentCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shadowAgentCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shadowAgentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shadowAgentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shadowAgentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shadowAgentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
