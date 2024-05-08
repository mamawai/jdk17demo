package com.example.demo;

import com.example.demo.test.testStatic;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    testStatic testStatic;

    public void setInterfaceMap(String interfaceMap) {
        this.interfaceMap = interfaceMap;
    }

    public String getInterfaceMap() {
        return interfaceMap;
    }

    public String toString() {
        System.out.println(testStatic.getDetails());
        return interfaceMap;
    }
}
