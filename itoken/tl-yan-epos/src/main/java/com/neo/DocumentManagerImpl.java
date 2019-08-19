package com.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.neo.MDocument.MDocumentItem;
import com.neo.MDocument.MDeal;
import com.neo.LocaleMessage;
import com.neo.MDocument.MPath;

public class DocumentManagerImpl implements DocumentManager {

    private static final String PAYIN = "0";
    private static final String SUBMITTED_DOCUMENTS = "7";

    @Override
    public MDocument loadDocument(String insureId) {
        String slb_domain = "";
        String slb_prefix = "";
        InsureFactorsBuild insureFactorsBuild = new InsureFactorsBuild();
        InsureFactors insureFactors = insureFactorsBuild.buildInsureFactors(insureId);
        SInsure insure = insureFactors.getSInsure();
        int insureStatus = insure.getStatus();
        DocumentMgr documentMgr = new DocumentMgr();
        InsureDataDao insureDataDao = new InsureDataDao();
        boolean isUL = true;
        SData data = insureDataDao.loadInsureData(insureId);
        MDocument mDocument = null;
        List<GroupDocumentDefine> groupDocDefines = documentMgr.getGroupDocumentDefineList(isUL,insureFactors);
        mDocument = buildPostSubmit(slb_domain + slb_prefix, groupDocDefines, data, insureStatus, insure, insureFactors);
        return null;
    }

    //hide for temprary
    //Post Submit, Grouping
    @SuppressWarnings("Duplicates")
    MDocument buildPostSubmit(String domain, List<GroupDocumentDefine> groupDocumentDefines, SData data, int insureStatus, SInsure sInsure, InsureFactors insureFactors) {
        MDocument mDocument = new MDocument();

        for (GroupDocumentDefine groupDocumentDefine : groupDocumentDefines) {
            MDocument.MGroupDocumentItem mGroupDocumentItem = new MDocument.MGroupDocumentItem();
            List<MDocumentItem> mDocumentItemsList = new ArrayList<>();
            mGroupDocumentItem.setGroupName(groupDocumentDefine.getGroupName());
            mGroupDocumentItem.setCode(groupDocumentDefine.getCode());
            mGroupDocumentItem.setExpand(groupDocumentDefine.isExpand());
            mGroupDocumentItem.setDocumentItemList(mDocumentItemsList);

            groupDocumentDefine.getDocumentDefineList().forEach(define ->{
                MDocumentItem item = formatDocumentItem(domain, define, data, insureStatus, sInsure, insureFactors);
                if(SUBMITTED_DOCUMENTS.equals(groupDocumentDefine.getCode())) item.removeDeal(DocumentDefine.Deal.TYPE_BROWSE); //remove browser for submitted document
                if(!PAYIN.equals(groupDocumentDefine.getCode()) && !SUBMITTED_DOCUMENTS.equals(groupDocumentDefine.getCode()))
                    item.removeDeal(DocumentDefine.Deal.TYPE_VIEW); //remove view except "PAYIN" and "Submitted"

                if(!org.apache.commons.lang.StringUtils.equalsIgnoreCase(SUBMITTED_DOCUMENTS,groupDocumentDefine.getCode())){
                    mDocumentItemsList.add(item);
                }
                if(org.apache.commons.lang.StringUtils.equalsIgnoreCase(SUBMITTED_DOCUMENTS,groupDocumentDefine.getCode()) && !(item.paths==null || item.paths.isEmpty())){
                    mDocument.addSubmittedDocument(item);
                }
            });

            mDocumentItemsList.sort(new Comparator<MDocumentItem>() {
                @Override
                public int compare(MDocumentItem o1, MDocumentItem o2) {
                    return Integer.compare(o1.sequence,o2.sequence);
                }
            });
            if(!StringUtils.equalsIgnoreCase(SUBMITTED_DOCUMENTS,groupDocumentDefine.getCode()) && !mDocumentItemsList.isEmpty()){
                mDocument.addGroupDocument(mGroupDocumentItem);
            }
        }

        if(mDocument.submittedDocuments == null || mDocument.submittedDocuments.isEmpty()){
            mDocument.submittedDocuments = new ArrayList<>();
        }

        return mDocument;
    }

