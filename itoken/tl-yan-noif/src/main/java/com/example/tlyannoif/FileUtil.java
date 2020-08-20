package com.example.tlyannoif;

import java.io.File;

import com.xiaoleilu.hutool.io.FileTypeUtil;

public class FileUtil {
	public static void main(String[] args) {
		File file = com.xiaoleilu.hutool.io.FileUtil.file("/Users/youths/Desktop/大地行_files/132.js");
		String type = FileTypeUtil.getType(file);
		System.out.println(type);
	}
}
