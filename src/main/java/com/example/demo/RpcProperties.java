package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = "classpath:RpcInterface.properties")
@ConfigurationProperties
public class RpcProperties {
    private String interfaceMap;

    public void setInterfaceMap(String interfaceMap) {
        this.interfaceMap = interfaceMap;
    }

    public String getInterfaceMap() {
        return interfaceMap;
    }

    public String toString() {
        return interfaceMap;
    }
}
