package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
@Component
public class MyFrameWork {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFrameWork.class);

    private MyFrameWork(){
        this.printProperties();
    }

    private void printProperties() {
        Properties pop = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = pop.entrySet();
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("JVM");
        sb.append(System.lineSeparator());

        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Object,Object> i = iterator.next();
            sb.append(i.getKey());
            sb.append(":");
            sb.append(i.getValue());
            sb.append(System.lineSeparator());
        }
        LOGGER.info(sb.toString());
    }
}
