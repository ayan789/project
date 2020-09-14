package com.example.tlcommoncomponents.decrypt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class FilterProperties {
    private String notDecryptUri;
    private String notLoginUri;
    private String notEncryptUri;
    private boolean decryptSwitch=true;
    private boolean loginSwitch=false;
    private boolean encryptSwitch=true;
    private String specialUri;
}
