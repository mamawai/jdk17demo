package com.example.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ComponentMainAImpl implements ComponentMain {
    private final String mark = "a";
}
