package com.ccic.salesapp.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "sonic.platform.gateway")
@Component
public class PltGatewayProperties {

    private Map<String,String> services=new HashMap<>();

    public String getSerName(String serviceId){
        return services.containsKey(serviceId.toUpperCase())?services.get(serviceId.toUpperCase()):serviceId.toUpperCase();
    }
}
