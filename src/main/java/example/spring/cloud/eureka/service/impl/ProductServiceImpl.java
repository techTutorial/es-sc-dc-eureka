package example.spring.cloud.eureka.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import example.spring.cloud.eureka.dto.ExampleProductDto;
import example.spring.cloud.eureka.dto.ExampleProductDto.Availability;
import example.spring.cloud.eureka.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("dcRt")
    private RestTemplate restTemplate;

    private static final String PROD_USER_API_URI = "http://dc-product-user";
    
    @HystrixCommand(fallbackMethod="getProductsArraysFallback")
    @Override
    public List<ExampleProductDto> getProductsArrays() {
    	ResponseEntity<ExampleProductDto[]> response =
  			  restTemplate.getForEntity(PROD_USER_API_URI + "/example/product", ExampleProductDto[].class);
	  	ExampleProductDto[] prodArray = response.getBody();
	  	List<ExampleProductDto> prodList = Arrays.asList(prodArray);
	  	return prodList;
    }

    public List<ExampleProductDto> getProductsArraysFallback() {
    	List<ExampleProductDto> prodList = new ArrayList<>();
    	prodList.add(new ExampleProductDto(0, "Product-0", 0, Availability.SOLD_OUT));
    	return prodList;
    }
    
}