    @SuppressWarnings("Duplicates")
    MDocumentItem formatDocumentItem(String domain, DocumentDefine documentDefine, SData data, int insureStatus, SInsure sInsure, InsureFactors insureFactors) {
        MDocumentItem mDocumentItem = new MDocumentItem();
        mDocumentItem.name = documentDefine.getDocName();
        mDocumentItem.type = documentDefine.getType();
        mDocumentItem.code = documentDefine.getCode();
        mDocumentItem.max = documentDefine.getMax();
        mDocumentItem.min = documentDefine.getMin();
        mDocumentItem.sequence = documentDefine.getSequence(); //hide for temprary
        mDocumentItem.watermarkText = MDocumentItem.addWaterMarkText(documentDefine.getCode(), sInsure.getInsureNo());
        LocaleMessage localeMessage = new LocaleMessage();
        List<DocumentDefine.Deal> deals = documentDefine.getDeals();
        if(DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())
                && insureStatus == InsureConfig.STATUS_SUBMIT){
            mDocumentItem.addDeal(MDeal.obtain(DocumentDefine.Deal.TYPE_PAY_INFO, localeMessage.getMessage("PAY_IN_INFO")));// for payin, status 1051, only display "pay in info"
        }else if(DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())
                && insureStatus == InsureConfig.STATUS_PAY_INFO_SUBMIT){ // after payin,1052, display "VIEW","BROWSE"
            mDocumentItem.addDeal(MDeal.obtain(DocumentDefine.Deal.TYPE_VIEW, localeMessage.getMessage("VIEW")));
            mDocumentItem.addDeal(MDeal.obtain(DocumentDefine.Deal.TYPE_BROWSE, localeMessage.getMessage("BROWSE")));
        }else{
            for (DocumentDefine.Deal deal : deals) {
                mDocumentItem.addDeal(formatDeal(deal));
            }
        }

        List<SDataItem> dataItems = data.findDataItems(documentDefine.getCode());

        boolean needRemoveBrowse = false;
        boolean needRemovePayInfo = false;
        boolean needRemovePayInfoSubmit = false;

        if(insureStatus == InsureConfig.STATUS_PRE_SUBMIT){
            // PRE SUBNIT 1050
            if(DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())){
                // submit no pay in
                return null;
            }
            // else do nothing
        }
//		else if(insureStatus == InsureConfig.STATUS_SUBMIT){
//			// SUBMIT 1051
//			if(!DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())){
//				// not payin no browe
//				mDocumentItem.min = 0;
//				needRemoveBrowse = true;
//			}
//
//		}
        else if(insureStatus == InsureConfig.STATUS_PAY_INFO_SUBMIT){
            // PAYIN SUBMIT 1052, view, browser
            mDocumentItem.min = 0;
            if(DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())){
                // submit no pay in
//				needRemoveBrowse = true;
                needRemovePayInfo = true;
                needRemovePayInfoSubmit = true;
            }
//			else{
//				needRemoveBrowse = true;
//			}
        }

        filterDocument(documentDefine, sInsure, mDocumentItem, insureFactors);

        if (needRemoveBrowse) {
            mDocumentItem.removeDeal(DocumentDefine.Deal.TYPE_BROWSE);
        }
        if (needRemovePayInfo) {
            mDocumentItem.removeDeal(DocumentDefine.Deal.TYPE_PAY_INFO);
        }
        if (needRemovePayInfoSubmit) {
            mDocumentItem.removeDeal(DocumentDefine.Deal.TYPE_PAY_INFO_SUBMIT);
        }

        if (DocumentDefine.TYPE_DOC_PROPOSAL.equals(documentDefine.getType())) {
            if (dataItems.size() > 0) {
//				mDocumentItem.viewUrl = dataItems.get(0).getUrl();
                mDocumentItem.viewUrl = domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&code=" +
                        documentDefine.getCode();
            }
        } else if (DocumentDefine.TYPE_DOC_FROM_PROXY.equals(documentDefine.getType()) ||
                DocumentDefine.TYPE_DOC_PAY_INFO.equals(documentDefine.getType())) {
            mDocumentItem.viewUrl =
                    domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&code=" + documentDefine.getCode();
        }
