package com.bruno_udemy.service_registy_udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistyUdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistyUdemyApplication.class, args);
	}

}
