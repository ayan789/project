package com.ccic.salessapp.common.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;

import static java.util.Collections.singletonMap;

public class FeignSpringFormEncoder extends SpringFormEncoder{
    public FeignSpringFormEncoder() {
    }

    public FeignSpringFormEncoder(Encoder delegate) {
        super(delegate);
    }

    @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
            if (bodyType.equals(MultipartFile.class)) {
                val file = (MultipartFile) object;
                val data = singletonMap(file.getName(), object);
                super.encode(data, MAP_STRING_WILDCARD, template);
                return;
            } else if (bodyType.equals(MultipartFile[].class)) {
                val file = (MultipartFile[]) object;
                if (file != null) {
                    val data = singletonMap(file.length == 0 ? "" : file[0].getName(), object);
                    super.encode(data, MAP_STRING_WILDCARD, template);
                    return;
                }
            }
            super.encode(object, bodyType, template);
        }
}
