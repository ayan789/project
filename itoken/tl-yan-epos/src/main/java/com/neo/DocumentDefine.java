package com.neo;


import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.FormulaUtil;
import lerrain.tool.formula.Value;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DocumentDefine {

    public static final String TYPE_DOC_FROM_PROXY = "type_from_proxy";//从proxy下载
    public static final String TYPE_DOC_PROPOSAL = "type_proposal";//建议书
    public static final String TYPE_DOC_PAY_INFO = "type_pay_info";//
    public static final String TYPE_DOC_NORMAL = "type_normal";//

    List<String> needSigns = new ArrayList<>();
    String code;
    String type;
    String docName;
    String docType;//Document Type(PLT-FileNet)
    int min = 0;
    int max = -1;
    List<Deal> deals = new ArrayList<>();
    Formula visible;
    String value;
    String text;
    int sequence;
    String needTopup;

    //hide for temprary
    public static DocumentDefine build(String code, String visible, String type, int sequence) {
        return build(code, -1, 0, visible, type, sequence);
    }

    //hide for temprary
    public static DocumentDefine build(String code, int max, int min, String visible, String type, int sequence) {
        DocumentDefine documentDef = new DocumentDefine();

        documentDef.code = code;

        if (StringTool.isNotNull(type)) {
            documentDef.type = type;
        } else {
            documentDef.type = TYPE_DOC_NORMAL;
        }

        documentDef.max = max;
        documentDef.min = min;
        documentDef.sequence = sequence;//hide for temprary

        if (visible != null) {
            documentDef.visible = FormulaUtil.formulaOf(visible);
        }

        if (StringTool.isNull(type)) {
            type = TYPE_DOC_NORMAL;
        }

//        if (TYPE_DOC_FROM_PROXY.equals(type) || TYPE_DOC_PROPOSAL.equals(type)){
        documentDef.addDeal(new Deal(Deal.TYPE_VIEW));
//        }

        if (TYPE_DOC_PAY_INFO.equals(type) || TYPE_DOC_NORMAL.equals(type)) {
            documentDef.addDeal(new Deal(Deal.TYPE_BROWSE));
        }

        if (TYPE_DOC_PAY_INFO.equals(type)) {
            documentDef.addDeal(new Deal(Deal.TYPE_PAY_INFO));
        }

        if (TYPE_DOC_PAY_INFO.equals(type)) {
            documentDef.addDeal(new Deal(Deal.TYPE_PAY_INFO_SUBMIT));
        }


        return documentDef;
    }

    public void addNeedSigns(String... arr) {
        if (needSigns == null) {
            needSigns = new ArrayList<>();
        }
        if (arr != null) {
            for (String code : arr) {
                needSigns.add(code);
            }
        }
    }

    public void addDeal(Deal deal) {
        if (deals == null) {
            deals = new ArrayList<>();
        }
        deals.add(deal);
    }

    public boolean booleanOfVisible(Factors factors) {
        if (visible == null) {
            return true;
        }
        return Value.booleanOf(visible, factors);
    }

    @Data
    public static class Deal {

        //view    browse   pay_info
        public static int TYPE_VIEW = 1;
        public static int TYPE_BROWSE = 2;
        public static int TYPE_PAY_INFO = 3;
        public static int TYPE_PAY_INFO_SUBMIT = 4;

        int type;
        String tag;

        public Deal(int type) {
            this.type = type;
            this.tag = getTag(type);
        }

        public static int getType(String type) {
            if ("view".equals(type)) {
                return TYPE_VIEW;
            }
            if ("browse".equals(type)) {
                return TYPE_BROWSE;
            }
            if ("pay_info".equals(type)) {
                return TYPE_PAY_INFO;
            }
            return TYPE_VIEW;
        }

        static String getTag(int type) {
            if (type == TYPE_VIEW) {
                return "VIEW";
            }
            if (type == TYPE_BROWSE) {
                return "BROWSE";
            }
            if (type == TYPE_PAY_INFO) {
                return "PAY_IN_INFO";
            }
            if(type == TYPE_PAY_INFO_SUBMIT){
                return "PAY_IN_INFO_SUBMIT";
            }
            return null;
        }
    }

	@Override
	public String toString() {
		return "DocumentDefine [needSigns=" + needSigns + ", code=" + code + ", type=" + type + ", docName=" + docName
				+ ", docType=" + docType + ", min=" + min + ", max=" + max + ", deals=" + deals + ", visible=" + visible+ ", value=" + value + ", text=" + text
				+ ", sequence=" + sequence + ", needTopup=" + needTopup + "]";
	}
    
    
}
