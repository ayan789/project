
package com.ccic.salesapp.noncar.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AirDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AirDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnFlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlightDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlightTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnFlightDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestAirport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AirlineNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IataNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EticketNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AirLineOrderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InsureNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AirDTO", propOrder = {
    "flightNumber",
    "returnFlightNumber",
    "flightDate",
    "flightTime",
    "returnFlightDate",
    "buyTime",
    "originAirport",
    "destAirport",
    "airlineNo",
    "iataNo",
    "eticketNo",
    "airLineOrderNo",
    "operator",
    "operateTime",
    "insureNo"
})
public class AirDTO {

    @XmlElement(name = "FlightNumber")
    protected String flightNumber;
    @XmlElement(name = "ReturnFlightNumber")
    protected String returnFlightNumber;
    @XmlElement(name = "FlightDate")
    protected String flightDate;
    @XmlElement(name = "FlightTime")
    protected String flightTime;
    @XmlElement(name = "ReturnFlightDate")
    protected String returnFlightDate;
    @XmlElement(name = "BuyTime")
    protected String buyTime;
    @XmlElement(name = "OriginAirport")
    protected String originAirport;
    @XmlElement(name = "DestAirport")
    protected String destAirport;
    @XmlElement(name = "AirlineNo")
    protected String airlineNo;
    @XmlElement(name = "IataNo")
    protected String iataNo;
    @XmlElement(name = "EticketNo")
    protected String eticketNo;
    @XmlElement(name = "AirLineOrderNo")
    protected String airLineOrderNo;
    @XmlElement(name = "Operator")
    protected String operator;
    @XmlElement(name = "OperateTime")
    protected String operateTime;
    @XmlElement(name = "InsureNo")
    protected String insureNo;

    /**
     * ��ȡflightNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * ����flightNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
    }

    /**
     * ��ȡreturnFlightNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnFlightNumber() {
        return returnFlightNumber;
    }

    /**
     * ����returnFlightNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnFlightNumber(String value) {
        this.returnFlightNumber = value;
    }

    /**
     * ��ȡflightDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightDate() {
        return flightDate;
    }

    /**
     * ����flightDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightDate(String value) {
        this.flightDate = value;
    }

    /**
     * ��ȡflightTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightTime() {
        return flightTime;
    }

    /**
     * ����flightTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightTime(String value) {
        this.flightTime = value;
    }

    /**
     * ��ȡreturnFlightDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnFlightDate() {
        return returnFlightDate;
    }

    /**
     * ����returnFlightDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnFlightDate(String value) {
        this.returnFlightDate = value;
    }

    /**
     * ��ȡbuyTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyTime() {
        return buyTime;
    }

    /**
     * ����buyTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyTime(String value) {
        this.buyTime = value;
    }

    /**
     * ��ȡoriginAirport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginAirport() {
        return originAirport;
    }

    /**
     * ����originAirport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginAirport(String value) {
        this.originAirport = value;
    }

    /**
     * ��ȡdestAirport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestAirport() {
        return destAirport;
    }

    /**
     * ����destAirport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestAirport(String value) {
        this.destAirport = value;
    }

    /**
     * ��ȡairlineNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineNo() {
        return airlineNo;
    }

    /**
     * ����airlineNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineNo(String value) {
        this.airlineNo = value;
    }

    /**
     * ��ȡiataNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIataNo() {
        return iataNo;
    }

    /**
     * ����iataNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIataNo(String value) {
        this.iataNo = value;
    }

    /**
     * ��ȡeticketNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEticketNo() {
        return eticketNo;
    }

    /**
     * ����eticketNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEticketNo(String value) {
        this.eticketNo = value;
    }

    /**
     * ��ȡairLineOrderNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirLineOrderNo() {
        return airLineOrderNo;
    }

    /**
     * ����airLineOrderNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirLineOrderNo(String value) {
        this.airLineOrderNo = value;
    }

    /**
     * ��ȡoperator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperator() {
        return operator;
    }

    /**
     * ����operator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperator(String value) {
        this.operator = value;
    }

    /**
     * ��ȡoperateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * ����operateTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateTime(String value) {
        this.operateTime = value;
    }

    /**
     * ��ȡinsureNo���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsureNo() {
        return insureNo;
    }

    /**
     * ����insureNo���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsureNo(String value) {
        this.insureNo = value;
    }

}
