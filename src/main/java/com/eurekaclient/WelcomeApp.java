package com.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// Below annotation is used to list down the application on the eureka server (i.e. the registry server).
//@EnableDiscoveryClient
@EnableEurekaClient
public class WelcomeApp {

	public static void main(String[] args) {
		SpringApplication.run(WelcomeApp.class, args);
	}
}