//        if (insureStatus == InsureConfig.STATUS_SUBMIT || insureStatus == InsureConfig.STATUS_PAY_INFO_SUBMIT) {
//            if (dataItems.size() > 0) {
//                if (SDataItem.TYPE_PDF_FROM_PROXY.equals(dataItems.get(0).getType())) {
//                    mDocumentItem.viewUrl = domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&code=" + documentDefine.getCode();
//                }
//            }
//        }

        for (SDataItem dataItem : dataItems) {
            if (SDataItem.TYPE_IMAGE.equals(dataItem.getType())) {
                MPath mPath = MPath.obtain(dataItem.getUrl(), dataItem.getStatus());
                mPath.name = dataItem.getName();
                mPath.path = dataItem.getUrl();
                mDocumentItem.addPath(mPath);
            } else if (SDataItem.TYPE_PDF.equals(dataItem.getType())) {
                MPath mPath = MPath.obtain(dataItem.getUrl(), dataItem.getStatus());
                mPath.name = dataItem.getName();
                if(StringUtils.equalsIgnoreCase("QUOTATION",documentDefine.getDocType()) || StringUtils.equalsIgnoreCase("UL_SALES_SHEET",documentDefine.getDocType())){
                    mPath.path = domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&code=" + documentDefine.getCode();

                }else{
                    mPath.path = domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&url=" + dataItem.getUrl();
                }
//						+ "&code=" + documentDefine.getCode();
                mDocumentItem.addPath(mPath);
            } else if (SDataItem.TYPE_PDF_FROM_PROXY.equals(dataItem.getType())) {
                MPath mPath = MPath.obtain(dataItem.getUrl(), dataItem.getStatus());
                mPath.name = dataItem.getName();
                mPath.path =
                        domain + "/apply/file/viewPDF?insureId=" + data.getInsureId() + "&url=" + dataItem.getUrl();
                mDocumentItem.addPath(mPath);
            }

        }

        return mDocumentItem;
    }

    MDeal formatDeal(DocumentDefine.Deal deal) {
        LocaleMessage localeMessage = new LocaleMessage();
        return MDeal.obtain(deal.getType(), localeMessage.getMessage(deal.getTag()));
    }

    private void filterDocument(DocumentDefine documentDefine, SInsure sInsure, MDocumentItem mDocumentItem, InsureFactors insureFactors) {
        if ("doc_fatca_insured".equals(documentDefine.getCode())) {
            int insurantAge = Integer.parseInt(sInsure.getInsurant().getAge());
            if (insurantAge < 17) {
                InformBuild informBuild = new InformBuild();
                JSONObject jsonObject = informBuild.loadInform(InformMgr.INFORM_TYPE_FATCA, sInsure.getInsureId());
                JSONArray jsonArray = jsonObject.getJSONObject("fatca1").getJSONArray("group");
                String value1 = jsonArray.getJSONObject(0).getString("value");
                String value2 = jsonArray.getJSONObject(1).getString("value");
                if ("Y".equals(value1) || "Y".equals(value2)) {
                    mDocumentItem.min = 0;
                }
            }
        }

        if ("doc_credit_card_consent_form".equals(documentDefine.getCode())) {
            // 判断 UOB
            if ((Boolean) insureFactors.get("CREDIT_CARD") && (Boolean) insureFactors.get("Acknowledgement_Form")) {
                mDocumentItem.name = mDocumentItem.name + "³";
            }
        }

        if ("doc_bank_account_consent_form_uob".equals(documentDefine.getCode())) {
            // 判断 UOB
            if ((Boolean) insureFactors.get("Acknowledgement_Form")) {
                mDocumentItem.name = mDocumentItem.name + "³";

            }
        }


    }


}
