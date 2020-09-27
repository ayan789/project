/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.submitMiddleGround;
import java.util.Date;

/**
 * Auto-generated: 2020-09-10 14:42:2
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ChannelOpInfoList {

    private String channelCode;
    private String channelComCode;
    private String channelProductCode;
    private Date trxDate;
    public void setChannelCode(String channelCode) {
         this.channelCode = channelCode;
     }
     public String getChannelCode() {
         return channelCode;
     }

    public void setChannelComCode(String channelComCode) {
         this.channelComCode = channelComCode;
     }
     public String getChannelComCode() {
         return channelComCode;
     }

    public void setChannelProductCode(String channelProductCode) {
         this.channelProductCode = channelProductCode;
     }
     public String getChannelProductCode() {
         return channelProductCode;
     }

    public void setTrxDate(Date trxDate) {
         this.trxDate = trxDate;
     }
     public Date getTrxDate() {
         return trxDate;
     }

}