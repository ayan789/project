package com.ccic.salesapp.noncar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DemoTask {
	public static void main(String[] args) {
		// 创建一个固定大小的线程池:
		ExecutorService es = Executors.newFixedThreadPool(4);
		List<String> list = new ArrayList<String>();
		list.add("buildSafeguardPlan");
		list.add("buildProductInfo");
		list.add("buildSafeguardDetail");
		list.add("buildClaimStatement");
		list.add("buildTermsAndConditions");
		for (int i = 0; i < list.size(); i++) {
			Task tesk = new Task();
			es.submit(new Task("" + list.get(i)));
		}
		// 关闭线程池:
		es.shutdown();
	}
}

class Task implements Runnable {
	private final String name;

	public Task() {
		this.name = "";
	}

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("start task " + name);
		if (name.equals("buildSafeguardPlan")) {
			//System.out.println("buildSafeguardPlan");
		} else if (name.equals("buildProductInfo")) {
			//System.out.println("buildProductInfo");
		} else if (name.equals("buildSafeguardDetail")) {
			//System.out.println("buildSafeguardDetail");
		} else if (name.equals("buildClaimStatement")) {
			//System.out.println("buildClaimStatement");
		} else if (name.equals("buildTermsAndConditions")) {
			//System.out.println("buildTermsAndConditions");
		} 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("end task " + name);
	}
}
