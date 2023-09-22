package com.example.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ComponentMainBImpl implements ComponentMain {
    private final String mark = "b";
}
