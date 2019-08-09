package com.neo;


import lombok.Data;
import java.util.Locale;

@Data
public class VMProgressRequest {

    String progressId;
    String currentStep;
    Object content;
    Locale locale;

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return LoginUtil.currentLocale();
    }

    public static VMProgressRequest obtain(String progressId, Object content) {
        return obtain(progressId, content, null);
    }

    public static VMProgressRequest obtain(String progressId, Object content, String currentStep) {
        VMProgressRequest vmProgressRequest = new VMProgressRequest();
        vmProgressRequest.progressId = progressId;
        vmProgressRequest.content = content;
        vmProgressRequest.currentStep = currentStep;
        return vmProgressRequest;
    }
}

