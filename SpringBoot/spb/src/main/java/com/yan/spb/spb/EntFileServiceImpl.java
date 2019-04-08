package com.yan.spb.spb;

import org.springframework.stereotype.Service;

@Service
public class EntFileServiceImpl implements EntFileService{

	@Override
	public int getCount() {
		System.out.println("run getCount...");
		return 500;
	}

	@Override
	public int getEntFileList() {
		System.out.println("run getEntFileList...");
		return 500;
	}
 
}
