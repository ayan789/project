package com.ccic.salessapp.common.springfox.autoconfigure;

import com.ccic.salessapp.common.core.web.PlatformErrorController;
import com.ccic.salessapp.common.springfox.ApiDocProperties;
import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConditionalOnProperty(name = "sonic.platform.api.enabled", havingValue = "true")
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(ApiDocProperties.class)
public class PlatformDocConfiguration {
    @Bean
    public Docket docket(ApiDocProperties properties) {
        Predicate<RequestHandler> predicate=(input)-> {
                Class<?> declaringClass=input.declaringClass();
                if(declaringClass==BasicErrorController.class||declaringClass==PlatformErrorController.class){
                    return false;
                }else if(declaringClass.isAnnotationPresent(RestController.class)){
                    return true;
                }
                return declaringClass.isAnnotationPresent(Controller.class);
        };
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(properties)).select()
                .apis(predicate)
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(operationParameters(properties));
    }

    private ApiInfo apiInfo(ApiDocProperties properties) {
        return new ApiInfoBuilder().title(properties.getTitle()).description(properties.getDescription()).version(properties.getVersion()).build();
    }

    private List<Parameter> operationParameters(ApiDocProperties properties){
        Map<String,String> headers=properties.getHeaders();
        if(CollectionUtils.isEmpty(headers))return new ArrayList<>();
        List<Parameter> operationParameters=new ArrayList<>();
        headers.forEach((k,v)->{operationParameters.add(new ParameterBuilder().name(k).description(v).modelRef(new ModelRef("String")).parameterType("header").required(true).build());});
        return operationParameters;
    }

    @Bean
    UiConfiguration uiConfig() {
        //docExpansion:list-展开   none:折叠
        return new UiConfiguration(null, "none", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }
}
