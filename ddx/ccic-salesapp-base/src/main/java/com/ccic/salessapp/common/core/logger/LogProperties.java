package com.ccic.salessapp.common.core.logger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

@ConfigurationProperties(prefix = "sonic.platform.log")
@Data
public class LogProperties {

    private String path="/deployments/applog";
    private String logName="daily.log";
    private int maxHistory=60;
    private String level=LogLevel.INFO.name();
}
