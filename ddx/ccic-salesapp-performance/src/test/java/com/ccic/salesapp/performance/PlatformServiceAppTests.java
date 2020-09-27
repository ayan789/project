package com.ccic.salesapp.performance;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlatformServiceApp.class)
public class PlatformServiceAppTests {
	@Autowired
	private ApplicationContext applicationContext;
	@Before
	public void beforeMethod(){
	}

	@Test
	@Ignore
	public void contextLoads() {
	}

}
