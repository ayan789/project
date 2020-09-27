
package com.ccic.salessapp.common.core.web;

import com.ccic.salessapp.common.core.filter.extend.db.PlatformFilterDbControl;
import com.ccic.salessapp.common.core.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "actuator")
public class PlatformRefreshController {
    private final static Logger log=Logger.getLogger(PlatformRefreshController.class);
    @Autowired
    PlatformFilterDbControl control;

    @GetMapping(value = "refresh/const",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpResult refresh() {
        log.info("refresh const");
        control.init();
        return HttpResult.success();
    }

}
