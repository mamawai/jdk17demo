package com.example;

import com.example.demo.RpcProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 用于发现 mapper 接口，加了这个就不用加@Mapper注解了 虽然加了也不会冲突只是有点冗余
@MapperScan("com.example.springStudy.persistence")
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
