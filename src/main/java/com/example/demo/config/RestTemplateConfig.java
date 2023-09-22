package com.example.demo.config;

import com.example.demo.test.TestRegisterResolvableDependency;
import com.example.demo.test2.MeBeanPostProcessor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public TestRegisterResolvableDependency testRegisterResolvableDependency(){
        return new TestRegisterResolvableDependency();
    }

    @Bean
    public MeBeanPostProcessor meBeanPostProcessor(){
        return new MeBeanPostProcessor();
    }

}
