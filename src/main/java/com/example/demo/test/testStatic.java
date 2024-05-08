package com.example.demo.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class testStatic {

    @Value("${de.afdc.config}")
    private String details;

    private static String value;
    static {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream("application.yml")) {
            properties.load(fis);
            value = properties.getProperty("de.afdc.config");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }
    public String getDetails() {
        return details;
    }


}
