package com.neo;

import com.alibaba.fastjson.JSON;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.FormulaUtil;
import lerrain.tool.formula.Value;
import lerrain.tool.script.Stack;
import lombok.Data;

import java.util.Map;

/**
 * 投保单数据参数
 * 用于控制投保流程中的一些条件判断
 */
@Data
public class InsureFactors extends Stack {

	private static final String NATIONALITY_THA = "THA";
//	private static final String USA = "USA";
//	private static final String CREDIT_CARD = "2";
//	private static final String PAYMENT_BILL = "1";
//	private static final String DIRECT_DEBIT_BANK_ACCOUNT = "3";
//	private static final String UOB_BANK_CODE = "024";
//	private static final String DIVIDEND = "dividend";//restore
//	private static final String MARITALSTATUS_SINGLE = "S";
//	private static final String DIVIDENG_OPTION_REINVESTMENT = "2";
	SInsure sInsure;

	public InsureFactors(SInsure sInsure) {
		super((Map) JSON.toJSON(sInsure));
		this.sInsure = sInsure;
	}

	public Value valueOf(String script) {
		return Value.valueOf(FormulaUtil.formulaOf(script), this);
	}

	public Formula formulaOf(String formula) {
		return FormulaUtil.formulaOf(formula);
	}

	@Override
	public Object get(String name) {

		SCustomer insurant = sInsure.getInsurant();
		SCustomer applicant = sInsure.getApplicant();
		InsureFactorChecker insureFactorChecker = new InsureFactorChecker();
		try {
			if("UNDERWRITING_DECISION".equals(name)){
				return insureFactorChecker.checkUnderwritingDecision();
			}

		} catch (Exception e) {

			//TODO 暂时保留，后面删掉
			e.printStackTrace();
		}

		return super.get(name);
	}

	public static void main(String[] args) {
		SInsure sInsure = new SInsure();
		sInsure.setInsureId("1112123");

		InsureFactors factors = new InsureFactors(sInsure);

		Formula formula = FormulaUtil.formulaOf("true");
		System.out.println(formula.run(factors));
	}

}
