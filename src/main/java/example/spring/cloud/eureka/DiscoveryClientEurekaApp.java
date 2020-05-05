package example.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
// list down applications on Eureka server (i.e. registry server).
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
public class DiscoveryClientEurekaApp {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientEurekaApp.class, args);
	}

	@LoadBalanced
	@Bean(name = "dcRt")
	public RestTemplate getRestTemplate() {
		// return new RestTemplate();
		// OR
		// how to ensure that below changes are picked > not working > Pending
		// create and set timeout property for Http client and pass it to RestTemplate.
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(10000);
		return new RestTemplate(clientHttpRequestFactory);
	}
	
	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}
	
}