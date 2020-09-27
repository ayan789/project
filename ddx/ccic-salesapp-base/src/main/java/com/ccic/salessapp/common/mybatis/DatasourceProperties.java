
package com.ccic.salessapp.common.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sonic.platform.datasource")
@Data
public class DatasourceProperties {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClass;
    private int initialSize=8;
    private int minIdle=8;
    private int maxActive=20;
    private int maxWait=6000;
    private int removeAbandonedTimeout=1800;

    private int timeBetweenEvictionRunsMillis=60 * 1000;

    private int minEvictableIdleTimeMillis=300*1000;

    private String validationQuery="select 1";

    private boolean testWhileIdle=true;

    private boolean testOnBorrow=false;

    private boolean testOnReturn=false;

    private boolean poolPreparedStatements=false;

    private int maxPoolPreparedStatementPerConnectionSize=-1;

}