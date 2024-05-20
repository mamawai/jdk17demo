package com.example.demo.javaPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Divide divide;

    @RequestMapping("/hello")
    public String getDiv() {
        int div = divide.div(6, 0);
        System.out.println(div);
        return String.valueOf(div);
    }
}
