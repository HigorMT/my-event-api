package com.myevent.core.configuration;

import feign.Feign;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.Logger.Level.FULL;

@Configuration
public class FeignBuilderConfig {

    @Bean
    public Feign.Builder feignBuilder(ObjectFactory<HttpMessageConverters> converters) {
        return Feign.builder()
                .contract(new org.springframework.cloud.openfeign.support.SpringMvcContract())
                .encoder(new SpringFormEncoder(new SpringEncoder(converters)))
                .logger(new CustomFeignRequestLogging())
                .logLevel(FULL);
    }

}


