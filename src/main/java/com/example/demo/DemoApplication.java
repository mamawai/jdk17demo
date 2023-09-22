package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@ImportResource("classpath:spring-config.xml")
@SpringBootApplication
public class DemoApplication {

	@Autowired
	MyFrameWork myFrameWork;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
