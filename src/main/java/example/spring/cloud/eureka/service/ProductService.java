package example.spring.cloud.eureka.service;

import java.util.List;

import example.spring.cloud.eureka.dto.ExampleProductDto;

public interface ProductService {
	
	public List<ExampleProductDto> getProductsArrays();

}
