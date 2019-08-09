package com.neo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;
import lerrain.tool.formula.FormulaUtil;
import lerrain.tool.formula.Value;
import lerrain.tool.script.InsureFactors;
import lerrain.tool.script.Stack;
import net.sf.json.JSON;

public class Main {

	public static void main(String[] args) {
//		Stack stack = new Stack();
//		stack.set("aaa", "aaa");
//		FactorsBuild.buildFactors(insureFactors);
//		loadProgress();

//		t0();
//		t1();
//		t2();
//		t3();
//		t4();
//		t5();
//		t6();
//		t7();
		t8();

//		double a = 2.0;
//		double b = 1.1;
//		System.out.println(a - b);

//		double a = 0.11;
//		double b = 2001299.32;
//		System.out.println(a + b);

//		double a = 0.14;
//		double b = 100;
//		System.out.println(a * b);

//		double a = 0.14;
//		double b = 100;
//		System.out.println(a / b);

	}

	private static void t0(){
		double a= 4.0 ;

		Formula f = FormulaUtil.formulaOf("return A1+2.0");
		Factors p = new Factors()
		{
			public Object get(String name)
			{
				System.out.println(name);
				if ("A1".equals(name))
					return  BigDecimal.valueOf(a);
				return null;
			}
		};
		System.out.println(f.run(p));
	}


	private static void t1()
	{
		Formula f = FormulaUtil.formulaOf(
				"if(A1+AGE+1 == 60){\n" +
				"     return 0.2;\n" +
				"}\n" +
				"else if(A1+AGE+1 == 80){\n" +
				"	return 0.8;\n" +
				"}\n" +
				"return 0;");
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				System.out.println(name);
				if ("A1".equals(name))
					return new Integer(30);
				else if ("AGE".equals(name))
					return new Integer(29);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t2()
	{
		Formula f = FormulaUtil.formulaOf("A1 gt 0 ? A1+1 : A1-1");
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				System.out.println(name);
				if ("A1".equals(name))
					return new Integer(2);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t3()
	{
		Formula f = FormulaUtil.formulaOf("round(A1,2)");
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				System.out.println(name);
				if ("A1".equals(name))
					return new Double(1.12345678);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t4()
	{
		Formula f = FormulaUtil.formulaOf("round(A1,2)");
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				System.out.println(name);
				if ("A1".equals(name))
					return new Double(1.12345678);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t5()
	{
		Formula f = FormulaUtil.formulaOf(
				"for (var k = 0;k lt A1;k++){\n" +
				"print(k);\n" +
				"}" );
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				if ("A1".equals(name))
					return new Integer(9);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t6()
	{
		Formula f = FormulaUtil.formulaOf("AGE ge 6 and AGE le 60 and DAILY_INCOME ge 200" );
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				if ("AGE".equals(name))
					return new Integer(16);
				if ("DAILY_INCOME".equals(name))
					return new Integer(101);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t7()
	{
		Formula f = FormulaUtil.formulaOf("PAY_FREQ_CODE == 'half_year' and ANNUAL_PREMIUM lt 24000" );
		Factors p = new Factors() 
		{
			public Object get(String name) 
			{
				if ("PAY_FREQ_CODE".equals(name))
					return new String("half_year");
				if ("ANNUAL_PREMIUM".equals(name))
					return new Integer(23001);
				return null;
			}
		};
		System.out.println(f.run(p));
	}
	
	private static void t8()
	{
		DocumentMgr mgr = new DocumentMgr();

//		Factors factors = new InsureFactors() ;
		InsureFactors insureFactors = new InsureFactors();
		insureFactors.set("payFreqCode","ol");
		List<DocumentDefine> lists = mgr.getDocumentList(insureFactors);


		System.out.println(lists.toString());
	}
	
	 public static void loadVMPage() {
		 	 
		 VMPageRequest request = new VMPageRequest();
	        request.setPageId("proposal_insurant");
//	        if (null != sPayInfo) {
//	            request.setContent(sPayInfo.toPayInfoModel());
//	        } else {
//	            sPayInfo = new SPayInfo();
//	            request.setContent(sPayInfo.toPayInfoModel());
//
//	        }
	        
		    VMService vmService = new VMService();
	        List<FieldModel> fields = vmService.loadVMPage(request);
	        if (fields != null) {
	           System.out.println(com.alibaba.fastjson.JSON.toJSON(fields));
	        }

	    }


	public static void loadProgress() {
		VMProgressRequest request = new VMProgressRequest();
		request.setProgressId("insure");
		request.setLocale(Locale.US);
		request.setCurrentStep("step_uob_information");

		VMService vmService = new VMService();

//		VMProgressRequest vmProgressRequest = VMProgressRequest.obtain("insure", null, "step_fna");

		ProgressModel progressModel = vmService.loadProgressStep(request);
		if (progressModel != null) {
			System.out.println(com.alibaba.fastjson.JSON.toJSON(progressModel));
		}

	}


	public static void loadDocument() {
		InsureFactorsBuild insureFactorsBuild = new InsureFactorsBuild();

	}
	

}
