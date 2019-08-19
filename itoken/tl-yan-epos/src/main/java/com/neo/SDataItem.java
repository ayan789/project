package com.neo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@Slf4j
@Data
public class SDataItem {

    public static final SDataItem EMPTY = new SDataItem();

    public static final int STATUS_UNSUBMIT = 0;//未提交
    public static final int STATUS_SUBMITTED = 1;//已提交

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_PDF = "pdf";
    public static final String TYPE_PDF_FROM_PROXY = "proxy";


    String id;
    String type;
    String insureId;
    String code;
    String url;
    String customerId;

    String name;

    //add
    Integer status = STATUS_UNSUBMIT;

    //add 2018/08/14
    String title;
    String givenName;
    String familyName;

    Date saveDate;

}