package com.yan.spb.spb;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
 
public class EntFileTest extends TmallApplicationTests {

    @Autowired
    private EntFileService entFileService;
    
    @Test
    public void testGetEntFileById(){
        Assert.assertSame("企业数量有误",500,entFileService.getCount());
    }
 
    @Test
    public void testGetEntFileList(){
        Assert.assertSame("企业数量不为10",10,entFileService.getEntFileList());
    }
}
