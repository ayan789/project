package com.example.tlyanprd;

public enum PrdConstants {
	// 利用构造函数传参
	TERMSCONDITIONS("条款须知"),
	INSURANCECLAUSES("保险条款"),
	INSURANCENOTICE("投保须知");

	private String str;

	private PrdConstants(String str) {
		this .str = str.toString();
	}

	@Override
	public String toString() {
		return String.valueOf(this.str);
	}

	public static void main(String[] args) {
		System.out.println(PrdConstants.TERMSCONDITIONS.toString());
	}
}
