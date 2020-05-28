package com.example.tlyanjiami;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sonic.platform.filter")
@Data
public class FilterProperties {
    private String notDecryptUri;
    private String notLoginUri;
    private String notEncryptUri;
    private boolean decryptSwitch=false;
    private boolean loginSwitch=false;
    private boolean encryptSwitch=false;
    private String specialUri;
}
