package com.neo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Document request/response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MDocument {

    public String insureId;

    public String insureNo;

    public int insureStatus;

    public boolean showSign = true;//是否显示签名

    public boolean showSubmit = true;//控制是否显示submit

    public boolean isSavedPayInfo = false;// 是否已经保存payInfo

    public boolean showPay = false; //是否显示支付

    public boolean inSubmission = false; //是否正在submit

    public String viewAll = ""; //viewAll link

    public List<MDocumentItem> documents;

    public List<MGroupDocumentItem> groupDocuments;

    public List<MDocumentItem> submittedDocuments;

    private final static String WATER_MASK_TEXT_DOCUMENT = "doc_copyOf_ID_insured,doc_copyOf_ID_card,doc_copyOf_passport,doc_copyOf_passport_payor,";

    public void addDocument(MDocumentItem document) {
        if (documents == null) {
            documents = new ArrayList<>();
        }
        documents.add(document);
    }

    public void addSubmittedDocument(MDocumentItem document) {
        if (submittedDocuments == null) {
            submittedDocuments = new ArrayList<>();
        }
        submittedDocuments.add(document);
    }

    //hide for temprary
    public void addGroupDocument(MGroupDocumentItem document) {
        if (groupDocuments == null) {
            groupDocuments = new ArrayList<>();
        }
        groupDocuments.add(document);
    }

    @Setter
    @Getter
    public static class MGroupDocumentItem{
        public String groupName;
        public String code;
        public boolean expand;
        public List<MDocumentItem> documentItemList;
    }

    public static class MDocumentItem {

        public String type;
        public String viewUrl;
        public String code;
        public String name;
        public String watermarkText;

        public int max;
        public int min;

        public int sequence;

        public List<MDeal> deals = new ArrayList<>();
        public List<MPath> paths = new ArrayList<>();

        // attach
        public List<MPath> additionalPaths = new ArrayList<>();

        public void removeDeal(int type) {
            if (deals != null) {
                for (MDeal deal : deals) {
                    if (deal.type == type) {
                        deals.remove(deal);
                        break;
                    }
                }
            }
        }

        public void addDeal(MDeal mDeal) {
            if (deals == null) {
                deals = new ArrayList<>();
            }
            deals.add(mDeal);
        }

        public void addPath(MPath mPath) {
            if (paths == null) {
                paths = new ArrayList<>();
            }
            paths.add(mPath);
        }

        public static String addWaterMarkText(String code, String insureNo) {
            if (StringTool.isNotNull(code)) {
                if (WATER_MASK_TEXT_DOCUMENT.indexOf(code) != -1) {
                    if (ThailandDateTool.isThai()) {
                        return "ใช้เพื่อสมัครทำประกันชีวิตกับบมจ.พรูเด็นเชียล\nประกันชีวิต (ประเทศไทย)\nใบคำขอเอาประกันภัยเลขที่ \"" + insureNo + "\" เท่านั้น";
                    } else {
                        return "To be used for the application of life insurance with Prudential Life\nAssurance (Thailand) Public Company Limited for application no.\n\"" + insureNo + "\" only";
                    }
                }
            }
            return null;
        }
    }

    public static class MPath {

        public String name;
        public String path;
        public int status;//1 未提交  2 已提交

        public static MPath obtain(String path, int status) {
            MPath mPath = new MPath();
            mPath.path = path;
            mPath.status = status;
            return mPath;
        }
    }

    public static class MDeal {
        public int type;
        public String tag;

        public static MDeal obtain(int type, String tag) {
            MDeal mDeal = new MDeal();
            mDeal.tag = tag;
            mDeal.type = type;
            return mDeal;
        }
    }

}

