package com.ccic.salessapp.common.feign;

import com.ccic.salessapp.common.core.exception.PlatformCoreException;
import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import java.net.URI;

@Slf4j
public class ProxyLoadBalancerFeignClient extends LoadBalancerFeignClient {
    final static String ERROR="call service %s error with uri %s";
    public ProxyLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
    }

    @Override
    public Response execute(Request request, Request.Options options){
        URI asUri = URI.create(request.url());
        String clientName = asUri.getHost();
        String path=asUri.getPath();
        log.info("you are calling the service {} ,uri {}",clientName,path);
        try {
            return super.execute(request, options);
        } catch (Exception e) {
            log.error(String.format(ERROR,clientName,path), e);
        }
        throw new PlatformCoreException(String.format(ERROR,clientName,path),90009);
    }
}

