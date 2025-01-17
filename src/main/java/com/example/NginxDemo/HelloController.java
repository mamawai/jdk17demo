package com.example.NginxDemo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
@Slf4j
public class HelloController {

    /**
     * 使用Nginx进行反向代理
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        String method = request.getMethod();
        log.info("execute HelloController#hello({})", method);
        return "Hello, Nginx In SpringBoot!";
    }
}
