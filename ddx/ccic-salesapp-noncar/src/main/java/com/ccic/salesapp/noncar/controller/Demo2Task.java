//package com.ccic.salesapp.noncar.controller;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import com.ccic.salesapp.noncar.dto.request.planelement.FormulaList;
//
//public class Demo2Task implements Callable<FormulaList> {
//	
//		int planId=12;
//	
//        public FormulaList call(int planId) throws Exception {
//        	FormulaList formula = new FormulaList();
//        	formula.setPlanId(planId);
//            return formula;
//        }
//    
//    
//    public static void main(String[] args) throws Exception {
//    	ExecutorService executor = Executors.newFixedThreadPool(4); 
//    	// 定义任务:
//    	Callable<FormulaList> task = new Demo2Task();
//    	// 提交任务并获得Future:
//    	Future<FormulaList> future = executor.submit(task);
//    	// 从Future获取异步执行返回的结果:
//    	FormulaList result = future.get(); // 可能阻塞
//    	System.out.println(result);
//	}
//}