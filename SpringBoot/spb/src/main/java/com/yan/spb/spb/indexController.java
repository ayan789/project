package com.yan.spb.spb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
	
	 /**
     * 健康检查
     * @return
     */
    @RequestMapping(value = "/healthCheck",method = RequestMethod.GET)
    public String health(){
        return "up";
    }


}
