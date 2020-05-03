package example.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// list down applications on Eureka server (i.e. registry server).
//@EnableDiscoveryClient
@EnableEurekaClient
public class DiscoveryClientApp {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientApp.class, args);
	}
}