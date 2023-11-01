package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	static RpcProperties rpcProperties;
	public DemoApplication(RpcProperties rpcProperties) {
		DemoApplication.rpcProperties = rpcProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		call();
	}

	static void call() {
		System.out.println(rpcProperties.toString());
	}
}
